/*******************************************************************************
 * Copyright (c) 2009, 2020 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.debug.statusline;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RootEditPart;
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

    @Override
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
        RootEditPart rootEditPart = viewer.getRootEditPart();
        if (rootEditPart != null) {
            EditPart contents = rootEditPart.getContents();
            if (contents instanceof DiagramEditPart) {
                IFigure figure = ((DiagramEditPart) contents).getFigure();
                if (figure != null) {
                    figure.translateToRelative(logicalLocation);
                }
            }
        }

        MouseLocationManager.INSTANCE.updateMouseLocation(logicalLocation, screenPoint);
    }

    @Override
    public void mouseEnter(final MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseExit(final MouseEvent e) {
        MouseLocationManager.INSTANCE.updateMouseLocation(null, null);
    }

    @Override
    public void mouseHover(final MouseEvent e) {
        // Nothing
    }
}
