/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.view.actions;

import org.eclipse.acceleo.ui.interpreter.internal.IInterpreterConstants;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterImages;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.acceleo.ui.interpreter.view.InterpreterView;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

/**
 * This action will be displayed on the interpreter view's toolbar. It will make it possible to show or hide the
 * variable part of said view.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ToggleVariableVisibilityAction extends Action {
    /** The tooltip we'll show for this action. */
    private static final String TOOLTIP_TEXT = InterpreterMessages.getString("intepreter.action.showvariables.tooltip"); //$NON-NLS-1$

    /** Keeps a reference to the interpreter view. */
    private InterpreterView view;

    /**
     * Instantiates our action given the interpreter view holding the composite to hide or show.
     * 
     * @param view
     *            The interpreter view.
     */
    public ToggleVariableVisibilityAction(InterpreterView view) {
        super(null, IAction.AS_CHECK_BOX);
        setToolTipText(TOOLTIP_TEXT);
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.VARIABLE_VISIBILITY_TOGGLE_ICON));
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
            view.toggleVariableVisibility();
        }
    }
}
