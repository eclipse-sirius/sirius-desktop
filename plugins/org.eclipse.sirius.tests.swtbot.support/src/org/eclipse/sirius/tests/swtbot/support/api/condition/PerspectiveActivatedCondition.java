/**
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES
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
 * Checks if an edit part is not displayed.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class PerspectiveActivatedCondition extends DefaultCondition {

    private final SWTWorkbenchBot bot;

    private final String perspectiveName;

    private final boolean negate;

    /**
     * Default constructor.
     * 
     * @param bot
     *            current Bot
     * @param perspectiveName
     *            name of the perspective that we are waiting for activation
     */
    public PerspectiveActivatedCondition(SWTWorkbenchBot bot, String perspectiveName) {
        this(bot, perspectiveName, false);
    }

    /**
     * Default constructor.
     * 
     * @param bot
     *            current Bot
     * @param perspectiveName
     *            name of the perspective that we are waiting for activation
     * @param negate
     *            inverse the test of the condition (deactivated instead of
     *            activated).
     */
    public PerspectiveActivatedCondition(SWTWorkbenchBot bot, String perspectiveName, boolean negate) {
        this.bot = bot;
        this.perspectiveName = perspectiveName;
        this.negate = negate;
    }

    @Override
    public boolean test() throws Exception {
        if (negate) {
            return !(bot.activePerspective().getLabel().equals(perspectiveName));
        } else {
            return bot.activePerspective().getLabel().equals(perspectiveName);
        }
    }

    @Override
    public String getFailureMessage() {
        if (negate) {
            return "The perspective " + perspectiveName + " has not yet been deactivated";
        } else {
            return "The perspective " + perspectiveName + " has not yet been activated";
        }
    }

}
