/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Test class for manual creation state management
 * 
 * @author mporhel
 */
public class PunctualStateTests extends AbstractDefaultModelSequenceTests {

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Lifelines";

    // Main part
    private SWTBotGefEditPart sequenceDiagramBot;

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    // State
    private SWTBotGefEditPart stateS1Bot;

    private SWTBotGefEditPart stateS2Bot;

    private Rectangle stateS1ScreenBounds;

    private Rectangle stateS2ScreenBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);

        sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();

        editor.maximize();
        arrangeAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        instanceRoleEditPartABot = null;
        super.tearDown();
    }

    /**
     * create the first state on lifeline A
     * 
     * @param yScreenStateS1
     *            vertical position
     * @param zoom
     *            amount of zoom
     */
    protected void doCreateS1OnLifelineA(int yScreenStateS1, double zoom) {

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        // Creation of an state
        Option<SWTBotGefEditPart> newStateOption = createPunctualStateWithResult(lifelineAPosition, yScreenStateS1);
        Assert.assertTrue(newStateOption.some());

        stateS1Bot = newStateOption.get();

        // Validates the position of the state
        stateS1ScreenBounds = assertStateHasValidScreenBounds(stateS1Bot, new Rectangle(0, yScreenStateS1, LayoutConstants.DEFAULT_EXECUTION_WIDTH,
                (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
        Assert.assertEquals(lifelineAPosition, stateS1ScreenBounds.getCenter().x);

        // Test Creation of a state on a state is not possible
        int yExecA1 = stateS1ScreenBounds.getCenter().y;
        Option<SWTBotGefEditPart> noStateOption = createPunctualStateWithResult(lifelineAPosition, yExecA1);
        Assert.assertFalse(noStateOption.some());
    }

    /**
     * create a state on lifeline B
     * 
     * @param yScreenStateS1
     *            vertical position
     * @param zoom
     *            amount of zoom
     */
    protected void doCreateS2OnLifelineB(int yScreenStateS1, double zoom) {
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of an state on lifeline B
        Option<SWTBotGefEditPart> newStateOption = createPunctualStateWithResult(lifelineBPosition, yScreenStateS1);
        Assert.assertTrue(newStateOption.some());

        stateS2Bot = newStateOption.get();

        stateS2ScreenBounds = assertStateHasValidScreenBounds(stateS2Bot, new Rectangle(0, yScreenStateS1, (int) (LayoutConstants.DEFAULT_EXECUTION_WIDTH * zoom),
                (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
    }

    /**
     * create the second state on lifeline A
     * 
     * @param yScreenStateS2
     *            vertical position
     * @param zoom
     *            amount of zoom
     */
    protected void doCreateS2OnLifelineA(int yScreenStateS2, double zoom) {
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);

        // Creation of an state on lifeline B
        Option<SWTBotGefEditPart> newStateOption = createPunctualStateWithResult(lifelineAPosition, yScreenStateS2);
        Assert.assertTrue(newStateOption.some());

        stateS2Bot = newStateOption.get();

        stateS2ScreenBounds = assertStateHasValidScreenBounds(stateS2Bot, new Rectangle(0, yScreenStateS2, (int) (LayoutConstants.DEFAULT_EXECUTION_WIDTH * zoom),
                (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
    }

    /**
     * Test method.
     */
    public void test_Creation() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        validateOrdering(1);
    }

    /**
     * Test method.
     */
    public void test_Creation2() {
        editor.maximize();

        test_Creation();

        validateOrdering(1);

        doCreateS2OnLifelineB(250, ZoomLevel.ZOOM_100.getAmount());

        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

        validateOrdering(2);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void test_Creation_Deletion() throws Exception {
        test_Creation();

        // Deletion of the states
        editor.click(stateS1ScreenBounds.getLocation());
        bot.menu("Edit").menu("Delete").click();

        // Click on the diagram to unfocus the created element
        editor.click(0, 0);

        assertStateDoesNotExist(STATE_S1);
    }

    /**
     * Test method.
     */
    public void test_Zoom() {
        try {
            ZoomLevel zoom50 = ZoomLevel.ZOOM_50;
            editor.zoom(ZoomLevel.ZOOM_50);

            doCreateS1OnLifelineA(75, zoom50.getAmount());

            validateOrdering(1);

            doCreateS2OnLifelineB(125, zoom50.getAmount());

            validateOrdering(2);

            bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 2));

            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);

            // manualRefresh();

            // Move down the state beyond the state on lifeline B
            Point expectedMove = new Point(0, 75);
            CheckEditPartMoved checkMoved = new CheckEditPartMoved(stateS1Bot);
            editor.drag(stateS1ScreenBounds.getLocation().getCopy(), stateS1ScreenBounds.getLocation().getCopy().getTranslated(expectedMove));
            bot.waitUntil(checkMoved);

            // Expected effect
            stateS1ScreenBounds.translate(expectedMove);

            // Validates the state bounds
            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
            assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

            validateOrdering(2);

            // Resize up the state beyond the state on lifeline B
            expectedMove = new Point(0, -75);
            editor.click(stateS1ScreenBounds.getTop());
            CheckEditPartResized checkResized = new CheckEditPartResized(stateS1Bot);
            editor.drag(stateS1ScreenBounds.getTop(), stateS1ScreenBounds.getTop().getCopy().getTranslated(expectedMove));
            bot.waitUntil(checkResized);

            // Expected effect
            stateS1ScreenBounds.translate(expectedMove);
            stateS1ScreenBounds.resize(0, expectedMove.getNegated().y);
            // move State S2 : reorder occurs : the center of the punctual state
            // moved
            stateS2ScreenBounds.y = stateS1ScreenBounds.bottom() + (int) (zoom50.getAmount() * LayoutConstants.EXECUTION_CHILDREN_MARGIN);

            // Validates the state bounds
            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
            assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

            validateOrdering(2);

            // Click on the diagram to unfocus the created element
            editor.click(0, 0);
            // Arrange all after moving and resizing
            arrangeAll();

            // Expected layout
            int timeStart = getScreenPosition(LIFELINE_A).y + getScreenSize(LIFELINE_A).height;
            int cA0 = timeStart + (int) (zoom50.getAmount() * (LayoutConstants.TIME_START_OFFSET + LayoutConstants.INTERACTION_STATE_MIN_HEIGHT_AFTER_LAYOUT / 2));
            int cB0 = cA0 + (int) (zoom50.getAmount() * (LayoutConstants.INTERACTION_STATE_MIN_HEIGHT_AFTER_LAYOUT + LayoutConstants.MIN_INTER_SEQUENCE_EVENTS_VERTICAL_GAP));
            int layoutedPunctualStateSize = (int) (zoom50.getAmount() * LayoutConstants.INTERACTION_STATE_MIN_HEIGHT_AFTER_LAYOUT);

            stateS1ScreenBounds.y = cA0 - layoutedPunctualStateSize / 2;
            stateS1ScreenBounds.height = layoutedPunctualStateSize;
            stateS2ScreenBounds.y = cB0 - layoutedPunctualStateSize / 2;
            stateS2ScreenBounds.height = layoutedPunctualStateSize;

            // Validates the state bounds
            assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
            assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

            validateOrdering(2);

            // Undo Arrange All
            undo("Arrange All");

        } finally {
            // Set zoom to default
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Test method.
     */
    public void test_Change_States_Order() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        validateOrdering(1);

        doCreateS2OnLifelineA(250, ZoomLevel.ZOOM_100.getAmount());
        validateOrdering(2);

        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 2));

        CheckEditPartMoved checkMoved = new CheckEditPartMoved(stateS1Bot);
        editor.drag(stateS1ScreenBounds.getTop(), stateS2ScreenBounds.getBottom().getTranslated(0, 50));
        bot.waitUntil(checkMoved);

        int deltaY = stateS2ScreenBounds.getBottom().getTranslated(0, 50).y - stateS1ScreenBounds.getTop().y;

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds.getTranslated(0, deltaY));
        assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);

        validateOrdering(2);
    }

    /**
     * Test method.
     */
    public void test_Move_In_Title_Range() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));
        validateOrdering(1);

        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);
        // Creation of a CF on lifeline B
        int cfCreationY = 300;
        SWTBotGefEditPart newCFBot = createCombinedFragmentWithResult(new Point(lifelineBPosition, cfCreationY));
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 1));
        validateOrdering(4);
        Assert.assertTrue(newCFBot != null);

        // Check CF bounds
        Rectangle newCFBounds = editor.getBounds(newCFBot);
        assertEquals(cfCreationY, newCFBounds.y);
        assertEquals(LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT + LayoutConstants.DEFAULT_OPERAND_HEIGHT, newCFBounds.height);
        assertEquals(LayoutConstants.DEFAULT_COMBINED_FRAGMENT_HEIGHT, newCFBounds.height);

        ICondition cond = new CheckSelectedCondition(editor, (IGraphicalEditPart) stateS1Bot.part());
        editor.drag(stateS1ScreenBounds.getCenter(), new Point(0, newCFBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2));
        bot.waitUntil(cond);

        assertEquals(newCFBounds, editor.getBounds(newCFBot));
        assertEquals(stateS1ScreenBounds, editor.getBounds(stateS1Bot));

        Option<SWTBotGefEditPart> newStateOption = createPunctualStateWithResult(newCFBounds.getCenter().x, newCFBounds.y + LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT / 2);
        assertFalse(newStateOption.some());

        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Move_In_Lifeline_Start_without_Stack_Overflow() {
        // Create a sync call
        Point start = new Point(getLifelineScreenX(LIFELINE_A), 200);
        Point end = new Point(getLifelineScreenX(LIFELINE_B), 200);
        createMessage(InteractionsConstants.SYNC_CALL_TOOL_ID, start, end);
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, ExecutionEditPart.class, 1));
        validateOrdering(4);

        // Add a punctual state
        SWTBotGefEditPart e1Bot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        Rectangle e1Bounds = editor.getBounds(e1Bot);
        doCreateS2OnLifelineB(e1Bounds.getTop().getTranslated(0, 10).y, ZoomLevel.ZOOM_100.getAmount());
        validateOrdering(5);

        // Move up the sync call
        Rectangle instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);
        ICondition cond = new CheckEditPartMoved(e1Bot);
        editor.drag(e1Bounds.getTop(), instanceRoleEditPartBBounds.getBottom().getTranslated(0, 5));
        bot.waitUntil(cond);
        validateOrdering(5);

        // Deselect
        editor.click(0, 0);

        // Move up the state
        cond = new CheckEditPartMoved(e1Bot);
        stateS2ScreenBounds = editor.getBounds(stateS2Bot);
        editor.drag(stateS2ScreenBounds.getTop(), instanceRoleEditPartBBounds.getBottom().getTranslated(0, -LayoutConstants.TIME_START_MIN_OFFSET));
        bot.waitUntil(cond);
        validateOrdering(5);
    }
}
