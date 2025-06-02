/*******************************************************************************
 * Copyright (c) 2025 CEA.
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
package org.eclipse.sirius.diagram.sequence.internal.tool.command.builders;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.description.tool.GateCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.tools.internal.command.builders.NodeCreationCommandBuilder;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;

/**
 * Specific NodeCreationCommandBuilder for Gate.
 * 
 * @author smonnier
 */
public class GateCreationCommandBuilder extends NodeCreationCommandBuilder {

    /**
     * the eventEnd starting.
     */
    protected EventEnd startingEndPredecessor;

    /**
     * the eventEnd finishing.
     */
    protected EventEnd finishingEndPredecessor;

    private Point location;

    /**
     * Constructor to renseign default elts needed by NodeCreationCommandBuilder and value needed by GateCreationTool.
     * 
     * @param tool
     *            the GateCreationTool tool
     * @param node
     *            the diagram element in which the created element should be displayed
     * @param startingEndPredecessor
     *            the eventEnd starting
     * @param finishingEndPredecessor
     *            the eventEnd finishing
     * @param location
     *            the clicked location.
     */
    public GateCreationCommandBuilder(GateCreationTool tool, DDiagramElementContainer node, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor, Point location) {
        super(tool, node);
        this.startingEndPredecessor = startingEndPredecessor;
        this.finishingEndPredecessor = finishingEndPredecessor;
        this.location = location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand buildCreateNodeCommandFromTool(EObject semanticContainer, EObject container) {
        final DCommand result = createEnclosingCommand();

        if (location != null && result instanceof SequenceCreatedEventsFlaggingSiriusCommand) {
            ((SequenceCreatedEventsFlaggingSiriusCommand) result).setLostNodesLocation(location);
        }

        if (permissionAuthority.canEditInstance(diagramElement)) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticContainer);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            variables.put(tool.getVariable(), semanticContainer);
            variables.put(tool.getViewVariable(), container);
            // <added for ExecutionCreationTool>
            if (tool instanceof OrderedElementCreationTool) {
                OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) tool;
                variables.put(orderedElementCreationTool.getStartingEndPredecessor(), startingEndPredecessor);
                variables.put(orderedElementCreationTool.getFinishingEndPredecessor(), finishingEndPredecessor);
            }
            // </added for ExecutionCreationTool>
            addDiagramVariable(result, container, interpreter);
            if (tool.getInitialOperation().getFirstModelOperations() != null) {
                result.getTasks().add(taskHelper.buildTaskFromModelOperation(diagram, semanticContainer, tool.getInitialOperation().getFirstModelOperations()));
            }

        } else {
            result.getTasks().add(UnexecutableTask.INSTANCE);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand createEnclosingCommand() {
        return new SequenceCreatedEventsFlaggingSiriusCommand(editingDomain, getEnclosingCommandLabel(), diagram, Gate.viewpointElementPredicate());
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * Overridden to have OrderedElementCreationTool.startingEndPredecessor &
     * OrderedElementCreationTool.finishingEndPredecessor variables in tool's precondition expression.
     */
    @Override
    protected boolean evaluatePrecondition(IInterpreter interpreter, EObject semanticContainer, String precondition) {
        if (tool instanceof OrderedElementCreationTool) {
            OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) tool;
            interpreter.setVariable(orderedElementCreationTool.getStartingEndPredecessor().getName(), startingEndPredecessor);
            interpreter.setVariable(orderedElementCreationTool.getFinishingEndPredecessor().getName(), finishingEndPredecessor);
        }
        boolean result = super.evaluatePrecondition(interpreter, semanticContainer, precondition);
        if (tool instanceof OrderedElementCreationTool) {
            OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) tool;
            interpreter.unSetVariable(orderedElementCreationTool.getStartingEndPredecessor().getName());
            interpreter.unSetVariable(orderedElementCreationTool.getFinishingEndPredecessor().getName());
        }
        return result;
    }

}
