/******************************************************************************
 * Copyright (c) 2004, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - adaptation
 *    Maxime Porhel (Obeo) <maxime.porhel@obeo.fr> - Trac bug #1501 : Issues with tree routing style.
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ITreeConnection;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.OrthogonalRouter;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;

import com.google.common.collect.Lists;

/**
 * The tree router of a forest router.
 */
public class DTreeRouter extends BendpointConnectionRouter implements OrthogonalRouter {

    private static final int DEFAULT_TRUNK_HEIGHT = 16;

    /** The branch router. */
    private final DBranchRouter branchRouter = new DBranchRouter(this);

    /** All connections. */
    private final ArrayList connectionList = new ArrayList();

    private Dimension trunkVertex;

    private Orientation trunkOrientation;

    private boolean updatingPeers;

    /**
     * Describes the orientation of the tree.
     */
    static final class Orientation {

        /**
         * Constant for the top orientation
         */
        public static final Orientation TOP = new Orientation();

        /**
         * Constant for the bottom orientation
         */
        public static final Orientation BOTTOM = new Orientation();

        /**
         * Constant for the right orientation
         */
        public static final Orientation RIGHT = new Orientation();

        /**
         * Constant for the left orientation
         */
        public static final Orientation LEFT = new Orientation();

        private Orientation() {
            // Empty constructor
        }

        /**
         * getEdge Method to return the edge point of the given Rectangle
         * representative of the orientation value of the instance.
         * 
         * @param bounds
         *            Rectangle to retrieve the edge value from.
         * @return Point that is the edge of the rectangle for the orientation
         *         of this.
         */
        public Point getEdge(final Rectangle bounds) {
            Point result = bounds.getLeft();
            if (this == TOP) {
                result = bounds.getTop();
            } else if (this == BOTTOM) {
                result = bounds.getBottom();
            } else if (this == RIGHT) {
                result = bounds.getRight();
            }
            return result;
        }

    }

    /**
     * Create a new DTreeRouter.
     */
    public DTreeRouter() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.AbstractRouter#invalidate(org.eclipse.draw2d.Connection)
     */
    @Override
    public void invalidate(final Connection conn) {
        if (conn.getSourceAnchor() == null || conn.getSourceAnchor().getOwner() == null || conn.getTargetAnchor() == null || conn.getTargetAnchor().getOwner() == null) {
            return;
        }

        final ListIterator li = Lists.newArrayList(connectionList).listIterator();
        while (li.hasNext()) {
            final Connection connNext = (Connection) li.next();

            if (!trunkVertexEqual(connNext, conn)) {
                updateConstraint(connNext);
            }
        }
    }

    private boolean trunkVertexEqual(final Connection connMaster, final Connection connSlave) {
        final PointList cmPts = connMaster.getPoints();
        final PointList csPts = connSlave.getPoints();
        if (cmPts.size() > 2 && csPts.size() > 2) {
            return cmPts.getPoint(2).equals(csPts.getPoint(2));
        }

        return false;
    }

    private Rectangle getTargetAnchorRelativeBounds(final Connection conn) {
        final Rectangle bounds = conn.getTargetAnchor().getOwner().getBounds().getCopy();
        conn.getTargetAnchor().getOwner().translateToAbsolute(bounds);
        conn.translateToRelative(bounds);
        return bounds;
    }

    private Rectangle getSourceAnchorRelativeBounds(final Connection conn) {
        final Rectangle bounds = conn.getSourceAnchor().getOwner().getBounds().getCopy();
        conn.getSourceAnchor().getOwner().translateToAbsolute(bounds);
        conn.translateToRelative(bounds);
        return bounds;
    }

    /**
     * getTrunkLocation Method to retrieve the trunk location in relative
     * coordinates based on current tree state.
     * 
     * @param conn
     *            Connection being routed
     * @return Point that is the trunk location in relative coordinates.
     */
    public Point getTrunkLocation(final Connection conn) {
        final Dimension vertex = getTrunkVertex();
        final Point target = getTrunkOrientation().getEdge(getTargetAnchorRelativeBounds(conn));
        Point ptTrunkLoc = new Point(vertex.width, vertex.height);
        ptTrunkLoc = ptTrunkLoc.getTranslated(target);
        return ptTrunkLoc;
    }

