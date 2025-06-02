/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.sample.interactions.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.sample.interactions.AbstractEnd;
import org.eclipse.sirius.sample.interactions.CallMessage;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.CombinedFragmentEnd;
import org.eclipse.sirius.sample.interactions.DestroyParticipantMessage;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.ExecutionEnd;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.InteractionUseEnd;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Message;
import org.eclipse.sirius.sample.interactions.MessageEnd;
import org.eclipse.sirius.sample.interactions.MixEnd;
import org.eclipse.sirius.sample.interactions.Operand;
import org.eclipse.sirius.sample.interactions.OperandEnd;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.sample.interactions.ReturnMessage;
import org.eclipse.sirius.sample.interactions.State;
import org.eclipse.sirius.sample.interactions.StateEnd;

/**
 * Java services for the sample 'Interactions' sequence diagrams.
 * 
 * @author pcdavid
 */
public class InteractionOrderingServices {

    /**
     * Helper class to keep track of who "contains" who depending on the interleaving of the start/finish ends.
     * 
     * @author pcdavid
     * @see InteractionOrderingServices#computeContainmentStructure(Participant)
     */
    private static final class EventContext {
        private final EObject parent;

        private final boolean start;

        private final EObject element;

        private final int level;

        public EventContext(EObject parent, EObject element, boolean start, int level) {
            this.parent = parent;
            this.element = element;
            this.level = level;
            this.start = start;
        }

        public boolean isStart() {
            return start;
        }

        public EObject getParent() {
            return parent;
        }

        public EObject getElement() {
            return element;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return String.format("%02d\t%s\t%s", getLevel(), element, parent);
        }
    }

    public Collection<EObject> computeSemanticElements(Execution exec) {
        return new LinkedHashSet<EObject>(Arrays.asList(exec, exec.getStart(), exec.getEnd(), currentParticipant(exec)));
    }

    public Collection<EObject> computeSemanticElements(State state) {
        return new LinkedHashSet<EObject>(Arrays.asList(state, state.getStart(), state.getEnd(), currentParticipant(state)));
    }

    public boolean eolPrecondition(Participant p) {
        Interaction i = (Interaction) new EObjectQuery(p).getFirstAncestorOfType(InteractionsPackage.Literals.INTERACTION).get();
        // @formatter:off
        return i.getMessages().stream()
                .filter(DestroyParticipantMessage.class::isInstance)
                .anyMatch(msg -> msg.getReceivingEnd() != null && msg.getReceivingEnd().getContext() == p);
        // @formatter::on
    }

    public boolean redimEolPrecondition(Participant p) {
        return !eolPrecondition(p);
    }

    public Collection<EObject> lostMessageEndSemanticCandidates(Interaction i) {
        Collection<EObject> result = new ArrayList<>();
        for (Message msg : i.getMessages()) {
            if ((msg.getSendingEnd() == null && msg.getReceivingEnd() != null) || (msg.getSendingEnd() != null && msg.getReceivingEnd() == null)) {
                result.add(msg);
            }
        }
        return result;
    }

    public EObject startingEnd(EObject self) {
        if (self instanceof Message) {
            return ((Message) self).getSendingEnd();
        } else if (self instanceof Execution) {
            return ((Execution) self).getStart();
        } else if (self instanceof State) {
            return ((State) self).getStart();
        } else if (self instanceof InteractionUse) {
            return ((InteractionUse) self).getStart();
        } else if (self instanceof CombinedFragment) {
            return ((CombinedFragment) self).getStart();
        } else if (self instanceof Operand) {
            return ((Operand) self).getStart();
        } else {
            return null;
        }
    }

    public EObject finishingEnd(EObject self) {
        if (self instanceof Message) {
            return ((Message) self).getReceivingEnd();
        } else if (self instanceof Execution) {
            return ((Execution) self).getEnd();
        } else if (self instanceof State) {
            return ((State) self).getEnd();
        } else if (self instanceof InteractionUse) {
            return ((InteractionUse) self).getFinish();
        } else if (self instanceof CombinedFragment) {
            return ((CombinedFragment) self).getFinish();
        } else {
            return null;
        }
    }

