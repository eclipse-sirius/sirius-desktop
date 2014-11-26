/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

/**
 * Implementation of an {@link IResourceChangeListener} that removes the changed
 * resource from a cache of files if existing in it.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class WorkspaceResourceChangeListenerImpl extends WorkspaceFileResourceChangeListener {

    /**
     * Init the {@link IResourceChangeListener}.
     * 
     */
    public void init() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        IResourceDelta delta = event.getDelta();

        if (delta != null) {
            ResourceDeltaVisitor visitor = getResourceDeltaVisitor();
            try {
                delta.accept(visitor);
            } catch (CoreException e) {
                DiagramUIPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, DiagramUIPlugin.ID, e.getMessage()));
            }

            for (String fileURI : visitor.changedFilesURI) {
                removeFileStatusAndURIFromMaps(fileURI);
            }
        }

    }
}
