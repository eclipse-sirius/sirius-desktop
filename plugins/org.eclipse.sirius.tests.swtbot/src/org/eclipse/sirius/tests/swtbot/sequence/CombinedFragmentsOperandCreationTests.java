/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.figure.OneLineMarginBorder;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

import com.google.common.collect.Iterables;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * @author edugueperoux, smonnier
 */
public class CombinedFragmentsOperandCreationTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test operand's combined fragment creation with a single click on operand
     * boundary.
     */
    // TODO operand size on creation issue see VP-1360
    public void testCombinedFragmentOperandCreationWithSingleClickOnFirstOperands() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
            org.eclipse.swtbot.swt.finder.widgets.TimeoutException: Timeout after: 10000 ms.: operation execution failed
            at org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(SWTBotFactory.java:407)
            at org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(SWTBotFactory.java:381)
            at org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(SWTBotFactory.java:369)
            at org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsOperandCreationTests.testCombinedFragmentOperandCreationWithSingleClickOnFirstOperands(CombinedFragmentsOperandCreationTests.java:96)
            */
            return;
        }
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

        OperandEditPart opPart = (OperandEditPart) newOperandOption.get().part();
        Assert.assertNull("The operand figure should not have a drop shadow.",   opPart.getMainFigure().getBorder());
        Assert.assertTrue("The operand border should be a single line.",   opPart.getPrimaryShape().getBorder() instanceof OneLineMarginBorder);
        Assert.assertEquals("The operand border should be a single line.", Boolean.FALSE, ReflectionHelper.getFieldValueWithoutException(opPart.getPrimaryShape(), "outline", Shape.class).get());
        Assert.assertEquals("The operand border should be place on the bottom of the figure.",PositionConstants.BOTTOM,  ((OneLineMarginBorder) opPart.getPrimaryShape().getBorder()).getPosition());
        Assert.assertEquals("The operand border should have a custom dash style.",Graphics.LINE_CUSTOM,  ((OneLineMarginBorder) opPart.getPrimaryShape().getBorder()).getStyle());
        
        Assert.assertNull("The CF figure should not have a drop shadow.", firstCombinedFragmentEditPart.getMainFigure().getBorder());
        Assert.assertNull("The CF compartment figure should not have a border.", Iterables.getOnlyElement(Iterables.filter(firstCombinedFragmentEditPart.getChildren(), ResizableCompartmentEditPart.class)).getFigure().getBorder());
        
        
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

        bot.sleep(500);
        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test operand's combined fragment creation with a single click on operand
     * boundary.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnSecondOperands() {
        Point creationLocation = secondOperandOfFirstCombinedFragmentBounds.getCenter();
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        Option<SWTBotGefEditPart> newOperandOption = createCombinedFragmentOperandWithResult(creationLocation);
        bot.waitUntil(checkMoved);

        Assert.assertTrue(newOperandOption.some());

        SWTBotGefEditPart newOperandBot = newOperandOption.get();
        Rectangle newOperandBounds = editor.getBounds(newOperandBot);

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
                .getBottomRight()), newOperandBounds);

        // Undo
        undo();
        assertNotChange();

        // Redo();
        redo();

        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // FIXME find why there is a delta of 1 pixel
        Assert.assertEquals(new Rectangle(secondOperandOfFirstCombinedFragmentBounds.getBottomLeft(), firstCombinedFragmentBounds.getResized(-1, LayoutConstants.DEFAULT_OPERAND_HEIGHT - 1)
                .getBottomRight()), newOperandBounds);

        // Delete
        editor.click(newOperandBounds.getCenter());
        CheckEditPartResized checkResized = new CheckEditPartResized(secondOperandOfFirstCombinedFragmentBot);
        deleteSelectedElement();
        bot.waitUntil(checkResized);
        Assert.assertEquals(2, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test operand's combined fragment creation with a single click on operand
     * boundary.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnBoundaryBetweenOperandsPossible() {
        Point creationLocation = secondOperandOfFirstCombinedFragmentBounds.getTop();
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondCombinedFragmentBot);
        Option<SWTBotGefEditPart> newOperandOption = createCombinedFragmentOperandWithResult(creationLocation);
        bot.waitUntil(checkMoved);

        Assert.assertTrue(newOperandOption.some());

        SWTBotGefEditPart newOperandBot = newOperandOption.get();
        Rectangle newOperandBounds = editor.getBounds(newOperandBot);

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
                .getBottomRight()), newOperandBounds);

        // Undo
        undo();
        assertNotChange();

        // Redo();
        redo();
        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // FIXME find why there is a delta of 1 pixel
        Assert.assertEquals(new Rectangle(secondOperandOfFirstCombinedFragmentBounds.getBottomLeft(), firstCombinedFragmentBounds.getResized(-1, LayoutConstants.DEFAULT_OPERAND_HEIGHT - 1)
                .getBottomRight()), newOperandBounds);

        // Delete
        editor.click(newOperandBounds.getCenter());
        CheckEditPartResized checkResized = new CheckEditPartResized(secondOperandOfFirstCombinedFragmentBot);
        deleteSelectedElement();
        bot.waitUntil(checkResized);
        Assert.assertEquals(2, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, deltaY), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
    }

    /**
     * Test operand's combined fragment creation with a single click in diagram.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnDiagramNotPossible() {
        Point creationLocation = new Point(5, 5);
        createCombinedFragmentOperand(creationLocation);

        assertNotChange();
    }

    /**
     * Test operand's combined fragment creation with a single click on instance
     * role.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnInstanceRoleNotPossible() {
        Point creationLocation = instanceRoleEditPartABounds.getCenter();
        createCombinedFragmentOperand(creationLocation);

        assertNotChange();
    }

    /**
     * Test operand's combined fragment creation with a single click on
     * lifeline.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnLifelineNotPossible() {
        Point creationLocation = new Point(instanceRoleEditPartABounds.getCenter().x, e1Bounds.y);
        createCombinedFragmentOperand(creationLocation);

        assertNotChange();
    }

    /**
     * Test operand's combined fragment creation with a single click on
     * execution.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnExecutionNotPossible() {
        Point creationLocation = e1Bounds.getCenter();
        createCombinedFragmentOperand(creationLocation);

        assertNotChange();
    }

    /**
     * Test operand's combined fragment creation with a single click on
     * interaction use.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnInteractionUseNotPossible() {
        Point start = instanceRoleEditPartCBounds.getBottomLeft().translate(0, 10);
        Point finish = instanceRoleEditPartEBounds.getBottomRight().translate(0, 30);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(start, finish);
        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        Point creationLocation = newInteractionUseBounds.getCenter();
        createCombinedFragmentOperand(creationLocation);

        Assert.assertEquals(newInteractionUseBounds, editor.getBounds(newInteractionUseBot));

        validateOrdering(14);
    }

    /**
     * Test operand's combined fragment creation with a single click on
     * interaction use.
     */
    public void testCombinedFragmentOperandCreationWithSingleClickOnExistingCFCHeaderNotPossible() {
        Point creationLocation = firstCombinedFragmentBounds.getTop().translate(0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        createCombinedFragmentOperand(creationLocation);

        assertNotChange();
    }

    /**
     * Test that combined fragment creation with a two click on existing
     * interaction use is not be possible.
     */
    // TODO
    public void testCombinedFragmentOperandCreationWithTwoClickCoveringSeveralCCFC() {
        Point start = new Point(instanceRoleEditPartBBounds.getLeft().x, e2Bounds.y - 10);
        Point end = new Point(instanceRoleEditPartBBounds.getRight().x, secondOperandOfSecondCombinedFragmentBounds.getCenter().y);

        ICondition done = new OperationDoneCondition();
        createCombinedFragmentOperand(start, end);
        bot.waitUntil(done);

        Assert.assertEquals(3, firstCombinedFragment.getOwnedOperands().size());

        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds.getResized(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondCombinedFragmentBot));

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
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));

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
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds.getTranslated(0, LayoutConstants.DEFAULT_OPERAND_HEIGHT), editor.getBounds(secondOperandOfSecondCombinedFragmentBot));

        // Delete

    }

    /**
     * Test that combined fragment creation with a two click on existing
     * interaction use is not be possible.
     */
    // TODO
    public void testCombinedFragmentOperandCreationWithTwoClickOnExistingIUWithSameCoveredParticipantsNotPossible() {
        Point start = new Point(instanceRoleEditPartBBounds.getLeft().x, e2Bounds.y - 10);
        Point end = new Point(instanceRoleEditPartBBounds.getRight().x, secondOperandOfSecondCombinedFragmentBounds.getCenter().y);
        createCombinedFragmentOperand(start, end);

        // TODO
    }

}
