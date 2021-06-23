/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberExecutionOnLifeline;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckSequenceMessageEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

import com.google.common.collect.Iterables;

/**
 * Tests only zoom and creation with single/double click, others features to test are Junit Plugin Tests.
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
public class InteractionUseMoveDownTests extends AbstractInteractionUseSequenceTests {

    /**
     * Test that move a the second InteractionUse under the end of lifeline is forbidden.
     */
    public void testSecondInteractionUseMoveUnderEndLifelineNotPossible() {
        int endOfLifeline = editor.getBounds(instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0)).getBottom().y;
        int dy = endOfLifeline - secondInteractionUseBounds.y + 20;
        editor.drag(secondInteractionUseBot, secondInteractionUseBounds.x, secondInteractionUseBounds.y + dy);

        testDiagramConsistency();
        validateOrdering(20);

    }

    /**
     * Test that vertical move down of the first InteractionUse is allowed until top of Exec e1, and shift all figures
     * below
     */
    public void testFirstInteractionUseVerticalMoveDownUntilE1TopPossibleAndShiftFiguresBelow() {

        int dy = e1Bounds.y - firstInteractionUseBounds.getBottom().y;
        ICondition condition = new CheckEditPartMoved(firstInteractionUseBot);
        editor.drag(firstInteractionUseBot, firstInteractionUseBounds.x, firstInteractionUseBounds.y + dy);
        bot.waitUntil(condition);

        int verticalSpaceExpansion = firstInteractionUseBounds.height;

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(returnMessageOfE4Bot));
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
        validateOrdering(20);

        // Redo
        redo();

        Assert.assertEquals(firstInteractionUseBounds.getTranslated(0, dy), editor.getBounds(firstInteractionUseBot));
        Assert.assertEquals(secondInteractionUseBounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(secondInteractionUseBot));

        Assert.assertEquals(e1Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(e5Bot));

        Assert.assertEquals(callMessageOnE1Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(callMessageOnE1Bot));
        Assert.assertEquals(callMessageOnE2Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(callMessageOnE2Bot));
        Assert.assertEquals(callMessageOnE4Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(callMessageOnE4Bot));
        Assert.assertEquals(returnMessageOfE1Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(returnMessageOfE1Bot));
        Assert.assertEquals(returnMessageOfE2Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(returnMessageOfE2Bot));
        Assert.assertEquals(returnMessageOfE4Bounds.getTranslated(0, verticalSpaceExpansion), editor.getBounds(returnMessageOfE4Bot));
        validateOrdering(20);
    }

    private int commonPreparationForMoveDownTests() {
        Rectangle lifelineEBounds = getLifelineEditPart(LIFELINE_E).getFigure().getBounds().getCopy();
        Point start = new Point(lifelineEBounds.getCenter().x, secondInteractionUseBounds.getBottom().getTranslated(0, 10).y);
        Point finish = start.getTranslated(0, 9);

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds);

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds);

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.y);

        validateOrdering(20);

        CheckNumberExecutionOnLifeline checkNewExecutionOnLifelineE = new CheckNumberExecutionOnLifeline(LIFELINE_E, 1, editor);
        createSyncCall(start, finish);
        bot.waitUntil(checkNewExecutionOnLifelineE);

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e6Bounds = editor.getBounds(e6Bot);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);
        callMessageOnE6Bounds = editor.getBounds(callMessageOnE6Bot);
        returnMessageOfE6Bounds = editor.getBounds(returnMessageOfE6Bot);

        int dY = LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP * 2 - (start.y - secondInteractionUseBounds.getBottom().y)
                + LayoutConstants.EXECUTION_CHILDREN_MARGIN;

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds);

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getResized(0, dY));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds.getResized(0, dY));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds.getResized(0, dY));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds.getTranslated(0, dY));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e6Bot.part(), e6Bounds);

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE6Bot.part(), callMessageOnE6Bounds, true, false);

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.getTranslated(0, dY).y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.getTranslated(0, dY).y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE6Bot.part(), returnMessageOfE6Bounds, true, false);

        validateOrdering(24);

        // Undo
        undo();

        testDiagramConsistency();

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds);

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds);

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.y);

        validateOrdering(20);

        // Redo
        redo();
        bot.waitUntil(checkNewExecutionOnLifelineE);

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds);

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getResized(0, dY));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds.getResized(0, dY));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds.getResized(0, dY));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds.getTranslated(0, dY));

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.getTranslated(0, dY).y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.getTranslated(0, dY).y);

        validateOrdering(24);

        return dY;
    }

    /**
     * test to move up the second interaction use
     */
    public void testSecondInteractionUseVerticalMoveDownAfterReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        int dY = commonPreparationForMoveDownTests();

        // Resize e2 to have enough space for second IU move
        CheckEditPartResized checkResized = new CheckEditPartResized(e2Bot);
        int e2Resize = 100;
        editor.drag(returnMessageOfE2Bounds.getTranslated(0, dY).getCenter(), returnMessageOfE2Bounds.getTranslated(0, dY).getCenter().getTranslated(0, e2Resize));
        bot.waitUntil(checkResized);

        // Move second interaction to have its top in the middle of E6
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondInteractionUseBot);
        editor.drag(secondInteractionUseBounds.getTop(), returnMessageOfE6Bounds.getBottom().getTranslated(0, 1));
        bot.waitUntil(checkMoved);

        int dy2 = returnMessageOfE6Bounds.bottom() - secondInteractionUseBounds.getTop().y + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        int deltaResize = secondInteractionUseBounds.height + LayoutConstants.EXECUTION_CHILDREN_MARGIN - 1;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds.getTranslated(0, dy2));

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getResized(0, dY + e2Resize + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds.getTranslated(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e6Bot.part(), e6Bounds);

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE6Bot.part(), callMessageOnE6Bounds, true, false); // callMessageOnE6Bounds.getTranslated(0,

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.getTranslated(0, dY + e2Resize + deltaResize).y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.getTranslated(0, dY + deltaResize).y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE6Bot.part(), returnMessageOfE6Bounds, true, false);

        validateOrdering(24);
    }

    /**
     * test to move up the second interaction use
     */
    public void testSecondInteractionUseVerticalMoveDownToCallOfReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        int dY = commonPreparationForMoveDownTests();

        // Move second interaction to have its top in the middle of
        // callMessageOnE6Bounds
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondInteractionUseBot);
        editor.drag(secondInteractionUseBounds.getTop(), callMessageOnE6Bounds.getCenter());
        bot.waitUntil(checkMoved);

        int dy2 = callMessageOnE6Bounds.getCenter().y - secondInteractionUseBounds.getTop().y;

        int deltaResize = LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds.getTranslated(0, dy2).getResized(0, deltaResize));

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds.getTranslated(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e6Bot.part(), e6Bounds.getTranslated(0, deltaResize));

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE6Bot.part(), callMessageOnE6Bounds.getResized(0, deltaResize), true, false);

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.getTranslated(0, dY + deltaResize).y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.getTranslated(0, dY + deltaResize).y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE6Bot.part(), returnMessageOfE6Bounds.getTranslated(0, deltaResize), true, false);

        validateOrdering(24);
    }

    /**
     * test to move up the second interaction use
     */
    public void testSecondInteractionUseVerticalMoveDownToCenterOfReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        if (TestsUtil.shouldSkipLongTests()) {
            /*
             * junit.framework.AssertionFailedError: The execution named is not displayed at the expected vertical
             * position expected:<420.0> but was:<485.0> at junit.framework.Assert.fail(Assert.java:57) at
             * junit.framework.Assert.failNotEquals(Assert.java:329) at
             * junit.framework.Assert.assertEquals(Assert.java:142) at
             * junit.framework.TestCase.assertEquals(TestCase.java:298) at
             * org.eclipse.sirius.tests.swtbot.sequence.AbstractSequenceDiagramTestCase.assertSequenceNodeHasValidBounds
             * (AbstractSequenceDiagramTestCase.java:1371) at
             * org.eclipse.sirius.tests.swtbot.sequence.AbstractSequenceDiagramTestCase.
             * assertExecutionHasValidScreenBounds(AbstractSequenceDiagramTestCase.java:1305) at
             * org.eclipse.sirius.tests.swtbot.sequence.InteractionUseMoveDownTests.commonPreparationForMoveDownTests(
             * InteractionUseMoveDownTests.java:227) at
             * org.eclipse.sirius.tests.swtbot.sequence.InteractionUseMoveDownTests.
             * testSecondInteractionUseVerticalMoveDownToCenterOfReflexiveSyncCallOnNotSemanticallyCoveredParticipant(
             * InteractionUseMoveDownTests.java:360)
             */
            return;
        }

        int dY = commonPreparationForMoveDownTests();

        // Move second interaction to have its top in the middle of E6
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondInteractionUseBot);
        editor.drag(secondInteractionUseBounds.getTop(), e6Bounds.getCenter());
        bot.waitUntil(checkMoved);

        int dy2 = e6Bounds.getCenter().y - secondInteractionUseBounds.getTop().y;
        int deltaResize = secondInteractionUseBounds.height;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds.getTranslated(0, dy2));

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds.getTranslated(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e6Bot.part(), e6Bounds.getResized(0, deltaResize));

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE6Bot.part(), callMessageOnE6Bounds, true, false);

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.getTranslated(0, dY + deltaResize).y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.getTranslated(0, dY + deltaResize).y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE6Bot.part(), returnMessageOfE6Bounds.getTranslated(0, deltaResize), true, false);

        validateOrdering(24);
    }

    /**
     * test to move up the second interaction use
     */
    public void testSecondInteractionUseVerticalMoveDownToReturnOfReflexiveSyncCallOnNotSemanticallyCoveredParticipant() {
        int dY = commonPreparationForMoveDownTests();

        // Move second interaction to have its top in the middle of E6
        CheckEditPartMoved checkMoved = new CheckEditPartMoved(secondInteractionUseBot);
        editor.drag(secondInteractionUseBounds.getTop(), returnMessageOfE6Bounds.getCenter());
        bot.waitUntil(checkMoved);

        int dy2 = returnMessageOfE6Bounds.getCenter().y - secondInteractionUseBounds.getTop().y;

        int deltaResize = secondInteractionUseBounds.height;

        e6Bot = instanceRoleEditPartEBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) firstInteractionUseBot.part(), firstInteractionUseBounds);
        assertInteractionUseHasValidScreenBounds((InteractionUseEditPart) secondInteractionUseBot.part(), secondInteractionUseBounds.getTranslated(0, dy2));

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds);
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds.getResized(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds.getTranslated(0, dY + deltaResize));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e6Bot.part(), e6Bounds);

        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds.y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE6Bot.part(), callMessageOnE6Bounds, true, false);

        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds.getTranslated(0, dY + deltaResize).y);
        assertMessageVerticalPosition((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds.getTranslated(0, dY + deltaResize).y);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE6Bot.part(), returnMessageOfE6Bounds.getResized(0, deltaResize), true, false);

        validateOrdering(24);
    }

    /**
     * Test that create message move down shift correctly all sub event ends.
     */
    public void testCreateMessageMoveDownToShiftAllSubEventEnds() {

        validateOrdering(20);
        Point startOfCreateMessage = new Point(instanceRoleEditPartDBounds.getCenter().x, firstInteractionUseBounds.y - 10);
        Point finishOfCreateMessage = instanceRoleEditPartEBounds.getCenter();
        createMessage(InteractionsConstants.CREATE_TOOL_ID, startOfCreateMessage.x, startOfCreateMessage.y, finishOfCreateMessage.x, finishOfCreateMessage.y);

        validateOrdering(22);
        SWTBotGefConnectionEditPart createMessageBot = instanceRoleEditPartEBot.parent().targetConnections().get(0);
        Rectangle createMessageBounds = editor.getBounds(createMessageBot);

        int dy = 8;
        int NB_MOVE_DOWN = 5;
        createMessageBot.select();

        // Avoid to drag from the label.
        editor.clickContextMenu("Hide label");

        SequenceDiagram sequenceDiagram = getSequenceDiagram();
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        List<EventEnd> initalSemanticOrdering = new ArrayList<EventEnd>(sequenceDDiagram.getSemanticOrdering().getEventEnds());
        Map<ISequenceEvent, Range> initalState = new HashMap<>();
        Set<ISequenceEvent> initalEvents = sequenceDiagram.getAllOrderedDelimitedSequenceEvents();
        for (ISequenceEvent ise : initalEvents) {
            initalState.put(ise, ise.getVerticalRange());
        }

        for (int i = 1; i <= NB_MOVE_DOWN; i++) {
            createMessageBounds = editor.getBounds(createMessageBot);

            CheckSequenceMessageEditPartMoved checkSequenceMessageEditPartMoved = new CheckSequenceMessageEditPartMoved(createMessageBot);
            editor.drag(createMessageBounds.getCenter(), createMessageBounds.getCenter().translate(0, dy));
            bot.waitUntil(checkSequenceMessageEditPartMoved);

            assertEquals("The expected drag n�" + (i) + " did not occurs", createMessageBounds.getCenter().getTranslated(0, dy).y, editor.getBounds(createMessageBot).getCenter().y, 2);

            // Check graphical and semantic ordering are sync.
            validateOrdering(22);

            // Check no reorder occurs / initial ordering
            assertTrue("No reorder should occur in this test.", Iterables.elementsEqual(initalSemanticOrdering, sequenceDDiagram.getGraphicalOrdering().getEventEnds()));

            Set<ISequenceEvent> events = sequenceDiagram.getAllOrderedDelimitedSequenceEvents();
            assertEquals("We should have the same number of events.", initalEvents.size(), events.size());
            for (ISequenceEvent ise : events) {
                assertEquals("We should have the same number of events.", initalState.get(ise).shifted(i * dy), ise.getVerticalRange());
            }
        }
    }

    /**
     * Test interaction use move up on a create message
     */
    // BUG : move of e2 move only e2 and its IU but not e4, its messages and e5
    public void testInteractionUseMoveUpOnCreateMessage() {

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

        Point startOfIU = new Point(instanceRoleEditPartDBounds.getBottomLeft().translate(-5, 5).x, editor.getBounds(instanceRoleEditPartEBot).getBottomRight().translate(5, 5).y);
        Point finishOfIU = newInstanceRoleEditParteBounds.getBottomRight().translate(10, 10);
        SWTBotGefEditPart newInteractionUseBot = createInteractionUseWithResult(startOfIU, finishOfIU);
        Assert.assertNotNull(newInteractionUseBot);

        Rectangle newInteractionUseBounds = editor.getBounds(newInteractionUseBot);

        // Checks that the newly created interaction use covers LIFELINE_D and
        // LIFELINE_E
        Assert.assertEquals(Math.max(new Rectangle(startOfIU, finishOfIU).height, 50), newInteractionUseBounds.height);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x >= instanceRoleEditPartDBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", newInteractionUseBounds.x <= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x >= instanceRoleEditPartEBounds.x + instanceRoleEditPartEBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", newInteractionUseBounds.getTopRight().x <= instanceRoleEditPartEBounds.getTopRight().x);

        // Move the new interaction upper than the create message
        editor.drag(newInteractionUseBounds.getTopLeft(), firstInteractionUseBounds.getBottomRight().getTranslated(-5, 5));

        // Assert that the new interaction use has not moved
        Assert.assertEquals(newInteractionUseBounds, editor.getBounds(newInteractionUseBot));

        // Delete
        newInteractionUseBot.select();
        deleteSelectedElement();
    }

}
