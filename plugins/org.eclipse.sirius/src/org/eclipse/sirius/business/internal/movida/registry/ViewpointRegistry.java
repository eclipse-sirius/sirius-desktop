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
package org.eclipse.sirius.business.internal.movida.registry;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryFilter;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistryListener2;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.query.ViewpointURIQuery;
import org.eclipse.sirius.business.internal.movida.ViewpointDependenciesTracker;
import org.eclipse.sirius.business.internal.movida.ViewpointResourceOperations;
import org.eclipse.sirius.business.internal.movida.registry.MaskingPolicy.MaskingChange;
import org.eclipse.sirius.business.internal.movida.registry.monitoring.CompositeResourceMonitor;
import org.eclipse.sirius.business.internal.movida.registry.monitoring.LegacyPluginMonitor;
import org.eclipse.sirius.business.internal.movida.registry.monitoring.PluginMonitor;
import org.eclipse.sirius.business.internal.movida.registry.monitoring.ViewpointResourceListener;
import org.eclipse.sirius.business.internal.movida.registry.monitoring.ViewpointResourceMonitor;
import org.eclipse.sirius.business.internal.movida.registry.monitoring.WorkspaceMonitor;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.base.relations.Relation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

/**
 * The registry of all canonical Sirius definitions known in the system.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ViewpointRegistry extends org.eclipse.sirius.business.api.componentization.ViewpointRegistry implements ViewpointResourceListener {
    private static final String UNABLE_TO_LOAD_THIS_FILE = "The viewpoint registry was not able to load this file ";

    /**
     * Internal class used to record entries changes.
     */
    static class RegistryChange {
        Set<URI> removed = Sets.newHashSet();

        Set<URI> added = Sets.newHashSet();

        Set<URI> changed = Sets.newHashSet();

        public RegistryChange normalize() {
            // Elements which were both added and removed are considered as
            // changed.
            this.changed.addAll(Sets.intersection(this.added, this.removed));
            // Make sure the three categories are exclusive.
            this.added.removeAll(this.changed);
            this.removed.removeAll(this.changed);
            return this;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            // CHECKSTYLE:OFF
            sb.append("Removed:\n\t").append(Joiner.on("\n\t").join(removed)).append("\n"); //$NON-NLS-2$ //$NON-NLS-3$
            sb.append("Added:\n\t").append(Joiner.on("\n\t").join(added)).append("\n"); //$NON-NLS-2$ //$NON-NLS-3$
            sb.append("Changed:\n\t").append(Joiner.on("\n\t").join(changed)).append("\n"); //$NON-NLS-2$ //$NON-NLS-3$
            // CHECKSTYLE:ON
            return sb.toString();
        }
    }

    private static final String VIEWPOINT_RESOURCE_TYPE_EXTENSION_POINT = "org.eclipse.sirius.viewpointResourceType"; //$NON-NLS-1$

    /**
     * The monitors from which we find the VSMs.
     */
    private final CompositeResourceMonitor monitors = new CompositeResourceMonitor();

    /**
     * The resource set in which the detected VSMs are all loaded.
     */
    private final ResourceSetImpl resourceSet = new ResourceSetImpl();

    /**
     * The Sirius resource handler used by this registry.
     */
    private final CompositeViewpointResourceHandler compositeResourceHandler = new CompositeViewpointResourceHandler();

    /**
     * The helper used to compute which of the resources and viewpoints loaded
     * are visible or masked.
     */
    private final MaskingPolicy maskingPolicy = new MaskingPolicy(monitors, compositeResourceHandler);

    /**
     * The entries in the registry, indexed by their Sirius URI
     * (viewpoint:/...). Each entry corresponds to a single, visible Sirius
     * provided by an unmasked Resource.
     */
    private final Map<URI, Entry> entries = Maps.newHashMap();

    /**
     * The listeners which are notified of changes in the registry entries.
     */
    private final CopyOnWriteArrayList<ViewpointRegistryListener> listeners = new CopyOnWriteArrayList<ViewpointRegistryListener>();

    /**
     * The listeners which are notified of changes in the registry entries.
     */
    private final CopyOnWriteArrayList<ViewpointRegistryListener2> legacyListeners = new CopyOnWriteArrayList<ViewpointRegistryListener2>();

    /**
     * A transient object used to store changes in the entries so that they are
     * all notified in a single call.
     */
    private RegistryChange currentChange;

    private ECrossReferenceAdapter crossReferencer = new ECrossReferenceAdapter();

    private LegacyPluginMonitor legacyMonitor;

    private CopyOnWriteArrayList<ViewpointRegistryFilter> filters = new CopyOnWriteArrayList<ViewpointRegistryFilter>();

    /**
     * Constructor.
     */
    public ViewpointRegistry() {
        resourceSet.setURIConverter(new ViewpointURIConverter(this));
        resourceSet.eAdapters().add(crossReferencer);
        monitors.setListener(this);
        legacyMonitor = new LegacyPluginMonitor(this);
        configureResourceHandler();
        monitors.addMonitor("Legacy Plugins", legacyMonitor); //$NON-NLS-1$
        monitors.addMonitor("Plugins", new PluginMonitor(Platform.getExtensionRegistry(), compositeResourceHandler)); //$NON-NLS-1$
        monitors.addMonitor("Workspace", new WorkspaceMonitor(ResourcesPlugin.getWorkspace(), compositeResourceHandler)); //$NON-NLS-1$
    }

    private void configureResourceHandler() {
        compositeResourceHandler.addResourceType(new DefaultViewpointResourceHandler());
        IConfigurationElement[] elements = EclipseUtil.getConfigurationElementsFor(VIEWPOINT_RESOURCE_TYPE_EXTENSION_POINT);
        for (IConfigurationElement element : elements) {
            if ("handler".equals(element.getName())) { //$NON-NLS-1$
                Object handler;
                try {
                    handler = element.createExecutableExtension("class"); //$NON-NLS-1$
                    if (handler instanceof ViewpointResourceHandler) {
                        compositeResourceHandler.addResourceType((ViewpointResourceHandler) handler);
                    }
                } catch (CoreException e) {
                    reportWarning("Could not instantiate contributed Sirius Resource Type handler " + element.getAttribute("class")); //$NON-NLS-2$
                }
            }
        }
    }

    private void reportWarning(String message) {
        SiriusPlugin.getDefault().warning(message, null);
    }

    /**
     * Starts the registry.
     */
    public void start() {
        if (!isRunning()) {
            monitors.start();
        }
    }

    /**
     * Stops the registry and dispose of all the system resources.
     */
    public void stop() {
        if (isRunning()) {
            monitors.stop();
            entries.clear();
            resourceSet.getResources().clear();
            resourceSet.eAdapters().clear();
        }
    }

    /**
     * Tests whether this registry is currently running.
     * 
     * @return <code>true</code> if this registry is started.
     */
    public boolean isRunning() {
        return monitors.isRunning();
    }

    /**
     * Adds a new listener to be notified of changes in this registry. Does
     * nothing if the listener was already registered.
     * 
     * @param listener
     *            the listener to add.
     */
    public void addListener(ViewpointRegistryListener listener) {
        this.listeners.addIfAbsent(listener);
    }

    /**
     * Removes a listener from the ones to notify of changes in this registry.
     * Does nothing if the <code>listener</code> was not actually a listener of
     * this registry.
     * 
     * @param listener
     *            the listener to remove.
     */
    public void removeListener(ViewpointRegistryListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Notify all the registry's listeners of the specified changes.
     */
    private void fireRegistryChange(RegistryChange change) {
        ImmutableSet<URI> removed = ImmutableSet.copyOf(change.removed);
        ImmutableSet<URI> added = ImmutableSet.copyOf(change.added);
        ImmutableSet<URI> changed = ImmutableSet.copyOf(change.changed);
        for (ViewpointRegistryListener listener : this.listeners) {
            listener.registryChanged(this, removed, added, changed);
        }

        for (ViewpointRegistryListener2 legacyListener : legacyListeners) {
            legacyListener.modelerDesciptionFilesLoaded();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void resourceEvent(ViewpointResourceMonitor origin, Set<URI> removed, Set<URI> added, Set<URI> changed) {
        /*
         * This method is the only one from which the content of the registry
         * changes.
         */
        synchronized (this) {
            /*
             * The change is stored in a field to avoid passing it along every
             * call. There is no risk of confusion as all public methods on the
             * registry are synchronized on the registry itself, so we can not
             * have multiple calls to e.g. resourceEvent() at the same time.
             */
            this.currentChange = new RegistryChange();
            for (URI r : removed) {
                resourceRemoved(r);
            }
            for (URI a : added) {
                resourceAdded(a);
            }
            for (URI c : changed) {
                resourceChanged(c);
            }
            this.currentChange.normalize();
            new StatusUpdater(this.resourceSet, this.entries, this.currentChange).updateStatus();
            fireRegistryChange(this.currentChange);
            // Don't hang on the values which are not needed anymore.
            this.currentChange = null;
            DialectManager.INSTANCE.invalidateMappingCache();
        }
    }

    /**
     * Returns the Sirius resource handler used by this registry.
     * 
     * @return the Sirius resource handler used by this registry.
     */
    public ViewpointResourceHandler getSiriusResourceHandler() {
        return compositeResourceHandler;
    }

    /**
     * Returns the registry entry currently representing the specified logical
     * Sirius URI, if any.
     * 
     * @param viewpointURI
     *            a logical Sirius URI.
     * @return the registry entry currently representing the specified logical
     *         Sirius.
     */
    Option<Entry> getEntry(URI viewpointURI) {
        Preconditions.checkNotNull(viewpointURI);
        Preconditions.checkArgument(ViewpointURIQuery.isValidViewpointURI(viewpointURI));
        synchronized (this) {
            Entry entry = entries.get(viewpointURI);
            return Options.fromNullable(entry);
        }
    }

    /**
     * Invoked when one of our monitors has detected a new resource which may
     * contain VSMs.
     * 
     * @param uri
     *            the concrete URI of the potential VSM resource.
     */
    private void resourceAdded(URI uri) {
        Option<Resource> vsm = load(uri);
        if (vsm.some()) {
            MaskingChange change = maskingPolicy.resourceLoaded(vsm.get());
            updateEntries(change);
            updateResourcesStatus(change);
        }
    }

    /**
     * Invoked when one of our monitors has detected that a previously existing
     * VSM resource has disappeared.
     * 
     * @param uri
     *            the concrete URI of the VSM resource which was removed.
     */
    private void resourceRemoved(URI uri) {
        Option<Resource> res = findResource(uri);
        if (res.some()) {
            MaskingChange change = maskingPolicy.aboutToUnload(res.get());
            updateEntries(change);
            updateResourcesStatus(change);
            resourceSet.getResources().remove(res.get());
        } else {
            warning(MessageFormat.format("Inconsistent notification: can not remove unknown resource at {0}", uri), null);
        }
    }

    /**
     * Invoked when one of our monitors has detected a change in the content of
     * an already known VSM resource.
     * 
     * @param uri
     *            the concrete URI of the VSM resource whose content has
     *            changed.
     */
    private void resourceChanged(URI uri) {
        /*
         * Dumb implementation for now, modeling a change as a removal of the
         * old version and the addition for the new one.
         */
        resourceRemoved(uri);
        resourceAdded(uri);
    }

    private void updateResourcesStatus(MaskingChange change) {
        for (Resource masked : change.masked) {
            // Unload all masked resource so that other Sirius which
            // depend on them can get re-resolved onto the non-masked
            // version of their element, if any.
            new ViewpointResourceOperations(masked).unloadAndResetProxyURIs();
        }
        for (Resource unmasked : change.unmasked) {
            ensureLoaded(unmasked);
        }
    }

    private void updateEntries(MaskingChange change) {
        for (Resource masked : change.masked) {
            unregisterViewpointsFrom(masked);
        }
        for (Resource unmasked : change.unmasked) {
            registerViewpointsFrom(unmasked);
        }
    }

    private Option<Resource> findResource(final URI uri) {
        try {
            Resource match = Iterables.find(resourceSet.getResources(), new Predicate<Resource>() {
                public boolean apply(Resource input) {
                    return input.getURI().equals(uri);
                }
            });
            return match != null ? Options.newSome(match) : Options.<Resource> newNone();
        } catch (NoSuchElementException nsee) {
            return Options.newNone();
        }
    }

    /**
     * Loads the model at the specified URI into the resource set. This assumes
     * it is not already loaded. No resolution is performed for references to
     * other resources. The resource is loaded only if it can be done without
     * errors.
     */
    private Option<Resource> load(URI uri) {
        Preconditions.checkNotNull(uri);
        Preconditions.checkArgument(uri.isPlatform(), "Unsupported URI scheme: " + uri);

        final Option<Resource> result;
        Resource vsm;
        try {
            vsm = resourceSet.getResource(uri, true);
            if (vsm == null) {
                warning(MessageFormat.format("Unable to load the VSM at {0}", uri), null);
                result = Options.newNone();
            } else if (!vsm.getErrors().isEmpty()) {
                warning(MessageFormat.format("Errors occured while trying to load the VSM at {0}", uri), null);
                vsm.unload();
                resourceSet.getResources().remove(vsm);
                result = Options.newNone();
            } else {
                result = Options.newSome(vsm);
            }
            return result;
        } catch (final WrappedException e) {
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + uri, e.exception());
            /* CHECKSTYLE:OFF -> we should handle this kind of exception */
        } catch (final RuntimeException e) {
            /* CHECKSTYLE:ON */
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + uri, e);
        }
        return Options.newNone();
    }

    private void ensureLoaded(Resource unmasked) {
        if (!unmasked.isLoaded()) {
            try {
                unmasked.load(Collections.emptyMap());
            } catch (IOException e) {
                warning(MessageFormat.format("Unable to load the VSM at {0}", unmasked.getURI()), e);
            }
        }
    }

    /**
     * Add an entry in the registry for each of the Sirius defined in the
     * specified resource.
     * 
     * @return the new entries added to the registry.
     */
    private Set<Entry> registerViewpointsFrom(Resource vsm) {
        Set<Entry> newEntries = createNewEntries(vsm);
        for (Entry entry : newEntries) {
            Preconditions.checkState(!entries.containsKey(entry.getLogicalURI()));
            addEntry(entry);
        }
        return newEntries;
    }

    /**
     * Removes from the registry all entries for Sirius defined in the specified
     * resource.
     * 
     * @return the old entries removed from the registry.
     */
    private Set<Entry> unregisterViewpointsFrom(Resource vsm) {
        Set<Entry> unregistered = Sets.newHashSet();
        for (Entry entry : Lists.newArrayList(entries.values())) {
            if (Objects.equal(entry.getResource(), vsm)) {
                removeEntry(entry);
                unregistered.add(entry);
            }
        }
        return unregistered;
    }

    private Entry addEntry(Entry entry) {
        Entry result = entries.put(entry.getLogicalURI(), entry);
        this.currentChange.added.add(entry.getLogicalURI());
        return result;
    }

    private void removeEntry(Entry entry) {
        entries.remove(entry.getLogicalURI());
        this.currentChange.removed.add(entry.getLogicalURI());
    }

    /**
     * Creates, but does not add to the registry, new entries for each of the
     * Sirius defined in the specified resource.
     */
    private Set<Entry> createNewEntries(Resource vsm) {
        ensureLoaded(vsm);
        Set<Entry> newEntries = Sets.newHashSet();
        for (Viewpoint viewpoint : compositeResourceHandler.collectViewpointDefinitions(vsm)) {
            newEntries.add(new Entry(viewpoint));
        }
        return newEntries;
    }

    // CHECKSTYLE:OFF
    private void warning(String message, Throwable th) {
        if (th == null) {
            System.out.println("WARN: " + message); //$NON-NLS-1$
        } else {
            System.out.println("WARN: " + message + "(" + th.toString() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        System.out.flush();
    }

    public void dumpStatus(StringBuilder out) {
        out.append("Entries:\n"); //$NON-NLS-1$
        for (URI uri : Ordering.usingToString().sortedCopy(entries.keySet())) {
            Entry entry = entries.get(uri);
            out.append(" - ").append(uri).append(" => ").append(entry.getResource().getURI()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

        out.append("\n"); //$NON-NLS-1$
        out.append("ResourceSet:\n"); //$NON-NLS-1$
        for (Resource res : resourceSet.getResources()) {
            out.append(" - ").append(res.getURI()).append(" [loaded=").append(String.valueOf(res.isLoaded())).append("]").append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            out.append("\tResolved dependencies:\n\t\t").append(Joiner.on("\n\t\t").join(new ResourceQuery(res).getResolvedDependencies())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            out.append("\tUnresolved dependencies:\n\t\t").append(Joiner.on("\n\t\t").join(new ResourceQuery(res).getUnresolvedDependencies())).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

        out.append("\n"); //$NON-NLS-1$
        out.append("Transitive dependencies:\n"); //$NON-NLS-1$
        ViewpointDependenciesTracker tracker = new ViewpointDependenciesTracker(this);
        for (URI uri : Sets.newHashSet(entries.keySet())) {
            if (uri.toString().startsWith("viewpoint:/t/")) { //$NON-NLS-1$
                tracker.add(uri);
            }
        }
        for (URI uri : tracker.getTrackedElements()) {
            out.append(uri).append("\n"); //$NON-NLS-1$
            out.append(" - direct:  ").append(Joiner.on(", ").join(tracker.getDependencies(uri))).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            out.append(" - reverse: ").append(Joiner.on(", ").join(tracker.getReverseDependencies(uri))).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            out.append("\n"); //$NON-NLS-1$
        }
        tracker.dispose();
    }

    // CHECKSTYLE:ON

    /**
     * Returns a {@link ViewpointRelations} which can be used to see various
     * interesting relationships between the Viewpoints in this registry as
     * Relations.
     * 
     * @return a view of various relationships between the Viewpoints in this
     *         registry as Relations.
     */
    public ViewpointRelations getRelations() {
        return new ViewpointRelations(this);
    }

    /**
     * Returns a newly initialized tracker for the viewpoints in this registry
     * on the specified relation. The tracker will be automatically updated when
     * the content of the registry changes. It must be
     * {@link ViewpointDependenciesTracker#dispose() disposed} when not needed
     * anymore.
     * 
     * @param vpRelation
     *            the relation to track.
     * @return a tracker for the specified relation among the viewpoints in this
     *         registry.
     */
    public ViewpointDependenciesTracker createTrackerFor(Relation<URI> vpRelation) {
        ViewpointDependenciesTracker tracker = new ViewpointDependenciesTracker(this, vpRelation);
        for (URI uri : entries.keySet()) {
            tracker.add(uri);
        }
        return tracker;
    }

    /**
     * Returns the concrete physical URI of the resource which currently
     * provides the specified logical Sirius, if any.
     * 
     * @param logicalURI
     *            the logical Sirius URI of the viewpoint to look for.
     * 
     * @return the concrete URI of the resource which provides the Sirius.
     */
    public Option<URI> getProvider(URI logicalURI) {
        Option<Entry> entry = getEntry(logicalURI);
        if (entry.some()) {
            return Options.newSome(entry.get().getResource().getURI());
        } else {
            return Options.newNone();
        }
    }

    /**
     * {@inheritDoc}
     */
    public synchronized Set<Viewpoint> getViewpoints() {
        return ImmutableSet.copyOf(Iterables.transform(entries.values(), new Function<Entry, Viewpoint>() {
            public Viewpoint apply(Entry from) {
                return from.getSirius();
            }
        }));
    }

    /**
     * {@inheritDoc}
     */
    public boolean isFromPlugin(Viewpoint viewpoint) {
        if (viewpoint != null) {
            Resource viewpointResource = viewpoint.eResource();
            if (viewpointResource != null) {
                URI uri = viewpointResource.getURI();
                return uri.isPlatformPlugin();
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public EObject find(EObject eObject) {
        EObject eObj = null;
        try {
            eObj = resourceSet.getEObject(EcoreUtil.getURI(eObject), true);
        } catch (final WrappedException e) {
            /* fail silently */
        }
        return eObj != null ? eObj : eObject;
    }

    /**
     * {@inheritDoc}
     */
    public ECrossReferenceAdapter getCrossReferencer() {
        return crossReferencer;
    }

    /**
     * {@inheritDoc}
     */
    public Viewpoint getViewpoint(RepresentationDescription description) {
        return new RepresentationDescriptionQuery(description).getParentViewpoint();
    }

    /**
     * {@inheritDoc}
     */
    public Viewpoint getViewpoint(URI viewpointUri) {
        if (entries.containsKey(viewpointUri)) {
            return (entries.get(viewpointUri)).getSirius();
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean addListener(ViewpointRegistryListener2 listener) {
        return legacyListeners.addIfAbsent(listener);
    }

    /**
     * {@inheritDoc}
     */
    public boolean removeListener(ViewpointRegistryListener2 listener) {
        return legacyListeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    public Set<Viewpoint> registerFromPlugin(String modelerModelResourcePath) {
        try {
            return legacyMonitor.registerFromPlugin(modelerModelResourcePath);
        } catch (final WrappedException e) {
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + modelerModelResourcePath, e.exception());
            /* CHECKSTYLE:OFF -> we should handle this kind of exception */
        } catch (final RuntimeException e) {
            /* CHECKSTYLE:ON */
            SiriusPlugin.getDefault().warning(UNABLE_TO_LOAD_THIS_FILE + modelerModelResourcePath, e);
        }
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     */
    public void disposeFromPlugin(Viewpoint viewpoint) {
        legacyMonitor.disposeFromPlugin(viewpoint);
    }

    /**
     * {@inheritDoc}
     */
    public boolean addFilter(ViewpointRegistryFilter filter) {
        return this.filters.add(filter);
    }

    /**
     * {@inheritDoc}
     */
    public boolean removeFilter(ViewpointRegistryFilter filter) {
        return this.filters.remove(filter);
    }

    /**
     * {@inheritDoc}
     */
    public void removeFilter(String id) {
        Iterator<ViewpointRegistryFilter> iter = filters.iterator();
        while (iter.hasNext()) {
            ViewpointRegistryFilter filter = iter.next();
            if (Objects.equal(id, filter.getId())) {
                iter.remove();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public <T extends Component> void registerFromWorkspace(Set<T> components) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(int size) {
        start();
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        if (isRunning()) {
            stop();
        }
    }
}
