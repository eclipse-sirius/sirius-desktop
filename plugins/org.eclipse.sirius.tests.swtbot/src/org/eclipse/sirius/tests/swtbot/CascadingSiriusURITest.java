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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotLabel;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Validate cascading viewpoint URI stability on VSM modification.
 * 
 * @author smonnier
 */
public class CascadingSiriusURITest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VSM_FILE_A = "a.odesign";

    private static final String VSM_FILE_B = "b.odesign";

    private static final String PROPERTIES = "Properties";

    private static final String VSM_FILE_C = "c.odesign";

    private static final String C_MODELER_PLATFORM_PATH = "platform:/resource/" + AbstractSiriusSwtBotGefTestCase.TEMP_PROJECT_NAME + "/c.odesign";

    private static final String B_MODELER_PLATFORM_PATH = "platform:/resource/" + AbstractSiriusSwtBotGefTestCase.TEMP_PROJECT_NAME + "/b.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/viewpoint_uri/ticket_2152/";

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM_FILE_A, VSM_FILE_B, VSM_FILE_C);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCascadingSiriusURIValidation() throws Exception {

        // Open c.odesign
        openOdesing(VSM_FILE_C);

        // Validate c.odesing
        bot.editorByTitle(VSM_FILE_C).setFocus();
        SWTBotTree treeC = bot.editorByTitle(VSM_FILE_C).bot().tree();
        SWTBotTreeItem groupC = treeC.expandNode(C_MODELER_PLATFORM_PATH).select("C");
        SWTBotUtils.clickContextMenu(groupC, "Validate");
        bot.button("OK").click();

        // Update group name in c.odesign
        bot.viewByTitle(PROPERTIES).setFocus();
        bot.viewByTitle(PROPERTIES).bot().text("C").setText("CModified");
        SWTBotView propertiesBot = bot.viewByTitle(PROPERTIES);
        SWTBotSiriusHelper.selectPropertyTabItem("Documentation", propertiesBot.bot());

        // Save and Close c.odesign
        bot.editorByTitle(VSM_FILE_C).setFocus();
        bot.editorByTitle(VSM_FILE_C).saveAndClose();

        // Open b.odesign
        openOdesing(VSM_FILE_B);

        // Validate b.odesing
        bot.editorByTitle(VSM_FILE_B).setFocus();
        SWTBotTree treeB = bot.editorByTitle(VSM_FILE_B).bot().tree();
        SWTBotTreeItem groupB = treeB.expandNode(B_MODELER_PLATFORM_PATH).select("B");
        SWTBotUtils.clickContextMenu(groupB, "Validate");

        // Check Validation completed successfully
        bot.waitUntil(Conditions.shellIsActive("Validation Information"));
        SWTBotLabel label = bot.shell("Validation Information").bot().label(1);
        assertEquals("The validation did not pass", "Validation completed successfully", label.getText());
        bot.button("OK").click();

        bot.editorByTitle(VSM_FILE_B).close();
    }

    private void openOdesing(String name) {
        bot.viewByTitle("Model Explorer").setFocus();
        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotTreeItem select = tree.expandNode(AbstractSiriusSwtBotGefTestCase.TEMP_PROJECT_NAME).select(name);
        SWTBotUtils.clickContextMenu(select, "Open");
    }
}
