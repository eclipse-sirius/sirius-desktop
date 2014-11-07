/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the "link with editor" feature works correctly in model explorer
 * view.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class LinkWithEditorFeatureWithModelExplorerViewTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3832.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3832.aird";

    private static final String PATH = "data/unit/VP-3832/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_NAME = " package entities";

    private UIResource sessionAirdResource;

    private SWTBot modelExplorerViewBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, REPRESENTATIONS_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        SWTBotUtils.waitAllUiEvents();
        modelExplorerView.setFocus();
        modelExplorerViewBot = modelExplorerView.bot();
    }

    /**
     * Ensure that the "link with editor" feature works correctly in model
     * explorer view by checking if the opened element is the selected one in
     * model explorer view.
     */
    public void testLinkWithEditorFeatureWithModelExplorerView() {
        SWTBotTreeItem projectTreeItemBot = modelExplorerViewBot.tree().expandNode(getProjectName(), true);
        SWTBotTreeItem representationsResourceTreeItemBot = projectTreeItemBot.getNode(REPRESENTATIONS_RESOURCE_NAME);
        SWTBotTreeItem viewpointTreeItemBot = representationsResourceTreeItemBot.getNode(0).getNode(EcoreModeler.DESIGN_VIEWPOINT_NAME);
        SWTBotTreeItem representationDescriptionTreeItemBot = viewpointTreeItemBot.getNode(0);
        SWTBotTreeItem representationTreeItemBot = representationDescriptionTreeItemBot.getNode(0);
        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        if (!modelExplorerView.toolbarToggleButton("Link with Editor").isChecked()) {
            modelExplorerView.toolbarToggleButton("Link with Editor").click();
        }
        openDiagram(REPRESENTATION_NAME);
        assertEquals("The opened representation should be selected in model explorer view", representationTreeItemBot.isSelected(), true);
    }

    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
    }

}
