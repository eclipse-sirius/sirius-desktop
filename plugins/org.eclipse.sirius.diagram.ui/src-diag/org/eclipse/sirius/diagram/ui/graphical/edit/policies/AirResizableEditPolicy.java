/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.ui.business.internal.operation.MoveViewOperation;
import org.eclipse.sirius.diagram.ui.business.internal.operation.ShiftDirectBorderedNodesOperation;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.validators.ResizeValidator;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.viewpoint.Style;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The specific edit policy to redefine auto size.
 * 
 * @author ymortier
 */
public class AirResizableEditPolicy extends ResizableShapeEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * Use our own ResizeTracker to set the "flag"
     * SiriusResizeTracker.FIX_CHILDREN_KEY when the corresponding shortcut is
     * pressed.
     */
    @Override
    protected ResizeTracker getResizeTracker(int direction) {
        return new SiriusResizeTracker((GraphicalEditPart) getHost(), direction);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy#getAutoSizeCommand(org.eclipse.gef.Request)
     */
    @Override
    protected Command getAutoSizeCommand(final Request request) {
        if (this.getHost() instanceof IDiagramNodeEditPart) {
            final EObject eObject = ((IGraphicalEditPart) this.getHost()).resolveSemanticElement();
            if (eObject instanceof DNode) {
                final NodeStyle nodeStyle = (NodeStyle) ((DNode) eObject).getStyle();
                if (nodeStyle != null && nodeStyle.getLabelPosition() == LabelPosition.NODE_LITERAL) {
                    final DNode viewNode = (DNode) eObject;
                    final SiriusWrapLabel label = ((IDiagramNodeEditPart) getHost()).getNodeLabel();
                    final Style style = viewNode.getStyle();
                    final DiagramElementMapping mapping = viewNode.getDiagramElementMapping();
                    final DefaultSizeNodeFigure defaultSizeNodeFigure = (DefaultSizeNodeFigure) ((IDiagramNodeEditPart) this.getHost()).getMainFigure();
                    final StyleConfiguration styleConfiguration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(mapping, style);
                    final Dimension newDim = styleConfiguration.fitToText(viewNode, label, defaultSizeNodeFigure);
                    final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(viewNode);
                    final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(transactionalEditingDomain, "Auto Size",
                            new EObjectAdapter(((IGraphicalEditPart) this.getHost()).getNotationView()), newDim);
                    return new ICommandProxy(setBoundsCommand);
                }
            }
        }
        return super.getAutoSizeCommand(request);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.NonResizableEditPolicy#getMoveCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        Command originalMoveCommand = super.getMoveCommand(request);

        if (getHost().getParent() != null) {
            return changeBendpointsOfEdges(getHost(), originalMoveCommand, request.getMoveDelta(), getHost().getViewer().getSelectedEditParts());
        } else {
            return null;
        }
    }

    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        Command originalAlignCommand = super.getAlignCommand(request);

        Point delta = request.getMoveDelta();
        if (getHost() instanceof AbstractGraphicalEditPart) {
            Rectangle locationAndSize = new PrecisionRectangle(((AbstractGraphicalEditPart) getHost()).getFigure().getBounds());
            ((AbstractGraphicalEditPart) getHost()).getFigure().translateToAbsolute(locationAndSize);
            Rectangle newLocationAndSize = request.getTransformedRectangle(locationAndSize);
            delta = newLocationAndSize.getTopLeft().getTranslated(locationAndSize.getTopLeft().getNegated());
        }
        // The primary selection is removed from this selected list because it
        // is not moved.
        List<?> movedEditParts = removePrimarySelection(getHost().getViewer().getSelectedEditParts());

        return changeBendpointsOfEdges(getHost(), originalAlignCommand, delta, movedEditParts);
    }

    private List<AbstractGraphicalEditPart> removePrimarySelection(List<?> selectedEditParts) {
        List<AbstractGraphicalEditPart> result = Lists.newArrayList();
        Iterable<AbstractGraphicalEditPart> selectedEditPartsWithoutPrimary = Iterables.filter(Iterables.filter(selectedEditParts, AbstractGraphicalEditPart.class),
                new Predicate<AbstractGraphicalEditPart>() {
                    @Override
                    public boolean apply(AbstractGraphicalEditPart input) {
                        return input.getSelected() != EditPart.SELECTED_PRIMARY;
                    }
                });
        Iterables.addAll(result, selectedEditPartsWithoutPrimary);
        return result;
    }

    /**
     * Add commands to the <code>originalCommand</code> to adapt the bendpoints
     * to avoid unexpected moves of segment.<BR>
     * For oblique and rectilinear edges, only the last segment must move.
     * 
     * @param host
     *            the <i>host</i> EditPart on which this policy is installed.
     * @param originalCommand
     *            The command to complete with the potential bendpoints impact.
     * @param moveDelta
     *            The move delta
     * @param movedEditParts
     *            Selected edit parts that will be moved
     * @return the completed command
     */
    public static Command changeBendpointsOfEdges(EditPart host, Command originalCommand, Point moveDelta, List<?> movedEditParts) {
        CompoundCommand result = new CompoundCommand(originalCommand.getLabel());
        // It's possible that host is not in the list of movedEditParts in case
        // of "Arrange Selection" action. Indeed, in this case, the arrange
        // selection launch a "false" arrange all (see
        // ArrangeSelectionLayoutProvider.layoutEditParts(List, IAdaptable) for
        // more details). In this case we do not consider the move given it
        // will be "revert" later by the "PinnedElementsHandler".
        if (movedEditParts.contains(host) && host instanceof AbstractGraphicalEditPart) {
            List<AbstractGraphicalEditPart> allMovedEditParts = getMovedChildren(Iterables.filter(movedEditParts, AbstractGraphicalEditPart.class), true);
            AbstractGraphicalEditPart currentMovedEditPart = (AbstractGraphicalEditPart) host;
            List<AbstractGraphicalEditPart> currentMovedEditPartAndItsChildren = getMovedChildren(currentMovedEditPart, true);
            final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(host.getModel());
            for (AbstractGraphicalEditPart movedEditPart : currentMovedEditPartAndItsChildren) {
                for (ConnectionEditPart connectionEditPart : Iterables.filter(movedEditPart.getSourceConnections(), ConnectionEditPart.class)) {
                    Option<? extends Command> optionalCommand = getBendpointsChangedCommand(transactionalEditingDomain, moveDelta, connectionEditPart, allMovedEditParts, true);
                    if (optionalCommand.some()) {
                        if (result.isEmpty()) {
                            result.add(originalCommand);
                        }
                        result.add(optionalCommand.get());
                    }
                }
                for (ConnectionEditPart connectionEditPart : Iterables.filter(movedEditPart.getTargetConnections(), ConnectionEditPart.class)) {
                    Option<? extends Command> optionalCommand = getBendpointsChangedCommand(transactionalEditingDomain, moveDelta, connectionEditPart, allMovedEditParts, false);
                    if (optionalCommand.some()) {
                        if (result.isEmpty()) {
                            result.add(originalCommand);
                        }
                        result.add(optionalCommand.get());
                    }
                }
            }
        }
        if (result.isEmpty()) {
            return originalCommand;
        } else {
            return result;
        }
    }

    private static List<AbstractGraphicalEditPart> getMovedChildren(Iterable<AbstractGraphicalEditPart> parentEditParts, boolean addSelf) {
        List<AbstractGraphicalEditPart> result = Lists.newArrayList();
        for (AbstractGraphicalEditPart abstractGraphicalEditPart : parentEditParts) {
            result.addAll(getMovedChildren(abstractGraphicalEditPart, true));
        }
        return result;
    }

    private static List<AbstractGraphicalEditPart> getMovedChildren(AbstractGraphicalEditPart parentEditPart, boolean addSelf) {
        List<AbstractGraphicalEditPart> result = Lists.newArrayList();
        if (addSelf) {
            result.add(parentEditPart);
        }
        result.addAll(getMovedChildren(Iterables.filter(parentEditPart.getChildren(), AbstractGraphicalEditPart.class), true));
        return result;
    }

    /**
     * Compute the command needed to adapt the bendpoints of the
     * <code>connectionEditPart</code> if needed.
     * 
     * @param transactionalEditingDomain
     *            the editing domain through which model changes are made
     * @param moveDelta
     *            The move delta
     * @param connectionEditPart
     *            the connectionEditPart to deal with
     * @param allMovedEditParts
     *            This list is used to check if the other end (source or target)
     *            is also moved. In this case, there is nothing to do for the
     *            last segment of oblique and rectilinear edges. If empty all
     *            parts of diagram are considered as moved (case of arrange all)
     * @param sourceMove
     *            true if the source of the <code>connectionEditPart</code> is
     *            moved, false if this is the target.
     * @return An optional command that computes the new bendpoints of the
     *         <code>connectionEditPart</code> if needed.
     */
    protected static Option<? extends Command> getBendpointsChangedCommand(TransactionalEditingDomain transactionalEditingDomain, Point moveDelta, ConnectionEditPart connectionEditPart,
            List<AbstractGraphicalEditPart> allMovedEditParts, boolean sourceMove) {
        Option<? extends Command> result = Options.newNone();
        Connection connectionFigure = connectionEditPart.getConnectionFigure();
        // Check that this connectionEditPart is orthogonal tree branch and is a
        // layout component
        ConnectionEditPartQuery connectionEditPartQuery = new ConnectionEditPartQuery(connectionEditPart);
        if (new ConnectionQuery(connectionFigure).isOrthogonalTreeBranch(connectionFigure.getPoints()) && connectionEditPartQuery.isLayoutComponent()) {
            CompoundCommand command = new CompoundCommand("Map GMF to Draw2D");

            SetConnectionAnchorsCommand setConnectionAnchorsCommand = new SetConnectionAnchorsCommand(transactionalEditingDomain, "Map GMF anchor to Draw2D anchor");
            setConnectionAnchorsCommand.setEdgeAdaptor(connectionEditPart);
            setConnectionAnchorsCommand.setNewSourceTerminal(((INodeEditPart) connectionEditPart.getSource()).mapConnectionAnchorToTerminal(connectionFigure.getSourceAnchor()));
            setConnectionAnchorsCommand.setNewTargetTerminal(((INodeEditPart) connectionEditPart.getTarget()).mapConnectionAnchorToTerminal(connectionFigure.getTargetAnchor()));
            command.add(new ICommandProxy(setConnectionAnchorsCommand));

            SetConnectionBendpointsAccordingToDraw2DCommand setConnectionBendpointsCommand = new SetConnectionBendpointsAccordingToDraw2DCommand(transactionalEditingDomain);
            setConnectionBendpointsCommand.setLabel("Map GMF points to Draw2D points");
            setConnectionBendpointsCommand.setSourceMove(sourceMove);
            setConnectionBendpointsCommand.setMoveDelta(new PrecisionPoint(moveDelta));
            setConnectionBendpointsCommand.setEdgeAdapter(connectionEditPart);
            command.add(new ICommandProxy(setConnectionBendpointsCommand));
            result = Options.newSome(command);
        } else if (connectionEditPartQuery.isEdgeWithObliqueRoutingStyle() || connectionEditPartQuery.isEdgeWithRectilinearRoutingStyle()) {
            if (!allMovedEditParts.isEmpty()) {
                if ((sourceMove && !allMovedEditParts.contains(connectionEditPart.getTarget())) || (!sourceMove && !allMovedEditParts.contains(connectionEditPart.getSource()))) {
                    SetConnectionBendpointsAccordingToExtremityMoveCommmand setConnectionBendpointsCommand = new SetConnectionBendpointsAccordingToExtremityMoveCommmand(transactionalEditingDomain);
                    setConnectionBendpointsCommand.setSourceMove(sourceMove);
                    setConnectionBendpointsCommand.setMoveDelta(new PrecisionPoint(moveDelta));
                    setConnectionBendpointsCommand.setEdgeAdapter(connectionEditPart);
                    result = Options.newSome(new ICommandProxy(setConnectionBendpointsCommand));
                }
            }

            if (result.some()) {
                CompoundCommand command = new CompoundCommand("Map GMF to Draw2D");
                // Reset the connection anchor source and target considering it
                // can be wrongly modified by the arrange selection (see
                // ArrangeSelectionLayoutProvider.layoutEditParts(List,
                // IAdaptable) and previous comment in changeBendpointsOfEdges
                // for more details)
                SetConnectionAnchorsCommand setConnectionAnchorsCommand = new SetConnectionAnchorsCommand(transactionalEditingDomain, StringStatics.BLANK);
                setConnectionAnchorsCommand.setEdgeAdaptor(connectionEditPart);
                setConnectionAnchorsCommand.setNewSourceTerminal(((INodeEditPart) connectionEditPart.getSource()).mapConnectionAnchorToTerminal(connectionFigure.getSourceAnchor()));
                setConnectionAnchorsCommand.setNewTargetTerminal(((INodeEditPart) connectionEditPart.getTarget()).mapConnectionAnchorToTerminal(connectionFigure.getTargetAnchor()));
                command.add(new ICommandProxy(setConnectionAnchorsCommand));
                command.add(result.get());
                result = Options.newSome(command);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#createSelectionHandles()
     */
    @Override
    protected List createSelectionHandles() {
        List selectionHandles = super.createSelectionHandles();
        for (Object selectionHandle : selectionHandles) {
            // For each created MoveHandle
            if (selectionHandle instanceof MoveHandle) {
                // We set a drag tracker that will not cause the duplication of
                // graphical elements when holding "Ctrl" key down and moving an
                // element
                ((MoveHandle) selectionHandle).setDragTracker(new NoCopyDragEditPartsTrackerEx(getHost()));
            }
        }
        return selectionHandles;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#getResizeCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        Command result = UnexecutableCommand.INSTANCE;

        boolean valid = true;
        ResizeValidator resizeValidator = new ResizeValidator(request);
        valid = resizeValidator.validate();

        if (valid) {
            ICommand solution = buildResizeCommand(request);
            if (solution != null) {
                result = new ICommandProxy(solution);
            }
        }
        return result;
    }

    /**
     * Build a specific command from the request that resize the edit part as
     * the normal command and change the location of children if needed:
     * <UL>
     * <LI>border nodes: to avoid an unexpected change of side</LI>
     * <LI>children nodes (container or not): The GMF coordinates of these nodes
     * are moved in order to keep these nodes at the same location graphically
     * (on screen). The GMF coordinates of these nodes are relative to its
     * parent.</LI>
     * </UL>
     * 
     * @param request
     *            the resize request
     * @return <code>null</code> or a Command
     */
    private ICommand buildResizeCommand(ChangeBoundsRequest request) {
        ICommand result;
        Command cmd = super.getResizeCommand(request);
        if (cmd == null) {
            result = null;
        } else {
            GraphicalEditPart hostPart = (GraphicalEditPart) getHost();
            TransactionalEditingDomain editingDomain = hostPart.getEditingDomain();
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, cmd.getLabel());
            ctc.add(new CommandProxy(cmd));
            RequestQuery rq = new RequestQuery(request);
            boolean keepSameAbsoluteLocation = false;
            if (rq.isResizeFromTop() || rq.isResizeFromLeft() || request.isCenteredResize()) {
                Object childrenMoveModeExtendedData = request.getExtendedData().get(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY);
                keepSameAbsoluteLocation = (childrenMoveModeExtendedData == null && SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE)
                        || (childrenMoveModeExtendedData != null && ((Boolean) childrenMoveModeExtendedData).booleanValue());
                if (keepSameAbsoluteLocation) {
                    addChildrenAdjustmentCommands(hostPart, ctc, request);
                }
            }

            if (hostPart instanceof IBorderedShapeEditPart) {
                addBorderChildrenAdjustmentCommands(hostPart, ctc, request, keepSameAbsoluteLocation);
            }
            if (ctc.size() == 1) {
                result = new CommandProxy(cmd);
            } else {
                result = ctc;
            }
        }
        return result;
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
    private void addChildrenAdjustmentCommands(GraphicalEditPart resizedPart, CompositeTransactionalCommand cc, ChangeBoundsRequest cbr) {
        PrecisionPoint delta = new PrecisionPoint(cbr.getMoveDelta().getNegated());
        GraphicalHelper.applyInverseZoomOnPoint(resizedPart, delta);
        DNodeContainerViewNodeContainerCompartmentEditPart compartment = Iterables
                .getFirst(Iterables.filter(resizedPart.getChildren(), DNodeContainerViewNodeContainerCompartmentEditPart.class), null);
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
    private void addBorderChildrenAdjustmentCommands(GraphicalEditPart resizedPart, CompositeTransactionalCommand cc, final ChangeBoundsRequest cbr, boolean keepSameAbsoluteLocation) {
        RequestQuery rq = new RequestQuery(cbr);
        Rectangle logicalDelta = rq.getLogicalDelta();
        EditPartQuery resizedPartQuery = new EditPartQuery(resizedPart);
        if (rq.isResizeFromTop() || rq.isResizeFromBottom()) {
            int verticalSizeDelta = logicalDelta.height;

            // The border nodes of the bottom side must be shift to stay on
            // the bottom side.
            List<Node> childrenToMove = resizedPartQuery.getBorderedNodes(PositionConstants.SOUTH);
            if (!childrenToMove.isEmpty()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(childrenToMove, verticalSizeDelta, PositionConstants.VERTICAL)));
            }
            // The border nodes of the east and west side must eventually be
            // shift to stay in the parent bounds.
            Map<Node, Integer> childrenToMoveWithDelta = resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.EAST, verticalSizeDelta);
            childrenToMoveWithDelta.putAll(resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.WEST, verticalSizeDelta));
            for (Entry<Node, Integer> entry : childrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue().intValue(),
                        PositionConstants.VERTICAL)));
            }

            if (keepSameAbsoluteLocation && (rq.isResizeFromTop() || cbr.isCenteredResize())) {
                if (cbr.isCenteredResize()) {
                    verticalSizeDelta = verticalSizeDelta + logicalDelta.y;
                }
                // The border nodes of the west and east sides must be shift to
                // stay at the same absolute location (except if they have
                // already moved to stay in the parent bounds).
                List<Node> borderNodes = resizedPartQuery.getBorderedNodes(PositionConstants.WEST);
                borderNodes.addAll(resizedPartQuery.getBorderedNodes(PositionConstants.EAST));
                borderNodes.removeAll(childrenToMoveWithDelta.keySet());
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(borderNodes, verticalSizeDelta, PositionConstants.VERTICAL)));
            }

        }
        if (rq.isResizeFromRight() || rq.isResizeFromLeft()) {
            int horizontalSizeDelta = logicalDelta.width;
            // The border node of the east size must be shift to stay on the
            // east side.
            List<Node> childrenToMove = resizedPartQuery.getBorderedNodes(PositionConstants.EAST);
            if (!childrenToMove.isEmpty()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(childrenToMove, horizontalSizeDelta, PositionConstants.HORIZONTAL)));
            }
            // The border nodes of the north or south size must eventually be
            // shift to stay in the parent bounds.
            Map<Node, Integer> childrenToMoveWithDelta = resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.NORTH, horizontalSizeDelta);
            childrenToMoveWithDelta.putAll(resizedPartQuery.getBorderedNodesToMoveWithDelta(PositionConstants.SOUTH, horizontalSizeDelta));
            for (Entry<Node, Integer> entry : childrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue().intValue(),
                        PositionConstants.HORIZONTAL)));
            }

            if (keepSameAbsoluteLocation && (rq.isResizeFromLeft() || cbr.isCenteredResize())) {
                if (cbr.isCenteredResize()) {
                    horizontalSizeDelta = horizontalSizeDelta + logicalDelta.x;
                }
                // The border nodes of the north and south sides must be shift
                // to stay at the same absolute location (except if they have
                // already moved to stay in the parent bounds).
                List<Node> borderNodes = resizedPartQuery.getBorderedNodes(PositionConstants.NORTH);
                borderNodes.addAll(resizedPartQuery.getBorderedNodes(PositionConstants.SOUTH));
                borderNodes.removeAll(childrenToMoveWithDelta.keySet());
                cc.compose(CommandFactory.createICommand(cc.getEditingDomain(), new ShiftDirectBorderedNodesOperation(borderNodes, horizontalSizeDelta, PositionConstants.HORIZONTAL)));
            }
        }
    }
}
