/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.edge.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.RectilinearEdgeUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * A representation file migration to repair GMF bend-points of edge. Source and target connection points must belongs
 * to bounds of source and target edge figure. Moreover, if edge is rectilinear and not vertical neither horizontal,
 * edge should have at least three bend-points.
 * 
 * @author jmallet
 */
public class RepairGMFbendpointsMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The Sirius version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("12.1.0.201708031200"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            boolean isModified = false;
            StringBuilder sb = new StringBuilder(Messages.RepairGMFbendpointsMigrationParticipant_title);
            for (DView dView : dAnalysis.getOwnedViews()) {
                for (DDiagram dDiagram : Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DDiagram.class)) {
                    if ("DSemanticDiagramSpec".equals(dDiagram.getClass().getSimpleName())) { //$NON-NLS-1$
                        List<Edge> edgeList = getEdgeList(dDiagram);
                        boolean isEdgeModified = false;
                        for (Edge edge : edgeList) {
                            isEdgeModified = checkAndRepairBendpointsOfEdge(edge);
                        }
                        if (isEdgeModified) {
                            isModified = true;
                            sb.append(MessageFormat.format(Messages.RepairGMFbendpointsMigrationParticipant_edgesModified, dDiagram.getName()));
                        }
                    }
                }
            }
            if (isModified) {
                DiagramPlugin.getDefault().logInfo(sb.toString());
            }
        }
    }

    /**
     * Check bend-points of a given edge and repair them if necessary.
     * 
     * @param edge
     *            the edge which contains bend-points to check
     * @return true if bend-points of the given edge have been modified, false otherwise
     */
    private boolean checkAndRepairBendpointsOfEdge(Edge edge) {
        boolean isEdgeModified = false;
        // compute Source and Target Reference point
        View source = edge.getSource();
        IdentityAnchor srcAnchor = (IdentityAnchor) edge.getSourceAnchor();
        View target = edge.getTarget();
        IdentityAnchor tgtAnchor = (IdentityAnchor) edge.getTargetAnchor();

        if (source != null && target != null) {
            List<RelativeBendpoint> pointList = ((RelativeBendpoints) edge.getBendpoints()).getPoints();
            if (srcAnchor != null && tgtAnchor != null && pointList.size() == 2) {
                Point srcRef = getAnchorPosition(srcAnchor, source);
                Point tgtRef = getAnchorPosition(tgtAnchor, target);

                // Retrieve anchor bend-points
                RelativeBendpoint firstPoint = pointList.get(0);
                PrecisionPoint srcPoint = new PrecisionPoint(firstPoint.getSourceX() + srcRef.x, firstPoint.getSourceY() + srcRef.y);
                RelativeBendpoint lastPoint = pointList.get(pointList.size() - 1);
                PrecisionPoint tgtPoint = new PrecisionPoint(lastPoint.getTargetX() + tgtRef.x, lastPoint.getTargetY() + tgtRef.y);

                Rectangle srcBounds = GMFHelper.getAbsoluteBounds(source).get();
                Rectangle tgtBounds = GMFHelper.getAbsoluteBounds(target).get();

                EdgeQuery edgeQuery = new EdgeQuery(edge);
                Routing routingStyle = edgeQuery.getRoutingStyle();

                if (Routing.RECTILINEAR_LITERAL.equals(routingStyle)) {
                    if (srcPoint.x != tgtPoint.x && srcPoint.y != tgtPoint.y) {
                        // edge is not horizontal neither vertical
                        isEdgeModified = repairBendpointsOfEdge(edge, srcBounds, srcRef, tgtBounds, tgtRef);
                    }
                } else if (!isPointOnBounds(srcPoint, srcBounds) || !isPointOnBounds(tgtPoint, tgtBounds)) {
                    // source and target connection must belong to bounds
                    isEdgeModified = repairBendpointsOfEdge(edge, srcBounds, srcRef, tgtBounds, tgtRef);
                }
            }
        }
        return isEdgeModified;
    }

    /**
     * Compute and set new list of bend-points used to define edge.
     * 
     * @param edge
     *            the edge which own bend-points to replace
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
    private boolean repairBendpointsOfEdge(Edge edge, Rectangle srcBounds, Point srcRef, Rectangle tgtBounds, Point tgtRef) {
        boolean isEdgeModified = false;
        PointList newPointList = new PointList();

        // compute intersection of anchors line with bounds
        Option<Point> srcConnectionBendpoint = GraphicalHelper.getIntersection(srcRef, tgtRef, srcBounds, true);
        Option<Point> tgtConnectionBendpoint = GraphicalHelper.getIntersection(srcRef, tgtRef, tgtBounds, false);

        if (srcConnectionBendpoint.some() && tgtConnectionBendpoint.some()) {
            isEdgeModified = true;
            EdgeQuery edgeQuery = new EdgeQuery(edge);
            Routing routingStyle = edgeQuery.getRoutingStyle();
            // Compute anchor logical coordinates
            if (Routing.RECTILINEAR_LITERAL.equals(routingStyle) && srcBounds != null && srcBounds.equals(tgtBounds)) {
                // Set a default first bendpoint position on the center of the east side of the source
                newPointList = RectilinearEdgeUtil.computeRectilinearBendpointsSameSourceAndTarget(srcBounds, srcBounds.getRight(), PositionConstants.EAST);
            } else if (Routing.RECTILINEAR_LITERAL.equals(routingStyle)) {
                newPointList = RectilinearEdgeUtil.computeRectilinearBendpoints(srcBounds, tgtBounds, srcConnectionBendpoint.get(), tgtConnectionBendpoint.get());
            } else {
                newPointList.addPoint(srcConnectionBendpoint.get());
                newPointList.addPoint(tgtConnectionBendpoint.get());
            }
            newPointList = RectilinearEdgeUtil.normalizeToStraightLineTolerance(newPointList, 2);

            setNewBendPoints(edge, srcRef, tgtRef, newPointList);
        }
        return isEdgeModified;
    }

    /**
     * Set bend-points of edge to a new bend-points list.
     * 
     * @param edge
     *            the edge which own bend-points to replace
     * @param srcRef
     *            source point used as reference to compute bend-points
     * @param tgtRef
     *            target point used as reference to compute bend-points
     * @param newPointList
     *            the new bend-points list
     */
    private void setNewBendPoints(Edge edge, Point srcRef, Point tgtRef, PointList newPointList) {
        List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();

        int numOfPoints = newPointList.size();
        for (short i = 0; i < numOfPoints; i++) {
            Dimension s = newPointList.getPoint(i).getDifference(srcRef);
            Dimension t = newPointList.getPoint(i).getDifference(tgtRef);
            newBendpoints.add(new RelativeBendpoint(s.width, s.height, t.width, t.height));
        }

        RelativeBendpoints points = (RelativeBendpoints) edge.getBendpoints();
        points.setPoints(newBendpoints);
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
    private Point getAnchorPosition(IdentityAnchor anchor, View view) {
        String id = anchor.getId();
        PrecisionPoint relativeReference = SlidableAnchor.parseTerminalString(id);
        SlidableAnchor slidableAnchor = new SlidableAnchor(view, relativeReference);
        return slidableAnchor.getReferencePoint();
    }

    /**
     * Check if a given point belongs to bounds of figure.
     * 
     * 
     * @param point
     *            the point to check
     * @param bounds
     *            bounds of the figure where point is checked
     * @return true if the point belongs to the figure bound, false otherwise.
     */
    private boolean isPointOnBounds(PrecisionPoint point, Rectangle bounds) {
        boolean isAlignWithBounds = point.x == bounds.x || point.x == bounds.x + bounds.width || point.y == bounds.y || point.y == bounds.y + bounds.height();
        boolean isInsideBounds = point.y >= bounds.y && point.y <= bounds.y + bounds.height() && point.x >= bounds.x && point.x <= bounds.x + bounds.width;
        return isInsideBounds && isAlignWithBounds;
    }

    /**
     * Return a list of the edges contained in a representation.
     * 
     * @param representation
     *            the representation
     * @return a list of edges
     */
    protected List<Edge> getEdgeList(DDiagram representation) {
        List<Edge> edges = new ArrayList<Edge>();
        TreeIterator<EObject> eAllContents = representation.eAllContents();
        while (eAllContents.hasNext()) {
            EObject element = eAllContents.next();
            if (element instanceof Edge) {
                edges.add((Edge) element);
                // Do not loop inner this edge
                eAllContents.prune();
            }
        }
        return edges;
    }

}
