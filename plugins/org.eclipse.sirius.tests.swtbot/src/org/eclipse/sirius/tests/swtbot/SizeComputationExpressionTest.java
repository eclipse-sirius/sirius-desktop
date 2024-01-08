/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TextEditorAppearedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * This class tests different configurations of the size computation expression.
 * 
 * @author gplouhinec
 */
public class SizeComputationExpressionTest extends AbstractSiriusSwtBotGefTestCase {

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private static final String DATA_UNIT_DIR = "data/unit/size/github-issue-65/"; //$NON-NLS-1$

    private static final String MODEL = "github-issue-65.ecore"; //$NON-NLS-1$

    private static final String SESSION_FILE = "github-issue-65.aird"; //$NON-NLS-1$

    private static final String FILE_DIR = "/"; //$NON-NLS-1$

    private static final String VSM_FILE = "github-issue-65.odesign"; //$NON-NLS-1$

    private static final String REPRESENTATION_DESCRIPTION_NAME = "github-issue-65"; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME = "new " + REPRESENTATION_DESCRIPTION_NAME; //$NON-NLS-1$

    private static final int DEFAULT_WIDTH_OR_HEIGHT = 0;

    private static final int CUSTOM_WIDTH_OR_HEIGHT = 5;

    private static final int AUTO_SIZE_MODE = -1;

    private static final int CUSTOM_SIZE_COMPUTATION_EXPRESSION = 12;

    private static final int WIDTH_AUTO_SIZED = 101;

    private static final int HEIGHT_AUTO_SIZED = 51;
    
    private static final int HEIGHT_AFTER_RENAME = 72;

    private static final int WIDTH_AFTER_RENAME = 210;

