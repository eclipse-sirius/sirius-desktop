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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.LocationRequest;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.ConnectionBendpointTrackerEx;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.MoveEdgeGroupManager;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SnapBendpointRequest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Cursor;

/**
 * This tracker also allows to change the behavior of SnapToShape (capability to
 * snap to all shapes).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusConnectionBendpointTrackerEx extends ConnectionBendpointTrackerEx {

    /**
     * The mode of this tracker concerning the snap to shape:
     * <UL>
     * <LI>true to snap to all shapes and not only brothers ones,</LI>
     * <LI>false otherwise.</LI>
     * </UL>
     * This variable is used when the request is updated to add an extended data
     * to the request.
     */
    boolean snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;

    private boolean moveGroupActivated;

    /**
     * Constructs a tracker for the given connection and index.
     * 
     * @param editpart
     *            the connection
     * @param i
     *            the index of the bendpoint
     */
    public SiriusConnectionBendpointTrackerEx(ConnectionEditPart editpart, int i) {
        super(editpart, i);
        setDisabledCursor(SharedCursors.NO);
    }

    @Override
    protected Request createSourceRequest() {
        BendpointRequest request = new SnapBendpointRequest();
        request.setType(getType());
        request.setIndex(getIndex());
        request.setSource(getConnectionEditPart());
        return request;
    }

    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        boolean handled = false;
        if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = !NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            handled = true;
        } else if (SWT.F3 == event.keyCode) {
            moveGroupActivated = true;
            handled = true;
        }
        if (handled) {
            return true;
        }
        return super.handleKeyDown(event);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        boolean handled = false;
        if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            handled = true;
        } else if (SWT.F3 == event.keyCode) {
            moveGroupActivated = false;
            handled = true;
        }
        if (handled) {
            return true;
        }
        return super.handleKeyUp(event);
    }

    /**
     * Update the request with information about snapToAll and moveGroup modes.
     */
    @SuppressWarnings({ "unchecked" })
    @Override
    protected void updateSourceRequest() {
        if (getSourceRequest() instanceof SnapBendpointRequest) {
            if (snapToAllShape) {
                ((SnapBendpointRequest) getSourceRequest()).setSnapToAllShape(true);
            } else {
                ((SnapBendpointRequest) getSourceRequest()).setSnapToAllShape(false);
            }
        }
        if (getSourceRequest() instanceof LocationRequest) {
            if (moveGroupActivated) {
                getSourceRequest().getExtendedData().put(MoveEdgeGroupManager.EDGE_GROUP_MOVE_KEY, Boolean.TRUE);
                getSourceRequest().getExtendedData().put(MoveEdgeGroupManager.EDGE_MOVE_DELTA, computeDelta((LocationRequest) getSourceRequest()));
                getSourceRequest().getExtendedData().put(MoveEdgeGroupManager.EDGE_GROUP_MOVE_HAS_BEEN_ACTIVATED_KEY, Boolean.TRUE);
            } else {
                getSourceRequest().getExtendedData().put(MoveEdgeGroupManager.EDGE_GROUP_MOVE_KEY, Boolean.FALSE);
                getSourceRequest().getExtendedData().remove(MoveEdgeGroupManager.EDGE_MOVE_DELTA);
            }
        }
        super.updateSourceRequest();
    }

    private Dimension computeDelta(LocationRequest bendpointRequest) {
        Point newLocation = bendpointRequest.getLocation();
        Dimension diff;
        if (newLocation != null) {
            diff = newLocation.getDifference(getStartLocation());
        } else {
            diff = getDragMoveDelta();
        }
        return diff;
    }

    /**
     * {@inheritDoc}
     * 
     * Cleans up the mode to original state.
     * 
     * @see org.eclipse.gef.tools.ResizeTracker#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        // Clean up the mode to original state.
        snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
        moveGroupActivated = false;
        return result;
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
}
