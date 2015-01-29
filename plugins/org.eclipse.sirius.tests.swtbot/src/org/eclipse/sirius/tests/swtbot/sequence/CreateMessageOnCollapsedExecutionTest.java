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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.collect.Iterables;

/**
 * 
 * @author jdupont
 */
public class CreateMessageOnCollapsedExecutionTest extends AbstractDefaultModelSequenceTests {

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);
    }

    /**
     * Create a message on Execution and check that the message position
     * correspond to origin and target click.
     */
    public void test_Create_Message_On_Execution() {
        do_test_Create_Message_On_Execution(false, InteractionsConstants.READ_TOOL_ID, FIRST_MESSAGE);
    }

    /**
     * Create a message on Execution parent, on a vertical range occupied by a
     * child execution and check that the message position correspond to origin
     * and target click.
     */
    public void test_Create_Message_On_Execution_Parent() {
        do_test_Create_Message_On_Execution_Parent(false, InteractionsConstants.READ_TOOL_ID, FIRST_MESSAGE, SECOND_MESSAGE);
    }

    /**
     * This test call the first and second test and create a third message on
     * collapse executions.
     */
    public void test_Create_Third_Message_On_Collpase_Execution() {
        do_test_Create_Message_On_Execution(true, InteractionsConstants.READ_TOOL_ID, FIRST_MESSAGE);
    }

    /**
     * Create a message Sync Call on Execution and check that the message
     * position correspond to origin and target click.
     */
    public void test_Create_Message_Sync_On_Execution() {
        do_test_Create_Message_On_Execution(false, InteractionsConstants.SYNC_CALL_TOOL_ID, FIRST_MESSAGE);

        Rectangle execA0 = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle execB0 = getExecutionScreenBounds(LIFELINE_B, 0);
        int expectedY = getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL) + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT;

        // Check complete bounds of return message -> start and end points.
        Rectangle expectedBounds = new Rectangle(execA0.right(), expectedY, execB0.x - execA0.right(), 0);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, expectedBounds, false);
    }

    /**
     * Create a SyncCall on Execution parent, on a vertical range occupied by a
     * child execution and check that the message position correspond to origin
     * and target click.
     */
    public void test_Create_Message_Sync_On_Execution_Parent() {
        do_test_Create_Message_On_Execution_Parent(false, InteractionsConstants.SYNC_CALL_TOOL_ID, FIRST_MESSAGE_SYNC_CALL, THIRD_MESSAGE_SYNC_CALL);

        Rectangle execA1 = getExecutionScreenBounds(LIFELINE_A, 1);
        Rectangle execB1 = getExecutionScreenBounds(LIFELINE_B, 1);
        int expectedY = getSequenceMessageScreenVerticalPosition(THIRD_MESSAGE_SYNC_CALL) + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT;

        // Check complete bounds of return message -> start and end points.
        Rectangle expectedBounds = new Rectangle(execA1.right(), expectedY, execB1.x - execA1.right(), 0);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, expectedBounds, false);

        Rectangle execA0 = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle execB0 = getExecutionScreenBounds(LIFELINE_B, 0);
        expectedY = getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL) + execB0.height;

        // Check complete bounds of return message -> start and end points.
        expectedBounds = new Rectangle(execA0.right(), expectedY, execB0.x - execA0.right(), 0);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, expectedBounds, false);
    }

    /**
     * This test call Sync Call the first and second test and create a third
     * message on collapse executions.
     */
    public void test_Create_Third_Message_Sync_On_Collapse_Execution() {
        do_test_Create_Message_On_Execution(true, InteractionsConstants.SYNC_CALL_TOOL_ID, FIRST_MESSAGE);

        Rectangle execA0 = getExecutionScreenBounds(LIFELINE_A, 0);
        Rectangle execB0 = getExecutionScreenBounds(LIFELINE_B, 0);
        int expectedY = getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE_SYNC_CALL) + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT;

        // Check complete bounds of return message -> start and end points.
        Rectangle expectedBounds = new Rectangle(execA0.right(), expectedY, execB0.x - execA0.right(), 0);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, expectedBounds, false);

        // Check complete bounds of execution and its collapsed state.
        DDiagramElement dde = getExecutionEditPart(LIFELINE_B, 0).resolveDiagramElement();
        assertTrue("The execution should be collapsed.", new DDiagramElementQuery(dde).isCollapsed());
        assertEquals("The element should have two graphical filter: sequence flag and collapse.", 2, dde.getGraphicalFilters().size());
        Iterable<CollapseFilter> collapseApplication = Iterables.filter(dde.getGraphicalFilters(), CollapseFilter.class);
        assertEquals("The element should have only one collapse application.", 1, Iterables.size(collapseApplication));
        assertEquals("Check the expanded bounds store in filter at creation.", LayoutConstants.DEFAULT_EXECUTION_WIDTH, collapseApplication.iterator().next().getWidth());
        assertEquals("Check the expanded bounds store in filter at creation.", LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT, collapseApplication.iterator().next().getHeight());

        Rectangle expectedExecB0 = new Rectangle(execB0.x, 210, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, expectedExecB0, true);

    }

    private void do_test_Create_Message_On_Execution(boolean collapse, String message_tool_id, String expected_message_name) {
        // Creation of an execution
        Point creationPoint = new Point(getLifelineScreenX(LIFELINE_A), 200);
        Option<SWTBotGefEditPart> execA0 = createExecutionWithResult(creationPoint.x, creationPoint.y);

        if (collapse) {
            // Switch outline to filters section
            bot.viewByTitle("Outline").setFocus();
            bot.viewByTitle("Outline").toolbarToggleButton("Filters").click();

            // Collapse execution
            changeCollapseFilterActivation();
        }

        // Retrieve the execution position
        Rectangle execution1Bounds = getExecutionScreenBounds((ExecutionEditPart) execA0.get().part());

        // Creation of a message
        Point start = new Point(execution1Bounds.getCenter().x, execution1Bounds.y + 10);
        Point end = new Point(getLifelineScreenX(LIFELINE_B), start.y);

        createAndCheckMessage(message_tool_id, start, end, expected_message_name);
    }

    /**
     * Create a message on Execution parent, on a vertical range occupied by a
     * child execution and check that the message position correspond to origin
     * and target click.
     */
    private void do_test_Create_Message_On_Execution_Parent(boolean collapse, String message_tool_id, String expected_msg1_name, String expected_msg2_name) {
        do_test_Create_Message_On_Execution(collapse, message_tool_id, expected_msg1_name);

        // Create execution son
        Rectangle execution1Bounds = getExecutionScreenBounds(getExecutionEditPart(LIFELINE_A, 0));
        Point creationPoint = new Point(execution1Bounds.getCenter());
        createExecution(creationPoint.x, creationPoint.y);

        // Retrieve the execution position
        Rectangle execution2Bounds = getExecutionScreenBounds(getExecutionEditPart(LIFELINE_A, 1));

        // Creation of a message
        assertFalse("The clicked point should be on the parent, but in the vertical range of the child.", execution2Bounds.contains(execution1Bounds.getCenter()));
        Point start = new Point(execution1Bounds.getCenter().x, execution2Bounds.y + 10);
        Point end = new Point(getLifelineScreenX(LIFELINE_B), start.y);

        createAndCheckMessage(message_tool_id, start, end, expected_msg2_name);

        // Check complete bounds -> start and end points.
        int expectedRight = InteractionsConstants.SYNC_CALL_TOOL_ID.equals(message_tool_id) ? getExecutionScreenBounds(LIFELINE_B, 1).x : getLifelineScreenX(LIFELINE_B);
        Rectangle expectedBounds = new Rectangle(execution2Bounds.right(), start.y, expectedRight - execution2Bounds.right(), 0);
        assertNamedMessageHasValidScreenBounds(expected_msg2_name, expectedBounds, true);
    }

    private void createAndCheckMessage(String message_tool_id, Point start, Point end, String expectedLabel) {
        createMessage(message_tool_id, start, end);

        // Validates the position
        assertMessageVerticalPosition(expectedLabel, start.y);
    }
}
