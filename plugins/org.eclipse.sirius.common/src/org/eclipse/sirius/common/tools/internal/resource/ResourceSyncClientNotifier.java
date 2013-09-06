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

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;

/**
 * A {@link Job} to notify {@link ResourceSyncClient}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ResourceSyncClientNotifier extends Job {

    /** The family id for this kind of job. */
    public static final String FAMILY = DslCommonPlugin.PLUGIN_ID + ".ResourceSyncClientNotification";

    private static final String ACTION_NAME = "ResourceSyncClient notification";

    private ResourceSyncClient resourceSyncClient;

    private Collection<ResourceSyncClient.ResourceStatusChange> changes;

    /**
     * Default constructor.
     * 
     * @param resourceSyncClient
     *            the {@link ResourceSyncClient} to notify
     * @param changes
     *            the {@link ResourceStatusChange} to broadcast
     */
    public ResourceSyncClientNotifier(ResourceSyncClient resourceSyncClient, Collection<ResourceSyncClient.ResourceStatusChange> changes) {
        super(ACTION_NAME);
        this.resourceSyncClient = resourceSyncClient;
        this.changes = changes;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        try {
            monitor.beginTask(ACTION_NAME, changes.size());
            resourceSyncClient.statusesChanged(changes);
            monitor.worked(changes.size());
        } finally {
            monitor.done();
        }
        return Status.OK_STATUS;
    }

    @Override
    public boolean belongsTo(Object family) {
        return FAMILY.equals(family);
    }
}
