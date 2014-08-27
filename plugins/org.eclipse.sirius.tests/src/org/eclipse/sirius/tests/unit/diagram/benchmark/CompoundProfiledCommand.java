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

import java.util.LinkedList;
import java.util.List;

/**
 * A profiled command that is compound with many profiled command.
 * 
 * @author ymortier
 */
public class CompoundProfiledCommand extends AbstractProfiledCommand {

    private List<AbstractProfiledCommand> subTests;

    public CompoundProfiledCommand() {
        this("Unnamed command");
    }

    /**
     * Creates a new Compound Benched test.
     * 
     * @param name
     *            the name of the test.
     */
    public CompoundProfiledCommand(final String name) {
        super(name);
        subTests = new LinkedList<AbstractProfiledCommand>();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.perf.ODBenchmarker.AbstractBenchedTest#doTest()
     */
    @Override
    protected void doTest() {
        for (AbstractProfiledCommand test : subTests) {
            test.doTest();
        }
    }

    /**
     * Adds a test.
     * 
     * @param test
     *            the test to add.
     */
    public void addTest(final AbstractProfiledCommand test) {
        this.subTests.add(test);
    }

}
