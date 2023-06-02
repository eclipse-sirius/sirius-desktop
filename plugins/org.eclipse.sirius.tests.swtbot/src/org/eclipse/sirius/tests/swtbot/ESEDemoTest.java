/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class ESEDemoTest extends AbstractScenarioTestCase {

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private UILocalSession localSession;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        UIResource ecoreEcoreResource = new UIResource(designerProject, MODELS_DIR, "Ecore.ecore");
        SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);
        localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);
        SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode("ecore");
        localSession.newDiagramRepresentation("ecore package entities", "Entities").on(ecoreTreeItem).withDefaultName().ok();
    }

    @Override
    protected void tearDown() throws Exception {
        // Close editor without saving
        closeAllEditors();
        // Close the session
        localSession.close(false);
        super.tearDown();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testGEFBotAPI() throws Exception {
        SWTBotSiriusDiagramEditor editor = SWTBotSiriusHelper.getSiriusDiagramEditor("ecore package entities");

        /* test tool element creation API to Add a node element */
        editor.setFocus();
        editor.activateTool("Class");
        ICondition done = new OperationDoneCondition();
        editor.click(100, 350);
        bot.waitUntil(done);

        editor.activateTool("Class");
        done = new OperationDoneCondition();
        editor.click(350, 350);
        bot.waitUntil(done);

        /* test direct edition API */
        editor.click(NEW_ECLASS_1);
        final Request request = new DirectEditRequest();

        /*
         * Workaround for GMF based modelers -> need to be in a SWTBotGMFEditor ->
         * org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants. REQ_DIRECTEDIT_EXTENDEDDATA_INITIAL_CHAR
         */
        request.getExtendedData().put("directedit_extendeddata_initial_char", 'a');

        final EditPart part = editor.getEditPart(NEW_ECLASS_1).part();
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                part.performRequest(request);
            }
        });

        editor.directEditType("MySuperClassForEse");

        /* retrieve the properties view */
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBot propertiesViewBot = propertiesView.bot();
        SWTBotTree tree = propertiesViewBot.tree();

        /* Change the name of the class by properties */
        editor.click(NEW_ECLASS_2);
        SWTBotTreeItem nodeClass22Name = tree.getTreeItem(NEW_ECLASS_2).getNode("Name");

        nodeClass22Name.select();
        bot.sleep(100);
        done = new OperationDoneCondition();
        SWTBotText text = propertiesViewBot.text();
        text.setText("Class2");
        tree.select(0);
        bot.waitUntil(done);

        /* test tool element creation API to Add a node element */
        editor.activateTool("Reference");
        editor.click("MySuperClassForEse");
        editor.click("Class2");
        editor.activateTool("Select");

        /* retrieve the outline */
        SiriusOutlineView outlineView = designerViews.openOutlineView().layers();

        /* test tool element creation API to Add a node element */
        editor.activateTool("Package");
        done = new OperationDoneCondition();
        editor.click(200, 100);
        bot.waitUntil(done);

        editor.activateTool("Class");
        done = new OperationDoneCondition();
        editor.click(215, 115);
        bot.waitUntil(done);

        editor.activateTool("Package");
        done = new OperationDoneCondition();
        editor.click(300, 220);
        bot.waitUntil(done);

        done = new OperationDoneCondition();
        outlineView.activateLayer("Package");
        bot.waitUntil(done);

        done = new OperationDoneCondition();
        outlineView.activateLayer("Package");
        bot.waitUntil(done);

        SWTBotCommonHelper.closeCurrentEditor();
        SWTBot shellBot = SWTBotSiriusHelper.getShellBot("Save");
        shellBot.button("No").click();
    }

}
