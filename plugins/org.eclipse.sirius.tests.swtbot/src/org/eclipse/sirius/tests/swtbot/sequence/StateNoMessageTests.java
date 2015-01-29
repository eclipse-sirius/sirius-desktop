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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Test class validating that states can not have message.
 * 
 * @author smonnier
 */
public class StateNoMessageTests extends AbstractStatesSequenceTests {

    // Main part
    private SWTBotGefEditPart sequenceDiagramBot;

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    private Rectangle instanceRoleEditPartABounds;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private Rectangle instanceRoleEditPartBBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);

        instanceRoleEditPartABounds = editor.getBounds(instanceRoleEditPartABot);
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);

        sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();
    }

    /**
     * Test method.
     */
    public void test_Connect_Message_As_Source_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.READ_TOOL_ID, stateS1ScreenBounds.getCenter(), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));

        Assert.assertEquals(0, stateS1Bot.sourceConnections().size());

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Connect_Create_Message_As_Source_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.CREATE_TOOL_ID, stateS1ScreenBounds.getCenter(), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));

        Assert.assertEquals(0, stateS1Bot.sourceConnections().size());

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Connect_Destroy_Message_As_Source_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.DESTROY_TOOL_ID, stateS1ScreenBounds.getCenter(), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));

        Assert.assertEquals(0, stateS1Bot.sourceConnections().size());

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Connect_Message_As_Target_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.READ_TOOL_ID, new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getCenter().y), stateS1ScreenBounds.getCenter());

        Assert.assertEquals(0, stateS1Bot.targetConnections().size());

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Connect_Message_As_Target_With_Second_Click_On_Lifeline_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.READ_TOOL_ID, new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getCenter().y), stateS1ScreenBounds.getBottom().getTranslated(0, 10));

        Assert.assertEquals(0, stateS1Bot.targetConnections().size());

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Connect_Create_Message_As_Target_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.CREATE_TOOL_ID, new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getCenter().y), stateS1ScreenBounds.getCenter());

        Assert.assertEquals(0, stateS1Bot.targetConnections().size());

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Connect_Destroy_Message_As_Target_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.DESTROY_TOOL_ID, new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getCenter().y), stateS1ScreenBounds.getCenter());

        Assert.assertEquals(0, stateS1Bot.targetConnections().size());

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Move_Message_To_Have_Source_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.READ_TOOL_ID, stateS1ScreenBounds.getBottom().getTranslated(0, 10), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getBottom()
                .getTranslated(0, 10).y));
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_MESSAGE, editor));

        SWTBotGefEditPart lifelineABot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart messageBot = lifelineABot.sourceConnections().get(0);

        Rectangle messageBounds = editor.getBounds(messageBot);

        editor.drag(messageBounds.getCenter(), new Point(messageBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, FIRST_MESSAGE, SequenceMessageEditPart.class));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(messageBounds, editor.getBounds(messageBot));
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_Create_Message_To_Have_Source_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.CREATE_TOOL_ID, stateS1ScreenBounds.getBottom().getTranslated(0, 10), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getBottom()
                .getTranslated(0, 10).y));
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_CREATE_MESSAGE, editor));

        SWTBotGefEditPart lifelineABot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart messageBot = lifelineABot.sourceConnections().get(0);

        Rectangle messageBounds = editor.getBounds(messageBot);

        editor.drag(messageBounds.getCenter(), new Point(messageBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, FIRST_CREATE_MESSAGE, SequenceMessageEditPart.class));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(messageBounds, editor.getBounds(messageBot));
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_Destroy_Message_To_Have_Source_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.DESTROY_TOOL_ID, stateS1ScreenBounds.getBottom().getTranslated(0, 10), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getBottom()
                .getTranslated(0, 10).y));
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_DESTROY_MESSAGE, editor));

        SWTBotGefEditPart lifelineABot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart messageBot = lifelineABot.sourceConnections().get(0);

        Rectangle messageBounds = editor.getBounds(messageBot);

        editor.drag(messageBounds.getCenter(), new Point(messageBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, FIRST_DESTROY_MESSAGE, SequenceMessageEditPart.class));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(messageBounds, editor.getBounds(messageBot));
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_Sync_Message_To_Have_Source_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        SWTBotGefEditPart lifelineBBot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);

        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, stateS1ScreenBounds.getBottom().getTranslated(0, 10), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds
                .getBottom().getTranslated(0, 10).y));
        bot.waitUntil(new CheckNumberOfDescendants(lifelineBBot, ExecutionEditPart.class, 1));

        SWTBotGefEditPart executionBot = lifelineBBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle executionBounds = editor.getBounds(executionBot);

        editor.drag(executionBounds.getTop(), new Point(executionBounds.getTop().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, (ExecutionEditPart) executionBot.part()));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(executionBounds, editor.getBounds(executionBot));
        validateOrdering(6);
    }

    /**
     * Test method.
     */
    public void test_Move_Message_To_Have_Target_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.READ_TOOL_ID, new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getBottom().getTranslated(0, 10).y), stateS1ScreenBounds.getBottom()
                .getTranslated(0, 10));
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_MESSAGE_ON_LIFELINE_A, editor));

        SWTBotGefEditPart lifelineABot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart messageBot = lifelineABot.targetConnections().get(0);

        Rectangle messageBounds = editor.getBounds(messageBot);

        editor.drag(messageBounds.getCenter(), new Point(messageBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, FIRST_MESSAGE_ON_LIFELINE_A, SequenceMessageEditPart.class));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(messageBounds, editor.getBounds(messageBot));
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_Create_Message_To_Have_Target_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.CREATE_TOOL_ID, new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getTop().getTranslated(0, -30).y), stateS1ScreenBounds.getTop()
                .getTranslated(0, -30));
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_CREATE_MESSAGE_ON_LIFELINE_A, editor));

        SWTBotGefEditPart lifelineBBot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart messageBot = lifelineBBot.sourceConnections().get(0);

        Rectangle messageBounds = editor.getBounds(messageBot);

        editor.drag(messageBounds.getCenter(), new Point(messageBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, FIRST_CREATE_MESSAGE_ON_LIFELINE_A, SequenceMessageEditPart.class));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(messageBounds, editor.getBounds(messageBot));
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_Destroy_Message_To_Have_Target_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        createMessage(InteractionsConstants.DESTROY_TOOL_ID, new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds.getBottom().getTranslated(0, 30).y), stateS1ScreenBounds
                .getBottom().getTranslated(0, 30));
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_DESTROY_MESSAGE, editor));

        SWTBotGefEditPart lifelineBBot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart messageBot = lifelineBBot.sourceConnections().get(0);

        Rectangle messageBounds = editor.getBounds(messageBot);

        editor.drag(messageBounds.getCenter(), new Point(messageBounds.getCenter().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, FIRST_DESTROY_MESSAGE, SequenceMessageEditPart.class));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(messageBounds, editor.getBounds(messageBot));
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_Sync_Message_To_Have_Target_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        SWTBotGefEditPart lifelineBBot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);

        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, stateS1ScreenBounds.getBottom().getTranslated(0, 10), new Point(instanceRoleEditPartBBounds.getCenter().x, stateS1ScreenBounds
                .getBottom().getTranslated(0, 10).y));
        bot.waitUntil(new CheckNumberOfDescendants(lifelineBBot, ExecutionEditPart.class, 1));

        SWTBotGefEditPart executionBot = lifelineBBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle executionBounds = editor.getBounds(executionBot);

        editor.drag(executionBounds.getBottom().getTranslated(0, -2), new Point(executionBounds.getBottom().x, stateS1ScreenBounds.getCenter().y));
        bot.waitUntil(new CheckSelectedCondition(editor, (ExecutionEditPart) executionBot.part()));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        Assert.assertEquals(executionBounds, editor.getBounds(executionBot));
        validateOrdering(6);
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
