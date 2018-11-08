/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.ReloadingPolicy;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;

/**
 * A policy returning synchronization actions needed after a workspace change.
 *
 * @author cbrun
 * @author pcdavid
 */
public class ReloadingPolicyImpl implements ReloadingPolicy {

    /**
     * The callback to use to ask the end-user's decision.
     */
    private final UICallBack callBack;

    /**
     * Constructor.
     *
     * @param callBack
     *            the callback to use to ask question to the user.
     */
    public ReloadingPolicyImpl(UICallBack callBack) {
        this.callBack = Objects.requireNonNull(callBack);
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
     * @return a list of actions to take, might be an empty list of there's nothing to do.
     */
    @Override
    public List<Action> getActions(Session session, Resource resource, ResourceStatus newStatus) {
        final List<Action> result;
        switch (newStatus) {

        case EXTERNAL_CHANGED:
            /*
             * The reference version has changed on disk, but we have no in-memory changes compared to the previous
             * reference version. Unconditionally update the in-memory version with the updated on-disk version.
             */
            result = Collections.singletonList(Action.RELOAD);
            break;

        case DELETED:
            /*
             * The in-memory version of the resource has not been modified since the last sync, and the underlying file
             * has been deleted. If this is a semantic resource, unconditionaly remove it, without loss. If this is an
             * aird file however, ask the user if the session should be closed (if it is not, the aird files will get
             * recreated on the next save).
             */
            result = new ArrayList<>();
            if (session.getAllSessionResources().contains(resource)) {
                if (callBack.shouldClose(session, resource)) {
                    result.add(Action.CLOSE_SESSION);
                }
            } else {
                result.add(Action.REMOVE);
            }
            break;

        case CONFLICTING_CHANGED:
            /*
             * The both the in-memory version and the on-disk version have been modified since the last sync (load or
             * save). We do not support fine-grained merging of possibly diverging changes: ask the user if we should
             * reload from disk (discarding the in-memory changes) or ignore the new version on disk for now and keep
             * the in-memory changes.
             */
            if (callBack.shouldReload(resource)) {
                result = Collections.singletonList(Action.RELOAD);
            } else {
                result = Collections.emptyList();
            }
            break;

        case CONFLICTING_DELETED:
            /*
             * We have made in-memory changes which have not been saved, but the underlying file has been deleted from
             * disk. Ask the user if the resource should be removed from the session (discarding the unsaved changes),
             * or keep the in-memory version (in which case the resource will be re-created on disk on the next save).
             */
            if (callBack.shouldRemove(resource)) {
                result = Collections.singletonList(Action.REMOVE);
            } else {
                result = Collections.emptyList();
            }
            break;

        case CHANGES_CANCELED:
            /*
             * CHANGES_CANCELED is a special status only used by RestoreToLastSavePointListener: unconditionally reload
             * the version on disk. Note that this may actually not be the same as the reference version we loaded
             * initially, if the version on disk has changed since then and the user chose to keep his in-memory version
             * (CONFLICTING_CHANGED).
             */
            result = Collections.singletonList(Action.RELOAD);
            break;

        default:
            /*
             * Do nothing by default.
             */
            result = Collections.emptyList();
        }
        return result;
    }
}
