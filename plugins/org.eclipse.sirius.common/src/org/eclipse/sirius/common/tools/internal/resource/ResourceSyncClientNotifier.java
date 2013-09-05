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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient.ResourceStatusChange;

/**
 * A {@link Job} to notify {@link ResourceSyncClient}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResourceSyncClientNotifier extends Job {

    /** The family id for this kind of job. */
    public static final String FAMILY = DslCommonPlugin.PLUGIN_ID + ".ResourceSyncClientNotification";

    private static final String ACTION_NAME = "ResourceSyncClient notification";

    private WorkspaceBackend workspaceBackend;

    private ResourceDeltaVisitor resourceDeltaVisitor;

    /**
     * Default constructor.
     * 
     * @param workspaceBackend
     *            the {@link workspaceBackend} have the
     *            {@link ResourceSyncClient} to notify
     * @param resourceDeltaVisitor
     *            the {@link ResourceDeltaVisitor} which has collected the
     *            added/removed/changed resources
     */
    public ResourceSyncClientNotifier(WorkspaceBackend workspaceBackend, ResourceDeltaVisitor resourceDeltaVisitor) {
        super(ACTION_NAME);
        this.workspaceBackend = workspaceBackend;
        this.resourceDeltaVisitor = resourceDeltaVisitor;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        monitor.beginTask(ACTION_NAME, 2 * (resourceDeltaVisitor.getRemovedResources().size() + resourceDeltaVisitor.getChangedResources().size() + resourceDeltaVisitor.getAddedResources().size()));
        final Collection<ResourceSyncClient.ResourceStatusChange> changes = new ArrayList<ResourceSyncClient.ResourceStatusChange>();
        for (final Resource removedRes : resourceDeltaVisitor.getRemovedResources()) {
            ResourceStatus oldStatus = ResourceSetSync.getStatus(removedRes);
            if (workspaceBackend.isChanged(oldStatus)) {
                changes.add(new ResourceStatusChange(removedRes, ResourceStatus.CONFLICTING_DELETED, oldStatus));
            } else {
                changes.add(new ResourceStatusChange(removedRes, ResourceStatus.DELETED, oldStatus));
            }
            monitor.worked(1);
        }
        for (final Resource changedResource : resourceDeltaVisitor.getChangedResources()) {
            ResourceStatus oldStatus = ResourceSetSync.getStatus(changedResource);
            if (workspaceBackend.isChanged(oldStatus)) {
                changes.add(new ResourceStatusChange(changedResource, ResourceStatus.CONFLICTING_CHANGED, oldStatus));
            } else {
                changes.add(new ResourceStatusChange(changedResource, ResourceStatus.EXTERNAL_CHANGED, oldStatus));
            }
            monitor.worked(1);
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
            monitor.worked(1);
        }

        // notify the client
        if (!changes.isEmpty()) {
            workspaceBackend.getClient().statusesChanged(changes);
            monitor.worked(changes.size());
        }
        return Status.OK_STATUS;
    }

    @Override
    public boolean belongsTo(Object family) {
        return FAMILY.equals(family);
    }
}
