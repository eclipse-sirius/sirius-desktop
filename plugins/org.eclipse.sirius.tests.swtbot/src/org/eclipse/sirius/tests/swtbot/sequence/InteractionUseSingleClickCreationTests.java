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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsDisplayed;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
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
public class InteractionUseSingleClickCreationTests extends AbstractInteractionUseSequenceTests {

    /**
     * Test that read message creation in range of an existing interaction use
     * from not covered participants.
     */
    public void testReadMessageCreationInRangeOfExistingIUFromNotCoveredParticipants() {
        int nbMessageBefore = interaction.getMessages().size();

        // Try to create message targeting Interaction use on executions
        Point start = new Point(instanceRoleEditPartDBounds.getCenter().x, secondInteractionUseBounds.getCenter().y);
        Point end = e5Bounds.getCenter();
        createMessage(InteractionsConstants.READ_TOOL_ID, start.x, start.y, end.x, end.y);

        // Try to create message targeting Interaction use on executions
        start = new Point(instanceRoleEditPartEBounds.getCenter().x, firstInteractionUseBounds.getCenter().y);
        end = e4Bounds.getCenter();
        createMessage(InteractionsConstants.READ_TOOL_ID, start.x, start.y, end.x, end.y);

        Assert.assertEquals(nbMessageBefore, interaction.getMessages().size());

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

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
     * Test if the InteractionUse CreationTool respect constraints :
     * <ol>
     * <ul>
     * creation with a single click
     * </ul>
     * </ol>
     */
    public void testInteractionUserCreationWithSingleClickOnExecution1() {
        // Test that creation on execution is possible
        Point singleClick = e1Bounds.getCenter();
        validateOrdering(20);

        ICondition checkEditPartIsDisplayed = new CheckEditPartIsDisplayed("ref.3", editor);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(singleClick);
        bot.waitUntil(checkEditPartIsDisplayed);

        validateOrdering(22);
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);

        // Checks that e1 is expanded down
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Checks events under the newly created IU are correclty shifted
        int dy = newInteractionUseBounds.height - e1Bounds.height / 2 + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();

        newInteractionUseBot = editor.getEditPart(singleClick, InteractionUseEditPart.class);
        Assert.assertNull("Undo have not deleted previously created IU", newInteractionUseBot);

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

        // Redo
        redo();

        newInteractionUseBot = editor.getEditPart(singleClick, InteractionUseEditPart.class);
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(22);

        // Deletion
        editor.click(newInteractionUseBot);
        deleteSelectedElement();

        newInteractionUseBot = editor.getEditPart(singleClick, InteractionUseEditPart.class);
        Assert.assertNull("Deletion of IU should works", newInteractionUseBot);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(20);

    }

    /**
     * Test that InteractionUse creation with on click at the low limit of a
     * execution is possible.
     */
    public void testInteractionUseCreationWithSingleClickOnLimitOfExecutionPossible() {
        // Test that creation on execution is possible
        Point singleClick = e3Bounds.getBottom();
        validateOrdering(20);

        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(singleClick);
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);
        validateOrdering(22);

        undo();
        validateOrdering(20);

