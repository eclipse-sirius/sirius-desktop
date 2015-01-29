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
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.sequence.condition.ConnectionEditPartChangedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Test that syncCall's execution resize from its call/return message branches
 * is possible until
 * {@link LayoutConstants#MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP} margin.
 * 
 * Test case with a proper data test becauseof SWTBot GEF can't drag call/return
 * message edge's horizontal segment (not this directly connected the syncCall's
 * execution). This is due to a GMF's DragTracker while SWTBot's GEF support
 * only support GEF DragTracker?
 * 
 * @author edugueperoux
 */
public class SyncCall3Test extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "syncCall/";

    private static final String REPRESENTATION_NAME = "SyncCall Tests";

    private static final String MODEL = "syncCall.interactions";

    private static final String SESSION_FILE = "syncCall.aird";

    private static final String TYPES_FILE = "types.ecore";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getSemanticModel() {
        return MODEL;
    }

    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    protected String getSessionModel() {
        return SESSION_FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    private Rectangle instanceRoleEditPartABounds;

    // Executions

    private SWTBotGefEditPart e1Bot;

    private SWTBotGefEditPart e2Bot;

    private Rectangle e1Bounds;

    private Rectangle e2Bounds;

    // Messages

    private SWTBotGefConnectionEditPart callMessageBot;

    private SWTBotGefConnectionEditPart returnMessageBot;

    private SequenceMessageEditPart callMessageEditPart;

    private SequenceMessageEditPart returnMessageEditPart;

    private Rectangle callMessageBounds;

    private Rectangle returnMessageBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);

        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartABounds = editor.getBounds(instanceRoleEditPartABot);

        e1Bot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e1Bounds = editor.getBounds(e1Bot);

        e2Bot = e1Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e2Bounds = editor.getBounds(e2Bot);

        callMessageBot = e2Bot.targetConnections().get(0);
        returnMessageBot = e2Bot.sourceConnections().get(0);

        callMessageEditPart = (SequenceMessageEditPart) callMessageBot.part();
        returnMessageEditPart = (SequenceMessageEditPart) returnMessageBot.part();

        callMessageBounds = editor.getBounds(callMessageBot);
        returnMessageBounds = editor.getBounds(returnMessageBot);

    }

    /**
     * Test that syncCall's execution move up is not possible before the
     * creation message of the remote lifeline.
     */
    public void test_SyncCall_Execution_Move_Out_Of_Remote_Created_Lifeline() {
        arrangeAll();

        ICondition done = new OperationDoneCondition();
        createParticipant(300, 50);
        bot.waitUntil(done);

        int createY = 350;
        done = new OperationDoneCondition();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, LIFELINE_A, createY, "newParticipant2 : ", 350);
        bot.waitUntil(done);

        assertExecutionDoesNotExist(LIFELINE_A, 2);

        int syncCallY = 450;
        done = new OperationDoneCondition();
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, "newParticipant2 : ", syncCallY, LIFELINE_A, 450);
        bot.waitUntil(done);
        Rectangle syncCallBounds = assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, new Rectangle(0, syncCallY, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);

        editor.click(0, 0);
        done = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 2));
        editor.drag(syncCallBounds.getCenter(), new Point(0, createY + 10));
        bot.waitUntil(done);

        undo();

        assertExecutionDoesNotExist(LIFELINE_A, 2);

        redo();

        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, syncCallBounds);

        editor.click(0, 0);
        done = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 2));
        editor.drag(syncCallBounds.getCenter(), new Point(0, createY - 10));
        bot.waitUntil(done);

        undo();

        assertExecutionDoesNotExist(LIFELINE_A, 2);

        redo();

        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, syncCallBounds);

        editor.click(0, 0);
        done = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 2));
        editor.drag(syncCallBounds.getCenter(), new Point(0, 250));
        bot.waitUntil(done);

        undo();

        assertExecutionDoesNotExist(LIFELINE_A, 2);
    }

    /**
     * Test that syncCall's execution move down is not possible after the
     * destruction message of the remote lifeline.
     */
    public void test_SyncCall_Execution_Move_Out_Of_Remote_Destructed_Lifeline() {
        arrangeAll();

        ICondition done = new OperationDoneCondition();
        createParticipant(300, 50);
        bot.waitUntil(done);

        int destroyY = 450;
        done = new OperationDoneCondition();
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, LIFELINE_A, destroyY, "newParticipant2 : ", 450);
        bot.waitUntil(done);

        assertExecutionDoesNotExist(LIFELINE_A, 2);

        int syncCallY = 350;
        done = new OperationDoneCondition();
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, "newParticipant2 : ", 350, LIFELINE_A, 350);
        bot.waitUntil(done);

        Rectangle syncCallBounds = assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, new Rectangle(0, syncCallY, 0, LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT), false);

        editor.click(0, 0);
        done = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 2));
        editor.drag(syncCallBounds.getCenter(), new Point(0, destroyY + 10));
        bot.waitUntil(done);

        undo();

        assertExecutionDoesNotExist(LIFELINE_A, 2);

        redo();

        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, syncCallBounds);

        editor.click(0, 0);
        done = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 2));
        editor.drag(syncCallBounds.getCenter(), new Point(0, 500));
        bot.waitUntil(done);

        undo();

        assertExecutionDoesNotExist(LIFELINE_A, 2);
        redo();

        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, syncCallBounds);

        editor.click(0, 0);
        done = new CheckSelectedCondition(editor, getExecutionEditPart(LIFELINE_A, 2));
        editor.drag(syncCallBounds.getCenter(), new Point(0, destroyY - 10));
        bot.waitUntil(done);

        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, syncCallBounds.getTranslated(0, destroyY - 10 - syncCallBounds.getCenter().y));

        undo();

        assertExecutionHasValidLogicalBounds(LIFELINE_A, 2, syncCallBounds);

    }

    /**
     * Test that syncCall's execution resize up of 5 pixels from the call
     * message is possible
     * {@link LayoutConstants#MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP}.
     */
    public void test_SyncCall_Execution_Resize_Up_A_Little_From_Call_Message() {
        // Test resize up the syncCall's execution of 5 pixels
        Point start = callMessageBounds.getBottom();
        Point end = start.getTranslated(0, -6);
        ICondition condition = new ConnectionEditPartChangedCondition(callMessageEditPart);
        editor.drag(start, end);
        bot.waitUntil(condition);

        int dy = start.y - end.y;
        dy -= 1;
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, -dy).resize(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(callMessageBounds.getResized(0, -dy), editor.getBounds(callMessageBot));
        Assert.assertEquals(returnMessageBounds, editor.getBounds(returnMessageBot));

    }

    /**
     * Test that syncCall's execution resize up from the call message is
     * possible until a minimum vertical margin
     * {@link LayoutConstants#MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP}.
     */
    public void test_SyncCall_Execution_Resize_Up_From_Call_Message() {
        // Test resize up the syncCall's execution until the middle of the range
        // of the call message
        Point start = callMessageBounds.getBottom();
        Point end = callMessageBounds.getCenter();
        ICondition condition = new ConnectionEditPartChangedCondition(callMessageEditPart);
        editor.drag(start, end);
        bot.waitUntil(condition);

        int dy = start.y - end.y;
        dy -= 1;
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, -dy).resize(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(callMessageBounds.getResized(0, -dy), editor.getBounds(callMessageBot));
        Assert.assertEquals(returnMessageBounds, editor.getBounds(returnMessageBot));

        e2Bounds = editor.getBounds(e2Bot);
        callMessageBounds = editor.getBounds(callMessageBot);

        // Test resize up the syncCall's execution until call message minimum's
        // range (LayoutConstants#MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP)
        start = callMessageBounds.getBottom();
        end = callMessageBounds.getTop().translate(0, LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP);
        condition = new ConnectionEditPartChangedCondition(callMessageEditPart);
        editor.drag(start, end);
        bot.waitUntil(condition);

        dy = start.y - end.y;
        dy -= 1;
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, -dy).resize(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(callMessageBounds.getResized(0, -dy), editor.getBounds(callMessageBot));
        Assert.assertEquals(returnMessageBounds, editor.getBounds(returnMessageBot));
    }

    /**
     * Test that syncCall's execution resize down from the return message is
     * possible until a minimum vertical margin
     * {@link LayoutConstants#MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP}.
     */
    public void test_SyncCall_Execution_Resize_Down_From_Return_Message() {
        // Test resize down the syncCall's execution until the middle of the
        // range of the return message
        Point start = returnMessageBounds.getTop();
        Point end = returnMessageBounds.getCenter();
        ICondition condition = new ConnectionEditPartChangedCondition(returnMessageEditPart);
        editor.drag(start, end);
        bot.waitUntil(condition);

        int dy = end.y - start.y;
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(callMessageBounds, editor.getBounds(callMessageBot));
        Assert.assertEquals(returnMessageBounds.getTranslated(0, dy).resize(0, -dy), editor.getBounds(returnMessageBot));

        e2Bounds = editor.getBounds(e2Bot);
        returnMessageBounds = editor.getBounds(returnMessageBot);

        // Test resize down the syncCall's execution until return message
        // minimum's range
        // (LayoutConstants#MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP)
        start = returnMessageBounds.getTop();
        end = returnMessageBounds.getBottom().translate(0, -LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP - 1);
        condition = new ConnectionEditPartChangedCondition(returnMessageEditPart);
        editor.drag(start, end);
        bot.waitUntil(condition);

        dy = end.y - start.y;
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(callMessageBounds, editor.getBounds(callMessageBot));
        Assert.assertEquals(returnMessageBounds.getTranslated(0, dy).resize(0, -dy), editor.getBounds(returnMessageBot));
    }

}
