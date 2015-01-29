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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.LayoutEditPartConstants;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;

/**
 * 
 * 
 */
public class SequenceExecutionMessageToSelfReparentTest extends AbstractDefaultModelSequenceTests {

    /**
     * Test method. Reparent a message (that contains a reflexive message).
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Reparent_Sync_Call_With_Sub_Reflexive() throws Exception {
        maximizeEditor(editor);
        arrangeAll();

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        populateDiagramBeforeReparentTest();

        Rectangle boundsE1 = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle boundsM1 = new Rectangle(boundsE1.getTop(), new Dimension(0, 0));
        Rectangle boundsM2 = new Rectangle(boundsE1.getBottom(), new Dimension(0, 0));

        Rectangle boundsE2 = getExecutionScreenBounds(LIFELINE_B, 1);
        Rectangle boundsM3 = new Rectangle(boundsE2.getTop(), new Dimension(0, 0));
        Rectangle boundsM4 = new Rectangle(boundsE2.getBottom(), new Dimension(0, 0));

        Rectangle boundsE3 = getExecutionScreenBounds(LIFELINE_B, 2);
        Rectangle boundsM5 = new Rectangle(boundsE3.getTop().getTranslated(0, -LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP), new Dimension(0,
                LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP));
        Rectangle boundsM6 = new Rectangle(boundsE3.getBottom(), new Dimension(0, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP));

        Point dragTarget = boundsE1.getBottom().getCopy().getTranslated(0, 20);
        int verticalDelta = dragTarget.y - boundsE2.getTop().y;
        // Move the main sync call to have its invocation message on range of
        // where was the execution of its sub reflexive sync call
        // This should not reconnect
        editor.drag(boundsE2.getCenter(), boundsE2.getCenter().getCopy().getTranslated(0, verticalDelta));

        // Expected bounds after the drag
        int horizontalDelta = -(boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width);
        boundsE2.translate(horizontalDelta, verticalDelta);
        boundsE3.translate(horizontalDelta, verticalDelta);
        boundsM3.translate(0, verticalDelta);
        boundsM3.resize(horizontalDelta, 0);
        boundsM4.translate(0, verticalDelta);
        boundsM4.resize(horizontalDelta, 0);
        boundsM5.translate(horizontalDelta, verticalDelta);
        boundsM6.translate(horizontalDelta, verticalDelta);

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 2, boundsE3);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, false);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, false);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, boundsM5, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 2, boundsM6, false, false);
        validateOrdering(12);

        // Move the message back where it was
        editor.drag(boundsE2.getCenter(), boundsE2.getCenter().getCopy().getTranslated(0, -verticalDelta));

        // Expected bounds after the drag
        boundsE2.translate(-horizontalDelta, -verticalDelta);
        boundsE3.translate(-horizontalDelta, -verticalDelta);
        boundsM3.translate(0, -verticalDelta);
        boundsM3.resize(-horizontalDelta, 0);
        boundsM4.translate(0, -verticalDelta);
        boundsM4.resize(-horizontalDelta, 0);
        boundsM5.translate(-horizontalDelta, -verticalDelta);
        boundsM6.translate(-horizontalDelta, -verticalDelta);

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 2, boundsE3);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, false);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, false);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, boundsM5, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 2, boundsM6, false, false);
        validateOrdering(12);
    }

    /**
     * Test method. Reparent a message (that contains a reflexive message) with
     * expansion zone.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Reparent_Sync_Call_With_Sub_Reflexive_With_Expansion_Zone() throws Exception {
        maximizeEditor(editor);
        arrangeAll();

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        populateDiagramBeforeReparentTest();

        Rectangle boundsE1 = getExecutionScreenBounds(LIFELINE_B, 0);
        Rectangle boundsM1 = new Rectangle(boundsE1.getTop(), new Dimension(0, 0));
        Rectangle boundsM2 = new Rectangle(boundsE1.getBottom(), new Dimension(0, 0));

        Rectangle boundsE2 = getExecutionScreenBounds(LIFELINE_B, 1);
        Rectangle boundsM3 = new Rectangle(boundsE2.getTop(), new Dimension(0, 0));
        Rectangle boundsM4 = new Rectangle(boundsE2.getBottom(), new Dimension(0, 0));

        Rectangle boundsE3 = getExecutionScreenBounds(LIFELINE_B, 2);
        Rectangle boundsM5 = new Rectangle(boundsE3.getTop().getTranslated(0, -LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP), new Dimension(0,
                LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP));
        Rectangle boundsM6 = new Rectangle(boundsE3.getBottom(), new Dimension(0, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP));

        Point dragTarget = boundsE1.getBottom().getCopy().getTranslated(0, 20);
        int verticalDelta = dragTarget.y - boundsE2.getTop().y;
        // Move the main sync call to have its invocation message on range of
        // where was the execution of its sub reflexive sync call
        // This should not reconnect
        editor.drag(boundsE2.getCenter(), boundsE2.getCenter().getCopy().getTranslated(0, verticalDelta));

        // Expected bounds after the drag
        int horizontalDelta = -(boundsE1.width - LayoutEditPartConstants.EXECUTION_BORDER_ITEM_OFFSET.width);
        boundsE2.translate(horizontalDelta, verticalDelta);
        boundsE3.translate(horizontalDelta, verticalDelta);
        boundsM3.translate(0, verticalDelta);
        boundsM3.resize(horizontalDelta, 0);
        boundsM4.translate(0, verticalDelta);
        boundsM4.resize(horizontalDelta, 0);
        boundsM5.translate(horizontalDelta, verticalDelta);
        boundsM6.translate(horizontalDelta, verticalDelta);

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 2, boundsE3);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, false);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, false);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, boundsM5, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 2, boundsM6, false, false);
        validateOrdering(12);

        dragTarget = boundsE1.getBottom().getCopy().getTranslated(0, -20);
        verticalDelta = dragTarget.y - boundsE2.getTop().y;
        // Move the message back where it was
        editor.drag(boundsE2.getCenter(), boundsE2.getCenter().getCopy().getTranslated(0, verticalDelta));

        // Expected bounds after the drag
        boundsE1.resize(0, boundsE2.height);
        boundsE2.translate(-horizontalDelta, verticalDelta);
        boundsE3.translate(-horizontalDelta, verticalDelta);
        boundsM2.setLocation(boundsM2.x, boundsE1.getBottom().y);
        boundsM3.translate(0, verticalDelta);
        boundsM3.resize(-horizontalDelta, 0);
        boundsM4.translate(0, verticalDelta);
        boundsM4.resize(-horizontalDelta, 0);
        boundsM5.translate(-horizontalDelta, verticalDelta);
        boundsM6.translate(-horizontalDelta, verticalDelta);

        assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertExecutionHasValidScreenBounds(LIFELINE_B, 2, boundsE3);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false, false);
        assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, false);
        assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B, boundsM5, true, false);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 2, boundsM6, false, false);
        validateOrdering(12);
    }

    private void populateDiagramBeforeReparentTest() {
        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, 150, lifelineBPosition, 150);

        // Expected bounds after message creation
        Rectangle boundsE1 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, 150, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        Rectangle boundsM1 = assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, new Rectangle(0, 150, 0, 0), true, false);
        Rectangle boundsM2 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, 150 + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT, 0, 0), false, false);

        int messageDelta = 10;
        int m3verticalTarget = boundsE1.y + messageDelta;
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, m3verticalTarget, lifelineBPosition, m3verticalTarget + 10);

        // Expected bounds after message creation
        int unknownGap = 3; // FIXME this delta must be done by the
        boundsE1.height += messageDelta + LayoutConstants.EXECUTION_CHILDREN_MARGIN + unknownGap;
        boundsM2.y = boundsE1.bottom();

        // Validate positions
        boundsE1 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1);
        boundsM1 = assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        boundsM2 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false);

        Rectangle boundsE2 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, m3verticalTarget, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        Rectangle boundsM3 = assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, new Rectangle(0, m3verticalTarget, 0, 0), true, false);
        Rectangle boundsM4 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, boundsE2.getBottom().y, 0, 0), false, false);

        int m5verticalTarget = boundsE2.y + messageDelta;
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineBPosition, m5verticalTarget, lifelineBPosition, m5verticalTarget + 10);

        // Expected effect
        int gap = messageDelta + 2 * LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP + LayoutConstants.EXECUTION_CHILDREN_MARGIN + unknownGap;
        boundsE2.height += gap;
        boundsE1.height += gap;
        boundsM4.y = boundsE2.bottom();
        boundsM2.y = boundsE1.bottom();

        // Validate positions
        boundsE1 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1);
        boundsM1 = assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, boundsM1, true);
        boundsM2 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, boundsM2, false);

        boundsE2 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        boundsM3 = assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL, boundsM3, true);
        boundsM4 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false);

        Rectangle boundsE3 = assertExecutionHasValidScreenBounds(LIFELINE_B, 2, new Rectangle(0, m5verticalTarget + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP, 0,
                LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
        Rectangle boundsM5 = assertNamedMessageHasValidScreenBounds(FIFTH_MESSAGE_SYNC_CALL_ON_LIFELINE_B,
                new Rectangle(0, m5verticalTarget, 0, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP), true, false);
        Rectangle boundsM6 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 2, new Rectangle(0, boundsE3.getBottom().y, 0, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP), false, false);
    }
}
