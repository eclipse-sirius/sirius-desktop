/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test class for basic message management
 * 
 * @author smonnier
 */
public class SequenceBasicMessageTest extends AbstractDefaultModelSequenceTests {

    public void test_Creation_Deletion() {
        editor.reveal(LIFELINE_A);
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 300, LIFELINE_B, 250);
        assertMessageVerticalPosition(FIRST_MESSAGE, 300);

        editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE));
        deleteSelectedElement();
        assertNoEditPartWithLabel(FIRST_MESSAGE);
    }

    public void test_Ordering() {
        editor.reveal(LIFELINE_A);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 300, LIFELINE_B, 250);
        CheckMessageEditPartIsDisplayed check = new CheckMessageEditPartIsDisplayed(FIRST_MESSAGE, editor);
        bot.waitUntil(check);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 150, LIFELINE_B, 250);
        check = new CheckMessageEditPartIsDisplayed(SECOND_MESSAGE, editor);
        bot.waitUntil(check);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 250, LIFELINE_B, 250);
        check = new CheckMessageEditPartIsDisplayed(THIRD_MESSAGE, editor);
        bot.waitUntil(check);

        assertMessageVerticalPosition(FIRST_MESSAGE, 300);
        assertMessageVerticalPosition(SECOND_MESSAGE, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 250);

        validateOrdering();
    }

    public void test_Edition() {
        editor.reveal(LIFELINE_A);
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 200, LIFELINE_B, 250);
        editor.directEdgeEditTypeOnBorderNodesOnly(LIFELINE_A, LIFELINE_B, "message 1");

        String newMessageLabel = FIRST_MESSAGE.replaceFirst("m1", "message 1");
        assertNotNull("There is no message with the label ", getSequenceMessageEditPart(newMessageLabel));
    }

    public void test_ArrangeAll() {
        // Go to the origin to avoid scroll bar. Indeed, there is sometimes,
        // without understanding why, scroll bar in this test, and this makes
        // fail the test.
        editor.scrollTo(0, 0);
        editor.reveal(LIFELINE_A);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 300, LIFELINE_B, 250);
        CheckMessageEditPartIsDisplayed check = new CheckMessageEditPartIsDisplayed(FIRST_MESSAGE, editor);
        bot.waitUntil(check);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 150, LIFELINE_B, 250);
        check = new CheckMessageEditPartIsDisplayed(SECOND_MESSAGE, editor);
        bot.waitUntil(check);

        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 250, LIFELINE_B, 250);
        check = new CheckMessageEditPartIsDisplayed(THIRD_MESSAGE, editor);
        bot.waitUntil(check);

        assertMessageVerticalPosition(FIRST_MESSAGE, 300);
        assertMessageVerticalPosition(SECOND_MESSAGE, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 250);

        validateOrdering();

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        arrangeAll();

        // Validates the graphical position
        int y = 130;
        assertMessageVerticalPosition(SECOND_MESSAGE, y);
        assertMessageVerticalPosition(THIRD_MESSAGE, y += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);
        assertMessageVerticalPosition(FIRST_MESSAGE, y += LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP);

        // Validates the semantic ordering equals the graphical ordering
        validateOrdering();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Undo_Redo() throws Exception {
        test_ArrangeAll();

        validateOrdering();

        // Undo Arrange All
        undo();

        validateOrdering();

        // Validates the graphical position
        assertMessageVerticalPosition(FIRST_MESSAGE, 300);
        assertMessageVerticalPosition(SECOND_MESSAGE, 150);
        assertMessageVerticalPosition(THIRD_MESSAGE, 250);

        // Undo THIRD_MESSAGE creation
        undo();

        assertMessageVerticalPosition(FIRST_MESSAGE, 300);
        assertMessageVerticalPosition(SECOND_MESSAGE, 150);

        assertNoEditPartWithLabel(THIRD_MESSAGE);

        // Undo SECOND_MESSAGE creation
        undo();

        assertMessageVerticalPosition(FIRST_MESSAGE, 300);

        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNoEditPartWithLabel(THIRD_MESSAGE);

        // Undo FIRST_MESSAGE creation
        editor.click(0, 0);
        undo();

        assertNoEditPartWithLabel(FIRST_MESSAGE);
        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNoEditPartWithLabel(THIRD_MESSAGE);

        // Redo FIRST_MESSAGE creation
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertNoEditPartWithLabel(SECOND_MESSAGE);
        assertNoEditPartWithLabel(THIRD_MESSAGE);

        // Redo SECOND_MESSAGE creation
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_MESSAGE));

        assertNoEditPartWithLabel(THIRD_MESSAGE);

        // Redo THIRD_MESSAGE creation
        redo();

        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(SECOND_MESSAGE));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 250, getSequenceMessageVerticalPosition(THIRD_MESSAGE));

        // Redo Arrange All
        redo();

        assertEquals("The message named " + SECOND_MESSAGE + " is not at the expected vertical position", 130, getSequenceMessageVerticalPosition(SECOND_MESSAGE));
        assertEquals("The message named " + THIRD_MESSAGE + " is not at the expected vertical position", 150, getSequenceMessageVerticalPosition(THIRD_MESSAGE));
        assertEquals("The message named " + FIRST_MESSAGE + " is not at the expected vertical position", 170, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Zoom() throws Exception {
        try {
            editor.zoom(ZoomLevel.ZOOM_50);
            // Reveal A to scroll to the left
            editor.reveal(LIFELINE_A);

            // Calculate the X position of the center of lifelines A and B

            int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
            int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

            // Creation of 3 message
            createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 150, lifelineBPosition, 125);
            createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 75, lifelineBPosition, 125);
            createMessage(InteractionsConstants.READ_TOOL_ID, lifelineAPosition, 125, lifelineBPosition, 125);

            // Validates the graphical position
            assertNotNull("The message named " + FIRST_MESSAGE + " has not been created", getSequenceMessageVerticalPosition(FIRST_MESSAGE));
            assertEquals(300, getSequenceMessageVerticalPosition(FIRST_MESSAGE));
            assertNotNull("The message named " + SECOND_MESSAGE + " has not been created", getSequenceMessageVerticalPosition(SECOND_MESSAGE));
            assertEquals(150, getSequenceMessageVerticalPosition(SECOND_MESSAGE));
            assertNotNull("The message named " + THIRD_MESSAGE + " has not been created", getSequenceMessageVerticalPosition(THIRD_MESSAGE));
            assertEquals(250, getSequenceMessageVerticalPosition(THIRD_MESSAGE));

            validateOrdering();
        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test move message to combined fragment.
     * <p>
     * Step 1 : Test move message to first operand.
     * <p>
     * Step 2 : Test move message to second operand.
     * <p>
     * Step 3 : Test move message under the combined fragment
     */
    public void test_Move_To_Combined_Fragment() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        // Create message
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 300, LIFELINE_B, 250);
        assertMessageVerticalPosition(FIRST_MESSAGE, 300);

        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 400);
        Point pointB = new Point(positionXLifeLineB, 400);

        // Create combined fragment
        ICondition done = new OperationDoneCondition();
        SWTBotGefEditPart combinedFragment = createCombinedFragmentWithResult(pointA, pointB);
        bot.waitUntil(done);

        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragment);

        // Create operand
        done = new OperationDoneCondition();
        Option<SWTBotGefEditPart> newOperandOption = createCombinedFragmentOperandWithResult(combinedFragmentBounds.getCenter());
        bot.waitUntil(done);

        SWTBotGefEditPart newOperandBot = newOperandOption.get();
        Rectangle newOperandBounds = editor.getBounds(newOperandBot);
        combinedFragmentBounds = editor.getBounds(combinedFragment);

        editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE));
        // Drag message to combined fragment
        done = new OperationDoneCondition();
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE), pointA.x, pointA.y + 50);
        bot.waitUntil(done);

        assertMessageVerticalPosition(FIRST_MESSAGE, 450);

        // Drag message to operand
        done = new OperationDoneCondition();
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE), newOperandBounds.getCenter().x, newOperandBounds.getCenter().y + 20);
        bot.waitUntil(done);

        assertMessageVerticalPosition(FIRST_MESSAGE, newOperandBounds.getCenter().y + 20);

        // Drag message under combiend fragment
        done = new OperationDoneCondition();
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getBottom().y + 20);
        bot.waitUntil(done);

        assertMessageVerticalPosition(FIRST_MESSAGE, combinedFragmentBounds.getBottom().y + 20);

        // Drag message to combined Fragment titlle (this operation is
        // forbidden)
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE), pointA.x, pointA.y + 5);
        assertMessageVerticalPosition(FIRST_MESSAGE, combinedFragmentBounds.getBottom().y + 20);
    }

    /**
     * Test move message to combined fragment when it's forbidden.
     * <p>
     * Step 1 : Test move message to first operand (it's forbidden).
     * <p>
     * Step 2 : Test move message to second operand (it's forbidden).
     * <p>
     * Step 3 : Test move message under the combined fragment (it's ok)
     */
    public void test_Move_To_Combined_Fragment_Forbidden() {
        editor.reveal(LIFELINE_B);
        arrangeAll();
        editor.maximize();
        // Create message between life line B and life line C
        ICondition done = new OperationDoneCondition();
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_B, 300, LIFELINE_C, 250);
        bot.waitUntil(done);

        assertMessageVerticalPosition(FIRST_MESSAGE, 300);

        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 400);
        Point pointB = new Point(positionXLifeLineB, 400);
        // Create combined fragment
        done = new OperationDoneCondition();
        SWTBotGefEditPart combinedFragment = createCombinedFragmentWithResult(pointA, pointB);
        bot.waitUntil(done);

        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragment);

        // Create operand
        done = new OperationDoneCondition();
        Option<SWTBotGefEditPart> newOperandOption = createCombinedFragmentOperandWithResult(combinedFragmentBounds.getCenter());
        bot.waitUntil(done);

        SWTBotGefEditPart newOperandBot = newOperandOption.get();
        Rectangle newOperandBounds = editor.getBounds(newOperandBot);
        combinedFragmentBounds = editor.getBounds(combinedFragment);

        editor.click(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE));
        // Drag message to combined fragment
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE), pointA.x, pointA.y + 50);
        assertMessageVerticalPosition(FIRST_MESSAGE, 300);

        // Drag message to operand
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE), newOperandBounds.getCenter().x, newOperandBounds.getCenter().y + 20);
        assertMessageVerticalPosition(FIRST_MESSAGE, 300);

        // Drag message under combined fragment
        done = new OperationDoneCondition();
        editor.drag(getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getBottom().y + 20);
        bot.waitUntil(done);

        assertMessageVerticalPosition(FIRST_MESSAGE, combinedFragmentBounds.getBottom().y + 20);
    }

    /**
     * Test move lost message to combined fragment.
     * <p>
     * Step 1 : Test move message to first operand.
     * <p>
     * Step 2 : Test move message to second operand.
     * <p>
     * Step 3 : Test move message under the combined fragment
     * 
     * @throws Exception
     *             exception
     */
    public void test_Move_Lost_To_Combined_Fragment() throws Exception {
        // Activate layer Lost and found messages creation
        enableLayer();

        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        // Create message
        ICondition done = new OperationDoneCondition();
        createMessage("Found Sync Call", LIFELINE_A, 300, LIFELINE_B, 250);
        bot.waitUntil(done);

        assertMessageVerticalPosition(FIRST_MESSAGE, 300);
        Rectangle executionBounds = getExecutionLogicalBounds(LIFELINE_A, 0);
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 400);
        Point pointB = new Point(positionXLifeLineB, 400);

        // Create combined fragment
        SWTBotGefEditPart combinedFragment = createCombinedFragmentWithResult(pointA, pointB);
        Rectangle combinedFragmentBounds = editor.getBounds(combinedFragment);
        // Create operand
        done = new OperationDoneCondition();
        Option<SWTBotGefEditPart> newOperandOption = createCombinedFragmentOperandWithResult(combinedFragmentBounds.getCenter());
        bot.waitUntil(done);

        SWTBotGefEditPart newOperandBot = newOperandOption.get();
        Rectangle newOperandBounds = editor.getBounds(newOperandBot);
        combinedFragmentBounds = editor.getBounds(combinedFragment);
        editor.click(getExecutionScreenPosition(LIFELINE_A, 0));

        // Drag message to combined fragment to first operand
        done = new OperationDoneCondition();
        editor.drag(getExecutionScreenPosition(LIFELINE_A, 0), pointA.x, combinedFragmentBounds.getCenter().y - 15);
        bot.waitUntil(done);

        executionBounds = getExecutionLogicalBounds(LIFELINE_A, 0);
        newOperandBot = newOperandOption.get();
        newOperandBounds = editor.getBounds(newOperandBot);
        combinedFragmentBounds = editor.getBounds(combinedFragment);
        assertTrue("the message sould be in first operand", combinedFragmentBounds.contains(executionBounds));

        // Drag message to second operand
        done = new OperationDoneCondition();
        editor.drag(getExecutionScreenPosition(LIFELINE_A, 0), newOperandBounds.getCenter().x, newOperandBounds.getCenter().y);
        bot.waitUntil(done);

        newOperandBot = newOperandOption.get();
        newOperandBounds = editor.getBounds(newOperandBot);
        combinedFragmentBounds = editor.getBounds(combinedFragment);
        executionBounds = getExecutionLogicalBounds(LIFELINE_A, 0);

        assertTrue("the message sould be in second operand", newOperandBounds.contains(executionBounds.getTopRight()));
        assertTrue("the message sould be in second operand", newOperandBounds.contains(executionBounds.getBottomRight()));
        // Drag message under combined fragment
        done = new OperationDoneCondition();
        editor.drag(getExecutionScreenPosition(LIFELINE_A, 0), combinedFragmentBounds.getCenter().x, combinedFragmentBounds.getBottom().y + 20);
        bot.waitUntil(done);

        executionBounds = getExecutionLogicalBounds(LIFELINE_A, 0);
        assertTrue("The message should be under combined fragment", executionBounds.y > combinedFragmentBounds.getBottom().y);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void enableLayer() throws Exception {
        ICondition done = new OperationDoneCondition();
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers().activateLayer("Lost and found messages creation");
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();
    }
}
