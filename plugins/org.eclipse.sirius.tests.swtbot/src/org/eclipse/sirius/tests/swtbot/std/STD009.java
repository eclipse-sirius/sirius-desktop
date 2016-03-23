/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.std;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNoOpenedSessionInModelContentView;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UISessionCreationWizardFlow.SessionChoice;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class for STD 009.
 * 
 * @author nlepine
 */
public class STD009 extends AbstractSiriusSwtBotGefTestCase {

    private static final String NEW_CLASS_1 = "Class1";

    private static final String NEW_CLASS_2 = "Class2";

    private final int DEFAULT_SLEEP_TIMER = 500;

    private final String[] viewpointsSelection = new String[] { "UML Analysis workspace" };

    private static final String MODEL = "STD-TEST-009.uml";

    private static final String SESSION_FILE = "STD-TEST-009.aird";

    private static final String DESIGN_FILE = "STD-TEST-009.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/std/009/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "new Class Diagram";

    private static final String MODEL_PACKAGE = "<Model>";

    private static final String VIEWPOINT_NAME = "UML Analysis workspace";

    private static final String REPRESENTATION_NAME_DIAGRAM = "Class Diagram";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, DESIGN_FILE);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSTD009() throws Exception {

        // Disable test that fails after Juno3 release (see Bugzilla
        // 424429).
        if (TestsUtil.isEclipse4xPlatform()) {
            return;
        }

        final UIResource ecoreEcoreResource = new UIResource(designerProject, FILE_DIR, MODEL);

        final SessionChoice wizard = designerPerspective.openSessionCreationWizardFromSemanticResource(ecoreEcoreResource);

        UILocalSession localSession = wizard.fromAlreadySelectedSemanticResource().withDefaultSessionName().finish().selectViewpoints(viewpointsSelection);

        final SWTBotTreeItem semanticResourceNode = localSession.getSemanticResourceNode(ecoreEcoreResource);
        final SWTBotTreeItem ecoreTreeItem = semanticResourceNode.getNode(MODEL_PACKAGE);
        final UIDiagramRepresentation representation = localSession.newDiagramRepresentation(REPRESENTATION_INSTANCE_NAME_DIAGRAM, REPRESENTATION_NAME_DIAGRAM).on(ecoreTreeItem).withDefaultName()
                .ok();

        // Initialization of a bot on the diagram
        final SWTBotSiriusDiagramEditor editor = representation.save().getEditor();

        // Adding various items of the palette on the diagram
        // Add a class
        editor.activateTool("Class");
        editor.click(50, 100);
        bot.sleep(DEFAULT_SLEEP_TIMER);

        // Add a class
        editor.activateTool("Class");
        editor.click(250, 100);
        bot.sleep(DEFAULT_SLEEP_TIMER);

        // Add an association
        editor.activateTool("Generalization");
        editor.click(NEW_CLASS_1);
        editor.click(NEW_CLASS_2);

        // Close the session and save the diagram
        localSession.close(true);

        bot.waitUntil(new CheckNoOpenedSessionInModelContentView(bot, SESSION_FILE));

        // Re open session
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME_DIAGRAM)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM, UIDiagramRepresentation.class).open();
        // Initialization of a bot on the diagram
        final SWTBotSiriusDiagramEditor editor2 = representation.getEditor();

        final SWTBotGefEditPart editPart = editor2.getEditPart(NEW_CLASS_1);
        assertNotNull(editPart);
        final EObject element = ((View) ((GraphicalEditPart) editPart.part()).getModel()).getElement();
        assertNotNull(element);
        assertNotNull(((DSemanticDecorator) element).getTarget());
        assertTrue("New generalization class is not created", hasGeneralizationChild(((DSemanticDecorator) element).getTarget()));

        localSession.closeNoDirty();

    }

    /**
     * @param element
     */
    private boolean hasGeneralizationChild(final EObject element) {
        for (final EObject content : element.eContents()) {
            if (content.eClass().getName().contains("Generalization")) {
                return true;
            }
        }
        return false;
    }

}
