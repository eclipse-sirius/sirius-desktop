/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILSCategoryBrowser;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILSRepresentationBrowser;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILSViewpointBrowser;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.UILocalSessionBrowser;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.junit.Test;

/**
 * 
 * Test to navigate in tree representations.
 * 
 * @author nlepine
 */
public class NavigateInTreeRepresentationTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /**
     * Odesign.
     */
    private static final String MODEL = "tree.odesign";

    /**
     * Test repository.
     */
    private static final String DATA_UNIT_DIR = "data/unit/tree/navigation/";

    /**
     * Session file.
     */
    private static final String SESSION_FILE = "tree.aird";

    /**
     * UML File.
     */
    private static final String ECORE_FILE = "tree.ecore";

    /**
     * File directory.
     */
    private static final String FILE_DIR = "/";

    /**
     * Session.
     */
    private UIResource sessionAirdResource;

    /**
     * Local Session.
     */
    private UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ECORE_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * Test create, open, close and delete tree representation.
     * 
     * @throws Exception
     *             when exception thrown
     */
    @Test
    public void testNavigationInTreeRepresentation() throws Exception {
        UITreeRepresentation tree;
        try {
            UILocalSessionBrowser localSessionBrowser = localSession.getLocalSessionBrowser();
            UILSCategoryBrowser perCategory = localSessionBrowser.perCategory();
            UILSViewpointBrowser selectViewpoint = perCategory.selectViewpoint("Design");
            UILSRepresentationBrowser selectRepresentation = selectViewpoint.selectRepresentation("Tree");
            UITreeRepresentation selectTreeInstance = selectRepresentation.selectTreeInstance("new Tree");
            tree = selectTreeInstance.open();

            assertEquals("The tree editor was not open.", bot.activeEditor().getTitle(), ("new Tree"));
            // navigate from the context menu
            SWTBotTree select = tree.getEditorBot().tree().select("EClass1");
            SWTBotUtils.clickContextMenu(select, "new Tree Creation on Class");
            bot.waitUntil(new DefaultCondition() {

                @Override
                public boolean test() throws Exception {
                    return ((SWTWorkbenchBot) bot).activeEditor().getTitle().equals("new Tree Creation on Class");
                }

                @Override
                public String getFailureMessage() {
                    return "The tree editor was not open from the navigate menu.";
                }
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        assertEquals("The tree editor was not open from the navigate menu.", bot.activeEditor().getTitle(), ("new Tree Creation on Class"));
    }
}