    public EObject ownerEvent(EObject self) {
        if (self instanceof AbstractEnd) {
            return null;
        } else if (self instanceof MixEnd) {
            return ((MixEnd) self).getExecution();
        } else if (self instanceof MessageEnd) {
            return ((MessageEnd) self).getMessage();
        } else if (self instanceof ExecutionEnd) {
            return ((ExecutionEnd) self).getExecution();
        } else if (self instanceof StateEnd) {
            return ((StateEnd) self).getState();
        } else {
            return null;
        }
    }

    public Participant currentParticipant(EObject self) {
        if (self instanceof Participant) {
            return (Participant) self;
        } else if (self instanceof AbstractEnd) {
            return (Participant) ((AbstractEnd) self).getContext();
        } else if (self instanceof Execution) {
            return ((Execution) self).getOwner();
        } else if (self instanceof State) {
            return ((State) self).getOwner();
        } else {
            return null;
        }
    }

    public Collection<EObject> getDirectEventsOnCurrentParticipant(EObject self) {
        return getDirectEventsOn(currentParticipant(self), self);
    }

    /**
     * Computes the semantic elements corresponding to the events directly below the specified parent on a given
     * lifelines. This is necessary because the VSM expects a tree-like structure for mappings, but in an 'Interactions'
     * model, the events corresponding to the start/finish of execution/messages/etc. are stored in a linear structure.
     * 
     * @param context
     *            the participant/lifeline on which to look.
     * @param parent
     *            the semantic element of the parent event.
     * @return the semantic elements of all the direct sub-events of <code>parent</code> on the given participant. The
     *         order is not specified.
     */
    public Collection<EObject> getDirectEventsOn(Participant context, EObject parent) {
        List<EventContext> structure = computeContainmentStructure(context);
        LinkedHashSet<EObject> events = new LinkedHashSet<EObject>();
        for (EventContext ec : structure) {
            if (ec.getParent().equals(parent)) {
                events.add(ec.getElement());
            }
        }
        Set<EObject> result = new LinkedHashSet<EObject>();
        for (EObject event : events) {
            if (event != parent) {
                result.add(event);
            }
        }
        return result;
    }

    /**
     * Returns the event end which represents the finishing of an operand. An operand only has a starting end in the
     * model. Its finishing end must be inferred from the context. If the operand is the last operand in the Combined
     * Fragment, it finishes with the end of the CF. Otherwise it finished when the next operand starts.
     * 
     * @param operand
     *            the operand.
     * @return the event end which represents the finishing of the operand.
     */
    public AbstractEnd getFinishingEnd(Operand operand) {
        AbstractEnd result = null;
        EObject eContainer = operand.eContainer();

        if (eContainer instanceof CombinedFragment) {
            CombinedFragment cf = (CombinedFragment) eContainer;
            result = cf.getFinish();

            Operand prev = null;
            for (Operand op : cf.getOwnedOperands()) {
                if (operand.equals(prev)) {
                    result = op.getStart();
                    break;
                } else {
                    prev = op;
                }
            }
        }

        return result;
    }

    /**
     * Returns the semantic element corresponding to the source of a message. This can be a participant or an execution.
     * 
     * @param msg
     *            the message.
     * @return the semantic elements corresponding to the source of the message.
     */
    public EObject getSendingContext(Message msg) {
        MessageEnd sendingEnd = msg.getSendingEnd();
        if (sendingEnd != null) {
            Participant p = sendingEnd.getContext();
            if (p != null) {
                List<EventContext> structure = computeContainmentStructure(p);
                for (EventContext ec : structure) {
                    if (ec.getElement().equals(msg) && ec.isStart()) {
                        EObject parent = ec.getParent();
                        if (parent != null) {
                            return parent;
                        } else {
                            return p;
                        }
                    }
                }
            } else if (sendingEnd.getGate() != null) {
                return sendingEnd.getGate();
            }
        }
        return msg;
    }

