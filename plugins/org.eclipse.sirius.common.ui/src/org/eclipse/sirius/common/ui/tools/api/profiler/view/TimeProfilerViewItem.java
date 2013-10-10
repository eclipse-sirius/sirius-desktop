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

import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.tools.api.util.ImageProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Entry of a {@link TimeProfilerView}.
 * 
 * @author ymortier
 */
public class TimeProfilerViewItem {

    /** The task. */
    private ProfilerTask task;

    /** The time in ms. */
    private Long time;

    /** The number of occurences. */
    private Integer occurences;

    /** The tree item wrapper */
    private TreeItemWrapper wrapper;

    /**
     * Create a {@link TimeProfilerViewItem}.
     * 
     * @param task
     *            the task.
     * @param time
     *            the time.
     * @param occurences
     *            the occurences of the task.
     */
    public TimeProfilerViewItem(final ProfilerTask task, final Long time, final Integer occurences) {
        this.task = task;
        this.time = time;
        this.occurences = occurences;
    }

    /**
     * Create a {@link TimeProfilerViewItem}.
     * 
     * @param task
     *            the task.
     * @param time
     *            the time.
     * @param occurences
     *            the occurences of the task.
     * @param wrapper
     *            the tree item wrapper
     */
    public TimeProfilerViewItem(final ProfilerTask task, final Long time, final Integer occurences, final TreeItemWrapper wrapper) {
        this(task, time, occurences);
        this.wrapper = wrapper;
    }

    /**
     * Return the task.
     * 
     * @return the task.
     */
    public ProfilerTask getTask() {
        return task;
    }

    /**
     * Return the time in ms.
     * 
     * @return the time in ms.
     */
    public Long getTime() {
        return time;
    }

    /**
     * Return the image of the category.
     * 
     * @return the image of the category.
     */
    public Image getCategoryImage() {
        if (this.task.getCategoryImage() != null) {
            return ImageProvider.getImageFromPath(this.task.getCategoryImage());
        } else {
            return null;
        }
    }

    /**
     * Return the image of the task.
     * 
     * @return the image of the task.
     */
    public Image getTaskImage() {
        if (this.task.getTaskImage() != null) {
            return ImageProvider.getImageFromPath(this.task.getTaskImage());
        } else {
            return null;
        }
    }

    /**
     * Return the occurences of the task.
     * 
     * @return the occurences of the task.
     */
    public Integer getOccurences() {
        return occurences;
    }

    /**
     * Get the tree item wrapper.
     * 
     * @return the tree item wrapper.
     */
    public TreeItemWrapper getTreeItemWrapper() {
        return this.wrapper;
    }

}
