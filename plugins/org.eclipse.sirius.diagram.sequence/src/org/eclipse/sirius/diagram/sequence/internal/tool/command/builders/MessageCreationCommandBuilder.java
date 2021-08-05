/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.helper.task.TaskHelper;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.tools.internal.command.builders.EdgeCreationCommandBuilder;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.EMFCommandFactoryUI;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;

/**
 * Specific EdgeCreationCommandBuilder for Message.
 * 
 * @author edugueperoux
 */
public class MessageCreationCommandBuilder extends EdgeCreationCommandBuilder {

    /**
     * the eventEnd starting.
     */
    protected EventEnd startingEndPredecessor;

    /**
     * the eventEnd finishing.
     */
    protected EventEnd finishingEndPredecessor;

    /**
     * the current diagram..
     */
    protected DDiagram diagram;

    /**
     * Constructor to renseign default elts needed by NodeCreationCommandBuilder and value needed by StateCreationTool.
     * 
     * @param tool
     *            the StateCreationTool tool
     * @param source
     *            the EdgeTarget source from which the created element should be displayed
     * @param target
     *            the EdgeTarget target from which the created element should be displayed
     * @param startingEndPredecessor
     *            the eventEnd starting
     * @param finishingEndPredecessor
     *            the eventEnd finishing
     */
    public MessageCreationCommandBuilder(MessageCreationTool tool, EdgeTarget source, EdgeTarget target, EventEnd startingEndPredecessor, EventEnd finishingEndPredecessor) {
        super(tool, source, target);
        this.startingEndPredecessor = startingEndPredecessor;
        this.finishingEndPredecessor = finishingEndPredecessor;

        if (source instanceof DDiagramElement) {
            diagram = ((DDiagramElement) source).getParentDiagram();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand buildCreateEdgeCommandFromTool(EObject semanticSource, EObject semanticTarget) {
        final ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(startingEndPredecessor);

        final DCommand result = createEnclosingCommand();

        final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
        variables.put(tool.getSourceVariable(), semanticSource);
        variables.put(tool.getSourceViewVariable(), source);
        variables.put(tool.getTargetVariable(), semanticTarget);
        variables.put(tool.getTargetViewVariable(), target);
        if (tool instanceof OrderedElementCreationTool) {
            OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) tool;
            variables.put(orderedElementCreationTool.getStartingEndPredecessor(), startingEndPredecessor);
            variables.put(orderedElementCreationTool.getFinishingEndPredecessor(), finishingEndPredecessor);
        }
        result.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(source), new EMFCommandFactoryUI()));
        TaskHelper taskHelper = new TaskHelper(modelAccessor, new EMFCommandFactoryUI());
        result.getTasks().add(taskHelper.buildTaskFromModelOperation(diagram, source, tool.getInitialOperation().getFirstModelOperations()));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DCommand createEnclosingCommand() {
        return new SequenceCreatedEventsFlaggingSiriusCommand(editingDomain, getEnclosingCommandLabel(), diagram, Message.viewpointElementPredicate());
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<DDiagram> getDDiagram() {
        return Options.newSome(diagram);
    }
}
