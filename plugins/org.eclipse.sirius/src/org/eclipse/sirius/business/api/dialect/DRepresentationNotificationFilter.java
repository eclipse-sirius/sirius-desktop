/*******************************************************************************
 * Copyright (c) 2009, 2014 Obeo and others. All rights
 * reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A {@link NotificationFilter} to have
 * {@link org.eclipse.emf.transaction.ResourceSetListener} notified only of
 * changes on its {@link DRepresentation}.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DRepresentationNotificationFilter extends NotificationFilter.Custom {

    private DRepresentation dRepresentation;

    /**
     * Default constructor.
     *
     * @param dRepresentation
     *            the {@link DRepresentation} for which we want to be notified
     */
    public DRepresentationNotificationFilter(DRepresentation dRepresentation) {
        this.dRepresentation = dRepresentation;
    }

    @Override
    public boolean matches(Notification notification) {
        boolean matches = false;
        if (!notification.isTouch()) {
            Object notifier = notification.getNotifier();
            if (notifier instanceof EObject) {
                EObject eObject = (EObject) notifier;
                Option<DRepresentation> dRepresentationOption = new EObjectQuery(eObject).getRepresentation();
                matches = dRepresentationOption.some() && dRepresentationOption.get() == dRepresentation;
            }
        }
        return matches;
    }
}
