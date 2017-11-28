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

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;

/**
 * 
 * A {@link ResourceSyncClient} gets notified when a resource status change.
 * 
 * @author cbrun
 * @since 0.9.0
 * 
 */
public interface ResourceSyncClient {

    /**
     * called by the {@link ResourceSetSync} when a resource status is changing.
     * 
     * @param resource
     *            the resource which is changing.
     * @param oldStatus
     *            the old status.
     * @param newStatus
     *            the new status.
     */
    void statusChanged(Resource resource, ResourceSetSync.ResourceStatus oldStatus, ResourceSetSync.ResourceStatus newStatus);

    /**
     * called by the {@link ResourceSetSync} when some resources have changed
     * their synchronization status.
     * 
     * @param changes
     *            the status changes which happened during either a single
     *            command execution, or a single workspace refresh operation.
     * @since 0.9.0
     * 
     */
    void statusesChanged(Collection<ResourceStatusChange> changes);

    /**
     * A class encapsulating the even of a resource status change.
     * 
     * @author cbrun
     * @since 0.9.0
     * 
     */
    class ResourceStatusChange {
        /**
         * The changing resource.
         */
        private final Resource resource;

        /**
         * The resource previous status.
         */
        private final ResourceStatus oldStatus;

        /**
         * The resource new status.
         */
        private final ResourceStatus newStatus;

        /**
         * Create a resource status change.
         * 
         * @param updatedResource
         *            the resource which changed.
         * @param newStatus
         *            the resource's new status.
         * @param oldStatus
         *            the resource's previous status.
         */
        public ResourceStatusChange(Resource updatedResource, ResourceStatus newStatus, ResourceStatus oldStatus) {
            this.resource = updatedResource;
            this.newStatus = newStatus;
            this.oldStatus = oldStatus;
        }

        /**
         * The resource's new status.
         * 
         * @return the resource's new status.
         */
        public ResourceStatus getNewStatus() {
            return newStatus;
        }

        /**
         * The resource's previous status.
         * 
         * @return the resource's previous status.
         */
        public ResourceStatus getOldStatus() {
            return oldStatus;
        }

        /**
         * The changing resource.
         * 
         * @return the resource.
         */
        public Resource getResource() {
            return resource;
        }
    }
}
