/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.handles.BendpointHandle;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.Direction;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.DirectionUtil;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.handles.BendpointMoveHandle;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.handles.BendpointRotateHandle;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelQuery;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;

/**
 * A specific {@link BendpointEditPolicy} to manage move/rotate and their
 * feedback of Dimension figure.
 *
 * @author <a href="mailto:mariot.chauvin@obeo.fr">Mariot Chauvin</a>
 */
public class BracketBendpointEditPolicy extends BendpointEditPolicy {

    /** The label used for the bracket edge move command. */
    public static final String MOVE_COMMAND_LABEL = Messages.BracketBendpointEditPolicy_moveBrackedCommandLabel;

    /** The label used for the bracket edge rotate command. */
    public static final String ROTATE_COMMAND_LABEL = Messages.BracketBendpointEditPolicy_rotateBracketCommandLabel;

    /** The background feedback color as in GMF. */
    private static final Color GRAY = new Color(null, 200, 200, 200);

    /** The feeback figure. */
    private IFigure feedbackFigure;

    /** The host {@link BracketEdgeEditPart}. */
    private BracketEdgeEditPart bracketEdgeEditPart;

    /**
     * Default constructor.
     *
     * @param bracketEdgeEditPart
     *            the host {@link BracketEdgeEditPart}
     */
    public BracketBendpointEditPolicy(BracketEdgeEditPart bracketEdgeEditPart) {
        this.bracketEdgeEditPart = bracketEdgeEditPart;
    }

    /**
     * Overridden to return custom selection handles. {@inheritDoc}
     */
    @SuppressWarnings("rawtypes")
    @Override
    protected List createSelectionHandles() {
        final List<BendpointHandle> selectionHandles = new ArrayList<BendpointHandle>();
        selectionHandles.add(new BendpointMoveHandle(bracketEdgeEditPart, getCursor()));
        selectionHandles.add(new BendpointRotateHandle(bracketEdgeEditPart, BracketConnectionQuery.ORIGIN_POINT_INDEX));
        selectionHandles.add(new BendpointRotateHandle(bracketEdgeEditPart, BracketConnectionQuery.TARGET_POINT_INDEX));
        return selectionHandles;
    }

