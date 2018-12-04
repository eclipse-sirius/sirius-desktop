/*******************************************************************************
 * Copyright (c) 2014, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.edit.commands;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.business.internal.operation.MoveViewOperation;
import org.eclipse.sirius.diagram.ui.business.internal.operation.ShiftDirectBorderedNodesOperation;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusResizeTracker;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This command avoids time consumption as long as it does not executed. The
 * "real" command is created during the execution.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ChildrenAdjustmentCommand extends AbstractTransactionalCommand {
    CompositeTransactionalCommand wrappedCommand;

    IGraphicalEditPart host;

    ChangeBoundsRequest request;

    private boolean adjustBorderNodes;

    private boolean adjustSubNodes;

    /**
     * Default constructor.
     *
     * @param host
     *            the <i>host</i> EditPart on which this policy is installed.
     * @param request
     *            the initial change bounds request
     */
    public ChildrenAdjustmentCommand(IGraphicalEditPart host, ChangeBoundsRequest request) {
        this(host, request, true, true);
    }

    /**
     * Default constructor.
     *
     * @param host
     *            the <i>host</i> EditPart on which this policy is installed.
     * @param request
     *            the initial change bounds request
     * @param borderNodes
     *            true to adjust border nodes positions
     * @param subNodes
     *            true to adjust sub nodes positions (Containers and Nodes in
     *            the content pane)
     */
    public ChildrenAdjustmentCommand(IGraphicalEditPart host, ChangeBoundsRequest request, boolean borderNodes, boolean subNodes) {
        super(host.getEditingDomain(), Messages.ChildrenAdjustmentCommand_label, null);
        this.host = host;
        this.request = request;
        this.adjustBorderNodes = borderNodes;
        this.adjustSubNodes = subNodes;

    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) {
        CommandResult result = CommandResult.newOKCommandResult();
        wrappedCommand = new CompositeTransactionalCommand(host.getEditingDomain(), this.getLabel());

        RequestQuery rq = new RequestQuery(request);
        boolean keepSameAbsoluteLocation = false;
        if (rq.isResizeFromTop() || rq.isResizeFromLeft() || request.isCenteredResize()) {
            Object childrenMoveModeExtendedData = request.getExtendedData().get(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY);
            keepSameAbsoluteLocation = (childrenMoveModeExtendedData == null && SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE)
                    || (childrenMoveModeExtendedData != null && ((Boolean) childrenMoveModeExtendedData).booleanValue());
            if (keepSameAbsoluteLocation && adjustSubNodes) {
                addChildrenAdjustmentCommands(host, wrappedCommand, request);
            } else {
                // Children have been indirectly moved so their edges must
                // adapted to only move last segment
                addChildrenEdgesAdjustmentCommands(host, wrappedCommand, request);
            }
        }

        if (host instanceof IBorderedShapeEditPart && adjustBorderNodes) {
            addBorderChildrenAdjustmentCommands(host, wrappedCommand, request, keepSameAbsoluteLocation);
        }

        if (wrappedCommand.size() > 0) {
            if (wrappedCommand.canExecute()) {
                try {
                    wrappedCommand.execute(new NullProgressMonitor(), null);
                } catch (ExecutionException e) {
                    result = CommandResult.newErrorCommandResult(e);
                }
            } else {
                // Not expected to be there
                result = CommandResult.newWarningCommandResult(Messages.ChildrenAdjustmentCommand_errorMsg, null);
            }
        }
        return result;
    }

    @Override
    public boolean canUndo() {
        if (wrappedCommand != null && wrappedCommand.size() > 0) {
            return wrappedCommand.canUndo();
        }
        return true;
    }

    @Override
    public boolean canRedo() {
        if (wrappedCommand != null && wrappedCommand.size() > 0) {
            return wrappedCommand.canRedo();
        }
        return true;
    }

    @Override
    public void dispose() {
        host = null;
        request = null;
        wrappedCommand = null;
    }

    /**
     * Add the needed commands, to move the children nodes, to the original
     * command.
     *
     * @param resizedPart
     *            The part that will be resized
     * @param cc
     *            The current command that resizes the parent part, command to
     *            complete with the moves of children
     * @param cbr
     *            The original request
     */
    private void addChildrenAdjustmentCommands(IGraphicalEditPart resizedPart, CompositeTransactionalCommand cc, ChangeBoundsRequest cbr) {
        PrecisionPoint delta = new PrecisionPoint(cbr.getMoveDelta().getNegated());
        GraphicalHelper.applyInverseZoomOnPoint(resizedPart, delta);
        AbstractDNodeContainerCompartmentEditPart compartment = Iterables.getFirst(Iterables.filter(resizedPart.getChildren(), AbstractDNodeContainerCompartmentEditPart.class), null);
        if (compartment != null) {
            Iterable<EditPart> childrenExceptBorderItemPart = Iterables.filter(compartment.getChildren(), EditPart.class);
            for (EditPart editPart : childrenExceptBorderItemPart) {
                IAdaptable adapter = new EObjectAdapter((Node) editPart.getModel());
                // Shift this view by the delta
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new MoveViewOperation(DiagramUIMessages.SetLocationCommand_Label_Resize, adapter, delta)));
            }
        }
    }

    /**
     * Add the needed commands, to "move" only the last segment of edges
     * pointing to the children nodes, to the original command.
     *
     * @param resizedPart
     *            The part that will be resized
     * @param cc
     *            The current command that resizes the parent part, command to
     *            complete with the moves of children
     * @param cbr
     *            The original request
     */
    private void addChildrenEdgesAdjustmentCommands(IGraphicalEditPart resizedPart, CompositeTransactionalCommand cc, ChangeBoundsRequest cbr) {
        PrecisionPoint delta = new PrecisionPoint(cbr.getMoveDelta());
        GraphicalHelper.applyInverseZoomOnPoint(resizedPart, delta);
        DNodeContainerViewNodeContainerCompartmentEditPart compartment = Iterables.getFirst(Iterables.filter(resizedPart.getChildren(), DNodeContainerViewNodeContainerCompartmentEditPart.class),
                null);
        if (compartment != null) {
            Iterable<EditPart> childrenExceptBorderItemPart = Iterables.filter(compartment.getChildren(), EditPart.class);
            for (EditPart editPart : childrenExceptBorderItemPart) {
                // Adapt edges to move only the last segment
                if (editPart instanceof IGraphicalEditPart) {
                    cc.compose(new ChangeBendpointsOfEdgesCommand((IGraphicalEditPart) editPart, delta));
                }
            }
        }
    }

    /**
     * Add the needed commands, to move the border nodes, to the original
     * command.
     *
     * @param resizedPart
     *            The part that will be resized (parent of the border nodes to
     *            move)
     * @param cc
     *            The current command that resizes the parent part, command to
     *            complete with the moves of border nodes
     * @param cbr
     *            The resize request
     * @param keepSameAbsoluteLocation
     *            true if the children must stay at the same absolute location,
     *            false otherwise. The location can change in one axis of there
     *            border node is on the moved side.
     */
    private void addBorderChildrenAdjustmentCommands(IGraphicalEditPart resizedPart, CompositeTransactionalCommand cc, final ChangeBoundsRequest cbr, boolean keepSameAbsoluteLocation) {
        RequestQuery rq = new RequestQuery(cbr);
        Rectangle logicalDelta = rq.getLogicalDelta();
        EditPartQuery resizedPartQuery = new EditPartQuery(resizedPart);
        if (rq.isResizeFromTop() || rq.isResizeFromBottom()) {
            int verticalSizeDelta = logicalDelta.height;

            // The border nodes of the bottom side must be shift to stay on
            // the bottom side.
            List<Node> childrenToMove = resizedPartQuery.getBorderedNodes(PositionConstants.SOUTH);
            if (!childrenToMove.isEmpty()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(childrenToMove, new Dimension(0, verticalSizeDelta))));
            }

            // The border nodes of the east and west side must eventually be
            // shift to stay in the parent bounds.
            Map<Node, Dimension> childrenToMoveWithDelta;
            if (rq.isResizeFromTop()) {
                childrenToMoveWithDelta = resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.EAST, PositionConstants.NORTH, verticalSizeDelta);
                childrenToMoveWithDelta.putAll(resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.WEST, PositionConstants.NORTH, verticalSizeDelta));
            } else {
                childrenToMoveWithDelta = resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.EAST, PositionConstants.SOUTH, verticalSizeDelta);
                childrenToMoveWithDelta.putAll(resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.WEST, PositionConstants.SOUTH, verticalSizeDelta));
            }
            for (Entry<Node, Dimension> entry : childrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue())));
            }

            if (rq.isResizeFromTop() || cbr.isCenteredResize()) {
                // The edges linked to border node of the north side must
                // adapted to only move the last segment.
                List<IBorderItemEditPart> childrenWithEdgesToMove = resizedPartQuery.getBorderNodeEditParts(PositionConstants.NORTH);
                PrecisionPoint delta;
                if (cbr.isCenteredResize()) {
                    delta = new PrecisionPoint(0, -verticalSizeDelta - logicalDelta.y);
                } else {
                    delta = new PrecisionPoint(0, -verticalSizeDelta);
                }
                for (IBorderItemEditPart borderNodeEditPart : childrenWithEdgesToMove) {
                    cc.compose(new ChangeBendpointsOfEdgesCommand(borderNodeEditPart, delta));
                }

                if (keepSameAbsoluteLocation) {
                    // The border nodes of the west and east sides must be shift
                    // to stay at the same absolute location (except if they
                    // have already moved to stay in the parent bounds).
                    if (cbr.isCenteredResize()) {
                        verticalSizeDelta = verticalSizeDelta + logicalDelta.y;
                    }
                    List<Node> borderNodes = resizedPartQuery.getBorderedNodes(PositionConstants.WEST);
                    borderNodes.addAll(resizedPartQuery.getBorderedNodes(PositionConstants.EAST));
                    borderNodes.removeAll(childrenToMoveWithDelta.keySet());
                    cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(borderNodes, new Dimension(0, verticalSizeDelta))));
                } else {
                    // The edges linked to border nodes of the west and east
                    // sides must be adapted to only move the last segment.
                    childrenWithEdgesToMove = resizedPartQuery.getBorderNodeEditParts(PositionConstants.WEST);
                    childrenWithEdgesToMove.addAll(resizedPartQuery.getBorderNodeEditParts(PositionConstants.EAST));
                    childrenWithEdgesToMove.removeAll(childrenToMoveWithDelta.keySet());
                    if (cbr.isCenteredResize()) {
                        delta = new PrecisionPoint(0, -verticalSizeDelta - logicalDelta.y);
                    } else {
                        delta = new PrecisionPoint(0, -verticalSizeDelta);
                    }
                    for (IBorderItemEditPart borderNodeEditPart : childrenWithEdgesToMove) {
                        cc.compose(new ChangeBendpointsOfEdgesCommand(borderNodeEditPart, delta));
                    }
                }
            }
        }
        if (rq.isResizeFromRight() || rq.isResizeFromLeft()) {
            int horizontalSizeDelta = logicalDelta.width;
            // The border node of the east side must be shift to stay on the
            // east side.
            List<Node> childrenToMove = resizedPartQuery.getBorderedNodes(PositionConstants.EAST);
            if (!childrenToMove.isEmpty()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(childrenToMove, new Dimension(horizontalSizeDelta, 0))));
            }
            // The border nodes of the north or south side must eventually be
            // shift to stay in the parent bounds.
            Map<Node, Dimension> childrenToMoveWithDelta;
            if (rq.isResizeFromRight()) {
                childrenToMoveWithDelta = resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.NORTH, PositionConstants.EAST, horizontalSizeDelta);
                childrenToMoveWithDelta.putAll(resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.SOUTH, PositionConstants.EAST, horizontalSizeDelta));
            } else {
                childrenToMoveWithDelta = resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.NORTH, PositionConstants.WEST, horizontalSizeDelta);
                childrenToMoveWithDelta.putAll(resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.SOUTH, PositionConstants.WEST, horizontalSizeDelta));

            }
            for (Entry<Node, Dimension> entry : childrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue())));
            }
            if (rq.isResizeFromLeft() || cbr.isCenteredResize()) {
                // The edges linked to border node of the west side must adapted
                // to only move the last segment.
                List<IBorderItemEditPart> childrenWithEdgesToMove = resizedPartQuery.getBorderNodeEditParts(PositionConstants.WEST);
                PrecisionPoint delta;
                if (cbr.isCenteredResize()) {
                    delta = new PrecisionPoint(-horizontalSizeDelta - logicalDelta.x, 0);
                } else {
                    delta = new PrecisionPoint(-horizontalSizeDelta, 0);
                }
                for (IBorderItemEditPart borderNodeEditPart : childrenWithEdgesToMove) {
                    cc.compose(new ChangeBendpointsOfEdgesCommand(borderNodeEditPart, delta));
                }

                if (keepSameAbsoluteLocation) {
                    // The border nodes of the north and south sides must be
                    // shift to stay at the same absolute location (except if
                    // they have already moved to stay in the parent bounds).
                    if (cbr.isCenteredResize()) {
                        horizontalSizeDelta = horizontalSizeDelta + logicalDelta.x;
                    }
                    List<Node> borderNodes = resizedPartQuery.getBorderedNodes(PositionConstants.NORTH);
                    borderNodes.addAll(resizedPartQuery.getBorderedNodes(PositionConstants.SOUTH));
                    borderNodes.removeAll(childrenToMoveWithDelta.keySet());
                    cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(borderNodes, new Dimension(horizontalSizeDelta, 0))));
                } else {
                    // The edges linked border nodes of the north and south
                    // sides must be adapted to only move the last segment.
                    childrenWithEdgesToMove = resizedPartQuery.getBorderNodeEditParts(PositionConstants.NORTH);
                    childrenWithEdgesToMove.addAll(resizedPartQuery.getBorderNodeEditParts(PositionConstants.SOUTH));
                    childrenWithEdgesToMove.removeAll(childrenToMoveWithDelta.keySet());
                    if (cbr.isCenteredResize()) {
                        delta = new PrecisionPoint(-horizontalSizeDelta - logicalDelta.x, 0);
                    } else {
                        delta = new PrecisionPoint(-horizontalSizeDelta, 0);
                    }
                    for (IBorderItemEditPart borderNodeEditPart : childrenWithEdgesToMove) {
                        cc.compose(new ChangeBendpointsOfEdgesCommand(borderNodeEditPart, delta));
                    }
                }
            }
        }
    }
}
