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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.ICreationTask;
import org.eclipse.sirius.business.api.helper.task.TaskExecutor;
import org.eclipse.sirius.business.internal.helper.task.IDeletionTask;
import org.eclipse.sirius.business.internal.helper.task.IModificationTask;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * The single command to use.
 * 
 * @author mchauvin
 */
public class SiriusCommand extends RecordingCommand implements DCommand {

    private static final int CREATED_OBJECTS = 0;

    private static final int CREATED_VIEWS = 1;

    private static final int DELETED_OBJECTS = 2;

    private static final int CREATED_REFERENCES = 3;

    private static final int AFFECTED_ELEMENTS = 4;

    /** The tasks of this command */
    private List<ICommandTask> tasks;

    /**
     * Create a new command.
     * 
     * @param domain
     *            the transactional editing domain
     * @param label
     *            my user-friendly label
     */
    public SiriusCommand(final TransactionalEditingDomain domain, final String label) {
        super(domain, label);
        // here were are smarter than GMF, number of tasks is lower than 5 fast
        // every time
        tasks = new ArrayList<ICommandTask>(5);
    }

    /**
     * Create a new command.
     * 
     * @param domain
     *            the transactional editing domain
     */
    public SiriusCommand(final TransactionalEditingDomain domain) {
        this(domain, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.RecordingCommand#doExecute()
     */
    @Override
    protected void doExecute() {
        TaskExecutor.execute(this.tasks);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.command.AbstractCommand#canExecute()
     */
    @Override
    public boolean canExecute() {
        return TaskExecutor.canExecute(this.tasks);
    }

    /**
     * Get the tasks list.
     * 
     * @return the tasks list
     */
    @Override
    public List<ICommandTask> getTasks() {
        return this.tasks;
    }

    // other things

    private Collection<EObject> getOperationsObject(final int kind) {
        final Collection<EObject> result = new ArrayList<EObject>();
        final Iterator<ICommandTask> it = tasks.iterator();
        while (it.hasNext()) {
            final ICommandTask task = it.next();
            addOperationsObjects(kind, task, result);
        }
        return result;
    }

    private void addOperationsObjects(final int kind, final ICommandTask task, final Collection<EObject> result) {

        switch (kind) {
        case CREATED_OBJECTS:
            if (task instanceof ICreationTask) {
                result.addAll(((ICreationTask) task).getCreatedElements());
            }
            break;
        case CREATED_VIEWS:
            if (task instanceof ICreationTask) {
                result.addAll(((ICreationTask) task).getCreatedRepresentationElements());
            }
            break;
        case DELETED_OBJECTS:
            if (task instanceof IDeletionTask) {
                result.addAll(((IDeletionTask) task).getDeletedElements());
            }
            break;
        case CREATED_REFERENCES:
            if (task instanceof IModificationTask) {
                result.addAll(((IModificationTask) task).getCreatedReferences());
            }
            break;
        case AFFECTED_ELEMENTS:
            if (task instanceof IModificationTask) {
                result.addAll(((IModificationTask) task).getAffectedElements());
            }
            break;
        default:
            break;
        }
        final Iterator<ICommandTask> it = task.getChildrenTasks().iterator();
        while (it.hasNext()) {
            final ICommandTask childTask = it.next();
            addOperationsObjects(kind, childTask, result);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.DCommand#getCreatedObjects()
     */
    @Override
    public Collection<EObject> getCreatedObjects() {
        return getOperationsObject(CREATED_OBJECTS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.DCommand#getCreatedRepresentationElements()
     */
    @Override
    public Collection<DRepresentationElement> getCreatedRepresentationElements() {
        final Collection<EObject> objects = getOperationsObject(CREATED_VIEWS);
        final Collection<DRepresentationElement> elements = new ArrayList<DRepresentationElement>(objects.size());
        for (final EObject eObj : objects) {
            if (eObj instanceof DRepresentationElement) {
                elements.add((DRepresentationElement) eObj);
            }
        }

        return elements;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.DCommand#getDeletedObjects()
     */
    @Override
    public Collection<EObject> getDeletedObjects() {
        return getOperationsObject(DELETED_OBJECTS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.DCommand#getCreatedReferences()
     */
    @Override
    public Collection<EObject> getCreatedReferences() {
        return getOperationsObject(CREATED_REFERENCES);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.DCommand#getAffectedElements()
     */
    @Override
    public Collection<EObject> getAffectedElements() {
        return getOperationsObject(AFFECTED_ELEMENTS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.DCommand#getCreatedElement()
     */
    @Override
    public EObject getCreatedElement() {
        return getCreatedRepresentationElements().isEmpty() ? null : (EObject) getCreatedRepresentationElements().iterator().next();
    }
}
