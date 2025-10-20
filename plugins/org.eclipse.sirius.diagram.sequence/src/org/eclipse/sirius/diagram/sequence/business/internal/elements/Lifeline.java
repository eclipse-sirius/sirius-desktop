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

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.business.internal.util.SubEventsHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Common interface for all the elements of a sequence diagram.
 * 
 * @author mporhel
 */
public class Lifeline extends AbstractSequenceNode implements ISequenceEvent {
    /**
     * The visual id.
     * 
     * @see org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart.VISUAL_ID
     */
    public static final int VISUAL_ID = 3001;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents a lifeline.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getExecutionMapping())
                    && InstanceRole.viewpointElementPredicate().apply((DDiagramElement) input.eContainer());
        }
    }

    /**
     * .
     * 
     * @param node
     *            .
     */
    Lifeline(Node node) {
        super(node);
        Preconditions.checkArgument(Lifeline.notationPredicate().apply(node), Messages.Lifeline_nonLifelineNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents a lifeline.
     * 
     * @return a predicate to check whether a GMF View represents a lifeline.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, Lifeline.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents a lifeline.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents a lifeline.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    public InstanceRole getInstanceRole() {
        return ISequenceElementAccessor.getInstanceRole((View) getNotationNode().eContainer()).get();
    }

    @Override
    public Range getVerticalRange() {
        return new SequenceNodeQuery(getNotationNode()).getVerticalRange();
    }

    @Override
    public boolean isLogicallyInstantaneous() {
        return false;
    }

    @Override
    public void setVerticalRange(Range range) throws IllegalStateException {
        RangeSetter.setVerticalRange(this, range);
    }

    /**
     * Returns the0 EOL marker for this lifeline, if any. Note that the mere presence of an EOL does not mean this
     * lifeline is explicitly destroyed, as EOLs can be used just to serve as visual hints of the end of lifelines and
     * convenient resize handles.
     * 
     * @return the EOL marker for this lifeline, if any.
     */
    public Option<EndOfLife> getEndOfLife() {
        for (View child : Iterables.filter(getNotationView().getChildren(), View.class)) {
            if (EndOfLife.notationPredicate().apply(child)) {
                return ISequenceElementAccessor.getEndOfLife(child);
            }
        }
        return Options.newNone();
    }

    @Override
    public Option<Lifeline> getLifeline() {
        return Options.newSome(this);
    }

    @Override
    public Rectangle getProperLogicalBounds() {
        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            Rectangle irBounds = getInstanceRole().getProperLogicalBounds();
            Point bottom = irBounds.getBottom();
            int width = bounds.getWidth();
            return new Rectangle(bottom.x - width / 2, bottom.y, bounds.getWidth(), bounds.getHeight());
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * Lifelines are the root of the events hierarchy and thus have not parent.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public ISequenceEvent getParentEvent() {
        return null;
    }

    @Override
    public List<ISequenceEvent> getSubEvents() {
        return new SubEventsHelper(this).getSubEvents();
    }

    @Override
    public Collection<ISequenceEvent> getEventsToMoveWith() {
        return getSubEvents();
    }

    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range) {
        return new SubEventsHelper(this).canChildOccupy(child, range);
    }

    @Override
    public boolean canChildOccupy(ISequenceEvent child, Range range, List<ISequenceEvent> eventsToIgnore, Collection<Lifeline> lifelines) {
        return new SubEventsHelper(this).canChildOccupy(child, range, eventsToIgnore, lifelines);
    }

    @Override
    public Range getOccupiedRange() {
        return new ISequenceEventQuery(this).getOccupiedRange();
    }

    @Override
    public Range getValidSubEventsRange() {
        Range result = getVerticalRange();
        if (result.width() > 2 * LayoutConstants.EXECUTION_CHILDREN_MARGIN) {
            result = result.shrinked(LayoutConstants.EXECUTION_CHILDREN_MARGIN);
        }
        return result;
    }

    /**
     * Locate the destruction message which destroys the lifeline, if any.
     * 
     * @return the destruction message which destroys the lifeline, if any.
     */
    public Option<Message> getDestructionMessage() {
        Option<EndOfLife> optEOL = getEndOfLife();
        if (optEOL.some()) {
            return optEOL.get().getDestructionMessage();
        } else {
            return Options.newNone();
        }
    }

    /**
     * Tests whether the lifeline is explicitly destroyed by a destruction message, or if it goes until the end of the
     * sequence.
     * 
     * @return <code>true</code> if the lifeline is explicitly destroyed by a destruction message.
     */
    public boolean isExplicitlyDestroyed() {
        Option<EndOfLife> optEOL = getEndOfLife();
        if (optEOL.some()) {
            return optEOL.get().isExplicitelyDestroyed();
        } else {
            return false;
        }
    }

    /**
     * Tests whether the lifeline is explicitly created by a creation message, or if it starts from the beginning of the
     * sequence.
     * 
     * @return <code>true</code> if the lifeline is explicitly created by a creation message.
     */
    public boolean isExplicitlyCreated() {
        InstanceRole opt = getInstanceRole();
        if (opt != null) {
            return opt.isExplicitlyCreated();
        } else {
            return false;
        }
    }

    /**
     * Locate the destruction message which creates the lifeline, if any.
     * 
     * @return the destruction message which creates the lifeline, if any.
     */
    public Option<Message> getCreationMessage() {
        InstanceRole opt = getInstanceRole();
        if (opt != null) {
            return opt.getCreationMessage();
        }
        return Options.newNone();
    }

    @Override
    public Option<Operand> getParentOperand() {
        return getParentOperand(getVerticalRange());
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
     * Finds the deepest Operand container convering the range if existing.
     * 
     * @param range
     *            the range where to look for the deepest operand
     * @return the deepest Operand convering this lifeline at this range
     * @see ISequenceEvent#getParentOperand()
     */
    public Option<Operand> getParentOperand(final Range range) {
        return new ParentOperandFinder(this).getParentOperand(range);
    }

    @Override
    public ISequenceEvent getHierarchicalParentEvent() {
        return null;
    }

    /**
     * Investigate recursively sub events to find all covering interaction uses.
     * 
     * @return all interaction uses covering this lifeline recursively
     */
    public Collection<InteractionUse> getAllCoveringInteractionUses() {
        Predicate<InteractionUse> interactionUseCoveringLifeline = new Predicate<InteractionUse>() {

            @Override
            public boolean apply(InteractionUse input) {
                return input.computeCoveredLifelines().contains(Lifeline.this);
            }
        };
        return Lists.newArrayList(Iterables.filter(getDiagram().getAllInteractionUses(), interactionUseCoveringLifeline));
    }
}
