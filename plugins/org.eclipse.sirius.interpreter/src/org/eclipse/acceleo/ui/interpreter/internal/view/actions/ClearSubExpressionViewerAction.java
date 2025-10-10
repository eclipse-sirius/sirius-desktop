/*******************************************************************************
 * Copyright (c) 2014, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.view.actions;

import org.eclipse.jface.viewers.Viewer;

/**
 * This action will be used to clear the "Sub-expressions" viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ClearSubExpressionViewerAction extends AbstractClearViewerAction {
    /**
     * Instantiates the "clear" action given the viewer it should operate on.
     * 
     * @param viewer
     *            The viewer that should be cleared through this action.
     */
    public ClearSubExpressionViewerAction(Viewer viewer) {
        super(viewer);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        if (getViewer() != null) {
            getViewer().setInput(null);
        }
    }
}
