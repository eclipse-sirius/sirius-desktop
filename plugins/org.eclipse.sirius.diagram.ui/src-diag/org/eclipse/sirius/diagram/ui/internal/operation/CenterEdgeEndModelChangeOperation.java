/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation - The code from org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ConnectionLayerEx;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.OrthogonalRouterUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.business.internal.query.DEdgeQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.ui.IEditorPart;

/**
 * A Model Change Operation to center the edge source and/or target. The
 * operation will compute the edge anchor and bendpoints according to the
 * {@link EdgeStyle#isSourceCentered()} and {@link EdgeStyle#isTargetCentered()}
 * values.
 * 
 * @author Florian Barbin
 * 
 */
public class CenterEdgeEndModelChangeOperation extends AbstractModelChangeOperation<Void> {

    private Edge edge;

    private Point existingSourceAnchorAbsoluteLocation;

    private Point existingTargetAnchorAbsoluteLocation;

    private Point newTargetAnchorAbsoluteLocation;

    private Point newSourceAnchorAbsoluteLocation;

    private ConnectionEditPart connectionEditPart;

    private Connection connection;

    public CenterEdgeEndModelChangeOperation(ConnectionEditPart connectionEditPart, Edge edge) {
        this(edge);
        this.connectionEditPart = connectionEditPart;
        this.connection = (Connection) connectionEditPart.getFigure();
    }

    public CenterEdgeEndModelChangeOperation(Edge edge) {
        this.edge = edge;
    }

    @Override
    public Void execute() {
        EObject element = edge.getElement();
        if (element instanceof DEdge) {
            DEdgeQuery query = new DEdgeQuery((DEdge) element);

            // we do not handle Tree routing style
            if (!(query.getRouting().getLiteral() == Routing.TREE_LITERAL.getLiteral())) {
                if (isEdgeSourceCentered() && isEdgeTargetCentered()) {

                    centerEdgeEnds(CenteringStyle.BOTH);
                } else if (isEdgeSourceCentered()) {
                    centerEdgeEnds(CenteringStyle.SOURCE);
                }

                else if (isEdgeTargetCentered()) {
                    centerEdgeEnds(CenteringStyle.TARGET);
                }
            }
        }
        return null;
    }

    /**
     * Center the connection end (Source, Target or Both) according to the
     * center value.
     * 
     * @param center
     *            the {@link CenteringStyle} value.
     */
    private void centerEdgeEnds(CenteringStyle center) {

        Bendpoints bendpoints = edge.getBendpoints();

        if (bendpoints instanceof RelativeBendpoints) {
            View edgeSourceView = edge.getSource();
            View edgeTargetView = edge.getTarget();

            if (edgeSourceView instanceof Node && edgeTargetView instanceof Node) {

                // We get the edge source and target nodes absolute bounds to
                // compute absolute anchors coordinates
                Option<Rectangle> sourceBounds = GMFHelper.getAbsoluteBounds(edgeSourceView);
                Option<Rectangle> targetBounds = GMFHelper.getAbsoluteBounds(edgeTargetView);

                if (sourceBounds.some() && targetBounds.some()) {

                    // Calculate the existing anchors absolute location
                    retrieveAndSetExistingAnchorsAbsoluteLocation(sourceBounds.get(), targetBounds.get());

                    // Compute the new anchors absolute coordinates according to
                    // the center value.
                    computeAndSetNewAnchorsAbsoluteCoordinates(sourceBounds.get(), targetBounds.get(), center);

                    DEdgeQuery query = new DEdgeQuery((DEdge) edge.getElement());
                    PointList newPointList = new PointList();
                    // Compute the existing bendpoints absolute location
                    newPointList = getAbsolutePointList((RelativeBendpoints) bendpoints);
                    if (!(query.getRouting().getLiteral() == Routing.RECTILINEAR_LITERAL.getLiteral())) {
                        // The default case of straight edge:
                        handleStraightCase(center, sourceBounds.get(), targetBounds.get(), newPointList);
                    } else {
                        // the rectilinear case
                        handleRectilinearCase(center, sourceBounds.get(), targetBounds.get(), newPointList);
                    }

                    GMFNotationUtilities.setGMFBendpoints(edge, newPointList, newSourceAnchorAbsoluteLocation, newTargetAnchorAbsoluteLocation);
                }
            }
        }

    }