        newInteractionUseBot = createInteractionUseWithResult(singleClick.getTranslated(0, 1));
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);
        validateOrdering(22);

        undo();
        validateOrdering(20);

        newInteractionUseBot = createInteractionUseWithResult(singleClick.getTranslated(0, -1));
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);
        validateOrdering(22);

        undo();
        validateOrdering(20);

        singleClick = e3Bounds.getTop();
        newInteractionUseBot = createInteractionUseWithResult(singleClick);
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);
        validateOrdering(22);

        undo();
        validateOrdering(20);

        newInteractionUseBot = createInteractionUseWithResult(singleClick.getTranslated(0, 1));
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);
        validateOrdering(22);

        undo();
        validateOrdering(20);

        newInteractionUseBot = createInteractionUseWithResult(singleClick.getTranslated(0, -1));
        Assert.assertNotNull("InteractionUse creation on execution should be possible", newInteractionUseBot);
        validateOrdering(22);

        undo();
        validateOrdering(20);
    }

    /**
     * Test if the InteractionUse CreationTool respect constraints :
     * <ol>
     * <ul>
     * creation with a single click
     * </ul>
     * </ol>
     */
    public void testInteractionUserCreationWithSingleClickOnDiagram() {
        // Test that creation on diagram is not possible
        Point singleClick = instanceRoleEditPartABounds.getBottomLeft().getTranslated(-5, 5);
        validateOrdering(20);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(singleClick);
        validateOrdering(20);
        Assert.assertNull("InteractionUse creation on diagram should not be possible", newInteractionUseBot);

    }

    /**
     * Test if the InteractionUse CreationTool respect constraints :
     * <ol>
     * <ul>
     * creation with a single click
     * </ul>
     * </ol>
     */
    public void testInteractionUserCreationWithSingleClickOnInstanceRole() {
        // Test that creation on instanceRole header is not possible
        Point singleClick = instanceRoleEditPartABounds.getCenter();
        validateOrdering(20);
        createInteractionUse(singleClick);

        //No new element
        validateOrdering(20);
    }

    /**
     * Test if the InteractionUse CreationTool respect constraints :
     * <ol>
     * <ul>
     * creation with a single click
     * </ul>
     * </ol>
     */
    public void testInteractionUserCreationWithSingleClickOnExistingIU() {
        // Test that creation on existing IU is not possible
        Point singleClick = firstInteractionUseBounds.getCenter();
        validateOrdering(20);
        createInteractionUse(singleClick);

        // No new elements
        validateOrdering(20);
    }

    /**
     * Test if the InteractionUse CreationTool respect constraints :
     * <ol>
     * <ul>
     * creation with a single click
     * </ul>
     * </ol>
     */
    public void testInteractionUserCreationWithSingleClickOnLifeline() {
        int dy = 2;
        // Test that creation on lifeline is possible
        Point singleClick = new Point(instanceRoleEditPartABounds.getCenter().x, firstInteractionUseBounds.y - dy);
        validateOrdering(20);
        createInteractionUse(singleClick);
        SWTBotGefEditPart newInteractionUseBot = editor.getEditPart(singleClick.getTranslated(0, 10), InteractionUseEditPart.class);
        validateOrdering(22);
        Assert.assertNotSame("InteractionUse creation on lifeline should be possible", firstInteractionUseBot, newInteractionUseBot);

        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Checks events under the newly created IU are correclty shifted
        dy = newInteractionUseBounds.height + LayoutConstants.EXECUTION_CHILDREN_MARGIN - dy/*
         * -
         * dy
         */;
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x >= instanceRoleEditPartABounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x >= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x <= instanceRoleEditPartABounds.getTopRight().x);
        Assert.assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, newInteractionUseBounds.height);
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

        // Undo
        undo();

        Assert.assertSame("Undo have not deleted previously created IU", firstInteractionUseBot, editor.getEditPart(singleClick.translate(0, 10), InteractionUseEditPart.class));

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

        // Redo
        redo();

        newInteractionUseBot = editor.getEditPart(singleClick.translate(0, 10), InteractionUseEditPart.class);
        Assert.assertNotSame("InteractionUse creation on lifeline should be redone", firstInteractionUseBot, newInteractionUseBot);

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
        validateOrdering(22);

        // Deletion
        editor.click(newInteractionUseBot);
        deleteSelectedElement();

        newInteractionUseBot = editor.getEditPart(singleClick.translate(0, 10), InteractionUseEditPart.class);
        Assert.assertNull("InteractionUse should be deleted", newInteractionUseBot);

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
        validateOrdering(20);

    }

    /**
     * Test that creation of a IU on a covered participant is well sized (until
     * the most imbricated Execution).
     */
    public void testIUCreationOnCoveredParticipantWithHighExecsImbrication() {
        Point creationPoint = e1Bounds.getCenter();

        // Add the first execution
        int nbExecs = 10;
        for (int i = 0; i < nbExecs; i++) {
            createExecution(creationPoint.x, creationPoint.y);
            creationPoint.x += LayoutConstants.DEFAULT_EXECUTION_WIDTH / 2;
            creationPoint.y += LayoutConstants.DEFAULT_EXECUTION_HEIGHT / 2;
        }
        Point iuCreationPoint = creationPoint;
        createInteractionUse(iuCreationPoint);
        validateOrdering(42);

        // Undo
        undo();
        validateOrdering(40);

        // Redo
        redo();
        validateOrdering(42);
    }
}
