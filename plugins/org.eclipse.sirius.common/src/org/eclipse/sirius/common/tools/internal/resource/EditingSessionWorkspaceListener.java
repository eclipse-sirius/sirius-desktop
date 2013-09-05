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

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.sirius.common.tools.DslCommonPlugin;

/**
 * Workspace resource listener.
 * 
 * @author cbrun
 */
public class EditingSessionWorkspaceListener implements IResourceChangeListener {

    private WorkspaceBackend workspaceBackend;

    /**
     * Default constructor.
     * 
     * @param workspaceBackend
     *            {@link WorkspaceBackend}
     */
    public EditingSessionWorkspaceListener(WorkspaceBackend workspaceBackend) {
        this.workspaceBackend = workspaceBackend;
    }

    /**
     * {@inheritDoc}
     */
    public void resourceChanged(final IResourceChangeEvent event) {
        final IResourceDelta delta = event.getDelta();
        try {
            if (delta != null) {
                final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor(workspaceBackend);
                delta.accept(visitor);
                ResourceSyncClientNotifier resourceSyncClientNotifier = new ResourceSyncClientNotifier(workspaceBackend, visitor);
                resourceSyncClientNotifier.schedule();
            }
        } catch (final CoreException exception) {
            DslCommonPlugin.getDefault().error("Error while refreshing resource", exception);
        }
    }
}
