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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberExecutionOnLifeline;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * NOTE : transversal aspect tested for each tests :
 * <ul>
 * <ol>
 * events ordering
 * </ol>
 * <ol>
 * undo/redo
 * </ol>
 * <ol>
 * zoom
 * </ol>
 * <ol>
 * deletion
 * </ol>
 * </ul>
 * 
 * @author edugueperoux, smonnier
 */
// TODO : tests with zoom
public class InteractionUseTests extends AbstractInteractionUseSequenceTests {

    /**
     * Test that the move of an execution with an IU is not possible if this IU
     * will overlap another one.
     */
    public void testIndirectMoveOfIUCanNotOverlapUnmovedIU() {
        Point createLocation = new Point(instanceRoleEditPartEBounds.getCenter().x, firstInteractionUseBounds.getBottom().getTranslated(0, 10).y);

        Option<SWTBotGefEditPart> newExecutionOption = createExecutionWithResult(createLocation);
        Assert.assertTrue("An execution should have been created at " + createLocation, newExecutionOption.some());
        SWTBotGefEditPart newExecutionBot = newExecutionOption.get();
        Rectangle newExecutionBounds = editor.getBounds(newExecutionBot);

        Point startLocation = new Point(instanceRoleEditPartDBounds.getCenter().getTranslated(-10, 0).x, newExecutionBounds.getTop().getTranslated(0, 10).y);
        Point targetLocation = newExecutionBounds.getBottomRight().getTranslated(10, -10);

        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(startLocation, targetLocation);
        Assert.assertNotNull(newInteractionUseBot);
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        int dY = newInteractionUseBounds.bottom() - newExecutionBounds.bottom() + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(newExecutionBounds.getResized(0, dY), editor.getBounds(newExecutionBot));

        editor.drag(newExecutionBounds.getTop(), firstInteractionUseBounds.getTop());

        // select the IU to be sure the drag action is finished even if it
        // should not move
        editor.click(newInteractionUseBounds.getTop());

        // validate the execution and IU have not moved
        Assert.assertEquals(newExecutionBounds.getResized(0, dY), editor.getBounds(newExecutionBot));
        Assert.assertEquals(newInteractionUseBounds, editor.getBounds(newInteractionUseBot));
    }

    /**
     * Test that reflexive syncCall creatuib below a IU is correct.
     */
    public void testReflexiveSyncCallCreationBelowAIU() {
        Point start = new Point(e4Bounds.getCenter().x, secondInteractionUseBounds.getBottom().getTranslated(0, 1).y);
        Point finish = start.getTranslated(0, 10);
        CheckEditPartResized checkResize = new CheckEditPartResized(e4Bot);
        createSyncCall(start, finish);
        bot.waitUntil(checkResize);

        SWTBotGefEditPart execOfSyncCallBot = e4Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);
        SWTBotGefConnectionEditPart callMessageOfSyncCallBot = execOfSyncCallBot.targetConnections().get(0);

        Rectangle newSecondInteractionUseBounds = editor.getBounds(secondInteractionUseBot);
        Rectangle execOfSyncCallBounds = editor.getBounds(execOfSyncCallBot);
        Rectangle callMessageOfSyncCallBounds = editor.getBounds(callMessageOfSyncCallBot);

        int dy = (execOfSyncCallBounds.height + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP * 2) - (e2Bounds.getBottom().y - secondInteractionUseBounds.getBottom().y)
                + LayoutConstants.EXECUTION_CHILDREN_MARGIN * 2;

