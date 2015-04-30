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
package org.eclipse.sirius.tools.api.command;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * Interface for viewpoint command.
 * 
 * @author ymortier
 */
public interface DCommand extends Command {

    /**
     * Get the task list to execute.
     * 
     * @return the task list to execute with this command
     */
    List<ICommandTask> getTasks();

    /**
     * Return all objects (instance of {@link EObject}) that have been created
     * by this command.
     * 
     * @return all objects (instance of {@link EObject}) that have been created
     *         by this command.
     */
    Collection<EObject> getCreatedObjects();

    /**
     * Return all representation elements (instance of
     * {@link DRepresentationElement}) that have been created by this command.
     * 
     * @return all representation elements (instance of
     *         {@link DRepresentationElement}) that have been created by this
     *         command.
     */
    Collection<DRepresentationElement> getCreatedRepresentationElements();

    /**
     * Return all objects (instance of {@link EObject}) that have been deleted
     * by this command.
     * 
     * @return all objects (instance of {@link EObject}) that have been deleted
     *         by this command.
     */
    Collection<EObject> getDeletedObjects();

    /**
     * Return all references (instance of SetValueHistory) that have been
     * created by this command.
     * 
     * @return all references (instance of SetValueHistory) that have been
     *         created by this command.
     */
    Collection<EObject> getCreatedReferences();

    /**
     * Return all elements (instance of {@link EObject}) that have been modified
     * by this command.
     * 
     * @return all elements (instance of {@link EObject}) that have been
     *         modified by this command.
     */
    Collection<EObject> getAffectedElements();

    /**
     * Return an element that has been created by this command.
     * 
     * @return an element that has been created by this command.
     */
    EObject getCreatedElement();

    /**
     * Set the label of the command.
     * 
     * @param label
     *            the command label
     */
    void setLabel(String label);

}
