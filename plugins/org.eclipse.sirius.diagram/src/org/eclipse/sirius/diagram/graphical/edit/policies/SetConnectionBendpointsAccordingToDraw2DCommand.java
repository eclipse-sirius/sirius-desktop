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
package org.eclipse.sirius.diagram.graphical.edit.policies;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;

import org.eclipse.sirius.diagram.ui.tools.api.layout.GraphicalHelper;

// CHECKSTYLE:OFF
/**
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SetConnectionBendpointsAccordingToDraw2DCommand extends SetConnectionBendpointsCommand {

    private boolean sourceMove;

    private Point moveDelta;

    /**
     * @param editingDomain
     */
    public SetConnectionBendpointsAccordingToDraw2DCommand(TransactionalEditingDomain editingDomain) {
        super(editingDomain);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecute(org.eclipse.core.runtime.IProgressMonitor,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if (getEdgeAdaptor() instanceof ConnectionEditPart) {
            ConnectionEditPart connectionEditPart = (ConnectionEditPart) getEdgeAdaptor();
            // Applied zoom on moveDelta, because moveDelta is only element in
            // relative value
            GraphicalHelper.appliedZoomOnRelativePoint(connectionEditPart, moveDelta);
            Connection connection = connectionEditPart.getConnectionFigure();

            Point tempSourceRefPoint = connection.getSourceAnchor().getReferencePoint();
            connection.translateToRelative(tempSourceRefPoint);

            Point tempTargetRefPoint = connection.getTargetAnchor().getReferencePoint();
            connection.translateToRelative(tempTargetRefPoint);

            PointList connectionPointList = connection.getPoints().getCopy();
            if (sourceMove) {
                tempSourceRefPoint.performTranslate(moveDelta.x, moveDelta.y);

                // Get the bounds of the source before and after move (to apply
                // specific move on the first point of the edge)
                IFigure sourceAnchorOnwer = connection.getSourceAnchor().getOwner();
                Rectangle rBox = (sourceAnchorOnwer instanceof Connection) ? ((Connection) sourceAnchorOnwer).getPoints().getBounds() : sourceAnchorOnwer.getBounds();
                PrecisionRectangle boxBeforeMove = new PrecisionRectangle(rBox);
                sourceAnchorOnwer.translateToAbsolute(boxBeforeMove);
                PrecisionRectangle boxAfterMove = new PrecisionRectangle(boxBeforeMove.getCopy());
                boxAfterMove.translate(moveDelta);
                boolean changeXDelta = boxBeforeMove.x != boxAfterMove.x;

                if (connectionPointList.getPoint(1).y < boxAfterMove.getCenter().y) {
                    if (connectionPointList.getPoint(1).y < boxBeforeMove.getCenter().y) {
                        // The source is below the horizontal trunk segment
                        // before and after the move
                        connectionPointList.setPoint(connectionPointList.getFirstPoint().translate(moveDelta), 0);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(1).translate(moveDelta.x, 0), 1);
                        }
                    } else {
                        // The source is above the horizontal trunk segment
                        // before the move and below after the move
                        connectionPointList.setPoint(connectionPointList.getFirstPoint().translate(moveDelta.x, moveDelta.y - boxBeforeMove.height), 0);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(1).translate(moveDelta.x, 0), 1);
                        }
                    }
                } else {
                    if (connectionPointList.getPoint(1).y < boxBeforeMove.getCenter().y) {
                        // The source is below the horizontal trunk segment
                        // before and above after the move
                        connectionPointList.setPoint(connectionPointList.getFirstPoint().translate(moveDelta.x, moveDelta.y + boxBeforeMove.height), 0);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(1).translate(moveDelta.x, 0), 1);
                        }
                    } else {
                        // The source is above the horizontal trunk segment
                        // before and after the move
                        connectionPointList.setPoint(connectionPointList.getFirstPoint().translate(moveDelta.x, moveDelta.y), 0);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(1).translate(moveDelta.x, 0), 1);
                        }
                    }
                }
            } else {
                tempTargetRefPoint.performTranslate(moveDelta.x, moveDelta.y);

                // Get the bounds of the target before and after move (to apply
                // specific move on the last point of the edge)
                IFigure targetAnchorOnwer = connection.getTargetAnchor().getOwner();
                Rectangle rBox = (targetAnchorOnwer instanceof Connection) ? ((Connection) targetAnchorOnwer).getPoints().getBounds() : targetAnchorOnwer.getBounds();
                PrecisionRectangle boxBeforeMove = new PrecisionRectangle(rBox.getCopy());
                targetAnchorOnwer.translateToAbsolute(boxBeforeMove.getCopy());
                PrecisionRectangle boxAfterMove = new PrecisionRectangle(boxBeforeMove.getCopy());
                boxAfterMove.translate(moveDelta);
                boolean changeXDelta = boxBeforeMove.x != boxAfterMove.x;
                if (connectionPointList.getPoint(1).y < boxAfterMove.getCenter().y) {
                    if (connectionPointList.getPoint(1).y < boxBeforeMove.getCenter().y) {
                        // The source is below the horizontal trunk segment
                        // before and after the move
                        connectionPointList.setPoint(connectionPointList.getLastPoint().translate(moveDelta), 3);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(2).translate(moveDelta.x, 0), 2);
                        }
                    } else {
                        // The source is above the horizontal trunk segment
                        // before the move and below after the move
                        connectionPointList.setPoint(connectionPointList.getLastPoint().translate(moveDelta.x, moveDelta.y - boxBeforeMove.height), 3);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(2).translate(moveDelta.x, 0), 2);
                        }
                    }
                } else {
                    if (connectionPointList.getPoint(1).y < boxBeforeMove.getCenter().y) {
                        // The source is below the horizontal trunk segment
                        // before and above after the move
                        connectionPointList.setPoint(connectionPointList.getLastPoint().translate(moveDelta.x, moveDelta.y + boxBeforeMove.height), 3);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(2).translate(moveDelta.x, 0), 2);
                        }
                    } else {
                        // The source is above the horizontal trunk segment
                        // before and after the move
                        connectionPointList.setPoint(connectionPointList.getLastPoint().translate(moveDelta.x, moveDelta.y), 3);
                        if (changeXDelta) {
                            connectionPointList.setPoint(connectionPointList.getPoint(2).translate(moveDelta.x, 0), 2);
                        }
                    }
                }
            }

            setNewPointList(connectionPointList, tempSourceRefPoint, tempTargetRefPoint);
            return super.doExecute(monitor, info);
        }
        return Status.OK_STATUS;
    }

    /**
     * @param b
     */
    public void setSourceMove(boolean sourceMove) {
        this.sourceMove = sourceMove;

    }

    /**
     * @param moveDelta
     */
    public void setMoveDelta(Point moveDelta) {
        this.moveDelta = moveDelta;

    }
    // CHECKSTYLE:OFF
}
