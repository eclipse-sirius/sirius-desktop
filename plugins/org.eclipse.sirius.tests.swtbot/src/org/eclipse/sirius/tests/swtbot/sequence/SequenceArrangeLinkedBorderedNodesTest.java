/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

/**
 * Test class to validate "Arrange Linked Border Nodes" does not throw NPE
 * anymore. Validate ticket #2232
 * 
 * @author smonnier
 */
public class SequenceArrangeLinkedBorderedNodesTest extends AbstractDefaultModelSequenceTests {

    /**
     * Test method to validate "Arrange Linked Border Nodes" does not throw NPE
     * anymore. Validate ticket #2232
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_ArrangeLinkedBorderedNodes() throws Exception {
        openErrorLogViewByAPI();
        try {
            SWTBot errorLogBot = bot.viewByPartName("Error Log").bot();

            int rowCount = errorLogBot.tree().rowCount();

            editor.setFocus();
            arrangeAll();
            // Reveal A to scroll to the left
            editor.reveal(LIFELINE_A);

            // Calculate the X position of the center of lifelines A, B and C
            int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
            int lifelineBPosition = getLifelineScreenX(LIFELINE_B);
            int lifelineCPosition = getLifelineScreenX(LIFELINE_C);

            // Creation of an sync call
            createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, 200, lifelineBPosition, 200);

            // Creation of an async call
            createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineBPosition, 230, lifelineCPosition, 230);

            editor.clickContextMenu("Linked Border Nodes");
            final long oldTimeout = SWTBotPreferences.TIMEOUT;
            try {
                // Depending on the configuration, a pop up appear when
                // "Arrange Linked Bordered Nodes" fail
                SWTBotPreferences.TIMEOUT = 1000;
                bot.waitUntil(Conditions.shellIsActive("Linked Border Nodes"));
                fail(TimeoutException.class + " expected for shell \"Linked Border Nodes\"");
            } catch (final TimeoutException e) {
                // Expected, the shell must not be found
            } finally {
                SWTBotPreferences.TIMEOUT = oldTimeout;
            }

            assertEquals(rowCount, errorLogBot.tree().rowCount());
        } finally {
            closeErrorLogViewByAPI();
        }
    }
}
