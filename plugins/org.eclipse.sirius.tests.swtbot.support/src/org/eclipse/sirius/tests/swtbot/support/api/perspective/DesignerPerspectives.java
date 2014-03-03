/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.perspective;

import org.eclipse.sirius.tests.swtbot.support.api.condition.PerspectiveActivatedCondition;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;

/**
 * A class to manage perspectives provided with Sirius.
 * 
 * @author mchauvin
 */
public class DesignerPerspectives {

    private static final String MODELING_PERSPECTIVE_NAME = "Modeling";

    private static final String VIEWPOINT_PERSPECTIVE_NAME = "Sirius";

    private final SWTWorkbenchBot bot;

    /**
     * Construct a new instance.
     * 
     * @param bot
     *            the workbench bot
     */
    public DesignerPerspectives(final SWTWorkbenchBot bot) {
        this.bot = bot;
    }

    /**
     * Open the design perspective.
     */
    public void openModelingPerspective() {
        openPerspective(DesignerPerspectives.MODELING_PERSPECTIVE_NAME);
    }

    /**
     * Open the Sirius perspective.
     */
    public void openSiriusPerspective() {
        openPerspective(DesignerPerspectives.VIEWPOINT_PERSPECTIVE_NAME);
    }

    /**
     * Open a perspective.
     * 
     * @param perspectiveName
     *            the perspective to open.
     */
    public void openPerspective(final String perspectiveName) {
        if (!bot.activePerspective().getLabel().equals(perspectiveName)) {
            bot.perspectiveByLabel(perspectiveName).activate();
            bot.waitUntil(new PerspectiveActivatedCondition(bot, perspectiveName));
        }
    }

}
