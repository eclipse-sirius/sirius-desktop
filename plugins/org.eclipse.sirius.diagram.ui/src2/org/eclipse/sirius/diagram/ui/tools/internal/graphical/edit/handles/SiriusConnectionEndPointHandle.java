/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.handles;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.SiriusConnectionEndPointTracker;

/**
 * Override ConnectionEndPointHandle to call our connection end point tracker
 * {@link SiriusConnectionEndPointTracker} and extend our Connection end
 * point handle {@link ConnectionEndpointHandle} (because don't exist in 3.3).
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class SiriusConnectionEndPointHandle extends ConnectionEndpointHandle {

    /**
     * Creates a new ConnectionStartHandle, sets its owner to <code>owner</code>
     * , and sets its locator to a {@link ConnectionLocator}.
     * 
     * @param owner
     *            the ConnectionEditPart owner
     * @param endPoint
     *            one of {@link ConnectionLocator#SOURCE} or
     *            {@link ConnectionLocator#TARGET}.
     */
    public SiriusConnectionEndPointHandle(ConnectionEditPart owner, int endPoint) {
        super(owner, endPoint);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.graphical.edit.handles.ConnectionEndpointHandle#createDragTracker()
     */
    @Override
    protected DragTracker createDragTracker() {
        if (isFixed())
            return null;
        SiriusConnectionEndPointTracker tracker;
        tracker = new SiriusConnectionEndPointTracker((ConnectionEditPart) getOwner());
        if (getEndPoint() == ConnectionLocator.SOURCE) {
            tracker.setCommandName(RequestConstants.REQ_RECONNECT_SOURCE);
        } else {
            tracker.setCommandName(RequestConstants.REQ_RECONNECT_TARGET);
        }
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }

}
