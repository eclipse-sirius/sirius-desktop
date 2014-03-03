/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;

/**
 * The provider for the content of the tree.
 * 
 * @author nlepine
 */
public class DTreeContentProvider implements ITreeContentProvider {

    /**
     * Returns the elements to display in the viewer (only the visible one).
     * 
     * @param inputElement
     *            the input element
     * @return the array of elements to display in the viewer
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof DTree) {
            final List<DTreeItem> visibleLines = getVisibleLines((DTree) inputElement);
            return visibleLines.toArray();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#inputChanged(Viewer,
     *      Object, Object)
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(final Object parentElement) {
        Object[] result = null;
        if (parentElement instanceof DTree) {
            final List<DTreeItem> visibleLines = getVisibleLines((DTree) parentElement);
            result = visibleLines.toArray();
        } else if (parentElement instanceof DTreeItem) {
            final List<DTreeItem> visibleLines = getVisibleLines((DTreeItem) parentElement);
            result = visibleLines.toArray();
        }
        return result;
    }

    /**
     * Returns the visible items of the given <@link DTreeItemContainer
     * lineContainer>.
     * <p>
     * 
     * @param treeItemContainer
     *            the line container
     * @return a list of visible lines
     */
    private List<DTreeItem> getVisibleLines(final DTreeItemContainer treeItemContainer) {
        return treeItemContainer.getOwnedTreeItems();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(final Object element) {
        if (element instanceof DTreeItem) {
            final DTreeItem line = (DTreeItem) element;
            return line.eContainer();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(final Object element) {
        final Object[] children = getChildren(element);
        return children != null && children.length > 0;
    }

    /**
     * Disposes of this content provider.
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
    }

}
