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
package org.eclipse.sirius.business.api.helper.task;

/**
 * A task to launch a runnable.
 * 
 * @author mchauvin
 * @since 2.6
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
    public void execute() {
        runnable.run();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "launch a runnable task";
    }

}
