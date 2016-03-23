/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemSelected;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.test.AbstractMMEcoreBasedScenarioTestCase;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class.
 * 
 * @author smonnier
 */
public class RequestInterpreterTest extends AbstractMMEcoreBasedScenarioTestCase {

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testInitalizeSession() throws Exception {
        // Disable test that fails after Juno3 release (see Bugzilla 424429).
        if (TestsUtil.isEclipse4xPlatform()) {
            return;
        }
        final UIResource ecoreEcoreResource = new UIResource(designerProject, MODELS_DIR, "Ecore.ecore");
        final UIResource ecoreAirdResource = new UIResource(designerProject, MODELS_DIR, "Ecore.aird");

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        final UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode("ecore");
        UIDiagramRepresentation openedDiagram = localSession.newDiagramRepresentation("ecore package entities", "Entities").on(ecoreTreeItem).withDefaultName().ok();

        // Set focus in 4.x
        openedDiagram.getEditor().click(0, 0);

        // Save and close the editor
        SWTBotCommonHelper.saveCurrentEditor(localSession.getOpenedSession());
        // SWTBotCommonHelper.closeCurrentEditor();

        // Initialization of a bot on the diagram

        // Open model requests interpreter view
        bot.menu("Window").menu("Show View").menu("Other...").click();
        bot.waitUntil(Conditions.shellIsActive("Show View"));
        SWTBot viewsListViewBot = bot.activeShell().bot();
        viewsListViewBot.text().setText("Model requests interpreter");
        SWTBotTree viewsTreeBot = viewsListViewBot.tree();
        SWTBotTreeItem modelRequestsInterpreterTreeItemBot = viewsTreeBot.getTreeItem("Sirius").expand().select().getNode("Model requests interpreter").select();
        modelRequestsInterpreterTreeItemBot.select();
        bot.waitUntil(new TreeItemSelected(modelRequestsInterpreterTreeItemBot));
        viewsListViewBot.button("OK").click();

        final SWTBotView requestInterpreterView = bot.viewByTitle("Model requests interpreter");
        requestInterpreterView.setFocus();

        final SWTBotSiriusDiagramEditor editor = SWTBotSiriusHelper.getSiriusDiagramEditor("ecore package entities");
        editor.click("EBigInteger");

        final SWTBot requestsInterpreterBot = requestInterpreterView.bot();
        requestsInterpreterBot.text().setText("feature:target");

        final SWTBotTree requestInterpreterTree = bot.tree();

        requestInterpreterTree.getTreeItem("EBigInteger [java.math.BigInteger]").expand().expandNode("ExtendedMetaData").getNode(0).select();

        requestsInterpreterBot.button("Set").click();

        bot.text().setText("EBigIntegerVar");

        bot.button("OK").click();

        requestsInterpreterBot.text().setText("var:EBigIntegerVar");

        requestsInterpreterBot.button("Set").click();

        bot.button("OK").click();

        requestsInterpreterBot.button("Set").click();

        bot.text().setText("element2");

        bot.button("OK").click();

        final SWTBotTree variableTree = requestsInterpreterBot.tree(1);

        SWTBotTreeItem[] allItems = variableTree.getAllItems();
        assertEquals("There is not as many items as expected", 3, allItems.length);
        assertTrue("The variable name is not correct", allItems[0].getText().startsWith("element="));
        assertTrue("The variable name is not correct", allItems[1].getText().startsWith("element2="));
        assertTrue("The variable name is not correct", allItems[2].getText().startsWith("EBigIntegerVar="));

        variableTree.getAllItems()[1].select();

        bot.button("Unset").click();

        variableTree.getAllItems()[0].select();

        bot.button("Unset").click();

        variableTree.getAllItems()[0].select();

        bot.button("Unset").click();

        allItems = variableTree.getAllItems();
        assertEquals("There is not as many items as expected. Some variables may have not been unset.", 0, allItems.length);

        localSession.closeNoDirty();
        designerProject.deleteResource(ecoreAirdResource);
    }

}
