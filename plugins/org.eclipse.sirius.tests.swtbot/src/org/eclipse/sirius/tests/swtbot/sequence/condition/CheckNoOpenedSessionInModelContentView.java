/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * 
 * @author smonnier
 */
public class CheckNoOpenedSessionInModelContentView extends DefaultCondition {

    private SWTGefBot bot;

    private String airdFileName;

    /**
     * Default Constructor
     * 
     * @param bot
     *            the main bot.
     * 
     */
    public CheckNoOpenedSessionInModelContentView(SWTGefBot bot, String airdFileName) {
        this.bot = bot;
        this.airdFileName = airdFileName;
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() throws Exception {
        SWTBotView modelContent = bot.viewByTitle("Model Explorer");
        modelContent.setFocus();
        return modelContent.bot().tree().getAllItems()[0].expandNode(this.airdFileName.split("/")).rowCount() == 0;
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return "The Model content view is not empty";
    }

}
