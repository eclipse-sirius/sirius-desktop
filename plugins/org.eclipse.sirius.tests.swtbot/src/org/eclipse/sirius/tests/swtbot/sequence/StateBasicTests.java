/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckResize;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swt.SWTException;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;

import com.google.common.collect.Iterables;

/**
 * Test class for creation state management
 * 
 * @author smonnier
 */
public class StateBasicTests extends AbstractStatesSequenceTests {

    // Main part
    private SWTBotGefEditPart sequenceDiagramBot;

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);

        sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();
    }

    /**
     * Test method.
     */
    public void test_Zoom() {
        try {
            ZoomLevel zoom50 = ZoomLevel.ZOOM_50;
            editor.zoom(ZoomLevel.ZOOM_50);

            doCreateS1OnLifelineA(75, zoom50.getAmount());

            doCreateS2OnLifelineB(125, zoom50.getAmount());

            bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 2));

            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

            // manualRefresh();

            // Move down the state beyond the state on lifeline B
            Point expectedMove = new Point(0, 75);
            CheckEditPartMoved checkMoved = new CheckEditPartMoved(stateS1Bot);
            editor.drag(stateS1ScreenBounds.getLocation().getCopy(), stateS1ScreenBounds.getLocation().getCopy().getTranslated(expectedMove));
            bot.waitUntil(checkMoved);

            // Expected effect
            stateS1ScreenBounds.translate(expectedMove);

            // Validates the state bounds
            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
            assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

            validateOrdering(4);

            // Resize up the state beyond the state on lifeline B
            expectedMove = new Point(0, -75);
            editor.click(stateS1ScreenBounds.getTop());
            CheckEditPartResized checkResized = new CheckEditPartResized(stateS1Bot);
            editor.drag(stateS1ScreenBounds.getTop(), stateS1ScreenBounds.getTop().getCopy().getTranslated(expectedMove));
            bot.waitUntil(checkResized);

            // Expected effect
            stateS1ScreenBounds.translate(expectedMove);
            stateS1ScreenBounds.resize(0, expectedMove.getNegated().y);

            // Validates the state bounds
            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
            assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

            validateOrdering(4);

            // Click on the diagram to unfocus the created element
            editor.click(0, 0);
            // Arrange all after moving and resizing
            arrangeAll();

            // Expected layout
            int sA0 = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + (int) (LayoutConstants.TIME_START_OFFSET * zoom50.getAmount());
            int sB0 = sA0 + (int) (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * zoom50.getAmount());
            int fB0 = sB0 + (int) (LayoutConstants.INTERACTION_STATE_MIN_HEIGHT_AFTER_LAYOUT * zoom50.getAmount());
            int fA0 = fB0 + (int) (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * zoom50.getAmount());

            stateS1ScreenBounds.y = sA0;
            stateS1ScreenBounds.height = fA0 - sA0;
            stateS2ScreenBounds.y = sB0;
            stateS2ScreenBounds.height = fB0 - sB0;

            // Validates the state bounds
            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
            stateS2ScreenBounds.x = stateS2ScreenBounds.x - LayoutConstants.UNIT;
            assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

            validateOrdering(4);
        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     */
    public void test_Creation() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
    }

    /**
     * Test method.
     */
    public void test_Creation2() {
        maximizeEditor(editor);

        test_Creation();

        doCreateS2OnLifelineB(250, ZoomLevel.ZOOM_100.getAmount());

        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Deletion() throws Exception {
        test_Creation();

        // Deletion of the states
        editor.click(stateS1ScreenBounds.getLocation());
        bot.menu("Edit").menu("Delete").click();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);

        assertStateDoesNotExist(STATE_S1);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Move() throws Exception {
        maximizeEditor(editor);

        test_Creation();
        // Select the diagram itself for the future drag
        editor.select(editor.mainEditPart());

        Point move = new Point(0, 50);
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(stateS1Bot);
        editor.drag(stateS1ScreenBounds.getLocation(), stateS1ScreenBounds.getLocation().getTranslated(move));
        bot.waitUntil(checkMoved);

        // Expected effect
        stateS1ScreenBounds.translate(move);

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

        validateOrdering(2);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Ordering() throws Exception {
        test_Creation();

        SequenceDiagramEditPart sequenceDiagramEditPart = Iterables.getOnlyElement(Iterables.filter(editor.rootEditPart().part().getChildren(), SequenceDiagramEditPart.class));
        assertNotNull("No SequenceDiagramEditPart found", sequenceDiagramEditPart);

        // Validates the semantic ordering equals the graphical ordering
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Change_States_Order() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        doCreateS2OnLifelineA(250, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 2));

        CheckEditPartMoved checkMoved = new CheckEditPartMoved(stateS1Bot);
        editor.drag(stateS1ScreenBounds.getTop(), stateS2ScreenBounds.getBottom().getTranslated(0, 50));
        bot.waitUntil(checkMoved);

        int deltaY = stateS2ScreenBounds.getBottom().getTranslated(0, 50).y - stateS1ScreenBounds.getTop().y;

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds.getTranslated(0, deltaY));
        assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

        validateOrdering(4);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Resize() throws Exception {
        test_Creation();

        // Resize up
        editor.click(stateS1ScreenBounds.getLocation());
        Point expectedTranslation = new Point(0, -50);
        CheckResize cR = new CheckResize(LIFELINE_A, 0, expectedTranslation, editor);
        editor.drag(stateS1ScreenBounds.getTop(), stateS1ScreenBounds.getTop().getCopy().getTranslated(expectedTranslation));
        bot.waitUntil(cR);

        // Expected effect
        stateS1ScreenBounds.translate(expectedTranslation);
        stateS1ScreenBounds.resize(0, expectedTranslation.getNegated().y);

        // Validates the position of the state
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

        validateOrdering(2);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Horizontal_Resize() throws Exception {
        test_Creation();

        // Resize
        editor.click(stateS1ScreenBounds.getLocation());
        Dimension expectedResize = new Dimension(50, 0);
        ICondition done = new CheckResize(LIFELINE_A, 0, expectedResize.getScaled(2), editor);
        editor.drag(stateS1ScreenBounds.getRight(), stateS1ScreenBounds.getRight().getCopy().getTranslated(expectedResize));
        bot.waitUntil(done);

        // Expected effect
        Rectangle resized = stateS1ScreenBounds.getCopy();
        resized.translate(-expectedResize.width, 0);
        resized.resize(2 * expectedResize.width, 0);

        // Validates the position of the state
        assertStateHasValidScreenBounds(stateS1Bot, resized);

        validateOrdering(2);

        undo();
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

        redo();
        assertStateHasValidScreenBounds(stateS1Bot, resized);

        editor.click(stateS1ScreenBounds.getLocation());
        expectedResize = expectedResize.getNegated();
        done = new CheckResize(LIFELINE_A, 0, expectedResize.getScaled(2), editor);
        editor.drag(resized.getRight(), resized.getRight().getCopy().getTranslated(expectedResize));
        bot.waitUntil(done);

        // Validates the position of the state
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

        validateOrdering(2);

        undo();
        assertStateHasValidScreenBounds(stateS1Bot, resized);

        redo();
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

        undo();
        assertStateHasValidScreenBounds(stateS1Bot, resized);

        undo();
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

    }

    /**
     * Test method.
     */
    public void test_Move_Resize_Ordering() {
        test_Creation2();

        // manualRefresh();

        // Move down the state beyond the state on lifeline B
        Point move = new Point(0, 150);
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(stateS1Bot);
        editor.drag(stateS1ScreenBounds.getLocation(), stateS1ScreenBounds.getLocation().getCopy().getTranslated(move));
        bot.waitUntil(checkMoved);

        // Expected effect
        stateS1ScreenBounds.translate(move);

        // Validates the position of the state
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

        validateOrdering(4);

        // Resize up the state beyond the state on lifeline B
        editor.click(stateS1ScreenBounds.getLocation());
        move = new Point(0, -150);
        CheckResize cR = new CheckResize(LIFELINE_A, 0, move, editor);
        editor.drag(stateS1ScreenBounds.getTop(), stateS1ScreenBounds.getTop().getCopy().getTranslated(move));
        bot.waitUntil(cR);

        // Expected effect
        stateS1ScreenBounds.translate(move);
        stateS1ScreenBounds.resize(0, move.getNegated().y);

        // Validates the position of the state, it should be back at where
        // it has been created
        // Validates the position of the state
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

        validateOrdering(4);
    }

    /**
     * Test method.
     * 
     */
    public void test_ArrangeAll() {
        test_Move_Resize_Ordering();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);

        // Arrange all after moving and resizing
        arrangeAll();

        // Expected layout
        int sA0 = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + LayoutConstants.TIME_START_OFFSET;
        int sB0 = sA0 + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        int fB0 = sB0 + LayoutConstants.INTERACTION_STATE_MIN_HEIGHT_AFTER_LAYOUT;
        int fA0 = fB0 + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;

        Rectangle expectedA0 = new Rectangle(0, sA0, 0, fA0 - sA0);
        Rectangle expectedB0 = new Rectangle(0, sB0, 0, fB0 - sB0);

        // Validate vertical position after arrange all
        assertStateHasValidScreenBounds(stateS1Bot, expectedA0, false);
        assertStateHasValidScreenBounds(stateS2Bot, expectedB0, false);
    }

    /**
     * Test resize state with 2 executions.
     * <p>
     * Step 1 : Resize state above the first execution. This action is forbidden.
     * <p>
     * Step 2 : Resize state between the limit of the first execution. This action is forbidden.
     * <p>
     * Step 3 : Resize state under the second execution.
     * <p>
     * Step 4 : Resize state between the limit of the second execution. This action move the second execution down.
     */
    public void test_Resize_State_With_2_Executions() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        // Create first execution on lifeLine A
        createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 150)).get();
        Rectangle firstExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 150, 0, 30), false);
        // Create first state on lifeLine A
        Option<SWTBotGefEditPart> state = createStateWithResult(new Point(getLifelineScreenX(LIFELINE_A), 250));
        Rectangle stateBoundsA = assertStateHasValidScreenBounds((StateEditPart) state.get().part(), new Rectangle(0, 250, 0, 30), false);
        // Create second execution on lifeLine A
        createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 350)).get();
        Rectangle secondExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 2, new Rectangle(0, 350, 0, 30), false);
        // Resize state above the first execution. This action is forbidden
        editor.select(state.get());
        editor.drag(stateBoundsA.getTop(), firstExecutionBoundsA.getCenter().x, firstExecutionBoundsA.getTop().y - 15);
        Rectangle stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, stateBoundsA.height);
        Rectangle firstExecution = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecution, true);
        editor.select(state.get());
        // Resize state between the limit of the first execution.
        // This action is forbidden.
        editor.drag(stateBoundsA.getTop(), firstExecutionBoundsA.getCenter().x, firstExecutionBoundsA.getCenter().y);
        stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, stateBoundsA.height);
        firstExecution = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecution, true);
        editor.select(state.get());
        // Resize state execution under the second execution. This action move
        // the second execution down.
        editor.drag(stateBoundsA.getBottom(), secondExecutionBoundsA.getCenter().x, secondExecutionBoundsA.getBottom().y + 15);
        stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, (secondExecutionBoundsA.getBottom().y + 15) - stateBoundsA.getTop().y);
        Rectangle secondExecution = new Rectangle(secondExecutionBoundsA.x, secondExecutionBoundsA.y + secondExecutionBoundsA.height + 15 + 5, secondExecutionBoundsA.width,
                secondExecutionBoundsA.height);
        bot.sleep(500);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 2, secondExecution, true);
        // Undo to return to begin state
        undo();
        editor.select(state.get());
        // Resize state between the limit of the second execution.
        // This action move the second execution down.
        editor.drag(stateBoundsA.getBottom(), secondExecutionBoundsA.getCenter().x, secondExecutionBoundsA.getCenter().y);
        stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, secondExecutionBoundsA.getCenter().y - stateBoundsA.y);
        secondExecution = new Rectangle(secondExecutionBoundsA.x, secondExecutionBoundsA.y + (secondExecutionBoundsA.height / 2) + 5, secondExecutionBoundsA.width, secondExecutionBoundsA.height);
        bot.sleep(500);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 2, secondExecution, true);
    }

    /**
     * Scenario that checks that resizing a state close to the message of the parent sync call is possible and does not
     * throw class cast exceptions.
     */
    public void test_Resize_State_On_Execution() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        // Create a sync call from lifeLine B to A
        createSyncCall(new Point(getLifelineScreenX(LIFELINE_B), 150), new Point(getLifelineScreenX(LIFELINE_A), 150));
        Rectangle firstSyncCall = getExecutionScreenBounds(LIFELINE_A, 0);
        ExecutionEditPart executionEP = getExecutionEditPart(LIFELINE_A, 0);
        Rectangle firstExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 150, 0, 50), false);

        // Resize the execution
        editor.click(firstExecutionBoundsA.getBottom());
        Point expectedTranslation = new Point(0, 200);
        CheckEditPartResized cR = new CheckEditPartResized(executionEP);
        editor.drag(firstExecutionBoundsA.getBottom(), firstExecutionBoundsA.getBottom().getCopy().getTranslated(expectedTranslation));
        bot.waitUntil(cR);

        // Create a state on the execution
        Option<SWTBotGefEditPart> state = createStateWithResult(new Point(getLifelineScreenX(LIFELINE_A), 250));
        Rectangle stateBoundsA = assertStateHasValidScreenBounds((StateEditPart) state.get().part(), new Rectangle(0, 250, 0, 30), false);

        // Resize state close to the execution upper bound or close to m1
        editor.select(state.get());
        expectedTranslation = new Point(0, -95);
        cR = new CheckEditPartResized(state.get());
        editor.drag(stateBoundsA.getTop(), stateBoundsA.getTop().getCopy().getTranslated(expectedTranslation));
        try {
            bot.waitUntil(cR);
        } catch (TimeoutException e) {
            boolean doesAnErrorOccurs = platformProblemsListener.doesAnErrorOccurs();
            if (doesAnErrorOccurs) {
                Throwable exception = platformProblemsListener.getErrors().values().stream().filter(status -> !status.isEmpty()).findFirst().get().iterator().next().getException();
                if (exception instanceof ClassCastException || (exception instanceof SWTException && exception.getCause() instanceof ClassCastException)) {
                    fail("The resize of the state next to the message m1 causes a ClassCastException");
                }
            }
            fail(e.getMessage());
        }
    }

    /**
     * Test resize state with 2 messages.
     * <p>
     * Step 1 : Resize state above the first message. This action is forbidden.
     * <p>
     * Step 2 : Resize state on the first message. This action is forbidden.
     * <p>
     * Step 3 : Resize state under the second message.
     * <p>
     * Step 4 : Resize state on the second message. This action is forbidden.
     */
    public void test_Resize_State_With_Message() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        // Create first message on lifeLine A
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_A, 150, LIFELINE_B, 150);
        assertMessageVerticalPosition(FIRST_MESSAGE, 150);
        // Create first state on lifeLine A
        Option<SWTBotGefEditPart> state = createStateWithResult(new Point(getLifelineScreenX(LIFELINE_A), 250));
        Rectangle stateBoundsA = assertStateHasValidScreenBounds((StateEditPart) state.get().part(), new Rectangle(0, 250, 0, 30), false);
        // Create second message on lifeLine B
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_B, 350, LIFELINE_A, 350);
        assertMessageVerticalPosition(SECOND_MESSAGE, 350);
        // Resize state above the first message. This action is forbidden
        editor.select(state.get());
        editor.drag(stateBoundsA.getTop(), getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE).x, getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE).y - 15);
        Rectangle stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, stateBoundsA.height);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertMessageVerticalPosition(FIRST_MESSAGE, 150);
        editor.select(state.get());
        // Resize state on the first message.
        // This action is forbidden.
        editor.drag(stateBoundsA.getTop(), getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE).x, getSequenceMessageScreenCenteredPosition(FIRST_MESSAGE).y);
        stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, stateBoundsA.height);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertMessageVerticalPosition(FIRST_MESSAGE, 150);
        editor.select(state.get());
        // Resize state execution under the second message. This action move
        // the second message down.
        editor.drag(stateBoundsA.getBottom(), getSequenceMessageScreenCenteredPosition(SECOND_MESSAGE).x, getSequenceMessageScreenCenteredPosition(SECOND_MESSAGE).y + 15);
        stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, (getSequenceMessageScreenCenteredPosition(SECOND_MESSAGE).y - 5) - stateBoundsA.getTop().y);
        bot.sleep(500);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertMessageVerticalPosition(SECOND_MESSAGE, 350 + 15 + 5);
        // Undo to return to begin state
        undo();
        editor.select(state.get());
        // Resize state on the second message, it's forbidden.
        editor.drag(stateBoundsA.getBottom(), getSequenceMessageScreenCenteredPosition(SECOND_MESSAGE).x, getSequenceMessageScreenCenteredPosition(SECOND_MESSAGE).y);
        stateBoundsAResize = new Rectangle(stateBoundsA.x, stateBoundsA.y, stateBoundsA.width, stateBoundsA.height);
        bot.sleep(500);
        assertStateHasValidScreenBounds((StateEditPart) state.get().part(), stateBoundsAResize, true);
        assertMessageVerticalPosition(SECOND_MESSAGE, 350);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        instanceRoleEditPartABot = null;
        super.tearDown();
    }
}
