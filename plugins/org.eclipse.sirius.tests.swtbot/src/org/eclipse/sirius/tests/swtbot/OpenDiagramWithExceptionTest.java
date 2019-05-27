/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Test suite to make sure that exceptions at diagram opening are properly handled.
 * 
 * @author fbarbin
 */
public class OpenDiagramWithExceptionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/classCastDiagram/";

    private static final String PATH2 = "/data/unit/classCastDiagram/illegalArgumentExceptionSample/";

    private static final String MODELER_PATH = "My.odesign";

    private static final String SEMANTIC_MODEL_PATH = "My1.ecore";

    private static final String SEMANTIC_MODEL_PATH2 = "My.ecore";

    private static final String AIRD_MODEL_PATH = "My.aird";

    private static final String AIRD_MODEL_PATH2 = "representations.aird";

    private static final String DESC_NAME = "Diag";

    private static final String DESC_NAME2 = "Doremi-4005";

    private static final String REPRESENTATION_NAME = "new " + DESC_NAME;

    private static final String REPRESENTATION_NAME2 = "new " + DESC_NAME2;

    /**
     * Make sure that when a {@link ClassCastException} occurs while opening a diagram (because of an unsynchronized
     * issue between GMF and Sirius model), the dialog asking for refreshing the editor is open and when accepting, the
     * editor is refreshed.
     */
    public void testClassCastException() {
        openDiagram(PATH, SEMANTIC_MODEL_PATH, AIRD_MODEL_PATH, DESC_NAME, REPRESENTATION_NAME);
    }

    /**
     * Make sure that when an {@link IllegalArgumentException} occurs while opening a diagram (because of an
     * unsynchronized issue between GMF and Sirius model), the dialog asking for refreshing the editor is open and when
     * accepting, the editor is refreshed.
     */
    public void testIllegalArgumentException() {
        openDiagram(PATH2, SEMANTIC_MODEL_PATH2, AIRD_MODEL_PATH2, DESC_NAME2, REPRESENTATION_NAME2);
    }

    /**
     * Make sure that when an {@link IllegalArgumentException} occurs while opening a diagram (because of an
     * unsynchronized issue between GMF and Sirius model), the dialog asking for refreshing the editor is open and when
     * accepting, the editor is refreshed.
     * 
     * @param path
     *            path of the sample
     * @param semanticModelPath
     *            semantic model
     * @param airdModelPath
     *            aird model
     * @param descName
     *            {@link DiagramDescription} name
     * @param representationName
     *            {@link DRepresentation} name
     */
    public void openDiagram(String path, String semanticModelPath, String airdModelPath, String descName, String representationName) {

        // If the refresh at opening is activated, the error does not occur.
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        copyFileToTestProject(Activator.PLUGIN_ID, path, semanticModelPath, airdModelPath, MODELER_PATH);
        sessionAirdResource = new UIResource(designerProject, "/", airdModelPath);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        // We perform the openRepresentation in a separate thread to avoid blocking the swtbot thread while the popup
        // asking for refreshing the representation is open. Indeed, openReprensation will call
        // DialectUIManager.INSTANCE.openEditor that will perform an EclipseUIUtil.displaySyncExec. That causes the
        // SWTBot thread waiting for the UI Thread end of execution but this one is waiting for the user with the
        // popup.
        Thread thread = new Thread(() -> {
            editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), descName, representationName, DDiagram.class);
        });
        thread.start();
        SWTBotSiriusHelper.getShellBot("Refresh the diagram").button("OK").click();
        bot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                // After having performed the refresh, the editor is expected to be dirty.
                return editor != null && editor.isDirty() && editor.getTitle().equals(representationName);
            }

            @Override
            public String getFailureMessage() {
                return "The editor should be opened and dirty.";
            }
        });
    }
}
