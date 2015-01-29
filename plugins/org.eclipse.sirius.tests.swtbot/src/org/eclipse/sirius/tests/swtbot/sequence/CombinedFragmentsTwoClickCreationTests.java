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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * @author edugueperoux, smonnier
 */
public class CombinedFragmentsTwoClickCreationTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test that CombinedFragment creation without covering lifelines is not
     * possible.
     */
    public void testCombinedFragmentCreationWithTwoClickWithoutLifelineCoveredNotPossible() {
        Point start = new Point(5, 5);
        Point finish = instanceRoleEditPartCBounds.getBottomRight().translate(5, 5);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(start, finish);
        Assert.assertNull("creation without selecting lifelines is not possible", newCombinedFragmentBot);

        assertNotChange();
    }

    /**
     * Test that creation to cover lifeline E is possible and expand e3, first
     * operand of first CFC.
     */
    public void testCombinedFragmentCreationWithTwoClickToCoverLifelineE() {
        Point start = new Point(instanceRoleEditPartEBounds.x, e2Bounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN);
        Point finish = new Point(instanceRoleEditPartEBounds.getRight().x, e2Bounds.y + 10);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(start, finish);
        Assert.assertNotNull("creation to cover lifeline E should be possible", newCombinedFragmentBot);

        // Checks events under the newly created CF are correctly shifted
        // Checks that e1 is expanded down
        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);

        // Checks events under the newly created CF are correctly shifted
        int dy = newCombinedFragmentBounds.bottom() - e2Bounds.bottom() + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));

        // Undo
        undo();

        assertNotChange();

        // Redo
        redo();

        newCombinedFragmentBot = editor.getEditPart(new Rectangle(start, finish).getCenter(), CombinedFragmentEditPart.class);
        Assert.assertNotNull("creation to cover lifeline E should be redone", newCombinedFragmentBot);

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));

        // Deletion
        newCombinedFragmentBot.select();
        deleteSelectedElement();
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 2));

        newCombinedFragmentBot = editor.getEditPart(new Rectangle(start, finish).getCenter(), CombinedFragmentEditPart.class);
        Assert.assertNull("Deletion of IU should works", newCombinedFragmentBot);

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));

    }

    /**
     * Test the creation of combined fragment in existing combined fragment
     * covering graphically not semantically covered participants.
     */
    public void testCombinedFragmentCreationInExistingCFCOnNotCoveredParticipant() {
        Point creationLocation = instanceRoleEditPartBBounds.getLeft().translate(-20, 0);
        createParticipant(creationLocation.x, creationLocation.y);
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, InstanceRoleEditPart.class, 6));

        testDiagramConsistency();
        Assert.assertEquals(2, secondCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantB));

        Assert.assertEquals(2, firstCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantB));

        // Refresh 2nd CF bot
        secondCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(1);
        secondOperandOfSecondCombinedFragmentBot = secondCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(1);

        Assert.assertEquals(secondCombinedFragmentBounds.getResized(editor.getBounds(instanceRoleEditPartBBot).right() - secondCombinedFragmentBounds.right(), 0),
                editor.getBounds(secondCombinedFragmentBot));

        Rectangle newSecondOperandOfSecondCombinedFragmentBounds = editor.getBounds(secondOperandOfSecondCombinedFragmentBot);
        Point start = newSecondOperandOfSecondCombinedFragmentBounds.getLeft().translate(10, 0);
        Point finish = newSecondOperandOfSecondCombinedFragmentBounds.getRight().translate(-10, 0);

        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(start, finish);

        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);
        CombinedFragment newCombinedFragment = (CombinedFragment) ((CombinedFragmentEditPart) newCombinedFragmentBot.part()).resolveTargetSemanticElement();

        Assert.assertEquals(2, newCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(newCombinedFragment.getCoveredParticipants().contains(participantB));

        Assert.assertTrue(editor.getBounds(secondOperandOfSecondCombinedFragmentBot).contains(newCombinedFragmentBounds));
        Assert.assertEquals(editor.getBounds(secondOperandOfSecondCombinedFragmentBot).getLeft().getTranslated(LayoutConstants.BORDER_FRAME_MARGIN, 0).x, newCombinedFragmentBounds.getLeft().x);
        Assert.assertEquals(editor.getBounds(secondOperandOfSecondCombinedFragmentBot).getRight().getTranslated(-LayoutConstants.BORDER_FRAME_MARGIN, 0).x, newCombinedFragmentBounds.getRight().x);

        // Undo
        undo();
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 2));
        undo();
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, InstanceRoleEditPart.class, 5));

        assertNotChange();

        // Redo
        redo();
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, InstanceRoleEditPart.class, 6));
        redo();
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 3));

        // Delete
        editor.click(newCombinedFragmentBounds.getTopLeft());
        deleteSelectedElement();

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 2));
    }

    /**
     * Test that CombinedFragment creation with a two click on existing
     * interaction use is not be possible.
     */
    public void testCombinedFragmentCreationWithTwoClickOnExistingIU() {
        Point start = instanceRoleEditPartCBounds.getBottomLeft().translate(0, 10);
        Point finish = instanceRoleEditPartEBounds.getBottomRight().translate(0, 10);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(start, finish);
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Test
        Point startOfCFC = newInteractionUseBounds.getLocation().translate(-10, 10);
        Point finishOfCFC = newInteractionUseBounds.getBottomRight().translate(10, -10);
        createCombinedFragmentWithResult(startOfCFC, finishOfCFC);

        Interaction interaction = (Interaction) firstCombinedFragment.eContainer();
        Assert.assertEquals(2, interaction.getCombinedFragments().size());

        validateOrdering(14);
    }

}
