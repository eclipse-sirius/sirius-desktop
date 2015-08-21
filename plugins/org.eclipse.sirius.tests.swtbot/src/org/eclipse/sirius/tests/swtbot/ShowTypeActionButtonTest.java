/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;

/**
 * Test show type action button on contextual menu from VSM.
 * 
 * 
 * @author jdupont
 */
public class ShowTypeActionButtonTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "Design";

    private static final String TYPED_VIEWPOINT_NAME = "<Viewpoint> Design";

    private static final String VSM_FILE = "ecore.odesign";

    private static final String GROUP = "Ecore Editing Workbench V4.6";

    private static final String TYPED_GROUP = "<Group> Ecore Editing Workbench V4.6";

    private static final String DATA_UNIT_DIR = "data/unit/showTypeActionButton/vp-2317/";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String TYPED_REPRESENTATION_NAME = "<DiagramDescription> Entities";

    private static final String SHOW_TYPE = "Show Types";

    private static final String HIDE_TYPE = "Hide Types";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, new String[] { VSM_FILE });
    }

    /**
     * Test show type action on VSM and hide type action.
     */
    public void testShowHideTypeAction() {
        SWTBotView projectExplorer = bot.viewByTitle("Model Explorer");
        projectExplorer.setFocus();
        projectExplorer.bot().tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();
        SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();

        String nodeLabel = "platform:/resource/" + getProjectName() + "/" + VSM_FILE;
        SWTBotMenu contextualMenu = activeEditor.bot().tree().expandNode(nodeLabel).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(REPRESENTATION_NAME).contextMenu(SHOW_TYPE);
        contextualMenu.click();
        contextualMenu = activeEditor.bot().tree().expandNode(nodeLabel).expandNode(TYPED_GROUP).expandNode(TYPED_VIEWPOINT_NAME).expandNode(TYPED_REPRESENTATION_NAME).contextMenu(HIDE_TYPE);
        contextualMenu.click();
        contextualMenu = activeEditor.bot().tree().expandNode(nodeLabel).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(REPRESENTATION_NAME).contextMenu(SHOW_TYPE);
    }

}
