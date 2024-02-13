/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

/**
 * Tests
 * 
 * @author nlepine
 */
public class ToolCreationPositionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String NEW_PACKAGE4 = "newPackage4";

    private static final String NEW_PACKAGE3 = "newPackage3";

    private static final String NEW_PACKAGE2 = "newPackage2";

    private static final String NEW_PACKAGE1 = "newPackage1";

    private static final String C4 = "C4";

    private static final String C3 = "C3";

    private static final String C2 = "C2";

    private static final String C1 = "C1";

    private static final String CLASS_CREATION_TOOL = "classCreation";

    private static final String CONTAINER_CREATION_TOOL = "Package";

    private static final Point DIAGRAM_CREATION_POINT = new Point(10, 10);

    private static final Point CONTAINER_CREATION_POINT = new Point(265, 130);

    private static final Point CONTAINER2_CREATION_POINT = new Point(517, 200);

    private static final String REPRESENTATION_INSTANCE_NAME = "new 2444";

    private static final String REPRESENTATION_NAME = "2444";

    private static final String MODEL = "2444.ecore";

    private static final String VSM = "2444.odesign";

    private static final String SESSION_FILE = "2444.aird";

    private static final String DATA_UNIT_DIR = "data/unit/nodeCreation/2444/";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        new UIResource(designerProject, MODEL);
    }

    private void openDiagram() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.setSnapToGrid(false);
    }

    /**
     * Test the nodes creation
     */
    public void testNodeCreationOnDiagram() {
        openDiagram();
        editor.activateTool(CLASS_CREATION_TOOL);
        editor.click(DIAGRAM_CREATION_POINT);
        checkNodeEditPartPosition(DIAGRAM_CREATION_POINT);

        // undo / redo
        undo(CLASS_CREATION_TOOL);
        checkNoNodeEditpart();
        redo(CLASS_CREATION_TOOL);
        checkNodeEditPartPosition(DIAGRAM_CREATION_POINT);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkNodeEditPartPosition(DIAGRAM_CREATION_POINT);
    }

    /**
     * Test the nodes creation
     */
    public void testNodeCreationOnContainer() {
        openDiagram();
        editor.activateTool(CLASS_CREATION_TOOL);
        editor.click(CONTAINER_CREATION_POINT);
        checkNode3EditPartPosition(CONTAINER_CREATION_POINT);

        // undo / redo
        undo(CLASS_CREATION_TOOL);
        checkNoContainer2Editpart();
        redo(CLASS_CREATION_TOOL);
        checkNode3EditPartPosition(CONTAINER_CREATION_POINT);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkNode3EditPartPosition(CONTAINER_CREATION_POINT);
    }

    /**
     * Test the nodes creation
     */
    public void testNodeCreationOnContainer2() {
        openDiagram();
        editor.activateTool(CLASS_CREATION_TOOL);
        editor.click(CONTAINER2_CREATION_POINT);
        checkNode3EditPartPosition(CONTAINER2_CREATION_POINT);

        // undo / redo
        undo(CLASS_CREATION_TOOL);
        checkNoContainer2Editpart();
        redo(CLASS_CREATION_TOOL);
        checkNode3EditPartPosition(CONTAINER2_CREATION_POINT);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkNode3EditPartPosition(CONTAINER2_CREATION_POINT);
    }

    /**
     * Test the containers creation
     */
    public void testContainerCreationOnDiagram() {
        openDiagram();
        editor.activateTool(CONTAINER_CREATION_TOOL);
        editor.click(DIAGRAM_CREATION_POINT);
        checkContainerEditPartPosition(DIAGRAM_CREATION_POINT);

        // undo / redo
        undo(CONTAINER_CREATION_TOOL);
        checkNoContainerEditpart();
        redo(CONTAINER_CREATION_TOOL);
        checkContainerEditPartPosition(DIAGRAM_CREATION_POINT);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkContainerEditPartPosition(DIAGRAM_CREATION_POINT);
    }

    /**
     * Test the containers creation
     */
    public void testContainerCreationOnContainer() {
        openDiagram();
        editor.activateTool(CONTAINER_CREATION_TOOL);
        editor.click(CONTAINER_CREATION_POINT);
        checkContainer2EditPartPosition(CONTAINER_CREATION_POINT);

        // undo / redo
        undo(CONTAINER_CREATION_TOOL);
        checkNoContainer2Editpart();
        redo(CONTAINER_CREATION_TOOL);
        checkContainer2EditPartPosition(CONTAINER_CREATION_POINT);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkContainer2EditPartPosition(CONTAINER_CREATION_POINT);
    }

    /**
     * Test the containers creation
     */
    public void testContainerCreationOnContainer2() {
        openDiagram();
        editor.activateTool(CONTAINER_CREATION_TOOL);
        editor.click(CONTAINER2_CREATION_POINT);
        checkContainer2EditPartPosition(CONTAINER2_CREATION_POINT);

        // undo / redo
        undo(CONTAINER_CREATION_TOOL);
        checkNoContainer2Editpart();
        redo(CONTAINER_CREATION_TOOL);
        checkContainer2EditPartPosition(CONTAINER2_CREATION_POINT);

        // close an reopen the diagram
        saveCloseReopenDiagram();
        checkContainer2EditPartPosition(CONTAINER2_CREATION_POINT);
    }

    private void saveCloseReopenDiagram() {
        editor.saveAndClose();
        openDiagram();
    }

    private void checkNoNodeEditpart() {
        checkNoEditPart(C1, DNodeEditPart.class);
        checkNoEditPart(C2, DNodeEditPart.class);
        checkNoEditPart(C3, DNodeEditPart.class);
        checkNoEditPart(C4, DNodeEditPart.class);
    }

    private void checkNoContainerEditpart() {
        checkNoEditPart(NEW_PACKAGE1, DNodeContainerEditPart.class);
        checkNoEditPart(NEW_PACKAGE2, DNodeContainerEditPart.class);
        checkNoEditPart(NEW_PACKAGE3, DNodeContainerEditPart.class);
        checkNoEditPart(NEW_PACKAGE4, DNodeContainerEditPart.class);
    }

    private void checkNoContainer2Editpart() {
        checkNoEditPart(NEW_PACKAGE1, DNodeContainer2EditPart.class);
        checkNoEditPart(NEW_PACKAGE2, DNodeContainer2EditPart.class);
        checkNoEditPart(NEW_PACKAGE3, DNodeContainer2EditPart.class);
        checkNoEditPart(NEW_PACKAGE4, DNodeContainer2EditPart.class);
    }

    private void checkNoEditPart(String name, Class<? extends EditPart> class1) {
        boolean found = true;
        try {
            editor.getEditPart(name, class1);
            found = true;
        } catch (WidgetNotFoundException e) {
            found = false;
        } finally {
            assertFalse("Edi part should not exist", found);
        }
    }

    private void checkNodeEditPartPosition(Point reference) {
        Point expectedPosition = reference;
        expectedPosition = checkNodeEditPartPosition(C1, expectedPosition);
        expectedPosition = checkNodeEditPartPosition(C2, expectedPosition);
        expectedPosition = checkNodeEditPartPosition(C3, expectedPosition);
        checkNodeEditPartPosition(C4, expectedPosition);
    }

    private void checkNode3EditPartPosition(Point reference) {
        Point expectedPosition = reference;
        expectedPosition = checkNode3EditPartPosition(C1, expectedPosition);
        expectedPosition = checkNode3EditPartPosition(C2, expectedPosition);
        expectedPosition = checkNode3EditPartPosition(C3, expectedPosition);
        checkNode3EditPartPosition(C4, expectedPosition);
    }

    private void checkContainerEditPartPosition(Point reference) {
        Point expectedPosition = reference;
        expectedPosition = checkContainerEditPartPosition(NEW_PACKAGE1, expectedPosition);
        expectedPosition = checkContainerEditPartPosition(NEW_PACKAGE2, expectedPosition);
        expectedPosition = checkContainerEditPartPosition(NEW_PACKAGE3, expectedPosition);
        checkContainerEditPartPosition(NEW_PACKAGE4, expectedPosition);
    }

    private void checkContainer2EditPartPosition(Point reference) {
        Point expectedPosition = reference;
        expectedPosition = checkContainer2EditPartPosition(NEW_PACKAGE1, expectedPosition);
        expectedPosition = checkContainer2EditPartPosition(NEW_PACKAGE2, expectedPosition);
        expectedPosition = checkContainer2EditPartPosition(NEW_PACKAGE3, expectedPosition);
        checkContainer2EditPartPosition(NEW_PACKAGE4, expectedPosition);
    }

    private Point checkNodeEditPartPosition(String label, Point expectedPosition) {
        SWTBotGefEditPart editPart = editor.getEditPart(label, DNodeEditPart.class);
        assertNotNull(editPart);
        return checkEditPartPosition(editPart, expectedPosition);
    }

    private Point checkNode3EditPartPosition(String label, Point expectedPosition) {
        SWTBotGefEditPart editPart = editor.getEditPart(label, DNode3EditPart.class);
        assertNotNull(editPart);
        return checkEditPartPosition(editPart, expectedPosition);
    }

    private Point checkContainerEditPartPosition(String label, Point expectedPosition) {
        SWTBotGefEditPart editPart = editor.getEditPart(label, DNodeContainerEditPart.class);
        assertNotNull(editPart);
        return checkEditPartPosition(editPart, expectedPosition);
    }

    private Point checkContainer2EditPartPosition(String label, Point expectedPosition) {
        SWTBotGefEditPart editPart = editor.getEditPart(label, DNodeContainer2EditPart.class);
        assertNotNull(editPart);
        return checkEditPartPosition(editPart, expectedPosition);
    }

    private Point checkEditPartPosition(SWTBotGefEditPart editPart, Point expectedPosition) {
        GraphicalEditPart ep = (GraphicalEditPart) editPart.part();
        ep.getFigure().translateToAbsolute(((GraphicalEditPart) editPart.part()).getFigure().getBounds());
        assertEquals(expectedPosition.x, ep.getFigure().getBounds().x, 1);
        assertEquals(expectedPosition.y, ep.getFigure().getBounds().y, 1);

        int nextX = ep.getFigure().getBounds().getRight().x + SiriusLayoutDataManager.PADDING;
        int nextY = ep.getFigure().getBounds().getRight().y;
        return new Point(nextX, nextY);
    }

}