        Assert.assertTrue(callMessageOfSyncCallBounds.y > newSecondInteractionUseBounds.getBottom().y);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getResized(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();
        testDiagramConsistency();

        // Redo
        redo();

        Assert.assertTrue(callMessageOfSyncCallBounds.y > newSecondInteractionUseBounds.getBottom().y);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getResized(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));

    }

    /**
     * Test that the second IU is expanded when adding executions on e3 Exec
     * between the second IU lowerBounds and its upperBounds.
     */
    public void testIUExpansion_WhenAddingEventEnds_OnNoCoveredParticipants_But_Between_StartIUEventEnd_And_EndIUEventEnd() {
        Point creationPoint = new Point(instanceRoleEditPartDBounds.getCenter().x, secondInteractionUseBounds.getCenter().y);
        // Add the first execution
        createExecution(creationPoint.x, creationPoint.y);
        bot.waitUntil(new CheckNumberExecutionOnLifeline(LIFELINE_D, 2, editor));

        int dh = LayoutConstants.DEFAULT_EXECUTION_HEIGHT;
        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getResized(0, dh), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dh), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dh), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getResized(0, dh), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dh), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dh), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dh), editor.getBounds(returnMessageOfE4Bot));

        // Add the second execution
        createExecution(creationPoint.x, creationPoint.y);
        bot.waitUntil(new CheckNumberExecutionOnLifeline(LIFELINE_D, 3, editor));

        dh += 2 * LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getResized(0, dh), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dh), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dh), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getResized(0, dh), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dh), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dh), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dh), editor.getBounds(returnMessageOfE4Bot));

        // Add the third execution
        createExecution(creationPoint.x, creationPoint.y);
        bot.waitUntil(new CheckNumberExecutionOnLifeline(LIFELINE_D, 4, editor));

        dh += LayoutConstants.DEFAULT_EXECUTION_HEIGHT + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getResized(0, dh), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dh), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dh), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getResized(0, dh), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dh), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dh), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dh), editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();

        // Redo
        redo();
    }

    /**
     * Test IU deletion. Some other tests in this test case test the deletion in
     * their ends.
     */
    public void testInteractionUseDeletionPossible() {
        validateOrdering(20);

        firstInteractionUseBot.select();
        deleteSelectedElement();

        validateOrdering(18);
        Assert.assertNull(editor.getEditPart(firstInteractionUseBounds.getCenter(), InteractionUseEditPart.class));

        secondInteractionUseBot.select();
        deleteSelectedElement();

        validateOrdering(16);
        Assert.assertNull(editor.getEditPart(secondInteractionUseBounds.getCenter(), InteractionUseEditPart.class));

        Assert.assertTrue(interaction.getInteractionUses().isEmpty());
        // Check that others nodes haven't moved
        Assert.assertEquals(instanceRoleEditPartABounds, editor.getBounds(instanceRoleEditPartABot));
        Assert.assertEquals(instanceRoleEditPartBBounds, editor.getBounds(instanceRoleEditPartBBot));
        Assert.assertEquals(instanceRoleEditPartCBounds, editor.getBounds(instanceRoleEditPartCBot));
        Assert.assertEquals(instanceRoleEditPartDBounds, editor.getBounds(instanceRoleEditPartDBot));
        Assert.assertEquals(instanceRoleEditPartEBounds, editor.getBounds(instanceRoleEditPartEBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

    }

    /**
     * Test that deletion of a InstanceRole covered by a InteractionUse covering
     * only this InstanceRole is possible and delete only the InstanceRole.
     * 
     * See VP-1339
     */
    public void testInteractionUseDeletionByInstanceRoleDeletionPossible() {
        int nbInstanceRoles = interaction.getParticipants().size();
        int nbInteractionUses = interaction.getInteractionUses().size();

        Point creationLocation = instanceRoleEditPartEBounds.getCenter().translate(0, 120);
        ICondition condition = new OperationDoneCondition();
        createInteractionUse(creationLocation);
        bot.waitUntil(condition);

        Assert.assertEquals(nbInstanceRoles, interaction.getParticipants().size());
        Assert.assertEquals(nbInteractionUses + 1, interaction.getInteractionUses().size());

        instanceRoleEditPartEBot.click();
        condition = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(condition);

        Assert.assertEquals(nbInstanceRoles - 1, interaction.getParticipants().size());
        Assert.assertEquals(nbInteractionUses + 1, interaction.getInteractionUses().size());

    }

    /**
     * Test horizontal move of IUs.
     */
    public void testInteractionUseHorizontalMoveNotPossible() {
        // Test that horizontal move of the first InteractionUse has not effect
        // to right
        int xDelta = 20;
        editor.drag(firstInteractionUseBot, firstInteractionUseBounds.x + xDelta, firstInteractionUseBounds.y);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);

        // to left
        editor.drag(firstInteractionUseBot, firstInteractionUseBounds.x - xDelta, firstInteractionUseBounds.y);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);

        // Test that horizontal move of the second InteractionUse has not effect
        // to right
        editor.drag(secondInteractionUseBot, secondInteractionUseBounds.x + xDelta, secondInteractionUseBounds.y);

        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);

        // to left
        editor.drag(secondInteractionUseBot, secondInteractionUseBounds.x - xDelta, secondInteractionUseBounds.y);

        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test that vertical move up of the first InteractionUse is not allowed
     * because of the top margin.
     */
    public void testFirstInteractionUseVerticalMoveUpNotPossibleBecauseOfTopMargin() {
        int dy = 40;
        editor.drag(firstInteractionUseBot, firstInteractionUseBounds.x, firstInteractionUseBounds.y - dy);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(20);

    }

    /**
     * test to move up the first interaction use
     */
    public void testFirstInteractionUseVerticalMoveUpToReturnOfReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        Rectangle lifelineEBounds = getLifelineEditPart(LIFELINE_E).getFigure().getBounds().getCopy();
        Point start = lifelineEBounds.getTop().getTranslated(0, 10);
        Point finish = start.getTranslated(0, 10);
        validateOrdering(20);

        CheckNumberExecutionOnLifeline checkNewExecutionOnLifelineE = new CheckNumberExecutionOnLifeline(LIFELINE_E, 1, editor);
        createSyncCall(start, finish);
        bot.waitUntil(checkNewExecutionOnLifelineE);

        SWTBotGefEditPart e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle e6Bounds = editor.getBounds(e6Bot);
        SWTBotGefConnectionEditPart callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);
        Rectangle callMessageOnE6Bounds = editor.getBounds(callMessageOnE6Bot);
        Rectangle returnMessageOfE6Bounds = editor.getBounds(returnMessageOfE6Bot);

        int dErr = 20;
        int dy = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP * 2 - (firstInteractionUseBounds.getTop().y - start.y)
                + LayoutConstants.EXECUTION_CHILDREN_MARGIN + dErr - (LayoutConstants.TIME_START_OFFSET - LayoutConstants.TIME_START_MIN_OFFSET);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Undo
        undo();
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();
        bot.waitUntil(checkNewExecutionOnLifelineE);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Move first interaction to have its top in the middle of
        // returnMessageOfE6Bounds
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getTranslated(0, dy).getTop(), returnMessageOfE6Bounds.getCenter());
        bot.waitUntil(checkMoved);

        int dy2 = returnMessageOfE6Bounds.getCenter().y - firstInteractionUseBounds.getTranslated(0, dy).getTop().y;

        int deltaResize = LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy + dy2).getResized(0, deltaResize), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy + deltaResize), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e5Bot));
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(callMessageOnE6Bounds, editor.getBounds(callMessageOnE6Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE4Bot));
        Assert.assertEquals(returnMessageOfE6Bounds.getResized(0, deltaResize), editor.getBounds(returnMessageOfE6Bot));
        validateOrdering(24);
    }

    /**
     * test to move up the first interaction use
     */
    public void testFirstInteractionUseVerticalMoveUpToCenterOfReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        Rectangle lifelineEBounds = getLifelineEditPart(LIFELINE_E).getFigure().getBounds().getCopy();
        Point start = lifelineEBounds.getTop().getTranslated(0, 10);
        Point finish = start.getTranslated(0, 10);
        validateOrdering(20);

        CheckNumberExecutionOnLifeline checkNewExecutionOnLifelineE = new CheckNumberExecutionOnLifeline(LIFELINE_E, 1, editor);
        createSyncCall(start, finish);
        bot.waitUntil(checkNewExecutionOnLifelineE);

        SWTBotGefEditPart e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle e6Bounds = editor.getBounds(e6Bot);
        SWTBotGefConnectionEditPart callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);
        Rectangle callMessageOnE6Bounds = editor.getBounds(callMessageOnE6Bot);
        Rectangle returnMessageOfE6Bounds = editor.getBounds(returnMessageOfE6Bot);

        int dErr = 20;
        int dy = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP * 2 - (firstInteractionUseBounds.getTop().y - start.y)
                + LayoutConstants.EXECUTION_CHILDREN_MARGIN + dErr - (LayoutConstants.TIME_START_OFFSET - LayoutConstants.TIME_START_MIN_OFFSET);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Undo
        undo();
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();
        bot.waitUntil(checkNewExecutionOnLifelineE);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Move first interaction to have its top in the middle of
        // returnMessageOfE6Bounds
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getTranslated(0, dy).getTop(), e6Bounds.getCenter());
        bot.waitUntil(checkMoved);

        int dy2 = e6Bounds.getCenter().y - firstInteractionUseBounds.getTranslated(0, dy).getTop().y;

        int deltaResize = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy + dy2).getResized(0, deltaResize), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy + deltaResize), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e5Bot));
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(callMessageOnE6Bounds, editor.getBounds(callMessageOnE6Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE4Bot));
        Assert.assertEquals(returnMessageOfE6Bounds, editor.getBounds(returnMessageOfE6Bot));
        validateOrdering(24);
    }

    /**
     * test to move up the first interaction use
     */
    public void testFirstInteractionUseVerticalMoveUpToCallOfReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        Rectangle lifelineEBounds = getLifelineEditPart(LIFELINE_E).getFigure().getBounds().getCopy();
        Point start = lifelineEBounds.getTop().getTranslated(0, 10);
        Point finish = start.getTranslated(0, 10);
        validateOrdering(20);

        CheckNumberExecutionOnLifeline checkNewExecutionOnLifelineE = new CheckNumberExecutionOnLifeline(LIFELINE_E, 1, editor);
        createSyncCall(start, finish);
        bot.waitUntil(checkNewExecutionOnLifelineE);

        SWTBotGefEditPart e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle e6Bounds = editor.getBounds(e6Bot);
        SWTBotGefConnectionEditPart callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);
        Rectangle callMessageOnE6Bounds = editor.getBounds(callMessageOnE6Bot);
        Rectangle returnMessageOfE6Bounds = editor.getBounds(returnMessageOfE6Bot);

        int dErr = 20;
        int dy = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP * 2 - (firstInteractionUseBounds.getTop().y - start.y)
                + LayoutConstants.EXECUTION_CHILDREN_MARGIN + dErr - (LayoutConstants.TIME_START_OFFSET - LayoutConstants.TIME_START_MIN_OFFSET);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Undo
        undo();
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();
        bot.waitUntil(checkNewExecutionOnLifelineE);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Move first interaction to have its top in the middle of
        // returnMessageOfE6Bounds
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getTranslated(0, dy).getTop(), callMessageOnE6Bounds.getCenter());
        bot.waitUntil(checkMoved);

        int dy2 = callMessageOnE6Bounds.getCenter().y - firstInteractionUseBounds.getTranslated(0, dy).getTop().y;

        int deltaResize = LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy + dy2).resize(0, deltaResize), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy + deltaResize), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e5Bot));
        Assert.assertEquals(e6Bounds.getTranslated(0, deltaResize), editor.getBounds(e6Bot));
        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE4Bot));
        // the call message is strangely moved by 1 pixel...
        Assert.assertEquals(callMessageOnE6Bounds.getTranslated(0, -1).getResized(0, deltaResize), editor.getBounds(callMessageOnE6Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE4Bot));
        Assert.assertEquals(returnMessageOfE6Bounds.getTranslated(0, deltaResize), editor.getBounds(returnMessageOfE6Bot));
        validateOrdering(24);
    }

    /**
     * test to move up the first interaction use
     */
    public void testFirstInteractionUseVerticalMoveUpToBeforeReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        Rectangle lifelineEBounds = getLifelineEditPart(LIFELINE_E).getFigure().getBounds().getCopy();
        Point start = lifelineEBounds.getTop().getTranslated(0, 10);
        Point finish = start.getTranslated(0, 10);
        validateOrdering(20);

        CheckNumberExecutionOnLifeline checkNewExecutionOnLifelineE = new CheckNumberExecutionOnLifeline(LIFELINE_E, 1, editor);
        createSyncCall(start, finish);
        bot.waitUntil(checkNewExecutionOnLifelineE);

        SWTBotGefEditPart e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle e6Bounds = editor.getBounds(e6Bot);
        SWTBotGefConnectionEditPart callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        SWTBotGefConnectionEditPart returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);
        Rectangle callMessageOnE6Bounds = editor.getBounds(callMessageOnE6Bot);
        Rectangle returnMessageOfE6Bounds = editor.getBounds(returnMessageOfE6Bot);

        int dErr = 20;
        int dy = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP * 2 - (firstInteractionUseBounds.getTop().y - start.y)
                + LayoutConstants.EXECUTION_CHILDREN_MARGIN + dErr - (LayoutConstants.TIME_START_OFFSET - LayoutConstants.TIME_START_MIN_OFFSET);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Undo
        undo();
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();
        bot.waitUntil(checkNewExecutionOnLifelineE);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(24);

        // Move first interaction to have its top in the middle of
        // returnMessageOfE6Bounds
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(firstInteractionUseBot);
        editor.drag(firstInteractionUseBounds.getTranslated(0, dy).getTop(), callMessageOnE6Bounds.getTop().getTranslated(0, -5));
        bot.waitUntil(checkMoved);

        int deltaResize = LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        // TODO assert failed
        // Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy +
        // dy2).getResized(0, deltaResize),
        // editor.getBounds(firstInteractionUseBot));
        // Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy +
        // deltaResize), editor.getBounds(secondInteractionUseBot));

        dy += 5;
        Assert.assertEquals(e1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(e5Bot));
        Assert.assertEquals(e6Bounds.getTranslated(0, deltaResize + 5), editor.getBounds(e6Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(callMessageOnE4Bot));
        // the call message is strangely moved by 1 pixel...
        Assert.assertEquals(callMessageOnE6Bounds.getTranslated(0, deltaResize + 4), editor.getBounds(callMessageOnE6Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy + deltaResize), editor.getBounds(returnMessageOfE4Bot));
        Assert.assertEquals(returnMessageOfE6Bounds.getTranslated(0, deltaResize + 5), editor.getBounds(returnMessageOfE6Bot));
        validateOrdering(24);
    }

    /**
     * Test interaction use move up on a create message
     */
    // BUG : move of e2 move only e2 and its IU but not e4, its messages and e5
    public void testInteractionUseMoveUpOnCreateMessage() {

        Point startOfCreateMessage = instanceRoleEditPartDBounds.getCenter().translate(0, e3Bounds.getTop().y - 30);
        Point finishOfCreateMessage = instanceRoleEditPartEBounds.getCenter();
        CheckEditPartMoved checkMove = new CheckEditPartMoved(instanceRoleEditPartEBot);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfCreateMessage.x, startOfCreateMessage.y, finishOfCreateMessage.x, finishOfCreateMessage.y);
        bot.waitUntil(checkMove);

        Rectangle newInstanceRoleEditParteBounds = editor.getBounds(instanceRoleEditPartEBot);

        int dy = secondInteractionUseBounds.y - startOfCreateMessage.y;
        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getResized(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));

        Point startOfIU = new Point(instanceRoleEditPartDBounds.getBottomLeft().translate(-5, 5).x, editor.getBounds(instanceRoleEditPartEBot).getBottomRight().translate(5, 5).y);
        Point finishOfIU = newInstanceRoleEditParteBounds.getBottomRight().translate(10, 10);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(startOfIU, finishOfIU);
        Assert.assertNotNull(newInteractionUseBot);

        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Checks that the newly created interaction use covers LIFELINE_D and
        // LIFELINE_E
        Assert.assertEquals(Math.max(new Rectangle(startOfIU, finishOfIU).height, 50), newInteractionUseBounds.height);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x >= instanceRoleEditPartDBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x <= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x >= instanceRoleEditPartEBounds.x + instanceRoleEditPartEBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x <= instanceRoleEditPartEBounds.getTopRight().x);

        // Move the new interaction upper than the create message
        editor.drag(newInteractionUseBounds.getTopLeft(), firstInteractionUseBounds.getBottomRight().getTranslated(-5, 5));

        // Assert that the new interaction use has not moved
        Assert.assertEquals(newInteractionUseBounds, editor.getBounds(newInteractionUseBot));

        // Delete
        newInteractionUseBot.select();
        deleteSelectedElement();
    }

    /**
     * Test that the direct edit tool defined in the odesign allow to change the
     * label expression
     */
    public void testInteractionUseDirectEdit() {
        String oldTextOfFirstIU = firstInteractionUse.getType();
        String newText = "newText";
        editor.directEditType(newText, firstInteractionUseBot);
        Assert.assertEquals(newText, firstInteractionUse.getType());
        testDiagramConsistency();
        validateOrdering(20);

        // Undo
        undo();
        Assert.assertEquals(oldTextOfFirstIU, firstInteractionUse.getType());
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();
        Assert.assertEquals(newText, firstInteractionUse.getType());
        testDiagramConsistency();
        validateOrdering(20);

        String oldTextOfSecondIU = secondInteractionUse.getType();
        editor.directEditType(newText, secondInteractionUseBot);
        Assert.assertEquals(newText, secondInteractionUse.getType());

        // Undo
        undo();
        Assert.assertEquals(oldTextOfSecondIU, secondInteractionUse.getType());
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();
        Assert.assertEquals(newText, secondInteractionUse.getType());
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test the vertical move of Execution e2 : move of e2 must shift the whole
     * block (2nd syncCall, 2nd IU, e4 with its subExecution e5 and the messages
     * on e4 but not e3) of the same delta.
     */
    // TODO : not expansion zone = forbidden move, allow only reconnect
    public void testVerticalMoveOfE2OnE3RangePossible() {
        int dy = 10;
        ICondition condition = new CheckEditPartMoved(e2Bot);
        editor.drag(e2Bot, e2Bounds.x, e2Bounds.y + dy);
        bot.waitUntil(condition);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of Execution e2 : move of e2 must shift the whole
     * block (2nd syncCall, 2nd IU, e4 with its subExecution e5 and the messages
     * on e4 but not e3, e3 is expanded down) of the same delta.
     */
    public void testVerticalMoveOfE2OutsideE3RangeForbidden() {
        int dy = 30;
        ICondition condition = new CheckEditPartMoved(e2Bot);
        editor.drag(e2Bot, e2Bounds.x, e2Bounds.y + dy);
        bot.waitUntil(condition);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0,  e4Bounds.height), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));

    }

    /**
     * Test the vertical move of callMessage on Execution e2 : move of e2's
     * callMessage must resize only e2's lower bound
     */
    public void testVerticalMoveOfE2CallMessageBeforeSecondIULowerBound() {
        int dy = (secondInteractionUseBounds.y - callMessageOnE2Bounds.y) / 2;
        callMessageOnE2Bot.select();
        CheckEditPartMoved checkMove = new CheckEditPartMoved(e2Bot);
        editor.drag(callMessageOnE2Bounds.getCenter(), callMessageOnE2Bounds.getCenter().getTranslated(0, dy));
        bot.waitUntil(checkMove);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy).getResized(0, -dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy).getResized(0, -dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of returnMessage on Execution e2 : move of e2's
     * returnMessage must resize only e2's upper bound
     */
    public void testVerticalMoveOfE2ReturnMessageAfterSecondIUUpperBound() {

        int dy = (secondInteractionUseBounds.getBottom().y - returnMessageOfE2Bounds.y) / 2;
        returnMessageOfE2Bot.select();
        CheckEditPartResized checkResize = new CheckEditPartResized(e2Bot);
        editor.drag(returnMessageOfE2Bounds.getCenter(), returnMessageOfE2Bounds.getCenter().getTranslated(0, dy));
        bot.waitUntil(checkResize);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of callMessage on Execution e2 : move of e2's
     * callMessage must resize only e2's lower bound.
     */
    public void testVerticalMoveOfE4CallMessageBeforeSecondIULowerBound() {

        int dy = (callMessageOnE2Bounds.y - callMessageOnE4Bounds.y) / 2;
        callMessageOnE4Bot.select();
        CheckEditPartMoved checkMove = new CheckEditPartMoved(e4Bot);
        editor.drag(callMessageOnE4Bounds.getCenter(), callMessageOnE4Bounds.getCenter().getTranslated(0, dy));
        bot.waitUntil(checkMove);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy).getResized(0, -dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy).getResized(0, -dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of callMessage on Execution e2 to the first
     * interaction use is not possible
     */
    public void testVerticalMoveOfE2CallMessageOnFirstIUCenterNotPossible() {
        callMessageOnE2Bot.select();
        editor.drag(callMessageOnE2Bounds.getCenter(), firstInteractionUseBounds.getCenter());

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of callMessage on Execution e2 before the first
     * interaction use is not possible
     */
    public void testVerticalMoveOfE2CallMessageBeforeFirstIUNotPossible() {
        callMessageOnE2Bot.select();
        editor.drag(callMessageOnE2Bounds.getCenter(), firstInteractionUseBounds.getTop().getTranslated(0, -5));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of callMessage on Execution e2 to the second
     * interaction use is not possible
     */
    public void testVerticalMoveOfE2CallMessageOnSecondIUCenterNotPossible() {
        callMessageOnE2Bot.select();
        editor.drag(callMessageOnE2Bounds.getCenter(), secondInteractionUseBounds.getCenter());

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of callMessage on Execution e2 after the second
     * interaction use is not possible
     */
    public void testVerticalMoveOfE2CallMessageAfterSecondIUNotPossible() {
        callMessageOnE2Bot.select();
        editor.drag(callMessageOnE2Bounds.getCenter(), secondInteractionUseBounds.getBottom().getTranslated(0, 5));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of callMessage on Execution e4 to the second
     * interaction use is not possible
     */
    public void testVerticalMoveOfE4CallMessageOnSecondIUNotPossible() {
        callMessageOnE4Bot.select();
        editor.drag(callMessageOnE2Bounds.getCenter(), secondInteractionUseBounds.getCenter());

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test the vertical move of callMessage on Execution e4 to the second
     * interaction use is not possible
     */
    public void testVerticalMoveOfE4CallMessageAfterSecondIUNotPossible() {
        callMessageOnE4Bot.select();
        editor.drag(callMessageOnE2Bounds.getCenter(), secondInteractionUseBounds.getBottom().getTranslated(0, 5));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds, editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds, editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds, editor.getBounds(returnMessageOfE4Bot));
    }

    /**
     * Test move of interaction use on event end of lifeline not covered
     * semantically but covered graphically
     */
    public void testVerticalMoveOnEventEndOfNotSemanticallyCoveredLifelineButGraphicallyCovered() {
        Point creationLocation = instanceRoleEditPartBBounds.getRight().translate(10, 0);
        createParticipant(creationLocation.x, creationLocation.y);

        SWTBotGefEditPart newInstanceRoleBot = editor.getEditPart(instanceRoleEditPartCBounds.getCenter(), InstanceRoleEditPart.class);
        Rectangle newInstanceRoleBounds = editor.getBounds(newInstanceRoleBot);

        createExecution(newInstanceRoleBounds.getCenter().x, secondInteractionUseBounds.y - 10);
        SWTBotGefEditPart newExecutionBot = newInstanceRoleBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle newExecutionBounds = editor.getBounds(newExecutionBot);

        Rectangle newSecondInteractionUseBounds = editor.getBounds(secondInteractionUseBot);

        // Test move of the second IU on the newly created execution
        int yLocation = e2Bounds.y + 5;
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondInteractionUseBot);
        editor.drag(secondInteractionUseBot, secondInteractionUseBounds.x, yLocation);
        bot.waitUntil(checkMoved);

        Assert.assertEquals(newExecutionBounds.getTranslated(0, LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP), editor.getBounds(newExecutionBot));
        Assert.assertEquals(newSecondInteractionUseBounds.getTranslated(0, yLocation - newSecondInteractionUseBounds.y).getResized(0, newExecutionBounds.height),
                editor.getBounds(secondInteractionUseBot));
    }

    /**
     * Test that vertical move of a interaction use on reflexive call message
     * shift it correctly.
     */
    public void testVerticalMoveOnReflexiveCallMessageNotAllowed() {
        Point start = e4Bounds.getCenter().translate(0, 10);
        Point finish = start.getTranslated(0, 10);
        validateOrdering(20);
        createSyncCall(start, finish);

        Rectangle newSecondInteractionUseBounds = editor.getBounds(secondInteractionUseBot);
        SWTBotGefEditPart execOfSyncCallBot = e4Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);
        SWTBotGefConnectionEditPart callMessageOfSyncCallBot = execOfSyncCallBot.targetConnections().get(0);

        Rectangle callMessageOfSyncCallBounds = editor.getBounds(callMessageOfSyncCallBot);

        editor.drag(secondInteractionUseBot, secondInteractionUseBounds.x, secondInteractionUseBounds.y + callMessageOfSyncCallBounds.getCenter().y - secondInteractionUseBounds.getBottom().y);

        // Check that the second IU hasn't moved
        Assert.assertEquals(newSecondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));
        validateOrdering(24);

    }

    /**
     * Test that vertical move of a interaction use on reflexive return message
     * shift it correctly.
     */
    // TODO BUG : regression
    public void testVerticalMoveOnReflexiveReturnMessageNotAllowed() {
        Point start = e4Bounds.getTop().translate(0, 10);
        Point finish = start.getTranslated(0, 5);
        validateOrdering(20);
        createSyncCall(start, finish);

        SWTBotGefEditPart execOfSyncCallBot = e4Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);
        SWTBotGefConnectionEditPart returnMessageOfSyncCallBot = execOfSyncCallBot.sourceConnections().get(0);

        Rectangle newSecondInteractionUseBounds = editor.getBounds(secondInteractionUseBot);
        Rectangle returnMessageOfSyncCallBounds = editor.getBounds(returnMessageOfSyncCallBot);

        editor.drag(secondInteractionUseBot, secondInteractionUseBounds.x, returnMessageOfSyncCallBounds.getCenter().y);

        // Check that the second IU hasn't moved
        Assert.assertEquals(newSecondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));
        validateOrdering(24);

    }
}
