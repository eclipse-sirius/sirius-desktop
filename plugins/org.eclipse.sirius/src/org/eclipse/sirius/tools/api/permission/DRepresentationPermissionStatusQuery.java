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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Query to checks LockStatus of DRepresentation.
 * 
 * @since 0.9.0
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DRepresentationPermissionStatusQuery {

    private DSemanticDecorator representation;

    /**
     * Construct a Query on DSemanticDecorator representing a DRepresentation.
     * 
     * @param representation
     *            the DSemanticDecorator representing a DRepresentation
     */
    public DRepresentationPermissionStatusQuery(DSemanticDecorator representation) {
        this.representation = representation;
    }

    /**
     * Checks if <code>elements</code> which is either a Collection of locked
     * elements or a Collection of unlocked elements contains the
     * {@link DSemanticDecorator} underlining the DialectEditor.
     * 
     * @param elements
     *            a Collection of locked elements or a Collection of unlocked
     *            elements
     * @return true if <code>elements</code> contains the
     *         {@link DSemanticDecorator} underlining the DialectEditor
     */
    public boolean isDSemanticDecoratorLockStatusNotification(Collection<EObject> elements) {
        return elements.contains(representation);
    }

    /**
     * Checks if <code>elements</code> which is either a Collection of locked
     * elements or a Collection of unlocked elements contains the
     * {@link DSemanticDecorator#getTarget()} element underlining the
     * DialectEditor.
     * 
     * @param elements
     *            a Collection of locked elements or a Collection of unlocked
     *            elements
     * @return true if <code>elements</code> contains the
     *         {@link DSemanticDecorator#getTarget()} element underlining the
     *         DialectEditor
     */
    public boolean isDSemanticDecoratorTargetLockStatusNotification(Collection<EObject> elements) {
        return representation.getTarget() != null && elements.contains(representation.getTarget());
    }

    /**
     * Get the corresponding {@link SessionListener}'s event to a
     * {@link LockStatus}, -1 if not corresponding event has been found.
     * 
     * @param lockStatus
     *            the {@link LockStatus}
     * @return the corresponding {@link SessionListener}'s event to a
     *         {@link LockStatus}, -1 else
     */
    public int getAssociatedSessionListenerEvent(LockStatus lockStatus) {
        int sessionListenerEvent = -1;
        switch (lockStatus) {
        case LOCKED_BY_ME:
            sessionListenerEvent = SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY;
            break;
        case LOCKED_BY_OTHER:
            sessionListenerEvent = SessionListener.REPRESENTATION_EDITION_PERMISSION_DENIED;
            break;
        case NOT_LOCKED:
            sessionListenerEvent = SessionListener.REPRESENTATION_EDITION_PERMISSION_GRANTED;
            break;
        default:
            break;
        }
        return sessionListenerEvent;
    }

}
