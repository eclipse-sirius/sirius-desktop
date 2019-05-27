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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.support.api.matcher.AbstractDecoratorMatcher;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OpenedSessionCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.hamcrest.Description;

/**
 * Test class
 * 
 * @author smonnier
 */
public class ValidationTest extends AbstractScenarioTestCase {

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private static final int DEFAULT_SLEEP_TIMER = 500;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
    }

    /**
     * Test.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testInitializeSession() throws Exception {

        final UIResource ecoreEcoreResource = new UIResource(designerProject, MODELS_DIR, "Ecore.ecore");

        // Create session and aird.
        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);
        localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode("ecore");
        final UIDiagramRepresentation representation = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("Design").selectRepresentation("Entities")
                .selectRepresentationInstance("ecore package entities", UIDiagramRepresentation.class).open();

        // Initialization of a bot on the diagram
        // save if sesion is dirty
        localSession.save();
        editor = representation.getEditor();

        // Adding of various item of the palette on the diagram
        // Add a class
        editor.activateTool("Class");
        editor.click(50, 100);
        bot.waitUntil(new CheckSelectedCondition(editor, NEW_ECLASS_1));

        // Launch diagram validation and close the diagram
        editor.mainEditPart().select();
        editor.clickContextMenu("Validate diagram");
        bot.sleep(DEFAULT_SLEEP_TIMER);
        editor.close();

        // Create a table
        semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        ecoreTreeItem = semanticResourceNode.getNode("ecore").select();
        SWTBotUtils.clickContextMenu(ecoreTreeItem, "Classes in ecore package");
        SWTBot wizardBot = SWTBotSiriusHelper.getShellBot("New Classes");
        wizardBot.button("OK").click();
        bot.sleep(1000);
        SWTBotUtils.waitProgressMonitorClose("Progress Information");

        // Close the table
        SWTBotCommonHelper.closeCurrentEditor();
        wizardBot = SWTBotSiriusHelper.getShellBot("Save");
        wizardBot.button(TestsUtil.isOxygenPlatform() ? "Don't Save" : "No").click();
        bot.sleep(500);

        // Check that all editors are closes
        assertTrue("There should be no opened editor.", bot.editors().isEmpty());
        assertEquals("There should not be more than one open session.", 1, SessionManager.INSTANCE.getSessions().size());

        // Open the "Problems" view
        openProblemsView();

        // Find and the "Infos" item in the Problems view about the class we
        // previously created
        doubleClicOnMarker();

        assertEquals("There should be 1 opened editor.", 1, bot.editors().size());
        SWTBotEditor activeEditor = bot.activeEditor();
        editor = representation.getEditor();
        assertEquals(activeEditor.getReference(), editor.getReference());
        assertEquals("The wrong editor was opened", "ecore package entities", editor.getTitle());
        assertEquals("There should not be more than one open session.", 1, SessionManager.INSTANCE.getSessions().size());
        assertEquals("The selection should be the element concerned by the marker", NEW_ECLASS_1,
                ((IDiagramElementEditPart) ((IStructuredSelection) editor.getSelection()).iterator().next()).resolveDiagramElement().getName());

        // Close the session and save it
        editor.close();
        localSession.close(true);
        bot.waitUntil(new OpenedSessionCondition(0));

        assertEquals("There should not be any opened session.", 0, SessionManager.INSTANCE.getSessions().size());
        assertEquals("There should not be any opened editor.", true, bot.editors().isEmpty());

        // Find and the "Infos" item in the Problems view about the class we
        // previously created
        doubleClicOnMarker();

        assertEquals("There should be 1 opened editor.", 1, bot.editors().size());
        activeEditor = bot.activeEditor();
        editor = representation.getEditor();
        assertEquals(activeEditor.getReference(), editor.getReference());
        assertEquals("The wrong editor was opened", "ecore package entities", editor.getTitle());
        assertEquals("There should one open session.", 1, SessionManager.INSTANCE.getSessions().size());
        Object selection = ((IStructuredSelection) editor.getSelection()).iterator().next();
        assertTrue("Selection should be a graphical edit part", selection instanceof IDiagramElementEditPart);
        IDiagramElementEditPart editPart = (IDiagramElementEditPart) selection;
        assertEquals("The selection should be the element concerned by the marker", NEW_ECLASS_1, editPart.resolveDiagramElement().getName());

        assertTrue("Edit part should be decorated with an information image", hasValidationInfoDecoration(editPart));

        representation.getEditor().click(NEW_ECLASS_1);
        representation.getEditor().close();
        // Save and close the session
        activeEditor.close();
        localSession.close(true);
    }

    /**
     * 
     */
    private void doubleClicOnMarker() {
        final SWTBotView errorLogView = bot.viewByPartName("Problems");
        errorLogView.setFocus();
        SWTBotTree errorLogTree = errorLogView.bot().tree();
        bot.sleep(DEFAULT_SLEEP_TIMER);

        errorLogTree.getTreeItem("Infos (1 item)").expand();
        bot.sleep(DEFAULT_SLEEP_TIMER);

        errorLogTree.getTreeItem("Infos (1 item)").getNode("The " + NEW_ECLASS_1 + " class is never used").select();
        bot.sleep(DEFAULT_SLEEP_TIMER);

        // Double click the info to reopen the diagram
        final SWTBotTreeItem node = errorLogTree.getTreeItem("Infos (1 item)").getNode("The " + NEW_ECLASS_1 + " class is never used");
        node.doubleClick();
        bot.sleep(DEFAULT_SLEEP_TIMER * 5);

    }

    /**
     * Indicates if the given editPart has an image decoration indicating a validation info.
     * 
     * @param editPart
     *            the edit part to test
     * @return true if the given editPart has an image decoration indicating a validation info, false otherwise
     */
    private boolean hasValidationInfoDecoration(IDiagramElementEditPart editPart) {
        return new AbstractDecoratorMatcher() {

            @Override
            protected Image getImage() {
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
            }

            @Override
            public void describeTo(Description description) {

            }
        }.matches(editPart);
    }

    private void openProblemsView() {
        bot.menu("Window").menu("Show View").menu("Other...").click();

        bot.sleep(DEFAULT_SLEEP_TIMER);

        bot.text().setText("pro");
        bot.sleep(DEFAULT_SLEEP_TIMER);

        bot.tree().getTreeItem("General").expand().getNode("Problems").select();

        bot.button(TestsUtil.isOxygenPlatform() ? "Open" : "OK").click();
    }
}
