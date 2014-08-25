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

import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OpenedSessionCondition;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.AbstractDecoratorMatcher;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.DeletedDecoratorMatcher;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * 
 * @author amartin
 */
public class DeleteSemanticElementToCheckDecorator extends AbstractScenarioTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/deleteSemanticElement/";

    private static final String FILE_DIR = "/";

    private static AbstractDecoratorMatcher matcher = new DeletedDecoratorMatcher();

    private static final String VIEWPOINT_NAME = "Design";

    private static final String SESSION_FILE = "DeleteSemanticElement.aird";

    private static final String MODEL = "DeleteSemanticElement.ecore";

    private static final String REPRESENTATION_NAME_DIAGRAM = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootPackage package entities";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    public void testDeleteSemanticElement() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);

        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        bot.waitUntil(new OpenedSessionCondition(1));
        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_NAME_DIAGRAM)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM, UIDiagramRepresentation.class).open();

        assertTrue("The element which are not deleted are decorated !", checkUntouchedElementAreNotDecorated(diagram));

        assertNotNull("[Delete Semantic Element TestCase]:Error the diagram couldn't be opened!", diagram);

        diagram.close();
    }

    private boolean CheckIfNodeIsDecoratedWithDeleted(UIDiagramRepresentation diagram, String nodeName) {
        SWTBotGefEditPart checkedNodeEditPart = getRightEPForDecoration(diagram.getEditor().getEditPart(nodeName));
        assertNotNull(checkedNodeEditPart);
        assertFalse("no decorator found", matcher.matches(checkedNodeEditPart));
        return true;
    }

    private boolean checkUntouchedElementAreNotDecorated(UIDiagramRepresentation diagram) {
        CheckIfNodeIsDecoratedWithDeleted(diagram, "NOT_TOERASE_CONTAINER");
        CheckIfNodeIsDecoratedWithDeleted(diagram, "NOT_TOERASE_CONTAINED");
        CheckIfNodeIsDecoratedWithDeleted(diagram, "NOT_TOERASE");
        return true;
    }

    private SWTBotGefEditPart getRightEPForDecoration(SWTBotGefEditPart editPart) {
        SWTBotGefEditPart part = editPart;
        if (part.part() instanceof IDiagramNameEditPart)
            part = part.parent();
        return part;
    }

}
