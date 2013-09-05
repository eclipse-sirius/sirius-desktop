/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

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

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskExecutor;
import org.eclipse.sirius.business.api.preferences.DesignerPreferencesKeys;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.SiriusCommand;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.tools.internal.command.builders.DeletionCommandBuilder;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * A task to delete several {@link DDiagramElement} instances.
 * 
 * @author ymortier
 */
public class DeleteSeveralDDiagramElementsTask extends AbstractCommandTask {

    /** the previous command. */
    private DCommand cmd;

    /** a viewpoint element. */
    private DDiagramElement dde;

    private TransactionalEditingDomain domain;

    private ModelAccessor modelAcessor;

    /** List of executed task, useful for undo * */
    private List<ICommandTask> executedTask;

    private DeletionCommandBuilder deletionCommandBuilder;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the editing domain.
     * @param modelAcessor
     *            the model accessor.
     * @param callback
     *            the user interface callback
     * @param cmd
     *            the current command.
     * @param dde
     *            the diagram element to delete.
     */
    public DeleteSeveralDDiagramElementsTask(final TransactionalEditingDomain domain, final ModelAccessor modelAcessor, final UICallBack callback, final DCommand cmd, final DDiagramElement dde) {
        this.cmd = cmd;
        this.dde = dde;
        this.domain = domain;
        this.modelAcessor = modelAcessor;
        this.deletionCommandBuilder = new DeletionCommandBuilder();
        this.deletionCommandBuilder.init(modelAcessor, domain, callback);
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
        EObject root = null;
        final boolean automaticRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, DesignerPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
        if (automaticRefresh) {
            root = EcoreUtil.getRootContainer(this.dde);
        } else {
            EObjectQuery eObjectQuery = new EObjectQuery(dde);
            root = eObjectQuery.getRepresentation().get();
        }
        final Set<DSemanticDecorator> vpElements = deletionCommandBuilder.getDElementToClearFromSemanticElements(root, completeCollection(this.cmd.getDeletedObjects()));

        final Iterator<DSemanticDecorator> it = vpElements.iterator();
        while (it.hasNext()) {
            final DSemanticDecorator eObj = it.next();
            tasks.add(new DeleteDDiagramElementTask(eObj, modelAcessor));

            if (eObj instanceof DDiagram) {
                final DCommand temp = new SiriusCommand(domain);
                deletionCommandBuilder.addDeleteDiagramTasks(temp, (DDiagram) eObj);
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
     * @see org.eclipse.sirius.business.api.helper.task.AbstractCommandTask#undo()
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
     * @see org.eclipse.sirius.business.api.helper.task.AbstractCommandTask#undo()
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
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return "delete diagram elements task";
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
