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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
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
public class InteractionUseResizeTests extends AbstractInteractionUseSequenceTests {

    /**
     * Test horizontal resizing of InteractionUse is not possible.
     */
    public void testInteractionUseHorizontalResizeNotPossible() {
        // Test that horizontal move of the first InteractionUse has not effect
        // to right
        int dy = 20;
        firstInteractionUseBot.resize(PositionConstants.EAST, firstInteractionUseBounds.width + dy, firstInteractionUseBounds.height);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);

        // to left
        firstInteractionUseBot.resize(PositionConstants.WEST, firstInteractionUseBounds.width + dy, firstInteractionUseBounds.height);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);

        // Test that horizontal move of the second InteractionUse has not effect
        // to right
        secondInteractionUseBot.resize(PositionConstants.EAST, secondInteractionUseBounds.width + dy, secondInteractionUseBounds.height);

        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);

        // to left
        secondInteractionUseBot.resize(PositionConstants.WEST, secondInteractionUseBounds.width + dy, secondInteractionUseBounds.height);

        Assert.assertEquals(secondInteractionUseBounds, editor.getBounds(secondInteractionUseBot));
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test that vertical resize up of the first InteractionUse is not allowed
     * because of the top margin.
     */
    public void testFirstInteractionUseVerticalResizeUpNotAllowedBecauseOfTopMargin() {
        int dy = 30;
        firstInteractionUseBot.resize(PositionConstants.NORTH, firstInteractionUseBounds.width, firstInteractionUseBounds.height + dy);

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
        testDiagramConsistency();
        validateOrdering(20);

    }

    /**
     * Test that vertical resize up of the first InteractionUse is allowed after
     * a move down to have sufficient between its top and the top margin.
     */
    public void testFirstInteractionUseVerticalResizeUpIsAllowed() {
        int dy = e1Bounds.y - firstInteractionUseBounds.getBottom().y;
        dy /= 2;
        ICondition condition = new CheckEditPartMoved(firstInteractionUseBot);
        editor.drag(firstInteractionUseBot, firstInteractionUseBounds.x, firstInteractionUseBounds.y + dy);
        bot.waitUntil(condition);

        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);

        // Test
        firstInteractionUseBot.select();
        condition = new CheckEditPartResized(firstInteractionUseBot);
        firstInteractionUseBot.resize(PositionConstants.NORTH, firstInteractionUseBounds.width, firstInteractionUseBounds.height + dy);
        bot.waitUntil(condition);

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, -dy).resize(0, dy), editor.getBounds(firstInteractionUseBot));
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

        // Undo
        undo();

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
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, -dy).resize(0, dy), editor.getBounds(firstInteractionUseBot));
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

        // Delete
        deleteSelectedElement();
        Assert.assertNull(editor.getEditPart(firstInteractionUseBounds.getCenter(), InteractionUseEditPart.class));

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
        validateOrdering(18);

    }

    /**
     * Test that vertical resize top of the second InteractionUse is not allowed
     * on the top of the Exec e2.
     */
    public void testSecondInteractionUseVerticalResizeUpIsNotAllowedOnTopOfE2() {
        int dy = secondInteractionUseBounds.y - e2Bounds.y;
        secondInteractionUseBot.resize(PositionConstants.NORTH, secondInteractionUseBounds.width, firstInteractionUseBounds.height + dy);

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
        testDiagramConsistency();
        validateOrdering(20);

    }

    /**
     * Test that vertical resize down of the second InteractionUse is allowed on
     * the bottom of the Exec e2.
     */
    public void testSecondInteractionUseVerticalResizeDownIsNotAllowedOnBottomOfE2() {
        int dy = e2Bounds.getBottom().y - secondInteractionUseBounds.getBottom().y;
        secondInteractionUseBot.select();
        ICondition condition = new CheckEditPartResized(secondInteractionUseBot);
        secondInteractionUseBot.resize(PositionConstants.SOUTH, secondInteractionUseBounds.width, firstInteractionUseBounds.height + dy);
        bot.waitUntil(condition);

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getResized(0, dy), editor.getBounds(secondInteractionUseBot));

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
        validateOrdering(20);

        // Undo
        undo();

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
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds, editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getResized(0, dy), editor.getBounds(secondInteractionUseBot));

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
        validateOrdering(20);
    }

    /**
     * Test that vertical resize down of the first InteractionUse is allowed.
     */
    public void testFirstInteractionUseVerticalResizeDownIsAllowedOnTopOfE1() {
        int dy = e1Bounds.y - firstInteractionUseBounds.getBottom().y;
        ICondition condition = new CheckEditPartResized(firstInteractionUseBot);
        firstInteractionUseBot.select();
        firstInteractionUseBot.resize(PositionConstants.SOUTH, firstInteractionUseBounds.width, firstInteractionUseBounds.height + dy);
        bot.waitUntil(condition);

        Assert.assertEquals(firstInteractionUseBounds.getResized(0, dy), editor.getBounds(firstInteractionUseBot));
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

        // Undo
        undo();

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
        testDiagramConsistency();
        validateOrdering(20);

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds.getResized(0, dy), editor.getBounds(firstInteractionUseBot));
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
     *
     */
    public void testFirstInteractionUseVerticalResizeUpToExecutionOnNotSemanticallyCoveredParticipant() {

        Point creationLocation = instanceRoleEditPartCBounds.getLeft().translate(-20, 0);
        createParticipant(creationLocation.x, creationLocation.y);
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test that decrease height of the first InteractionUse is not possible
     * because its height is already at the minimum
     * (LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT).
     */
    public void testFirstInteractionUseDecreaseItMininumHeightFromTopNotPossible() {

        int dy = 5;
        firstInteractionUseBot.resize(PositionConstants.NORTH, firstInteractionUseBounds.width, firstInteractionUseBounds.height - dy);

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
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test that decrease height of the first InteractionUse is not possible
     * because its height is already at the minimum
     * (LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT).
     */
    public void testFirstInteractionUseDecreaseItMininumHeightFromBottomNotPossible() {
        int dy = 5;
        firstInteractionUseBot.resize(PositionConstants.SOUTH, firstInteractionUseBounds.width, firstInteractionUseBounds.height - dy);

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
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test that decrease height of the second InteractionUse is not possible
     * because its height is already at the minimum
     * (LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT).
     */
    public void testSecondInteractionUseDecreaseItMininumHeightFromTopNotPossible() {
        int dy = 5;
        secondInteractionUseBot.resize(PositionConstants.NORTH, secondInteractionUseBounds.width, secondInteractionUseBounds.height - dy);

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
        testDiagramConsistency();
        validateOrdering(20);
    }

    /**
     * Test that decrease height of the second InteractionUse is not possible
     * because its height is already at the minimum
     * (LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT).
     */
    public void testSecondInteractionUseDecreaseItMininumHeightFromBottomNotPossible() {
        int dy = 5;
        secondInteractionUseBot.resize(PositionConstants.SOUTH, secondInteractionUseBounds.width, secondInteractionUseBounds.height - dy);

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
        testDiagramConsistency();
        validateOrdering(20);
    }

}
