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

import java.util.Set;

import org.eclipse.emf.common.util.URI;

/**
 * A <code>ViewpointRegistryListener</code> can be notified when changes occur
 * in the set of visible viewpoints available from the registry.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public interface ViewpointRegistryListener {
    /**
     * Indicates that the set of entries in the registry has changed.
     * <p>
     * All the different kinds of notifications are grouped in a single call to
     * this method so that logically consistent changes are notified in a single
     * atomic notification.
     * <p>
     * Note that a given URI can not appear in more than one of the parameters:
     * the sets are mutually exclusive.
     * 
     * @param registry
     *            the registry whose entries changed.
     * @param removed
     *            the logical URIs of the Viewpoint definitions which have
     *            disappeared. When this notification is received, the receiver
     *            must assume they are not accessible anymore.
     * @param added
     *            the logical URIs of the new Viewpoint definitions which have
     *            appeared. When this notification is received, the receiver can
     *            assume they are accessible.
     * @param changed
     *            the logical URIs of the Viewpoint definition whose content or
     *            state has changed. For an URI to be in this set, it must have
     *            been previously <code>added</code> and not yet
     *            <code>removed</code>. When this notification is received, the
     *            receiver can assume the changes have already taken place in
     *            the registry.
     */
    void registryChanged(ViewpointRegistry registry, Set<URI> removed, Set<URI> added, Set<URI> changed);
}
