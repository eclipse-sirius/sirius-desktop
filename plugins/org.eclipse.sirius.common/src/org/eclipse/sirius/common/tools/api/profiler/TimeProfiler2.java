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

import java.util.Collection;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.ext.base.collect.StackEx;

/**
 * An advanced profiler.
 * 
 * @author ymortier
 */
public class TimeProfiler2 extends TimeProfiler {

    /** The Other task. */
    public static final ProfilerTask OTHER_TASK = new ProfilerTask("Other", "Other");

    /** The roots tasks. */
    private List<CompositeTask> roots;

    /** The running tasks. */
    private StackEx<CompositeTask> runningTasks;

    /** The times of running tasks. */
    private Map<CompositeTask, Date> runningTasksTime;

    /**
     * Creates a new {@link TimeProfiler2}.
     * 
     */
    public TimeProfiler2() {
        super();
    }

    /**
     * Initializes the profiler.
     */
    @Override
    public void init() {
        super.init();
        if (runningTasks != null) {
            this.runningTasks.clear();
        } else {
            this.runningTasks = new StackEx<CompositeTask>();
        }
        if (runningTasksTime != null) {
            this.runningTasksTime.clear();
        } else {
            this.runningTasksTime = new HashMap<CompositeTask, Date>();
        }
        if (this.roots != null) {
            this.roots.clear();
        } else {
            this.roots = new LinkedList<CompositeTask>();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.profiler.TimeProfiler#getCountTask(org.eclipse.sirius.common.tools.api.profiler.ProfilerTask)
     */
    @Override
    public int getCountTask(final ProfilerTask task) {
        // default implementation.
        // useless for TimeProfiler2
        return super.getCountTask(task);
    }

    /**
     * Returns a human readable string of the status of the profiler.
     * 
     * @return a human readable string of the status of the profiler.
     */
    @Override
    public String getStatus() {
        Set<Entry<ProfilerTask, Long>> timeSet = times.entrySet();
        final StringBuffer result = new StringBuffer(timeSet.size() * 100);
        if (isActive) {
            final Iterator<Entry<ProfilerTask, Long>> it = timeSet.iterator();
            while (it.hasNext()) {
                final Entry<ProfilerTask, Long> entry = it.next();
                final StringBuffer line = new StringBuffer("\n  " + entry.getKey() + "  :"); //$NON-NLS-1$ //$NON-NLS-2$
                final CompositeTask compositeTask = getCompositeTask(entry.getKey());
                if (compositeTask != null) {
                    line.append(compositeTask.getOccurences());
                    line.append(" occurences for "); //$NON-NLS-1$
                }
                line.append(getMissingSpacesForAlignment(line.toString(), TimeProfiler.ALIGNMENT_LINE_SIZE));
                line.append(entry.getValue().toString());
                line.append(getMissingSpacesForAlignment(line.toString(), TimeProfiler.ALIGNMENT_LINE_SIZE + 10));
                line.append(" ms"); //$NON-NLS-1$
                result.append(line);
            }
        }
        return result.toString();
    }

    /**
     * Returns the roots tasks.
     * 
     * @return the roots tasks.
     */
    public List<CompositeTask> getRoots() {
        return roots;
    }

    /**
     * Returns the ellapsed time for the specified task.
     * 
     * @param task
     *            the task.
     * @return the ellapsed time for the specified task.
     */
    @Override
    public long getTimeEllapsed(final ProfilerTask task) {
        // default implementation.
        // useless for TimeProfiler2
        return super.getTimeEllapsed(task);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.profiler.TimeProfiler#startWork(org.eclipse.sirius.common.tools.api.profiler.ProfilerTask)
     */
    @Override
    public void startWork(final ProfilerTask task) {
        // default implementation.
        super.startWork(task);
        if (isActive()) {
            // specific implementation.
            final Date start = new Date();
            if (this.runningTasks.isEmpty()) {
                //
                // try to find the task in roots.
                CompositeTask compositeTask = getCompositeTask(task);
                final Iterator<CompositeTask> iterRoots = this.getRoots().iterator();
                while (iterRoots.hasNext()) {
                    final CompositeTask current = iterRoots.next();
                    if (current.getProfilerTask() == task) {
                        compositeTask = current;
                    }
                }
                if (compositeTask == null) {
                    //
                    // It is the first time for this task.
                    compositeTask = new CompositeTask(task);
                    this.roots.add(compositeTask);
                }
                this.runningTasks.push(compositeTask);
                this.runningTasksTime.put(compositeTask, start);
                compositeTask.addOccurence();
            } else {
                final CompositeTask runningTask = this.runningTasks.peek();
                final CompositeTask newTask = runningTask.findOrCreateChild(task);
                this.runningTasks.push(newTask);
                this.runningTasksTime.put(newTask, start);
                newTask.addOccurence();
            }
        }
    }

    /**
     * Returns the composite task chich profiler task is equal to
     * <code>task</code>.
     * 
     * @param task
     *            The task which CompositeTask we seek.
     * @return The sought composite task, <code>null</code> if it wasn't found.
     */
    private CompositeTask getCompositeTask(final ProfilerTask task) {
        CompositeTask result = null;
        final Iterator<CompositeTask> iterRoots = this.getRoots().iterator();
        while (iterRoots.hasNext() && result == null) {
            final CompositeTask current = iterRoots.next();
            if (current.getProfilerTask() == task) {
                result = current;
            } else if (current.getChildren().size() > 0) {
                result = internalGetCompositeTask(current, task);
            }
        }
        return result;
    }

    /**
     * Returns a composite task in the given <code>parent</code>'s children list
     * which ProfilerTask is equal to the given <code>task</code>.
     * 
     * @param parent
     *            Parent in which children we seek a composite task.
     * @param task
     *            Task which CompositeTask we seek.
     * @return The sought composite task, <code>null</code> if it wasn't found
     *         in this parent.
     */
    private CompositeTask internalGetCompositeTask(final CompositeTask parent, final ProfilerTask task) {
        CompositeTask result = null;
        final Iterator<CompositeTask> childIterator = parent.getChildren().iterator();
        while (childIterator.hasNext()) {
            final Object next = childIterator.next();
            if (next instanceof CompositeTask) {
                if (((CompositeTask) next).getProfilerTask() == task) {
                    result = (CompositeTask) next;
                } else if (((CompositeTask) next).getChildren().size() > 0) {
                    result = internalGetCompositeTask((CompositeTask) next, task);
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.tools.api.profiler.TimeProfiler#stopWork(org.eclipse.sirius.common.tools.api.profiler.ProfilerTask)
     */
    @Override
    public void stopWork(final ProfilerTask task) {
        try {
            // default implementation.
            super.stopWork(task);
            if (isActive()) {
                // specific implementation.
                final Date stop = new Date();
                final CompositeTask runningTask = this.runningTasks.pop();
                if (runningTask.getProfilerTask() != task) {
                    // DslCommonPlugin.getDefault().error("Invalid stop task
                    // event !!! Stopped task : " + task.toString() + ". Running
                    // task : " + runningTask.getProfilerTask().toString(),
                    // new RuntimeException());
                    this.runningTasks.push(runningTask);
                    stopWork(runningTask.getProfilerTask());
                    if (!this.runningTasks.isEmpty()) {
                        stopWork(task);
                    }
                    return;
                }
                final Date start = runningTasksTime.get(runningTask);
                final long time = stop.getTime() - start.getTime();
                runningTask.addTime(time);
                runningTask.stopTask();
            }
        } catch (final EmptyStackException e) {
            DslCommonPlugin.getDefault().error("Empty stack in stopWork. Stopped task : " + task.toString(), e);
        }
    }

    /**
     * Composite task.
     * 
     * @author ymortier
     */
    public static class CompositeTask {

        /** The task. */
        private ProfilerTask profilerTask;

        /** The time ellapsed. */
        private long ellapsedTime;

        /** The number of occurences. */
        private int occurences;

        /** The minimum time. */
        private long min;

        /** The maximum time. */
        private long max;

        /** The children. */
        private Map<ProfilerTask, CompositeTask> children;

        /** The parent. */
        private CompositeTask parent;

        /**
         * Creates a new {@link CompositeTask}.
         * 
         * @param task
         *            the corresponding profiler task.
         */
        public CompositeTask(final ProfilerTask task) {
            this.profilerTask = task;
            this.children = new HashMap<ProfilerTask, CompositeTask>();
        }

        /**
         * Adds the specified time to ellapsed time.
         * 
         * @param time
         *            the time to add.
         */
        public void addTime(final long time) {
            if (time < min || min == 0) {
                min = time;
            }
            if (time > max || max == 0) {
                max = time;
            }
            this.ellapsedTime += time;
        }

        /**
         * Adds an occurence of the task.
         */
        public void addOccurence() {
            this.occurences++;
        }

        /**
         * Finds or creates the {@link CompositeTask} corresponding to the
         * specified {@link ProfilerTask}.
         * 
         * @param task
         *            the profiler task.
         * @return the found or created {@link CompositeTask}.
         */
        public CompositeTask findOrCreateChild(final ProfilerTask task) {
            CompositeTask child = this.children.get(task);
            if (child == null) {
                child = new CompositeTask(task);
                child.parent = this;
                this.children.put(task, child);
            }
            return child;
        }

        /**
         * Returns the ellapsed time.
         * 
         * @return the ellapsed time.
         */
        public long getEllapsedTime() {
            return ellapsedTime;
        }

        /**
         * Returns the number of occurences.
         * 
         * @return the number of occurences.
         */
        public int getOccurences() {
            return occurences;
        }

        /**
         * Returns the profiler tasks.
         * 
         * @return the profiler tasks.
         */
        public ProfilerTask getProfilerTask() {
            return profilerTask;
        }

        /**
         * Returns the children.
         * 
         * @return the children.
         */
        public Collection<CompositeTask> getChildren() {
            return children.values();
        }

        /**
         * Stop the specified task.
         */
        public void stopTask() {
            int childTime = 0;
            if (!getChildren().isEmpty()) {
                final Iterator<CompositeTask> iterChildren = this.getChildren().iterator();
                while (iterChildren.hasNext()) {
                    final CompositeTask currentChild = iterChildren.next();
                    childTime += currentChild.getEllapsedTime();
                }
                if (childTime < this.ellapsedTime) {
                    final CompositeTask compositeTask = this.findOrCreateChild(TimeProfiler2.OTHER_TASK);
                    compositeTask.addTime(this.ellapsedTime - childTime);
                }
            }
        }

        /**
         * Returns the parent task.
         * 
         * @return the parent task.
         */
        public CompositeTask getParent() {
            return parent;
        }

        /**
         * Returns the minimum time.
         * 
         * @return the minimum time.
         */
        public long getMin() {
            return min;
        }

        /**
         * Returns the max time.
         * 
         * @return the max time.
         */
        public long getMax() {
            return max;
        }

    }

}
