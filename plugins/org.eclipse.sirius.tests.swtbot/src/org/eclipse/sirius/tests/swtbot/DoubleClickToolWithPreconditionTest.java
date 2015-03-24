/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test class that check if precondition is working for double click tool.
 * 
 * See OD-883.
 * 
 * @author @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 */
public class DoubleClickToolWithPreconditionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PACKAGE_NAME = "P1";

    private static final String AIRD = "461991.aird";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "bug461991";

    private static final String REPRESENTATION_NAME = "new bug461991";

    private static final String PATH = "/data/unit/doubleClick/bug461991/";

    private static final String ODESIGN = "461991.odesign";

    private static final String SEMANTIC = "461991.ecore";

    private static final String FILE_DIR = "/";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC, AIRD, ODESIGN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, AIRD);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, AIRD);

    }

    /**
     * Tests that the precondition of the double click tool works.
     */
    public void testDoubleClickWithPrecondition() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
        checkDoubleClickRemoveAction();
    }

    private void checkDoubleClickRemoveAction() {
        // Double click on P1
        editor.getEditPart(PACKAGE_NAME, AbstractBorderedShapeEditPart.class).select();
        editor.doubleClick(PACKAGE_NAME);
        SWTBotUtils.waitAllUiEvents();
        // Recover semantic model
        SWTBotGefEditPart diagramBot = editor.rootEditPart().children().iterator().next();
        DSemanticDiagram semanticDiagram = (DSemanticDiagram) ((Diagram) diagramBot.part().getModel()).getElement();
        List<DDiagramElement> diagramElements = semanticDiagram.getOwnedDiagramElements();
        // Check that P1 Package exists
        boolean isPresent = false;
        for (DDiagramElement element : diagramElements) {
            if (PACKAGE_NAME.equals(element.getName())) {
                isPresent = true;
            }
        }
        assertTrue("The P1 package has been deleted while the false precondition does not allow this.", isPresent);
    }

}
