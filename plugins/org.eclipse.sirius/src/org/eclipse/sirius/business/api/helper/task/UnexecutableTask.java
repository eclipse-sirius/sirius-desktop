/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.helper.task;

import org.eclipse.sirius.tools.api.Messages;

/**
 * A singleton {@link UnexecutableTask#INSTANCE} that cannot execute.
 * 
 * @author mchauvin
 */
public final class UnexecutableTask extends AbstractCommandTask {

    /**
     * The one instance of this object.
     */
    public static final UnexecutableTask INSTANCE = new UnexecutableTask();

    private UnexecutableTask() {

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.AbstractCommandTask#canExecute()
     */
    @Override
    public boolean canExecute() {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.UnexecutableTask_label;
    }

}
