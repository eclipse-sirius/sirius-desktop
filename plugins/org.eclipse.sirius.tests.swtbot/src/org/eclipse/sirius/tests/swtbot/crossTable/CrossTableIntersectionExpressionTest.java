/*******************************************************************************
 * Copyright (c) 2014, 2021 Obeo.
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
package org.eclipse.sirius.tests.swtbot.crossTable;

import java.text.MessageFormat;

import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckTreeItemEnabled;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the column finder expression appears in the "problems" view.
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy MALLET</a>
 */
public class CrossTableIntersectionExpressionTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * Viewpoint Specific Model.
     */
    private static final String VSM = "testColumnFinderExpressionLog.odesign";

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/crossTable/columnFinderExpressionLog/";

    /** Session file. */
    private static final String SESSION_FILE = "representations.aird";

    /** UML File. */
    private static final String ECORE_FILE = "crossTable.ecore";

    /** File directory. */
    private static final String FILE_DIR = "/";

    /** Local Session. */
    private UILocalSession localSession;

    private static final String ERROR_NODE = "Errors (2 items)";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, ECORE_FILE, SESSION_FILE, VSM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Test that the column finder expression appears in the "problems" view when there is a mistake.
     */
    public void testLoggerExpressionValue() {
        openRepresentation(localSession.getOpenedSession(), "CrossTableDesc", "new CrossTableDesc", DTable.class);
        SWTBotView problemViewBot = bot.viewByPartName("Problems");
        assertTrue("Find column expression error does not appear in the problems view.", checkProblemLogMessage(problemViewBot));
    }

    /**
     * Check that the error message appears in the "Problems" view.
     * 
     * @param problemViewBot
     *            the view to check
     * @return boolean
     */
    private Boolean checkProblemLogMessage(SWTBotView problemViewBot) {
        problemViewBot.setFocus();
        SWTBotTree problemsTree = problemViewBot.bot().tree();
        bot.waitUntil(new CheckTreeItemEnabled(problemsTree.getTreeItem(ERROR_NODE)));
        String errorMessage = MessageFormat.format(Messages.MarkerRuntimeLoggerImpl_featureWithMessage, "columnFinderExpression", "Unknown service \"myImaginaryService\"");
        for (SWTBotTreeItem item : problemsTree.getAllItems()) {
            item.expand();
            for (String itemMessage : item.getNodes()) {
                if (errorMessage.equals(itemMessage)) {
                    return true;
                }
            }
        }
        return false;
    }
}
