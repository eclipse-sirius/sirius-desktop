/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.benchmark;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * A profiled command.
 * 
 * @author ymortier
 */
public abstract class AbstractProfiledCommand {

    /** The default repeat count (if set below 1, 1 will be used). */
    protected static final int REPEAT_TEST = 1;

    private int repeatCount;

    /** The name of the test. */
    private String name;

    /** the elapsed times. */
    protected long[] times = new long[REPEAT_TEST];

    public AbstractProfiledCommand() {
        this("Unnammed command");
    }

    /**
     * Creates a new benched test.
     * 
     * @param name
     *            the name of the test.
     */
    public AbstractProfiledCommand(final String name) {
        this.name = name;
    }

    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this command's name.
     * 
     * @param name
     *            New name of the command.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is invoked before the execution of the command.
     */
    public void preTest() {
        // Subclasses can override this to execute pre-test operations.
    }

    /**
     * This method is invoked after the execution of the command.
     */
    public void postTest() {
        TestCase.assertTrue(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false));
    }

    /**
     * Runs the test.
     */
    protected abstract void doTest();

    /**
     * Executes the test and compute the duration of its execution.
     */
    public void profiledTest() {
        repeatCount = 0;
        DslCommonPlugin.PROFILER.setActive(true);
        try {
            DslCommonPlugin.PROFILER.init();
            for (int i = 0; i < times.length; i++) {
                repeatCount = i;
                preTest();
                final long start = System.currentTimeMillis();
                doTest();
                final long finish = System.currentTimeMillis();
                postTest();
                times[i] = (finish - start);
            }
        } finally {
            DslCommonPlugin.PROFILER.setActive(false);
        }
    }

    protected int getRepeatCount() {
        return repeatCount;
    }

    /**
     * Get the average of the elapsed time for a test.
     * 
     * @return the elapsed time average for a test
     */
    public long getAverageElaspedTime() {
        long sum = 0;
        for (int i = 0; i < times.length; i++) {
            sum += times[i];
        }
        return Math.round((double) sum / times.length);
    }

    /**
     * Get the maximum elapsed time for a test.
     * 
     * @return the maximum elapsed time average for a test
     */
    public long getMaxElapsedTime() {
        long max = 0;
        for (int i = 0; i < times.length; i++) {
            max = Math.max(max, times[i]);
        }
        return max;
    }

}