    /**
     * Deal with the straight edge case.
     * 
     * @param center
     *            the {@link Center} value: {@link Center#SOURCE}
     *            {@link Center#TARGET} or {@link Center#BOTH}.
     * @param sourceBounds
     *            the source figure bounds.
     * @param targetBounds
     *            the target figure bounds.
     * @param existingPointList
     *            the existing point list before centering any connection end.
     */
    private void handleStraightCase(CenteringStyle center, Rectangle sourceBounds, Rectangle targetBounds, PointList existingPointList) {
        Point sourceLineOrigin = newTargetAnchorAbsoluteLocation;
        Point targetLineOrigin = newSourceAnchorAbsoluteLocation;
        Point sourceLineTerminus = newSourceAnchorAbsoluteLocation;
        Point targetLineTerminus = newTargetAnchorAbsoluteLocation;

        // In case of edge with more than 2 bendpoints, we just
        // compute the last segment coordinates.
        if (existingPointList.size() > 2) {
            sourceLineOrigin = existingPointList.getPoint(1);
            targetLineOrigin = existingPointList.getPoint(existingPointList.size() - 2);
        }
        Option<Point> sourceConnectionPoint = Options.newNone();
        Option<Point> targetConnectionPoint = Options.newNone();

        if (center == CenteringStyle.BOTH || center == CenteringStyle.SOURCE) {
            sourceConnectionPoint = GraphicalHelper.getIntersection(sourceLineOrigin, sourceLineTerminus, sourceBounds, false);
        }
        if (center == CenteringStyle.BOTH || center == CenteringStyle.TARGET) {
            targetConnectionPoint = GraphicalHelper.getIntersection(targetLineOrigin, targetLineTerminus, targetBounds, false);
        }

        if (sourceConnectionPoint.some() || targetConnectionPoint.some()) {

            if (sourceConnectionPoint.some()) {
                existingPointList.setPoint(sourceConnectionPoint.get(), 0);
                centerSourceAnchor();
            }

            if (targetConnectionPoint.some()) {
                existingPointList.setPoint(targetConnectionPoint.get(), existingPointList.size() - 1);
                centerTargetAnchor();
            }
        }
    }

