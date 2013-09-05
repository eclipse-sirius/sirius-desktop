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
package org.eclipse.sirius.common.tools.api.profiler;

/**
 * An event sent by the {@link TimeProfiler}.
 * 
 * @author ymortier
 */
public class ProfilerEvent {

    /** The task of the event. */
    private ProfilerTask task;

    /** The profiler. */
    private TimeProfiler profiler;

    /**
     * Create an event with the specified task and profiler.
     * 
     * @param task
     *            the task;
     * @param profiler
     *            the profiler.
     */
    public ProfilerEvent(final ProfilerTask task, final TimeProfiler profiler) {
        this.task = task;
        this.profiler = profiler;
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
     * The profiler.
     * 
     * @return the profiler.
     */
    public TimeProfiler getProfiler() {
        return profiler;
    }

}
