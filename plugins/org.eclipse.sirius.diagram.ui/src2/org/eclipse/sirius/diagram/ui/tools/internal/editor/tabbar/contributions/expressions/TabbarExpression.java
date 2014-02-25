/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISources;

/**
 * Abstract tabbar expression to check whether tabbar contribution item should
 * be display according to selection. Sub-classes should implements {@link TabbarExpression#isVisible(IStructuredSelection).
 * 
 * @author fbarbin
 * 
 */
public abstract class TabbarExpression extends Expression {

    @Override
    public EvaluationResult evaluate(IEvaluationContext context) throws CoreException {

        Object variable = context.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
        if (variable instanceof IStructuredSelection) {
            if (isVisible((IStructuredSelection) variable)) {
                return EvaluationResult.TRUE;
            }
        }

        return EvaluationResult.FALSE;
    }

    @Override
    public void collectExpressionInfo(ExpressionInfo info) {
        info.addVariableNameAccess(ISources.ACTIVE_CURRENT_SELECTION_NAME);
        super.collectExpressionInfo(info);
    }

    /**
     * check if tabbar contribution should be visible with current selection.
     * 
     * @param selection
     *            the current selection get from selection variable.
     * @return true if should be visible, otherwise false.
     */
    protected abstract boolean isVisible(IStructuredSelection selection);
}
