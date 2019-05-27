/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * A SWTBot test for VSM editor container drop property sections.
 * 
 * See eclipse bug 465952.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ContainerDropPropertySectionsTests extends AbstractContentAssistTest {

    private static final String PATH = "/data/unit/dragAndDrop/tc-1041/";

    private static final String MODELER_RESOURCE_NAME = "tc1041.odesign";

    private static final String GROUP_NAME = "Ticket #1041";

    private static final String VIEWPOINT_NAME = "Test case for ticket #1041";

    private static final String DIAGRAM_DESCRIPTION_NAME1 = "TC1041 representation 2 Blank";

    private static final String DIAGRAM_DESCRIPTION_NAME2 = "TC1041 representation 3";

    private static final String DIAGRAM_DESCRIPTION_NAME3 = "TC1041 representation 1";

    private static final String FIRST_REPRESENTATION_CONTAINER_NAME = "Container EPackage R1";

    private static final String DEFAULT_LAYER_NAME = "Default";

    private static final String QUALIFIED_NAME_SEPARATOR = " > ";

    private static final String MODEL = "tc1041.ecore";

    private static final String SESSION_FILE = "tc1041.aird";

    private SWTBotEditor odesignEditorBot;

    private SWTBotTreeItem viewpointItemBot;

    private SWTBotView propertiesBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODEL, SESSION_FILE, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(MODELER_RESOURCE_NAME).doubleClick();

        odesignEditorBot = bot.activeEditor();
        odesignEditorBot.setFocus();
        viewpointItemBot = odesignEditorBot.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + MODELER_RESOURCE_NAME, true);
        viewpointItemBot.setFocus();
    }

    /**
     * Test Containers sections. The items in the section must have their label qualified.
     */
    public void testContainersReferences() {
        SWTBotTreeItem layerNode = viewpointItemBot.getNode(0).getNode(0).getNode(0).getNode(DEFAULT_LAYER_NAME);
        SWTBotTreeItem containerDropNode = layerNode.getNode(1).getNode(1);
        containerDropNode.select();

        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();

        SWTBotButton appliedOnButton = propertiesBot.bot().button(0);
        appliedOnButton.click();
        SWTBotShell appliedOnSelectorShell = bot.shell("Containers -- Ticket #1041 > Test case for ticket #1041 > TC1041 representation 1 > Default > tools > Drag & Drop R1");
        SWTBot appliedOnSelectorShellBot = appliedOnSelectorShell.bot();
        SWTBotTable table1 = appliedOnSelectorShellBot.table(0);
        assertEquals("The left containers selection table does not contains all elements it should.", 16, table1.rowCount());
        SWTBotTable table2 = appliedOnSelectorShellBot.table(1);
        assertEquals("The right containers selection table does not contains all elements it should.", 2, table2.rowCount());
        assertEquals(GROUP_NAME + QUALIFIED_NAME_SEPARATOR + VIEWPOINT_NAME + QUALIFIED_NAME_SEPARATOR + DIAGRAM_DESCRIPTION_NAME1, table1.getTableItem(1).getText());
        assertEquals(GROUP_NAME + QUALIFIED_NAME_SEPARATOR + VIEWPOINT_NAME + QUALIFIED_NAME_SEPARATOR + DIAGRAM_DESCRIPTION_NAME2, table1.getTableItem(4).getText());
        assertEquals(GROUP_NAME + QUALIFIED_NAME_SEPARATOR + VIEWPOINT_NAME + QUALIFIED_NAME_SEPARATOR + DIAGRAM_DESCRIPTION_NAME3, table2.getTableItem(0).getText());
        assertEquals(GROUP_NAME + QUALIFIED_NAME_SEPARATOR + VIEWPOINT_NAME + QUALIFIED_NAME_SEPARATOR + DIAGRAM_DESCRIPTION_NAME3 + QUALIFIED_NAME_SEPARATOR + DEFAULT_LAYER_NAME
                + QUALIFIED_NAME_SEPARATOR + FIRST_REPRESENTATION_CONTAINER_NAME, table2.getTableItem(1).getText());
        appliedOnSelectorShell.close();

    }

    @Override
    protected void tearDown() throws Exception {
        odesignEditorBot.close();
        odesignEditorBot = null;
        propertiesBot = null;
        viewpointItemBot = null;
        super.tearDown();
    }
}
