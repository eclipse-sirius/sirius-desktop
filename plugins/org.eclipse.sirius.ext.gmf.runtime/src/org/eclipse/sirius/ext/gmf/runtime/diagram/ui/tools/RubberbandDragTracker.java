/******************************************************************************
 * Copyright (c) 2004, 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
// CHECKSTYLE:OFF
package org.eclipse.sirius.ext.gmf.runtime.diagram.ui.tools;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.SharedCursors;
import org.eclipse.swt.events.MouseEvent;

/**
 * @author tisrar
 * @canBeSeenBy org.eclipse.gmf.runtime.diagram.ui.*
 * 
 * 
 */
public class RubberbandDragTracker extends RubberbandSelectionTool implements MoveInDiagramDragTracker {

    private Point previousMouseLocation;

    @Override
    protected boolean handleButtonDown(int button) {
        if (button == 2) {
            setCursor(SharedCursors.HAND);
            return stateTransition(STATE_INITIAL, STATE_SCROLL_DIAGRAM);
        } else {
            return super.handleButtonDown(button);
        }
    }

    @Override
    public void mouseDrag(MouseEvent me, EditPartViewer viewer) {
        previousMouseLocation = getCurrentInput().getMouseLocation().getCopy();
        super.mouseDrag(me, viewer);
    }

    @Override
    protected boolean handleDragStarted() {
        if (isInState(STATE_SCROLL_DIAGRAM)) {
            return stateTransition(STATE_SCROLL_DIAGRAM, STATE_SCROLL_DIAGRAM_IN_PROGRESS);
        }
        return super.handleDragStarted();
    }

    @Override
    protected boolean handleDragInProgress() {
        if (isInState(STATE_SCROLL_DIAGRAM_IN_PROGRESS)) {
            if (getCurrentViewer().getControl() instanceof FigureCanvas) {
                FigureCanvas figureCanvas = (FigureCanvas) getCurrentViewer().getControl();
                Point currentMouseLocation = getCurrentInput().getMouseLocation();
                Dimension difference = previousMouseLocation.getDifference(currentMouseLocation);
                Point location = figureCanvas.getViewport().getViewLocation();
                figureCanvas.scrollTo(location.x + difference.width, location.y + difference.height);
            }
            return true;
        }
        return super.handleDragInProgress();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gef.tools.AbstractTool#handleFinished()
     */
    @Override
    protected void handleFinished() {
        // nothing goes here

    }

}
// CHECKSTYLE:ON
