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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.sirius.ext.base.Option;

/**
 * The {@link TimeProfiler} is useful to gather time information while executing
 * long process. It should be used in debug mode as a support for analysis.
 * 
 * An example usage is : <br>
 * profiler = new TimeProfiler();<br>
 * ...<br>
 * profiler.startWork("My Work ID");<br>
 * ...<br>
 * profiler.stopWork("My Work ID");<br>
 * ...<br>
 * profiler.getStatus();<br>
 * <br>
 * All the execution times will be cumulated in order to give back a sorted
 * table of where your computation is taking the most time.
 * 
 * @author ymortier
 * 
 */
public class TimeProfiler {

    /**
     * The profiler task registry.
     */
    public static final ProfilerTaskRegistry PROFILER_TASK_REGISTRY = new ProfilerTaskRegistry();

    /**
     * Default line size.
     */
    protected static final int ALIGNMENT_LINE_SIZE = 150;

    /** The cumulated times. */
    protected Map<ProfilerTask, Long> times;

    /** is active. */
    protected boolean isActive;

    /** Count the number of tasks. */
    private Map<ProfilerTask, Integer> countTasks;

    /** The last starting time */
    private Map<ProfilerTask, Long> startTimes;

    /** The listeners. */
    private final List<ProfilerListener> listeners = new ArrayList<ProfilerListener>(3);

    /**
     * Create a new {@link TimeProfiler} inactive by default.
     */
    public TimeProfiler() {
        init();
    }

    /**
     * 
     * @return a Long representing the current time.
     */
    private Long now() {
        return Long.valueOf(new Date().getTime());
    }

    private void initMaps() {
        // if (times == null)
        times = new HashMap<ProfilerTask, Long>();
        // else
        // times.clear();

        // if (startTimes == null)
        startTimes = new HashMap<ProfilerTask, Long>();
        // else
        // startTimes.clear();

        // if (countTasks == null)
        countTasks = new HashMap<ProfilerTask, Integer>();
        // else
        // countTasks.clear();
    }

    /**
     * initialize and clear the {@link TimeProfiler}.
     */
    public void init() {
        initMaps();
        // AcceleoConsole.getDefault().asDefaultForSystemOut();
    }

    /**
     * Tell the {@link TimeProfiler} that the process is starting a new work
     * concerning a given task.
     * 
     * @param profilerTaskKey
     *            the key of the task on which add the work time.
     */
    public void startWork(final String profilerTaskKey) {
        if (isActive) {
            Option<ProfilerTask> option = TimeProfiler.PROFILER_TASK_REGISTRY.get(profilerTaskKey);
            if (option.some()) {
                startWork(option.get());
            }
        }
    }

    /**
     * Tell the {@link TimeProfiler} that the process is starting a new work
     * concerning a given task.
     * 
     * @param task
     *            the task on which add the work time.
     */
    public void startWork(final ProfilerTask task) {
        if (isActive) {
            startTimes.put(task, now());
            final Integer countI = this.countTasks.get(task);
            final int count = countI == null ? 0 : countI.intValue();
            this.countTasks.put(task, Integer.valueOf(count + 1));
            // this.fireTaskStarted(task);
        }
    }

    /**
     * Tell the {@link TimeProfiler} that the process is stopping a new work
     * concerning a given task.
     * 
     * @param profilerTaskKey
     *            the key of task on which add the work time.
     */
    public void stopWork(final String profilerTaskKey) {
        if (isActive) {
            Option<ProfilerTask> option = TimeProfiler.PROFILER_TASK_REGISTRY.get(profilerTaskKey);
            if (option.some()) {
                stopWork(option.get());
            }
        }
    }

    /**
     * Tell the {@link TimeProfiler} that the process is stopping to compute a
     * work concerning a given task.
     * 
     * @param task
     *            name of the task on which add the work time.
     */
    public void stopWork(final ProfilerTask task) {
        if (isActive) {
            // the end.
            final long endTime = now().longValue();
            // get start time.
            if (!startTimes.containsKey(task)) {
                startTimes.put(task, now());
            }
            long startTime = now().longValue();
            if (startTimes.containsKey(task)) {
                startTime = startTimes.get(task).longValue();
            }
            // compute duration.
            final long duration = endTime - startTime;
            if (!times.containsKey(task)) {
                times.put(task, Long.valueOf(0L));
            }
            final long oldDuration = times.get(task).longValue();
            times.put(task, Long.valueOf(oldDuration + duration));
            // remove running task.
            startTimes.remove(task);
            // this.fireTaskStopped(task);
        }
    }

    /**
     * Get the total time ellapsed associated to the task.
     * 
     * @param task
     *            the task
     * @return the cumulated time of execution associated to the task.
     */
    public long getTimeEllapsed(final ProfilerTask task) {
        if (isActive && times.containsKey(task)) {
            return times.get(task).longValue();
        }
        return 0;
    }

