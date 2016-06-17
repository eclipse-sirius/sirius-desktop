/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.DeleteEObjectTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * A task to delete several {@link DRepresentationElement} instances.
 * 
 * @author ymortier
 */
public class DeleteDRepresentationElementsTask extends AbstractCompoundTask {

    /** the previous command. */
    private DCommand cmd;

    /** a representation element. */
    private DRepresentationElement repElt;

    private ModelAccessor modelAccessor;

    private TaskHelper taskHelper;

    /**
     * Default constructor.
     * 
     * @param modelAcessor
     *            the model accessor.
     * @param cmd
     *            the current command.
     * @param taskHelper
     *            the command task helper.
     * @param repElt
     *            the representation element to delete.
     */
    public DeleteDRepresentationElementsTask(final ModelAccessor modelAcessor, final DCommand cmd, TaskHelper taskHelper, final DRepresentationElement repElt) {
        this.cmd = cmd;
        this.modelAccessor = modelAcessor;
        this.taskHelper = taskHelper;
        this.repElt = repElt;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    protected List<ICommandTask> prepareSubTasks() {
        /*
         * Now delete all the DSemanticDecorators corresponding to the semantic
         * elements
         */
        final List<ICommandTask> tasks = new ArrayList<ICommandTask>();
        EObject root = null;
        final boolean automaticRefresh = Platform.getPreferencesService().getBoolean(SiriusPlugin.ID, SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false, null);
        if (automaticRefresh) {
            root = new EObjectQuery(this.repElt).getDAnalysis();
        } else {
            EObjectQuery eObjectQuery = new EObjectQuery(repElt);
            root = eObjectQuery.getRepresentation().get();
        }

        final Set<DSemanticDecorator> vpElements = taskHelper.getDElementToClearFromSemanticElements(root, completeCollection(this.cmd.getDeletedObjects()));

        // In case of ExternalJavaActionTask, UnsetTask, ...(all tasks excepted
        // the IDeleteTasks: ForTasks and RemoveEleemntTasks), the command is
        // not aware of its deleted elements, but we could at least check if the
        // target of the current representation element has been deleted.
        if (repElt.eContainer() != null && (repElt.getTarget() == null || repElt.getTarget().eContainer() == null)) {
            vpElements.add(repElt);
        }

        for (DSemanticDecorator semDec : vpElements) {
            tasks.add(new DeleteEObjectTask(semDec, modelAccessor));
            addDialectSpecificAdditionalDeleteSubTasks(semDec, tasks);
        }
        return tasks;
    }

    /**
     * This method can be overridden to add Dialect specific additional delete
     * tasks. A DeleteEObjectTask for the given decorator has already been added
     * to subtasks.
     * 
     * @param decorator
     *            the current decorator to delete
     * @param subTasks
     *            a List<ICommand> to complete
     */
    protected void addDialectSpecificAdditionalDeleteSubTasks(DSemanticDecorator decorator, List<ICommandTask> subTasks) {
        // Nothing to add per default.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.DeleteDRepresentationElementsTask_label;
    }

    private Set<EObject> completeCollection(final Collection<EObject> semantics) {
        final Set<EObject> result = Sets.newHashSet(semantics);
        for (EObject sem : semantics) {
            Iterators.addAll(result, sem.eAllContents());
        }
        return result;
    }
}
