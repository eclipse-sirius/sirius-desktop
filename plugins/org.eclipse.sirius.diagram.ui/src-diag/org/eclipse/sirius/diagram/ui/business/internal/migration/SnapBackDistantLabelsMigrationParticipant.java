/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A representation file migration to detect labels that are far from their edge
 * and reset them to their default location.
 * 
 * @author lredor
 */
public class SnapBackDistantLabelsMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The Sirius version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("10.1.9.201706191500"); //$NON-NLS-1$

    /**
     * The absolute maximum x coordinate from which label is considered distant.
     */
    private static final int X_UPPER_LIMIT = 1000;

    /**
     * The absolute maximum y coordinate from which label is considered distant.
     */
    private static final int Y_UPPER_LIMIT = 1000;

    /**
     * The absolute minimal x coordinate from which label is not considered
     * distant.
     */
    private static final int X_LOWER_LIMIT = 250;

    /**
     * The absolute minimal y coordinate from which label is not considered
     * distant.
     */
    private static final int Y_LOWER_LIMIT = 250;

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
                for (DDiagram dDiagram : Iterables.filter(dView.getOwnedRepresentations(), DDiagram.class)) {
                    List<Node> currentDistantEdgeLabels = getDistantEdgeLabels(dDiagram);
                    distantEdgeLabels.addAll(currentDistantEdgeLabels);
                    if (currentDistantEdgeLabels.size() > 0) {
                        isModified = true;
                        if (currentDistantEdgeLabels.size() > 1) {
                            sb.append(MessageFormat.format(Messages.SnapBackDistantLabelsMigrationParticipant_severalLabelsSnapBacked, currentDistantEdgeLabels.size(), dDiagram.getName()));
                        } else {
                            sb.append(MessageFormat.format(Messages.SnapBackDistantLabelsMigrationParticipant_oneLabelSnapBacked, dDiagram.getName()));
                        }
                    }
                }
            }

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
     * Create dummy edge name edit part to initialize the
     * registerSnapBackPosition() method of each kind of edit part. Without this
     * initialization, when using
     * {@link LabelEditPart#getSnapBackPosition(String)}, the returned value is
     * null.
     */
    private void initializeSnapBackPositionMap() {
        new DEdgeNameEditPart(null);
        new DEdgeBeginNameEditPart(null);
        new DEdgeEndNameEditPart(null);
    }

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
     * Check is the label is considered as distant from its edge:
     * <UL>
     * <LI>If the label is less than 250 pixels away from the reference point on
     * its corresponding edge, the label is not considered as distant.</LI>
     * <LI>If the label is more than 1000 pixels away from the reference point
     * on its corresponding edge, the label is considered as distant.</LI>
     * <LI>Between these 2 limits, the label is considered as distant if the
     * distance between the center of label and edge is highest that
     * "4*the size of the nearest segment".</LI>
     * </UL>
     * 
     * @param edgeLabel
     *            The label to check
     * @return true if the label is considered as distant from its edge, false
     *         otherwise.
     */
    private boolean isLabelDistant(Edge edge, Node edgeLabel) {
        boolean isLabelDistant = false;
        if (edgeLabel.getLayoutConstraint() instanceof Location) {
            Location location = (Location) edgeLabel.getLayoutConstraint();
            // Do not deal with label between -lowerLimit and lowerLimit.
            if (!(Math.abs(location.getX()) <= X_LOWER_LIMIT && Math.abs(location.getY()) <= Y_LOWER_LIMIT)) {
                isLabelDistant = Math.abs(location.getX()) > X_UPPER_LIMIT || Math.abs(location.getY()) > Y_UPPER_LIMIT;
                if (!isLabelDistant) {
                    try {
                        // Try to be more precise that just using location of
                        // label: Check if the distance between center of label
                        // and edge is highest that "4*the size of the nearest
                        // segment".
                        List<Point> result = Lists.newArrayList();
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

    @SuppressWarnings("restriction")
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
     * Get the {@link Edge}s with labels contained in the given {@link DDiagram}
     * .
     * 
     * @param dDiagram
     *            the given {@link DDiagram}.
     * @return the {@link Edge}s with labels contained in the given
     *         {@link DDiagram}.
     */
    private Collection<Edge> getEdges(DDiagram dDiagram) {
        DDiagramGraphicalQuery query = new DDiagramGraphicalQuery(dDiagram);
        Option<Diagram> gmfDiagram = query.getAssociatedGMFDiagram();
        if (gmfDiagram.some()) {
            return Lists.newArrayList(Iterables.filter(gmfDiagram.get().getEdges(), Edge.class));
        }
        return new ArrayList<Edge>();
    }
}
