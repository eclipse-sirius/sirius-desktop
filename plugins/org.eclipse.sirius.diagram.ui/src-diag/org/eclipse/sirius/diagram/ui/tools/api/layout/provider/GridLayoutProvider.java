/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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

import java.text.MessageFormat;
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
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutExtender;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.GridView;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.GridView.Column;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.GridViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.SimpleViewOrdering;
import org.eclipse.sirius.diagram.ui.tools.api.layout.ordering.ViewOrderingHint;

/**
 * A layout provider that arranges views according to the specified
 * {@link GridViewOrdering}.
 * 
 * @author ymortier
 */
public class GridLayoutProvider extends DefaultLayoutProvider implements ExtendableLayoutProvider {

    /** Each case of the grid has the same dimension. */
    public static final int SAME_DIMENSION = 1;

    /** Each case dimension is computed by line or column. */
    public static final int DIMENSION_BY_LINE_OR_COLUMN = 2;

    /** Each case has its owned dimension. */
    public static final int FREE_DIMENSION = 3;

    /** Cache location to improve performances. */
    private Map<Point, Point> locationsCache = new HashMap<Point, Point>();

    /** The mode of the column size. */
    private int columnSizeMode = GridLayoutProvider.SAME_DIMENSION;

    /**
     * The mode of the line size.
     */
    private int lineSizeMode = GridLayoutProvider.SAME_DIMENSION;

    /** The padding. */
    private Insets padding = new Insets(30, 30, 30, 30);

    /**
     * Maps each column with its max width.
     * <ul>
     * <li>Key : Integer, index of the column.</li>
     * <li>Value : Integer, the max width.</li>
     * </ul>
     */
    private Map<Integer, Integer> maxWidths = new HashMap<Integer, Integer>();

    /**
     * Maps each line with its max height.
     * <ul>
     * <li>Key : Integer, index of the line.</li>
     * <li>Value : Integer, the max height.</li>
     * </ul>
     */
    private Map<Integer, Integer> maxHeights = new HashMap<Integer, Integer>();

    private final LayoutExtender extender = new LayoutExtender(this);

    /**
     * Return the padding.
     * 
     * @return the padding.
     */
    public Insets getPadding() {
        return padding;
    }

    /**
     * Returns the column size computing style. The style can be
     * <ul>
     * <li>{@link #SAME_DIMENSION}</li>
     * <li>{@link #DIMENSION_BY_LINE_OR_COLUMN}</li>
     * <li>{@link #FREE_DIMENSION}</li>
     * </ul>
     * 
     * @return the column size computing style.
     */
    public int getColumnSizeMode() {
        return columnSizeMode;
    }

