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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.StateEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Test class validating that states can not have message.
 * 
 * @author smonnier
 */
public class StateHierarchyTests extends AbstractStatesSequenceTests {

    // Main part
    private SWTBotGefEditPart sequenceDiagramBot;

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    private Rectangle instanceRoleEditPartABounds;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private Rectangle instanceRoleEditPartBBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);

        instanceRoleEditPartABounds = editor.getBounds(instanceRoleEditPartABot);
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);

        sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();
    }

    /**
     * Test method.
     */
    public void test_Reparent_State_On_State_Not_Possible() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        doCreateS2OnLifelineA(300, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 2));

        editor.drag(stateS1ScreenBounds.getTop(), stateS2ScreenBounds.getCenter());
        bot.waitUntil(new CheckSelectedCondition(editor, (ExecutionEditPart) stateS1Bot.part()));

        // Validates the state bounds
        assertStateHasValidScreenBounds(stateS1Bot, stateS1ScreenBounds);
        assertStateHasValidScreenBounds(stateS2Bot, stateS2ScreenBounds);
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Reparent_State_On_Execution() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        Option<SWTBotGefEditPart> executionOption = createExecutionWithResult(stateS1ScreenBounds.getBottom().getTranslated(0, 100));
        Assert.assertTrue(executionOption.some());

        SWTBotGefEditPart executionBot = executionOption.get();
        Rectangle executionBounds = editor.getBounds(executionBot);

        CheckEditPartResized checkResized = new CheckEditPartResized(executionBot);
        editor.drag(stateS1ScreenBounds.getTop(), executionBounds.getCenter());
        bot.waitUntil(checkResized);

        int deltaY = executionBounds.getCenter().y - stateS1ScreenBounds.getTop().y;

        // Validates the state bounds
        assertStateHasValidScreenBounds(editor.getEditPart(STATE_S1).parent(), stateS1ScreenBounds.getTranslated(0, deltaY));
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) executionBot.part(), executionBounds.getResized(0, stateS1ScreenBounds.height), true);
        Assert.assertEquals(executionBounds.getResized(0, stateS1ScreenBounds.height), editor.getBounds(executionBot));
        validateOrdering(4);
    }

    /**
     * Test method.
     */
    public void test_Reparent_State_On_Sub_Execution() {
        doCreateS1OnLifelineA(200, ZoomLevel.ZOOM_100.getAmount());
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, StateEditPart.class, 1));

        // Create an execution with a sub execution
        Option<SWTBotGefEditPart> executionOption1 = createExecutionWithResult(stateS1ScreenBounds.getBottom().getTranslated(0, 100));
        Assert.assertTrue(executionOption1.some());
        SWTBotGefEditPart executionBot1 = executionOption1.get();
        Rectangle executionBounds1 = editor.getBounds(executionBot1);

        Option<SWTBotGefEditPart> executionOption2 = createExecutionWithResult(executionBounds1.getCenter());
        Assert.assertTrue(executionOption2.some());
        SWTBotGefEditPart executionBot2 = executionOption2.get();
        Rectangle executionBounds2 = editor.getBounds(executionBot2);
        executionBounds1 = editor.getBounds(executionBot1);

        // Move state to sub execution
        CheckEditPartResized checkResized = new CheckEditPartResized(executionBot2);
        editor.drag(stateS1ScreenBounds.getTop(), executionBounds2.getCenter());
        bot.waitUntil(checkResized);

        int deltaX = executionBounds2.getCenter().x - stateS1ScreenBounds.getCenter().x;
        int deltaY = executionBounds2.getCenter().y - stateS1ScreenBounds.getTop().y;

        // Validates the state bounds
        assertStateHasValidScreenBounds(editor.getEditPart(STATE_S1).parent(), stateS1ScreenBounds.getTranslated(deltaX, deltaY));
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) executionBot1.part(), executionBounds1.getResized(0, stateS1ScreenBounds.height), true);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) executionBot2.part(), executionBounds2.getResized(0, stateS1ScreenBounds.height), true);
        Assert.assertEquals(executionBounds1.getResized(0, stateS1ScreenBounds.height), editor.getBounds(executionBot1));
        validateOrdering(6);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        instanceRoleEditPartABot = null;
        instanceRoleEditPartABounds = null;

        super.tearDown();
    }
}