    /**
     * return the number of consecutive calls of the given task.
     * 
     * @param profilerTaskKey
     *            the key of the task to count instance calls
     * @return the number of consecutive calls
     */
    public int getCountTask(final String profilerTaskKey) {
        Option<ProfilerTask> option = TimeProfiler.PROFILER_TASK_REGISTRY.get(profilerTaskKey);
        if (option.some()) {
            return getCountTask(option.get());
        }
        return 0;
    }

    /**
     * return the number of consecutive calls of the given task.
     * 
     * @param task
     *            a given task.
     * @return the number of consecutive calls
     */
    public int getCountTask(final ProfilerTask task) {
        final Integer count = this.countTasks.get(task);
        return count == null ? 0 : count.intValue();
    }

    /**
     * Return all tasks.
     * 
     * @return all tasks.
     */
    public Set<ProfilerTask> getTasks() {
        return this.times.keySet();
    }

    /**
     * Return all running tasks.
     * 
     * @return all running tasks.
     */
    public Set<ProfilerTask> getRunningTasks() {
        return this.startTimes.keySet();
    }

    /**
     * Get the status of the profiling.
     * 
     * @return a readable status of the whole profiling.
     */
    public String getStatus() {
        int defaultLineLength = 100;
        final StringBuffer result = new StringBuffer(times.keySet().size() * defaultLineLength);
        if (isActive) {
            final Iterator<ProfilerTask> it = times.keySet().iterator();
            while (it.hasNext()) {
                final ProfilerTask name = it.next();
                String line = "\n  " + name + "  :"; //$NON-NLS-1$ //$NON-NLS-2$
                line += getMissingSpacesForAlignment(line, TimeProfiler.ALIGNMENT_LINE_SIZE);
                line += times.get(name).toString();
                line += getMissingSpacesForAlignment(line, TimeProfiler.ALIGNMENT_LINE_SIZE + 10);
                line += " ms"; //$NON-NLS-1$
                result.append(line);
            }
        }
        return result.toString();
    }

    /**
     * Return a {@link String} filled with white-spaces in order to align the
     * given message.
     * 
     * @param original
     *            original message.
     * @param alignmentSize
     *            size to reach.
     * @return a {@link String} filled with white-spaces in order to align the
     *         given message.
     */
    protected String getMissingSpacesForAlignment(final String original, final int alignmentSize) {
        int currentSize = original.length();
        final StringBuffer result = new StringBuffer();
        while (currentSize < alignmentSize) {
            result.append(' ');
            currentSize += 1;
        }
        return new String(result);
    }

    /**
     * Get the activation state of TimeProfiler.
     * 
     * @return true if the {@link TimeProfiler} is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Activate or desactivate the TimeProfiler.
     * 
     * @param isProfilerActive
     *            activate or de-activate the {@link TimeProfiler}.
     */
    public void setActive(final boolean isProfilerActive) {
        this.isActive = isProfilerActive;
    }

    /**
     * Add a listener to this profiler.
     * 
     * @param listener
     *            the listener to add.
     * @throws IllegalArgumentException
     *             if <code>listener</code> is <code>null</code>.
     */
    public void addProfilerListener(final ProfilerListener listener) throws IllegalArgumentException {
        if (listener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.listeners.add(listener);
    }

    /**
     * Remove a listener.
     * 
     * @param listener
     *            the listener to remove.
     */
    public void removeProfilerListener(final ProfilerListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * Fire a reinit event.
     */
    protected void fireProfilerReinited() {
        final ProfilerEvent event = new ProfilerEvent(null, this);
        final Iterator<ProfilerListener> iterListeners = this.listeners.iterator();
        while (iterListeners.hasNext()) {
            final ProfilerListener profilerListener = iterListeners.next();
            profilerListener.profilerReinited(event);
        }
    }

    /**
     * Fire a start event with the specified taskName.
     * 
     * @param task
     *            the task.
     */
    protected void fireTaskStarted(final ProfilerTask task) {
        final ProfilerEvent event = new ProfilerEvent(task, this);
        final Iterator<ProfilerListener> iterListeners = this.listeners.iterator();
        while (iterListeners.hasNext()) {
            final ProfilerListener profilerListener = iterListeners.next();
            profilerListener.taskStarted(event);
        }
    }

    /**
     * Fire a start event with the specified taskName.
     * 
     * @param task
     *            the task.
     */
    protected void fireTaskStopped(final ProfilerTask task) {
        final ProfilerEvent event = new ProfilerEvent(task, this);
        final Iterator<ProfilerListener> iterListeners = this.listeners.iterator();
        while (iterListeners.hasNext()) {
            final ProfilerListener profilerListener = iterListeners.next();
            profilerListener.taskStopped(event);
        }
    }

}
