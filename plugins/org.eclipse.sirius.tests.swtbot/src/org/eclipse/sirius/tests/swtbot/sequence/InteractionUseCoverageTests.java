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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.api.SequenceDiagramLayout;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
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
// TODO : tests with zoom
public class InteractionUseCoverageTests extends AbstractInteractionUseSequenceTests {

    /**
     * Checks the diagram consistency following semantic eventEnds ordering.
     */
    @Override
    public void testDiagramConsistency() {

        // Check InstanceRoles positions
        Assert.assertEquals(origin.x, instanceRoleEditPartABounds.x);
        Assert.assertEquals(instanceRoleEditPartABounds.getTopRight().x + (3 * LayoutConstants.LIFELINES_MIN_X_GAP), instanceRoleEditPartBBounds.x);
        Assert.assertEquals(instanceRoleEditPartBBounds.getTopRight().x + (3 * LayoutConstants.LIFELINES_MIN_X_GAP), instanceRoleEditPartCBounds.x);
        Assert.assertEquals(instanceRoleEditPartCBounds.getTopRight().x + (3 * LayoutConstants.LIFELINES_MIN_X_GAP), instanceRoleEditPartDBounds.x);
        Assert.assertEquals(instanceRoleEditPartDBounds.getTopRight().x + (3 * LayoutConstants.LIFELINES_MIN_X_GAP), instanceRoleEditPartEBounds.x);

        Assert.assertEquals(origin.y, instanceRoleEditPartABounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartBBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartCBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartDBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartEBounds.y);

        // Checks semantic covered lifelines
        Assert.assertEquals(4, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        Assert.assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        // Check IU bounds
        Assert.assertTrue("InteractionUse has bad left margin ", firstInteractionUseBounds.x >= instanceRoleEditPartABounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", firstInteractionUseBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad top margin ", firstInteractionUseBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        Assert.assertTrue("InteractionUse has bad right margin ", firstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", firstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);
        Assert.assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, firstInteractionUseBounds.height);

        Assert.assertTrue("InteractionUse has bad left margin ", secondInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", secondInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad top margin ", secondInteractionUseBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        Assert.assertTrue("InteractionUse has bad right margin ", secondInteractionUseBounds.getTopRight().x >= instanceRoleEditPartCBounds.x + instanceRoleEditPartCBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", secondInteractionUseBounds.getTopRight().x <= instanceRoleEditPartCBounds.getTopRight().x);
        Assert.assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, secondInteractionUseBounds.height);

        // Check event ends orders
        Assert.assertTrue("Exec e1 must be below first IU", e1Bounds.y > firstInteractionUseBounds.getBottomLeft().y);
        Assert.assertTrue("Follow the start of Exec e3", e3Bounds.y > e1Bounds.getBottomLeft().y);
        Assert.assertTrue("Follow the start of Exec e4", e4Bounds.y > e3Bounds.y);
        Assert.assertTrue("Follow the start of Exec e2", e2Bounds.y > e4Bounds.y);
        Assert.assertTrue("Follow the start of IU ref.2", secondInteractionUseBounds.y > e2Bounds.y);
        Assert.assertTrue("Follow the end of Exec e2", e2Bounds.getBottomLeft().y > secondInteractionUseBounds.getBottomLeft().y);
        Assert.assertTrue("Follow the start of Exec e5", e5Bounds.y > e2Bounds.getBottomLeft().y);
        Assert.assertTrue("Follow the end of Exec e4", e4Bounds.getBottomLeft().y > e5Bounds.getBottomLeft().y);
        Assert.assertTrue("Follow the end of Exec e4", e3Bounds.getBottomLeft().y > e4Bounds.getBottomLeft().y);
    }

    /**
     * Test interaction use move up on a create message
     */
    // BUG : move of e2 move only e2 and its IU but not e4, its messages and e5
    public void testInteractionUseCoveringCreateMessageNotPossible() {

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

        Point startOfIU = new Point(instanceRoleEditPartDBounds.getBottomLeft().translate(-5, 5).x, firstInteractionUseBounds.getBottomRight().translate(5, 5).y);
        Point finishOfIU = newInstanceRoleEditParteBounds.getBottomRight().translate(10, 10);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(startOfIU, finishOfIU);
        Assert.assertNotNull(newInteractionUseBot);

        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Checks that the newly created interaction use covers LIFELINE_D
        Assert.assertEquals(Math.max(new Rectangle(startOfIU, finishOfIU).height, 50), newInteractionUseBounds.height);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x >= instanceRoleEditPartDBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x <= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        // Undo
        undo();
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

        // Redo()
        redo();
        Assert.assertEquals(Math.max(new Rectangle(startOfIU, finishOfIU).height, 50), newInteractionUseBounds.height);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x >= instanceRoleEditPartDBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x <= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        // Delete
        newInteractionUseBot.select();
        deleteSelectedElement();
    }

    /**
     * Test that changing InteractionUse#getCoveredParticipants directly by
     * changing the semantic model, change the coveredParticipants graphically:
     * add participantE to the coveredParticipants list of firstInteractionUse
     */
    public void testCoveredParticipantsGraphicalChangeByAddingSemanticallyEToCoveredParticipants() {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstInteractionUse);
        CompoundCommand cc = new CompoundCommand();
        cc.append(AddCommand.create(transactionalEditingDomain, firstInteractionUse, InteractionsPackage.eINSTANCE.getInteractionUse_CoveredParticipants(), participantE));
        cc.append(new RefreshGraphicalCoverageCommand(transactionalEditingDomain));

        ICondition done = new OperationDoneCondition();
        transactionalEditingDomain.getCommandStack().execute(cc);
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();

        Rectangle newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartABounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartEBounds.x + instanceRoleEditPartEBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartEBounds.getTopRight().x);

        // Undo
        undo();
        newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);

        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartABounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        // Redo
        redo();

        newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);

        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartABounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartEBounds.x + instanceRoleEditPartEBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartEBounds.getTopRight().x);

    }

    /**
     * Test that changing InteractionUse#getCoveredParticipants directly by
     * changing the semantic model change the coveredParticipants graphically:
     * remove participantA to the coveredParticipants list of
     * firstInteractionUse
     */
    public void testCoveredParticipantsGraphicalChangeByRemovingSemanticallyAToCoveredParticipants() {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstInteractionUse);
        CompoundCommand cc = new CompoundCommand();
        cc.append(RemoveCommand.create(transactionalEditingDomain, firstInteractionUse, InteractionsPackage.eINSTANCE.getInteractionUse_CoveredParticipants(), participantA));
        cc.append(new RefreshGraphicalCoverageCommand(transactionalEditingDomain));

        ICondition done = new OperationDoneCondition();
        transactionalEditingDomain.getCommandStack().execute(cc);
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();

        Rectangle newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad top margin ", newFirstInteractionUseBounds.y >= instanceRoleEditPartBBounds.getBottomLeft().y);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        // Undo
        undo();

        newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);

        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartABounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        // Redo
        redo();

        newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);

        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

    }

    /**
     * Test that adding a participant between covered participant of a
     * interaction use doesn't add it to the covered participants list of this
     * IU.
     */
    public void testCoveredParticipantsChangeByAddingANewParticipant() {
        Point creationLocation = instanceRoleEditPartBBounds.getRight().translate(10, 0);
        createParticipant(creationLocation.x, creationLocation.y);

        // Checks that the InteractionUse.coveredParticipant feature hasn't
        // changed
        Assert.assertEquals(4, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        Assert.assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test that covered participant deletion resize the related interaction use
     * if deleted participant is graphically the leftmost or rightmost in y.
     */
    // TODO
    public void testCoveredParticipantsChangeByRemovingAExistingParticipant2() {
        // Remove LIFELINE_A
        instanceRoleEditPartABot.parent().select();
        deleteSelectedElement();

        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstInteractionUse);
        ICondition done = new OperationDoneCondition();
        transactionalEditingDomain.getCommandStack().execute(new RefreshGraphicalCoverageCommand(transactionalEditingDomain));
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();

        // New bounds if lost messages.
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);

        // Checks that the InteractionUse.coveredParticipant feature has changed
        Assert.assertEquals(3, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        Assert.assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        // Checks that the first IU is horizontally resized
        Rectangle newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        Assert.assertTrue("First InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("First InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("First InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("First InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);

        // Remove LIFELINE_C
        instanceRoleEditPartCBot.parent().select();
        deleteSelectedElement();
        done = new OperationDoneCondition();
        transactionalEditingDomain.getCommandStack().execute(new RefreshGraphicalCoverageCommand(transactionalEditingDomain));
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();

        // New bounds if lost messages.
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);
        instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);

        // Checks that the InteractionUse.coveredParticipant feature has changed
        Assert.assertEquals(2, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        Assert.assertEquals(1, secondInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));

        // Checks that the second IU is horizontally resized
        Rectangle newSecondInteractionUseBounds = editor.getBounds(secondInteractionUseBot);
        Assert.assertTrue("Second InteractionUse has bad left margin ", newSecondInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("Second InteractionUse has bad left margin ", newSecondInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("Second InteractionUse has bad right margin ", newSecondInteractionUseBounds.getTopRight().x >= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("Second InteractionUse has bad right margin ", newSecondInteractionUseBounds.getTopRight().x <= instanceRoleEditPartBBounds.getTopRight().x);
        // And also the first one
        newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        Assert.assertTrue("First InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("First InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("First InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("First InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);
    }

    /**
     * Test that covered participant deletion resize the related interaction use
     * if deleted participant is graphically the leftmost or rightmost in y.
     */
    // TODO
    public void testCoveredParticipantsChangeByRemovingAExistingParticipant() {
        // Remove LIFELINE_A
        callMessageOnE1Bot.select();
        deleteSelectedElement();
        returnMessageOfE1Bot.select();
        deleteSelectedElement();
        callMessageOnE2Bot.select();
        deleteSelectedElement();
        returnMessageOfE2Bot.select();
        deleteSelectedElement();
        instanceRoleEditPartABot.parent().select();
        deleteSelectedElement();

        TransactionalEditingDomain transactionalEditingDomain = localSession.getOpenedSession().getTransactionalEditingDomain();
        ICondition done = new OperationDoneCondition();
        transactionalEditingDomain.getCommandStack().execute(new RefreshGraphicalCoverageCommand(transactionalEditingDomain));
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();

        // Checks that the InteractionUse.coveredParticipant feature has changed
        Assert.assertEquals(3, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        Assert.assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        // Checks that the first IU is horizontally resized
        Rectangle newFirstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newFirstInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newFirstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);
        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));

        // Remove LIFELINE_C
        callMessageOnE4Bot.select();
        deleteSelectedElement();
        returnMessageOfE4Bot.select();
        deleteSelectedElement();
        instanceRoleEditPartCBot.parent().select();
        deleteSelectedElement();

        done = new OperationDoneCondition();
        transactionalEditingDomain.getCommandStack().execute(new RefreshGraphicalCoverageCommand(transactionalEditingDomain));
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();

        // Checks that the InteractionUse.coveredParticipant feature has changed
        Assert.assertEquals(2, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        Assert.assertEquals(1, secondInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));

        // Checks that the second IU is horizontally resized
        Rectangle newSecondInteractionUseBounds = editor.getBounds(secondInteractionUseBot);
        Assert.assertTrue("InteractionUse has bad left margin ", newSecondInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newSecondInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newSecondInteractionUseBounds.getTopRight().x >= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newSecondInteractionUseBounds.getTopRight().x <= instanceRoleEditPartBBounds.getTopRight().x);
        Assert.assertEquals(newFirstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
    }

    private class RefreshGraphicalCoverageCommand extends RecordingCommand {

        /**
         * @param transactionalEditingDomain
         */
        public RefreshGraphicalCoverageCommand(TransactionalEditingDomain transactionalEditingDomain) {
            super(transactionalEditingDomain);
        }

        @Override
        protected void doExecute() {
            new SequenceDiagramLayout((SequenceDiagramEditPart) sequenceDiagramBot.part()).refreshGraphicalCoverage();
        }
    }
}
