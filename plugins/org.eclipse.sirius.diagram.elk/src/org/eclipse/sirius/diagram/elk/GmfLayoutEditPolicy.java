/*******************************************************************************
 * Copyright (c) 2009, 2025 Kiel University and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.elk;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.elk.core.math.ElkMath;
import org.eclipse.elk.core.math.KVector;
import org.eclipse.elk.core.math.KVectorChain;
import org.eclipse.elk.core.options.CoreOptions;
import org.eclipse.elk.core.options.EdgeLabelPlacement;
import org.eclipse.elk.core.options.EdgeRouting;
import org.eclipse.elk.core.service.LayoutMapping;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.core.util.Pair;
import org.eclipse.elk.core.util.WrappedException;
import org.eclipse.elk.graph.ElkBendPoint;
import org.eclipse.elk.graph.ElkConnectableShape;
import org.eclipse.elk.graph.ElkEdge;
import org.eclipse.elk.graph.ElkEdgeSection;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkLabel;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.elk.graph.ElkPort;
import org.eclipse.elk.graph.ElkShape;
import org.eclipse.elk.graph.util.ElkGraphUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PrecisionPointList;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Edit policy used to apply layout. This edit policy creates a {@link GmfLayoutCommand} to directly manipulate layout
 * data in the GMF notation model.
 * 
 * Copied from org.eclipse.elk.conn.gmf.GmfLayoutEditPolicy of commit 53a98c9c35bc38b6b7513e0e73fd9d688c34937f.
 * 
 * @author msp
 * @kieler.design proposed by msp
 * @kieler.rating proposed yellow by msp
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConnectionBendpointEditPolicy
 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.GraphicalNodeEditPolicy
 */
public class GmfLayoutEditPolicy extends AbstractEditPolicy {

    /** map of edge layouts to existing point lists. */
    private Map<ElkEdgeSection, PrecisionPointList> pointListMap = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(req.getType()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command getCommand(final Request request) {
        if (ApplyLayoutRequest.REQ_APPLY_LAYOUT.equals(request.getType())) {
            if (request instanceof ApplyLayoutRequest) {
                ApplyLayoutRequest layoutRequest = (ApplyLayoutRequest) request;
                IGraphicalEditPart hostEditPart = (IGraphicalEditPart) getHost();
                GmfLayoutCommand command = new GmfLayoutCommand(hostEditPart.getEditingDomain(), "Automatic Layout", new EObjectAdapter((View) hostEditPart.getModel()));
                double scale = layoutRequest.getScale();

                // retrieve layout data from the request and compute layout data
                // for the command
                for (Pair<ElkGraphElement, IGraphicalEditPart> layoutPair : layoutRequest.getElements()) {
                    if (layoutPair.getFirst() instanceof ElkNode) {
                        addShapeLayout(command, (ElkShape) layoutPair.getFirst(), layoutPair.getSecond(), scale);
                    } else if (layoutPair.getFirst() instanceof ElkPort) {
                        addShapeLayout(command, (ElkPort) layoutPair.getFirst(), layoutPair.getSecond(), scale);
                    } else if (layoutPair.getFirst() instanceof ElkEdge) {
                        addEdgeLayout(command, (ElkEdge) layoutPair.getFirst(), (ConnectionEditPart) layoutPair.getSecond(), scale);
                    } else if (layoutPair.getFirst() instanceof ElkLabel) {
                        addLabelLayout(command, (ElkLabel) layoutPair.getFirst(), layoutPair.getSecond(), scale);
                    }
                }

                pointListMap.clear();
                return new ICommandProxy(command);
            } else {
                return null;
            }
        } else {
            return super.getCommand(request);
        }
    }

    /**
     * Adds a shape layout to the given command.
     * 
     * @param command
     *            command to which a shape layout shall be added
     * @param elkShape
     *            graph element with layout data
     * @param editPart
     *            edit part to which layout is applied
     * @param scale
     *            scale factor for coordinates
     */
    private void addShapeLayout(final GmfLayoutCommand command, final ElkShape elkShape, final IGraphicalEditPart editPart, final double scale) {

        View view = (View) editPart.getModel();

        // The ELK coordinates use floating point, but GMF uses Integer coordinates. The accumulation of rounding could
        // have side effects (switch of one pixel). To avoid the problem, we try to find the more appropriated rounding,
        // above or below, according to parents coordinates.
        PrecisionPoint roundedCoordinates = SiriusElkUtil.getRoundedCoordinatesAccordingToParents(elkShape);
        // Compute the new location
        Point newLocation = new PrecisionPoint(roundedCoordinates.preciseX() * scale, roundedCoordinates.preciseY() * scale);
        Point locationComputedFromOriginalElkShapeLocation = new PrecisionPoint(elkShape.getX() * scale, elkShape.getY() * scale);
        // Border nodes are not concerned by insets.
        if (view instanceof Node && !(elkShape instanceof ElkPort) && !(isRegion(editPart))) {
            Dimension containerTopLeftNegated = GMFHelper.getContainerTopLeftInsets((Node) view, true).getNegated();
            newLocation.translate(containerTopLeftNegated);
            locationComputedFromOriginalElkShapeLocation.translate(containerTopLeftNegated);
        }

        // Compute the new size
        // We add the border size for containers that require it (see createNode method for details)
        PrecisionDimension newSize;
        if (isRegionContainer(editPart)) {
            // The size of region container is "computed" according to its children.
            newSize = new PrecisionDimension(-1, -1);
        } else {
            double shadowBorderSize = ElkDiagramLayoutConnector.getShadowBorderSize(editPart);
            newSize = new PrecisionDimension((elkShape.getWidth() + shadowBorderSize) * scale, (elkShape.getHeight() + shadowBorderSize) * scale);
        }

        // Adapt location and size to real alignment. For some kind of Nodes the alignment is changed in
        // ElkDiagramLayoutConnector.createNodeLabel to have better
        // result for its parent size. So the location must be adapted according to this change.
        EObject siriusObject = editPart.resolveSemanticElement();
        if (siriusObject instanceof DDiagramElement) {
            if (siriusObject instanceof DNodeContainer) {
                if (new DDiagramElementContainerExperimentalQuery((DNodeContainer) siriusObject).isRegionInVerticalStack()) {
                    // The alignment has been forced to center
                    DDiagramElement dde = (DDiagramElement) siriusObject;
                    Style style = dde.getStyle();
                    if (style instanceof LabelStyle) {
                        LabelAlignment labelAlignment = ((LabelStyle) style).getLabelAlignment();
                        if (labelAlignment.equals(LabelAlignment.LEFT) || labelAlignment.equals(LabelAlignment.RIGHT)) {
                            if ((locationComputedFromOriginalElkShapeLocation.preciseX() * 2 % 1) == 0) {
                                // Use directly the original, instead of the rounded value * 2, as it is an integer.
                                newSize.setPreciseWidth(newSize.preciseWidth() + (locationComputedFromOriginalElkShapeLocation.preciseX() * 2));
                            } else {
                                newSize.setPreciseWidth(newSize.preciseWidth() + (newLocation.preciseX() * 2));
                            }
                            newLocation.setX(0);
                        }
                    }
                }
            }
        }

        // Check whether the location has changed
        Object oldx = ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_X());
        Object oldy = ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_Y());

