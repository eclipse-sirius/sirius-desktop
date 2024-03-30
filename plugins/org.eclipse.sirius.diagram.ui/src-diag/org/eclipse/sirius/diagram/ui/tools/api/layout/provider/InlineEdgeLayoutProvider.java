/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.layout.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.diagram.ui.requests.SetAllBendpointRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.AbstractEdgeViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingHint;

import com.google.common.collect.Iterables;

/**
 * <p>
 * This provider layouts edge "inline" along nodes. The client can choose the
 * side of the node to use ({@link PositionConstants}), and the start position
 * of the line.
 * <p>
 * <p>
 * Here are the possible sides that can be used :
 * <ul>
 * <li>{@link PositionConstants#NORTH} : the top of the node.</li>
 * <li>{@link PositionConstants#WEST} : the left of the node.</li>
 * <li>{@link PositionConstants#EAST} : the right of the node.</li>
 * <li>{@link PositionConstants#SOUTH} : the bottom of the node.</li>
 * 
 * TODOYMO implements
 * <li>{@link PositionConstants#NSEW} : all sides of the node.</li>
 * <li>{@link PositionConstants#SOUTH_EAST},
 * {@link PositionConstants#SOUTH_WEST}, {@link PositionConstants#NORTH_EAST},
 * {@link PositionConstants#NORTH_WEST} : the combination of the two positions.</li>
 * END TODOYMO
 * <li>any other combination : compute the better side according to the source
 * and target nodes.</li>
 * </ul>
 * </p>
 * <p>
 * Here are the possible start positions :
 * <ul>
 * <li>{@link PositionConstants#TOP}</li>
 * <li>{@link PositionConstants#BOTTOM}</li>
 * <li>{@link PositionConstants#RIGHT}</li>
 * <li>{@link PositionConstants#LEFT}</li>
 * <li>{@link PositionConstants#CENTER} (horizontal)</li>
 * <li>{@link PositionConstants#MIDDLE} (vertical)</li>
 * </ul>
 * </p>
 * <p>
 * The two values <code>changeNodeHeight</code> and
 * <code>changeNodeWitdth</code> indicate if this layout provider can change the
 * dimension of nodes.
 * </p>
 * 
 * FIXME to terminate.
 * 
 * @author ymortier
 */
public class InlineEdgeLayoutProvider extends DefaultLayoutProvider {
    /** The default padding. */
    private static final Insets DEFAULT_PADDING = new Insets(30, 30, 10, 30);

    /**
     * Map each Connection with its {@link MoveEdgeDescriptor} instance.
     */
    protected Map connectionsToMoveEdgeDescriptor = new HashMap();

    /** The side to use. */
    private int side;

    /** The position of the first edge. */
    private int start;

    /** <code>true</code> if the height of nodes can be changed. */
    private boolean changeNodeHeight;

    /** <code>true</code> if the width of nodes can be changed. */
    private boolean changeNodeWidth;

    /** The padding. */
    private Insets paddings = InlineEdgeLayoutProvider.DEFAULT_PADDING;

    /**
     * The alignment ({@link PositionConstants#HORIZONTAL} or
     * {@link PositionConstants#VERTICAL} or {@link PositionConstants#NONE}).
     * Default is {@link PositionConstants#NONE}
     */
    private int alignment = PositionConstants.NONE;

    /**
     * Set the side to use.
     * 
     * @param side
     *            the side to use.
     */
    public void setSide(final int side) {
        this.side = side;
    }

    /**
     * Return the side to use.
     * 
     * @return the side to use.
     */
    public int getSide() {
        return side;
    }

    /**
     * Set the start position.
     * 
     * @param start
     *            the start position.
     */
    public void setStart(final int start) {
        this.start = start;
    }

    /**
     * Return the start position.
     * 
     * @return the start position.
     */
    public int getStart() {
        return start;
    }

    /**
     * <code>true</code> if the height of nodes can be changed.
     * 
     * @param changeNodeHeight
     *            <code>true</code> if the height of nodes can be changed.
     */
    public void setChangeNodeHeight(final boolean changeNodeHeight) {
        this.changeNodeHeight = changeNodeHeight;
    }

    /**
     * <code>true</code> if the width of nodes can be changed.
     * 
     * @param changeNodeWidth
     *            <code>true</code> if the width of nodes can be changed.
     */
    public void setChangeNodeWidth(final boolean changeNodeWidth) {
        this.changeNodeWidth = changeNodeWidth;
    }

