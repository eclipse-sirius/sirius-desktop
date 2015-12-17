/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.internal.tool.command.builders;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.tools.internal.command.builders.NodeCreationCommandBuilder;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;

import com.google.common.base.Predicates;

/**
 * Specific NodeCreationCommandBuilder for ObservationPoint.
 * 
 * @author mporhel
 */
public class ObservationPointCreationCommandBuilder extends NodeCreationCommandBuilder {

    /**
     * the eventEnd starting.
     */
    protected EventEnd startingEndPredecessor;

    /**
     * the eventEnd finishing.
     */
    protected EventEnd finishingEndPredecessor;

    /**
     * Constructor to renseign default elts needed by NodeCreationCommandBuilder
     * and value needed by ExecutionCreationTool.
     * 
     * @param tool
     *            the ObservationPoint creation tool
     * @param diagramElement
     *            the clicked diagram element
     * @param startingEndPredecessor
     *            the eventEnd starting
     * @param finishingEndPredecessor
     *            the eventEnd finishing
     */
    public ObservationPointCreationCommandBuilder(ObservationPointCreationTool tool, DDiagramElement diagramElement, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor) {
        super(tool, diagramElement);
        this.startingEndPredecessor = startingEndPredecessor;
        this.finishingEndPredecessor = finishingEndPredecessor;
    }

    /**
     * Constructor to renseign default elts needed by NodeCreationCommandBuilder
     * and value needed by ExecutionCreationTool.
     * 
     * @param tool
     *            the ObservationPoint creation tool
     * @param diagram
     *            the clicked diagram
     * @param startingEndPredecessor
     *            the eventEnd starting
     * @param finishingEndPredecessor
     *            the eventEnd finishing
     */
    public ObservationPointCreationCommandBuilder(ObservationPointCreationTool tool, DDiagram diagram, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor) {
        super(tool, diagram);
        this.startingEndPredecessor = startingEndPredecessor;
        this.finishingEndPredecessor = finishingEndPredecessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand buildCreateNodeCommandFromTool(EObject semanticContainer, EObject container) {
        final DCommand result = createEnclosingCommand();

        if (permissionAuthority.canEditInstance(diagramElement)) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticContainer);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            variables.put(tool.getVariable(), semanticContainer);
            variables.put(tool.getViewVariable(), container);
            // <added for ObservationPointCreationTool>
            if (tool instanceof OrderedElementCreationTool) {
                OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) tool;
                variables.put(orderedElementCreationTool.getStartingEndPredecessor(), startingEndPredecessor);
                variables.put(orderedElementCreationTool.getFinishingEndPredecessor(), finishingEndPredecessor);
            }
            // </added for ObservationPointCreationTool>
            addDiagramVariable(result, container, interpreter);
            if (diagram != null && tool.getInitialOperation().getFirstModelOperations() != null) {
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
        return new SequenceCreatedEventsFlaggingSiriusCommand(editingDomain, getEnclosingCommandLabel(), diagram, Predicates.<DDiagramElement> alwaysFalse());
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * Overridden to have OrderedElementCreationTool.startingEndPredecessor &
     * OrderedElementCreationTool.finishingEndPredecessor variables in tool's
     * precondition expression.
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
