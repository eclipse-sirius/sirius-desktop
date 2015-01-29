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

/**
 * Test class for STD 010.
 * 
 * @author nlepine
 */
public class STD010 extends AbstractSiriusSwtBotGefTestCase {

    private static final String NEW_CLASS = "NewEClass1";

    private static final String NEW_ATTRIBUTE = "newAttribute";

    private static final String PACKAGE_NAME = "APackage";

    private final int DEFAULT_SLEEP_TIMER = 500;

    private final String[] viewpointsSelection = new String[] { "Design", "Quality" };

    private static final String MODEL = "STD-TEST-010.ecore";

    private static final String SESSION_FILE = "STD-TEST-010.aird";

    private static final String DATA_UNIT_DIR = "data/unit/std/010/";

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
    public void testSTD010() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        final UIResource ecoreEcoreResource = new UIResource(designerProject, FILE_DIR, MODEL);

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        // Open the editor on the representation (that is automatically created)
        SWTBotSiriusDiagramEditor editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM, DDiagram.class);

        // Adding various items of the palette on the diagram
        // Add a class
        editor.activateTool("Class");
        editor.click(PACKAGE_NAME);
        bot.sleep(DEFAULT_SLEEP_TIMER);

        // Add an attribute in the new class
        editor.activateTool("Attribute");
        editor.click(NEW_CLASS);
        bot.sleep(DEFAULT_SLEEP_TIMER);

        // Close the session
        localSession.close(true);

        bot.waitUntil(new CheckNoOpenedSessionInModelContentView(bot, SESSION_FILE));

        // Re open session
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME_DIAGRAM)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM, UIDiagramRepresentation.class).open();

        // Open the editor on the representation
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_NAME_DIAGRAM, REPRESENTATION_INSTANCE_NAME_DIAGRAM, DDiagram.class);

        assertNotNull(editor.getEditPart(NEW_CLASS));
        assertNotNull(editor.getEditPart(NEW_ATTRIBUTE));

        localSession.closeNoDirty();

    }

}
