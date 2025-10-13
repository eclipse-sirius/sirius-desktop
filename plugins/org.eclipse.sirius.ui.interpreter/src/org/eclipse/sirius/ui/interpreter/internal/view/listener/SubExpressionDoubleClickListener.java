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

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * This will allow us to react to double click events in the sub-expressions view in order to expand when needed.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class SubExpressionDoubleClickListener implements IDoubleClickListener {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
     */
    public void doubleClick(DoubleClickEvent event) {
        ISelection selection = event.getSelection();
        if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
            return;
        }
        final Object target = ((IStructuredSelection) selection).getFirstElement();
        if (event.getViewer() instanceof TreeViewer && ((TreeViewer) event.getViewer()).isExpandable(target)) {
            final TreeViewer viewer = (TreeViewer) event.getViewer();
            if (selection instanceof ITreeSelection) {
                TreePath[] paths = ((ITreeSelection) selection).getPathsFor(target);
                for (int i = 0; i < paths.length; i++) {
                    viewer.setExpandedState(paths[i], !viewer.getExpandedState(paths[i]));
                }
            } else {
                viewer.setExpandedState(target, !viewer.getExpandedState(target));
            }
        }
    }
}
