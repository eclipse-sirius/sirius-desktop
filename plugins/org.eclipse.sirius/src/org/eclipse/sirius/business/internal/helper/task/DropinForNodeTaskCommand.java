/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;

/**
 * Default command for dropin node.
 * 
 * @author lredor
 */
public class DropinForNodeTaskCommand extends AbstractCommandTask {

    /**
     * (non-Javadoc).
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        // do nothing
    }

    public String getLabel() {
        return "DropIn task for node";
    }
}
