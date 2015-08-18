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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient.ResourceStatusChange;

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

    @Override
    public void resourceChanged(final IResourceChangeEvent event) {
        final IResourceDelta delta = event.getDelta();
        try {
            if (delta != null) {
                final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor(workspaceBackend);
                delta.accept(visitor);
                Collection<ResourceSyncClient.ResourceStatusChange> changes = getChanges(visitor);
                // notify the client
                if (!changes.isEmpty()) {
                    ResourceSyncClientNotifier resourceSyncClientNotifier = new ResourceSyncClientNotifier(workspaceBackend.getClient(), changes);
                    resourceSyncClientNotifier.run(new NullProgressMonitor());
                }
            }
        } catch (final CoreException exception) {
            DslCommonPlugin.getDefault().error("Error while refreshing resource", exception);
        }
    }

    private Collection<ResourceSyncClient.ResourceStatusChange> getChanges(ResourceDeltaVisitor resourceDeltaVisitor) {
        final Collection<ResourceSyncClient.ResourceStatusChange> changes = new ArrayList<ResourceSyncClient.ResourceStatusChange>();
        for (final Resource removedRes : resourceDeltaVisitor.getRemovedResources()) {
            ResourceStatus oldStatus = ResourceSetSync.getStatus(removedRes);
            if (workspaceBackend.isChanged(oldStatus)) {
                changes.add(new ResourceStatusChange(removedRes, ResourceStatus.CONFLICTING_DELETED, oldStatus));
            } else {
                changes.add(new ResourceStatusChange(removedRes, ResourceStatus.DELETED, oldStatus));
            }
        }
        for (final Resource changedResource : resourceDeltaVisitor.getChangedResources()) {
            ResourceStatus oldStatus = ResourceSetSync.getStatus(changedResource);
            if (workspaceBackend.isChanged(oldStatus)) {
                changes.add(new ResourceStatusChange(changedResource, ResourceStatus.CONFLICTING_CHANGED, oldStatus));
            } else {
                changes.add(new ResourceStatusChange(changedResource, ResourceStatus.EXTERNAL_CHANGED, oldStatus));
            }
        }
        // It could be added resources in case of
        // fragmentation
        for (final Resource addedResource : resourceDeltaVisitor.getAddedResources()) {
            // Notify of added resource in case of
            // fragmentation to correctly clean the
            // ResourceSetSync.savedResources list
            ResourceStatus oldStatus = ResourceSetSync.getStatus(addedResource);
            if (ResourceStatus.SYNC.equals(oldStatus)) {
                changes.add(new ResourceStatusChange(addedResource, ResourceStatus.SYNC, oldStatus));
            }
        }
        return changes;
    }
}