    /**
     * Returns the semantic element corresponding to the target of a message. This can be a participant, execution or an
     * instance role.
     * 
     * @param msg
     *            the message.
     * @return the semantic elements corresponding to the target of the message.
     */
    public EObject getReceivingContext(Message msg) {
        MessageEnd receivingEnd = msg.getReceivingEnd();
        if (receivingEnd != null) {
            Participant p = receivingEnd.getContext();
            if (p != null) {
                List<EventContext> structure = computeContainmentStructure(p);
                for (EventContext ec : structure) {
                    if (ec.getElement().equals(msg) && !ec.isStart()) {
                        EObject parent = ec.getParent();
                        if (parent != null) {
                            return parent;
                        } else {
                            return p;
                        }
                    }
                }
            } else if (receivingEnd.getGate() != null) {
                return receivingEnd.getGate();
            }
        }
        return msg;
    }

    /**
     * Get the semantic elements associated to the specified message.
     * 
     * @param msg
     *            the specified message
     * @return the semantic elements associated to the specified message
     */
    public Collection<EObject> getMessageAssociatedElements(Message msg) {
        Collection<EObject> messageAssociatedElements = new LinkedHashSet<EObject>();
        messageAssociatedElements.add(msg);
        messageAssociatedElements.add(msg.getSendingEnd());
        messageAssociatedElements.add(msg.getReceivingEnd());
        messageAssociatedElements.add(getSendingContext(msg));
        messageAssociatedElements.add(getReceivingContext(msg));
        return messageAssociatedElements;
    }

    /**
     * Delete a combined fragments, including all the events it contains.
     * 
     * @param obj
     *            the combined fragment to delete.
     * @return the parent of the fragment deleted.
     */
    public EObject deleteCombinedFragment(CombinedFragment cf) {
        EObject result = cf.eContainer();
        delete((Interaction) cf.eContainer(), cf.getStart(), cf.getFinish(), true, cf.getCoveredParticipants());
        return result;
    }

    /**
     * Delete an operand, including all the events it contains. Does nothing if the operand is the only one in the
     * fragment, as this would produce an invalid interaction.
     * 
     * @param oper
     *            the operand to delete.
     * @return the parent of the operand deleted.
     */
    public EObject deleteOperand(Operand oper) {
        EObject result = oper.eContainer();
        if (result instanceof CombinedFragment) {
            CombinedFragment cf = (CombinedFragment) result;

            OperandEnd startingEnd = oper.getStart();
            AbstractEnd finishingEnd = null;
            EList<Operand> siblings = cf.getOwnedOperands();
            if (siblings.size() == 1) {
                // Do nothing it asked to delete the only operand in a CF.
                return result;
            }
            int index = siblings.indexOf(oper);
            assert index != -1 : "inconsistent model";
            if (index == siblings.size() - 1) {
                // The last operand ends with the CF
                finishingEnd = cf.getFinish();
            } else {
                // Other operands ends when their successor starts
                finishingEnd = siblings.get(index + 1).getStart();
            }
            delete((Interaction) cf.eContainer(), startingEnd, finishingEnd, false, cf.getCoveredParticipants());
        }
        return result;
    }

    /**
     * Delete all the events of an interaction inside a range of event ends.
     * 
     * @param inter
     *            the interaction from which to delete elements.
     * @param startingEnd
     *            the starting of the range of events to delete.
     * @param finishingEnd
     *            the finishing of the range of events to delete.
     * @param deleteFinishingEnd
     *            whether to delete the finishing end (and the corresponding event) or to stop just before.
     * @param coverage
     *            the participants from which to delete events. Events which happen in the specified range but strictly
     *            on participants outside this list will not be deleted.
     */
    public void delete(Interaction inter, AbstractEnd startingEnd, AbstractEnd finishingEnd, boolean deleteFinishingEnd, EList<Participant> coverage) {
        Set<EObject> toDelete = new HashSet<EObject>();

        boolean inside = false;
        for (AbstractEnd end : inter.getEnds()) {
            EObject ownerEvent = getOwnerEvent(end);
            if (end == startingEnd) {
                toDelete.add(end);
                toDelete.add(ownerEvent);
                inside = true;
            } else if (end == finishingEnd) {
                if (deleteFinishingEnd) {
                    toDelete.add(end);
                    toDelete.add(ownerEvent);
                }
                break;
            } else if (inside && covers(end, coverage)) {
                toDelete.add(end);
                toDelete.add(ownerEvent);
            }
        }

        for (EObject obj : toDelete) {
            EcoreUtil.delete(obj);
        }
    }

