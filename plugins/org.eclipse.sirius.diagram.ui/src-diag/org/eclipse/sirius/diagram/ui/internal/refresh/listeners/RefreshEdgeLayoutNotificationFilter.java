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
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.dialect.DRepresentationNotificationFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A NotificationFilter to restrict the scope of RefreshEdgeLayoutChangeTrigger.
 * 
 * @author Florian Barbin
 */
public class RefreshEdgeLayoutNotificationFilter extends DRepresentationNotificationFilter {

    private static final Set<EStructuralFeature> INTERESTING_FEATURES = new HashSet<EStructuralFeature>();

    static {
        INTERESTING_FEATURES.add(DiagramPackage.Literals.DEDGE__OWNED_STYLE);
        INTERESTING_FEATURES.add(DiagramPackage.Literals.EDGE_STYLE__CENTERED);
        INTERESTING_FEATURES.add(NotationPackage.Literals.ROUTING_STYLE__ROUTING);
        INTERESTING_FEATURES.add(NotationPackage.Literals.DIAGRAM__PERSISTED_EDGES);
    }

    private static final Set<EStructuralFeature> INDIRECT_INTERESTING_FEATURES = new HashSet<EStructuralFeature>();

    static {
        INDIRECT_INTERESTING_FEATURES.add(ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES);
        INDIRECT_INTERESTING_FEATURES.add(DiagramPackage.Literals.EDGE_STYLE__ROUTING_STYLE);
    }

    public RefreshEdgeLayoutNotificationFilter(DDiagram dDiagram) {
        super(dDiagram);
    }

    @Override
    public boolean matches(Notification notification) {
        boolean matches = super.matches(notification) && notification.getNewValue() != null && INTERESTING_FEATURES.contains(notification.getFeature());
        return matches;
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
    public boolean otherNotificationsAreIndirectlyConcerned(Notification notification, Collection<Notification> notifications) {
        boolean otherNotificationsAreIndirectlyConcerned = false;
        if (NotationPackage.Literals.ROUTING_STYLE__ROUTING == notification.getFeature() || DiagramPackage.Literals.EDGE_STYLE__CENTERED == notification.getFeature()) {
            for (Notification currentNotification : notifications) {
                if (currentNotification != notification && !INDIRECT_INTERESTING_FEATURES.contains(currentNotification.getFeature())) {
                    otherNotificationsAreIndirectlyConcerned = true;
                    break;
                }
            }
        }
        return otherNotificationsAreIndirectlyConcerned;
    }

}
