/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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

import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemAvailableCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemChildrenNumberCondition;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ViewpointSelectionDialog;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

import com.google.common.collect.Sets;

/**
 * Test class concerning the marker and quick fix for modeling project without representations file.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class CreateMissingRepresentationFileResolutionTest extends AbstractScenarioTestCase {


    private static final String DATA_UNIT_DIR = "/data/unit/editors/traceability/vp1038/";

    private static final String SEMANTIC_MODEL_PATH = "vp1038.ecore";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_MODEL_PATH);
    }

    /**
     * check that expected error related to the marker occurred.
     *
     * @return true if one error occurred.
     */
    protected synchronized boolean doesAnErrorOccurs() {
        if (errors != null) {
            return errors.values().size() != 1;
        }
        return false;
    }

    /**
     * This scenario tests the quick fix concerning the project marker when a modeling project has no representations
     * file.
     */
    public void testNoRepresentationsFileMarker() {
        // Initialization of the modeling project
        designerProject.convertToModelingProject();
        designerProject.save();

        // Deletion of the aird file
        SWTBotTreeItem airdTreeItem = designerProject.getProjectTreeItem().getNode("representations.aird");
        airdTreeItem.select();
        SWTBotUtils.clickContextMenu(airdTreeItem, "Delete");
        bot.waitUntil(Conditions.shellIsActive("Delete Resources"));
        bot.button("OK").click();
        bot.button("Continue").click();
        bot.waitUntil(Conditions.shellIsActive("Close the representations file?"));
        bot.button("Yes").click();

        // Access the marker in the problem view and trigger the quick fix
        SWTBotView problemsView = bot.viewByPartName("Problems");
        problemsView.setFocus();
        SWTBotTree problemsTree = problemsView.bot().tree();
        problemsTree.getTreeItem("Errors (1 item)").expand();
        final SWTBotTreeItem node = problemsTree.getTreeItem("Errors (1 item)").getNode("Zero representations file found in \"DesignerTestProject\". A modeling project must contain one.");
        node.select();
        SWTBotUtils.clickContextMenu(node, "Quick Fix");
        bot.waitUntil(Conditions.shellIsActive("Quick Fix"));
        bot.button("Finish").click();

        // Check that the aird file has been created
        bot.waitUntil(new TreeItemAvailableCondition(designerProject.getProjectTreeItem(), "representations.aird", true));
        // Check that the problem view is now empty
        bot.waitUntil(new TreeItemChildrenNumberCondition(problemsTree, 0));
        Assert.assertEquals("The problem view should not have entries anymore", 0, problemsTree.getAllItems().length);
        Session session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(TEMP_PROJECT_NAME + "/representations.aird", true), new NullProgressMonitor());
        Assert.assertNotNull("The session should not be null", session);
        Assert.assertTrue("The session is expected to be opened", session.isOpen());
        Assert.assertTrue("The session is not empty", session.getSemanticResources().size() > 0);
        Assert.assertEquals("The session is not empty", SEMANTIC_MODEL_PATH, session.getSemanticResources().iterator().next().getURI().lastSegment());
    }

    /**
     * This scenario tests the quick fix concerning the project marker when a modeling project has no representations
     * file.
     */
    public void testNoRepresentationsFileMarker2() {
        // Initialization of the modeling project
        designerProject.convertToModelingProject();

        // Deletion of the semantic model
        SWTBotTreeItem semanticModelTreeItem = designerProject.getProjectTreeItem().getNode(SEMANTIC_MODEL_PATH);
        semanticModelTreeItem.select();
        SWTBotUtils.clickContextMenu(semanticModelTreeItem, "Delete");
        bot.waitUntil(Conditions.shellIsActive("Delete Resources"));
        bot.button("OK").click();
        bot.waitUntil(new TreeItemAvailableCondition(designerProject.getProjectTreeItem(), SEMANTIC_MODEL_PATH, false));

        // Deletion of the aird file
        SWTBotTreeItem airdTreeItem = designerProject.getProjectTreeItem().getNode("representations.aird");
        airdTreeItem.select();
        SWTBotUtils.clickContextMenu(airdTreeItem, "Delete");
        bot.waitUntil(Conditions.shellIsActive("Delete Resources"));
        bot.button("OK").click();
        bot.button("Continue").click();
        bot.waitUntil(Conditions.shellIsActive("Close the representations file?"));
        bot.button("Yes").click();

        // Access the marker in the problem view and trigger the quick fix
        SWTBotView problemsView = bot.viewByPartName("Problems");
        problemsView.setFocus();
        SWTBotTree problemsTree = problemsView.bot().tree();
        problemsTree.getTreeItem("Errors (1 item)").expand();
        final SWTBotTreeItem node = problemsTree.getTreeItem("Errors (1 item)").getNode("Zero representations file found in \"DesignerTestProject\". A modeling project must contain one.");
        node.select();
        SWTBotUtils.clickContextMenu(node, "Quick Fix");
        bot.waitUntil(Conditions.shellIsActive("Quick Fix"));
        bot.button("Finish").click();

        // Check that the aird file has been created
        bot.waitUntil(new TreeItemAvailableCondition(designerProject.getProjectTreeItem(), "representations.aird", true));
        // Check that the problem view is now empty
        bot.waitUntil(new TreeItemChildrenNumberCondition(problemsTree, 0));
        Assert.assertEquals("The problem view should not have entries anymore", 0, problemsTree.getAllItems().length);
        Session session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(TEMP_PROJECT_NAME + "/representations.aird", true), new NullProgressMonitor());
        Assert.assertNotNull("The session should not be null", session);
        Assert.assertTrue("The session is expected to be opened", session.isOpen());

        // Create a new car model in the project
        SWTBotUtils.clickContextMenu(designerProject.getProjectTreeItem(), "Other...");
        bot.waitUntil(Conditions.shellIsActive("New"));
        bot.text("type filter text").setText("Car");
        bot.button("&Next >").click();
        bot.tree().select(designerProject.getName());
        bot.button("&Next >").click();
        bot.comboBox().setSelection("Garage");
        bot.button("Finish").click();

        // Check that the viewpoint Garage is available and can be selected
        designerProject.getProjectTreeItem().select();
        SWTBotUtils.clickContextMenu(designerProject.getProjectTreeItem(), "Viewpoints Selection");
        final SWTBotShell shellViewpointsSelection = bot.shell("Viewpoints Selection");
        Set<String> viewpointToSelect = Sets.newLinkedHashSet();
        viewpointToSelect.add("Documentation");
        viewpointToSelect.add("Garage");
        Set<String> viewpointToDeselect = Sets.newLinkedHashSet();
        new ViewpointSelectionDialog(bot).selectViewpoint(viewpointToSelect, viewpointToDeselect);
        bot.waitUntil(Conditions.shellCloses(shellViewpointsSelection));
        Assert.assertEquals("2 viewpoints should have been selected", 2, session.getSelectedViewpoints(false).size());
    }

}
