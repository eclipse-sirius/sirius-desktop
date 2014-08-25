/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Test class for tree folding.
 * 
 * @author smonnier
 */
public class NavigationFromDNodeListElementTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new TC814 Square List Package";

    private static final String REPRESENTATION_NAME = "TC814 Square List Package";

    private static final String VIEWPOINT_NAME = "Test case for ticket #814";

    private static final String MODEL = "tc814.ecore";

    private static final String SESSION_FILE = "tc814.aird";

    private static final String VSM_FILE = "tc814.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/navigation/tc814/";

    private static final String FILE_DIR = "/";

    private static final String EXPECTED_NAVIGATION_REPRESENTATION_NAME = "New detail : Navigate to Not List";

    private static final String EXPECTED_NAVIGATION_REPRESENTATION_INSTANCE_NAME = "new Navigate to Not List";

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

        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME, UIDiagramRepresentation.class).open();

        editor = diagram.getEditor();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNavigationFromDNodeListElement() throws Exception {
        final long oldTimeout = SWTBotPreferences.TIMEOUT;

        try {
            SWTBotPreferences.TIMEOUT = 1000;

            editor.click(175, 45);

            editor.clickContextMenu(EXPECTED_NAVIGATION_REPRESENTATION_NAME);

            bot.button("OK").click();
            assertEditorIsNotError("Right click navigation editor did not opened correctly", bot.activeEditor());
            assertEquals("The active editor is not the one expected", EXPECTED_NAVIGATION_REPRESENTATION_INSTANCE_NAME, bot.activeEditor().getTitle());
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }
}
