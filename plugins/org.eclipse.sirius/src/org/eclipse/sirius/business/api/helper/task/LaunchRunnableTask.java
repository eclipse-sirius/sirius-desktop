/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.task;

import org.eclipse.sirius.viewpoint.Messages;

/**
 * A task to launch a runnable.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class LaunchRunnableTask extends AbstractCommandTask {

    private Runnable runnable;

    /**
     * Create a new instance.
     * 
     * @param runnable
     *            a runnable
     */
    public LaunchRunnableTask(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        runnable.run();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.LaunchRunnableTask_label;
    }

}
