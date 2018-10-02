/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * SWTBot condition to check the number of opened editor.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class NumberOfOpenedEditorsCondition extends DefaultCondition {
    private final int expectedNumber;

    private final SWTWorkbenchBot bot;

    /**
     * Constructor.
     * 
     * @param bot
     *            Bot.
     * @param expectedNumber
     *            number of expected opened editor
     */
    public NumberOfOpenedEditorsCondition(SWTWorkbenchBot bot, int expectedNumber) {
        this.bot = bot;
        this.expectedNumber = expectedNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        int newNumberOfEditors = bot.editors().size();
        return expectedNumber == newNumberOfEditors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "The number of opened editors is " + bot.editors().size() + " instead of the expected " + expectedNumber;
    }
}
