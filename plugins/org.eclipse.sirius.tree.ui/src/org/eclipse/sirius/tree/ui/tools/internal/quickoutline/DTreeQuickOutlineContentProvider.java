/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.quickoutline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItemContainer;

/**
 * A Tree Quick Outline specific {@link ITreeContentProvider}.
 * 
 * @author ymortier
 */
public class DTreeQuickOutlineContentProvider implements ITreeContentProvider {

    private static final Object[] EMPTY = {};

    @Override
    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof DTree) {
            DTree tree = (DTree) inputElement;
            return tree.getOwnedTreeItems().toArray();
        }
        return EMPTY;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof DTreeItemContainer) {
            return ((DTreeItemContainer) parentElement).getOwnedTreeItems().toArray();
        }
        return EMPTY;
    }

    @Override
    public Object getParent(Object element) {
        Object result;
        if (element instanceof DTree) {
            result = null;
        } else if (element instanceof EObject) {
            result = ((EObject) element).eContainer();
        } else {
            result = null;
        }
        return result;
    }

    @Override
    public boolean hasChildren(Object element) {
        boolean result = false;
        if (element instanceof DTreeItemContainer) {
            DTreeItemContainer treeItemContainer = (DTreeItemContainer) element;
            result = !treeItemContainer.getOwnedTreeItems().isEmpty();
        }
        return result;
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // nothing to do.
    }

    @Override
    public void dispose() {
        // empty.
    }

}
