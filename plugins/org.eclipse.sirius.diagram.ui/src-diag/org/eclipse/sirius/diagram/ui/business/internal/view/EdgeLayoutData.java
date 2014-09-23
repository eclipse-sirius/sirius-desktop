/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.view;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.ConnectorStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SetConnectionBendpointsAccordingToExtremityMoveCommmand;

/**
 * Store the layout information of a DEdge at a given time. This class is used
 * only during drag'n'drop.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EdgeLayoutData extends AbstractEdgeLayoutData {
    /**
     * This layout data has been created because of a move, or of a drag'n'drop,
     * of its source. If both source and target have been moved, or
     * drap'n'droped, it should be handled by the "consumer" of the data.
     */
    public static final int CAUSED_BY_SOURCE = 0;

    /**
     * This layout data has been created because of a move, or of a drag'n'drop,
     * of its target. If both source and target have been moved, or
     * drap'n'droped, it should be handled by the "consumer" of the data.
     */
    public static final int CAUSED_BY_TARGET = 1;

    /** This layout data has been created because of another reason. */
    public static final int CAUSED_BY_SOMETING_ELSE = 2;

    private PointList pointList;

    /**
     * <code>String</code> identifier associated with the source anchor
     * 
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.IAnchorableFigure}
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor.parseTerminalString}
     * 
     */
    private String sourceTerminal;

    private Point sourceRefPoint;

    /**
     * <code>String</code> identifier associated with the target anchor.
     * 
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.IAnchorableFigure}
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor.parseTerminalString}
     */
    private String targetTerminal;

    private Point targetRefPoint;

    private Routing routing;

    private EdgeLabelLayoutData edgeLabelLayoutData;

    private LayoutData edgeSourceLayoutData;

    private LayoutData edgeTargetLayoutData;

    /**
     * Constructor.
     * 
     * @param parent
     *            the parent layout data.
     * @param target
     *            The node to deal with
     * @param gmfEdge
     *            the corresponding GMF edge
     */
    public EdgeLayoutData(final LayoutData parent, final DEdge target, final Edge gmfEdge) {
        super(parent, target, gmfEdge);
    }

    /**
     * Constructor for not yet created DEdge but known source/target.
     * 
     * @param edgeSourceLayoutData
     *            the source layout data.
     * @param edgeTargetLayoutData
     *            the target layout data
     */
    public EdgeLayoutData(LayoutData edgeSourceLayoutData, LayoutData edgeTargetLayoutData) {
        super(null, null, null);
        this.edgeSourceLayoutData = edgeSourceLayoutData;
        this.edgeTargetLayoutData = edgeTargetLayoutData;
    }

    /**
     * Initialize this object.
     * 
     * @param gmfEdge
     *            the corresponding GMF edge
     */
    @Override
    protected void init(final Edge gmfEdge) {
        final ConnectorStyle connectorStyle = (ConnectorStyle) gmfEdge.getStyle(NotationPackage.eINSTANCE.getConnectorStyle());
        if (connectorStyle != null) {
            routing = connectorStyle.getRouting();
        }
        if (gmfEdge.getSourceAnchor() instanceof IdentityAnchor) {
            sourceTerminal = ((IdentityAnchor) gmfEdge.getSourceAnchor()).getId();
        }
        if (gmfEdge.getTargetAnchor() instanceof IdentityAnchor) {
            targetTerminal = ((IdentityAnchor) gmfEdge.getTargetAnchor()).getId();
        }
        final ConnectionEditPart connectionEditPart = (ConnectionEditPart) getRoot().getViewer().getEditPartRegistry().get(gmfEdge);
        if (connectionEditPart != null) {
            final PolylineConnectionEx polylineConnectionEx = (PolylineConnectionEx) connectionEditPart.getFigure();
            polylineConnectionEx.getConnectionRouter().route(polylineConnectionEx);
            sourceRefPoint = polylineConnectionEx.getSourceAnchor().getReferencePoint();
            polylineConnectionEx.translateToRelative(sourceRefPoint);
            targetRefPoint = polylineConnectionEx.getTargetAnchor().getReferencePoint();
            polylineConnectionEx.translateToRelative(targetRefPoint);
            pointList = polylineConnectionEx.getPoints().getCopy();
            // Translate some points with the moveDelta of rootEditPart
            // according to source or target move. If both source and target
            // have been moved, do not change anything.
            int cause = getCause();
            if (cause == CAUSED_BY_SOURCE || cause == CAUSED_BY_TARGET) {
                Point moveDelta = getRoot().getMoveDelta();
                SetConnectionBendpointsAccordingToExtremityMoveCommmand.adaptPointListAndRefPoints(cause == CAUSED_BY_SOURCE, moveDelta, connectionEditPart, sourceRefPoint, targetRefPoint, pointList);
            }
        }
        edgeLabelLayoutData = new EdgeLabelLayoutData(getParent(), getTarget(), gmfEdge);
    }

    /**
     * Return this EdgeLayoutData if the edge is the target of it.
     * 
     * @param edge
     *            The search element
     * @return the corresponding EdgeLayoutData or null if not found.
     */
    public EdgeLayoutData getData(final DEdge edge) {
        return !isConsume() && getTarget().equals(edge) ? this : null;
    }

    /**
     * Return the pointList.
     * 
     * @return the pointList
     */
    public PointList getPointList() {
        return pointList;
    }

    /**
     * Return the sourceRefPoint.
     * 
     * @return the sourceRefPoint
     */
    public Point getSourceRefPoint() {
        return sourceRefPoint;
    }

    /**
     * Return the targetRefPoint.
     * 
     * @return the targetRefPoint
     */
    public Point getTargetRefPoint() {
        return targetRefPoint;
    }

    /**
     * Return the routing style.
     * 
     * @return the routing
     */
    public Routing getRouting() {
        return routing;
    }

    /**
     * Return the source terminal.
     * 
     * @return the sourceTerminal
     */
    public String getSourceTerminal() {
        return sourceTerminal;
    }

    /**
     * Return the target terminal.
     * 
     * @return the targetTerminal
     */
    public String getTargetTerminal() {
        return targetTerminal;
    }

    /**
     * Returns the edgeLabelLayoutData.
     * 
     * @return The edgeLabelLayoutData.
     */
    public EdgeLabelLayoutData getEdgeLabelLayoutData() {
        return edgeLabelLayoutData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isConsume() {
        return super.isConsume() && getEdgeLabelLayoutData().isConsume();
    }

    /**
     * Get the source of the edge.
     * 
     * @return the source of the edge
     */
    public LayoutData getEdgeSourceLayoutData() {
        return edgeSourceLayoutData;
    }

    /**
     * Get the target of the edge.
     * 
     * @return the target of the edge.
     */
    public LayoutData getEdgeTargetLayoutData() {
        return edgeTargetLayoutData;
    }

    /**
     * Set the point list.
     * 
     * @param pointList
     *            the point list.
     */
    public void setPointList(PointList pointList) {
        this.pointList = pointList;
    }

    /**
     * Set the source terminal.
     * 
     * @param sourceTerminal
     *            the source terminal.
     */
    public void setSourceTerminal(String sourceTerminal) {
        this.sourceTerminal = sourceTerminal;
    }

    /**
     * Set the reference point for the source.
     * 
     * @param sourceRefPoint
     *            the reference point for the source.
     */
    public void setSourceRefPoint(Point sourceRefPoint) {
        this.sourceRefPoint = sourceRefPoint;
    }

    /**
     * Set the target terminal.
     * 
     * @param targetTerminal
     *            the target terminal.
     */
    public void setTargetTerminal(String targetTerminal) {
        this.targetTerminal = targetTerminal;
    }

    /**
     * Set the reference point for the target.
     * 
     * @param targetRefPoint
     *            the reference point for the target.
     */
    public void setTargetRefPoint(Point targetRefPoint) {
        this.targetRefPoint = targetRefPoint;
    }

    /**
     * Set the routing style.
     * 
     * @param routing
     *            the routing style.
     */
    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    /**
     * Get the origin of this layout data:
     * <UL>
     * <LI>Caused by a move of the source: CAUSED_BY_SOURCE</LI>
     * <LI>Caused by a move of the target: CAUSED_BY_TARGET</LI>
     * <LI>Caused by something else: CAUSED_BY_SOMETING_ELSE.</LI>
     * </UL>
     * 
     * @return the cause of this layout data
     */
    public int getCause() {
        int result = CAUSED_BY_SOMETING_ELSE;
        if (getTarget() != null && getParent().getTarget() != null) {
            if (getParent().getTarget().equals(getTarget().getSourceNode())) {
                result = CAUSED_BY_SOURCE;
            } else if (getParent().getTarget().equals(getTarget().getTargetNode())) {
                result = CAUSED_BY_TARGET;
            }
        }
        return result;
    }
}
