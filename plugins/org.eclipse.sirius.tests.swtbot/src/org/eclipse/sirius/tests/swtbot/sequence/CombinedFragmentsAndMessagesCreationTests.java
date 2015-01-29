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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckMessageEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * @author edugueperoux, smonnier
 */
public class CombinedFragmentsAndMessagesCreationTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test that it is not possible de create an interaction use covering
     * another interaction use, and all lifeline, when this one is in a combined
     * fragment.
     */
    public void testCreateMessageInCombinedFragmentCreatedOnExecution() {
        Point sourceLocation = e1Bounds.getLeft().getTranslated(-10, 0);
        Point targetLocation = new Point(instanceRoleEditPartCBounds.getCenter().getTranslated(10, 0).x, e1Bounds.getCenter().getTranslated(0, 10).y);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(sourceLocation, targetLocation);
        Assert.assertNotNull(newCombinedFragmentBot);
        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);

        int dy = newCombinedFragmentBounds.bottom() - e1Bounds.bottom() + LayoutConstants.EXECUTION_CHILDREN_MARGIN;

        // Validate diagram

        Assert.assertEquals(instanceRoleEditPartBBounds.getLeft().x, newCombinedFragmentBounds.getLeft().x);
        Assert.assertEquals(instanceRoleEditPartCBounds.getRight().x, newCombinedFragmentBounds.getRight().x);
        Assert.assertEquals(LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT, newCombinedFragmentBounds.height);
        Assert.assertEquals(sourceLocation.y, newCombinedFragmentBounds.getTop().y);

        Assert.assertEquals(e1Bounds.getResized(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        validateOrdering(15);
        Point sourceMessageLocation = new Point(instanceRoleEditPartCBounds.getCenter().x, e1Bounds.getResized(0, dy).getCenter().y);
        Point targetMessageLocation = e1Bounds.getResized(0, dy).getCenter();

        // add sync call in new CF
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, sourceMessageLocation, targetMessageLocation);
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, ExecutionEditPart.class, 3));

        SWTBotGefEditPart newExecutionBot = e1Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle newExecutionBounds = editor.getBounds(newExecutionBot);

        int dy2 = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT - (newCombinedFragmentBounds.getBottom().y - sourceMessageLocation.y) + LayoutConstants.EXECUTION_CHILDREN_MARGIN;

        // Validate diagram
        Assert.assertEquals(1, newExecutionBot.sourceConnections().size());
        Assert.assertEquals(1, newExecutionBot.targetConnections().size());

        Assert.assertEquals(sourceMessageLocation.y, newExecutionBounds.getTop().y);
        Assert.assertEquals(LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT, newExecutionBounds.height);

        Assert.assertEquals(newCombinedFragmentBounds.getResized(0, dy2), editor.getBounds(newCombinedFragmentBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy + dy2), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + dy2), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        validateOrdering(19);

        // Undo
        undo();
        undo();

        assertNotChange();

        // Redo
        redo();
        redo();

        dy2 = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT - (newCombinedFragmentBounds.getBottom().y - sourceMessageLocation.y) + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        newCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);

        // Validate diagram
        Assert.assertEquals(1, newExecutionBot.sourceConnections().size());
        Assert.assertEquals(1, newExecutionBot.targetConnections().size());

        Assert.assertEquals(sourceMessageLocation.y, newExecutionBounds.getTop().y);
        Assert.assertEquals(LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT, newExecutionBounds.height);

        Assert.assertEquals(newCombinedFragmentBounds.getResized(0, dy2), editor.getBounds(newCombinedFragmentBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy + dy2), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy + dy2), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, dy + dy2), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test that Create message creation from the second combined fragment
     * header on LIFELINE_B is not possible.
     */
    public void testCreateMessageCreationOnHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = instanceRoleEditPartEBounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that Create message creation in range of the second combined
     * fragment header on LIFELINE_B is not possible.
     */
    public void testCreateMessageCreationOnRangeOfHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = instanceRoleEditPartEBounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that Destroy message creation from the second combined fragment
     * header on LIFELINE_B is not possible.
     */
    public void testDestroyMessageCreationOnHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that Destroy message creation in range of the second combined
     * fragment header on LIFELINE_B is not possible.
     */
    public void testDestroyMessageCreationOnRangeOfHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartDBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.DESTROY_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that Read message creation from the second combined fragment header
     * on LIFELINE_B is not possible.
     */
    public void testReadMessageCreationOnHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.READ_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that Read message creation in range of the second combined fragment
     * header on LIFELINE_B is not possible.
     */
    public void testReadMessageCreationOnRangeOfHeaderOfExistingCFCNotPossible() {
        Point start = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        Point end = new Point(instanceRoleEditPartDBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createMessage(InteractionsConstants.READ_TOOL_ID, start, end);

        assertNotChange();
    }

    /**
     * Test that read message creation in range of an existing combined fragment
     * from not covered participants.
     */
    public void testReadMessageCreationInRangeOfExistingCFCFromNotCoveredParticipants() {
        int nbMessageBefore = interaction.getMessages().size();

        Point start = new Point(instanceRoleEditPartCBounds.getCenter().x, e2Bounds.getCenter().y);
        Point end = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.getBottom().y + (secondCombinedFragmentBounds.y - firstCombinedFragmentBounds.getBottom().y) / 2);
        createMessage(InteractionsConstants.READ_TOOL_ID, start.x, start.y, end.x, end.y);

        Assert.assertEquals(nbMessageBefore, interaction.getMessages().size());

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(instanceRoleEditPartABounds, editor.getBounds(instanceRoleEditPartABot));
        Assert.assertEquals(instanceRoleEditPartBBounds, editor.getBounds(instanceRoleEditPartBBot));
        Assert.assertEquals(instanceRoleEditPartCBounds, editor.getBounds(instanceRoleEditPartCBot));
        Assert.assertEquals(instanceRoleEditPartDBounds, editor.getBounds(instanceRoleEditPartDBot));
        Assert.assertEquals(instanceRoleEditPartEBounds, editor.getBounds(instanceRoleEditPartEBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

    }

    /**
     * Test that create message creation is not possible from a covered
     * participant to a not covered participant.
     */
    public void testCreateMessageCreationFromCoveredParticipantByCombinedFragmentToNotCovered() {
        Point start = e2Bounds.getCenter();
        Point finish = instanceRoleEditPartEBounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start.x, start.y, finish.x, finish.y);

        Assert.assertEquals(instanceRoleEditPartABounds, editor.getBounds(instanceRoleEditPartABot));
        Assert.assertEquals(instanceRoleEditPartBBounds, editor.getBounds(instanceRoleEditPartBBot));
        Assert.assertEquals(instanceRoleEditPartCBounds, editor.getBounds(instanceRoleEditPartCBot));
        Assert.assertEquals(instanceRoleEditPartDBounds, editor.getBounds(instanceRoleEditPartDBot));
        Assert.assertEquals(instanceRoleEditPartEBounds, editor.getBounds(instanceRoleEditPartEBot));

        assertDiagramElementsUnchanged();

    }

    /**
     * Test that create message creation is not possible from a not covered
     * participant to a covered participant.
     */
    public void testCreateMessageCreationFromNotCoveredParticipantByCombinedFragmentToCovered() {
        Point start = new Point(instanceRoleEditPartDBounds.getCenter().x, e2Bounds.getCenter().y);
        Point finish = instanceRoleEditPartABounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start.x, start.y, finish.x, finish.y);

        Assert.assertEquals(instanceRoleEditPartABounds, editor.getBounds(instanceRoleEditPartABot));
        Assert.assertEquals(instanceRoleEditPartBBounds, editor.getBounds(instanceRoleEditPartBBot));
        Assert.assertEquals(instanceRoleEditPartCBounds, editor.getBounds(instanceRoleEditPartCBot));
        Assert.assertEquals(instanceRoleEditPartDBounds, editor.getBounds(instanceRoleEditPartDBot));
        Assert.assertEquals(instanceRoleEditPartEBounds, editor.getBounds(instanceRoleEditPartEBot));

        assertDiagramElementsUnchanged();

    }

    /**
     * Test create message from LIFELINE_C to the newly created participant
     * between A & B, in the range between the first combined fragment and the
     * second.
     */
    public void testCreateMessageCreationFromNotCoveredParticipantSemanticallyByCombinedFragmentToCovered() {    	
        Point creationLocation = instanceRoleEditPartBBounds.getLeft().translate(-10, 0);
        createParticipant(creationLocation.x, creationLocation.y);
        SWTBotGefEditPart newInstanceRoleBot = editor.getEditPart(instanceRoleEditPartBBounds.getCenter(), InstanceRoleEditPart.class);
        Rectangle newInstanceRoleBounds = editor.getBounds(newInstanceRoleBot);

        Rectangle newInstanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);

        Point start = new Point(newInstanceRoleEditPartCBounds.getCenter().x, secondCombinedFragmentBounds.y - (secondCombinedFragmentBounds.y - firstCombinedFragmentBounds.getBottom().y) / 2);
        Point finish = newInstanceRoleBounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start.x, start.y, finish.x, finish.y);
        bot.waitUntil(new CheckMessageEditPartIsDisplayed(FIRST_CREATE_MESSAGE_ON_NEW_PARTICIPANT, editor));

        testDiagramConsistency();
    }

    /**
     * Test that destroy message creation is not possible from a covered
     * participant to a not covered participant.
     */
    public void testDestroyMessageCreationFromCoveredParticipantByCombinedFragmentToNotCovered() {
        Point start = new Point(instanceRoleEditPartDBounds.getCenter().x, e2Bounds.getCenter().y);
        Point finish = instanceRoleEditPartABounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, start.x, start.y, finish.x, finish.y);

        Assert.assertEquals(instanceRoleEditPartABounds, editor.getBounds(instanceRoleEditPartABot));
        Assert.assertEquals(instanceRoleEditPartBBounds, editor.getBounds(instanceRoleEditPartBBot));
        Assert.assertEquals(instanceRoleEditPartCBounds, editor.getBounds(instanceRoleEditPartCBot));
        Assert.assertEquals(instanceRoleEditPartDBounds, editor.getBounds(instanceRoleEditPartDBot));
        Assert.assertEquals(instanceRoleEditPartEBounds, editor.getBounds(instanceRoleEditPartEBot));

        assertDiagramElementsUnchanged();

    }

}
