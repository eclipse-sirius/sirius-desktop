/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Test class for port selection.
 * 
 * @author dlecan
 */
public class PortSelectionTest extends AbstractSiriusSwtBotGefTestCase {

    private interface CalculatePointCallBack {
        Point caculatePoint(Rectangle bounds);
    }

    // Real margin is 19, but we add margin to margin
    private static final int MARGIN_SIZE = 19;

    private static final String REPRESENTATION_INSTANCE_NAME = "new Component Diagram";

    private static final String REPRESENTATION_NAME = "Component Diagram";

    private static final String VIEWPOINT_NAME = "UML Analysis";

    private static final String MODEL = "manyEdges.uml";

    private static final String SESSION_FILE = "manyEdges.aird";

    private static final String VSM_FILE = "manyEdges.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portSelectionWithManyEdges/";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testClickInCenterSelectPortWithManyEdges() throws Exception {
        clickWithCallBackToPortToSelectPortWithManyEdges(new CalculatePointCallBack() {

            @Override
            public Point caculatePoint(final Rectangle bounds) {
                return bounds.getCenter();
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testClickTopRightPortToSelectPortWithManyEdges() throws Exception {

        clickWithCallBackToPortToSelectPortWithManyEdges(new CalculatePointCallBack() {

            @Override
            public Point caculatePoint(final Rectangle bounds) {
                final Point pointToClick = new Point();

                final Point topRight = bounds.getTopRight();
                // Margin is a 20x20px rectangle around port.
                pointToClick.x = topRight.x + (MARGIN_SIZE - bounds.width) / 2;
                pointToClick.y = topRight.y - (MARGIN_SIZE - bounds.height) / 2;
                return pointToClick;
            }
        });
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testClickBottomLeftPortToSelectPortWithManyEdges() throws Exception {
        clickWithCallBackToPortToSelectPortWithManyEdges(new CalculatePointCallBack() {

            @Override
            public Point caculatePoint(final Rectangle bounds) {
                final Point pointToClick = new Point();

                final Point bottomLeft = bounds.getBottomLeft();
                // Margin is a 20x20px rectangle around port.
                pointToClick.x = bottomLeft.x + (MARGIN_SIZE - bounds.width) / 2;
                pointToClick.y = bottomLeft.y + (MARGIN_SIZE - bounds.height) / 2;
                return pointToClick;
            }
        });
    }

    /**
     * Test the selection of a port with a marquee (the selection rectangle)
     * from left to right. It's not possible with the default
     * RubberbandDragTracker of GMF).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testPortSelectionWithMarkee() throws Exception {
        // Select diagram.
        editor.select(editor.mainEditPart());
        assertThat("Nothing should be selected.", editor.mainEditPart().part().getSelected(), greaterThanOrEqualTo(EditPart.SELECTED));

        final IGraphicalEditPart portEditPart = getPortEditPart();

        final Rectangle bounds = portEditPart.getFigure().getBounds();
        final Rectangle selection = new Rectangle(140, 110, 50, 80);
        // Check that there is nothing under the start point (to allow a
        // selection with rectangle)
        editor.click(selection.x, selection.y);
        final ISelection currentSelection = editor.getSelection();
        assertTrue("No element should be at these coordinates (only diagram).", checkSelectionIsEmpty(currentSelection));
        // Check that there is nothing under the end point (to allow a selection
        // with rectangle)
        editor.click(selection.getBottomRight().x, selection.getBottomRight().y);
        assertTrue("No element should be at these coordinates (only diagram).", checkSelectionIsEmpty(currentSelection));
        // Check that the port is contains by the selection rectangle
        assertTrue("The selection rectangle should contain the port.", selection.contains(bounds));
        // Draw the rectangle for selection (from left to right)
        editor.drag(selection.x, selection.y, selection.getBottomRight().x, selection.getBottomRight().y);

        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return portEditPart.getSelected() >= EditPart.SELECTED;
            }

            @Override
            public String getFailureMessage() {
                return "Port edit part should be selected after drawing a selection rectangle from left to right around it";
            }
        });

        assertThat("Port edit part should be selected after drawing a selection rectangle from left to right around it", portEditPart.getSelected(), greaterThanOrEqualTo(EditPart.SELECTED));
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

    private void clickWithCallBackToPortToSelectPortWithManyEdges(final CalculatePointCallBack callBack) throws Exception {
        // Unselect anything.
        editor.select(editor.mainEditPart());

        final IGraphicalEditPart portEditPart = getPortEditPart();

        final Rectangle bounds = portEditPart.getFigure().getBounds();

        final Point pointToClick = callBack.caculatePoint(bounds);

        editor.click(pointToClick.x, pointToClick.y);

        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return portEditPart.getSelected() >= EditPart.SELECTED;
            }

            @Override
            public String getFailureMessage() {
                return "Port edit part should be selected after clic on it";
            }
        });

        assertThat("Port edit part should be selected after clic on it", portEditPart.getSelected(), greaterThanOrEqualTo(EditPart.SELECTED));
    }

    private IGraphicalEditPart getPortEditPart() {
        // Need to use parent because "PortToClick" will gives you the figure
        // which contains the name not the port itself.
        final SWTBotGefEditPart swtBotEditPart = editor.getEditPart("PortToClick").parent();

        final IGraphicalEditPart portEditPart = (IGraphicalEditPart) swtBotEditPart.part();

        assertThat("Port edit part should not be selected", portEditPart.getSelected(), is(EditPart.SELECTED_NONE));

        return portEditPart;
    }
}
