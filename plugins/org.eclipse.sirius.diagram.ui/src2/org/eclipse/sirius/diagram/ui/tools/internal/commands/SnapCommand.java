/******************************************************************************
 * Copyright (c) 2007, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Michael Golubev (Borland) - Fix for Bug 261192
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

// Copied from org.eclipse.gmf.runtime.diagram.ui.internal.commands.SnapCommand
//CHECKSTYLE:OFF
/**
 * This command is used to snap edit parts on a diagram, where the edit parts
 * are passed in as the parameter.
 * 
 * Duplicate class for
 * {@link org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusContainerEditPolicy}
 * needs.
 * 
 * @author carson_li
 */
public class SnapCommand extends AbstractTransactionalCommand {

    /** the edit parts requested to be snapped to grid */
    protected List<? extends IGraphicalEditPart> editparts;

    /**
     * Constructor for <code>SnapCommand</code>.
     * 
     * @param editingDomain
     *            the editing domain through which model changes are made
     * @param editparts
     *            the list containing the edit parts that need to be snapped
     */
    public SnapCommand(TransactionalEditingDomain editingDomain, List<? extends IGraphicalEditPart> editparts) {

        super(editingDomain, DiagramUIMessages.SnapToGrid_textLabel, null);
        this.editparts = editparts;

    }

    /*
     * @see org.eclipse.gmf.runtime.emf.commands.core.command.
     * AbstractTransactionalCommand#getAffectedFiles()
     */
    public List getAffectedFiles() {
        if (editparts != null) {
            // we only need the first child since all the edit parts being
            // snapped originate from the same diagram
            IGraphicalEditPart parent = editparts.get(0);
            View view = (View) parent.getParent().getModel();
            return getWorkspaceFiles(view);
        }
        return super.getAffectedFiles();
    }

    /**
     * Executes a snap command for all the desired edit parts.
     * 
     */
    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

        CompositeTransactionalCommand snapCommand = new CompositeTransactionalCommand(getEditingDomain(), getLabel());

        for (IGraphicalEditPart newEditPart : editparts) {
            if (newEditPart.getModel() instanceof Node) {

                LayoutConstraint constraint = ((Node) newEditPart.getModel()).getLayoutConstraint();
                if (constraint instanceof Bounds) {

                    Bounds bounds = (Bounds) constraint;
                    ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
                    request.setEditParts(newEditPart);

                    // translate all coordinates to device units as a standard
                    // if necessary
                    // this is done since moveDelta uses device units

                    PrecisionDimension moveDeltaDim = new PrecisionDimension(bounds.getX() - newEditPart.getFigure().getBounds().x, bounds.getY() - newEditPart.getFigure().getBounds().y);
                    /*
                     * Distance in pixels needs to be scaled by the scaling
                     * factor of the zoom tool, i.e. ScaledRootEditPart
                     */
                    newEditPart.getFigure().translateToAbsolute(moveDeltaDim);
                    PrecisionPoint moveDelta = new PrecisionPoint(moveDeltaDim.preciseWidth(), moveDeltaDim.preciseHeight());

                    // In the case that the figure bounds and model's layout
                    // constant are the same,
                    // xDiff and yDiff will evaluate to zero, but snapToHelper
                    // will still locate the closest
                    // NorthWest snap locations. In the case that they're not,
                    // we assume the figure's bounds
                    // have not been updated, so the moveDelta value will
                    // simulate a drag to the new location
                    // In both situations we base the resulting snap location
                    // off the figure's bounds

                    // snapToGrid logic taken from DragEditPartsTracker.java
                    request.getExtendedData().clear();
                    request.setMoveDelta(moveDelta);

                    PrecisionRectangle figureBounds = null;
                    IFigure figure = newEditPart.getFigure();
                    if (figure instanceof HandleBounds) {
                        figureBounds = new PrecisionRectangle(((HandleBounds) figure).getHandleBounds());
                    } else {
                        figureBounds = new PrecisionRectangle(figure.getBounds());
                    }

                    figure.translateToAbsolute(figureBounds);

                    SnapToHelper snapToHelper = (SnapToHelper) newEditPart.getAdapter(SnapToHelper.class);
                    PrecisionRectangle baseRect = new PrecisionRectangle(figureBounds);
                    baseRect.translate(moveDelta);

                    if (snapToHelper != null) {
                        snapToHelper.snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[] { baseRect }, moveDelta);
                        request.setMoveDelta(moveDelta);
                    }

                    Command gefMove = newEditPart.getCommand(request);
                    if (gefMove != null) {
                        snapCommand.add(new CommandProxy(gefMove));
                    }

                }
            }
        }

        if (snapCommand != null && snapCommand.canExecute()) {
            snapCommand.execute(progressMonitor, info);
        }
        return CommandResult.newOKCommandResult();
    }
}
// CHECLSTYLE:ON
