/*******************************************************************************
 * Copyright (c) 2008, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.handles;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.handles.ConnectionHandle;
import org.eclipse.gef.tools.ConnectionEndpointTracker;

/**
 * Class copy from GMF 3.5 (org.eclipse.gef.handles.ConnectionEndpointHandle) because this class don't exist in 3.3.
 * 
 * A handle used at the start or end of the
 * {@link org.eclipse.draw2d.Connection}. A ConnectionEndpointHandle may be
 * extended rather than using the final
 * 
 * @author Anthony Hunter
 * @since 0.9.0
 */
public class ConnectionEndpointHandle extends ConnectionHandle {

    /**
     * Caches whether the handle is for the source or target endpoint. endPoint
     * is either {@link ConnectionLocator#SOURCE} or
     * {@link ConnectionLocator#TARGET}.
     */
    private int endPoint;

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
    public ConnectionEndpointHandle(ConnectionEditPart owner, int endPoint) {
        setOwner(owner);
        Assert.isTrue(endPoint == ConnectionLocator.SOURCE || endPoint == ConnectionLocator.TARGET);
        this.endPoint = endPoint;
        setLocator(new ConnectionLocator(getConnection(), endPoint));
    }

    /**
     * Creates a new ConnectionStartHandle and sets its owner to
     * <code>owner</code>. If the handle is fixed, it cannot be dragged.
     * 
     * @param owner
     *            the ConnectionEditPart owner
     * @param fixed
     *            if true, handle cannot be dragged.
     * @param endPoint
     *            one of {@link ConnectionLocator#SOURCE} or
     *            {@link ConnectionLocator#TARGET}.
     */
    public ConnectionEndpointHandle(ConnectionEditPart owner, boolean fixed, int endPoint) {
        super(fixed);
        setOwner(owner);
        Assert.isTrue(endPoint == ConnectionLocator.SOURCE || endPoint == ConnectionLocator.TARGET);
        this.endPoint = endPoint;
        setLocator(new ConnectionLocator(getConnection(), endPoint));
    }

    /**
     * Creates a new ConnectionStartHandle.
     * 
     * @param endPoint
     *            one of {@link ConnectionLocator#SOURCE} or
     *            {@link ConnectionLocator#TARGET}.
     */
    public ConnectionEndpointHandle(int endPoint) {
        Assert.isTrue(endPoint == ConnectionLocator.SOURCE || endPoint == ConnectionLocator.TARGET);
        this.endPoint = endPoint;
    }

    /**
     * Creates and returns a new {@link ConnectionEndpointTracker}.
     * 
     * @return the new ConnectionEndpointTracker
     */
    protected DragTracker createDragTracker() {
        if (isFixed())
            return null;
        ConnectionEndpointTracker tracker;
        tracker = new ConnectionEndpointTracker((ConnectionEditPart) getOwner());
        if (endPoint == ConnectionLocator.SOURCE) {
            tracker.setCommandName(RequestConstants.REQ_RECONNECT_SOURCE);
        } else {
            tracker.setCommandName(RequestConstants.REQ_RECONNECT_TARGET);
        }
        tracker.setDefaultCursor(getCursor());
        return tracker;
    }

    /**
     * Return the endpoint handle.
     * 
     * @return the endPoint handle, which is is either
     *         {@link ConnectionLocator#SOURCE} or
     *         {@link ConnectionLocator#TARGET}.
     * @since 0.9.0
     */
    public int getEndPoint() {
        return endPoint;
    }

}
