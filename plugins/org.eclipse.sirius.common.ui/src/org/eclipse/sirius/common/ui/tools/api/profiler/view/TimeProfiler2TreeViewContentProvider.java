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
package org.eclipse.sirius.common.ui.tools.api.profiler.view;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler2;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler2.CompositeTask;

/**
 * Time profiler with tree display.
 * 
 * @author ymortier
 */
public class TimeProfiler2TreeViewContentProvider extends ArrayContentProvider implements ITreeContentProvider {

    private static final Object[] EMPTY_ARRAY = {};

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(final Object parentElement) {
        if (parentElement instanceof CompositeTask) {
            return ((CompositeTask) parentElement).getChildren().toArray();
        }
        return EMPTY_ARRAY;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(final Object element) {
        if (element instanceof CompositeTask) {
            return ((CompositeTask) element).getParent();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ArrayContentProvider#getElements(java.lang.Object)
     */
    @Override
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof TimeProfiler2) {
            return ((TimeProfiler2) inputElement).getRoots().toArray();
        }
        return super.getElements(inputElement);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(final Object element) {
        if (element instanceof CompositeTask) {
            return !((CompositeTask) element).getChildren().isEmpty();
        }
        return false;
    }

}
