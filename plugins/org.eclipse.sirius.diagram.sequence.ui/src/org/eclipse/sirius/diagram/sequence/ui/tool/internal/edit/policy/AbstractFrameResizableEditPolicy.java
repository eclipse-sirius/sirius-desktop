/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.AbstractInteractionFrameValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.ISEComplexMoveValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.HorizontalGuide;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.RangeGuide;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirResizableEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusResizeTracker;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swt.graphics.Color;

import com.google.common.collect.Lists;

/**
 * A specific AirResizableEditPolicy to manage interaction use roles move & resize requests.
 * 
 * @author mporhel
 */
public abstract class AbstractFrameResizableEditPolicy extends AirResizableEditPolicy {

    /**
     * The color to use for the horizontal feedback rules shown when moving/resizing an execution.
     */
    private static final Color FRAME_FEEDBACK_COLOR = ColorConstants.lightGray;

    private Collection<Figure> guides = Lists.newArrayList();

    /**
     * Constructor.
     */
    public AbstractFrameResizableEditPolicy() {
        super();
        setResizeDirections(PositionConstants.NORTH_SOUTH);
    }

    /**
     * Frames can only be vertically resized.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void createResizeHandle(List handles, int direction) {
        if ((PositionConstants.NORTH & direction) == PositionConstants.NORTH || (PositionConstants.SOUTH & direction) == PositionConstants.SOUTH) {
            super.createResizeHandle(handles, direction);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResizeDirections(int newDirections) {
        super.setResizeDirections(PositionConstants.NORTH_SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        eraseChangeBoundsFeedback(request);
        cancelHorizontalDelta(request);

        super.showChangeBoundsFeedback(request);

        ISequenceEventEditPart hostPart = (ISequenceEventEditPart) getHost();
        RequestQuery requestQuery = new RequestQuery(request);

        ISequenceEvent frame = hostPart.getISequenceEvent();
        if (hostPart.getSelected() == EditPart.SELECTED_PRIMARY && requestQuery.isMove()) {
            ISEComplexMoveValidator validator = ISEComplexMoveValidator.getOrCreateValidator(request, requestQuery, frame);
            if (validator != null) {
                SequenceInteractionFeedBackBuilder feedBackBuilder = new SequenceInteractionFeedBackBuilder(validator, getFeedbackLayer(), hostPart);
                for (Figure fig : feedBackBuilder.buildFeedBack()) {
                    addFeedback(fig);
                    guides.add(fig);
                }
            }
        } else if (requestQuery.isResize()) {
            Rectangle newBounds = getNewBounds(getHostAbsoluteBounds().getCopy(), request);
            Figure frameGuide = new RangeGuide(FRAME_FEEDBACK_COLOR, new Range(newBounds.y, newBounds.y + Math.max(0, newBounds.height)), false);
            Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
            bounds.y = newBounds.y;
            bounds.height = newBounds.height;
            frameGuide.setBounds(bounds);
            addFeedback(frameGuide);
            guides.add(frameGuide);

            // display a guide for each Operand start
            for (ISequenceEventEditPart child : getChildrenToFeedBack(request)) {
                newBounds = getNewBounds(getAbsoluteBounds(child.getFigure()), request);
                HorizontalGuide childGuide = createAndAddFeedbackHGuide(newBounds.getTop().y);
                guides.add(childGuide);
            }

            if (frame instanceof AbstractFrame) {
                AbstractInteractionFrameValidator validator = AbstractInteractionFrameValidator.getOrCreateResizeValidator(request, (AbstractFrame) frame);
                if (!validator.isValid()) {
                    feedBackConflicts(validator);
                }

                feedBackExpansion(validator);
            }
        }
    }

    private void feedBackExpansion(AbstractInteractionFrameValidator validator) {
        Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
        Range expansionZone = validator.getExpansionZone();
        if (!expansionZone.isEmpty()) {
            Rectangle screenRange = new Rectangle(0, expansionZone.getLowerBound(), 0, expansionZone.width());
            screenRange.performScale(GraphicalHelper.getZoom(getHost()));
            Range expand = RangeHelper.verticalRange(screenRange);

            RangeGuide expansion = new RangeGuide(validator.isValid() ? ColorConstants.blue : ColorConstants.red, expand, true);
            bounds.height = expand.width();
            bounds.y = expand.getLowerBound();
            expansion.setBounds(bounds);
            addFeedback(expansion);
            guides.add(expansion);
        }
    }

    private void feedBackConflicts(AbstractInteractionFrameValidator validator) {
        for (Integer conflict : validator.getInvalidPositions()) {
            Point conflictingPosition = new Point(0, conflict);
            conflictingPosition.performScale(GraphicalHelper.getZoom(getHost()));

            Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
            bounds.y = conflictingPosition.y;
            bounds.height = 1;

            HorizontalGuide conflictGuide = new HorizontalGuide(ColorConstants.red, conflictingPosition.y);
            conflictGuide.setBounds(bounds);
            addFeedback(conflictGuide);
            guides.add(conflictGuide);
        }
    }

    private Rectangle getNewBounds(Rectangle oldBounds, ChangeBoundsRequest cbr) {
        Rectangle newBounds = cbr.getTransformedRectangle(oldBounds).getCopy();

        FreeformViewport viewport = FigureUtilities.getFreeformViewport(getHostFigure());
        if (viewport != null) {
            newBounds.translate(viewport.getViewLocation());
        }

        return newBounds;
    }

    private HorizontalGuide createAndAddFeedbackHGuide(int y) {
        Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
        bounds.height = 1;
        bounds.y = y;

        HorizontalGuide guide = new HorizontalGuide(FRAME_FEEDBACK_COLOR, y);
        guide.setBounds(bounds);
        addFeedback(guide);
        return guide;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
        // cancelHorizontalDelta((ChangeBoundsRequest) request);
        removeFeedBackOnGuides();
        super.eraseChangeBoundsFeedback(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAutoSizeCommand(Request request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Cancel horizontal changes of the given request.
     * 
     * @param request
     *            a request.
     */
    protected void cancelHorizontalDelta(ChangeBoundsRequest request) {
        if (request == null) {
            return;
        }

        Point moveDelta = request.getMoveDelta();
        if (moveDelta != null) {
            request.setMoveDelta(new Point(0, moveDelta.y));
        }

        Dimension sizeDelta = request.getSizeDelta();
        if (sizeDelta != null) {
            request.setSizeDelta(new Dimension(0, sizeDelta.height));
        }
    }

