/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

public class SequenceExecutionMessageToSelfTest extends AbstractDefaultModelSequenceTests {

    private SWTBotGefEditPart instanceRoleBBot;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        instanceRoleBBot = editor.getEditPart(LIFELINE_B);
    }

    /**
     * Test of VP-1335. Create a sync call that contains a reflexive sync call.
     * Validate that it is possible to select all and delete without NPE.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Delete_Message_And_Sub_Reflexive_Messages() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        startToListenErrorLog(true, true);

        editor.setFocus();
        maximizeEditor(editor);
        try {
            arrangeAll();

            // Reveal A to scroll to the left
            editor.reveal(LIFELINE_A);

            // Calculate the X position of the center of lifelines A, B and
            // C
            int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
            int lifelineBPosition = getLifelineScreenX(LIFELINE_B);
            getLifelineScreenX(LIFELINE_C);

            int verticalPosition = 150;

            Point sourceLocation = new Point(lifelineAPosition, verticalPosition);
            Point targetLocation = new Point(lifelineBPosition, verticalPosition);

            CheckNumberOfDescendants checkDescendants = new CheckNumberOfDescendants(instanceRoleBBot.parent(), ExecutionEditPart.class, 1);
            createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, sourceLocation, targetLocation);
            bot.waitUntil(checkDescendants);

            SWTBotGefEditPart e1Bot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);

            SWTBotGefConnectionEditPart callMessageOnE1Bot = e1Bot.targetConnections().get(0);
            SWTBotGefConnectionEditPart returnMessageOfE1Bot = e1Bot.sourceConnections().get(0);

            // Expected bounds after message creation
            Rectangle boundsE1 = assertExecutionHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(0, verticalPosition, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);
            assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE_SYNC_CALL, new Rectangle(new Point(0, verticalPosition), new Point(0, verticalPosition)), true, false);
            assertReturnMessageHasValidScreenBounds(LIFELINE_B, 0, new Rectangle(new Point(0, verticalPosition + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT),
                    new Point(0, verticalPosition + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT)), false, false);

            // Create a sub sync call
            checkDescendants = new CheckNumberOfDescendants(instanceRoleBBot.parent(), ExecutionEditPart.class, 2);
            createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, targetLocation.getTranslated(0, LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP),
                    targetLocation.getTranslated(0, LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + 10));
            bot.waitUntil(checkDescendants);

            SWTBotGefEditPart e2Bot = e1Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);

            SWTBotGefConnectionEditPart callMessageOnE2Bot = e2Bot.targetConnections().get(0);
            SWTBotGefConnectionEditPart returnMessageOfE2Bot = e2Bot.sourceConnections().get(0);

            Rectangle boundsE2 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1,
                    new Rectangle(0, verticalPosition + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP, 0,
                            LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT),
                    false);
            Rectangle boundsM3 = assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_B,
                    new Rectangle(new Point(0, verticalPosition + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP),
                            new Point(0, verticalPosition + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP)),
                    true, false);
            Rectangle boundsM4 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1,
                    new Rectangle(
                            new Point(0,
                                    verticalPosition + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP
                                            + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT),
                            new Point(0, verticalPosition + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP
                                    + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP)),
                    false, false);

            // update E1
            boundsE1 = getExecutionScreenBounds(LIFELINE_B, 0);

            // Select all created messages
            ICondition checkSelected = CheckSelectedCondition.multipleSelection(editor, callMessageOnE1Bot.part(), returnMessageOfE1Bot.part(), e1Bot.part(), e2Bot.part(), callMessageOnE2Bot.part(),
                    returnMessageOfE2Bot.part());
            editor.select(e1Bot, callMessageOnE1Bot, returnMessageOfE1Bot, e2Bot, callMessageOnE2Bot, returnMessageOfE2Bot);
            bot.waitUntil(checkSelected);

            checkDescendants = new CheckNumberOfDescendants(instanceRoleBBot.parent(), ExecutionEditPart.class, 0);
            deleteSelectedElement();
            bot.waitUntil(checkDescendants);

            // Undo
            checkDescendants = new CheckNumberOfDescendants(instanceRoleBBot.parent(), ExecutionEditPart.class, 2);
            undo();
            bot.waitUntil(checkDescendants);

            // Expected bounds after delete undo
            assertExecutionHasValidScreenBounds(LIFELINE_B, 0, boundsE1, false);
            assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2, false);
            assertNamedMessageHasValidScreenBounds(THIRD_MESSAGE_SYNC_CALL_ON_LIFELINE_B, boundsM3, true, false);
            assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM4, false, false);

            // Redo
            checkDescendants = new CheckNumberOfDescendants(instanceRoleBBot.parent(), ExecutionEditPart.class, 0);
            bot.menu("Edit").menu("Redo Delete").click();
            bot.waitUntil(checkDescendants);
        } finally {
            editor.restore();
            assertFalse(doesAnErrorOccurs() || doesAWarningOccurs());
        }
    }

    @Override
    protected void tearDown() throws Exception {
        instanceRoleBBot = null;
        super.tearDown();
    }
}
