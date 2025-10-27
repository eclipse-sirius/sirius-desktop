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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Represents an execution on a lifeline or another parent execution.
 * 
 * @author mporhel, pcdavid, smonnier
 */
public abstract class AbstractNodeEvent extends AbstractSequenceNode implements ISequenceEvent {

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
     * Predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return (AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getExecutionMapping())
                    || AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getStateMapping()))
                    && !InstanceRole.viewpointElementPredicate().apply((DDiagramElement) input.eContainer());
        }
    }

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

    /**
     * Returns a predicate to check whether a GMF View represents an execution.
     * 
     * @return a predicate to check whether a GMF View represents an execution.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, AbstractNodeEvent.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an execution.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
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

    /**
     * Returns all the messages linked to one of the sides (top or bottom) of this execution. The resulting list can be:
     * <ul>
     * <li>empty, meaning there is no messages linked to any of this execution's sides.</li>
     * <li>a list with a single element, in which case that element is the message linked to the top side of this
     * execution.</li>
     * <li>a list with two element, in which case the first element is the message linked to the top side of this
     * execution and the second is the message linked to the bottom side.</li>
     * </ul>
     * Note that the case where there is only one linked message and it is linked to the bottom side is not supported.
     * 
     * @return all the messages linked to one of the sides (top or bottom) of this execution.
     */
    public abstract List<Message> getLinkedMessages();

    /**
     * Returns the hierarchical parent event of this event (from a Notation point of view), if any.
     * 
     * @param noFoundParentExceptionMessage
     *            message of the RuntimeException which will be created if no parent is found.
     * 
     * @return the hierarchical parent event of this event, if any.
     */
    protected ISequenceEvent getHierarchicalParentEvent(String noFoundParentExceptionMessage) {
        if (CacheHelper.isStructuralCacheEnabled()) {
            ISequenceEvent hierarchicalParent = CacheHelper.getAbstractNodeEventToHierarchicalParentCache().get(this);
            if (hierarchicalParent != null) {
                return hierarchicalParent;
            }
        }

        EObject viewContainer = this.view.eContainer();
        if (viewContainer instanceof View) {
            View parentView = (View) viewContainer;
            Option<ISequenceEvent> parentElement = ISequenceElementAccessor.getISequenceEvent(parentView);
            if (parentElement.some()) {
                if (CacheHelper.isStructuralCacheEnabled()) {
                    CacheHelper.getAbstractNodeEventToHierarchicalParentCache().put(this, parentElement.get());
                }

                return parentElement.get();
            }
        }
        throw new RuntimeException(MessageFormat.format(noFoundParentExceptionMessage, this));
    }
}
