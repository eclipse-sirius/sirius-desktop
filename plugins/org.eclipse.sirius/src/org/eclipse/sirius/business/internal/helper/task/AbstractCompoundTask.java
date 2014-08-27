/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

import java.util.List;

import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskExecutor;

/**
 * Abstract command able to compute its subtasks at execution time.
 * 
 * @author mporhel
 */
public abstract class AbstractCompoundTask extends AbstractCommandTask {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public final void execute() {
        final List<ICommandTask> tasks = prepareSubTasks();

        if (TaskExecutor.canExecute(tasks)) {
            getChildrenTasks().addAll(0, tasks);
        }
    }

    /**
     * Prepare some sub tasks to add at the beginning of the getChildren() tasks
     * (after a TaskExecutor.canExecute() check on the list). They will be
     * executed at the end of this command.
     * 
     * @return a List of subtasks.
     */
    protected abstract List<ICommandTask> prepareSubTasks();
}
