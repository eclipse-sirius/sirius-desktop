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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.junit.Assert;

/**
 * Test class for syncCall
 * 
 * @author smonnier, edugueperoux
 */
public class SyncCallMoveTest extends AbstractDefaultModelSequenceTests {

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleABot;

    private SWTBotGefEditPart instanceRoleBBot;

    private Rectangle instanceRoleABounds;

    private Rectangle instanceRoleBBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);
        arrangeAll();

        // InstanceRoles
        instanceRoleABot = editor.getEditPart(LIFELINE_A);
        instanceRoleBBot = editor.getEditPart(LIFELINE_B);

        instanceRoleABounds = editor.getBounds(instanceRoleABot);
        instanceRoleBBounds = editor.getBounds(instanceRoleBBot);
    }

    /**
     * Test move a execution on callMessage of reflexive syncCall is forbidden.
     */
    public void test_SyncCall_Move_On_CallMessage_Of_ReflexiveSyncCall_Forbidden() {
        Point startOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 200);
        Point endOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 300);
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);

        Point startOfSecondSyncCall = instanceRoleABounds.getCenter().translate(0, 100);
        Point endOfSecondSyncCall = instanceRoleBBounds.getCenter().translate(0, 100);
        createSyncCall(startOfSecondSyncCall, endOfSecondSyncCall);

        // Select the diagram itself for the future drag
        editor.select(editor.mainEditPart());

        SWTBotGefEditPart executionEditPartOFirstSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);
        SWTBotGefEditPart executionEditPartOfReflexiveSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);

        SWTBotGefConnectionEditPart callMessageOnE1Bot = executionEditPartOFirstSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart callMessageOnE2Bot = executionEditPartOfReflexiveSyncCallBot.targetConnections().get(0);

        SWTBotGefConnectionEditPart returnMessageOfE1Bot = executionEditPartOFirstSyncCallBot.sourceConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE2Bot = executionEditPartOfReflexiveSyncCallBot.sourceConnections().get(0);

        Rectangle executionOfReflexiveSyncCallBounds = editor.getBounds(executionEditPartOfReflexiveSyncCallBot);
        Rectangle executionOfFirstSyncCallBounds = editor.getBounds(executionEditPartOFirstSyncCallBot);

        Rectangle callMessageOnE1Bounds = editor.getBounds(callMessageOnE1Bot);
        Rectangle callMessageOnE2Bounds = editor.getBounds(callMessageOnE2Bot);

        Rectangle returnMessageOfE1Bounds = editor.getBounds(returnMessageOfE1Bot);
        Rectangle returnMessageOfE2Bounds = editor.getBounds(returnMessageOfE2Bot);

        int dy = callMessageOnE2Bounds.getCenter().y - executionOfFirstSyncCallBounds.getBottom().y;
        ICondition condition = new CheckEditPartMoved(executionEditPartOFirstSyncCallBot);
        editor.drag(executionEditPartOFirstSyncCallBot, executionOfFirstSyncCallBounds.x, executionOfFirstSyncCallBounds.y + dy);
        bot.waitUntil(condition);

        // Effect
        executionOfFirstSyncCallBounds.y += dy;
        callMessageOnE1Bounds.y += dy;
        returnMessageOfE1Bounds.y += dy;

        int dy2 = executionOfFirstSyncCallBounds.height;
        executionOfReflexiveSyncCallBounds.y += dy2;
        callMessageOnE2Bounds.y += dy2;
        returnMessageOfE2Bounds.y += dy2;

        assertEquals(executionOfFirstSyncCallBounds, editor.getBounds(executionEditPartOFirstSyncCallBot));
        assertEquals(executionOfReflexiveSyncCallBounds, editor.getBounds(executionEditPartOfReflexiveSyncCallBot));

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);

        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds, true, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds, false, false);
    }

    /**
     * Test move a execution on returnMessage of reflexive syncCall is
     * forbidden.
     */
    public void test_SyncCall_Move_On_ReturnMessage_Of_ReflexiveSyncCall_Forbidden() {
        Point startOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 100);
        Point endOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 200);
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);

        Point startOfSecondSyncCall = instanceRoleABounds.getCenter().translate(0, 300);
        Point endOfSecondSyncCall = instanceRoleBBounds.getCenter().translate(0, 300);
        createSyncCall(startOfSecondSyncCall, endOfSecondSyncCall);

        SWTBotGefEditPart executionEditPartOfReflexiveSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefEditPart executionEditPartOfSecondSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);

        SWTBotGefConnectionEditPart callMessageOnE1Bot = executionEditPartOfReflexiveSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart callMessageOnE2Bot = executionEditPartOfSecondSyncCallBot.targetConnections().get(0);

        SWTBotGefConnectionEditPart returnMessageOfE1Bot = executionEditPartOfReflexiveSyncCallBot.sourceConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE2Bot = executionEditPartOfSecondSyncCallBot.sourceConnections().get(0);

        Rectangle executionOfReflexiveSyncCallBounds = editor.getBounds(executionEditPartOfReflexiveSyncCallBot);
        Rectangle executionOfSecondSyncCallBounds = editor.getBounds(executionEditPartOfSecondSyncCallBot);

        Rectangle callMessageOnE1Bounds = editor.getBounds(callMessageOnE1Bot);
        Rectangle callMessageOnE2Bounds = editor.getBounds(callMessageOnE2Bot);

        Rectangle returnMessageOfE1Bounds = editor.getBounds(returnMessageOfE1Bot);
        Rectangle returnMessageOfE2Bounds = editor.getBounds(returnMessageOfE2Bot);

        int dy = returnMessageOfE1Bounds.y - executionOfSecondSyncCallBounds.y;
        ICondition condition = new OperationDoneCondition();
        editor.drag(executionEditPartOfSecondSyncCallBot, returnMessageOfE1Bounds.getCenter().x, executionOfSecondSyncCallBounds.y + dy);

        try {
            bot.waitUntil(condition, SWTBotPreferences.TIMEOUT);
        } catch (TimeoutException e) {
        }

        assertEquals(executionOfSecondSyncCallBounds, editor.getBounds(executionEditPartOfSecondSyncCallBot));
        assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));

        assertEquals(executionOfReflexiveSyncCallBounds, editor.getBounds(executionEditPartOfReflexiveSyncCallBot));
        assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));

    }

    /**
     * Test the move of a syncCall owning a reflexive syncCall, on another
     * syncCall.
     */
    // TODO
    public void test_SyncCall_Move_Reconnect_Forbidden_If_FinalTarget_On_CallReturnMessage_Differents() {
        Point startOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 100);
        Point endOfFirstSyncCall = instanceRoleABounds.getCenter().translate(0, 100);
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);

        Point startOfSecondSyncCall = instanceRoleABounds.getCenter().translate(0, 110);
        Point endOfSecondSyncCall = instanceRoleABounds.getCenter().translate(0, 120);
        createSyncCall(startOfSecondSyncCall, endOfSecondSyncCall);

        Point startOfThirdSyncCall = instanceRoleABounds.getCenter().translate(0, 300);
        Point endOfThirdSyncCall = instanceRoleBBounds.getCenter().translate(0, 300);
        createSyncCall(startOfThirdSyncCall, endOfThirdSyncCall);

        SWTBotGefEditPart executionEditPartOfFirstSyncCallBot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefEditPart executionEditPartOfReflexiveSyncCallBot = instanceRoleABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);
        SWTBotGefEditPart executionEditPartOfThirdSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);

        SWTBotGefConnectionEditPart callMessageOnE1Bot = executionEditPartOfFirstSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart callMessageOnE2Bot = executionEditPartOfReflexiveSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart callMessageOnE3Bot = executionEditPartOfThirdSyncCallBot.targetConnections().get(0);

        SWTBotGefConnectionEditPart returnMessageOfE1Bot = executionEditPartOfFirstSyncCallBot.sourceConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE2Bot = executionEditPartOfReflexiveSyncCallBot.sourceConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE3Bot = executionEditPartOfThirdSyncCallBot.sourceConnections().get(0);

        Rectangle executionOfFirstSyncCallBounds = editor.getBounds(executionEditPartOfFirstSyncCallBot);
        Rectangle executionOfReflexiveSyncCallBounds = editor.getBounds(executionEditPartOfReflexiveSyncCallBot);
        Rectangle executionOfThirdSyncCallBounds = editor.getBounds(executionEditPartOfThirdSyncCallBot);

        Rectangle callMessageOnE1Bounds = editor.getBounds(callMessageOnE1Bot);
        Rectangle callMessageOnE2Bounds = editor.getBounds(callMessageOnE2Bot);
        Rectangle callMessageOnE3Bounds = editor.getBounds(callMessageOnE3Bot);

        Rectangle returnMessageOfE1Bounds = editor.getBounds(returnMessageOfE1Bot);
        Rectangle returnMessageOfE2Bounds = editor.getBounds(returnMessageOfE2Bot);
        Rectangle returnMessageOfE3Bounds = editor.getBounds(returnMessageOfE3Bot);

        // Tests
        editor.select(executionEditPartOfFirstSyncCallBot, executionEditPartOfReflexiveSyncCallBot);
        ICondition condition = new OperationDoneCondition();
        editor.drag(executionEditPartOfFirstSyncCallBot, executionOfThirdSyncCallBounds.getCenter().x, executionOfThirdSyncCallBounds.getCenter().y);
        bot.waitUntil(condition);

        // What to expect?
        // move must be fobbiden?

        assertEquals(executionOfFirstSyncCallBounds, editor.getBounds(executionEditPartOfFirstSyncCallBot));
        assertEquals(executionOfReflexiveSyncCallBounds, editor.getBounds(executionEditPartOfReflexiveSyncCallBot));
        assertEquals(executionOfThirdSyncCallBounds, editor.getBounds(executionEditPartOfThirdSyncCallBot));

        assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        assertEquals(callMessageOnE3Bounds, editor.getBounds(callMessageOnE3Bot));

        assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        assertEquals(returnMessageOfE3Bounds, editor.getBounds(returnMessageOfE3Bot));
    }

    /**
     * Test Reconnect by dropping a sync call, with linked sync call, on the
     * message ends of other sync calls. These message ends will reconnect on
     * the dropped sync call.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SyncCall_Move_Reconnect_forbidden() throws Exception {
        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        resizeLifeline(LIFELINE_B, 200);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getScreenPosition(LIFELINE_A).x + getScreenSize(LIFELINE_A).width / 2;
        int lifelineBPosition = getScreenPosition(LIFELINE_B).x + getScreenSize(LIFELINE_B).width / 2;
        int lifelineCPosition = getScreenPosition(LIFELINE_C).x + getScreenSize(LIFELINE_C).width / 2;

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineAPosition, 150);
        editor.click(lifelineBPosition, 150);

        // Validates the position of the execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 0));

        // Creation of a Sync Call
        editor.activateTool("Sync Call");
        editor.click(lifelineCPosition, 250);
        editor.click(lifelineBPosition, 250);

        // Validates the position of the second execution
        assertNotNull("The execution index 1 on lifeline " + LIFELINE_B + " has not been found", getExecutionScreenPosition(LIFELINE_B, 1));

        Point firstExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 0).getCopy();
        Dimension firstExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 0).getCopy();
        Point secondExecutionScreenPosition = getExecutionScreenPosition(LIFELINE_B, 1).getCopy();
        Dimension secondExecutionDimension = getExecutionScreenDimension(LIFELINE_B, 1).getCopy();

        // move the first sync call of lifeline B to include the first bound of
        // the second sync call (forbidden move)
        editor.drag(firstExecutionScreenPosition, new Point(firstExecutionScreenPosition.x, secondExecutionScreenPosition.y - 10));

        // Effect
        firstExecutionScreenPosition.y = secondExecutionScreenPosition.y - 10;
        secondExecutionScreenPosition.y += firstExecutionDimension.height;

        // Validates the position of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 0).x);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " is not at the expected vertical position", firstExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.x, getExecutionScreenPosition(LIFELINE_B, 1).x);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " is not at the expected vertical position", secondExecutionScreenPosition.y, getExecutionScreenPosition(LIFELINE_B, 1).y);

        // Validates the dimension of the execution
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected height", firstExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().height);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected height", secondExecutionDimension.height, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().height);
        assertEquals("The execution index 0 on lifeline " + LIFELINE_B + " has not the expected width", firstExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 0).getCopy().width);
        assertEquals("The execution index 1 on lifeline " + LIFELINE_B + " has not the expected width", secondExecutionDimension.width, getExecutionScreenDimension(LIFELINE_B, 1).getCopy().width);

        // Validate the positions of the messages
        assertMessageVerticalPosition(FIRST_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 0).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 0), getExecutionScreenPosition(LIFELINE_B, 0).y + getExecutionScreenDimension(LIFELINE_B, 0).height);
        assertMessageVerticalPosition(THIRD_MESSAGE_SYNC_CALL, getExecutionScreenPosition(LIFELINE_B, 1).y);
        assertMessageVerticalPosition(getReturnSyncCall(LIFELINE_B, 1), getExecutionScreenPosition(LIFELINE_B, 1).y + getExecutionScreenDimension(LIFELINE_B, 1).height);

        assertEquals("The first bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageFirstBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageLastBendpointScreenPosition(FIRST_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 0).x,
                getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);
        assertEquals("The last bendpoint of the return message linked to " + FIRST_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineAPosition + 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 0)).x);

        assertEquals("The first bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageFirstBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL).x);
        assertEquals("The last bendpoint of the message named " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageLastBendpointScreenPosition(THIRD_MESSAGE_SYNC_CALL).x);
        assertEquals("The first bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", getExecutionScreenPosition(LIFELINE_B, 1).x
                + getExecutionScreenDimension(LIFELINE_B, 1).width, getSequenceMessageFirstBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 1)).x);
        assertEquals("The last bendpoint of the return message linked to " + THIRD_MESSAGE_SYNC_CALL + " is not at the expected horizontal position", lifelineCPosition - 2,
                getSequenceMessageLastBendpointScreenPosition(getReturnSyncCall(LIFELINE_B, 1)).x);

        validateOrdering(8);
    }

    /**
     * Test syncCall move on create message is forbidden.
     */
    public void test_SyncCall_MoveOnCreateMessageForbidden() {
        Point start = instanceRoleABounds.getCenter().translate(0, 100);
        Point end = instanceRoleBBounds.getCenter();
        ICondition condition = new OperationDoneCondition();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);
        bot.waitUntil(condition);

        Point startOfFirstSyncCall = instanceRoleABounds.getCenter().translate(0, 200);
        Point endOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 200);
        condition = new OperationDoneCondition();
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);
        bot.waitUntil(condition);
        bot.sleep(400);

        SWTBotGefConnectionEditPart createMessageBot = instanceRoleBBot.parent().targetConnections().get(0);
        SWTBotGefEditPart execOfFirstSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefConnectionEditPart callMessageBot = execOfFirstSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageBot = execOfFirstSyncCallBot.sourceConnections().get(0);

        Rectangle createMessageBounds = editor.getBounds(createMessageBot);
        Rectangle newInstanceRoleBBounds = editor.getBounds(instanceRoleBBot);
        Rectangle callMessageBounds = editor.getBounds(callMessageBot);
        Rectangle execOfFirstSyncCallBounds = editor.getBounds(execOfFirstSyncCallBot);
        Rectangle returnMessageBounds = editor.getBounds(returnMessageBot);

        execOfFirstSyncCallBot.select();
        editor.drag(execOfFirstSyncCallBot, newInstanceRoleBBounds.getCenter());
        bot.sleep(400);

        Assert.assertEquals(createMessageBounds, editor.getBounds(createMessageBot));
        Assert.assertEquals(newInstanceRoleBBounds, editor.getBounds(instanceRoleBBot));
        Assert.assertEquals(callMessageBounds, editor.getBounds(callMessageBot));
        Assert.assertEquals(execOfFirstSyncCallBounds, editor.getBounds(execOfFirstSyncCallBot));
        Assert.assertEquals(returnMessageBounds, editor.getBounds(returnMessageBot));

    }

    /**
     * Test syncCall move before create message is forbidden.
     */
    public void test_SyncCall_MoveBeforeCreateMessageForbidden() {
        Point start = instanceRoleABounds.getCenter().translate(0, 100);
        Point end = instanceRoleBBounds.getCenter();
        ICondition condition = new OperationDoneCondition();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);
        bot.waitUntil(condition);

        Point startOfFirstSyncCall = instanceRoleABounds.getCenter().translate(0, 200);
        Point endOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 200);
        condition = new OperationDoneCondition();
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);
        bot.waitUntil(condition);
        bot.sleep(400);

        SWTBotGefConnectionEditPart createMessageBot = instanceRoleBBot.parent().targetConnections().get(0);
        SWTBotGefEditPart execOfFirstSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefConnectionEditPart callMessageBot = execOfFirstSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageBot = execOfFirstSyncCallBot.sourceConnections().get(0);

        Rectangle createMessageBounds = editor.getBounds(createMessageBot);
        Rectangle newInstanceRoleBBounds = editor.getBounds(instanceRoleBBot);
        Rectangle callMessageBounds = editor.getBounds(callMessageBot);
        Rectangle execOfFirstSyncCallBounds = editor.getBounds(execOfFirstSyncCallBot);
        Rectangle returnMessageBounds = editor.getBounds(returnMessageBot);

        execOfFirstSyncCallBot.select();
        editor.drag(execOfFirstSyncCallBot, newInstanceRoleBBounds.getTop().translate(0, -execOfFirstSyncCallBounds.height));
        bot.sleep(400);

        Assert.assertEquals(createMessageBounds, editor.getBounds(createMessageBot));
        Assert.assertEquals(newInstanceRoleBBounds, editor.getBounds(instanceRoleBBot));
        Assert.assertEquals(callMessageBounds, editor.getBounds(callMessageBot));
        Assert.assertEquals(execOfFirstSyncCallBounds, editor.getBounds(execOfFirstSyncCallBot));
        Assert.assertEquals(returnMessageBounds, editor.getBounds(returnMessageBot));

    }

    /**
     * Test syncCall move on destroy message is forbidden.
     */
    public void test_SyncCall_MoveOnDestroyMessageForbidden() {
        Point start = instanceRoleABounds.getCenter().translate(0, 200);
        Point end = instanceRoleBBounds.getCenter().translate(0, 200);
        ICondition condition = new OperationDoneCondition();
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, start, end);
        bot.waitUntil(condition);
        bot.sleep(400);

        SWTBotGefEditPart endOfLifeBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(EndOfLifeEditPart.class)).get(0);
        SWTBotGefConnectionEditPart destroyMessageBot = endOfLifeBot.targetConnections().get(0);

        Point startOfFirstSyncCall = instanceRoleABounds.getCenter().translate(0, 100);
        Point endOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 100);
        condition = new OperationDoneCondition();
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);
        bot.waitUntil(condition);
        bot.sleep(400);

        SWTBotGefEditPart execOfFirstSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefConnectionEditPart callMessageBot = execOfFirstSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageBot = execOfFirstSyncCallBot.sourceConnections().get(0);

        Rectangle destroyMessageBounds = editor.getBounds(destroyMessageBot);
        Rectangle newInstanceRoleBBounds = editor.getBounds(instanceRoleBBot);
        Rectangle callMessageBounds = editor.getBounds(callMessageBot);
        Rectangle execOfFirstSyncCallBounds = editor.getBounds(execOfFirstSyncCallBot);
        Rectangle returnMessageBounds = editor.getBounds(returnMessageBot);

        execOfFirstSyncCallBot.select();
        editor.drag(execOfFirstSyncCallBot, destroyMessageBounds.getCenter());
        bot.sleep(400);

        Assert.assertEquals(destroyMessageBounds, editor.getBounds(destroyMessageBot));
        Assert.assertEquals(newInstanceRoleBBounds, editor.getBounds(instanceRoleBBot));
        Assert.assertEquals(callMessageBounds, editor.getBounds(callMessageBot));
        Assert.assertEquals(execOfFirstSyncCallBounds, editor.getBounds(execOfFirstSyncCallBot));
        Assert.assertEquals(returnMessageBounds, editor.getBounds(returnMessageBot));
    }

    /**
     * Test syncCall move after destroy message is forbidden.
     */
    public void test_SyncCall_MoveAfterDestroyMessageForbidden() {
        Point start = instanceRoleABounds.getCenter().translate(0, 200);
        Point end = instanceRoleBBounds.getCenter().translate(0, 200);
        ICondition condition = new OperationDoneCondition();
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, start, end);
        bot.waitUntil(condition);
        bot.sleep(400);

        SWTBotGefEditPart endOfLifeBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(EndOfLifeEditPart.class)).get(0);
        SWTBotGefConnectionEditPart destroyMessageBot = endOfLifeBot.targetConnections().get(0);

        Point startOfFirstSyncCall = instanceRoleABounds.getCenter().translate(0, 100);
        Point endOfFirstSyncCall = instanceRoleBBounds.getCenter().translate(0, 100);
        condition = new OperationDoneCondition();
        createSyncCall(startOfFirstSyncCall, endOfFirstSyncCall);
        bot.waitUntil(condition);
        bot.sleep(400);

        SWTBotGefEditPart execOfFirstSyncCallBot = instanceRoleBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefConnectionEditPart callMessageBot = execOfFirstSyncCallBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageBot = execOfFirstSyncCallBot.sourceConnections().get(0);

        Rectangle destroyMessageBounds = editor.getBounds(destroyMessageBot);
        Rectangle newInstanceRoleBBounds = editor.getBounds(instanceRoleBBot);
        Rectangle callMessageBounds = editor.getBounds(callMessageBot);
        Rectangle execOfFirstSyncCallBounds = editor.getBounds(execOfFirstSyncCallBot);
        Rectangle returnMessageBounds = editor.getBounds(returnMessageBot);

        execOfFirstSyncCallBot.select();
        editor.drag(execOfFirstSyncCallBot, destroyMessageBounds.getBottom().translate(0, execOfFirstSyncCallBounds.height));
        bot.sleep(400);

        Assert.assertEquals(destroyMessageBounds, editor.getBounds(destroyMessageBot));
        Assert.assertEquals(newInstanceRoleBBounds, editor.getBounds(instanceRoleBBot));
        Assert.assertEquals(callMessageBounds, editor.getBounds(callMessageBot));
        Assert.assertEquals(execOfFirstSyncCallBounds, editor.getBounds(execOfFirstSyncCallBot));
        Assert.assertEquals(returnMessageBounds, editor.getBounds(returnMessageBot));

    }

}
