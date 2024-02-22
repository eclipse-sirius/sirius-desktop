/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberExecutionOnLifeline;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckSequenceMessageEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
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
 * @author smonnier
 */
public class CombinedFragmentsDisabledTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test that execution move on LIFELINE_C in first combined fragment's
     * header range is possible and that the header range is stable.
     */
    // TODO the combined fragment height should be stable
    public void testExecutionMoveInRangeOfHeaderOfExistingCFCStable() {
        Point creationLocation = new Point(instanceRoleEditPartCBounds.getCenter().x, secondCombinedFragmentBounds.getBottom().getTranslated(0, 10).y);
        CheckNumberExecutionOnLifeline checkExecutionCreated = new CheckNumberExecutionOnLifeline(LIFELINE_C, 1, editor);
        createExecution(creationLocation.x, creationLocation.y);
        bot.waitUntil(checkExecutionCreated);

        SWTBotGefEditPart e3Bot = instanceRoleEditPartCBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle e3Bounds = editor.getBounds(e3Bot);
        Rectangle copye3Bounds = e3Bounds.getCopy();

        CheckEditPartMoved checkMoved = new CheckEditPartMoved(e3Bot);
        editor.drag(e3Bounds.getTop(), firstCombinedFragmentBounds.getTop().getTranslated(0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2));
        bot.waitUntil(checkMoved);

        e3Bounds = editor.getBounds(e3Bot);

        checkMoved = new CheckEditPartMoved(e3Bot);
        editor.drag(e3Bounds.getTop(), e3Bounds.getTop().getTranslated(0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2));
        bot.waitUntil(checkMoved);

        testDiagramConsistency();
        assertDiagramElementsUnchanged();
        Assert.assertEquals(copye3Bounds, editor.getBounds(e3Bot));
    }

    /**
     * Test that destroy message move in first combined fragment's header range
     * is possible and that the header range is stable.
     */
    // TODO the combined fragment height should be stable
    // TODO not possible to move destroy message anymore?
    // TODO the same with create message
    public void testDestroyMessageMoveInRangeOfHeaderOfExistingCFCStable() {
        Point sourceLocation = new Point(instanceRoleEditPartCBounds.getCenter().x, secondCombinedFragmentBounds.getBottom().getTranslated(0, 10).y);
        Point targetLocation = new Point(instanceRoleEditPartDBounds.getCenter().x, secondCombinedFragmentBounds.getBottom().getTranslated(0, 10).y);

        CheckMessageEditPartIsDisplayed checkMessageCreated = new CheckMessageEditPartIsDisplayed("m_destroy1", editor);
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, sourceLocation, targetLocation);
        bot.waitUntil(checkMessageCreated);

        SWTBotGefEditPart lifelineCBot = instanceRoleEditPartCBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart destroyMessageBot = lifelineCBot.sourceConnections().get(0);
        Rectangle destroyMessageBounds = editor.getBounds(destroyMessageBot);

        int moveDelta = firstCombinedFragmentBounds.getTop().getTranslated(0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2).y - destroyMessageBounds.getCenter().y;

        CheckSequenceMessageEditPartMoved checkMoved = new CheckSequenceMessageEditPartMoved(destroyMessageBot);
        editor.drag(destroyMessageBounds.getCenter(), destroyMessageBounds.getCenter().getTranslated(0, moveDelta));
        bot.waitUntil(checkMoved);

        testDiagramConsistency();
        assertDiagramElementsUnchanged();
    }

    /**
     * Test that read message creation from a execution owning a combined
     * fragment to another covered lifeline is possible.
     */
    // TODO : at the execution creation, the event ends under this
    // insertion point are not exactly shifted of the height of the created
    // combined fragment.
    public void testReadMessageCreationFromExecutionOwingCombinedFragmentToAnotherCoveredLifeline() {
        Point creationLocation = instanceRoleEditPartDBounds.getCenter().translate(0, e1Bounds.y - instanceRoleEditPartDBounds.getCenter().y - 2);
        createExecution(creationLocation.x, creationLocation.y);
        SWTBotGefEditPart e3Bot = instanceRoleEditPartDBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle e3Bounds = editor.getBounds(e3Bot);

        int dy = e3Bounds.getBottom().y + LayoutConstants.EXECUTION_CHILDREN_MARGIN - e1Bounds.y;
        Assert.assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        Point start = new Point(instanceRoleEditPartDBounds.x, e3Bounds.getCenter().y);
        Point end = new Point(instanceRoleEditPartEBounds.getRight().x, e3Bounds.getCenter().y);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(start, end);
        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);

        int dh = newCombinedFragmentBounds.height;
        Assert.assertEquals(e1Bounds.getTranslated(0, dy + dh), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + dh), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dh), editor.getBounds(e3Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        e3Bounds = editor.getBounds(e3Bot);

        // Test
        createMessage(InteractionsConstants.READ_TOOL_ID, e3Bounds.getCenter(), e3Bounds.getCenter()
                .translate(instanceRoleEditPartEBounds.getCenter().x - instanceRoleEditPartDBounds.getCenter().x, 0));

        Assert.assertEquals(e1Bounds.getTranslated(0, dy + dh), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + dh), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dh), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Undo
        undo();
        // TODO : assert

        redo();
        // TODO : assert

    }

    /**
     * Test operand's combined fragment creation with a single click on operand
     * boundary.
     */
    // TODO operand size on creation issue see VP-1360
    public void testCombinedFragmentOperandCreationWithSingleClickOnFirstOperands() {
        Point creationLocation = firstOperandOfFirstCombinedFragmentBounds.getCenter();
        ICondition condition = new OperationDoneCondition();
        Option<SWTBotGefEditPart> newOperandOption = createCombinedFragmentOperandWithResult(creationLocation);
        bot.waitUntil(condition);

        Assert.assertTrue(newOperandOption.some());

        SWTBotGefEditPart newOperandBot = newOperandOption.get();
        bot.sleep(500);
        Rectangle newOperandBounds = editor.getBounds(newOperandBot);
        bot.sleep(500);

        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // FIXME find why there is a delta of 1 pixel
        Assert.assertEquals(
                new Rectangle(firstOperandOfFirstCombinedFragmentBounds.getBottomLeft(), secondOperandOfFirstCombinedFragmentBounds.getTranslated(-1, LayoutConstants.DEFAULT_OPERAND_HEIGHT - 1)
                        .getTopRight()), newOperandBounds);

        // Undo
        undo();
        assertNotChange();

        // Redo();
        redo();

        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // FIXME find why there is a delta of 1 pixel
        Assert.assertEquals(
                new Rectangle(firstOperandOfFirstCombinedFragmentBounds.getBottomLeft(), secondOperandOfFirstCombinedFragmentBounds.getTranslated(-1, LayoutConstants.DEFAULT_OPERAND_HEIGHT - 1)
                        .getTopRight()), newOperandBounds);

        // Delete
        condition = new OperationDoneCondition();
        editor.click(newOperandBounds.getCenter());
        deleteSelectedElement();
        bot.waitUntil(condition);

        Assert.assertEquals(2, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test that combined fragment creation with a two click on existing
     * interaction use is not be possible.
     */
    // TODO
    public void testCombinedFragmentOperandCreationWithTwoClickCoveringSeveralCCFC() {
        Point start = new Point(instanceRoleEditPartBBounds.getLeft().x, e2Bounds.y - 10);
        Point end = new Point(instanceRoleEditPartBBounds.getRight().x, secondOperandOfSecondCombinedFragmentBounds.getCenter().y);
        createCombinedFragmentOperand(start, end);

        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Undo
        undo();
        Assert.assertEquals(2, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Redo
        redo();
        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Delete

    }

    /**
     * Test that deletion of the single operand of a combined fragment is not
     * possible.
     */
    public void testCombinedFragmentOperandDeletion() {
        firstOperandOfFirstCombinedFragmentBot.click();
        ICondition operationDoneCondition = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(operationDoneCondition);

        // Test deletion of the first operand of the first combined fragment
        // with its execution
        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(1, firstCombinedFragment.getOwnedOperands().size());
        assertTrue(firstCombinedFragment.getOwnedOperands().contains(secondOperandOfFirstCombinedFragment));
        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(
                secondOperandOfFirstCombinedFragmentBounds.getCopy().setLocation(firstOperandOfFirstCombinedFragmentBounds.getLocation()).resize(0, firstOperandOfFirstCombinedFragmentBounds.height),
                editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Test that deletion of the last operand of the first combined fragment
        // is not possible
        secondOperandOfFirstCombinedFragmentBot.select();
        deleteSelectedElement();

        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(1, firstCombinedFragment.getOwnedOperands().size());
        assertTrue(firstCombinedFragment.getOwnedOperands().contains(secondOperandOfFirstCombinedFragment));
        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(
                secondOperandOfFirstCombinedFragmentBounds.getCopy().setLocation(firstOperandOfFirstCombinedFragmentBounds.getLocation()).resize(0, firstOperandOfFirstCombinedFragmentBounds.height),
                editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Test that deletion of the second operand of the second combined
        // fragment doesn't change the second combined fragment bounds and its
        // remaining operand is expanded to absorbs bounds of the deleted
        // operand.
        secondOperandOfSecondCombinedFragmentBot.select();
        deleteSelectedElement();

        Assert.assertEquals(1, secondCombinedFragment.getOwnedOperands().size());
        assertTrue(secondCombinedFragment.getOwnedOperands().contains(firstOperandOfSecondCombinedFragment));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getResized(0, secondOperandOfSecondCombinedFragmentBounds.height), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));

        // Undo the delete of the second operand of the second combined fragment
        undo();
        Assert.assertEquals(2, secondCombinedFragment.getOwnedOperands().size());
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        secondOperandOfSecondCombinedFragmentBot = secondCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(1);
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Undo the delete of the first operand of the first combined fragment
        undo();
        Assert.assertEquals(2, interaction.getExecutions().size());
        Assert.assertEquals(2, firstCombinedFragment.getOwnedOperands().size());
        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        firstOperandOfFirstCombinedFragmentBot = firstCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(0);
        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));

        // Redo the delete of the first operand of the first combined fragment
        redo();
        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(1, firstCombinedFragment.getOwnedOperands().size());
        assertTrue(firstCombinedFragment.getOwnedOperands().contains(secondOperandOfFirstCombinedFragment));
        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(
                secondOperandOfFirstCombinedFragmentBounds.getCopy().setLocation(firstOperandOfFirstCombinedFragmentBounds.getLocation()).resize(0, firstOperandOfFirstCombinedFragmentBounds.height),
                editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Redo the delete of the second operand of the second combined fragment
        redo();
        Assert.assertEquals(1, secondCombinedFragment.getOwnedOperands().size());
        assertTrue(secondCombinedFragment.getOwnedOperands().contains(firstOperandOfSecondCombinedFragment));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getResized(0, secondOperandOfSecondCombinedFragmentBounds.height), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test that combined fragment is resize up.
     */
    public void testCombinedFragmentVerticalResizeUp() {
        int dy = 20;
        secondCombinedFragmentBot.resize(PositionConstants.NORTH, secondCombinedFragmentBounds.width, secondCombinedFragmentBounds.height + dy);

        Assert.assertEquals(secondCombinedFragmentBounds.getResized(0, dy), editor.getBounds(secondCombinedFragmentBot));

        // TODO

    }
}