    /**
     * Deal with the rectilinear edge case.
     * 
     * @param center
     *            the {@link CenteringStyle} value:
     *            {@link CenteringStyle#SOURCE} {@link CenteringStyle#TARGET} or
     *            {@link CenteringStyle#BOTH}.
     * @param sourceBounds
     *            the source figure bounds.
     * @param targetBounds
     *            the target figure bounds.
     * @param existingPointList
     *            the existing point list before centering any connection end.
     */
    private void handleRectilinearCase(CenteringStyle center, Rectangle sourceBounds, Rectangle targetBounds, PointList existingPointList) {

        int sourceAnchorRelativeLocation = PositionConstants.NONE;
        int targetAnchorRelativeLocation = PositionConstants.NONE;
        PointList rectilinear = null;

        // If the connection is available (the edge already exist) we retrieve
        // the rectilinear bendpoints.
        if (connection != null) {
            rectilinear = getRectilinearPointListFromConnection();

            sourceAnchorRelativeLocation = RectilinearHelper.getAnchorOffRectangleDirection(rectilinear.getFirstPoint(), sourceBounds);
            targetAnchorRelativeLocation = RectilinearHelper.getAnchorOffRectangleDirection(rectilinear.getLastPoint(), targetBounds);

        } else {
            rectilinear = existingPointList.getCopy();

            // GMF bendpoints are not reliable: in some cases they are
            // completely far away from what draw2D is really displaying. If
            // there is only two GMF bendpoints, we can compute them with the
            // information we know.
            if (rectilinear.size() == 2) {
                computePointListByIntersections(rectilinear, sourceBounds, targetBounds);
            }

            sourceAnchorRelativeLocation = RectilinearHelper.getAnchorOffRectangleDirection(rectilinear.getFirstPoint(), sourceBounds);
            targetAnchorRelativeLocation = RectilinearHelper.getAnchorOffRectangleDirection(rectilinear.getLastPoint(), targetBounds);

            RectilinearHelper.transformToRectilinear(rectilinear, sourceAnchorRelativeLocation, targetAnchorRelativeLocation);

        }
        if (rectilinear.size() >= 2) {
            if (center == CenteringStyle.BOTH || center == CenteringStyle.SOURCE) {
                handleSourceRectilinearRoutingStyle(sourceBounds, rectilinear, sourceAnchorRelativeLocation);
            }
            if (center == CenteringStyle.BOTH || center == CenteringStyle.TARGET) {
                handleTargetRectilinearRoutingStyle(targetBounds, rectilinear, targetAnchorRelativeLocation);
            }

            existingPointList.removeAllPoints();
            existingPointList.addAll(rectilinear);
        }
    }

    private void computePointListByIntersections(PointList rectilinear, Rectangle sourceBounds, Rectangle targetBounds) {
        Option<Point> sourceConnectionPoint = GraphicalHelper.getIntersection(existingSourceAnchorAbsoluteLocation, existingTargetAnchorAbsoluteLocation, sourceBounds, false);
        Option<Point> targetConnectionPoint = GraphicalHelper.getIntersection(existingSourceAnchorAbsoluteLocation, existingTargetAnchorAbsoluteLocation, targetBounds, false);
        if (sourceConnectionPoint.some() && targetConnectionPoint.some()) {
            rectilinear.removeAllPoints();
            rectilinear.addPoint(sourceConnectionPoint.get());
            rectilinear.addPoint(targetConnectionPoint.get());
        }
    }

    private void handleSourceRectilinearRoutingStyle(Rectangle figureBounds, PointList rectilinear, int sourceAnchorRelativeLocation) {

        Point newConnectionPoint = rectilinear.getFirstPoint().getCopy();
        Point secondFromSrc = rectilinear.getPoint(1);
        switch (sourceAnchorRelativeLocation) {
        case PositionConstants.WEST:
        case PositionConstants.EAST:
            newConnectionPoint.setY(newSourceAnchorAbsoluteLocation.y());
            secondFromSrc.setY(newSourceAnchorAbsoluteLocation.y());
            break;

        case PositionConstants.NORTH:
        case PositionConstants.SOUTH:
            newConnectionPoint.setX(newSourceAnchorAbsoluteLocation.x());
            secondFromSrc.setX(newSourceAnchorAbsoluteLocation.x());
            break;

        default:
            // this case should not happen.
            break;
        }

        rectilinear.setPoint(newConnectionPoint, 0);
        rectilinear.setPoint(secondFromSrc, 1);
        centerSourceAnchor();

    }

    private void handleTargetRectilinearRoutingStyle(Rectangle figureBounds, PointList rectilinear, int targetAnchorRelativeLocation) {

        Point newConnectionPoint = rectilinear.getLastPoint().getCopy();
        Point secondFromTgt = rectilinear.getPoint(rectilinear.size() - 2);
        switch (targetAnchorRelativeLocation) {
        case PositionConstants.WEST:
        case PositionConstants.EAST:
            newConnectionPoint.setY(newTargetAnchorAbsoluteLocation.y());
            secondFromTgt.setY(newTargetAnchorAbsoluteLocation.y());
            break;

        case PositionConstants.NORTH:
        case PositionConstants.SOUTH:
            newConnectionPoint.setX(newTargetAnchorAbsoluteLocation.x());
            secondFromTgt.setX(newTargetAnchorAbsoluteLocation.x());
            break;

        default:
            // this case should not happen.
            break;
        }

        rectilinear.setPoint(newConnectionPoint, rectilinear.size() - 1);
        rectilinear.setPoint(secondFromTgt, rectilinear.size() - 2);
        centerTargetAnchor();
    }

