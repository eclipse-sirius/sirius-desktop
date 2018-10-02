/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gef.ConnectionEditPart;

/**
 * A specific Sirius tracker to override the reveal behavior at selection.
 * 
 * @author lfasani
 *
 */
@SuppressWarnings("restriction")
public class SelectConnectionEditPartTracker extends org.eclipse.gmf.runtime.gef.ui.internal.tools.SelectConnectionEditPartTracker {

    /**
     * Default constructor.
     * 
     * @param owner
     *            ConnectionNodeEditPart that creates and owns the tracker object
     */
    public SelectConnectionEditPartTracker(ConnectionEditPart owner) {
        super(owner);
    }

    @Override
    protected boolean handleButtonUp(int button) {
        // The above code is copied from the super classes (SelectConnectionEditPartTracker and
        // SelectEditPartTracker) to disable the reveal.

        boolean bExecuteDrag = isInState(STATE_DRAG_IN_PROGRESS) && shouldAllowDrag();

        boolean bRet = false;
        if (isInState(STATE_DRAG)) {
            performSelection();
            if (getFlag(MAX_FLAG))
                performDirectEdit();
            // The SelectEditPartTracker behavior is overridden here to never reveal the selected element.
            // if (button == 1 && getSourceEditPart().getSelected() != EditPart.SELECTED_NONE)
            // getCurrentViewer().reveal(getSourceEditPart());
            setState(STATE_TERMINAL);
        }

        if (bExecuteDrag) {
            Method eraseSourceFeedbackMethod;
            try {
                eraseSourceFeedbackMethod = org.eclipse.gmf.runtime.gef.ui.internal.tools.SelectConnectionEditPartTracker.class.getDeclaredMethod("eraseSourceFeedback"); //$NON-NLS-1$
                eraseSourceFeedbackMethod.setAccessible(true);
                eraseSourceFeedbackMethod.invoke(this);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            }
            setCurrentCommand(getCommand());
            executeCurrentCommand();
        }

        return bRet;
    }

}