    public EObject getOwnerEvent(AbstractEnd end) {
        if (end instanceof ExecutionEnd) {
            return ((ExecutionEnd) end).getExecution();
        } else if (end instanceof StateEnd) {
            return ((StateEnd) end).getState();
        } else if (end instanceof MessageEnd) {
            return ((MessageEnd) end).getMessage();
        } else if (end instanceof CombinedFragmentEnd) {
            return ((CombinedFragmentEnd) end).getOwner();
        } else if (end instanceof InteractionUseEnd) {
            return ((InteractionUseEnd) end).getOwner();
        } else if (end instanceof OperandEnd) {
            return ((OperandEnd) end).getOwner();
        } else {
            assert false : "unhandled kind of AbstractEnd";
            return null;
        }
    }

    public boolean covers(AbstractEnd end, Collection<Participant> participants) {
        if (end instanceof ExecutionEnd) {
            return participants.contains(((ExecutionEnd) end).getContext());
        } else if (end instanceof MessageEnd) {
            return participants.contains(((MessageEnd) end).getContext());
        } else if (end instanceof CombinedFragmentEnd) {
            Set<Participant> covered = new HashSet<Participant>(((CombinedFragmentEnd) end).getOwner().getCoveredParticipants());
            covered.retainAll(participants);
            return !covered.isEmpty();
        } else if (end instanceof InteractionUseEnd) {
            Set<Participant> covered = new HashSet<Participant>(((InteractionUseEnd) end).getOwner().getCoveredParticipants());
            covered.retainAll(participants);
            return !covered.isEmpty();
        } else if (end instanceof OperandEnd) {
            Set<Participant> covered = new HashSet<Participant>(((CombinedFragment) ((OperandEnd) end).getOwner().eContainer()).getCoveredParticipants());
            covered.retainAll(participants);
            return !covered.isEmpty();
        } else {
            assert false : "unhandled kind of AbstractEnd";
            return false;
        }
    }

    public List<EventContext> computeContainmentStructure(Participant owner) {
        if (owner == null || !(owner.eContainer() instanceof Interaction)) {
            return Collections.emptyList();
        } else {
            Interaction interaction = (Interaction) owner.eContainer();
            Stack<EObject> ancestors = new Stack<EObject>();
            ancestors.push(owner);
            List<EventContext> result = new ArrayList<EventContext>();
            for (AbstractEnd end : interaction.getEnds()) {
                if (end.getContext() != owner) {
                    continue;
                }

                if (isStartingExecutionEnd(end)) {
                    ExecutionEnd execEnd = (ExecutionEnd) end;
                    result.add(new EventContext(ancestors.peek(), execEnd.getExecution(), true, ancestors.size() + 1));
                    ancestors.push(execEnd.getExecution());
                }
                if (isStartingStateEnd(end)) {
                    StateEnd execEnd = (StateEnd) end;
                    result.add(new EventContext(ancestors.peek(), execEnd.getState(), true, ancestors.size() + 1));
                    ancestors.push(execEnd.getState());
                }

                if (end instanceof MessageEnd) {
                    MessageEnd msgEnd = (MessageEnd) end;
                    Message msg = msgEnd.getMessage();
                    if (msg != null) {
                        result.add(new EventContext(ancestors.peek(), msgEnd.getMessage(), msgEnd.equals(msg.getSendingEnd()), ancestors.size()));
                    }
                }

                if (isFinishingExecutionEnd(end)) {
                    ExecutionEnd execEnd = (ExecutionEnd) end;
                    ancestors.pop();
                    result.add(new EventContext(ancestors.peek(), execEnd.getExecution(), false, ancestors.size() + 1));
                }
                if (isFinishingStateEnd(end)) {
                    StateEnd execEnd = (StateEnd) end;
                    ancestors.pop();
                    result.add(new EventContext(ancestors.peek(), execEnd.getState(), false, ancestors.size() + 1));
                }
            }
            return result;
        }
    }