    /**
     * setTrunkLocation Setter method to set the trunk location. Translates the
     * point into a relative point from the target edge.
     * 
     * @param conn
     *            Connection being routed
     * @param ptTrunkLoc
     *            Point that is the trunk location in relative coordinates.
     */
    public void setTrunkLocation(final Connection conn, final Point ptTrunkLoc) {
        final Point ptRelTrunkLoc = new Point(ptTrunkLoc);

        final Rectangle targetAnchorBounds = getTargetAnchorRelativeBounds(conn);

        // update orientation
        if (isTopDown(conn)) {
            if (ptTrunkLoc.y < targetAnchorBounds.getCenter().y) {
                setTrunkOrientation(Orientation.TOP);
            } else {
                setTrunkOrientation(Orientation.BOTTOM);
            }
        } else {
            if (ptTrunkLoc.x < targetAnchorBounds.getCenter().x) {
                setTrunkOrientation(Orientation.LEFT);
            } else {
                setTrunkOrientation(Orientation.RIGHT);
            }
        }

        final Point target = getTrunkOrientation().getEdge(targetAnchorBounds);

        final Dimension currentVertex = ptRelTrunkLoc.getDifference(target);
        setTrunkVertex(currentVertex);
    }

    /**
     * UpdateConstraint Updates the constraint value for the connection based on
     * the tree vertex.
     * 
     * @param conn
     *            Connection whose constraint is to be updated.
     */
    protected void updateConstraint(final Connection conn) {
        boolean update = conn != null && conn.getSourceAnchor() != null && conn.getTargetAnchor() != null;
        update = update && conn.getSourceAnchor().getOwner() != null && conn.getTargetAnchor().getOwner() != null;
        if (update) {
            if (isUpdatingPeers()) {
                return;
            }

            List bendpoints = (List) conn.getRoutingConstraint();
            if (bendpoints == null) {
                bendpoints = new ArrayList(conn.getPoints().size());
            }

            final Point sourceRefPoint = conn.getSourceAnchor().getReferencePoint();
            conn.translateToRelative(sourceRefPoint);

            final Point targetRefPoint = conn.getTargetAnchor().getReferencePoint();
            conn.translateToRelative(targetRefPoint);

            final Point ptTrunk = getTrunkLocation(conn);
            final Point ptSource = getBranchRouter().getSourceLocation(conn, ptTrunk);

            bendpoints.clear();
            final PointList pts = getBranchRouter().recreateBranch(conn, ptSource, ptTrunk);
            for (int i = 0; i < pts.size(); i++) {
                final Bendpoint bp = new AbsoluteBendpoint(pts.getPoint(i));
                bendpoints.add(bp);
            }

            setUpdatingPeers(true);

            try {
                setConstraint(conn, bendpoints);
                conn.invalidate();
                conn.validate();
            } finally {
                setUpdatingPeers(false);
            }
        }
    }

    /**
     * getPointsFromConstraint Utility method retrieve the PointList equivalent
     * of the bendpoint constraint set in the Connection.
     * 
     * @param conn
     *            Connection to retrieve the constraint from.
     * @return PointList list of points that is the direct equivalent of the set
     *         constraint.
     */
    public PointList getPointsFromConstraint(final Connection conn) {
        final List bendpoints = (List) conn.getRoutingConstraint();
        if (bendpoints == null) {
            return new PointList();
        }

        final PointList points = new PointList(bendpoints.size());
        for (int i = 0; i < bendpoints.size(); i++) {
            final Bendpoint bp = (Bendpoint) bendpoints.get(i);
            points.addPoint(bp.getLocation());
        }

        DTreeRouter.straightenPoints(points, MapModeUtil.getMapMode(conn).DPtoLP(3));
        return points;
    }

