/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.command.builders;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * A command task which has the ability to define the
 * {@link DRepresentationElement} to be selected after the tool execution.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class ElementsToSelectTask extends AbstractCommandTask {

    private AbstractToolDescription toolDescription;

    private IInterpreter interpreter;

    private EObject context;

    private DRepresentation dRepresentation;

    /**
     * Constructor.
     * 
     * @param toolDescription
     *            the tool from which to get the expression
     * @param interpreter
     *            the interpreter
     * @param context
     *            the context of interpreter
     * @param dRepresentation
     *            the representation on which to store the selected elements
     */
    public ElementsToSelectTask(final AbstractToolDescription toolDescription, final IInterpreter interpreter, final EObject context, final DRepresentation dRepresentation) {
        this.toolDescription = toolDescription;
        this.interpreter = interpreter;
        this.context = context;
        this.dRepresentation = dRepresentation;

    }

    @Override
    public String getLabel() {
        return "Get result of Elements To Select interpreted expression";
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        String elementsToSelectExpression = toolDescription.getElementsToSelect();
        try {
            UIState uiState = dRepresentation.getUiState();
            if (uiState == null) {
                uiState = ViewpointFactory.eINSTANCE.createUIState();
                dRepresentation.setUiState(uiState);
            }
            uiState.unsetElementsToSelect();
            uiState.setInverseSelectionOrder(toolDescription.isInverseSelectionOrder());

            if (!elementsToSelectExpression.isEmpty()) {
                Collection<EObject> evaluateCollection = interpreter.evaluateCollection(context, elementsToSelectExpression);
                // ensure the EList is set
                uiState.getElementsToSelect().clear();
                uiState.getElementsToSelect().addAll(evaluateCollection);
            }
        } catch (EvaluationException e) {
            SiriusPlugin.getDefault().warning("the following \"Elements To Select\" expression could not be correctly evaluated : " + elementsToSelectExpression, e);
        }
    }
}