    private void removeFeedBackOnGuides() {
        if (guides != null && !guides.isEmpty()) {
            for (Figure hGuide : guides) {
                removeFeedback(hGuide);
            }
            guides.clear();
        }
    }

    /**
     * Return absolute bounds of the current host.
     * 
     * @return absolute bounds of the current host.
     */
    private Rectangle getHostAbsoluteBounds() {
        return getAbsoluteBounds(getHostFigure());
    }

    private Rectangle getAbsoluteBounds(IFigure figure) {
        Rectangle bounds = figure.getBounds().getCopy();
        figure.getParent().translateToAbsolute(bounds);
        return bounds;
    }

    /**
     * Additional feedback.
     * 
     * @param request
     *            the request to feedback.
     * @return a collection of {@link ISequenceEventEditPart} to feedback.
     */
    protected abstract Collection<ISequenceEventEditPart> getChildrenToFeedBack(ChangeBoundsRequest request);

    @Override
    protected ResizeTracker getResizeTracker(int direction) {
        return new SiriusResizeTracker((GraphicalEditPart) getHost(), direction) {
            @Override
            protected boolean handleButtonUp(int button) {
                CacheHelper.clearCaches();
                return super.handleButtonUp(button);
            }

            @Override
            protected boolean handleButtonDown(int button) {
                boolean handleButtonDown = super.handleButtonDown(button);
                CacheHelper.initCaches();
                return handleButtonDown;
            }
        };
    }
}
