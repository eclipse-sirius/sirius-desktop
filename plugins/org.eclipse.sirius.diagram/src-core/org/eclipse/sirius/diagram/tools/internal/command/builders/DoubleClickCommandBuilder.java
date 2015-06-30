/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.Navigation;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;

/**
 * A builder to execute operations in a Double Click tool.
 * 
 * @author smonnier
 * 
 */
public class DoubleClickCommandBuilder extends AbstractDiagramCommandBuilder {

    private DoubleClickDescription tool;

    private DDiagramElement dDiagramElement;

    /**
     * .
     * 
     * @param tool
     *            : the double click tool
     * @param dDiagramElement
     *            : where we execute the double click action
     */
    public DoubleClickCommandBuilder(DoubleClickDescription tool, DDiagramElement dDiagramElement) {
        super();
        this.tool = tool;
        this.dDiagramElement = dDiagramElement;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    @Override
    public Command buildCommand() {
        if (canDoubleClick()) {
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            variables.put(tool.getElement(), dDiagramElement.getTarget());
            variables.put(tool.getElementView(), dDiagramElement);

            final DCommand cmd = createEnclosingCommand();
            IInterpreter interpreter = InterpreterUtil.getInterpreter(dDiagramElement);
            cmd.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));

            Option<DDiagram> parentDiagram = getDDiagram();
            if (tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                cmd.getTasks().add(taskHelper.buildTaskFromModelOperation(parentDiagram.get(), dDiagramElement, tool.getInitialOperation().getFirstModelOperations()));
                addPostOperationTasks(cmd, interpreter);
            }

            return cmd;
        }
        return UnexecutableCommand.INSTANCE;
    }

    private boolean canDoubleClick() {
        // Layouting mode on diagrams
        // if the dDiagram is in layoutMode
        // We disable this double click, unless the tool is only containing
        // Navigation descriptions
        boolean valid = !(isInLayoutingModeDiagram(dDiagramElement) && !(tool.getInitialOperation().getFirstModelOperations() instanceof Navigation));
        valid = valid && checkPrecondition(dDiagramElement, tool);
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
        if (dDiagramElement != null) {
            addRefreshTask(dDiagramElement, command, tool);
            Option<DDiagram> parentDiagram = new EObjectQuery(dDiagramElement).getParentDiagram();
            command.getTasks().add(new ElementsToSelectTask(tool, interpreter, dDiagramElement.getTarget(), parentDiagram.get()));
        }
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
        return new EObjectQuery(dDiagramElement).getParentDiagram();
    }
}