    /**
     * straightenPoints This is a simpler version of the.
     * 
     * @see updateIfNotRectilinear that simply ensures that the lines are
     *      horizontal or vertical without any intelligence in terms of shortest
     *      distance around a rectangle.
     * 
     * @param newLine
     *            PointList to check for rectilinear qualities and change if
     *            necessary.
     * @param tolerance
     *            int tolerance value by which points will be straightened in
     *            HiMetrics
     */
    protected static void straightenPoints(final PointList newLine, final int tolerance) {
        for (int i = 0; i < newLine.size() - 1; i++) {
            final Point ptCurrent = newLine.getPoint(i);
            final Point ptNext = newLine.getPoint(i + 1);

            final int xDelta = Math.abs(ptNext.x - ptCurrent.x);
            final int yDelta = Math.abs(ptNext.y - ptCurrent.y);

            if (xDelta < yDelta) {
                if (xDelta > tolerance) {
                    return;
                }
                if (i == newLine.size() - 2) {
                    // The last point is more important than the other (not
                    // change the end of the line)
                    ptCurrent.setX(ptNext.x);
                } else {
                    ptNext.setX(ptCurrent.x);
                }
            } else {
                if (yDelta > tolerance) {
                    return;
                }
                if (i == newLine.size() - 2) {
                    // The last point is more important than the other (not
                    // change the end of the line)
                    ptCurrent.setY(ptNext.y);
                } else {
                    ptNext.setY(ptCurrent.y);
                }
            }

            newLine.setPoint(ptNext, i + 1);
        }
    }

