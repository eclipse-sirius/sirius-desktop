/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.diagram;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.edge.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.part.SiriusLinkDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ext.base.Option;

/**
 * Specific factory for {@link Edge} used by the
 * {@link DDiagramCanonicalSynchronizer}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ConnectionsFactory {

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
     * Create a {@link Edge} according to information provided in
     * <code>viewDescriptor</code> and <code>domain2NotationMap</code>.
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
                    DiagramPlugin.getDefault().logError("GMF Edge not created between source element : " + sourceEdgeTarget + ", and target element : " + targetEdgeTarget);
                }
            }
        }
        return createdEdge;
    }

    /**
     * As FontStyle.fontHeight default value is 9 and BasicLabelStyle.labelSize
     * default value is 8 we must synchronize this property.
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
                EdgeLayoutData egdeLayoutData = SiriusLayoutDataManager.INSTANCE.getData(dEdge, false);

                if (egdeLayoutData != null) {
                    EdgeLayoutData oppositeEgdeLayoutData = SiriusLayoutDataManager.INSTANCE.getOppositeEdgeLayoutData(egdeLayoutData, false);
                    routing = egdeLayoutData.getRouting();

                    if (oppositeEgdeLayoutData != null) {
                        getAttributesForSourceAndTargetMove(egdeLayoutData, oppositeEgdeLayoutData, edge, source, target);
                    } else {
                        getAttributesForSourceOrTargetMove(egdeLayoutData, edge, source, target);
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
                    pointList.addPoint(sourceAnchor.getLocation(sourceAnchor.getReferencePoint()));

                    PrecisionPoint targetRelativeReference = SlidableAnchor.parseTerminalString(targetTerminal);
                    SlidableAnchor targetAnchor = new SlidableAnchor(target, targetRelativeReference);
                    pointList.addPoint(targetAnchor.getLocation(targetAnchor.getReferencePoint()));
                }
            }
        }
    }

    /**
     * Only source or target of edge has been moved (or dragged and dropped).
     * Use the data as is, except for border node that potentially needs
     * adaptation.
     * 
     * @param egdeLayoutData
     *            Edge layout data from source (or from target)
     * @param edge
     *            The edge to adapt
     * @param source
     *            The source view of the edge
     * @param target
     *            The target view of the edge
     */
    protected void getAttributesForSourceOrTargetMove(EdgeLayoutData egdeLayoutData, Edge edge, View source, View target) {
        sourceTerminal = egdeLayoutData.getSourceTerminal();
        targetTerminal = egdeLayoutData.getTargetTerminal();
        // sourceRefPoint, targetRefPoint and pointList of
        // the edgeLayoutData can be null if the edge is
        // hide when the layout data is stored
        if (egdeLayoutData.getSourceRefPoint() != null) {
            sourceRefPoint = egdeLayoutData.getSourceRefPoint();
        }
        if (egdeLayoutData.getTargetRefPoint() != null) {
            targetRefPoint = egdeLayoutData.getTargetRefPoint();
        }

        if (egdeLayoutData.getPointList() != null) {
            pointList = egdeLayoutData.getPointList();
        }
    }

    /**
     * Both source and target of edge have been moved (or dragged and dropped).
     * We must use a "mix" of the edge layout data from source that contains the
     * impacted points for the source move and the edge layout data from target
     * that contains the impacted points for the target move.
     * 
     * @param egdeLayoutData
     *            Edge layout data from source (or from target)
     * @param oppositeEgdeLayoutData
     *            Opposite layout data from target (or from source)
     * @param edge
     *            The edge to adapt
     * @param source
     *            The source view of the edge
     * @param target
     *            The target view of the edge
     */
    private void getAttributesForSourceAndTargetMove(EdgeLayoutData egdeLayoutData, EdgeLayoutData oppositeEgdeLayoutData, Edge edge, View source, View target) {
        EdgeLayoutData layoutDataFromSource;
        EdgeLayoutData layoutDataFromTarget;
        if (egdeLayoutData.getTarget().getSourceNode().equals(egdeLayoutData.getParent().getTarget())) {
            layoutDataFromSource = egdeLayoutData;
            layoutDataFromTarget = oppositeEgdeLayoutData;
        } else {
            layoutDataFromSource = oppositeEgdeLayoutData;
            layoutDataFromTarget = egdeLayoutData;
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
            if (sourceTerminal.length() == 0)
                edge.setSourceAnchor(null);
            else {
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
            if (targetTerminal.length() == 0)
                edge.setTargetAnchor(null);
            else {
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
