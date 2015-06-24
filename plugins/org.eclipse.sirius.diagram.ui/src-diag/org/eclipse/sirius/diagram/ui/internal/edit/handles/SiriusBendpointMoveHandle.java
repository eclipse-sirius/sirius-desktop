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

import org.eclipse.draw2d.Locator;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gmf.runtime.gef.ui.internal.handles.BendpointMoveHandleEx;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.ConnectionBendpointTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SiriusConnectionBendpointTrackerEx;

/**
 * A specific BendpointMoveHandleEx to use SiriusConnectionBendpointTrackerEx as
 * tracker.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusBendpointMoveHandle extends BendpointMoveHandleEx {

    /**
     * Default constructor.
     * 
     * @param owner
     *            the ConnectionEditPart owner
     * @param index
     *            the index
     * @param locator
     *            the Locator
     */
    public SiriusBendpointMoveHandle(ConnectionEditPart owner, int index, Locator locator) {
        super(owner, index, locator);
    }

    /**
     * Creates and returns a new {@link ConnectionBendpointTrackerEx}.
     * 
     * @return the new ConnectionBendpointTrackerEx
     */
    @Override
    protected DragTracker createDragTracker() {
        ConnectionBendpointTrackerEx tracker;
        tracker = new SiriusConnectionBendpointTrackerEx((ConnectionEditPart) getOwner(), getIndex());
        tracker.setType(RequestConstants.REQ_MOVE_BENDPOINT);
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }
}
