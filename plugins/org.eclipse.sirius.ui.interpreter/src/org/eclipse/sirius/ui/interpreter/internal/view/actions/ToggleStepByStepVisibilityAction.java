/*******************************************************************************
 * Copyright (c) 2013, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.actions;

import org.eclipse.sirius.ui.interpreter.internal.IInterpreterConstants;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterImages;
import org.eclipse.sirius.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * This action will be displayed on the interpreter view's toolbar. It will make it possible to show or hide the
 * sub-expressions part of said view.
 * 
 * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
 */
public class ToggleStepByStepVisibilityAction extends Action {
    /** The tooltip we'll show for this action. */
    private static final String TOOLTIP_TEXT = InterpreterMessages.getString("intepreter.action.showstepbystep.tooltip"); //$NON-NLS-1$

    /** Keeps a reference to the interpreter view. */
    private InterpreterView view;

    /**
     * Instantiates our action given the interpreter view holding the composite to hide or show.
     * 
     * @param view
     *            The interpreter view.
     */
    public ToggleStepByStepVisibilityAction(InterpreterView view) {
        super(null, IAction.AS_CHECK_BOX);
        setToolTipText(TOOLTIP_TEXT);
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.STEP_BY_STEP_VISIBILITY_TOGGLE_ICON));
        this.view = view;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        if (view != null) {
            view.toggleStepByStepVisibility();
        }
    }
}
