/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.statusline;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;

/**
 * Class to handle mouse listeners.
 */
public class MouseListeners implements MouseMoveListener, MouseTrackListener {

    private final EditPartViewer viewer;

    /**
     * Constructor.
     * 
     * @param viewer
     *            Viewer to use
     */
    public MouseListeners(final EditPartViewer viewer) {
        this.viewer = viewer;
    }

    public void mouseMove(final MouseEvent e) {
        final org.eclipse.swt.graphics.Point mouseLocation = e.display.getCursorLocation();

        final FigureCanvas control = (FigureCanvas) viewer.getControl();
        final org.eclipse.swt.graphics.Point screenSWTPoint = control.toControl(mouseLocation);

        final Point screenPoint = new Point(screenSWTPoint.x, screenSWTPoint.y);

        // Screen to logical
        // Logical : coordinate system for diagram children
        // equivalent to GMF absolute bounds + Insets
        // scroll : viewport
        // zoom : ScalableFreeformRootEditPart getZoomManager getZoom
        final Point logicalLocation = screenPoint.getCopy();
        ((DiagramEditPart) viewer.getRootEditPart().getContents()).getFigure().translateToRelative(logicalLocation);

        MouseLocationManager.INSTANCE.updateMouseLocation(logicalLocation, screenPoint);
    }

    public void mouseEnter(final MouseEvent e) {
        // Nothing
    }

    public void mouseExit(final MouseEvent e) {
        MouseLocationManager.INSTANCE.updateMouseLocation(null, null);
    }

    public void mouseHover(final MouseEvent e) {
        // Nothing
    }
}
