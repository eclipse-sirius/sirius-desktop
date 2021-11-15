/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
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

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This class is responsible to check whether a request on an interaction use should be accepted (i.e. it would produce
 * a well-formed diagram). While doing the validation, it also stores all the relevant information required to actually
 * perform the interaction properly.
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
    protected final Set<ISequenceEvent> movedElements = new HashSet<>();

    /**
     * Not in moved elements.
     */
    protected final Predicate<ISequenceEvent> unmoved = Predicates.not(Predicates.in(movedElements));

    private boolean initialized;

    private final Collection<Integer> invalidPositions = new ArrayList<>();

    private Predicate<Object> unMove = Predicates.instanceOf(Lifeline.class);

    private Predicate<Object> invalidParents;

    private Function<ISequenceEvent, Range> futureRangeFunction = new Function<ISequenceEvent, Range>() {

        @Override
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
     *            the interaction use or combined fragment which will be resized.
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
     * Return the validation status. Validate the request result in the first call only.
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
     * Performs all the computations required to validate the resizing, and stores any important information which will
     * be useful to actually execute the resize if it is valid, like for example avoid contact with siblings.
     */
    protected void validate() {
        valid = checkAndComputeRanges();
        if (valid) {
            Collection<Lifeline> coveredLifelines = frame.computeCoveredLifelines();
            Collection<ISequenceEvent> finalParents = getFinalParentsWithAutoExpand(coveredLifelines);

            Collection<ISequenceEvent> movableParents = Lists.newArrayList(Iterables.filter(finalParents, Predicates.not(unMove)));
            Collection<ISequenceEvent> fixedParents = Lists.newArrayList(Iterables.filter(finalParents, unMove));
            if (movableParents.isEmpty() || !movedElements.containsAll(movableParents)) {

                valid = valid && Iterables.isEmpty(Iterables.filter(finalParents, invalidParents));
                valid = valid && checkParentOperands(finalParents, coveredLifelines);
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

    private boolean checkParentOperands(Collection<ISequenceEvent> finalParents, Collection<Lifeline> coveredLifelines) {
        Iterable<Operand> finalOperandParents = Iterables.filter(finalParents, Operand.class);

        // Step1 : check that parentOperands contains at most one Operand.
        boolean checked;
        if (Iterables.size(finalOperandParents) > 1) {
            // If two or more Operands are detected, this means that after this move/resize, the current frame would
            // not be fully included in one of the parents.
            checked = false;
        } else {
            // We need to check which Operand might contained the current Frame after move/resize.
            // finalOperandParents.size == 0
            // No reference parent operand directly in finalParents.
            // finalOperandParents.size == 1
            // If one operand is found in the parents, it might have other co-parents on some lifelines (executions
            // which are sub events or parent events of the moved/resized frame).
            // We must ensure that the found potential parent Operand or the parent Operand of the parent executions is
            // compatible with the current move/resize : it must be unique (or null) and able to contain the current
            // frame (compatible coverage).
            checked = true;
            Operand commonParentOperand = null;
            boolean operandProvided = false;

            // Step 2: check that all final parents belongs to the same operand.
            // if operand is not null : it must be the parent operand of all other final parent.
            // if operand is null : all parent operands must have the same parent operand
            for (ISequenceEvent finalParent : finalParents) {
                Operand parentOperand;
                if (finalParent instanceof Operand) {
                    parentOperand = (Operand) finalParent;
                } else {
                    parentOperand = finalParent.getParentOperand().get();
                }

                if (!operandProvided) {
                    // Operand of the first final parent.
                    operandProvided = true;
                    commonParentOperand = parentOperand;
                } else {
                    if (commonParentOperand != null && commonParentOperand.equals(parentOperand)) {
                        // Same final parent operand
                        checked = true;
                    } else if (commonParentOperand == null && parentOperand == null) {
                        // Common parent operand is still null
                        checked = true;
                    } else {
                        // Several final parent operands found (Op_1 vs Op_2 or null vs Op_1)
                        checked = false;
                        break;
                    }
                }
            }

            if (checked && commonParentOperand != null) {
                checked = commonParentOperand.computeCoveredLifelines().containsAll(coveredLifelines);
            }
        }
        return checked;
    }

    private Collection<ISequenceEvent> getFinalParentsWithAutoExpand(Collection<Lifeline> coveredLifelines) {
        Collection<ISequenceEvent> finalParentsWithAutoExpand = new ArrayList<>();
        Collection<ISequenceEvent> finalParents = getFinalParents(coveredLifelines);
        for (ISequenceEvent localParent : finalParents) {
            // check the need of space expansion
            if (localParent != null) {
                if (!movedElements.contains(localParent) && !localParent.canChildOccupy(frame, finalRange, new ArrayList<ISequenceEvent>(movedElements), coveredLifelines)) {
                    expansionZone = computeExpansionZone();
                }

                finalParentsWithAutoExpand.add(localParent);
            }
        }

        return finalParentsWithAutoExpand;
    }

    /**
     * Computes, checks and stores the initial and final range of the interaction use if the resize is performed.
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
     * Resizing an interaction use can not change which parents it is on, and can not have any impact on that parents's
     * ranges, so the final range of the interaction use after the resize must be strictly included in the ranges of the
     * parents.
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
             * We make two tests separately so that is is easier when debugging to determine which of the conditions
             * went wrong, if any.
             */
            boolean interactionInRange = parentRange.includes(finalRange.grown(LayoutConstants.EXECUTION_CHILDREN_MARGIN));
            checked = checked && interactionInRange;
        }

        return checked;
    }

    /**
     * Get final parents event after application of the current interaction.
     * 
     * @param coveredLifelines
     *            the lifeline covered by the current frame.
     * 
     * @return final parents.
     */
    protected abstract Collection<ISequenceEvent> getFinalParents(Collection<Lifeline> coveredLifelines);

    private boolean checkLocalSiblings(Collection<ISequenceEvent> finalParents) {
        boolean okForSiblings = true;
        for (ISequenceEvent localParent : finalParents) {
            for (ISequenceEvent localSibling : Iterables.filter(localParent.getSubEvents(), unmoved)) {
                if (frame.equals(localSibling) || finalParents.contains(localSibling)) {
                    // Frame is moved : to not check it.
                    // Do not consider elements identified as final parents as siblings. By construction, their range
                    // will intersects with the final range of the current frame.
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
     *            the host frame
     * @return a validator.
     */
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
