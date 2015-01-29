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

import java.util.Collection;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsNotDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

import com.google.common.collect.Lists;

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
public class InteractionUseTwoClickCreationTests extends AbstractInteractionUseSequenceTests {

    /**
     * Waiting condition on interaction use creation
     * 
     * @author smonnier
     */
    private class CheckInteractionCreation extends DefaultCondition {

        /**
         * expected number of Interaction use on the diagram
         */
        private int expectedInteractionUses;

        public CheckInteractionCreation(int expectedInteractionUses) {
            super();
            this.expectedInteractionUses = expectedInteractionUses;
        }

        /**
         * {@inheritDoc}
         */
        public String getFailureMessage() {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        public boolean test() throws Exception {
            return interaction.getInteractionUses().size() == expectedInteractionUses;
        }

    }

    /**
     * Test the creation of interaction use in existing combined fragment
     * covering graphically not semantically covered participants.
     */
    public void testInteractionUseCreationInExistingCFCOnNotCoveredParticipantNotPossible() {
        Point start = new Point(instanceRoleEditPartABounds.x, e3Bounds.getBottom().y + 30);
        Point finish = new Point(instanceRoleEditPartEBounds.getRight().x, e3Bounds.getBottom().y + 30);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(start, finish);
        CombinedFragment newCombinedFragment = (CombinedFragment) ((CombinedFragmentEditPart) newCombinedFragmentBot.part()).resolveTargetSemanticElement();

        Point creationLocation = instanceRoleEditPartDBounds.getLeft().translate(-20, 0);
        ICondition condition = new CheckEditPartMoved(instanceRoleEditPartEBot);
        createParticipant(creationLocation.x, creationLocation.y);
        bot.waitUntil(condition);

        testDiagramConsistency();
        Assert.assertEquals(5, newCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantD));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantE));

        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);
        start = newCombinedFragmentBounds.getLeft();
        finish = newCombinedFragmentBounds.getRight();
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(start, finish);
        Assert.assertNotNull("Interaction use creation in combined fragment not covering all participants covered graphically should be possible and with the same covered participants",
                newInteractionUseBot);
        InteractionUse newInteractionUse = (InteractionUse) ((InteractionUseEditPart) newInteractionUseBot.part()).resolveTargetSemanticElement();

        Assert.assertEquals(5, newInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantD));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantE));

        // Undo
        undo();

        testDiagramConsistency();
        Assert.assertEquals(5, newCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantD));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantE));

        // Redo
        redo();

        testDiagramConsistency();
        Assert.assertEquals(5, newCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantD));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantE));

        Assert.assertEquals(5, newInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantD));
        Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participantE));

        // Delete
        newCombinedFragmentBot.select();
        deleteSelectedElement();

        testDiagramConsistency();
    }

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
     * Test that creation without covering lifelines is not possible.
     */
    public void testInteractionUserCreationWithTwoClickWithoutLifelineCoveredNotPossible() {
        Point start = new Point(5, 5);
        Point finish = instanceRoleEditPartCBounds.getBottomRight().translate(5, 5);
        validateOrdering(20);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(start, finish);
        Assert.assertNull("creation without selecting lifelines is not possible", newInteractionUseBot);
        validateOrdering(20);

    }

    /**
     * Test that creation to cover lifeline E is possible and shift down
     * syncCall of e3, second IU, syncCall of e4, and expand down e3 to insert
     * the newly created IU.
     */
    public void testInteractionUserCreationWithTwoClickToCoverLifelineE() {

        Point start = new Point(instanceRoleEditPartEBounds.x, e3Bounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN);
        Point finish = new Point(instanceRoleEditPartEBounds.getRight().x, e3Bounds.y + 10);
        validateOrdering(20);

        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartResized(e3Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(start, finish);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        Assert.assertNotNull("creation to cover lifeline E should be possible", newInteractionUseBot);
        validateOrdering(22);

        // Checks events under the newly created IU are correctly shifted
        // Checks that e1 is expanded down
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);
        assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, newInteractionUseBounds.height);

        // Checks events under the newly created IU are correctly shifted
        int dy = newInteractionUseBounds.bottom() - callMessageOnE4Bounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dy), editor.getBounds(e3Bot));
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

        newInteractionUseBot = editor.getEditPart(new Rectangle(start, finish).getCenter(), InteractionUseEditPart.class);
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

        newInteractionUseBot = editor.getEditPart(new Rectangle(start, finish).getCenter(), InteractionUseEditPart.class);
        Assert.assertNotNull("creation to cover lifeline E should be redone", newInteractionUseBot);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(22);

        ICondition checkEditPartIsNotDisplayed = new CheckEditPartIsNotDisplayed("ref.3", editor);
        // Deletion
        editor.click(newInteractionUseBot);
        deleteSelectedElement();
        bot.waitUntil(checkEditPartIsNotDisplayed);

        newInteractionUseBot = editor.getEditPart(new Rectangle(start, finish).getCenter(), InteractionUseEditPart.class);
        Assert.assertNull("Deletion of IU should works", newInteractionUseBot);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dy), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(20);

    }

    /**
     * Test interaction use creation above a create message
     */
    // TODO : why dErr?
    public void testInteractionUseCreationWithTwoClickAboveCreateMessage() {
        Point startOfCreateMessage = new Point(instanceRoleEditPartBBounds.getCenter().x, e3Bounds.y - 10);
        Point finishOfCreateMessage = instanceRoleEditPartEBounds.getCenter();

        ICondition condition = new CheckEditPartMoved(instanceRoleEditPartEBot);
        ICondition conditionE1 = new CheckEditPartMoved(e1Bot);
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfCreateMessage.x, startOfCreateMessage.y, finishOfCreateMessage.x, finishOfCreateMessage.y);
        bot.waitUntil(condition);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        int dyOfCreateMessage = editor.getBounds(instanceRoleEditPartEBot).getBottom().y - e3Bounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(returnMessageOfE4Bot));

        // Test
        Point startOfIU = instanceRoleEditPartDBounds.getBottomLeft().translate(-5, 5);
        Point finishOfIU = new Point(editor.getBounds(instanceRoleEditPartEBot).getTopRight().translate(5, -5).x, firstInteractionUseBounds.getTopRight().translate(5, -5).y);

        conditionE1 = new CheckEditPartMoved(e1Bot);
        conditionE2 = new CheckEditPartMoved(e2Bot);
        conditionE3 = new CheckEditPartMoved(e3Bot);
        conditionE4 = new CheckEditPartMoved(e4Bot);
        conditionE5 = new CheckEditPartMoved(e5Bot);

        createInteractionUse(startOfIU, finishOfIU);

        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        SWTBotGefEditPart newInteractionUseBot = editor.getEditPart(startOfIU.translate(30, 30), InteractionUseEditPart.class);
        Assert.assertNotNull(newInteractionUseBot);

        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Checks that the newly created interaction use covers only LIFELINE_D
        Assert.assertEquals(Math.max(new Rectangle(startOfIU, finishOfIU).height, LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT), newInteractionUseBounds.height);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x >= instanceRoleEditPartDBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x <= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        // Checks events under the newly created IU are correctly shifted
        int dyOfNewInteractionUse = LayoutConstants.TIME_START_OFFSET + LayoutConstants.EXECUTION_CHILDREN_MARGIN;

        Assert.assertEquals(e1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(returnMessageOfE4Bot));

        // Undo
        undo();

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(returnMessageOfE4Bot));

        // Redo
        redo();

        Assert.assertEquals(e1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(returnMessageOfE4Bot));

        ICondition checkEditPartIsNotDisplayed = new CheckEditPartIsNotDisplayed("ref.3", editor);
        // Delete
        newInteractionUseBot.click();
        deleteSelectedElement();

        bot.waitUntil(checkEditPartIsNotDisplayed);

        Assert.assertEquals(e1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dyOfNewInteractionUse), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyOfCreateMessage + dyOfNewInteractionUse), editor.getBounds(returnMessageOfE4Bot));

    }

    /**
     * Test interaction use creation on a create message
     */
    public void testInteractionUserCreationWithTwoClickOnCreateMessage() {
        Point startOfCreateMessage = new Point(instanceRoleEditPartBBounds.getCenter().x, e3Bounds.y - 10);
        Point finishOfCreateMessage = instanceRoleEditPartEBounds.getCenter();

        ICondition condition = new CheckEditPartMoved(instanceRoleEditPartEBot);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfCreateMessage.x, startOfCreateMessage.y, finishOfCreateMessage.x, finishOfCreateMessage.y);
        bot.waitUntil(condition);

        // Select the diagram itself for the future creation
        editor.select(editor.mainEditPart());

        int dyOfCreateMessage = editor.getBounds(instanceRoleEditPartEBot).getBottom().y - e3Bounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN;

        // Test
        Point startOfIU = new Point(instanceRoleEditPartDBounds.getBottom().translate(-5, 5).x, e1Bounds.getBottom().translate(5, 5).y);
        Point finishOfIU = editor.getBounds(instanceRoleEditPartEBot).getBottomRight().translate(10, 10);

        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);

        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(startOfIU, finishOfIU);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        Assert.assertNotNull(newInteractionUseBot);

        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Checks that the newly created interaction use covers only LIFELINE_D
        Assert.assertEquals(75, newInteractionUseBounds.height, 1);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x >= instanceRoleEditPartDBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x <= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        int expand = 65;
        Assert.assertEquals(e2Bounds.getTranslated(0, expand), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, expand), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, expand), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, expand), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, expand), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, expand), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, expand), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, expand), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, expand), editor.getBounds(returnMessageOfE4Bot));

        conditionE2 = new CheckEditPartMoved(e2Bot);
        conditionE3 = new CheckEditPartMoved(e3Bot);
        conditionE4 = new CheckEditPartMoved(e4Bot);
        conditionE5 = new CheckEditPartMoved(e5Bot);
        // Undo
        undo();
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyOfCreateMessage), editor.getBounds(returnMessageOfE4Bot));

        conditionE2 = new CheckEditPartMoved(e2Bot);
        conditionE3 = new CheckEditPartMoved(e3Bot);
        conditionE4 = new CheckEditPartMoved(e4Bot);
        conditionE5 = new CheckEditPartMoved(e5Bot);
        // Redo
        redo();
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, expand), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, expand), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, expand), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, expand), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, expand), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, expand), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, expand), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, expand), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, expand), editor.getBounds(returnMessageOfE4Bot));

        ICondition checkEditPartIsNotDisplayed = new CheckEditPartIsNotDisplayed("ref.3", editor);
        // Delete
        newInteractionUseBot.click();
        deleteSelectedElement();

        bot.waitUntil(checkEditPartIsNotDisplayed);

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, expand), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, expand), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, expand), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, expand), editor.getBounds(e5Bot));

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, expand), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, expand), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, expand), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds, editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, expand), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, expand), editor.getBounds(returnMessageOfE4Bot));

    }

    /**
     * Test that interaction use creation with a two click on existing
     * interaction use is not be possible.
     */
    public void testInteractionUseCreationWithTwoClickOnExistingIUWithSameCoveredParticipantsNotPossible() {
        Point startOfCFC = firstInteractionUseBounds.getLocation().translate(-10, 10);
        Point finishOfCFC = firstInteractionUseBounds.getBottomRight().translate(10, -10);
        validateOrdering(20);
        createInteractionUse(startOfCFC, finishOfCFC);
        validateOrdering(20);
        Assert.assertEquals(2, interaction.getInteractionUses().size());
    }

    /**
     * Test that interaction use creation with a two click on existing
     * interaction use will cover only participants without interaction use on
     * range.
     */
    public void testInteractionUseCreationWithTwoClickOnExistingIUWithCommonCoveredParticipants() {
        Point startOfCFC = new Point(instanceRoleEditPartEBounds.getRight().x, firstInteractionUseBounds.getCenter().y);
        Point finishOfCFC = firstInteractionUseBounds.getCenter();
        validateOrdering(20);

        createInteractionUseWithResult(startOfCFC, finishOfCFC);
        bot.waitUntil(new CheckInteractionCreation(3));
        Assert.assertEquals(3, interaction.getInteractionUses().size());

        validateNewInteractionUseCovering(3, Lists.newArrayList(participantE));
        validateOrdering(22);
    }

    /**
     * Test interaction use creation with two clicks to cover all existing
     * events under the first interaction use shift correctly all events under.
     */
    public void testInteractionUserCreationWithTwoClickToCoverLifelineFromAToEOnAllEventEndsAfterFirstInteractionUse() {

        // Delete the second interaction use to be able to create a new
        // interaction use covering all non interaction use event
        secondInteractionUseBot.select();

        deleteSelectedElement();

        Point start = new Point(instanceRoleEditPartABounds.x - 10, e1Bounds.y - LayoutConstants.EXECUTION_CHILDREN_MARGIN * 2);
        Point finish = new Point(instanceRoleEditPartEBounds.getRight().x + 10, e3Bounds.getBottom().y + 10);

        CheckEditPartMoved execMoveChecker = new CheckEditPartMoved(e1Bot);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(start, finish);
        Assert.assertNotNull("creation to cover lifeline existing InteractionUse should be possible", newInteractionUseBot);

        // Checks events under the newly created IU are correctly shifted
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        int expectedHeight = finish.y - start.y;
        Assert.assertEquals(expectedHeight, newInteractionUseBounds.height, 1);

        // Checks events under the newly created IU are correctly shifted
        int dy = newInteractionUseBounds.bottom() - callMessageOnE1Bounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE4Bot));
        bot.waitUntil(execMoveChecker);
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE4Bot));

    }

    /**
     * Validate the covering of the new interaction use
     * 
     * @param expectedNumberOfInteractionUses
     *            expected number of interaction uses on the diagram, including
     *            the new one
     * @param expectedCoveredParticipant
     *            expected collection of covered participants by the new
     *            interaction use
     */
    private void validateNewInteractionUseCovering(int expectedNumberOfInteractionUses, Collection<Participant> expectedCoveredParticipant) {
        Assert.assertEquals(expectedNumberOfInteractionUses, sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).size());
        InteractionUseEditPart newInteractionUseEditPart = null;
        for (SWTBotGefEditPart swtbotiuep : sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class))) {
            if (!swtbotiuep.part().equals(firstInteractionEditPart) && !swtbotiuep.part().equals(secondInteractionEditPart)) {
                // Finds the new InteractionUseEditPart
                newInteractionUseEditPart = (InteractionUseEditPart) swtbotiuep.part();
            }
        }
        Assert.assertNotNull(newInteractionUseEditPart);
        InteractionUse newInteractionUse = (InteractionUse) newInteractionUseEditPart.resolveTargetSemanticElement();
        Assert.assertEquals(expectedCoveredParticipant.size(), newInteractionUse.getCoveredParticipants().size());
        // Validate the participants covering
        for (Participant participant : expectedCoveredParticipant) {
            Assert.assertTrue(newInteractionUse.getCoveredParticipants().contains(participant));
        }
    }
}
