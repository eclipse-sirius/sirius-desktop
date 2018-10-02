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

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;

/**
 * A {@link DefaultCondition} to test the text of a {@link AbstractSWTBot}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TextWidgetCondition extends DefaultCondition {

    private final AbstractSWTBot<? extends Widget> abstractSWTBot;

    private final String expectedText;

    /**
     * Default constructor.
     * 
     * @param abstractSWTBot
     *            the bot on which check the condition
     * @param expectedText
     *            the expected text
     */
    public TextWidgetCondition(AbstractSWTBot<? extends Widget> abstractSWTBot, String expectedText) {
        this.abstractSWTBot = abstractSWTBot;
        this.expectedText = expectedText;
    }

    @Override
    public boolean test() throws Exception {
        return expectedText.equals(abstractSWTBot.getText());
    }

    @Override
    public String getFailureMessage() {
        return "The bot " + abstractSWTBot + " has not the expected text : " + expectedText;
    }

}
