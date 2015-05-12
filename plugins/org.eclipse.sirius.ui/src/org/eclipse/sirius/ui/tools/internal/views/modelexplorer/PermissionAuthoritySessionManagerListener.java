/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;

/**
 * Reacts to the creation of new {@link Session} by registering the
 * {@link IAuthorityListener} on session resourceSet.
 * 
 * This {@link SessionManagerListener} is added only when
 * {@link #register(IAuthorityListener)} is called. It must not be added by
 * another way.<BR>
 * It must be removed by calling {@link #unregister()}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class PermissionAuthoritySessionManagerListener extends SessionManagerListener.Stub {

    /**
     * The permission authority listener.
     */
    private IAuthorityListener authorityListener;

    /**
     * Default constructor.
     */
    public PermissionAuthoritySessionManagerListener() {
    }

    @Override
    public void notifyAddSession(Session newSession) {
        // We add the IAuthorityListener on the resourceSet of this added
        // session.
        PermissionAuthorityRegistry.getDefault().getPermissionAuthority(newSession.getTransactionalEditingDomain().getResourceSet()).addAuthorityListener(authorityListener);

    }

    @Override
    public void notifyRemoveSession(Session removedSession) {
        // We remove the IAuthorityListener from the resourceSet of this removed
        // session.
        PermissionAuthorityRegistry.getDefault().getPermissionAuthority(removedSession.getTransactionalEditingDomain().getResourceSet()).removeAuthorityListener(authorityListener);
    }

    /**
     * Register this authorityListener as new permission authority listener for
     * existing session. It is also added to the future session with this
     * {@link SessionManagerListener}.
     * 
     * @param permissionAuthorityListener
     *            The {@link IAuthorityListener} to register
     */
    public void register(IAuthorityListener permissionAuthorityListener) {
        this.authorityListener = permissionAuthorityListener;
        // Iterate on already opened sessions to add the IAuthorityListener on
        // there resourceSet.
        for (Session session : SessionManager.INSTANCE.getSessions()) {
            PermissionAuthorityRegistry.getDefault().getPermissionAuthority(session.getTransactionalEditingDomain().getResourceSet()).addAuthorityListener(this.authorityListener);
        }
        // Register this SessionManagerListener, in charge of adding the
        // IAuthorityListener as permission authority listener for the new
        // sessions
        SessionManager.INSTANCE.addSessionsListener(this);
    }

    /**
     * Remove the {@link SessionManagerListener} added during the call to
     * {@link #register(IAuthorityListener)}, and remove the
     * {@link IAuthorityListener} from each existing sessions.
     */
    public void unregister() {
        SessionManager.INSTANCE.removeSessionsListener(this);
        // Iterate on opened sessions to remove the IAuthorityListener from
        // there resourceSet.
        for (Session session : SessionManager.INSTANCE.getSessions()) {
            PermissionAuthorityRegistry.getDefault().getPermissionAuthority(session.getTransactionalEditingDomain().getResourceSet()).removeAuthorityListener(this.authorityListener);
        }
        authorityListener = null;
    }
}
