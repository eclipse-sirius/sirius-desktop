/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.listener;

/**
 * This interface need to be implemented by classes which want to receive
 * notifications.
 * 
 * @author mchauvin
 * @see Notification
 */
public interface NotificationReceiver {

    /**
     * Method called when a notification is received.
     * 
     * @param kind
     *            {@link Notification.Kind#START} or
     *            {@link Notification.Kind#STOP}
     * @param notification
     *            the notification
     */
    void receive(int kind, int notification);

}
