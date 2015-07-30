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

import java.io.FileNotFoundException;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
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

    /**
     * Test show type action on VSM and hide type action.
     */
    public void testShowHideTypeAction() throws FileNotFoundException {
        openVSM();
        SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();
        
        //Active the 
        SWTBotMenu contextualMenu = activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(GROUP).expandNode(VIEWPOINT_NAME)
                .expandNode(REPRESENTATION_NAME).contextMenu(SHOW_TYPE);
        contextualMenu.click();
        contextualMenu = activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(TYPED_GROUP).expandNode(TYPED_VIEWPOINT_NAME)
                .expandNode(TYPED_REPRESENTATION_NAME).contextMenu(HIDE_TYPE);
        contextualMenu.click();
        contextualMenu = activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(REPRESENTATION_NAME)
                .contextMenu(SHOW_TYPE);
    }

    /**
     * Open the VSM
     */
    private void openVSM() {
        SWTBotView projectExplorer = bot.viewByTitle("Model Explorer");
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();
    }

    /**
     * Return files used in the current test.
     */
    String[] getFilesUsedForTest() {
        return new String[] { VSM_FILE };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, getFilesUsedForTest());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
    }

}
