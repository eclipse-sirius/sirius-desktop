/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceDiagramQuery;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Default validator to check if a message creation request is valid.
 * 
 * @author edugueperoux
 */
public class DefaultMessageCreationValidator extends AbstractMessageCreationValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(CreateConnectionRequest request) {
        boolean valid = super.isValid(request);
        if (sequenceElementTarget instanceof Gate targetGate) {
            valid = valid && checkTargetGateNotExplicitlyMovedOutOfParentUpperTime(targetGate);
        } else {
            valid = valid && validateNotCreatingMessageOnState() && validateNotCreatingMessageInInteractionUse();
            valid = valid && checkTargetLifelineNotExplicitlyCreatedAtUpperTime() && checkTargetLifelineNotExplicitlyDestroyedAtLowerTime();
        }
        valid = valid && validateNotCreatingMessageInDifferentOperands();
        return valid;
    }

    private boolean checkTargetGateNotExplicitlyMovedOutOfParentUpperTime(Gate targetGate) {
        boolean valid = true;

        ISequenceElement hierarchicalParent = targetGate.getHierarchicalParent();
        if (hierarchicalParent instanceof ISequenceEvent ise) {
            valid = ise.getVerticalRange().getUpperBound() >= firstClickLocation.y;
        }

        return valid;
    }

    private boolean checkTargetLifelineNotExplicitlyCreatedAtUpperTime() {
        boolean valid = true;

        SequenceDiagram sequenceDiagram = sequenceElementSource.getDiagram();
        SequenceDiagramQuery sequenceDiagramQuery = new SequenceDiagramQuery(sequenceDiagram);
        for (ISequenceEvent sequenceEvent : Iterables.filter(sequenceDiagramQuery.getAllSequenceEventsUpperThan(firstClickLocation.y), Predicates.not(Predicates.instanceOf(Lifeline.class)))) {
            if (isCreateMessageFor(sequenceEvent, sequenceElementTarget.getLifeline().get().getInstanceRole())) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private boolean checkTargetLifelineNotExplicitlyDestroyedAtLowerTime() {
        boolean valid = true;

        SequenceDiagram sequenceDiagram = sequenceElementSource.getDiagram();
        SequenceDiagramQuery sequenceDiagramQuery = new SequenceDiagramQuery(sequenceDiagram);

        for (ISequenceEvent sequenceEvent : Iterables.filter(sequenceDiagramQuery.getAllSequenceEventsLowerThan(firstClickLocation.y), Predicates.not(Predicates.instanceOf(Lifeline.class)))) {
            if (isDestroyMessageFor(sequenceEvent, sequenceElementTarget.getLifeline().get().getInstanceRole())) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    /**
     * Check if the sequenceEvent is a {@link ISequenceEvent} of lifelineTarget.
     * 
     * @param sequenceEvent
     *            the {@link ISequenceEvent} to check
     * 
     * @param lifelineTarget
     *            the probable parent (direct or indirect) of sequenceEvent
     * 
     * @return true if sequenceEvent is a {@link ISequenceEvent} of lifelineTarget
     */
    protected boolean isSequenceEventOfLifeline(ISequenceEvent sequenceEvent, Option<Lifeline> lifelineTarget) {
        Option<Lifeline> lifeline = sequenceEvent.getLifeline();
        if (sequenceEvent instanceof Message) {
            lifeline = ((Message) sequenceEvent).getSourceElement().getLifeline();
        }
        return lifeline.equals(lifelineTarget);
    }

    /**
     * Check if sequenceEvent is a message targeting (directly or indirectly) lifelineTarget.
     * 
     * @param sequenceEvent
     *            the {@link ISequenceEvent} to check
     * @param lifelineTarget
     *            the probable target (direct or indirect) of sequenceEvent
     * 
     * @return true if sequenceEvent is a {@link Message} targeting (directly or indirectly) lifelineTarget
     */
    protected boolean isMessageTargeting(ISequenceEvent sequenceEvent, Option<Lifeline> lifelineTarget) {
        boolean result = false;
        if (sequenceEvent instanceof Message) {
            Message message = (Message) sequenceEvent;
            result = message.getTargetElement().getLifeline().equals(lifelineTarget);
        }
        return result;
    }

    /**
     * Validates that a message is not created between two elements that are not in the same operand.
     * 
     * @return the validation that a message is not created between two elements that are not in the same operand.
     */
    private boolean validateNotCreatingMessageInDifferentOperands() {
        boolean result = true;

        Option<Operand> sourceParentOperand = null;
        Option<Operand> sourceGateSiblingOperand = null;
        if (sequenceElementSource instanceof Lifeline l) {
            sourceParentOperand = l.getParentOperand(secondClickLocation.y);
        } else if (sequenceElementSource instanceof AbstractNodeEvent ane) {
            sourceParentOperand = ane.getParentOperand(secondClickLocation.y);
        } else if (sequenceElementSource instanceof ISequenceEvent ise) {
            sourceParentOperand = ise.getParentOperand();
        } else if (sequenceElementSource instanceof InstanceRole ir) {
            sourceParentOperand = ir.getLifeline().get().getParentOperand(secondClickLocation.y);
        } else if (sequenceElementSource instanceof Gate g) {
            sourceParentOperand = g.getParentOperand();
            sourceGateSiblingOperand = g.getSiblingOperand();
        }

        Option<Operand> targetParentOperand = null;
        Option<Operand> targetGateSiblingOperand = null;
        if (sequenceElementTarget instanceof Lifeline l) {
            targetParentOperand = l.getParentOperand(secondClickLocation.y);
        } else if (sequenceElementTarget instanceof AbstractNodeEvent ane) {
            targetParentOperand = ane.getParentOperand(secondClickLocation.y);
        } else if (sequenceElementTarget instanceof ISequenceEvent ise) {
            targetParentOperand = ise.getParentOperand();
        } else if (sequenceElementTarget instanceof InstanceRole ir) {
            targetParentOperand = ir.getLifeline().get().getParentOperand(secondClickLocation.y);
        } else if (sequenceElementTarget instanceof Gate g) {
            targetParentOperand = g.getParentOperand();
            targetGateSiblingOperand = g.getSiblingOperand();
        }

        if (targetParentOperand != null && sourceParentOperand != null) {
            result = targetParentOperand.equals(sourceParentOperand);
            if (sourceGateSiblingOperand != null && sourceGateSiblingOperand.some() && targetParentOperand.some()) {
                result = result || sourceGateSiblingOperand.get().getCombinedFragment().equals(targetParentOperand.get().getCombinedFragment());
            }
            if (targetGateSiblingOperand != null && targetGateSiblingOperand.some() && sourceParentOperand.some()) {
                result = result || targetGateSiblingOperand.get().getCombinedFragment().equals(sourceParentOperand.get().getCombinedFragment());
            }
        }
        return result;
    }

    /**
     * Validates that a message is not created inside an interaction use.
     * 
     * @return the validation that a message is not created inside an interaction use.
     */
    private boolean validateNotCreatingMessageOnState() {
        boolean result = true;
        Option<Lifeline> lifelineOption = sequenceElementTarget.getLifeline();

        if (sequenceElementTarget instanceof Gate) {
            result = true;
        } else if (!lifelineOption.some()) {
            result = false;
        } else {
            Lifeline lifeline = lifelineOption.get();
            for (State state : lifeline.getDiagram().getAllStates()) {
                if (state.getLifeline().get().equals(lifeline) && state.getVerticalRange().includes(firstClickLocation.y)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Validates that a message is not created inside an interaction use.
     * 
     * @return the validation that a message is not created inside an interaction use.
     */
    private boolean validateNotCreatingMessageInInteractionUse() {
        Option<Lifeline> lifeline = sequenceElementTarget.getLifeline();

        Predicate<InteractionUse> interactionUseOnRealTargetLocation = new Predicate<InteractionUse>() {
            // Filters interaction use at the vertical position of the
            // source but on the targeted lifeline.
            public boolean apply(InteractionUse input) {
                return input.getVerticalRange().includes(firstClickLocation.y);
            }
        };

        return sequenceElementTarget instanceof Gate || (lifeline.some() && !Iterables.any(lifeline.get().getAllCoveringInteractionUses(), interactionUseOnRealTargetLocation));
    }

    /**
     * Check if sequenceEvent is a create message of createdInstanceRole.
     * 
     * @param sequenceEvent
     *            the {@link ISequenceEvent} to check
     * 
     * @param createdInstanceRole
     *            the probably {@link InstanceRole} created by sequenceEvent
     * 
     * @return true if createdInstanceRole is well created by the sequenceEvent
     */
    protected boolean isCreateMessageFor(ISequenceEvent sequenceEvent, InstanceRole createdInstanceRole) {
        boolean result = false;
        if (sequenceEvent instanceof Message) {
            Message createMessage = (Message) sequenceEvent;
            result = createMessage.getKind() == Message.Kind.CREATION && createMessage.getTargetElement().equals(createdInstanceRole);
        }
        return result;
    }

    /**
     * Check if sequenceEvent is a destroy message of destroyedInstanceRole.
     * 
     * @param sequenceEvent
     *            the {@link ISequenceEvent} to check
     * 
     * @param destroyedInstanceRole
     *            the probably {@link InstanceRole} destroyed by sequenceEvent
     * 
     * @return true if destroyedInstanceRole is well destroyed by the sequenceEvent
     */
    protected boolean isDestroyMessageFor(ISequenceEvent sequenceEvent, InstanceRole destroyedInstanceRole) {
        boolean result = false;
        if (sequenceEvent instanceof Message) {
            Message createMessage = (Message) sequenceEvent;
            result = createMessage.getKind() == Message.Kind.DESTRUCTION;
            if (createMessage.getTargetElement() instanceof EndOfLife) {
                EndOfLife endOfLife = (EndOfLife) createMessage.getTargetElement();
                result = endOfLife.getLifeline().get().getInstanceRole().equals(destroyedInstanceRole);
            } else {
                result = false;
            }
        }
        return result;
    }
}
