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

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
            return (notifier instanceof EObject && isPartOfTheSameDiagram((EObject) notifier));
        }
        return false;
    }

    /**
     * Test whether the edge centering should be refreshed for this
     * notification.
     * 
     * @param notification
     *            the notification.
     * @return true if this notification concerns the edge ends centering, false
     *         otherwise.
     */
    public static boolean isNotificationForRefreshEdgeLayout(Notification notification) {
        switch (notification.getEventType()) {
        case Notification.SET:
        case Notification.ADD:

            Set<EStructuralFeature> featureToContainDDiagramElements = Sets.newLinkedHashSet();
            featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getDEdge_OwnedStyle());
            featureToContainDDiagramElements.add(DiagramPackage.eINSTANCE.getEdgeStyle_Centered());
            featureToContainDDiagramElements.add(NotationPackage.eINSTANCE.getRoutingStyle_Routing());
            return featureToContainDDiagramElements.contains(notification.getFeature());
        default:
            break;
        }

        return false;
    }

    /**
     * Test whether the other notifications concern the given one. For instance,
     * in case of a manual modification of the GMF routing style, we also update
     * the Sirius style and we add the routing style within the custom features.
     * This method aims to check whether we are in the case of an individual
     * modification or a global one.
     * 
     * @param notification
     *            the notification for which we are notified.
     * @param notifications
     *            the whole notification list.
     * @return true if the notifications list contains only notifications
     *         induced by the first one.
     */
    public static boolean otherNotificationsAreIndirectlyConcerned(Notification notification, Collection<Notification> notifications) {
        if (NotationPackage.eINSTANCE.getRoutingStyle_Routing().equals(notification.getFeature())) {
            Set<EStructuralFeature> concernedFeature = Sets.newLinkedHashSet();
            concernedFeature.add(ViewpointPackage.eINSTANCE.getCustomizable_CustomFeatures());
            concernedFeature.add(DiagramPackage.eINSTANCE.getEdgeStyle_RoutingStyle());
            for (Notification currentNotification : notifications) {
                if (currentNotification != notification && !concernedFeature.contains(currentNotification.getFeature())) {
                    return false;
                }
            }
            return true;
        } else {
            return notifications.size() == 1;
        }
    }

    private boolean isPartOfTheSameDiagram(EObject eObject) {
        DDiagram currentDDiagram = getParentDDiagram(eObject);
        return currentDDiagram == this.dDiagram;

    }

    private DDiagram getParentDDiagram(EObject eObject) {
        DDiagram parent = null;
        if (eObject instanceof DDiagram) {
            parent = (DDiagram) eObject;
        } else if (eObject instanceof Diagram) {
            parent = (DDiagram) ((Diagram) eObject).getElement();
        } else if (eObject != null) {
            parent = getParentDDiagram(eObject.eContainer());
        }

        return parent;
    }

}
