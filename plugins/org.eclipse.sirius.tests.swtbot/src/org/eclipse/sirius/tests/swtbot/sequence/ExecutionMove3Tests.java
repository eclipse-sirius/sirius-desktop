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

/**
 * Execution Move Tests.
 * 
 * @author pcdavid
 */
public class ExecutionMove3Tests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "vp-851/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "simple-messages-to-self.interactions";

    private static final String SESSION_FILE = "simple-messages-to-self.aird";

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

    private SWTBotGefConnectionEditPart readMessage4Bot;

    private SWTBotGefConnectionEditPart readMessage5Bot;

    private Rectangle readMessage1Bounds;

    private Rectangle readMessage2Bounds;

    private Rectangle readMessage4Bounds;

    private Rectangle readMessage5Bounds;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.mainEditPart().part().getViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, Boolean.FALSE);
        editor.mainEditPart().part().getViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED, Boolean.FALSE);

        maximizeEditor(editor);
        arrangeAll();
        editor.reveal(LIFELINE_A);

        // InstanceRoles
        SWTBotGefEditPart instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);

        // Execs
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
        readMessage1Bot = lifelineEditPartBBot.sourceConnections().get(0);
        readMessage2Bot = lifelineEditPartBBot.sourceConnections().get(1);
        readMessage4Bot = lifelineEditPartBBot.sourceConnections().get(2);
        readMessage5Bot = lifelineEditPartBBot.sourceConnections().get(3);

        readMessage1Bounds = editor.getBounds(readMessage1Bot);
        readMessage2Bounds = editor.getBounds(readMessage2Bot);
        readMessage4Bounds = editor.getBounds(readMessage4Bot);
        readMessage5Bounds = editor.getBounds(readMessage5Bot);
    }

    private static final int NB_ENDS_SIMPLE_MESSAGES_TO_SELF = 5 * 2 + 4 * 2;

    /**
     * Move e3 just above e1 but close enough to trigger a shift.
     */
    public void test_move_plain_execution_above_sibling_shifts_sibling_and_children_remote_simple_messages_to_self() {
        int newY = readMessage1Bounds.getCenter().y;
        int dy = newY - e3Bounds.y;
        
        ICondition conditionE1 = new CheckEditPartMoved(e1Bot);
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);

        e3Bot.select();
        editor.drag(e3Bot, e3Bounds.x, newY);
        
        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);

        // layout : 5 pix
        dy += 1;
        int dyShift = e3Bounds.height;

        // On lifeline 'a'
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds.getTranslated(0, dyShift));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getTranslated(0, dyShift));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e3Bot.part(), e3Bounds.getTranslated(0, dy));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e4Bot.part(), e4Bounds.getTranslated(0, dyShift));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e5Bot.part(), e5Bounds.getTranslated(0, dyShift));

        // On lifelines 'b' and 'c'
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) readMessage1Bot.part(), readMessage1Bounds.getResized(0, dyShift), true, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) readMessage2Bot.part(), readMessage2Bounds.getTranslated(0, dyShift), true, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) readMessage4Bot.part(), readMessage4Bounds.getTranslated(0, dyShift), true, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) readMessage5Bot.part(), readMessage5Bounds.getTranslated(0, dyShift), true, false);

        validateOrdering(NB_ENDS_SIMPLE_MESSAGES_TO_SELF);
    }

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
        readMessage4Bot = null;
        readMessage5Bot = null;

        readMessage1Bounds = null;
        readMessage2Bounds = null;
        readMessage4Bounds = null;
        readMessage5Bounds = null;

        super.tearDown();
    }
}
