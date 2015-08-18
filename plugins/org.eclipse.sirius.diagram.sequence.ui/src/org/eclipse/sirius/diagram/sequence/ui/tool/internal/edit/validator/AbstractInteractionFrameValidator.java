/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * This class is responsible to check whether a request on an interaction use
 * should be accepted (i.e. it would produce a well-formed diagram). While doing
 * the validation, it also stores all the relevant information required to
 * actually perform the interaction properly.
 * 
 * @author mporhel
 */
public abstract class AbstractInteractionFrameValidator {
    private static final String FRAME_RESIZE_VALIDATOR = "org.eclipse.sirius.sequence.resize.frame.validator"; //$NON-NLS-1$

    /**
     * Expansion zone.
     */
    protected Range expansionZone = Range.emptyRange();

    /**
     * Current interaction use or combined fragment.
     */
    protected final AbstractFrame frame;

    /**
     * Final Range of the current interaction use.
     */
    protected Range finalRange;

    /**
     * Initial Range of the current interaction use.
     */
    protected Range initialRange;

    /**
     * The default interaction use/combined fragment height.
     */
    protected int defaultFrameHeight;

    /**
     * Validation status.
     */
    protected boolean valid = true;

    /**
     * Other moved elements.
     */
    protected final Set<ISequenceEvent> movedElements = Sets.newHashSet();

    /**
     * Not in moved elements.
     */
    protected final Predicate<ISequenceEvent> unmoved = Predicates.not(Predicates.in(movedElements));

    private boolean initialized;

    private final Collection<Integer> invalidPositions = Lists.newArrayList();

    private Predicate<Object> unMove = Predicates.instanceOf(Lifeline.class);

    private Predicate<Object> invalidParents;

    private Function<ISequenceEvent, Range> futureRangeFunction = new Function<ISequenceEvent, Range>() {

        public Range apply(ISequenceEvent from) {
            Range range = from.getVerticalRange();
            if (frame.equals(from)) {
                range = finalRange;
            } else if (expansionZone != null && !expansionZone.isEmpty()) {
                if (range.includes(expansionZone.getLowerBound())) {
                    range = new Range(range.getLowerBound(), range.getUpperBound() + expansionZone.width());
                } else if (range.getLowerBound() >= expansionZone.getLowerBound()) {
                    range = range.shifted(expansionZone.width());
                }
            }

            return range;
        }
    };

    /**
     * Allow to query the request.
     */
    private final RequestQuery requestQuery;

    /**
     * Constructor.
     * 
     * @param frame
     *            the interaction use or combined fragment which will be
     *            resized.
     * @param requestQuery
     *            a query on the request targeting the execution.
     */
    public AbstractInteractionFrameValidator(AbstractFrame frame, RequestQuery requestQuery) {
        this.frame = frame;
        this.requestQuery = requestQuery;
        this.valid = false;

        this.invalidParents = Predicates.or(Predicates.instanceOf(AbstractFrame.class), Predicates.instanceOf(State.class));

    }

    /**
     * Return the validation status. Validate the request result in the first
     * call only.
     * 
     * @return the validation status.
     */
    public final boolean isValid() {
        if (!initialized) {
            validate();
            initialized = true;
        }
        return valid;
    }

    public Range getFinalRange() {
        return finalRange;
    }

    /**
     * Performs all the computations required to validate the resizing, and
     * stores any important information which will be useful to actually execute
     * the resize if it is valid, like for example avoid contact with siblings.
     */
    protected void validate() {
        valid = checkAndComputeRanges();
        if (valid) {
            Collection<ISequenceEvent> finalParents = getFinalParentsWithAutoExpand();

            Collection<ISequenceEvent> movableParents = Lists.newArrayList(Iterables.filter(finalParents, Predicates.not(unMove)));
            Collection<ISequenceEvent> fixedParents = Lists.newArrayList(Iterables.filter(finalParents, unMove));
            if (movableParents.isEmpty() || !movedElements.containsAll(movableParents)) {

                valid = valid && Iterables.isEmpty(Iterables.filter(finalParents, invalidParents));
                valid = valid && (!Iterables.any(finalParents, Predicates.instanceOf(Operand.class)) || finalParents.size() == 1);
                valid = valid && checkFinalRangeStrictlyIncludedInParents(movableParents);
                valid = valid && checkLocalSiblings(movableParents);
            }
            valid = valid && checkFinalRangeStrictlyIncludedInParents(fixedParents);
            valid = valid && checkLocalSiblings(fixedParents);
        }

        if (getRequestQuery().isResize()) {
            valid = valid && checkGlobalPositions();
        }
    }

    private Collection<ISequenceEvent> getFinalParentsWithAutoExpand() {
        Collection<ISequenceEvent> finalParentsWithAutoExpand = Lists.newArrayList();
        Collection<ISequenceEvent> finalParents = getFinalParents();
        Collection<Lifeline> coveredLifelines = frame.computeCoveredLifelines();
        for (ISequenceEvent localParent : finalParents) {
            // check the need of space expansion
            if (localParent != null) {
                if (!movedElements.contains(localParent) && !localParent.canChildOccupy(frame, finalRange, Lists.newArrayList(movedElements), coveredLifelines)) {
                    expansionZone = computeExpansionZone();
                }

                finalParentsWithAutoExpand.add(localParent);
            }
        }

        return finalParentsWithAutoExpand;
    }

    /**
     * Computes, checks and stores the initial and final range of the
     * interaction use if the resize is performed.
     */
    private boolean checkAndComputeRanges() {
        // Proper range
        initialRange = frame.getVerticalRange();
        Rectangle newBounds = getResizedBounds(new Rectangle(0, initialRange.getLowerBound(), 0, initialRange.width()));

        if (newBounds.height < defaultFrameHeight) {
            finalRange = initialRange;
            return false;
        }

        finalRange = RangeHelper.verticalRange(newBounds);
        return true;
    }

