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

import org.eclipse.sirius.ui.interpreter.internal.view.actions.ClearResultViewerAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * This class will be used in order to populate the right-click menu of the result viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ResultMenuListener implements IMenuListener {
    /** The viewer on which this menu listener operates. */
    private Viewer resultViewer;

    /**
     * Creates this menu listener given the viewer on which it operates.
     * 
     * @param resultViewer
     *            The viewer on which this menu listener operates.
     */
    public ResultMenuListener(Viewer resultViewer) {
        this.resultViewer = resultViewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    public void menuAboutToShow(IMenuManager manager) {
        manager.add(new ClearResultViewerAction(resultViewer));
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }
}
