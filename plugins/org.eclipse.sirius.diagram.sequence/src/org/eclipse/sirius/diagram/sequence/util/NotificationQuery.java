/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.util;

import org.eclipse.emf.common.notify.Notification;

/**
 * Queries on EMF Notifications to identify what they are about.
 * 
 * @author pcdavid
 */
public class NotificationQuery extends org.eclipse.sirius.diagram.tools.internal.util.NotificationQuery {
    /**
     * Constructor.
     * 
     * @param notif
     *            the notification to query.
     */
    public NotificationQuery(Notification notif) {
        super(notif);
    }
}
