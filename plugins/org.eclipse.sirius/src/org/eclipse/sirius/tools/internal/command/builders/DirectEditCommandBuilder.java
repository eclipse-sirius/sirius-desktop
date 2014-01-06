/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.command.builders;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.label.InitInterpreterFromParsedVariableTask;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DLabelled;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.DirectEditLabel;

public class DirectEditCommandBuilder extends AbstractCommandBuilder {

    private static final String EDIT_LABEL = "Edit label";

    private DLabelled labeled;

    private DirectEditLabel directEditTool;

    private String newValue;

    /**
     * Constructor.
     * 
     * @param labeled
     *            : the element on which the label should be changed.
     * @param directEditTool
     *            : the tool description.
     * @param newValue
     *            : the new label value
     */
    public DirectEditCommandBuilder(DLabelled labeled, DirectEditLabel directEditTool, String newValue) {
        this.labeled = labeled;
        this.directEditTool = directEditTool;
        this.newValue = newValue;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    public Command buildCommand() {
        // Layouting mode on diagrams, if the ddiagram is in
        // LayoutingMode, we do not allow direct edit.
        if (this.permissionAuthority.canEditInstance(labeled) && !isInLayoutingModeDiagram(labeled)) {
            final DCommand result = createEnclosingCommand();
            /*
             * First we need to init the mask variables.
             */
            String messageFormat = "$0";
            if (directEditTool.getMask() != null) {
                messageFormat = directEditTool.getMask().getMask();
            }
            IInterpreter interpreter = InterpreterUtil.getInterpreter(labeled);
            result.getTasks().add(new InitInterpreterFromParsedVariableTask(interpreter, messageFormat, newValue));

            Option<DDiagram> parentDiagram = new EObjectQuery(labeled).getParentDiagram();
            if (parentDiagram.some() && labeled instanceof DSemanticDecorator && ((DSemanticDecorator) labeled).getTarget() != null && directEditTool.getInitialOperation() != null) {
                final ICommandTask operations = taskHelper.buildTaskFromModelOperation(parentDiagram.get(), ((DSemanticDecorator) labeled).getTarget(), directEditTool.getInitialOperation()
                        .getFirstModelOperations());
                result.getTasks().add(operations);
            }
            addPostOperationTasks(result, interpreter);
            return result;
        }
        return UnexecutableCommand.INSTANCE;
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
        if (labeled instanceof DDiagramElement) {
            addRefreshTask((DDiagramElement) labeled, command, directEditTool);
        }
        /**
         * FIX to update the node list element
         */
        if (labeled instanceof DSemanticDecorator) {
            addRemoveDanglingReferencesTask(command, directEditTool, (DSemanticDecorator) labeled);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected String getEnclosingCommandLabel() {
        return EDIT_LABEL;
    }

}
