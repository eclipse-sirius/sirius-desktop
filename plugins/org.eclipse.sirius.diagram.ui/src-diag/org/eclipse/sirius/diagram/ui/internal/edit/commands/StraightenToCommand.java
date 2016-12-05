/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.EdgeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.operation.ShiftDirectBorderedNodesOperation;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SetConnectionBendpointsAndLabelCommmand;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.internal.refresh.edge.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Lists;

/**
 * Command to straighten edge.<BR>
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StraightenToCommand extends AbstractTransactionalCommand {
    /** Edge edit part to straighten. */
    private AbstractDiagramEdgeEditPart edgeEditPart;

    /**
     * The straighten type must by one of:
     * <UL>
     * <LI>{@link StraightenToAction#TO_TOP}</LI>
     * <LI>{@link StraightenToAction#TO_BOTTOM}</LI>
     * <LI>{@link StraightenToAction#TO_LEFT}</LI>
     * <LI>{@link StraightenToAction#TO_RIGHT}</LI>
     * </UL>
     */
    private int straightenType;

    /** The source edit part of the <code>edgeEditPart</code>. */
    private IGraphicalEditPart sourceEditPart;

    /** The target edit part of the <code>edgeEditPart</code>. */
    private IGraphicalEditPart targetEditPart;

    /**
     * Boolean to indicate if the source of the edge is a border node.<br>
     * Value computed during {@link #canExecute()} method.
     */
    private boolean isSourceABorderNode;

    /**
     * Boolean to indicate if the target of the edge is a border node.<br>
     * Value computed during {@link #canExecute()} method.
     */
    private boolean isTargetABorderNode;

    /**
     * Boolean to indicate if this command moves the source or the target of the
     * edge.<br>
     * Value computed during {@link #canExecute()} method, more precisely in
     * {@link #isSourceWillBeMoved()} .
     */
    private boolean moveSource;

    /**
     * Boolean to indicate that we are in the specific case where both source
     * and target point will be moved. This case is when the source and the
     * target of the edge are border nodes and edge is centered on each side.
     */
    private boolean isSpecificCase;

    /**
     * The x delta by which the source (or target) will be moved.<br>
     * Value computed during {@link #canExecute()} method, more precisely in
     * {@link #isNewLocationInParentBounds()}.
     */
    private int deltaX = 0;

    /**
     * The y delta by which the source (or target) will be moved.<br>
     * Value computed during {@link #canExecute()} method, more precisely in
     * {@link #isNewLocationInParentBounds()}.
     */
    private int deltaY = 0;

    /**
     * Default constructor.
     *
     * @param edgeEditPart
     *            Selected edit part that will be straightened
     * @param straightenType
     *            The straighten type
     */
    public StraightenToCommand(AbstractDiagramEdgeEditPart edgeEditPart, int straightenType) {
        super(edgeEditPart.getEditingDomain(), StraightenToAction.getLabel(straightenType), null);
        this.edgeEditPart = edgeEditPart;
        this.straightenType = straightenType;
        if (edgeEditPart.getSource() instanceof IGraphicalEditPart) {
            sourceEditPart = (IGraphicalEditPart) edgeEditPart.getSource();
        }
        if (edgeEditPart.getTarget() instanceof IGraphicalEditPart) {
            targetEditPart = (IGraphicalEditPart) edgeEditPart.getTarget();
        }
    }

    @Override
    public boolean canExecute() {
        boolean canExecute = false;
        Object model = edgeEditPart.getModel();
        if (model instanceof Edge && sourceEditPart != null && targetEditPart != null) {
            Edge edge = (Edge) model;
            EdgeQuery edgeQuery = new EdgeQuery(edge);
            // Check if this edge has a rectilinear routing style or an oblique
            // routing style (not a tree routing style).
            canExecute = !edgeQuery.isEdgeWithTreeRoutingStyle();
            // Check if the source or the target of this edge is another edge
            if (canExecute) {
                canExecute = !(sourceEditPart instanceof ConnectionEditPart || targetEditPart instanceof ConnectionEditPart);
            }
            if (canExecute) {
                isSourceABorderNode = sourceEditPart instanceof AbstractDiagramBorderNodeEditPart;
                isTargetABorderNode = targetEditPart instanceof AbstractDiagramBorderNodeEditPart;
                // Check if the source and the target are not on the same axis
                // (west and east sides or north and south sides)
                int axis = getSourceAndTargetSameAxis();
                canExecute = axis != PositionConstants.NONE;
                if (canExecute) {
                    if ((axis == PositionConstants.HORIZONTAL && (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM))
                            || (axis == PositionConstants.VERTICAL && (straightenType == StraightenToAction.TO_LEFT || straightenType == StraightenToAction.TO_RIGHT))) {
                        moveSource = isSourceWillBeMoved();
                    } else {
                        canExecute = false;
                    }
                }
            }
            if (canExecute) {
                // A straighten action can be disabled if the edge centering is
                // activated on an edge.
                canExecute = !isCentered();
            }
            if (canExecute) {
                // Compute if new location is in bounds of its container.
                canExecute = isNewLocationInParentBounds();
            }
            if (canExecute) {
                // Check if the border node (source or target) will overlapped
                // another border node
                canExecute = !isOverlapped();
            }
        }
        return canExecute;
    }

    @Override
    public boolean canUndo() {
        return true;
    }

    @Override
    public boolean canRedo() {
        return true;
    }

    @Override
    public void dispose() {
        edgeEditPart = null;
        sourceEditPart = null;
        targetEditPart = null;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        CommandResult commandResult = CommandResult.newOKCommandResult();
        if (edgeEditPart.getFigure() instanceof Connection && edgeEditPart.getModel() instanceof Edge) {
            CompositeCommand cc = new CompositeCommand(getLabel());
            Rectangle sourceBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceEditPart);
            Rectangle targetBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetEditPart);
            Connection figure = (Connection) edgeEditPart.getFigure();
            Point firstPoint = figure.getPoints().getFirstPoint().getCopy();
            Point lastPoint = figure.getPoints().getLastPoint().getCopy();
            if (moveSource) {
                if (isSourceABorderNode) {
                    // Change the source bounds considering the delta
                    sourceBounds = sourceBounds.getTranslated(deltaX, deltaY);
                }
                if (isSpecificCase) {
                    computePointsInSpecificCase(firstPoint, lastPoint, sourceBounds, targetBounds);
                } else {
                    firstPoint = firstPoint.getTranslated(deltaX, deltaY);
                }
                completeCommand(cc, sourceEditPart, isSourceABorderNode, sourceBounds, targetBounds, firstPoint, lastPoint);
            } else {
                if (isTargetABorderNode) {
                    // Change the target bounds considering the delta
                    targetBounds = targetBounds.getTranslated(deltaX, deltaY);
                }
                if (isSpecificCase) {
                    computePointsInSpecificCase(firstPoint, lastPoint, sourceBounds, targetBounds);
                } else {
                    lastPoint = lastPoint.getTranslated(deltaX, deltaY);
                }
                completeCommand(cc, targetEditPart, isTargetABorderNode, sourceBounds, targetBounds, firstPoint, lastPoint);
            }
            // Execute the command
            IStatus status = cc.execute(monitor, info);
            if (status != null && !status.isOK()) {
                if (status.getSeverity() == IStatus.CANCEL) {
                    commandResult = CommandResult.newCancelledCommandResult();
                } else if (status.getSeverity() == IStatus.ERROR) {
                    commandResult = CommandResult.newErrorCommandResult(status.getException());
                }
            }
        }
        return commandResult;
    }

    private void computePointsInSpecificCase(Point firstPoint, Point lastPoint, Rectangle sourceBounds, Rectangle targetBounds) {
        if (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM) {
            firstPoint.setX(firstPoint.x).setY(sourceBounds.getCenter().y);
            lastPoint.setX(lastPoint.x).setY(targetBounds.getCenter().y);
        } else {
            firstPoint.setX(sourceBounds.getCenter().x).setY(firstPoint.y);
            lastPoint.setX(targetBounds.getCenter().x).setY(lastPoint.y);
        }
    }

    /**
     * Complete the command to add:
     * <UL>
     * <LI>A command to move the border node (if it is a border node)</LI>
     * <LI>A command to reset the source and target anchor</LI>
     * <LI>A command to reset the bendpoints</LI>
     * </UL>
     * 
     * @param command
     *            The command to complete
     * @param editPartOnMovedSide
     *            The {@link IGraphicalEditPart} on the side of the edge that
     *            will be moved.
     * @param isABorderNode
     *            true if the <code>editPartOnMovedSide</code> is a border node,
     *            false otherwise
     * @param sourceBounds
     *            The bounds of the source edit part
     * @param targetBounds
     *            The bounds of the target edit part
     * @param firstPoint
     *            The first point to set for the edge
     * @param lastPoint
     *            The last point to set for the edge
     */
    private void completeCommand(CompositeCommand command, IGraphicalEditPart editPartOnMovedSide, boolean isABorderNode, Rectangle sourceBounds, Rectangle targetBounds, Point firstPoint,
            Point lastPoint) {
        if (isABorderNode) {
            // Add command to move the border node
            command.add(
                    CommandFactory.createICommand(getEditingDomain(), new ShiftDirectBorderedNodesOperation(Lists.newArrayList((Node) editPartOnMovedSide.getModel()), new Dimension(deltaX, deltaY))));
            if ((editPartOnMovedSide.getSourceConnections().size() + editPartOnMovedSide.getTargetConnections().size()) > 1) {
                // Add a command to correctly moved all linked edges
                PrecisionPoint moveDelta = new PrecisionPoint(deltaX, deltaY);
                // Applied zoom on moveDelta, because it is what is expected in
                // ChangeBendpointsOfEdgesCommand
                GraphicalHelper.applyZoomOnPoint(editPartOnMovedSide, moveDelta);
                ChangeBendpointsOfEdgesCommand cboec = new ChangeBendpointsOfEdgesCommand(editPartOnMovedSide, moveDelta);
                cboec.setIgnoreSelectionChecks(true);
                command.add(cboec);
            }
        }
        // Add a command to change the anchors
        SetConnectionAnchorsCommand scaCommand = new SetConnectionAnchorsCommand(getEditingDomain(), StringStatics.BLANK);
        // Compute the new source and target anchors. One can be considered as
        // stable, but to ensure that the corresponding edge point will be
        // stable, we must reset both (to be sure there is no existing
        // desynchronization between GMF and draw2d).
        PrecisionPoint newSourceAnchor = BaseSlidableAnchor.getAnchorRelativeLocation(firstPoint, sourceBounds);
        scaCommand.setNewSourceTerminal(SlidableAnchor.composeTerminalString(newSourceAnchor));
        PrecisionPoint newTargetAnchor = BaseSlidableAnchor.getAnchorRelativeLocation(lastPoint, targetBounds);
        scaCommand.setNewTargetTerminal(SlidableAnchor.composeTerminalString(newTargetAnchor));
        scaCommand.setEdgeAdaptor(new EObjectAdapter((Edge) edgeEditPart.getModel()));
        command.add(scaCommand);
        // Add a command to set the new points (only 2 points)
        SetConnectionBendpointsAndLabelCommmand resetBendpointsCmd = new SetConnectionBendpointsAndLabelCommmand(getEditingDomain());
        resetBendpointsCmd.setEdgeAdapter(new EObjectAdapter((Edge) edgeEditPart.getModel()));
        PointList newPointList = new PointList(2);
        newPointList.addPoint(firstPoint);
        newPointList.addPoint(lastPoint);
        resetBendpointsCmd.setNewPointList(newPointList, firstPoint, lastPoint);
        resetBendpointsCmd.setLabelsToUpdate(edgeEditPart);
        command.add(resetBendpointsCmd);
    }

    /**
     * Get the axis ({@link PositionConstants#HORIZONTAL} or
     * {@link PositionConstants#VERTICAL}) if the source and the target are on
     * the same axis, {@link PositionConstants#NONE} otherwise.
     * 
     * @return the axis of the edge
     */
    private int getSourceAndTargetSameAxis() {
        int axis = PositionConstants.NONE;
        int sideOfSource = PositionConstants.NONE;
        if (isSourceABorderNode) {
            sideOfSource = ((IBorderItemEditPart) edgeEditPart.getSource()).getBorderItemLocator().getCurrentSideOfParent();
        } else if (edgeEditPart.getSource() instanceof GraphicalEditPart && edgeEditPart.getFigure() instanceof Connection) {
            Point firstPoint = ((Connection) edgeEditPart.getFigure()).getPoints().getFirstPoint();
            sideOfSource = getLocation(firstPoint, GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) edgeEditPart.getSource()));
        }
        int sideOfTarget = PositionConstants.NONE;
        if (isTargetABorderNode) {
            sideOfTarget = ((IBorderItemEditPart) edgeEditPart.getTarget()).getBorderItemLocator().getCurrentSideOfParent();
        } else if (edgeEditPart.getTarget() instanceof GraphicalEditPart && edgeEditPart.getFigure() instanceof Connection) {
            Point lastPoint = ((Connection) edgeEditPart.getFigure()).getPoints().getLastPoint();
            sideOfTarget = getLocation(lastPoint, GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) edgeEditPart.getTarget()));
        }
        if (isOnHorizontalAxis(sideOfSource) && isOnHorizontalAxis(sideOfTarget)) {
            axis = PositionConstants.HORIZONTAL;
        } else if (isOnVerticalAxis(sideOfSource) && isOnVerticalAxis(sideOfTarget)) {
            axis = PositionConstants.VERTICAL;
        }
        return axis;
    }

    /**
     * @return true if source will be moved, false if target will be moved.
     */
    private boolean isSourceWillBeMoved() {
        boolean isSourceWillBeMoved = false;
        Point sourcePoint;
        Point targetPoint;
        if (isSourceABorderNode) {
            sourcePoint = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceEditPart).getCenter();
        } else if (edgeEditPart.getFigure() instanceof ViewEdgeFigure) {
            PointList pointList = ((ViewEdgeFigure) edgeEditPart.getFigure()).getPoints().getCopy();
            sourcePoint = pointList.getFirstPoint();
        } else {
            sourcePoint = new Point();
        }
        if (isTargetABorderNode) {
            targetPoint = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetEditPart).getCenter();
        } else if (edgeEditPart.getFigure() instanceof ViewEdgeFigure) {
            PointList pointList = ((ViewEdgeFigure) edgeEditPart.getFigure()).getPoints().getCopy();
            targetPoint = pointList.getLastPoint();
        } else {
            targetPoint = new Point();
        }
        if (straightenType == StraightenToAction.TO_LEFT) {
            if (sourcePoint.x > targetPoint.x) {
                isSourceWillBeMoved = true;
            }
        } else if (straightenType == StraightenToAction.TO_RIGHT) {
            if (sourcePoint.x < targetPoint.x) {
                isSourceWillBeMoved = true;
            }
        } else if (straightenType == StraightenToAction.TO_TOP) {
            if (sourcePoint.y > targetPoint.y) {
                isSourceWillBeMoved = true;
            }
        } else if (sourcePoint.y < targetPoint.y) {
            isSourceWillBeMoved = true;
        }
        return isSourceWillBeMoved;
    }

    /**
     * The centered constraint must be respect. There is several cases:
     * <UL>
     * <LI>Oblique edges: Move forbidden if at least one side is centered.</LI>
     * <LI>Rectilinear edges: Move forbidden if the moved side is centered.LI>
     * </UL>
     * There is an exception when the source and the target of the edge are
     * border nodes and edge is centered on each side.
     * 
     * @return true if the centering constraint forbids the move, false
     *         otherwise.
     */
    private boolean isCentered() {
        boolean isCentered = false;
        if (edgeEditPart.getFigure() instanceof ViewEdgeFigure) {
            ViewEdgeFigure figure = (ViewEdgeFigure) edgeEditPart.getFigure();
            boolean isExceptionCase = isSourceABorderNode && isTargetABorderNode && figure.isSourceCentered() && figure.isTargetCentered();
            if (isExceptionCase) {
                isSpecificCase = true;
            } else if (new ConnectionEditPartQuery(edgeEditPart).isEdgeWithObliqueRoutingStyle()) {
                isCentered = figure.isSourceCentered() || figure.isTargetCentered();
            } else {
                if (moveSource) {
                    isCentered = figure.isSourceCentered();
                } else {
                    isCentered = figure.isTargetCentered();
                }
            }
        }
        return isCentered;
    }

    /**
     * Check if the new location is in the parent bounds. This method also sets
     * deltaX and deltaY used later during command execution.
     * 
     * @return true if the source or target of the edge is in the bounds of its
     *         parent after the straighten, false otherwise.
     */
    private boolean isNewLocationInParentBounds() {
        boolean isNewLocationInParentBounds = false;
        if (edgeEditPart.getFigure() instanceof Connection) {
            Connection figure = (Connection) edgeEditPart.getFigure();
            Point firstPoint = figure.getPoints().getFirstPoint().getCopy();
            Point lastPoint = figure.getPoints().getLastPoint().getCopy();
            if (isSpecificCase) {
                firstPoint = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceEditPart).getCenter();
                lastPoint = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetEditPart).getCenter();
            }
            if (moveSource) {
                if (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM) {
                    deltaY = lastPoint.y - firstPoint.y;
                } else if (straightenType == StraightenToAction.TO_LEFT || straightenType == StraightenToAction.TO_RIGHT) {
                    deltaX = lastPoint.x - firstPoint.x;
                }
                if (isSourceABorderNode) {
                    Rectangle parentBorderNodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) sourceEditPart.getParent());
                    Rectangle borderNodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceEditPart);
                    // Get the computed bounds after the move
                    borderNodeBounds = borderNodeBounds.getTranslated(deltaX, deltaY);
                    if (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM) {
                        if (parentBorderNodeBounds.y <= borderNodeBounds.y && (borderNodeBounds.y + borderNodeBounds.height) <= (parentBorderNodeBounds.y + parentBorderNodeBounds.height)) {
                            isNewLocationInParentBounds = true;
                        }
                    } else if (straightenType == StraightenToAction.TO_LEFT || straightenType == StraightenToAction.TO_RIGHT) {
                        if (parentBorderNodeBounds.x <= borderNodeBounds.x && (borderNodeBounds.x + borderNodeBounds.width) <= (parentBorderNodeBounds.x + parentBorderNodeBounds.width)) {
                            isNewLocationInParentBounds = true;
                        }
                    }
                } else {
                    Rectangle bounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceEditPart);
                    if (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM) {
                        if (bounds.y <= lastPoint.y && lastPoint.y <= (bounds.y + bounds.height)) {
                            isNewLocationInParentBounds = true;
                        }
                    } else if (straightenType == StraightenToAction.TO_LEFT || straightenType == StraightenToAction.TO_RIGHT) {
                        if (bounds.x <= lastPoint.x && lastPoint.x <= (bounds.x + bounds.width)) {
                            isNewLocationInParentBounds = true;
                        }
                    }
                }
            } else {
                if (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM) {
                    deltaY = firstPoint.y - lastPoint.y;
                } else if (straightenType == StraightenToAction.TO_LEFT || straightenType == StraightenToAction.TO_RIGHT) {
                    deltaX = firstPoint.x - lastPoint.x;
                }
                if (isTargetABorderNode) {
                    Rectangle parentBorderNodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) targetEditPart.getParent());
                    Rectangle borderNodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetEditPart);
                    // Get the computed bounds after the move
                    borderNodeBounds = borderNodeBounds.getTranslated(deltaX, deltaY);
                    if (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM) {
                        if (parentBorderNodeBounds.y <= borderNodeBounds.y && (borderNodeBounds.y + borderNodeBounds.height) <= (parentBorderNodeBounds.y + parentBorderNodeBounds.height)) {
                            isNewLocationInParentBounds = true;
                        }
                    } else if (straightenType == StraightenToAction.TO_LEFT || straightenType == StraightenToAction.TO_RIGHT) {
                        if (parentBorderNodeBounds.x <= borderNodeBounds.x && (borderNodeBounds.x + borderNodeBounds.width) <= (parentBorderNodeBounds.x + parentBorderNodeBounds.width)) {
                            isNewLocationInParentBounds = true;
                        }
                    }
                } else {
                    Rectangle bounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(targetEditPart);
                    if (straightenType == StraightenToAction.TO_TOP || straightenType == StraightenToAction.TO_BOTTOM) {
                        if (bounds.y <= firstPoint.y && firstPoint.y <= (bounds.y + bounds.height)) {
                            isNewLocationInParentBounds = true;
                        }
                    } else if (straightenType == StraightenToAction.TO_LEFT || straightenType == StraightenToAction.TO_RIGHT) {
                        if (bounds.x <= firstPoint.x && firstPoint.x <= (bounds.x + bounds.width)) {
                            isNewLocationInParentBounds = true;
                        }
                    }
                }
            }
        }
        return isNewLocationInParentBounds;
    }

    /**
     * {@link #isNewLocationInParentBounds()} must be called before this method
     * as it determines the {@link #deltaX} and {@link #deltaY} needed to check
     * overlap.
     * 
     * @return true if the border node overlapped another border node after the
     *         move, false otherwise (or if the moved node is not a border
     *         node).
     */
    private boolean isOverlapped() {
        boolean isOverlapped = false;
        if ((moveSource && isSourceABorderNode)) {
            // The source border node will be moved
            if (sourceEditPart.getModel() instanceof Node) {
                isOverlapped = isOverlapped((Node) sourceEditPart.getModel());
            }
        } else if (!moveSource && isTargetABorderNode) {
            // The target border node will be moved
            if (targetEditPart.getModel() instanceof Node) {
                isOverlapped = isOverlapped((Node) targetEditPart.getModel());
            }
        }
        return isOverlapped;
    }

    /**
     * Check if the <code>node</node> is overlapped another border node after
     * applying the move (with {@link #deltaX} and {@link #deltaY}).
     * 
     * @param node
     *            The {@link Node} to check
     * @return true if there is an overlap, false otherwise.
     */
    private boolean isOverlapped(Node node) {
        boolean isOverlapped = false;
        Node parentNode = (Node) node.eContainer();
        // Create a canonicalDBorderItemLocator to locate this
        // border node after move.
        CanonicalDBorderItemLocator borderItemLocator = new CanonicalDBorderItemLocator(parentNode, PositionConstants.NSEW);
        borderItemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
        LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Location) {
            Rectangle constraint;
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                constraint = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
            } else {
                Location location = (Location) layoutConstraint;
                constraint = new Rectangle(location.getX(), location.getY(), -1, -1);
            }
            // Change the constraint to respect the expected move
            constraint.translate(deltaX, deltaY);
            Point originalLocation = constraint.getLocation();
            // Check if this location is available
            Point parentAbsoluteLocation = GMFHelper.getAbsoluteLocation(parentNode);
            constraint.translate(parentAbsoluteLocation.x, parentAbsoluteLocation.y);
            final Point realLocation = borderItemLocator.getValidLocation(constraint, node, Lists.newArrayList(node));
            final Dimension d = realLocation.getDifference(parentAbsoluteLocation);
            realLocation.setLocation(new Point(d.width, d.height));
            if (!originalLocation.equals(realLocation)) {
                isOverlapped = true;
            }
        }
        return isOverlapped;
    }

    private boolean isOnHorizontalAxis(int side) {
        return ((side & PositionConstants.EAST) > 0) || ((side & PositionConstants.WEST) > 0);
    }

    private boolean isOnVerticalAxis(int side) {
        return ((side & PositionConstants.NORTH) > 0) || ((side & PositionConstants.SOUTH) > 0);
    }

    /**
     * <P>
     * Returns an integer which represents the position of the given point with
     * respect to this rectangle. Possible return values are bitwise ORs of the
     * constants WEST, EAST, NORTH, and SOUTH as found in
     * {@link org.eclipse.draw2d.PositionConstants}.
     * 
     * <P>
     * Returns PositionConstant.NONE if no correspondence is found.
     * 
     * @param point
     *            The Point whose position has to be determined
     * @param rect
     *            the {@link Rectangle} on which <code>location</code> is
     *            supposed to be
     * @return An <code>int</code> which is a {@link PositionConstant}
     */
    private int getLocation(Point point, Rectangle rect) {
        int side = rect.getPosition(point);
        if (side == PositionConstants.NONE) {
            if (point.x() == rect.x) {
                side = PositionConstants.WEST;
            } else if (point.x() == (rect.x + rect.width)) {
                side = PositionConstants.EAST;
            }
            if (point.y() == rect.y) {
                side = side | PositionConstants.NORTH;
            } else if (point.y() == (rect.y + rect.height)) {
                side = side | PositionConstants.SOUTH;
            }
        }
        return side;
    }
}
