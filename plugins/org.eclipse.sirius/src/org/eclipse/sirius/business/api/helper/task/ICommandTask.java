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

import java.util.List;

import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;

/**
 * An interface fast similar to org.eclipse.emf.command. but
 * 
 * @author mchauvin
 */
public interface ICommandTask {

    /**
     * Get a quick description for the task.
     * 
     * @return a quick description
     */
    String getLabel();

    /**
     * Get the children tasks.
     * 
     * @return the children tasks
     */
    List<ICommandTask> getChildrenTasks();

    /**
     * Execute the task. By default the implementation should not execute the
     * children tasks.
     * 
     * @throws MetaClassNotFoundException
     *             a meta class was not found.
     * @throws FeatureNotFoundException
     *             a feature was not found.
     */
    void execute() throws MetaClassNotFoundException, FeatureNotFoundException;

    /**
     * Check if the task execute itself its children tasks.
     * 
     * @return true if the task execute itself its children tasks, false
     *         otherwise
     */
    boolean executeMyselfChildrenTasks();

    /**
     * Test if the task can execute or not.
     * 
     * @return true if task can execute, false otherwise
     */
    boolean canExecute();

}
