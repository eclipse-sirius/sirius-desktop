/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.bracket.handles;

import org.eclipse.draw2d.BendpointLocator;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.handles.BendpointHandle;
import org.eclipse.gef.tools.ConnectionBendpointTracker;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.locators.BendpointRotateLocator;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;

/**
 * A BendpointHandle that is used to rotate an existing bendpoint.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BendpointRotateHandle extends BendpointHandle {

    /** The {@link BendpointLocator} . */
    private BendpointLocator bendpointLocator;

    /**
     * Creates a new BendpointMoveHandle, sets its owner to <code>owner</code>
     * and its index to <code>index</code>, and sets its locator to a new
     * {@link BendpointLocator}.
     * 
     * @param owner
     *            the ConnectionEditPart owner
     * @param index
     *            the index
     */
    public BendpointRotateHandle(ConnectionEditPart owner, int index) {
        super();
        setOwner(owner);
        setIndex(index);
        this.bendpointLocator = new BendpointRotateLocator(getConnection(), index);
        setLocator(bendpointLocator);
        setCursor(SharedCursors.SIZEALL);
    }

    /**
     * Creates and returns a new {@link ConnectionBendpointTracker}.
     * 
     * @return the new ConnectionBendpointTracker
     */
    protected DragTracker createDragTracker() {
        ConnectionBendpointTracker tracker;
        tracker = new ConnectionBendpointTracker((ConnectionEditPart) getOwner(), getIndex());
        tracker.setType(RequestConstants.REQ_ROTATE_BENDPOINT);
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }

}
