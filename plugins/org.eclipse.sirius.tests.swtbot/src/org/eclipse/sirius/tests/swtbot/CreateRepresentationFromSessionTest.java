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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test representation creation from Model Content view
 * 
 * @author nlepine
 */
public class CreateRepresentationFromSessionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String THERE_IS_NO_SESSION = "There is no session";

    private static final String THE_REPRESENTATION_IS_NOT_CREATED = "The representation is not created";

    private static final String OK = "OK";

    private static final String DIAGRAM_NAME = "Entities";

    private static final String TABLE_NAME = "Classes";

    private static final String TREE_NAME = "Tree";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String CANCEL = "Cancel";

    private static final String NEXT = "Next >";

    private static final String BACK = "< Back";

    private static final String FINISH = "Finish";

    private static final String MODEL_001 = "My.ecore";

    private static final String MODEL_002 = "My_.ecore";

    private static final String MODEL_003 = "My2.ecore";

    private static final String SESSION_FILE_001 = "My.aird";

    private static final String SESSION_FILE_002 = "My_.aird";

    private static final String VSM = "ecore.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/session/representationCreation/";

    private static final String FILE_DIR = "/";

    private UILocalSession localSession;

    /**
     * Test the diagram creation on session wizard.
     * 
     */
    public void testDiagramCreation() {
        // create the representation
        SWTBotEditor editor = createRepresentation(DIAGRAM_NAME, "p1");
        assertNotNull(THE_REPRESENTATION_IS_NOT_CREATED, editor);

        Session session = localSession.getOpenedSession();
        assertNotNull(THERE_IS_NO_SESSION, session);
        session.close(new NullProgressMonitor());
    }

    /**
     * Test the table creation on session wizard.
     * 
     */
    public void testTableCreation() {
        // create the representation
        SWTBotEditor editor = createRepresentation(TABLE_NAME, "p1");
        assertNotNull(THE_REPRESENTATION_IS_NOT_CREATED, editor);

        Session session = localSession.getOpenedSession();
        assertNotNull(THERE_IS_NO_SESSION, session);
        session.close(new NullProgressMonitor());
    }

    /**
     * Test the table creation on session wizard.
     * 
     */
    public void testTreeCreation() {
        // create the representation
        SWTBotEditor editor = createRepresentation(TREE_NAME, "p1");
        assertNotNull(THE_REPRESENTATION_IS_NOT_CREATED, editor);

        Session session = localSession.getOpenedSession();
        assertNotNull(THERE_IS_NO_SESSION, session);
        session.close(new NullProgressMonitor());
    }

    /**
     * Test the import diagram creation on session wizard.
     * 
     */
    public void testImportDiagramCreation() {
        // create the representation
        SWTBotEditor editor = createRepresentation("Diagram Import Import", "Import", "p1");
        assertNotNull(THE_REPRESENTATION_IS_NOT_CREATED, editor);

        Session session = localSession.getOpenedSession();
        assertNotNull(THERE_IS_NO_SESSION, session);
        session.close(new NullProgressMonitor());
    }

    /**
     * Test the diagram creation on session wizard with no semantic element in the model.
     * 
     */
    public void testDiagramCreationWithoutSemanticElement() {
        SWTBot wizardBot = secondWizard("WithoutSemanticElement");
        SWTBotTreeItem[] allItems = wizardBot.tree().getAllItems();
        assertEquals("The session must have 0 semantic resources.", 0, allItems.length);
        for (int i = 0; i < allItems.length; i++) {
            SWTBotTreeItem swtBotTreeItem = allItems[i];
            for (int j = 0; j < swtBotTreeItem.getItems().length; j++) {
                SWTBotTreeItem swtBotTreeItem2 = swtBotTreeItem.getItems()[j];
                assertFalse(swtBotTreeItem2.isActive());
            }
        }
        wizardBot.button(CANCEL).click();

        Session session = localSession.getOpenedSession();
        assertNotNull(THERE_IS_NO_SESSION, session);
        assertEquals(session.getStatus(), SessionStatus.SYNC);
        session.close(new NullProgressMonitor());
    }

    /**
     * Test the cancel on the first wizard.
     * 
     */
    public void testCancelFirstWizard() {
        cancelFirstWizard();

        Session session = localSession.getOpenedSession();
        assertNotNull(THERE_IS_NO_SESSION, session);
        assertEquals(session.getStatus(), SessionStatus.SYNC);
        session.close(new NullProgressMonitor());
    }

    /**
     * Test the cancel on the first wizard.
     * 
     */
    public void testCancelSecondWizard() {
        cancelSecondWizard(TREE_NAME);

        Session session = localSession.getOpenedSession();
        assertNotNull(THERE_IS_NO_SESSION, session);
        assertEquals(session.getStatus(), SessionStatus.SYNC);
        session.close(new NullProgressMonitor());
    }

    /**
     * Test that empty viewpoint are not displayed
     */
    public void testEmptySirius() {
        // create representation
        createOnContextMenu();

        // select representation to create
        bot.waitUntil(Conditions.shellIsActive("Create Representation Wizard"));
        SWTBotShell shell = bot.shell("Create Representation Wizard");
        shell.activate();

        boolean found = true;
        try {
            shell.bot().tree().expandNode("Documentation");
        } catch (WidgetNotFoundException e) {
            found = false;
        }
        assertFalse("The viewpoint Documentation must be not visible", found);
    }

    /**
     * Use context menu and open create representation wizard.
     */
    private void createOnContextMenu() {
        SWTBotTreeItem sessionTreeItem = localSession.getRootSessionTreeItem();

        try {
            sessionTreeItem.contextMenu("Create Representation").click();
        } catch (WidgetNotFoundException e) {
            SWTBotUtils.clickContextMenu(sessionTreeItem, "Create Representation");
        }
    }

    /**
     * Create a representation from the session wizard.
     * 
     * @param representation
     * @param semanticElement
     * @return the opened representation
     */
    private SWTBotEditor createRepresentation(String representation, String semanticElement) {
        return createRepresentation(representation, representation, semanticElement);
    }

    /**
     * Create a representation from the session wizard.
     * 
     * @param representation
     * @param representationDescLabel
     * @param semanticElement
     * @return the opened representation
     */
    private SWTBotEditor createRepresentation(String representation, String representationDescLabel, String semanticElement) {
        SWTBot wizardBot = secondWizard(representation);

        wizardBot.tree().expandNode("platform:/resource/DesignerTestProject/My.ecore").expandNode(semanticElement).select();
        checkButtonAfterSelectionSecondWizard(wizardBot);
        wizardBot.button(FINISH).click();

        // choose the representation name
        bot.waitUntil(Conditions.shellIsActive("New " + representationDescLabel));
        SWTBotShell shell = bot.shell("New " + representationDescLabel);
        shell.activate();
        shell.bot().button(OK).click();
        SWTBotUtils.waitAllUiEvents();
        SWTBotUtils.waitProgressMonitorClose("Representation creation");

        return bot.activeEditor();
    }

    /**
     * Cancel the second wizard.
     * 
     * @param representation
     *            name
     */
    private void cancelSecondWizard(String representation) {
        SWTBot wizardBot = secondWizard(representation);

        wizardBot.button(CANCEL).click();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Open the second wizard.
     * 
     * @param representation
     * @return
     */
    private SWTBot secondWizard(String representation) {
        // create representation
        createOnContextMenu();

        // select representation to create
        bot.waitUntil(Conditions.shellIsActive("Create Representation Wizard"));
        SWTBotShell shell = bot.shell("Create Representation Wizard");
        shell.activate();
        SWTBot wizardBot = shell.bot();
        checkButtonBeforeSelectionFirstWizard(wizardBot);

        wizardBot.tree().expandNode(VIEWPOINT_NAME).expandNode(representation).select();
        checkButtonAfterSelectionFirstWizard(wizardBot);
        wizardBot.button(NEXT).click();

        // select semantic element of the new representation
        bot.waitUntil(Conditions.shellIsActive("Create Representation"));
        shell = bot.shell("Create Representation");
        shell.activate();
        checkButtonBeforeSelectionSecondWizard(shell.bot());

        return shell.bot();
    }

    /**
     * cancel on the first wizard
     */
    private void cancelFirstWizard() {
        createOnContextMenu();

        // select representation to create
        bot.waitUntil(Conditions.shellIsActive("Create Representation Wizard"));
        SWTBotShell shell = bot.shell("Create Representation Wizard");
        shell.activate();
        shell.bot().button(CANCEL);
    }

    private void checkButtonAfterSelectionSecondWizard(SWTBot swtBot) {
        assertFalse(swtBot.button(NEXT).isEnabled());
        assertTrue(swtBot.button(BACK).isEnabled());
        assertTrue(swtBot.button(CANCEL).isEnabled());
    }

    private void checkButtonBeforeSelectionSecondWizard(SWTBot swtBot) {
        assertFalse(swtBot.button(FINISH).isEnabled());
        assertTrue(swtBot.button(BACK).isEnabled());
        assertFalse(swtBot.button(NEXT).isEnabled());
        assertTrue(swtBot.button(CANCEL).isEnabled());
    }

    private void checkButtonAfterSelectionFirstWizard(SWTBot swtBot) {
        assertFalse(swtBot.button(FINISH).isEnabled());
        assertFalse(swtBot.button(BACK).isEnabled());
        assertTrue(swtBot.button(CANCEL).isEnabled());
    }

    private void checkButtonBeforeSelectionFirstWizard(SWTBot swtBot) {
        assertFalse(swtBot.button(FINISH).isEnabled());
        assertFalse(swtBot.button(BACK).isEnabled());
        assertFalse(swtBot.button(NEXT).isEnabled());
        assertTrue(swtBot.button(CANCEL).isEnabled());
    }

    /**
     * Return files used in the current test.
     */
    String[] getFilesUsedForTest() {
        return new String[] { MODEL_001, SESSION_FILE_001, MODEL_002, SESSION_FILE_002, MODEL_003, VSM };
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
        UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_001);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

}
