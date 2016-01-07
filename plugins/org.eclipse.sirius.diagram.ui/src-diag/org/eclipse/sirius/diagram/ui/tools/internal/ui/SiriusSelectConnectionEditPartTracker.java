/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.SelectConnectionEditPartTracker;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.MoveEdgeGroupManager;
import org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools.MoveInDiagramDragTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;

/**
 * A specific Sirius tracker to handle the move edge group feature.
 * 
 * @author Florian Barbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusSelectConnectionEditPartTracker extends SelectConnectionEditPartTracker implements MoveInDiagramDragTracker {

    private boolean moveGroupActivated;

    /**
     * The getSourceRequest method is private so we need to keep the reference
     * in the createSourceRequest method.
     */
    private BendpointRequest bendpointRequest;

    private Point previousMouseLocation;

    /**
     * Method SelectConnectionEditPartTracker.
     * 
     * @param owner
     *            ConnectionNodeEditPart that creates and owns the tracker
     *            object
     */
    public SiriusSelectConnectionEditPartTracker(ConnectionEditPart owner) {
        super(owner);
        setDisabledCursor(SharedCursors.NO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Request createSourceRequest() {
        Request rq = super.createSourceRequest();
        if (rq instanceof BendpointRequest) {
            bendpointRequest = (BendpointRequest) rq;
        }
        return rq;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void updateSourceRequest() {
        super.updateSourceRequest();
        if (bendpointRequest != null) {
            if (moveGroupActivated) {
                bendpointRequest.getExtendedData().put(MoveEdgeGroupManager.EDGE_MOVE_DELTA, computeDelta());
                bendpointRequest.getExtendedData().put(MoveEdgeGroupManager.EDGE_GROUP_MOVE_KEY, Boolean.TRUE);
                bendpointRequest.getExtendedData().put(MoveEdgeGroupManager.EDGE_GROUP_MOVE_HAS_BEEN_ACTIVATED_KEY, Boolean.TRUE);
            } else {
                bendpointRequest.getExtendedData().remove(MoveEdgeGroupManager.EDGE_MOVE_DELTA);
                bendpointRequest.getExtendedData().put(MoveEdgeGroupManager.EDGE_GROUP_MOVE_KEY, Boolean.FALSE);
            }
        }
    }

    @Override
    public void deactivate() {
        super.deactivate();
        // We also set our field to null.
        bendpointRequest = null;
    }

    private Dimension computeDelta() {
        Point newLocation = bendpointRequest.getLocation();
        Dimension diff;
        if (newLocation != null) {
            diff = newLocation.getDifference(getStartLocation());
        } else {
            diff = getDragMoveDelta();
        }
        return diff;
    }

    @Override
    protected boolean handleKeyDown(KeyEvent e) {
        if (SWT.F3 == e.keyCode) {
            moveGroupActivated = true;
            return true;
        }
        return super.handleKeyDown(e);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent e) {
        if (SWT.F3 == e.keyCode) {
            moveGroupActivated = false;
            return true;
        }
        return super.handleKeyUp(e);
    }

    @Override
    protected Cursor calculateCursor() {
        Cursor cursorToReturn;
        if (moveGroupActivated) {
            Command command = getCurrentCommand();
            if (command == null || !command.canExecute()) {
                cursorToReturn = getDisabledCursor();
            } else {
                cursorToReturn = SharedCursors.ARROW;
            }
        } else {
            cursorToReturn = super.calculateCursor();
        }
        return cursorToReturn;
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
