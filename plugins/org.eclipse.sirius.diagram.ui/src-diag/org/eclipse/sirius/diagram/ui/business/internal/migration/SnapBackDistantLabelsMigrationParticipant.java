/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES and others.
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.query.DDiagramGraphicalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A representation file migration to detect labels that are far from their edge and reset them to their default
 * location. In diagram containing such labels, this migration also detects nodes with very huge coordinates and
 * relocates them to a more readable location.
 * 
 * @author lredor
 */
@SuppressWarnings("restriction")
public class SnapBackDistantLabelsMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The Sirius version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("12.1.0.201706291600"); //$NON-NLS-1$

    /**
     * The absolute maximum x or y coordinate from which label is considered distant.
     */
    private static final int LABEL_UPPER_LIMIT = 1000;

    /**
     * The absolute minimal x or y coordinate from which label is not considered distant.
     */
    private static final int LABEL_LOWER_LIMIT = 250;

    /**
     * The absolute maximum x or y coordinates from which node is considered with "very huge coordinates".
     */
    private static final int NODES_LIMIT = 1000000;

    /**
     * Nodes with "very huge coordinates" are relocated to location near from it. This value is arbitrary. It allows to
     * "easily" see elements with a zoom of 10%.
     */
    private static final int NODES_LIMIT_MOVE = 5000;

    private static final String NEGATIVE_X_KEY = "-x"; //$NON-NLS-1$

    private static final String POSITIVE_X_KEY = "+x"; //$NON-NLS-1$

    private static final String NEGATIVE_Y_KEY = "-y"; //$NON-NLS-1$

    private static final String POSITIVE_Y_KEY = "+y"; //$NON-NLS-1$

    /**
     * Determines a setter based on an input value and a new value.
     * 
     * @author lredor
     *
     */
    public interface Setter<F, T> {
        /**
         * Set a value of type <code>F</code> with the new value <code>T</code>.
         * 
         * @param input
         *            The object to modify
         * @param newValue
         *            The new value to set one property of <code>input</code>
         */
        void set(F input, T newValue);
    }

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            initializeSnapBackPositionMap();

            boolean isModified = false;
            StringBuilder sb = new StringBuilder(Messages.SnapBackDistantLabelsMigrationParticipant_title);
            List<Node> distantEdgeLabels = new ArrayList<Node>();

            for (DView dView : dAnalysis.getOwnedViews()) {
                List<DRepresentationDescriptor> loadedRepresentationsDescriptors = new DViewQuery(dView).getLoadedRepresentationsDescriptors();

                for (DRepresentationDescriptor descriptor : loadedRepresentationsDescriptors) {
                    DRepresentation rep = descriptor.getRepresentation();
                    if (rep instanceof DDiagram) {
                        // Search distant edge labels
                        DDiagram dDiagram = (DDiagram) rep;
                        List<Node> currentDistantEdgeLabels = getDistantEdgeLabels(dDiagram);
                        distantEdgeLabels.addAll(currentDistantEdgeLabels);

                        if (currentDistantEdgeLabels.size() > 0) {
                            isModified = true;
                            // we use the descriptor to get the name because the cross referencer is not loaded during
                            // migration so we cannot retrieve it from representation.
                            String name = descriptor.getName();
                            // Add information to be logged
                            if (currentDistantEdgeLabels.size() > 1) {
                                sb.append(MessageFormat.format(Messages.SnapBackDistantLabelsMigrationParticipant_severalLabelsSnapBacked, currentDistantEdgeLabels.size(), name));
                            } else {
                                sb.append(MessageFormat.format(Messages.SnapBackDistantLabelsMigrationParticipant_oneLabelSnapBacked, name));
                            }

                            // Search and fix nodes with huge coordinates.
                            boolean nodesMoved = false;
                            Map<String, Map<Node, Integer>> currentnodesWithExtremeLocation = getNodesWithExtremeLocation(dDiagram);
                            for (String key : currentnodesWithExtremeLocation.keySet()) {
                                if (NEGATIVE_X_KEY.equals(key)) {
                                    nodesMoved = moveNodesX(currentnodesWithExtremeLocation.get(key), false);
                                } else if (POSITIVE_X_KEY.equals(key)) {
                                    nodesMoved = moveNodesX(currentnodesWithExtremeLocation.get(key), true);
                                } else if (NEGATIVE_Y_KEY.equals(key)) {
                                    nodesMoved = moveNodesY(currentnodesWithExtremeLocation.get(key), false);
                                } else {
                                    nodesMoved = moveNodesY(currentnodesWithExtremeLocation.get(key), true);
                                }
                            }

                            if (nodesMoved) {
                                // Add information to be logged
                                sb.append(Messages.SnapBackDistantLabelsMigrationParticipant_nodesMoved);
                            }
                        }
                    }
                }
            }

            // Fix distant edge labels
            for (Node distantEdgeLabel : distantEdgeLabels) {
                Point offset = LabelEditPart.getSnapBackPosition(distantEdgeLabel.getType());
                if (offset != null) {
                    ViewUtil.setStructuralFeatureValue(distantEdgeLabel, NotationPackage.eINSTANCE.getLocation_X(), Integer.valueOf(offset.x));
                    ViewUtil.setStructuralFeatureValue(distantEdgeLabel, NotationPackage.eINSTANCE.getLocation_Y(), Integer.valueOf(offset.y));
                }
            }

            if (isModified) {
                DiagramPlugin.getDefault().logInfo(sb.toString());
            }
        }
    }

    /**
     * Move all nodes to a more readable location.
     * 
     * @param nodesWithExtremeLocation
     *            List of couple, node with extreme location and corresponding huge coordinate.
     * @param isPositive
     *            true if the above list of nodes concerns nodes with positive X, false if it concerns negative X
     * @return true if at least one node has been moved, false otherwise
     */
    protected boolean moveNodesX(Map<Node, Integer> nodesWithExtremeLocation, boolean isPositive) {
        return moveNodes(nodesWithExtremeLocation, isPositive, (input, newValue) -> input.setX(newValue));
    }

    /**
     * Move all nodes to a more readable location.
     * 
     * @param nodesWithExtremeLocation
     *            List of couple, node with extreme location and corresponding huge coordinate.
     * @param isPositive
     *            true if the above list of nodes concerns nodes with positive X, false if it concerns negative X
     * @return true if at least one node has been moved, false otherwise
     */
    protected boolean moveNodesY(Map<Node, Integer> nodesWithExtremeLocation, boolean isPositive) {
        return moveNodes(nodesWithExtremeLocation, isPositive, (input, newValue) -> input.setY(newValue));
    }

    /**
     * Move all nodes to a more readable location.
     * 
     * @param nodesWithExtremeLocation
     *            List of couple, node with extreme location and corresponding location.
     * @param isPositive
     *            true if the above list of nodes concerns nodes with positive X or Y, false if it concerns negative X
     *            or Y
     * @param setter
     *            A setter to set new X or Y coordinate
     * @return true if at least one node has been moved, false otherwise
     */
    protected boolean moveNodes(Map<Node, Integer> nodesWithExtremeLocation, boolean isPositive, Setter<Location, Integer> setter) {
        boolean nodesMoved = false;
        int min = -NODES_LIMIT;
        int max = Integer.MIN_VALUE;
        if (isPositive) {
            min = Integer.MAX_VALUE;
            max = NODES_LIMIT;
        }
        // Get min and max values
        for (Integer coordinate : nodesWithExtremeLocation.values()) {
            nodesMoved = true;
            if (coordinate < min) {
                min = coordinate;
            }
            if (coordinate > max) {
                max = coordinate;
            }
        }
        // Get delta between min and max
        int delta = max - min;
        if (delta < NODES_LIMIT) {
            // Moves all nodes with the same delta
            for (Entry<Node, Integer> entry : nodesWithExtremeLocation.entrySet()) {
                if (isPositive) {
                    setter.set((Location) entry.getKey().getLayoutConstraint(), entry.getValue() - max - NODES_LIMIT_MOVE);
                } else {
                    setter.set((Location) entry.getKey().getLayoutConstraint(), entry.getValue() - min + NODES_LIMIT_MOVE);
                }
            }
        } else {
            // Move all nodes to the same location (-NODES_LIMIT)
            for (Entry<Node, Integer> entry : nodesWithExtremeLocation.entrySet()) {
                if (isPositive) {
                    setter.set((Location) entry.getKey().getLayoutConstraint(), -NODES_LIMIT_MOVE);
                } else {
                    setter.set((Location) entry.getKey().getLayoutConstraint(), NODES_LIMIT_MOVE);
                }
            }
        }
        return nodesMoved;
    }

    /**
     * Create dummy edge name edit part to initialize the registerSnapBackPosition() method of each kind of edit part.
     * Without this initialization, when using {@link LabelEditPart#getSnapBackPosition(String)}, the returned value is
     * null.
     */
    private void initializeSnapBackPositionMap() {
        new DEdgeNameEditPart(null);
        new DEdgeBeginNameEditPart(null);
        new DEdgeEndNameEditPart(null);
    }

    /**
     * Get all labels considered as distant for this <code>dDiagram</code>.
     * 
     * @param dDiagram
     *            The diagram in which to search.
     * @return a list of labels
     */
    private List<Node> getDistantEdgeLabels(DDiagram dDiagram) {
        List<Node> distantEdgeLabels = new ArrayList<Node>();
        for (Edge edge : getEdges(dDiagram)) {
            for (Object child : edge.getChildren()) {
                if (child instanceof Node && new ViewQuery((Node) child).isForEdgeNameEditPart()) {
                    if (isLabelDistant(edge, (Node) child)) {
                        distantEdgeLabels.add((Node) child);
                    }
                }
            }
        }
        return distantEdgeLabels;
    }

    /**
     * Get all nodes with huge coordinates for this <code>dDiagram</code>. The nodes are grouped by nodes with negative
     * X, positive X, negative Y and positive Y (using key {@link #NEGATIVE_X_KEY}, {@link #POSITIVE_X_KEY},
     * {@link #NEGATIVE_Y_KEY}, {@link #POSITIVE_Y_KEY}).
     * 
     * @param dDiagram
     *            The diagram in which to search.
     * @return a list of nodes with the "huge coordinate" group by type.
     */
    private Map<String, Map<Node, Integer>> getNodesWithExtremeLocation(DDiagram dDiagram) {
        Map<Node, Integer> nodesWithExtremeNegativeXLocation = new HashMap<Node, Integer>();
        Map<Node, Integer> nodesWithExtremePositiveXLocation = new HashMap<Node, Integer>();
        Map<Node, Integer> nodesWithExtremeNegativeYLocation = new HashMap<Node, Integer>();
        Map<Node, Integer> nodesWithExtremePositiveYLocation = new HashMap<Node, Integer>();
        for (Node node : getNodes(dDiagram)) {
            if (node.getLayoutConstraint() instanceof Location) {
                Location location = (Location) node.getLayoutConstraint();
                Point point = new Point(location.getX(), location.getY());
                // Do not deal with label between -lowerLimit and lowerLimit.
                if (Math.abs(location.getX()) > NODES_LIMIT) {
                    if (location.getX() > 0) {
                        nodesWithExtremePositiveXLocation.put(node, point.x());
                    } else {
                        nodesWithExtremeNegativeXLocation.put(node, point.x());
                    }
                }
                if (Math.abs(location.getY()) > NODES_LIMIT) {
                    if (location.getY() > 0) {
                        nodesWithExtremePositiveYLocation.put(node, point.y());
                    } else {
                        nodesWithExtremeNegativeYLocation.put(node, point.y());
                    }
                }
            }
        }
        Map<String, Map<Node, Integer>> result = new HashMap<String, Map<Node, Integer>>();
        result.put(NEGATIVE_X_KEY, nodesWithExtremeNegativeXLocation);
        result.put(POSITIVE_X_KEY, nodesWithExtremePositiveXLocation);
        result.put(NEGATIVE_Y_KEY, nodesWithExtremeNegativeYLocation);
        result.put(POSITIVE_Y_KEY, nodesWithExtremePositiveYLocation);
        return result;
    }

    /**
     * Check is the label is considered as distant from its edge:
     * <UL>
     * <LI>If the label is less than 250 pixels away from the reference point on its corresponding edge, the label is
     * not considered as distant.</LI>
     * <LI>If the label is more than 1000 pixels away from the reference point on its corresponding edge, the label is
     * considered as distant.</LI>
     * <LI>Between these 2 limits, the label is considered as distant if the distance between the center of label and
     * edge is highest that "4*the size of the nearest segment".</LI>
     * </UL>
     * 
     * @param edgeLabel
     *            The label to check
     * @return true if the label is considered as distant from its edge, false otherwise.
     */
    private boolean isLabelDistant(Edge edge, Node edgeLabel) {
        boolean isLabelDistant = false;
        if (edgeLabel.getLayoutConstraint() instanceof Location) {
            Location location = (Location) edgeLabel.getLayoutConstraint();
            // Do not deal with label between -lowerLimit and lowerLimit.
            if (!(Math.abs(location.getX()) <= LABEL_LOWER_LIMIT && Math.abs(location.getY()) <= LABEL_LOWER_LIMIT)) {
                isLabelDistant = Math.abs(location.getX()) > LABEL_UPPER_LIMIT || Math.abs(location.getY()) > LABEL_UPPER_LIMIT;
                if (!isLabelDistant) {
                    try {
                        // Try to be more precise that just using location of
                        // label: Check if the distance between center of label
                        // and edge is highest that "4*the size of the nearest
                        // segment".
                        List<Point> result = new ArrayList<>();
                        PointList ptList = new PointList();
                        Anchor anchor = edge.getSourceAnchor();
                        // We only handle case with node as source and identity
                        // anchor.
                        if (anchor instanceof IdentityAnchor && edge.getSource() instanceof Node) {
                            PrecisionPoint relativeReference = BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) anchor).getId());
                            Rectangle sourceBounds = new NodeQuery((Node) edge.getSource()).getHandleBounds();
                            PrecisionPoint srcAnchorLoc = new PrecisionPoint(relativeReference.preciseX() * sourceBounds.width() + sourceBounds.x(),
                                    relativeReference.preciseY() * sourceBounds.height() + sourceBounds.y());
                            RelativeBendpoints bp = (RelativeBendpoints) edge.getBendpoints();
                            for (int i = 0; i < bp.getPoints().size(); i++) {
                                RelativeBendpoint rbp = (RelativeBendpoint) bp.getPoints().get(i);
                                Point fromSrc = srcAnchorLoc.getTranslated(rbp.getSourceX(), rbp.getSourceY());
                                result.add(fromSrc);
                                ptList.addPoint(fromSrc);
                            }
                            Point refPoint = PointListUtilities.calculatePointRelativeToLine(ptList, 0, getLocationOfReference(edgeLabel), true);
                            Point labelCenter = org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelQuery.relativeCenterCoordinateFromOffset(ptList, refPoint,
                                    new Point(location.getX(), location.getY()));

                            @SuppressWarnings("rawtypes")
                            List segments = PointListUtilities.getLineSegments(ptList);
                            LineSeg nearestSegment = PointListUtilities.getNearestSegment(segments, labelCenter.x, labelCenter.y);
                            double distance = nearestSegment.distanceToPoint(labelCenter.x(), labelCenter.y());
                            isLabelDistant = distance > nearestSegment.length() * 4;
                        }
                        // CHECKSTYLE:OFF
                    } catch (Exception e) {
                        // Do nothing, just avoid some specific cases not
                        // handled above.
                    }
                    // CHECKSTYLE:ON
                }
            }
        }
        return isLabelDistant;
    }

    private int getLocationOfReference(Node edgeLabel) {
        int locationOfRef = LabelViewConstants.MIDDLE_LOCATION;
        int type = SiriusVisualIDRegistry.getVisualID(edgeLabel.getType());
        if (DEdgeBeginNameEditPart.VISUAL_ID == type) {
            locationOfRef = LabelViewConstants.SOURCE_LOCATION;
        } else if (DEdgeEndNameEditPart.VISUAL_ID == type) {
            locationOfRef = LabelViewConstants.TARGET_LOCATION;
        }
        return locationOfRef;
    }

    /**
     * Get the {@link Edge}s with labels contained in the given {@link DDiagram} .
     * 
     * @param dDiagram
     *            the given {@link DDiagram}.
     * @return the {@link Edge}s with labels contained in the given {@link DDiagram}.
     */
    private Collection<Edge> getEdges(DDiagram dDiagram) {
        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
        Option<Diagram> gmfDiagram = query.getAssociatedGMFDiagram();
        if (gmfDiagram.some()) {
            return Lists.newArrayList(Iterables.filter(gmfDiagram.get().getEdges(), Edge.class));
        }
        return new ArrayList<>();
    }

    /**
     * Get the {@link Edge}s with labels contained in the given {@link DDiagram} .
     * 
     * @param dDiagram
     *            the given {@link DDiagram}.
     * @return the {@link Edge}s with labels contained in the given {@link DDiagram}.
     */
    private Collection<Node> getNodes(DDiagram dDiagram) {
        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
        Option<Diagram> gmfDiagram = query.getAssociatedGMFDiagram();
        if (gmfDiagram.some()) {
            return Lists.newArrayList(Iterables.filter(gmfDiagram.get().getChildren(), Node.class));
        }
        return new ArrayList<>();
    }
}
