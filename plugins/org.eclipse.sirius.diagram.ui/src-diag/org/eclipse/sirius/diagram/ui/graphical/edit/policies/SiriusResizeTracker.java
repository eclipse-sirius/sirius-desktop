/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

/**
 * Override of {@link ResizeTracker} to allow a resize that also moves all
 * children. The previous behavior (the one of original {@link ResizeTracker})
 * is accessible with the shortcut F3.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class SiriusResizeTracker extends ResizeTracker {

    /**
     * Constant passed to extended data of the request to keep the location of
     * the children of resized part fix.
     */
    public static final String CHILDREN_MOVE_MODE_KEY = "keepSameAbsoluteLocation";

    /**
     * The default mode for {@link #childrenMoveMode}.
     */
    public static final boolean DEFAULT_CHILDREN_MOVE_MODE = true;

    /**
     * The key shortcut used to change the default behavior of childrenMoveMode.
     * 'r' as relative : the position of the children would remain relative to
     * the resized container
     */
    protected static final int CHILDREN_MOVE_MODE_SHORTCUT_KEY = SWT.F3;

    /**
     * The mode of the resize tracker:
     * <UL>
     * <LI>true when the children of the resized element should stay at the same
     * absolute location,</LI>
     * <LI>false otherwise.</LI>
     * </UL>
     * This variable is used when the request is updated to add an extended data
     * to the request.
     */
    boolean childrenMoveMode = DEFAULT_CHILDREN_MOVE_MODE;

    /**
     * Default constructor.
     * 
     * @param owner
     *            of the resize handle which returned this tracker
     * @param direction
     *            the direction
     */
    public SiriusResizeTracker(GraphicalEditPart owner, int direction) {
        super(owner, direction);
    }

    @Override
    protected boolean handleKeyDown(KeyEvent event) {
        if (SiriusResizeTracker.CHILDREN_MOVE_MODE_SHORTCUT_KEY == event.keyCode) {
            childrenMoveMode = !SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE;
            return true;
        }
        return super.handleKeyDown(event);
    }

    @Override
    protected boolean handleKeyUp(KeyEvent event) {
        if (SiriusResizeTracker.CHILDREN_MOVE_MODE_SHORTCUT_KEY == event.keyCode) {
            childrenMoveMode = SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE;
            return true;
        }
        return super.handleKeyUp(event);
    }

    /**
     * Set {@link CHILDREN_MOVE_MODE_KEY} extended data after update of request
     * (the extended data are cleaned during the
     * {@link ResizeTracker#updateSourceRequest()}).
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void updateSourceRequest() {
        super.updateSourceRequest();
        if (childrenMoveMode) {
            getSourceRequest().getExtendedData().put(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY, Boolean.TRUE);
        } else {
            getSourceRequest().getExtendedData().put(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY, Boolean.FALSE);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Cleans up the mode to original state.
     * 
     * @see org.eclipse.gef.tools.ResizeTracker#handleButtonUp(int)
     */
    @Override
    protected boolean handleButtonUp(int button) {
        boolean result = super.handleButtonUp(button);
        childrenMoveMode = SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE;
        return result;
    }
}