        if (oldx != null && oldy != null && newLocation.x == (Integer) oldx && newLocation.y == (Integer) oldy) {
            newLocation = null;
        }

        // Check whether the size has changed
        Object oldWidth = ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Width());
        Object oldHeight = ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getSize_Height());

        if (oldWidth != null && oldHeight != null && newSize.width == (Integer) oldWidth && newSize.height == (Integer) oldHeight) {
            newSize = null;
        }

        if (newLocation != null || newSize != null) {
            command.addShapeLayout(view, newLocation, newSize);
        }
    }

    private boolean isRegion(IGraphicalEditPart editPart) {
        EObject siriusObject = editPart.resolveSemanticElement();
        if (siriusObject instanceof DDiagramElementContainer) {
            return new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) siriusObject).isRegion();
        }
        return false;
    }

    private boolean isRegionContainer(IGraphicalEditPart editPart) {
        EObject siriusObject = editPart.resolveSemanticElement();
        if (siriusObject instanceof DNodeContainer) {
            return new DNodeContainerExperimentalQuery((DNodeContainer) siriusObject).isRegionContainer();
        }
        return false;
    }

    /**
     * Adds an edge layout to the given command. This method handles standard edges, edges pointing to another edge (as
     * source or as target) but not in all conditions (bugzilla 571925 is a first step to handle edge on edge). It does
     * not handle "note attachment" (as Note is currently not layouted).</BR>
     * With bugzilla 571925 and "egde on egde" specific behavior, the ELKGraph is modified during this step (during the
     * construction of the command). It's not the classical way. Maybe all the ELKGraph modification (around virtual
     * nodes and split edges) must be done at the end of method
     * {@link org.eclipse.sirius.diagram.elk.ElkDiagramLayoutConnector#transferLayout(LayoutMapping, boolean)} just
     * before calling " diagramEditPart.getCommand(applyLayoutRequest)". This will be maybe done later.
     * 
     * @param command
     *            command to which an edge layout shall be added
     * @param elkEdge
     *            edge with layout data
     * @param connectionEditPart
     *            edit part to which layout is applied
     * @param scale
     *            scale factor for coordinates
     */
    private void addEdgeLayout(final GmfLayoutCommand command, final ElkEdge elkEdge, final ConnectionEditPart connectionEditPart, final double scale) {

        ElkEdge secondPartOfEdge = elkEdge.getProperty(ElkDiagramLayoutConnector.SECOND_PART_OF_SPLIT_EDGE);
        if (secondPartOfEdge != null) {
            // It's an edge on edge case. We should "reconstruct" the original layout (build the full edge from the 2
            // parts)
            ElkEdgeSection firstSection = elkEdge.getSections().get(0);
            ElkEdgeSection secondSection = secondPartOfEdge.getSections().get(0);
            // Be sure to have a vertical or an horizontal segment for the "junction". Indeed, the virtual node
            // can leads to some double location not exactly on the same axis. This boolean is set to true when the
            // alignment is done.
            boolean firstPointOfSecondPartAfterStartPoint = true;
            int firstSectionRoundEndY = (int) Math.round(firstSection.getEndY());
            int firstSectionRoundEndX = (int) Math.round(firstSection.getEndX());
            for (ElkBendPoint elkBendPoint : secondSection.getBendPoints()) {
                if (firstPointOfSecondPartAfterStartPoint) {
                    int secondSectionRoundFirstBendpointY = (int) Math.round(elkBendPoint.getY());
                    int secondSectionRoundFirstBendpointX = (int) Math.round(elkBendPoint.getX());
                    if (secondSectionRoundFirstBendpointY - 1 <= firstSectionRoundEndY && firstSectionRoundEndY <= secondSectionRoundFirstBendpointY + 1) {
                        // Align the previous point
                        if (firstSection.getBendPoints().isEmpty()) {
                            firstSection.setStartY(firstSectionRoundEndY);
                        } else {
                            firstSection.getBendPoints().get(firstSection.getBendPoints().size() - 1).setY(firstSectionRoundEndY);;
                        }
                        // Create a bendpoint aligned on the end point of first part
                        ElkGraphUtil.createBendPoint(firstSection, elkBendPoint.getX(), firstSectionRoundEndY);
                    } else if (secondSectionRoundFirstBendpointX - 1 <= firstSectionRoundEndX && firstSectionRoundEndX <= secondSectionRoundFirstBendpointX + 1) {
                        // Align the previous point
                        if (firstSection.getBendPoints().isEmpty()) {
                            firstSection.setStartX(firstSectionRoundEndX);
                        } else {
                            firstSection.getBendPoints().get(firstSection.getBendPoints().size() - 1).setX(firstSectionRoundEndX);
                        }
                        // Create a bendpoint aligned on the end point of first part
                        ElkGraphUtil.createBendPoint(firstSection, Math.round(firstSection.getEndX()), elkBendPoint.getY());
                    }
                    firstPointOfSecondPartAfterStartPoint = false;
                } else {
                    ElkGraphUtil.createBendPoint(firstSection, elkBendPoint.getX(), elkBendPoint.getY());
                }
            }
            if (firstPointOfSecondPartAfterStartPoint) {
                int secondSectionRoundEndX = (int) Math.round(secondSection.getEndX());
                int secondSectionRoundEndY = (int) Math.round(secondSection.getEndY());
                if (secondSectionRoundEndY - 1 <= firstSectionRoundEndY && firstSectionRoundEndY <= secondSectionRoundEndY + 1) {
                    // Align the previous point
                    if (firstSection.getBendPoints().isEmpty()) {
                        firstSection.setStartY(firstSectionRoundEndY);
                    } else {
                        firstSection.getBendPoints().get(firstSection.getBendPoints().size() - 1).setY(firstSectionRoundEndY);;
                    }
                    // Change the end point
                    firstSection.setEndX(secondSection.getEndX());
                    firstSection.setEndY(firstSectionRoundEndY);
                } else if (secondSectionRoundEndX - 1 <= firstSectionRoundEndX && firstSectionRoundEndX <= secondSectionRoundEndX + 1) {
                    // Align the previous point
                    if (firstSection.getBendPoints().isEmpty()) {
                        firstSection.setStartX(firstSectionRoundEndX);
                    } else {
                        firstSection.getBendPoints().get(firstSection.getBendPoints().size() - 1).setX(firstSectionRoundEndX);
                    }
                    // Change the end point
                    firstSection.setEndX(firstSectionRoundEndX);
                    firstSection.setEndY(secondSection.getEndY());
                }
            } else {
                firstSection.setEndX(secondSection.getEndX());
                firstSection.setEndY(secondSection.getEndY());
            }
            // Reset to the original target (we keep the "virtual" node as target, used later for edge(s) pointing to
            // this edge)
            elkEdge.getTargets().add(0, secondPartOfEdge.getTargets().get(0));
        }
        if (connectionEditPart.getSource() != null && connectionEditPart.getTarget() != null) {
            PointList currentEdgeBendpoints = getBendPoints(elkEdge, connectionEditPart.getFigure(), scale);
            // create source terminal identifier
            INodeEditPart sourceEditPart = (INodeEditPart) connectionEditPart.getSource();
            ConnectionAnchor sourceAnchor;
            if (!ElkDiagramLayoutConnector.EDGE_ON_EDGE_ID_NODE.equals(elkEdge.getSources().get(0).getIdentifier())) {
                KVector sourceRel = getRelativeSourcePoint(elkEdge);
                sourceAnchor = new SlidableAnchor(sourceEditPart.getFigure(), new PrecisionPoint(sourceRel.x, sourceRel.y));
            } else {
                // Case of edge pointing to another edge as source: This edge should probably be split: As there is a
                // virtual node, a potential overlap between the two edges exists. We must change that because the
                // virtual node will no longer exist on Sirius side.
                // Identify the last bendpoint of the current edge that is on the source edge and stop on it (do not use
                // the path that overlap the source edge).
                currentEdgeBendpoints = cutEdgeOnEdge(elkEdge, connectionEditPart, scale, true);

                // Compute the source anchor
                PrecisionPointList sourceEdgePointsListInAsboluteCoordinates = getPointListInAbsoluteCoordinates(searchEdgeWithSecondPartProperty(elkEdge.getSources().get(0).getIncomingEdges()));
                Rectangle absoluteBounds = sourceEdgePointsListInAsboluteCoordinates.getBounds();
                // Set a minimal witdh or height to 1 pixel instead of 0 (to avoid computation problem in anchor)
                if (absoluteBounds.width() == 0) {
                    absoluteBounds.setWidth(1);
                } else if (absoluteBounds.height() == 0) {
                    absoluteBounds.setHeight(1);
                }
                Point absoluteOrigin = absoluteBounds.getTopLeft();
                KVector relativeOrigin = ElkUtil.toRelative(new KVector(absoluteOrigin.preciseX(), absoluteOrigin.preciseY()), elkEdge.getContainingNode());
                PrecisionPoint sourceAnchorPoint = BaseSlidableAnchor.getAnchorRelativeLocation(currentEdgeBendpoints.getFirstPoint(),
                        new PrecisionRectangle(relativeOrigin.x, relativeOrigin.y, absoluteBounds.width(), absoluteBounds.height()));
                sourceAnchor = new SlidableAnchor(sourceEditPart.getFigure(), sourceAnchorPoint);
            }
            String sourceTerminal = sourceEditPart.mapConnectionAnchorToTerminal(sourceAnchor);

            // create target terminal identifier
            INodeEditPart targetEditPart = (INodeEditPart) connectionEditPart.getTarget();
            ConnectionAnchor targetAnchor;
            if (!ElkDiagramLayoutConnector.EDGE_ON_EDGE_ID_NODE.equals(elkEdge.getTargets().get(0).getIdentifier())) {
                KVector targetRel = getRelativeTargetPoint(elkEdge);
                targetAnchor = new SlidableAnchor(targetEditPart.getFigure(), new PrecisionPoint(targetRel.x, targetRel.y));
            } else {
                // Case of edge pointing to another edge as target: This edge should probably be split: As there is a
                // virtual node, a potential overlap between the two edges exists. We must change that because the
                // virtual node will no longer exist on Sirius side.
                // Identify the first bendpoint of the current edge that is on the target edge and stop on it (do not
                // use the path that overlap the target edge).
                currentEdgeBendpoints = cutEdgeOnEdge(elkEdge, connectionEditPart, scale, false);

                // Compute the target anchor
                PrecisionPointList targetEdgePointsListInAsboluteCoordinates = getPointListInAbsoluteCoordinates(searchEdgeWithSecondPartProperty(elkEdge.getTargets().get(0).getIncomingEdges()));
                Rectangle absoluteBounds = targetEdgePointsListInAsboluteCoordinates.getBounds();
                // Set a minimal witdh or height to 1 pixel instead of 0 (to avoid computation problem in anchor)
                if (absoluteBounds.width() == 0) {
                    absoluteBounds.setWidth(1);
                } else if (absoluteBounds.height() == 0) {
                    absoluteBounds.setHeight(1);
                }
                Point absoluteOrigin = absoluteBounds.getTopLeft();
                KVector relativeOrigin = ElkUtil.toRelative(new KVector(absoluteOrigin.preciseX(), absoluteOrigin.preciseY()), elkEdge.getContainingNode());
                PrecisionPoint targetAnchorPoint = BaseSlidableAnchor.getAnchorRelativeLocation(currentEdgeBendpoints.getLastPoint(),
                        new Rectangle((int) Math.round(relativeOrigin.x), (int) Math.round(relativeOrigin.y), absoluteBounds.width(), absoluteBounds.height()));
                targetAnchor = new SlidableAnchor(targetEditPart.getFigure(), targetAnchorPoint);
            }
            String targetTerminal = targetEditPart.mapConnectionAnchorToTerminal(targetAnchor);

            // retrieve junction points and transform them to absolute
            // coordinates
            KVectorChain junctionPoints = elkEdge.getProperty(CoreOptions.JUNCTION_POINTS);
            String serializedJP = null;
            if (junctionPoints != null && !junctionPoints.isEmpty()) {
                for (KVector point : junctionPoints) {
                    ElkUtil.toAbsolute(point, elkEdge.getContainingNode());
                }
                serializedJP = junctionPoints.toString();
            }

            EdgeRouting edgeRouting = elkEdge.getContainingNode().getProperty(CoreOptions.EDGE_ROUTING);
            Routing routingToSet = Routing.MANUAL_LITERAL;
            if (edgeRouting.equals(EdgeRouting.ORTHOGONAL)) {
                routingToSet = Routing.RECTILINEAR_LITERAL;
            }
            command.addEdgeLayout((Edge) connectionEditPart.getModel(), currentEdgeBendpoints, sourceTerminal, targetTerminal, serializedJP, routingToSet);
        }
    }

    private PrecisionPointList getPointListInAbsoluteCoordinates(ElkEdge edge) {
        ElkEdgeSection edgeSection = edge.getSections().get(0);
        PrecisionPointList edgePointsListInAsboluteCoordinates = new PrecisionPointList();
        KVector absolutePoint = ElkUtil.toAbsolute(new KVector(edgeSection.getStartX(), edgeSection.getStartY()), edge.getContainingNode());
        edgePointsListInAsboluteCoordinates.addPrecisionPoint(absolutePoint.x, absolutePoint.y);
        for (ElkBendPoint elkBendpoint : edgeSection.getBendPoints()) {
            absolutePoint = ElkUtil.toAbsolute(new KVector(elkBendpoint.getX(), elkBendpoint.getY()), edge.getContainingNode());
            edgePointsListInAsboluteCoordinates.addPrecisionPoint(absolutePoint.x, absolutePoint.y);
        }
        absolutePoint = ElkUtil.toAbsolute(new KVector(edgeSection.getEndX(), edgeSection.getEndY()), edge.getContainingNode());
        edgePointsListInAsboluteCoordinates.addPrecisionPoint(absolutePoint.x, absolutePoint.y);
        return edgePointsListInAsboluteCoordinates;
    }

    private PointList cutEdgeOnEdge(ElkEdge elkEdgeToCut, ConnectionEditPart connectionEditPart, double scale, boolean sourceIsAnotherEdge) {
        LineSeg lineSegToCutInAbsolute;
        PrecisionPoint junctionPoint = new PrecisionPoint();
        ElkEdgeSection sectionToCut = elkEdgeToCut.getSections().get(0);
        if (sourceIsAnotherEdge) {
            junctionPoint = new PrecisionPoint(sectionToCut.getStartX(), sectionToCut.getStartY());
            if (sectionToCut.getBendPoints().size() > 0) {
                ElkBendPoint otherPoint = sectionToCut.getBendPoints().get(0);
                lineSegToCutInAbsolute = new LineSeg(junctionPoint, new PrecisionPoint(otherPoint.getX(), otherPoint.getY()));
            } else {
                lineSegToCutInAbsolute = new LineSeg(junctionPoint, new PrecisionPoint(sectionToCut.getEndX(), sectionToCut.getEndY()));
            }
        } else {
            junctionPoint = new PrecisionPoint(elkEdgeToCut.getSections().get(0).getEndX(), elkEdgeToCut.getSections().get(0).getEndY());
            if (sectionToCut.getBendPoints().size() > 0) {
                ElkBendPoint otherPoint = sectionToCut.getBendPoints().get(sectionToCut.getBendPoints().size() - 1);
                lineSegToCutInAbsolute = new LineSeg(junctionPoint, new PrecisionPoint(otherPoint.getX(), otherPoint.getY()));
            } else {
                lineSegToCutInAbsolute = new LineSeg(junctionPoint, new PrecisionPoint(sectionToCut.getStartX(), sectionToCut.getStartY()));
            }
        }
        KVector absolutePoint = ElkUtil.toAbsolute(new KVector(lineSegToCutInAbsolute.getOrigin().preciseX(), lineSegToCutInAbsolute.getOrigin().preciseY()), elkEdgeToCut.getContainingNode());
        lineSegToCutInAbsolute.setOrigin(new PrecisionPoint(absolutePoint.x, absolutePoint.y));
        absolutePoint = ElkUtil.toAbsolute(new KVector(lineSegToCutInAbsolute.getTerminus().preciseX(), lineSegToCutInAbsolute.getTerminus().preciseY()), elkEdgeToCut.getContainingNode());
        lineSegToCutInAbsolute.setTerminus(new PrecisionPoint(absolutePoint.x, absolutePoint.y));

        // Search the segment on source or target edge that contains the junction point, and use its coordinates
        // (aligned before in addEdgeLayout for this source or target edge)
        ElkEdge sourceOrTargetEdge;
        if (sourceIsAnotherEdge) {
            sourceOrTargetEdge = searchEdgeWithSecondPartProperty(elkEdgeToCut.getSources().get(0).getIncomingEdges());
        } else {
            sourceOrTargetEdge = searchEdgeWithSecondPartProperty(elkEdgeToCut.getTargets().get(0).getIncomingEdges());
        }
        PrecisionPointList sourceOrTargetEdgePointsListInAsboluteCoordinates = getPointListInAbsoluteCoordinates(sourceOrTargetEdge);
        // Use the source or target edge coordinates (aligned before in addEdgeLayout for this source or target edge)
        LineSeg sourceOrTargetEdgeLineSegInAbsolute = findNearestLineSegOfPoint(sourceOrTargetEdgePointsListInAsboluteCoordinates, junctionPoint);
        if (sourceOrTargetEdgeLineSegInAbsolute.isHorizontal()) {
            // Use the y of source or target edge segment (if points of segment to cut are near it)
            double yToUse = ((PrecisionPoint) sourceOrTargetEdgeLineSegInAbsolute.getOrigin()).preciseY();
            PrecisionPoint origin = (PrecisionPoint) lineSegToCutInAbsolute.getOrigin();
            if (yToUse - 1 <= origin.preciseY() && origin.preciseY() <= yToUse + 1) {
                lineSegToCutInAbsolute.setOrigin(new PrecisionPoint(origin.preciseX(), yToUse));
            }
            PrecisionPoint terminus = (PrecisionPoint) lineSegToCutInAbsolute.getTerminus();
            if (yToUse - 1 <= terminus.preciseY() && terminus.preciseY() <= yToUse + 1) {
                lineSegToCutInAbsolute.setTerminus(new PrecisionPoint(terminus.preciseX(), yToUse));
            }
        } else if (sourceOrTargetEdgeLineSegInAbsolute.isVertical()) {
            // Use the x of source or target edge segment (if points of segment to cut are near it)
            double xToUse = ((PrecisionPoint) sourceOrTargetEdgeLineSegInAbsolute.getOrigin()).preciseX();
            PrecisionPoint origin = (PrecisionPoint) lineSegToCutInAbsolute.getOrigin();
            if (xToUse - 1 <= origin.preciseX() && origin.preciseX() <= xToUse + 1) {
                lineSegToCutInAbsolute.setOrigin(new PrecisionPoint(xToUse, origin.preciseY()));
            }
            PrecisionPoint terminus = (PrecisionPoint) lineSegToCutInAbsolute.getTerminus();
            if (xToUse - 1 <= terminus.preciseX() && terminus.preciseX() <= xToUse + 1) {
                lineSegToCutInAbsolute.setTerminus(new PrecisionPoint(xToUse, terminus.preciseY()));
            }
        }
        // Do the cut
        PointList elkEdgeToCutPointList = getBendPoints(elkEdgeToCut, connectionEditPart.getFigure(), scale);
        if (sourceOrTargetEdgeLineSegInAbsolute.containsPoint(lineSegToCutInAbsolute.getTerminus(), 1)) {
            // lineSegToCut other extremity is on the sourceOrTargetEdgeLineSeg, only this point should be kept. The
            // first/last should be delete.
            KVector relativeOriginPoint = ElkUtil.toRelative(new KVector(lineSegToCutInAbsolute.getTerminus().preciseX(), lineSegToCutInAbsolute.getTerminus().preciseY()),
                    elkEdgeToCut.getContainingNode());
            if (sourceIsAnotherEdge) {
                // First point to delete
                elkEdgeToCutPointList.removePoint(0);
                Point point = elkEdgeToCutPointList.getFirstPoint();
                if (sourceOrTargetEdgeLineSegInAbsolute.isHorizontal()) {
                    // y should be used
                    point.setY((int) Math.round(relativeOriginPoint.y));
                } else if (sourceOrTargetEdgeLineSegInAbsolute.isVertical()) {
                    // x should be used
                    point.setX((int) Math.round(relativeOriginPoint.x));
                }
                elkEdgeToCutPointList.setPoint(point, 0);
            } else {
                // Last point to delete
                elkEdgeToCutPointList.removePoint(elkEdgeToCutPointList.size() - 1);
                Point point = elkEdgeToCutPointList.getLastPoint();
                if (sourceOrTargetEdgeLineSegInAbsolute.isHorizontal()) {
                    // y should be used
                    point.setY((int) Math.round(relativeOriginPoint.y));
                } else if (sourceOrTargetEdgeLineSegInAbsolute.isVertical()) {
                    // x should be used
                    point.setX((int) Math.round(relativeOriginPoint.x));
                }
                elkEdgeToCutPointList.setPoint(point, elkEdgeToCutPointList.size() - 1);
            }
        } else if (lineSegToCutInAbsolute.containsPoint(sourceOrTargetEdgeLineSegInAbsolute.getOrigin(), 1)) {
            // lineSegToCut contains the starting point of the sourceOrTargetEdgeLineSeg, this point replace the
            // first/last point.
            KVector relativeOriginPoint = ElkUtil.toRelative(new KVector(sourceOrTargetEdgeLineSegInAbsolute.getOrigin().preciseX(), sourceOrTargetEdgeLineSegInAbsolute.getOrigin().preciseY()),
                    elkEdgeToCut.getContainingNode());
            if (sourceIsAnotherEdge) {
                // First point to replace
                elkEdgeToCutPointList.setPoint(new PrecisionPoint(relativeOriginPoint.x, relativeOriginPoint.y), 0);
            } else {
                // Last point to replace
                elkEdgeToCutPointList.setPoint(sourceOrTargetEdgeLineSegInAbsolute.getOrigin(), elkEdgeToCutPointList.size() - 1);
            }
        } else if (lineSegToCutInAbsolute.containsPoint(sourceOrTargetEdgeLineSegInAbsolute.getTerminus(), 1)) {
            // lineSegToCut contains the ending point of the sourceOrTargetEdgeLineSeg, this point replace the
            // first/last point.
            KVector relativeTerminusPoint = ElkUtil.toRelative(new KVector(sourceOrTargetEdgeLineSegInAbsolute.getTerminus().preciseX(), sourceOrTargetEdgeLineSegInAbsolute.getTerminus().preciseY()),
                    elkEdgeToCut.getContainingNode());
            if (sourceIsAnotherEdge) {
                // First point to replace
                elkEdgeToCutPointList.setPoint(new PrecisionPoint(relativeTerminusPoint.x, relativeTerminusPoint.y), 0);
                // "Aligned" the second last point
                if (sourceOrTargetEdgeLineSegInAbsolute.isHorizontal()) {
                    // y should be used
                    elkEdgeToCutPointList.setPoint(new PrecisionPoint(elkEdgeToCutPointList.getPoint(1).x, relativeTerminusPoint.y), 1);
                } else if (sourceOrTargetEdgeLineSegInAbsolute.isVertical()) {
                    // x should be used
                    elkEdgeToCutPointList.setPoint(new PrecisionPoint(relativeTerminusPoint.x, elkEdgeToCutPointList.getPoint(1).y), 1);
                }
            } else {
                // Last point to replace
                elkEdgeToCutPointList.setPoint(new PrecisionPoint(relativeTerminusPoint.x, relativeTerminusPoint.y), elkEdgeToCutPointList.size() - 1);
                // "Aligned" the before last point
                if (sourceOrTargetEdgeLineSegInAbsolute.isHorizontal()) {
                    // y should be used
                    elkEdgeToCutPointList.setPoint(new PrecisionPoint(elkEdgeToCutPointList.getPoint(elkEdgeToCutPointList.size() - 2).x, relativeTerminusPoint.y), elkEdgeToCutPointList.size() - 2);
                } else if (sourceOrTargetEdgeLineSegInAbsolute.isVertical()) {
                    // x should be used
                    elkEdgeToCutPointList.setPoint(new PrecisionPoint(relativeTerminusPoint.x, elkEdgeToCutPointList.getPoint(elkEdgeToCutPointList.size() - 2).y), elkEdgeToCutPointList.size() - 2);
                }
            }
        }
        return elkEdgeToCutPointList;
    }

    private ElkEdge searchEdgeWithSecondPartProperty(EList<ElkEdge> edges) {
        for (ElkEdge elkEdge : edges) {
            if (elkEdge.getProperty(ElkDiagramLayoutConnector.SECOND_PART_OF_SPLIT_EDGE) != null) {
                return elkEdge;
            }
        }
        return null;
    }

    /**
     * Create a vector that contains the relative position of the source point to the corresponding source node or port.
     * 
     * @param edge
     *            an edge
     * @return the relative source point
     */
    private KVector getRelativeSourcePoint(final ElkEdge edge) {
        // The edge should have exactly one source shape
        ElkConnectableShape sourceShape = edge.getSources().get(0);

        // The edge should have one edge section after layout
        ElkEdgeSection edgeSection = edge.getSections().get(0);
        KVector sourcePoint = new KVector(edgeSection.getStartX(), edgeSection.getStartY());

        // We will now make the source point absolute, and then relative to the
        // source node
        ElkUtil.toAbsolute(sourcePoint, edge.getContainingNode());
        // The method ElkUtil.toRelative is not used here. Indeed, the decimals are then ignored for node, and this
        // leads to a slightly wrong value for the anchor.
        GmfLayoutEditPolicy.toRelativeRound(sourcePoint, ElkGraphUtil.connectableShapeToNode(sourceShape));

        // The end result will be coordinates between 0 and 1, with 0 being at
        // the left / top or the source shape and
        // 1 being at the right / bottom
        if (sourceShape instanceof ElkPort) {
            ElkPort sourcePort = (ElkPort) sourceShape;

            // calculate the relative position to the port size
            if (sourcePort.getWidth() <= 0) {
                sourcePoint.x = 0;
            } else {
                sourcePoint.x = (sourcePoint.x - sourcePort.getX()) / sourcePort.getWidth();
            }

            if (sourcePort.getHeight() <= 0) {
                sourcePoint.y = 0;
            } else {
                sourcePoint.y = (sourcePoint.y - sourcePort.getY()) / sourcePort.getHeight();
            }
        } else {
            // calculate the relative position to the node size
            if (sourceShape.getWidth() <= 0) {
                sourcePoint.x = 0;
            } else {
                sourcePoint.x /= sourceShape.getWidth();
            }
            if (sourceShape.getHeight() <= 0) {
                sourcePoint.y = 0;
            } else {
                sourcePoint.y /= sourceShape.getHeight();
            }
        }

        // check the bound of the relative position
        return sourcePoint.bound(0, 0, 1, 1);
    }

    /**
     * Create a vector that contains the relative position of the target point to the corresponding target node or port.
     * 
     * @param edge
     *            an edge
     * @return the relative target point
     */
    private KVector getRelativeTargetPoint(final ElkEdge edge) {
        // The edge should have exactly one target shape
        ElkConnectableShape targetShape = edge.getTargets().get(0);

        // The edge should have one edge section after layout
        ElkEdgeSection edgeSection = edge.getSections().get(0);
        KVector targetPoint = new KVector(edgeSection.getEndX(), edgeSection.getEndY());

        // We will now make the target point absolute, and then relative to the
        // target node
        ElkUtil.toAbsolute(targetPoint, edge.getContainingNode());
        // The method ElkUtil.toRelative is not used here. Indeed, the decimals are then ignored for node, and this
        // leads to a slightly wrong value for the anchor.
        GmfLayoutEditPolicy.toRelativeRound(targetPoint, ElkGraphUtil.connectableShapeToNode(targetShape));

        // The end result will be coordinates between 0 and 1, with 0 being at
        // the left / top or the source shape and
        // 1 being at the right / bottom
        if (targetShape instanceof ElkPort) {
            ElkPort targetPort = (ElkPort) targetShape;

            // calculate the relative position to the port size
            if (targetPort.getWidth() <= 0) {
                targetPoint.x = 0;
            } else {
                targetPoint.x = (targetPoint.x - targetPort.getX()) / targetPort.getWidth();
            }

            if (targetPort.getHeight() <= 0) {
                targetPoint.y = 0;
            } else {
                targetPoint.y = (targetPoint.y - targetPort.getY()) / targetPort.getHeight();
            }
        } else {
            // calculate the relative position to the node size
            if (targetShape.getWidth() <= 0) {
                targetPoint.x = 0;
            } else {
                targetPoint.x /= targetShape.getWidth();
            }
            if (targetShape.getHeight() <= 0) {
                targetPoint.y = 0;
            } else {
                targetPoint.y /= targetShape.getHeight();
            }
        }

        // check the bound of the relative position
        return targetPoint.bound(0, 0, 1, 1);
    }

    /** see LabelViewConstants.TARGET_LOCATION. */
    private static final int SOURCE_LOCATION = 85;

    /** see LabelViewConstants.MIDDLE_LOCATION. */
    private static final int MIDDLE_LOCATION = 50;

    /** see LabelViewConstants.SOURCE_LOCATION. */
    private static final int TARGET_LOCATION = 15;

    /**
     * Adds an edge label layout to the given command.
     * 
     * @param command
     *            command to which the edge label layout shall be added
     * @param klabel
     *            label with layout data
     * @param labelEditPart
     *            edit part to which layout is applied
     * @param scale
     *            scale factor for coordinates
     */
    private void addLabelLayout(final GmfLayoutCommand command, final ElkLabel klabel, final GraphicalEditPart labelEditPart, final double scale) {

        ElkGraphElement parent = klabel.getParent();

        // node and port labels are processed separately
        if (parent instanceof ElkNode || parent instanceof ElkPort) {
            View view = (View) labelEditPart.getModel();
            int xpos = (int) (klabel.getX() * scale);
            int ypos = (int) (klabel.getY() * scale);
            Object oldx = ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_X());
            Object oldy = ViewUtil.getStructuralFeatureValue(view, NotationPackage.eINSTANCE.getLocation_Y());

            if (oldx == null || oldy == null || xpos != (Integer) oldx || ypos != (Integer) oldy) {
                command.addShapeLayout(view, new Point(xpos, ypos), null);
            }
            return;
        } else if (parent instanceof ElkEdge) {
            // calculate direct new location of the label
            Rectangle targetBounds = new Rectangle(labelEditPart.getFigure().getBounds());
            targetBounds.setX((int) (klabel.getX() * scale));
            targetBounds.setY((int) (klabel.getY() * scale));

            ConnectionEditPart connectionEditPart = (ConnectionEditPart) labelEditPart.getParent();
            PointList bendPoints = getBendPoints((ElkEdge) parent, connectionEditPart.getFigure(), scale);
            EObject modelElement = connectionEditPart.getNotationView().getElement();
            EdgeLabelPlacement labelPlacement = klabel.getProperty(CoreOptions.EDGE_LABELS_PLACEMENT);

            // for labels of the opposite reference of an ecore reference,
            // the list of bend points must be reversed
            if (modelElement instanceof EReference && labelPlacement == EdgeLabelPlacement.TAIL) {
                bendPoints = bendPoints.getCopy();
                bendPoints.reverse();
            }

            // get the referencePoint for the label
            int fromEnd, keyPoint = ConnectionLocator.MIDDLE;
            if (labelEditPart instanceof LabelEditPart) {
                keyPoint = ((LabelEditPart) labelEditPart).getKeyPoint();
            }
            switch (keyPoint) {
            case ConnectionLocator.SOURCE:
                fromEnd = SOURCE_LOCATION;
                break;
            case ConnectionLocator.TARGET:
                fromEnd = TARGET_LOCATION;
                break;
            default:
                fromEnd = MIDDLE_LOCATION;
                break;
            }
            Point refPoint = PointListUtilities.calculatePointRelativeToLine(bendPoints, 0, fromEnd, true);

            // get the new relative location
            Point normalPoint = offsetFromRelativeCoordinate(targetBounds, bendPoints, refPoint);
            if (normalPoint != null) {
                command.addShapeLayout((View) labelEditPart.getModel(), normalPoint, null);
            }
        }
    }

    /**
     * Transform the bend points of the given edge layout into a point list, reusing existing ones if possible. The
     * source and target points of the edge layout are included in the point list.
     * 
     * @param edge
     *            the edge for which to fetch bend points
     * @param isSplineEdge
     *            indicates whether the connection supports splines
     * @return point list with the bend points of the edge layout
     * @param scale
     *            scale factor for coordinates
     */
    private PointList getBendPoints(final ElkEdge edge, final IFigure edgeFigure, final double scale) {
        // This assumes that the edge has at least one edge section, which by
        // this point it should
        ElkEdgeSection edgeSection = edge.getSections().get(0);
        PrecisionPointList pointList = pointListMap.get(edgeSection);
        if (pointList == null) {
            KVectorChain bendPoints = ElkUtil.createVectorChain(edgeSection);

            // for connections that support splines the control points are
            // passed without change
            boolean approx = handleSplineConnection(edgeFigure, edge.getProperty(CoreOptions.EDGE_ROUTING));

            // in other cases an approximation is used
            if (approx && bendPoints.size() >= 1) {
                bendPoints = ElkMath.approximateBezierSpline(bendPoints);
            }

            bendPoints.scale(scale);
            pointList = new PrecisionPointList(bendPoints.size() + 2);
            for (KVector bendPoint : bendPoints) {
                pointList.addPrecisionPoint(bendPoint.x, bendPoint.y);
            }

            pointListMap.put(edgeSection, pointList);
        }
        return pointList;
    }

    /**
     * class name of the ELK SplineConnection.
     * 
     * TODO: This class doesn't exist anymore...
     */
    private static final String SPLINE_CONNECTION = "org.eclipse.elk.core.model.gmf.figures.SplineConnection";

    /**
     * Handle the ELK SplineConnection class without a direct reference to it. Reflection is used to avoid a dependency
     * to its containing plugin.
     * 
     * @param edgeFigure
     *            the edge figure instance
     * @param edgeRouting
     *            the edge routing returned by the layout algorithm
     * @return {@code true} if an approximation should be used to represent the spline
     */
    private static boolean handleSplineConnection(final IFigure edgeFigure, final EdgeRouting edgeRouting) {
        boolean isSC;
        Class<?> clazz = edgeFigure.getClass();
        do {
            String canonicalName = clazz.getCanonicalName();
            // in some cases, eg anonymous classes, the canonical name may be
            // null
            isSC = canonicalName != null && canonicalName.equals(SPLINE_CONNECTION);
            clazz = clazz.getSuperclass();
        } while (!isSC && clazz != null);
        if (isSC) {
            clazz = edgeFigure.getClass();
            try {
                if (edgeRouting == EdgeRouting.SPLINES) {
                    // SplineConnection.SPLINE_CUBIC
                    clazz.getMethod("setSplineMode", int.class).invoke(edgeFigure, 1);
                } else {
                    // SplineConnection.SPLINE_OFF
                    clazz.getMethod("setSplineMode", int.class).invoke(edgeFigure, 0);
                }
                return false;
            } catch (Exception exception) {
                throw new WrappedException(exception);
            }
        }
        // no spline connection class, but spline representation is requested
        return edgeRouting == EdgeRouting.SPLINES;
    }

    /**
     * <!-- CHECKSTYLEOFF LineLength --> Calculates the label offset from the reference point given the label bounds and
     * a points list. This code has been copied and adapted from
     * {@link org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper#offsetFromRelativeCoordinate(IFigure, Rectangle, PointList, Point)}
     * ,
     * {@link org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper#normalizeRelativePointToPointOnLine(PointList, Point, Point)}
     * , and
     * {@link org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper#getOrthogonalDistances(LineSeg, Point, Point)}
     * .
     * 
     * <!-- CHECKSTYLEON LineLength -->
     * 
     * @param bounds
     *            the {@code Rectangle} that is the bounding box of the label
     * @param points
     *            the {@code PointList} that the label offset is relative to
     * @param therefPoint
     *            the {@code Point} that is the reference point that the offset is based on, or {@code null}
     * @return a {@code Point} which represents a value offset from the {@code refPoint} point oriented based on the
     *         nearest line segment, or {@code null} if no such point can be determined
     */
    @SuppressWarnings("restriction")
    public static Point offsetFromRelativeCoordinate(final Rectangle bounds, final PointList points, final Point therefPoint) {
        Point refPoint = therefPoint;
        if (refPoint == null) {
            refPoint = points.getFirstPoint();
        }
        // compensate for the fact that we are using the figure center
        bounds.translate(bounds.width / 2, bounds.height / 2);
        Point offset = new Point(bounds.x - refPoint.x, bounds.y - refPoint.y);
        // calculate slope of line
        if (points.size() == 1) {
            // this is a node...
            return offset;
        } else if (points.size() >= 2) {
            // this is an edge...
            int segIndex = PointListUtilities.findNearestLineSegIndexOfPoint(points, refPoint);
            @SuppressWarnings("rawtypes")
            List segmentsList = PointListUtilities.getLineSegments(points);
            if (segIndex <= 0) {
                segIndex = 0;
            } else if (segIndex > segmentsList.size()) {
                segIndex = segmentsList.size() - 1;
            } else {
                segIndex--;
            }
            LineSeg segment = (LineSeg) segmentsList.get(segIndex);
            Point normalOffset = null;
            if (segment.isHorizontal()) {
                if (segment.getOrigin().x > segment.getTerminus().x) {
                    normalOffset = offset.getNegated();
                    return normalOffset;
                } else {
                    normalOffset = offset;
                    return normalOffset;
                }
            } else if (segment.isVertical()) {
                if (segment.getOrigin().y < segment.getTerminus().y) {
                    normalOffset = offset.scale(-1, 1).transpose();
                    return normalOffset;
                } else {
                    normalOffset = offset.scale(1, -1).transpose();
                    return normalOffset;
                }
            } else {
                Point offsetRefPoint = refPoint.getTranslated(offset);
                LineSeg parallelSeg = segment.getParallelLineSegThroughPoint(offsetRefPoint);
                Point p1 = parallelSeg.perpIntersect(refPoint.x, refPoint.y);
                double dx = p1.getDistance(offsetRefPoint) * ((p1.x > offsetRefPoint.x) ? -1 : 1);
                double dy = p1.getDistance(refPoint) * ((p1.y < refPoint.y) ? -1 : 1);
                Point orth = new PrecisionPoint(dx, dy);
                // reflection in the y axis
                if (segment.getOrigin().x > segment.getTerminus().x) {
                    orth = orth.scale(-1, -1);
                }
                return orth;
            }
        }
        return null;
    }

    /**
     * Method inspired from {@link PointListUtilities#findNearestLineSegIndexOfPoint(PointList, Point)}. Calculate the
     * nearest line segment index distance wise to the given point.
     * 
     * @param points
     *            PointList to calculate the nearest line segment of.
     * @param ptCoord
     *            the <code>Point</code> to test containment of.
     * @return int Index of line segment that is nearest in the polyline to the given point. The index is 1 based where
     *         1 represents the first segment.
     */
    static public LineSeg findNearestLineSegOfPoint(PointList points, final Point ptCoord) {
        int BIGDISTANCE = 32766;
        List<?> mySegments = PointListUtilities.getLineSegments(points);
        ListIterator<?> lineIter = mySegments.listIterator();
        double minDistance = BIGDISTANCE;
        double nextDistance = 0;

        LineSeg result = null;
        while (lineIter.hasNext()) {
            LineSeg aSegment = (LineSeg) lineIter.next();

            nextDistance = aSegment.preciseDistanceToPoint(ptCoord.x, ptCoord.y);

            if (nextDistance < minDistance) {
                minDistance = nextDistance;
                result = aSegment;
            }
        }

        return result;
    }

    /**
     * Converts a double value into an integer value, avoiding rounding effects.</BR>
     * Copied from {@link org.eclipse.draw2d.geometry.PrecisionGeometry#doubleToInteger(double)}.
     * 
     * @param doubleValue
     *            the double value to convert
     * @return the integer value for the double.
     */
    protected static final int doubleToInteger(double doubleValue) {
        return (int) Math.floor(doubleValue + 0.000000001);
    }

    /**
     * Converts the given absolute point to a relative location (using round coordinates, as it will we in Draw2D
     * after)./BR> Copied from {@link ElkUtil#toRelative(KVector, ElkNode)}.
     *
     * @param point
     *            an absolute point
     * @param parent
     *            the parent node to which the point shall be made relative to
     * @return {@code point} for convenience
     */
    public static KVector toRelativeRound(final KVector point, final ElkNode parent) {
        ElkNode node = parent;
        while (node != null) {
            point.add(-doubleToInteger(node.getX()), -doubleToInteger(node.getY()));
            node = node.getParent();
        }
        return point;
    }
}
