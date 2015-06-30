/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.EObjectCollectionWrapper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;

/**
 * Command builder for selection wizard tool.
 * 
 * @author mporhel
 */
public class SelectionWizardCommandBuilder extends AbstractDiagramCommandBuilder {

    /**
     * Current tool description from which this CommandBuilder build a Command.
     */
    protected final SelectionWizardDescription tool;

    /**
     * View on which the current ToolDescription's operations are executed.
     */
    protected DSemanticDecorator containerView;

    private final Collection<EObject> selectedElements;

    /**
     * Create a new selection wizard command builder instance.
     * 
     * @param tool
     *            a selection wizard tool
     * @param containerView
     *            the diagram or diagram element on which the operations should
     *            be applied.
     * @param selectedElements
     *            the selected elements.
     */
    public SelectionWizardCommandBuilder(final SelectionWizardDescription tool, final DSemanticDecorator containerView, Collection<EObject> selectedElements) {
        this.tool = tool;
        this.containerView = containerView;
        this.selectedElements = selectedElements;
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
            Option<DDiagram> representation = getDDiagram();
            if (representation.some() && tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                addPreOperationTasks(result, interpreter);

                result.getTasks().add(taskHelper.buildTaskFromModelOperation(representation.get(), containerView.getTarget(), tool.getInitialOperation().getFirstModelOperations()));

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
        command.getTasks().add(new ElementsToSelectTask(tool, interpreter, containerView.getTarget(), parentDiagram.get()));
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
        variables.put(tool.getContainer(), containerView.getTarget());
        variables.put(tool.getContainerView(), containerView);
        if (!tool.isMultiple()) {
            variables.put(tool.getElement(), selectedElements.iterator().next());
        } else {
            variables.put(tool.getElement(), new EObjectCollectionWrapper(selectedElements));
        }
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
