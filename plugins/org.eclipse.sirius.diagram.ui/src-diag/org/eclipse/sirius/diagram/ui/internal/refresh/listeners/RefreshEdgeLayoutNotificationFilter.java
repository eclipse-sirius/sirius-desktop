/*******************************************************************************        
 * Copyright (c) 2014 THALES GLOBAL SERVICES.       
 * All rights reserved. This program and the accompanying materials     
 * are made available under the terms of the Eclipse Public License v1.0        
 * which accompanies this distribution, and is available at     
 * http://www.eclipse.org/legal/epl-v10.html        
 *      
 * Contributors:        
 *    Obeo - initial API and implementation     
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.listeners;

import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;

import com.google.common.collect.Sets;

/**
 * A NotificationFilter to restrict the scope of RefreshEdgeLayoutChangeTrigger.
 * 
 * @author Florian Barbin
 * 
 */
public class RefreshEdgeLayoutNotificationFilter extends NotificationFilter.Custom {
    private DDiagram dDiagram;

    public RefreshEdgeLayoutNotificationFilter(DDiagram dDiagram) {
        super();
        this.dDiagram = dDiagram;
    }

    @Override
    public boolean matches(Notification notification) {
        if (!notification.isTouch() && notification.getNewValue() != null) {
            Object notifier = notification.getNotifier();

            // The notifier has to be part of the same ddiagram.
            if (isPartOfTheSameDiagram(notifier)) {
                return isNotificationForRefreshEdgeLayout(notification);
            }
        }
        return false;
    }

    private boolean isNotificationForRefreshEdgeLayout(Notification notification) {
        switch (notification.getEventType()) {
        case Notification.SET:
        case Notification.ADD:

            Set<EStructuralFeature> featureToContainDDiagramElements = Sets.newLinkedHashSet();
            featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDEdge_OwnedStyle());
            featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getEdgeStyle_Centered());
            featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getEdgeStyle_RoutingStyle());
            return featureToContainDDiagramElements.contains(notification.getFeature());
        default:
            break;
        }

        return false;
    }

    private boolean isPartOfTheSameDiagram(Object notifier) {
        DEdge dEdge = null;
        if (notifier instanceof DEdge) {
            dEdge = (DEdge) notifier;
        } else if (notifier instanceof EdgeStyle) {
            EObject container = ((EdgeStyle) notifier).eContainer();
            if (container instanceof DEdge) {
                dEdge = (DEdge) container;
            }
        }
        if (dEdge != null) {
            DDiagram parentDDiagram = dEdge.getParentDiagram();
            return parentDDiagram == this.dDiagram;

        }
        return false;
    }

}
