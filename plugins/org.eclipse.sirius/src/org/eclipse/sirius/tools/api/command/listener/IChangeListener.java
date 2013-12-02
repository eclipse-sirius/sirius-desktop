/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.api.command.listener;

import org.eclipse.emf.common.notify.Adapter;

/**
 * A listener which record created, modified and deleted elements and which is
 * able to launch a trigger operation with these elements as parameters.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public interface IChangeListener extends Adapter {
    /**
     * Activate this listener.
     */
    void activate();

    /**
     * Deactivate this listener.
     */
    void deactivate();

    /**
     * Set the trigger operation for this listener.
     * 
     * @param operation
     *            the trigger operation
     */
    void setTriggerOperation(TriggerOperation operation);

    /**
     * Launches the trigger operation if there is one.
     */
    void launchTriggerOperation();

    /**
     * Check if this listener is activated.
     * 
     * @return true if this listener is activated.
     */
    boolean isActivated();
}