    /**
     * Sets the column size computing style. The style can be
     * <ul>
     * <li>{@link #SAME_DIMENSION}</li>
     * <li>{@link #DIMENSION_BY_LINE_OR_COLUMN}</li>
     * <li>{@link #FREE_DIMENSION}</li>
     * </ul>
     * 
     * @param columnSizeMode
     *            the column size computing style.
     */
    public void setColumnSizeMode(final int columnSizeMode) {
        if (columnSizeMode < GridLayoutProvider.SAME_DIMENSION || columnSizeMode > GridLayoutProvider.FREE_DIMENSION) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.GridLayoutProvider_unknownMode, columnSizeMode));
        }
        this.columnSizeMode = columnSizeMode;
    }

    /**
     * Returns the line size computing style. The style can be
     * <ul>
     * <li>{@link #SAME_DIMENSION}</li>
     * <li>{@link #DIMENSION_BY_LINE_OR_COLUMN}</li>
     * <li>{@link #FREE_DIMENSION}</li>
     * </ul>
     * 
     * @return the line size computing style.
     */
    public int getLineSizeMode() {
        return lineSizeMode;
    }

    /**
     * Sets the line size computing style. The style can be
     * <ul>
     * <li>{@link #SAME_DIMENSION}</li>
     * <li>{@link #DIMENSION_BY_LINE_OR_COLUMN}</li>
     * <li>{@link #FREE_DIMENSION}</li>
     * </ul>
     * 
     * @param lineSizeMode
     *            the line size computing style.
     */
    public void setLineSizeMode(final int lineSizeMode) {
        if (lineSizeMode < GridLayoutProvider.SAME_DIMENSION || lineSizeMode > GridLayoutProvider.FREE_DIMENSION) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.GridLayoutProvider_unknownMode, lineSizeMode));
        }
        this.lineSizeMode = lineSizeMode;
    }

    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        extender.startLayouting();
        this.maxHeights.clear();
        this.maxWidths.clear();
        this.locationsCache.clear();
        final Iterator<?> iterEditParts = selectedObjects.iterator();
        final List<View> views = new ArrayList<View>(selectedObjects.size());
        final Map<View, IGraphicalEditPart> viewsToEditPartMap = new HashMap<View, IGraphicalEditPart>();
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
        GridViewOrdering viewOrdering = ViewOrderingHint.getInstance().consumeViewOrdering(getContainerEditPart(selectedObjects).getNotationView());
        if (viewOrdering == null) {
            viewOrdering = new SimpleViewOrdering();
        }
        viewOrdering.setViews(views);
        final GridView gridView = viewOrdering.getSortedViewsAsGrid();

        final Command command = this.buildGrid(gridView, viewsToEditPartMap);
        //
        // Layout the container.
        final Command layoutContainer = this.getLayoutContainerCommand(getContainerEditPart(selectedObjects), gridView);

        this.maxHeights.clear();
        this.maxWidths.clear();
        this.locationsCache.clear();

        final CompoundCommand cc = new CompoundCommand();
        cc.add(command);
        if (layoutContainer != null && layoutContainer.canExecute()) {
            cc.add(layoutContainer);
        }
        return cc;
    }

    /**
     * Return the command that layout the container of this grid.
     * 
     * @param gridView
     *            the grid.
     * @return the command that layout the container of this grid.
     */
    private Command getLayoutContainerCommand(final IGraphicalEditPart containerEditPart, final GridView gridView) {
        final Dimension containerMinDimension = getLayoutDimensions(gridView);
        final Dimension expand = new Dimension(0, 0);

        final Dimension containerBounds = this.getBounds(containerEditPart).getSize();
        if (containerBounds.width < containerMinDimension.width) {
            expand.setWidth(containerMinDimension.width - containerBounds.width);
        }
        if (containerBounds.height < containerMinDimension.height) {
            expand.setHeight(containerMinDimension.height - containerBounds.height);
        }

        if (expand.width > 0 || expand.height > 0) {
            final Object existingRequest = this.viewsToChangeBoundsRequest.get(containerEditPart.getNotationView());
            if (existingRequest == null) {
                final ChangeBoundsRequest cbr = new ChangeBoundsRequest(org.eclipse.gef.RequestConstants.REQ_RESIZE);
                cbr.setResizeDirection(PositionConstants.SOUTH_EAST);
                cbr.setSizeDelta(expand);
                final Command command = this.buildCommandWrapper(cbr, containerEditPart);
                return command;
            } else if (existingRequest instanceof ChangeBoundsRequest) {
                final ChangeBoundsRequest cbr = (ChangeBoundsRequest) existingRequest;
                cbr.setResizeDirection(PositionConstants.SOUTH_EAST);
                cbr.setSizeDelta(expand);
            }
        }
        return null;
    }

    /**
     * Creates the command that arranges all views in a grid format.
     * 
     * @param gridView
     *            the grid.
     * @param viewsToEditPart
     *            map each view to its edit part.
     * @return the command that arranges all views in a grid format.
     */
    protected Command buildGrid(final GridView gridView, final Map<View, IGraphicalEditPart> viewsToEditPart) {
        final CompoundCommand cc = new CompoundCommand();
        if (this.getColumnSizeMode() != GridLayoutProvider.FREE_DIMENSION || this.getLineSizeMode() != GridLayoutProvider.FREE_DIMENSION) {
            // init size.
            final Iterator<Column> iterColumns = gridView.iteratorColumns();
            while (iterColumns.hasNext()) {
                final Column currentColumn = iterColumns.next();
                final Integer columnIndex = Integer.valueOf(currentColumn.getIndex());
                this.maxWidths.put(columnIndex, Integer.valueOf(-1));
                for (int i = 0; i < currentColumn.getViewsCount(); i++) {
                    final Integer lineIndex = Integer.valueOf(i);
                    final View view = currentColumn.getViewAt(i);
                    Dimension size = new Dimension(0, 0);
                    if (view != null) {
                        final IGraphicalEditPart editPart = viewsToEditPart.get(view);
                        size = getBounds(editPart).getSize();
                    }
                    if (maxHeights.get(lineIndex) == null) {
                        maxHeights.put(lineIndex, Integer.valueOf(-1));
                    }
                    if (size.width > maxWidths.get(columnIndex).intValue()) {
                        maxWidths.put(columnIndex, Integer.valueOf(size.width));
                    }
                    if (size.height > maxHeights.get(lineIndex).intValue()) {
                        maxHeights.put(lineIndex, Integer.valueOf(size.height));
                    }

                }
            }
        }
        //
        // Create command
        final Iterator<Column> iterColumns = gridView.iteratorColumns();
        while (iterColumns.hasNext()) {
            final Column currentColumn = iterColumns.next();
            for (int i = 0; i < currentColumn.getViewsCount(); i++) {
                final Point caseLocation = getLocation(currentColumn.getIndex(), i, gridView, viewsToEditPart);
                if (currentColumn.getViewAt(i) != null) {
                    final IGraphicalEditPart editPart = viewsToEditPart.get(currentColumn.getViewAt(i));
                    final Point newLocation = new Point(caseLocation.x + getPadding().left, caseLocation.y + getPadding().top);
                    final Command command = createChangeBoundsCommand(editPart, newLocation);
                    if (command != null && command.canExecute()) {
                        cc.add(command);
                    }
                }
            }
        }

        return cc;
    }

    /**
     * Create the command that changes the bounds of the specified edit part.
     * 
     * @param editPart
     *            the specified edit part
     * 
     * @param newPosition
     *            the new position of the figure.
     * @return the command that changes the bounds of the specified edit part.
     */
    protected Command createChangeBoundsCommand(final IGraphicalEditPart editPart, final Point newPosition) {
        Command result = null;
        final Object existingRequest = this.findRequest(editPart, org.eclipse.gef.RequestConstants.REQ_MOVE);
        ChangeBoundsRequest request = null;
        double scale = 1.0;
        if (editPart.getRoot() instanceof DiagramRootEditPart) {
            final ZoomManager zoomManager = ((DiagramRootEditPart) editPart.getRoot()).getZoomManager();
            scale = zoomManager.getZoom();
        }
        if (existingRequest instanceof ChangeBoundsRequest) {
            request = (ChangeBoundsRequest) existingRequest;
        } else if (existingRequest == null) {
            request = new ChangeBoundsRequest();
            request.setEditParts(editPart);
            result = this.buildCommandWrapper(request, editPart);
        }
        if (newPosition != null) {
            final Rectangle intrinsicBounds = editPart.getFigure().getBounds();
            final Dimension delta = newPosition.getDifference(intrinsicBounds.getLocation());
            delta.width *= scale;
            delta.height *= scale;
            if ((delta.width != 0 || delta.height != 0) && request != null) {
                request.setMoveDelta(new Point(delta.width, delta.height));
                request.setLocation(newPosition);
                request.setType(org.eclipse.gef.RequestConstants.REQ_MOVE);
                result = this.buildCommandWrapper(request, editPart);
                extender.getUpdatedBounds().put(editPart, new Rectangle(newPosition, intrinsicBounds.getSize()));
            } else {
                // no move, return null.
                return null;
            }
        }
        return result;
    }

    /**
     * Return the max dimension.
     * 
     * @return the max dimension.
     */
    private Dimension getDiagramMaxDimension() {
        int maxHeight = -1;
        final int maxWidth = -1;
        final Iterator<Integer> iterHeights = this.maxHeights.values().iterator();
        while (iterHeights.hasNext()) {
            final Integer currentValue = iterHeights.next();
            if (currentValue.intValue() > maxHeight) {
                maxHeight = currentValue.intValue();
            }
        }
        final Iterator<Integer> iterWidths = this.maxWidths.values().iterator();
        while (iterWidths.hasNext()) {
            final Integer currentValue = iterWidths.next();
            if (currentValue.intValue() > maxWidth) {
                maxHeight = currentValue.intValue();
            }
        }
        return new Dimension(maxWidth, maxHeight);
    }

    private Dimension getLayoutDimensions(final GridView gridView) {
        final Dimension result = new Dimension();
        switch (this.getLineSizeMode()) {
        case FREE_DIMENSION:
        case DIMENSION_BY_LINE_OR_COLUMN:
            final Iterator<Integer> iterHeights = this.maxHeights.values().iterator();
            while (iterHeights.hasNext()) {
                final Integer currentValue = iterHeights.next();
                result.setHeight(result.height + currentValue.intValue());
                result.setHeight(result.height + getPadding().top);
                result.setHeight(result.height + getPadding().bottom);
            }
            break;
        case SAME_DIMENSION:
            result.setHeight(getDiagramMaxDimension().height + (getPadding().top + getPadding().bottom) * gridView.getLinesCount());
            break;
        default:
            break;
        }

        switch (this.getColumnSizeMode()) {
        case FREE_DIMENSION:
        case DIMENSION_BY_LINE_OR_COLUMN:
            final Iterator<Integer> iterWidths = this.maxWidths.values().iterator();
            while (iterWidths.hasNext()) {
                final Integer currentValue = iterWidths.next();
                result.setWidth(result.width + currentValue.intValue());
                result.setWidth(result.width + getPadding().left);
                result.setWidth(result.width + getPadding().top);
            }
            break;
        case SAME_DIMENSION:
            result.setWidth(getDiagramMaxDimension().width + (getPadding().left + getPadding().right) * gridView.getColumnsCount());
            break;
        default:
            break;
        }
        return result;
    }

    // FIXME do a real implementation.
    /**
     * Get the the first parent editPart of selected objects.
     * 
     * @param selectedObjects
     *            the list of selected object
     * @return an {@link EditPart} instance
     */
    protected IGraphicalEditPart getContainerEditPart(final List<?> selectedObjects) {
        if (selectedObjects != null && !selectedObjects.isEmpty()) {
            return (IGraphicalEditPart) ((EditPart) selectedObjects.iterator().next()).getParent();
        }
        return null;
    }

    /**
     * Return the location of the case (x, y).
     * 
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     * @param gridView
     *            the grid.
     * @param viewsToEditParts
     *            maps each view with ots corresponding edit part.
     * @return the location of the case
     */
    public Point getLocation(final int x, final int y, final GridView gridView, final Map<View, IGraphicalEditPart> viewsToEditParts) {
        final Point point = new Point(x, y);
        final Point cachedLocation = this.locationsCache.get(point);
        if (cachedLocation != null) {
            return cachedLocation;
        }
        final Point location = new Point();
        if (x == 0 && y == 0) {
            location.setX(0);
            location.setY(0);
        } else {
            if (x == 0) {
                location.setX(0);
            } else {
                final Point left = getLocation(x - 1, y, gridView, viewsToEditParts);
                final View leftView = gridView.getViewAt(x - 1, y);
                Dimension size = new Dimension(0, 0);
                if (leftView != null) {
                    final IGraphicalEditPart leftEditPart = viewsToEditParts.get(leftView);
                    size = this.getBounds(leftEditPart).getSize();
                }
                switch (this.getColumnSizeMode()) {
                case FREE_DIMENSION:
                    location.setX(left.x + size.width + getPadding().left + getPadding().right);
                    break;
                case SAME_DIMENSION:
                    location.setX(left.x + getDiagramMaxDimension().width + getPadding().left + getPadding().right);
                    break;
                case DIMENSION_BY_LINE_OR_COLUMN:
                    location.setX(left.x + this.maxWidths.get(Integer.valueOf(x - 1)).intValue() + getPadding().right + getPadding().left);
                    break;
                default:
                    break;
                }
            }
            if (y == 0) {
                location.setY(0);
            } else {
                final Point top = getLocation(x, y - 1, gridView, viewsToEditParts);
                final View topView = gridView.getViewAt(x, y - 1);
                Dimension size = new Dimension(0, 0);
                if (topView != null) {
                    final IGraphicalEditPart topEditPart = viewsToEditParts.get(topView);
                    size = this.getBounds(topEditPart).getSize();
                }
                switch (this.getLineSizeMode()) {
                case FREE_DIMENSION:
                    location.setY(top.y + size.height + getPadding().top + getPadding().bottom);
                    break;
                case SAME_DIMENSION:
                    location.setY(top.y + getDiagramMaxDimension().height + getPadding().top + getPadding().bottom);
                    break;
                case DIMENSION_BY_LINE_OR_COLUMN:
                    location.setY(top.y + this.maxHeights.get(Integer.valueOf(y - 1)).intValue() + getPadding().top + getPadding().bottom);
                    break;
                default:
                    break;
                }
            }
        }
        this.locationsCache.put(point, location);
        return location;
    }

    @Override
    public LayoutExtender getExtender() {
        return extender;
    }

    @Override
    public boolean handleConnectableListItems() {
        return false;
    }

    @Override
    public Rectangle provideNodeMetrics(Node node) {
        return null;
    }
}
