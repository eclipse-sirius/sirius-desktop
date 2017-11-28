/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.resource;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.common.tools.api.resource.AbstractResourceSyncBackend;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;

/**
 * {@link AbstractResourceSyncBackend} specific to workspace.
 * 
 * @author cbr
 */
public class WorkspaceBackend extends AbstractResourceSyncBackend {

    private EditingSessionWorkspaceListener listener;

    /**
     * Workspace resource listener. Default constructor.
     * 
     * @param client
     *            a {@link ResourceSyncClient}
     */
    public WorkspaceBackend(ResourceSyncClient client) {
        super(client);
    }

    boolean isChanged(ResourceStatus oldStatus) {
        return oldStatus == ResourceStatus.CHANGED || oldStatus == ResourceStatus.CONFLICTING_CHANGED || oldStatus == ResourceStatus.CONFLICTING_DELETED;
    }

    @Override
    public void install() {
        if (listener == null) {
            listener = new EditingSessionWorkspaceListener(this);
            final IWorkspace workspace = ResourcesPlugin.getWorkspace();
            workspace.addResourceChangeListener(listener);
        }
    }

    @Override
    public void uninstall() {
        if (listener != null) {
            final IWorkspace workspace = ResourcesPlugin.getWorkspace();
            workspace.removeResourceChangeListener(listener);
            listener = null;
        }
        super.uninstall();
    }

    public ResourceSet getObservedSet() {
        return observedSet;
    }

    public ResourceSyncClient getClient() {
        return client;
    }

}