    public boolean isStartingExecutionEnd(AbstractEnd end) {
        if (end instanceof ExecutionEnd) {
            ExecutionEnd ee = (ExecutionEnd) end;
            return ee.getExecution() != null && ee.getExecution().getStart() == end;
        } else {
            return false;
        }
    }

    public boolean isFinishingExecutionEnd(AbstractEnd end) {
        if (end instanceof ExecutionEnd) {
            ExecutionEnd ee = (ExecutionEnd) end;
            return ee.getExecution() != null && ee.getExecution().getEnd() == end;
        } else {
            return false;
        }
    }

    public boolean isStartingStateEnd(AbstractEnd end) {
        if (end instanceof StateEnd) {
            StateEnd ee = (StateEnd) end;
            return ee.getState() != null && ee.getState().getStart() == end;
        } else {
            return false;
        }
    }

    public boolean isFinishingStateEnd(AbstractEnd end) {
        if (end instanceof StateEnd) {
            StateEnd ee = (StateEnd) end;
            return ee.getState() != null && ee.getState().getEnd() == end;
        } else {
            return false;
        }
    }

    /**
     * Compute the depth of a combined fragment. If the EObject if not a combined fragment, this method return 0.
     * 
     * @param eobject
     *            the EObject to find the depth if it is a combined fragment
     * @return the depth if it is a combined fragment.
     */
    public int computeCombinedFragmentDepth(EObject eobject) {
        int combinedFragmentDepth = 0;
        if (eobject instanceof CombinedFragment) {
            CombinedFragment currentCombinedFragment = (CombinedFragment) eobject;
            EList<Participant> coveredParticipants = currentCombinedFragment.getCoveredParticipants();
            CombinedFragmentEnd start = currentCombinedFragment.getStart();
            EObject eContainer = start.eContainer();
            EList<EObject> eContents = eContainer.eContents();
            int startIndex = eContents.lastIndexOf(start);

            List<EObject> contents = eContents.subList(0, startIndex);
            for (EObject obj : contents) {
                if (obj instanceof CombinedFragmentEnd) {
                    CombinedFragmentEnd combinedFragmentEnd = (CombinedFragmentEnd) obj;
                    CombinedFragment combinedFragment = combinedFragmentEnd.getOwner();
                    if (covers(combinedFragmentEnd, coveredParticipants)) {
                        if (combinedFragment.getStart().equals(combinedFragmentEnd)) {
                            combinedFragmentDepth++;
                        } else if (combinedFragment.getFinish().equals(combinedFragmentEnd)) {
                            combinedFragmentDepth--;
                        }
                    }
                }
            }
        }

        return getModuloDepth(combinedFragmentDepth, 5);
    }

    private int getModuloDepth(int absoluteDepth, int nbOfColors) {
        // We defined 5 colors in the vsm
        // Avoid to have two consecutive level of frame with same color :
        int mod = absoluteDepth % nbOfColors;
        int qot = absoluteDepth / nbOfColors;
        if (qot % 2 != 0) {
            mod = nbOfColors - mod;
        }
        return mod;
    }

