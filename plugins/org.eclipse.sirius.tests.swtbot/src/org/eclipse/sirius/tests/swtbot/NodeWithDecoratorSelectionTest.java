/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.test.AbstractScenarioTestCase;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;

/**
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class NodeWithDecoratorSelectionTest extends AbstractScenarioTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/decorator/validation/";

    private static final String SEMANTIC_RESOURCE_FILENAME = "My.ecore";

    private static final String SESSION_RESOURCE_FILENAME = "representations.aird";

    private static final String MODELER_RESOURCE_FILENAME = "My.odesign";

    private static final String REPRESENTATION_INSTANCE_NAME = "new diag";

    private static final String REPRESENTATION_NAME = "diag";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_FILENAME, SESSION_RESOURCE_FILENAME, MODELER_RESOURCE_FILENAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILENAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test that element under decorator is selected even if it is not the
     * parent of the decorator.
     */
    public void testDDEUnderDecoratorSelection() {
        // Launch the validation to make decorators visible
        editor.clickContextMenu("Validate diagram");
        SWTBotUtils.waitAllUiEvents();

        // Click on the error decorator of container "root"
        // editor.click(218, 28);
        // Check that the border node "EClass2Border" is selected (and not the
        // parent of the decorator under the cursor, ie container "root")
        // bot.waitUntil(new CheckSelectedCondition(editor, "EClass2Border",
        // AbstractDiagramBorderNodeEditPart.class));

        // Click on the error decorator of container "root"
        editor.click(209, 28);
        // Check that the container "root" is selected (the parent of the
        // decorator under the cursor is also the DDiagramElement under the
        // cursor)
        bot.waitUntil(new CheckSelectedCondition(editor, "root", AbstractDiagramContainerEditPart.class));

        // Click on the error decorator of node "EClass2"
        editor.click(122, 93);
        // Check that the node "EClass1" is selected (and not the parent
        // of the decorator under the cursor, ie node "EClass2")
        bot.waitUntil(new CheckSelectedCondition(editor, "EClass1", AbstractDiagramNodeEditPart.class));

        // Click on the error decorator of border node "att2-1"
        editor.click(150, 114);
        // Check that the node "EClass1" is selected (and not the parent
        // of the decorator under the cursor, ie node "EClass2")
        bot.waitUntil(new CheckSelectedCondition(editor, "EClass1", AbstractDiagramNodeEditPart.class));

        // Click on the navigation decorator of container "root"
        // editor.click(218, 188);
        // Check that the border node "EClass1Border" is selected (and not the
        // parent of the decorator under the cursor, ie container "root")
        // bot.waitUntil(new CheckSelectedCondition(editor, "EClass1Border",
        // AbstractDiagramBorderNodeEditPart.class));

        // Click on the navigation decorator of container "root"
        editor.click(209, 181);
        // Check that the border node "a3-1" is selected (and not the parent
        // of the decorator under the cursor, ie container "root")
        bot.waitUntil(new CheckSelectedCondition(editor, "a3-1", AbstractDiagramBorderNodeEditPart.class));

        // Click on the warning decorator of green list "root2"
        editor.click(335, 172);
        // Check that the border node "EClass1Border" is selected (and not the
        // parent of the decorator under the cursor, ie list "root2"). This case
        // is a limitation (the label "EClass1Border" under the decorator is not
        // selected but its container instead, this is because figure of this
        // part is not a "MouseEventTarget").
        bot.waitUntil(new CheckSelectedCondition(editor, "EClass1Border", AbstractDiagramBorderNodeEditPart.class));

        // Click on the navigation decorator of green list "root2"
        editor.click(334, 318);
        // Check that the purple list "root3" is selected (and not the
        // parent of the decorator under the cursor, ie green list "root2").
        // This case is a limitation (the list item label "EClass2" under the
        // decorator is not selected but its container instead, this is because
        // figure of this part is not a "MouseEventTarget").
        bot.waitUntil(new CheckSelectedCondition(editor, "root3", AbstractDiagramListEditPart.class));
    }

}
