/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES and others.
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
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.query.ISequenceEventQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceNodeQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.RangeSetter;
import org.eclipse.sirius.diagram.sequence.business.internal.util.SubEventsHelper;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Represents an operand inside a combined fragment.
 * 
 * @author pcdavid
 */
public class Operand extends AbstractSequenceNode implements ISequenceEvent {
    /**
     * The visual ID.
     * 
     * @see DNodeContainer2EditPart.VISUAL_ID.
     */
    public static final int VISUAL_ID = 3008;

    /**
     * The visual ID of the compartment contained by the operand.
     * 
     * see org.eclipse.sirius.diagram.internal.edit.parts. DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID
     */
    public static final int COMPARTMENT_VISUAL_ID = 7002;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getOperandMapping());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing the operand.
     */
    Operand(Node node) {
        super(node);
        Preconditions.checkArgument(Operand.notationPredicate().apply(node), Messages.Operand_nonOperandNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an execution.
     * 
     * @return a predicate to check whether a GMF View represents an execution.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, Operand.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a GMF View represents an combined fragment compartment.
     * 
     * @return a predicate to check whether a GMF View represents an combined fragment compartment.
     */
    public static Predicate<View> compartmentNotationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), COMPARTMENT_VISUAL_ID, Operand.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an execution.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    /**
     * Operands are not associated to a particular lifeline.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Option<Lifeline> getLifeline() {
        return Options.newNone();
    }

    @Override
    public Rectangle getProperLogicalBounds() {
        Rectangle cfBounds = getCombinedFragment().getProperLogicalBounds();
        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            return new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()).getTranslated(cfBounds.getLocation());
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public ISequenceEvent getParentEvent() {
        return getCombinedFragment();
    }

    @Override
    public ISequenceEvent getHierarchicalParentEvent() {
        return getCombinedFragment();
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
    public Range getVerticalRange() {
        // Rectangle logicalBounds = getProperLogicalBounds();
        // return new Range(logicalBounds.y, logicalBounds.bottom());
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
     * Finds the parent Combined fragment.
     * 
     * @return the parent Combined fragment.
     */
    public CombinedFragment getCombinedFragment() {
        CombinedFragment combinedFragment = null;
        if (CacheHelper.isStructuralCacheEnabled()) {
            combinedFragment = CacheHelper.getOperandToCombinedFragmentCache().get(this);
        }
        if (combinedFragment == null) {
            EObject viewContainer = null;
            try {
                viewContainer = this.view.eContainer();
            } catch (IllegalStateException e) {
                if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
                    // Nothing to log here, this can happen if the resource is not accessible anymore (distant
                    // resource).
                } else {
                    throw e;
                }
            }
            if (viewContainer instanceof View) {
                View parentView = (View) viewContainer;
                Option<CombinedFragment> parentElement = ISequenceElementAccessor.getCombinedFragment(parentView);
                // The parent should be the compartment of the Combined Fragment
                if (parentElement.some()) {
                    combinedFragment = parentElement.get();
                } else {
                    // The grand parent should be the Combined Fragment we are
                    // looking for
                    View grandParentView = (View) viewContainer.eContainer();
                    Option<CombinedFragment> grandparentElement = ISequenceElementAccessor.getCombinedFragment(grandParentView);
                    if (grandparentElement.some()) {
                        combinedFragment = grandparentElement.get();
                    }
                }
                if (combinedFragment != null) {
                    if (CacheHelper.isStructuralCacheEnabled()) {
                        CacheHelper.getOperandToCombinedFragmentCache().put(this, combinedFragment);
                    }
                }
            }
            if (combinedFragment == null) {
                throw new RuntimeException(MessageFormat.format(Messages.Operand_invalidOperandContext, this));
            }
        }
        return combinedFragment;
    }

    /**
     * Finds the index of this operand among the parent combined fragment.
     * 
     * @return the index of this operand among the parent combined fragment.
     */
    public int getIndex() {
        CombinedFragment parentCombinedFragment = getCombinedFragment();
        return parentCombinedFragment.getIndexOfOperand(this);
    }

    /**
     * Check if this operand is the last in the parent {@link CombinedFragment}.
     * 
     * @return if this operand is the last in the parent {@link CombinedFragment}.
     */
    public boolean isLastOperand() {
        return getIndex() == getCombinedFragment().getOperands().size() - 1;
    }

    /**
     * Check if this operand is the first in the parent {@link CombinedFragment} .
     * 
     * @return if this operand is the first in the parent {@link CombinedFragment}.
     */
    public boolean isFirstOperand() {
        return getIndex() == 0;
    }

    /**
     * Finds the following operand.
     * 
     * @return the following operand if existing, Options.newNone() otherwise
     */
    public Option<Operand> getFollowingOperand() {
        CombinedFragment combinedFragment = getCombinedFragment();
        return combinedFragment.getOperand(combinedFragment.getIndexOfOperand(this) + 1);
    }

    /**
     * Finds the previous operand.
     * 
     * @return the previous operand if existing, Options.newNone() otherwise
     */
    public Option<Operand> getPreviousOperand() {
        CombinedFragment combinedFragment = getCombinedFragment();
        return combinedFragment.getOperand(combinedFragment.getIndexOfOperand(this) - 1);
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
        return getVerticalRange().shrinked(LayoutConstants.EXECUTION_CHILDREN_MARGIN);
    }

    /**
     * Get the covered lifelines.
     * 
     * @return the covered lifelines.
     */
    public Collection<Lifeline> computeCoveredLifelines() {
        return getCombinedFragment().computeCoveredLifelines();
    }

    /**
     * Returns the last event end (in semantic order) which is contained inside this operand, excluding the operand's
     * finishing end. If the operand is empty, this is the starting end of the operand itself.
     * 
     * @return the last event end which is contained inside this operand.
     */
    public EventEnd getLastContainedEventEnd() {
        List<ISequenceEvent> subEvents = getSubEvents();
        if (subEvents.isEmpty()) {
            List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(this);
            assert !ends.isEmpty();
            return ends.get(0);
        } else {
            ISequenceEvent lastEvent = subEvents.get(subEvents.size() - 1);
            List<EventEnd> ends = EventEndHelper.findEndsFromSemanticOrdering(lastEvent);
            assert ends.size() == 2;
            return ends.get(1);
        }
    }

    @Override
    public Option<Operand> getParentOperand() {
        return getCombinedFragment().getParentOperand();
    }

}
