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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.AbstractSequenceInteractionValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.RangeGuide;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.draw2d.figure.HorizontalGuide;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swt.graphics.Color;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A builder for complex sequence move feedback.
 * 
 * @author mporhel
 */
public class SequenceInteractionFeedBackBuilder {

    /**
     * The color to use for the horizontal feedback rules shown when moving/resizing an ISequencEvent.
     */
    public static final Color ISE_FEEDBACK_COLOR = ColorConstants.lightGray;

    /**
     * The color to use for the horizontal feedback rules shown when an expansion is triggered.
     */
    public static final Color EXPANSION_FEEDBACK_COLOR = ColorConstants.blue;

    /**
     * The color to use for the horizontal feedback rules shown when there command is not valid.
     */
    public static final Color CONFLICT_FEEDBACK_COLOR = ColorConstants.red;

    private final AbstractSequenceInteractionValidator validator;

    private final IFigure feedBackLayer;

    private final IGraphicalEditPart hostPart;

    /**
     * Constructor.
     * 
     * @param validator
     *            the move validator.
     * @param feedBackLayer
     *            the feedback layer.
     * @param hostPart
     *            the main selected part.
     */
    public SequenceInteractionFeedBackBuilder(AbstractSequenceInteractionValidator validator, IFigure feedBackLayer, IGraphicalEditPart hostPart) {
        this.validator = validator;
        this.feedBackLayer = feedBackLayer;
        this.hostPart = hostPart;
    }

    /**
     * Build the command.
     * 
     * @return a composite transactional command.
     */
    public Collection<Figure> buildFeedBack() {
        Collection<Figure> feedbacks = new ArrayList<>();

        // validation on the first call;
        validator.validate();

        feedBackCreatedElements(feedbacks);

        feedBackMovedElements(feedbacks);

        feedBackResizedElements(feedbacks);

        feedBackExpansion(feedbacks);

        feedBackErrors(feedbacks);

        feedBackConflicts(feedbacks);

        return feedbacks;

    }

    private void feedBackConflicts(Collection<Figure> feedbacks) {
        for (Integer conflict : validator.getInvalidPostions()) {
            Point conflictingPosition = new Point(0, conflict);
            conflictingPosition.performScale(GraphicalHelper.getZoom(hostPart));

            Rectangle bounds = feedBackLayer.getBounds().getCopy();
            bounds.y = conflictingPosition.y;
            bounds.height = 1;

            HorizontalGuide conflictGuide = new HorizontalGuide(CONFLICT_FEEDBACK_COLOR, conflictingPosition.y);
            conflictGuide.setBounds(bounds);
            feedbacks.add(conflictGuide);
        }

        for (Range conflict : validator.getInvalidRanges()) {
            Rectangle screenRange = new Rectangle(0, conflict.getLowerBound(), 0, conflict.width());
            screenRange.performScale(GraphicalHelper.getZoom(hostPart));
            Range conflictRange = RangeHelper.verticalRange(screenRange);

            Rectangle bounds = feedBackLayer.getBounds().getCopy();
            bounds.y = conflictRange.getLowerBound();
            bounds.height = Math.max(1, conflictRange.width());

            RangeGuide guide = new RangeGuide(CONFLICT_FEEDBACK_COLOR, conflictRange, true);
            guide.setBounds(bounds);
            feedbacks.add(guide);
        }
    }

    private void feedBackErrors(Collection<Figure> feedbacks) {
        for (ISequenceEvent errorEvent : validator.getEventsInError()) {
            addFeedBack(errorEvent, CONFLICT_FEEDBACK_COLOR, true, feedbacks, validator.getRangeFunction().apply(errorEvent));
        }
    }

    private void addFeedBack(ISequenceEvent event, Color color, boolean fill, Collection<Figure> feedbacks, Range movedRange) {
        Rectangle screenRange = new Rectangle(0, movedRange.getLowerBound(), 0, movedRange.width());
        screenRange.performScale(GraphicalHelper.getZoom(hostPart));
        Range moveRange = RangeHelper.verticalRange(screenRange);

        Rectangle bounds = feedBackLayer.getBounds().getCopy();
        if (event != null && event.isLogicallyInstantaneous()) {
            moveRange = new Range(screenRange.getCenter().y, screenRange.getCenter().y);
            bounds.y = moveRange.getLowerBound();
            bounds.height = 1;
        } else {
            bounds.y = moveRange.getLowerBound();
            bounds.height = Math.max(1, moveRange.width());
        }

        RangeGuide guide = new RangeGuide(color, moveRange, fill);
        guide.setBounds(bounds);

        feedbacks.add(guide);
    }

    private void feedBackExpansion(Collection<Figure> feedbacks) {
        Rectangle bounds = feedBackLayer.getBounds().getCopy();
        Range expansionZone = validator.getExpansionZone();
        if (expansionZone != null && !expansionZone.isEmpty() && expansionZone.width() != 0) {
            Rectangle screenRange = new Rectangle(0, expansionZone.getLowerBound(), 0, expansionZone.width());
            screenRange.performScale(GraphicalHelper.getZoom(hostPart));
            Range expand = RangeHelper.verticalRange(screenRange);

            RangeGuide expansion = new RangeGuide(validator.isValid() ? EXPANSION_FEEDBACK_COLOR : CONFLICT_FEEDBACK_COLOR, expand, true);
            bounds.height = expand.width();
            bounds.y = expand.getLowerBound();
            expansion.setBounds(bounds);

            feedbacks.add(expansion);
        }
    }

    private void feedBackMovedElements(Collection<Figure> feedbacks) {
        for (ISequenceEvent movedElement : Iterables.filter(validator.getMovedElements(), Predicates.not(Predicates.in(validator.getEventsInError())))) {
            addFeedBack(movedElement, ISE_FEEDBACK_COLOR, false, feedbacks, validator.getRangeFunction().apply(movedElement));
        }
    }

    private void feedBackCreatedElements(Collection<Figure> feedbacks) {
        for (Range creationRange : validator.getCreatedElements()) {
            addFeedBack(null, validator.isValid() ? ISE_FEEDBACK_COLOR : CONFLICT_FEEDBACK_COLOR, false, feedbacks, creationRange);
        }
    }

    private void feedBackResizedElements(Collection<Figure> feedbacks) {
        for (ISequenceEvent movedElement : Iterables.filter(validator.getResizedStartMessages(), Predicates.not(Predicates.in(validator.getEventsInError())))) {
            addFeedBack(movedElement, ISE_FEEDBACK_COLOR, false, feedbacks, validator.getRangeFunction().apply(movedElement));
        }

        for (ISequenceEvent movedElement : Iterables.filter(validator.getResizedEndMessages(), Predicates.not(Predicates.in(validator.getEventsInError())))) {
            Range feedbackRange = validator.getRangeFunction().apply(movedElement);
            Range expansionZone = validator.getExpansionZone();
            if ((expansionZone != null && !expansionZone.isEmpty()) && feedbackRange.includes(expansionZone.getUpperBound())) {
                feedbackRange = new Range(feedbackRange.getLowerBound(), feedbackRange.getUpperBound() - expansionZone.width());
            }
            addFeedBack(movedElement, ISE_FEEDBACK_COLOR, false, feedbacks, feedbackRange);
        }
    }
}
