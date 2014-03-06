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
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.sirius.viewpoint.DRepresentation;
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
        boolean remove = notification.getEventType() == Notification.REMOVE || notification.getEventType() == Notification.UNSET;
        boolean unsetTarget = remove && notification.getNotifier() == dRepresentation && notification.getFeature() == ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET;

        boolean representationDeleted = false;
        if (!unsetTarget && notification.getFeature() == ViewpointPackage.Literals.DVIEW__OWNED_REPRESENTATIONS) {
            // If the representation eContainer is still a DView, this remove
            // notification does not indicate a delete but a move. No need to
            // close the editor.
            if (remove) {
                representationDeleted = notification.getOldValue() == dRepresentation && !(dRepresentation.eContainer() instanceof DView);
            } else if (notification.getEventType() == Notification.REMOVE_MANY) {
                representationDeleted = ((Collection<?>) notification.getOldValue()).contains(dRepresentation) && !(dRepresentation.eContainer() instanceof DView);
            }
        }
        return unsetTarget || representationDeleted;
    }
}
