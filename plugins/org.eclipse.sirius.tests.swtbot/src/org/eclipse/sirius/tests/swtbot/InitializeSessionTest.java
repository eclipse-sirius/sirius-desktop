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

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.SessionClosedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemSelected;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class.
 * 
 * @author smonnier
 */
public class InitializeSessionTest extends AbstractScenarioTestCase {

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testInitializeSession() throws Exception {

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
        final SWTBotSiriusDiagramEditor editor = SWTBotSiriusHelper.getSiriusDiagramEditor("ecore package entities");

        // Adding of various item of the palette on the diagram
        // Add a class
        ICondition classCreatedCondition = new OperationDoneCondition();
        editor.activateTool("Class");
        editor.click(100, 200);
        bot.waitUntil(classCreatedCondition);

        // Add a second class
        classCreatedCondition = new OperationDoneCondition();
        editor.activateTool("Class");
        editor.click(120, 350);
        bot.waitUntil(classCreatedCondition);

        // get the properties view
        bot.viewByTitle("Properties").setFocus();
        final SWTBot propertiesViewBot = bot.viewByTitle("Properties").bot();
        final SWTBotTree tree = propertiesViewBot.tree();

        // Change the name of the class
        editor.click(InitializeSessionTest.NEW_ECLASS_1);
        bot.viewByTitle("Properties").setFocus();
        final SWTBotTreeItem nodeClass21Name = tree.getTreeItem(InitializeSessionTest.NEW_ECLASS_1).getNode("Name");
        nodeClass21Name.select();
        nodeClass21Name.click();

        ICondition done = new OperationDoneCondition();
        propertiesViewBot.text().setText("Class1");
        tree.select(0);
        propertiesViewBot.waitUntil(done);

        SWTBotTreeItem interfaceOfNewEClass1TreeItem = tree.getTreeItem("Class1").getNode("Interface");
        interfaceOfNewEClass1TreeItem.select();
        bot.waitUntil(new TreeItemSelected(interfaceOfNewEClass1TreeItem));

        // Change the name of the class
        editor.click(InitializeSessionTest.NEW_ECLASS_2);
        bot.viewByTitle("Properties").setFocus();
        final SWTBotTreeItem nodeClass22Name = tree.getTreeItem(InitializeSessionTest.NEW_ECLASS_2).getNode("Name");
        nodeClass22Name.select();
        nodeClass22Name.click();

        done = new OperationDoneCondition();
        propertiesViewBot.text().setText("Class2");
        tree.select(0);
        propertiesViewBot.waitUntil(done);

        SWTBotTreeItem interfaceOfNewEClass2TreeItem = tree.getTreeItem("Class2").getNode("Interface");
        interfaceOfNewEClass2TreeItem.select();
        bot.waitUntil(new TreeItemSelected(interfaceOfNewEClass2TreeItem));

        // Add a reference between the two classes
        editor.activateTool("Reference");
        editor.click("Class1");
        editor.click("Class2");
        editor.activateTool("Select");

        // save the editor
        // SWTBotCommonHelper.saveCurrentEditor();
        SWTBotCommonHelper.closeCurrentEditor();
        SWTBotUtils.waitAllUiEvents();

        SWTBot shellBot = SWTBotSiriusHelper.getShellBot("Save");
        shellBot.button("No").click();

        // Create an entity diagram on the top element of the model

        localSession.close(false);
        bot.waitUntil(new SessionClosedCondition(localSession.getOpenedSession()));
        SWTBotUtils.waitAllUiEvents();

        designerProject.deleteResource(ecoreAirdResource);
    }
}
