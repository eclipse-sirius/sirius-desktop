/******************************************************************************
 * Copyright (c) 2007, 2017 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Michael Golubev (Borland) - Fix for Bug 261192
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionDimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.SnapToGrid;
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
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

// Copied from org.eclipse.gmf.runtime.diagram.ui.internal.commands.SnapCommand
//CHECKSTYLE:OFF
/**
 * This command is used to snap edit parts on a diagram, where the edit parts are passed in as the parameter.
 * 
 * Duplicate class for {@link org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusContainerEditPolicy} needs.
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
     * @see org.eclipse.gmf.runtime.emf.commands.core.command. AbstractTransactionalCommand#getAffectedFiles()
     */
    @Override
    public List getAffectedFiles() {
        if (editparts != null) {
            // we only need the first child since all the edit parts being
            // snapped originate from the same diagram
            IGraphicalEditPart parent = editparts.get(0);
            View view = parent.getPrimaryView();
            return getWorkspaceFiles(view);
        }
        return super.getAffectedFiles();
    }

    /**
     * Executes a snap command for all the desired edit parts.
     * 
     */
    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

        CompositeTransactionalCommand snapCommand = new CompositeTransactionalCommand(getEditingDomain(), getLabel());
        editparts.stream().forEach(newEditPart -> {
            if (newEditPart instanceof IDiagramBorderNodeEditPart) {
                handleBorderNode((IDiagramBorderNodeEditPart) newEditPart, snapCommand);
            } else if (newEditPart instanceof IAbstractDiagramNodeEditPart) {
                handleNodeAndContainer((IAbstractDiagramNodeEditPart) newEditPart, snapCommand);
            }
            // We create a single snapCommand per border node to locate them one after another to avoid location
            // conflicts.
            newEditPart.getChildren().stream().filter(IDiagramBorderNodeEditPart.class::isInstance).forEach(borderNodeEditPart -> {
                snapCommand.add(new SnapCommand(getEditingDomain(), Collections.singletonList((IDiagramBorderNodeEditPart) borderNodeEditPart)));
            });

            // For each edit part we add a new SnapCommand to snap its children

            @SuppressWarnings("unchecked")
            List<IGraphicalEditPart> editParts = (List<IGraphicalEditPart>) newEditPart.getChildren().stream().filter(ep -> {
                return !(ep instanceof IDiagramBorderNodeEditPart) && ep instanceof IGraphicalEditPart;
            }).collect(Collectors.toList());
            if (!editParts.isEmpty()) {
                snapCommand.add(new SnapCommand(getEditingDomain(), editParts));
            }
        });
        if (snapCommand != null && snapCommand.canExecute()) {
            snapCommand.execute(progressMonitor, info);
        }
        return CommandResult.newOKCommandResult();
    }

    private void handleNodeAndContainer(IAbstractDiagramNodeEditPart newEditPart, CompositeTransactionalCommand snapCommand) {
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
                 * Distance in pixels needs to be scaled by the scaling factor of the zoom tool, i.e. ScaledRootEditPart
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

                // We compute the new GMF Absolute Location. The SnapToHelper works with absolute coordinates.
                Point newGMFAbsoluteLocation = GMFHelper.getAbsoluteLocation((Node) newEditPart.getModel(), true, true);

                // We convert in screen coordinate as it is expected by the SnapToHelper
                GraphicalHelper.logical2screen(newGMFAbsoluteLocation, newEditPart);
                SnapToHelper snapToHelper = newEditPart.getAdapter(SnapToHelper.class);
                PrecisionRectangle baseRect = new PrecisionRectangle(figureBounds);
                baseRect.setLocation(newGMFAbsoluteLocation);
                if (snapToHelper != null) {
                    PrecisionPoint result = new PrecisionPoint();
                    snapToHelper.snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[] { baseRect }, result);

                    request.setMoveDelta(moveDelta.getTranslated(result));
                }

                Command gefMove = newEditPart.getCommand(request);
                if (gefMove != null) {
                    snapCommand.add(new CommandProxy(gefMove));
                }

            }
        }
    }

    /**
     * We make a specific treatment for border node since we need to use the {@link CanonicalDBorderItemLocator}.
     * 
     * @param newEditPart
     * @param snapCommand
     */
    private void handleBorderNode(IDiagramBorderNodeEditPart newEditPart, CompositeTransactionalCommand snapCommand) {
        if (newEditPart.getModel() instanceof Node) {
            Node node = (Node) newEditPart.getModel();
            Node parentNode = (Node) node.eContainer();

            // We use the CanonicalDBorderItemLocator to compute the new border node location in absolute coordinates
            Dimension spacing = (Dimension) newEditPart.getViewer().getProperty(SnapToGrid.PROPERTY_GRID_SPACING);
            CanonicalDBorderItemLocator itemLocator = initCDBIL(node, parentNode, spacing);
            Rectangle newGMFAbsoluteBounds = GMFHelper.getAbsoluteBounds(node, true, false, false, false);
            Point newValidLocation = itemLocator.getValidLocation(newGMFAbsoluteBounds, node, Collections.singleton(node));

            // We compute the new relative coordinates
            Point parentAbsoluteLocation = GMFHelper.getAbsoluteLocation(parentNode, true, false);
            Point newValidRelativeLocation = newValidLocation.getTranslated(parentAbsoluteLocation.getNegated());

            // We compute the move delta by calculating the difference between the current figure relative location and
            // the new computed location.
            EditPartQuery editPartQuery = new EditPartQuery(newEditPart);
            IAbstractDiagramNodeEditPart parentEditPart = editPartQuery.getFirstAncestorOfType(IAbstractDiagramNodeEditPart.class);
            Rectangle currentFigureBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(newEditPart, true);
            Point figureParentAbsoluteLocation = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentEditPart, true).getLocation();
            Point currentRelativeFigureLocation = currentFigureBounds.getLocation().getTranslated(figureParentAbsoluteLocation.getNegated());
            Point moveDelta = newValidRelativeLocation.getTranslated(currentRelativeFigureLocation.getNegated());

            // We create the ChangeBoundsRequest and get the new command.
            ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
            request.setEditParts(newEditPart);
            // The move delta is always in screen coordinates, that means by considering the zoom level (the scroll
            // position is not relevant since the move delta is a relative distance)
            double scale = GraphicalHelper.getZoom(newEditPart);
            moveDelta = new PrecisionPoint(moveDelta);
            moveDelta.scale(scale);
            request.setMoveDelta(moveDelta);
            Command gefMove = newEditPart.getCommand(request);
            if (gefMove != null) {
                snapCommand.add(new CommandProxy(gefMove));
            }
        }

    }

    private CanonicalDBorderItemLocator initCDBIL(Node node, Node parentNode, Dimension spacing) {
        CanonicalDBorderItemLocator itemLocator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW, true, spacing.height);
        if (new ViewQuery(node).isForNameEditPart()) {
            itemLocator.setBorderItemOffset(IBorderItemOffsets.NO_OFFSET);
        } else {
            if (new DDiagramElementQuery((DDiagramElement) node.getElement()).isIndirectlyCollapsed()) {
                itemLocator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
            } else {
                itemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
            }
        }
        return itemLocator;
    }
}
// CHECLSTYLE:ON
