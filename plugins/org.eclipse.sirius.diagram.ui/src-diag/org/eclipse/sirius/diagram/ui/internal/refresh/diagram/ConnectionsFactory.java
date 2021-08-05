/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.diagram;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.CenteringStyle;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.edge.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.part.SiriusLinkDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.routers.RectilinearEdgeUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Specific factory for {@link Edge} used by the {@link DDiagramCanonicalSynchronizer}.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ConnectionsFactory {

    /**
     * Value of null Terminal.
     */
    private static final String EMPTY_TERMINAL = ""; //$NON-NLS-1$

    /**
     * The {@link Diagram} corresponding to the DSemanticDiagram to synchronize.
     */
    private Diagram gmfDiagram;

    /**
     * Sirius GMF notation model View factory.
     */
    private IViewProvider viewpointViewProvider;

    // Anchors
    private String sourceTerminal;

    private String targetTerminal;

    // Bendpoints
    private PointList pointList = new PointList();

    private Point sourceRefPoint = new Point(0, 0);

    private Point targetRefPoint = new Point(0, 0);

    // Routing
    private Routing routing;

    /**
     * Default constructor.
     *
     * @param gmfDiagram
     *            the {@link Diagram} on which create {@link Edge}.
     * @param viewpointViewProvider
     *            the {@link IViewProvider} to provide gmf view
     */
    public ConnectionsFactory(Diagram gmfDiagram, IViewProvider viewpointViewProvider) {
        this.gmfDiagram = gmfDiagram;
        this.viewpointViewProvider = viewpointViewProvider;
    }

    /**
     * Create a {@link Edge} according to information provided in <code>viewDescriptor</code> and
     * <code>domain2NotationMap</code>.
     *
     * @param viewDescriptor
     *            {@link SiriusLinkDescriptor} describing the Edge to create.
     *
     * @param domain2NotationMap
     *            map from DDiagramElement to gmf notation view.
     *
     * @return the created {@link Edge}
     */
    public Edge createEdge(SiriusLinkDescriptor viewDescriptor, Map<EObject, View> domain2NotationMap) {
        Edge createdEdge = null;
        pointList = new PointList();

        final View source = domain2NotationMap.get(viewDescriptor.getSource());
        final View target = domain2NotationMap.get(viewDescriptor.getDestination());

        if (source != null && target != null) {
            if (source.getElement() instanceof EdgeTarget && target.getElement() instanceof EdgeTarget) {
                EdgeTarget sourceEdgeTarget = (EdgeTarget) source.getElement();
                EdgeTarget targetEdgeTarget = (EdgeTarget) target.getElement();

                IAdaptable semanticAdapter = viewDescriptor.getSemanticAdapter();
                View containerView = gmfDiagram;
                String semanticHint = null;
                int index = ViewUtil.APPEND;
                boolean persisted = true;
                PreferencesHint preferencesHint = DiagramUIPlugin.DIAGRAM_PREFERENCES_HINT;

                Edge edge = viewpointViewProvider.createEdge(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);

                if (edge != null) {

                    updateFontHeight(edge);

                    getAttributes(edge, sourceEdgeTarget, targetEdgeTarget, source, target);

                    // Set endPoints :
                    edge.setSource(source);
                    edge.setTarget(target);

                    // Set connection anchors
                    setConnectionAnchors(edge);

                    // Set Bendpoints :
                    List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();

                    int numOfPoints = pointList.size();
                    for (short i = 0; i < numOfPoints; i++) {
                        Dimension s = pointList.getPoint(i).getDifference(sourceRefPoint);
                        Dimension t = pointList.getPoint(i).getDifference(targetRefPoint);
                        newBendpoints.add(new RelativeBendpoint(s.width, s.height, t.width, t.height));
                    }

                    RelativeBendpoints points = (RelativeBendpoints) edge.getBendpoints();
                    points.setPoints(newBendpoints);

                    // Routing
                    if (routing != null) {
                        Style routingStyle = edge.getStyle(NotationPackage.Literals.ROUTING_STYLE);
                        routingStyle.eSet(NotationPackage.Literals.ROUTING_STYLE__ROUTING, routing);
                    }

                    // Update the map with the new edge
                    domain2NotationMap.put(viewDescriptor.getModelElement(), edge);

                    createdEdge = edge;
                } else {
                    DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.ConnectionsFactory_edgeNotCreatedMsg, sourceEdgeTarget, targetEdgeTarget));
                }
            }
        }
        return createdEdge;
    }

    /**
     * As FontStyle.fontHeight default value is 9 and BasicLabelStyle.labelSize default value is 8 we must synchronize
     * this property.
     *
     * @param edge
     */
    // FIXME : as we have begin/center/endLabelStyle we should have a FontStyle
    // for each Node children instead of Edge.fontStyle
    private void updateFontHeight(Edge edge) {
        Style style = edge.getStyle(NotationPackage.Literals.FONT_STYLE);
        if (style instanceof FontStyle) {
            FontStyle fontStyle = (FontStyle) style;
            EObject element = edge.getElement();
            if (element instanceof DEdge) {
                DEdge dEdge = (DEdge) element;
                CenterLabelStyle centerLabelStyle = dEdge.getOwnedStyle().getCenterLabelStyle();
                if (centerLabelStyle != null) {
                    int labelSize = centerLabelStyle.getLabelSize();
                    fontStyle.setFontHeight(Math.max(labelSize, 1));
                }
            }
        }
    }

    private void getAttributes(Edge edge, EdgeTarget sourceEdgeTarget, EdgeTarget targetEdgeTarget, View source, View target) {
        if (edge.getElement() instanceof DEdge) {
            EObject element = edge.getElement();
            if (element instanceof DEdge) {
                DEdge dEdge = (DEdge) element;
                EdgeLayoutData edgeLayoutData = SiriusLayoutDataManager.INSTANCE.getData(dEdge, false);

                if (edgeLayoutData != null) {
                    EdgeLayoutData oppositeEdgeLayoutData = SiriusLayoutDataManager.INSTANCE.getOppositeEdgeLayoutData(edgeLayoutData, false);
                    routing = edgeLayoutData.getRouting();

                    if (oppositeEdgeLayoutData != null) {
                        getAttributesForSourceAndTargetMove(edgeLayoutData, oppositeEdgeLayoutData, edge, source, target);
                    } else {
                        getAttributesForSourceOrTargetMove(edgeLayoutData, edge, source, target);
                    }
                } else {
                    Option<Rectangle> optionalSourceBounds = GMFHelper.getAbsoluteBounds(source);
                    LayoutData sourceLayoutData = null;
                    if (sourceEdgeTarget instanceof AbstractDNode) {
                        AbstractDNode sourceDNode = (AbstractDNode) sourceEdgeTarget;
                        sourceLayoutData = SiriusLayoutDataManager.INSTANCE.getData(sourceDNode);
                    }
                    Point firstClick = getFirstClick(sourceLayoutData, optionalSourceBounds);

                    if (optionalSourceBounds.some()) {
                        PrecisionPoint sourceRelativeLocation = BaseSlidableAnchor.getAnchorRelativeLocation(firstClick, optionalSourceBounds.get());
                        sourceTerminal = GMFNotationUtilities.getTerminalString(sourceRelativeLocation.preciseX(), sourceRelativeLocation.preciseY());
                    } else {
                        sourceTerminal = GMFNotationUtilities.getTerminalString(0.5d, 0.5d);
                    }

                    Option<Rectangle> optionaltargetBounds = GMFHelper.getAbsoluteBounds(target);
                    LayoutData targetLayoutData = null;
                    if (targetEdgeTarget instanceof AbstractDNode) {
                        AbstractDNode targetDNode = (AbstractDNode) targetEdgeTarget;
                        targetLayoutData = SiriusLayoutDataManager.INSTANCE.getData(targetDNode);
                    }
                    Point secondClick = getSecondClick(targetLayoutData, optionaltargetBounds);

                    if (optionaltargetBounds.some()) {
                        PrecisionPoint targetRelativeLocation = BaseSlidableAnchor.getAnchorRelativeLocation(secondClick, optionaltargetBounds.get());
                        targetTerminal = GMFNotationUtilities.getTerminalString(targetRelativeLocation.preciseX(), targetRelativeLocation.preciseY());
                    } else {
                        targetTerminal = GMFNotationUtilities.getTerminalString(0.5d, 0.5d);
                    }

                    // Computes pointList
                    PrecisionPoint sourceRelativeReference = SlidableAnchor.parseTerminalString(sourceTerminal);
                    SlidableAnchor sourceAnchor = new SlidableAnchor(source, sourceRelativeReference);
                    sourceRefPoint = sourceAnchor.getLocation(sourceAnchor.getReferencePoint());

                    PrecisionPoint targetRelativeReference = SlidableAnchor.parseTerminalString(targetTerminal);
                    SlidableAnchor targetAnchor = new SlidableAnchor(target, targetRelativeReference);
                    targetRefPoint = targetAnchor.getLocation(targetAnchor.getReferencePoint());

                    Optional<Point> srcConnectionBendpoint = GraphicalHelper.getIntersection(sourceRefPoint, targetRefPoint, optionalSourceBounds.get(), true);
                    Optional<Point> tgtConnectionBendpoint = GraphicalHelper.getIntersection(sourceRefPoint, targetRefPoint, optionaltargetBounds.get(), false);

                    if (srcConnectionBendpoint.isPresent() && tgtConnectionBendpoint.isPresent()) {
                        pointList.addPoint(srcConnectionBendpoint.get());
                        pointList.addPoint(tgtConnectionBendpoint.get());
                        EdgeQuery edgeQuery = new EdgeQuery(edge);
                        if (edgeQuery.isEdgeWithRectilinearRoutingStyle()) {
                            RectilinearEdgeUtil.centerEdgeEnds(pointList, sourceRefPoint, targetRefPoint, CenteringStyle.BOTH);
                        }
                    } else {
                        // no intersection found, case when source and target are overlapped
                        pointList.addPoint(sourceRefPoint);
                        pointList.addPoint(targetRefPoint);
                    }
                }
                pointList = RectilinearEdgeUtil.normalizeToStraightLineTolerance(pointList, 2);
            }
        }
    }

    /**
     * Only source or target of edge has been moved (or dragged and dropped). Use the data as is, except for border node
     * that potentially needs adaptation.
     *
     * @param edgeLayoutData
     *            Edge layout data from source (or from target)
     * @param edge
     *            The edge to adapt
     * @param source
     *            The source view of the edge
     * @param target
     *            The target view of the edge
     */
    protected void getAttributesForSourceOrTargetMove(EdgeLayoutData edgeLayoutData, Edge edge, View source, View target) {
        sourceTerminal = edgeLayoutData.getSourceTerminal();
        if (EMPTY_TERMINAL.equals(sourceTerminal)) {
            sourceTerminal = GMFNotationUtilities.getTerminalString(0.5d, 0.5d);
        }
        targetTerminal = edgeLayoutData.getTargetTerminal();
        if (EMPTY_TERMINAL.equals(targetTerminal)) {
            targetTerminal = GMFNotationUtilities.getTerminalString(0.5d, 0.5d);
        }
        // sourceRefPoint, targetRefPoint and pointList of
        // the edgeLayoutData can be null if the edge is
        // hide when the layout data is stored
        if (edgeLayoutData.getSourceRefPoint() != null) {
            sourceRefPoint = edgeLayoutData.getSourceRefPoint().getCopy();
        }
        if (edgeLayoutData.getTargetRefPoint() != null) {
            targetRefPoint = edgeLayoutData.getTargetRefPoint().getCopy();
        }

        if (edgeLayoutData.getPointList() != null) {
            GraphicalEditPart srceEditPart = GMFHelper.getGraphicalEditPart(source).get();
            GraphicalEditPart tgtEditPart = GMFHelper.getGraphicalEditPart(target).get();

            if (srceEditPart != null && tgtEditPart != null) {
                GraphicalHelper.screen2logical(sourceRefPoint, srceEditPart);
                GraphicalHelper.screen2logical(targetRefPoint, tgtEditPart);

                // Compute connection bendpoints
                Rectangle optionalSourceBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(srceEditPart);
                Optional<Point> srcConnectionBendpoint = GraphicalHelper.getIntersection(sourceRefPoint, targetRefPoint, optionalSourceBounds, true);
                Rectangle optionaltargetBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(tgtEditPart);
                Optional<Point> tgtConnectionBendpoint = GraphicalHelper.getIntersection(sourceRefPoint, targetRefPoint, optionaltargetBounds, false);

                if (srcConnectionBendpoint.isPresent() && tgtConnectionBendpoint.isPresent()) {
                    EdgeQuery edgeQuery = new EdgeQuery(edge);
                    Routing routingStyle = edgeQuery.getRoutingStyle();
                    // Compute anchor logical coordinates
                    if (Routing.RECTILINEAR_LITERAL.equals(routingStyle) && srceEditPart != null && srceEditPart.equals(tgtEditPart)) {
                        // Set a default first bendpoint position on the center of the east side of the source
                        pointList = RectilinearEdgeUtil.computeRectilinearBendpointsSameSourceAndTarget(optionalSourceBounds, srceEditPart.getFigure().getBounds().getRight(), PositionConstants.EAST);
                    } else if (Routing.RECTILINEAR_LITERAL.equals(routingStyle)) {
                        pointList = RectilinearEdgeUtil.computeRectilinearBendpoints(optionalSourceBounds, optionaltargetBounds, srcConnectionBendpoint.get(), tgtConnectionBendpoint.get());
                    } else {
                        pointList.addPoint(srcConnectionBendpoint.get());
                        pointList.addPoint(tgtConnectionBendpoint.get());
                    }
                }
            }
            if (pointList.size() == 0) {
                // no intersection found, case when source and target are overlapped
                pointList = edgeLayoutData.getPointList();
            }
        }
    }

    /**
     * Both source and target of edge have been moved (or dragged and dropped). We must use a "mix" of the edge layout
     * data from source that contains the impacted points for the source move and the edge layout data from target that
     * contains the impacted points for the target move.
     *
     * @param edgeLayoutData
     *            Edge layout data from source (or from target)
     * @param oppositeEdgeLayoutData
     *            Opposite layout data from target (or from source)
     * @param edge
     *            The edge to adapt
     * @param source
     *            The source view of the edge
     * @param target
     *            The target view of the edge
     */
    private void getAttributesForSourceAndTargetMove(EdgeLayoutData edgeLayoutData, EdgeLayoutData oppositeEdgeLayoutData, Edge edge, View source, View target) {
        EdgeLayoutData layoutDataFromSource;
        EdgeLayoutData layoutDataFromTarget;
        if (edgeLayoutData.getTarget().getSourceNode().equals(edgeLayoutData.getParent().getTarget())) {
            layoutDataFromSource = edgeLayoutData;
            layoutDataFromTarget = oppositeEdgeLayoutData;
        } else {
            layoutDataFromSource = oppositeEdgeLayoutData;
            layoutDataFromTarget = edgeLayoutData;
        }
        sourceTerminal = layoutDataFromSource.getSourceTerminal();
        targetTerminal = layoutDataFromTarget.getTargetTerminal();
        // sourceRefPoint, targetRefPoint and pointList of
        // the edgeLayoutData can be null if the edge is
        // hide when the layout data is stored
        if (layoutDataFromSource.getSourceRefPoint() != null) {
            sourceRefPoint = layoutDataFromSource.getSourceRefPoint();
        }
        if (layoutDataFromTarget.getTargetRefPoint() != null) {
            targetRefPoint = layoutDataFromTarget.getTargetRefPoint();
        }

        if (layoutDataFromSource.getPointList() != null) {
            pointList = layoutDataFromSource.getPointList();

            if (layoutDataFromTarget.getPointList() != null) {
                // Get the last point from layout data from target
                pointList.setPoint(layoutDataFromTarget.getPointList().getLastPoint(), pointList.size() - 1);
                if (pointList.size() > 2) {
                    // Also get the second point to retrieve the original value
                    pointList.setPoint(layoutDataFromTarget.getPointList().getPoint(1), 1);
                }
            }
            if (pointList.size() > 2) {
                // All the intermediate points are kept fixed, but because both
                // source and target have been moved, also moved all
                // intermediate points.
                if (layoutDataFromSource.getTargetRefPoint() != null) {
                    Point targetRefPointFromSource = layoutDataFromSource.getTargetRefPoint();
                    Dimension delta = targetRefPoint.getDifference(targetRefPointFromSource);
                    for (int i = 1; i < pointList.size() - 1; i++) {
                        pointList.setPoint(pointList.getPoint(i).translate(delta.width, delta.height), i);
                    }
                }
            }
        }
    }

    private Point getFirstClick(LayoutData sourceLayoutData, Option<Rectangle> optionalSourceBounds) {
        Point firstClick = new Point(0, 0);
        if (sourceLayoutData == null) {
            if (optionalSourceBounds.some()) {
                firstClick = optionalSourceBounds.get().getCenter();
            }
        } else {
            Point sourceLocation = sourceLayoutData.getLocation();
            firstClick = sourceLocation;
        }
        return firstClick;
    }

    private Point getSecondClick(LayoutData targetLayoutData, Option<Rectangle> optionaltargetBounds) {
        Point secondClick = new Point(0, 0);
        if (targetLayoutData == null) {
            if (optionaltargetBounds.some()) {
                secondClick = optionaltargetBounds.get().getCenter();
            }
        } else {
            Point targetLocation = targetLayoutData.getLocation();
            secondClick = targetLocation;
        }
        return secondClick;
    }

    private void setConnectionAnchors(Edge edge) {
        EdgeQuery edgeQuery = new EdgeQuery(edge);
        // Set connection anchors :
        if (edgeQuery.isEdgeOnTreeOnSourceSide()) {
            // Use sourceTerminal of other edges
            Option<IdentityAnchor> optionalSourceIdentityAnchorOfBrother = edgeQuery.getSourceAnchorOfFirstBrotherWithSameSource();
            if (optionalSourceIdentityAnchorOfBrother.some()) {
                sourceTerminal = optionalSourceIdentityAnchorOfBrother.get().getId();
            }
        }
        if (sourceTerminal != null) {
            if (sourceTerminal.length() == 0) {
                edge.setSourceAnchor(null);
            } else {
                IdentityAnchor a = (IdentityAnchor) edge.getSourceAnchor();
                if (a == null) {
                    a = NotationFactory.eINSTANCE.createIdentityAnchor();
                }
                a.setId(sourceTerminal);
                edge.setSourceAnchor(a);
            }
        }
        if (edgeQuery.isEdgeOnTreeOnTargetSide()) {
            Option<IdentityAnchor> optionalTargetIdentityAnchorOfBrother = edgeQuery.getTargetAnchorOfFirstBrotherWithSameTarget();
            if (optionalTargetIdentityAnchorOfBrother.some()) {
                // Use targetTerminal of other edges
                targetTerminal = optionalTargetIdentityAnchorOfBrother.get().getId();
            }
        } else if (edgeQuery.usedTreeRouter()) {
            // Use center anchor (because this is what is done
            // by the {@link
            // org.eclipse.sirius.diagram.ui.tools.internal.routers.DTreeRouter#getTrunkLocation(org.eclipse.draw2d.Connection)}
            targetTerminal = "(0.5,0.5)"; //$NON-NLS-1$
        }
        if (targetTerminal != null) {
            if (targetTerminal.length() == 0) {
                edge.setTargetAnchor(null);
            } else {
                IdentityAnchor a = (IdentityAnchor) edge.getTargetAnchor();
                if (a == null) {
                    a = NotationFactory.eINSTANCE.createIdentityAnchor();
                }
                a.setId(targetTerminal);
                edge.setTargetAnchor(a);
            }

        }
    }
}
