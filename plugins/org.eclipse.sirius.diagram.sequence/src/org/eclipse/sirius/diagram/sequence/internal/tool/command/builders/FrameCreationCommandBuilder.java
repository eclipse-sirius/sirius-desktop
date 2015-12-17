/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.UnexecutableTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.tools.internal.command.builders.ContainerCreationCommandBuilder;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Specific ContainerCreationCommandBuilder for InteractionUse &
 * CombinedFragment.
 * 
 * @author edugueperoux
 */
public class FrameCreationCommandBuilder extends ContainerCreationCommandBuilder {

    /**
     * the eventEnd starting.
     */
    protected EventEnd startingEndPredecessor;

    /**
     * the eventEnd finishing.
     */
    protected EventEnd finishingEndPredecessor;

    /**
     * the coverage.
     */
    protected List<EObject> coverage;

    /**
     * Constructor to renseign default elts needed by NodeCreationCommandBuilder
     * and value needed by StateCreationTool.
     * 
     * @param diagram
     *            the diagram in which the created element should be displayed
     * @param tool
     *            the ContainerCreationDescription tool
     * @param startingEndPredecessor
     *            the eventEnd starting
     * @param finishingEndPredecessor
     *            the eventEnd finishing
     * @param coverage
     *            the coverage
     */
    public FrameCreationCommandBuilder(ContainerCreationDescription tool, DDiagram diagram, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor, List<EObject> coverage) {
        super(tool, diagram);
        this.startingEndPredecessor = startingEndPredecessor;
        this.finishingEndPredecessor = finishingEndPredecessor;
        this.coverage = coverage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand buildCreateNodeCommandFromTool(EObject semanticContainer, EObject container) {
        final DCommand result = createEnclosingCommand();
        if (permissionAuthority.canEditInstance(container)) {
            final IInterpreter interpreter = InterpreterUtil.getInterpreter(semanticContainer);
            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            result.getTasks().add(new InitInterpreterVariablesTask(variables, interpreter, uiCallback));
            variables.put(tool.getVariable(), semanticContainer);
            variables.put(tool.getViewVariable(), container);

            if (tool instanceof OrderedElementCreationTool) {
                OrderedElementCreationTool oect = (OrderedElementCreationTool) tool;
                variables.put(oect.getStartingEndPredecessor(), startingEndPredecessor);
                variables.put(oect.getFinishingEndPredecessor(), finishingEndPredecessor);
            }
            if (tool instanceof CoveringElementCreationTool) {
                CoveringElementCreationTool cect = (CoveringElementCreationTool) tool;
                variables.put(cect.getCoveredLifelines(), coverage);
            }

            addDiagramVariable(result, container, interpreter);
            result.getTasks().add(taskHelper.buildTaskFromModelOperation(diagram, semanticContainer, tool.getInitialOperation().getFirstModelOperations()));
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
        Predicate<DDiagramElement> shouldFlag = Predicates.or(CombinedFragment.viewpointElementPredicate(), InteractionUse.viewpointElementPredicate());
        return new SequenceCreatedEventsFlaggingSiriusCommand(editingDomain, getEnclosingCommandLabel(), diagram, shouldFlag);
    }

    /**
     * {@inheritDoc}
     * 
     * Overridden to have OrderedElementCreationTool.startingEndPredecessor &
     * OrderedElementCreationTool.finishingEndPredecessor &
     * CoveringElementCreationTool.coveredLifelines variables in tool's
     * precondition expression.
     */
    @Override
    protected boolean evaluatePrecondition(IInterpreter interpreter, EObject semanticContainer, String precondition) {
        if (tool instanceof OrderedElementCreationTool) {
            OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) tool;
            interpreter.setVariable(orderedElementCreationTool.getStartingEndPredecessor().getName(), startingEndPredecessor);
            interpreter.setVariable(orderedElementCreationTool.getFinishingEndPredecessor().getName(), finishingEndPredecessor);
        }
        if (tool instanceof CoveringElementCreationTool) {
            CoveringElementCreationTool coveringElementCreationTool = (CoveringElementCreationTool) tool;
            interpreter.setVariable(coveringElementCreationTool.getCoveredLifelines().getName(), coverage);
        }
        boolean result = super.evaluatePrecondition(interpreter, semanticContainer, precondition);
        if (tool instanceof OrderedElementCreationTool) {
            OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) tool;
            interpreter.unSetVariable(orderedElementCreationTool.getStartingEndPredecessor().getName());
            interpreter.unSetVariable(orderedElementCreationTool.getFinishingEndPredecessor().getName());
        }
        if (tool instanceof CoveringElementCreationTool) {
            CoveringElementCreationTool coveringElementCreationTool = (CoveringElementCreationTool) tool;
            interpreter.unSetVariable(coveringElementCreationTool.getCoveredLifelines().getName());
        }
        return result;
    }

}
