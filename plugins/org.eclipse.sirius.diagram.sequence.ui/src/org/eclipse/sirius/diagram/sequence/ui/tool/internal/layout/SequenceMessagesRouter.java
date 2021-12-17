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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LifelineNodeFigure;

/**
 * A specific router for the messages on a sequence diagram.
 * 
 * @author pcdavid
 */
public class SequenceMessagesRouter extends AbstractRouter implements ConnectionRouter {

    /**
     * A point, reused for computations to avoid creating many instances.
     */
    private static final PrecisionPoint A_POINT = new PrecisionPoint();

    /**
     * The constraints associated to each connection to route.
     */
    private Map<Connection, Object> constraints = new WeakHashMap<Connection, Object>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConstraint(Connection connection, Object constraint) {
        this.constraints.put(connection, constraint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getConstraint(Connection connection) {
        return this.constraints.get(connection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Connection connection) {
        this.constraints.remove(connection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void route(Connection conn) {
        if (!isValidConnection(conn)) {
            return;
        }

        IGraphicalEditPart part = null;
        if (conn instanceof AbstractDiagramEdgeEditPart.ViewEdgeFigure) {
            part = ((AbstractDiagramEdgeEditPart.ViewEdgeFigure) conn).getEditPart();
        }

        boolean isReflexiveMessage = isReflectiveMessage(part);
        List<Bendpoint> bendpoints = getRefreshedConstraint(conn, isReflexiveMessage);

        Point sourceRef = getReferencePoint(conn, true, bendpoints);
        Point targetRef = getReferencePoint(conn, false, bendpoints);

        boolean leftToRight = conn.getSourceAnchor().getReferencePoint().x < conn.getTargetAnchor().getReferencePoint().x;
        boolean msgToSelf = sourceRef.x == targetRef.x && sourceRef.y != targetRef.y || bendpoints.size() >= 4;
        msgToSelf = msgToSelf || isReflexiveMessage;

        Rectangle sourceOwnerBounds = getAnchorOwnerBounds(conn.getSourceAnchor());
        Rectangle targetOwnerBounds = getAnchorOwnerBounds(conn.getTargetAnchor());

        sourceRef.setX(getRefX(true, leftToRight, msgToSelf, sourceOwnerBounds, conn.getSourceAnchor().getOwner()));
        targetRef.setX(getRefX(false, leftToRight, msgToSelf, targetOwnerBounds, conn.getTargetAnchor().getOwner()));

        PointList points = new PointList(Math.max(2, bendpoints.size()));

        if (msgToSelf) {
            // We are working on a message to self
            // We align the 2 extra bendpoint with the relative source and
            // target
            Point secondPoint = sourceRef.getCopy();
            Point thirdPoint = targetRef.getCopy();
            double zoom = part == null ? 1.0 : GraphicalHelper.getZoom(part);

            int hGap = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP;
            if (part instanceof SequenceMessageEditPart) {
                Message msg = (Message) ((SequenceMessageEditPart) part).getISequenceEvent();
                if (isReflexiveMessage) {
                    hGap = msg.getReflexiveMessageWidth();
                }
            }

            secondPoint.setX(Math.max(sourceRef.x, targetRef.x) + (int) (hGap * zoom));
            thirdPoint.setX(secondPoint.x);

            conn.translateToRelative(sourceRef);
            conn.translateToRelative(secondPoint);
            conn.translateToRelative(thirdPoint);

            points.addPoint(sourceRef);
            points.addPoint(secondPoint);
            points.addPoint(thirdPoint);
        } else {
            conn.translateToRelative(sourceRef);
            points.addPoint(sourceRef);
        }
        conn.translateToRelative(targetRef);
        points.addPoint(targetRef);
        conn.setPoints(points);

        // This specific update have no use anymore and there was a problem with
        // reflexive sync call and scroll
        // updateEdgeBendpointOnMessageToSelfCreation(part, conn);
    }

    private int getRefX(boolean source, boolean leftToRight, boolean msgToSelf, Rectangle anchorOwnerBounds, IFigure anchorOwner) {
        int refX;
        boolean onLeft = (source && !leftToRight) || (leftToRight && !source);
        onLeft = onLeft && !msgToSelf;

        if (msgToSelf) { // bendpoints.size() >= 4 ||
            // Source and Target of message to self are on the right
            refX = anchorOwnerBounds.getRight().x;
        } else {
            refX = onLeft ? anchorOwnerBounds.getLeft().x : anchorOwnerBounds.getRight().x;
        }

        // Corrects figure for messages linked to lifelines (gap between line
        // and real figure)
        if (anchorOwner instanceof LifelineNodeFigure) {
            LifelineNodeFigure lnf = (LifelineNodeFigure) anchorOwner;
            int refMidWidth = (anchorOwnerBounds.width - lnf.getLineWidth()) / 2;
            refX += onLeft ? refMidWidth : -refMidWidth;
        }
        return refX;
    }

    private Point getReferencePoint(Connection conn, boolean source, List<Bendpoint> bendpoints) {
        Point ref;
        if (bendpoints.isEmpty()) {
            ConnectionAnchor anchor = source ? conn.getSourceAnchor() : conn.getTargetAnchor();
            ref = anchor.getReferencePoint().getCopy();
        } else {
            int index = source ? 0 : bendpoints.size() - 1;
            ref = new Point((bendpoints.get(index)).getLocation());
            conn.translateToAbsolute(ref);
        }
        return ref;
    }

    private Rectangle getAnchorOwnerBounds(ConnectionAnchor anchor) {
        Rectangle ownerBounds = anchor.getOwner().getBounds().getCopy();
        anchor.getOwner().getParent().translateToAbsolute(ownerBounds);
        return ownerBounds;
    }

    private boolean isReflectiveMessage(IGraphicalEditPart part) {
        if (part instanceof SequenceMessageEditPart) {
            ISequenceEvent ise = ((SequenceMessageEditPart) part).getISequenceEvent();
            return ise instanceof Message && ((Message) ise).isReflective();
        }
        return false;
    }

    private List<Bendpoint> getRefreshedConstraint(Connection conn, boolean isReflectiveMessage) {
        boolean noBendpointsAtbeginning = false;
        @SuppressWarnings("unchecked")
        List<Bendpoint> bendpoints = (List<Bendpoint>) getConstraint(conn);
        if (bendpoints == null) {
            noBendpointsAtbeginning = true;
            bendpoints = Collections.emptyList();
        }
        refreshBendpoints(bendpoints, conn, isReflectiveMessage);

        if (!(noBendpointsAtbeginning && Collections.emptyList().equals(bendpoints))) {
            // There is no need to set constraint if there is no bendpoints at
            // the beginning of this method and that is finally empty.
            setConstraint(conn, bendpoints);
        }
        return bendpoints;
    }

    private boolean isValidConnection(Connection conn) {
        return isValidAnchor(conn.getSourceAnchor()) && isValidAnchor(conn.getTargetAnchor());
    }

    private boolean isValidAnchor(ConnectionAnchor anchor) {
        return anchor != null && anchor.getOwner() != null;
    }

    private void refreshBendpoints(List<Bendpoint> bendpoints, Connection conn, boolean isReflexiveMessage) {

        IGraphicalEditPart part = null;
        if (conn instanceof AbstractDiagramEdgeEditPart.ViewEdgeFigure) {
            part = ((AbstractDiagramEdgeEditPart.ViewEdgeFigure) conn).getEditPart();
        }

        if (bendpoints.size() > 2) {
            if (isReflexiveMessage) {
                if (bendpoints.size() > 4) {
                    // The user want to resize a message to self by pulling an
                    // edge (between bendpoints)
                    // This has the effect to add a fifth bendpoint
                    align5BendpointsOfMessageToSelf(bendpoints, conn);
                } else if (bendpoints.size() == 4) {
                    // The user want to resize a message to self by pulling a
                    // bendpoint
                    align4BendpointsOfMessageToSelf(bendpoints, conn);
                }
            } else {
                /*
                 * The only case where we have more than two bendpoints is when the user has dragged a connection, which
                 * created a temporary intermediate bendpoint. We use that new point as the reference Y coordinate for
                 * the start and end points, but remove the intermediate bendpoint itself.
                 */
                Bendpoint start = bendpoints.get(0);
                Bendpoint end = bendpoints.get(bendpoints.size() - 1);
                Bendpoint moveRef = bendpoints.get(1);
                bendpoints.clear();

                A_POINT.setLocation(start.getLocation());
                A_POINT.setY(moveRef.getLocation().y);
                Bendpoint newStart = new AbsoluteBendpoint(A_POINT);
                bendpoints.add(newStart);
                /*
                 * I don't understand exactly why, but the intermediate point must be kept here, otherwise the edges can
                 * only be moved of a very small vertical distance at a time.
                 */
                bendpoints.add(moveRef);
                A_POINT.setLocation(end.getLocation());
                A_POINT.setY(moveRef.getLocation().y);
                Bendpoint newEnd = new AbsoluteBendpoint(A_POINT);
                bendpoints.add(newEnd);
            }
        } else if (bendpoints.size() == 2) {
            Bendpoint start = bendpoints.get(0);
            Bendpoint end = bendpoints.get(bendpoints.size() - 1);

            bendpoints.clear();
            Bendpoint newStart = new AbsoluteBendpoint(start.getLocation());
            Bendpoint newEnd = new AbsoluteBendpoint(end.getLocation());

            if (isReflexiveMessage) {
                bendpoints.addAll(createMessageToSelf(start, end, (SequenceMessageEditPart) part));
            } else {
                if (part instanceof SequenceMessageEditPart
                        && (((SequenceMessageEditPart) part).getTarget() instanceof InstanceRoleEditPart || ((SequenceMessageEditPart) part).getTarget() instanceof EndOfLifeEditPart)) {
                    IGraphicalEditPart target = (IGraphicalEditPart) ((SequenceMessageEditPart) part).getTarget();
                    Rectangle targetBounds = target.getFigure().getBounds();
                    int yCenterPosition = targetBounds.y + targetBounds.height / 2;
                    newStart.getLocation().setY(yCenterPosition);
                }

                bendpoints.add(newStart);
                // Ensure the message is horizontal.
                newEnd.getLocation().setY(newStart.getLocation().y);
                bendpoints.add(newEnd);
            }
        }
    }

    /**
     * Having a connection with 5 bendpoints means that we are working on a message to self. We will compare the
     * positions of the bendpoints with the previous ones. This way, we will know if the user has pulled a bendpoint and
     * will move the other closest bendpoints to keep the layout.
     * 
     * @param bendpoints
     *            the list of bendpoints on a connection
     * @param conn
     *            the message to self connection
     */
    private void align4BendpointsOfMessageToSelf(final List<Bendpoint> bendpoints, Connection conn) {

        Bendpoint newStart = new AbsoluteBendpoint(bendpoints.get(0).getLocation());
        Bendpoint newSecondPoint = new AbsoluteBendpoint(bendpoints.get(1).getLocation());
        Bendpoint newThirdPoint = new AbsoluteBendpoint(bendpoints.get(2).getLocation());
        Bendpoint newEnd = new AbsoluteBendpoint(bendpoints.get(3).getLocation());
        bendpoints.clear();

        Bendpoint oldSecondPoint;
        Bendpoint oldThirdPoint;
        if (conn.getPoints() != null && conn.getPoints().size() == 4) {
            oldSecondPoint = new AbsoluteBendpoint(conn.getPoints().getPoint(1));
            oldThirdPoint = new AbsoluteBendpoint(conn.getPoints().getPoint(2));
        } else {
            oldSecondPoint = newSecondPoint;
            oldThirdPoint = newThirdPoint;
        }

        if (oldSecondPoint.getLocation().y != newSecondPoint.getLocation().y) {
            // Second point Y position has changed. We need to update the start
            // point.
            if (newSecondPoint.getLocation().y > newThirdPoint.getLocation().y - LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP) {
                // limit vertical move to keep the minimum distance
                newSecondPoint.getLocation().setY(newThirdPoint.getLocation().y - LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP);
            }
            newStart.getLocation().setY(newSecondPoint.getLocation().y);
        }

        if (oldThirdPoint.getLocation().y != newThirdPoint.getLocation().y) {
            // Second point Y position has changed. We need to update the start
            // point.
            if (newThirdPoint.getLocation().y < newSecondPoint.getLocation().y + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP) {
                // limit vertical move to keep the minimum distance
                newThirdPoint.getLocation().setY(newSecondPoint.getLocation().y + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP);
            }
            newEnd.getLocation().setY(newThirdPoint.getLocation().y);
        }

        bendpoints.add(newStart);
        bendpoints.add(newSecondPoint);
        bendpoints.add(newThirdPoint);
        bendpoints.add(newEnd);
    }

    /**
     * Having a connection with 5 bendpoints means that we are working on a message to self that the user is resizing by
     * pulling an edge between 2 bendpoints. In that case we will align the bendpoints just before and after to keep the
     * same layout.
     * 
     * @param bendpoints
     *            the list of bendpoints on a connection
     * @param conn
     *            the message to self connection
     */
    private void align5BendpointsOfMessageToSelf(final List<Bendpoint> bendpoints, Connection conn) {

        Bendpoint newStart = new AbsoluteBendpoint(bendpoints.get(0).getLocation());
        Bendpoint secondPoint = new AbsoluteBendpoint(bendpoints.get(1).getLocation());
        Bendpoint thirdPoint = new AbsoluteBendpoint(bendpoints.get(2).getLocation());
        Bendpoint fourthPoint = new AbsoluteBendpoint(bendpoints.get(3).getLocation());
        Bendpoint newEnd = new AbsoluteBendpoint(bendpoints.get(4).getLocation());
        bendpoints.clear();

        Bendpoint oldSecondPoint = new AbsoluteBendpoint(conn.getPoints().getPoint(1));
        Bendpoint oldThirdPoint = new AbsoluteBendpoint(conn.getPoints().getPoint(2));

        if (oldSecondPoint.getLocation().y != secondPoint.getLocation().y) {
            // The new bendpoint is between old start and old second points
            if (secondPoint.getLocation().y > oldThirdPoint.getLocation().y - LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP) {
                // limit vertical move to keep the minimum distance
                secondPoint.getLocation().setY(oldThirdPoint.getLocation().y - LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP);
            }
            newStart.getLocation().setY(secondPoint.getLocation().y);
            thirdPoint.getLocation().setY(secondPoint.getLocation().y);
        } else if (oldThirdPoint.getLocation().y != fourthPoint.getLocation().y) {

            // The new bendpoint is between old third and old end points
            if (fourthPoint.getLocation().y < oldSecondPoint.getLocation().y + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP) {
                // limit vertical move to keep the minimum distance
                fourthPoint.getLocation().setY(oldSecondPoint.getLocation().y + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP);
            }
            thirdPoint.getLocation().setY(fourthPoint.getLocation().y);
            newEnd.getLocation().setY(fourthPoint.getLocation().y);
        }

        bendpoints.add(newStart);
        bendpoints.add(secondPoint);
        bendpoints.add(thirdPoint);
        bendpoints.add(fourthPoint);
        bendpoints.add(newEnd);
    }

    /**
     * Creates a message to self from start to end by adding 2 bendpoints shifted on the right by
     * LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_GAP.
     * 
     * @param start
     *            the message to self start position
     * @param end
     *            the message to self end position
     * @return the list of bendpoints to create a message to self from start to end
     */
    private List<Bendpoint> createMessageToSelf(Bendpoint start, Bendpoint end, SequenceMessageEditPart part) {
        ArrayList<Bendpoint> messageToSelfBendpoint = new ArrayList<Bendpoint>();

        Bendpoint newStart = new AbsoluteBendpoint(start.getLocation());
        Bendpoint newSecondPoint = new AbsoluteBendpoint(start.getLocation());
        Bendpoint newThirdPoint = new AbsoluteBendpoint(end.getLocation());
        Bendpoint newEnd = new AbsoluteBendpoint(end.getLocation());

        int minGap = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP;
        if (newEnd.getLocation().y - newStart.getLocation().y < minGap) {
            newThirdPoint.getLocation().setY(newStart.getLocation().y + minGap);
            newEnd.getLocation().setY(newThirdPoint.getLocation().y);
        }

        messageToSelfBendpoint.add(newStart);
        messageToSelfBendpoint.add(newSecondPoint);
        messageToSelfBendpoint.add(newThirdPoint);
        messageToSelfBendpoint.add(newEnd);

        return messageToSelfBendpoint;
    }
}