    /**
     * Resizing an interaction use can not change which parents it is on, and
     * can not have any impact on that parents's ranges, so the final range of
     * the interaction use after the resize must be strictly included in the
     * ranges of the parents.
     */
    private boolean checkFinalRangeStrictlyIncludedInParents(Collection<ISequenceEvent> parentEvents) {
        boolean checked = true;

        Iterable<ISequenceEvent> unMovedParents = Iterables.filter(parentEvents, unmoved);
        Iterator<ISequenceEvent> iterator = unMovedParents.iterator();
        while (checked && iterator.hasNext()) {
            ISequenceEvent parent = iterator.next();
            Range parentRange = parent.getVerticalRange();

            if (expansionZone != null && !expansionZone.isEmpty()) {
                parentRange = new Range(parentRange.getLowerBound(), parentRange.getUpperBound() + expansionZone.width());
            }
            /*
             * We make two tests separately so that is is easier when debugging
             * to determine which of the conditions went wrong, if any.
             */
            boolean interactionInRange = parentRange.includes(finalRange.grown(LayoutConstants.EXECUTION_CHILDREN_MARGIN));
            checked = checked && interactionInRange;
        }

        return checked;
    }

    /**
     * Get final parents event after application of the current interaction.
     * 
     * @return final parents.
     */
    protected abstract Collection<ISequenceEvent> getFinalParents();

    private boolean checkLocalSiblings(Collection<ISequenceEvent> finalParents) {
        boolean okForSiblings = true;
        for (ISequenceEvent localParent : finalParents) {
            for (ISequenceEvent localSibling : Iterables.filter(localParent.getSubEvents(), unmoved)) {
                if (frame.equals(localSibling)) {
                    continue;
                }

                okForSiblings = checkSibling(localSibling);
                if (!okForSiblings) {
                    break;
                }
            }
            if (!okForSiblings) {
                break;
            }
        }
        return okForSiblings;
    }

    private boolean checkSibling(ISequenceEvent sibling) {
        Range siblingRange = sibling.getVerticalRange();

        if (canExpand()) {
            if (expansionZone != null && !expansionZone.isEmpty()) {
                siblingRange = getExpandedRange(siblingRange);
            } else if (siblingRange.intersects(finalRange)) {
                expansionZone = computeExpansionZone();
                siblingRange = getExpandedRange(siblingRange);
            }
            // Uncomment to avoid forbidden feedback between resize and resize +
            // auto-expand
            // return !siblingRange.intersects(finalRange);
        }
        return !siblingRange.intersects(finalRange);
    }

    private Range getExpandedRange(Range siblingRange) {
        if (expansionZone != null && !expansionZone.isEmpty() && siblingRange.intersects(expansionZone)) {
            return siblingRange.shifted(expansionZone.width());
        }
        return siblingRange;
    }

    private Rectangle getResizedBounds(Rectangle bounds) {
        return getRequestQuery().getLogicalTransformedRectangle(bounds);
    }

    /**
     * Get the expansion zone requested to validate the move.
     * 
     * @return an expansion zone.
     */
    public Range getExpansionZone() {
        return canExpand() && expansionZone != null ? expansionZone : Range.emptyRange();
    }

    /**
     * Check that the current validator handles auto-expand.
     * 
     * @return true if the validator supports the autoexpand.
     */
    protected abstract boolean canExpand();

    /**
     * Compute the allowed expansion zone.
     * 
     * @return the computed expansion zone.
     */
    protected abstract Range computeExpansionZone();

    /**
     * Other moved elements.
     * 
     * @param otherMovedElements
     *            the other moved elements.
     */
    public void setMovedElements(Collection<ISequenceEvent> otherMovedElements) {
        if (otherMovedElements != null && !otherMovedElements.isEmpty()) {
            movedElements.addAll(otherMovedElements);
        }
    }

    /**
     * Get the validator from the request extended data or a new one.
     * 
     * @param cbr
     *            the current resize request.
     * @param host
     *            the host execution
     * @return a validator.
     */
    @SuppressWarnings("unchecked")
    public static AbstractInteractionFrameValidator getOrCreateResizeValidator(ChangeBoundsRequest cbr, AbstractFrame host) {
        RequestQuery requestQuery = new RequestQuery(cbr);
        Preconditions.checkArgument(requestQuery.isResize());
        AbstractInteractionFrameValidator validator = null;
        Object object = cbr.getExtendedData().get(FRAME_RESIZE_VALIDATOR);
        if (object instanceof AbstractInteractionFrameValidator) {
            validator = (AbstractInteractionFrameValidator) object;
            if (!validator.getRequestQuery().getLogicalDelta().equals(requestQuery.getLogicalDelta())) {
                validator = null;
            }
        }

        if (validator == null && requestQuery.isResize()) {
            if (host instanceof CombinedFragment) {
                validator = new CombinedFragmentResizeValidator((CombinedFragment) host, requestQuery);
            } else if (host instanceof InteractionUse) {
                validator = new InteractionUseResizeValidator((InteractionUse) host, requestQuery);
            }
            cbr.getExtendedData().put(FRAME_RESIZE_VALIDATOR, validator);
        }
        return validator;
    }

    public Collection<Integer> getInvalidPositions() {
        return invalidPositions;
    }

    private boolean checkGlobalPositions() {
        boolean safeMove = true;
        invalidPositions.addAll(new PositionsChecker(frame.getDiagram(), futureRangeFunction).getInvalidPositions());
        safeMove = invalidPositions.isEmpty();
        return safeMove;
    }

    public RequestQuery getRequestQuery() {
        return requestQuery;
    }
}
