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

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.ClearExpressionViewerAction;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.EvaluateAction;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * This class will be used in order to populate the right-click menu of the Expression viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ExpressionMenuListener implements IMenuListener {
    /** The viewer on which this menu listener operates. */
    private final SourceViewer sourceViewer;

    private final InterpreterView interpreterView;

    /**
     * Creates this menu listener given the viewer on which it operates.
     * 
     * @param sourceViewer
     *            The viewer on which this menu listener operates.
     */
    public ExpressionMenuListener(SourceViewer sourceViewer, InterpreterView interpreterView) {
        this.sourceViewer = sourceViewer;
        this.interpreterView = interpreterView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    public void menuAboutToShow(IMenuManager manager) {
        manager.add(new Separator("org.eclipse.ui.interpreter.view.expression.menu")); //$NON-NLS-1$
        manager.add(new EvaluateAction(interpreterView));
        manager.add(new ClearExpressionViewerAction(sourceViewer));
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }
}
