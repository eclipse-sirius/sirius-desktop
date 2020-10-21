/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.layout.horizontal;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message.Kind;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.AbstractSequenceLayout;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * Computes the appropriate graphical locations of sequence events and lifelines on a sequence diagram to reflect the
 * semantic order.
 * 
 * @author pcdavid, mporhel
 */
public class LostMessageEndHorizontalLayoutHelper {

    private final Map<LostMessageEnd, Message> lostMessages = Maps.newHashMap();

    private final Multimap<Lifeline, LostMessageEnd> lostSources = HashMultimap.create();

    private final Multimap<Lifeline, LostMessageEnd> lostTargets = HashMultimap.create();

    private Map<LostMessageEnd, Operand> operands = Maps.newHashMap();

    private Multimap<Operand, LostMessageEnd> operands2lostEnds = HashMultimap.create();

    private Set<LostMessageEnd> diagramLostEnds = Sets.newHashSet();

    private SequenceDiagram sequenceDiagram;

    private Set<LostMessageEnd> unconnectedLostEnds = Sets.newHashSet();

    /**
     * Constructor.
     * 
     * @param diagram
     *            the sequence diagram for which to compute the horizontal locations.
     */
    public LostMessageEndHorizontalLayoutHelper(SequenceDiagram diagram) {
        this.sequenceDiagram = diagram;
    }

    /**
     * Populate lost message ends and helper context.
     */
    public void populateLostMessageEnds() {
        for (Message msg : sequenceDiagram.getAllMessages()) {
            populateLostEnds(msg);
        }

        registerUnconnectedLostEnds();
    }

    private void registerUnconnectedLostEnds() {
        Predicate<LostMessageEnd> unConnectedEnds = Predicates.not(Predicates.in(lostMessages.keySet()));
        for (LostMessageEnd lme : Iterables.filter(sequenceDiagram.getAllLostMessageEnds(), unConnectedEnds)) {
            unconnectedLostEnds.add(lme);

            // look viewpoint edges
            if (lme.getNotationNode().getElement() instanceof EdgeTarget) {
                EdgeTarget targetNode = getKnownTargetNode(lme);
                if (targetNode != null) {
                    ISequenceEvent ise = getISequenceEvent(targetNode);
                    if (ise != null && ise.getLifeline().some()) {
                        lostSources.put(ise.getLifeline().get(), lme);
                        Option<Operand> parentOperand = ise.getParentOperand();
                        if (parentOperand.some()) {
                            operands.put(lme, parentOperand.get());
                            operands2lostEnds.put(parentOperand.get(), lme);
                        } else {
                            diagramLostEnds.add(lme);
                        }
                    }
                }
                EdgeTarget sourceNode = getKnownSourceNode(lme);
                if (sourceNode != null) {
                    ISequenceEvent ise = getISequenceEvent(sourceNode);
                    if (ise != null && ise.getLifeline().some()) {
                        lostTargets.put(ise.getLifeline().get(), lme);
                        Option<Operand> parentOperand = ise.getParentOperand();
                        if (parentOperand.some()) {
                            operands.put(lme, parentOperand.get());
                            operands2lostEnds.put(parentOperand.get(), lme);
                        } else {
                            diagramLostEnds.add(lme);
                        }
                    }
                }
            }
        }
    }

    private void populateLostEnds(Message msg) {
        ISequenceNode sourceElement = msg.getSourceElement();
        ISequenceNode targetElement = msg.getTargetElement();

        if (sourceElement != null && targetElement != null) {
            Option<Lifeline> sourceLifeline = sourceElement.getLifeline();
            Option<Lifeline> targetLifeline = targetElement.getLifeline();

            // Only messages with one lost end are handled.
            if (sourceElement instanceof LostMessageEnd && targetLifeline.some()) {
                LostMessageEnd sourceLME = (LostMessageEnd) sourceElement;
                lostSources.put(targetLifeline.get(), sourceLME);
                lostMessages.put(sourceLME, msg);
                Option<Operand> parentOperand = msg.getParentOperand();
                if (parentOperand.some()) {
                    operands.put(sourceLME, parentOperand.get());
                    operands2lostEnds.put(parentOperand.get(), sourceLME);
                } else {
                    diagramLostEnds.add(sourceLME);
                }
            } else if (targetElement instanceof LostMessageEnd && sourceLifeline.some()) {
                LostMessageEnd targetLME = (LostMessageEnd) targetElement;
                lostTargets.put(sourceLifeline.get(), targetLME);
                lostMessages.put(targetLME, msg);
                Option<Operand> parentOperand = msg.getParentOperand();
                if (parentOperand.some()) {
                    operands.put(targetLME, parentOperand.get());
                    operands2lostEnds.put(parentOperand.get(), targetLME);
                } else {
                    diagramLostEnds.add(targetLME);
                }
            }
        }
    }

