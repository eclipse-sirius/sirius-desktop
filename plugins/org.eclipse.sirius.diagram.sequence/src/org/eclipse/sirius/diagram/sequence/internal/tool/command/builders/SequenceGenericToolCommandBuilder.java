/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.tools.internal.command.builders.GenericToolCommandBuilder;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Specific GenericToolCommandBuilder.
 * 
 * @author mporhel
 */
public class SequenceGenericToolCommandBuilder extends GenericToolCommandBuilder implements SequenceToolCommandBuilder {

    /**
     * the eventEnd before the click.
     */
    protected EventEnd endBefore;

    private Point location;

    private final Diagram gmfDiagram;

    /**
     * Constructor to renseign default elts needed by NodeCreationCommandBuilder
     * and value needed by StateCreationTool.
     * 
     * @param tool
     *            the StateCreationTool tool
     * @param containerView
     *            the diagram element in which the created element should be
     *            displayed
     * @param endBefore
     *            the eventEnd starting
     * @param location
     *            the location for flag.
     * @param diagram
     *            the concerned diagram
     */
    public SequenceGenericToolCommandBuilder(ToolDescription tool, EObject containerView, EventEnd endBefore, Point location, Diagram diagram) {
        super(tool, containerView);
        this.location = location;
        this.gmfDiagram = diagram;
        this.endBefore = endBefore;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * Overridden to have END_BEFORE_VARIABLE in tool precondition expression.
     */
    @Override
    protected boolean evaluatePrecondition(IInterpreter interpreter, EObject semanticContainer, String precondition) {
        interpreter.setVariable(END_BEFORE_VARIABLE, endBefore);

        boolean result = super.evaluatePrecondition(interpreter, semanticContainer, precondition);

        interpreter.unSetVariable(END_BEFORE_VARIABLE);
        return result;
    }

    @Override
    protected DCommand createEnclosingCommand() {
        Option<DDiagram> parentDiagram = new EObjectQuery(containerView).getParentDiagram();
        Predicate<DDiagramElement> viewpointElementPredicate = Predicates.or(Message.viewpointElementPredicate(), Execution.viewpointElementPredicate(), State.viewpointElementPredicate(),
                CombinedFragment.viewpointElementPredicate(), Operand.viewpointElementPredicate(), InteractionUse.viewpointElementPredicate());
        SequenceCreatedEventsFlaggingSiriusCommand cmd = new SequenceCreatedEventsFlaggingSiriusCommand(editingDomain, getEnclosingCommandLabel(), parentDiagram.get(), viewpointElementPredicate);
        if (location != null) {
            cmd.setLostNodesLocation(location);
        }
        return cmd;
    };

    @Override
    protected void addPreOperationTasks(DCommand command, final IInterpreter interpreter) {
        super.addPreOperationTasks(command, interpreter);

        addSetEndBeforeVariableTask(command, interpreter, endBefore);
    }

    @Override
    protected void addPostOperationTasks(DCommand command, final IInterpreter interpreter) {
        super.addPostOperationTasks(command, interpreter);

        addUnsetEndBeforeTask(command, interpreter);

        if (gmfDiagram != null) {
            command.getTasks().add(new AbstractCommandTask() {

                @Override
                public String getLabel() {
                    return Messages.SequenceGenericToolCommandBuilder_canonicalSyncTask;
                }

                @Override
                public void execute() {
                    CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(gmfDiagram);
                    canonicalSynchronizer.synchronize();
                }
            });
        }
    }

    /**
     * Complete the given command with a task to set the endBefore variable.
     * 
     * @param command
     *            the command to complete
     * @param interpreter
     *            the interpreter to updated in the command
     * @param endBefore
     *            the end before variable to set
     */
    public static void addSetEndBeforeVariableTask(DCommand command, final IInterpreter interpreter, final EventEnd endBefore) {
        command.getTasks().add(new AbstractCommandTask() {

            @Override
            public String getLabel() {
                return Messages.SequenceGenericToolCommandBuilder_setEndBeforeTask;
            }

            @Override
            public void execute() {
                interpreter.setVariable(END_BEFORE_VARIABLE, endBefore);
            }
        });
    }

    /**
     * Complete the given command with a task to unset the endBefore variable.
     *
     * @param command
     *            the command to complete
     * @param interpreter
     *            the interpreter to updated in the command
     */
    public static void addUnsetEndBeforeTask(DCommand command, final IInterpreter interpreter) {
        command.getTasks().add(new AbstractCommandTask() {

            @Override
            public String getLabel() {
                return Messages.SequenceGenericToolCommandBuilder_unsetEndBeforeTask;
            }

            @Override
            public void execute() {
                interpreter.unSetVariable(END_BEFORE_VARIABLE);
            }
        });
    }
}
