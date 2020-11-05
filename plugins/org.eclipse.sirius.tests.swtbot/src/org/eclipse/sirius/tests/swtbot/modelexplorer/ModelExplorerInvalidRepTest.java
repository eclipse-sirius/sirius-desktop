/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * A SWTBot test for checking the Invalid representation section under the aird node.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ModelExplorerInvalidRepTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL_FILE = "model.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "rightClickEditPart.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/modelExplorer/invalidRep/";

    private static final String INVALID_REPS_SECTION = Messages.ViewpointsFolderInvalidItemImpl_invalidRepresentations_title;

    private static final String REPRESENTATION_NAME_1 = "TestClassDiagram Invalid 1";

    private static final String REPRESENTATION_NAME_2 = "TestClassDiagram Invalid 2";

    private static final String REPRESENTATION_NAME_3 = "TestClassDiagram Invalid 3";

    private UIResource sessionAirdResource;

    private SWTBot modelExplorerViewBot;

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
     * Test the "Invalid Representations" section under the aird node.
     */
    public void testInvalidRepresentationsSection() {
        SWTBotTreeItem expandNode = modelExplorerViewBot.tree().expandNode("DesignerTestProject");
        SWTBotTreeItem airdBotItem = expandNode.select(SESSION_FILE).getNode(SESSION_FILE);
        airdBotItem.expand();
        SWTBotTreeItem resourceTreeItem = airdBotItem.getNode(INVALID_REPS_SECTION).select().expand().getItems()[0].expand();
        assertEquals("Bad number of VPs with invalid representations", 1, resourceTreeItem.getNodes().size());

        SWTBotTreeItem vpTreeItem = resourceTreeItem.getItems()[0].expand();
        assertEquals("Bad number of representation descriptions with invalid representations", 1, vpTreeItem.getNodes().size());

        SWTBotTreeItem repDescriptionTreeItem = vpTreeItem.getItems()[0].expand();
        List<String> foundInvalidRepNodes = repDescriptionTreeItem.getNodes();
        assertTrue("Invalid representation nodes are not found", foundInvalidRepNodes.containsAll(Arrays.asList(REPRESENTATION_NAME_1, REPRESENTATION_NAME_2, REPRESENTATION_NAME_3)));

        deleteRepresentation(repDescriptionTreeItem, REPRESENTATION_NAME_1);
        deleteRepresentation(repDescriptionTreeItem, REPRESENTATION_NAME_2);
        deleteRepresentation(repDescriptionTreeItem, REPRESENTATION_NAME_3);

        List<String> airdNodes = airdBotItem.getNodes();
        assertTrue("The Invalid representation node is still present as there is no more invalid representation.", !airdNodes.contains(INVALID_REPS_SECTION));
    }

    private void deleteRepresentation(SWTBotTreeItem repDescriptionTreeItem, String representationName) {
        repDescriptionTreeItem.getNode(representationName).contextMenu("Delete").click();
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("Delete representation");
        wizardBot.button("OK").click();
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        modelExplorerViewBot = null;

        super.tearDown();
    }
}