    /**
     * Computes the delta between lostEnds and their attached lifeline.
     * 
     * @param pack
     *            pack the space between instance roles.
     * @return computed deltas.
     */
    public Map<LostMessageEnd, Integer> computeLostMessageEndDeltaWithLifeline(boolean pack) {
        Map<LostMessageEnd, Integer> deltas = Maps.newHashMap();

        for (Lifeline lifeline : sequenceDiagram.getAllLifelines()) {
            int lifelineX = lifeline.getProperLogicalBounds().x;

            // Align sources on left
            Map<Operand, Integer> maxOpSourceDeltas = Maps.newHashMap();
            int maxLifelineSourceDelta = 0;
            for (LostMessageEnd lostSource : lostSources.get(lifeline)) {
                Rectangle bounds = lostSource.getProperLogicalBounds().getCopy();
                int delta = bounds.x - lifelineX;
                if (pack || AbstractSequenceLayout.createdFromTool(lostSource) || AbstractSequenceLayout.createdFromExternalChange(lostSource)) {
                    delta = getFoundEndPackedX(lostSource, lifeline, lifelineX, bounds.width);
                }
                deltas.put(lostSource, delta);

                // Force align
                if (operands.containsKey(lostSource)) {
                    Operand op = operands.get(lostSource);
                    int opMax = 0;
                    if (maxOpSourceDeltas.containsKey(op)) {
                        opMax = maxOpSourceDeltas.get(op);
                    }
                    opMax = Math.min(opMax, delta);
                    maxOpSourceDeltas.put(op, opMax);
                } else {
                    Kind kind = getMessageKind(lostSource);

                    if (!Message.Kind.CREATION.equals(kind) && !Message.Kind.DESTRUCTION.equals(kind)) {
                        maxLifelineSourceDelta = Math.min(maxLifelineSourceDelta, delta);
                    }
                }
            }

            // Align targets on right
            Map<Operand, Integer> maxOpTargetDeltas = Maps.newHashMap();
            int maxLifelineTargetDelta = 0;
            for (LostMessageEnd lostTarget : lostTargets.get(lifeline)) {
                Rectangle bounds = lostTarget.getProperLogicalBounds().getCopy();
                int delta = bounds.x - lifelineX;
                if (pack || AbstractSequenceLayout.createdFromTool(lostTarget) || AbstractSequenceLayout.createdFromExternalChange(lostTarget)) {
                    delta = LayoutConstants.LOST_MESSAGE_DEFAULT_WIDTH;
                    if (lostMessages.containsKey(lostTarget)) {
                        Message message = lostMessages.get(lostTarget);
                        delta += message.getSourceElement().getProperLogicalBounds().right() - lifelineX;
                    } else if (unconnectedLostEnds.contains(lostTarget)) {
                        ISequenceEvent sourceEvent = getISequenceEvent(getKnownSourceNode(lostTarget));
                        if (sourceEvent != null) {
                            delta += sourceEvent.getProperLogicalBounds().right() - lifelineX;
                        }
                    }
                }
                deltas.put(lostTarget, delta);

                // Force align
                if (operands.containsKey(lostTarget)) {
                    Operand op = operands.get(lostTarget);
                    int opMax = 0;
                    if (maxOpTargetDeltas.containsKey(op)) {
                        opMax = maxOpTargetDeltas.get(op);
                    }
                    opMax = Math.max(opMax, delta);
                    maxOpTargetDeltas.put(op, opMax);
                } else {
                    maxLifelineTargetDelta = Math.max(maxLifelineTargetDelta, delta);
                }
            }

            // Force align
            if (pack) {
                for (Map.Entry<Operand, Integer> entry : maxOpSourceDeltas.entrySet()) {
                    Integer maxSourceDelta = entry.getValue();
                    for (LostMessageEnd source : Iterables.filter(operands2lostEnds.get(entry.getKey()), Predicates.in(lostSources.get(lifeline)))) {
                        deltas.put(source, maxSourceDelta);
                    }
                }
                for (LostMessageEnd source : Iterables.filter(lostSources.get(lifeline), Predicates.not(Predicates.in(operands.keySet())))) {
                    Kind kind = getMessageKind(source);

                    if (!Message.Kind.CREATION.equals(kind) && !Message.Kind.DESTRUCTION.equals(kind)) {
                        deltas.put(source, maxLifelineSourceDelta);
                    }
                }
                for (Map.Entry<Operand, Integer> entry : maxOpTargetDeltas.entrySet()) {
                    Integer maxTargetDelta = entry.getValue();
                    for (LostMessageEnd target : Iterables.filter(operands2lostEnds.get(entry.getKey()), Predicates.in(lostTargets.get(lifeline)))) {
                        deltas.put(target, maxTargetDelta);
                    }
                }
                for (LostMessageEnd target : Iterables.filter(lostTargets.get(lifeline), Predicates.not(Predicates.in(operands.keySet())))) {
                    deltas.put(target, maxLifelineTargetDelta);
                }
            }
        }
        return deltas;
    }

