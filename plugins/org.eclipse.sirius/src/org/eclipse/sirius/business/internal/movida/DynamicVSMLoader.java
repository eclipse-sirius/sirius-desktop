/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida;

import java.text.MessageFormat;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistryListener;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * A VSM Reloader is responsible for keeping the VSM resources loaded in a
 * resource set in sync with the logical Viewpoints actually needed in the
 * resource set's context. It makes sure that all the VSMs required by the
 * context, and only them, are loaded and properly resolved.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class DynamicVSMLoader implements ViewpointRegistryListener {
    /**
     * The registry to use for information about which logical viewpoints are
     * available, which concrete resources they map to, and when and how they
     * change.
     */
    private final ViewpointRegistry registry;

    /**
     * The resource set to manage.
     */
    private final ResourceSet resourceSet;

    /**
     * The logical URIs of all the Viewpoints which are explicitly required.
     */
    private final Set<URI> requiredViewpoints = Sets.newHashSet();

    /**
     * The physical URIs of all the VSMs which provide the required Viewpoints.
     */
    private final Set<URI> requiredProviders = Sets.newHashSet();

    private final Set<URI> protectedResources = Sets.newHashSet();

    private Runnable errorHandler;

    /**
     * Constructor.
     * 
     * @param resourceSet
     *            the resource set to manage.
     * @param registry
     *            the registry to use.
     */
    public DynamicVSMLoader(ResourceSet resourceSet, ViewpointRegistry registry) {
        this.resourceSet = Preconditions.checkNotNull(resourceSet);
        this.registry = Preconditions.checkNotNull(registry);
        this.registry.addListener(this);
    }

    /**
     * Returns the registry used by this loader.
     * 
     * @return the registry used by this loader.
     */
    public ViewpointRegistry getRegistry() {
        return registry;
    }

    /**
     * Returns the logical URIs of all the Viewpoints required.
     * 
     * @return the logical URIs of all the Viewpoints required.
     */
    public Set<URI> getRequiredViewpoints() {
        return requiredViewpoints;
    }

    public synchronized void setErrorHandler(Runnable errorHandler) {
        this.errorHandler = errorHandler;
    }

    /**
     * Mark the resource identified by the specified URI as protected: the
     * loader will not attempt to load, reload or unload it.
     * 
     * @param uri
     *            the physical URI of the resource to consider as protected.
     */
    public synchronized void protect(URI uri) {
        this.protectedResources.add(Preconditions.checkNotNull(uri));
    }

    /**
     * Mark the resource identified by the specified URI as not protected.
     * 
     * @param uri
     *            the physical URI of the resource to consider as not protected.
     */
    public synchronized void unprotect(URI uri) {
        this.protectedResources.remove(Preconditions.checkNotNull(uri));
    }

    /**
     * Tell this loader that the viewpoint specified by the supplied logical URI
     * should always be loaded in the resource set, along with its dependencies.
     * 
     * @param viewpoint
     *            the logical Sirius URI of the Sirius which is required.
     */
    public synchronized void require(URI viewpoint) {
        Preconditions.checkNotNull(viewpoint);
        Option<URI> provider = registry.getProvider(viewpoint);
        Preconditions.checkState(provider.some(), MessageFormat.format("The specified viewpoint {0} is not available.", viewpoint.toString())); //$NON-NLS-1$
        boolean added = this.requiredViewpoints.add(viewpoint);
        if (added) {
            refresh();
        }
    }

    /**
     * Tell this loader that the viewpoint specified by the supplied logical URI
     * is no longer explicitly required. The underlying resource may be unloaded
     * from the resource set if the loader detects that it is no longer
     * required, even indirectly. Does nothing if the specified viewpoint was
     * not an explicit requirement.
     * 
     * @param viewpoint
     *            the logical Sirius URI of the Sirius which is no longer
     *            directly required.
     */
    public synchronized void unrequire(URI viewpoint) {
        boolean removed = this.requiredViewpoints.remove(Preconditions.checkNotNull(viewpoint));
        if (removed) {
            refresh();
        }
    }

    /**
     * Stop managing the resource set and release all resources.
     */
    public void dispose() {
        this.registry.removeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void registryChanged(ViewpointRegistry reg, Set<URI> removed, Set<URI> added, Set<URI> changed) {
        assert reg == this.registry;
        SetView<URI> absentRequirements = Sets.intersection(requiredViewpoints, removed);
        if (!absentRequirements.isEmpty()) {
            if (errorHandler != null) {
                errorHandler.run();
            } else {
                throw new IllegalStateException("The required viewpoints " + Joiner.on(", ").join(absentRequirements) + " are not available anymore."); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
            }
        }
        refresh();
    }

    private void refresh() {
        Set<URI> providers = computeRequiredProviders();
        Set<Resource> maybeUnneeded = getAndUnloadPotentialUnneededProviders(providers);
        loadAndResolve(providers);
        removeUnneededResources(maybeUnneeded);
        setRequiredProviders(providers);
    }

    private Set<URI> computeRequiredProviders() {
        Set<URI> newProviders = Sets.newHashSet();
        for (URI uri : requiredViewpoints) {
            Option<URI> provider = registry.getProvider(uri);
            assert provider.some();
            newProviders.add(provider.get());
        }
        return newProviders;
    }

    private Set<Resource> getAndUnloadPotentialUnneededProviders(Set<URI> required) {
        Set<Resource> maybeUnneeded = Sets.newHashSet();
        for (Resource res : resourceSet.getResources()) {
            if (!required.contains(res.getURI()) && res.getURI().isPlatform() && !protectedResources.contains(res.getURI())) {
                maybeUnneeded.add(res);
                res.unload();
            }
        }
        return maybeUnneeded;
    }

    private void loadAndResolve(Set<URI> providers) {
        for (URI uri : providers) {
            if (!requiredProviders.contains(uri)) {
                Resource res = resourceSet.getResource(uri, true);
                EcoreUtil.resolveAll(res);
            }
        }
    }

    /**
     * Resources identified as potentially unneeded can be completely removed of
     * the resolution step has not loaded them.
     */
    private void removeUnneededResources(Set<Resource> maybeUnneeded) {
        for (Resource resource : maybeUnneeded) {
            if (!resource.isLoaded()) {
                resourceSet.getResources().remove(resource);
            }
        }
    }

    private void setRequiredProviders(Set<URI> newProviders) {
        requiredProviders.clear();
        requiredProviders.addAll(newProviders);
    }
}
