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
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Queries from a {@link SequenceDiagram}.
 * 
 * @author edugueperoux
 * 
 */
public class SequenceDiagramQuery {

    private SequenceDiagram sequenceDiagram;

    /**
     * Create the query.
     * 
     * @param sequenceDiagram
     *            the source of the query.
     */
    public SequenceDiagramQuery(SequenceDiagram sequenceDiagram) {
        this.sequenceDiagram = sequenceDiagram;
    }

    /**
     * Get all {@link ISequenceEvent} of the current {@link SequenceDiagram} with range's lower bound strictly inferior
     * to timePoint.
     * 
     * @param timePoint
     *            the timePoint from which get all lower {@link ISequenceEvent}
     * 
     * @return the set of {@link ISequenceEvent} lower timePoint, sorted by range from lower to upper
     */
    public Set<ISequenceEvent> getAllSequenceEventsLowerThan(int timePoint) {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        Preconditions.checkArgument(timePoint > 0, Messages.SequenceDiagramQuery_invalidTimePoint);

        Set<ISequenceEvent> sequenceEventLowers = new TreeSet<ISequenceEvent>(new RangeComparator());
        for (ISequenceEvent sequenceEvent : getAllSequenceEvents()) {
            Range sequenceEventRange = sequenceEvent.getVerticalRange();
            if (sequenceEventRange.getLowerBound() < timePoint) {
                sequenceEventLowers.add(sequenceEvent);
            }
        }
        return sequenceEventLowers;
    }

    /**
     * Get all {@link ISequenceEvent} of the current {@link SequenceDiagram} with range's including strictly timePoint.
     * 
     * @param timePoint
     *            the timePoint from which get all {@link ISequenceEvent} including it in their range
     * 
     * @return the set of {@link ISequenceEvent} having timePoint in their range, sorted by range from lower to upper
     */
    public Set<ISequenceEvent> getAllSequenceEventsOn(int timePoint) {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        Preconditions.checkArgument(timePoint > 0, Messages.SequenceDiagramQuery_invalidTimePoint);

        Set<ISequenceEvent> sequenceEventOns = new TreeSet<ISequenceEvent>(new RangeComparator());
        for (ISequenceEvent sequenceEvent : getAllSequenceEvents()) {
            Range sequenceEventRange = sequenceEvent.getVerticalRange();
            if (sequenceEventRange.includes(timePoint)) {
                sequenceEventOns.add(sequenceEvent);
            }
        }
        return sequenceEventOns;
    }

    /**
     * Get all {@link ISequenceEvent} of the current {@link SequenceDiagram} with range's lowerbound strictly upper to
     * timePoint.
     * 
     * @param timePoint
     *            the timePoint from which get all upper {@link ISequenceEvent}
     * 
     * @return the set of {@link ISequenceEvent} upper timePoint, sorted by range from lower to upper
     */
    public Set<ISequenceEvent> getAllSequenceEventsUpperThan(int timePoint) {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        Preconditions.checkArgument(timePoint > 0, Messages.SequenceDiagramQuery_invalidTimePoint);

        Set<ISequenceEvent> sequenceEventUppers = new TreeSet<ISequenceEvent>(new RangeComparator());
        for (ISequenceEvent sequenceEvent : getAllSequenceEvents()) {
            Range sequenceEventRange = sequenceEvent.getVerticalRange();
            if (sequenceEventRange.getLowerBound() > timePoint) {
                sequenceEventUppers.add(sequenceEvent);
            }
        }
        return sequenceEventUppers;
    }

    /**
     * Get all {@link ISequenceEvent} of the current {@link SequenceDiagram}.
     * 
     * @return the set of {@link ISequenceEvent} of the current {@link SequenceDiagram}, sorted by range from lower to
     *         upper
     */
    public Set<ISequenceEvent> getAllSequenceEvents() {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);

