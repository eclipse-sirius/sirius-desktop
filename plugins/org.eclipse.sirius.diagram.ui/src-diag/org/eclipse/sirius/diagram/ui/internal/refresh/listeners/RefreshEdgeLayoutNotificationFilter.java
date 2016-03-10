/*******************************************************************************        
 * Copyright (c) 2014, 2016 THALES GLOBAL SERVICES.       
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
import org.eclipse.sirius.business.api.dialect.DRepresentationNotificationFilter;
import org.eclipse.sirius.diagram.DDiagram;

/**
 * A NotificationFilter to restrict the scope of RefreshEdgeLayoutChangeTrigger
 * (all notifications of the current diagram that have a new value).
 * 
 * @author Florian Barbin
 */
public class RefreshEdgeLayoutNotificationFilter extends DRepresentationNotificationFilter {

    public RefreshEdgeLayoutNotificationFilter(DDiagram dDiagram) {
        super(dDiagram);
    }

    @Override
    public boolean matches(Notification notification) {
        return super.matches(notification) && notification.getNewValue() != null;
    }
}
