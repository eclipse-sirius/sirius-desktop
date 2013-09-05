/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.graphical.edit.policies;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
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
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
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
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.Node;

import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.LabelPosition;
import org.eclipse.sirius.NodeStyle;
import org.eclipse.sirius.Style;
import org.eclipse.sirius.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.business.internal.operation.ShiftDirectBorderedNodesOperation;
import org.eclipse.sirius.diagram.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramNodeEditPart;
import org.eclipse.sirius.diagram.edit.internal.validators.ResizeValidator;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;

/**
 * The specific edit policy to redefine auto size.
 * 
 * @author ymortier
 */
public class AirResizableEditPolicy extends ResizableShapeEditPolicy {

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
        final ChangeBoundsRequest req = new ChangeBoundsRequest(REQ_MOVE_CHILDREN);
        req.setEditParts(getHost());

        req.setMoveDelta(request.getMoveDelta());
        req.setSizeDelta(request.getSizeDelta());
        req.setLocation(request.getLocation());
        req.setExtendedData(request.getExtendedData());
        if (getHost().getParent() != null) {
            return changeBendpointsOfTreeEdges(getHost(), getHost().getParent().getCommand(req), request.getMoveDelta());
        } else {
            return null;
        }
    }

    // CHECKSTYLE:OFF
    protected Command changeBendpointsOfTreeEdges(EditPart host, Command command, Point moveDelta) {
        CompoundCommand result = new CompoundCommand(command.getLabel());
        if (host instanceof AbstractGraphicalEditPart) {
            AbstractGraphicalEditPart graphicalEditPart = (AbstractGraphicalEditPart) host;
            final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(host.getModel());
            List<Object> sourceConnections = graphicalEditPart.getSourceConnections();
            for (int i = 0; i < sourceConnections.size(); i++) {
                if (sourceConnections.get(i) instanceof ConnectionEditPart) {
                    ConnectionEditPart connectionEditPart = (ConnectionEditPart) sourceConnections.get(i);
                    Option<? extends Command> optionalCommand = getBendpointsChangedCommand(transactionalEditingDomain, moveDelta, connectionEditPart, true);
                    if (optionalCommand.some()) {
                        if (result.isEmpty()) {
                            result.add(command);
                        }
                        result.add(optionalCommand.get());
                    }
                }
            }
            List<Object> targetConnections = graphicalEditPart.getTargetConnections();
            for (int i = 0; i < targetConnections.size(); i++) {
                if (targetConnections.get(i) instanceof ConnectionEditPart) {
                    ConnectionEditPart connectionEditPart = (ConnectionEditPart) targetConnections.get(i);
                    Option<? extends Command> optionalCommand = getBendpointsChangedCommand(transactionalEditingDomain, moveDelta, connectionEditPart, false);
                    if (optionalCommand.some()) {
                        if (result.isEmpty()) {
                            result.add(command);
                        }
                        result.add(optionalCommand.get());
                    }
                }
            }
        }
        if (result.isEmpty()) {
            return command;
        } else {
            return result;
        }
    }

    /**
     * @param host
     * @param command
     * @param moveDelta
     * @param result
     * @param connectionEditPart
     * @return
     */
    protected Option<? extends Command> getBendpointsChangedCommand(TransactionalEditingDomain transactionalEditingDomain, Point moveDelta, ConnectionEditPart connectionEditPart, boolean sourceMove) {
        Connection connectionFigure = connectionEditPart.getConnectionFigure();
        // Check that this connectionEditPart is orthogonal tree branch and is a
        // layout component
        if (new ConnectionQuery(connectionFigure).isOrthogonalTreeBranch(connectionFigure.getPoints()) && new ConnectionEditPartQuery(connectionEditPart).isLayoutComponent()) {
            CompoundCommand command = new CompoundCommand("Map GMF to Draw2D");

            SetConnectionAnchorsCommand setConnectionAnchorsCommand = new SetConnectionAnchorsCommand(transactionalEditingDomain, "Map GMF anchor to Draw2D anchor");
            setConnectionAnchorsCommand.setEdgeAdaptor(connectionEditPart);
            setConnectionAnchorsCommand.setNewSourceTerminal(((INodeEditPart) connectionEditPart.getSource()).mapConnectionAnchorToTerminal(connectionFigure.getSourceAnchor()));
            setConnectionAnchorsCommand.setNewTargetTerminal(((INodeEditPart) connectionEditPart.getTarget()).mapConnectionAnchorToTerminal(connectionFigure.getTargetAnchor()));
            command.add(new ICommandProxy(setConnectionAnchorsCommand));

            SetConnectionBendpointsAccordingToDraw2DCommand setConnectionBendpointsCommand = new SetConnectionBendpointsAccordingToDraw2DCommand(transactionalEditingDomain);
            setConnectionBendpointsCommand.setLabel("Map GMF points to Draw2D points");
            setConnectionBendpointsCommand.setSourceMove(sourceMove);
            setConnectionBendpointsCommand.setMoveDelta(moveDelta);
            setConnectionBendpointsCommand.setEdgeAdapter(connectionEditPart);
            command.add(new ICommandProxy(setConnectionBendpointsCommand));
            return Options.newSome(command);
        }
        return Options.newNone();
    }

    /**
     * getPointsFromConstraint Utility method retrieve the PointList equivalent
     * of the bendpoint constraint set in the Connection.
     * 
     * @param conn
     *            Connection to retrieve the constraint from.
     * @return PointList list of points that is the direct equivalent of the set
     *         constraint.
     */
    public PointList getPointsFromConstraint(Connection conn) {
        List bendpoints = (List) conn.getRoutingConstraint();
        if (bendpoints == null)
            return new PointList();

        PointList points = new PointList(bendpoints.size());
        for (int i = 0; i < bendpoints.size(); i++) {
            Bendpoint bp = (Bendpoint) bendpoints.get(i);
            points.addPoint(bp.getLocation());
        }

        straightenPoints(points, MapModeUtil.getMapMode(conn).DPtoLP(3));
        return points;
    }

    /**
     * straightenPoints This is a simpler version of the @see
     * updateIfNotRectilinear that simply ensures that the lines are horizontal
     * or vertical without any intelligence in terms of shortest distance around
     * a rectangle.
     * 
     * @param newLine
     *            PointList to check for rectilinear qualities and change if
     *            necessary.
     * @param tolerance
     *            int tolerance value by which points will be straightened in
     *            HiMetrics
     */
    static protected void straightenPoints(PointList newLine, int tolerance) {
        for (int i = 0; i < newLine.size() - 1; i++) {
            Point ptCurrent = newLine.getPoint(i);
            Point ptNext = newLine.getPoint(i + 1);

            int xDelta = Math.abs(ptNext.x - ptCurrent.x);
            int yDelta = Math.abs(ptNext.y - ptCurrent.y);

            if (xDelta < yDelta) {
                if (xDelta > tolerance)
                    return;
                ptNext.x = ptCurrent.x;
            } else {
                if (yDelta > tolerance)
                    return;
                ptNext.y = ptCurrent.y;
            }

            newLine.setPoint(ptNext, i + 1);
        }
    }

    // CHECKSTYLE:ON

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
     * Build a specific command from the request that resize the editpart as the
     * normal command and change the location of the borderedNodes if needed.
     * 
     * @param request
     *            request the resize request
     * @return <code>null</code> or a Command
     */
    private ICommand buildResizeCommand(ChangeBoundsRequest request) {
        Command cmd = super.getResizeCommand(request);
        if (cmd == null) {
            return null;
        }
        GraphicalEditPart hostPart = (GraphicalEditPart) getHost();
        TransactionalEditingDomain editingDomain = hostPart.getEditingDomain();
        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, cmd.getLabel());
        ctc.add(new CommandProxy(cmd));

        if (hostPart instanceof IBorderedShapeEditPart) {
            addChildrenAdjustmentCommands(hostPart, ctc, editingDomain, request);
        }
        return ctc;
    }

    /**
     * Add the needed commands to move the bordered nodes to the
     * CompositeTransactionalCommand.
     * 
     * @param resizedPart
     *            The part that has been resized (parent of the bordered nodes
     *            to move)
     * @param cc
     *            The current command that resize the parent part
     * @param editingDomain
     *            The editing domain use
     * @param cbr
     */
    private void addChildrenAdjustmentCommands(GraphicalEditPart resizedPart, CompositeTransactionalCommand cc, TransactionalEditingDomain editingDomain, final ChangeBoundsRequest cbr) {
        RequestQuery rq = new RequestQuery(cbr);
        Rectangle logicalDelta = rq.getLogicalDelta();
        if (rq.isResizeFromTop() || rq.isResizeFromBottom()) {
            int verticalSizeDelta = logicalDelta.height;
            // The bordered nodes of the bottom size must be shift to stay on
            // the bottom side.
            List<Node> childrenToMove = new EditPartQuery(resizedPart).getBorderedNodes(PositionConstants.SOUTH);
            if (!childrenToMove.isEmpty()) {
                cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDirectBorderedNodesOperation(childrenToMove, verticalSizeDelta, PositionConstants.VERTICAL)));
            }
            // The bordered nodes of the east or west size must eventually be
            // shift to stay in the parent bounds.
            Map<Node, Integer> eastChildrenToMoveWithDelta = new EditPartQuery(resizedPart).getBorderedNodesToMoveWithDelta(PositionConstants.EAST, verticalSizeDelta);
            for (Entry<Node, Integer> entry : eastChildrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue().intValue(),
                        PositionConstants.VERTICAL)));
            }
            Map<Node, Integer> westChildrenToMoveWithDelta = new EditPartQuery(resizedPart).getBorderedNodesToMoveWithDelta(PositionConstants.WEST, verticalSizeDelta);
            for (Entry<Node, Integer> entry : westChildrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue().intValue(),
                        PositionConstants.VERTICAL)));
            }
        }
        if (rq.isResizeFromRight() || rq.isResizeFromLeft()) {
            int horizontalSizeDelta = logicalDelta.width;
            // The bordered node of the east size must be shift to stay on the
            // east side.
            List<Node> childrenToMove = new EditPartQuery(resizedPart).getBorderedNodes(PositionConstants.EAST);
            if (!childrenToMove.isEmpty()) {
                cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDirectBorderedNodesOperation(childrenToMove, horizontalSizeDelta, PositionConstants.HORIZONTAL)));
            }
            // The bordered nodes of the north or south size must eventually be
            // shift to stay in the parent bounds.
            Map<Node, Integer> eastChildrenToMoveWithDelta = new EditPartQuery(resizedPart).getBorderedNodesToMoveWithDelta(PositionConstants.NORTH, horizontalSizeDelta);
            for (Entry<Node, Integer> entry : eastChildrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue().intValue(),
                        PositionConstants.HORIZONTAL)));
            }
            Map<Node, Integer> westChildrenToMoveWithDelta = new EditPartQuery(resizedPart).getBorderedNodesToMoveWithDelta(PositionConstants.SOUTH, horizontalSizeDelta);
            for (Entry<Node, Integer> entry : westChildrenToMoveWithDelta.entrySet()) {
                cc.compose(CommandFactory.createICommand(editingDomain, new ShiftDirectBorderedNodesOperation(Lists.newArrayList(entry.getKey()), entry.getValue().intValue(),
                        PositionConstants.HORIZONTAL)));
            }
        }
    }
}
