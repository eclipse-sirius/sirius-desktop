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
package org.eclipse.sirius.business.api.session;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;

/**
 * A policy returning synchronization actions needed after a workspace change.
 * 
 * @author cbrun
 * @since 0.9.0
 */
public interface ReloadingPolicy {
    /**
     * An action about what should be done in conflicting cases.
     * 
     * @author cedric
     * 
     */
    enum Action {
        /**
         * 
         * The action to do.
         */
        RELOAD, REMOVE, CLOSE_SESSION
    }

    /**
     * Return the action to take considering the new status.
     * 
     * @param session
     *            the session the resource is from.
     * 
     * @param resource
     *            resource concerned by the new status.
     * @param newStatus
     *            new status of the resource.
     * @return a list of actions to take, might be an empty list of there's
     *         nothing to do.
     */
    List<Action> getActions(Session session, Resource resource, ResourceStatus newStatus);

}
