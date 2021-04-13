/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.RectilinearEdgeUtil;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.osgi.framework.Version;

/**
 * A {@link AbstractRepresentationsFileMigrationParticipant} that repairs Edges with only one Bendpoint by setting a
 * minimum of two Bendpoints.
 * 
 * @author gplouhinec
 *
 */
public class RepairEdgesWithOneBendpointMigrationParticipant extends RepairGMFbendpointsMigrationParticipant {

    /**
     * The Sirius version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("14.5.0.202104070943"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected String getMessageMigrationParticipantTitle() {
        return Messages.RepairEdgesWithOneBendpointMigrationParticipant_title;
    }

    @Override
    protected String getMessageEdgesModified(DDiagram dDiagram) {
        return MessageFormat.format(Messages.RepairEdgesWithOneBendpointMigrationParticipant_edgesModified, dDiagram.getName());
    }

    /**
     * Check bend-points of a given edge and repair them if necessary.
     * 
     * @param edge
     *            the edge which contains bend-points to check
     * @return true if bend-points of the given edge have been modified, false otherwise
     */
    @Override
    protected boolean checkAndRepairBendpointsOfEdge(Edge edge) {
        boolean isEdgeModified = false;
        // compute Source and Target Reference point
        View source = edge.getSource();
        IdentityAnchor srcAnchor = edge.getSourceAnchor() instanceof IdentityAnchor ? (IdentityAnchor) edge.getSourceAnchor() : null;
        View target = edge.getTarget();
        IdentityAnchor tgtAnchor = edge.getTargetAnchor() instanceof IdentityAnchor ? (IdentityAnchor) edge.getTargetAnchor() : null;

        if (source != null && target != null) {
            if (edge.getBendpoints() instanceof RelativeBendpoints) {
                List<RelativeBendpoint> pointList = ((RelativeBendpoints) edge.getBendpoints()).getPoints();
                if (pointList.size() <= 1) {
                    // Edges with BracketEdgeStyle style should not be managed, they seem to work with a single
                    // bendpoint.
                    if (edge.getElement() instanceof DEdge && ((DEdge) edge.getElement()).getOwnedStyle() instanceof BracketEdgeStyle) {
                        return isEdgeModified;
                    }

                    Point srcRef = getAnchorPoint(srcAnchor, source);
                    Point tgtRef = getAnchorPoint(tgtAnchor, target);

                    Rectangle srcBounds = GMFHelper.getAbsoluteBounds(source).get();
                    Rectangle tgtBounds = GMFHelper.getAbsoluteBounds(target).get();

                    isEdgeModified = repairBendpointsOfEdge(edge, srcBounds, srcRef, tgtBounds, tgtRef);
                }
            }
        }
        return isEdgeModified;
    }

    /**
     * Return the relative coordinates of the anchor.
     * 
     * @param anchor
     *            anchor from which point is extract
     * @param view
     *            <code>View</code> that this anchor is associated with.
     * @return relative coordinates of the anchor.
     */
    private Point getAnchorPoint(IdentityAnchor anchor, View view) {
        Point result = null;
        if (anchor != null) {
            result = getAnchorPosition(anchor, view);
        } else {
            if (view instanceof Node) {
                Node node = (Node) view;
                Bounds bounds = (Bounds) node.getLayoutConstraint();
                result = new Point(bounds.getX(), bounds.getY());
            } else if (view instanceof Edge) {
                Edge edge = (Edge) view;
                Point sourcePoint = getAnchorPosition((IdentityAnchor) edge.getSourceAnchor(), edge);
                Point targetPoint = getAnchorPosition((IdentityAnchor) edge.getTargetAnchor(), edge);
                result = new Point((sourcePoint.x + targetPoint.x) / 2, (sourcePoint.y + targetPoint.y) / 2);
            }
        }
        return result;
    }

    /**
     * Compute and set a new list of bend-points used to define the edge.
     * 
     * @param edge
     *            the edge which owns bend-points to replace
     * @param srcBounds
     *            bounds of the figure of source edge
     * @param srcRef
     *            source point used as reference to compute bend-points
     * @param tgtBounds
     *            bounds of the figure of target edge
     * @param tgtRef
     *            target point used as reference to compute bend-points
     * @return true if bend-points of the given edge have been modified, false otherwise
     */
    @Override
    protected boolean repairBendpointsOfEdge(Edge edge, Rectangle srcBounds, Point srcRef, Rectangle tgtBounds, Point tgtRef) {
        boolean isEdgeModified = false;
        PointList newPointList = new PointList();

        // compute intersection of anchors line with bounds
        Point srcConnectionBendpoint = GraphicalHelper.getIntersection(srcRef, tgtRef, srcBounds, true, true).orElse(srcRef);
        Point tgtConnectionBendpoint = GraphicalHelper.getIntersection(srcRef, tgtRef, tgtBounds, false, true).orElse(tgtRef);

        if (srcConnectionBendpoint != null && tgtConnectionBendpoint != null) {
            isEdgeModified = true;
            EdgeQuery edgeQuery = new EdgeQuery(edge);
            Routing routingStyle = edgeQuery.getRoutingStyle();
            // Compute anchor logical coordinates
            if (Routing.RECTILINEAR_LITERAL.equals(routingStyle) && srcBounds != null && srcBounds.equals(tgtBounds)) {
                // Set a default first bendpoint position on the center of the east side of the source
                newPointList = RectilinearEdgeUtil.computeRectilinearBendpointsSameSourceAndTarget(srcBounds, srcBounds.getRight(), PositionConstants.EAST);
            } else if (Routing.RECTILINEAR_LITERAL.equals(routingStyle)) {
                newPointList = RectilinearEdgeUtil.computeRectilinearBendpoints(srcBounds, tgtBounds, srcConnectionBendpoint, tgtConnectionBendpoint);
            } else {
                newPointList.addPoint(srcConnectionBendpoint);
                newPointList.addPoint(tgtConnectionBendpoint);
            }
            newPointList = RectilinearEdgeUtil.normalizeToStraightLineTolerance(newPointList, 2);

            setNewBendPoints(edge, srcRef, tgtRef, newPointList);
        }
        return isEdgeModified;
    }
}
