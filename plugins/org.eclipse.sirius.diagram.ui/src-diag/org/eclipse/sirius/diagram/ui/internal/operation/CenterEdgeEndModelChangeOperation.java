/*******************************************************************************
 * Copyright (c) 2014-2019 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation - The code from org.eclipse.gmf.runtime.draw2d.ui.internal.routers.RectilinearRouter
 *******************************************************************************/

package org.eclipse.sirius.diagram.ui.internal.operation;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ConnectionLayerEx;
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
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;
import org.eclipse.sirius.diagram.ui.graphical.figures.CanonicalRectilinearRouter;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.RectilinearEdgeUtil;
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
@SuppressWarnings("restriction")
public class CenterEdgeEndModelChangeOperation extends AbstractModelChangeOperation<Void> {

    private Edge edge;

    private PrecisionPoint existingSourceAnchorAbsoluteLocation;

    private PrecisionPoint existingTargetAnchorAbsoluteLocation;

    private PrecisionPoint newTargetAnchorAbsoluteLocation;

    private PrecisionPoint newSourceAnchorAbsoluteLocation;

    private ConnectionEditPart connectionEditPart;

    private Connection connection;

    private boolean useFigure = true;

    private Dimension sourceFigureSize;

    private Dimension targetFigureSize;

    private static final String CENTERED_ANCHOR = GMFNotationUtilities.getTerminalString(0.5d, 0.5d);

    /**
     * Constructor to use the connectionEditPart to compute the new edge
     * bendpoints.
     * 
     * @param connectionEditPart
     *            the edge connectionEditPart.
     * @param edge
     *            the GMF edge.
     */
    public CenterEdgeEndModelChangeOperation(ConnectionEditPart connectionEditPart, Edge edge) {
        this(edge);
        this.connectionEditPart = connectionEditPart;
        this.connection = (Connection) connectionEditPart.getFigure();
    }

    /**
     * Default constructor to center the given gmf edge ends (if needed). This
     * operation will try to retrieve the corresponding figure (if possible) to
     * compute the new bendpoints.
     * 
     * @param edge
     *            the GMF edge to center ends.
     */
    public CenterEdgeEndModelChangeOperation(Edge edge) {
        this.edge = edge;
    }

    /**
     * Constructor to explicitly specify whether this operation should try to
     * retrieve the edge figure to compute the new bendpoints.
     * 
     * @param edge
     *            the GMF edge.
     * @param useFigure
     *            true if this operation should use the corresponding draw2D
     *            figure, false otherwise.
     */
    public CenterEdgeEndModelChangeOperation(Edge edge, boolean useFigure) {
        this(edge);
        this.useFigure = useFigure;
    }

    /**
     * Set the source and target figure size. Those size will be used instead of
     * compute them.
     * 
     * @param sourceFigureSize
     *            the edge source figure size. If null, the size will be
     *            computed.
     * @param targetFigureSize
     *            the edge target figure size. If null, the size will be
     *            computed.
     */
    public void setSourceAndTargetSize(Dimension sourceFigureSize, Dimension targetFigureSize) {
        this.sourceFigureSize = sourceFigureSize;
        this.targetFigureSize = targetFigureSize;
    }

    @Override
    public Void execute() {
        Routing routingValue = getRoutingValue();

        // we do not handle Tree routing style
        if (!Routing.TREE_LITERAL.equals(routingValue)) {
            if (isEdgeSourceCentered() && isEdgeTargetCentered()) {

                centerEdgeEnds(CenteringStyle.BOTH, routingValue);
            } else if (isEdgeSourceCentered()) {
                centerEdgeEnds(CenteringStyle.SOURCE, routingValue);
            }

            else if (isEdgeTargetCentered()) {
                centerEdgeEnds(CenteringStyle.TARGET, routingValue);
            }
        }

        return null;
    }

    private Routing getRoutingValue() {
        Routing routingValue = Routing.MANUAL_LITERAL;
        EdgeQuery edgeQuery = new EdgeQuery(edge);
        if (edgeQuery.isEdgeWithTreeRoutingStyle()) {
            routingValue = Routing.TREE_LITERAL;
        } else if (edgeQuery.isEdgeWithRectilinearRoutingStyle()) {
            routingValue = Routing.RECTILINEAR_LITERAL;
        }
        return routingValue;
    }

