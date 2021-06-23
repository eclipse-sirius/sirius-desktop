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

import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.RangeHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test ExecutionSelectionEditPolicy for move and resize commands.
 * 
 * @author edugueperoux
 */
public class ExecutionSelectionEditPolicyTests extends AbstractDefaultModelSequenceTests {

    /**
     * Test ExecutionSelectionEditPolicy#getMoveCommand
     */
    public void testGetMoveCommand1() {
        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B).parent();
        SWTBotGefEditPart instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C).parent();
        String LIFELINE_D = "d";
        Rectangle instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        SWTBotGefEditPart instanceRoleEditPartDBot = createParticipant(LIFELINE_D, instanceRoleEditPartCBounds.getRight().x, LayoutConstants.LIFELINES_START_Y);
        LIFELINE_D += " : ";

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);
        int lifelineCPosition = getLifelineScreenX(LIFELINE_C);
        int lifelineDPosition = getLifelineScreenX(LIFELINE_D);

        // tests Sync Call move

        // Creation of a Sync Call between LIFELINE_A and LIFELINE_B
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, 110, lifelineBPosition, 110);
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineBPosition, 300, lifelineCPosition, 300);
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineCPosition, 140, lifelineDPosition, 140);

        // Test that move of execution of C provokes expansion of execution of B
        // and shift of thirteen SyncCall
        SWTBotGefEditPart executionEditPartOfBBot = instanceRoleEditPartBBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefEditPart executionEditPartOfCBot = instanceRoleEditPartCBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        SWTBotGefEditPart executionEditPartOfDBot = instanceRoleEditPartDBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);

        SWTBotGefConnectionEditPart callMessageOfExecBBot = executionEditPartOfBBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart callMessageOfExecDBot = executionEditPartOfDBot.targetConnections().get(0);

        int diff = editor.getBounds(callMessageOfExecDBot).getBottom().y - editor.getBounds(callMessageOfExecBBot).getBottom().y;

        int yPos = editor.getBounds(callMessageOfExecBBot).getBottom().y + diff / 2;

        ICondition condition = new CheckEditPartMoved(executionEditPartOfCBot);
        editor.drag(executionEditPartOfCBot, lifelineCPosition, yPos);
        bot.waitUntil(condition);

        Rectangle executionEditPartOfBBounds = editor.getBounds(executionEditPartOfBBot);
        Range executionEditPartOfBRange = RangeHelper.verticalRange(executionEditPartOfBBounds);
        Rectangle executionEditPartOfCBounds = editor.getBounds(executionEditPartOfCBot);
        Range executionEditPartOfCRange = RangeHelper.verticalRange(executionEditPartOfCBounds);
        Rectangle executionEditPartOfDBounds = editor.getBounds(executionEditPartOfDBot);
        Range executionEditPartOfDRange = RangeHelper.verticalRange(executionEditPartOfDBounds);
        assertTrue("start execution of B must be before start execution of C", executionEditPartOfBRange.getLowerBound() < executionEditPartOfCRange.getLowerBound());
        assertTrue("end execution of C must be before start execution of D", executionEditPartOfCRange.getUpperBound() < executionEditPartOfDRange.getLowerBound());
        assertTrue("end execution of D must be before end execution of B", executionEditPartOfDRange.getUpperBound() < executionEditPartOfBRange.getUpperBound());

    }

    /**
     * Test ExecutionSelectionEditPolicy#getMoveCommand : Try to reparent a reflexive synCall child of a execution to be
     * child of its lifeline
     */
    public void testGetMoveCommand2() {
        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B).parent();

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // tests Sync Call move

        // Creation of a Sync Call between LIFELINE_A and LIFELINE_B
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, 110, lifelineBPosition, 110);
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineBPosition, 140, lifelineBPosition, 150);

        // Test that move of execution of C provokes expansion of execution of B
        // and shift of thirteen SyncCall
        List<SWTBotGefEditPart> executionEditPartOfBBots = instanceRoleEditPartBBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        SWTBotGefEditPart executionEditPartOfBBot = executionEditPartOfBBots.get(0);
        // resize first created execution to place on it a reflexive Syncall
        // else can do that
        Rectangle executionEditPartOfBBounds = editor.getBounds(executionEditPartOfBBot);

        SWTBotGefEditPart reflexiveExecutionEditPartOfBBot = executionEditPartOfBBots.get(1);
        Rectangle reflexiveExecutionEditPartOfBBounds = editor.getBounds(reflexiveExecutionEditPartOfBBot);

        editor.drag(reflexiveExecutionEditPartOfBBot, lifelineBPosition, 400);

        Rectangle newExecutionEditPartOfBBounds = editor.getBounds(executionEditPartOfBBot);
        Rectangle newReflexiveExecutionEditPartOfBBounds = editor.getBounds(reflexiveExecutionEditPartOfBBot);

        assertEquals(executionEditPartOfBBounds, newExecutionEditPartOfBBounds);
        assertEquals(reflexiveExecutionEditPartOfBBounds.getTranslated(-LayoutConstants.DEFAULT_EXECUTION_WIDTH / 2, 400), newReflexiveExecutionEditPartOfBBounds);

    }

    /**
     * Test ExecutionSelectionEditPolicy#getResizeCommand
     * 
     */
    public void testGetResizeCommand1() {
        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();

        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B).parent();

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of a Sync Call between LIFELINE_A and LIFELINE_B
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineAPosition, 200, lifelineBPosition, 200);

        // Creation of a Reflexive Sync Call on LIFELINE_B
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, lifelineBPosition, 210, lifelineBPosition, 220);

        List<SWTBotGefEditPart> executionEditPartOfBBots = instanceRoleEditPartBBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        SWTBotGefEditPart firstExecutionEditPartOfBBot = executionEditPartOfBBots.get(0);
        SWTBotGefEditPart reflexiveExecutionEditPartOfBBot = executionEditPartOfBBots.get(1);

        Rectangle firstExecutionBounds = editor.getBounds(firstExecutionEditPartOfBBot);
        Rectangle reflexiveExecutionBounds = editor.getBounds(reflexiveExecutionEditPartOfBBot);

        SWTBotGefConnectionEditPart callMessageReflexifBot = reflexiveExecutionEditPartOfBBot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageReflexifBot = reflexiveExecutionEditPartOfBBot.sourceConnections().get(0);

        SequenceMessageEditPart returnMessageReflexifEditPart = (SequenceMessageEditPart) returnMessageReflexifBot.part();
        SequenceMessageEditPart callMessageReflexifEditPart = (SequenceMessageEditPart) callMessageReflexifBot.part();

        Point callMessageReflexifLastPoint = callMessageReflexifEditPart.getConnectionFigure().getPoints().getLastPoint();

        Point returnMessageReflexifLastPoint = returnMessageReflexifEditPart.getConnectionFigure().getPoints().getLastPoint();

        // Resize a sub execution of a reflexive message (Sync Call) upper to
        // limit of parent execution

        assertTrue(firstExecutionBounds.getBottomRight().y > returnMessageReflexifLastPoint.y);

        // Test if we can resize the subExecution down until its return message
        // lastPoint is on parent execution bottom limit : normally not allowed
        int delta = firstExecutionBounds.getBottom().y - returnMessageReflexifLastPoint.y;
        reflexiveExecutionEditPartOfBBot.select();

        firstExecutionBounds = editor.getBounds(firstExecutionEditPartOfBBot);
        returnMessageReflexifLastPoint = returnMessageReflexifEditPart.getConnectionFigure().getPoints().getLastPoint();

        assertTrue("Return message connection last point is not on a execution", firstExecutionBounds.getBottom().y > returnMessageReflexifLastPoint.y);

        // Test if we can resize the subExecution down until its return message
        // lastPoint is on parent execution bottom limit minus Layout: normally
        // not allowed
        reflexiveExecutionBounds = editor.getBounds(reflexiveExecutionEditPartOfBBot);
        delta = firstExecutionBounds.getBottom().y - returnMessageReflexifLastPoint.y - LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        reflexiveExecutionEditPartOfBBot.select();
        reflexiveExecutionEditPartOfBBot.resize(PositionConstants.SOUTH, reflexiveExecutionBounds.width, reflexiveExecutionBounds.height + delta);

        firstExecutionBounds = editor.getBounds(firstExecutionEditPartOfBBot);
        returnMessageReflexifLastPoint = returnMessageReflexifEditPart.getConnectionFigure().getPoints().getLastPoint();

        assertTrue("Return message connection last point is not on a execution", firstExecutionBounds.getBottom().y > returnMessageReflexifLastPoint.y);

        // Resize a sub execution of a reflexive message (Sync Call) down to
        // limit of parent execution
        callMessageReflexifLastPoint = callMessageReflexifEditPart.getConnectionFigure().getPoints().getLastPoint();
        assertTrue(firstExecutionBounds.getBottomRight().y < callMessageReflexifLastPoint.y);

        delta = callMessageReflexifLastPoint.y - firstExecutionBounds.y;
        reflexiveExecutionEditPartOfBBot.select();
        reflexiveExecutionEditPartOfBBot.resize(PositionConstants.NORTH, reflexiveExecutionBounds.width, reflexiveExecutionBounds.height + delta);

        firstExecutionBounds = editor.getBounds(firstExecutionEditPartOfBBot);
        returnMessageReflexifLastPoint = returnMessageReflexifEditPart.getConnectionFigure().getPoints().getLastPoint();

        assertTrue("Call message connection last point is not on a execution", returnMessageReflexifLastPoint.y > firstExecutionBounds.y);
    }

}
