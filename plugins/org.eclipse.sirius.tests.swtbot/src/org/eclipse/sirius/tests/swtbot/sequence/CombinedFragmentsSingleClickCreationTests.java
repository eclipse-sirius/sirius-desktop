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
import java.util.HashMap;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * @author edugueperoux, smonnier
 */
public class CombinedFragmentsSingleClickCreationTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test that nested CombinedFragment creation with a single click on
     * existing CombinedFragment (on Operand) is possible and expand parent
     * bounds to respect left diagram margin.
     */
    public void testCombinedFragmentCreationDeeplyNested() {
        Point creationLocation = new Point(instanceRoleEditPartABounds.getCenter().x, secondOperandOfFirstCombinedFragmentBounds.getCenter().y);

        SWTBotGefEditPart parentCombinedFragmentBot = firstCombinedFragmentBot;
        CombinedFragment parentCombinedFragment = firstCombinedFragment;
        SWTBotGefEditPart parentOperandBot = secondOperandOfFirstCombinedFragmentBot;
        Rectangle parentOperandBounds = secondOperandOfFirstCombinedFragmentBounds;

        SWTBotGefEditPart newCombinedFragmentBot;
        CombinedFragment newCombinedFragment;
        Rectangle newCombinedFragmentBounds;

        HashMap<SWTBotGefEditPart, Rectangle> combinedFragmentBoundsMap = new HashMap<SWTBotGefEditPart, Rectangle>();
        ArrayList<SWTBotGefEditPart> combinedFragmentBots = Lists.<SWTBotGefEditPart> newArrayList();

        final int NB_CFC_CREATION = TestsUtil.shouldSkipLongTests() ? 5 : 20;
        for (int i = 0; i < NB_CFC_CREATION; i++) {
            ICondition done = new OperationDoneCondition();
            createCombinedFragment(creationLocation);
            bot.waitUntil(done);

            newCombinedFragmentBot = editor.getEditPart(creationLocation.getTranslated(0, 5), CombinedFragmentEditPart.class);
            newCombinedFragment = (CombinedFragment) ((CombinedFragmentEditPart) newCombinedFragmentBot.part()).resolveTargetSemanticElement();
            newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);
            combinedFragmentBoundsMap.put(newCombinedFragmentBot, newCombinedFragmentBounds.getCopy());
            combinedFragmentBots.add(newCombinedFragmentBot);
            parentOperandBounds = editor.getBounds(secondOperandOfFirstCombinedFragmentBot);
            creationLocation = new Point(editor.getBounds(instanceRoleEditPartABot).getCenter().x, newCombinedFragmentBounds.getCenter().y);

            // Checks constraints
            Assert.assertEquals(1, newCombinedFragment.getCoveredParticipants().size());
            Assert.assertEquals(participantA, newCombinedFragment.getCoveredParticipants().get(0));
            Assert.assertTrue(parentOperandBounds.contains(newCombinedFragmentBounds));

            Rectangle newFirstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);
            firstOperandOfFirstCombinedFragmentBounds = editor.getBounds(firstOperandOfFirstCombinedFragmentBot);
            e2Bounds = editor.getBounds(e2Bot);
            instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);

            // Validate execution expand when adding sub execution keep the main
            // execution in bounds of its parent operand
            Assert.assertTrue(firstOperandOfFirstCombinedFragmentBounds.contains(e2Bounds));

            // Validate that the gap between the combined fragment and the next
            // lifeline is stable
            if (i >= 1) {
                Assert.assertEquals("CF " + i, LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartCBounds.getLeft().x - newFirstCombinedFragmentBounds.getRight().x);
            } else {
                Assert.assertEquals("CF " + i, 2 * LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartCBounds.getLeft().x - newFirstCombinedFragmentBounds.getRight().x);
            }

            // Reassign variable for next iteration
            parentCombinedFragmentBot = newCombinedFragmentBot;
            parentCombinedFragment = newCombinedFragment;
            parentOperandBot = parentCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(0);
            parentCombinedFragment.getOwnedOperands().get(0);
            parentOperandBounds = editor.getBounds(parentOperandBot);
        }
        for (int i = NB_CFC_CREATION - 1; i >= 0; i--) {
            Assert.assertEquals(combinedFragmentBoundsMap.get(combinedFragmentBots.get(i)), editor.getBounds(combinedFragmentBots.get(i)));
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
            if (i == 0) {
                Assert.assertEquals("CF " + i, LayoutConstants.LIFELINES_MIN_PACKED_X_GAP, instanceRoleEditPartCBounds.getLeft().x - newFirstCombinedFragmentBounds.getRight().x);
            } else if (i == 1) {
                Assert.assertEquals("CF " + i, 2 * LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartCBounds.getLeft().x - newFirstCombinedFragmentBounds.getRight().x);
            } else {
                Assert.assertEquals("CF " + i, LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartCBounds.getLeft().x - newFirstCombinedFragmentBounds.getRight().x);
            }
        }

        assertNotChange();

        for (int i = 0; i < NB_CFC_CREATION; i++) {
            Assert.assertEquals(combinedFragmentBoundsMap.get(combinedFragmentBots.get(i)), editor.getBounds(combinedFragmentBots.get(i)));
            redo();
            // Checks constraints
            Rectangle newFirstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);
            firstOperandOfFirstCombinedFragmentBounds = editor.getBounds(firstOperandOfFirstCombinedFragmentBot);
            e2Bounds = editor.getBounds(e2Bot);
            instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);

            // Validate execution expand when adding sub execution keep the main
            // execution in bounds of its parent operand
            Assert.assertTrue(firstOperandOfFirstCombinedFragmentBounds.contains(e2Bounds));

            // Validate that the gap between the combined fragment and the next
            // lifeline is stable
            Assert.assertTrue(LayoutConstants.LIFELINES_MIN_X_GAP <= instanceRoleEditPartCBounds.getLeft().x - newFirstCombinedFragmentBounds.getRight().x);
            Assert.assertTrue(2 * LayoutConstants.LIFELINES_MIN_X_GAP >= instanceRoleEditPartCBounds.getLeft().x - newFirstCombinedFragmentBounds.getRight().x);
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
     * Test that CombinedFragment creation with a single click on diagram is not
     * possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnDiagram() {
        Point creationLocation = instanceRoleEditPartABounds.getBottomLeft().getTranslated(-5, 5);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(creationLocation);
        Assert.assertNull("CombinedFragment creation on diagram should be not possible", newCombinedFragmentBot);

        assertNotChange();
    }

    /**
     * Test that combined fragment creation with single click on instance role
     * is not possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnInstanceRoleNotPossible() {
        Point creationLocation = instanceRoleEditPartABounds.getCenter();
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(creationLocation);
        Assert.assertNull("CombinedFragment creation on instance role should be not possible", newCombinedFragmentBot);

        assertNotChange();
    }

    /**
     * Test that CombinedFragment creation on lifeline is possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnLifeline() {
        Point creationLocation = new Point(instanceRoleEditPartABounds.getCenter().x, firstCombinedFragmentBounds.y - 2);
        ICondition done = new OperationDoneCondition();
        createCombinedFragment(creationLocation);
        bot.waitUntil(done);
        SWTBotGefEditPart newCombinedFragmentBot = editor.getEditPart(creationLocation.getTranslated(0, 10), CombinedFragmentEditPart.class);
        Assert.assertNotSame("CombinedFragment creation on lifeline should be possible", firstCombinedFragmentBot, newCombinedFragmentBot);

        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);

        // Checks events under the newly created CFC are correctly shifted
        int dy = newCombinedFragmentBounds.getBottom().y + LayoutConstants.EXECUTION_CHILDREN_MARGIN - firstCombinedFragmentBounds.y;

        Assert.assertEquals(LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT, newCombinedFragmentBounds.height);
        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        // Undo
        undo();

        Assert.assertSame("Undo have not deleted previously created IU", firstCombinedFragmentBot, editor.getEditPart(creationLocation.getTranslated(0, 10), CombinedFragmentEditPart.class));

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        assertNotChange();

        // Redo
        redo();

        newCombinedFragmentBot = editor.getEditPart(creationLocation.translate(0, 10), CombinedFragmentEditPart.class);
        Assert.assertNotSame("CombinedFragment creation on lifeline should be redone", firstCombinedFragmentBot, newCombinedFragmentBot);

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        // Deletion
        editor.click(newCombinedFragmentBot);
        deleteSelectedElement();

        newCombinedFragmentBot = editor.getEditPart(creationLocation.translate(0, 10), CombinedFragmentEditPart.class);
        Assert.assertNull("CombinedFragment should be deleted", newCombinedFragmentBot);

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

    }

    /**
     * Test that CombinedFragment creation on execution is possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnExecution1() {
        Point creationLocation = e1Bounds.getCenter();
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(creationLocation);
        Assert.assertNotNull("CombinedFragment creation on execution should be possible", newCombinedFragmentBot);

        // Checks that e1 is expanded down
        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);

        // Checks events under the newly created IU are correclty shifted
        int dy = newCombinedFragmentBounds.bottom() - e1Bounds.bottom() + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        // Undo
        undo();

        newCombinedFragmentBot = editor.getEditPart(creationLocation.getTranslated(0, 5), CombinedFragmentEditPart.class);
        Assert.assertNull("Undo have not deleted previously created IU", newCombinedFragmentBot);

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        assertNotChange();

        // Redo
        redo();

        newCombinedFragmentBot = editor.getEditPart(creationLocation.getTranslated(0, 5), CombinedFragmentEditPart.class);
        Assert.assertNotNull("CombinedFragment creation on execution should be possible", newCombinedFragmentBot);

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        // Deletion
        editor.click(newCombinedFragmentBot);
        deleteSelectedElement();

        newCombinedFragmentBot = editor.getEditPart(creationLocation, CombinedFragmentEditPart.class);
        Assert.assertNull("Deletion of IU should works", newCombinedFragmentBot);

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(e1Bounds.getResized(0, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));

        validateOrdering(12);
    }

    /**
     * Test that CombinedFragment creation with a single click on header label
     * of existing CombinedFragment is not possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnHeaderLabelOfExistingCFCNotPossible() {
        Point creationLocation = firstCombinedFragmentBounds.getLocation().getTranslated(4, 4);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(creationLocation);
        Assert.assertNull("CombinedFragment creation on header of another CombinedFragment should not be possible", newCombinedFragmentBot);

        assertNotChange();
    }

    /**
     * Test that CombinedFragment creation with a single click on header (on
     * diagram) of existing CombinedFragment is not possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnHeaderOnDiagramOfExistingCFCNotPossible() {
        Point creationLocation = firstCombinedFragmentBounds.getTop().getTranslated(0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(creationLocation);
        Assert.assertNull("CombinedFragment creation on header of another CombinedFragment should not be possible", newCombinedFragmentBot);

        assertNotChange();
    }

    /**
     * Test that CombinedFragment creation with a single click on header
     * (covering a lifeline) of existing CombinedFragment is not possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnHeaderCoveringOneLifelineOfExistingCFC() {
        Point creationLocation = new Point(instanceRoleEditPartBBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createCombinedFragment(creationLocation);
        SWTBotGefEditPart newCombinedFragmentBot = editor.getEditPart(creationLocation.getTranslated(0, 5), CombinedFragmentEditPart.class);
        Assert.assertSame("CombinedFragment creation on header of another CombinedFragment should not be possible", firstCombinedFragmentBot, newCombinedFragmentBot);

        assertNotChange();
    }

    /**
     * Test that CombinedFragment creation with a single click in the range of
     * header of existing CombinedFragment but outside the coveredParticipants
     * of this CombinedFragment is not possible and doesn't increase the header
     * range.
     */
    public void testCombinedFragmentCreationWithSingleClickInRangeOfHeaderOfExistingCFCNotPossible() {
        Point creationLocation = new Point(instanceRoleEditPartCBounds.getCenter().x, firstCombinedFragmentBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createCombinedFragment(creationLocation);
        SWTBotGefEditPart newCombinedFragmentBot = editor.getEditPart(creationLocation.getTranslated(0, 5), CombinedFragmentEditPart.class);

        Assert.assertNull(newCombinedFragmentBot);

        assertNotChange();
    }

    /**
     * Test that CombinedFragment creation with a single click on existing
     * interaction use is not possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnExistingIU() {
        Point start = instanceRoleEditPartCBounds.getBottomLeft().translate(0, 10);
        Point finish = instanceRoleEditPartEBounds.getBottomRight().translate(0, 10);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(start, finish);
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Test
        Point creationLocation = newInteractionUseBounds.getCenter();
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(creationLocation);
        Assert.assertNull("combined fragment creation on interaction use should not possible", newCombinedFragmentBot);

        validateOrdering(14);

        // Undo
        undo();

        assertNotChange();
    }

    /**
     * Test that CombinedFragment creation with a single click on existing
     * CombinedFragment (on Operand) is possible.
     */
    public void testCombinedFragmentCreationWithSingleClickOnExistingCFC() {
        // Test that creation on existing CFC is possible
        Point creationLocation = new Point(instanceRoleEditPartABounds.getCenter().x, firstCombinedFragmentBounds.getCenter().y);
        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(creationLocation);
        Assert.assertNotSame("CombinedFragment creation on another CombinedFragment should not be possible", firstCombinedFragmentBot, newCombinedFragmentBot);
        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);

        int leftMargin = LayoutConstants.BORDER_FRAME_MARGIN;
        int rightMargin = LayoutConstants.BORDER_FRAME_MARGIN;
        int dx = leftMargin;
        int dy = newCombinedFragmentBounds.bottom() - e2Bounds.bottom() + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        int dw = leftMargin + rightMargin;
        Assert.assertEquals(e1Bounds.getTranslated(dx, 0), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(dx, 0).resize(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(dw, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(dx, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getResized(dw, dy), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy).resize(dw, 0), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(dx, dy), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(dx, dy), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        Assert.assertEquals(instanceRoleEditPartABounds.getCenter().getTranslated(dx, 0), editor.getBounds(instanceRoleEditPartABot).getCenter());

        Assert.assertEquals(creationLocation.y, newCombinedFragmentBounds.getTop().y);
        Assert.assertEquals(instanceRoleEditPartABounds.getCenter().getTranslated(dx, 0).x, newCombinedFragmentBounds.getTop().x);

        validateOrdering(15);

        // Undo
        undo();

        assertNotChange();

        // Redo
        redo();

        Assert.assertEquals(e1Bounds.getTranslated(dx, 0), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(dx, 0).resize(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(dw, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(dx, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getResized(dw, dy), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, dy).resize(dw, 0), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(dx, dy), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(dx, dy), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        Assert.assertEquals(instanceRoleEditPartABounds.getCenter().getTranslated(dx, 0), editor.getBounds(instanceRoleEditPartABot).getCenter());

        Assert.assertEquals(creationLocation.y, newCombinedFragmentBounds.getTop().y);
        Assert.assertEquals(instanceRoleEditPartABounds.getCenter().getTranslated(dx, 0).x, newCombinedFragmentBounds.getTop().x);

        validateOrdering(15);

        // Deletion
        newCombinedFragmentBot.click();
        deleteSelectedElement();
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 2));

        Assert.assertEquals(e1Bounds.getTranslated(dx, 0), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(dx, 0).resize(0, dy), editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(dx, 0).resize(0, dy), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(dx, dy), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getTranslated(dx, 0).resize(0, dy), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(dx, dy).resize(0, 0), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(dx, dy).resize(0, 0), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(dx, dy).resize(0, 0), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        Assert.assertEquals(instanceRoleEditPartABounds.getCenter().getTranslated(dx, 0), editor.getBounds(instanceRoleEditPartABot).getCenter());

        validateOrdering(12);
    }

}
