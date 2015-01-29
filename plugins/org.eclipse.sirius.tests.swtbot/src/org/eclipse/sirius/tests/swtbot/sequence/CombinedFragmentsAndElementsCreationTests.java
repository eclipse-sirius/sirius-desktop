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

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * @author edugueperoux, smonnier
 */
public class CombinedFragmentsAndElementsCreationTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test that interaction use creation on LIFELINE_B in second combined
     * fragment's header is not possible.
     */
    public void testInteractionUseCreationOnHeaderOfExistingCFCNotPossible() {
        Point creationLocation = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createInteractionUse(creationLocation);
        SWTBotGefEditPart newInteractionUseBot = editor.getEditPart(creationLocation.getTranslated(0, 5), InteractionUseEditPart.class);

        Assert.assertNull(newInteractionUseBot);

        assertNotChange();
    }

    /**
     * Test that interaction use creation on LIFELINE_C in second combined
     * fragment's header range is not possible.
     */
    public void testInteractionUseCreationInRangeOfHeaderOfExistingCFCNotPossible() {
        Point creationLocation = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createInteractionUse(creationLocation);
        SWTBotGefEditPart newInteractionUseBot = editor.getEditPart(creationLocation.getTranslated(0, 5), InteractionUseEditPart.class);

        Assert.assertNull(newInteractionUseBot);

        assertNotChange();
    }

    /**
     * Test that execution creation on lifeline on the combined fragment's
     * header is not possible.
     */
    public void testExecutionCreationOnHeaderOfExistingCFCNotPossible() {
        Point creationLocation = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createExecution(creationLocation.x, creationLocation.y);

        assertNotChange();
    }

    /**
     * Test that execution creation on LIFELINE_C in second combined fragment's
     * header range is not possible.
     */
    public void testExecutionCreationInRangeOfHeaderOfExistingCFCNotPossible() {
        Point creationLocation = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createExecution(creationLocation.x, creationLocation.y);

        assertNotChange();
    }

    /**
     * Test that SyncCall creation from the second combined fragment header on
     * LIFELINE_B is not possible.
     */
    public void testSyncCallCreationOnHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that SyncCall creation in range of the second combined fragment
     * header on LIFELINE_B is not possible.
     */
    public void testSyncCallCreationOnRangeOfHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartDBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that ASyncCall creation from the second combined fragment header on
     * LIFELINE_B is not possible.
     */
    public void testASyncCallCreationOnHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.ASYNC_CALL_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that ASyncCall creation in range of the second combined fragment
     * header on LIFELINE_B is not possible.
     */
    public void testASyncCallCreationOnRangeOfHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartDBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.ASYNC_CALL_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that subExecution creation on combined fragment expand size of
     * combined fragment correctly.
     */
    public void testSubExecutionCreationInCombinedFragment() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
                java.lang.AssertionError: expected:<Rectangle(50.0, 200.0, 485.0, 510.0)> but was:<Rectangle(50.0, 200.0, 470.0, 490.0)>
                at org.junit.Assert.fail(Assert.java:88)
                at org.junit.Assert.failNotEquals(Assert.java:743)
                at org.junit.Assert.assertEquals(Assert.java:118)
                at org.junit.Assert.assertEquals(Assert.java:144)
                at org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsAndElementsCreationTests.testSubExecutionCreationInCombinedFragment(CombinedFragmentsAndElementsCreationTests.java:205)
            */
            return;
        }
        Point creationLocation = new Point(instanceRoleEditPartBBounds.getCenter().x, e2Bounds.getCenter().y);
        SWTBotGefEditPart newExecutionBot;
        Rectangle newExecutionBounds;

        ArrayList<Rectangle> combinedFragmentBounds = Lists.<Rectangle> newArrayList();

        final int NB_SUBEXECUTION_CREATION = TestsUtil.shouldSkipLongTests() ? 5 : 20;
        for (int i = 0; i < NB_SUBEXECUTION_CREATION; i++) {
            createExecution(creationLocation.x, creationLocation.y);
            newExecutionBot = editor.getEditPart(creationLocation.getTranslated(LayoutConstants.DEFAULT_EXECUTION_WIDTH, 5), ExecutionEditPart.class);
            newExecutionBounds = editor.getBounds(newExecutionBot);
            creationLocation = newExecutionBounds.getCenter();

            // Checks constraints
            Rectangle newFirstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);
            firstOperandOfFirstCombinedFragmentBounds = editor.getBounds(firstOperandOfFirstCombinedFragmentBot);
            e2Bounds = editor.getBounds(e2Bot);
            instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);

            // Validate execution expand when adding sub execution keep the main
            // execution in bounds of its parent operand
            Assert.assertTrue(firstOperandOfFirstCombinedFragmentBounds.contains(e2Bounds));

            // Validate that the right side of the combined fragment is at least
            // LayoutConstants.BORDER_FRAME_MARGIN away from the deepest
            // execution
            if (newExecutionBounds.getRight().x + LayoutConstants.BORDER_FRAME_MARGIN < firstCombinedFragmentBounds.getRight().x) {
                Assert.assertEquals(firstCombinedFragmentBounds.getTopRight(), newFirstCombinedFragmentBounds.getTopRight());
            } else {
                Assert.assertEquals(firstCombinedFragmentBounds.getTop().y, newFirstCombinedFragmentBounds.getTop().y);
                Assert.assertEquals(newExecutionBounds.getRight().getTranslated(LayoutConstants.BORDER_FRAME_MARGIN, 0).x, newFirstCombinedFragmentBounds.getRight().x);
            }

            // Validate that the gap between the combined fragment and the next
            // lifeline is less than minimum gap constant
            Assert.assertEquals(true, instanceRoleEditPartCBounds.getLeft().x >= newFirstCombinedFragmentBounds.getRight().x + LayoutConstants.LIFELINES_MIN_X_GAP);

            combinedFragmentBounds.add(newFirstCombinedFragmentBounds.getCopy());
        }
        for (int i = NB_SUBEXECUTION_CREATION - 1; i >= 0; i--) {
            Assert.assertEquals(combinedFragmentBounds.get(i), editor.getBounds(firstCombinedFragmentBot));
            undo();

            // Checks constraints
            Rectangle newFirstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);
            firstOperandOfFirstCombinedFragmentBounds = editor.getBounds(firstOperandOfFirstCombinedFragmentBot);
            e2Bounds = editor.getBounds(e2Bot);
            instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);

            // Validate execution expand when adding sub execution keep the main
            // execution in bounds of its parent operand
            Assert.assertTrue(firstOperandOfFirstCombinedFragmentBounds.contains(e2Bounds));

            // Validate that the gap between the combined fragment and the next
            // lifeline is less than minimum gap constant
            Assert.assertEquals(true, instanceRoleEditPartCBounds.getLeft().x >= newFirstCombinedFragmentBounds.getRight().x + LayoutConstants.LIFELINES_MIN_X_GAP);
        }

        assertNotChange();

        for (int i = 0; i < NB_SUBEXECUTION_CREATION; i++) {
            redo();

            Assert.assertEquals(combinedFragmentBounds.get(i), editor.getBounds(firstCombinedFragmentBot));

            // Checks constraints
            Rectangle newFirstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);
            firstOperandOfFirstCombinedFragmentBounds = editor.getBounds(firstOperandOfFirstCombinedFragmentBot);
            e2Bounds = editor.getBounds(e2Bot);
            instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);

            // Validate execution expand when adding sub execution keep the main
            // execution in bounds of its parent operand
            Assert.assertTrue(firstOperandOfFirstCombinedFragmentBounds.contains(e2Bounds));

            // Validate that the gap between the combined fragment and the next
            // lifeline is less than minimum gap constant
            Assert.assertEquals(true, instanceRoleEditPartCBounds.getLeft().x >= newFirstCombinedFragmentBounds.getRight().x + LayoutConstants.LIFELINES_MIN_X_GAP);
        }

        // Test deletion
        Rectangle secondCombinedFragmentBoundsCopy = editor.getBounds(secondCombinedFragmentBot).getCopy();
        Rectangle e1BoundsCopy = editor.getBounds(e1Bot).getCopy();
        firstCombinedFragmentBot.select();
        deleteSelectedElement();
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 1));

        // Checks
        Assert.assertEquals(1, sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).size());
        Assert.assertEquals(1, sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).size());
        Assert.assertEquals(secondCombinedFragmentBoundsCopy, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(e1BoundsCopy, editor.getBounds(e1Bot));
    }

    /**
     * Test that it is not possible de create an interaction use covering
     * another interaction use when this one is in a combined fragment.
     */
    public void testCreateInteractionUseCoveringInteractionUseInCombinedFragmentNotPossible() {
        Point createLocation = new Point(instanceRoleEditPartBBounds.getCenter().x, secondOperandOfSecondCombinedFragmentBounds.getCenter().y);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(createLocation, createLocation);
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Test
        Point startOfIU = newInteractionUseBounds.getTopLeft().translate(-10, 10);
        Point finishOfIU = newInteractionUseBounds.getBottomRight().translate(10, -10);
        createInteractionUse(startOfIU, finishOfIU);

        Interaction interaction = (Interaction) firstCombinedFragment.eContainer();
        Assert.assertEquals(1, interaction.getInteractionUses().size());
    }

    /**
     * Test that it is not possible de create an interaction use covering
     * another interaction use when this one is in a combined fragment and the
     * other lifeline in the combined fragment.
     */
    public void testCreateInteractionUseCoveringInteractionUseAndOtherLifelineInCombinedFragment() {
        Point createLocation = new Point(instanceRoleEditPartBBounds.getCenter().x, secondOperandOfSecondCombinedFragmentBounds.getCenter().y);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(createLocation, createLocation);
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Test
        Point startOfIU = new Point(instanceRoleEditPartABounds.getCenter().getTranslated(-10, 0).x, newInteractionUseBounds.getTopLeft().translate(0, 10).y);
        Point finishOfIU = newInteractionUseBounds.getBottomRight().translate(10, -10);
        createInteractionUse(startOfIU, finishOfIU);
        SWTBotGefEditPart newSecondInteractionUseBot = editor.getEditPart("ref.2").parent();
        Assert.assertNotNull(newSecondInteractionUseBot);
        Rectangle newSecondInteractionUseBounds = editor.getBounds(newSecondInteractionUseBot);

        Assert.assertEquals(new Dimension(LayoutConstants.DEFAULT_INTERACTION_USE_WIDTH * 2, LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT), newSecondInteractionUseBounds.getSize());

        // Check covering
        Interaction interaction = (Interaction) firstCombinedFragment.eContainer();
        Assert.assertEquals(2, interaction.getInteractionUses().size());
        Assert.assertEquals(1, interaction.getInteractionUses().get(0).getCoveredParticipants().size());
        Assert.assertEquals(participantB, interaction.getInteractionUses().get(0).getCoveredParticipants().get(0));
        Assert.assertEquals(1, interaction.getInteractionUses().get(1).getCoveredParticipants().size());
        Assert.assertEquals(participantA, interaction.getInteractionUses().get(1).getCoveredParticipants().get(0));

        // undo
        undo();
        undo();
        assertNotChange();

        // redo
        redo();
        redo();
        // Check covering
        Assert.assertEquals(2, interaction.getInteractionUses().size());
        Assert.assertEquals(1, interaction.getInteractionUses().get(0).getCoveredParticipants().size());
        Assert.assertEquals(participantB, interaction.getInteractionUses().get(0).getCoveredParticipants().get(0));
        Assert.assertEquals(1, interaction.getInteractionUses().get(1).getCoveredParticipants().size());
        Assert.assertEquals(participantA, interaction.getInteractionUses().get(1).getCoveredParticipants().get(0));
    }

    /**
     * Test that it is not possible de create an interaction use covering
     * another interaction use, and all lifeline, when this one is in a combined
     * fragment.
     */
    public void testCreateInteractionUseCoveringInteractionUseAndAllLifelines() {
        Point createLocation = new Point(instanceRoleEditPartBBounds.getCenter().x, secondOperandOfSecondCombinedFragmentBounds.getCenter().y);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(createLocation, createLocation);
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Test
        Point startOfIU = new Point(instanceRoleEditPartABounds.getCenter().getTranslated(-10, 0).x, newInteractionUseBounds.getTopLeft().translate(0, 10).y);
        Point finishOfIU = new Point(instanceRoleEditPartEBounds.getCenter().getTranslated(+10, 0).x, newInteractionUseBounds.getBottomRight().translate(0, -10).y);
        createInteractionUse(startOfIU, finishOfIU);
        SWTBotGefEditPart newSecondInteractionUseBot = editor.getEditPart("ref.2").parent();
        Assert.assertNotNull(newSecondInteractionUseBot);
        Rectangle newSecondInteractionUseBounds = editor.getBounds(newSecondInteractionUseBot);

        Assert.assertEquals(new Dimension(LayoutConstants.DEFAULT_INTERACTION_USE_WIDTH * 2, LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT), newSecondInteractionUseBounds.getSize());

        // Check covering
        Interaction interaction = (Interaction) firstCombinedFragment.eContainer();
        Assert.assertEquals(2, interaction.getInteractionUses().size());
        Assert.assertEquals(1, interaction.getInteractionUses().get(0).getCoveredParticipants().size());
        Assert.assertEquals(participantB, interaction.getInteractionUses().get(0).getCoveredParticipants().get(0));
        Assert.assertEquals(1, interaction.getInteractionUses().get(1).getCoveredParticipants().size());
        Assert.assertEquals(participantA, interaction.getInteractionUses().get(1).getCoveredParticipants().get(0));

        // undo
        undo();
        undo();
        assertNotChange();

        // redo
        redo();
        redo();
        // Check covering
        Assert.assertEquals(2, interaction.getInteractionUses().size());
        Assert.assertEquals(1, interaction.getInteractionUses().get(0).getCoveredParticipants().size());
        Assert.assertEquals(participantB, interaction.getInteractionUses().get(0).getCoveredParticipants().get(0));
        Assert.assertEquals(1, interaction.getInteractionUses().get(1).getCoveredParticipants().size());
        Assert.assertEquals(participantA, interaction.getInteractionUses().get(1).getCoveredParticipants().get(0));
    }

}
