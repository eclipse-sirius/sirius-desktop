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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test if a bordered node, not visible because it's parent is not completely
 * visible because the a small parent container, can be selected by the user
 * with a marquee (a rectangle selection). See VP-3473.
 * 
 * @author MVenisse
 */
public class PortNotVisibleSelectionTest extends AbstractSiriusSwtBotGefTestCase {

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private static final String DATA_UNIT_DIR = "data/unit/hiddenElements/VP-3473/";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String FILE_DIR = "/";

    private static final String VSM_FILE = "My.odesign";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Diagram";

    private static final String CONTAINER_TO_RESIZE = "p1";

    private static final String BORDERED_NODE_TO_SELECT = "a2";

    private static final String LABEL_TO_SELECT = "E2";

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * This test check that the bordered node is not selectable with a marquee
     * after a resize of its grand-parent that "hide" it (and at the same time
     * check that the bordered label of its parent is not selected).
     * 
     * @throws Exception
     *             Test error
     */
    public void testPortSelectionAfterContainerResize() throws Exception {
        editor = openDiagram(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);

        // Click on container p1
        SWTBotGefEditPart p1EditPart = editor.getEditPart(CONTAINER_TO_RESIZE, AbstractBorderedShapeEditPart.class);
        editor.click(p1EditPart);
        bot.waitUntil(new CheckSelectedCondition(editor, CONTAINER_TO_RESIZE, AbstractBorderedShapeEditPart.class));

        // Get the coordinates of bordered node a2
        final SWTBotGefEditPart borderedNodeEditPart = editor.getEditPart(BORDERED_NODE_TO_SELECT, AbstractDiagramBorderNodeEditPart.class);
        final SWTBotGefEditPart labelEditPart = editor.getEditPart(LABEL_TO_SELECT, AbstractDiagramNameEditPart.class);
        Rectangle borderedNodeBounds = editor.getBounds(borderedNodeEditPart);
        Rectangle selection = borderedNodeBounds.getExpanded(20, 20);
        // Add the height of p1 to be sure that the selection traverse p1
        selection.height = selection.height + 323;

        // Resize p1
        Rectangle p1Bounds = editor.getBounds(p1EditPart);
        Point p1BottomRightCornerBeforeResize = p1Bounds.getBottomRight();
        Point endPoint = new Point(p1BottomRightCornerBeforeResize.x - 200, p1BottomRightCornerBeforeResize.y);

        CheckEditPartResized resizedCondition = new CheckEditPartResized(p1EditPart);
        editor.drag(p1BottomRightCornerBeforeResize, endPoint);
        bot.waitUntil(resizedCondition);

        // Try to select a2 with drawing a selection rectangle (hide because p1
        // is resized)

        // Check that there is nothing under the start point (to allow a
        // selection with rectangle)
        editor.click(selection.x, selection.y);
        SWTBotUtils.waitAllUiEvents();
        assertTrue("It should not be any element in these coordinates (only diagram).", checkSelectionIsEmpty(editor.getSelection()));
        // Check that there is nothing under the end point (to allow a selection
        // with rectangle)
        editor.click(selection.getBottomRight().x, selection.getBottomRight().y);
        SWTBotUtils.waitAllUiEvents();
        assertTrue("It should not be any element in these coordinates (only diagram).", checkSelectionIsEmpty(editor.getSelection()));
        // Check that the port is contains by the selection rectangle
        assertTrue("The selection rectangle should contain the port.", selection.contains(borderedNodeBounds));
        // Select element with marquee (should select nothing)
        editor.drag(selection.x, selection.y, selection.getBottomRight().x, selection.getBottomRight().y);
        SWTBotUtils.waitAllUiEvents();
        // Check that a2 is not selected
        assertEquals("Port edit part should not be selected after drawing a selection rectangle around it", EditPart.SELECTED_NONE, borderedNodeEditPart.part().getSelected());
        // Check that label E2 is not selected
        assertEquals("Bordered label edit part should not be selected after drawing a selection rectangle around it", EditPart.SELECTED_NONE, labelEditPart.part().getSelected());
    }

    /**
     * Check that the selection is empty or contains only the DDiagramEditPart.
     * 
     * @param selection
     *            The selection to check.
     * @return true if the selection is empty or contains only the
     *         DiagramEditPart, false otherwise.
     */
    private boolean checkSelectionIsEmpty(final ISelection selection) {
        boolean result = false;
        if (selection.isEmpty()) {
            result = true;
        } else if (selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            if (structuredSelection.size() == 1 && structuredSelection.getFirstElement() instanceof DiagramEditPart) {
                result = true;
            }
        }
        return result;
    }

}
