/*******************************************************************************
 * Copyright (c) 2015, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.command.builders;

import java.text.MessageFormat;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * A command task which has the ability to define the {@link DRepresentationElement} to be selected after the tool
 * execution.
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
        return Messages.ElementsToSelectTask_label;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        String elementsToSelectExpression = toolDescription.getElementsToSelect();
        try {
            UIState uiState = dRepresentation.getUiState();
            uiState.unsetElementsToSelect();
            uiState.setInverseSelectionOrder(toolDescription.isInverseSelectionOrder());

            if (!elementsToSelectExpression.isEmpty()) {
                Collection<EObject> evaluateCollection = interpreter.evaluateCollection(context, elementsToSelectExpression);
                // ensure the EList is set
                uiState.getElementsToSelect().clear();
                uiState.getElementsToSelect().addAll(evaluateCollection);
            }
        } catch (EvaluationException e) {
            SiriusPlugin.getDefault().warning(MessageFormat.format(Messages.ElementsToSelectTask_errorMsg, elementsToSelectExpression), e);
        }
    }
}
