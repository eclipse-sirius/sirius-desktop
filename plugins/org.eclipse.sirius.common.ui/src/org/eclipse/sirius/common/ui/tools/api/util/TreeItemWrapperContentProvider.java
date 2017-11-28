/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.api.util;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;

/**
 * the content provider of {@link TreeItemWrapper}.
 * 
 * @author mporhel
 */
public class TreeItemWrapperContentProvider extends ArrayContentProvider implements ITreeContentProvider {

    private static final Object[] EMPTY_ARRAY = {};

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(final Object element) {
        return ((TreeItemWrapper) element).getParent();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof TreeItemWrapper) {
            return ((TreeItemWrapper) parentElement).getChildren().toArray();
        }
        return EMPTY_ARRAY;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(final Object element) {
        return ((TreeItemWrapper) element).getChildren().size() > 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // empty
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof TreeItemWrapper) {
            return ((TreeItemWrapper) inputElement).getChildren().toArray();
        }
        return super.getElements(inputElement);
    }
}
