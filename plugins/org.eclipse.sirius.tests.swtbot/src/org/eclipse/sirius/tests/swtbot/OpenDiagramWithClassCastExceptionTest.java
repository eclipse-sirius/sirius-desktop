/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Test suite to make sure that ClassCastException at diagram opening is properly handled.
 * 
 * @author fbarbin
 */
public class OpenDiagramWithClassCastExceptionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/classCastDiagram/";

    private static final String MODELER_PATH = "My.odesign";

    private static final String SEMANTIC_MODEL_PATH = "My1.ecore";

    private static final String AIRD_MODEL_PATH = "My.aird";

    private static final String DESC_NAME = "Diag";

    private static final String REPRESENTATION_NAME = "new " + DESC_NAME;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL_PATH, AIRD_MODEL_PATH, MODELER_PATH);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", AIRD_MODEL_PATH);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Make sure that when a ClassCastException occurs while opening a diagram (because of an unsynchronized issue
     * between GMF and Sirius model), the dialog asking for refreshing the editor is open and when accepting, the editor
     * is refreshed.
     */
    public void testClassCastException() {

        // If the refresh at opening is activated, the error does not occur.
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        // We perform the openRepresentation in a separate thread to avoid blocking the swtbot thread while the popup
        // asking for refreshing the representation is open. Indeed, openReprensation will call
        // DialectUIManager.INSTANCE.openEditor that will perform an EclipseUIUtil.displaySyncExec. That causes the
        // SWTBot thread waiting for the UI Thread end of execution but this one is waiting for the user with the
        // popup.
        Thread thread = new Thread(() -> {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DESC_NAME, REPRESENTATION_NAME, DDiagram.class);
        });
        thread.start();
        bot.waitUntil(Conditions.shellIsActive("Refresh the diagram"));
        bot.activeShell().bot().button("OK").click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                // After having performed the refresh, the editor is expected to be dirty.
                return editor != null && editor.isDirty() && editor.getTitle().equals(REPRESENTATION_NAME);
            }

            @Override
            public String getFailureMessage() {
                return "The editor should be opened and dirty.";
            }
        });
    }
}
