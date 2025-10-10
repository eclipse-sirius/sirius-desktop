/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.sirius.ui.interpreter.internal.language.SplitExpression;
import org.eclipse.sirius.ui.interpreter.internal.language.SubExpression;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;

/**
 * This will be added to the sub-expression viewer in order to launch evaluation of parts of the expression entered by
 * the user as split through the expression splitting thread.
 * 
 * @author <a href="mailto:marwa.rostren@obeo.fr">Marwa Rostren</a>
 */
public class SubExpressionListener implements ISelectionChangedListener {
    private InterpreterView interpreterView;

    /**
     * Constructor.
     * 
     * @param interpreterView
     */
    public SubExpressionListener(InterpreterView interpreterView) {
        this.interpreterView = interpreterView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        if (event.getSelection() instanceof IStructuredSelection) {
            Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
            if (selection instanceof SubExpression) {
                interpreterView.evaluateSubExpression(((SubExpression) selection).getExpression());
            } else if (selection instanceof SplitExpression) {
                interpreterView.evaluateSubExpression(((SplitExpression) selection).getFullExpression());
            }
        }
    }
}
