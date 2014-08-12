/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.helper.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.DeleteDRepresentationElementTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskExecutor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.tools.internal.command.TableCommandFactory;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A task to delete {@link DTableElement} instances.
 * 
 * @author lredor
 */
public class DeleteTableElementsTask extends AbstractCommandTask {

    /** the previous command. */
    private SiriusCommand cmd;

    /** the command factory. */
    private TableCommandFactory tableCommandFactory;

    /** a table element. */
    private DTableElement tableElement;

    private TransactionalEditingDomain domain;

    private ModelAccessor modelAccessor;

    /** List of executed task, useful for undo * */
    private List<ICommandTask> executedTask;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param modelAcessor
     *            the model accessor.
     * @param cmd
     *            the current command.
     * @param tableCommandFactory
     *            the command factory.
     * @param tableElement
     *            the viewpoint element to delete.
     */
    public DeleteTableElementsTask(final TransactionalEditingDomain domain, final ModelAccessor modelAcessor, final SiriusCommand cmd, final TableCommandFactory tableCommandFactory,
            final DTableElement tableElement) {
        this.cmd = cmd;
        this.tableCommandFactory = tableCommandFactory;
        this.tableElement = tableElement;
        this.domain = domain;
        this.modelAccessor = modelAcessor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {
        /*
         * Now delete all the viewpoints corresponding to the semantic elements
         */
        final List<ICommandTask> tasks = new ArrayList<ICommandTask>();
        Set<DSemanticDecorator> vpElements = null;
        final boolean automaticRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
        if (automaticRefresh) {
            vpElements = tableCommandFactory.getCommandTaskHelper().getDElementToClearFromSemanticElements(EcoreUtil.getRootContainer(this.tableElement),
                    completeCollection(this.cmd.getDeletedObjects()));
        } else {
            vpElements = new HashSet<DSemanticDecorator>();
            vpElements.add(tableElement);
        }

        final Iterator<DSemanticDecorator> it = vpElements.iterator();
        while (it.hasNext()) {
            final DSemanticDecorator eObj = it.next();
            tasks.add(new DeleteDRepresentationElementTask(eObj, modelAccessor));

            if (eObj instanceof DTable) {
                // TODOMCH change this really crappy code ! ! ! !
                // create a command only to add tasks
                final SiriusCommand temp = new SiriusCommand(domain);
                tableCommandFactory.addDeleteTableTasks(temp, (DTable) eObj);
                final List<ICommandTask> tempTasks = temp.getTasks();
                if (TaskExecutor.canExecute(tempTasks)) {
                    TaskExecutor.execute(tempTasks);
                    if (this.executedTask == null) {
                        this.executedTask = new LinkedList<ICommandTask>();
                    }
                    this.executedTask.addAll(tempTasks);
                }
            }
        }
        if (TaskExecutor.canExecute(tasks)) {
            TaskExecutor.execute(tasks);
            if (this.executedTask != null) {
                this.executedTask.addAll(tasks);
            } else {
                this.executedTask = tasks;
            }
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#undo()
     */
    @Override
    public void undo() {
        if (this.executedTask != null) {
            TaskExecutor.undo(this.executedTask);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#undo()
     */
    @Override
    public void redo() {
        if (this.executedTask != null) {
            TaskExecutor.redo(this.executedTask);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see forg.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "delete viewpint elements task";
    }

    private Set<EObject> completeCollection(final Collection<EObject> semantics) {
        final Set<EObject> result = new HashSet<EObject>(semantics);
        final Iterator<EObject> iterSemantics = semantics.iterator();
        while (iterSemantics.hasNext()) {
            final EObject sem = iterSemantics.next();
            final Iterator<EObject> iterContent = sem.eAllContents();
            while (iterContent.hasNext()) {
                result.add(iterContent.next());
            }
        }
        return result;
    }

}
