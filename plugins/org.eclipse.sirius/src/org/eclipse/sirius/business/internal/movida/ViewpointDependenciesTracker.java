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
package org.eclipse.sirius.business.internal.movida;

import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistryListener;
import org.eclipse.sirius.ext.base.relations.DependencyTracker;
import org.eclipse.sirius.ext.base.relations.Relation;

import com.google.common.base.Preconditions;

/**
 * Tracks various kinds of dependencies between a set of Viewpoints.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ViewpointDependenciesTracker extends DependencyTracker<URI> {
    /**
     * Notifies the trackers of changes in the registry so that their
     * information is up to date.
     * 
     * @author pierre-charles.david@obeo.fr
     */
    private class TrackerUpdater implements ViewpointRegistryListener {
        /**
         * {@inheritDoc}
         */
        public void registryChanged(ViewpointRegistry reg, Set<URI> removed, Set<URI> added, Set<URI> changed) {
            for (URI r : removed) {
                ViewpointDependenciesTracker.this.remove(r);
            }
            for (URI a : added) {
                ViewpointDependenciesTracker.this.add(a);
            }
            for (URI c : changed) {
                ViewpointDependenciesTracker.this.update(c);
            }
        }
    }

    private final ViewpointRegistry registry;

    private final TrackerUpdater updater;

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry to use to resolve Sirius URIs.
     */
    public ViewpointDependenciesTracker(ViewpointRegistry registry) {
        this(registry, Preconditions.checkNotNull(registry).getRelations().getTransitiveRequires());
    }

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry to use to resolve Sirius URIs.
     * @param relation
     *            the viewpoint relation to track.
     */
    public ViewpointDependenciesTracker(ViewpointRegistry registry, Relation<URI> relation) {
        super(Preconditions.checkNotNull(relation));
        this.registry = Preconditions.checkNotNull(registry);
        this.updater = new TrackerUpdater();
        this.registry.addListener(this.updater);
    }

    /**
     * Dispose this tracker.
     */
    public void dispose() {
        this.registry.removeListener(this.updater);
    }
}
