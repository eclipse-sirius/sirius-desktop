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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
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
 * @author edugueperoux, smonnier
 */
public class CombinedFragmentsTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test operand's combined fragment creation with a single click on operand
     * boundary.
     */
    public void testCombinedFragmentOperandMultipleSelectionDeletionNotPossible() {
        Point creationLocation = secondOperandOfFirstCombinedFragmentBounds.getCenter();
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        Option<SWTBotGefEditPart> newOperandOption1 = createCombinedFragmentOperandWithResult(creationLocation);
        bot.waitUntil(checkMoved);

        Assert.assertTrue(newOperandOption1.some());

        SWTBotGefEditPart newOperandBot1 = newOperandOption1.get();
        Rectangle newOperandBounds1 = editor.getBounds(newOperandBot1);

        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        int deltaY = LayoutConstants.DEFAULT_OPERAND_HEIGHT + LayoutConstants.EXECUTION_CHILDREN_MARGIN - secondCombinedFragmentBounds.y + firstCombinedFragmentBounds.bottom();
        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // FIXME find why there is a delta of 1 pixel
        Assert.assertEquals(new Rectangle(secondOperandOfFirstCombinedFragmentBounds.getBottomLeft(), firstCombinedFragmentBounds.getResized(-1, LayoutConstants.DEFAULT_OPERAND_HEIGHT - 1)
                .getBottomRight()), newOperandBounds1);

        creationLocation = newOperandBounds1.getCenter();
        checkMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        Option<SWTBotGefEditPart> newOperandOption2 = createCombinedFragmentOperandWithResult(creationLocation);
        bot.waitUntil(checkMoved);

        Assert.assertTrue(newOperandOption2.some());

        SWTBotGefEditPart newOperandBot2 = newOperandOption2.get();
        Rectangle newOperandBounds2 = editor.getBounds(newOperandBot2);

        Assert.assertEquals(4, firstCombinedFragment.getOwnedOperands().size());

        editor.select(newOperandBot1, newOperandBot2);
        checkDeleteMenuEnablement(false);

        // select another operand and validate nothing has been deleted
        editor.select(firstOperandOfFirstCombinedFragmentBot);

        Assert.assertEquals(4, firstCombinedFragment.getOwnedOperands().size());
    }

    /**
     * Test that vertical move down of the inner combined fragment in the second
     * combined fragment shift the inner combined fragments and its contents of
     * the same dy, and resize the container.
     */
    public void testSubCombinedFragmentVerticalMove() {
        ICondition done = new OperationDoneCondition();
        createCombinedFragment(secondOperandOfSecondCombinedFragmentBounds.getLeft(), secondOperandOfSecondCombinedFragmentBounds.getRight());
        bot.waitUntil(done);

        SWTBotGefEditPart thirdCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(2);
        Rectangle thirdCFBounds = editor.getBounds(thirdCombinedFragmentBot);

        done = new OperationDoneCondition();
        createExecution(getLifelineScreenX(LIFELINE_B), thirdCFBounds.getCenter().y);
        bot.waitUntil(done);

        SWTBotGefEditPart e3Bot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(2);
        Rectangle e3Bounds = editor.getBounds(e3Bot);

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
        firstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);
        secondCombinedFragmentBounds = editor.getBounds(secondCombinedFragmentBot);
        firstOperandOfSecondCombinedFragmentBounds = editor.getBounds(firstOperandOfSecondCombinedFragmentBot);
        secondOperandOfSecondCombinedFragmentBounds = editor.getBounds(secondOperandOfSecondCombinedFragmentBot);

        int dy = 20;
        done = new OperationDoneCondition();
        editor.drag(thirdCombinedFragmentBot, thirdCFBounds.x, thirdCFBounds.y + dy);
        bot.waitUntil(done);

        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(thirdCFBounds.getTranslated(0, dy), editor.getBounds(thirdCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getResized(0, thirdCFBounds.height), editor.getBounds(secondCombinedFragmentBot));
    }

    /**
     * Test CFC deletion. Some other tests in this test case test the deletion
     * in their ends.
     */
    public void testCombinedFragmentDeletionPossible() {

        int valueToAdd = 20;

        Interaction interaction = (Interaction) firstCombinedFragment.eContainer();

        firstCombinedFragmentBot.select();
        deleteSelectedElement();

        Assert.assertNull(editor.getEditPart(firstCombinedFragmentBounds.getCenter(), CombinedFragmentEditPart.class));

        secondCombinedFragmentBot.select();
        deleteSelectedElement();

        Assert.assertNull(editor.getEditPart(secondCombinedFragmentBounds.getCenter(), CombinedFragmentEditPart.class));

        // Check InstanceRoles positions
        Assert.assertEquals(origin.x, instanceRoleEditPartABounds.x);
        Assert.assertEquals(instanceRoleEditPartABounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP + valueToAdd, instanceRoleEditPartBBounds.x);
        Assert.assertEquals(instanceRoleEditPartBBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP + valueToAdd, instanceRoleEditPartCBounds.x);
        Assert.assertEquals(instanceRoleEditPartCBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP + valueToAdd, instanceRoleEditPartDBounds.x);
        Assert.assertEquals(instanceRoleEditPartDBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP + valueToAdd, instanceRoleEditPartEBounds.x);

        Assert.assertEquals(origin.y, instanceRoleEditPartABounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartBBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartCBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartDBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartEBounds.y);

        // Checks semantic covered participants
        assertTrue(interaction.getCombinedFragments().isEmpty());

        // TODO : Check bounds
    }

    /**
     * Test that deletion of a InstanceRole covered by a CombinedFragment
     * covering only this InstanceRole is possible and delete only the
     * InstanceRole.
     * 
     * See VP-1339
     */
    public void testCombinedFragmentDeletionByInstanceRoleDeletionPossible() {
        int nbInstanceRoles = interaction.getParticipants().size();
        int nbCombinedFragments = interaction.getCombinedFragments().size();

        Point creationLocation = instanceRoleEditPartEBounds.getCenter().translate(0, 100);
        ICondition condition = new OperationDoneCondition();
        createCombinedFragment(creationLocation);
        bot.waitUntil(condition);

        Assert.assertEquals(nbInstanceRoles, interaction.getParticipants().size());
        Assert.assertEquals(nbCombinedFragments + 1, interaction.getCombinedFragments().size());

        instanceRoleEditPartEBot.click();
        condition = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(condition);

        Assert.assertEquals(nbInstanceRoles - 1, interaction.getParticipants().size());
        Assert.assertEquals(nbCombinedFragments + 1, interaction.getCombinedFragments().size());

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
        SWTBotUtils.waitAllUiEvents();

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
        checkDeleteMenuEnablement(false);
        SWTBotUtils.waitAllUiEvents();

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
        operationDoneCondition = new OperationDoneCondition();
        deleteSelectedElement();
        bot.waitUntil(operationDoneCondition);
        SWTBotUtils.waitAllUiEvents();

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
     * Test that it is possible to delete a combined fragment if its only
     * operand is also selected (VP-1284).
     */
    public void testCombinedFragmentAndSingleOperandDeletion() {
        firstOperandOfFirstCombinedFragmentBot.click();
        CheckEditPartResized checkResized = new CheckEditPartResized(secondOperandOfFirstCombinedFragmentBot);
        deleteSelectedElement();
        bot.waitUntil(checkResized);

        // Test deletion of the first operand of the first combined fragment
        // with its execution
        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(1, firstCombinedFragment.getOwnedOperands().size());
        assertTrue(firstCombinedFragment.getOwnedOperands().contains(secondOperandOfFirstCombinedFragment));
        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, -firstOperandOfFirstCombinedFragmentBounds.height).resize(0, firstOperandOfFirstCombinedFragmentBounds.height),
                editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        editor.drag(firstCombinedFragmentBounds.getTopLeft().getTranslated(-10, -10), firstCombinedFragmentBounds.getBottomRight().getTranslated(10, 10));
        CheckNumberOfDescendants checkDescendant = new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 1);
        deleteSelectedElement();
        bot.waitUntil(checkDescendant);

        // Validate deletion
        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // undo
        undo();
        undo();

        // Reload the bots
        firstCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        firstOperandOfFirstCombinedFragmentBot = firstCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(0);
        secondOperandOfFirstCombinedFragmentBot = firstCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(1);

        assertNotChange();

        // redo
        redo();
        redo();

        // Validate deletion
        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test that it is possible to delete a combined fragment if all its
     * operands are also selected (VP-1284).
     */
    public void testCombinedFragmentAndManyOperandsDeletion() {
        editor.select(firstCombinedFragmentBot, firstOperandOfFirstCombinedFragmentBot, secondOperandOfFirstCombinedFragmentBot);
        CheckNumberOfDescendants checkDescendant = new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 1);
        deleteSelectedElement();
        bot.waitUntil(checkDescendant);

        // Validate deletion
        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // undo
        undo();

        assertNotChange();

        // redo
        redo();

        // Validate deletion
        Assert.assertEquals(1, interaction.getExecutions().size());
        assertTrue(interaction.getExecutions().contains(e1));
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test horizontal move of CFCs.
     */
    public void testCombinedFragmentHorizontalMoveNotPossible() {
        // Test that horizontal move of the first CombinedFragment has not
        // effect
        // to right
        int xDelta = 20;
        editor.drag(firstCombinedFragmentBot, firstCombinedFragmentBounds.x + xDelta, firstCombinedFragmentBounds.y);

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));

        // to left
        editor.drag(firstCombinedFragmentBot, firstCombinedFragmentBounds.x - xDelta, firstCombinedFragmentBounds.y);

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));

        // Test that horizontal move of the second CombinedFragment has not
        // effect
        // to right
        editor.drag(secondCombinedFragmentBot, secondCombinedFragmentBounds.x + xDelta, secondCombinedFragmentBounds.y);

        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));

        // to left
        editor.drag(secondCombinedFragmentBot, secondCombinedFragmentBounds.x - xDelta, secondCombinedFragmentBounds.y);

        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
    }

    /**
     * 
     */
    public void _testCombinedFragmentVerticalMoveDownWithEventEndsInSameRange() {
        // TODO
    }

    /**
     * Test that vertical move down of the first combined fragment on the second
     * combined fragment shift the first combined fragments and its contents of
     * the same dy, and shift the other events.
     */
    public void testCombinedFragmentVerticalMoveDownShiftCorrectlyBothCFCAndTheirContents() {

        int dy = secondCombinedFragmentBounds.y - firstCombinedFragmentBounds.y - 10;
        ICondition done = new OperationDoneCondition();
        editor.drag(firstCombinedFragmentBot, firstCombinedFragmentBounds.x, firstCombinedFragmentBounds.y + dy);
        bot.waitUntil(done);

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, firstCombinedFragmentBounds.height), editor.getBounds(secondCombinedFragmentBot));

    }

    /**
     * Test that operand's combined fragment is not possible.
     */
    public void testCombinedFragmentOperandVerticalMoveNotPossible() {
        int dy = 20;
        // First operand of first combined fragment
        // move down
        editor.drag(firstOperandOfFirstCombinedFragmentBot, firstOperandOfFirstCombinedFragmentBounds.x, firstOperandOfFirstCombinedFragmentBounds.y + dy);

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));

        // move up
        editor.drag(firstOperandOfFirstCombinedFragmentBot, firstOperandOfFirstCombinedFragmentBounds.x, firstOperandOfFirstCombinedFragmentBounds.y - dy);

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));

        // Second operand of first combined fragment
        // move down
        editor.drag(secondOperandOfFirstCombinedFragmentBot, secondOperandOfFirstCombinedFragmentBounds.x, secondOperandOfFirstCombinedFragmentBounds.y + dy);

        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));

        // move up
        editor.drag(secondOperandOfFirstCombinedFragmentBot, secondOperandOfFirstCombinedFragmentBounds.x, secondOperandOfFirstCombinedFragmentBounds.y - dy);

        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
    }

    /**
     * Test horizontal resizing of CFC is not possible.
     */
    public void testCombinedFragmentHorizontalResizeNotPossible() {
        // Test that horizontal move of the first CombinedFragment has not
        // effect
        // to right
        int dy = 20;
        firstCombinedFragmentBot.resize(PositionConstants.EAST, firstCombinedFragmentBounds.width + dy, firstCombinedFragmentBounds.height);

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));

        // to left
        firstCombinedFragmentBot.resize(PositionConstants.WEST, firstCombinedFragmentBounds.width + dy, firstCombinedFragmentBounds.height);

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));

        // Test that horizontal move of the second CombinedFragment has not
        // effect
        // to right
        secondCombinedFragmentBot.resize(PositionConstants.EAST, secondCombinedFragmentBounds.width + dy, secondCombinedFragmentBounds.height);

        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));

        // to left
        secondCombinedFragmentBot.resize(PositionConstants.WEST, secondCombinedFragmentBounds.width + dy, secondCombinedFragmentBounds.height);

        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));
    }

    /**
     * Test that combined fragment is resize up.
     */
    public void testCombinedFragmentVerticalResizeUp() {
        int dy = 10;
        // Select the combined fragment
        editor.click(secondCombinedFragmentBounds.getTop());
        bot.waitUntil(new CheckSelectedCondition(editor, "opt.2"));

        // Resize the combined fragment
        CheckEditPartResized checkResize = new CheckEditPartResized(secondCombinedFragmentBot);
        editor.drag(secondCombinedFragmentBounds.getTop(), secondCombinedFragmentBounds.getTop().getTranslated(0, -dy));
        bot.waitUntil(checkResize);

        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, -dy).getResized(0, dy), editor.getBounds(secondCombinedFragmentBot));

        // TODO complete this test.
    }

    /**
     * Test indirect resize down of the first combined fragment by move down a
     * execution in the first operand.
     */
    public void testCombinedFragmentIndirectVerticalResizeDownPossible() {
        Point source = e2Bounds.getCenter();
        Point target = new Point(instanceRoleEditPartABounds.getCenter().x, firstOperandOfFirstCombinedFragmentBounds.getCenter().y);
        createMessage(InteractionsConstants.CALL_TOOL_ID, source, target);
        //

        createMessage(InteractionsConstants.CALL_TOOL_ID, source.getTranslated(0, 5), target.getTranslated(0, 5));
        //

        // Test
        int dy = secondOperandOfSecondCombinedFragmentBounds.getCenter().y - e2Bounds.getBottom().y;
        e2Bot.resize(PositionConstants.SOUTH, e2Bounds.width, e2Bounds.height + dy);
    }

    /**
     * Test move up of a execution in first operand of first combined fragment
     * on lifeline A in the range of execution on lifeline B :
     * 
     */
    // BUG : reparent the execution on lifeline A as child of execution on
    // lifeline B.
    public void testExecMoveInCombinedFragment() {
        Point creationLocation = new Point(instanceRoleEditPartABounds.getCenter().x, e2Bounds.getBottom().y + 10);
        createExecution(creationLocation.x, creationLocation.y);

        SWTBotGefEditPart newExecutionBot = editor.getEditPart(creationLocation.getTranslated(0, 10), ExecutionEditPart.class);
        Assert.assertNotNull(newExecutionBot);
        Rectangle newExecutionBounds = editor.getBounds(newExecutionBot);

        // Test move up
        int dy = e2Bounds.y + 10 - newExecutionBounds.y;
        ICondition done = new OperationDoneCondition();
        editor.drag(newExecutionBot, instanceRoleEditPartABounds.getCenter().x, newExecutionBounds.y + dy);
        bot.waitUntil(done);

        Assert.assertEquals(newExecutionBounds.getTranslated(0, dy), editor.getBounds(newExecutionBot));
    }

    /**
     * Test that the direct edit tool defined in the odesign allow to change the
     * label expression of combined fragment.
     */
    public void testCombinedFragmentDirectEdit() {
        // On first combined fragment
        String oldTextOfFirstCFC = firstCombinedFragment.getOperator();
        String newText = "newText";
        editor.directEditType(newText, firstCombinedFragmentBot);
        Assert.assertEquals(newText, firstCombinedFragment.getOperator());

        // Undo
        undo();
        Assert.assertEquals(oldTextOfFirstCFC, firstCombinedFragment.getOperator());

        // Redo
        redo();
        Assert.assertEquals(newText, firstCombinedFragment.getOperator());

        // On second combined fragment
        String oldTextOfSecondCFC = secondCombinedFragment.getOperator();
        editor.directEditType(newText, secondCombinedFragmentBot);
        Assert.assertEquals(newText, secondCombinedFragment.getOperator());

        // Undo
        undo();
        Assert.assertEquals(oldTextOfSecondCFC, secondCombinedFragment.getOperator());

        // Redo
        redo();
        Assert.assertEquals(newText, secondCombinedFragment.getOperator());
    }

    /**
     * Test that the direct edit tool defined in the odesign allow to change the
     * label expression of operand.
     */
    public void testCombinedFragmentOperandDirectEdit() {
        // On first combined fragment
        String oldTextOfFirstCFOOfFirstCFC = firstOperandOfFirstCombinedFragment.getName();
        String newText = "newText";
        editor.directEditType(newText, firstOperandOfFirstCombinedFragmentBot);
        Assert.assertEquals(newText, firstOperandOfFirstCombinedFragment.getName());

        // Undo
        undo();
        Assert.assertEquals(oldTextOfFirstCFOOfFirstCFC, firstOperandOfFirstCombinedFragment.getName());

        // Redo
        redo();
        Assert.assertEquals(newText, firstOperandOfFirstCombinedFragment.getName());

        // On second combined fragment
        String oldTextOfFirstCFOOfSecondCFC = firstOperandOfSecondCombinedFragment.getName();
        editor.directEditType(newText, firstOperandOfSecondCombinedFragmentBot);
        Assert.assertEquals(newText, firstOperandOfSecondCombinedFragment.getName());

        // Undo
        undo();
        Assert.assertEquals(oldTextOfFirstCFOOfSecondCFC, firstOperandOfSecondCombinedFragment.getName());

        // Redo
        redo();
        Assert.assertEquals(newText, firstOperandOfSecondCombinedFragment.getName());
    }

    /**
     * Test that destroy message creation is not possible from a covered
     * participant to a not covered participant.
     */
    public void testResizeCombinedFragmentCoveringLifelineHavingCreateDestroyMessageInOperand() {
        // Remove the executions, the second combined fragment and the second
        // operand of the first combined fragment to be able to add create and
        // destroy message
        editor.click(e1Bounds.getCenter());
        deleteSelectedElement();
        editor.click(e2Bounds.getCenter());
        deleteSelectedElement();
        editor.click(secondCombinedFragmentBounds.getTop());
        deleteSelectedElement();
        editor.click(secondOperandOfFirstCombinedFragmentBounds.getCenter());
        deleteSelectedElement();

        // Add create message in the combined fragment
        Point createMessageSource = new Point(instanceRoleEditPartABounds.getCenter().x, firstOperandOfFirstCombinedFragmentBounds.getTop().getTranslated(0, 10).y);
        Point createMessageTarget = new Point(instanceRoleEditPartBBounds.getCenter().x, firstOperandOfFirstCombinedFragmentBounds.getTop().getTranslated(0, 10).y);
        createMessage(InteractionsConstants.CREATE_TOOL_ID, createMessageSource, createMessageTarget);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed("m_create1", editor));

        // Add destroy message in the combined fragment
        Point destroyMessageSource = new Point(instanceRoleEditPartABounds.getCenter().x, firstCombinedFragmentBounds.getBottom().getTranslated(0, -10).y);
        Point destroyMessageTarget = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.getBottom().getTranslated(0, -10).y);
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, destroyMessageSource, destroyMessageTarget);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed("m_destroy2", editor));

        // Save messages positions
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        SWTBotGefEditPart lifelineABot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefConnectionEditPart createMessageBot = lifelineABot.sourceConnections().get(0);
        Rectangle createMessageBounds = editor.getBounds(createMessageBot);
        SWTBotGefConnectionEditPart destroyMessageBot = lifelineABot.sourceConnections().get(1);
        Rectangle destroyMessageBounds = editor.getBounds(destroyMessageBot);

        // Select the combined fragment
        editor.click(firstCombinedFragmentBounds.getTop());
        bot.waitUntil(new CheckSelectedCondition(editor, "opt.1"));

        // Resize the combined fragment
        int yDelta = 200;
        CheckEditPartResized checkResize = new CheckEditPartResized(firstCombinedFragmentBot);
        editor.drag(firstCombinedFragmentBounds.getBottom(), firstCombinedFragmentBounds.getBottom().getTranslated(0, yDelta));
        bot.waitUntil(checkResize);

        // Validate stability
        assertEquals(firstCombinedFragmentBounds.getResized(0, yDelta), editor.getBounds(firstCombinedFragmentBot));
        assertEquals(createMessageBounds, editor.getBounds(createMessageBot));
        assertEquals(destroyMessageBounds, editor.getBounds(destroyMessageBot));
        validateOrdering(7);

        // Undo all
        for (int i = 0; i < 7; i++) {
            undo();
        }

        // Refresh bot
        secondOperandOfFirstCombinedFragmentBot = firstCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(1);

        // Validate stability
        assertNotChange();
        validateOrdering(12);

        // Redo all
        for (int i = 0; i < 7; i++) {
            redo();
        }

        // Validate stability
        assertEquals(firstCombinedFragmentBounds.getResized(0, yDelta), editor.getBounds(firstCombinedFragmentBot));
        assertEquals(createMessageBounds, editor.getBounds(createMessageBot));
        assertEquals(destroyMessageBounds, editor.getBounds(destroyMessageBot));
        validateOrdering(7);
    }

    /**
     * Test that vertical move of a combined fragment is not limited by its
     * messages.
     */
    public void testCombinedFragmentWithMessageMove() {
        // Add a message in the second combined fragment
        Point createMessageSource = new Point(instanceRoleEditPartABounds.getCenter().x, firstOperandOfSecondCombinedFragmentBounds.getTop().getTranslated(0, 10).y);
        Point createMessageTarget = new Point(instanceRoleEditPartBBounds.getCenter().x, firstOperandOfSecondCombinedFragmentBounds.getTop().getTranslated(0, 10).y);
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, createMessageSource, createMessageTarget);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed("m1", editor));

        // Delete the first combined fragment to have free space
        editor.click(firstCombinedFragmentBounds.getTopLeft());
        bot.waitUntil(new CheckSelectedCondition(editor, firstCombinedFragmentEditPart));
        deleteSelectedElement();

        // Validate that the execution has not moved
        Assert.assertEquals("The first execution bounds should be unchanged", e1Bounds, editor.getBounds(e1Bot));

        // Update the second combined fragments bounds that have been extended
        // because of inner message creation
        secondCombinedFragmentBounds = editor.getBounds(secondCombinedFragmentBot);

        // Save bounds of the new sync call
        Rectangle boundsE2 = assertExecutionHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(0, createMessageSource.y, 0, 50), false);
        Rectangle boundsM1 = assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE, new Rectangle(new Point(0, getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE)), new Point(0,
                getSequenceMessageScreenVerticalPosition(FIRST_MESSAGE))), true, false);
        Rectangle boundsM2 = assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, new Rectangle(new Point(0, getReturnSyncCallScreenPosition(LIFELINE_B, 1)), new Point(0,
                getReturnSyncCallScreenPosition(LIFELINE_B, 1))), false, false);

        // Slightly move up
        int dy = -10;
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        editor.drag(secondCombinedFragmentBot, secondCombinedFragmentBounds.x, secondCombinedFragmentBounds.y + dy);
        bot.waitUntil(checkEditPartMoved);

        // Sets expected bounds for the sync call
        boundsE2.translate(0, dy);
        boundsM1.translate(0, dy);
        boundsM2.translate(0, dy);

        // Validate bounds
        Assert.assertEquals("The first execution bounds should be unchanged", e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM2, false, true);

        // Bigger move up to be just under E1
        int dy2 = e1Bounds.getBottom().y - editor.getBounds(secondCombinedFragmentBot).y + 10;
        checkEditPartMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        editor.drag(secondCombinedFragmentBot, secondCombinedFragmentBounds.x, secondCombinedFragmentBounds.getTranslated(0, dy).y + dy2);
        bot.waitUntil(checkEditPartMoved);

        // Sets expected bounds for the sync call
        boundsE2.translate(0, dy2);
        boundsM1.translate(0, dy2);
        boundsM2.translate(0, dy2);

        // Validate bounds
        Assert.assertEquals("The first execution bounds should be unchanged", e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(secondCombinedFragmentBot));
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM2, false, true);

        // Slightly move down
        int dy3 = 10;
        checkEditPartMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        editor.drag(secondCombinedFragmentBot, secondCombinedFragmentBounds.x, secondCombinedFragmentBounds.getTranslated(0, dy + dy2).y + dy3);
        bot.waitUntil(checkEditPartMoved);

        // Sets expected bounds for the sync call
        boundsE2.translate(0, dy3);
        boundsM1.translate(0, dy3);
        boundsM2.translate(0, dy3);

        // Validate bounds
        Assert.assertEquals("The first execution bounds should be unchanged", e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy + dy2 + dy3), editor.getBounds(secondCombinedFragmentBot));
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM2, false, true);

        // Bigger move down to be moved where it was at the beginning
        int dy4 = secondCombinedFragmentBounds.getTop().y - editor.getBounds(secondCombinedFragmentBot).y;
        checkEditPartMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        editor.drag(secondCombinedFragmentBot, secondCombinedFragmentBounds.x, secondCombinedFragmentBounds.getTranslated(0, dy + dy2 + dy3).y + dy4);
        bot.waitUntil(checkEditPartMoved);

        // Sets expected bounds for the sync call
        boundsE2.translate(0, dy4);
        boundsM1.translate(0, dy4);
        boundsM2.translate(0, dy4);

        // Validate bounds
        Assert.assertEquals("The first execution bounds should be unchanged", e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy + dy2 + dy3 + dy4), editor.getBounds(secondCombinedFragmentBot));
        assertExecutionHasValidScreenBounds(LIFELINE_B, 1, boundsE2);
        assertNamedMessageHasValidScreenBounds(FIRST_MESSAGE, boundsM1, true);
        assertReturnMessageHasValidScreenBounds(LIFELINE_B, 1, boundsM2, false, true);
    }

}
