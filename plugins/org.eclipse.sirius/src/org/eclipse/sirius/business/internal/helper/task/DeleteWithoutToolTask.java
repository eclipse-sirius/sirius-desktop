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
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.DeleteEObjectTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Delete without tool task.
 * 
 * @author mporhel
 */
public class DeleteWithoutToolTask extends AbstractCompoundTask {

    private DRepresentationElement element;

    private Set<EObject> allSemanticElements;

    private ModelAccessor modelAccessor;

    private TaskHelper taskHelper;

    /**
     * Construct a delete without tool task.
     * 
     * @param element
     *            the current {@link DRepresentationElement} to delete.
     * @param allSemanticElements
     *            All the precomputed semantic elements to delete.
     * @param modelAccessor
     *            the model accessor to use for deletion
     * @param taskHelper
     *            the task helper to use to compute the DSemanticDecorator to
     *            delete
     */
    public DeleteWithoutToolTask(DRepresentationElement element, Set<EObject> allSemanticElements, ModelAccessor modelAccessor, TaskHelper taskHelper) {
        this.element = element;
        this.allSemanticElements = allSemanticElements;
        this.modelAccessor = modelAccessor;
        this.taskHelper = taskHelper;
    }

    /**
     * Construct a delete without tool task. The semantic elements to delete
     * will be computed from the given DRepresentationElement element.
     * 
     * @param element
     *            the current {@link DRepresentationElement} to delete.
     * @param modelAccessor
     *            the model accessor to use for deletion
     * @param taskHelper
     *            the task helper to use to compute the DSemanticDecorator to
     *            delete
     */
    public DeleteWithoutToolTask(DRepresentationElement element, ModelAccessor modelAccessor, TaskHelper taskHelper) {
        this.element = element;
        this.modelAccessor = modelAccessor;
        this.taskHelper = taskHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return "Delete without tool";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ICommandTask> prepareSubTasks() {
        List<ICommandTask> subTasks = Lists.newArrayList();
        Option<DRepresentation> parentRepresentation = new EObjectQuery(element).getRepresentation();
        if (parentRepresentation.some()) {

            if (allSemanticElements == null) {
                allSemanticElements = Sets.newLinkedHashSet();
                // Get the corresponding semanticElement (and
                // their children)
                addSemanticElementsToDestroy(element, allSemanticElements);
            }

            // Now delete all the representation elements corresponding to the
            // semantic
            // elements to delete
            final Set<DSemanticDecorator> diagramElements = taskHelper.getDElementToClearFromSemanticElements(parentRepresentation.get(), allSemanticElements);
            for (final DSemanticDecorator decorator : diagramElements) {
                subTasks.add(new DeleteEObjectTask(decorator, modelAccessor));
                addDialectSpecificAdditionalDeleteSubTasks(decorator, subTasks);
            }
        }

        // Now delete all the semantic elements
        for (final EObject semantic : allSemanticElements) {
            DeleteEObjectTask deleteSemanticElementTask = new DeleteEObjectTask(semantic, modelAccessor);
            subTasks.add(deleteSemanticElementTask);
        }

        return subTasks;
    }

    private Set<EObject> addSemanticElementsToDestroy(final DSemanticDecorator repElt, final Set<EObject> elementsToDestroy) {
        EObject semantic = repElt.getTarget();
        if (semantic != null && !elementsToDestroy.contains(semantic)) {
            Iterables.addAll(elementsToDestroy, AllContents.of(semantic, true));
        }
        return elementsToDestroy;
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
}
