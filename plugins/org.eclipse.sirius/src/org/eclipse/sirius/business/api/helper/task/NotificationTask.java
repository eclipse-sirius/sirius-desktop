/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.task;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.listener.NotificationUtil;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * This task is useful to send a notification.
 * 
 * @author mchauvin
 * @see org.eclipse.sirius.common.tools.api.listener.Notification
 */
public class NotificationTask extends AbstractCommandTask {

    private EObject element;

    private int kind;

    private int notification;

    /**
     * A task to send a notification.
     * 
     * @param element
     *            the element on which to retrieve adapters.
     * @param kind
     *            The kind of the notification.
     * @param notification
     *            The notification.
     * @see org.eclipse.sirius.common.tools.api.listener.Notification
     */
    public NotificationTask(final EObject element, final int kind, final int notification) {
        this.kind = kind;
        this.notification = notification;
        this.element = element;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        NotificationUtil.sendNotification(element, kind, notification);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.NotificationTask_label;
    }

}
