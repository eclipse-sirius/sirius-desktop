/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * An {@link org.eclipse.swtbot.swt.finder.waits.ICondition} only to be used to
 * deal with unnamed shells. Tests that the active shell is different from the
 * one given in parameter.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class ShellChangedCondition extends DefaultCondition {

    private SWTBotShell oldShell;

    /**
     * Tests that the active shell is different from the one given in parameter.
     * 
     * @param oldShell
     *            the old shell that should change
     */
    public ShellChangedCondition(SWTBotShell oldShell) {
        this.oldShell = oldShell;
    }

    @Override
    public boolean test() throws Exception {
        return oldShell.widget != bot.activeShell().widget;
    }

    @Override
    public String getFailureMessage() {
        return "Shell did not activate";
    }

}
