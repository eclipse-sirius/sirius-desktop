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
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test creation of edge mapping : relation and element based edge.
 * 
 * @author nlepine
 */
public class EdgeMappingTestCase extends AbstractSiriusSwtBotGefTestCase {
    private static final String ODESIGN = "platform:/resource/DesignerTestProject/ecore.odesign";

    private static final String ELEMENT_BASED_EDGE = "Element Based Edge";

    private static final String REQUIRED_DOMAIN_CLASS = "Domain Class*:";

    private static final String OPTIONAL_DOMAIN_CLASS = "Domain Class:";

    private static final String GENERAL = "General";

    private static final String PROPERTIES = "Properties";

    private static final String RELATION_BASED_EDGE = "Relation Based Edge";

    private static final String MODEL = "ecore.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeMapping/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testEdgeMappingCreationOnLayer() throws Exception {
        // open editor
        SWTBotCommonHelper.openEditor(getProjectName(), MODEL);
        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(MODEL);
        odesignEditor.setFocus();

        // expand the tree : Default Layer
        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotTreeItem defaultItem = tree.expandNode(ODESIGN).expandNode("Ecore Editing Workbench V4.6").expandNode("Design").expandNode("Entities").expandNode("Default").select();

        // Create Relation Based Edge
        SWTBotUtils.clickContextMenu(defaultItem, RELATION_BASED_EDGE);
        defaultItem.select(RELATION_BASED_EDGE);
        SWTBotView propertiesView = bot.viewByTitle(PROPERTIES);
        propertiesView.setFocus();
        try {
            SWTBotSiriusHelper.selectPropertyTabItem(GENERAL, propertiesView.bot());
            propertiesView.bot().textWithLabel(OPTIONAL_DOMAIN_CLASS);
        } catch (WidgetNotFoundException e) {
            // the tab item is not in the property view -> ok
        }

        // Create Element Based Edge
        defaultItem.select();
        SWTBotUtils.clickContextMenu(defaultItem, ELEMENT_BASED_EDGE);
        defaultItem.expandNode(ELEMENT_BASED_EDGE).select();
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL, propertiesView.bot());
        propertiesView.bot().textWithLabel(REQUIRED_DOMAIN_CLASS);

        // Save and Close c.odesign
        bot.editorByTitle(MODEL).setFocus();
        bot.editorByTitle(MODEL).saveAndClose();
    }

}
