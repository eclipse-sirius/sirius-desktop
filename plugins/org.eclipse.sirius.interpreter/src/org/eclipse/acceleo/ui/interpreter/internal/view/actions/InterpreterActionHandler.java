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

import org.eclipse.acceleo.ui.interpreter.view.InterpreterView;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This will be used as the action handler for the "evaluate" action of the interpreter view.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class InterpreterActionHandler extends AbstractHandler {
    /** Action ID of the "Evaluate" action. Must be kept in sync with the plugin.xml declaration. */
    public static final String EVALUATE_ACTION_ID = "org.eclipse.acceleo.ui.interpreter.evaluateaction"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        if (EVALUATE_ACTION_ID.equals(event.getCommand().getId())) {
            final IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
            if (activePart instanceof InterpreterView) {
                EvaluateAction action = new EvaluateAction();
                action.initialize((InterpreterView) activePart);
                action.run();
            }
        }
        return null;
    }
}
