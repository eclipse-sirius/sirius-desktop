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
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.profiler.TimeProfiler;

/**
 * the content provider of {@link TimeProfilerView}.
 * 
 * @author ymortier
 */
public class TimeProfilerViewContentProvider implements IStructuredContentProvider {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(final Object inputElement) {
        final TimeProfiler profiler = DslCommonPlugin.PROFILER;
        final Set<ProfilerTask> tasks = profiler.getTasks();
        final TimeProfilerViewItem[] result = new TimeProfilerViewItem[tasks.size()];
        final Iterator<ProfilerTask> iterTasks = tasks.iterator();
        int i = 0;
        while (iterTasks.hasNext()) {
            final ProfilerTask task = iterTasks.next();
            final long time = profiler.getTimeEllapsed(task);
            final int occurences = profiler.getCountTask(task);
            final TimeProfilerViewItem item = new TimeProfilerViewItem(task, Long.valueOf(time), Integer.valueOf(occurences));
            result[i++] = item;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        // empty.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        // empty.
    }

}
