/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation and others - for getAnchorRelativeLocation copied from
 *                 org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.refresh;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.EdgeLayoutData;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.edge.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;

/**
 * A Command to update sequence message bendpoints after the
 * CanonicalSynchronizer.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class FixBendpointsOnCreationCommand extends RecordingCommand {

    private Edge newEdge;

    private View source;

    private View target;

    // Anchors
    private String sourceTerminal;

    private String targetTerminal;

    // Bendpoints
    private PointList pointList;

    // Routing
    private Routing routing;

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} on which execute this
     *            Command
     * 
     * @param createdEdge
     *            the {@link Edge} for which update the bendpoints
     */
    public FixBendpointsOnCreationCommand(TransactionalEditingDomain domain, Edge createdEdge) {
        super(domain);
        this.newEdge = createdEdge;
    }

    /**
     * Overridden to update the bendpoints of the specified {@link Edge}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {

        this.source = newEdge.getSource();
        this.target = newEdge.getTarget();

        if (source != null && target != null) {

            EObject element = newEdge.getElement();
            if (element instanceof DEdge) {
                DEdge dEdge = (DEdge) element;
                updateEdge(dEdge);
            }
        }
    }

    private void updateEdge(DEdge dEdge) {

        // Bendpoints
        pointList = new PointList();
        Point sourceRefPoint = new Point(0, 0);
        Point targetRefPoint = new Point(0, 0);

        EdgeLayoutData egdeLayoutData = null;
        SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(true);
        try {
            egdeLayoutData = SiriusLayoutDataManager.INSTANCE.getData(dEdge, false);
        } finally {
            SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(false);
        }
        if (egdeLayoutData != null) {

            sourceTerminal = egdeLayoutData.getSourceTerminal();
            targetTerminal = egdeLayoutData.getTargetTerminal();

            // pointList, sourceRefPoint, targetRefPoint of
            // the edgeLayoutData can be null if the edge is
            // hide when the layout data is stored
            if (egdeLayoutData.getPointList() != null) {
                pointList = egdeLayoutData.getPointList();
            }
            if (egdeLayoutData.getSourceRefPoint() != null) {
                sourceRefPoint = egdeLayoutData.getSourceRefPoint();
            }
            if (egdeLayoutData.getTargetRefPoint() != null) {
                targetRefPoint = egdeLayoutData.getTargetRefPoint();
            }

            routing = egdeLayoutData.getRouting();

        } else {
            computeEdgeDataWithoutEdgeLayoutData();
        }

        // Set connection anchors :
        updateConnectionAnchors();

        // Set Bendpoints :
        updateBendpoints(sourceRefPoint, targetRefPoint);
    }

    private void computeEdgeDataWithoutEdgeLayoutData() {
        Point firstClick = new Point(0, 0);
        Rectangle sourceBounds = GMFHelper.getAbsoluteBounds((Node) source);
        if (source.getElement() instanceof AbstractDNode) {
            AbstractDNode sourceDNode = (AbstractDNode) source.getElement();
            if (sourceDNode.eContainer() instanceof AbstractDNode) {
                AbstractDNode parentDNode = (AbstractDNode) sourceDNode.eContainer();
                if (parentDNode.getOwnedBorderedNodes().contains(sourceDNode)) {
                    sourceBounds.y += IBorderItemOffsets.DEFAULT_OFFSET.height;
                    sourceBounds.height += 5;
                }
            }
            LayoutData sourceLayoutData = null;
            SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(true);
            try {
                sourceLayoutData = SiriusLayoutDataManager.INSTANCE.getData(sourceDNode);
            } finally {
                SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(false);
            }
            if (sourceLayoutData == null) {
                firstClick = sourceBounds.getCenter();
            } else {
                Point sourceLocation = sourceLayoutData.getLocation();
                firstClick = sourceLocation;
            }
        }

        PrecisionPoint sourceRelativeLocation = getAnchorRelativeLocation(firstClick, sourceBounds);
        sourceTerminal = "(" + sourceRelativeLocation.preciseX() + "," + sourceRelativeLocation.preciseY() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        Point secondClick = new Point(0, 0);
        Rectangle targetBounds = GMFHelper.getAbsoluteBounds((Node) target);

        if (target.getElement() instanceof AbstractDNode) {
            AbstractDNode targetDNode = (AbstractDNode) target.getElement();
            if (targetDNode.eContainer() instanceof AbstractDNode) {
                AbstractDNode parentDNode = (AbstractDNode) targetDNode.eContainer();
                if (parentDNode.getOwnedBorderedNodes().contains(targetDNode)) {
                    targetBounds.y += IBorderItemOffsets.DEFAULT_OFFSET.height;
                    targetBounds.height += 5;
                }
            }
            LayoutData targetLayoutData = null;
            SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(true);
            try {
                targetLayoutData = SiriusLayoutDataManager.INSTANCE.getData(targetDNode);
            } finally {
                SiriusLayoutDataManager.INSTANCE.setIgnoreConsumeState(false);
            }

            if (targetLayoutData == null) {
                secondClick = targetBounds.getCenter();
            } else {
                Point targetLocation = targetLayoutData.getLocation();
                secondClick = targetLocation;
            }
        }

        PrecisionPoint targetRelativeLocation = getAnchorRelativeLocation(secondClick, targetBounds);
        targetTerminal = "(" + targetRelativeLocation.preciseX() + "," + targetRelativeLocation.preciseY() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        // Computes pointList
        PrecisionPoint sourceRelativeReference = SequenceSlidableAnchor.parseTerminalString(sourceTerminal);
        SlidableAnchor sourceAnchor = new SequenceSlidableAnchor((Node) source, sourceRelativeReference);
        pointList.addPoint(sourceAnchor.getLocation(sourceAnchor.getReferencePoint()));

        PrecisionPoint targetRelativeReference = SequenceSlidableAnchor.parseTerminalString(targetTerminal);
        SlidableAnchor targetAnchor = new SequenceSlidableAnchor((Node) target, targetRelativeReference);
        pointList.addPoint(targetAnchor.getLocation(targetAnchor.getReferencePoint()));
    }

    /*
     * Copied from org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor.
     * getAnchorRelativeLocation() to avoid a dependency towards just for this.
     */
    private PrecisionPoint getAnchorRelativeLocation(Point p, Rectangle bounds) {
        if (bounds.width == 0 || bounds.height == 0) {
            /*
             * If figure hasn't been laid out yet, we don't want to fail the
             * slidable anchor creation. Hence, we'll just return the (0.5, 0.5)
             * meaning that the anchor reference point is the center of the
             * figure.
             */
            return new PrecisionPoint(0.5, 0.5);
        }
        PrecisionPoint relLocation;
        PrecisionPoint temp = new PrecisionPoint(p);
        if (p.x < bounds.x || p.x > bounds.x + bounds.width || p.y < bounds.y || p.y > bounds.y + bounds.height) {
            if (p.x < bounds.x || p.x > bounds.x + bounds.width) {
                temp.preciseX = p.x < bounds.x ? bounds.x : bounds.x + bounds.width;
            }
            if (p.y < bounds.y || p.y > bounds.y + bounds.height) {
                temp.preciseY = p.y < bounds.y ? bounds.y : bounds.y + bounds.height;
            }
            relLocation = new PrecisionPoint((temp.preciseX - bounds.x) / bounds.width, (temp.preciseY - bounds.y) / bounds.height);
        } else {

            relLocation = new PrecisionPoint((temp.preciseX - bounds.x) / bounds.width, (temp.preciseY - bounds.y) / bounds.height);
        }
        return relLocation;
    }

    private void updateConnectionAnchors() {
        if (sourceTerminal != null) {
            if (sourceTerminal.length() == 0)
                newEdge.setSourceAnchor(null);
            else {
                IdentityAnchor a = (IdentityAnchor) newEdge.getSourceAnchor();
                if (a == null)
                    a = NotationFactory.eINSTANCE.createIdentityAnchor();
                a.setId(sourceTerminal);
                newEdge.setSourceAnchor(a);
            }
        }
        if (targetTerminal != null) {
            if (targetTerminal.length() == 0)
                newEdge.setTargetAnchor(null);
            else {
                IdentityAnchor a = (IdentityAnchor) newEdge.getTargetAnchor();
                if (a == null)
                    a = NotationFactory.eINSTANCE.createIdentityAnchor();
                a.setId(targetTerminal);
                newEdge.setTargetAnchor(a);
            }

        }
    }

    private void updateBendpoints(Point sourceRefPoint, Point targetRefPoint) {
        List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();
        int numOfPoints = pointList.size();
        for (short i = 0; i < numOfPoints; i++) {
            Dimension s = pointList.getPoint(i).getDifference(sourceRefPoint);
            Dimension t = pointList.getPoint(i).getDifference(targetRefPoint);
            newBendpoints.add(new RelativeBendpoint(s.width, s.height, t.width, t.height));
        }

        RelativeBendpoints points = (RelativeBendpoints) newEdge.getBendpoints();
        points.setPoints(newBendpoints);

        // Routing
        if (routing != null) {
            Style routingStyle = newEdge.getStyle(NotationPackage.Literals.ROUTING_STYLE);
            routingStyle.eSet(NotationPackage.Literals.ROUTING_STYLE__ROUTING, routing);
        }
    }
}
