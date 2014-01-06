/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.permission;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * A permission status listener to notify the editor about permission authority
 * in order to update its title image.
 * 
 * @since 0.9.0
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class DRepresentationPermissionStatusListener implements IAuthorityListener {

    private DSemanticDecorator representation;

    private SessionListener editor;

    private DRepresentationPermissionStatusQuery dRepresentationPermissionStatusQuery;

    /**
     * Constructor.
     * 
     * @param representation
     *            the current {@link org.eclipse.sirius.viewpoint.DSemanticDecorator}.
     * @param editor
     *            the current editor.
     */
    public DRepresentationPermissionStatusListener(DSemanticDecorator representation, SessionListener editor) {
        this.representation = representation;
        this.editor = editor;
        this.dRepresentationPermissionStatusQuery = new DRepresentationPermissionStatusQuery(representation);
    }

    /**
     * {@link org.eclipse.sirius.viewpoint.DSemanticDecorator} getter method.
     * 
     * @return this.representation
     */
    public DSemanticDecorator getRepresentation() {
        return representation;
    }

    /**
     * {@inheritDoc}
     * 
     * Notify the editor that the edition permission has been restricted.
     */
    public void notifyIsLocked(EObject lockedElement) {
        notifyIsLocked(Collections.singletonList(lockedElement));
    }

    /**
     * {@inheritDoc}
     * 
     * Notify the editor that the edition permission has been restricted.
     */
    public void notifyIsLocked(Collection<EObject> lockedElements) {
        IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation);
        if (dRepresentationPermissionStatusQuery.isDSemanticDecoratorLockStatusNotification(lockedElements)) {
            LockStatus lockStatus = permissionAuthority.getLockStatus(representation);
            changeUILockStatus(lockStatus);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Notify the editor that the edition permission have been granted.
     */
    public void notifyIsReleased(EObject unlockedElement) {
        notifyIsReleased(Collections.singletonList(unlockedElement));
    }

    /**
     * {@inheritDoc}
     * 
     * Notify the editor that the edition permission have been granted.
     */
    public void notifyIsReleased(Collection<EObject> unlockedElements) {
        if (dRepresentationPermissionStatusQuery.isDSemanticDecoratorLockStatusNotification(unlockedElements)) {
            changeUILockStatus(LockStatus.NOT_LOCKED);
        }
    }

    /**
     * Notify the DialectEditor of changed LockStatus on its root element.
     * 
     * @param lockStatus
     *            the {@link LockStatus} to indicate with which
     *            {@link SessionListener} event notify DialectEditor
     */
    private void changeUILockStatus(LockStatus lockStatus) {
        int sessionListenerEvent = dRepresentationPermissionStatusQuery.getAssociatedSessionListenerEvent(lockStatus);
        if (sessionListenerEvent != -1) {
            editor.notify(sessionListenerEvent);
        }
    }
}
