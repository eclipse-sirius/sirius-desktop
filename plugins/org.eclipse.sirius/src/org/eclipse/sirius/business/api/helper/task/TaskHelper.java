/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.task;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.business.internal.helper.task.ExecuteToolOperationTask;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Helper for get tasks from ModelOperation. Provide some utilities reused in
 * different EMFCommandFactory
 * 
 * @author lredor
 * 
 */
public class TaskHelper {

    /** The model accessor. */
    private ModelAccessor modelAccessor;

    /** the user interface callback. */
    private UICallBack uiCallback;

    /**
     * The default constructor.
     * 
     * @param modelAccessor
     *            The model accessor
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public TaskHelper(final ModelAccessor modelAccessor, final UICallBack uiCallback) {
        this.modelAccessor = modelAccessor;
        this.uiCallback = uiCallback;
    }

    /**
     * Create an {@link ExecuteToolOperationTask} with the specified context and
     * operation.
     * 
     * @param representation
     *            the representation
     * @param target
     *            the context.
     * @param op
     *            the operation.
     * @return the created {@link ExecuteToolOperationTask}.
     * @since 2.6
     */
    public ICommandTask buildTaskFromModelOperation(final DRepresentation representation, final EObject target, final ModelOperation op) {
        final ExecuteToolOperationTask task = new ExecuteToolOperationTask(modelAccessor, target, representation, op, uiCallback);
        return task;
    }

    /**
     * Returns all the {@link DSemanticDecorator} elements to delete.
     * 
     * @param root
     *            the root view point element.
     * @param semanticElements
     *            semantic elements.
     * @return all the {@link DSemanticDecorator} elements to delete.
     */
    public Set<DSemanticDecorator> getDElementToClearFromSemanticElements(final EObject root, final Set<EObject> semanticElements) {
        final Set<DSemanticDecorator> decoratorsToDestroy = new HashSet<DSemanticDecorator>();
        if (!semanticElements.isEmpty()) {
            final Iterator<EObject> it = root.eAllContents();
            while (it.hasNext()) {
                final EObject eObj = it.next();

                if (eObj instanceof DRepresentationElement) {
                    final DRepresentationElement representationElement = (DRepresentationElement) eObj;
                    if (representationElement.getSemanticElements().isEmpty()) {
                        if (semanticElements.contains(representationElement.getTarget())) {
                            decoratorsToDestroy.add(representationElement);
                        }
                    } else if (semanticElements.containsAll(representationElement.getSemanticElements())) {
                        decoratorsToDestroy.add(representationElement);
                    }
                } else if (eObj instanceof DSemanticDecorator) {
                    final DSemanticDecorator decorator = (DSemanticDecorator) eObj;
                    if (semanticElements.contains(decorator.getTarget())) {
                        decoratorsToDestroy.add(decorator);
                    }
                }
            }
        }
        return decoratorsToDestroy;
    }

    /**
     * Check the precondition of the specified tool with the specified
     * container.
     * 
     * @param container
     *            the container variable.
     * @param toolDescription
     *            the
     *            {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription}
     *            .
     * @return <code>true</code> if the predicate is <code>true</code>.
     * @deprecated the code will be in
     *             {@link org.eclipse.sirius.tools.internal.command.builders.AbstractCommandBuilder}
     *             in future refactoring
     */
    @Deprecated
    public boolean checkPrecondition(final EObject container, final AbstractToolDescription toolDescription) {
        boolean result = true;
        if (toolDescription.getPrecondition() != null && !StringUtil.isEmpty(toolDescription.getPrecondition().trim())) {
            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);
            // acceleoInterpreter.clearVariables();
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, container);
            try {
                result = false;
                result = interpreter.evaluateBoolean(container, toolDescription.getPrecondition());
            } catch (final EvaluationException e) {
                // silent.
            }
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        }
        return result;
    }

}
