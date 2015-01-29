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
package org.eclipse.sirius.tests.swtbot.std;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNoOpenedSessionInModelContentView;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class for STD 008.
 * 
 * @author nlepine
 */
public class STD008 extends AbstractSiriusSwtBotGefTestCase {

    private static final String NEW_CLASS = "NewEClass3";

    private final int DEFAULT_SLEEP_TIMER = 500;

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private static final String MODEL = "STD-TEST-008.ecore";

    private static final String SESSION_FILE = "STD-TEST-008.aird";

    private static final String DATA_UNIT_DIR = "data/unit/std/008/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootSTDTestCase package entities";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM = "Entities";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSTD008() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
            org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find node with text: STD-TEST-008.ecore
            at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem.java:334)
            at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:308)
            at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:346)
            at org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.getUIItemFromResource(UIProject.java:152)
            at org.eclipse.sirius.tests.swtbot.support.api.business.UIProject.selectResource(UIProject.java:122)
            at org.eclipse.sirius.tests.swtbot.support.api.business.UIPerspective.openSessionCreationWizardFromSemanticResource(UIPerspective.java:188)
            at org.eclipse.sirius.tests.swtbot.std.STD008.testSTD008(STD008.java:74)
             */
            return;
        }
        final UIResource ecoreEcoreResource = new UIResource(designerProject, FILE_DIR, MODEL);

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        // Open the editor on the representation (that is automatically created)
        final SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM, DDiagram.class);

        // Adding various items of the palette on the diagram
        // Add a package
        editor.activateTool("Class");
        editor.click(50, 100);
        bot.sleep(DEFAULT_SLEEP_TIMER);

        // Move the class
        editor.drag(NEW_CLASS, 150, 150);

        // resize the class
        final Rectangle bounds = ((GraphicalEditPart) editor.getEditPart(NEW_CLASS).part()).getFigure().getBounds();
        editor.drag(bounds.x + bounds.width, bounds.y + bounds.height, 250, 250);

        // Close the session and save the diagram
        localSession.close(true);

        bot.waitUntil(new CheckNoOpenedSessionInModelContentView(bot, SESSION_FILE));

        // Re open session
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME_DIAGRAM)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM, UIDiagramRepresentation.class).open();
        // Open the editor on the representation
        SWTBotSiriusDiagramEditor editor2 = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM, DDiagram.class);

        final SWTBotGefEditPart editPart = editor2.getEditPart(NEW_CLASS);
        final Rectangle bounds2 = ((GraphicalEditPart) editPart.part()).getFigure().getBounds();
        assertNotNull(editPart);
        assertEquals(bounds2, bounds);

        localSession.closeNoDirty();

    }

}
