/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.ViewNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.collect.Iterables;

/**
 * Test class which checks if label is well positioned when it is node label position.
 * 
 * @author nlepine
 */
public class NodeLabelPositionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new Diag-2321";

    private static final String REPRESENTATION_NAME = "Diag-2321";

    private static final String MODEL = "testcase.ecore";

    private static final String SESSION_FILE = "testcase.aird";

    private static final String VSM_FILE = "2321.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/labelPosition/";

    private static final String FILE_DIR = "/";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.setSnapToGrid(false);
    }

    /**
     * Tests that the left alignment is taken in consideration with wrapped label.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testNodeWrappedLabelPositionWithLeftAlignement() throws Exception {
        SWTBotGefEditPart editPart1 = editor.getEditPart("SubPackage1", AbstractDiagramNodeEditPart.class);
        assertNotNull(editPart1);
        checkLabelAlignment(editPart1, PositionConstants.LEFT);
    }

    /**
     * Tests that the right alignment is taken in consideration with wrapped label.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testNodeWrappedLabelPositionWithRightAlignement() throws Exception {
        SWTBotGefEditPart editPart1 = editor.getEditPart("SubPackage2", AbstractDiagramNodeEditPart.class);
        assertNotNull(editPart1);
        checkLabelAlignment(editPart1, PositionConstants.RIGHT);
    }

    /**
     * Tests that the center alignment is taken in consideration with wrapped label.
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testNodeWrappedLabelPositionWithCenterAlignement() throws Exception {
        SWTBotGefEditPart editPart = editor.getEditPart("SubPackage3", AbstractDiagramNodeEditPart.class);
        assertNotNull(editPart);
        checkLabelAlignment(editPart, PositionConstants.CENTER);
    }

    /**
     * Verifies that the label is aligned correctly regarding the value set in VSM.
     * 
     * @param editPart
     *            the edit part to check its alignment.
     * @param alignment
     *            alignment that should be active.
     */
    private void checkLabelAlignment(SWTBotGefEditPart editPart, int alignment) {
        DNodeEditPart part = (DNodeEditPart) editPart.part();
        SiriusWrapLabel nodeLabel = part.getPrimaryShape().getNodeLabel();
        int textWrapAlignment = nodeLabel.getTextWrapAlignment();

        checkAlignment("wrong text wrap alignment.", alignment, textWrapAlignment);
        int textAlignment = nodeLabel.getTextAlignment();
        checkAlignment("wrong text alignment.", alignment, textAlignment);
        int labelAlignment = nodeLabel.getLabelAlignment2();
        checkAlignment("wrong label alignment.", alignment, labelAlignment);
    }

    /**
     * Verifies the alignment is the expected one.
     * 
     * @param errorMessage
     *            error message if not equals
     * @param expectedAlignment
     *            expected alignment
     * @param actualAlignment
     *            tested alignment
     */
    private void checkAlignment(String errorMessage, int expectedAlignment, int actualAlignment) {
        switch (expectedAlignment) {
        case PositionConstants.LEFT:
            assertEquals(errorMessage, PositionConstants.LEFT, actualAlignment);
            break;
        case PositionConstants.RIGHT:
            assertEquals(errorMessage, PositionConstants.RIGHT, actualAlignment);
            break;
        case PositionConstants.CENTER:
            assertEquals(errorMessage, PositionConstants.CENTER, actualAlignment);
            break;
        default:
            break;
        };
    }

    /**
     * Test the border label position in gauge figure
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testBorderLabelPosition() throws Exception {
        SWTBotGefEditPart editPart1 = editor.getEditPart("SubPackage1Border", AbstractDiagramNodeEditPart.class);
        assertNotNull(editPart1);
        checkBorderLabelPosition(editPart1, new Point(96, 180), new Point(158, 185));
        editor.drag(editPart1, 150, 150);
        SWTBotUtils.waitAllUiEvents();
        checkBorderLabelPosition(editPart1, new Point(150, 150), new Point(212, 155));

        // resize the edit part
        Rectangle bounds = editor.getBounds(editPart1);
        editor.drag(bounds.getBottomRight(), 160, 160);
        SWTBotUtils.waitAllUiEvents();
        checkBorderLabelPositionCenteredUnderParent(editPart1, new Point(150, 150));
    }

    /**
     * Test the node label position in gauge figure
     * 
     * @throws Exception
     *             In case of problem
     */
    public void testNodeLabelPosition() throws Exception {
        SWTBotGefEditPart editPart1 = editor.getEditPart("SubPackage1", AbstractDiagramNodeEditPart.class);
        assertNotNull(editPart1);
        checkNodeLabelPosition(editPart1, new Point(20, 20), new Point(0, 0));
        editor.drag(editPart1, 50, 50);
        SWTBotUtils.waitAllUiEvents();
        checkNodeLabelPosition(editPart1, new Point(50, 50), new Point(0, 0));

        // resize the edit part
        editor.drag(111, 131, 150, 150);
        SWTBotUtils.waitAllUiEvents();
        checkNodeLabelPosition(editPart1, new Point(89, 69), new Point(0, 0));
    }

    /**
     * Check the node label position of the edit part
     * 
     * @param editPart1
     */
    private void checkNodeLabelPosition(SWTBotGefEditPart editPart1, Point p1, Point p2) {
        assertEquals(((GraphicalEditPart) editPart1.part()).getFigure().getBounds().x, p1.x);
        assertEquals(((GraphicalEditPart) editPart1.part()).getFigure().getBounds().y, p1.y);

        ViewNodeFigure viewNodeFigure = getViewNodeFigure(((GraphicalEditPart) editPart1.part()).getFigure());
        assertNotNull(viewNodeFigure);
        assertEquals("The x coordinate of the label is not what is expected.", p2.x, viewNodeFigure.getNodeLabel().getBounds().x);
        assertEquals("The y coordinate of the label is not what is expected.", p2.y, viewNodeFigure.getNodeLabel().getBounds().y);
    }

    /**
     * Check the border label position of the edit part is centered and under its parent.
     * 
     * @param editPart1
     *            The parent
     * @param expectedLocation
     *            The expected location for parent
     */
    private void checkBorderLabelPositionCenteredUnderParent(SWTBotGefEditPart editPart1, Point expectedLocation) {
        Rectangle parentBounds = ((GraphicalEditPart) editPart1.part()).getFigure().getBounds();
        SWTBotGefEditPart labelEditPart = editor.getEditPart("SubPackage1Border");
        assertNotNull(labelEditPart);
        Dimension labelSize = ((GraphicalEditPart) labelEditPart.part()).getFigure().getSize();

        int xCoordinateComputedLocation = parentBounds.getCenter().x - (labelSize.width / 2);
        int spaceBetweenNodeAndItsLabel = 1;
        int yCoordinateComputedLocation = parentBounds.getBottom().y + spaceBetweenNodeAndItsLabel;
        checkBorderLabelPosition(editPart1, expectedLocation, new Point(xCoordinateComputedLocation, yCoordinateComputedLocation));
    }

    /**
     * Check the border label position of the edit part
     * 
     * @param editPart1
     */
    private void checkBorderLabelPosition(SWTBotGefEditPart editPart1, Point p1, Point p2) {
        Rectangle bounds = ((GraphicalEditPart) editPart1.part()).getFigure().getBounds();
        assertEquals(p1.x, bounds.x);
        assertEquals(p1.y, bounds.y);

        SWTBotGefEditPart labelEditPart = editor.getEditPart("SubPackage1Border");
        assertNotNull(labelEditPart);
        Rectangle labelBounds = ((GraphicalEditPart) labelEditPart.part()).getFigure().getBounds();
        // Add a delta tolerance because in Eclipse 3.8 it failed of 1 pixel
        assertEquals(p2.y, labelBounds.y, 1);
        // If the x coordinate is not the expected one, compute it from the
        // center of its parent.
        if (Math.abs(p2.x - labelBounds.x) > 1) {
            int xCoordinateCOmputedLocation = bounds.getCenter().x - (labelBounds.width / 2);
            assertEquals(xCoordinateCOmputedLocation, labelBounds.x, 1);
        }
    }

    /**
     * Finds the ViewNodeFigure children.
     * 
     * @param figure
     * @return the view node figure
     */
    private ViewNodeFigure getViewNodeFigure(IFigure figure) {
        while (!(figure instanceof WorkspaceImageFigure) && figure.getChildren() != null && !figure.getChildren().isEmpty()) {
            if (!(Iterables.isEmpty(Iterables.filter(figure.getChildren(), ViewNodeFigure.class)))) {
                return Iterables.get((Iterables.filter(figure.getChildren(), ViewNodeFigure.class)), 0);
            }
            for (Object object : figure.getChildren()) {
                if (object instanceof IFigure) {
                    IFigure childFigure = (IFigure) object;
                    ViewNodeFigure fig = getViewNodeFigure(childFigure);
                    if (fig != null) {
                        return fig;
                    }
                }
            }
        }
        return null;
    }
}