    /**
     * Compute the depth of an Execution. If the EObject if not an Execution, this method return 0.
     * 
     * @param eobject
     *            the EObject to find the depth if it is an Execution
     * @return the depth if it is an Execution.
     */
    public int computeExecutionDepth(EObject eobject) {
        int executionDepth = 0;
        if (eobject instanceof Execution) {
            Execution currentExecution = (Execution) eobject;
            Participant currentLifeline = currentExecution.getOwner();
            ExecutionEnd start = currentExecution.getStart();
            EObject eContainer = start.eContainer();
            EList<EObject> eContents = eContainer.eContents();
            int startIndex = eContents.lastIndexOf(start);

            List<EObject> contents = eContents.subList(0, startIndex);
            for (EObject obj : contents) {
                if (obj instanceof ExecutionEnd) {
                    ExecutionEnd executionEnd = (ExecutionEnd) obj;
                    Execution execution = executionEnd.getExecution();
                    if (currentLifeline != null && execution != null && currentLifeline.equals(execution.getOwner())) {
                        if (execution.getStart().equals(executionEnd)) {
                            executionDepth++;
                        } else if (execution.getEnd().equals(executionEnd)) {
                            executionDepth--;
                        }
                    }
                }
            }
        }
        return getModuloDepth(executionDepth, 10);
    }

    /**
     * Check if the current participant can be created, regarding the end before click.
     * 
     * @param eobject
     *            the EObject to find the depth if it is an Execution
     * @return the depth if it is an Execution.
     */
    public boolean canCreate(Participant participant, EObject endBefore) {
        boolean result = false;
        if (participant != null && participant.eContainer() instanceof Interaction) {
            result = true;
            AbstractEnd semanticEndBefore = getSemanticEnd(endBefore);
            if (semanticEndBefore != null) {
                for (AbstractEnd end : ((Interaction) participant.eContainer()).getEnds()) {
                    result = participant != end.getContext();
                    if (!result || end == semanticEndBefore) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Check if the current participant can be destroyed, regarding the end before click.
     * 
     * @param eobject
     *            the EObject to find the depth if it is an Execution
     * @return the depth if it is an Execution.
     */
    public boolean canDestroy(Participant participant, EObject endBefore) {
        boolean result = false;
        if (participant != null && participant.eContainer() instanceof Interaction) {
            result = true;
            AbstractEnd semanticEndBefore = getSemanticEnd(endBefore);
            List<AbstractEnd> ends = new ArrayList<AbstractEnd>(((Interaction) participant.eContainer()).getEnds());
            Collections.reverse(ends);
            for (AbstractEnd end : ends) {
                if (end != semanticEndBefore) {
                    result = participant != end.getContext();
                }

                if (!result || end == semanticEndBefore) {
                    break;
                }
            }
        }
        return result;
    }

    private AbstractEnd getSemanticEnd(EObject endBefore) {
        if (endBefore instanceof EventEnd && ((EventEnd) endBefore).getSemanticEnd() instanceof AbstractEnd) {
            return (AbstractEnd) ((EventEnd) endBefore).getSemanticEnd();
        }
        return null;
    }

    /**
     * Check if the current potential source
     * 
     * @param eobject
     *            the EObject to find the depth if it is an Execution
     * @return true if valid.
     */
    public boolean isValidSourceForConstraintCreation(EObject source) {
        boolean isValid = true;
        if (source instanceof CallMessage) {
            CallMessage msg = (CallMessage) source;
            isValid = msg.getReceivingEnd() instanceof MixEnd && msg.getSendingEnd().getContext() != msg.getReceivingEnd().getContext();
        } else if (source instanceof ReturnMessage) {
            ReturnMessage msg = (ReturnMessage) source;
            isValid = msg.getSendingEnd() instanceof MixEnd && msg.getSendingEnd().getContext() != msg.getReceivingEnd().getContext();
        }
        return isValid;
    }

    /**
     * Check if the current participant can be destroyed, regarding the end before click.
     * 
     * @param eobject
     *            the EObject to find the depth if it is an Execution
     * @return true if valid.
     */
    public boolean isValidTargetForConstraintCreation(EObject target) {
        boolean isValid = true;
        if (target instanceof CallMessage) {
            CallMessage msg = (CallMessage) target;
            isValid = msg.getReceivingEnd() instanceof MixEnd && msg.getSendingEnd().getContext() != msg.getReceivingEnd().getContext();
        } else if (target instanceof ReturnMessage) {
            ReturnMessage msg = (ReturnMessage) target;
            isValid = msg.getSendingEnd() instanceof MixEnd && msg.getSendingEnd().getContext() != msg.getReceivingEnd().getContext();
        }
        return isValid;
    }
}
