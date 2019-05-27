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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Test for VP-1737.
 * 
 * @author pcdavid
 */
public class NavigateToNewRepresentationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "VP-1737.1";

    private static final String REPRESENTATION_NAME = "VP-1737.1";

    private static final String MODEL = "vp-1737.ecore";

    private static final String SESSION_FILE = "vp-1737.aird";

    private static final String VSM_FILE = "vp-1737.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/vp-1737/";

    private static final String FILE_DIR = "/";

    private static final String EXPECTED_NEW_REPRESENTATION_NAME = "Creation test";

    private static final String EXPECTED_NEW_REPRESENTATION_INSTANCE_NAME = "Test";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current editor.
     */
    protected SWTBotSiriusDiagramEditor editor;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test for VP-1737.
     */
    public void testCreateNewRepresentationWithComputedNamed() {
        final long oldTimeout = SWTBotPreferences.TIMEOUT;

        try {
            SWTBotPreferences.TIMEOUT = 1000;
            editor.click(65, 40);
            editor.clickContextMenu(EXPECTED_NEW_REPRESENTATION_NAME);
            SWTBot representationBot = SWTBotSiriusHelper.getShellBot("New VP-1737.2");
            representationBot.button("OK").click();
            assertEditorIsNotError("Right click New representation editor did not opened correctly", bot.activeEditor());
            assertEquals("The active editor is not the one expected", EXPECTED_NEW_REPRESENTATION_INSTANCE_NAME, bot.activeEditor().getTitle());
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }
}
