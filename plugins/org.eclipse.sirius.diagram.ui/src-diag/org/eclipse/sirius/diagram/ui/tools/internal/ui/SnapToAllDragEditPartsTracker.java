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
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

/**
 * A specific dragEditPartTracket that allows to change the behavior of
 * SnapToShape (capability to snap to all shapes and not only brothers one).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class SnapToAllDragEditPartsTracker extends DragEditPartsTrackerEx {

    /**
     * Constant passed to extended data of the request to keep the chosen mode
     * (with KEY {@link #SNAP_TO_ALL}.
     */
    public static final String SNAP_TO_ALL_SHAPE_KEY = "snapToAllShape"; //$NON-NLS-1$

    /**
     * The default mode for {@link #snapToAllShape}.
     */
    public static final boolean DEFAULT_SNAP_TO_SHAPE_MODE = false;

    /**
     * The key shortcut used to change the default behavior of snap to shape.
     */
    public static final int SNAP_TO_ALL = SWT.F4;

    /**
     * The mode of this tracker concerning the snap to shape:
     * <UL>
     * <LI>true to snap to all shapes and not only brothers ones,</LI>
     * <LI>false otherwise.</LI>
     * </UL>
     * This variable is used when the request is updated to add an extended data
     * to the request.
     */
    boolean snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;

    /**
     * Default constructor.
     * 
     * @param sourceEditPart
     *            the source edit part
     */
    public SnapToAllDragEditPartsTracker(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * Overridden to update the {@link ChangeBoundsRequest} with information
     * about snapToAll mode.
     * 
     * {@inheritDoc}
     */
    @Override
    protected void snapPoint(ChangeBoundsRequest request) {
        if (snapToAllShape) {
            getTargetRequest().getExtendedData().put(SnapToAllDragEditPartsTracker.SNAP_TO_ALL_SHAPE_KEY, Boolean.TRUE);
        } else {
            getTargetRequest().getExtendedData().put(SnapToAllDragEditPartsTracker.SNAP_TO_ALL_SHAPE_KEY, Boolean.FALSE);
        }
        super.snapPoint(request);
    }

    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        if (SnapToAllDragEditPartsTracker.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = !SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyDown(event);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        if (SnapToAllDragEditPartsTracker.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyUp(event);
    }

    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        // Clean up the mode to original state.
        snapToAllShape = SnapToAllDragEditPartsTracker.DEFAULT_SNAP_TO_SHAPE_MODE;
        return result;
    }
}
