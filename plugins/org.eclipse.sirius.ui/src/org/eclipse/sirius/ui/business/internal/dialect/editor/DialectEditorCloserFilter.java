/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.dialect.editor;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A {@link NotificationFilter} to be notifier of deletion of the current
 * {@link DRepresentation} target if it is a DSemanticDecorator.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DialectEditorCloserFilter extends NotificationFilter.Custom {

    private DRepresentation dRepresentation;

    /**
     * Default constructor.
     * 
     * @param dRepresentation
     *            the {@link DRepresentation} on which to be notifier of target
     *            deletion
     */
    public DialectEditorCloserFilter(DRepresentation dRepresentation) {
        this.dRepresentation = dRepresentation;
    }

    @Override
    public boolean matches(Notification notification) {
        return isTargetUnset(notification) || isRepresentationDeletion(notification) || isTargetDetachment(notification);
    }

    private boolean isTargetUnset(Notification notification) {
        boolean remove = notification.getEventType() == Notification.REMOVE || notification.getEventType() == Notification.UNSET;
        return remove && notification.getNotifier() == dRepresentation && notification.getFeature() == ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET;
    }

    private boolean isRepresentationDeletion(Notification notification) {
        boolean representationDeleted = false;
        if (notification.getFeature() == ViewpointPackage.Literals.DVIEW__OWNED_REPRESENTATIONS && wasInOldValue(notification, dRepresentation)) {
            // If the representation eContainer is still a DView, this
            // remove notification does not indicate a delete but a move.
            // No need to close the editor.
            representationDeleted = !(dRepresentation.eContainer() instanceof DView);
        } else if (notification.getFeature() == ViewpointPackage.Literals.DANALYSIS__OWNED_VIEWS && wasInOldValue(notification, dRepresentation.eContainer())) {
            // If it is a undo or a rollback but not a
            // DRepresentationContainer moved from a DAnalysis to another
            representationDeleted = dRepresentation.eContainer() == null || !(dRepresentation.eContainer() != null && dRepresentation.eContainer().eContainer() instanceof DView);
        }
        return representationDeleted;
    }

    private boolean wasInOldValue(Notification notification, EObject eObject) {
        boolean isCurrentDRepresentationRemove = false;
        int eventType = notification.getEventType();
        if (eventType == Notification.REMOVE || eventType == Notification.UNSET || eventType == Notification.REMOVE_MANY) {
            isCurrentDRepresentationRemove = isInOldValue(notification, eObject);
        }
        return isCurrentDRepresentationRemove;
    }

    private boolean isInOldValue(Notification notification, EObject obj) {
        if (notification.getOldValue() instanceof Collection) {
            return ((Collection<?>) notification.getOldValue()).contains(obj);
        } else {
            return notification.getOldValue() == obj;
        }
    }

    private boolean isTargetDetachment(Notification notification) {
        boolean detachedTarget = false;
        if (DanglingRefRemovalTrigger.IS_DETACHMENT.apply(notification) && dRepresentation instanceof DSemanticDecorator) {
            EObject target = ((DSemanticDecorator) dRepresentation).getTarget();
            detachedTarget = isInOldValue(notification, target) && target.eContainer() == null;
        }

        return detachedTarget;
    }

}
