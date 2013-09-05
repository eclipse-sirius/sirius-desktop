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

import java.util.Collection;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.internal.command.builders.PaneBasedSelectionWizardCommandBuilder;

/**
 * Specific GenericToolCommandBuilder.
 * 
 * @author mporhel
 */
public class SequencePaneBasedSelectionWizardCommandBuilder extends PaneBasedSelectionWizardCommandBuilder implements SequenceToolCommandBuilder {

    /**
     * the eventEnd before the click.
     */
    protected EventEnd endBefore;

    private Point location;

    /**
     * Constructor to renseign default elts needed by
     * PaneBasedSelectionWizardDescription on a sequence diagram.
     * 
     * @param tool
     *            the PaneBasedSelectionWizardDescription tool
     * @param containerView
     *            the diagram element in which the created element should be
     *            displayed
     * @param selectedElements
     *            the selected elements.
     * @param endBefore
     *            the eventEnd starting * @param location the location for flag.
     */
    public SequencePaneBasedSelectionWizardCommandBuilder(final PaneBasedSelectionWizardDescription tool, final DSemanticDecorator containerView, Collection<EObject> selectedElements,
            EventEnd endBefore, Point location) {
        super(tool, containerView, selectedElements);
        this.endBefore = endBefore;
        this.location = location;
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addPreOperationTasks(DCommand command, final IInterpreter interpreter) {
        super.addPreOperationTasks(command, interpreter);

        command.getTasks().add(new AbstractCommandTask() {

            public String getLabel() {
                return "Add end before variable";
            }

            public void execute() {
                interpreter.setVariable(END_BEFORE_VARIABLE, endBefore);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addPostOperationTasks(DCommand command, final IInterpreter interpreter) {
        super.addPostOperationTasks(command, interpreter);

        command.getTasks().add(new AbstractCommandTask() {

            public String getLabel() {
                return "Unset end before variable";
            }

            public void execute() {
                interpreter.unSetVariable(END_BEFORE_VARIABLE);
            }
        });
    }
}
