/*******************************************************************************
 * Copyright (c) 2012, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.command;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.util.GMFNotationUtilities;
import org.eclipse.sirius.ext.base.Option;

/**
 * Controls the movement of source and target edge anchor. Use to move vertical
 * segment from left to right or from right to left. This command :
 * <UL>
 * <LI>Change the anchors as the overriden command,</LI>
 * <LI>Change bendpoints according to new anchor,</LI>
 * <LI>Change anchor and bendpoints of brother edges (edge in the same tree).</LI>
 * </UL>
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class TreeLayoutSetConnectionAnchorsCommand extends SetConnectionAnchorsCommand {

    Point sourceRefPoint;

    Point targetRefPoint;

    PointList connectionPointList;

    /**
     * Constructor.
     * 
     * @param editingDomain
     *            the editing domain through which model changes are made
     * @param label
     *            the command label
     */
    public TreeLayoutSetConnectionAnchorsCommand(TransactionalEditingDomain editingDomain, String label) {
        super(editingDomain, label);
    }

    /**
     * Set the new sourceRefPoint, targetRefPoint and connectionPointList
     * according to drawd2 edge.
     * 
     * @param sourceAnchorChanged
     *            true if the source anchor has been changed, false otherwise
     * @param targetAnchorChanged
     *            true if the target anchor has been changed, false otherwise
     */
    protected void setComplementaryData(boolean sourceAnchorChanged, boolean targetAnchorChanged) {
        if (getEdgeAdaptor() instanceof ConnectionEditPart) {
            Connection connection = ((ConnectionEditPart) getEdgeAdaptor()).getConnectionFigure();
            Edge edge = getEdgeAdaptor().getAdapter(Edge.class);

            if (sourceAnchorChanged || connection.getSourceAnchor() == null) {
                // Recompute the new source ref point
                // Get bounds of sourceNode
                EditPart editPart = ((ConnectionEditPart) getEdgeAdaptor()).getSource();
                Rectangle sourceBounds = null;
                if (editPart instanceof GraphicalEditPart) {
                    sourceBounds = ((GraphicalEditPart) editPart).getFigure().getBounds();
                } else {
                    // We are in reconnection (compute the sourceRefPoint using
                    // GMF model data)
                    Option<Rectangle> optionalSourceBounds = GMFHelper.getAbsoluteBounds(edge.getSource());
                    if (optionalSourceBounds.some()) {
                        sourceBounds = optionalSourceBounds.get();
                    }
                }
                if (sourceBounds != null) {
                    PrecisionPoint sourceAnchorReference = edge.getSourceAnchor() instanceof IdentityAnchor ? BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) edge.getSourceAnchor()).getId())
                            : new PrecisionPoint(0.5, 0.5);
                    sourceRefPoint = new PrecisionPoint(sourceBounds.x + sourceBounds.width * sourceAnchorReference.preciseX(), sourceBounds.y + sourceBounds.height * sourceAnchorReference.preciseY());
                }
            } else {
                // Use the source ref point of the current figure
                sourceRefPoint = connection.getSourceAnchor().getReferencePoint();
                connection.translateToRelative(sourceRefPoint);
            }
            if (targetAnchorChanged || connection.getTargetAnchor() == null) {
                // Recompute the new target ref point
                // Get bounds of targetNode
                EditPart editPart = ((ConnectionEditPart) getEdgeAdaptor()).getTarget();
                Rectangle targetBounds = null;
                if (editPart instanceof GraphicalEditPart) {
                    targetBounds = ((GraphicalEditPart) editPart).getFigure().getBounds();
                } else {
                    // We are in reconnection (compute the sourceRefPoint using
                    // GMF model data)
                    Option<Rectangle> optionalTargetBounds = GMFHelper.getAbsoluteBounds(edge.getTarget());
                    if (optionalTargetBounds.some()) {
                        targetBounds = optionalTargetBounds.get();
                    }
                }
                if (targetBounds != null) {
                    PrecisionPoint targetAnchorReference = edge.getTargetAnchor() instanceof IdentityAnchor ? BaseSlidableAnchor.parseTerminalString(((IdentityAnchor) edge.getTargetAnchor()).getId())
                            : new PrecisionPoint(0.5, 0.5);
                    targetRefPoint = new PrecisionPoint(targetBounds.x + targetBounds.width * targetAnchorReference.preciseX(), targetBounds.y + targetBounds.height * targetAnchorReference.preciseY());
                }
            } else {
                // Use the target ref point of the current figure
                targetRefPoint = connection.getTargetAnchor().getReferencePoint();
                connection.translateToRelative(targetRefPoint);
            }

            connectionPointList = connection.getPoints().getCopy();
        }
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
        assert null != getEdgeAdaptor() : Messages.TreeLayoutSetConnectionAnchorsCommand_nullChildInSetConnectionAnchorsCommand;              

        Edge edge = getEdgeAdaptor().getAdapter(Edge.class);
        assert null != edge : Messages.TreeLayoutSetConnectionAnchorsCommand_nullEdgeInSetConnectionAnchorsCommand;     

        // Retrieve old source anchor corresponding before move source point
        IdentityAnchor oldSourceAnchor = null;
        if (edge.getSourceAnchor() instanceof IdentityAnchor) {
            oldSourceAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
            oldSourceAnchor.setId(((IdentityAnchor) edge.getSourceAnchor()).getId());
        }

        // Retrieve old target anchor corresponding before move target point
        IdentityAnchor oldTargetAnchor = null;
        if (edge.getTargetAnchor() instanceof IdentityAnchor) {
            oldTargetAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
            oldTargetAnchor.setId(((IdentityAnchor) edge.getTargetAnchor()).getId());
        }

        boolean newSourceAnchor = false;
        if (getNewSourceTerminal() != null) {
            if (getNewSourceTerminal().length() == 0)
                edge.setSourceAnchor(null);
            else {
                if (oldSourceAnchor == null) {
                    // We are in reconnection mode so the new edge must take the
                    // anchor of other edges (if at least one exists) and the
                    // bendpoints must be recompute according to brother
                    IdentityAnchor sourceAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                    sourceAnchor.setId(getNewSourceTerminal());
                    edge.setSourceAnchor(sourceAnchor);
                    newSourceAnchor = true;
                } else {
                    IdentityAnchor a = (IdentityAnchor) edge.getSourceAnchor();
                    if (a == null)
                        a = NotationFactory.eINSTANCE.createIdentityAnchor();
                    // Modify edge anchor with new value
                    a.setId(getNewSourceTerminal());
                    edge.setSourceAnchor(a);
                    newSourceAnchor = true;
                }
            }
        }
        boolean newTargetAnchor = false;
        if (getNewTargetTerminal() != null) {
            if (getNewTargetTerminal().length() == 0) {
                edge.setTargetAnchor(null);
            } else {
                if (oldTargetAnchor == null) {
                    // We are in reconnection mode so the new edge must take the
                    // anchor of other edges (if at least one exists) and the
                    // bendpoints must be recompute according to brother
                    IdentityAnchor targetAnchor = NotationFactory.eINSTANCE.createIdentityAnchor();
                    targetAnchor.setId(getNewTargetTerminal());
                    edge.setTargetAnchor(targetAnchor);
                    newTargetAnchor = true;
                } else {
                    // Modify edge anchor with new value
                    IdentityAnchor targetAnchor = (IdentityAnchor) edge.getTargetAnchor();
                    targetAnchor.setId(getNewTargetTerminal());
                    newTargetAnchor = true;
                }
            }
        }
        setComplementaryData(newSourceAnchor, newTargetAnchor);
        if (newSourceAnchor || newTargetAnchor) {
            // Set the GMF bendpoints of the edge according to the source or
            // target anchor change
            GMFNotationUtilities.setGMFBendpoints(edge, connectionPointList, sourceRefPoint, targetRefPoint);
            // Change the source or target anchor and the bendpoints of the
            // brothers of edge> according to this edge
            GMFNotationUtilities.setBrothersAnchorAndBendpointsAccordingToEdge(edge);
        }

        return CommandResult.newOKCommandResult();
    }
}
