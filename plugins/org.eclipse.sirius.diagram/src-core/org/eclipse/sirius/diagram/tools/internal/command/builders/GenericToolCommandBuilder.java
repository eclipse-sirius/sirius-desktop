/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;

/**
 * Command builder for generic tool.
 * 
 * @author mporhel
 */
public class GenericToolCommandBuilder extends AbstractDiagramCommandBuilder {

    /**
     * Current tool description from which this CommandBuilder build a Command.
     */
    protected final ToolDescription tool;

    /**
     * View on which the current ToolDescription's operations are executed.
     */
    protected EObject containerView;

    /**
     * Create a new node creation command builder instance.
     * 
     * @param tool
     *            a node creation tool
     * @param containerView
     *            the diagram or diagram element on which the operations should
     *            be applied.
     */
    public GenericToolCommandBuilder(final ToolDescription tool, final EObject containerView) {
        this.tool = tool;
        this.containerView = containerView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    @Override
    public final Command buildCommand() {
        final DCommand result = createEnclosingCommand();
        final IInterpreter interpreter = InterpreterUtil.getInterpreter(containerView);
        if (checkGenericToolPrecondition(interpreter)) {
            Option<DDiagram> parentDiagram = getDDiagram();
            if (parentDiagram.some() && tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                addPreOperationTasks(result, interpreter);

                EObject container = containerView;
                if (containerView instanceof DSemanticDecorator) {
                    container = ((DSemanticDecorator) containerView).getTarget();
                }
                result.getTasks().add(taskHelper.buildTaskFromModelOperation(parentDiagram.get(), container, tool.getInitialOperation().getFirstModelOperations()));

                addPostOperationTasks(result, interpreter);
            }
        } else {
            result.getTasks().add(UnexecutableTask.INSTANCE);
        }
        return result;
    }

    /**
     * Add tasks to execute before model operations.
     * 
     * @param command
     *            the command to complete
     * @param interpreter
     *            the current interpreter
     */
    protected void addPreOperationTasks(DCommand command, IInterpreter interpreter) {
        final InitInterpreterVariablesTask initInterpreterVariablesTask = buildInitVariablesTasks(interpreter);
        command.getTasks().add(initInterpreterVariablesTask);
        addDiagramVariable(command, containerView, interpreter);
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
        if (containerView instanceof DDiagramElement) {
            addRefreshTask((DDiagramElement) containerView, command, tool);

        } else if (containerView instanceof DDiagram) {
            addRefreshTask((DDiagram) containerView, command, tool);
        }
        Option<DDiagram> parentDiagram = new EObjectQuery(containerView).getParentDiagram();
        command.getTasks().add(new ElementsToSelectTask(tool, interpreter, containerView, parentDiagram.get()));
    }

    private InitInterpreterVariablesTask buildInitVariablesTasks(final IInterpreter interpreter) {
        final Map<AbstractVariable, Object> variables = getVariables();

        final InitInterpreterVariablesTask initInterpreterVariablesTask = new InitInterpreterVariablesTask(variables, interpreter, uiCallback);
        return initInterpreterVariablesTask;
    }

    /**
     * Variables used by the tool.
     * 
     * @return variables.
     */
    protected Map<AbstractVariable, Object> getVariables() {
        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        if (containerView instanceof DSemanticDecorator) {
            variables.put(tool.getElement(), ((DSemanticDecorator) containerView).getTarget());
        }
        variables.put(tool.getElementView(), containerView);
        return variables;
    }

    private boolean checkGenericToolPrecondition(IInterpreter interpreter) {
        boolean checked = false;

        // init variables for precondition
        final InitInterpreterVariablesTask initInterpreterVariablesTask = buildInitVariablesTasks(interpreter);
        initInterpreterVariablesTask.execute();

        if (containerView instanceof DDiagramElement) {
            checked = checkPrecondition((DDiagramElement) containerView, tool);
        } else if (containerView instanceof DDiagram) {
            checked = checkPrecondition((DDiagram) containerView, tool);
        }
        return checked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEnclosingCommandLabel() {
        return new IdentifiedElementQuery(tool).getLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<DDiagram> getDDiagram() {
        return new EObjectQuery(containerView).getParentDiagram();
    }
}
