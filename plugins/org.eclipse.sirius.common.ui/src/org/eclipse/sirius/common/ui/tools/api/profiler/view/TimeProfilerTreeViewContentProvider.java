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

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;

/**
 * the content provider of {@link TimeProfilerView}.
 * 
 * @author mchauvin
 */
public class TimeProfilerTreeViewContentProvider implements ITreeContentProvider {

    private TimeProfiler profiler = DslCommonPlugin.PROFILER;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public Object getParent(final Object element) {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public Object[] getChildren(final Object parentElement) {
        final List<TreeItemWrapper> children = ((TimeProfilerViewItem) parentElement).getTreeItemWrapper().getChildren();
        return convertToViewItems(children);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public boolean hasChildren(final Object element) {
        return ((TimeProfilerViewItem) element).getTreeItemWrapper().getChildren().size() > 0;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        // empty
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // empty
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement == TreeItemWrapper.ROOT_ITEM) {
            return convertToViewItems(TreeItemWrapper.ROOT_ITEM.getChildren());
        }
        return new Object[] {};
    }

    private TimeProfilerViewItem[] convertToViewItems(final List<TreeItemWrapper> treeItemWrappers) {
        final TimeProfilerViewItem[] result = new TimeProfilerViewItem[treeItemWrappers.size()];
        final Iterator<TreeItemWrapper> iterTreeItemWrapper = treeItemWrappers.iterator();
        int i = 0;
        while (iterTreeItemWrapper.hasNext()) {
            final TreeItemWrapper treeItemWrapper = iterTreeItemWrapper.next();
            final ProfilerTask task = (ProfilerTask) treeItemWrapper.getWrappedObject();
            final long time = profiler.getTimeEllapsed(task);
            final int occurences = profiler.getCountTask(task);
            final TimeProfilerViewItem item = new TimeProfilerViewItem(task, Long.valueOf(time), Integer.valueOf(occurences), treeItemWrapper);
            result[i++] = item;
        }
        return result;
    }

}
