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

import java.util.ArrayList;

public abstract class AbstractProfiledCommandWithUnit extends AbstractProfiledCommand {

    /**
     * Creates a new benched test.
     * 
     * @param name
     *            the name of the test.
     */
    public AbstractProfiledCommandWithUnit(String name) {
        super(name);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Long>[] unitTimes = new ArrayList[REPEAT_TEST];

    /**
     * This method is invoked before the execution of the command.
     */
    public void preTest() {
        unitTimes[getRepeatCount()] = new ArrayList<Long>();
    }

    protected void doUnit(final AbstractProfiledCommandUnit unit) {
        unit.profiledTest();
        unitTimes[getRepeatCount()].add(unit.getElapsedTime());
    }

    /**
     * Get the average of the elapsed time for a test.
     * 
     * @return the elapsed time average for a test
     */
    public long getAverageElaspedTime() {
        long sum = 0;

        for (int i = 0; i < times.length; i++) {
            for (Long unitTime : unitTimes[i]) {
                sum += unitTime;
            }
        }
        return Math.round((double) sum / (times.length * unitTimes[0].size()));
    }

    /**
     * Get the maximum elapsed time for a test.
     * 
     * @return the maximum elapsed time average for a test
     */
    public long getMaxElapsedTime() {
        long max = 0;
        for (int i = 0; i < times.length; i++) {
            for (Long unitTime : unitTimes[i]) {
                max = Math.max(max, unitTime);
            }
        }
        return max;
    }

    protected abstract class AbstractProfiledCommandUnit {

        private long elapsedTime;

        protected abstract void doTest();

        protected void profiledTest() {
            final long start = System.currentTimeMillis();
            doTest();
            final long finish = System.currentTimeMillis();
            elapsedTime = finish - start;
        }

        protected long getElapsedTime() {
            return elapsedTime;
        }

    }

}