    /**
     * Center the connection end (Source, Target or Both) according to the
     * center value.
     * 
     * @param center
     *            the {@link CenteringStyle} value.
     */
    private void centerEdgeEnds(CenteringStyle center, Routing routingValue) {
        Bendpoints bendpoints = edge.getBendpoints();

        if (bendpoints instanceof RelativeBendpoints) {
            View edgeSourceView = edge.getSource();
            View edgeTargetView = edge.getTarget();

            if (edgeSourceView instanceof Node && edgeTargetView instanceof Node) {

                setConnectionIfNull();

                // We get the edge source and target nodes absolute bounds to
                // compute absolute anchors coordinates
                Option<Rectangle> sourceBounds = getAbsoluteSourceBounds(edgeSourceView);
                Option<Rectangle> targetBounds = getAbsoluteTargetBounds(edgeTargetView);

                if (sourceBounds.some() && targetBounds.some()) {

                    // Calculate the existing anchors absolute location
                    retrieveAndSetExistingAnchorsAbsoluteLocation(sourceBounds.get(), targetBounds.get());

                    // Compute the new anchors absolute coordinates according to
                    // the center value.
                    computeAndSetNewAnchorsAbsoluteCoordinates(sourceBounds.get(), targetBounds.get(), center);

                    PointList newPointList = new PointList();
                    // Compute the existing bendpoints absolute location
                    newPointList = getAbsolutePointList((RelativeBendpoints) bendpoints);
                    if (!(Routing.RECTILINEAR_LITERAL.getLiteral().equals(routingValue.getLiteral()))) {
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
     * Because of the difference between the GMF absolute bounds computation and
     * the draw2D absolute bounds, we will compute these bounds from draw2D if
     * we have access to the figure, or from GMF if we work in "pure" GMF
     * context.
     * 
     * @param gmfView
     *            the GMFView to compute the absolute bounds.
     * @param isSource
     *            true if it represents the edge source, false otherwise.
     * @return the absolute bounds.
     */
    private Option<Rectangle> getAbsoluteBounds(View gmfView, boolean isSource) {
        if (connectionEditPart != null) {
            EditPart editPart = null;
            if (isSource) {
                editPart = connectionEditPart.getSource();
            } else {
                editPart = connectionEditPart.getTarget();
            }
            if (editPart instanceof GraphicalEditPart) {
                return Options.newSome(GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) editPart));
            }
        }
        return GMFHelper.getAbsoluteBounds(gmfView, true);
    }

    private Option<Rectangle> getAbsoluteSourceBounds(View edgeSourceView) {
        Option<Rectangle> option = getAbsoluteBounds(edgeSourceView, true);
        if (sourceFigureSize != null && option.some()) {
            Rectangle rectangle = option.get();
            rectangle.setSize(sourceFigureSize);
        }
        return option;
    }

    private Option<Rectangle> getAbsoluteTargetBounds(View edgeTargetView) {
        Option<Rectangle> option = getAbsoluteBounds(edgeTargetView, false);
        if (targetFigureSize != null && option.some()) {
            Rectangle rectangle = option.get();
            rectangle.setSize(targetFigureSize);
        }
        return option;
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
        // In some particular cases, we could have a bendpoint list with less
        // than 2 elements. In this case we create a new point list that will
        // contain the two bendpoints computed from the intersection points.
        else if (existingPointList.size() < 2) {
            existingPointList = new PointList(new int[] { 0, 0, 0, 0 });
        }
        Optional<Point> sourceConnectionPoint = Optional.empty();
        Optional<Point> targetConnectionPoint = Optional.empty();

        if (center == CenteringStyle.BOTH || center == CenteringStyle.SOURCE) {
            sourceConnectionPoint = GraphicalHelper.getIntersection(sourceLineOrigin, sourceLineTerminus, sourceBounds, false);
        }
        if (center == CenteringStyle.BOTH || center == CenteringStyle.TARGET) {
            targetConnectionPoint = GraphicalHelper.getIntersection(targetLineOrigin, targetLineTerminus, targetBounds, false);
        }

        if (sourceConnectionPoint.isPresent() || targetConnectionPoint.isPresent()) {

            if (sourceConnectionPoint.isPresent()) {
                existingPointList.setPoint(sourceConnectionPoint.get(), 0);
                centerSourceAnchor();
            }

            if (targetConnectionPoint.isPresent()) {
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

        PointList rectilinear = null;

        // If the connection is available (the edge already exist) we retrieve
        // the rectilinear bendpoints.
        if (connection != null) {
            rectilinear = getRectilinearPointListFromConnection();

        } else {
            rectilinear = existingPointList.getCopy();

            // GMF bendpoints are not reliable: in some cases they are
            // completely far away from what draw2D is really displaying. If
            // there is only two GMF bendpoints, we can compute them with the
            // information we know. We also could have a point list with less
            // than two elements.
            if (rectilinear.size() <= 2) {
                computePointListByIntersections(rectilinear, sourceBounds, targetBounds);
            }
            if (rectilinear.size() >= 2) {
                CanonicalRectilinearRouter rectilinearRouter = new CanonicalRectilinearRouter();
                rectilinearRouter.routeLine(edge, 0, rectilinear, sourceBounds, targetBounds);
            }
        }
        if (rectilinear.size() >= 2) {
            RectilinearEdgeUtil.centerEdgeEnds(rectilinear, newSourceAnchorAbsoluteLocation, newTargetAnchorAbsoluteLocation, center);

            if (center == CenteringStyle.BOTH || center == CenteringStyle.SOURCE) {
                centerSourceAnchor();
            }
            if (center == CenteringStyle.BOTH || center == CenteringStyle.TARGET) {
                centerTargetAnchor();
            }

            existingPointList.removeAllPoints();
            existingPointList.addAll(rectilinear);
        }
    }

    private void computePointListByIntersections(PointList rectilinear, Rectangle sourceBounds, Rectangle targetBounds) {
        Optional<Point> sourceConnectionPoint = GraphicalHelper.getIntersection(existingSourceAnchorAbsoluteLocation, existingTargetAnchorAbsoluteLocation, sourceBounds, false);
        Optional<Point> targetConnectionPoint = GraphicalHelper.getIntersection(existingSourceAnchorAbsoluteLocation, existingTargetAnchorAbsoluteLocation, targetBounds, false);
        if (sourceConnectionPoint.isPresent() && targetConnectionPoint.isPresent()) {
            rectilinear.removeAllPoints();
            rectilinear.addPoint(sourceConnectionPoint.get());
            rectilinear.addPoint(targetConnectionPoint.get());
        }
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
        existingSourceAnchorAbsoluteLocation = getAbsoluteAnchorCoordinates(sourceBounds, getAnchorId(edge.getSourceAnchor()));
        existingTargetAnchorAbsoluteLocation = getAbsoluteAnchorCoordinates(targetBounds, getAnchorId(edge.getTargetAnchor()));
    }

    private PrecisionPoint getAnchorId(Anchor anchor) {
        PrecisionPoint anchorId = new PrecisionPoint(0.5, 0.5);
        if (anchor instanceof IdentityAnchor) {
            anchorId = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) anchor).getId());
        }
        return anchorId;
    }

    /**
     * Set the source anchor at (0.5, 0.5).
     */
    private void centerSourceAnchor() {
        Anchor currentAnchor = edge.getSourceAnchor();
        IdentityAnchor newAnchor = setOrCreateNewAnchorIfNeeded(currentAnchor);
        if (newAnchor != currentAnchor) {
            edge.setSourceAnchor(newAnchor);
        }

    }

    /**
     * Set the target anchor at (0.5, 0.5).
     */
    private void centerTargetAnchor() {
        Anchor currentAnchor = edge.getTargetAnchor();
        IdentityAnchor newAnchor = setOrCreateNewAnchorIfNeeded(currentAnchor);
        if (newAnchor != currentAnchor) {
            edge.setTargetAnchor(newAnchor);
        }
    }

    private IdentityAnchor setOrCreateNewAnchorIfNeeded(Anchor anchor) {
        if (anchor instanceof IdentityAnchor) {
            if (!((IdentityAnchor) anchor).getId().equals(CENTERED_ANCHOR)) {
                ((IdentityAnchor) anchor).setId(CENTERED_ANCHOR);
            }
            return (IdentityAnchor) anchor;
        } else {
            IdentityAnchor a = NotationFactory.eINSTANCE.createIdentityAnchor();
            a.setId(CENTERED_ANCHOR);
            return a;
        }
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
    private PrecisionPoint getAbsoluteAnchorCoordinates(Rectangle absoluteBounds, PrecisionPoint precisionPoint) {
        return new PrecisionPoint(absoluteBounds.x() + (absoluteBounds.width() * precisionPoint.preciseX()), absoluteBounds.y() + (absoluteBounds.height() * precisionPoint.preciseY()));

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
        Option<PointList> pointList = Options.newNone();
        if (connection != null) {
            pointList = Options.newSome(connection.getPoints().getCopy());
        }

        return pointList;
    }

    /**
     * We try to retrieve the edge connection figure.
     */
    @SuppressWarnings("rawtypes")
    private void setConnectionIfNull() {
        if (connection != null || !useFigure) {
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
     * <strong>We suppose the connection attribute is not null</strong>
     * 
     * @return a rectilinear PointList.
     */
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
}
