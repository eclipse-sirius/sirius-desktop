/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckResize;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.collect.Iterables;

/**
 * Test class for creation message management
 * 
 * @author smonnier, edugueperoux, jdupont
 */
public class ExecutionTests extends AbstractDefaultModelSequenceTests {

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    private Rectangle instanceRoleEditPartABounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);

        instanceRoleEditPartABounds = editor.getBounds(instanceRoleEditPartABot);

    }

    /**
     * Test method.
     */
    public void test_Creation() {
        doCreateA0A1(200, ZoomLevel.ZOOM_100.getAmount());
    }

    /**
     * Test method.
     */
    public void test_Zoom() {
        try {
            ZoomLevel zoom50 = ZoomLevel.ZOOM_50;
            editor.zoom(ZoomLevel.ZOOM_50);

            doCreateA0A1(75, zoom50.getAmount());

            Rectangle boundsA0 = getExecutionScreenBounds(LIFELINE_A, 0);
            Rectangle boundsA1 = getExecutionScreenBounds(LIFELINE_A, 1);
            int deltaExecutionVerticalPosition = boundsA1.y - boundsA0.y;

            doCreateB0(125, zoom50.getAmount());

            assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
            assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);

            Rectangle boundsB0 = getExecutionScreenBounds(LIFELINE_B, 0);

            editor.click(0, 0);
            manualRefresh();

            // Move down the execution beyond the execution on lifeline B
            Point expectedMove = new Point(0, 75);
            editor.drag(boundsA0.getLocation().getCopy(), boundsA0.getLocation().getCopy().getTranslated(expectedMove));

            // Expected effect
            boundsA0.translate(expectedMove);
            boundsA1.translate(expectedMove);

            // Validates the execution bounds
            assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
            assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
            assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

            // Validates the delta of execution position
            assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

            validateOrdering(6);

            // Resize up the execution beyond the execution on lifeline B
            expectedMove = new Point(0, -75);
            editor.click(boundsA0.getTop());
            editor.drag(boundsA0.getTop(), boundsA0.getTop().getCopy().getTranslated(expectedMove));

            // Expected effect
            boundsA0.translate(expectedMove);
            boundsA0.resize(0, expectedMove.getNegated().y);
            deltaExecutionVerticalPosition += expectedMove.getNegated().y;

            // Validates the execution bounds
            assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
            assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
            assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

            // Validates the delta of execution position
            assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

            validateOrdering(6);

            // Move up the sub execution on lifeline A
            // Click on the diagram to unfocus
            expectedMove = new Point(0, -70);
            editor.drag(boundsA1.getCenter(), boundsA1.getCenter().getCopy().getTranslated(expectedMove));

            // Expected effect
            boundsA1.translate(expectedMove);
            deltaExecutionVerticalPosition += expectedMove.y;

            // Validates the execution bounds
            assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
            assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
            assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

            // Validates the delta of execution position
            assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

            validateOrdering(6);

            // Resize down the sub execution on lifeline A
            Dimension expectedResize = new Dimension(0, 60);
            editor.drag(boundsA1.getBottom(), boundsA1.getBottom().getCopy().getTranslated(expectedResize));

            // Expected effect
            boundsA1.resize(expectedResize);

            // Validates the execution bounds
            assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
            assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
            assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

            // Validates the delta of execution position
            assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

            validateOrdering(6);

            // Click on the diagram to unfocus the created element
            editor.click(0, 0);
            // Arrange all after moving and resizing
            arrangeAll();

            // Expected layout
            int sA0 = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height + (int) (LayoutConstants.TIME_START_OFFSET * zoom50.getAmount());
            int sA1 = sA0 + (int) (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * zoom50.getAmount());
            int sB0 = sA1 + (int) (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * zoom50.getAmount());
            int fB0 = sB0 + (int) (LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT * zoom50.getAmount());
            int fA1 = fB0 + (int) (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * zoom50.getAmount());
            int fA0 = fA1 + (int) (LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP * zoom50.getAmount());

            boundsA0.y = sA0;
            boundsA0.height = fA0 - sA0;
            boundsA1.y = sA1;
            boundsA1.height = fA1 - sA1;
            boundsB0.x = boundsB0.x - LayoutConstants.UNIT;
            boundsB0.y = sB0;
            boundsB0.height = fB0 - sB0;

            // Validates the execution bounds
            assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
            assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
            assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

            validateOrdering(6);

        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     */
    public void test_Creation2() {
        maximizeEditor(editor);

        test_Creation();

        Rectangle boundsA0 = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle boundsA1 = getExecutionScreenBounds(LIFELINE_A, 1);

        doCreateB0(260, ZoomLevel.ZOOM_100.getAmount());

        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
    }

    /**
     * Test that creation of a subExecution e3 on exec e1 in the same range as the exec e2 owned by e1, add correctly e3
     * as child of e2 and with range of e3 included in range of e2.
     */
    public void testExecutionCreationAsSubExecutionOfExecutionOwningAlreadyAExecution() {
        // Create e1 Exec
        Point creationPoint = new Point(instanceRoleEditPartABounds.getCenter().x, 200);
        createExecution(creationPoint.x, creationPoint.y);

        SWTBotGefEditPart e1Bot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);

        // Create e2 child of e1
        creationPoint.y += LayoutConstants.DEFAULT_EXECUTION_HEIGHT / 2;
        createExecution(creationPoint.x, creationPoint.y);

        SWTBotGefEditPart e2Bot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);

        // Test
        createExecution(creationPoint.x, creationPoint.y);

        SWTBotGefEditPart e3Bot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(2);

        Rectangle e1Bounds = editor.getBounds(e1Bot);
        Rectangle e2Bounds = editor.getBounds(e2Bot);
        Rectangle e3Bounds = editor.getBounds(e3Bot);

        Range e1Range = RangeHelper.verticalRange(e1Bounds);
        Range e2Range = RangeHelper.verticalRange(e2Bounds);
        Range e3Range = RangeHelper.verticalRange(e3Bounds);

        assertTrue("e1's range must include range of e2", e1Range.includes(e2Range));
        assertTrue("e2's range must include range of e3", e2Range.includes(e3Range));

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Deletion() throws Exception {
        test_Creation();

        Rectangle execA0ScreenBounds = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle execA1ScreenBounds = getExecutionScreenBounds(LIFELINE_A, 1);

        // Deletion of the executions
        editor.click(execA1ScreenBounds.getLocation());
        bot.menu("Edit").menu("Delete").click();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);
        manualRefresh();

        assertExecutionDoesNotExist(LIFELINE_A, 1);
        assertNotNull("The execution index 0 has not been found", getExecutionScreenPosition(LIFELINE_A, 0));

        // Deletion of the executions
        editor.click(execA0ScreenBounds.getLocation());
        bot.menu("Edit").menu("Delete").click();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);
        manualRefresh();

        assertExecutionDoesNotExist(LIFELINE_A, 0);
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

        editor.click(0, 0);
        manualRefresh();

        Rectangle boundsA0 = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle boundsA1 = getExecutionScreenBounds(LIFELINE_A, 1);
        int deltaExecutionVerticalPosition = boundsA1.y - boundsA0.y;

        Point move = new Point(0, 50);
        editor.drag(boundsA0.getLocation(), boundsA0.getLocation().getTranslated(move));

        // Expected effect
        boundsA0.translate(move);
        boundsA1.translate(move);

        // Validates the execution bounds
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);

        // Validates the delta of execution position
        assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

        validateOrdering(4);
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

        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();

        // Validates the semantic ordering equals the graphical ordering
        assertEquals("The number of event in semantic ordering is not as expected", 4, sequenceDDiagram.getSemanticOrdering().getEventEnds().size());
        assertEquals("The number of event in graphical ordering is not as expected", 4, sequenceDDiagram.getGraphicalOrdering().getEventEnds().size());
        assertTrue("The semantic ordering does not match the graphical ordering",
                Iterables.elementsEqual(sequenceDDiagram.getSemanticOrdering().getEventEnds(), sequenceDDiagram.getGraphicalOrdering().getEventEnds()));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Resize() throws Exception {
        test_Creation();

        editor.click(0, 0);
        manualRefresh();

        Rectangle boundsA0 = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle boundsA1 = getExecutionScreenBounds(LIFELINE_A, 1);
        int deltaExecutionVerticalPosition = boundsA1.y - boundsA0.y;

        // Resize up
        editor.click(boundsA0.getLocation());
        Point expectedTranslation = new Point(0, -50);
        CheckResize cR = new CheckResize(LIFELINE_A, 0, expectedTranslation, editor);
        editor.drag(boundsA0.getTop(), boundsA0.getTop().getCopy().getTranslated(expectedTranslation));
        bot.waitUntil(cR);

        // Expected effect
        boundsA0.translate(expectedTranslation);
        boundsA0.resize(0, expectedTranslation.getNegated().y);
        deltaExecutionVerticalPosition += -expectedTranslation.y;

        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);

        // Validates the delta of execution position
        assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

        validateOrdering(4);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Big_Resize_From_Top() throws Exception {
        doCreateB0(300, ZoomLevel.ZOOM_100.getAmount());
        Rectangle boundsB0 = getExecutionScreenBounds(LIFELINE_B, 0);

        int m1y = boundsB0.y + 10;
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_B, m1y, LIFELINE_A, m1y);

        assertMessageVerticalPosition(FIRST_MESSAGE, m1y);

        // Resize up
        editor.click(boundsB0.getLocation());
        Point expectedTranslation = new Point(0, -150);
        CheckResize cR = new CheckResize(LIFELINE_B, 0, expectedTranslation, editor);
        editor.drag(boundsB0.getTop(), boundsB0.getTop().getCopy().getTranslated(expectedTranslation));
        bot.waitUntil(cR);

        // Expected effect
        boundsB0.translate(expectedTranslation);
        boundsB0.resize(0, expectedTranslation.getNegated().y);

        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);
        assertMessageVerticalPosition(FIRST_MESSAGE, m1y);

        // Resize down
        editor.click(boundsB0.getLocation());
        expectedTranslation = expectedTranslation.getNegated();
        cR = new CheckResize(LIFELINE_B, 0, expectedTranslation, editor);
        editor.drag(boundsB0.getTop(), boundsB0.getTop().getCopy().getTranslated(expectedTranslation));
        bot.waitUntil(cR);

        // Expected effect
        boundsB0.translate(expectedTranslation);
        boundsB0.resize(0, expectedTranslation.getNegated().y);

        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);
        assertMessageVerticalPosition(FIRST_MESSAGE, m1y);

        validateOrdering(4);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Big_Resize_From_Bottom() throws Exception {
        doCreateB0(150, ZoomLevel.ZOOM_100.getAmount());
        Rectangle boundsB0 = getExecutionScreenBounds(LIFELINE_B, 0);

        int m1y = boundsB0.y + 10;
        createMessage(InteractionsConstants.READ_TOOL_ID, LIFELINE_B, m1y, LIFELINE_A, m1y);

        assertMessageVerticalPosition(FIRST_MESSAGE, m1y);

        // Resize down
        editor.click(boundsB0.getLocation());
        Dimension expectedResize = new Dimension(0, 150);
        CheckResize cR = new CheckResize(LIFELINE_B, 0, expectedResize, editor);
        editor.drag(boundsB0.getBottom(), boundsB0.getBottom().getCopy().getTranslated(expectedResize));
        bot.waitUntil(cR);

        // Expected effect
        boundsB0.resize(expectedResize);

        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);
        assertMessageVerticalPosition(FIRST_MESSAGE, m1y);

        // Resize up
        editor.click(boundsB0.getLocation());
        expectedResize = expectedResize.getNegated();
        cR = new CheckResize(LIFELINE_B, 0, expectedResize, editor);
        editor.drag(boundsB0.getBottom(), boundsB0.getBottom().getCopy().getTranslated(expectedResize));
        bot.waitUntil(cR);

        // Expected effect
        boundsB0.resize(expectedResize);

        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);
        assertMessageVerticalPosition(FIRST_MESSAGE, m1y);

        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_Resize_Ordering() {
        test_Creation2();

        editor.click(0, 0);
        manualRefresh();

        Rectangle boundsA0 = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle boundsA1 = getExecutionScreenBounds(LIFELINE_A, 1);
        Rectangle boundsB0 = getExecutionScreenBounds(LIFELINE_B, 0);
        int deltaExecutionVerticalPosition = boundsA1.y - boundsA0.y;

        // Move down the execution beyond the execution on lifeline B
        Point move = new Point(0, 150);
        editor.drag(boundsA0.getLocation(), boundsA0.getLocation().getCopy().getTranslated(move));

        // Expected effect
        boundsA0.translate(move);
        boundsA1.translate(move);

        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

        // Validates the delta of execution position
        assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

        validateOrdering(6);

        // Resize up the execution beyond the execution on lifeline B
        editor.click(boundsA0.getLocation());
        move = new Point(0, -150);
        CheckResize cR = new CheckResize(LIFELINE_A, 0, move, editor);
        editor.drag(boundsA0.getTop(), boundsA0.getTop().getCopy().getTranslated(move));
        bot.waitUntil(cR);

        // Expected effect
        boundsA0.translate(move);
        boundsA0.resize(0, move.getNegated().y);
        deltaExecutionVerticalPosition += 150;

        // Validates the position of the execution, it should be back at where
        // it has been created
        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

        // Validates the delta of execution position
        assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

        validateOrdering(6);

        // Move up the sub execution on lifeline A
        editor.drag(boundsA1.getLocation(), boundsA1.getLocation().getCopy().getTranslated(move));

        // Expected effect
        boundsA1.translate(move);
        deltaExecutionVerticalPosition -= 150;

        // Validates the position of the execution, it should be back at where
        // it has been created
        // Validates the position of the execution
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

        // Validates the delta of execution position
        assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

        validateOrdering(6);

        // Resize down the sub execution on lifeline A
        move = new Point(0, 125);
        editor.click(boundsA1.getLocation());
        editor.drag(boundsA1.getBottom(), boundsA1.getBottom().getCopy().getTranslated(move));

        // Expected effect
        boundsA1.resize(0, move.y);

        // Validates the position of the execution, it should be back at where
        // it has been created
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, boundsA0);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, boundsA1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsB0);

        // Validates the delta of execution position
        assertEquals("The delta between the execution is not as expected", deltaExecutionVerticalPosition, boundsA1.y - boundsA0.y);

        validateOrdering(6);

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
        int sA1 = sA0 + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        int sB0 = sA1 + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        int fB0 = sB0 + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT;
        int fA1 = fB0 + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;
        int fA0 = fA1 + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;

        Rectangle expectedA0 = new Rectangle(0, sA0, 0, fA0 - sA0);
        Rectangle expectedA1 = new Rectangle(0, sA1, 0, fA1 - sA1);
        Rectangle expectedB0 = new Rectangle(0, sB0, 0, fB0 - sB0);

        // Validate vertical position after arrange all
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, expectedA0, false);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, expectedA1, false);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, expectedB0, false);
    }

    private void doCreateA0A1(int yScreenExecA0, double zoom) {

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        // Creation of an execution
        createExecution(lifelineAPosition, yScreenExecA0);

        // Validates the position of the execution
        Rectangle execA0ScreenBounds = assertExecutionHasValidScreenBounds(LIFELINE_A, 0,
                new Rectangle(0, yScreenExecA0, LayoutConstants.DEFAULT_EXECUTION_WIDTH, (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);

        // Creation of an execution
        int yExecA1 = execA0ScreenBounds.getCenter().y;
        createExecution(lifelineAPosition, yExecA1);

        // Expected effect
        int executionA0HeightDelta = (int) (4 * LayoutConstants.EXECUTION_CHILDREN_MARGIN * zoom);
        execA0ScreenBounds.resize(0, executionA0HeightDelta);

        // Validates the position of the second execution
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, execA0ScreenBounds, false);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, new Rectangle(0, yExecA1, 0, (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
    }

    private void doCreateB0(int yScreenExecB0, double zoom) {
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of an execution on lifeline B
        createExecution(lifelineBPosition, yScreenExecB0);

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0,
                new Rectangle(0, yScreenExecB0, (int) (LayoutConstants.DEFAULT_EXECUTION_WIDTH * zoom), (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
    }

    /**
     * Test method.
     * 
     */
    public void disabled_test_Undo_Redo() {
        test_ArrangeAll();

        // Undo Arrange All
        bot.menu("Edit").menu("Undo Arrange All").click();

        Point firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 0).getCopy();
        Dimension firstExecutionDimension = getExecutionScreenDimension(LIFELINE_A, 0).getCopy();
        Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 1).getCopy();
        Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_A, 1).getCopy();

        // Undo Resize sub execution
        bot.menu("Edit").menu("Undo Resize").click();
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " has not the expected height", secondExecutionDimension.height - 125,
                getExecutionScreenDimension(LIFELINE_A, 1).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_A, 1).getCopy().width);

        // Undo Move move sub execution
        bot.menu("Edit").menu("Undo Move Element").click();
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_A, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_A, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " is not at the expected vertical position", secondExecutionScreenPosition.y + 150,
                getExecutionScreenPosition(LIFELINE_A, 1).y);

        // Undo Resize main execution on lifeline A
        bot.menu("Edit").menu("Undo Resize").click();
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " has not the expected height", firstExecutionDimension.height - 150,
                getExecutionScreenDimension(LIFELINE_A, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " has not the expected height", secondExecutionDimension.height - 125,
                getExecutionScreenDimension(LIFELINE_A, 1).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_A, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_A, 1).getCopy().width);

        // Undo Move move main execution on lifeline A
        bot.menu("Edit").menu("Undo Move Element").click();
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_A, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_A + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_A, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_A, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_A + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_A, 1).y);

        // Undo add execution
        bot.menu("Edit").menu("Undo Do Command").click();
        bot.menu("Edit").menu("Undo Execution").click();

        assertNotNull("The execution index 0 on lifeline " + LIFELINE_A + " has not been found", getExecutionScreenPosition(LIFELINE_A, 0));
        assertNotNull("The execution index 1 on lifeline " + LIFELINE_A + " has not been found", getExecutionScreenPosition(LIFELINE_A, 1));
        assertNull(getExecutionScreenPosition(LIFELINE_B, 0));

        // Undo add execution
        bot.menu("Edit").menu(UNDO_LAYOUT_COMMAND).click();
        bot.menu("Edit").menu(UNDO_LAYOUT_COMMAND).click();
        bot.menu("Edit").menu("Undo Execution").click();

        assertNotNull("The execution index 0 on lifeline " + LIFELINE_A + " has not been found", getExecutionScreenPosition(LIFELINE_A, 0));
        assertNull(getExecutionScreenPosition(LIFELINE_A, 1));
        assertNull(getExecutionScreenPosition(LIFELINE_B, 0));

        // Undo add execution
        bot.menu("Edit").menu(UNDO_LAYOUT_COMMAND).click();
        bot.menu("Edit").menu(UNDO_LAYOUT_COMMAND).click();
        bot.menu("Edit").menu("Undo Execution").click();
        assertNull(getExecutionScreenPosition(LIFELINE_A, 0));
        assertNull(getExecutionScreenPosition(LIFELINE_A, 1));
        assertNull(getExecutionScreenPosition(LIFELINE_B, 0));

        // Redo add execution
        bot.menu("Edit").menu("Redo Execution").click();

        // TODO FIXME find why there is nothing in the command stack after Redo
        // Execution
    }

    /**
     * Test move execution in combinedFragment and on life line.
     * <p>
     * Step 1 : Move execution in combinedFragment's title. This operation is forbidden.
     * <p>
     * Step 2 : Move execution in combinedFragment
     * <p>
     * Step 3 : Move execution under combinedFragment
     * <p>
     * Step 4 : Move execution in combinedFragment (from under combinedFragment to combinedFragment)
     */
    public void test_Move_In_CombinedFragment() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 200);
        Point pointB = new Point(positionXLifeLineB, 200);

        // Create execution on lifeLine B
        SWTBotGefEditPart executionB = createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_B), 130)).get();
        Rectangle executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution position is wrong", 130, executionBoundsB.getTop().y);
        // Create combined fragment on life line A & B
        SWTBotGefEditPart combinedFragmentAB = createCombinedFragmentWithResult(pointA, pointB);
        Rectangle combinedFragmentBoundsAB = editor.getBounds(combinedFragmentAB);
        assertEquals("the combinedFragment position is wrong", 200, combinedFragmentBoundsAB.getTop().y);

        // Drag execution in combined fragment (it's forbidden)
        editor.drag(executionB, combinedFragmentBoundsAB.getCenter().x, combinedFragmentBoundsAB.getTop().y + 5);
        bot.sleep(500);

        executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution must not be moved", 130, executionBoundsB.getTop().y);

        // Drag execution to combined fragment
        ICondition done = new OperationDoneCondition();
        editor.drag(executionB, combinedFragmentBoundsAB.getCenter().x, combinedFragmentBoundsAB.getCenter().y);
        bot.waitUntil(done);

        executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution must be placed in combined fragment", combinedFragmentBoundsAB.getCenter().y, executionBoundsB.getTop().y);

        // Drag execution under combined fragment
        done = new OperationDoneCondition();
        editor.drag(executionB, combinedFragmentBoundsAB.getCenter().x, combinedFragmentBoundsAB.getBottom().y + 20);
        bot.waitUntil(done);

        executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution must be placed under combined fragment", combinedFragmentBoundsAB.getBottom().y + 20, executionBoundsB.getTop().y);
        // Drag execution in combined fragment
        done = new OperationDoneCondition();
        editor.drag(executionB, combinedFragmentBoundsAB.getCenter().x, combinedFragmentBoundsAB.getCenter().y);
        bot.waitUntil(done);

        executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution must be placed in combined fragment", combinedFragmentBoundsAB.getCenter().y, executionBoundsB.getTop().y);
    }

    /**
     * Test move execution in interaction use. This action is forbidden.
     */
    public void test_Move_In_InteractionUse_Forbidden() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        int positionXLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXLifeLineB = getLifelineScreenX(LIFELINE_B) + 20;
        Point pointA = new Point(positionXLifeLineA, 200);
        Point pointB = new Point(positionXLifeLineB, 200);
        // Create execution on lifeLine B
        SWTBotGefEditPart executionB = createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_B), 130)).get();
        Rectangle executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution position is wrong", 130, executionBoundsB.getTop().y);
        // Create interaction use on life line A & B
        SWTBotGefEditPart interactionUseAB = createInteractionUseWithResult(pointA, pointB);
        Rectangle interactionUseBoundsAB = editor.getBounds(interactionUseAB);
        assertEquals("the interactionUse position is wrong", 200, interactionUseBoundsAB.getTop().y);

        // Drag execution in combined fragment (it's forbidden)
        editor.drag(executionB, interactionUseBoundsAB.getCenter().x, interactionUseBoundsAB.getCenter().y);
        bot.sleep(500);

        executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution must not be moved", 130, executionBoundsB.getTop().y);
    }

    /**
     * Test resize execution simple.
     * <p>
     * Step 1 : Resize execution overlap first execution. Execution includes the execution overlap.
     * <p>
     * Step 2 : Resize execution overlap third execution. Execution include the execution overlap.
     * <p>
     * Step 3 : Resize execution on the first execution. This action is forbidden.
     * <p>
     * Step 4 : Resize execution on the third execution.
     */
    public void test_Resize_Execution_Simple() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        // Create first execution on lifeLine A
        createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 150)).get();
        Rectangle firstExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 150, 0, 30), false);
        // Create second execution on lifeLine A
        SWTBotGefEditPart secondExecutionA = createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 250)).get();
        Rectangle secondExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 1, new Rectangle(0, 250, 0, 30), false);
        // Create third execution on lifeLine A
        createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 350)).get();
        Rectangle thirdExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 2, new Rectangle(0, 350, 0, 30), false);

        // Resize execution overlap first execution. Second execution include
        // first execution.
        ICondition done = new OperationDoneCondition();
        editor.select(secondExecutionA);
        editor.drag(secondExecutionBoundsA.getTop(), firstExecutionBoundsA.getCenter().x, firstExecutionBoundsA.getTop().y - 15);
        bot.waitUntil(done);

        Rectangle firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x + 15, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        Rectangle secondExecutionBoundsAResize = new Rectangle(secondExecutionBoundsA.x, firstExecutionBoundsA.y - 15, secondExecutionBoundsA.width,
                secondExecutionBoundsA.getBottom().y - (firstExecutionBoundsA.getTop().y - 15));
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, secondExecutionBoundsAResize, true);

        // Undo to return to begin state
        undo();
        // Resize execution overlap third execution. The second execution
        // include third execution
        done = new OperationDoneCondition();
        editor.select(secondExecutionA);
        editor.drag(secondExecutionBoundsA.getBottom(), thirdExecutionBoundsA.getCenter().x, thirdExecutionBoundsA.getBottom().y + 15);
        bot.waitUntil(done);

        Rectangle thirdExecutionBoundsAResize = new Rectangle(thirdExecutionBoundsA.x + 15, thirdExecutionBoundsA.y, thirdExecutionBoundsA.width, thirdExecutionBoundsA.height);
        secondExecutionBoundsAResize = new Rectangle(secondExecutionBoundsA.x, secondExecutionBoundsA.y, secondExecutionBoundsA.width,
                (thirdExecutionBoundsA.getBottom().y + 15) - secondExecutionBoundsA.getTop().y);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 2, thirdExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, secondExecutionBoundsAResize, true);

        // Undo to return to begin state
        undo();

        // Resize execution on first execution (on top). This action is
        // forbidden
        editor.select(secondExecutionA);
        editor.drag(secondExecutionBoundsA.getTop(), firstExecutionBoundsA.getCenter().x, firstExecutionBoundsA.getTop().y);
        bot.sleep(500);

        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsA, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, secondExecutionBoundsA, true);

        // Resize execution on first execution (on center). This action is
        // forbidden
        editor.select(secondExecutionA);
        editor.drag(secondExecutionBoundsA.getTop(), firstExecutionBoundsA.getCenter().x, firstExecutionBoundsA.getCenter().y);
        bot.sleep(500);

        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsA, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, secondExecutionBoundsA, true);

        // Resize execution on third execution (on center). This action must be
        // move third execution.
        done = new OperationDoneCondition();
        editor.select(secondExecutionA);
        editor.drag(secondExecutionBoundsA.getBottom(), thirdExecutionBoundsA.getCenter().x, thirdExecutionBoundsA.getCenter().y);
        bot.waitUntil(done);

        // The value 20 correspond to half part execution + 5
        thirdExecutionBoundsAResize = new Rectangle(thirdExecutionBoundsA.x, thirdExecutionBoundsA.y + 20, thirdExecutionBoundsA.width, thirdExecutionBoundsA.height);
        secondExecutionBoundsAResize = new Rectangle(secondExecutionBoundsA.x, secondExecutionBoundsA.y, secondExecutionBoundsA.width,
                (thirdExecutionBoundsA.getCenter().y) - secondExecutionBoundsA.getTop().y);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 2, thirdExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, secondExecutionBoundsAResize, true);

        // Undo to return to begin state
        undo();

        // Resize execution on third execution (on the bottom). This action is
        // forbidden.
        editor.select(secondExecutionA);
        editor.drag(secondExecutionBoundsA.getBottom(), thirdExecutionBoundsA.getCenter().x, thirdExecutionBoundsA.getBottom().y);
        bot.sleep(500);

        thirdExecutionBoundsAResize = new Rectangle(thirdExecutionBoundsA.x, thirdExecutionBoundsA.y, thirdExecutionBoundsA.width, thirdExecutionBoundsA.height);
        secondExecutionBoundsAResize = new Rectangle(secondExecutionBoundsA.x, secondExecutionBoundsA.y, secondExecutionBoundsA.width, secondExecutionBoundsA.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 2, thirdExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, secondExecutionBoundsAResize, true);
    }

    /**
     * Test resize execution with sync call.
     * <p>
     * Step 1 : Resize execution above the first sync call. The execution is in the sync call
     * <p>
     * Step 2 : Resize execution in the first sync call. This action is forbidden
     * <p>
     * Step 3 : Resize execution in the second sync call. This action move sync call
     * <p>
     * Step 4 : Resize execution after the second sync call. The execution is in the sync call
     */
    public void test_Resize_Execution_With_Sync_Call() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        // Create first sync call message on lifeLine A
        ICondition done = new OperationDoneCondition();
        createSyncCall(new Point(getLifelineScreenX(LIFELINE_A), 150), new Point(getLifelineScreenX(LIFELINE_B), 150));
        bot.waitUntil(done);

        // Create first execution on lifeLine A
        SWTBotGefEditPart firstExecutionA = createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 250)).get();
        Rectangle firstExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 250, 0, 30), false);

        // Create second sync call message on lifeLine A
        done = new OperationDoneCondition();
        createSyncCall(new Point(getLifelineScreenX(LIFELINE_A), 350), new Point(getLifelineScreenX(LIFELINE_B), 350));
        bot.waitUntil(done);

        // Resize execution above the first sync call
        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);

        Rectangle firstSyncCall = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle secondSyncCall = getExecutionScreenBounds(LIFELINE_B, 1);

        done = new OperationDoneCondition();
        editor.drag(firstExecutionBoundsA.getTop(), firstSyncCall.getCenter().x, firstSyncCall.getTop().y - 15);
        bot.waitUntil(done);

        Rectangle firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstSyncCall.y - 15, firstExecutionBoundsA.width,
                firstExecutionBoundsA.getBottom().y - (firstSyncCall.getTop().y - 15));
        Rectangle firstExecutionSyncCall = new Rectangle(firstSyncCall.x, firstSyncCall.y, firstSyncCall.width, firstSyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstExecutionSyncCall, true);

        // Undo to return to begin state
        undo();

        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);
        // Resize operation in the first sync call. This operation is forbidden
        editor.drag(firstExecutionBoundsA.getTop(), firstSyncCall.getCenter().x, firstSyncCall.getCenter().y);

        firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        firstExecutionSyncCall = new Rectangle(firstSyncCall.x, firstSyncCall.y, firstSyncCall.width, firstSyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstExecutionSyncCall, true);

        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);

        // Resize execution in the second sync call
        done = new OperationDoneCondition();
        editor.drag(firstExecutionBoundsA.getBottom(), secondSyncCall.getCenter().x, secondSyncCall.getCenter().y);
        bot.waitUntil(done);

        firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, secondSyncCall.getCenter().y - firstExecutionBoundsA.getTop().y);
        Rectangle secondExecutionSyncCall = new Rectangle(secondSyncCall.x, secondSyncCall.y + (secondSyncCall.height / 2) + 5, secondSyncCall.width, secondSyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, secondExecutionSyncCall, true);

        // Undo to return to begin state
        undo();

        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);

        // Resize execution after the second sync call
        done = new OperationDoneCondition();
        editor.drag(firstExecutionBoundsA.getBottom(), secondSyncCall.getCenter().x, secondSyncCall.getBottom().y + 15);
        bot.waitUntil(done);

        firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width,
                (secondSyncCall.getBottom().y + 15) - firstExecutionBoundsA.getTop().y);
        secondExecutionSyncCall = new Rectangle(secondSyncCall.x, secondSyncCall.y, secondSyncCall.width, secondSyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, secondExecutionSyncCall, true);
    }

    /**
     * Test resize execution with async call.
     * <p>
     * Step 1 : Resize execution under the first message async call. The execution is in the async call
     * <p>
     * Step 2 : Resize execution on the first message async call. This action is forbidden.
     * <p>
     * Step 3 : Resize execution under the second message async call. The execution is in the async call
     * <p>
     * Step 4 : Resize execution on the second message async call. This action is forbidden
     */
    public void test_Resize_Execution_With_Async_Call() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        // Create first async call message on lifeLine A
        ICondition done = new OperationDoneCondition();
        createAsyncCall(new Point(getLifelineScreenX(LIFELINE_A), 150), new Point(getLifelineScreenX(LIFELINE_B), 150));
        bot.waitUntil(done);

        // Create first execution on lifeLine A
        SWTBotGefEditPart firstExecutionA = createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 250)).get();
        Rectangle firstExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 250, 0, 30), false);

        // Create second async call message on lifeLine A
        done = new OperationDoneCondition();
        createAsyncCall(new Point(getLifelineScreenX(LIFELINE_A), 350), new Point(getLifelineScreenX(LIFELINE_B), 350));
        bot.waitUntil(done);

        // Resize execution under the first async call
        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);

        Rectangle firstAsyncCall = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle secondAsyncCall = getExecutionScreenBounds(LIFELINE_B, 1);

        done = new OperationDoneCondition();
        editor.drag(firstExecutionBoundsA.getTop(), firstAsyncCall.getCenter().x, firstAsyncCall.getCenter().y);
        bot.waitUntil(done);

        Rectangle firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstAsyncCall.getCenter().y, firstExecutionBoundsA.width,
                firstExecutionBoundsA.getBottom().y - (firstAsyncCall.getCenter().y));
        Rectangle firstExecutionSyncCall = new Rectangle(firstAsyncCall.x, firstAsyncCall.y, firstAsyncCall.width, firstAsyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstExecutionSyncCall, true);

        // Undo to return to begin state
        undo();

        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);
        // Resize operation on the first message sync call. This operation is
        // forbidden
        editor.drag(firstExecutionBoundsA.getTop(), firstAsyncCall.getCenter().x, firstAsyncCall.getTop().y);

        firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        firstExecutionSyncCall = new Rectangle(firstAsyncCall.x, firstAsyncCall.y, firstAsyncCall.width, firstAsyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstExecutionSyncCall, true);

        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);

        // Resize execution under the second async call
        done = new OperationDoneCondition();
        editor.drag(firstExecutionBoundsA.getBottom(), secondAsyncCall.getCenter().x, secondAsyncCall.getCenter().y);
        bot.waitUntil(done);

        firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, secondAsyncCall.getCenter().y - firstExecutionBoundsA.getTop().y);
        Rectangle secondExecutionAsyncCall = new Rectangle(secondAsyncCall.x, secondAsyncCall.y, secondAsyncCall.width, secondAsyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, secondExecutionAsyncCall, true);

        // Undo to return to begin state
        undo();

        done = new CheckSelectedCondition(editor, firstExecutionA.part());
        editor.select(firstExecutionA);
        bot.waitUntil(done);
        // Resize execution on the second message sync call, this action is
        // forbidden
        editor.drag(firstExecutionBoundsA.getBottom(), secondAsyncCall.getCenter().x, secondAsyncCall.getTop().y);

        firstExecutionBoundsAResize = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        secondExecutionAsyncCall = new Rectangle(secondAsyncCall.x, secondAsyncCall.y, secondAsyncCall.width, secondAsyncCall.height);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecutionBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, secondExecutionAsyncCall, true);
    }

    /**
     * Test resize sync call execution with 2 executions.
     * <p>
     * Step 1 : Resize sync call execution above the first execution.
     * <p>
     * Step 2 : Resize sync call execution between the limit of the first execution. This action is forbidden.
     * <p>
     * Step 3 : Resize sync call execution under the second execution.
     * <p>
     * Step 4 : Resize sync call execution between the limit of the second execution. This action move the second
     * execution down.
     */
    public void test_Resize_Sync_Call_With_2_Executions() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();
        // Create first execution on lifeLine A
        createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 150)).get();
        Rectangle firstExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 0, new Rectangle(0, 150, 0, 30), false);
        // Create first sync call message on lifeLine A
        createSyncCall(new Point(getLifelineScreenX(LIFELINE_A), 250), new Point(getLifelineScreenX(LIFELINE_B), 250));
        // Create second execution on lifeLine A
        createExecutionWithResult(new Point(getLifelineScreenX(LIFELINE_A), 350)).get();
        Rectangle secondExecutionBoundsA = assertExecutionHasValidScreenBounds(LIFELINE_A, 1, new Rectangle(0, 350, 0, 30), false);
        // Resize sync call execution above the first execution
        Rectangle firstSyncCall = getExecutionScreenBounds(LIFELINE_B, 0);
        ExecutionEditPart executionE2 = getExecutionEditPart(LIFELINE_B, 0);

        ICondition done = new OperationDoneCondition();
        editor.select(getBotEditPart(executionE2));
        editor.drag(firstSyncCall.getTop(), firstExecutionBoundsA.getCenter().x, firstExecutionBoundsA.getTop().y - 15);
        bot.waitUntil(done);

        Rectangle firstSyncCallBoundsAResize = new Rectangle(firstSyncCall.x, firstExecutionBoundsA.getTop().y - 15, firstExecutionBoundsA.width,
                firstSyncCall.getBottom().y - (firstExecutionBoundsA.getTop().y - 15));
        Rectangle firstExecution = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstSyncCallBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecution, true);

        // Undo to return to begin state
        undo();

        editor.select(getBotEditPart(executionE2));
        // Resize sync call execution between the limit of the first execution.
        // This action is forbidden.
        editor.drag(firstSyncCall.getTop(), firstExecutionBoundsA.getCenter().x, firstExecutionBoundsA.getCenter().y);
        bot.sleep(500);

        firstSyncCallBoundsAResize = new Rectangle(firstSyncCall.x, firstSyncCall.y, firstSyncCall.width, firstSyncCall.height);
        firstExecution = new Rectangle(firstExecutionBoundsA.x, firstExecutionBoundsA.y, firstExecutionBoundsA.width, firstExecutionBoundsA.height);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstSyncCallBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 0, firstExecution, true);

        editor.select(getBotEditPart(executionE2));
        // Resize sync call execution under the second execution
        done = new OperationDoneCondition();
        editor.drag(firstSyncCall.getBottom(), secondExecutionBoundsA.getCenter().x, secondExecutionBoundsA.getBottom().y + 15);
        bot.waitUntil(done);

        firstSyncCallBoundsAResize = new Rectangle(firstSyncCall.x, firstSyncCall.y, firstSyncCall.width, (secondExecutionBoundsA.getBottom().y + 15) - firstSyncCall.getTop().y);
        Rectangle secondExecution = new Rectangle(secondExecutionBoundsA.x, secondExecutionBoundsA.y, secondExecutionBoundsA.width, secondExecutionBoundsA.height);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstSyncCallBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, secondExecution, true);

        // Undo to return to begin state
        undo();
        editor.select(getBotEditPart(executionE2));
        // Resize sync call execution between the limit of the second execution.
        // This action move the second execution down.
        done = new OperationDoneCondition();
        editor.drag(firstSyncCall.getBottom(), secondExecutionBoundsA.getCenter().x, secondExecutionBoundsA.getCenter().y);
        bot.waitUntil(done);

        firstSyncCallBoundsAResize = new Rectangle(firstSyncCall.x, firstSyncCall.y, firstSyncCall.width, secondExecutionBoundsA.getCenter().y - firstSyncCall.y);
        secondExecution = new Rectangle(secondExecutionBoundsA.x, secondExecutionBoundsA.y + (secondExecutionBoundsA.height / 2) + 5, secondExecutionBoundsA.width, secondExecutionBoundsA.height);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, firstSyncCallBoundsAResize, true);
        assertExecutionHasValidScreenBounds(LIFELINE_A, 1, secondExecution, true);
    }

    /**
     * Test move execution in combinedFragment and on life line.
     * <p>
     * Step 1 : Resize execution in limits of Operand upper bounds. This operation is forbidden.
     * <p>
     * Step 2 : Resize execution in combinedFragment
     */
    public void test_Resize_In_CombinedFragment() {
        editor.reveal(LIFELINE_A);
        arrangeAll();
        editor.maximize();

        int positionXBeforeLifeLineA = getLifelineScreenX(LIFELINE_A) - 20;
        int positionXAfterLifeLineA = getLifelineScreenX(LIFELINE_A) + 20;
        Point pointBefore = new Point(positionXBeforeLifeLineA, 200);
        Point pointAfter = new Point(positionXAfterLifeLineA, 200);

        // Create combined fragment on life line A
        SWTBotGefEditPart combinedFragmentA = createCombinedFragmentWithResult(pointBefore, pointAfter);
        Rectangle combinedFragmentBoundsA = editor.getBounds(combinedFragmentA);
        assertEquals("the combinedFragment position is wrong", 200, combinedFragmentBoundsA.getTop().y);

        // Create execution in CombinedFragment
        SWTBotGefEditPart executionB = createExecutionWithResult(combinedFragmentBoundsA.getCenter().x, combinedFragmentBoundsA.getCenter().y).get();
        Rectangle executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution position is wrong", combinedFragmentBoundsA.getCenter().y, executionBoundsB.getTop().y);

        // Resize execution in combined fragment, just before the lower bound of
        // the lifeline, just when grand parent Combined Fragment starts to be
        // authorized
        int limitAuthorized = combinedFragmentBoundsA.getTop().y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        ICondition done = new OperationDoneCondition();
        editor.drag(executionBoundsB.getTop(), combinedFragmentBoundsA.getCenter().x, limitAuthorized);
        bot.waitUntil(done);

        executionBoundsB = editor.getBounds(executionB);
        assertEquals("the execution must be resized in combined fragment", limitAuthorized, executionBoundsB.getTop().y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        instanceRoleEditPartABot = null;
        instanceRoleEditPartABounds = null;

        super.tearDown();
    }
}