    private void computeAndSetNewAnchorsAbsoluteCoordinates(Rectangle sourceBounds, Rectangle targetBounds, CenteringStyle center) {

        newSourceAnchorAbsoluteLocation = existingSourceAnchorAbsoluteLocation;
        newTargetAnchorAbsoluteLocation = existingTargetAnchorAbsoluteLocation;

        // if the source is centered, we compute the new anchor coordinates
        // source anchor
        if (center == CenteringStyle.BOTH || center == CenteringStyle.SOURCE) {
            PrecisionPoint newSourceAnchor = new PrecisionPoint(0.5, 0.5);
            newSourceAnchorAbsoluteLocation = getAbsoluteAnchorCoordinates(sourceBounds, newSourceAnchor);
        }

        // if the target is centered, we compute the new anchor coordinates
        if (center == CenteringStyle.BOTH || center == CenteringStyle.TARGET) {

            PrecisionPoint newTargetAnchor = new PrecisionPoint(0.5, 0.5);
            newTargetAnchorAbsoluteLocation = getAbsoluteAnchorCoordinates(targetBounds, newTargetAnchor);
        }

    }

    /**
     * Retrieve the absolute coordinates of bendpoints.
     * 
     * @param bendpoints
     *            the bendpoints.
     * @return the absolute bendpoint coordinates list.
     */
    private PointList getAbsolutePointList(RelativeBendpoints bendpoints) {

        PointList pointList = new PointList();

        Option<PointList> option = getAbsolutePointListFromConnection();
        if (option.some()) {
            pointList = option.get();
        } else {
            List<RelativeBendpoint> relativeBendpoints = bendpoints.getPoints();
            for (int i = 0; i < relativeBendpoints.size(); i++) {
                float weight = i / ((float) relativeBendpoints.size() - 1);
                Point absoluteLocation = getLocation(existingSourceAnchorAbsoluteLocation, existingTargetAnchorAbsoluteLocation, relativeBendpoints.get(i), weight);
                pointList.addPoint(absoluteLocation);
            }
        }

        return pointList;
    }

