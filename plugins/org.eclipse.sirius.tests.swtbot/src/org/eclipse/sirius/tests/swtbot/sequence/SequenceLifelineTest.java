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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Test class for lifeline management.
 * 
 * @author smonnier
 */
public class SequenceLifelineTest extends AbstractDefaultModelSequenceTests {

    private static final String NEW_PARTICIPANT4 = "newParticipant4 : ";

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Deletion() throws Exception {
        // Reveal C to scroll to the right
        editor.reveal(LIFELINE_C);

        // Creation
        Point screenPositionC = getScreenPosition(LIFELINE_C);
        ICondition done = new OperationDoneCondition();
        createParticipant(screenPositionC.x - 250, 300);
        bot.waitUntil(done);

        assertEquals("the new lifeline is not at the expected X position", getScreenPosition(LIFELINE_C).x - 250, getScreenPosition(NEW_PARTICIPANT4).x);
        assertEquals("the new lifeline is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, getScreenPosition(NEW_PARTICIPANT4).y);

        // Size
        checkLifelineSize(NEW_PARTICIPANT4, 40);

        // Deletion
        bot.menu("Edit").menu("Delete").click();

        try {
            editor.getEditPart(NEW_PARTICIPANT4);
            fail(WidgetNotFoundException.class + " expected");

        } catch (final WidgetNotFoundException e) {
            // Expected, the edit part must not be found
        }
    }

    /**
     * @throws Exception
     *             Tests the creation of a new participant within the semantic
     *             model .interactions
     * 
     * @author lchituc
     */
    public void test_SemanticLifelineCreation() throws Exception {
        Interaction interaction = (Interaction) localSession.getOpenedSession().getSemanticResources().iterator().next().getContents().get(0);
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        Participant newParticipant = InteractionsFactory.eINSTANCE.createParticipant();
        newParticipant.setName(NEW_LIFELINE_D);
        Command addParticipantCmd = AddCommand.create(domain, interaction, InteractionsPackage.Literals.INTERACTION__PARTICIPANTS, newParticipant);
        domain.getCommandStack().execute(addParticipantCmd);

        SWTBotUtils.waitAllUiEvents();
        // Reload session ?
        // bot.activeShell().bot().button("Yes").click();

        // Refresh in order to see the participant recently added in the
        // .interaction file
        manualRefresh();

        editor.reveal(NEW_LIFELINE_D + " : ");

        // Check the size of the new life line
        checkLifelineSize(NEW_LIFELINE_D + " : ", 40);

        // Validates the new life line has been inserted at the same
        // y position as the other participants
        Point newLifelinePosition = getScreenPosition(NEW_LIFELINE_D + " : ");
        assertEquals("the new lifeline is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, newLifelinePosition.y);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_SimpleSlide() throws Exception {
        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);
        editor.scrollTo(0, 0);

        Point lifelineAPosition = getScreenPosition(LIFELINE_A);
        Point lifelineBPosition = getScreenPosition(LIFELINE_B);
        Dimension lifelineASize = getScreenSize(LIFELINE_A);
        Dimension lifelineBSize = getScreenSize(LIFELINE_B);
        int positionsGap = lifelineBPosition.x - lifelineAPosition.x - 40;

        // move the first lifeline to its south east
        ICondition done = new OperationDoneCondition();
        editor.drag(LIFELINE_A, 200, 200);
        bot.waitUntil(done);

        // validate the lifeline A has only been moved horizontally
        lifelineAPosition = getScreenPosition(LIFELINE_A);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", 200, lifelineAPosition.x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, lifelineAPosition.y);
        assertEquals("the lifeline " + LIFELINE_A + " has not the same height as the begining", lifelineASize.height, getScreenSize(LIFELINE_A).height);
        assertEquals("the lifeline " + LIFELINE_A + " has not the same width as the begining", lifelineASize.width, getScreenSize(LIFELINE_A).width);

        // validate the lifeline B has been moved as much as the lifeline A
        lifelineBPosition = getScreenPosition(LIFELINE_B);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", 200 + positionsGap, lifelineBPosition.x);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, lifelineBPosition.y);
        assertEquals("the lifeline " + LIFELINE_B + " has not the same height as the begining", lifelineBSize.height, getScreenSize(LIFELINE_B).height);
        assertEquals("the lifeline " + LIFELINE_B + " has not the same width as the begining", lifelineBSize.width, getScreenSize(LIFELINE_B).width);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_InvertLifelines() throws Exception {
        editor.maximize();
        try {
            editor.reveal(LIFELINE_A);
            editor.scrollTo(0, 0);

            Point lifelineAPosition = getScreenPosition(LIFELINE_A);
            Point lifelineBPosition = getScreenPosition(LIFELINE_B);
            Dimension lifelineASize = getScreenSize(LIFELINE_A);
            Dimension lifelineBSize = getScreenSize(LIFELINE_B);

            // move the second lifeline to its south west, beyond the first
            // lifeline
            // to invert
            ICondition done = new OperationDoneCondition();
            editor.drag(LIFELINE_B, getScreenPosition(LIFELINE_A).x - 10, getScreenPosition(LIFELINE_A).y - 20);
            bot.waitUntil(done);
            SWTBotUtils.waitAllUiEvents();

            // validate the lifeline B has only been moved horizontally
            lifelineBPosition = getScreenPosition(LIFELINE_B);
            assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", LayoutConstants.LIFELINES_START_X, lifelineBPosition.x);
            assertEquals("the lifeline " + LIFELINE_B + " is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, lifelineBPosition.y);
            assertEquals("the lifeline " + LIFELINE_B + " has not the same height as the begining", lifelineBSize.height, getScreenSize(LIFELINE_B).height);
            assertEquals("the lifeline " + LIFELINE_B + " has not the same width as the begining", lifelineBSize.width, getScreenSize(LIFELINE_B).width);

            // validate the lifeline A has been moved on the right of the new
            // position of B with the default gap
            lifelineAPosition = getScreenPosition(LIFELINE_A);
            assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position",
                    LayoutConstants.LIFELINES_START_X + getScreenSize(LIFELINE_B).width + LayoutConstants.LIFELINES_MIN_X_GAP, lifelineAPosition.x);
            assertEquals("the lifeline " + LIFELINE_A + " is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, lifelineAPosition.y);
            assertEquals("the lifeline " + LIFELINE_A + " has not the same height as the begining", lifelineASize.height, getScreenSize(LIFELINE_A).height);
            assertEquals("the lifeline " + LIFELINE_A + " has not the same width as the begining", lifelineASize.width, getScreenSize(LIFELINE_A).width);
        } finally {
            editor.restore();
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_InsertNewLifeline() throws Exception {
        editor.reveal(LIFELINE_A);
        editor.scrollTo(0, 0);

        Point lifelineAPosition = getScreenPosition(LIFELINE_A);
        Point lifelineBPosition = getScreenPosition(LIFELINE_B);
        Dimension lifelineASize = getScreenSize(LIFELINE_A);
        Dimension lifelineBSize = getScreenSize(LIFELINE_B);

        ICondition done = new OperationDoneCondition();
        createParticipant(lifelineAPosition.x + lifelineASize.width + LayoutConstants.LIFELINES_MIN_X_GAP / 2, 200);
        bot.waitUntil(done);

        // Click on the diagram to unfocus the created element
        editor.click(LayoutConstants.LIFELINES_START_X - 10, LayoutConstants.LIFELINES_START_Y - 10);

        // Refresh in order to arrange the lifelines properly
        manualRefresh();

        // Validate Lifeline A has not moved
        Point newLifelineAPosition = getScreenPosition(LIFELINE_A);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineAPosition.x, newLifelineAPosition.x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected Y position", lifelineAPosition.y, newLifelineAPosition.y);
        assertEquals("the lifeline " + LIFELINE_A + " has not the same height as the begining", lifelineASize.height, getScreenSize(LIFELINE_A).height);
        assertEquals("the lifeline " + LIFELINE_A + " has not the same width as the begining", lifelineASize.width, getScreenSize(LIFELINE_A).width);

        // Validates the new lifeline has been inserted betwen the two lifelines
        // and with the minimum gap after lifeline A
        Point newLifelinePosition = getScreenPosition(NEW_PARTICIPANT4);
        assertEquals("the new lifeline is not at the expected X position", lifelineAPosition.x + lifelineASize.width + LayoutConstants.LIFELINES_MIN_X_GAP, newLifelinePosition.x);
        assertEquals("the new lifeline is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, newLifelinePosition.y);

        // validate the lifeline B has been moved on the right with the minimum
        // gap after the new lifeline
        lifelineBPosition = getScreenPosition(LIFELINE_B);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", newLifelinePosition.x + getScreenSize(NEW_PARTICIPANT4).width + LayoutConstants.LIFELINES_MIN_X_GAP,
                lifelineBPosition.x);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, lifelineBPosition.y);
        assertEquals("the lifeline " + LIFELINE_B + " has not the same height as the begining", lifelineBSize.height, getScreenSize(LIFELINE_B).height);
        assertEquals("the lifeline " + LIFELINE_B + " has not the same width as the begining", lifelineBSize.width, getScreenSize(LIFELINE_B).width);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_InvertLifelinesFromRightToLeft() throws Exception {
        // Go to the origin to avoid scroll bar. Indeed, there is sometimes,
        // without understanding why, scroll bar in this test, and this makes
        // fail the test.
        editor.scrollTo(0, 0);
        editor.reveal(LIFELINE_B);

        Point lifelineAPosition = getScreenPosition(LIFELINE_A);
        Point lifelineBPosition = getScreenPosition(LIFELINE_B);
        Dimension lifelineASize = getScreenSize(LIFELINE_A);
        Dimension lifelineBSize = getScreenSize(LIFELINE_B);

        // The mouse left position click is relative to the screen
        Point scrollSize = GraphicalHelper.getScrollSize((IGraphicalEditPart) editor.getEditPart(LIFELINE_B).part());
        ICondition done = new OperationDoneCondition();
        createParticipant(lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP - scrollSize.x, 200);
        bot.waitUntil(done);

        // Validates the position of the new lifeline
        Point newLifelinePosition = getScreenPosition(NEW_PARTICIPANT4);
        assertEquals("the new lifeline is not at the expected X position", lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP, newLifelinePosition.x);
        assertEquals("the new lifeline is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, newLifelinePosition.y);

        // Move the new lifeline between lifelines A and B
        // The mouse drag is relative to the screen
        // The overlap is no longer supported to avoid wrong reorder.
        int toXPosition = lifelineAPosition.x + lifelineASize.width + LayoutConstants.LIFELINES_MIN_X_GAP / 2 + getScreenSize(NEW_PARTICIPANT4).width / 2 - scrollSize.x;
        done = new OperationDoneCondition();
        editor.dragCentered(NEW_PARTICIPANT4, toXPosition, 200);
        bot.waitUntil(done);

        // Validate Lifeline A has not moved
        Point newLifelineAPosition = getScreenPosition(LIFELINE_A);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineAPosition.x, newLifelineAPosition.x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected Y position", lifelineAPosition.y, newLifelineAPosition.y);
        assertEquals("the lifeline " + LIFELINE_A + " has not the same height as the begining", lifelineASize.height, getScreenSize(LIFELINE_A).height);
        assertEquals("the lifeline " + LIFELINE_A + " has not the same width as the begining", lifelineASize.width, getScreenSize(LIFELINE_A).width);

        // Validates the new lifeline has been moved betwen the two lifelines
        // and with the minimum gap after lifeline A
        newLifelinePosition = getScreenPosition(NEW_PARTICIPANT4);
        assertEquals("the new lifeline is not at the expected X position", lifelineAPosition.x + lifelineASize.width + LayoutConstants.LIFELINES_MIN_X_GAP, newLifelinePosition.x);
        assertEquals("the new lifeline is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, newLifelinePosition.y);

        // validate the lifeline B has been moved on the right with the minimum
        // gap after the new lifeline
        lifelineBPosition = getScreenPosition(LIFELINE_B);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", newLifelinePosition.x + getScreenSize(NEW_PARTICIPANT4).width + LayoutConstants.LIFELINES_MIN_X_GAP,
                lifelineBPosition.x);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected Y position", LayoutConstants.LIFELINES_START_Y, lifelineBPosition.y);
        assertEquals("the lifeline " + LIFELINE_B + " has not the same height as the begining", lifelineBSize.height, getScreenSize(LIFELINE_B).height);
        assertEquals("the lifeline " + LIFELINE_B + " has not the same width as the begining", lifelineBSize.width, getScreenSize(LIFELINE_B).width);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_LifelineMoveOtherGapsStability() throws Exception {
        maximizeEditor(editor);

        // Reveal C to scroll to the right
        editor.reveal(LIFELINE_C);
        Point lifelineCPosition = getLogicalPosition(LIFELINE_C);

        // Creation
        ICondition done = new OperationDoneCondition();
        createParticipant(lifelineCPosition.x - 200, 300);
        bot.waitUntil(done);
        editor.reveal(LIFELINE_A);
        editor.scrollTo(0, 0);

        Point lifelineAPosition = getLogicalPosition(LIFELINE_A);
        Point lifelineBPosition = getLogicalPosition(LIFELINE_B);
        int deltaX_B_NP = lifelineBPosition.getDifference(getLogicalPosition(NEW_PARTICIPANT4)).width;
        int deltaX_B_C = lifelineBPosition.getDifference(getLogicalPosition(LIFELINE_C)).width;

        // move A to the right
        int hGap = 100;
        done = new OperationDoneCondition();
        editor.drag(LIFELINE_A, lifelineAPosition.x + hGap, 200);
        bot.waitUntil(done);

        // Lifelines where the gap is not respected are moved (new Participant
        // 4, B but not C)
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineAPosition.x + hGap, getLogicalPosition(LIFELINE_A).x);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", lifelineBPosition.x + hGap - 40, getLogicalPosition(LIFELINE_B).x);
        editor.reveal(LIFELINE_C);
        assertEquals("the lifeline " + NEW_PARTICIPANT4 + " is not at the expected X position", deltaX_B_NP, getLogicalPosition(LIFELINE_B).getDifference(getLogicalPosition(NEW_PARTICIPANT4)).width);
        assertEquals("the lifeline " + LIFELINE_C + " is not at the expected X position", deltaX_B_C, getLogicalPosition(LIFELINE_B).getDifference(getLogicalPosition(LIFELINE_C)).width);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_LifelineInsertOtherGapsStability_enoughGap() throws Exception {
        maximizeEditor(editor);
        // Reveal C to scroll to the right
        editor.reveal(LIFELINE_C);

        // Creation
        Point screenPositionC = getScreenPosition(LIFELINE_C);
        ICondition done = new OperationDoneCondition();
        createParticipant(screenPositionC.x - 250, 300);
        bot.waitUntil(done);

        editor.reveal(LIFELINE_A);
        editor.scrollTo(0, 0);

        Point lifelineBPosition = getScreenPosition(LIFELINE_B);
        Dimension lifelineBSize = getScreenSize(LIFELINE_B);
        int deltaX_B_NP = lifelineBPosition.getDifference(getLogicalPosition(NEW_PARTICIPANT4)).width;
        int deltaX_B_C = lifelineBPosition.getDifference(getLogicalPosition(LIFELINE_C)).width;

        // move A on the right of B
        Point scrollSize = GraphicalHelper.getScrollSize((IGraphicalEditPart) editor.getEditPart(LIFELINE_A).part());
        done = new OperationDoneCondition();
        editor.drag(LIFELINE_A, lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP - scrollSize.x, 200);
        bot.waitUntil(done);

        // the new lifeline is too far from the new position of LIFELINE_A
        // Only A will be moved here
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", lifelineBPosition.x, getScreenPosition(LIFELINE_B).x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(LIFELINE_A).x);
        editor.reveal(LIFELINE_C);
        assertEquals("The gap between " + NEW_PARTICIPANT4 + " and " + LIFELINE_B + " is not stable", deltaX_B_NP,
                getLogicalPosition(LIFELINE_B).getDifference(getLogicalPosition(NEW_PARTICIPANT4)).width);
        assertEquals("The gap between " + LIFELINE_C + " and " + LIFELINE_B + " is not stable", deltaX_B_C, getLogicalPosition(LIFELINE_B).getDifference(getLogicalPosition(LIFELINE_C)).width);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_LifelineInsertOtherGapsStability_enoughGap_tooClose() throws Exception {
        maximizeEditor(editor);
        // Reveal C to scroll to the right
        editor.reveal(LIFELINE_C);

        // Creation
        Point screenPositionC = getScreenPosition(LIFELINE_C);
        ICondition done = new OperationDoneCondition();
        createParticipant(screenPositionC.x - 250, 300);
        bot.waitUntil(done);

        editor.reveal(LIFELINE_A);
        editor.scrollTo(0, 0);

        Point lifelineBPosition = getScreenPosition(LIFELINE_B);
        Dimension lifelineBSize = getScreenSize(LIFELINE_B);
        int deltaX_B_NP = lifelineBPosition.getDifference(getLogicalPosition(NEW_PARTICIPANT4)).width;
        int deltaX_NP_C = getLogicalPosition(NEW_PARTICIPANT4).getDifference(getLogicalPosition(LIFELINE_C)).width;

        // move A on the right of B
        Point scrollSize = GraphicalHelper.getScrollSize((IGraphicalEditPart) editor.getEditPart(LIFELINE_A).part());
        done = new OperationDoneCondition();
        editor.drag(LIFELINE_A, lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP / 2 - scrollSize.x, 200);
        bot.waitUntil(done);

        // the new lifeline is too far from the new position of LIFELINE_A
        // Only A will be moved here
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", lifelineBPosition.x, getScreenPosition(LIFELINE_B).x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(LIFELINE_A).x);
        editor.reveal(LIFELINE_C);
        assertEquals("The gap between " + NEW_PARTICIPANT4 + " and " + LIFELINE_B + " is not stable", deltaX_B_NP - LayoutConstants.LIFELINES_MIN_X_GAP / 2, getLogicalPosition(LIFELINE_B)
                .getDifference(getLogicalPosition(NEW_PARTICIPANT4)).width);
        assertEquals("The gap between " + LIFELINE_C + " and " + NEW_PARTICIPANT4 + " is not stable", deltaX_NP_C,
                getLogicalPosition(NEW_PARTICIPANT4).getDifference(getLogicalPosition(LIFELINE_C)).width);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_LifelineInsertOtherGapsStability_notEnoughGap() throws Exception {
        maximizeEditor(editor);
        // Reveal C to scroll to the right
        editor.reveal(LIFELINE_C);

        // Creation of a lifeline after B
        // The gap will be larger than the minimum gap but small enough to not
        // be able to insert a lifeline
        editor.reveal(LIFELINE_B);
        Point screenPositionB = getScreenPosition(LIFELINE_B);
        Dimension lifelineBSize = getScreenSize(LIFELINE_B);
        ICondition done = new OperationDoneCondition();
        createParticipant(screenPositionB.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP + 100, 300);
        bot.waitUntil(done);

        editor.reveal(LIFELINE_A);
        editor.scrollTo(0, 0);

        Point lifelineBPosition = getScreenPosition(LIFELINE_B);
        Point lifelineCPosition = getScreenPosition(LIFELINE_C);
        Point lifelineNPPosition = getScreenPosition(NEW_LIFELINE);
        int deltaXNC = lifelineCPosition.getDifference(lifelineNPPosition).width;

        // move A on the right of B
        Point scrollSize = GraphicalHelper.getScrollSize((IGraphicalEditPart) editor.getEditPart(LIFELINE_A).part());
        done = new OperationDoneCondition();
        editor.drag(LIFELINE_A, lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP / 2 - scrollSize.x, 200);
        bot.waitUntil(done);

        // the previous gap between B and the new lifeline must be kept between
        // A and the new lifeline
        // B should not move
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", lifelineBPosition.x, getScreenPosition(LIFELINE_B).x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(LIFELINE_A).x);
        assertEquals("the new lifeline is not at the expected X position", editor.getBounds(editor.getEditPart(LIFELINE_A)).right() + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(NEW_PARTICIPANT4).x);
        assertEquals("The gap between " + LIFELINE_C + " and " + NEW_PARTICIPANT4 + " is not stable", deltaXNC, getScreenPosition(LIFELINE_C).getDifference(getScreenPosition(NEW_PARTICIPANT4)).width);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Undo_Redo_Move() throws Exception {
        maximizeEditor(editor);
        // Reveal C to scroll to the right
        editor.reveal(LIFELINE_C);

        // Creation of a lifeline after B
        // The gap will be larger than the minimum gap but small enough to not
        // be able to insert a lifeline
        editor.reveal(LIFELINE_B);
        Point screenPositionB = getScreenPosition(LIFELINE_B);
        Dimension lifelineBSize = getScreenSize(LIFELINE_B);
        ICondition done = new OperationDoneCondition();
        createParticipant(screenPositionB.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP + 100, 300);
        bot.waitUntil(done);

        editor.reveal(LIFELINE_A);
        editor.scrollTo(0, 0);

        Point lifelineAPosition = getScreenPosition(LIFELINE_A);
        Point lifelineBPosition = getScreenPosition(LIFELINE_B);
        Point lifelineCPosition = getScreenPosition(LIFELINE_C);
        Point lifelineNPPosition = getScreenPosition(NEW_LIFELINE);
        int deltaXNC = lifelineCPosition.getDifference(lifelineNPPosition).width;

        // move A on the right of B
        Point scrollSize = GraphicalHelper.getScrollSize((IGraphicalEditPart) editor.getEditPart(LIFELINE_A).part());
        done = new OperationDoneCondition();
        editor.drag(LIFELINE_A, lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP / 2 - scrollSize.x, 200);
        bot.waitUntil(done);

        // the previous gap between B and the new lifeline must be kept between
        // A and the new lifeline
        // B should not move
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", lifelineBPosition.x, getScreenPosition(LIFELINE_B).x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(LIFELINE_A).x);
        assertEquals("the new lifeline is not at the expected X position", editor.getBounds(editor.getEditPart(LIFELINE_A)).right() + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(NEW_PARTICIPANT4).x);
        assertEquals("the lifeline " + LIFELINE_C + " is not at the expected X position", deltaXNC, getScreenPosition(LIFELINE_C).getDifference(getScreenPosition(NEW_PARTICIPANT4)).width);

        undo("InstanceRole move");

        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineAPosition.x, getScreenPosition(LIFELINE_A).x);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", lifelineBPosition.x, getScreenPosition(LIFELINE_B).x);
        assertEquals("the new lifeline is not at the expected X position", lifelineNPPosition.x, getScreenPosition(NEW_LIFELINE).x);
        assertEquals("the lifeline " + LIFELINE_C + " is not at the expected X position", lifelineCPosition.x, getScreenPosition(LIFELINE_C).x);

        redo("InstanceRole move");

        // the previous gap between B and the new lifeline must be kept between
        // A and the new lifeline
        // B should not move
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", lifelineBPosition.x, getScreenPosition(LIFELINE_B).x);
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", lifelineBPosition.x + lifelineBSize.width + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(LIFELINE_A).x);
        assertEquals("the new lifeline is not at the expected X position", editor.getBounds(editor.getEditPart(LIFELINE_A)).right() + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(NEW_PARTICIPANT4).x);
        assertEquals("the new lifeline is not at the expected X position", editor.getBounds(editor.getEditPart(LIFELINE_A)).right() + LayoutConstants.LIFELINES_MIN_X_GAP,
                getScreenPosition(NEW_PARTICIPANT4).x);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_ArrangeAll() throws Exception {
        // Reveal C to scroll to the right
        editor.reveal(LIFELINE_C);

        // Creation
        Rectangle aBounds = getBounds(LIFELINE_A, false);
        Rectangle bBounds = getBounds(LIFELINE_B, false);
        Rectangle cBounds = getBounds(LIFELINE_C, false);
        ICondition done = new OperationDoneCondition();
        createParticipant(getBounds(LIFELINE_B, true).right() + 10, 300);
        bot.waitUntil(done);

        // Arrange All
        done = new OperationDoneCondition();
        arrangeAll();
        bot.waitUntil(done);
        Rectangle npBounds = getBounds(NEW_LIFELINE, false);

        assertArrangeAllEffect(aBounds, bBounds, npBounds);

        undo(ARRANGE_ALL_COMMAND);

        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", aBounds.x, getLogicalPosition(LIFELINE_A).x);
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", bBounds.x, getLogicalPosition(LIFELINE_B).x);
        assertEquals("the new lifeline is not at the expected X position", npBounds.x, getLogicalPosition(NEW_PARTICIPANT4).x);
        assertEquals("the lifeline " + LIFELINE_C + " is not at the expected X position", cBounds.x, getLogicalPosition(LIFELINE_C).x);

        redo(ARRANGE_ALL_COMMAND);

        assertArrangeAllEffect(aBounds, bBounds, npBounds);

    }

    private void assertArrangeAllEffect(Rectangle aBounds, Rectangle bBounds, Rectangle npBounds) {
        int expectedX = LayoutConstants.LIFELINES_START_X;
        assertEquals("the lifeline " + LIFELINE_A + " is not at the expected X position", expectedX, getLogicalPosition(LIFELINE_A).x);
        expectedX += aBounds.width + LayoutConstants.LIFELINES_MIN_PACKED_X_GAP;
        assertEquals("the lifeline " + LIFELINE_B + " is not at the expected X position", expectedX, getLogicalPosition(LIFELINE_B).x);
        expectedX += bBounds.width + LayoutConstants.LIFELINES_MIN_PACKED_X_GAP;
        assertEquals("the new lifeline is not at the expected X position", expectedX, getLogicalPosition(NEW_PARTICIPANT4).x);
        expectedX += npBounds.width + LayoutConstants.LIFELINES_MIN_PACKED_X_GAP;
        assertEquals("the lifeline " + LIFELINE_C + " is not at the expected X position", expectedX, getLogicalPosition(LIFELINE_C).x);
    }

}