        Set<ISequenceEvent> allSequenceEvents = new TreeSet<ISequenceEvent>(new RangeComparator());
        allSequenceEvents.addAll(sequenceDiagram.getAllLifelines());
        allSequenceEvents.addAll(sequenceDiagram.getAllAbstractNodeEvents());
        allSequenceEvents.addAll(sequenceDiagram.getAllMessages());
        allSequenceEvents.addAll(sequenceDiagram.getAllInteractionUses());
        allSequenceEvents.addAll(sequenceDiagram.getAllCombinedFragments());
        allSequenceEvents.addAll(sequenceDiagram.getAllOperands());
        allSequenceEvents.addAll(sequenceDiagram.getAllStates());
        return allSequenceEvents;
    }

    /**
     * Get all {@link ISequenceEvent} of the current {@link SequenceDiagram} of the specified {@link Lifeline}.
     * 
     * @param lifeline
     *            the specified {@link Lifeline}
     * 
     * @return the set of {@link ISequenceEvent} of the current {@link SequenceDiagram} of the specified
     *         {@link Lifeline}, sorted by range from lower to upper
     */
    public Set<ISequenceEvent> getAllSequenceEventsOnLifeline(Lifeline lifeline) {
        Set<ISequenceEvent> allSequenceEventsOnLifeline = new TreeSet<ISequenceEvent>(new RangeComparator());
        for (ISequenceEvent sequenceEvent : Iterables.filter(getAllSequenceEvents(), Predicates.not(Predicates.instanceOf(Lifeline.class)))) {
            Option<Lifeline> lifelineOfSequenceEventOption = sequenceEvent.getLifeline();
            if (lifelineOfSequenceEventOption.some() && lifelineOfSequenceEventOption.get().equals(lifeline)) {
                allSequenceEventsOnLifeline.add(sequenceEvent);
            } else if (!lifelineOfSequenceEventOption.some()) {
                if (sequenceEvent instanceof Message) {
                    Message message = (Message) sequenceEvent;
                    Option<Lifeline> sourceLifelineOption = message.getSourceLifeline();
                    Option<Lifeline> targetLifelineOption = message.getTargetLifeline();
                    if (sourceLifelineOption.some() && sourceLifelineOption.get().equals(lifeline)) {
                        allSequenceEventsOnLifeline.add(message);
                    } else if (targetLifelineOption.some() && targetLifelineOption.get().equals(lifeline)) {
                        allSequenceEventsOnLifeline.add(message);
                    }
                } else if (sequenceEvent instanceof AbstractFrame) {
                    AbstractFrame abstractFrame = (AbstractFrame) sequenceEvent;
                    Collection<Lifeline> coveredLifelines = abstractFrame.computeCoveredLifelines();
                    if (coveredLifelines.contains(lifeline)) {
                        allSequenceEventsOnLifeline.add(abstractFrame);
                    }
                }
            }
        }
        return allSequenceEventsOnLifeline;
    }

    /**
     * Get all events having at least one bound on the given {@link Lifeline} in the given range.
     * 
     * @param lifeline
     *            the lifeline to check.
     * @param inclusionRange
     *            the range to look in.
     * @return events on the given lifeline and range.
     */
    public SortedSet<ISequenceEvent> getAllSequenceEventsOnLifelineOnRange(Lifeline lifeline, Range inclusionRange) {
        SortedSet<ISequenceEvent> allSequenceEventsOnLifelineStrictlyIncludedBetween = new TreeSet<ISequenceEvent>(new RangeComparator());
        for (ISequenceEvent sequenceEvent : getAllSequenceEventsOnLifeline(lifeline)) {
            Range sequenceEventRange = sequenceEvent.getVerticalRange();
            if (inclusionRange.includesAtLeastOneBound(sequenceEventRange)) {
                allSequenceEventsOnLifelineStrictlyIncludedBetween.add(sequenceEvent);
            }
        }
        return allSequenceEventsOnLifelineStrictlyIncludedBetween;
    }

    /**
     * Get all {@link ISequenceNode} of the current {@link SequenceDiagram} with range's lowerbound strictly lower to
     * timePoint.
     * 
     * @param timePoint
     *            the timePoint from which get all lower {@link ISequenceNode}
     * 
     * @return the set of {@link ISequenceNode} lower timePoint, sorted by range from lower to upper
     */
    public Set<ISequenceNode> getAllSequenceNodesLowerThan(int timePoint) {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        Preconditions.checkArgument(timePoint > 0, Messages.SequenceDiagramQuery_invalidTimePoint);

        Set<ISequenceNode> sequenceNodeLowers = new TreeSet<ISequenceNode>(new RangeComparator());
        for (ISequenceNode sequenceNode : getAllSequenceNodes()) {
            Range sequenceNodeRange = RangeHelper.verticalRange(sequenceNode.getBounds());
            if (sequenceNodeRange.getLowerBound() < timePoint) {
                sequenceNodeLowers.add(sequenceNode);
            }
        }
        return sequenceNodeLowers;
    }

    /**
     * Get all {@link ISequenceNode} of the current {@link SequenceDiagram} with range's including strictly timePoint.
     * 
     * @param timePoint
     *            the timePoint from which get all {@link ISequenceNode} including it in their range
     * 
     * @return the set of {@link ISequenceNode} having timePoint in their range, sorted by range from lower to upper
     */
    public Set<ISequenceNode> getAllSequenceNodesOn(int timePoint) {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        Preconditions.checkArgument(timePoint > 0, Messages.SequenceDiagramQuery_invalidTimePoint);

        Set<ISequenceNode> sequenceNodeUnders = new TreeSet<ISequenceNode>(new RangeComparator());
        for (ISequenceNode sequenceNode : getAllSequenceNodes()) {
            Range sequenceEventRange = RangeHelper.verticalRange(sequenceNode.getBounds());
            if (sequenceEventRange.includes(timePoint)) {
                sequenceNodeUnders.add(sequenceNode);
            }
        }
        return sequenceNodeUnders;
    }

    /**
     * Get all {@link ISequenceNode} of the current {@link SequenceDiagram} with range's lowerbound strictly upper to
     * timePoint.
     * 
     * @param timePoint
     *            the timePoint from which get all upper {@link ISequenceNode}
     * 
     * @return the set of {@link ISequenceNode} upper timePoint, sorted by range from lower to upper
     */
    public Set<ISequenceNode> getAllSequenceNodesUpperThan(int timePoint) {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        Preconditions.checkArgument(timePoint > 0, Messages.SequenceDiagramQuery_invalidTimePoint);

        Set<ISequenceNode> sequenceNodeUnders = new TreeSet<ISequenceNode>(new RangeComparator());
        for (ISequenceNode sequenceNode : getAllSequenceNodes()) {
            Range sequenceNodeRange = RangeHelper.verticalRange(sequenceNode.getBounds());
            if (sequenceNodeRange.getLowerBound() > timePoint) {
                sequenceNodeUnders.add(sequenceNode);
            }
        }
        return sequenceNodeUnders;
    }

    /**
     * Get all {@link ISequenceNode} of the current {@link SequenceDiagram}.
     * 
     * @return the set of {@link ISequenceNode} of the current {@link SequenceDiagram}, sorted by range from lower to
     *         upper
     */
    public Set<ISequenceNode> getAllSequenceNodes() {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);

        Set<ISequenceNode> allSequenceNodes = new TreeSet<ISequenceNode>(new RangeComparator());
        allSequenceNodes.addAll(sequenceDiagram.getAllInstanceRoles());
        allSequenceNodes.addAll(sequenceDiagram.getAllLifelines());
        allSequenceNodes.addAll(sequenceDiagram.getAllAbstractNodeEvents());
        allSequenceNodes.addAll(sequenceDiagram.getAllInteractionUses());
        allSequenceNodes.addAll(sequenceDiagram.getAllCombinedFragments());
        allSequenceNodes.addAll(sequenceDiagram.getAllOperands());
        allSequenceNodes.addAll(sequenceDiagram.getAllEndOfLifes());
        return allSequenceNodes;
    }

    /**
     * Get the initial time from which event ends can graphically start. This initial time corresponds to y coordinate
     * of the bottom of the biggest InstanceRole not connected to a Create message +
     * {@link LayoutConstants#TIME_START_OFFSET}.
     * 
     * @return the initial time if the current {@link SequenceDiagram} contains {@link Lifeline}, 0 else
     */
    public int getInitialTime() {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        int initialTime = 0;
        for (Lifeline lifeline : sequenceDiagram.getAllLifelines()) {
            int initialTimeCandidate = lifeline.getVerticalRange().getLowerBound();
            if (initialTimeCandidate > initialTime && !lifeline.getInstanceRole().isExplicitlyCreated()) {
                initialTime = initialTimeCandidate;
            }
        }
        return initialTime + LayoutConstants.TIME_START_OFFSET + IBorderItemOffsets.DEFAULT_OFFSET.height;
    }

    /**
     * Get the final time until which event ends can graphically end. This final time corresponds to y coordinate of the
     * bottom of {@link Lifeline} - {@link LayoutConstants#TIME_STOP_OFFSET}.
     * 
     * @return the final time if the current {@link SequenceDiagram} contains {@link Lifeline}, 0 else
     */
    public int getFinalTime() {
        Objects.requireNonNull(sequenceDiagram, Messages.SequenceDiagramQuery_nullSequenceDiagram);
        int finalTime = 0;
        if (!sequenceDiagram.getAllLifelines().isEmpty()) {
            Lifeline lifeline = sequenceDiagram.getAllLifelines().iterator().next();
            finalTime = lifeline.getVerticalRange().getUpperBound() - LayoutConstants.TIME_STOP_OFFSET;
        }
        return finalTime;
    }

}
