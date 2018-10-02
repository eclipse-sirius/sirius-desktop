/*******************************************************************************        
 * Copyright (c) 2014, 2016 THALES GLOBAL SERVICES.       
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
