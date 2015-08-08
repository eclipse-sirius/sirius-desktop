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
package org.eclipse.sirius.diagram.ui.internal.edit.handles;

import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gmf.runtime.gef.ui.internal.handles.LineSegMoveInvisibleHandle;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.ConnectionBendpointTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SiriusConnectionBendpointTrackerEx;

/**
 * A LineSegMoveInvisibleHandle Sirius extension to handle the move edge group
 * feature. See bug #471104 relevant ticket or specification for more details.
 * 
 * @author Florian Barbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusLineSegMoveInvisibleHandle extends LineSegMoveInvisibleHandle {

    public SiriusLineSegMoveInvisibleHandle(ConnectionEditPart owner, int index) {
        super(owner, index);
    }

    /**
     * A copy of
     * {@link org.eclipse.gmf.runtime.gef.ui.internal.handles.LineSegMoveInvisibleHandle#createDragTracker()}
     * to use the SiriusConnectionBendpointTrackerEx instead of the GMF one.
     */
    @Override
    protected DragTracker createDragTracker() {
        ConnectionBendpointTrackerEx tracker;
        tracker = new SiriusConnectionBendpointTrackerEx((ConnectionEditPart) getOwner(), getIndex()) {

            /**
             * Called once the drag has been interpreted. This is where the real
             * work of the drag is carried out. By default, the current command
             * is executed.
             */
            @Override
            protected void performDrag() {
                setCurrentCommand(getCommand());
                executeCurrentCommand();
            }
        };
        tracker.setType(RequestConstants.REQ_CREATE_BENDPOINT);
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }

}
