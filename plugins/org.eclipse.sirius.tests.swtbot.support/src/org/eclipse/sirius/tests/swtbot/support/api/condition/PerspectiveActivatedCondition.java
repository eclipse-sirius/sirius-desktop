/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

    /**
     * Default constructor.
     * 
     * @param bot
     *            current Bot
     * @param perspectiveName
     *            name of the perspective that we are waiting for activation
     */
    public PerspectiveActivatedCondition(SWTWorkbenchBot bot, String perspectiveName) {
        this.bot = bot;
        this.perspectiveName = perspectiveName;
    }

    @Override
    public boolean test() throws Exception {
        return bot.activePerspective().getLabel().equals(perspectiveName);
    }

    @Override
    public String getFailureMessage() {
        return "The perspective " + perspectiveName + " has not yet been activated";
    }

}
