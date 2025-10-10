/*******************************************************************************
 * Copyright (c) 2010, 2025 Obeo.
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
 * This action will be called by the interpreter view in order to evaluate an expression, compiling it if necessary.
 * <p>
 * This action is available through the "M1 + M2 + D" (control + shift + D) keyboard shortcut.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class EvaluateAction extends Action {
    /** Name of this action. */
    private static final String NAME = InterpreterMessages.getString("interpreter.action.evaluate.name"); //$NON-NLS-1$

    /** The tooltip we'll show for this action. */
    private static final String TOOLTIP_TEXT = InterpreterMessages.getString("interpreter.action.evaluate.tooltip"); //$NON-NLS-1$

    /** References the interpreter from which the action was triggered. */
    private InterpreterView interpreterView;

    /**
     * Instantiates the evaluate action.
     */
    public EvaluateAction() {
        super(NAME, IAction.AS_PUSH_BUTTON);
        setToolTipText(TOOLTIP_TEXT);
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.EVALUATE_ACTION_ICON));
        setActionDefinitionId(InterpreterActionHandler.EVALUATE_ACTION_ID);
    }

    /**
     * Instantiates the evaluate action.
     * 
     * @param interpreterView
     *            The view on which this action operates.
     */
    public EvaluateAction(InterpreterView interpreterView) {
        this();
        this.interpreterView = interpreterView;
    }

    /**
     * Initializes the evaluation action given the interpreter from which it was triggered.
     * 
     * @param view
     *            The interpreter view from which this evaluation was triggered.
     */
    public void initialize(InterpreterView view) {
        interpreterView = view;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        if (interpreterView != null) {
            interpreterView.compileAndEvaluate();
        }
    }
}