    private int getFoundEndPackedX(LostMessageEnd lostSourceEnd, Lifeline lifeline, int lifelineX, int lostEndWidth) {
        int refX = lifelineX;
        Kind kind = getMessageKind(lostSourceEnd);

        if (Message.Kind.CREATION.equals(kind) && lifeline.getInstanceRole() != null) {
            refX = lifeline.getInstanceRole().getProperLogicalBounds().x;
        } else if (Message.Kind.DESTRUCTION.equals(kind) && lifeline.getEndOfLife().some()) {
            refX = lifeline.getEndOfLife().get().getProperLogicalBounds().x;
        }
        return refX - lifelineX - LayoutConstants.LOST_MESSAGE_DEFAULT_WIDTH - lostEndWidth;
    }

    /**
     * Get the found ends gap.
     * 
     * @param lifeline
     *            the current lifeline.
     * @param zone
     *            zone to get look for lost messages, can be null.
     * @param lostEndsDelta
     *            the computed deltas with lifeline.
     * @return the gap.
     */
    public int getLeftGap(Lifeline lifeline, Range zone, Map<LostMessageEnd, Integer> lostEndsDelta) {
        int foundEndsGap = getLostMessageEndsGap(lifeline, lostSources, zone, lostEndsDelta, true);
        int lostMessageEndsGap = getLostMessageEndsGap(lifeline, lostTargets, zone, lostEndsDelta, true);
        return Math.max(lostMessageEndsGap, foundEndsGap);
    }

    /**
     * Get the lost ends gap.
     * 
     * @param lifeline
     *            the current lifeline.
     * @param zone
     *            zone to get look for lost messages, can be null.
     * @param lostEndsDelta
     *            the computed deltas with lifeline.
     * @return the gap.
     */
    public int getRightEndsGap(Lifeline lifeline, Range zone, Map<LostMessageEnd, Integer> lostEndsDelta) {
        int lostMessageEndsGap = getLostMessageEndsGap(lifeline, lostTargets, zone, lostEndsDelta, false);
        int foundEndsGap = getLostMessageEndsGap(lifeline, lostSources, zone, lostEndsDelta, false);
        return Math.max(lostMessageEndsGap, foundEndsGap);
    }

    private EdgeTarget getKnownSourceNode(LostMessageEnd lme) {
        EdgeTarget sourceNode = null;
        EdgeTarget dde = (EdgeTarget) lme.getNotationNode().getElement();
        Iterable<DEdge> incomingEdges = Iterables.filter(dde.getIncomingEdges(), Message.viewpointElementPredicate());
        if (!Iterables.isEmpty(incomingEdges)) {
            DEdge msg = incomingEdges.iterator().next();
            sourceNode = msg.getSourceNode();
        }
        return sourceNode;
    }

    private EdgeTarget getKnownTargetNode(LostMessageEnd lme) {
        EdgeTarget targetNode = null;
        EdgeTarget dde = (EdgeTarget) lme.getNotationNode().getElement();
        Iterable<DEdge> outgoingEdges = Iterables.filter(dde.getOutgoingEdges(), Message.viewpointElementPredicate());
        if (!Iterables.isEmpty(outgoingEdges)) {
            DEdge msg = outgoingEdges.iterator().next();
            targetNode = msg.getTargetNode();
        }
        return targetNode;
    }

