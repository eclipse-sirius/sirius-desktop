/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * This test checks that there are no errors during the D&D of an E element and when content this E element is modified
 * as a result of the D&D.
 * 
 * @author Séraphin Costa
 *
 */
public class DragDropWithUpdateNodeTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "/data/unit/dragAndDrop/bug318/";

    private static final String SEMANTIC_MODEL = "My.ecore";

    private static final String REPRESENTATION_MODEL = "representations.aird";

    private static final String MODELER = "My.odesign";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "DBugDnDRefresh";

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_MODEL, REPRESENTATION_MODEL, MODELER);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATION_MODEL);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
    }

    private SWTBotGefEditPart getCompartment(SWTBotGefEditPart partBot) {
        return partBot.children() //
                .stream() //
                .filter((childPartBot) -> childPartBot.part() instanceof AbstractDNodeContainerCompartmentEditPart) //
                .findAny() //
                .orElseThrow();
    }

    /**
     * Test of a D&D that involves modifying a border node (deletion and creation) of the drag&dropped element.
     */
    public void testDragAndDropWithUpdateNode() {
        // check the diagram before D&D: diagram with empty container pkg2 and with node cls2
        List<? extends GraphicalEditPart> diagramContentBefore = editor.getDiagramEditPart().getChildren();
        assertEquals("Expect only cls2 and pkg2 on the diagram before D&D", 2, diagramContentBefore.size());

        SWTBotGefEditPart cls2Part = editor.getEditPart("cls2", DNodeEditPart.class);
        SWTBotGefEditPart pkg2Part = editor.getEditPart("pkg2", DNodeContainerEditPart.class);

        List<SWTBotGefEditPart> pkg2ContentBefore = getCompartment(pkg2Part).children();
        assertTrue("Expect pkg2 empty before D&D", pkg2ContentBefore.isEmpty());

        Point sourceDragLocation = editor.getAbsoluteBounds(cls2Part).getCenter();
        Point targetDropLocation = editor.getAbsoluteBounds(pkg2Part).getCenter();

        // start log listener
        startToListenErrorLog(false, true);

        // perform drag cls2 to pkg2
        editor.drag(sourceDragLocation, targetDropLocation);

        // check the diagram after D&D: diagram with container pkg2 and with node cls2 inside
        List<? extends GraphicalEditPart> diagramContentAfter = editor.getDiagramEditPart().getChildren();
        assertEquals("Expect only pkg2 on the diagram after D&D", 1, diagramContentAfter.size());
        List<SWTBotGefEditPart> pkg2ContentAfter = getCompartment(pkg2Part).children();
        assertEquals("Expect cls2 in pkg2 after D&D", 1, pkg2ContentAfter.size());

        // The error log is checked in the tearDown
    }
}