    private void retrieveAndSetExistingAnchorsAbsoluteLocation(Rectangle sourceBounds, Rectangle targetBounds) {
        PrecisionPoint sourcePrecisionPoint = new PrecisionPoint(0.5, 0.5);
        PrecisionPoint targetPrecisionPoint = new PrecisionPoint(0.5, 0.5);

        Anchor sourceAnchor = edge.getSourceAnchor();
        Anchor targetAnchor = edge.getTargetAnchor();

        if (sourceAnchor instanceof IdentityAnchor) {
            sourcePrecisionPoint = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) sourceAnchor).getId());
        }

        if (targetAnchor instanceof IdentityAnchor) {
            targetPrecisionPoint = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) targetAnchor).getId());
        }

        existingSourceAnchorAbsoluteLocation = getAbsoluteAnchorCoordinates(sourceBounds, sourcePrecisionPoint);

        existingTargetAnchorAbsoluteLocation = getAbsoluteAnchorCoordinates(targetBounds, targetPrecisionPoint);
    }

    /**
     * Set the source anchor at (0.5, 0.5).
     */
    private void centerSourceAnchor() {
        IdentityAnchor a = NotationFactory.eINSTANCE.createIdentityAnchor();
        a.setId(GMFNotationUtilities.getTerminalString(0.5d, 0.5d));
        edge.setSourceAnchor(a);
    }

    /**
     * Set the target anchor at (0.5, 0.5).
     */
    private void centerTargetAnchor() {
        IdentityAnchor a = NotationFactory.eINSTANCE.createIdentityAnchor();
        a.setId(GMFNotationUtilities.getTerminalString(0.5d, 0.5d));
        edge.setTargetAnchor(a);
    }

    /**
     * Compute the anchor absolute coordinates following the figure bounds and
     * the anchor location.
     * 
     * @param absoluteBounds
     *            the figure absolute bounds.
     * @param precisionPoint
     *            the anchor location.
     * @return the anchor absolute location.
     */
    private Point getAbsoluteAnchorCoordinates(Rectangle absoluteBounds, PrecisionPoint precisionPoint) {
        return new Point((int) (absoluteBounds.x() + Math.round(absoluteBounds.width() * precisionPoint.preciseX())), (int) (absoluteBounds.y() + Math.round(absoluteBounds.height()
                * precisionPoint.preciseY())));

    }

    private boolean isEdgeSourceCentered() {
        return isCentered(true);
    }

    private boolean isEdgeTargetCentered() {
        return isCentered(false);
    }

    private boolean isCentered(boolean source) {
        boolean centered = false;
        EObject element = edge.getElement();
        if (element instanceof DEdge) {
            EdgeStyle edgeStyle = ((DEdge) element).getOwnedStyle();
            CenteringStyle centeringStyle = edgeStyle.getCentered();
            if (source) {
                centered = centeringStyle == CenteringStyle.BOTH || centeringStyle == CenteringStyle.SOURCE;
            } else {
                centered = centeringStyle == CenteringStyle.BOTH || centeringStyle == CenteringStyle.TARGET;
            }
        }
        return centered;
    }

    /**
     * Inspired by org.eclipse.draw2d.RelativeBendpoint.getLocation() to compute
     * the absolute bendpoint location as draw2d do.
     */
    private Point getLocation(Point sourceAnchor, Point targetAnchor, RelativeBendpoint gmfRelativeBendpoint, float weight) {
        PrecisionPoint a1 = new PrecisionPoint(sourceAnchor);
        PrecisionPoint a2 = new PrecisionPoint(targetAnchor);

        return new PrecisionPoint((a1.preciseX() + gmfRelativeBendpoint.getSourceX()) * (1.0 - weight) + weight * (a2.preciseX() + gmfRelativeBendpoint.getTargetX()),
                (a1.preciseY() + gmfRelativeBendpoint.getSourceY()) * (1.0 - weight) + weight * (a2.preciseY() + gmfRelativeBendpoint.getTargetY()));
    }

    private Option<PointList> getAbsolutePointListFromConnection() {
        setConnectionIfNull();
        Option<PointList> pointList = Options.newNone();
        if (connection != null) {
            pointList = Options.newSome(connection.getPoints().getCopy());
        }

        return pointList;
    }

    /**
     * We try to retrieve the edge connection figure.
     */
    private void setConnectionIfNull() {
        if (connection != null) {
            return;
        }
        Option<GraphicalEditPart> option = Options.newNone();
        final IEditorPart editorPart = EclipseUIUtil.getActiveEditor();
        if (editorPart instanceof DiagramEditor) {
            option = GMFHelper.getGraphicalEditPart(edge, (DiagramEditor) editorPart);

        }
        // If the active editor is not a DiagramEditor, that means the focus is
        // on a VSM or an other editor that triggered this operation
        else {

            List diagramEditors = EditorService.getInstance().getRegisteredEditorParts();
            for (Iterator it = diagramEditors.iterator(); it.hasNext();) {
                Object object = it.next();
                if (object instanceof DiagramEditor) {
                    option = GMFHelper.getGraphicalEditPart(edge, (DiagramEditor) object);
                }
                if (option.some()) {
                    break;
                }
            }

        }

        if (option.some()) {
            GraphicalEditPart editPart = option.get();
            if (editPart instanceof DEdgeEditPart) {
                connectionEditPart = (DEdgeEditPart) editPart;
                connection = ((DEdgeEditPart) editPart).getConnectionFigure();
            }
        }
    }

    /**
     * In the case of an existing edge that was oblique and is now transformed
     * to rectilinear, we need to anticipate the future rectilinear bendpoints
     * to next center the source or target connection ends.
     * 
     * @return a rectilinear PointList.
     */
    @SuppressWarnings("restriction")
    private PointList getRectilinearPointListFromConnection() {
        ConnectionRouter oldConnectionRouter = connection.getConnectionRouter();
        boolean needToRetrieveOldRouter = false;

        // If the router is already a rectilinear one, we don't need to change
        // it.
        if (!(oldConnectionRouter instanceof RectilinearRouter)) {
            ConnectionRouter connectionRouter = null;
            LayerManager layerManager = LayerManager.Helper.find(connectionEditPart);
            if (layerManager != null) {
                ConnectionLayer cLayer = (ConnectionLayer) layerManager.getLayer(LayerConstants.CONNECTION_LAYER);
                if (cLayer instanceof ConnectionLayerEx) {
                    connectionRouter = ((ConnectionLayerEx) cLayer).getRectilinearRouter();
                }
            }
            if (connectionRouter == null) {
                connectionRouter = new RectilinearRouter();
            }

            connection.setConnectionRouter(connectionRouter);
            needToRetrieveOldRouter = true;
        }
        connection.getConnectionRouter().route(connection);
        PointList pointList = connection.getPoints().getCopy();

        // We restore the old router once we got the rectilinear point
        // list.
        if (needToRetrieveOldRouter) {
            connection.setConnectionRouter(oldConnectionRouter);
            connection.getConnectionRouter().route(connection);
        }

        return pointList;
    }

    /**
     * This class contains code from
     * <code>org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
     * RectilinearRouter</code>.
     * 
     * @author Florian Barbin
     * 
     */
    private static class RectilinearHelper {
        /**
         * Inspire from org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
         * RectilinearRouter.
         * 
         * @param existingPointList
         */
        @SuppressWarnings("restriction")
        private static void transformToRectilinear(PointList existingPointList, int sourceAnchorRelativeLocation, int targetAnchorRelativeLocation) {
            Point offStart = existingPointList.getFirstPoint();
            Point offEnd = existingPointList.getLastPoint();
            Dimension offsetDim = offStart.getDifference(offEnd).scale(0.5);
            offStart.translate(getTranslationValue(sourceAnchorRelativeLocation, Math.abs(offsetDim.width), Math.abs(offsetDim.height)));
            offEnd.translate(getTranslationValue(targetAnchorRelativeLocation, Math.abs(offsetDim.width), Math.abs(offsetDim.height)));
            existingPointList.insertPoint(offStart, 1);
            existingPointList.insertPoint(offEnd, 2);

            int offSourceDirection = getOffShapeDirection(sourceAnchorRelativeLocation);
            int offTargetDirection = getOffShapeDirection(targetAnchorRelativeLocation);

            /*
             * Convert the polyline to rectilinear. If the connection is
             * rectilinear already then the connection will remain as it is.
             */
            OrthogonalRouterUtilities.transformToOrthogonalPointList(existingPointList, offSourceDirection, offTargetDirection);
            removeRedundantPoints(existingPointList);

        }

        /**
         * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
         * RectilinearRouter. Returns a translation dimension for the anchor
         * point. Translation dimension translates the anchor point off the
         * shape. The off shape direction is specified by the relative to the
         * shape geographic position of the anchor
         * 
         * @param position
         *            relative to the shape geographic position of the anchor
         * @param xFactorValue
         *            translation value along x-axis
         * @param yFactorValue
         *            translation value along y-axis
         * @return
         */
        private static Dimension getTranslationValue(int position, int xFactorValue, int yFactorValue) {
            Dimension translationDimension = new Dimension();
            if (position == PositionConstants.EAST) {
                translationDimension.width = xFactorValue;
            } else if (position == PositionConstants.SOUTH) {
                translationDimension.height = yFactorValue;
            } else if (position == PositionConstants.WEST) {
                translationDimension.width = -xFactorValue;
            } else if (position == PositionConstants.NORTH) {
                translationDimension.height = -yFactorValue;
            }
            return translationDimension;
        }

        /**
         * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
         * RectilinearRouter. Determines the relative to rectangle geographic
         * location of a point. Example: If shape is closer to the the top edge
         * of the rectangle location would be north. Method used to determine
         * which side of shape's bounding rectangle is closer to connection's
         * anchor point. All geometric quantities must be in the same coordinate
         * system.
         * 
         * @param anchorPoint
         *            location of the anchor point
         * @param rect
         *            bounding rectangle of the shape
         * @return
         */
        private static int getAnchorOffRectangleDirection(Point anchorPoint, Rectangle rect) {
            int position = PositionConstants.NORTH;
            int criteriaValue = Math.abs(anchorPoint.y - rect.y);
            int tempCriteria = Math.abs(anchorPoint.y - rect.y - rect.height);
            if (tempCriteria < criteriaValue) {
                criteriaValue = tempCriteria;
                position = PositionConstants.SOUTH;
            }

            tempCriteria = Math.abs(anchorPoint.x - rect.x);
            if (tempCriteria < criteriaValue) {
                criteriaValue = tempCriteria;
                position = PositionConstants.WEST;
            }

            tempCriteria = Math.abs(anchorPoint.x - rect.x - rect.width);
            if (tempCriteria < criteriaValue) {
                criteriaValue = tempCriteria;
                position = PositionConstants.EAST;
            }

            return position;
        }

        /**
         * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
         * RectilinearRouter. Determines whether the rectilinear line segment
         * coming out of the shape should be horizontal or vertical based on the
         * anchor geographic position relative to the shape
         * 
         * @param anchorRelativeLocation
         * @return
         */
        private static int getOffShapeDirection(int anchorRelativeLocation) {
            if (anchorRelativeLocation == PositionConstants.EAST || anchorRelativeLocation == PositionConstants.WEST) {
                return PositionConstants.HORIZONTAL;
            } else if (anchorRelativeLocation == PositionConstants.NORTH || anchorRelativeLocation == PositionConstants.SOUTH) {
                return PositionConstants.VERTICAL;
            }
            return PositionConstants.NONE;
        }

        /**
         * From org.eclipse.gmf.runtime.draw2d.ui.internal.routers.
         * RectilinearRouter. Iterates through points of a polyline and does the
         * following: if 3 points lie on the same line the middle point is
         * removed
         * 
         * @param line
         *            polyline's points
         */
        private static boolean removeRedundantPoints(PointList line) {
            int initialNumberOfPoints = line.size();
            if (line.size() > 2) {
                PointList newLine = new PointList(line.size());
                newLine.addPoint(line.removePoint(0));
                while (line.size() >= 2) {
                    Point p0 = newLine.getLastPoint();
                    Point p1 = line.getPoint(0);
                    Point p2 = line.getPoint(1);
                    if (p0.x == p1.x && p0.x == p2.x) {
                        // Have two vertical segments in a row
                        // get rid of the point between
                        line.removePoint(0);
                    } else if (p0.y == p1.y && p0.y == p2.y) {
                        // Have two horizontal segments in a row
                        // get rid of the point between
                        line.removePoint(0);
                    } else {
                        newLine.addPoint(line.removePoint(0));
                    }
                }
                while (line.size() > 0) {
                    newLine.addPoint(line.removePoint(0));
                }
                line.removeAllPoints();
                line.addAll(newLine);
            }
            return line.size() != initialNumberOfPoints;
        }
    }
}
