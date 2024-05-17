/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.diagram.business.internal.helper.task;

import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * A Task to initialize the global variables before executing a direct edit tools. The same variables that are set
 * {@link org.eclipse.sirius.diagram.business.internal.metamodel.helper.DiagramElementMappingHelper#computeInputLabelOfDirectEditLabel(org.eclipse.sirius.diagram.DDiagramElement, DDiagram, org.eclipse.sirius.diagram.description.tool.DirectEditLabel, IInterpreter)}.</BR>
 * It can also be used to unset these variables (with the dedicated constructor).
 * 
 * @author Laurent Redor
 */
public class InitGlobalDirectEditVariablesTask extends AbstractCommandTask {

    private IInterpreter interpreter;

    private DDiagram dDiagram;

    private DRepresentationElement dRepresentationElement;

    private boolean unset;

    /**
     * Default constructor to set the variables.
     * 
     * @param interpreter
     *            the interpreter
     * @param dDiagram
     *            The representation containing the <code>dRepresentationElement</code>
     * @param dRepresentationElement
     *            The current <code>dRepresentationElement</code> edited
     */
    public InitGlobalDirectEditVariablesTask(final IInterpreter interpreter, final DDiagram dDiagram, final DRepresentationElement dRepresentationElement) {
        this.interpreter = interpreter;
        this.dDiagram = dDiagram;
        this.dRepresentationElement = dRepresentationElement;
    }

    /**
     * Default constructor to unset the variables.
     * 
     * @param interpreter
     *            the interpreter
     * @param dDiagram
     *            The representation containing the <code>dRepresentationElement</code>
     * @param dRepresentationElement
     *            The current <code>dRepresentationElement</code> edited
     * @param set
     * 
     */
    public InitGlobalDirectEditVariablesTask(final IInterpreter interpreter) {
        this.interpreter = interpreter;
        this.unset = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        if (unset) {
            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
            interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        } else {
            interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, dDiagram);
            interpreter.setVariable(IInterpreterSiriusVariables.VIEW, dRepresentationElement);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    @Override
    public String getLabel() {
        if (unset) {
            return Messages.InitGlobalDirectEditVariablesTask_unsetLabel;
        } else {
            return Messages.InitGlobalDirectEditVariablesTask_setLabel;
        }
    }
}
