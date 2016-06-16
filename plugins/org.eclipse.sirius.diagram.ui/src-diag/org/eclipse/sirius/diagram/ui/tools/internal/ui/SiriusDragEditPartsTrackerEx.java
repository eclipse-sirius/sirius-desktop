/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.MoveInDiagramDragTracker;
import org.eclipse.swt.events.MouseEvent;

/**
 * Specific implementation of
 * {@link org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx}.
 * <br/>
 * It adds margins around parent dragged edit part in order to lower drag 'n
 * drop sensibility.
 * 
 * @author dlecan
 */
public class SiriusDragEditPartsTrackerEx extends SnapToAllDragEditPartsTracker implements MoveInDiagramDragTracker {

    /**
     * The x margin used when moving bordered node to not have drag'n drop
     * considered.
     */
    private static final int X_MARGIN = 30;

    /**
     * The y margin used when moving bordered node to not have drag'n drop
     * considered.
     */
    private static final int Y_MARGIN = X_MARGIN;

    private Point previousMouseLocation;

    /**
     * Constructor.
     * 
     * @param sourceEditPart
     *            Edit part.
     */
    public SiriusDragEditPartsTrackerEx(final EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * Overridden to find the {@link EditPart} under the adapted mouse location,
     * to allow when moving bordered node to not have drag'n drop considered.
     * 
     * {@inheritDoc}
     */
    @Override
    protected boolean updateTargetUnderMouse() {
        if (!isTargetLocked()) {

            EditPart editPart = getCurrentViewer().findObjectAtExcluding(getAdaptedMouseLocation(), getExclusionSet(), getTargetingConditional());

            if (editPart != null) {
                editPart = editPart.getTargetEditPart(getTargetRequest());
            }

            final boolean changed = getTargetEditPart() != editPart;
            setTargetEditPart(editPart);

            return changed;
        } else {
            return false;
        }
    }

    /**
     * Get the parent of the current edit part.
     * 
     * @return The parent of the current edit part
     */
    protected EditPart getSourceParentEditPart() {
        final EditPart parent = getSourceEditPart().getParent();
        return parent;
    }

    /**
     * Indicates if mouse location has to be adapted.
     * 
     * @param parentBounds
     *            Parent bounds
     * @param mouseLocation
     *            Current mouse location
     * @return <code>true</code> if mouse location has to be adapted.
     *         <code>false</code> otherwise
     */
    protected boolean hasToAdaptMouseLocation(final Rectangle parentBounds, final Point mouseLocation) {
        final Rectangle boundsAndMargins = parentBounds.getExpanded(X_MARGIN, Y_MARGIN);
        return boundsAndMargins.contains(mouseLocation) && !parentBounds.contains(mouseLocation);
    }

    /**
     * Adapt mouse location. Algorithm does an homothetic transformation,
     * centered on center of the provided rectangle to relocalize mouse
     * position.
     * 
     * @return Relocated mouse position.
     */
    protected Point getAdaptedMouseLocation() {
        final Rectangle parentBounds = getAbsoluteSourceParentBounds();

        final Point mouseRealLocation = super.getLocation();
        Point mouseAdaptedLocation;
        if (hasToAdaptMouseLocation(parentBounds, mouseRealLocation)) {
            final Point center = parentBounds.getCenter();
            final Dimension rectangleDimension = parentBounds.getSize();

            final double xLocation = getAdaptedMouseXLocation(mouseRealLocation.x, center.x, rectangleDimension.width);
            final double yLocation = getAdaptedMouseYLocation(mouseRealLocation.y, center.y, rectangleDimension.height);
            mouseAdaptedLocation = new PrecisionPoint(xLocation, yLocation);
        } else {
            mouseAdaptedLocation = mouseRealLocation.getCopy();
        }

        return mouseAdaptedLocation;
    }

    /**
     * Get absolute parent bounds.
     * 
     * @return absolute parent bounds
     */
    private Rectangle getAbsoluteSourceParentBounds() {
        final EditPart parent = getSourceParentEditPart();

        final Rectangle absoluteBounds = ((GraphicalEditPart) parent).getFigure().getBounds().getCopy();
        ((GraphicalEditPart) parent).getFigure().translateToAbsolute(absoluteBounds);
        return absoluteBounds;
    }

    private double getAdaptedMouseXLocation(final int xMouseLocation, final int xCenter, final double width) {
        return getAdaptedMouseCoordinateLocation(xMouseLocation, xCenter, width, X_MARGIN);
    }

    private double getAdaptedMouseYLocation(final int yMouseLocation, final int yCenter, final double height) {
        return getAdaptedMouseCoordinateLocation(yMouseLocation, yCenter, height, Y_MARGIN);
    }

    private double getAdaptedMouseCoordinateLocation(final int mouseLocation, final int center, final double length, final int delta) {
        final double halfLength = length / 2;
        final double result = center + (halfLength / (halfLength + delta)) * (mouseLocation - center);

        return result;
    }

    /**
     * Overridden to update the created {@link ChangeBoundsRequest} to use the
     * adapted mouse location, to allow when moving bordered node to not have
     * drag'n drop considered.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void updateTargetRequest() {
        super.updateTargetRequest();
        if (getTargetRequest() instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest targetRequest = (ChangeBoundsRequest) getTargetRequest();
            targetRequest.setLocation(getAdaptedMouseLocation());
        }
    }

    /**
     * Always disable the clone with Ctrl key in Sirius because it only clone
     * the graphical element and not the semantic element.
     * 
     * @param cloneActive
     *            true if cloning should be active (never considered here)
     * @see org.eclipse.gef.tools.DragEditPartsTracker#setCloneActive(boolean)
     */
    @Override
    protected void setCloneActive(boolean cloneActive) {
        super.setCloneActive(false);
    }

    @Override
    protected boolean handleButtonDown(int button) {
        if (button == 2) {
            setCursor(SharedCursors.HAND);
            return stateTransition(STATE_INITIAL, STATE_SCROLL_DIAGRAM);
        } else {
            return super.handleButtonDown(button);
        }
    }

    @Override
    public void mouseDrag(MouseEvent me, EditPartViewer viewer) {
        previousMouseLocation = getCurrentInput().getMouseLocation().getCopy();
        super.mouseDrag(me, viewer);
    }

    @Override
    protected boolean handleDragStarted() {
        if (isInState(STATE_SCROLL_DIAGRAM)) {
            return stateTransition(STATE_SCROLL_DIAGRAM, STATE_SCROLL_DIAGRAM_IN_PROGRESS);
        }
        return super.handleDragStarted();
    }

    @Override
    protected boolean handleDragInProgress() {
        if (isInState(STATE_SCROLL_DIAGRAM_IN_PROGRESS)) {
            if (getCurrentViewer().getControl() instanceof FigureCanvas) {
                FigureCanvas figureCanvas = (FigureCanvas) getCurrentViewer().getControl();
                Point currentMouseLocation = getCurrentInput().getMouseLocation();
                Dimension difference = previousMouseLocation.getDifference(currentMouseLocation);
                Point location = figureCanvas.getViewport().getViewLocation();
                figureCanvas.scrollTo(location.x + difference.width, location.y + difference.height);
            }
            return true;
        }
        return super.handleDragInProgress();
    }

    @Override
    protected void reveal(EditPart editpart) {
        // In Sirius, the drag'n'drop can change (delete and create a new
        // one) the previous container of the drag'n'droped element. In this
        // case, the reveal causes a NPE because the hierarchy of edit part is
        // broken.
        if (editpart.getRoot() != null) {
            super.reveal(editpart);
        }
    }
}