    /**
     * Return <code>true</code> if the height of nodes can be changed.
     * 
     * @return <code>true</code> if the height of nodes can be changed.
     */
    public boolean isChangeNodeHeight() {
        return changeNodeHeight;
    }

    /**
     * Return <code>true</code> if the width of nodes can be changed.
     * 
     * @return <code>true</code> if the width of nodes can be changed.
     */
    public boolean isChangeNodeWidth() {
        return changeNodeWidth;
    }

    /**
     * Set the paddings.
     * 
     * @param paddings
     *            the paddings.
     */
    public void setPaddings(final Insets paddings) {
        if (paddings != null) {
            this.paddings = paddings;
        } else {
            this.paddings = InlineEdgeLayoutProvider.DEFAULT_PADDING;
        }
    }

    /**
     * Return the paddings.
     * 
     * @return the paddings.
     */
    public Insets getPaddings() {
        return paddings;
    }

    /**
     * Return the alignment.
     * 
     * @return the alignment.
     */
    public int getAlignment() {
        return alignment;
    }

    /**
     * Set the alignment. Possible alignments are :
     * <ul>
     * <li> {@link PositionConstants#HORIZONTAL} : start.x = end.x</li>
     * <li> {@link PositionConstants#VERTICAL} : start.y = end.y</li>
     * <li> {@link PositionConstants#NONE}</li>
     * </ul>
     * 
     * @param alignment
     *            the alignment.
     */
    public void setAlignment(final int alignment) {
        this.alignment = alignment;
        switch (this.alignment) {
        case PositionConstants.VERTICAL:
        case PositionConstants.HORIZONTAL:
        case PositionConstants.NONE:
            break;
        default:
            this.alignment = PositionConstants.NONE;
        }
    }

    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        final CompoundCommand cc = new CompoundCommand();

        this.connectionsToMoveEdgeDescriptor.clear();

