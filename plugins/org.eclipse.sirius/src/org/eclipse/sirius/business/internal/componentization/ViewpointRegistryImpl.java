/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.componentization;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.componentization.ISiriusComponent;
import org.eclipse.sirius.business.api.componentization.ViewpointFileCollector;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryFilter;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryListener2;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tools.internal.uri.ViewpointProtocolException;
import org.eclipse.sirius.tools.internal.uri.ViewpointProtocolParser;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class ViewpointRegistryImpl extends ViewpointRegistry {
    private ResourceSet resourceSet;

    private Map<URI, Viewpoint> viewpointsFromPlugin;

    private Set<Viewpoint> viewpointsFromWorkspace;

    private Set<ViewpointRegistryFilter> filters;

    private Set<ViewpointRegistryListener2> newListeners;

    private ECrossReferenceAdapter crossReferencer;

    private boolean shouldInvalidateCache;

    private LoadingCache<EObject, EObject> foundCache = prepareFoundCache();

    private Map<String, ViewpointFileCollector> collectors;

    /**
     * Avoid instantiation.
     */
    public ViewpointRegistryImpl() {
        collectors = new HashMap<>();
        collectors.put(SiriusUtil.DESCRIPTION_MODEL_EXTENSION, new ViewpointFileCollector() {

            @Override
            public boolean isValid(final EObject descRoot) {
                boolean result = false;
                EList<Diagnostic> errors = descRoot.eResource().getErrors();
                if (errors.isEmpty()) {
                    if (descRoot instanceof Group) {
                        result = true;
                    } else {
                        if (descRoot == null) {
                            // Nothing, already been log
                        } else {
                            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.ViewpointRegistryImpl_cantLoadVSMErrorMsg, descRoot.eResource().getURI()),
                                    new RuntimeException(Messages.ViewpointRegistryImpl_cantDeployVSMErrorMsg));
                        }
                    }
                } else {
                    for (Diagnostic diagnostic : errors) {
                        SiriusPlugin.getDefault().warning(diagnostic.getMessage(), new RuntimeException(Messages.ViewpointRegistryImpl_cantDeployVSMErrorMsg));
                    }
                }
                return result;
            }

            @Override
            public Collection<Viewpoint> collect(EObject root) {
                return Lists.newArrayList(Iterators.filter(root.eAllContents(), Viewpoint.class));
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
    public void addViewpointFileCollector(String fileExtension, ViewpointFileCollector collector) {
        this.collectors.put(fileExtension, collector);
    }

    /**
     * Add a viewpoint file collector for a given file extension.
     * 
     * @param fileExtension
     *            the file extension.
     * @param collector
     *            the collector responsible for providing the viewpoints from a
     *            loaded model having the given file extension.
     * @deprecated use
     *             {@link ViewpointRegistryImpl#addViewpointFileCollector(String, ViewpointFileCollector)}
     *             instead
     */
    @Deprecated
    public void addSiriusFileCollector(String fileExtension, ViewpointFileCollector collector) {
        addViewpointFileCollector(fileExtension, collector);
    }

    /**
     * Init the registry with a given size.
     * 
     * @param size
     *            the initial size.
     */
    @Override
    public void init(final int size) {
        this.viewpointsFromPlugin = new LinkedHashMap<>();
        this.viewpointsFromWorkspace = new HashSet<Viewpoint>(size);
        this.resourceSet = new ResourceSetImpl();

        crossReferencer = new ECrossReferenceAdapter();
        this.resourceSet.eAdapters().add(crossReferencer);
        IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
        if (workspaceRoot != null) {
            workspaceRoot.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
        }
        initViewpointsFromPlugins();
        refreshComponentsFromWorkspace();
    }

    /**
     * Get the viewpoint from a viewpoint uri.
     * 
     * @param viewpointUri
     *            the viewpoint uri. It should used viewpoint protocol.
     * @return the viewpoint if found, throw an exception otherwise
     * @throws ViewpointProtocolException
     *             if the uri could not be parsed or the viewpoint could not be
     *             found
     * @since 0.9.0
     */
    @Override
    public Viewpoint getViewpoint(final URI viewpointUri) throws ViewpointProtocolException {
        return ViewpointProtocolParser.getViewpoint(viewpointUri);
    }

    private void invalidateCache() {
        DialectManager.INSTANCE.invalidateMappingCache();
        prepareFoundCache();
    }

    private LoadingCache<EObject, EObject> prepareFoundCache() {
        return CacheBuilder.newBuilder().weakKeys().build(CacheLoader.from(new Function<EObject, EObject>() {
            @Override
            public EObject apply(EObject from) {
                return lookForEquivalentInRegistry(from);
            }
        }));
    }

    /**
     * Add a filter on the registry.
     * 
     * @see ViewpointRegistry#getViewpoints()
     * @param filter
     *            the filter to add;
     * @return <code>true</code> if the filter was added, <code>false</code>
     *         otherwise.
     */
    @Override
    public boolean addFilter(final ViewpointRegistryFilter filter) {
        if (filters == null) {
            filters = new HashSet<ViewpointRegistryFilter>(4);
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
    @Override
    public boolean removeFilter(final ViewpointRegistryFilter filter) {
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
    @Override
    public void removeFilter(final String id) {

        if (filters != null) {
            invalidateCache();
            final HashSet<ViewpointRegistryFilter> toRemove = new HashSet<ViewpointRegistryFilter>(filters.size());
            final Iterator<ViewpointRegistryFilter> it = filters.iterator();
            while (it.hasNext()) {
                final ViewpointRegistryFilter filter = it.next();
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
    @Override
    public boolean addListener(final ViewpointRegistryListener2 listener) {
        if (newListeners == null) {
            newListeners = new HashSet<ViewpointRegistryListener2>(4);
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
    @Override
    public boolean removeListener(final ViewpointRegistryListener2 listener) {
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
     * @return the added Viewpoints;
     */
    @Override
    public Set<Viewpoint> registerFromPlugin(final String modelerModelResourcePath) {

        final Set<Viewpoint> addedViewpoints = new HashSet<Viewpoint>();

        try {
            final URI resourceURI = URI.createPlatformPluginURI(modelerModelResourcePath, true);
            Resource resource = resourceSet.getResource(resourceURI, false);
            if (resource != null) {
                unloadAndRemove(resource);
            }
            final EObject root = load(resourceURI, resourceSet);
            Option<ViewpointFileCollector> collector = getCollectorFromURI(resourceURI);
            if (collector.some() && collector.get().isValid(root)) {
                for (final Viewpoint viewpoint : collector.get().collect(root)) {
                    Option<URI> uri = new ViewpointQuery(viewpoint).getViewpointURI();
                    if (uri.some()) {
                        viewpointsFromPlugin.put(uri.get(), viewpoint);
                    } else {
                        viewpointsFromPlugin.put(EcoreUtil.getURI(viewpoint), viewpoint);
                    }
                    mapToViewpointProtocol(viewpoint);
                    addedViewpoints.add(viewpoint);
                }
                // needed so that invalidateCache works correctly.
                // DiagramDescriptionMappingsRegistryImpl.cleanDiagramDescriptionNoMoreInResource() required that vp is
                // not in a resource.
                unloadInExistingSessions(resourceURI.toPlatformString(true), false);

                /* add cross referencer */
                addCrossReferencer(root.eResource());
            } else {
                unloadAndRemove(resourceURI);
            }

        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ViewpointRegistryImpl_FileLoadingErrorMsg, modelerModelResourcePath), e);
        } catch (final WrappedException e) {
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.ViewpointRegistryImpl_FileLoadingErrorMsg, modelerModelResourcePath), e.exception());
            /* CHECKSTYLE:OFF -> we should handle this kind of exception */
        } catch (final RuntimeException e) {
            /* CHECKSTYLE:ON */
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.ViewpointRegistryImpl_FileLoadingErrorMsg, modelerModelResourcePath), e);
        }
        invalidateCache();
        return addedViewpoints;
    }
    
    @Override
    public void reloadAllFromPlugins() {
        boolean[] touched = { false };
        this.viewpointsFromPlugin.values().stream().map(vp -> vp.eResource()).distinct().filter(r -> r != null).forEach(res -> {
            URI uri = res.getURI();
            if (uri != null) {
                String path = uri.toPlatformString(true);
                this.registerFromPlugin(path);
                touched[0] = true;
            }
        });
        if (touched[0] && newListeners != null) {
            for (final ViewpointRegistryListener2 listener : newListeners) {
                listener.modelerDesciptionFilesLoaded();
            }
        }
    }

    private Option<ViewpointFileCollector> getCollectorFromURI(URI fileURI) {
        Option<ViewpointFileCollector> result = Options.newNone();
        if (!StringUtil.isEmpty(fileURI.fileExtension())) {
            result = Options.newSome(collectors.get(fileURI.fileExtension()));
        }
        return result;
    }

    private Option<ViewpointFileCollector> getCollectorFromIFile(IFile file) {
        Option<ViewpointFileCollector> result = Options.newNone();
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
    @Override
    public <T extends Component> void registerFromWorkspace(final Set<T> components) {
        viewpointsFromWorkspace.clear();
        for (final Component c : components) {
            if (c instanceof Viewpoint) {
                Viewpoint viewpoint = (Viewpoint) c;
                viewpointsFromWorkspace.add(viewpoint);
                mapToViewpointProtocol(viewpoint);
            }
        }
        invalidateCache();
    }

    /**
     * Dispose a {@link Viewpoint}. This method should be called on
     * {@link org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)}
     * 
     * @param viewpoint
     *            the viewpoint to dispose
     */
    @Override
    public void disposeFromPlugin(final Viewpoint viewpoint) {
        viewpointsFromPlugin.remove(viewpoint);

        // remap the viewpoint uri to another viewpoint with same
        // viewpointUri if exists
        URI vpURI = ViewpointProtocolParser.buildViewpointUri(EcoreUtil.getURI(viewpoint));
        URI mappedURI = URIMappingRegistryImpl.INSTANCE.get(vpURI);

        Resource vpResource = viewpoint.eResource();
        if (mappedURI != null && mappedURI.isPlatformPlugin() && vpResource != null && mappedURI.toPlatformString(true).equals(vpResource.getURI().toPlatformString(true))) {
            final String vpName = viewpoint.getName();
            final String pluginName = vpResource.getURI().segment(1);

            if (vpName != null && pluginName != null) {
                Iterable<Viewpoint> sameNameAndPluginViewpoints = Iterables.filter(viewpointsFromPlugin.values(), new Predicate<Viewpoint>() {

                    @Override
                    public boolean apply(final Viewpoint input) {
                        return vpName.equals(input.getName()) && pluginName.equals(input.eResource().getURI().segment(1));
                    }
                });

                for (Viewpoint sameNameAndPluginSirius : sameNameAndPluginViewpoints) {
                    mapToViewpointProtocol(sameNameAndPluginSirius);
                    break;
                }
            }
        }

        for (final Viewpoint viewpointFromPlugin : viewpointsFromPlugin.values()) {
            if (viewpointFromPlugin.eResource().equals(vpResource)) {
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
    @Override
    public Set<Viewpoint> getViewpoints() {
        final Set<Viewpoint> all = new HashSet<Viewpoint>(this.viewpointsFromPlugin.size() + this.viewpointsFromWorkspace.size());
        all.addAll(this.viewpointsFromWorkspace);
        all.addAll(this.viewpointsFromPlugin.values());

        if (filters != null) {
            final Set<Viewpoint> toRemove = new LinkedHashSet<Viewpoint>();
            for (final ViewpointRegistryFilter filter : filters) {
                /*
                 * Filter viewpoints from workspace
                 */
                for (final Viewpoint viewpoint : this.viewpointsFromWorkspace) {
                    if (filter.filter(viewpoint)) {
                        toRemove.add(viewpoint);
                    }
                }
                /*
                 * Filter viewpoints from plug-in
                 */
                for (final Viewpoint viewpoint : this.viewpointsFromPlugin.values()) {
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
     * @since 0.9.0
     */
    @Override
    public Viewpoint getViewpoint(final RepresentationDescription description) {
        return new RepresentationDescriptionQuery(description).getParentViewpoint();
    }

    /**
     * Check if a viewpoint comes from plug-in.
     * 
     * @param viewpoint
     *            the viewpoint to check.
     * @return <code>true</code> if the plug-in comes from plug-in false if it
     *         comes from workspace.
     */
    @Override
    public boolean isFromPlugin(final Viewpoint viewpoint) {
        if (viewpointsFromPlugin != null) {
            return viewpointsFromPlugin.containsValue(viewpoint);
        }
        return false;
    }

    /**
     * Dispose the registry.
     */
    @Override
    public void dispose() {
        disposeViewpointsFromPlugins();
        viewpointsFromWorkspace.clear();
        if (filters != null) {
            filters.clear();
        }
        IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();
        if (workspaceRoot != null) {
            workspaceRoot.getWorkspace().removeResourceChangeListener(this);
        }
        invalidateCache();
    }

    /**
     * Dispose the viewpoint from plug-ins from registry.
     */
    protected void disposeViewpointsFromPlugins() {
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
        final List<IFile> files = collectFilesContainingViewpoints();
        final Iterator<IFile> fileIt = files.iterator();
        final Set<Viewpoint> viewpoints = new HashSet<Viewpoint>();
        final Set<Resource> resourcesToResolve = new HashSet<Resource>();
        while (fileIt.hasNext()) {
            final IFile file = fileIt.next();

            EObject descRoot = load(file, resourceSet);
            if (descRoot != null) {
                Option<ViewpointFileCollector> collector = getCollectorFromIFile(file);

                if (collector.some() && collector.get().isValid(descRoot)) {
                    viewpoints.addAll(collector.get().collect(descRoot));
                    mapToSiriusProtocol(collector.get().collect(descRoot));
                    resourcesToResolve.add(descRoot.eResource());
                } else {
                    unloadAndRemove(file);
                }
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
                SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ViewpointRegistryImpl_FileLoadingErrorMsg, res.getURI().toString()), e);
            }
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        }

        final Set<Component> components = new HashSet<Component>(viewpoints.size());
        components.addAll(viewpoints);
        this.registerFromWorkspace(components);
    }

    private List<IFile> collectFilesContainingViewpoints() {
        return EclipseUtil.getFilesFromWorkspace(null, "." + SiriusUtil.DESCRIPTION_MODEL_EXTENSION); //$NON-NLS-1$
    }

    private void mapToSiriusProtocol(Collection<Viewpoint> viewpoints) {
        for (Viewpoint viewpoint : viewpoints) {
            mapToViewpointProtocol(viewpoint);
        }
    }

    private void mapToViewpointProtocol(Viewpoint viewpoint) {
        final Resource resource = viewpoint.eResource();
        if (resource != null && resource.getURI() != null) {
            URI uri = EcoreUtil.getURI(viewpoint);
            URIMappingRegistryImpl.INSTANCE.put(ViewpointProtocolParser.buildViewpointUri(uri), resource.getURI());
        }
    }

    private void unloadAndRemove(final IFile file) {
        unloadAndRemove(URI.createPlatformResourceURI(file.getFullPath().toOSString(), true));
    }

    private void unloadAndRemove(final URI resourceURI) {
        Resource resource = resourceSet.getResource(resourceURI, false);
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
                SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ViewpointRegistryImpl_unableToUnloadFileErrorMsg, resource.getURI().toString()), e);
            } catch (final NullPointerException e) {
                SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ViewpointRegistryImpl_unableToUnloadFileErrorMsg, resource.getURI().toString()), e);
            }
            resourceSet.getResources().remove(resource);
        }
    }

    private EObject load(final IFile file, final ResourceSet set) {
        try {
            final URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toOSString(), true);
            return load(fileURI, set);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ViewpointRegistryImpl_FileLoadingErrorMsg, file.getName()), e);
        } catch (final WrappedException e) {
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.ViewpointRegistryImpl_FileLoadingErrorMsg, file.getName()), e.exception());
            /* CHECKSTYLE:OFF -> we should handle this kind of exception */
        } catch (final RuntimeException e) {
            /* CHECKSTYLE:ON */
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.ViewpointRegistryImpl_FileLoadingErrorMsg, file.getName()), e);
        }
        return null;
    }

    /* CHECKSTYLE:OFF -> this method may throw runtime exception */
    private EObject load(final URI resourceURI, final ResourceSet set) throws IOException, WrappedException, RuntimeException {
        final Resource resource = set.getResource(resourceURI, true);
        resource.load(Collections.EMPTY_MAP);
        // see #544563 we resolve the resource set in case viewpoints are not registered in their dependencies order
        // leading to mappings not resolved.
        if (Boolean.getBoolean("org.eclipse.sirius.viewpoint.registry.forceResolveOnLoad")) { //$NON-NLS-1$
            EcoreUtil.resolveAll(set);
        }
        return resource.getContents().get(0);
        /* CHECKSTYLE:ON */
    }

    /**
     * Should be called only at initialization or if user want to reload all
     * Sirius plug-ins (all diagrams should be closed) !.
     */
    private void initViewpointsFromPlugins() {
        /* should be sufficient to load one class of each plug-in */
        EclipseUtil.getExtensionPlugins(ISiriusComponent.CLASS_TO_EXTEND, ISiriusComponent.ID, ISiriusComponent.CLASS_ATTRIBUTE);
        invalidateCache();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    @Override
    public void resourceChanged(final IResourceChangeEvent event) {
        /*
         * Refresh the registry when an odesign resource changed, is added or
         * removed.
         */

        this.refreshWorkspaceComponents(event.getDelta());
        if (shouldInvalidateCache) {
            invalidateCache();
            if (newListeners != null) {
                for (final ViewpointRegistryListener2 listener : newListeners) {
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
            if (mightContainViewpoints(resource)) {
                switch (resourceDelta.getKind()) {
                case IResourceDelta.ADDED:
                    this.fileAdded(file);
                    break;
                case IResourceDelta.REMOVED:
                    this.fileRemoved(file);
                    break;
                case IResourceDelta.REPLACED:
                    this.invalidateCacheAndReloadFile(file);
                    break;
                case IResourceDelta.CHANGED:
                    if (!onlyMarkerChanged(resourceDelta)) {
                        this.invalidateCacheAndReloadFile(file);
                    }
                    break;
                case IResourceDelta.CONTENT:
                    this.invalidateCacheAndReloadFile(file);
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

    private boolean mightContainViewpoints(final IResource resource) {
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
        Option<ViewpointFileCollector> collector = getCollectorFromIFile(file);
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
    private void invalidateCacheAndReloadFile(final IFile file) {
        shouldInvalidateCache = true;
        reloadFile(file);
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
            removeViewpointsNotPersistedInAFile();
            unloadInExistingSessions(file, true);
            unloadAndRemove(file);
        } else {
            /* remove affected viewpoints */
            final EObject descRoot = load(file, resourceSet);
            Option<ViewpointFileCollector> collector = getCollectorFromIFile(file);

            if (collector.some() && collector.get().isValid(descRoot)) {
                for (final Viewpoint toRemove : collector.get().collect(descRoot)) {
                    for (final Viewpoint loadedSirius : new ArrayList<Viewpoint>(this.viewpointsFromWorkspace)) {
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
    private void removeViewpointsNotPersistedInAFile() {
        for (final Viewpoint viewpoint : new ArrayList<Viewpoint>(this.viewpointsFromWorkspace)) {
            Resource viewpointResource = viewpoint.eResource();
            if (viewpointResource == null) {
                removeFromWorkspaceAndUpdateURiMapping(viewpoint);
            } else {
                final Path path = new Path(viewpointResource.getURI().toPlatformString(true));
                final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
                if (!file.exists()) {
                    removeFromWorkspaceAndUpdateURiMapping(viewpoint);
                }
            }
        }
    }

    private void reloadFile(final IFile odesignFile) {
        /* Removes all viewpoints. */
        for (final Viewpoint viewpoint : new ArrayList<Viewpoint>(this.viewpointsFromWorkspace)) {
            final Resource viewpointResource = viewpoint.eResource();
            if (viewpointResource != null && viewpointResource.getURI().toPlatformString(true).equals(odesignFile.getFullPath().toString())) {
                /* Removes this viewpoint */
                removeFromWorkspaceAndUpdateURiMapping(viewpoint);
            }
        }

        unloadAndRemove(odesignFile);

        /* load new viewpoints. */
        final EObject descRoot = load(odesignFile, resourceSet);
        Option<ViewpointFileCollector> collector = getCollectorFromIFile(odesignFile);
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

    private void unloadInExistingSessions(IFile file, boolean remove) {
        unloadInExistingSessions(file.getFullPath().toString(), remove);
    }

    private void unloadInExistingSessions(String resourcePath, boolean remove) {
        /* Reload EditingDomain of all Sessions. */
        for (Session session : SessionManager.INSTANCE.getSessions()) {
            ResourceSet sessionResourceSet = session.getTransactionalEditingDomain().getResourceSet();
            for (final Resource resource : new ArrayList<Resource>(sessionResourceSet.getResources())) {
                final URI resourceURI = resource.getURI();
                if (resourceURI != null) {
                    if (resourceURI.toPlatformString(true) != null && resourceURI.toPlatformString(true).equals(resourcePath)) {
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
    private void removeFromWorkspaceAndUpdateURiMapping(Viewpoint viewpoint) {
        this.viewpointsFromWorkspace.remove(viewpoint);
        URI viewpointUri = ViewpointProtocolParser.buildViewpointUri(EcoreUtil.getURI(viewpoint));

        for (Viewpoint vp : this.viewpointsFromPlugin.values()) {
            if (StringUtil.equals(vp.getName(), viewpoint.getName())) {
                URI vpURI = ViewpointProtocolParser.buildViewpointUri(EcoreUtil.getURI(vp));
                if (vpURI != null && viewpointUri != null && StringUtil.equals(vpURI.toString(), viewpointUri.toString())) {
                    mapToViewpointProtocol(vp);
                    return;
                }
            }
        }

        // No found corresponding viewpoint in plugins, clean uri mapping
        URIMappingRegistryImpl.INSTANCE.removeKey(ViewpointProtocolParser.buildViewpointUri(viewpointUri));
    }

    /**
     * Get cross referencer installed on modeler description resources.
     * 
     * @return the cross referencer.
     */
    @Override
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
    @Override
    public EObject find(final EObject eObject) {
        try {
            return foundCache.get(eObject);
        } catch (ExecutionException e) {
            return eObject;
        }
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
