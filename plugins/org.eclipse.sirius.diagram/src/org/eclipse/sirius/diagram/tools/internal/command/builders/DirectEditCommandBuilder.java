/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.label.InitInterpreterFromParsedVariableTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.helper.task.InitGlobalDirectEditVariablesTask;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

public class DirectEditCommandBuilder extends AbstractDiagramCommandBuilder {

    private DRepresentationElement repElement;

    private DirectEditLabel directEditTool;

    private String newValue;

    /**
     * Constructor.
     * 
     * @param repElement
     *            : the element on which the label should be changed.
     * @param directEditTool
     *            : the tool description.
     * @param newValue
     *            : the new label value
     */
    public DirectEditCommandBuilder(DRepresentationElement repElement, DirectEditLabel directEditTool, String newValue) {
        this.repElement = repElement;
        this.directEditTool = directEditTool;
        this.newValue = newValue;
    }

    @Override
    public Command buildCommand() {
        if (this.permissionAuthority.canEditInstance(repElement) && canDirectEdit()) {
            final DCommand result = createEnclosingCommand();
            /*
             * First we need to init the mask variables.
             */
            String messageFormat = "$0"; //$NON-NLS-1$
            if (directEditTool.getMask() != null) {
                messageFormat = directEditTool.getMask().getMask();
            }
            IInterpreter interpreter = InterpreterUtil.getInterpreter(repElement);
            Option<DDiagram> parentDiagram = getDDiagram();
            if (parentDiagram.some() && repElement.getTarget() != null && directEditTool.getInitialOperation() != null) {
                result.getTasks().add(new InitInterpreterFromParsedVariableTask(interpreter, messageFormat, newValue));
                result.getTasks().add(new InitGlobalDirectEditVariablesTask(interpreter, parentDiagram.get(), repElement));

                final ICommandTask operations = taskHelper.buildTaskFromModelOperation(parentDiagram.get(), repElement.getTarget(), directEditTool.getInitialOperation().getFirstModelOperations());
                result.getTasks().add(operations);
            }
            addPostOperationTasks(result, interpreter);
            result.getTasks().add(new InitGlobalDirectEditVariablesTask(interpreter));
            result.getTasks().add(new InitInterpreterFromParsedVariableTask(interpreter, messageFormat, newValue, true));
            return result;
        }
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Indicates if the label to edit can be directly editable.
     * 
     * @return true if the label can be direct edited, false otherwise
     */
    public boolean canDirectEdit() {
        // if the diagram is in LayoutingMode or show/hide mode, we do not allow direct edit.
        boolean valid = !isInLayoutingModeDiagram(repElement) && !isInShowingModeDiagram(repElement);
        valid = valid && checkPrecondition((DDiagramElement) repElement, directEditTool);
        return valid;
    }

    /**
     * Add tasks to execute after model operations.
     * 
     * @param command
     *            the command to complete
     * @param interpreter
     *            the current interpreter.
     */
    protected void addPostOperationTasks(final DCommand command, IInterpreter interpreter) {
        if (repElement instanceof DDiagramElement) {
            addRefreshTask((DDiagramElement) repElement, command, directEditTool);
            Option<DDiagram> parentDiagram = new EObjectQuery(repElement).getParentDiagram();
            command.getTasks().add(new ElementsToSelectTask(directEditTool, interpreter, repElement.getTarget(), parentDiagram.get()));
        }
    }

    @Override
    protected String getEnclosingCommandLabel() {
        return Messages.DirectEditCommandBuilder_editLabel;
    }

    @Override
    protected Option<DDiagram> getDDiagram() {
        return new EObjectQuery(repElement).getParentDiagram();
    }
}
