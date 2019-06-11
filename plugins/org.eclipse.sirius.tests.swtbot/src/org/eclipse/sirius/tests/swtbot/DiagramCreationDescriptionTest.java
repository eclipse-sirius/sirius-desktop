/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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

import java.text.MessageFormat;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.IEditorReference;

/**
 * Test class for diagram creation description.
 * 
 * @author smonnier
 */
public class DiagramCreationDescriptionTest extends AbstractSiriusSwtBotGefTestCase {

    /**
     * 
     */
    private static final String REPRESENTATION_DESCRIPTION_LABEL = "TC732 Square representation 2";

    private static final String REPRESENTATION_INSTANCE_NAME = "TC732 Square representation 1";

    private static final String REPRESENTATION_NAME = "TC732 Square representation 1";

    private static final String MODEL = "tc732.ecore";

    private static final String SESSION_FILE_1_VIEWPOINT = "tc732_1viewpoint.aird";

    private static final String SESSION_FILE_2_VIEWPOINTS = "tc732_2viewpoints.aird";

    private static final String VSM_FILE = "tc732.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/navigation/tc732/";

    private static final String FILE_DIR = "/";

    private static final String EXPECTED_NEW_REPRESENTATION_NAME = "Representation 2";

    private static final String EXPECTED_NEW_REPRESENTATION_INSTANCE_NAME = "new Representation 2";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE_1_VIEWPOINT, SESSION_FILE_2_VIEWPOINTS, VSM_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        diagram = null;
        super.tearDown();
    }

    private void initializeSecondSession() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_2_VIEWPOINTS);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    private void initializeFirstSession() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_1_VIEWPOINT);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentationFromContainer() throws Exception {
        doTestNewRepresentationWithoutContextualMenu(new Point(300, 300));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentationFromNode() throws Exception {
        doTestNewRepresentationWithoutContextualMenu(new Point(325, 160));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentationFromEdge() throws Exception {
        doTestNewRepresentationWithoutContextualMenu(new Point(265, 135));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentation2FromContainer() throws Exception {
        doTestNewRepresentationWithContextualMenu(new Point(300, 300), "System1)");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentation2FromContainerWithoutRefreshOnOpening() throws Exception {
        doTestNewRepresentationWithContextualMenu(new Point(300, 300), "System1)", true);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentation2FromNode() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        doTestNewRepresentationWithContextualMenu(new Point(325, 160), "Sous-package 2)");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentation2FromEdge() throws Exception {
        doTestNewRepresentationWithContextualMenu(new Point(265, 135), "System1)");
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNewRepresentation2FromEdgeWithoutRefreshOnOpening() throws Exception {
        doTestNewRepresentationWithContextualMenu(new Point(265, 135), "System1)", true);
    }

    private void doTestNewRepresentationWithContextualMenu(Point point, String diagramName) throws Exception {
        doTestNewRepresentationWithContextualMenu(point, diagramName, false);
    }

    private void doTestNewRepresentationWithContextualMenu(Point point, String diagramName, boolean disableRefreshOnOpening) throws Exception {
        if (disableRefreshOnOpening) {
            changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        }

        initializeSecondSession();

        editor.click(point.x, point.y);

        editor.clickContextMenu(EXPECTED_NEW_REPRESENTATION_NAME);

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(MessageFormat.format(Messages.createRepresentationInputDialog_Title, REPRESENTATION_DESCRIPTION_LABEL)));

        bot.button("OK").click();
        assertEditorIsNotError("Right click new representation editor did not opened correctly", bot.activeEditor());
        assertEquals("The active editor is not the one expected", EXPECTED_NEW_REPRESENTATION_INSTANCE_NAME, bot.activeEditor().getTitle());
        // Test VP-3039
        if (disableRefreshOnOpening) {
            IEditorReference reference = bot.activeEditor().getReference();
            SWTBotSiriusDiagramEditor activeEditor = new SWTBotSiriusDiagramEditor(reference, bot);
            assertNotNull("The diagram must not be blank", activeEditor.getEditPart("Sous-package1"));
        }
    }

    private void doTestNewRepresentationWithoutContextualMenu(Point point) throws Exception {
        final long oldTimeout = SWTBotPreferences.TIMEOUT;

        try {
            SWTBotPreferences.TIMEOUT = 1000;

            initializeFirstSession();

            editor.click(point.x, point.y);

            SWTBotUtils.waitAllUiEvents();

            editor.clickContextMenu(EXPECTED_NEW_REPRESENTATION_NAME);

            fail(WidgetNotFoundException.class + " New representation menu not expected to be available");
        } catch (final WidgetNotFoundException e) {
            assertEquals("The active editor is not the one expected", REPRESENTATION_INSTANCE_NAME, bot.activeEditor().getTitle());
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

}
