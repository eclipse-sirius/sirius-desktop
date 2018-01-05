/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.internal.ui.rulers.RulerDragTracker;
import org.eclipse.gef.internal.ui.rulers.RulerEditPart;
import org.eclipse.gef.tools.SimpleDragTracker;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.InsertBlankSpaceGuide;
import org.eclipse.swt.graphics.Cursor;

/**
 * A specific {@link RulerDragTracker} to insert vertical blank space instead of create guide. This
 * {@link RulerDragTracker} is used when Ctrl key is pressed when user clicks in the ruler.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
@SuppressWarnings("restriction")
public class SiriusRulerDragTracker extends SimpleDragTracker {
    /**
     * The RulerEditPart associated to this drag tracker.
     */
    protected RulerEditPart source;

    /**
     * The location where the end-user starts to define the zone to insert blank space.
     */
    private int startLocation;

    /**
     * Same location as <code>startLocation</code> but with consideration of zoom (to correctly draw the feedback).
     */
    private int startLocationForFeedback;

    /**
     * The location where the end-user ends to define the zone to insert blank space.
     */
    private int endLocation;

    /**
     * The feedback figure representing the blank space to insert.
     */
    private InsertBlankSpaceGuide blankSpaceGuide;

    /**
     * Default constructor.
     * 
     * @param source
     *            The RulerEditPart associated to this drag tracker.
     */
    public SiriusRulerDragTracker(RulerEditPart source) {
        this.source = source;
    }

    @Override
    protected Command getCommand() {
        Command result = UnexecutableCommand.INSTANCE;
        // The case (startLocation == 0 && endLocation == 0) corresponds to the case where the user do the first click
        // (without moving).
        if ((startLocation == 0 && endLocation == 0) || (startLocation != 0 && endLocation >= startLocation)) {
            if (source.isHorizontal()) {
                result = ((SiriusRulerEditPart) source).createInsertHorizontalBlankSpaceCommand(startLocation, endLocation - startLocation);
            } else {
                result = ((SiriusRulerEditPart) source).createInsertVerticalBlankSpaceCommand(startLocation, endLocation - startLocation);
            }
        }
        return result;
    }

    @Override
    protected boolean handleButtonDown(int button) {
        startLocation = getCurrentPosition();
        startLocationForFeedback = getCurrentPositionZoomed();
        return super.handleButtonDown(button);
    }

    @Override
    protected boolean handleDragInProgress() {
        endLocation = getCurrentPosition();
        return super.handleDragInProgress();
    }

    /**
     * Execute the command and clean up the recorded location.
     * 
     * @see org.eclipse.gef.internal.ui.rulers.RulerDragTracker#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        // Clean up the recorded location.
        startLocation = 0;
        startLocationForFeedback = 0;
        endLocation = 0;
        return result;
    }

    @Override
    protected Cursor calculateCursor() {
        if (getCommand().canExecute()) {
            return source.isHorizontal() ? SharedCursors.SIZEWE : SharedCursors.SIZENS;
        }
        return SharedCursors.NO;
    }

    @Override
    protected void showSourceFeedback() {
        if (blankSpaceGuide == null) {
            blankSpaceGuide = new InsertBlankSpaceGuide(ColorConstants.blue, !source.isHorizontal());
            getFeedbackLayer().add(blankSpaceGuide);
        }
        Rectangle bounds = getFeedbackLayer().getBounds().getCopy();
        if (source.isHorizontal()) {
            bounds.x = startLocationForFeedback;
            bounds.width = getCurrentPositionZoomed() - startLocationForFeedback;
        } else {
            bounds.y = startLocationForFeedback;
            bounds.height = getCurrentPositionZoomed() - startLocationForFeedback;
        }
        blankSpaceGuide.setBounds(bounds);
    }

    @Override
    protected void eraseSourceFeedback() {
        if (blankSpaceGuide != null) {
            getFeedbackLayer().remove(blankSpaceGuide);
            blankSpaceGuide = null;
        }
    }

    /**
     * Returns the layer used for displaying feedback.
     * 
     * @return the feedback layer
     */
    protected IFigure getFeedbackLayer() {
        return ((SiriusRulerEditPart) source).getFeedbackLayer();
    }

    /**
     * Return the current position in the ruler zoomed (ie if the location is 100 and the zoom is 200%, the returned
     * value will be 200).<BR>
     * Method copied from {@link org.eclipse.gef.internal.ui.rulers.RulerDragTracker#getCurrentPositionZoomed()}.
     * 
     * @return the current position in the ruler.
     */
    protected int getCurrentPositionZoomed() {
        Point pt = getLocation();
        source.getFigure().translateToRelative(pt);
        int position = source.isHorizontal() ? pt.x : pt.y;
        return position;
    }

    /**
     * Return the current position in the ruler.<BR>
     * Method copied from {@link org.eclipse.gef.internal.ui.rulers.RulerDragTracker#getCurrentPosition()}.
     * 
     * @return the current position in the ruler.
     */
    protected int getCurrentPosition() {
        int position = getCurrentPositionZoomed();
        ZoomManager zoomManager = source.getZoomManager();
        if (zoomManager != null) {
            position = (int) Math.round(position / zoomManager.getZoom());
        }
        return position;
    }

    @Override
    protected String getCommandName() {
        return Messages.InsertBlankSpace_cmdName;
    }
}