    private static final String NEW_ECLASS_NAME = "EClass 01234567890123456789"; //$NON-NLS-1$

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
    }

    public void testAutoSizeComputationExpression() {
        // Creates an EClass at a free location
        SWTBotGefEditPart eClassEP = editor.getEditPart("EClass28", AbstractDiagramNodeEditPart.class); //$NON-NLS-1$
        Point point = editor.getBounds(eClassEP).getTopRight().getCopy();
        point.x = point.x + 10;
        editor.activateTool("EClass"); //$NON-NLS-1$
        editor.click(point);

        // Select the new EClass and direct edit
        eClassEP = editor.getEditPart("EClass29", AbstractDiagramNodeEditPart.class); //$NON-NLS-1$
        editor.click(eClassEP);
        bot.waitUntil(new CheckSelectedCondition(editor, "EClass29", AbstractDiagramNodeEditPart.class)); //$NON-NLS-1$
        // Click a second time on the edit part to enable the direct edit mode.
        editor.clickCentered(eClassEP);
        bot.waitUntil(new TextEditorAppearedCondition(editor, AbstractDiagramNodeEditPart.class, null));
        // Edit the current edit part by adding a suffix to its current name.
        editor.directEditType(NEW_ECLASS_NAME);

        // Check that an editPart exists with the expected new name.
        eClassEP = editor.getEditPart(NEW_ECLASS_NAME, AbstractDiagramNodeEditPart.class);

        // Check its new size
        Dimension sizeAfterRename = editor.getBounds(eClassEP).getSize().getCopy();
        assertEquals(HEIGHT_AFTER_RENAME, sizeAfterRename.height);
        assertEquals(WIDTH_AFTER_RENAME, sizeAfterRename.width);

        moveNodeTwiceAndCheckSize(eClassEP, HEIGHT_AFTER_RENAME, WIDTH_AFTER_RENAME);
    }

    public void testAllCombinationsSizeComputationExpressionSizeNodeWithSquareStyle() {
        testSizeComputationExpressionSquareStyle("EClass01", AUTO_SIZE_MODE, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass02", AUTO_SIZE_MODE, CUSTOM_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass03", AUTO_SIZE_MODE, DEFAULT_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass04", AUTO_SIZE_MODE, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass05", AUTO_SIZE_MODE, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass06", AUTO_SIZE_MODE, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass07", AUTO_SIZE_MODE, CUSTOM_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass08", AUTO_SIZE_MODE, CUSTOM_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass09", AUTO_SIZE_MODE, DEFAULT_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass10", AUTO_SIZE_MODE, DEFAULT_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass11", AUTO_SIZE_MODE, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass12", AUTO_SIZE_MODE, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass13", AUTO_SIZE_MODE, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, false, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass14", AUTO_SIZE_MODE, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, false, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass15", CUSTOM_SIZE_COMPUTATION_EXPRESSION, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass16", CUSTOM_SIZE_COMPUTATION_EXPRESSION, CUSTOM_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass17", CUSTOM_SIZE_COMPUTATION_EXPRESSION, DEFAULT_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass18", CUSTOM_SIZE_COMPUTATION_EXPRESSION, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass19", CUSTOM_SIZE_COMPUTATION_EXPRESSION, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass20", CUSTOM_SIZE_COMPUTATION_EXPRESSION, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass21", CUSTOM_SIZE_COMPUTATION_EXPRESSION, CUSTOM_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass22", CUSTOM_SIZE_COMPUTATION_EXPRESSION, CUSTOM_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass23", CUSTOM_SIZE_COMPUTATION_EXPRESSION, DEFAULT_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass24", CUSTOM_SIZE_COMPUTATION_EXPRESSION, DEFAULT_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass25", CUSTOM_SIZE_COMPUTATION_EXPRESSION, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, false, true); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass26", CUSTOM_SIZE_COMPUTATION_EXPRESSION, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, true, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass27", CUSTOM_SIZE_COMPUTATION_EXPRESSION, CUSTOM_WIDTH_OR_HEIGHT, CUSTOM_WIDTH_OR_HEIGHT, false, false); //$NON-NLS-1$
        testSizeComputationExpressionSquareStyle("EClass28", CUSTOM_SIZE_COMPUTATION_EXPRESSION, DEFAULT_WIDTH_OR_HEIGHT, DEFAULT_WIDTH_OR_HEIGHT, false, false); //$NON-NLS-1$
    }

    /**
     * Tests that an element is the right size depending the following parameters after opening the diagram, moving the
     * element and resizing it.
     * 
     * @param eClassName
     *            the name of the element to test
     * @param sizeComputationExpression
     *            value of the Size Computation Expression in VSM; -1 value corresponds to auto size mode
     * @param heightExpression
     *            value of the Height Expression in VSM; 0 is the default value
     * @param widthExpression
     *            value of the Width Expression in VSM; 0 is the default value
     * @param horizontalResizingAllowed
     *            indicates if horizontal resize is allowed in VSM
     * @param verticalResizingAllowed
     *            indicates if vertical resize is allowed in VSM
     */
    private void testSizeComputationExpressionSquareStyle(String eClassName, int sizeComputationExpression, int heightExpression, int widthExpression, boolean horizontalResizingAllowed,
            boolean verticalResizingAllowed) {
        SWTBotGefEditPart eClassEP = editor.getEditPart(eClassName, AbstractDiagramNodeEditPart.class);
        editor.click(eClassEP);
        bot.waitUntil(new CheckSelectedCondition(editor, eClassName, AbstractDiagramNodeEditPart.class));

        int expectedHeight = HEIGHT_AUTO_SIZED;
        int expectedWidth = WIDTH_AUTO_SIZED;
        if (sizeComputationExpression != -1) {
            expectedHeight = sizeComputationExpression * LayoutUtils.SCALE;
            expectedWidth = sizeComputationExpression * LayoutUtils.SCALE;
        }
        if (heightExpression != 0) {
            expectedHeight = heightExpression * LayoutUtils.SCALE;
        }
        if (widthExpression != 0) {
            expectedWidth = widthExpression * LayoutUtils.SCALE;
        }
        Dimension size = editor.getBounds(eClassEP).getSize().getCopy();
        assertEquals(expectedHeight, size.height);
        assertEquals(expectedWidth, size.width);

        // Move the node twice: A common bug is that the size changes after moving the node once or twice, so we check
        // that the size hasn't changed.
        moveNodeTwiceAndCheckSize(eClassEP, expectedHeight, expectedWidth);

        // Resize the node horizontally and vertically.
        resizeHorizontallyAndVerticallyAndCheckSize(eClassEP, horizontalResizingAllowed, verticalResizingAllowed, expectedHeight, expectedWidth);
    }

    /**
     * Used to move a node twice and check that its size hasn't changed after each move. A common bug is that the size
     * is updated after moving the node once or twice.
     * 
     * @param eClassEP
     *            the element to move
     * @param expectedHeight
     *            vertical size value before move
     * @param expectedWidth
     *            horizontal size value before move
     */
    private void moveNodeTwiceAndCheckSize(SWTBotGefEditPart eClassEP, int expectedHeight, int expectedWidth) {
        Point eClassCenterBeforeMove = editor.getBounds(eClassEP).getCenter().getCopy();
        Point eClassNewPosition = new Point(eClassCenterBeforeMove.x + 50, eClassCenterBeforeMove.y + 50);
        CheckEditPartMoved movedCondition = new CheckEditPartMoved(eClassEP);
        editor.drag(eClassCenterBeforeMove, eClassNewPosition);
        // Check it has been properly moved and its size hasn't changed.
        bot.waitUntil(movedCondition);
        GraphicTestsSupportHelp.assertEquals(eClassNewPosition, editor.getBounds(eClassEP).getCenter().getCopy(), 2);
        Dimension sizeAfterMove = editor.getBounds(eClassEP).getSize().getCopy();
        assertEquals(expectedHeight, sizeAfterMove.height);
        assertEquals(expectedWidth, sizeAfterMove.width);

        movedCondition = new CheckEditPartMoved(eClassEP);
        editor.drag(eClassNewPosition, eClassCenterBeforeMove);
        // Check it has been properly moved a second time and its size hasn't changed.
        bot.waitUntil(movedCondition);
        GraphicTestsSupportHelp.assertEquals(eClassCenterBeforeMove, editor.getBounds(eClassEP).getCenter().getCopy(), 2);
        sizeAfterMove = editor.getBounds(eClassEP).getSize().getCopy();
        assertEquals(expectedHeight, sizeAfterMove.height);
        assertEquals(expectedWidth, sizeAfterMove.width);
    }

    /**
     * Resize the element horizontally and vertically. If a resize is not allowed, the corresponding size should not be
     * updated.
     * 
     * @param eClassEP
     *            the element to move
     * @param horizontalResizingAllowed
     *            indicates if horizontal resize is allowed
     * @param verticalResizingAllowed
     *            indicates if vertical resize is allowed
     * @param expectedHeightBeforeResize
     *            vertical size value before resize
     * @param expectedWidthBeforeResize
     *            horizontal size value before resize
     */
    private void resizeHorizontallyAndVerticallyAndCheckSize(SWTBotGefEditPart eClassEP, boolean horizontalResizingAllowed, boolean verticalResizingAllowed, int expectedHeightBeforeResize,
            int expectedWidthBeforeResize) {
        int expectedHeight = expectedHeightBeforeResize;
        int expectedWidth = expectedWidthBeforeResize;

        // Resize the element horizontally.
        Point eClassRightBeforeResize = editor.getBounds(eClassEP).getRight().getCopy();
        Point newRight = new Point(eClassRightBeforeResize.x + 50, eClassRightBeforeResize.y);
        CheckEditPartResized resizedCondition = new CheckEditPartResized(eClassEP);
        editor.drag(eClassRightBeforeResize, newRight);
        Dimension sizeAfterMove = editor.getBounds(eClassEP).getSize().getCopy();
        if (horizontalResizingAllowed) {
            // Size should be updated horizontally.
            bot.waitUntil(resizedCondition);
            sizeAfterMove = editor.getBounds(eClassEP).getSize().getCopy();
            GraphicTestsSupportHelp.assertEquals(newRight, editor.getBounds(eClassEP).getRight().getCopy(), 2, 0);
            assertEquals(expectedHeight, sizeAfterMove.height);
            Assert.assertEquals(expectedWidth + 50, sizeAfterMove.width, 2);
            expectedWidth = sizeAfterMove.width;
        } else {
            // Size should not be updated.
            GraphicTestsSupportHelp.assertEquals(eClassRightBeforeResize, editor.getBounds(eClassEP).getRight().getCopy(), 0);
            assertEquals(expectedHeight, sizeAfterMove.height);
            assertEquals(expectedWidth, sizeAfterMove.width);
        }

        // Resize the element vertically
        Point eClassBottomBeforeResize = editor.getBounds(eClassEP).getBottom().getCopy();
        Point newBottom = new Point(eClassBottomBeforeResize.x, eClassBottomBeforeResize.y + 50);
        resizedCondition = new CheckEditPartResized(eClassEP);
        editor.drag(eClassBottomBeforeResize, newBottom);
        sizeAfterMove = editor.getBounds(eClassEP).getSize().getCopy();
        if (verticalResizingAllowed) {
            // Size should be updated vertically.
            bot.waitUntil(resizedCondition);
            sizeAfterMove = editor.getBounds(eClassEP).getSize().getCopy();
            GraphicTestsSupportHelp.assertEquals(newBottom, editor.getBounds(eClassEP).getBottom().getCopy(), 0, 2);
            Assert.assertEquals(expectedHeight + 50, sizeAfterMove.height, 2);
            assertEquals(expectedWidth, sizeAfterMove.width);
        } else {
            // Size should not be updated.
            GraphicTestsSupportHelp.assertEquals(eClassBottomBeforeResize, editor.getBounds(eClassEP).getBottom().getCopy(), 0);
            assertEquals(expectedHeight, sizeAfterMove.height);
            assertEquals(expectedWidth, sizeAfterMove.width);
        }
    }
}