    /**
     * Returns the branch router in the chain.
     * 
     * @return The getBranchRouter router
     * 
     */
    protected DBranchRouter getBranchRouter() {
        return branchRouter;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.BendpointConnectionRouter#remove(org.eclipse.draw2d.Connection)
     */
    @Override
    public void remove(final Connection conn) {
        if (conn.getSourceAnchor() == null || conn.getTargetAnchor() == null) {
            return;
        }

        final int index = connectionList.indexOf(conn);
        connectionList.remove(conn);
        for (int i = index + 1; i < connectionList.size(); i++) {
            ((Connection) connectionList.get(i)).revalidate();
        }

        getBranchRouter().remove(conn);
    }

    /**
     * isTopDown Utility method to determine if the connection should routed in
     * a top-down fashion or in a horizontal fashion.
     * 
     * @param conn
     *            Connection to query
     * @return boolean true if connection should be routed top-down, false
     *         otherwise.
     */
    public boolean isTopDown(final Connection conn) {
        if (conn instanceof ITreeConnection) {
            return ((ITreeConnection) conn).getOrientation().equals(ITreeConnection.Orientation.VERTICAL);
        }
        return true;
    }

    /**
     * checkTrunkVertex Method to initialize the trunk vertex to a default value
     * if not already set
     * 
     * @param conn
     *            Connection to be routed.
     */
    private void checkTrunkVertex(final Connection conn) {
        if (getTrunkVertex() == null) {
            final Rectangle sourceRect = getSourceAnchorRelativeBounds(conn);
            final Rectangle targetRect = getTargetAnchorRelativeBounds(conn);

            final Dimension defaultTrunk = new Dimension(0, DEFAULT_TRUNK_HEIGHT);
            conn.translateToRelative(defaultTrunk);

            if (isTopDown(conn)) {
                if (sourceRect.getCenter().y < targetRect.getCenter().y) {
                    setTrunkVertex(new Dimension(0, -defaultTrunk.height));
                    setTrunkOrientation(Orientation.TOP);
                } else {
                    setTrunkVertex(new Dimension(0, defaultTrunk.height));
                    setTrunkOrientation(Orientation.BOTTOM);
                }
            } else {
                if (sourceRect.getCenter().x < targetRect.getCenter().x) {
                    setTrunkVertex(new Dimension(-defaultTrunk.height, 0));
                    setTrunkOrientation(Orientation.LEFT);
                } else {
                    setTrunkVertex(new Dimension(defaultTrunk.height, 0));
                    setTrunkOrientation(Orientation.RIGHT);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.BendpointConnectionRouter#route(org.eclipse.draw2d.Connection)
     */
    @Override
    public void route(final Connection conn) {
        internalRoute(conn);
    }

    /**
     * @param conn
     */
    private void internalRoute(final Connection conn) {
        if (conn.getSourceAnchor() == null || conn.getSourceAnchor().getOwner() == null || conn.getTargetAnchor() == null || conn.getTargetAnchor().getOwner() == null) {
            super.route(conn);
            return;
        }

        if (!connectionList.contains(conn)) {
            connectionList.add(conn);
        }

        checkTrunkVertex(conn);

        getBranchRouter().route(conn);
        invalidate(conn);
    }

    /**
     * Returns the trunk vertex.
     * 
     * @return Returns the truckVertex.
     */
    protected Dimension getTrunkVertex() {
        return trunkVertex;
    }

    /**
     * Sets the trunk vertex.
     * 
     * @param trunkVertex
     *            The trunkVertex to set.
     */
    protected void setTrunkVertex(final Dimension trunkVertex) {
        this.trunkVertex = trunkVertex;
    }

    /**
     * Returns the trunk orientation.
     * 
     * @return Returns the trunkOrientation.
     */
    protected Orientation getTrunkOrientation() {
        return trunkOrientation;
    }

    /**
     * Sets the trunk orientation.
     * 
     * @param trunkOrientation
     *            The trunkOrientation to set.
     */
    protected void setTrunkOrientation(final Orientation trunkOrientation) {
        this.trunkOrientation = trunkOrientation;
    }

    /**
     * Utility method to determine if the given set of points conforms to the
     * constraints of being an orthogonal connection tree-branch. 1. Points size
     * must be 4. 2. Source point resides with-in boundary of source shape based
     * on orientation 3. Target point resides with-in boundary of target shape
     * based on orientation 4. Middle line is perpendicular to the 2 end lines.
     * 
     * @param conn
     *            the <code>Connection</code> to test
     * @param points
     *            <code>PointList</code> to test constraints against
     * @return <code>boolean</code> <code>true</code> if points represent valid
     *         orthogaonl tree branch, <code>false</code> otherwise.
     */
    public boolean isOrthogonalTreeBranch(final Connection conn, final PointList points) {
        boolean result = false;
        if (isTreeBranch(conn, points)) {
            final LineSeg branch = new LineSeg(points.getPoint(0), points.getPoint(1));
            final LineSeg trunkShoulder = new LineSeg(points.getPoint(1), points.getPoint(2));
            final LineSeg trunk = new LineSeg(points.getPoint(2), points.getPoint(3));

            if (isTopDown(conn)) {
                result = branch.isVertical() && trunkShoulder.isHorizontal() && trunk.isVertical();
            } else {
                result = branch.isHorizontal() && trunkShoulder.isVertical() && trunk.isHorizontal();
            }
        }

        return result;
    }

    /**
     * Utility method to determine if the given set of points conforms to the
     * constraints of being a connection tree-branch. 1. Points size must be 4.
     * 2. Source point resides with-in boundary of source shape based on
     * orientation 3. Target point resides with-in boundary of target shape
     * based on orientation
     * 
     * @param conn
     *            the <code>Connection</code> to test
     * @param points
     *            the <code>PointList</code> to test constraints against
     * @return <code>boolean</code> <code>true</code> if points represent valid
     *         tree branch, <code>false</code> otherwise.
     */
    public boolean isTreeBranch(final Connection conn, final PointList points) {
        boolean result = false;
        if (points.size() == 4) {
            // just check if ends are with-in the owner bounding box
            final Rectangle targetBounds = getTargetAnchorRelativeBounds(conn);
            final Rectangle sourceBounds = getSourceAnchorRelativeBounds(conn);

            if (isTopDown(conn)) {
                result = (points.getPoint(0).x > sourceBounds.x && points.getPoint(0).x < sourceBounds.x + sourceBounds.width)
                        && (points.getPoint(3).x > targetBounds.x && points.getPoint(3).x < targetBounds.x + targetBounds.width);
            } else {
                result = (points.getPoint(0).y > sourceBounds.y && points.getPoint(0).y < sourceBounds.y + sourceBounds.height)
                        && (points.getPoint(3).y > targetBounds.y && points.getPoint(3).y < targetBounds.y + targetBounds.height);
            }
        }

        return result;
    }

    /**
     * Returns the updating peers.
     * 
     * @return Returns the updatingPeers.
     */
    protected boolean isUpdatingPeers() {
        return updatingPeers;
    }

    /**
     * Set the updating peers.
     * 
     * @param updatingPeers
     *            The updatingPeers to set.
     */
    protected void setUpdatingPeers(final boolean updatingPeers) {
        this.updatingPeers = updatingPeers;
    }

}
