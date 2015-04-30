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

import java.util.Iterator;
import java.util.List;

import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Provide methods to execute tasks recursively.
 * 
 * @author mchauvin
 */
public final class TaskExecutor {

    /**
     * Avoid instanciation.
     */
    private TaskExecutor() {

    }

    /**
     * Test if a list of tasks can execute.
     * 
     * @param tasks
     *            the tasks list to test
     * @return true if the tasks can execute, false otherwise
     */
    public static boolean canExecute(final List<ICommandTask> tasks) {

        boolean result = true;
        final Iterator<ICommandTask> it = tasks.iterator();
        while (it.hasNext() && result) {
            final ICommandTask task = it.next();
            result = result && TaskExecutor.canExecute(task);
        }
        return result;
    }

    /**
     * Execute a list of tasks.
     * 
     * @param tasks
     *            the tasks list to execute
     */
    public static void execute(final List<ICommandTask> tasks) {
        final Iterator<ICommandTask> it = tasks.iterator();
        while (it.hasNext()) {
            final ICommandTask task = it.next();
            try {
                task.execute();
            } catch (MetaClassNotFoundException e) {
                SiriusPlugin.getDefault().error("Error while modifying model", e);
            } catch (FeatureNotFoundException e) {
                SiriusPlugin.getDefault().error("Error while modifying model", e);
            }
            if (!task.executeMyselfChildrenTasks()) {
                TaskExecutor.execute(task.getChildrenTasks());
            }
        }
    }

    private static boolean canExecute(final ICommandTask task) {

        if (!task.canExecute()) {
            return false;
        }
        boolean result = true;
        final Iterator<ICommandTask> it = task.getChildrenTasks().iterator();
        while (it.hasNext() && result) {
            final ICommandTask childTask = it.next();
            result = result && TaskExecutor.canExecute(childTask);
        }
        return result;
    }

}
