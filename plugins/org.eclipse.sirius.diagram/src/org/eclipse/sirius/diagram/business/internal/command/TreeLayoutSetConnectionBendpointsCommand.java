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
package org.eclipse.sirius.diagram.business.internal.command;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.diagram.tools.internal.util.GMFNotationUtilities;

/**
 * A specific SetConnectionBendpointsCommand to change all GMF edges of this
 * tree (and not only the moved edge). See VP-3065.<BR>
 * <BR>
 * Name convention used in javadoc of this class: <BR>
 * 
 * <pre>
 *       ^                        
 *       |                        
 *       |   target segment       
 *       |                        
 * ______|   horizontal segment   
 * |                                           
 * |                                           
 * |         source segment       
 * |
 * </pre>
 * 
 * <BR>
 * This class changes the bendpoints (and the anchor if needed) when moving :
 * <UL>
 * <LI>The source segment: horizontal movement</LI>
 * <LI>The end point of the source segment (that is also the origin point of the
 * horizontal segment): horizontal or/and vertical movement</LI>
 * <LI>The horizontal segment: vertical movement</LI>
 * <LI>The origin point of the target segment (that is also the end point of the
 * horizontal segment): horizontal or/and vertical movement</LI>
 * <LI>The target segment: horizontal movement</LI>
 * </UL>
 * <BR>
 * The horizontal movement of source segment and end point of the source segment
 * does not work. <BR>
 * When one of this element is moved :
 * <UL>
 * <LI>The source or the target anchor of the moved edge is changed if needed,</LI>
 * <LI>The bendpoints of the moved edge is changed,</LI>
 * <LI>The brother edges of the moved edge are adapted according to the new edge
 * anchors and bendpoints.</LI>
 * </UL>
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("all")
public class TreeLayoutSetConnectionBendpointsCommand extends SetConnectionBendpointsCommand {

    /**
     * Default constructor.
     * 
     * @param editingDomain
     *            the editing domain through which model changes are made.
     */
    public TreeLayoutSetConnectionBendpointsCommand(TransactionalEditingDomain editingDomain) {
        super(editingDomain);
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

        Assert.isNotNull(getNewPointList());
        Assert.isNotNull(getSourceRefPoint());
        Assert.isNotNull(getTargetRefPoint());

        Edge edge = (Edge) getEdgeAdaptor().getAdapter(Edge.class);
        Assert.isNotNull(edge);

        if (edge.getBendpoints() instanceof RelativeBendpoints) {
            RelativeBendpoints relativeBendpoints = (RelativeBendpoints) edge.getBendpoints();
            if (relativeBendpoints.getPoints().size() == 4) {
                // Retrieve the second point (end of the source segment)
                Point oldLocationForEndOfSourceSegment = new Point(((RelativeBendpoint) relativeBendpoints.getPoints().get(1)).getSourceX(),
                        ((RelativeBendpoint) relativeBendpoints.getPoints().get(1)).getSourceY());
                // Retrieve the third point (start of the target segment)
                Point oldLocationForStartOfTargetSegment = new Point(((RelativeBendpoint) relativeBendpoints.getPoints().get(2)).getSourceX(), ((RelativeBendpoint) relativeBendpoints.getPoints().get(
                        2)).getSourceY());

                // Compute temporary list of bendpoints to determine the delta
                List newBendpoints = new ArrayList();
                int numOfPoints = getNewPointList().size();
                for (short i = 0; i < numOfPoints; i++) {
                    Dimension s = getNewPointList().getPoint(i).getDifference(getSourceRefPoint());
                    Dimension t = getNewPointList().getPoint(i).getDifference(getTargetRefPoint());
                    newBendpoints.add(new RelativeBendpoint(s.width, s.height, t.width, t.height));
                }

                // Compute delta for y position. Delta is the difference between
                // the y last position and the y actual position
                int deltaY = ((RelativeBendpoint) newBendpoints.get(1)).getSourceY() - oldLocationForEndOfSourceSegment.y;
                // Compute delta for target x position. Delta is the difference
                // between the last x position and the actual x position.
                int deltaXTarget = oldLocationForStartOfTargetSegment.x - ((RelativeBendpoint) newBendpoints.get(2)).getSourceX();
                // Compute delta for source x position. Delta is the difference
                // between the last x position and the actual x position.
                int deltaXSource = oldLocationForEndOfSourceSegment.x - ((RelativeBendpoint) newBendpoints.get(1)).getSourceX();

                Point targetRefPoint = getTargetRefPoint();
                Point sourceRefPoint = getSourceRefPoint();
                if (deltaXTarget != 0) {
                    // Move targetAnchor according to delta
                    Option<Point> optionalTargetRefPoint = GMFNotationUtilities.setTargetAnchor(edge, deltaXTarget);
                    if (optionalTargetRefPoint.some()) {
                        targetRefPoint = optionalTargetRefPoint.get();
                    }
                } else if (deltaXSource != 0) {
                    // Move sourceAnchor according to delta
                    Option<Point> optionalSourceRefPoint = GMFNotationUtilities.setSourceAnchor(edge, deltaXTarget);
                    if (optionalSourceRefPoint.some()) {
                        sourceRefPoint = optionalSourceRefPoint.get();
                    }
                }

                GMFNotationUtilities.setGMFBendpoints(edge, getNewPointList(), sourceRefPoint, targetRefPoint);
                // Changed the source or target anchor and the bendpoints of the
                // brothers of the current edge.
                GMFNotationUtilities.setBrothersAnchorAndBendpointsAccordingToEdge(edge);
            } else {
                Point sourceRefPoint = getSourceRefPoint();
                Point targetRefPoint = getTargetRefPoint();
                Option<Point> optionalSourceRefPoint = GMFNotationUtilities.setSourceAnchor(edge, getNewPointList());
                Option<Point> optionalTargetRefPoint = GMFNotationUtilities.setTargetAnchor(edge, getNewPointList());
                if (optionalSourceRefPoint.some()) {
                    sourceRefPoint = optionalSourceRefPoint.get();
                }
                if (optionalTargetRefPoint.some()) {
                    targetRefPoint = optionalTargetRefPoint.get();
                }
                GMFNotationUtilities.setGMFBendpoints(edge, getNewPointList(), sourceRefPoint, targetRefPoint);
                // Changed the source or target anchor and the bendpoints of the
                // brothers of the current edge.
                GMFNotationUtilities.setBrothersAnchorAndBendpointsAccordingToEdge(edge);
            }
        }
        return CommandResult.newOKCommandResult();
    }
}
