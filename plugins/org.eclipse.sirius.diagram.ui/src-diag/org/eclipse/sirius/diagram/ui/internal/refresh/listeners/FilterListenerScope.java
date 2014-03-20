/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;

import com.google.common.base.Predicate;

/**
 * A NotificationFilter for {@link FilterListener}.
 * 
 * @author mporhel
 */
public class FilterListenerScope implements Predicate<Notification> {

    /**
     * {@inheritDoc}
     */
    public boolean apply(Notification input) {
        boolean applies = false;

        Object notifier = input.getNotifier();
        if (!input.isTouch() && notifier instanceof EObject) {
            // Do not react to GMF changes.
            applies = !NotationPackage.eINSTANCE.equals(((EObject) notifier).eClass().getEPackage());

            // Do not react to internal storage on filter application nor to
            // their modifications.
            applies = applies && !(notifier instanceof GraphicalFilter) && !(DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters().equals(input.getFeature()));

            // Do not react to transient notifications.
            applies = applies && !(new NotificationQuery(input).isTransientNotification());
        }
        return applies;
    }
}
