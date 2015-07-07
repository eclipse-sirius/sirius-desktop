/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test the labels of viewpoints in viewpoint selection window.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class ViewpointSelectionDialogTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_FILE = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "ViewpointSelectionDialogTest.odesign";

    private static final String VSM1_ID = "ViewpointID";

    private static final String VSM2_ID = "Viewpoint2ID";

    private static final String VSM_LABEL = "Viewpoint Label";

    private static final String DATA_UNIT_DIR = "data/unit/ViewpointSelectionDialog/";

    private static final String VIEWPOINTS_SELECTION = "Viewpoints Selection";

    SWTBot modelExplorerViewBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        modelExplorerView.setFocus();
        modelExplorerViewBot = modelExplorerView.bot();
    }

    /**
     * Ensure that the labels of viewpoints are displayed instead of viewpoints
     * names on the viewpoint selection window.
     * 
     */
    public void testViewpointsLabelsOnViewpointSelectionDialog() {
        // open the viewpoint selection dialog
        SWTBotTreeItem sessionTreeItem = localSession.getRootSessionTreeItem();
        sessionTreeItem.contextMenu(VIEWPOINTS_SELECTION).click();
        bot.waitUntil(Conditions.shellIsActive(VIEWPOINTS_SELECTION));
        assertThat("The dialog is not correct for the viewpoints selection", bot.activeShell().getText(), equalTo(VIEWPOINTS_SELECTION));
        SWTBotTable table = bot.table(0);
        assertTrue("The Viewpoint should be displayed by its label", table.containsItem(VSM_LABEL) && !table.containsItem(VSM1_ID));
        assertTrue("The Viewpoint should be displayed by its id", table.containsItem(VSM2_ID));
    }

}
