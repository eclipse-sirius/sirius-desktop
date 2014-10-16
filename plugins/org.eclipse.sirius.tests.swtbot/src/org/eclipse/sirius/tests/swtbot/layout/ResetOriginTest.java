/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test the Reset Origin action.
 * 
 * @author Florian Barbin
 * 
 */
public class ResetOriginTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String MODEL = "resetOrigin.ecore";

    private static final String SESSION_FILE = "resetOrigin.aird";

    private static final String VSM_FILE = "resetOrigin.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/resetOrigin/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "resetOrigin";

    private static final String REPRESENTATION_FOR_DIAGRAM_NAME = "resetDiagramOrigin";

    private static final String REPRESENTATION_FOR_CONTAINER_NAME = "resetContainerOrigin";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * This test opens the representation, launches the action on the diagram
     * and checks that one of the container has been moved at the right
     * location. All primary shapes are moved equally so we don't need to check
     * each one of them.
     */
    public void testResetDiagramOriginAction() {
        openDiagram(REPRESENTATION_FOR_DIAGRAM_NAME);
        SWTBotGefEditPart mostLeftEditPart = editor.getEditPart("eClassdfgdf dfgdfgdf gdfg dfgdfg d f", DNodeNameEditPart.class);
        SWTBotGefEditPart mostTopEditPart = editor.getEditPart("eClass", DNodeNameEditPart.class);
        SWTBotGefEditPart package4EditPart = editor.getEditPart("Package4", IDiagramContainerEditPart.class);
        IGraphicalEditPart editPart = (IGraphicalEditPart) package4EditPart.part();
        Node container = (Node) editPart.getModel();
        Bounds bounds = (Bounds) container.getLayoutConstraint();
        Point locationBefore = new Point(bounds.getX(), bounds.getY());

        // we compute the reset origin delta from the most left and the top
        // figures. (the -20 corresponds to the 20px margin)
        int xDelta = ((GraphicalEditPart) mostLeftEditPart.part()).getFigure().getBounds().x() - 20;
        int yDelta = ((GraphicalEditPart) mostTopEditPart.part()).getFigure().getBounds().y() - 20;
        Point expectedDelta = new Point(-xDelta, -yDelta);

        resetOrigin();
        bounds = (Bounds) container.getLayoutConstraint();

        hasTheExpectedBounds(bounds, locationBefore.getTranslated(expectedDelta.x(), expectedDelta.y()));

    }

    /**
     * This test opens the representation, launches the action on a container
     * and checks that one of the container has been moved at the right
     * location. All primary shapes are moved equally so we don't need to check
     * each one of them.
     */
    public void testResetContainerOriginAction() {
        openDiagram(REPRESENTATION_FOR_CONTAINER_NAME);
        SWTBotGefEditPart mostLeftEditPart = editor.getEditPart("PackageA", IDiagramContainerEditPart.class);
        SWTBotGefEditPart mostTopEditPart = editor.getEditPart("PackageB", IDiagramContainerEditPart.class);
        SWTBotGefEditPart package2EditPart = editor.getEditPart("Package2", IDiagramContainerEditPart.class);
        editor.select(package2EditPart);
        Node checkedContainer = (Node) mostLeftEditPart.part().getModel();
        Bounds checkedBounds = (Bounds) checkedContainer.getLayoutConstraint();
        Point locationBefore = new Point(checkedBounds.getX(), checkedBounds.getY());

        // we compute the reset origin delta from the most left and the top
        // figures. (the -20 corresponds to the 20px margin)
        int xDelta = ((GraphicalEditPart) mostLeftEditPart.part()).getFigure().getBounds().x() - 20;
        int yDelta = ((GraphicalEditPart) mostTopEditPart.part()).getFigure().getBounds().y() - 20;
        Point expectedDelta = new Point(-xDelta, -yDelta);

        resetOrigin();
        Bounds newBounds = (Bounds) checkedContainer.getLayoutConstraint();

        hasTheExpectedBounds(newBounds, locationBefore.getTranslated(expectedDelta.x(), expectedDelta.y()));
    }

    private void hasTheExpectedBounds(Bounds bounds, Point expectedLocation) {

        Rectangle currentRectangle = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        assertEquals("The Package4 container do not have the expected bounds after Reset Origin", new Rectangle(expectedLocation.x(), expectedLocation.y(), -1, -1), currentRectangle);

    }

    private void resetOrigin() {
        editor.clickContextMenu("Reset Origin");
        SWTBotUtils.waitAllUiEvents();
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        editor = null;
        super.tearDown();
    }

    private void openDiagram(String representationName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

    }
}