    /**
     * Get the {@link Cursor}.
     *
     * @return the {@link Cursor}
     */
    private Cursor getCursor() {
        Cursor cursor = null;
        final Direction currentDirection = DirectionUtil.getDirection(getConnection());
        switch (currentDirection) {
        case LEFT:
        case RIGHT:
            cursor = Cursors.SIZEWE;
            break;
        default:
            cursor = Cursors.SIZENS;
            break;
        }
        final PointList points = getConnection().getPoints();
        if (points.size() == 2) {
            if (cursor == Cursors.SIZEWE) {
                cursor = Cursors.SIZENS;
            } else if (cursor == Cursors.SIZENS) {
                cursor = Cursors.SIZEWE;
            }
        }
        return cursor;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gef.editpolicies.BendpointEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(Request request) {
        Command command = null;
        if (request instanceof BendpointRequest) {
            final BendpointRequest bendpointRequest = (BendpointRequest) request;
            if (org.eclipse.gef.RequestConstants.REQ_MOVE_BENDPOINT.equals(request.getType())) {
                command = getMoveBendpointCommand(bendpointRequest);
            } else if (RequestConstants.REQ_ROTATE_BENDPOINT.equals(request.getType())) {
                command = getRotateBendpointCommand(bendpointRequest);
            }
        } else {
            command = super.getCommand(request);
        }
        return command;
    }

    /**
     * Get a {@link Command} to manage the move request for Dimension figure.
     *
     * @param request
     *            the {@link BendpointRequest}
     * @return a {@link Command} to manage the move request
     */
    @Override
    protected Command getMoveBendpointCommand(BendpointRequest request) {
        CompoundCommand compoundCommand = new CompoundCommand(MOVE_COMMAND_LABEL);
        appendUpdateGMFBendpointsCommand(compoundCommand, request);
        return new ICommandProxy(new GMFCommandWrapper(bracketEdgeEditPart.getEditingDomain(), compoundCommand));

    }

    /**
     * Get a {@link Command} to manage the rotate request for Dimension figure.
     *
     * @param request
     *            the {@link BendpointRequest}
     * @return a {@link Command} to manage the rotate request
     */
    protected Command getRotateBendpointCommand(BendpointRequest request) {
        CompoundCommand compoundCommand = new CompoundCommand(ROTATE_COMMAND_LABEL);
        appendUpdateGMFBendpointsCommand(compoundCommand, request);
        return new ICommandProxy(new GMFCommandWrapper(bracketEdgeEditPart.getEditingDomain(), compoundCommand));
    }

    /**
     * Constructs a GEF {@link Command} for move/rotate request.
     *
     * @param compoundCommand
     *            The command to complete with the update GMF bendpoints
     *            command.
     * @param request
     *            {@link BendpointRequest} move/rotate request
     * @return EMF {@link Command} to update the GMF {@link RelativeBendpoint}.
     */
    private void appendUpdateGMFBendpointsCommand(CompoundCommand compoundCommand, BendpointRequest request) {
        final BracketConnectionQuery bracketFigureQuery = new BracketConnectionQuery(request, getConnection());
        final PointList newPointList = bracketFigureQuery.getNewPointList();

        final TransactionalEditingDomain domain = bracketEdgeEditPart.getEditingDomain();

        final Edge edge = (Edge) bracketEdgeEditPart.getModel();

        final List<RelativeBendpoint> gmfRelativeBendpointsFromPointList = bracketFigureQuery.getGMFRelativeBendpoints(newPointList);

        final org.eclipse.emf.common.command.Command updateGMFBendpointsCmd = SetCommand.create(domain, edge.getBendpoints(), NotationPackage.Literals.RELATIVE_BENDPOINTS__POINTS,
                gmfRelativeBendpointsFromPointList);
        compoundCommand.append(updateGMFBendpointsCmd);
        appendUpdateGMFLabelsOffsetCommand(compoundCommand, domain, getConnection().getPoints(), newPointList);
    }

    /**
     * Constructs EMF {@link Command}s to move labels of this edge.
     *
     * @param compoundCommand
     *            The command to complete with the update GMF labels offset
     *            command.
     * @param domain
     *            Current editing domain
     * @param oldPointList
     *            Old points list of the edge
     * @param newPointList
     *            New points list of the edge
     * @return The commands to move the label of this edge.
     */
    private void appendUpdateGMFLabelsOffsetCommand(CompoundCommand compoundCommand, TransactionalEditingDomain domain, PointList oldPointList, PointList newPointList) {
        // For each label, compute the new offset
        List<?> children = bracketEdgeEditPart.getChildren();
        for (Object child : children) {
            if (child instanceof AbstractDEdgeNameEditPart) {
                AbstractDEdgeNameEditPart labelEditPartToUpdate = (AbstractDEdgeNameEditPart) child;
                Node labelNodeToUpdate = (Node) labelEditPartToUpdate.getModel();
                LayoutConstraint layoutConstraint = labelNodeToUpdate.getLayoutConstraint();
                if (layoutConstraint instanceof Bounds) {
                    Bounds bounds = (Bounds) layoutConstraint;
                    Point newLabelOffset = new EdgeLabelQuery(oldPointList, newPointList, false, new Point(bounds.getX(), bounds.getY()), labelEditPartToUpdate.getFigure().getSize(),
                            labelEditPartToUpdate.getKeyPoint(), true).calculateGMFLabelOffset();
                    Bounds labelBounds = NotationFactory.eINSTANCE.createBounds();
                    labelBounds.setX(newLabelOffset.x);
                    labelBounds.setY(newLabelOffset.y);
                    labelBounds.setWidth(bounds.getWidth());
                    labelBounds.setHeight(bounds.getHeight());
                    compoundCommand.append(SetCommand.create(domain, labelNodeToUpdate, NotationPackage.Literals.NODE__LAYOUT_CONSTRAINT, labelBounds));
                }
            }
        }
    }

    /**
     * We do not allow to create bendpoint. {@inheritDoc}
     */
    @Override
    protected Command getCreateBendpointCommand(BendpointRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * We do not allow to delete bendpoint. {@inheritDoc}
     */
    @Override
    protected Command getDeleteBendpointCommand(BendpointRequest request) {
        return UnexecutableCommand.INSTANCE;
    }

    /**
     * Overridden to manage feedback for move and rotate request. {@inheritDoc}
     */
    @Override
    public void showSourceFeedback(Request request) {
        if (request instanceof BendpointRequest) {
            final BendpointRequest bendpointRequest = (BendpointRequest) request;
            if (REQ_MOVE_BENDPOINT.equals(request.getType())) {
                showMoveLineSegFeedback(bendpointRequest);
            } else if (RequestConstants.REQ_ROTATE_BENDPOINT.equals(request.getType())) {
                showRotateLineSegFeedback(bendpointRequest);
            }
        }
    }

    /**
     * Shows feedback when a line segment is being moved.
     *
     * @param request
     *            the {@link BendpointRequest} to use for feedback
     */
    private void showMoveLineSegFeedback(BendpointRequest request) {
        showLineSegFeedback(request);
    }

    /**
     * Shows feedback when a line segment is being rotated.
     *
     * @param request
     *            the {@link BendpointRequest} to use for feedback
     */
    private void showRotateLineSegFeedback(BendpointRequest request) {
        showLineSegFeedback(request);
    }

    /**
     * Shows feedback when a line segment is being moved/rotated.
     *
     * @param request
     *            the {@link BendpointRequest} to use for feedback
     */
    public void showLineSegFeedback(BendpointRequest request) {
        final BracketConnectionQuery bracketConnectionQuery = new BracketConnectionQuery(request, getConnection());
        final PointList newPointList = bracketConnectionQuery.getNewPointList();

        final PointList feedbackScreenList = new PointList(newPointList.size());
        double zoom = GraphicalHelper.getZoom(bracketEdgeEditPart);
        for (int i = 0; i < newPointList.size(); i++) {
            Point point = newPointList.getPoint(i);
            point.performScale(zoom);
            feedbackScreenList.addPoint(point);
        }

        final PolylineConnection connectionFeedback = new PolylineConnection();
        connectionFeedback.setForegroundColor(FigureUtilities.mixColors(GRAY, ColorConstants.black));
        connectionFeedback.setLineStyle(Graphics.LINE_DOT);
        connectionFeedback.setPoints(feedbackScreenList);

        removeFeedbackFigure();
        addFeedbackFigure(connectionFeedback);
    }

    /**
     * Overridden to remove previous computed feedback for move or rotate
     * request on Dimension figure. {@inheritDoc}
     */
    @Override
    public void eraseSourceFeedback(Request request) {
        if (REQ_MOVE_BENDPOINT.equals(request.getType()) || RequestConstants.REQ_ROTATE_BENDPOINT.equals(request.getType())) {
            removeFeedbackFigure();
        }
    }

    /**
     * Add the specified <code>Figure</code> from the
     * LayerConstants#FEEDBACK_LAYER.
     *
     * @param figure
     *            the feedback to add
     */
    private void addFeedbackFigure(final IFigure figure) {
        feedbackFigure = figure;
        addFeedback(feedbackFigure);

    }

    /**
     * Removes the feedback figure from the LayerConstants#FEEDBACK_LAYER.
     */
    private void removeFeedbackFigure() {
        if (feedbackFigure != null) {
            removeFeedback(feedbackFigure);
            feedbackFigure = null;
        }
    }
}
