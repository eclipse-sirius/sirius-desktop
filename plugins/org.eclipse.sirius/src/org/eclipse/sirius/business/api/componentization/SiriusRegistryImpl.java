/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.componentization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.description.Component;
import org.eclipse.sirius.description.Group;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.Sirius;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tools.internal.uri.SiriusProtocolException;
import org.eclipse.sirius.tools.internal.uri.SiriusProtocolParser;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;

public class SiriusRegistryImpl extends SiriusRegistry {
    private static final String UNABLE_TO_LOAD_THIS_FILE = "The viewpoint registry was not able to load this file ";

    private ResourceSet resourceSet;

    private Set<Sirius> viewpointsFromPlugin;

    private Set<Sirius> viewpointsFromWorkspace;

    private Set<SiriusRegistryFilter> filters;

    private Set<SiriusRegistryListener> listeners;

    private Set<SiriusRegistryListener2> newListeners;

    private ECrossReferenceAdapter crossReferencer;

    private boolean shouldInvalidateCache;

    private Map<EObject, EObject> foundCache = prepareFoundCache();

    private final DiagramDescriptionMappingsRegistry mappingsRegistry = DiagramDescriptionMappingsRegistry.INSTANCE;

    private Map<String, SiriusFileCollector> collectors;

    /**
     * Avoid instantiation.
     */
    public SiriusRegistryImpl() {
        collectors = Maps.newHashMap();
        collectors.put(SiriusUtil.DESCRIPTION_MODEL_EXTENSION, new SiriusFileCollector() {

            public boolean isValid(final EObject descRoot) {
                boolean result;
                if (descRoot instanceof Group) {
                    result = true;
                } else {
                    result = false;
                    if (descRoot == null) {
                        // Nothing, already been log
                    } else {
                        SiriusPlugin.getDefault().warning("The viewpoint specification model : " + descRoot.eResource().getURI() + " can't be loaded, it may need to be migrated.",
                                new RuntimeException("Can't deploy VSM."));
                    }
                }
                return result;
            }

            public Collection<Sirius> collect(EObject root) {
                return Lists.newArrayList(Iterators.filter(root.eAllContents(), Sirius.class));
            }
        });
        prepareFoundCache();
    }

    /**
     * Add a viewpoint file collector for a given file extension.
     * 
     * @param fileExtension
     *            the file extension.
     * @param collector
     *            the collector responsible for providing the viewpoints from a
     *            loaded model having the given file extension.
     */
    public void addSiriusFileCollector(String fileExtension, SiriusFileCollector collector) {
        this.collectors.put(fileExtension, collector);
    }

    /**
     * Init the registry with a given size.
     * 
     * @param size
     *            the initial size.
     */
    public void init(final int size) {
        this.viewpointsFromPlugin = new HashSet<Sirius>(size);
        this.viewpointsFromWorkspace = new HashSet<Sirius>(size);
        this.resourceSet = new ResourceSetImpl();

        crossReferencer = new ECrossReferenceAdapter();
        this.resourceSet.eAdapters().add(crossReferencer);

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);

