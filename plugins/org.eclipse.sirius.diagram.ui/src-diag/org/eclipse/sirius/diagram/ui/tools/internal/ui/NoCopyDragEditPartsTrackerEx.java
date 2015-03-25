/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
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
 * A specific dragEditPartTracket that disable the clone feature. Indeed, in
 * Sirius it's not natural to clone a graphical element that will be removed on
 * the next refresh.
 * 
 * This tracker also allows to change the behavior of SnapToShape (capability to
 * snap to all shapes and not only brothers one).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class NoCopyDragEditPartsTrackerEx extends DragEditPartsTrackerEx {

    /**
     * Constant passed to extended data of the request to keep the chosen mode
     * (with KEY {@link #SNAP_TO_ALL}.
     */
    public static final String SNAP_TO_ALL_SHAPE_KEY = "snapToAllShape";

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
    boolean snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;

    /**
     * Defaul constructor.
     * 
     * @param sourceEditPart
     *            the source edit part
     */
    public NoCopyDragEditPartsTrackerEx(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * Always disable the clone with Ctrl key in Sirius because it only clone
     * the graphical element and not the semantic element.
     * 
     * @param cloneActive
     *            true if cloning should be active (never considered here)
     * @see org.eclipse.gef.tools.DragEditPartsTracker#setCloneActive(boolean)
     */
    @Override
    protected void setCloneActive(boolean cloneActive) {
        super.setCloneActive(false);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx#reveal
     * (org.eclipse.gef.EditPart)
     */
    @Override
    protected void reveal(EditPart editpart) {
        // In Sirius, the drag'n'drop can change (delete and create a new
        // one) the previous container of the drag'n'droped element. In this
        // case, the reveal causes a NPE because the hierarchy of edit part is
        // broken.
        if (editpart.getRoot() != null) {
            super.reveal(editpart);
        }
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
            getTargetRequest().getExtendedData().put(NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL_SHAPE_KEY, Boolean.TRUE);
        } else {
            getTargetRequest().getExtendedData().put(NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL_SHAPE_KEY, Boolean.FALSE);
        }
        super.snapPoint(request);
    }

    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = !NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyDown(event);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        if (NoCopyDragEditPartsTrackerEx.SNAP_TO_ALL == event.keyCode) {
            snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
            return true;
        }
        return super.handleKeyUp(event);
    }

    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        // Clean up the mode to original state.
        snapToAllShape = NoCopyDragEditPartsTrackerEx.DEFAULT_SNAP_TO_SHAPE_MODE;
        return result;
    }
}
