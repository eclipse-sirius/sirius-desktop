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

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.ConnectionBendpointTrackerEx;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.SnapBendpointRequest;
import org.eclipse.swt.events.KeyEvent;

/**
 * This tracker also allows to change the behavior of SnapToShape (capability to
 * snap to all shapes).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
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
        if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = !NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyDown(event);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyUp(event);
    }

    /**
     * Update the request with information about snapToAll mode.
     */
    @Override
    protected void updateSourceRequest() {
        if (getSourceRequest() instanceof SnapBendpointRequest) {
            if (snapToAllShape) {
                ((SnapBendpointRequest) getSourceRequest()).setSnapToAllShape(true);
            } else {
                ((SnapBendpointRequest) getSourceRequest()).setSnapToAllShape(false);
            }
        }
        super.updateSourceRequest();
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
        return result;
    }
}
