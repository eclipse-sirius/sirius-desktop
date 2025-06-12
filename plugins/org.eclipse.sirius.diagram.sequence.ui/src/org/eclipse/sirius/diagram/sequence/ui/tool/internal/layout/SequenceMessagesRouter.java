/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
import java.util.Optional;
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
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy.SequenceMessageEditPolicy;
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
    private static final PrecisionPoint BUTTON_DOWN_POINT = new PrecisionPoint();

    /**
     * A point, reused for computations to avoid creating many instances.
     */
    private static final PrecisionPoint A_POINT = new PrecisionPoint();

    /**
     * A point, reused for computations to avoid creating many instances.
     */
    private static final PrecisionPoint S_POINT = new PrecisionPoint();

    /**
     * A point, reused for computations to avoid creating many instances.
     */
    private static final PrecisionPoint F_POINT = new PrecisionPoint();

    private static Optional<Connection> dragInProgress = Optional.empty();

    /**
     * The constraints associated to each connection to route.
     */
    private Map<Connection, Object> constraints = new WeakHashMap<Connection, Object>();

    @Override
    public void setConstraint(Connection connection, Object constraint) {
        if (constraint instanceof BendpointRequest br) {
            Object initialClick = br.getExtendedData().get(SequenceMessageEditPart.MSG_OBLIQUE_CBR_INITAL_CLICK);
            if (dragInProgress.isEmpty() && initialClick instanceof Point obliqueMsgInitialClick) {
                BUTTON_DOWN_POINT.setLocation(obliqueMsgInitialClick);
                dragInProgress = Optional.of(connection);
            }
            return;
        }
        this.constraints.put(connection, constraint);
    }

    @Override
    public Object getConstraint(Connection connection) {
        return this.constraints.get(connection);
    }

    @Override
    public void remove(Connection connection) {
        this.constraints.remove(connection);
    }

    @Override
    public void route(Connection conn) {
        if (!isValidConnection(conn)) {
            return;
        }

        IGraphicalEditPart part = null;
        if (conn instanceof AbstractDiagramEdgeEditPart.ViewEdgeFigure) {
            part = ((AbstractDiagramEdgeEditPart.ViewEdgeFigure) conn).getEditPart();
        }

        boolean isObliqueMessage = isObliqueMessage(part);
        boolean isReflexiveMessage = isReflectiveMessage(part);
        List<Bendpoint> bendpoints = getRefreshedConstraint(conn, isReflexiveMessage, isObliqueMessage);

        Point sourceRef = getReferencePoint(conn, true, bendpoints);
        Point targetRef = getReferencePoint(conn, false, bendpoints);

        boolean leftToRight = conn.getSourceAnchor().getReferencePoint().x < conn.getTargetAnchor().getReferencePoint().x;
        boolean msgToSelf = sourceRef.x == targetRef.x && sourceRef.y != targetRef.y || bendpoints.size() >= 4;
        msgToSelf = msgToSelf || isReflexiveMessage;
        msgToSelf = msgToSelf && (!isObliqueMessage || isReflexiveMessage);

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

            int hGap = getMessageFromPart(part) //
                    .filter(msg -> isReflexiveMessage) //
                    .map(Message::getReflexiveMessageWidth) //
                    .orElse(LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP);

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

    private static Optional<Message> getMessageFromPart(IGraphicalEditPart part) {
        Optional<Message> optMsg = Optional.empty();
        if (part instanceof SequenceMessageEditPart isePart && isePart.getISequenceEvent() instanceof Message msg) {
            return Optional.of(msg);
        }
        return optMsg;
    }

    private boolean isObliqueMessage(IGraphicalEditPart part) {
        return getMessageFromPart(part).map(Message::isOblique).orElse(false);
    }

    private boolean isReflectiveMessage(IGraphicalEditPart part) {
        return getMessageFromPart(part).map(Message::isReflective).orElse(false);
    }

    private List<Bendpoint> getRefreshedConstraint(Connection conn, boolean isReflectiveMessage, boolean isObliqueMessage) {
        boolean noBendpointsAtbeginning = false;
        @SuppressWarnings("unchecked")
        List<Bendpoint> bendpoints = (List<Bendpoint>) getConstraint(conn);
        if (bendpoints == null) {
            noBendpointsAtbeginning = true;
            bendpoints = Collections.emptyList();
        }
        refreshBendpoints(bendpoints, conn, isReflectiveMessage, isObliqueMessage);

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

    private void refreshBendpoints(List<Bendpoint> bendpoints, Connection conn, boolean isReflexiveMessage, boolean isObliqueMessage) {

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
            } else if (isObliqueMessage) {
                refreshBendpointsForObliqueMessage(bendpoints, conn);
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

            // Do not re-init helper points when routing others messages.
            if (dragInProgress.isPresent() && (dragInProgress.get() == conn || dragInProgress.get().getParent() == null)) {
                // Re-init computation points : connection drag is over.
                A_POINT.setLocation(0, 0);
                S_POINT.setLocation(0, 0);
                F_POINT.setLocation(0, 0);
                dragInProgress = Optional.empty();
            }

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

                if (!isObliqueMessage) {
                    // Ensure the message is horizontal.
                    newEnd.getLocation().setY(newStart.getLocation().y);
                }
                bendpoints.add(newEnd);
            }
        }
    }

    private void refreshBendpointsForObliqueMessage(List<Bendpoint> bendpoints, Connection conn) {
        // Should be computed in policy and passed as a ready to use constraint.

        Bendpoint start = bendpoints.get(0);
        Bendpoint cursorReference = bendpoints.get(1);
        Bendpoint finish = bendpoints.get(bendpoints.size() - 1);

        // Compute move type and reference points once (equivalent to handleButtonDown)
        if (BUTTON_DOWN_POINT.y != 0 && dragInProgress.isPresent() && dragInProgress.get() == conn) {
            A_POINT.setLocation(BUTTON_DOWN_POINT);
            BUTTON_DOWN_POINT.setLocation(0, 0);

            S_POINT.setLocation(start.getLocation());
            F_POINT.setLocation(finish.getLocation());
        }
        Bendpoint newStart = start;
        Bendpoint newFinish = finish;
        int deltaY = cursorReference.getLocation().y - A_POINT.y;

        int width = finish.getLocation().x - start.getLocation().x;
        if (width < 0) {
            if (A_POINT.x == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_TARGET) {
                // Move target
                Point preciseFinish = new Point(finish.getLocation().x, Math.max(S_POINT.y + LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT, F_POINT.y + deltaY));
                newFinish = new AbsoluteBendpoint(preciseFinish);
            } else if (A_POINT.x == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_SOURCE) {
                // Move source
                Point preciseStart = new Point(start.getLocation().x, Math.min(F_POINT.y - LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT, S_POINT.y + deltaY));
                newStart = new AbsoluteBendpoint(preciseStart);
            } else {
                // Move edge
                Point preciseStart = new Point(start.getLocation().x, S_POINT.y + deltaY);
                newStart = new AbsoluteBendpoint(preciseStart);

                Point preciseFinish = new Point(finish.getLocation().x, F_POINT.y + deltaY);
                newFinish = new AbsoluteBendpoint(preciseFinish);
            }
        } else {
            if (A_POINT.x == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_SOURCE) {
                // Move source
                Point preciseStart = new Point(start.getLocation().x, Math.min(F_POINT.y - LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT, S_POINT.y + deltaY));
                newStart = new AbsoluteBendpoint(preciseStart);
            } else if (A_POINT.x == SequenceMessageEditPolicy.OBLIQUE_MESSAGE_MOVE_TARGET) {
                // Move target
                Point preciseFinish = new Point(finish.getLocation().x, Math.max(S_POINT.y + LayoutConstants.DEFAULT_MESSAGE_MIN_OBLIQUE_HEIGHT, F_POINT.y + deltaY));
                newFinish = new AbsoluteBendpoint(preciseFinish);
            } else {
                // Move edge
                Point preciseStart = new Point(start.getLocation().x, S_POINT.y + deltaY);
                newStart = new AbsoluteBendpoint(preciseStart);

                Point preciseFinish = new Point(finish.getLocation().x, F_POINT.y + deltaY);
                newFinish = new AbsoluteBendpoint(preciseFinish);
            }
        }

        bendpoints.clear();
        bendpoints.add(newStart);
        bendpoints.add(cursorReference);
        bendpoints.add(newFinish);
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
