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
 * This action will be displayed on the interpreter view's toolbar. It will make it possible to enable or disable the
 * real-time evaluation of expressions.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ToggleRealTimeAction extends Action {
    /** The tooltip we'll show for this action. */
    private static final String TOOLTIP_TEXT = InterpreterMessages.getString("intepreter.action.realtime.tooltip"); //$NON-NLS-1$

    /** Keeps a reference to the interpreter view. */
    private InterpreterView view;

    /**
     * Instantiates the real-time enablement toggle given the interpreter view in should operate on.
     * 
     * @param view
     *            The view on which this action operates.
     */
    public ToggleRealTimeAction(InterpreterView view) {
        super(null, IAction.AS_CHECK_BOX);
        setToolTipText(TOOLTIP_TEXT);
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.REALTIME_TOGGLE_ICON));
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
            view.toggleRealTime();
        }
    }
}
