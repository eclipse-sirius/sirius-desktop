/*******************************************************************************
 * Copyright (c) 2015, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.MoveInDiagramDragTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

/**
 * A specific dragEditPartTracket that allows to change the behavior of
 * SnapToShape (capability to snap to all shapes and not only brothers one).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SnapToAllDragEditPartsTracker extends DragEditPartsTrackerEx implements MoveInDiagramDragTracker {

    /**
     * Constant passed to extended data of the request to keep the chosen mode
     * (with KEY {@link #SNAP_TO_ALL}.
     */
    public static final String SNAP_TO_ALL_SHAPE_KEY = "snapToAllShape"; //$NON-NLS-1$

    /**
     * The default mode for {@link #snapToAllShape}.
     */
    public static final boolean DEFAULT_SNAP_TO_SHAPE_MODE = false;

    /**
     * The key shortcut used to change the default behavior of snap to shape.
     */
    public static final int SNAP_TO_ALL = SWT.F4;

    /**
     * The mode of this tracker concerning the snap to shape:
     * <UL>
     * <LI>true to snap to all shapes and not only brothers ones,</LI>
     * <LI>false otherwise.</LI>
     * </UL>
     * This variable is used when the request is updated to add an extended data
     * to the request.
     */
    boolean snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;

    private Point previousMouseLocation;

    /**
     * Default constructor.
     * 
     * @param sourceEditPart
     *            the source edit part
     */
    public SnapToAllDragEditPartsTracker(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * Overridden to update the {@link ChangeBoundsRequest} with information
     * about snapToAll mode.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void snapPoint(ChangeBoundsRequest request) {
        if (snapToAllShape) {
            getTargetRequest().getExtendedData().put(SnapToAllDragEditPartsTracker.SNAP_TO_ALL_SHAPE_KEY, Boolean.TRUE);
        } else {
            getTargetRequest().getExtendedData().put(SnapToAllDragEditPartsTracker.SNAP_TO_ALL_SHAPE_KEY, Boolean.FALSE);
        }
        super.snapPoint(request);
    }

    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        if (SnapToAllDragEditPartsTracker.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = !SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyDown(event);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        if (SnapToAllDragEditPartsTracker.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyUp(event);
    }

    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        // Clean up the mode to original state.
        snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
        return result;
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
}
