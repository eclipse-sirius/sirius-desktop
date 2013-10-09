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
package org.eclipse.sirius.business.internal.movida.registry.monitoring;

import java.util.Set;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * A partial implementation of ViewpointResourceMonitor to use as a starting
 * point.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public abstract class AbstractViewpointResourceMonitor implements ViewpointResourceMonitor {
    /**
     * The listener to notify when we detect interesting events.
     */
    protected ViewpointResourceListener listener;

    /**
     * Whether or not we are actively monitoring.
     */
    protected volatile boolean running;

    /**
     * {@inheritDoc}
     */
    public synchronized void setListener(ViewpointResourceListener listener) {
        this.listener = listener;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * {@inheritDoc}
     */
    protected synchronized void notifyResourcesEvents(Set<URI> removed, Set<URI> added, Set<URI> changed) {
        Preconditions.checkNotNull(removed);
        Preconditions.checkNotNull(added);
        Preconditions.checkNotNull(changed);
        assert Sets.intersection(removed, added).isEmpty() && Sets.intersection(added, changed).isEmpty() && Sets.intersection(changed, removed).isEmpty();

        if (listener != null && !(removed.isEmpty() && added.isEmpty() && changed.isEmpty())) {
            listener.resourceEvent(this, removed, added, changed);
        }
    }
}
