/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.layout.horizontal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.AbstractSequenceOrderingLayout;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceElementQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;

/**
 * Computes the appropriate graphical locations of sequence events and lifelines
 * on a sequence diagram to reflect the semantic order.
 * 
 * @author pcdavid, mporhel
 */
public class SequenceHorizontalLayout extends AbstractSequenceOrderingLayout<ISequenceElement, Rectangle, InstanceRole> {

    private static final Function<Rectangle, Integer> RECT_TO_X = new Function<Rectangle, Integer>() {
        public Integer apply(Rectangle input) {
            return input.x;
        }
    };

    private final Insets padding = new Insets(LayoutConstants.LIFELINES_START_Y, LayoutConstants.LIFELINES_START_X, 0, LayoutConstants.LIFELINES_MIN_X_GAP - LayoutConstants.LIFELINES_START_X);

    private final Collection<AbstractFrame> frames = Lists.newArrayList();

    private final Multimap<AbstractFrame, Lifeline> coverage = HashMultimap.create();

    private final Multimap<Lifeline, AbstractFrame> invCoverage = HashMultimap.create();

    private final Map<AbstractFrame, Range> ranges = Maps.newHashMap();

    private final Map<AbstractFrame, Integer> frameChildrenDepths = Maps.newHashMap();

    private final Map<Lifeline, Integer> lifelineChildrenDepths = Maps.newHashMap();

    private LostMessageEndHorizontalLayoutHelper lostMessageEndHorizontalLayoutHelper;

    private final Map<Message, Integer> reflexiveMessagesToLayout = Maps.newHashMap();

