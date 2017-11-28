/*******************************************************************************
 * Copyright (c) 2007, 2010, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.ReloadingPolicy;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;

import com.google.common.base.Preconditions;

/**
 * A policy returning synchronization actions needed after a workspace change.
 * 
 * @author cbrun
 * 
 */
public class ReloadingPolicyImpl implements ReloadingPolicy {

    private final UICallBack callBack;

    /**
     * Constructor.
     * 
     * @param callBack
     *            the callback to use to ask question to the user.
     */
    public ReloadingPolicyImpl(UICallBack callBack) {
        Preconditions.checkNotNull(callBack);
        this.callBack = callBack;
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
    public List<Action> getActions(final Session session, final Resource resource, final ResourceStatus newStatus) {
        List<Action> actions = new ArrayList<>();

        switch (newStatus) {
        case EXTERNAL_CHANGED:
            actions = handleExternalChange(session, resource, newStatus);
            break;
        case DELETED:
            actions = handleExternalDelete(session, resource, newStatus);
            break;
        case CONFLICTING_CHANGED:
            actions = handleExternalChangeConflict(session, resource, newStatus);
            break;
        case CONFLICTING_DELETED:
            actions = handleExternalDeleteConflict(session, resource, newStatus);
            break;
        case CHANGES_CANCELED:
            actions = handleChangesCanceled(session, resource, newStatus);
            break;
        default:
            break;
        }

        return actions;
    }

    private List<Action> handleChangesCanceled(Session session, Resource resource, ResourceStatus newStatus) {
        return Arrays.asList(Action.RELOAD);
    }

    private List<Action> handleExternalChangeConflict(final Session session, final Resource resource, final ResourceStatus newStatus) {
        final List<Action> actions = new ArrayList<>();
        if (shouldReload(resource)) {
            actions.add(Action.RELOAD);
        }
        return actions;
    }

    private List<Action> handleExternalDeleteConflict(final Session session, final Resource resource, final ResourceStatus newStatus) {
        final List<Action> actions = new ArrayList<>();
        if (shouldRemove(resource)) {
            actions.add(Action.REMOVE);
        }
        return actions;
    }

    private List<Action> handleExternalDelete(final Session session, final Resource resource, final ResourceStatus newStatus) {
        final List<Action> actions = new ArrayList<>();

        if (isRepresentationsResourceOwnedBySession(session, resource)) {
            if (shouldClose(session, resource)) {
                actions.add(Action.CLOSE_SESSION);
            }
        } else {
            actions.add(Action.REMOVE);
        }
        return actions;
    }

    private boolean isRepresentationsResourceOwnedBySession(final Session session, final Resource resource) {
        boolean isRepresentationsResourceOwnedBySession = session.getAllSessionResources().contains(resource);
        return isRepresentationsResourceOwnedBySession;
    }

    private List<Action> handleExternalChange(final Session session, final Resource resource, final ResourceStatus newStatus) {
        final List<Action> actions = new ArrayList<>();
        actions.add(Action.RELOAD);
        return actions;
    }

    private boolean shouldReload(final Resource resource) {
        if (callBack != null) {
            return callBack.shouldReload(resource);
        }
        return true;
    }

    private boolean shouldRemove(final Resource resource) {
        if (callBack != null) {
            return callBack.shouldRemove(resource);
        }
        return true;
    }

    private boolean shouldClose(final Session session, final Resource resource) {
        if (callBack != null) {
            return callBack.shouldClose(session, resource);
        }
        return true;
    }
}
