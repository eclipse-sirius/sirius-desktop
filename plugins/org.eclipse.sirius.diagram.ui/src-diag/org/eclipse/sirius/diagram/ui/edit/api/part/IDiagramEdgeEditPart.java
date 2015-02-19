/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationPreCommitListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;

/**
 * The edit part for <code>DEdge</code>s.
 * 
 * @author ymortier
 */
public interface IDiagramEdgeEditPart extends IDiagramElementEditPart, INodeEditPart, IPrimaryEditPart, NotificationListener, ConnectionEditPart, ITreeBranchEditPart {

    /**
     * Returns the figure of the connection.
     * 
     * @return the figure of the connection.
     */
    PolylineConnectionEx getPolylineConnectionFigure();

    /**
     * Refreshs the visuals of the connection.
     */
    void refreshVisuals();

    /**
     * Refreshes the source decoration of the connection.
     */
    void refreshSourceDecoration();

    /**
     * Refreshes the target decoration of the connection.
     */
    void refreshTargetDecoration();

    /**
     * Refreshes the foreground color of the connection.
     */
    void refreshForegroundColor();

    /**
     * Refreshes the style of the connection.
     */
    void refreshLineStyle();

    /**
     * Returns the listener that listens the routing style of the connection.
     * 
     * @return the listener that listens the routing style of the connection.
     */
    NotificationPreCommitListener getEAdapterRoutingStyle();

    /**
     * This method is invoked when the routing style of the connection is
     * changed.
     * 
     * @param message
     *            the notification.
     */
    void routingStyleChanged(Notification message);
}
