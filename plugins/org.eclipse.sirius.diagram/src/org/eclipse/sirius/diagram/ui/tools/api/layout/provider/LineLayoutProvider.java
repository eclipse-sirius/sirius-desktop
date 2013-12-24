/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.layout.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.SimpleViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingHint;

/**
 * Layout edit parts in line according to the {@link ViewOrdering} to use.
 * 
 * @author ymortier
 */
public class LineLayoutProvider extends AbstractLayoutProvider {

    /** The default padding. */
    private static final Insets DEFAULT_PADDING = new Insets(30, 30, 30, 30);

    /**
     * <code>true</code> if the line is horizontal, <code>false</code> if the
     * line is vertical.
     */
    private boolean horizontal = true;

    /**
     * <code>true</code> if the line is horizontal, <code>false</code> if the
     * line is vertical.
     * 
     * @param horizontal
     *            <code>true</code> if the line is horizontal,
     *            <code>false</code> if the line is vertical.
     */
    public void setHorizontal(final boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider#layoutEditParts(java.util.List,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        final Iterator<?> iterEditParts = selectedObjects.iterator();
        final List<View> views = new ArrayList<View>(selectedObjects.size());
        final Map<View, ShapeEditPart> viewsToEditPartMap = new HashMap<View, ShapeEditPart>();
        while (iterEditParts.hasNext()) {
            final Object next = iterEditParts.next();
            if (next instanceof ShapeEditPart && !(next instanceof IBorderItemEditPart)) {
                final ShapeEditPart shapeEditPart = (ShapeEditPart) next;
                final View view = shapeEditPart.getNotationView();
                viewsToEditPartMap.put(view, shapeEditPart);
                views.add(view);
            } else {
                iterEditParts.remove();
            }
        }
        ViewOrdering viewOrdering = ViewOrderingHint.getInstance().consumeViewOrdering(getContainerEditPart(selectedObjects).getNotationView());
        if (viewOrdering == null) {
            // use a simple view ordering ... too bad.
            viewOrdering = new SimpleViewOrdering();
        }

        viewOrdering.setViews(views);
        final List<View> sortedViews = viewOrdering.getSortedViews();
        final List<ShapeEditPart> sortedEditParts = new ArrayList<ShapeEditPart>(sortedViews.size());
        final Iterator<View> iterSortedViews = sortedViews.listIterator();
        while (iterSortedViews.hasNext()) {
            final View currentView = iterSortedViews.next();
            final ShapeEditPart currentEditPart = viewsToEditPartMap.get(currentView);
            sortedEditParts.add(currentEditPart);
        }
        return createNodeChangeBoundCommands(sortedEditParts);
    }

