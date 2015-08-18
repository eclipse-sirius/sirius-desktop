/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.util;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Lists;

/**
 * Utilities for GMF notation model modifications.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class GMFNotationUtilities {

    /**
     * Error message for case where we detect an edge connected to other edge.
     */
    private static final String MSG_EDGE_ON_EDGE_NOT_MANAGED = "Edge on edge not managed";

    /**
     * Default constructor.
     */
    private GMFNotationUtilities() {

    }

    /**
     * Set the source anchor of the <code>edge</code> according to the new
     * points list. The second point of this list is used to determine the new X
     * location of this Anchor.
     * 
     * @param edge
     *            The edge to modify
     * @param newPoints
     *            The new points list
     * @return an optional Point representing the source reference point
     *         corresponding to the new source anchor. This Option can be null
     *         if the anchor is not changed.
     */
    public static Option<Point> setSourceAnchor(Edge edge, PointList newPoints) {
        Point referencePointOfChangedAnchor = null;
        if (edge.getSource() instanceof Node) {
            // Get the source bounds
            Rectangle sourceFigure = GMFHelper.getBounds((Node) edge.getSource(), true);
            // Get the new x location of anchor according to second points of
            // the
            // newPoints list (this point is the end of the first segment)
            int newXAnchorLocation = newPoints.getPoint(1).x;
            // Compute new X anchor percentage
            double newXAnchorPercentage = ((double) (newXAnchorLocation - sourceFigure.x)) / ((double) sourceFigure.width);

            if (edge.getSourceAnchor() instanceof IdentityAnchor) {
                // Get current anchor position
                IdentityAnchor anchor = (IdentityAnchor) edge.getSourceAnchor();
                PrecisionPoint relativeReferencePoint = BaseSlidableAnchor.parseTerminalString(anchor.getId());
                if (relativeReferencePoint.preciseX() != newXAnchorPercentage) {
                    // Change x id anchor
                    setIdentityAnchorId(anchor, newXAnchorPercentage, relativeReferencePoint.preciseY());
                    referencePointOfChangedAnchor = new PrecisionPoint(sourceFigure.getLocation().x + sourceFigure.width * newXAnchorPercentage, sourceFigure.getLocation().y + sourceFigure.height
                            * relativeReferencePoint.preciseY());
                }
            } else if (edge.getSourceAnchor() == null) {
                // Use the center of the source if there is no previous
                // sourceAnchor
                IdentityAnchor anchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                setIdentityAnchorId(anchor, newXAnchorPercentage, 0.5d);
                edge.setSourceAnchor(anchor);
                referencePointOfChangedAnchor = new PrecisionPoint(sourceFigure.getLocation().x + sourceFigure.width * newXAnchorPercentage, sourceFigure.getLocation().y + sourceFigure.height * 0.5d);
            }
        } else if (edge.getSource() instanceof Edge) {
            throw new UnsupportedOperationException(MSG_EDGE_ON_EDGE_NOT_MANAGED);
        }
        if (referencePointOfChangedAnchor == null) {
            return Options.newNone();
        } else {
            return Options.newSome(referencePointOfChangedAnchor);
        }
    }

    /**
     * Set the target anchor of the <code>edge</code> according to the new
     * points list. The third point of this list is used to determine the new X
     * location of this Anchor.
     * 
     * @param edge
     *            The edge to modify
     * @param newPoints
     *            The new points list
     * @return an optional Point representing the target reference point
     *         corresponding to the new target anchor. This Option can be null
     *         if the anchor is not changed.
     */
    public static Option<Point> setTargetAnchor(Edge edge, PointList newPoints) {
        Point referencePointOfChangedAnchor = null;
        if (edge.getTarget() instanceof Node) {
            // Get the target bounds
            Rectangle targetFigure = GMFHelper.getBounds((Node) edge.getTarget(), true);
            // Get the new x location of anchor according to third points of the
            // newPoints list (this point is the beginning of the last segment)
            int newXAnchorLocation = newPoints.getPoint(2).x;
            // Compute new X anchor percentage
            double newXAnchorPercentage = ((double) (newXAnchorLocation - targetFigure.x)) / ((double) targetFigure.width);

            if (edge.getTargetAnchor() instanceof IdentityAnchor) {
                // Get current anchor position
                IdentityAnchor anchor = (IdentityAnchor) edge.getTargetAnchor();
                PrecisionPoint relativeReferencePoint = BaseSlidableAnchor.parseTerminalString(anchor.getId());
                if (relativeReferencePoint.preciseX() != newXAnchorPercentage) {
                    // Change x id anchor
                    setIdentityAnchorId(anchor, newXAnchorPercentage, relativeReferencePoint.preciseY());
                    referencePointOfChangedAnchor = new PrecisionPoint(targetFigure.getLocation().x + targetFigure.width * newXAnchorPercentage, targetFigure.getLocation().y + targetFigure.height
                            * relativeReferencePoint.preciseY());
                }
            } else if (edge.getTargetAnchor() == null) {
                // Use the center of the source if there is no previous
                // sourceAnchor
                IdentityAnchor anchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                setIdentityAnchorId(anchor, newXAnchorPercentage, 0.5d);
                edge.setTargetAnchor(anchor);
                referencePointOfChangedAnchor = new PrecisionPoint(targetFigure.getLocation().x + targetFigure.width * newXAnchorPercentage, targetFigure.getLocation().y + targetFigure.height * 0.5d);
            }
        } else if (edge.getTarget() instanceof Edge) {
            throw new UnsupportedOperationException(MSG_EDGE_ON_EDGE_NOT_MANAGED);
        }
        if (referencePointOfChangedAnchor == null) {
            return Options.newNone();
        } else {
            return Options.newSome(referencePointOfChangedAnchor);
        }
    }

    /**
     * Set the id of an IdentiyAnchor from x and y percentages.
     * 
     * @param anchor
     *            The anchor on which to set id
     * @param xPercentage
     *            the x percentage to use in id
     * @param yPercentage
     *            the y percentage to use in id
     */
    public static void setIdentityAnchorId(IdentityAnchor anchor, double xPercentage, double yPercentage) {
        anchor.setId(getTerminalString(xPercentage, yPercentage));
    }

    /**
     * Get the terminal string used in {@link IdentityAnchor} from its
     * xPercentage and yPercentage.
     * 
     * @param xPercentage
     *            the x percentage to use in id
     * @param yPercentage
     *            the y percentage to use in id
     * @return the string terminal used in {@link IdentityAnchor}
     */
    public static String getTerminalString(double xPercentage, double yPercentage) {
        return "(" + String.valueOf(xPercentage) + "," + String.valueOf(yPercentage) + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    /**
     * Change the bendpoints of the GMF edge according to draw2d elements
     * (pointList, sourceRefPoint and targetRefPoint).
     * 
     * @param edge
     *            The edge to modify
     * @param newPoints
     *            The new points list
     * @param sourceRefPoint
     *            The source reference point (to compute the GMF
     *            RelativeBendpoints)
     * @param targetRefPoint
     *            The target reference point (to compute the GMF
     *            RelativeBendpoints)
     */
    public static void setGMFBendpoints(Edge edge, PointList newPoints, Point sourceRefPoint, Point targetRefPoint) {
        List<Object> newBendpoints = Lists.newArrayList();
        int numOfPoints = newPoints.size();
        for (short i = 0; i < numOfPoints; i++) {
            Dimension s = newPoints.getPoint(i).getDifference(sourceRefPoint);
            Dimension t = newPoints.getPoint(i).getDifference(targetRefPoint);
            newBendpoints.add(new RelativeBendpoint(s.width, s.height, t.width, t.height));
        }
        RelativeBendpoints points = (RelativeBendpoints) edge.getBendpoints();
        points.setPoints(newBendpoints);
    }

    /**
     * Compute the new source anchor according to an horizontal move
     * representing by the deltaX.
     * 
     * @param edge
     *            The edge to modify
     * @param deltaX
     *            The horizontal delta move
     * 
     * @return an optional Point representing the source reference point
     *         corresponding to the new source. This Option can be null if the
     *         anchor is not changed.
     */
    public static Option<PrecisionPoint> setSourceAnchor(Edge edge, int deltaX) {
        // Compute new x anchor of source point after horizontal source
        // segment movement
        if (edge.getSource() instanceof Node) {
            Rectangle sourceFigure = GMFHelper.getBounds((Node) edge.getSource(), true);
            if (edge.getSourceAnchor() instanceof IdentityAnchor) {
                // Get current anchor position
                IdentityAnchor anchor = (IdentityAnchor) edge.getSourceAnchor();
                PrecisionPoint relativeReferencePoint = BaseSlidableAnchor.parseTerminalString(anchor.getId());
                // Get the current x location of end segment
                double xCurrentPosition = sourceFigure.x + sourceFigure.width * relativeReferencePoint.preciseX();
                // Compute position of x location of end segment after move
                double xNewPosition = xCurrentPosition - deltaX;
                // Compute new anchor percentage
                double newXAnchorPercentage = (xNewPosition - sourceFigure.x) / (sourceFigure.width);
                // Change x id anchor
                setIdentityAnchorId(anchor, newXAnchorPercentage, relativeReferencePoint.preciseY());
                return Options.newSome(new PrecisionPoint(sourceFigure.getLocation().x + sourceFigure.width * newXAnchorPercentage, sourceFigure.getLocation().y + sourceFigure.height
                        * relativeReferencePoint.preciseY()));
            }
        } else {
            throw new UnsupportedOperationException(MSG_EDGE_ON_EDGE_NOT_MANAGED);
        }
        return Options.newNone();
    }

    /**
     * Compute the new target anchor according to an horizontal move
     * representing by the deltaX.
     * 
     * @param edge
     *            The edge to modify
     * @param deltaX
     *            The horizontal delta move
     * 
     * @return an optional Point representing the target reference point
     *         corresponding to the new target anchor. This Option can be null
     *         if the anchor is not changed.
     */
    public static Option<PrecisionPoint> setTargetAnchor(Edge edge, int deltaX) {
        // Compute new x anchor of target point after horizontal target
        // segment movement
        if (edge.getTarget() instanceof Node) {
            Rectangle targetFigure = GMFHelper.getBounds((Node) edge.getTarget(), true);
            if (edge.getTargetAnchor() instanceof IdentityAnchor) {
                // Get current anchor position
                IdentityAnchor anchor = (IdentityAnchor) edge.getTargetAnchor();
                PrecisionPoint relativeReferencePoint = BaseSlidableAnchor.parseTerminalString(anchor.getId());
                // Get the current x location of end segment
                double xCurrentPosition = targetFigure.x + targetFigure.width * relativeReferencePoint.preciseX();
                // Compute position of x location of end segment after move
                double xNewPosition = xCurrentPosition - deltaX;
                // Compute new anchor percentage
                double newXAnchorPercentage = (xNewPosition - targetFigure.x) / (targetFigure.width);
                // Change x id anchor
                setIdentityAnchorId(anchor, newXAnchorPercentage, relativeReferencePoint.preciseY());
                return Options.newSome(new PrecisionPoint(targetFigure.getLocation().x + targetFigure.width * newXAnchorPercentage, targetFigure.getLocation().y + targetFigure.height
                        * relativeReferencePoint.preciseY()));
            }
        } else {
            throw new UnsupportedOperationException(MSG_EDGE_ON_EDGE_NOT_MANAGED);
        }
        return Options.newNone();
    }

    /**
     * Recompute all the bendpoints of <code>edgeToModify</code> according to a
     * reference edge. This two edges must be branch on the same tree
     * (TreeRouter).
     * 
     * @param referenceEdge
     *            The reference edge
     * @param edgeToModify
     *            Edge to modify
     */
    public static void setBendpoints(Edge referenceEdge, Edge edgeToModify) {
        String sourceTerminalString = getTerminalString(0.5d, 0.5d);
        if (edgeToModify.getSourceAnchor() instanceof IdentityAnchor) {
            sourceTerminalString = ((IdentityAnchor) edgeToModify.getSourceAnchor()).getId();
        }
        String targetTerminalString = getTerminalString(0.5d, 0.5d);
        if (edgeToModify.getTargetAnchor() instanceof IdentityAnchor) {
            targetTerminalString = ((IdentityAnchor) edgeToModify.getTargetAnchor()).getId();
        }
        PrecisionPoint sourceAnchorReference = BaseSlidableAnchor.parseTerminalString(sourceTerminalString);
        PrecisionPoint targetAnchorReference = BaseSlidableAnchor.parseTerminalString(targetTerminalString);

        Point sourceLocation = null;
        Point targetLocation = null;
        Rectangle sourceBounds = null;
        Rectangle targetBounds = null;
        if (edgeToModify.getSource() instanceof Node) {
            sourceBounds = GMFHelper.getBounds((Node) edgeToModify.getSource(), true);
            sourceLocation = new PrecisionPoint(sourceBounds.x + sourceBounds.width * sourceAnchorReference.preciseX(), sourceBounds.y + sourceBounds.height * sourceAnchorReference.preciseY());
        } else if (edgeToModify.getSource() instanceof Edge) {
            // TODO Manage edge on egde ...
            throw new UnsupportedOperationException(MSG_EDGE_ON_EDGE_NOT_MANAGED);
        }
        if (edgeToModify.getTarget() instanceof Node) {
            targetBounds = GMFHelper.getBounds((Node) edgeToModify.getTarget(), true);
            targetLocation = new PrecisionPoint(targetBounds.x + targetBounds.width * targetAnchorReference.preciseX(), targetBounds.y + targetBounds.height * targetAnchorReference.preciseY());
        } else if (edgeToModify.getTarget() instanceof Edge) {
            // TODO Manage edge on egde ...
            throw new UnsupportedOperationException(MSG_EDGE_ON_EDGE_NOT_MANAGED);
        }

        if (referenceEdge.getBendpoints() instanceof RelativeBendpoints) {
            Object objectSecondRelativeBendpointOfMovedEdge = ((RelativeBendpoints) referenceEdge.getBendpoints()).getPoints().get(1);
            if (objectSecondRelativeBendpointOfMovedEdge instanceof RelativeBendpoint) {
                RelativeBendpoint secondRelativeBendpointOfMovedEdge = (RelativeBendpoint) objectSecondRelativeBendpointOfMovedEdge;
                List<Object> brotherNewBendpoints = Lists.newArrayList();
                brotherNewBendpoints.add(new RelativeBendpoint(0, sourceBounds.y - sourceLocation.y, sourceLocation.x - targetLocation.x, sourceBounds.y - targetLocation.y));
                brotherNewBendpoints.add(new RelativeBendpoint(0, targetLocation.y + secondRelativeBendpointOfMovedEdge.getTargetY() - sourceLocation.y, sourceLocation.x - targetLocation.x,
                        secondRelativeBendpointOfMovedEdge.getTargetY()));
                brotherNewBendpoints.add(new RelativeBendpoint(targetLocation.x - sourceLocation.x, targetLocation.y + secondRelativeBendpointOfMovedEdge.getTargetY() - sourceLocation.y, 0,
                        secondRelativeBendpointOfMovedEdge.getTargetY()));
                brotherNewBendpoints.add(new RelativeBendpoint(targetLocation.x - sourceLocation.x, targetBounds.y + targetBounds.width - sourceLocation.y, 0, targetBounds.y + targetBounds.width
                        - targetLocation.y));
                ((RelativeBendpoints) edgeToModify.getBendpoints()).setPoints(brotherNewBendpoints);
            }
        }
    }

    /**
     * Set the target anchor of the <code>edgeToModify</code> with the same
     * target anchor of the <code>referencEdge</code> (only if reference anchor
     * is an identity anchor and, the anchor of edge to modify is null or is an
     * identify anchor).
     * 
     * @param referenceEdge
     *            reference edge
     * @param edgeToModify
     *            edge to modify
     */
    public static void setTargetAnchor(Edge referenceEdge, Edge edgeToModify) {
        if (referenceEdge.getTargetAnchor() instanceof IdentityAnchor && (edgeToModify.getTargetAnchor() instanceof IdentityAnchor || edgeToModify.getTargetAnchor() == null)) {
            IdentityAnchor refAnchor = (IdentityAnchor) referenceEdge.getTargetAnchor();
            if (edgeToModify.getTargetAnchor() == null) {
                edgeToModify.setTargetAnchor(NotationFactory.eINSTANCE.createIdentityAnchor());
            }
            IdentityAnchor anchorToModify = (IdentityAnchor) edgeToModify.getTargetAnchor();
            anchorToModify.setId(refAnchor.getId());
        }
    }

    /**
     * Set the source anchor of the <code>edgeToModify</code> with the same
     * target anchor of the <code>referenceEdge</code> (only if reference anchor
     * is an identity anchor and, the anchor of edge to modify is null or is an
     * identify anchor).
     * 
     * @param referenceEdge
     *            reference edge
     * @param edgeToModify
     *            edge to modify
     */
    public static void setSourceAnchor(Edge referenceEdge, Edge edgeToModify) {
        if (referenceEdge.getSourceAnchor() instanceof IdentityAnchor && (edgeToModify.getSourceAnchor() instanceof IdentityAnchor || edgeToModify.getSourceAnchor() == null)) {
            IdentityAnchor refAnchor = (IdentityAnchor) referenceEdge.getSourceAnchor();
            if (edgeToModify.getSourceAnchor() == null) {
                edgeToModify.setSourceAnchor(NotationFactory.eINSTANCE.createIdentityAnchor());
            }
            IdentityAnchor anchorToModify = (IdentityAnchor) edgeToModify.getSourceAnchor();
            anchorToModify.setId(refAnchor.getId());
        }
    }

    /**
     * Change the source or target anchor and the bendpoints of the brothers of
     * <code>edge</code> according to this edge.
     * 
     * @param edge
     *            The edge reference
     */
    public static void setBrothersAnchorAndBendpointsAccordingToEdge(Edge edge) {
        EdgeQuery edgeQuery = new EdgeQuery(edge);
        boolean sourceSide = false;
        List<Edge> brothers;
        if (edgeQuery.isEdgeOnTreeOnSourceSide()) {
            brothers = edgeQuery.getBrothersOnTreeOnSourceSide();
            sourceSide = true;
        } else if (edgeQuery.isEdgeOnTreeOnTargetSide()) {
            brothers = edgeQuery.getBrothersOnTreeOnTargetSide();
        } else {
            brothers = Lists.newArrayList();
        }
        for (Edge brother : brothers) {
            if (sourceSide) {
                GMFNotationUtilities.setSourceAnchor(edge, brother);
            } else {
                GMFNotationUtilities.setTargetAnchor(edge, brother);
            }
            GMFNotationUtilities.setBendpoints(edge, brother);
        }
    }
}
