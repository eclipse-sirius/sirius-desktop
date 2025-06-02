/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractNodeEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceMessageViewQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.Iterables;

/**
 * .
 * 
 * @author mporhel
 * 
 */
public final class RangeSetter {

    /**
     * Avoid instanticiation.
     */
    private RangeSetter() {
        // Do nothing.
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange(Range)} for lifelines and executions.
     * Assumes the {@link ISequenceEventEditPart} is a Node.
     * 
     * @param self
     *            the (root) execution edit part.
     * @param range
     *            the vertical range of the given sequence event.
     */
    public static void setVerticalRange(AbstractNodeEvent self, Range range) {
        CacheHelper.clearRangeDependantCaches();

        Range oldRange = self.getVerticalRange();
        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = range.width();

        RangeSetter.setVerticalRange(self.getNotationNode(), deltaY, size);
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange(Range)} for states. Assumes the
     * {@link ISequenceEventEditPart} is a Node.
     * 
     * @param self
     *            the state edit part.
     * @param range
     *            the vertical range of the given sequence event.
     */
    public static void setVerticalRange(State self, Range range) {
        CacheHelper.clearRangeDependantCaches();

        Range oldRange = self.getVerticalRange();
        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = range.width();

        RangeSetter.setVerticalRange(self.getNotationNode(), deltaY, size);
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange(Range)} for interaction uses. Assumes the
     * {@link ISequenceEventEditPart} is a Node.
     * 
     * @param self
     *            the InteractionUse.
     * @param range
     *            the vertical range of the given sequence event.
     */
    public static void setVerticalRange(InteractionUse self, Range range) {
        CacheHelper.clearRangeDependantCaches();

        Range oldRange = self.getVerticalRange();
        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = range.width();

        RangeSetter.setVerticalRange(self.getNotationNode(), deltaY, size);
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange(Range)} for combined fragments. Assumes
     * the {@link ISequenceEventEditPart} is a Node.
     * 
     * @param self
     *            the CombinedFragment.
     * @param range
     *            the vertical range of the given sequence event.
     */
    public static void setVerticalRange(CombinedFragment self, Range range) {
        CacheHelper.clearRangeDependantCaches();

        Range oldRange = self.getVerticalRange();
        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = range.width();

        RangeSetter.setVerticalRange(self.getNotationNode(), deltaY, size);
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange(Range)} for combined fragments. Assumes
     * the {@link ISequenceEventEditPart} is a Node.
     * 
     * @param self
     *            the CombinedFragment.
     * @param range
     *            the vertical range of the given sequence event.
     */
    public static void setVerticalRange(Operand self, Range range) {
        CacheHelper.clearRangeDependantCaches();

        Range oldRange = self.getVerticalRange();
        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = range.width();

        RangeSetter.setVerticalRange(self.getNotationNode(), deltaY, size);
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange(Range)} for lifelines and executions.
     * Assumes the {@link ISequenceEventEditPart} is a Node.
     * 
     * @param self
     *            the (root) execution edit part.
     * @param range
     *            the vertical range of the given sequence event.
     */
    public static void setVerticalRange(Lifeline self, Range range) {
        CacheHelper.clearRangeDependantCaches();

        InstanceRole instanceRole = self.getInstanceRole();
        Rectangle irepBounds = instanceRole.getBounds();
        Range irRange = new Range(range.getLowerBound() - irepBounds.height, range.getUpperBound());
        RangeSetter.setVerticalRange(instanceRole, irRange);

        Range oldRange = self.getVerticalRange();
        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = range.width();
        RangeSetter.setVerticalRange(self.getNotationNode(), deltaY, size);

        Option<EndOfLife> eol = self.getEndOfLife();
        if (eol.some()) {
            EndOfLife endOfLife = eol.get();
            Rectangle eolBounds = endOfLife.getBounds();
            Range eolRange = new Range(range.getUpperBound(), range.getUpperBound() + eolBounds.height);
            RangeSetter.setVerticalRange(endOfLife, eolRange);
        }
    }

    /**
     * .
     * 
     * @param self
     *            .
     * @param range
     *            .
     */
    private static void setVerticalRange(InstanceRole self, Range range) {
        Node node = self.getNotationNode();
        Range oldRange = new SequenceNodeQuery(node).getVerticalRange();

        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = oldRange.width();

        RangeSetter.setVerticalRange(node, deltaY, size);
    }

    /**
     * .
     * 
     * @param self
     *            .
     * @param range
     *            .
     */
    private static void setVerticalRange(EndOfLife self, Range range) {
        Node node = self.getNotationNode();
        Range oldRange = new SequenceNodeQuery(node).getVerticalRange();

        int deltaY = range.getLowerBound() - oldRange.getLowerBound();
        int size = oldRange.width();

        RangeSetter.setVerticalRange(node, deltaY, size);
    }