    // Get the first known event in hierarchy.
    private ISequenceEvent getISequenceEvent(EdgeTarget lostNode) {
        ISequenceEvent correspondingEvent = null;
        List<ISequenceEvent> knownEnds = Lists.newArrayList();
        knownEnds.addAll(sequenceDiagram.getAllLifelines());
        knownEnds.addAll(sequenceDiagram.getAllExecutions());

        for (ISequenceEvent ise : knownEnds) {
            if (ise.getNotationView() != null && lostNode != null && lostNode.equals(ise.getNotationView().getElement())) {
                correspondingEvent = ise;
                break;
            }
        }
        if (correspondingEvent == null) {
            EObject eContainer = lostNode.eContainer();
            for (ISequenceEvent ise : knownEnds) {
                if (ise.getNotationView() != null && eContainer != null && eContainer.equals(ise.getNotationView().getElement())) {
                    correspondingEvent = ise;
                }
            }
        }
        return correspondingEvent;
    }

    private Kind getMessageKind(LostMessageEnd lostSourceEnd) {
        Kind kind = Message.Kind.BASIC;
        Message message = lostMessages.get(lostSourceEnd);
        if (message != null) {
            kind = message.getKind();
        } else if (unconnectedLostEnds.contains(lostSourceEnd) && lostSourceEnd.getNotationNode().getElement() instanceof EdgeTarget) {
            EdgeTarget dde = (EdgeTarget) lostSourceEnd.getNotationNode().getElement();
            if (!dde.getOutgoingEdges().isEmpty()) {
                kind = Message.VIEWPOINT_MESSAGE_KIND.apply(dde.getOutgoingEdges().iterator().next());
            }
        }
        return kind;
    }

    private int getLostMessageEndsGap(Lifeline lifeline, Multimap<Lifeline, LostMessageEnd> lostEnds, Range zone, Map<LostMessageEnd, Integer> lostEndsDelta, boolean revertDelta) {
        int gap = 0;
        if (lostEnds.containsKey(lifeline)) {
            int maxLostEndWidth = 0;
            int maxDelta = 0;
            int frameBorder = 0;
            for (LostMessageEnd lme : lostEnds.get(lifeline)) {
                // Message in zone ?
                if (zone == null || lostMessages.containsKey(lme) && zone.includesAtLeastOneBound(lostMessages.get(lme).getVerticalRange())) {
                    int delta = lostEndsDelta.get(lme);
                    maxDelta = Math.max(maxDelta, revertDelta ? -delta : delta);
                    if (maxDelta > 0) {
                        frameBorder = LayoutConstants.BORDER_FRAME_MARGIN;
                        maxLostEndWidth = Math.max(maxLostEndWidth, lme.getProperLogicalBounds().width);
                    }
                }
            }
            gap = maxDelta + maxLostEndWidth + frameBorder;
        }
        return gap;
    }

    /**
     * Dispose the helper context.
     */
    public void dispose() {
        operands.clear();
        operands2lostEnds.clear();
        diagramLostEnds.clear();
        lostMessages.clear();
        lostSources.clear();
        lostTargets.clear();
        unconnectedLostEnds.clear();
    }

    /**
     * Compute the lost message ends futur locations.
     * 
     * @param irMoves
     *            computed instance roles new locations.
     * @param lostEndsDelta
     *            computed delats between lost message ends and lifelines.
     * @return lost message ends locations.
     */
    public Map<LostMessageEnd, Rectangle> computeLostMessageEndsHorizontalBounds(Map<InstanceRole, Rectangle> irMoves, Map<LostMessageEnd, Integer> lostEndsDelta) {
        Map<LostMessageEnd, Rectangle> lostMessageEndMoves = Maps.newHashMap();

        for (Lifeline lifeline : sequenceDiagram.getAllLifelines()) {
            int newLifelineX = getNewLifelineX(lifeline, irMoves);

            // Compute new ends x.
            for (LostMessageEnd lostSource : Iterables.concat(lostSources.get(lifeline), lostTargets.get(lifeline))) {
                int deltaWithLifeline = lostEndsDelta.get(lostSource);
                Rectangle bounds = lostSource.getProperLogicalBounds().getCopy();

                bounds.x = newLifelineX + deltaWithLifeline;
                lostMessageEndMoves.put(lostSource, bounds);
            }
        }
        return lostMessageEndMoves;
    }

    private int getNewLifelineX(Lifeline lifeline, Map<InstanceRole, Rectangle> irMoves) {
        int lifelineOldX = lifeline.getProperLogicalBounds().x;
        InstanceRole instanceRole = lifeline.getInstanceRole();

        int deltaX = 0;
        if (instanceRole != null && irMoves.containsKey(instanceRole)) {
            Rectangle irBounds = instanceRole.getProperLogicalBounds().getCopy();
            Rectangle irFutureBounds = irMoves.get(instanceRole);

            deltaX = irFutureBounds.x - irBounds.x;
        }
        return lifelineOldX + deltaX;
    }

}
