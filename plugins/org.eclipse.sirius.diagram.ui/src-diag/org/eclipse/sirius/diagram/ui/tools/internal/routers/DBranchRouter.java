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
 *    Obeo - some corrections 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.OrthogonalRouterUtilities;

/**
 * Specific AIR Tree Router to correct some anomalies from the GMF one.
 * 
 * (original author is sshaw)
 * 
 * @author ymortier
 */
public class DBranchRouter extends AbstractRouter {

    private final DTreeRouter tree;

    /**
     * Creates a new branch router.
     * 
     * @param tree
     *            the tree router.
     */
    public DBranchRouter(final DTreeRouter tree) {
        super();
        this.tree = tree;
    }

    /**
     * bla.
     * 
     * @see org.eclipse.draw2d.ConnectionRouter#route(org.eclipse.draw2d.Connection)
     * 
     *      case 1: connection has never been routed before and needs points to
     *      be populated. points.size() < 4
     * 
     *      case 2: user moved the trunk vertex of the connection by either
     *      moving the line attached to the target or second last target line
     * 
     *      case 3: user moved the source or target shape causing a layout of
     *      the connection.
     * 
     *      case 4: user moved the source line attached to the source shape.
     * 
     *      case 5: connection is being rerouted as a result of an invalidation
     *      from case 2.
     * 
     * @param conn
     *            the connection.
     * 
     */
    public void route(final Connection conn) {
        internalRoute(conn);
    }

    /**
     * @param conn
     */
    private void internalRoute(final Connection conn) {
        final Point ptTrunkLoc = getTrunkLocation(conn);

        getTree().setTrunkLocation(conn, ptTrunkLoc);

        final Point ptSourceLoc = getSourceLocation(conn, ptTrunkLoc);

        final PointList points = recreateBranch(conn, ptSourceLoc, ptTrunkLoc);
        conn.setPoints(points);
    }

    /**
     * getTrunkLocation Method to retrieve the trunk location in relative
     * coordinates.
     * 
     * @param conn
     *            Connection being routed
     * @return Point that is the trunk location in relative coordinates.
     */
    protected Point getTrunkLocation(final Connection conn) {

        final PointList points = getTree().getPointsFromConstraint(conn);
        final Point ptTrunkLoc = getTree().getTrunkLocation(conn); // default;

        // check valid again based on constraint
        if (getTree().isTreeBranch(conn, points)) {
            if (getTree().isTopDown(conn)) {
                ptTrunkLoc.setX(points.getPoint(3).x);
            } else {
                ptTrunkLoc.setY(points.getPoint(3).y);
            }

            if (getTree().isOrthogonalTreeBranch(conn, points)) {
                if (getTree().isTopDown(conn)) {
                    ptTrunkLoc.setY(points.getPoint(2).y);
                } else {
                    ptTrunkLoc.setX(points.getPoint(2).x);
                }
            }
        }

        return ptTrunkLoc;
    }

    /**
     * getSourceLocation Method to retrieve the source location where the
     * connection is connected to the source element.
     * 
     * @param conn
     *            Connection to be routed.
     * @param ptTrunkLoc
     *            Point trunk location in relative coordinates
     * @return Point source location in relative coordinates
     */
    public Point getSourceLocation(final Connection conn, final Point ptTrunkLoc) {

        /* source reference is in absolute */
        final Point ptSourceRef = conn.getSourceAnchor().getReferencePoint();

        /* get trunk location in absolute */
        Point absoluteTrunkLocation = ptTrunkLoc.getCopy();
        conn.translateToAbsolute(absoluteTrunkLocation);

        final boolean bTopDown = getTree().isTopDown(conn);

        /* branch offset is in absolute */
        final int branchOffset = bTopDown ? ptSourceRef.x : ptSourceRef.y;

        Point ref;

        if (bTopDown) {
            ref = new Point(branchOffset, absoluteTrunkLocation.y);
        } else {
            ref = new Point(absoluteTrunkLocation.x, branchOffset);
        }

        /*
         * the reference point should be in relative for
         * getOrthogonalLineSegToAnchorLoc method
         */
        conn.translateToRelative(ref);

        final LineSeg line = OrthogonalRouterUtilities.getOrthogonalLineSegToAnchorLoc(conn, conn.getSourceAnchor(), ref);
        return line.getOrigin();
    }

    /**
     * recreateBranch Utility method used to recreate the points list for the
     * branch connection given a trunk vertex location and a source attachpoint
     * location.
     * 
     * @param conn
     *            Connection used to do translate points to relative
     *            coordinates.
     * @param ptSourceLoc
     *            Point that is attached to the source node
     * @param ptTrunkLoc
     *            Point that is the vertex between the line attached to the
     *            target and the "shoulder" line that holds the individual
     *            source branches.
     * @return PointList that represents the full connection tree branch.
     */
    public PointList recreateBranch(final Connection conn, final Point ptSourceLoc, final Point ptTrunkLoc) {
        final PointList points = new PointList(4);
        final boolean bTopDown = getTree().isTopDown(conn);

        points.addPoint(new Point(ptSourceLoc));

        final Point pt2 = bTopDown ? new Point(ptSourceLoc.x, ptTrunkLoc.y) : new Point(ptTrunkLoc.x, ptSourceLoc.y);
        points.addPoint(pt2);

        points.addPoint(new Point(ptTrunkLoc));

        final LineSeg line = OrthogonalRouterUtilities.getOrthogonalLineSegToAnchorLoc(conn, conn.getTargetAnchor(), ptTrunkLoc);
        final Point ptTargetLoc = line.getOrigin();

        final Point pt4 = bTopDown ? new Point(ptTrunkLoc.x, ptTargetLoc.y) : new Point(ptTargetLoc.x, ptTrunkLoc.y);
        points.addPoint(pt4);

        return points;
    }

    /**
     * getTree Getter method for the container tree router.
     * 
     * @return Returns the tree.
     */
    protected DTreeRouter getTree() {
        return tree;
    }

}
