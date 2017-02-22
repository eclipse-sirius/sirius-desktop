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
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.Assert;

/**
 * Execution Move Tests.
 * 
 * @author pcdavid
 */
public class ExecutionMove2Tests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "vp-851/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "simple-messages.interactions";

    private static final String SESSION_FILE = "simple-messages.aird";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getSemanticModel() {
        return MODEL;
    }

    protected String getSessionModel() {
        return SESSION_FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    private SWTBotGefEditPart e1Bot;

    private SWTBotGefEditPart e2Bot;

    private SWTBotGefEditPart e3Bot;

    private SWTBotGefEditPart e4Bot;

    private SWTBotGefEditPart e5Bot;

    private Rectangle e1Bounds;

    private Rectangle e2Bounds;

    private Rectangle e3Bounds;

    private Rectangle e4Bounds;

    private Rectangle e5Bounds;

    // Read Messages
    private SWTBotGefConnectionEditPart readMessage1Bot;

    private SWTBotGefConnectionEditPart readMessage2Bot;

    private SWTBotGefConnectionEditPart readMessage3Bot;

    private SWTBotGefConnectionEditPart readMessage4Bot;

    private SWTBotGefConnectionEditPart readMessage5Bot;

    private SWTBotGefConnectionEditPart readMessage6Bot;

    private SWTBotGefConnectionEditPart readMessage7Bot;

    private Rectangle readMessage1Bounds;

    private Rectangle readMessage2Bounds;

    private Rectangle readMessage3Bounds;

    private Rectangle readMessage4Bounds;

    private Rectangle readMessage5Bounds;

    private Rectangle readMessage6Bounds;

    private Rectangle readMessage7Bounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.mainEditPart().part().getViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, Boolean.FALSE);
        editor.mainEditPart().part().getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.FALSE);

        maximizeEditor(editor);
        editor.reveal(LIFELINE_A);

        // InstanceRoles
        SWTBotGefEditPart instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
        SWTBotGefEditPart instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);

        e1Bot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e2Bot = e1Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e3Bot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);
        e4Bot = e2Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e5Bot = e1Bot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
        e3Bounds = editor.getBounds(e3Bot);
        e4Bounds = editor.getBounds(e4Bot);
        e5Bounds = editor.getBounds(e5Bot);

        // Messages
        SWTBotGefEditPart lifelineEditPartBBot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefEditPart lifelineEditPartCBot = instanceRoleEditPartCBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        readMessage1Bot = lifelineEditPartBBot.sourceConnections().get(0);
        readMessage3Bot = lifelineEditPartBBot.sourceConnections().get(1);
        readMessage5Bot = lifelineEditPartBBot.sourceConnections().get(2);
        readMessage6Bot = lifelineEditPartBBot.sourceConnections().get(3);
        readMessage2Bot = lifelineEditPartCBot.sourceConnections().get(0);
        readMessage4Bot = lifelineEditPartCBot.sourceConnections().get(1);
        readMessage7Bot = lifelineEditPartCBot.sourceConnections().get(2);

        readMessage1Bounds = editor.getBounds(readMessage1Bot);
        readMessage2Bounds = editor.getBounds(readMessage2Bot);
        readMessage3Bounds = editor.getBounds(readMessage3Bot);
        readMessage4Bounds = editor.getBounds(readMessage4Bot);
        readMessage5Bounds = editor.getBounds(readMessage5Bot);
        readMessage6Bounds = editor.getBounds(readMessage6Bot);
        readMessage7Bounds = editor.getBounds(readMessage7Bot);
    }

    private static final int NB_ENDS_SIMPLE_MESSAGES = (5 * 2) + (7 * 2);

    /**
     * Move e3 just above e1 but close enough to trigger a shift.
     */
    public void test_move_plain_execution_above_sibling_shifts_sibling_and_children_remote_simple_messages() {
        int newY = 150;
        int dy = newY - e3Bounds.y;
        
        e3Bot.select();
        
        ICondition conditionE1 = new CheckEditPartMoved(e1Bot);
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        editor.drag(e3Bot, e3Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        // On lifeline 'a'
        int dyShift = e3Bounds.height;
        Assert.assertEquals(e1Bounds.getTranslated(0, dyShift), editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds.getTranslated(0, dyShift), editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds.getTranslated(0, dyShift), editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds.getTranslated(0, dyShift), editor.getBounds(e5Bot));

        // On lifelines 'b' and 'c'
        assertMessageVerticalPosition((SequenceMessageEditPart) readMessage1Bot.part(), readMessage1Bounds.y);
        assertMessageVerticalPosition((SequenceMessageEditPart) readMessage2Bot.part(), readMessage2Bounds.y + dyShift);
        assertMessageVerticalPosition((SequenceMessageEditPart) readMessage3Bot.part(), readMessage3Bounds.y + dyShift);
        assertMessageVerticalPosition((SequenceMessageEditPart) readMessage4Bot.part(), readMessage4Bounds.y + dyShift);
        assertMessageVerticalPosition((SequenceMessageEditPart) readMessage5Bot.part(), readMessage5Bounds.y + dyShift);
        assertMessageVerticalPosition((SequenceMessageEditPart) readMessage6Bot.part(), readMessage6Bounds.y + dyShift);
        assertMessageVerticalPosition((SequenceMessageEditPart) readMessage7Bot.part(), readMessage7Bounds.y + dyShift);

        validateOrdering(NB_ENDS_SIMPLE_MESSAGES);

    }

    /**
     * Test that move the top of e1 in the middle of e3 in same range's
     * lowerbound as the readMessage6 is not possible.
     */
    public void test_move_execution_group_on_top_of_smaller_execution_remote_simple_messages() {
        int newY = e3Bounds.getCenter().y;
        
        e1Bot.select();
        
        editor.drag(e1Bot, e3Bounds.x, newY);
        bot.sleep(500);

        assertNotChanged();

        validateOrdering(NB_ENDS_SIMPLE_MESSAGES);
    }

    private void assertNotChanged() {
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));
        Assert.assertEquals(e4Bounds, editor.getBounds(e4Bot));
        Assert.assertEquals(e5Bounds, editor.getBounds(e5Bot));

        Assert.assertEquals(readMessage1Bounds, editor.getBounds(readMessage1Bot));
        Assert.assertEquals(readMessage2Bounds, editor.getBounds(readMessage2Bot));
        Assert.assertEquals(readMessage3Bounds, editor.getBounds(readMessage3Bot));
        Assert.assertEquals(readMessage4Bounds, editor.getBounds(readMessage4Bot));
        Assert.assertEquals(readMessage5Bounds, editor.getBounds(readMessage5Bot));
        Assert.assertEquals(readMessage6Bounds, editor.getBounds(readMessage6Bot));
        Assert.assertEquals(readMessage7Bounds, editor.getBounds(readMessage7Bot));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        e1Bot = null;
        e2Bot = null;
        e3Bot = null;
        e4Bot = null;
        e5Bot = null;

        e1Bounds = null;
        e2Bounds = null;
        e3Bounds = null;
        e4Bounds = null;
        e5Bounds = null;

        // Read Messages
        readMessage1Bot = null;
        readMessage2Bot = null;
        readMessage3Bot = null;
        readMessage4Bot = null;
        readMessage5Bot = null;
        readMessage6Bot = null;
        readMessage7Bot = null;

        readMessage1Bounds = null;
        readMessage2Bounds = null;
        readMessage3Bounds = null;
        readMessage4Bounds = null;
        readMessage5Bounds = null;
        readMessage6Bounds = null;
        readMessage7Bounds = null;

        super.tearDown();
    }
}
