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

/**
 * A <code>ViewpointResourceListener</code> can be notified of the appearance,
 * disappearance and changes in resources which can potentially contain
 * viewpoint definitions.
 * <p>
 * Note: a resource move is modeled by a removal followed by an addition,
 * whether or not the resource's content changes during the move.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public interface ViewpointResourceListener {
    /**
     * Invoked when relevant changes in resources potentially containing VSMs
     * are detected.
     * <p>
     * All the different kinds of notifications are grouped in a single call to
     * this method so that logically consistent changes may be notified in a
     * single atomic notification, although this is not a strict requirement for
     * {@link ViewpointResourceMonitor}s.
     * <p>
     * Note that a given URI can not appear in more than one of the parameters:
     * the sets are mutually exclusive.
     * 
     * @param origin
     *            the monitor which detected the change.
     * 
     * @param removed
     *            the URIs of the resources which have disappeared. When this
     *            notification is received, the receiver must assume they are
     *            not accessible anymore.
     * @param added
     *            the URIs of the new resources which have appeared. When this
     *            notification is received, the receiver can assume they are
     *            accessible.
     * @param changed
     *            the URIs of the resources whose content has changed. For an
     *            URI to be in this set, it must have been previously
     *            <code>added</code> and not yet <code>removed</code>. When this
     *            notification is received, the receiver can assume they are
     *            accessible and (re)loading them will yield the updated
     *            content.
     */
    void resourceEvent(ViewpointResourceMonitor origin, Set<URI> removed, Set<URI> added, Set<URI> changed);
}
