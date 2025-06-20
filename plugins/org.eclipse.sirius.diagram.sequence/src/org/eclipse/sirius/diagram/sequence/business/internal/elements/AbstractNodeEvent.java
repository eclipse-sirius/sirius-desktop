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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Represents an execution on a lifeline or another parent execution.
 * 
 * @author mporhel, pcdavid, smonnier
 */
public abstract class AbstractNodeEvent extends AbstractSequenceNodeEvent implements ISequenceEvent {

    /**
     * Predicate to filter Frames and Operand from possible new parents of an execution reparent.
     */
    public static final Predicate<ISequenceEvent> NO_REPARENTABLE_EVENTS = new Predicate<ISequenceEvent>() {
        @Override
        public boolean apply(ISequenceEvent input) {
            return input instanceof AbstractFrame || input instanceof State || input instanceof Operand || input instanceof Message;
        }
    };

    /**
     * The visual ID. Same as a normal bordered node.
     * 
     * see org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart. VISUAL_ID
     */
    public static final int VISUAL_ID = 3001;

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing this execution.
     */
    AbstractNodeEvent(Node node) {
        super(node);
        Preconditions.checkArgument(AbstractNodeEvent.notationPredicate().apply(node), Messages.AbstractNodeEvent_nonAbstractNodeEventNode);
    }


    @Override
    public ISequenceEvent getParentEvent() {
        if (CacheHelper.isStructuralCacheEnabled()) {
            ISequenceEvent parentEvent = CacheHelper.getEventToParentEventCache().get(this);
            if (parentEvent != null) {
                return parentEvent;
            }
        }

        ISequenceEvent parent = getHierarchicalParentEvent();
        List<ISequenceEvent> potentialSiblings = parent.getSubEvents();
        if (!potentialSiblings.contains(this)) {
            // look for parentOperand
            parent = getParentOperand().get();
        }

        if (CacheHelper.isStructuralCacheEnabled()) {
            CacheHelper.getEventToParentEventCache().put(this, parent);
        }
        return parent;
    }

    /**
     * Finds the deepest Operand container including the position if existing.
     * 
     * @param verticalPosition
     *            the position where to look for the deepest operand
     * @return the deepest Operand convering this lifeline at this range
     * @see ISequenceEvent#getParentOperand()
     */
    public Option<Operand> getParentOperand(final int verticalPosition) {
        return new ParentOperandFinder(this).getParentOperand(new Range(verticalPosition, verticalPosition));
    }

    /**
     * Finds the deepest Operand container including the position if existing.
     * 
     * @param range
     *            the range where to look for the deepest operand
     * @return the deepest Operand convering this lifeline at this range
     * @see ISequenceEvent#getParentOperand()
     */
    public Option<Operand> getParentOperand(final Range range) {
        return new ParentOperandFinder(this).getParentOperand(range);
    }

    /**
     * Finds the deepest Operand container if existing.
     * 
     * @return the deepest Operand container if existing
     */
    @Override
    public Option<Operand> getParentOperand() {
        if (CacheHelper.isStructuralCacheEnabled()) {
            Option<Operand> parentOperand = CacheHelper.getEventToParentOperandCache().get(this);
            if (parentOperand != null) {
                return parentOperand;
            }
        }

        Option<Operand> parentOperand = new ParentOperandFinder(this).getParentOperand();
        if (CacheHelper.isStructuralCacheEnabled()) {
            CacheHelper.getEventToParentOperandCache().put(this, parentOperand);
        }
        return parentOperand;
    }

    @Override
    public Range getVerticalRange() {
        return new SequenceNodeQuery(getNotationNode()).getVerticalRange();
    }

    @Override
    public void setVerticalRange(Range range) throws IllegalStateException {
        RangeSetter.setVerticalRange(this, range);
    }

    @Override
    public Rectangle getProperLogicalBounds() {
        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            ISequenceEvent parent = getHierarchicalParentEvent();
            Rectangle parentLogicalBounds = parent.getProperLogicalBounds();

            Point location = getProperLogicalLocation(parent, bounds, parentLogicalBounds);
            Dimension size = new Dimension(bounds.getWidth(), bounds.getHeight());
            return new Rectangle(location, size);
        } else {
            throw new RuntimeException();
        }
    }

    private Point getProperLogicalLocation(ISequenceEvent parent, Bounds bounds, Rectangle parentLogicalBounds) {
        int x = parentLogicalBounds.x;
        int y = parentLogicalBounds.y + bounds.getY();

        if (Lifeline.notationPredicate().apply(parent.getNotationView()) || this instanceof State) {
            /*
             * Top-level executions which are directly on a lifeline are horizontally centered on the lifeline.
             */
            Point top = parentLogicalBounds.getTop();
            int width = bounds.getWidth();
            x = top.x - width / 2;
        } else {
            /*
             * Sub-executions horizontally overlap partially their parent execution of
             * IBorderItemOffsets.DEFAULT_OFFSET.width. We can not depend on that type here (it is in a UI plug-in, but
             * we use its value: 8 pixels.
             */
            Point topRight = parentLogicalBounds.getTopRight();
            x = topRight.x - 5;
        }
        return new Point(x, y);
    }
}
