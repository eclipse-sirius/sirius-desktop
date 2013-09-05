/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.listener;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;

/**
 * A class to send custom notifications.
 * 
 * @author mchauvin
 */
public final class NotificationUtil {

    /**
     * Avoid instantiation
     */
    private NotificationUtil() {

    }

    /**
     * Send a notification to all {@link NotificationReceiver} instances
     * registered as adapter of an {@link EObject}.
     * 
     * @param eObj
     *            an {@link EObject} instance
     * @param kind
     *            {@link Notification.Kind#START} or
     *            {@link Notification.Kind#STOP}
     * @param notification
     *            {@link Notification#USER_OPERATION_ON_MANY_ELEMENTS}
     * @see Notification.Kind
     */
    public static void sendNotification(final EObject eObj, final int kind, final int notification) {
        final Iterator<Adapter> itAdaptaters = eObj.eAdapters().iterator();
        while (itAdaptaters.hasNext()) {
            final Adapter adapter = itAdaptaters.next();
            if (adapter instanceof NotificationReceiver) {
                ((NotificationReceiver) adapter).receive(kind, notification);
            }
        }
    }

}
