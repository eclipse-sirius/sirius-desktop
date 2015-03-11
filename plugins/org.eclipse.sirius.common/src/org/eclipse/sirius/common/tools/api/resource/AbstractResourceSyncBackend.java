/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.resource;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Backend used to synchronize a resourceset with external events about the
 * physical state of the data.
 * 
 * @author cbrun
 * 
 * @since 0.9.0
 */
public abstract class AbstractResourceSyncBackend {

    /**
     * The set the backend is focused on.
     */
    protected ResourceSet observedSet;

    /**
     * The client to update when a change occurs.
     */
    protected final ResourceSyncClient client;

    /**
     * Create a new {@link AbstractResourceSyncBackend} providing the client to
     * update when a change occurs.
     * 
     * @param client
     *            client to update when a change occurs.
     */
    public AbstractResourceSyncBackend(final ResourceSyncClient client) {
        this.client = client;
    }

    /**
     * initialize and prepare the backend.
     */
    public void install() {

    }

    /**
     * de-initialize the backend.
     */
    public void uninstall() {
        observedSet = null;
    }

    /**
     * Specify which {@link ResourceSet} the backend should notify about.
     * 
     * @param target
     *            the resourceset to inspect.
     */
    public void setResourceSet(final ResourceSet target) {
        this.observedSet = target;
    }

}
