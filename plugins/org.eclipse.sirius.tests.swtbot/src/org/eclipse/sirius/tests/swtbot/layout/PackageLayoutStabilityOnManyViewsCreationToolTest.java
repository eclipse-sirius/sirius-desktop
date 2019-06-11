/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;

/**
 * Tests containers layout stability on adding ports using a Palette tool.
 * 
 * @author lchituc
 */
public class PackageLayoutStabilityOnManyViewsCreationToolTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new Diagram";

    private static final String REPRESENTATION_NAME = "Diagram";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/VP-2545/";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method when automatic refresh is disabled.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testContainerLayoutStabilityOnManyViewsCreationToolManualRefresh() throws Exception {
        // Gets the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart("p1").parent();
        final Point p1Location = editor.getBounds(p1Bot).getLocation();

        // Gets the location of P11
        SWTBotGefEditPart p11Bot = editor.getEditPart("p11").parent();
        final Point p11Location = editor.getBounds(p11Bot).getLocation();

        // Gets the location of P111
        SWTBotGefEditPart p111Bot = editor.getEditPart("p111").parent();
        final Point p111Location = editor.getBounds(p111Bot).getLocation();

        // Adds the ports for each package using the Palette tool
        editor.activateTool("New Port on each container");
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                // Click on p1 package
                editor.click(p111Location.x + 20, p111Location.y + 20);
            }
        });
        // Wait until the port is created and displayed on the diagram
        CheckEditPartIsDisplayed cS = new CheckEditPartIsDisplayed("C1", editor);
        bot.waitUntil(cS);

        // Launching manual refresh
        editor.click(0, 0);
        editor.save();
        manualRefresh();

        // Gets the location of P1 after adding the ports
        final Point p1Location_after = editor.getBounds(p1Bot).getLocation();

        // Gets the location of P11 after adding the ports
        final Point p11Location_after = editor.getBounds(p11Bot).getLocation();

        // Gets the location of P111 after adding the ports
        final Point p111Location_after = editor.getBounds(p111Bot).getLocation();

        // Asserts that the three packages kept their initial position.
        // Throw an exception if edit part of the added port, C1, is not
        // found
        assertTrue("The port, C1, should be displayed on the diagram", editor.getEditPart("C1") != null);
        assertEquals("p1 package has not kept its initial position!", p1Location, p1Location_after);
        assertEquals("p11 package has not kept its initial position!", p11Location, p11Location_after);
        assertEquals("p111 package has not kept its initial position!", p111Location, p111Location_after);

    }

    /**
     * Test method when automatic refresh is enabled.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testContainerLayoutStabilityOnManyViewsCreationToolAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        // Gets the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart("p1").parent();
        final Point p1Location = editor.getBounds(p1Bot).getLocation();

        // Gets the location of P11
        SWTBotGefEditPart p11Bot = editor.getEditPart("p11").parent();
        final Point p11Location = editor.getBounds(p11Bot).getLocation();

        // Gets the location of P111
        SWTBotGefEditPart p111Bot = editor.getEditPart("p111").parent();
        final Point p111Location = editor.getBounds(p111Bot).getLocation();

        // Adds the ports for each package using the Palette tool
        editor.activateTool("New Port on each container");
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                // Click on p1 package
                editor.click(p111Location.x + 20, p111Location.y + 20);
            }
        });
        // Wait until the port is created and displayed on the diagram
        CheckEditPartIsDisplayed cS = new CheckEditPartIsDisplayed("C1", editor);
        bot.waitUntil(cS);

        // Gets the location of P1 after adding the ports
        final Point p1Location_after = editor.getBounds(p1Bot).getLocation();

        // Gets the location of P11 after adding the ports
        final Point p11Location_after = editor.getBounds(p11Bot).getLocation();

        // Gets the location of P111 after adding the ports
        final Point p111Location_after = editor.getBounds(p111Bot).getLocation();

        // Asserts that the three packages kept their initial position.
        // Throw an exception if edit part of the added port, C1, is not
        // found
        assertTrue("The port, C1, should be displayed on the diagram", editor.getEditPart("C1") != null);
        assertEquals("p1 package has not kept its initial position!", p1Location, p1Location_after);
        assertEquals("p11 package has not kept its initial position!", p11Location, p11Location_after);
        assertEquals("p111 package has not kept its initial position!", p111Location, p111Location_after);
    }

}
