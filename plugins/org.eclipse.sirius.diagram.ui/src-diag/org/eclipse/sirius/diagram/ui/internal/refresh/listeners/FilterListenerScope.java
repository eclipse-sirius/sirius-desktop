/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
    @Override
    public boolean apply(Notification input) {
        boolean applies = false;

        Object notifier = input.getNotifier();
        if (!input.isTouch() && notifier instanceof EObject) {
            // Do not react to GMF changes.
            applies = !NotationPackage.eINSTANCE.equals(((EObject) notifier).eClass().getEPackage());

            // Do not react to changeId feature changes.
            applies = applies && !ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_ChangeId().equals(input.getFeature());

            // Do not react to arrangeConstraints feature changes.
            applies = applies && !DiagramPackage.eINSTANCE.getAbstractDNode_ArrangeConstraints().equals(input.getFeature());

            // Do not react to internal storage on filter application nor to
            // their modifications.
            applies = applies && !(notifier instanceof GraphicalFilter) && !(DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters().equals(input.getFeature()));

            // Do not react to transient notifications.
            applies = applies && !(new NotificationQuery(input).isTransientNotification());
        }
        return applies;
    }
}
