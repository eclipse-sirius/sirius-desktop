/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
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

import java.util.Collection;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.tools.internal.command.builders.PaneBasedSelectionWizardCommandBuilder;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

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
     *            the eventEnd starting
     * @param location
     *            the location for flag.
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

    @Override
    protected DCommand createEnclosingCommand() {
        Option<DDiagram> parentDiagram = getDDiagram();
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

        SequenceGenericToolCommandBuilder.addSetEndBeforeVariableTask(command, interpreter, endBefore);
    }

    @Override
    protected void addPostOperationTasks(DCommand command, final IInterpreter interpreter) {
        super.addPostOperationTasks(command, interpreter);

       SequenceGenericToolCommandBuilder.addUnsetEndBeforeTask(command, interpreter);
    }
}
