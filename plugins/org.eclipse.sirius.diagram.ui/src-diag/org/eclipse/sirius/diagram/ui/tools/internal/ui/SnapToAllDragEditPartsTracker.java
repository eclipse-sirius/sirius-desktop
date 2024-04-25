/*******************************************************************************
 * Copyright (c) 2015, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import java.util.Date;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gmf.runtime.diagram.ui.internal.ruler.SnapToHelperUtil;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.MoveInDiagramDragTracker;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
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
     * Constant passed to extended data of the request to keep the chosen mode (with KEY {@link #SNAP_TO_ALL}).
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
     * Copied from {@link AbstractTool#MODIFIER_NO_SNAPPING}. It is not accessible here but is used in the overridden of
     * {@link #handleKeyDown(KeyEvent)}.<BR/>
     * Key modifier for ignoring snap while dragging. It's CTRL on Mac, and ALT on all other platforms.
     */
    protected static final int MODIFIER_NO_SNAPPING;

    static {
        if (Platform.OS_MACOSX.equals(Platform.getOS())) {
            MODIFIER_NO_SNAPPING = SWT.CTRL;
        } else {
            MODIFIER_NO_SNAPPING = SWT.ALT;
        }
    }

    /**
     * True when a move is triggered through an arrow key. This mode is a specific mode used by Sirius instead of the
     * one of GMF/GEF. This "old" mode, based on a mouse move simulation, is "incomplete" when the container of the
     * moved element has a scrollbar.<BR/>
     * This mode is enabled when an arrow key is pressed, in {@link #handleKeyDown(KeyEvent)} and is disabled when this
     * key is released , in {@link #handleKeyUp(KeyEvent).
     */
    private boolean moveWithArrowKeysSiriusMode;

    /**
     * Copied from {@link org.eclipse.gef.tools.AbstractTool} to allow a similar acceleration move behavior.
     */
    private long accessibleBegin;

    /**
     * Copied from {@link org.eclipse.gef.tools.AbstractTool} to allow a similar acceleration move behavior.
     */
    private int accessibleStep;

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
     * Overridden to update the {@link ChangeBoundsRequest} with information about snapToAll mode and to adapt all code
     * usually called in all "super.snapPoint()" for the new mode.
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.ui.SnapToAllDragEditPartsTracker#snapPoint(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected void snapPoint(ChangeBoundsRequest request) {
        if (snapToAllShape) {
            getTargetRequest().getExtendedData().put(SnapToAllDragEditPartsTracker.SNAP_TO_ALL_SHAPE_KEY, Boolean.TRUE);
        } else {
            getTargetRequest().getExtendedData().put(SnapToAllDragEditPartsTracker.SNAP_TO_ALL_SHAPE_KEY, Boolean.FALSE);
        }
        if (!moveWithArrowKeysSiriusMode) {
            super.snapPoint(request);
        } else {
            // Copied from
            // org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx.snapPoint(ChangeBoundsRequest)
            // Adapted to use precise coordinates for move delta (in case of zoom of 50% for example).
            Point moveDelta = request.getMoveDelta();
            if (getState() == STATE_ACCESSIBLE_DRAG_IN_PROGRESS) {
                int restrictedDirection = 0;

                if (moveDelta.preciseX() > 0) {
                    restrictedDirection = restrictedDirection | PositionConstants.EAST;
                } else if (moveDelta.preciseX() < 0) {
                    restrictedDirection = restrictedDirection | PositionConstants.WEST;
                }

                if (moveDelta.preciseY() > 0) {
                    restrictedDirection = restrictedDirection | PositionConstants.SOUTH;
                } else if (moveDelta.preciseY() < 0) {
                    restrictedDirection = restrictedDirection | PositionConstants.NORTH;
                }

                request.getExtendedData().put(SnapToHelperUtil.RESTRICTED_DIRECTIONS, restrictedDirection);
            }
            // Copied from org.eclipse.gef.tools.DragEditPartsTracker.snapPoint(ChangeBoundsRequest)
            if (getSnapToHelper() != null && request.isSnapToEnabled()) {
                PrecisionRectangle baseRect = getSourceBounds().getPreciseCopy();
                PrecisionRectangle jointRect = getOperationSetBounds().getPreciseCopy();
                PrecisionPoint preciseDelta = new PrecisionPoint(moveDelta);
                baseRect.translate(preciseDelta);
                jointRect.translate(preciseDelta);
                getSnapToHelper().snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL, new PrecisionRectangle[] { baseRect, jointRect }, preciseDelta);
                request.setMoveDelta(preciseDelta);
            }
        }
    }

    /**
     * Method overridden to handle stapToAll and to enable the new mode in case of move with arrow key.
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.ui.SnapToAllDragEditPartsTracker#handleKeyDown(org.eclipse.swt.events.KeyEvent)
     */
    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        boolean result = true;
        if (SnapToAllDragEditPartsTracker.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = !SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
        } else if (!acceptArrowKey(event)) {
            result = super.handleKeyDown(event);
        } else {
            moveWithArrowKeysSiriusMode = true;
            // Enable the move behavior based on a request set according to the arrow direction pressed.

            // With this mode, it is not possible to enable the SnapToAllDragEditPartsTracker.SNAP_TO_ALL mode. Indeed,
            // this mode is enabled by pressing the F4 key during a mouse move. It was also not available in the classic
            // GMF/GEF mode.

            // By the way, the previous mode does not correctly handle the snap to shape (but it is currently not the
            // goal of this new mode to fix this).

            // Reproduce the "acceleration behavior" of
            // org.eclipse.gef.tools.DragEditPartsTracker.handleKeyDown(KeyEvent) to get the increment to use.
            siriusAccStepIncrement();
            PrecisionPoint moveDelta;
            switch (event.keyCode) {
            case SWT.ARROW_DOWN:
                moveDelta = new PrecisionPoint(0, siriusAccGetStep());
                break;
            case SWT.ARROW_UP:
                moveDelta = new PrecisionPoint(0, -siriusAccGetStep());
                break;
            case SWT.ARROW_RIGHT:
                int stepping = siriusAccGetStep();
                if (isCurrentViewerMirrored2()) {
                    stepping = -stepping;
                }
                moveDelta = new PrecisionPoint(stepping, 0);
                break;
            case SWT.ARROW_LEFT:
                int step = -siriusAccGetStep();
                if (isCurrentViewerMirrored2()) {
                    step = -step;
                }
                moveDelta = new PrecisionPoint(step, 0);
                break;
            default:
                moveDelta = new PrecisionPoint(0, 0);
            }
            // Adapt the moveDelta to the zoom level, because later in
            // org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy.getConstraintFor(ChangeBoundsRequest,
            // GraphicalEditPart) the compute is done with coordinates considering the zoom. This avoids to have, for
            // example, a move of 4 pixels when the zoom level is 25%
            double zoomFactor = GraphicalHelper.getZoom(getSourceEditPart());
            PrecisionPoint preciseDelta = (PrecisionPoint) new PrecisionPoint(moveDelta).scale(zoomFactor);
            // Set the request according to the move
            ChangeBoundsRequest req = (ChangeBoundsRequest) getTargetRequest();
            if (req.getMoveDelta().preciseX() == 0 && req.getMoveDelta().preciseY() == 0) {
                req.setMoveDelta(preciseDelta);
            } else {
                req.setMoveDelta(req.getMoveDelta().getTranslated(preciseDelta));
            }
            req.setConstrainedResize(false);
            // The org.eclipse.gef.tools.DragEditPartsTracker.MODIFIER_CONSTRAINED_MOVE, through
            // org.eclipse.gef.requests.ChangeBoundsRequest.isConstrainedMove(), is not handle in this mode. Indeed, the
            // user only moves the node in one direction.
            // Set the snap deactivation if the modifier key is pressed.
            req.setSnapToEnabled(!getCurrentInput().isModKeyDown(MODIFIER_NO_SNAPPING));
            // Set target edit part as parent: The parent remains the same.
            setTargetEditPart(getSourceEditPart().getParent());
            req.setType(getCommandName());
            req.setEditParts(getOperationSet());

            setState(STATE_ACCESSIBLE_DRAG_IN_PROGRESS);
            handleDragInProgress();
        }
        return result;
    }

    /**
     * Method overridden to handle snapToAll and to disable the new mode in case of move with arrow key. This mode has
     * been enabled in {@link #handleKeyDown(KeyEvent)}.
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.ui.SnapToAllDragEditPartsTracker#handleKeyUp(org.eclipse.swt.events.KeyEvent)
     */
    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        if (SnapToAllDragEditPartsTracker.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        if (acceptArrowKey(event)) {
            moveWithArrowKeysSiriusMode = false;
            siriusAccStepReset();
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

    /**
     * Copied from {@link org.eclipse.gef.tools.AbstractTool#isCurrentViewerMirrored()}.
     * 
     * @return true if the current viewer is mirrored, false otherwise.
     */
    private boolean isCurrentViewerMirrored2() {
        return (getCurrentViewer().getControl().getStyle() & SWT.MIRRORED) != 0;
    }

    /**
     * Method overridden to handle the new mode. Only snap is called here because all other steps are not needed in the
     * new mode.
     * 
     * @see org.eclipse.gef.tools.DragEditPartsTracker#updateTargetRequest()
     */
    @Override
    protected void updateTargetRequest() {
        if (moveWithArrowKeysSiriusMode) {
            snapPoint((ChangeBoundsRequest) getTargetRequest());
        } else {
            super.updateTargetRequest();
        }
    }

    /**
     * Method overridden to do nothing in case of the new mode. Indeed, when a node is move with an arrow key there is
     * no target change.
     * 
     * @see org.eclipse.gef.tools.TargetingTool#updateTargetUnderMouse()
     */
    @Override
    protected boolean updateTargetUnderMouse() {
        if (moveWithArrowKeysSiriusMode) {
            return false;
        }
        return super.updateTargetUnderMouse();
    }

    /**
     * Return true if a move is currently in progress with arrow key, false otherwise.
     * 
     * @return true if a move is currently in progress with arrow key, false otherwise.
     */
    public boolean isMoveWithArrowKeysSiriusMode() {
        return moveWithArrowKeysSiriusMode;
    }

    /**
     * Set the moveWithArrowKeysSiriusMode.
     * 
     * @param moveWithArrowKeysSiriusMode
     *            The new mode status
     */
    protected void setMoveWithArrowKeysSiriusMode(boolean moveWithArrowKeysSiriusMode) {
        this.moveWithArrowKeysSiriusMode = moveWithArrowKeysSiriusMode;
    }

    /**
     * Method overridden to initialize the cloned field {@link #accessibleBegin}. No longer necessary as soon as GEF
     * issue https://github.com/eclipse/gef-classic/issues/426 will be done.
     * 
     * @see org.eclipse.gef.tools.AbstractTool#activate()
     */
    @Override
    public void activate() {
        super.activate();
        siriusAccStepReset();
    }

    /**
     * Method cloned from {@link org.eclipse.gef.tools.AbstractTool#.accGetStep()} to have the same behavior here. No
     * longer necessary as soon as GEF issue https://github.com/eclipse/gef-classic/issues/426 will be done.
     * 
     * @return the current computed step.
     */
    int siriusAccGetStep() {
        return accessibleStep;
    }

    /**
     * Method cloned from {@link org.eclipse.gef.tools.AbstractTool#accStepIncrement()} to have the same behavior here.
     * No longer necessary as soon as GEF issue https://github.com/eclipse/gef-classic/issues/426 will be done.
     * 
     * @return the current computed step.
     */
    void siriusAccStepIncrement() {
        if (accessibleBegin == -1) {
            accessibleBegin = new Date().getTime();
            accessibleStep = 1;
        } else {
            accessibleStep = 4;
            long elapsed = new Date().getTime() - accessibleBegin;
            if (elapsed > 1000)
                accessibleStep = Math.min(16, (int) (elapsed / 150));
        }
    }

    /**
     * Method cloned from {@link org.eclipse.gef.tools.AbstractTool#accStepReset()} to have the same behavior here. No
     * longer necessary as soon as GEF issue https://github.com/eclipse/gef-classic/issues/426 will be done.
     */
    void siriusAccStepReset() {
        accessibleBegin = -1;
    }
}