    /**
     * .
     * 
     * @param node
     *            .
     * @param deltaY
     *            .
     * @param size
     *            .
     */
    private static void setVerticalRange(Node node, int deltaY, int size) {
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        int realDeltaY = deltaY;
        // if (realDeltaY > 0) {
        // realDeltaY -= IBorderItemOffsets.DEFAULT_OFFSET.height;
        // }else if(realDeltaY<0){
        // realDeltaY += IBorderItemOffsets.DEFAULT_OFFSET.height;
        // }
        if (layoutConstraint instanceof Location && realDeltaY != 0) {
            Location location = (Location) layoutConstraint;
            location.setY(location.getY() + realDeltaY);
        }
        if (layoutConstraint instanceof Size) {
            Size s = (Size) layoutConstraint;
            if (s.getHeight() != size) {
                s.setHeight(size);
            }
        }
    }

    /**
     * Common implementation of {@link ISequenceEventEditPart#setVerticalRange(Range)} for lifelines and executions.
     * Assumes the {@link ISequenceEventEditPart} is a Node.
     * 
     * @param self
     *            the (root) execution edit part.
     * @param range
     *            the vertical range of the given sequence event.
     */
    public static void setVerticalRange(Message self, Range range) {
        CacheHelper.clearRangeDependantCaches();

        RangeSetter.handlePotentialLostEndOrGate(self.getSourceElement(), range.getLowerBound());
        RangeSetter.handlePotentialLostEndOrGate(self.getTargetElement(), range.getUpperBound());

        Edge edge = self.getNotationEdge();
        SequenceMessageViewQuery query = new SequenceMessageViewQuery(edge);

        int firstPointVerticalPosition = query.getFirstPointVerticalPosition(true);
        int lastPointVerticalPosition = query.getLastPointVerticalPosition(true);

        int firstPointVerticalPositionFromTarget = query.getFirstPointVerticalPosition(false);
        int lastPointVerticalPositionFromTarget = query.getLastPointVerticalPosition(false);

        int deltaFirstPointY = range.getLowerBound() - firstPointVerticalPosition;
        int deltaFirstPointYFromTarget = range.getLowerBound() - firstPointVerticalPositionFromTarget;

        int deltaLastPointY = range.getUpperBound() - lastPointVerticalPosition;
        int deltaLastPointYFromTarget = range.getUpperBound() - lastPointVerticalPositionFromTarget;

        RangeSetter.updateBendpoints(edge, firstPointVerticalPosition, lastPointVerticalPosition, deltaFirstPointY, deltaFirstPointYFromTarget, deltaLastPointY, deltaLastPointYFromTarget);
    }

    private static void handlePotentialLostEndOrGate(ISequenceNode end, int targetLogicalMiddleValue) {
        if (end instanceof LostMessageEnd) {
            Node node = end.getNotationNode();
            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                int middleValue = bounds.getY() + bounds.getHeight() / 2;
                RangeSetter.setVerticalRange(node, targetLogicalMiddleValue - middleValue, bounds.getHeight());
            }
        } else if (end instanceof Gate g) {
            ISequenceElement hierarchicalParent = g.getHierarchicalParent();
            int targetMiddle = targetLogicalMiddleValue - hierarchicalParent.getProperLogicalBounds().y;
            
            Node node = end.getNotationNode();
            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                int middleValue = bounds.getY() + bounds.getHeight() / 2;
                RangeSetter.setVerticalRange(node, targetMiddle - middleValue, bounds.getHeight());
            }
        }
    }

    private static void updateBendpoints(Edge edge, int firstPointVerticalPosition, int lastPointVerticalPosition, int deltaFirstPointY, int deltaFirstPointYFromTarget, int deltaLastPointY,
            int deltaLastPointYFromTarget) {
        List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();
        RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();

        Iterable<RelativeBendpoint> relPoints = Iterables.filter(bendpoints.getPoints(), RelativeBendpoint.class);

        for (int i = 0; i < Iterables.size(relPoints) / 2; i++) {
            RelativeBendpoint p = Iterables.get(relPoints, i);
            RangeSetter.addBendpoint(newBendpoints, p, deltaFirstPointY, deltaFirstPointYFromTarget);
        }

        for (int i = Iterables.size(relPoints) / 2; i < Iterables.size(relPoints); i++) {
            RelativeBendpoint p = Iterables.get(relPoints, i);
            RangeSetter.addBendpoint(newBendpoints, p, deltaLastPointY, deltaLastPointYFromTarget);
        }

        if (!BendpointsHelper.areSameBendpoints(bendpoints.getPoints(), newBendpoints)) {
            bendpoints.setPoints(newBendpoints);
        }
    }

    private static void addBendpoint(List<RelativeBendpoint> newBendpoints, RelativeBendpoint p, double deltaSourceY, double deltaTargetY) {
        newBendpoints.add(new RelativeBendpoint(p.getSourceX(), (int) (p.getSourceY() + deltaSourceY), p.getTargetX(), (int) (p.getTargetY() + deltaTargetY)));
    }
}