        final Map<EditPart, List<EditPart>> containerToChildren = this.split(selectedObjects);
        final Iterator<Entry<EditPart, List<EditPart>>> iterLayouts = containerToChildren.entrySet().iterator();
        while (iterLayouts.hasNext()) {
            final Entry<EditPart, List<EditPart>> currentLayout = iterLayouts.next();
            final IGraphicalEditPart container = (IGraphicalEditPart) currentLayout.getKey();
            final List<EditPart> children = currentLayout.getValue();
            DefaultLayoutProvider.retainType(children, ConnectionEditPart.class);
            final AbstractEdgeViewOrdering viewOrdering = ViewOrderingHint.getInstance().consumeEdgeViewOrdering(container.getNotationView());
            final Command command = this.createChangeBoundsCommand(children, viewOrdering);
            if (command != null && command.canExecute()) {
                cc.add(command);
            }
        }
        connectionsToMoveEdgeDescriptor.clear();
        return cc;
    }

    /**
     * Create the command that changes the bounds of the specified connections.
     * 
     * @param connections
     *            the connections to layout (instances of
     *            {@link ConnectionEditPart}).
     * @param viewOrdering
     *            the view ordering.
     * @return the command that changes the bounds of the specified connections.
     */
    protected Command createChangeBoundsCommand(final List connections, final AbstractEdgeViewOrdering viewOrdering) {
        final CompoundCommand cc = new CompoundCommand();

        final Iterator<EditPart> iterNodes = this.getNodesEditPart(connections).iterator();

        while (iterNodes.hasNext()) {
            final IGraphicalEditPart currentEditPart = (IGraphicalEditPart) iterNodes.next();
            final List<org.eclipse.gef.ConnectionEditPart> connectionsToInit = new ArrayList<>(currentEditPart.getSourceConnections().size() + currentEditPart.getTargetConnections().size());
            connectionsToInit.addAll(currentEditPart.getSourceConnections());
            connectionsToInit.addAll(currentEditPart.getTargetConnections());
            DefaultLayoutProvider.retainType(connections, ConnectionEditPart.class);
            this.initEdgesStep(currentEditPart, viewOrdering, connectionsToInit);
        }
        //
        // Manage alignement !
        this.align(viewOrdering);
        //
        // Create commands.
        final Iterator iterDescriptors = this.connectionsToMoveEdgeDescriptor.values().iterator();
        while (iterDescriptors.hasNext()) {
            final MoveEdgeDescriptor currentDescriptor = (MoveEdgeDescriptor) iterDescriptors.next();
            final Command command = this.createChangeEdgeBoundsCommand(currentDescriptor);
            if (command != null && command.canExecute()) {
                cc.add(command);
            }
        }

        return cc;
    }

    /**
     * Return all edit parts that are a source or a target of one connection of
     * <code>connections</code>.
     * 
     * @param connections
     *            the connections.
     * @return all edit parts that are a source or a target of one connection of
     *         <code>connections</code>.
     */
    protected Set<EditPart> getNodesEditPart(final List<ConnectionEditPart> connections) {
        final Set<EditPart> result = new HashSet<EditPart>();
        final Iterator<ConnectionEditPart> iterConnections = connections.iterator();
        while (iterConnections.hasNext()) {
            final ConnectionEditPart connectionEditPart = iterConnections.next();
            if (connectionEditPart.getSource() instanceof IGraphicalEditPart) {
                result.add(connectionEditPart.getSource());
            }
            if (connectionEditPart.getTarget() instanceof IGraphicalEditPart) {
                result.add(connectionEditPart.getTarget());
            }
        }
        return result;
    }

    /**
     * Init the {@link MoveEdgeDescriptor} of the specified connector.
     * 
     * @param connector
     *            the edit part that is the source or the target of connections
     *            to init.
     * @param ordering
     *            the ordering.
     * @param connections
     *            the connections to layout for this connector (instances of
     *            {@link ConnectionEditPart}).
     */
    protected void initEdgesStep(final IGraphicalEditPart connector, final AbstractEdgeViewOrdering ordering, final List<? extends org.eclipse.gef.ConnectionEditPart> connections) {
        List<ConnectionEditPart> gmfConnections = connections.stream()
                .filter(ConnectionEditPart.class::isInstance)
                .map(ConnectionEditPart.class::cast)
                .toList();
        final Map<View, ConnectionEditPart> viewsToConnections = this.getViews(gmfConnections);
        ordering.setConnector(connector.getNotationView());
        ordering.setViews(viewsToConnections.keySet());
        final Iterator<View> iterViews = ordering.getSortedViews().iterator();
        int step = 0;
        while (iterViews.hasNext()) {
            final View currentView = iterViews.next();
            final ConnectionEditPart currentConnection = viewsToConnections.get(currentView);

            MoveEdgeDescriptor moveEdgeDescriptor = (MoveEdgeDescriptor) this.connectionsToMoveEdgeDescriptor.get(currentConnection);
            if (moveEdgeDescriptor == null) {
                // The connection has no MoveEdgeDescriptor.
                // Let's create one.
                moveEdgeDescriptor = new MoveEdgeDescriptor(currentConnection);
                this.connectionsToMoveEdgeDescriptor.put(currentConnection, moveEdgeDescriptor);
            }

            if (connector.getSourceConnections().contains(currentConnection)) {
                // source
                moveEdgeDescriptor.setSourceStep(step);
            } else {
                // target
                moveEdgeDescriptor.setTargetStep(step);
            }
            step++;
        }
    }

    @Override
    public boolean provides(final IOperation operation) {
        return false;
    }

    /**
     * Create the command that changes the bounds of the specified edge.
     * 
     * @param moveEdgeDescriptor
     *            the instance that describes how to move the edge.
     * @return the created command
     */
    protected Command createChangeEdgeBoundsCommand(final MoveEdgeDescriptor moveEdgeDescriptor) {
        // the connection edit part.
        final ConnectionEditPart connectionEditPart = moveEdgeDescriptor.getConnectionEditPart();

        final Point sourcePoint = moveEdgeDescriptor.getSourceLocation(true);
        final Point targetPoint = moveEdgeDescriptor.getTargetLocation(true);

        final PointList points = new PointList(2);
        points.addPoint(sourcePoint);
        points.addPoint(targetPoint);

        final Point ref1 = new Point(0, 0);
        final Point ref2 = new Point(0, 0);

        final FreeformViewport viewport = this.getFreeformViewport(moveEdgeDescriptor.connectionEditPart.getConnectionFigure().getSourceAnchor().getOwner());
        final Point delta = viewport.getViewLocation();

        // the zoom.
        double scale = 1.0;
        if (moveEdgeDescriptor.getConnectionEditPart().getRoot() instanceof DiagramRootEditPart) {
            final ZoomManager zoomManager = ((DiagramRootEditPart) moveEdgeDescriptor.getConnectionEditPart().getRoot()).getZoomManager();
            scale = zoomManager.getZoom();
        }

        if (moveEdgeDescriptor.getConnectionEditPart().getSource() instanceof IGraphicalEditPart) {
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) moveEdgeDescriptor.getConnectionEditPart().getSource();
            ref1.setY(this.getBounds(graphicalEditPart).getSize().height / 2);
        }
        if (moveEdgeDescriptor.getConnectionEditPart().getTarget() instanceof IGraphicalEditPart) {
            final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) moveEdgeDescriptor.getConnectionEditPart().getTarget();
            ref2.setY(this.getBounds(graphicalEditPart).getSize().height / 2);
        }

        final Point source = connectionEditPart.getConnectionFigure().getSourceAnchor().getReferencePoint().getTranslated(delta);
        source.setX((int) ((double) source.x * (double) 1 / scale));
        source.setY((int) ((double) source.y * (double) 1 / scale));

        final Point target = connectionEditPart.getConnectionFigure().getTargetAnchor().getReferencePoint().getTranslated(delta);
        target.setX((int) ((double) target.x * (double) 1 / scale));
        target.setY((int) ((double) target.y * (double) 1 / scale));

        final SetAllBendpointRequest setAllBendpointRequest = new SetAllBendpointRequest(RequestConstants.REQ_SET_ALL_BENDPOINT, points, source, target);

        final Command command = this.buildCommandWrapper(setAllBendpointRequest, connectionEditPart);
        if (command != null && command.canExecute()) {
            // this.getViewsToChangeBoundsRequest().put(connectionEditPart.getNotationView(),
            // bendpointRequest);
        }
        return command;
    }

    /**
     * Returns the {@link FreeformViewport} that owned this figure.
     * 
     * @return the {@link FreeformViewport} that owned this figure.
     */
    private FreeformViewport getFreeformViewport(final IFigure figure) {
        IFigure current = figure;
        while (!(current instanceof FreeformViewport) && current != null) {
            current = current.getParent();
        }
        return (FreeformViewport) current;
    }

    /**
     * Create and return a command that resize connectors.
     * 
     * @return and return a command that resize connectors.
     */
    protected Command createResizeConnectorsCommand() {
        final CompoundCommand cc = new CompoundCommand();
        return cc;
    }

    /**
     * Describe how an edge should be located.
     * 
     * @author ymortier
     */
    protected class MoveEdgeDescriptor {

        /** The edge to move. */
        private ConnectionEditPart connectionEditPart;

        /** The source step. */
        private int sourceStep;

        /** The target step. */
        private int targetStep;

        /**
         * Create a new {@link MoveEdgeDescriptor}.
         * 
         * @param connectionEditPart
         *            the connection.
         */
        public MoveEdgeDescriptor(final ConnectionEditPart connectionEditPart) {
            this.connectionEditPart = connectionEditPart;
        }

        /**
         * Return the source step.
         * 
         * @return the source step.
         */
        public int getSourceStep() {
            return sourceStep;
        }

        /**
         * Return the target step.
         * 
         * @return the target step.
         */
        public int getTargetStep() {
            return targetStep;
        }

        /**
         * Define the source step.
         * 
         * @param sourceStep
         *            the source step.
         */
        public void setSourceStep(final int sourceStep) {
            this.sourceStep = sourceStep;
        }

        /**
         * Define the target step.
         * 
         * @param targetStep
         *            the target step.
         */
        public void setTargetStep(final int targetStep) {
            this.targetStep = targetStep;
        }

        /**
         * Return the connection edit part.
         * 
         * @return the connection edit part.
         */
        public ConnectionEditPart getConnectionEditPart() {
            return connectionEditPart;
        }

        /**
         * Return the location of the source of this edge according to its
         * <code>sourceStep</code>.
         * 
         * @param relative
         *            if <code>true</code> then the returned location is
         *            relative to the source figure, the location is an absolute
         *            location otherwise.
         * @return the location of the source of this edge according to its
         *         <code>sourceStep</code>.
         */
        public Point getSourceLocation(final boolean relative) {
            final int pos = InlineEdgeLayoutProvider.this.computePos(this.sourceStep);
            final Rectangle sourceBounds = getBounds((IGraphicalEditPart) connectionEditPart.getSource());
            final Point sourceLocation = InlineEdgeLayoutProvider.this.computePrimaryPoint(pos, sourceBounds);
            if (!relative) {
                sourceLocation.translate(sourceBounds.getLocation());
            }
            return sourceLocation;
        }

        /**
         * Return the location of the target of this edge according to its
         * <code>targetStep</code>.
         * 
         * @param relative
         *            if <code>true</code> then the returned location is
         *            relative to the target figure, the location is an absolute
         *            location otherwise.
         * @return the location of the target of this edge according to its
         *         <code>sourceStep</code>.
         */
        public Point getTargetLocation(final boolean relative) {
            final int pos = InlineEdgeLayoutProvider.this.computePos(this.targetStep);
            final Rectangle targetBounds = getBounds((IGraphicalEditPart) connectionEditPart.getTarget());
            final Point targetLocation = InlineEdgeLayoutProvider.this.computePrimaryPoint(pos, targetBounds);
            if (!relative) {
                targetLocation.translate(targetBounds.getLocation());
            }
            return targetLocation;
        }

        /**
         * Approximate the new <code>sourceStep</code> assuming the
         * <code>desiredLocation</code>.
         * 
         * @param desiredLocation
         *            the desired source location.
         * @param relative
         *            <code>true</code> if <code>desiredLocation</code> is
         *            relative to the source figure.
         */
        public void approximateSourceStep(final Point desiredLocation, final boolean relative) {
            final Rectangle sourceBounds = getBounds((IGraphicalEditPart) connectionEditPart.getSource());
            final Point location = desiredLocation.getCopy();
            if (!relative) {
                location.setX(location.x - sourceBounds.x);
                location.setY(location.y - sourceBounds.y);
            }
            final int pos = posFromPrimaryPoint(location, sourceBounds);
            this.sourceStep = stepFromPos(pos);
        }

        /**
         * Approximate the new <code>targetStep</code> assuming the
         * <code>desiredLocation</code>.
         * 
         * @param desiredLocation
         *            the desired target location.
         * @param relative
         *            <code>true</code> if <code>desiredLocation</code> is
         *            relative to the target figure.
         */
        public void approximateTargetStep(final Point desiredLocation, final boolean relative) {
            final Rectangle targetBounds = getBounds((IGraphicalEditPart) connectionEditPart.getTarget());
            final Point location = desiredLocation.getCopy();
            if (!relative) {
                location.setX(location.x - targetBounds.x);
                location.setY(location.y - targetBounds.y);
            }
            final int pos = posFromPrimaryPoint(location, targetBounds);
            this.targetStep = stepFromPos(pos);
        }

    }

    private int computePos(final int current) {

        int result = current;

        switch (this.side) {
        case PositionConstants.EAST:
        case PositionConstants.WEST:
        case PositionConstants.EAST_WEST:
            result = this.getPaddings().top + (this.getPaddings().top + this.getPaddings().bottom) * current;
            break;
        case PositionConstants.NORTH:
        case PositionConstants.SOUTH:
        case PositionConstants.NORTH_SOUTH:
            result = this.getPaddings().left + (this.getPaddings().left + this.getPaddings().right) * current;
            break;
        default:
            break;
        }
        return result;
    }

    private int stepFromPos(final int pos) {

        int result = pos;

        switch (this.side) {
        case PositionConstants.EAST:
        case PositionConstants.WEST:
        case PositionConstants.EAST_WEST:
            result = (int) ((pos - this.getPaddings().top) / ((double) this.getPaddings().top + this.getPaddings().bottom));
            break;
        case PositionConstants.NORTH:
        case PositionConstants.SOUTH:
        case PositionConstants.NORTH_SOUTH:
            result = (int) ((pos - this.getPaddings().left) / ((double) this.getPaddings().left + this.getPaddings().right));
            break;
        default:
            break;
        }
        return result;
    }

    private Point computePrimaryPoint(final int pos, final Rectangle connectorBounds) {
        final Point result = new Point();
        switch (this.getSide()) {
        case PositionConstants.EAST:
            result.setX(0);
            switch (this.getStart()) {
            case PositionConstants.BOTTOM:
                result.setY(connectorBounds.height - pos);
                break;
            case PositionConstants.MIDDLE:
                result.setY(connectorBounds.height / 2 + pos);
                break;
            default:
                result.setY(pos);
            }
            break;
        case PositionConstants.WEST:
        case PositionConstants.EAST_WEST:
            result.setX(connectorBounds.width);
            switch (this.getStart()) {
            case PositionConstants.BOTTOM:
                result.setY(connectorBounds.height - pos);
                break;
            case PositionConstants.MIDDLE:
                result.setY(connectorBounds.height / 2 + pos);
                break;
            default:
                result.setY(pos);
                break;
            }
            break;
        case PositionConstants.NORTH:
            result.setY(0);
            switch (this.getStart()) {
            case PositionConstants.RIGHT:
                result.setX(connectorBounds.width - pos);
                break;
            case PositionConstants.CENTER:
                result.setX(connectorBounds.width / 2 + pos);
                break;
            default:
                break;
            }
            break;
        case PositionConstants.SOUTH:
        case PositionConstants.NORTH_SOUTH:
            result.setY(connectorBounds.height);
            switch (this.getStart()) {
            case PositionConstants.RIGHT:
                result.setX(connectorBounds.width - pos);
                break;
            case PositionConstants.CENTER:
                result.setX(connectorBounds.width / 2 + pos);
                break;
            default:
                break;
            }
            break;
        default:
            break;
        }
        return result;
    }

    private int posFromPrimaryPoint(final Point pt, final Rectangle connectorBounds) {

        int result = 0;

        switch (this.getSide()) {
        case PositionConstants.EAST:
        case PositionConstants.WEST:
        case PositionConstants.EAST_WEST:
            switch (this.getStart()) {
            case PositionConstants.BOTTOM:
                result = connectorBounds.height - pt.y;
                break;
            case PositionConstants.MIDDLE:
                result = pt.y - connectorBounds.height / 2;
                break;
            default:
                result = pt.y;
                break;
            }
            break;
        case PositionConstants.NORTH:
        case PositionConstants.SOUTH:
        case PositionConstants.NORTH_SOUTH:
            switch (this.getStart()) {
            case PositionConstants.RIGHT:
                result = connectorBounds.width - pt.x;
                break;
            case PositionConstants.CENTER:
                result = pt.x - connectorBounds.width / 2;
                break;
            default:
                result = pt.x;
                break;
            }
            break;
        default:
            break;
        }
        return result;
    }

    /**
     * Align the edge according to the specified <code>alignment</code> value.
     * 
     * @param viewOrdering
     *            the edge view ordering
     */
    protected void align(final AbstractEdgeViewOrdering viewOrdering) {
        switch (this.getAlignment()) {
        case PositionConstants.HORIZONTAL:
            this.horizontalAlign(viewOrdering);
            break;
        case PositionConstants.VERTICAL:
            this.verticalAlign(viewOrdering);
            break;
        default:
            // do nothing.
        }
    }

    // start.y = end.y
    private void verticalAlign(final AbstractEdgeViewOrdering viewOrdering) {
        final Map connectorsToStepRearrangeMin = this.initConnectorsToStepRearrangeMin();
        boolean again = true;
        while (again) {
            again = false;
            final Iterator iterMoveDescriptors = this.connectionsToMoveEdgeDescriptor.values().iterator();
            while (iterMoveDescriptors.hasNext()) {
                final MoveEdgeDescriptor currentDescriptor = (MoveEdgeDescriptor) iterMoveDescriptors.next();
                final Point sourcePoint = currentDescriptor.getSourceLocation(false);
                final Point targetPoint = currentDescriptor.getTargetLocation(false);
                if (sourcePoint.y < targetPoint.y - 29 || sourcePoint.y > targetPoint.y + 29) {
                    if (sourcePoint.y < targetPoint.y) {
                        sourcePoint.setY(targetPoint.y);
                        currentDescriptor.approximateSourceStep(sourcePoint, false);
                        final IGraphicalEditPart connector = (IGraphicalEditPart) currentDescriptor.getConnectionEditPart().getSource();
                        final int connectorStart = ((Integer) connectorsToStepRearrangeMin.get(connector.getNotationView())).intValue();
                        final int result = this.rearrangeConnector(connector, connectorStart, viewOrdering);
                        connectorsToStepRearrangeMin.put(connector.getNotationView(), Integer.valueOf(result));
                        if (result != connectorStart) {
                            again = true;
                        }
                    } else {
                        targetPoint.setY(sourcePoint.y);
                        currentDescriptor.approximateTargetStep(targetPoint, false);
                        final IGraphicalEditPart connector = (IGraphicalEditPart) currentDescriptor.getConnectionEditPart().getTarget();
                        final int connectorStart = ((Integer) connectorsToStepRearrangeMin.get(connector.getNotationView())).intValue();
                        final int result = this.rearrangeConnector(connector, connectorStart, viewOrdering);
                        connectorsToStepRearrangeMin.put(connector.getNotationView(), Integer.valueOf(result));
                        if (result != connectorStart) {
                            again = true;
                        }
                    }
                }
            }
        }
    }

    // start.y = end.y
    private void horizontalAlign(final AbstractEdgeViewOrdering viewOrdering) {
        boolean again = true;
        while (again) {
            again = false;
            final Iterator iterMoveDescriptors = this.connectionsToMoveEdgeDescriptor.values().iterator();
            while (iterMoveDescriptors.hasNext()) {
                final MoveEdgeDescriptor currentDescriptor = (MoveEdgeDescriptor) iterMoveDescriptors.next();
                final Point sourcePoint = currentDescriptor.getSourceLocation(false);
                final Point targetPoint = currentDescriptor.getTargetLocation(false);
                if (sourcePoint.x < targetPoint.x - 10 || sourcePoint.x > targetPoint.x + 10) {
                    if (sourcePoint.x < targetPoint.x) {
                        sourcePoint.setX(targetPoint.x);
                        currentDescriptor.approximateSourceStep(sourcePoint, false);
                        again = true;
                    } else {
                        targetPoint.setX(sourcePoint.x);
                        currentDescriptor.approximateTargetStep(targetPoint, false);
                        again = true;
                    }

                }
            }
        }
    }

    private Map initConnectorsToStepRearrangeMin() {
        final Map result = new HashMap();
        final Iterator iterDescriptors = this.connectionsToMoveEdgeDescriptor.values().iterator();
        while (iterDescriptors.hasNext()) {
            final MoveEdgeDescriptor currentDescriptor = (MoveEdgeDescriptor) iterDescriptors.next();
            final ConnectionEditPart connectionEditPart = currentDescriptor.getConnectionEditPart();
            if (connectionEditPart.getSource() instanceof IGraphicalEditPart) {
                final View sourceConnector = ((IGraphicalEditPart) connectionEditPart.getSource()).getNotationView();
                if (!result.containsKey(sourceConnector)) {
                    result.put(sourceConnector, Integer.valueOf(0));
                }
            }
            if (connectionEditPart.getTarget() instanceof IGraphicalEditPart) {
                final View targetConnector = ((IGraphicalEditPart) connectionEditPart.getTarget()).getNotationView();
                if (!result.containsKey(targetConnector)) {
                    result.put(targetConnector, Integer.valueOf(0));
                }
            }
        }
        return result;
    }

    private int rearrangeConnector(final IGraphicalEditPart connector, final int connectorStart, final AbstractEdgeViewOrdering viewOrdering) {
        int result = connectorStart;
        final List<EditPart> connections = new ArrayList<>(connector.getSourceConnections().size() + connector.getTargetConnections().size());
        Iterables.addAll(connections, Iterables.filter(connector.getSourceConnections(), EditPart.class));
        Iterables.addAll(connections, Iterables.filter(connector.getTargetConnections(), EditPart.class));

        final Map<View, EditPart> views = this.getViews(connections);
        viewOrdering.setConnector(connector.getNotationView());
        viewOrdering.setViews(views.keySet());

        final List<View> sortedViews = viewOrdering.getSortedViews();

        final Iterator<View> iterViews = sortedViews.iterator();
        int current = 0;
        while (iterViews.hasNext()) {
            final View currentView = iterViews.next();
            final ConnectionEditPart connectionEditPart = (ConnectionEditPart) views.get(currentView);
            final MoveEdgeDescriptor descriptor = (MoveEdgeDescriptor) this.connectionsToMoveEdgeDescriptor.get(connectionEditPart);
            final boolean source = connectionEditPart.getSource().equals(connector);
            if (source) {
                // source
                int step = descriptor.getSourceStep();
                if (step < current) {
                    step = current;
                }
                if (step > current) {
                    if (result == connectorStart) {
                        result = step + 1;
                    }
                    current = step;
                }
                descriptor.setSourceStep(step);
            } else {
                // target
                int step = descriptor.getTargetStep();
                if (step < current) {
                    step = current;
                }
                if (step > current) {
                    if (result == connectorStart) {
                        result = step + 1;
                    }
                    current = step;
                }
                descriptor.setTargetStep(step);
            }
            current++;
        }
        return result;
    }
}
