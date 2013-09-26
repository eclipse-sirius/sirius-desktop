/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect;

import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Basic notification for representations.
 * 
 * @author cbrun
 * 
 */
public class RepresentationNotification {
    /**
     * A representation adding.
     */
    public static final int ADD = 1;

    /**
     * A representation removing.
     */
    public static final int REMOVE = 2;

    private final DRepresentation representation;

    private final int type;

    /**
     * Create a new notification instance.
     * 
     * @param representation
     *            the representation .
     * @param type
     *            the type of notification.
     */
    public RepresentationNotification(DRepresentation representation, int type) {
        this.representation = representation;
        this.type = type;
    }

    /**
     * Returns the representation targetted by the notification.
     * 
     * @return the representation targetted by the notification.
     */
    public DRepresentation getTarget() {
        return this.representation;
    }

    /**
     * Return the type of representation event this notification represents.
     * 
     * @return the type of representation event.
     * 
     * @see RepresentationNotification#ADD
     * @see RepresentationNotification#REMOVE
     */
    public int getType() {
        return type;
    }
}
