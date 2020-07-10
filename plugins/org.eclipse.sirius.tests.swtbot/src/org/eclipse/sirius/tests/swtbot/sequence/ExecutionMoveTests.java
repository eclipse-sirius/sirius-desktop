/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Execution Move Tests.
 * 
 * @author pcdavid
 */
// WARNING for SWTBot tests : reparent execution recreate editParts => reget the
// bot
public class ExecutionMoveTests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "vp-851/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "executions.interactions";

    private static final String SESSION_FILE = "executions.aird";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return MODEL;
    }

    @Override
    protected String getSessionModel() {
        return SESSION_FILE;
    }

    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    private SWTBotGefEditPart sequenceDiagramBot;

    private Interaction interaction;

    private SWTBotGefEditPart instanceRoleABot;

    private SWTBotGefEditPart instanceRoleBBot;

    private SWTBotGefEditPart e1Bot;

    private SWTBotGefEditPart e2Bot;

    private SWTBotGefEditPart e3Bot;

    private SWTBotGefEditPart e4Bot;

    private SWTBotGefEditPart e5Bot;

    private SWTBotGefEditPart e6Bot;

    private SWTBotGefEditPart e7Bot;

    private SWTBotGefEditPart e8Bot;

    private SWTBotGefEditPart e9Bot;

    private EObject e1;

    private EObject e2;

    private EObject e3;

    private EObject e4;

    private EObject e5;

    private EObject e6;

    private EObject e7;

    private EObject e8;

    private EObject e9;

    private Rectangle e1Bounds;

    private Rectangle e2Bounds;

    private Rectangle e3Bounds;

    private Rectangle e4Bounds;

    private Rectangle e5Bounds;

    private Rectangle e6Bounds;

    private Rectangle e7Bounds;

    private Rectangle e8Bounds;

    private Rectangle e9Bounds;

    private static final int NB_ENDS_EXECUTIONS = (5 * 2) + (4 * 2);

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.mainEditPart().part().getViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, Boolean.FALSE);
        editor.mainEditPart().part().getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.FALSE);

        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);

        sequenceDiagramBot = editor.mainEditPart();
        SequenceDiagramEditPart sequenceDiagramEditPart = (SequenceDiagramEditPart) sequenceDiagramBot.part();
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();
        interaction = (Interaction) sequenceDDiagram.getTarget();

        // InstanceRoles
        instanceRoleABot = editor.getEditPart(LIFELINE_A);
        instanceRoleBBot = editor.getEditPart(LIFELINE_B);

        // Execs
        e1 = interaction.getExecutions().get(2);
        e2 = interaction.getExecutions().get(3);
        e3 = interaction.getExecutions().get(8);
        e4 = interaction.getExecutions().get(4);
        e5 = interaction.getExecutions().get(6);
        e6 = interaction.getExecutions().get(0);
        e7 = interaction.getExecutions().get(1);
        e8 = interaction.getExecutions().get(7);
        e9 = interaction.getExecutions().get(5);

        e1Bot = instanceRoleABot.parent().descendants(WithSemantic.withSemantic(e1)).get(0);
        e2Bot = e1Bot.descendants(WithSemantic.withSemantic(e2)).get(0);
        e3Bot = instanceRoleABot.parent().descendants(WithSemantic.withSemantic(e3)).get(0);
        e4Bot = e2Bot.descendants(WithSemantic.withSemantic(e4)).get(0);
        e5Bot = e1Bot.descendants(WithSemantic.withSemantic(e5)).get(0);
        e6Bot = instanceRoleBBot.parent().descendants(WithSemantic.withSemantic(e6)).get(0);
        e7Bot = instanceRoleBBot.parent().descendants(WithSemantic.withSemantic(e7)).get(0);
        e8Bot = instanceRoleBBot.parent().descendants(WithSemantic.withSemantic(e8)).get(0);
        e9Bot = instanceRoleBBot.parent().descendants(WithSemantic.withSemantic(e9)).get(0);

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
        e3Bounds = editor.getBounds(e3Bot);
        e4Bounds = editor.getBounds(e4Bot);
        e5Bounds = editor.getBounds(e5Bot);
        e6Bounds = editor.getBounds(e6Bot);
        e7Bounds = editor.getBounds(e7Bot);
        e8Bounds = editor.getBounds(e8Bot);
        e9Bounds = editor.getBounds(e9Bot);

    }

    /**
     * Move e6 with a message in lifeline start zone.
     */
    public void test_move_execution_with_message_in_init_zone() {
    	if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        ICondition done = new OperationDoneCondition();
        arrangeAll();
        bot.waitUntil(done);

        Rectangle bounds = getExecutionLogicalBounds(LIFELINE_B, 0);
        Point e6Center = bounds.getCenter();

        done = new OperationDoneCondition();
        createMessage(InteractionsConstants.READ_TOOL_ID, e6Center, new Point(getLifelineScreenX(LIFELINE_C), e6Center.y));
        bot.waitUntil(done);

        done = new OperationDoneCondition();
        arrangeAll();
        bot.waitUntil(done);

        bounds = getExecutionLogicalBounds(LIFELINE_B, 0);
        assertEquals(130, bounds.y);
        assertEquals(40, bounds.height);

        int pos = getSequenceMessageVerticalPosition("m1");
        assertEquals(150, pos);

        Rectangle following = getExecutionLogicalBounds(LIFELINE_B, 1);
        assertEquals(190, following.y);
        assertEquals(40, following.height);

        done = new OperationDoneCondition();
        int dY = 15;
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(0, -dY));
        bot.waitUntil(done);

        bounds = getExecutionLogicalBounds(LIFELINE_B, 0);
        assertEquals(115, bounds.y);
        assertEquals(40, bounds.height);

        pos = getSequenceMessageVerticalPosition("m1");
        assertEquals(135, pos);

        following = getExecutionLogicalBounds(LIFELINE_B, 1);
        assertEquals(190, following.y);
        assertEquals(40, following.height);

    }

    /**
     * Move e3 between e2 and e5
     */
    public void test_move_execution_inside_new_parent_between_siblings() {
        int newY = (e2Bounds.getBottom().y + e5Bounds.y) / 2;
        int dy = newY - e3Bounds.y;
        
        e3Bot.select();
        
        ICondition conditionE1 = new CheckEditPartResized(e1Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        ICondition conditionE9 = new CheckEditPartResized(e9Bot);
        
        editor.drag(e3Bot, e3Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE8);
        bot.waitUntil(conditionE9);

        e3Bot = e1Bot.descendants(WithSemantic.withSemantic(e3)).get(0);

        // On lifeline 'a'
        int dxShift = 3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4;
        int dyShift = e3Bounds.height;
        Assert.assertEquals(e1Bounds.getResized(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(dxShift, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyShift), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds, editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds.getResized(0, dyShift), editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move e3 just above e1 but close enough to trigger a shift.
     */
    public void test_move_plain_execution_above_sibling_shifts_sibling_and_children() {
    	if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
    	int newY = 170;
        int dy = newY - e3Bounds.y;
        
        e3Bot.select();
        
        ICondition conditionE1 = new CheckEditPartMoved(e1Bot);
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE7 = new CheckEditPartMoved(e7Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        ICondition conditionE9 = new CheckEditPartMoved(e9Bot);
        
        editor.drag(e3Bot, e3Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE7);
        bot.waitUntil(conditionE8);
        bot.waitUntil(conditionE9);

        // On lifeline 'a'
        int dyShift = e3Bounds.height;
        Assert.assertEquals(e1Bounds.getTranslated(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyShift), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyShift), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyShift), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds.getTranslated(0, dyShift), editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds.getTranslated(0, dyShift), editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move the top of e1 inside e3.
     */
    public void test_move_execution_group_on_top_of_smaller_execution() {
        int newY = e3Bounds.getCenter().y;
        int dy = newY - e1Bounds.y;
        
        e1Bot.select();
        
        ICondition conditionE3 = new CheckEditPartResized(e3Bot);
        ICondition conditionE8 = new CheckEditPartResized(e8Bot);
        
        editor.drag(e1Bot, e1Bounds.x, newY);
        
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE8);

        // e1, e2, e4 and e5 reparented => reget bot
        e1Bot = e3Bot.descendants(WithSemantic.withSemantic(e1)).get(0);
        e2Bot = e1Bot.descendants(WithSemantic.withSemantic(e2)).get(0);
        e4Bot = e2Bot.descendants(WithSemantic.withSemantic(e4)).get(0);
        e5Bot = e1Bot.descendants(WithSemantic.withSemantic(e5)).get(0);

        // On lifeline 'a'
        int dxShift = 3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4;
        int dyShift = e1Bounds.height;
        Assert.assertEquals(e1Bounds.getTranslated(dxShift, dy), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(dxShift, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getResized(0, dyShift), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(dxShift, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(dxShift, dy), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds, editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getResized(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds, editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);

    }

    /**
     * Move e3 at the beginning of e1 to reparent e3, expand e1 and shift
     * previous e1 children.
     */
    public void test_move_execution_inside_new_parent_with_expansion() {
        int newY = e1Bounds.y + LayoutConstants.EXECUTION_CHILDREN_MARGIN;
        int dy = newY - e3Bounds.y;
        
        e3Bot.select();
        
        ICondition conditionE1 = new CheckEditPartResized(e1Bot);
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE7 = new CheckEditPartResized(e7Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        ICondition conditionE9 = new CheckEditPartMoved(e9Bot);
        
        editor.drag(e3Bot, e3Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE7);
        bot.waitUntil(conditionE8);
        bot.waitUntil(conditionE9);

        e3Bot = e1Bot.descendants(WithSemantic.withSemantic(e3)).get(0);

        // On lifeline 'a'
        int dxShift = 3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4;
        int dyShift = e3Bounds.height;
        Assert.assertEquals(e1Bounds.getResized(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyShift), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(dxShift, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyShift), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyShift), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds.getResized(0, dyShift), editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds.getTranslated(0, dyShift), editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move e2 out of e1, just above it
     */
    public void test_move_execution_out_of_parent_and_above_it() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        int newY = 170;
        int dy = newY - e2Bounds.y;
        
        e2Bot.select();
        
        ICondition conditionE7 = new CheckEditPartMoved(e7Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        ICondition conditionE9 = new CheckEditPartMoved(e9Bot);
        
        editor.drag(e2Bot, e2Bounds.x, newY);
        
        bot.waitUntil(conditionE7);
        bot.waitUntil(conditionE8);
        bot.waitUntil(conditionE9);

        e1Bot = instanceRoleABot.parent().descendants(WithSemantic.withSemantic(e1)).get(0);
        e2Bot = instanceRoleABot.parent().descendants(WithSemantic.withSemantic(e2)).get(0);
        e3Bot = instanceRoleABot.parent().descendants(WithSemantic.withSemantic(e3)).get(0);
        e4Bot = e2Bot.descendants(WithSemantic.withSemantic(e4)).get(0);
        e5Bot = e1Bot.descendants(WithSemantic.withSemantic(e5)).get(0);

        // On lifeline 'a'
        int dxShift = -3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4;
        int dyShift = e2Bounds.height;
        Assert.assertEquals(e1Bounds.getTranslated(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(dxShift, dy), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(dxShift, dy), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyShift), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds.getTranslated(0, dyShift), editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds.getTranslated(0, dyShift), editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move e5 partially below e1 to trigger a expansion of e1.
     */
    public void test_move_execution_partially_below_parent_trigger_expansion1() {
        int newY = e1Bounds.getBottom().y - e5Bounds.height + 5;
        int dy = newY - e5Bounds.y;
        
        e5Bot.select();
        
        ICondition conditionE1 = new CheckEditPartResized(e1Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        
        editor.drag(e5Bot, e5Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE8);

        // On lifeline 'a'
        int dyShift = e5Bounds.height;
        Assert.assertEquals(e1Bounds.getResized(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds, editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds, editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move e5 partially below e1 to trigger a expansion of e1.
     */
    public void test_move_execution_partially_below_parent_trigger_expansion2() {
        int newY = e1Bounds.getBottom().y - e5Bounds.height + 15;
        int dy = newY - e5Bounds.y;
        
        e5Bot.select();
        
        ICondition conditionE1 = new CheckEditPartResized(e1Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        
        editor.drag(e5Bot, e5Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE8);

        // On lifeline 'a'
        int dyShift = e5Bounds.height;
        Assert.assertEquals(e1Bounds.getResized(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds, editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds, editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move e5 partially below e1 to trigger a expansion of e1.
     */
    public void test_move_execution_partially_below_parent_trigger_expansion3() {
        int newY = e1Bounds.getBottom().y - e5Bounds.height + 25;
        int dy = newY - e5Bounds.y;
        
        e5Bot.select();
        
        ICondition conditionE1 = new CheckEditPartResized(e1Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        
        editor.drag(e5Bot, e5Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE8);

        // On lifeline 'a'
        int dyShift = e5Bounds.height;
        Assert.assertEquals(e1Bounds.getResized(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dy), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds, editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds, editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move e5 partially below e1 to check that it is not allowed.
     */
    public void test_move_execution_partially_below_parent_not_allowed() {
        int newY = e1Bounds.getBottom().y - e5Bounds.height + 30;
        
        e5Bot.select();
        
        editor.drag(e5Bot, e5Bounds.x, newY);
        
        bot.sleep(500);

        // On lifeline 'a'
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds, editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds, editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds, editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * Move e3 on e1 before e2.
     */
    public void test_move_execution_inside_new_parent_before_siblings() {
        int newY = (e1Bounds.y + e2Bounds.y) / 2 - 2;
        int dy = newY - e3Bounds.y;
        
        e3Bot.select();
        
        ICondition conditionE1 = new CheckEditPartResized(e1Bot);
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE7 = new CheckEditPartResized(e7Bot);
        ICondition conditionE8 = new CheckEditPartMoved(e8Bot);
        ICondition conditionE9 = new CheckEditPartMoved(e9Bot);
        
        editor.drag(e3Bot, e3Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE7);
        bot.waitUntil(conditionE8);
        bot.waitUntil(conditionE9);

        e3Bot = e1Bot.descendants(WithSemantic.withSemantic(e3)).get(0);

        // On lifeline 'a'
        int dxShift = 3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4;
        int dyShift = e3Bounds.height;
        int dErr = 3; // layout, e7 is resized and there is only 2 pix between 2
                      // two consecutives events
        Assert.assertEquals(e1Bounds.getResized(0, dyShift + dErr), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyShift + dErr), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(dxShift, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyShift + dErr), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyShift + dErr), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        Assert.assertEquals(e6Bounds, editor.getBounds(e6Bot));
        Assert.assertEquals(e7Bounds.getResized(0, dyShift + dErr), editor.getBounds(e7Bot));
        Assert.assertEquals(e8Bounds.getTranslated(0, dyShift + dErr), editor.getBounds(e8Bot));
        Assert.assertEquals(e9Bounds.getTranslated(0, dyShift + dErr), editor.getBounds(e9Bot));

        validateOrdering(NB_ENDS_EXECUTIONS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        sequenceDiagramBot = null;

        interaction = null;

        instanceRoleABot = null;
        instanceRoleBBot = null;

        e1Bot = null;
        e2Bot = null;
        e3Bot = null;
        e4Bot = null;
        e5Bot = null;
        e6Bot = null;
        e7Bot = null;
        e8Bot = null;
        e9Bot = null;

        e1 = null;
        e2 = null;
        e3 = null;
        e4 = null;
        e5 = null;
        e6 = null;
        e7 = null;
        e8 = null;
        e9 = null;

        e1Bounds = null;
        e2Bounds = null;
        e3Bounds = null;
        e4Bounds = null;
        e5Bounds = null;
        e6Bounds = null;
        e7Bounds = null;
        e8Bounds = null;
        e9Bounds = null;

        super.tearDown();
    }

}