    /**
     * Constructor.
     * 
     * @param diagram
     *            the sequence diagram for which to compute the horizontal
     *            locations.
     */
    public SequenceHorizontalLayout(SequenceDiagram diagram) {
        super(diagram);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init(boolean pack) {
        if (pack) {
            this.padding.left = LayoutConstants.LIFELINES_START_X;
            this.padding.right = LayoutConstants.LIFELINES_MIN_PACKED_X_GAP - LayoutConstants.LIFELINES_START_X;
        }

        populateSortedIntanceRoles();

        populateLifelineDepth();
        populateFrames();
        populateLostMessageEnds();

        populateReflexiveMessages();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Rectangle getOldLayoutData(ISequenceElement ise) {
        return ise.getProperLogicalBounds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<ISequenceElement, Rectangle> computeLayout(boolean pack) {
        LinkedHashMap<ISequenceElement, Rectangle> allMoves = Maps.newLinkedHashMap();

        Map<LostMessageEnd, Integer> lostEndsDelta = lostMessageEndHorizontalLayoutHelper.computeLostMessageEndDeltaWithLifeline(pack);
        Map<Message, Rectangle> reflexiveMessagesMoves = computeReflexiveMessagesHorizontalBounds();

        Map<InstanceRole, Rectangle> irMoves = computeInstanceRoleHorizontalLocations(pack, lostEndsDelta);
        Map<LostMessageEnd, Rectangle> lostMessageEndMoves = lostMessageEndHorizontalLayoutHelper.computeLostMessageEndsHorizontalBounds(irMoves, lostEndsDelta);
        Map<AbstractFrame, Rectangle> frameMoves = computeFrameHorizontalBounds(irMoves, lostEndsDelta);

        allMoves.putAll(irMoves);
        allMoves.putAll(frameMoves);
        allMoves.putAll(lostMessageEndMoves);
        allMoves.putAll(reflexiveMessagesMoves);
        return allMoves;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean applyComputedLayout(Map<? extends ISequenceElement, Rectangle> bounds, boolean pack) {
        boolean applied = false;

        applied = layoutInstanceRoles(bounds, pack) || applied;
        applied = layoutFrames(bounds, pack) || applied;
        applied = layoutLostMessageEnds(bounds, pack) || applied;
        applied = layoutReflexiveMessages(bounds, pack) || applied;

        // if (pack) {
        // correctGmfLocations();
        // }

        return applied;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispose() {
        frameChildrenDepths.clear();
        lifelineChildrenDepths.clear();
        coverage.clear();
        invCoverage.clear();
        ranges.clear();
        frames.clear();
        lostMessageEndHorizontalLayoutHelper.dispose();
        reflexiveMessagesToLayout.clear();

        super.dispose();
    }

    @SuppressWarnings("unused")
    private void correctGmfLocations() {
        layoutAbstractNodes();
        layoutLifeline();
    }

    private void layoutLifeline() {
        for (Lifeline lifeline : sequenceDiagram.getAllLifelines()) {
            Rectangle properLogicalBounds = lifeline.getProperLogicalBounds();
            Rectangle parentLogicalBounds = lifeline.getInstanceRole().getProperLogicalBounds();
            LayoutConstraint layoutConstraint = lifeline.getNotationNode().getLayoutConstraint();
            if (layoutConstraint instanceof Location) {
                Location loc = (Location) layoutConstraint;
                loc.setX(properLogicalBounds.x - parentLogicalBounds.x);
            }
        }
    }

    private void layoutAbstractNodes() {
        for (AbstractNodeEvent exec : sequenceDiagram.getAllAbstractNodeEvents()) {
            Rectangle properLogicalBounds = exec.getProperLogicalBounds();
            Rectangle parentLogicalBounds = exec.getHierarchicalParentEvent().getProperLogicalBounds();
            LayoutConstraint layoutConstraint = exec.getNotationNode().getLayoutConstraint();
            if (layoutConstraint instanceof Location) {
                Location loc = (Location) layoutConstraint;
                loc.setX(properLogicalBounds.x - parentLogicalBounds.x);
            }
        }
    }

    private void populateSortedIntanceRoles() {
        // Graphicall order
        graphicalOrdering.addAll(sequenceDiagram.getSortedInstanceRole());

        // If a semantic order is specified, sort the instance roles.
        semanticOrdering.addAll(graphicalOrdering);
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        if (sequenceDDiagram != null && !sequenceDDiagram.getInstanceRoleSemanticOrdering().getSemanticInstanceRoles().isEmpty()) {
            final List<EObject> semanticOrder = sequenceDDiagram.getInstanceRoleSemanticOrdering().getSemanticInstanceRoles();
            Function<InstanceRole, Integer> semanticIndex = new Function<InstanceRole, Integer>() {
                public Integer apply(InstanceRole ir) {
                    Option<EObject> semIr = ir.getSemanticTargetElement();
                    return semIr.some() ? semanticOrder.indexOf(semIr.get()) : -1;
                }
            };
            Collections.sort(semanticOrdering, Ordering.natural().onResultOf(semanticIndex));
        }

        for (InstanceRole role : semanticOrdering) {
            ISequenceElementQuery query = new ISequenceElementQuery(role);
            if (query.hasAbsoluteBoundsFlag()) {
                Rectangle flaggedAbsoluteBounds = query.getFlaggedAbsoluteBounds();
                oldFlaggedLayoutData.put(role, flaggedAbsoluteBounds);
                flaggedEnds.add(role);
            }
        }

        Collections.sort(flaggedEnds, Ordering.natural().onResultOf(getOldFlaggedPosition()));
    }

    private void populateLifelineDepth() {
        List<Lifeline> allLifelines = sequenceDiagram.getAllLifelines();
        for (Lifeline lifeline : allLifelines) {
            int depth = getOrComputeMaxChildrenDepth(lifeline.getNotationNode(), lifeline.getVerticalRange());
            lifelineChildrenDepths.put(lifeline, depth);
        }
    }

    private void populateLostMessageEnds() {
        lostMessageEndHorizontalLayoutHelper = new LostMessageEndHorizontalLayoutHelper(sequenceDiagram);
        lostMessageEndHorizontalLayoutHelper.populateLostMessageEnds();
    }

    private void populateFrames() {
        Collection<AbstractFrame> allFrames = Lists.newArrayList();
        allFrames.addAll(sequenceDiagram.getAllInteractionUses());
        allFrames.addAll(sequenceDiagram.getAllCombinedFragments());

        for (AbstractFrame frame : allFrames) {
            Collection<Lifeline> coveredLifelines = frame.computeCoveredLifelines();
            if (!coveredLifelines.isEmpty()) {
                frames.add(frame);
                coverage.putAll(frame, coveredLifelines);
                ranges.put(frame, frame.getVerticalRange());
            }
        }

        for (AbstractFrame frame : frames) {
            getOrComputeMaxChildrenDepth(frame, Collections.singletonList(frame));
        }

        Multimaps.invertFrom(coverage, invCoverage);
    }

    private void populateReflexiveMessages() {
        for (Message msg : sequenceDiagram.getAllMessages()) {
            if (msg.isReflective()) {
                int width = msg.getReflexiveMessageWidth();
                if (width != 0) {
                    reflexiveMessagesToLayout.put(msg, width);
                }
            }
        }

    }

    private int getOrComputeMaxChildrenDepth(Node node, Range range) {
        int maxChildrenDepth = 0;
        for (Node child : Iterables.filter(Iterables.filter(node.getChildren(), Node.class), AbstractNodeEvent.notationPredicate())) {
            AbstractNodeEvent childExec = ISequenceElementAccessor.getAbstractNodeEvent(child).get();
            if (range == null || range.intersects(childExec.getVerticalRange())) {
                int childDepth = 1 + getOrComputeMaxChildrenDepth(child, range);
                maxChildrenDepth = Math.max(childDepth, maxChildrenDepth);
            }
        }
        return maxChildrenDepth;
    }

    private int getOrComputeMaxChildrenDepth(AbstractFrame frame, Collection<AbstractFrame> framesToIgnore) {
        int children = 0;
        if (!frameChildrenDepths.containsKey(frame)) {
            Collection<Lifeline> frameCoverage = coverage.get(frame);
            Range frameRange = ranges.get(frame);
            for (AbstractFrame potentialChild : Iterables.filter(frames, Predicates.not(Predicates.in(framesToIgnore)))) {
                Collection<Lifeline> potentialCoverage = coverage.get(potentialChild);
                Range potentialRange = ranges.get(potentialChild);

                if (frame != potentialChild && frameCoverage.containsAll(potentialCoverage) && frameRange.includes(potentialRange)) {
                    ArrayList<AbstractFrame> newArrayList = Lists.newArrayList(framesToIgnore);
                    newArrayList.add(potentialChild);
                    children = Math.max(children, 1 + getOrComputeMaxChildrenDepth(potentialChild, newArrayList));
                }
            }
            frameChildrenDepths.put(frame, children);
        } else {
            children = frameChildrenDepths.get(frame);
        }
        return children;
    }

    private Map<AbstractFrame, Rectangle> computeFrameHorizontalBounds(Map<InstanceRole, Rectangle> irMoves, Map<LostMessageEnd, Integer> lostEndsDelta) {
        Map<AbstractFrame, Rectangle> frameMoves = Maps.newHashMap();

        for (AbstractFrame frame : frames) {
            Rectangle newBounds = null;
            Lifeline leftLifeline = null;
            Lifeline rightLifeline = null;
            for (Lifeline lifeline : coverage.get(frame)) {
                Rectangle lBounds = getInstanceRoleBounds(lifeline.getInstanceRole(), irMoves);
                if (newBounds == null) {
                    newBounds = lBounds.getCopy();
                } else {
                    newBounds.union(lBounds);
                }

                // look for right lifeline
                if (lBounds.right() == newBounds.right()) {
                    rightLifeline = lifeline;
                }

                // look for left lifeline
                if (lBounds.x == newBounds.x) {
                    leftLifeline = lifeline;
                }
            }

            Integer frameDepth = frameChildrenDepths.get(frame);
            int frameDepthGap = frameDepth * LayoutConstants.BORDER_FRAME_MARGIN;

            if (rightLifeline != null) {
                Range verticalRange = frame.getVerticalRange();
                int irWidth = getInstanceRoleBounds(rightLifeline.getInstanceRole(), irMoves).width;
                int lifelineRightGap = getLifelineRightGap(rightLifeline, verticalRange, irWidth, lostEndsDelta);
                newBounds.width += Math.max(lifelineRightGap, frameDepthGap);
            }

            if (leftLifeline != null) {
                Range verticalRange = frame.getVerticalRange();
                int irWidth = getInstanceRoleBounds(leftLifeline.getInstanceRole(), irMoves).width;
                int lifelineLeftGap = getLifelineLeftGap(rightLifeline, verticalRange, irWidth, lostEndsDelta);
                lifelineLeftGap = Math.max(lifelineLeftGap, frameDepthGap);

                newBounds.x -= lifelineLeftGap;
                newBounds.width += lifelineLeftGap;
            }

            frameMoves.put(frame, newBounds);
        }
        return frameMoves;
    }

    private Rectangle getInstanceRoleBounds(InstanceRole instanceRole, Map<InstanceRole, Rectangle> irMoves) {
        if (irMoves.containsKey(instanceRole)) {
            return irMoves.get(instanceRole);
        }
        return instanceRole.getBounds();
    }

    /**
     * Computes the horizontal absolute location of instance roles.
     * 
     * @param pack
     *            pack the diagram
     * @param lostEndsDelta
     * @param reflexiveMessagesMoves
     * 
     * @return a map associating each instance role edit part to the new
     *         absolute horizontal location it should have.
     */
    private Map<InstanceRole, Rectangle> computeInstanceRoleHorizontalLocations(boolean pack, Map<LostMessageEnd, Integer> lostEndsDelta) {
        final Map<InstanceRole, Rectangle> computedMoves = new HashMap<InstanceRole, Rectangle>();

        // initial position
        int currentX = padding.left;
        for (InstanceRole instanceRole : semanticOrdering) {
            currentX = computeLocation(currentX, instanceRole, pack, lostEndsDelta, computedMoves);
        }
        return computedMoves;
    }

    /**
     * Compute and store the new bounds of the instance roles, the x location
     * will be the only modified value. Return the next minimum x.
     */
    private int computeLocation(final int currentX, final InstanceRole instanceRole, boolean pack, Map<LostMessageEnd, Integer> lostEndsDelta, final Map<InstanceRole, Rectangle> computedMoves) {
        final Rectangle oldBounds = instanceRole.getProperLogicalBounds();
        final Option<Lifeline> lifeline = instanceRole.getLifeline();

        int newMinX = currentX;
        int rightComputedGap = 0;
        if (lifeline.some()) {
            int maxFrameDepth = getMaxFrameDepth(lifeline.get());
            int foundMessagesGap = getLifelineLeftGap(lifeline.get(), null, oldBounds.width, lostEndsDelta);
            int frameGap = maxFrameDepth * LayoutConstants.BORDER_FRAME_MARGIN;
            int rightGap = getLifelineRightGap(lifeline.get(), null, oldBounds.width, lostEndsDelta);

            // Make space for frame and found messages.
            newMinX += frameGap + foundMessagesGap;
            // Update computed gap
            rightComputedGap = frameGap + rightGap;
        }

        Rectangle newBounds = oldBounds.getCopy();
        if (pack) {
            newBounds.x = newMinX;
        } else {
            // shift the current instancerole to the right ?
            // don't reduce previous delta with known/flagged predecessor
            int deltaStablePosition = getDeltaStablePosition(newMinX, instanceRole, Maps.transformValues(computedMoves, RECT_TO_X));

            newBounds.x = Math.max(newMinX, Math.max(newBounds.x, deltaStablePosition));
        }

        // Store computed move
        computedMoves.put(instanceRole, newBounds);

        // Return the next minX : right of the current instance role + minimum
        // gap + place for frames and found messages
        return newBounds.right() + getMinInstanceRoleGap() + rightComputedGap;
    }

    private int getMaxReflexiveDepth(Lifeline lifeline, Range zone) {
        int maxWidth = 0;
        for (Entry<Message, Integer> msg : reflexiveMessagesToLayout.entrySet()) {
            if (lifeline.equals(msg.getKey().getLifeline().get()) && (zone == null || zone.includes(msg.getKey().getVerticalRange()))) {
                maxWidth = Math.max(maxWidth, msg.getValue());
            }
        }
        return maxWidth;
    }

    private Map<Message, Rectangle> computeReflexiveMessagesHorizontalBounds() {
        final Map<Message, Rectangle> computedMoves = new HashMap<Message, Rectangle>();
        for (Entry<Message, Integer> msg : reflexiveMessagesToLayout.entrySet()) {
            Rectangle properLogicalBounds = msg.getKey().getProperLogicalBounds();
            properLogicalBounds.width = msg.getValue();
            computedMoves.put(msg.getKey(), properLogicalBounds);
        }
        return computedMoves;
    }

    private int getMinInstanceRoleGap() {
        return padding.right + padding.left;
    }

    private int getMaxExecDepth(Lifeline lifeline) {
        int depth = 0;
        if (lifelineChildrenDepths.containsKey(lifeline)) {
            depth = lifelineChildrenDepths.get(lifeline);
        }
        return depth;
    }

    private int getMaxFrameDepth(Lifeline lifeline) {
        int depth = 0;
        Collection<AbstractFrame> collection = invCoverage.get(lifeline);
        if (collection != null && !collection.isEmpty()) {
            for (AbstractFrame abstractFrame : collection) {
                Integer integer = frameChildrenDepths.get(abstractFrame);
                depth = Math.max(integer, depth);
            }
        }
        return depth;
    }

    /**
     * Compute the right gap for the given lifeline and range.
     * 
     * @param lifeline
     *            the current lifeline.
     * @param zone
     *            if not null, the restricted vertical range to look for
     *            execution and lost ends.
     * @param irWidth
     *            the instance role width.
     * @param lostEndsDelta
     * @return the right gap for the current lifeline.
     */
    private int getLifelineRightGap(Lifeline lifeline, Range zone, int irWidth, Map<LostMessageEnd, Integer> lostEndsDelta) {
        int rightGap = 0;
        int execDepth = zone == null ? getMaxExecDepth(lifeline) : getOrComputeMaxChildrenDepth(lifeline.getNotationNode(), zone);

        // handle Execution and State hierarchy
        if (execDepth != 0) {
            // the first execution is centered on the lifeline.
            int gap = LayoutConstants.UNIT;
            // TODO get the execution vsm size.
            gap += (execDepth - 1) * 15;
            // Remove the mid instance role size to get the gap relative to its
            // right and ad the minimum gap between last execution and frame
            // border.
            gap += -irWidth / 2 + LayoutConstants.UNIT;
            rightGap = Math.max(0, gap);
        }

        // Make space for reflexive messages
        int reflexiveGap = getMaxReflexiveDepth(lifeline, zone) - irWidth / 2;
        rightGap = Math.max(rightGap, reflexiveGap);

        // handle lost message ends
        rightGap = Math.max(rightGap, lostMessageEndHorizontalLayoutHelper.getRightEndsGap(lifeline, zone, lostEndsDelta) - irWidth / 2);

        return rightGap;
    }

    private int getLifelineLeftGap(Lifeline lifeline, Range zone, int irWidth, Map<LostMessageEnd, Integer> lostEndsDelta) {
        int leftGap = 0;

        // handle lost message ends
        leftGap = Math.max(leftGap, lostMessageEndHorizontalLayoutHelper.getLeftGap(lifeline, zone, lostEndsDelta) - irWidth / 2);

        return leftGap;
    }

    private boolean layoutInstanceRoles(Map<? extends ISequenceElement, Rectangle> bounds, boolean pack) {
        boolean applied = false;
        for (InstanceRole instanceRole : Iterables.filter(bounds.keySet(), InstanceRole.class)) {
            final Node node = instanceRole.getNotationNode();
            final Integer computedX = bounds.get(instanceRole).x;

            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Location) {
                Location location = (Location) layoutConstraint;
                location.setX(computedX);
            }
            applied = true;
        }
        return applied;
    }

    private boolean layoutFrames(Map<? extends ISequenceElement, Rectangle> bounds, boolean pack) {
        boolean applied = false;
        for (AbstractFrame frame : Iterables.filter(bounds.keySet(), AbstractFrame.class)) {
            Rectangle newBounds = bounds.get(frame);
            Node notationNode = frame.getNotationNode();
            LayoutConstraint layoutConstraint = notationNode.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds && newBounds != null) {
                Bounds b = (Bounds) layoutConstraint;
                b.setWidth(newBounds.width);
                b.setX(newBounds.x);
                applied = true;
            }

            if (frame instanceof CombinedFragment) {
                CombinedFragment cf = (CombinedFragment) frame;
                for (Operand op : cf.getOperands()) {
                    Node opNode = op.getNotationNode();
                    LayoutConstraint opLC = opNode.getLayoutConstraint();
                    if (opLC instanceof Bounds && newBounds != null) {
                        Bounds opBounds = (Bounds) opLC;
                        opBounds.setWidth(newBounds.width);
                        opBounds.setX(0);
                        applied = true;
                    }
                }
            }
        }
        return applied;
    }

    private boolean layoutLostMessageEnds(Map<? extends ISequenceElement, Rectangle> bounds, boolean pack) {
        boolean applied = false;
        for (LostMessageEnd lostMessageEnd : Iterables.filter(bounds.keySet(), LostMessageEnd.class)) {
            final Node node = lostMessageEnd.getNotationNode();
            final Integer computedX = bounds.get(lostMessageEnd).x;

            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Location) {
                Location location = (Location) layoutConstraint;
                location.setX(computedX);
                applied = true;
            }
        }
        return applied;
    }

    private boolean layoutReflexiveMessages(Map<? extends ISequenceElement, Rectangle> bounds, boolean pack) {
        boolean applied = false;
        for (Message msg : Iterables.filter(bounds.keySet(), Message.class)) {
            Bendpoints bendpoints = msg.getNotationEdge().getBendpoints();
            if (msg.isReflective() && bendpoints instanceof RelativeBendpoints) {
                RelativeBendpoints relativeBendpoints = (RelativeBendpoints) bendpoints;
                Iterable<RelativeBendpoint> points = Iterables.filter(relativeBendpoints.getPoints(), RelativeBendpoint.class);
                if (Iterables.size(points) == 4) {
                    RelativeBendpoint p0 = Iterables.get(points, 0);
                    RelativeBendpoint p1 = Iterables.get(points, 1);
                    RelativeBendpoint p2 = Iterables.get(points, 2);
                    RelativeBendpoint p3 = Iterables.get(points, 3);

                    int deltaX = bounds.get(msg).width - p1.getSourceX();
                    RelativeBendpoint newP1 = new RelativeBendpoint(p1.getSourceX() + deltaX, p1.getSourceY(), p1.getTargetX() + deltaX, p1.getTargetY());
                    RelativeBendpoint newP2 = new RelativeBendpoint(p2.getSourceX() + deltaX, p2.getSourceY(), p2.getTargetX() + deltaX, p2.getTargetY());

                    List<RelativeBendpoint> newPoints = Lists.newArrayList();
                    newPoints.add(p0);
                    newPoints.add(newP1);
                    newPoints.add(newP2);
                    newPoints.add(p3);

                    relativeBendpoints.setPoints(newPoints);
                    applied = true;
                }
            }
        }
        return applied;
    }

    @Override
    protected Function<InstanceRole, Integer> getOldPosition() {
        return new Function<InstanceRole, Integer>() {
            public Integer apply(InstanceRole input) {
                return input.getProperLogicalBounds().x;
            }
        };
    }

    @Override
    protected Function<InstanceRole, Integer> getOldFlaggedPosition() {
        return new Function<InstanceRole, Integer>() {
            public Integer apply(InstanceRole input) {
                int oldFlaggedPosition = Integer.MIN_VALUE;
                Rectangle flaggedData = oldFlaggedLayoutData.get(input);
                if (flaggedData != null) {
                    oldFlaggedPosition = flaggedData.x;
                }
                return oldFlaggedPosition;
            }
        };
    }
}