        initSiriussFromPlugins();
        refreshComponentsFromWorkspace();
    }

    /**
     * Get the viewpoint from a viewpoint uri.
     * 
     * @param viewpointUri
     *            the viewpoint uri. It should used viewpoint protocol.
     * @return the viewpoint if found, throw an exception otherwise
     * @throws SiriusProtocolException
     *             if the uri could not be parsed or the viewpoint could not be
     *             found
     * @since 2.7
     */
    public Sirius getSirius(final URI viewpointUri) throws SiriusProtocolException {
        return SiriusProtocolParser.getSirius(viewpointUri);
    }

    /**
     * Get the mappings registry to retrieve mappings.
     * 
     * @return the mappings registry
     * @since 2.2
     */
    public DiagramDescriptionMappingsRegistry getDiagramDescriptionMappingsRegistry() {
        return this.mappingsRegistry;
    }

    private void invalidateCache() {
        mappingsRegistry.computeMappings();
        prepareFoundCache();
    }

    private ConcurrentMap<EObject, EObject> prepareFoundCache() {
        return new MapMaker().weakKeys().makeComputingMap(new Function<EObject, EObject>() {

            public EObject apply(EObject from) {
                return lookForEquivalentInRegistry(from);
            }
        });
    }

    /**
     * Add a filter on the registry.
     * 
     * @see SiriusRegistry#getSiriuss()
     * @param filter
     *            the filter to add;
     * @return <code>true</code> if the filter was added, <code>false</code>
     *         otherwise.
     */
    public boolean addFilter(final SiriusRegistryFilter filter) {
        if (filters == null) {
            filters = new HashSet<SiriusRegistryFilter>(4);
        }
        invalidateCache();
        return filters.add(filter);
    }

    /**
     * Remove a filter from the registry.
     * 
     * @param filter
     *            the filter to remove
     * @return <code>true</code> if removed, <code>false</code> otherwise.
     */
    public boolean removeFilter(final SiriusRegistryFilter filter) {
        invalidateCache();
        if (filters != null) {
            return filters.remove(filter);
        }
        return false;
    }

    /**
     * Remove filters on the registry based on their id.
     * 
     * @param id
     *            the id of the filters to remove
     */
    public void removeFilter(final String id) {

        if (filters != null) {
            invalidateCache();
            final HashSet<SiriusRegistryFilter> toRemove = new HashSet<SiriusRegistryFilter>(filters.size());
            final Iterator<SiriusRegistryFilter> it = filters.iterator();
            while (it.hasNext()) {
                final SiriusRegistryFilter filter = it.next();
                if (filter.getId().equals(id)) {
                    toRemove.add(filter);
                }
            }
            filters.removeAll(toRemove);
        }
    }

    /**
     * Add a listener on the registry.
     * 
     * @param listener
     *            the listener to add;
     * @return <code>true</code> if the listener was added, <code>false</code>
     *         otherwise.
     */
    @Deprecated
    public boolean addListener(final SiriusRegistryListener listener) {
        if (listeners == null) {
            listeners = new HashSet<SiriusRegistryListener>(4);
        }
        return listeners.add(listener);
    }

    /**
     * Add a listener on the registry.
     * 
     * @param listener
     *            the listener to add;
     * @return <code>true</code> if the listener was added, <code>false</code>
     *         otherwise.
     */
    public boolean addListener(final SiriusRegistryListener2 listener) {
        if (newListeners == null) {
            newListeners = new HashSet<SiriusRegistryListener2>(4);
        }
        return newListeners.add(listener);
    }

    /**
     * Remove a listener from the registry.
     * 
     * @param listener
     *            the listener to remove
     * @return <code>true</code> if removed, <code>false</code> otherwise.
     */
    @Deprecated
    public boolean removeListener(final SiriusRegistryListener listener) {
        if (listeners != null) {
            return listeners.remove(listener);
        }
        return false;
    }

    /**
     * Remove a listener from the registry.
     * 
     * @param listener
     *            the listener to remove
     * @return <code>true</code> if removed, <code>false</code> otherwise.
     */
    public boolean removeListener(final SiriusRegistryListener2 listener) {
        if (newListeners != null) {
            return newListeners.remove(listener);
        }
        return false;
    }

    /**
     * Register all components in the resource. This method should be called on
     * org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     * 
     * @param modelerModelResourcePath
     *            the platform file path ("pluginname/rep1/rep2/file.odesign)
     * @return the added Siriuss;
     */
    public Set<Sirius> registerFromPlugin(final String modelerModelResourcePath) {

        final Set<Sirius> addedSirius = new HashSet<Sirius>();

        try {
            final URI fileURI = URI.createPlatformPluginURI(modelerModelResourcePath, true);
            final EObject root = load(fileURI, resourceSet);
            Option<SiriusFileCollector> collector = getCollectorFromURI(fileURI);
            if (collector.some() && collector.get().isValid(root)) {
                for (final Sirius viewpoint : collector.get().collect(root)) {
                    viewpointsFromPlugin.add(viewpoint);
                    mapToSiriusProtocol(viewpoint);
                    addedSirius.add(viewpoint);
                }

                /* add cross referencer */
                addCrossReferencer(root.eResource());
            } else {
                unloadAndRemove(fileURI);
            }

        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(UNABLE_TO_LOAD_THIS_FILE + modelerModelResourcePath, e);
        } catch (final WrappedException e) {
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + modelerModelResourcePath, e.exception());
            /* CHECKSTYLE:OFF -> we should handle this kind of exception */
        } catch (final RuntimeException e) {
            /* CHECKSTYLE:ON */
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + modelerModelResourcePath, e);
        }
        invalidateCache();
        return addedSirius;
    }

    private Option<SiriusFileCollector> getCollectorFromURI(URI fileURI) {
        Option<SiriusFileCollector> result = Options.newNone();
        if (!StringUtil.isEmpty(fileURI.fileExtension())) {
            result = Options.newSome(collectors.get(fileURI.fileExtension()));
        }
        return result;
    }

    private Option<SiriusFileCollector> getCollectorFromIFile(IFile file) {
        Option<SiriusFileCollector> result = Options.newNone();
        if (!StringUtil.isEmpty(file.getFileExtension())) {
            result = Options.newSome(collectors.get(file.getFileExtension()));
        }
        return result;
    }

    /**
     * Registers all components from the workspace. All previously registered
     * components will be disposed.
     * 
     * @param components
     *            the viewpoints to register.
     * @param <T>
     *            the type
     */
    public <T extends Component> void registerFromWorkspace(final Set<T> components) {
        viewpointsFromWorkspace.clear();
        for (final Component c : components) {
            if (c instanceof Sirius) {
                Sirius viewpoint = (Sirius) c;
                viewpointsFromWorkspace.add(viewpoint);
                mapToSiriusProtocol(viewpoint);
            }
        }
        invalidateCache();
    }

    /**
     * Dispose a {@link Sirius}. This method should be called on
     * {@link org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)}
     * 
     * @param viewpoint
     *            the viewpoint to dispose
     */
    public void disposeFromPlugin(final Sirius viewpoint) {
        viewpointsFromPlugin.remove(viewpoint);

        // remap the viewpoint uri to another viewpoint with same
        // viewpointUri if exists
        URI vpURI = SiriusProtocolParser.buildSiriusUri(EcoreUtil.getURI(viewpoint));
        URI mappedURI = URIMappingRegistryImpl.INSTANCE.get(vpURI);

        if (mappedURI != null && mappedURI.isPlatformPlugin() && viewpoint.eResource() != null && mappedURI.toPlatformString(true).equals(viewpoint.eResource().getURI().toPlatformString(true))) {
            final String vpName = viewpoint.getName();
            final String pluginName = viewpoint.eResource().getURI().segment(1);

            if (vpName != null && pluginName != null) {
                Iterable<Sirius> sameNameAndPluginSiriuss = Iterables.filter(viewpointsFromPlugin, new Predicate<Sirius>() {

                    public boolean apply(final Sirius input) {
                        return vpName.equals(input.getName()) && pluginName.equals(input.eResource().getURI().segment(1));
                    }
                });

                for (Sirius sameNameAndPluginSirius : sameNameAndPluginSiriuss) {
                    mapToSiriusProtocol(sameNameAndPluginSirius);
                    break;
                }
            }
        }

        for (final Sirius viewpointFromPlugin : viewpointsFromPlugin) {
            if (viewpointFromPlugin.eResource().equals(viewpoint.eResource())) {
                return;
            }
        }

        /*
         * resource does not contribute anymore viewpoints => remove cross
         * referencer
         */
        removeCrossReferencer(viewpoint.eResource());
        invalidateCache();

    }

    /**
     * Return an unmodifiable set of the current registered viewpoints.
     * 
     * @return the viewpoints registered
     */
    public Set<Sirius> getSiriuss() {
        final Set<Sirius> all = new HashSet<Sirius>(this.viewpointsFromPlugin.size() + this.viewpointsFromWorkspace.size());
        all.addAll(this.viewpointsFromWorkspace);
        all.addAll(this.viewpointsFromPlugin);

        if (filters != null) {
            final Set<Sirius> toRemove = new LinkedHashSet<Sirius>();
            for (final SiriusRegistryFilter filter : filters) {
                /*
                 * Filter viewpoints from workspace
                 */
                for (final Sirius viewpoint : this.viewpointsFromWorkspace) {
                    if (filter.filter(viewpoint)) {
                        toRemove.add(viewpoint);
                    }
                }
                /*
                 * Filter viewpoints from plug-in
                 */
                for (final Sirius viewpoint : this.viewpointsFromPlugin) {
                    if (filter.filter(viewpoint)) {
                        toRemove.add(viewpoint);
                    }
                }
            }
            /*
             * Remove filtered viewpoints
             */
            all.removeAll(toRemove);
        }
        return Collections.unmodifiableSet(all);
    }

    /**
     * Returns the viewpoint which defines the specified representation
     * description.
     * 
     * @param description
     *            the representation description.
     * @return the Sirius which defines the representation description, or
     *         <code>null</code> if it could not be found.
     * @since 2.3
     */
    public Sirius getSirius(final RepresentationDescription description) {
        return new RepresentationDescriptionQuery(description).getParentSirius();
    }

    /**
     * Check if a viewpoint comes from plug-in.
     * 
     * @param viewpoint
     *            the viewpoint to check.
     * @return <code>true</code> if the plug-in comes from plug-in false if it
     *         comes from workspace.
     */
    public boolean isFromPlugin(final Sirius viewpoint) {
        if (viewpointsFromPlugin != null) {
            return viewpointsFromPlugin.contains(viewpoint);
        }
        return false;
    }

    /**
     * Dispose the registry.
     */
    public void dispose() {
        disposeSiriussFromPlugins();
        viewpointsFromWorkspace.clear();
        if (filters != null) {
            filters.clear();
        }
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.removeResourceChangeListener(this);
        invalidateCache();
    }

    /**
     * Dispose the viewpoint from plug-ins from registry.
     */
    protected void disposeSiriussFromPlugins() {
        viewpointsFromPlugin.clear();
        invalidateCache();
    }

    /**
     * Scan all the workspaces and update the registry with added and removed
     * components.
     */
    private void refreshComponentsFromWorkspace() {
        /*
         * Here we scan the workspaces to update the Sirius registry
         */
        final List<IFile> files = collectFilesContainingSiriuss();
        final Iterator<IFile> fileIt = files.iterator();
        final Set<Sirius> viewpoints = new HashSet<Sirius>();
        final Set<Resource> resourcesToResolve = new HashSet<Resource>();
        while (fileIt.hasNext()) {
            final IFile file = fileIt.next();

            EObject descRoot = load(file, resourceSet);

            Option<SiriusFileCollector> collector = getCollectorFromIFile(file);

            if (collector.some() && collector.get().isValid(descRoot)) {
                viewpoints.addAll(collector.get().collect(descRoot));
                mapToSiriusProtocol(collector.get().collect(descRoot));
                resourcesToResolve.add(descRoot.eResource());
            } else {
                unloadAndRemove(file);
            }
        }

        final Iterator<Resource> resourceIt = resourcesToResolve.iterator();
        while (resourceIt.hasNext()) {
            final Resource res = resourceIt.next();
            /* add cross referencer */
            addCrossReferencer(res);

            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.RESOLVE_ALL_KEY);
            try {
                ModelUtils.resolveAll(resourceSet);
                // CHECKSTYLE:OFF
            } catch (final RuntimeException e) {
                // CHECKSTYLE:ON
                SiriusPlugin.getDefault().error(UNABLE_TO_LOAD_THIS_FILE + res.getURI().toString(), e);
            }
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        }

        final Set<Component> components = new HashSet<Component>(viewpoints.size());
        components.addAll(viewpoints);
        this.registerFromWorkspace(components);
    }

    private List<IFile> collectFilesContainingSiriuss() {
        return EclipseUtil.getFilesFromWorkspace(null, "." + SiriusUtil.DESCRIPTION_MODEL_EXTENSION);
    }

    private void mapToSiriusProtocol(Collection<Sirius> viewpoints) {
        for (Sirius viewpoint : viewpoints) {
            mapToSiriusProtocol(viewpoint);
        }
    }

    private void mapToSiriusProtocol(Sirius viewpoint) {
        final Resource resource = viewpoint.eResource();
        if (resource != null && resource.getURI() != null) {
            URI uri = EcoreUtil.getURI(viewpoint);
            URIMappingRegistryImpl.INSTANCE.put(SiriusProtocolParser.buildSiriusUri(uri), resource.getURI());
        }
    }

    private void unloadAndRemove(final IFile file) {
        unloadAndRemove(URI.createPlatformResourceURI(file.getFullPath().toOSString(), true));
    }

    private void unloadAndRemove(final URI fileURI) {

        Resource resource = resourceSet.getResource(fileURI, true);
        unloadAndRemove(resource);

    }

    private void unloadAndRemove(final Resource resource) {
        if (resource != null) {
            try {
                removeCrossReferencer(resource);
            } catch (final ClassCastException e) {
                /*
                 * the cross reference remove may fail, but we should unload the
                 * resource
                 */
            }
            try {
                resource.unload();
            } catch (final IllegalArgumentException e) {
                SiriusPlugin.getDefault().error("The viewpoint registry was not able to unload this file " + resource.getURI().toString(), e);
            } catch (final NullPointerException e) {
                SiriusPlugin.getDefault().error("The viewpoint registry was not able to unload this file " + resource.getURI().toString(), e);
            }
            resourceSet.getResources().remove(resource);
        }
    }

    private EObject load(final IFile file, final ResourceSet set) {
        try {
            final URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
            return load(fileURI, set);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(UNABLE_TO_LOAD_THIS_FILE + file.getName(), e);
        } catch (final WrappedException e) {
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + file.getName(), e.exception());
            /* CHECKSTYLE:OFF -> we should handle this kind of exception */
        } catch (final RuntimeException e) {
            /* CHECKSTYLE:ON */
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + file.getName(), e);
        }
        return null;
    }

    /* CHECKSTYLE:OFF -> this method may throw runtime exception */
    private EObject load(final URI fileURI, final ResourceSet set) throws IOException, WrappedException, RuntimeException {
        final Resource loaded = set.getResource(fileURI, true);
        loaded.load(Collections.EMPTY_MAP);
        return loaded.getContents().get(0);
        /* CHECKSTYLE:ON */
    }

    /**
     * Should be called only at initialization or if user want to reload all
     * Sirius plug-ins (all diagrams should be closed) !.
     */
    private void initSiriussFromPlugins() {
        /* should be sufficient to load one class of each plug-in */
        EclipseUtil.getExtensionPlugins(ISiriusComponent.CLASS_TO_EXTEND, ISiriusComponent.ID, ISiriusComponent.CLASS_ATTRIBUTE);
        invalidateCache();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    public void resourceChanged(final IResourceChangeEvent event) {
        /*
         * Refresh the registry when an odesign resource changed, is added or
         * removed.
         */

        this.refreshWorkspaceComponents(event.getDelta());
        if (shouldInvalidateCache) {
            invalidateCache();
            if (newListeners != null) {
                for (final SiriusRegistryListener2 listener : newListeners) {
                    listener.modelerDesciptionFilesLoaded();
                }
            }
            shouldInvalidateCache = false;
        }

    }

    /**
     * Refreshes components that come from the workspace with the given resource
     * delta.
     * 
     * @param resourceDelta
     *            a resource delta.
     */
    private void refreshWorkspaceComponents(final IResourceDelta resourceDelta) {

        final IResource resource = resourceDelta.getResource();
        if (resource.getType() == IResource.FILE) {
            final IFile file = (IFile) resourceDelta.getResource();
            if (mightContainSiriuss(resource)) {
                switch (resourceDelta.getKind()) {
                case IResourceDelta.ADDED:
                    this.fileAdded(file);
                    break;
                case IResourceDelta.REMOVED:
                    this.fileRemoved(file);
                    shouldInvalidateCache = true;
                    break;
                case IResourceDelta.REPLACED:
                    this.fileReplaced(file);
                    shouldInvalidateCache = true;
                    break;
                case IResourceDelta.CHANGED:
                    if (!onlyMarkerChanged(resourceDelta)) {
                        this.fileDeltaChanged(file);
                        shouldInvalidateCache = true;
                    }
                    break;
                case IResourceDelta.CONTENT:
                    this.fileDeltaChanged(file);
                    shouldInvalidateCache = true;
                    break;
                default:
                    // do nothing.
                    break;
                }
            }
        }

        for (final IResourceDelta child : resourceDelta.getAffectedChildren()) {
            refreshWorkspaceComponents(child);
        }
    }

    private boolean mightContainSiriuss(final IResource resource) {
        return (resource instanceof IFile) && getCollectorFromIFile((IFile) resource).some();
    }

    private boolean onlyMarkerChanged(final IResourceDelta resourceDelta) {
        return resourceDelta.getMarkerDeltas().length != 0;
    }

    /**
     * Invoked when a odesign file is added.
     * 
     * @param file
     *            the added file.
     */
    protected void fileAdded(final IFile file) {

        shouldInvalidateCache = true;

        /* Add all viewpoints into the registry. */
        final EObject descRoot = load(file, resourceSet);
        Option<SiriusFileCollector> collector = getCollectorFromIFile(file);
        if (collector.some() && collector.get().isValid(descRoot)) {
            viewpointsFromWorkspace.addAll(collector.get().collect(descRoot));
            mapToSiriusProtocol(collector.get().collect(descRoot));

            /* add cross referencer */
            addCrossReferencer(descRoot.eResource());
        } else {
            unloadAndRemove(file);
        }
    }

    /**
     * Invoked when a odesign file changed.
     * 
     * @param file
     *            the file which changed.
     */
    private void fileDeltaChanged(final IFile file) {
        shouldInvalidateCache = true;
        reloadFile(file);
        if (listeners != null) {
            for (final SiriusRegistryListener listener : listeners) {
                listener.modelerDesciptionFileReloaded(file);
            }
        }
    }

    /**
     * Invoked when a resource is replaced (AFTER STATE).
     * 
     * @param resource
     *            the replaced resource (AFTER STATE).
     */
    private void fileReplaced(final IFile file) {
        shouldInvalidateCache = true;
        reloadFile(file);
        if (listeners != null) {
            for (final SiriusRegistryListener listener : listeners) {
                listener.modelerDesciptionFileReloaded(file);
            }
        }
    }

    /**
     * Invoked when a odesign file is removed.
     * 
     * @param file
     *            the removed file.
     */
    protected void fileRemoved(final IFile file) {
        shouldInvalidateCache = true;
        if (!file.exists()) {
            removeSiriussNotPersistedInAFile();
            unloadInExistingSessions(file, true);
        } else {
            /* remove affected viewpoints */
            final EObject descRoot = load(file, resourceSet);
            Option<SiriusFileCollector> collector = getCollectorFromIFile(file);

            if (collector.some() && collector.get().isValid(descRoot)) {
                for (final Sirius toRemove : collector.get().collect(descRoot)) {
                    for (final Sirius loadedSirius : new ArrayList<Sirius>(this.viewpointsFromWorkspace)) {
                        if (EqualityHelper.areEquals(toRemove, loadedSirius)) {
                            removeFromWorkspaceAndUpdateURiMapping(toRemove);
                        }
                    }
                }
            }
            unloadAndRemove(file);
            unloadInExistingSessions(file, true);
        }
    }

    /**
     * Removes all viewpoints from workspace that are not persisted in a file.
     */
    private void removeSiriussNotPersistedInAFile() {
        for (final Sirius viewpoint : new ArrayList<Sirius>(this.viewpointsFromWorkspace)) {
            if (viewpoint.eResource() == null) {
                removeFromWorkspaceAndUpdateURiMapping(viewpoint);
            } else {
                final Path path = new Path(viewpoint.eResource().getURI().toPlatformString(true));
                final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
                if (!file.exists()) {
                    removeFromWorkspaceAndUpdateURiMapping(viewpoint);
                }
            }
        }
    }

    private void reloadFile(final IFile odesignFile) {
        /* Removes all viewpoints. */
        for (final Sirius viewpoint : new ArrayList<Sirius>(this.viewpointsFromWorkspace)) {
            final Resource resource = viewpoint.eResource();
            if (resource != null && resource.getURI().toPlatformString(true).equals(odesignFile.getFullPath().toString())) {
                /* Removes this viewpoint */
                removeFromWorkspaceAndUpdateURiMapping(viewpoint);
            }
        }

        unloadAndRemove(odesignFile);

        /* load new viewpoints. */
        final EObject descRoot = load(odesignFile, resourceSet);
        Option<SiriusFileCollector> collector = getCollectorFromIFile(odesignFile);
        if (collector.some() && collector.get().isValid(descRoot)) {
            viewpointsFromWorkspace.addAll(collector.get().collect(descRoot));
            mapToSiriusProtocol(collector.get().collect(descRoot));

            unloadInExistingSessions(odesignFile, false);

            /* add cross referencer */
            addCrossReferencer(descRoot.eResource());
        } else {
            unloadAndRemove(odesignFile);
        }
    }

    private void unloadInExistingSessions(final IFile odesignFile, boolean remove) {
        /* Reload EditingDomain of all Sessions. */
        for (Session session : SessionManager.INSTANCE.getSessions()) {
            ResourceSet sessionResourceSet = session.getTransactionalEditingDomain().getResourceSet();
            for (final Resource resource : Lists.newArrayList(sessionResourceSet.getResources())) {
                final URI resourceURI = resource.getURI();
                if (resourceURI != null) {
                    if (resourceURI.toPlatformString(true) != null && resourceURI.toPlatformString(true).equals(odesignFile.getFullPath().toString())) {
                        resource.unload();
                        if (remove) {
                            sessionResourceSet.getResources().remove(resource);
                        }
                    }
                }
            }
        }
    }

    /**
     * Remove the current viewpoint from viewpointFromWorkspace and update uri
     * mapping to corresponding viewpoint from plugins if exists
     * 
     * @param viewpoint
     */
    private void removeFromWorkspaceAndUpdateURiMapping(Sirius viewpoint) {
        this.viewpointsFromWorkspace.remove(viewpoint);
        URI viewpointUri = SiriusProtocolParser.buildSiriusUri(EcoreUtil.getURI(viewpoint));

        for (Sirius vp : this.viewpointsFromPlugin) {
            if (StringUtil.equals(vp.getName(), viewpoint.getName())) {
                URI vpURI = SiriusProtocolParser.buildSiriusUri(EcoreUtil.getURI(vp));
                if (vpURI != null && viewpointUri != null && StringUtil.equals(vpURI.toString(), viewpointUri.toString())) {
                    mapToSiriusProtocol(vp);
                    return;
                }
            }
        }

        // No found corresponding viewpoint in plugins, clean uri mapping
        URIMappingRegistryImpl.INSTANCE.removeKey(SiriusProtocolParser.buildSiriusUri(viewpointUri));
    }

    /**
     * Get cross referencer installed on modeler description resources.
     * 
     * @return the cross referencer.
     */
    public ECrossReferenceAdapter getCrossReferencer() {
        return crossReferencer;
    }

    /**
     * Find the emf object equals to the given one in the viewpoint registry
     * resource set.
     * 
     * @param eObject
     *            the emf object to look for
     * @return the eObject instance if found, the given object otherwise
     */
    public EObject find(final EObject eObject) {
        return foundCache.get(eObject);
    }

    private EObject lookForEquivalentInRegistry(final EObject eObject) {
        EObject eObj = null;
        try {
            eObj = resourceSet.getEObject(EcoreUtil.getURI(eObject), true);
            if (eObj != null) {
                addCrossReferencer(eObj.eResource());
            }
        } catch (final WrappedException e) {
            /* fail silently */
        }
        return eObj != null ? eObj : eObject;
    }

    private void removeCrossReferencer(final Resource eResource) {
        if (eResource != null) {
            eResource.eAdapters().remove(crossReferencer);
        }
    }

    private void addCrossReferencer(final Resource eResource) {
        if (eResource != null && !eResource.eAdapters().contains(crossReferencer)) {
            eResource.eAdapters().add(crossReferencer);
        }
    }
}