    /**
     * Create the change bounds commands.
     * 
     * @param sortedNodes
     *            the nodes to move.
     * @return the change bounds command.
     */
    protected Command createNodeChangeBoundCommands(final List<ShapeEditPart> sortedNodes) {
        final CompoundCommand result = new CompoundCommand();
        final Iterator<ShapeEditPart> iterEditParts = sortedNodes.iterator();
        int currentX = 0;
        while (iterEditParts.hasNext()) {
            final ShapeEditPart shapeEditPart = iterEditParts.next();
            if (!(shapeEditPart instanceof IBorderItemEditPart)) {

                final View view = shapeEditPart.getNotationView();
                // the zoom.
                double scale = 1.0;
                if (shapeEditPart.getRoot() instanceof DiagramRootEditPart) {
                    final ZoomManager zoomManager = ((DiagramRootEditPart) shapeEditPart.getRoot()).getZoomManager();
                    scale = zoomManager.getZoom();
                }
                //
                // Compute request data.
                final Point ptOldLocation = shapeEditPart.getFigure().getBounds().getLocation();
                // shapeEditPart.getFigure().translateToAbsolute(ptOldLocation);
                final int locationX = horizontal ? currentX + this.getPadding().left : this.getPadding().left;
                final int locationY = horizontal ? this.getPadding().top : currentX + this.getPadding().top;
                final Point ptLocation = new Point(locationX, locationY);
                final Dimension delta = ptLocation.getDifference(ptOldLocation);

                final Object existingRequest = this.findRequest(view, org.eclipse.gef.RequestConstants.REQ_MOVE);
                int step = 0;
                if (existingRequest == null) {
                    final ChangeBoundsRequest request = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_MOVE);
                    request.setEditParts(shapeEditPart);
                    request.setMoveDelta(new PrecisionPoint(delta.width * scale, delta.height * scale));
                    request.setLocation(new PrecisionPoint(ptLocation.x * scale, ptLocation.y * scale));
                    step = this.horizontal ? getBounds(shapeEditPart).width : getBounds(shapeEditPart).height;

                    final Command cmd = this.buildCommandWrapper(request, shapeEditPart);
                    if (cmd != null && cmd.canExecute()) {
                        result.add(cmd);
                        // this.getViewsToChangeBoundsRequest().put(view,
                        // request);
                    }
                } else if (existingRequest instanceof ChangeBoundsRequest) {
                    final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) existingRequest;
                    changeBoundsRequest.setMoveDelta(new PrecisionPoint(delta.width * scale, delta.height * scale));
                    changeBoundsRequest.setLocation(new PrecisionPoint(ptLocation.x * scale, ptLocation.y * scale));

                    step = this.horizontal ? getBounds(shapeEditPart).width : getBounds(shapeEditPart).height;
                }
                currentX += horizontal ? step + getPadding().right + getPadding().left : step + this.getPadding().bottom + this.getPadding().top;

                // check the size of the container.
                EditPart container = shapeEditPart.getParent();
                while (container instanceof CompartmentEditPart) {
                    container = container.getParent();
                }
                if (container instanceof ShapeEditPart) {
                    final ShapeEditPart containerEditPart = (ShapeEditPart) container;

                    // The minimum witdh
                    final int minWidth = this.horizontal ? ((getPadding().left + getPadding().right) * sortedNodes.size()) + (getNodeMaxWidth(sortedNodes) * sortedNodes.size()) : getPadding().left
                            + getNodeMaxWidth(sortedNodes) + getPadding().right;
                    // The minimum height
                    final int minHeight = this.horizontal ? getPadding().top + this.getNodeMaxHeight(sortedNodes) + this.getPadding().bottom : ((getPadding().top + getPadding().bottom) * sortedNodes
                            .size()) + (this.getNodeMaxHeight(sortedNodes) * sortedNodes.size());

                    final Dimension minDimension = new Dimension(minWidth, minHeight);

                    final Dimension difference = minDimension.getShrinked(containerEditPart.getFigure().getBounds().getSize());
                    if (difference.width > 0 || difference.height > 0) {
                        final Object existingContainerRequest = this.findRequest(containerEditPart, org.eclipse.gef.RequestConstants.REQ_RESIZE); // ;this.getViewsToChangeBoundsRequest().get(containerEditPart.getNotationView());
                        createChangeBoundsCommand(result, existingContainerRequest, containerEditPart, difference, scale);
                    }

                }

            }
        }
        return result;
    }

    private void createChangeBoundsCommand(final CompoundCommand compoundCommand, final Object existingContainerRequest, final ShapeEditPart containerEditPart, final Dimension difference,
            final double scale) {

        if (existingContainerRequest == null) {
            final ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest();
            changeBoundsRequest.setEditParts(containerEditPart);
            changeBoundsRequest.setResizeDirection(PositionConstants.SOUTH_EAST);
            changeBoundsRequest.setSizeDelta(new Dimension((int) (difference.width * scale), (int) (difference.height * scale)));
            changeBoundsRequest.setLocation(new Point(0, 0));
            changeBoundsRequest.setType(org.eclipse.gef.RequestConstants.REQ_RESIZE);
            final Command cmd = this.buildCommandWrapper(changeBoundsRequest, containerEditPart);
            if (cmd.canExecute()) {
                compoundCommand.add(cmd);
                // this.getViewsToChangeBoundsRequest().put(containerEditPart.getNotationView(),
                // changeBoundsRequest);
            }
        } else if (existingContainerRequest instanceof ChangeBoundsRequest) {
            final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) existingContainerRequest;
            changeBoundsRequest.setResizeDirection(PositionConstants.SOUTH_EAST);
            changeBoundsRequest.setSizeDelta(new Dimension((int) (difference.width * scale), (int) (difference.height * scale)));
        }
    }

    /**
     * Return the maximum width of all nodes (instances of {@link ShapeEditPart}
     * ) that are in the specified list.
     * 
     * @param nodes
     *            the nodes.
     * @return the maximum width of all nodes that are in the specified list.
     */
    protected int getNodeMaxWidth(final List<ShapeEditPart> nodes) {
        int max = -1;
        for (final ShapeEditPart shapeEditPart : nodes) {
            final Object existingRequest = this.getViewsToChangeBoundsRequest().get(shapeEditPart.getNotationView());
            int width = shapeEditPart.getFigure().getBounds().width;
            if (existingRequest instanceof ChangeBoundsRequest) {
                width = width + ((ChangeBoundsRequest) existingRequest).getSizeDelta().width;
            }
            if (width > max) {
                max = width;
            }
        }
        return max;
    }

    /**
     * Return the maximum height of all nodes (instances of
     * {@link ShapeEditPart}) that are in the specified list.
     * 
     * @param nodes
     *            the nodes.
     * @return the maximum width of all nodes that are in the specified list.
     */
    protected int getNodeMaxHeight(final List<ShapeEditPart> nodes) {
        int max = -1;
        for (final ShapeEditPart shapeEditPart : nodes) {
            final int height = this.getBounds(shapeEditPart).height;
            if (height > max) {
                max = height;
            }
        }
        return max;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.provider.AbstractLayoutProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    @Override
    public boolean provides(final IOperation operation) {
        final View cview = getContainer(operation);
        if (cview == null) {
            return false;
        }
        final IAdaptable layoutHint = ((ILayoutNodeOperation) operation).getLayoutHint();
        final String layoutType = (String) layoutHint.getAdapter(String.class);
        return LayoutType.DEFAULT.equals(layoutType);
    }

    /**
     * Return the padding to use.
     * 
     * @return the padding to use.
     */
    public Insets getPadding() {
        return DEFAULT_PADDING;
    }

    // FIXME do a real implementation.
    /**
     * Get the container edit part of an object list. Currently the function
     * takes only the first object of the list
     * 
     * @param selectedObjects
     *            the selected object
     * @return the container edit part
     */
    protected IGraphicalEditPart getContainerEditPart(final List<EditPart> selectedObjects) {
        if (selectedObjects != null && !selectedObjects.isEmpty()) {
            return (IGraphicalEditPart) (selectedObjects.iterator().next()).getParent();
        }
        return null;
    }

}
