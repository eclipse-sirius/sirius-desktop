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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Execution Move Tests.
 * 
 * @author pcdavid
 */
public class ExecutionMove5Tests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "vp-851/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "sync-messages.interactions";

    private static final String SESSION_FILE = "sync-messages.aird";

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

    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    private SWTBotGefEditPart sequenceDiagramBot;

    private Interaction interaction;

    private SWTBotGefEditPart e1Bot;

    private SWTBotGefEditPart e2Bot;

    private SWTBotGefEditPart e3Bot;

    private SWTBotGefEditPart e4Bot;

    private SWTBotGefEditPart e5Bot;

    private SWTBotGefEditPart e6Bot;

    private EObject e1;

    private EObject e2;

    private EObject e3;

    private EObject e4;

    private EObject e5;

    private EObject e6;

    private Rectangle e1Bounds;

    private Rectangle e2Bounds;

    private Rectangle e3Bounds;

    private Rectangle e4Bounds;

    private Rectangle e5Bounds;

    private Rectangle e6Bounds;

    // Messages
    private SWTBotGefConnectionEditPart callMessageOnE1Bot;

    private SWTBotGefConnectionEditPart callMessageOnE2Bot;

    private SWTBotGefConnectionEditPart callMessageOnE3Bot;

    private SWTBotGefConnectionEditPart callMessageOnE4Bot;

    private SWTBotGefConnectionEditPart callMessageOnE5Bot;

    private SWTBotGefConnectionEditPart callMessageOnE6Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE1Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE2Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE3Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE4Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE5Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE6Bot;

    private Rectangle callMessageOnE1Bounds;

    private Rectangle callMessageOnE2Bounds;

    private Rectangle callMessageOnE3Bounds;

    private Rectangle callMessageOnE4Bounds;

    private Rectangle callMessageOnE5Bounds;

    private Rectangle callMessageOnE6Bounds;

    private Rectangle returnMessageOfE1Bounds;

    private Rectangle returnMessageOfE2Bounds;

    private Rectangle returnMessageOfE3Bounds;

    private Rectangle returnMessageOfE4Bounds;

    private Rectangle returnMessageOfE5Bounds;

    private Rectangle returnMessageOfE6Bounds;

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

        sequenceDiagramBot = editor.mainEditPart();
        SequenceDiagramEditPart sequenceDiagramEditPart = (SequenceDiagramEditPart) sequenceDiagramBot.part();
        SequenceDDiagram sequenceDDiagram = (SequenceDDiagram) sequenceDiagramEditPart.resolveSemanticElement();
        interaction = (Interaction) sequenceDDiagram.getTarget();

        // InstanceRoles
        SWTBotGefEditPart instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
        SWTBotGefEditPart instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        SWTBotGefEditPart instanceRoleEditPartDBot = editor.getEditPart(LIFELINE_D);

        // Execs
        e1 = interaction.getExecutions().get(0);
        e2 = interaction.getExecutions().get(2);
        e3 = interaction.getExecutions().get(4);
        e4 = interaction.getExecutions().get(1);
        e5 = interaction.getExecutions().get(3);
        e6 = interaction.getExecutions().get(5);

        e1Bot = instanceRoleEditPartABot.parent().descendants(WithSemantic.withSemantic(e1)).get(0);
        e2Bot = instanceRoleEditPartABot.parent().descendants(WithSemantic.withSemantic(e2)).get(0);
        e3Bot = instanceRoleEditPartBBot.parent().descendants(WithSemantic.withSemantic(e3)).get(0);
        e4Bot = instanceRoleEditPartDBot.parent().descendants(WithSemantic.withSemantic(e4)).get(0);
        e5Bot = instanceRoleEditPartDBot.parent().descendants(WithSemantic.withSemantic(e5)).get(0);
        e6Bot = instanceRoleEditPartCBot.parent().descendants(WithSemantic.withSemantic(e6)).get(0);

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
        e3Bounds = editor.getBounds(e3Bot);
        e4Bounds = editor.getBounds(e4Bot);
        e5Bounds = editor.getBounds(e5Bot);
        e6Bounds = editor.getBounds(e6Bot);

        // Messages
        callMessageOnE1Bot = e1Bot.targetConnections().get(0);
        callMessageOnE2Bot = e2Bot.targetConnections().get(0);
        callMessageOnE3Bot = e3Bot.targetConnections().get(0);
        callMessageOnE4Bot = e4Bot.targetConnections().get(0);
        callMessageOnE5Bot = e5Bot.targetConnections().get(0);
        callMessageOnE6Bot = e6Bot.targetConnections().get(0);

        returnMessageOfE1Bot = e1Bot.sourceConnections().get(0);
        returnMessageOfE2Bot = e2Bot.sourceConnections().get(0);
        returnMessageOfE3Bot = e3Bot.sourceConnections().get(0);
        returnMessageOfE4Bot = e4Bot.sourceConnections().get(0);
        returnMessageOfE5Bot = e5Bot.sourceConnections().get(0);
        returnMessageOfE6Bot = e6Bot.sourceConnections().get(0);

        callMessageOnE1Bounds = editor.getBounds(callMessageOnE1Bot);
        callMessageOnE2Bounds = editor.getBounds(callMessageOnE2Bot);
        callMessageOnE3Bounds = editor.getBounds(callMessageOnE3Bot);
        callMessageOnE4Bounds = editor.getBounds(callMessageOnE4Bot);
        callMessageOnE5Bounds = editor.getBounds(callMessageOnE5Bot);
        callMessageOnE6Bounds = editor.getBounds(callMessageOnE6Bot);

        returnMessageOfE1Bounds = editor.getBounds(returnMessageOfE1Bot);
        returnMessageOfE2Bounds = editor.getBounds(returnMessageOfE2Bot);
        returnMessageOfE3Bounds = editor.getBounds(returnMessageOfE3Bot);
        returnMessageOfE4Bounds = editor.getBounds(returnMessageOfE4Bot);
        returnMessageOfE5Bounds = editor.getBounds(returnMessageOfE5Bot);
        returnMessageOfE6Bounds = editor.getBounds(returnMessageOfE6Bot);

    }

    /**
     * Move the second syncCall above the first syncCall.
     */
    public void test_move_sync_call_above_sibling_trigger_shift() {
        int newY = 150;
        int dy = newY - e2Bounds.y;

        e2Bot.select();

        ICondition conditionE1 = new CheckEditPartMoved(e1Bot);
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE4 = new CheckEditPartMoved(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE6 = new CheckEditPartMoved(e6Bot);

        editor.drag(e2Bot, e2Bounds.x, newY);

        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE6);

        int dyShift = e2Bounds.height;
        assertEquals(e1Bounds.getTranslated(0, dyShift), editor.getBounds(e1Bot));
        assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));
        assertEquals(e4Bounds.getTranslated(0, dyShift), editor.getBounds(e4Bot));
        assertEquals(e5Bounds.getTranslated(0, dyShift), editor.getBounds(e5Bot));
        assertEquals(e6Bounds.getTranslated(0, dyShift), editor.getBounds(e6Bot));

        assertEquals(callMessageOnE1Bounds.getTranslated(0, dyShift).y, editor.getBounds(callMessageOnE1Bot).y, 1);
        assertEquals(callMessageOnE2Bounds.getTranslated(0, dy).y, editor.getBounds(callMessageOnE2Bot).y, 1);
        assertEquals(callMessageOnE3Bounds.getTranslated(0, dyShift).y, editor.getBounds(callMessageOnE3Bot).y, 1);
        assertEquals(callMessageOnE4Bounds.getTranslated(0, dyShift).y, editor.getBounds(callMessageOnE4Bot).y, 1);
        assertEquals(callMessageOnE5Bounds.getTranslated(0, dyShift).y, editor.getBounds(callMessageOnE5Bot).y, 1);
        assertEquals(callMessageOnE6Bounds.getTranslated(0, dyShift).y, editor.getBounds(callMessageOnE6Bot).y, 1);
        assertEquals(returnMessageOfE5Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE5Bot).y, 1);
        assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE4Bot).y, 1);
        assertEquals(returnMessageOfE3Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE3Bot).y, 1);
        assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy).y, editor.getBounds(returnMessageOfE2Bot).y, 1);
        assertEquals(returnMessageOfE1Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE1Bot).y, 1);
        assertEquals(returnMessageOfE6Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE6Bot).y, 1);

    }

    /**
     * Reparent the second syncCall on the first.
     */
    public void test_move_sync_call_inside_sibling_trigger_shift() {
        int newY = e1Bounds.getCenter().y;
        int dy = newY - e2Bounds.y;

        e2Bot.select();

        ICondition conditionE1 = new CheckEditPartResized(e1Bot);
        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);
        ICondition conditionE4 = new CheckEditPartResized(e4Bot);
        ICondition conditionE5 = new CheckEditPartMoved(e5Bot);
        ICondition conditionE6 = new CheckEditPartMoved(e6Bot);

        editor.drag(e2Bot, e2Bounds.x, newY);

        bot.waitUntil(conditionE1);
        bot.waitUntil(conditionE3);
        bot.waitUntil(conditionE4);
        bot.waitUntil(conditionE5);
        bot.waitUntil(conditionE6);

        e2Bot = e1Bot.descendants(WithSemantic.withSemantic(e2)).get(0);

        // On lifeline 'a'
        int dxShift = 3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4;
        int dyShift = e2Bounds.height;
        assertEquals(e1Bounds.getResized(0, dyShift), editor.getBounds(e1Bot));
        assertEquals(e2Bounds.getTranslated(dxShift, dy), editor.getBounds(e2Bot));
        assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));
        assertEquals(e4Bounds.getResized(0, dyShift), editor.getBounds(e4Bot));
        assertEquals(e5Bounds.getTranslated(0, dyShift), editor.getBounds(e5Bot));
        assertEquals(e6Bounds.getTranslated(0, dyShift), editor.getBounds(e6Bot));

        assertEquals(callMessageOnE1Bounds, editor.getBounds(callMessageOnE1Bot));
        assertEquals(callMessageOnE2Bounds.getTranslated(dxShift, dy).resize(-dxShift, 0), editor.getBounds(callMessageOnE2Bot));
        assertEquals(callMessageOnE3Bounds.getTranslated(0, dyShift), editor.getBounds(callMessageOnE3Bot));
        assertEquals(callMessageOnE4Bounds.y, editor.getBounds(callMessageOnE4Bot).y, 1);
        assertEquals(callMessageOnE5Bounds.getTranslated(0, dyShift).y, editor.getBounds(callMessageOnE5Bot).y, 1);
        assertEquals(callMessageOnE6Bounds.getTranslated(0, dyShift).y, editor.getBounds(callMessageOnE6Bot).y, 1);

        assertEquals(returnMessageOfE1Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE1Bot).y);
        assertEquals(returnMessageOfE2Bounds.getTranslated(dxShift, dy).resize(-dxShift, 0), editor.getBounds(returnMessageOfE2Bot));
        assertEquals(returnMessageOfE3Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE3Bot).y, 1);
        assertEquals(returnMessageOfE4Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE4Bot).y, 1);
        assertEquals(returnMessageOfE5Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE5Bot).y, 1);
        assertEquals(returnMessageOfE6Bounds.getTranslated(0, dyShift).y, editor.getBounds(returnMessageOfE6Bot).y, 1);

    }

    /**
     * Move the syncCall of LIFELINE_B to have the first syncCall between call &
     * return message of the moved syncCall.
     */
    public void test_move_reverse_sync_call_above_sibling_trigger_shift() {
        int newY = 150;
        int dy = newY - e3Bounds.y;

        e3Bot.select();

        ICondition conditionE3 = new CheckEditPartMoved(e3Bot);

        editor.drag(e3Bot, e3Bounds.x, newY);

        bot.waitUntil(conditionE3);

        assertEquals(e1Bounds, editor.getBounds(e1Bot));
        assertEquals(e2Bounds, editor.getBounds(e2Bot));
        assertEquals(e3Bounds.getTranslated(0, dy), editor.getBounds(e3Bot));
        assertEquals(e4Bounds, editor.getBounds(e4Bot));
        assertEquals(e5Bounds, editor.getBounds(e5Bot));
        assertEquals(e6Bounds, editor.getBounds(e6Bot));

        int dxShift = -3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4 / 2;
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE1Bot.part(), callMessageOnE1Bounds.getResized(dxShift, 0), false, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE2Bot.part(), callMessageOnE2Bounds, false, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE3Bot.part(), callMessageOnE3Bounds.getTranslated(0, dy), true, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE4Bot.part(), callMessageOnE4Bounds, true, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE5Bot.part(), callMessageOnE5Bounds, true, true);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) callMessageOnE6Bot.part(), callMessageOnE6Bounds, false, true);

        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE1Bot.part(), returnMessageOfE1Bounds.getTranslated(dxShift, 0).getResized(-dxShift, 0), true, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE2Bot.part(), returnMessageOfE2Bounds, true, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE3Bot.part(), returnMessageOfE3Bounds.getTranslated(0, dy), false, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE4Bot.part(), returnMessageOfE4Bounds, false, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE5Bot.part(), returnMessageOfE5Bounds, false, false);
        assertMessageHasValidScreenBounds((SequenceMessageEditPart) returnMessageOfE6Bot.part(), returnMessageOfE6Bounds, true, false);
    }

    @Override
    protected void tearDown() throws Exception {
        sequenceDiagramBot = null;

        interaction = null;

        e1Bot = null;
        e2Bot = null;
        e3Bot = null;
        e4Bot = null;
        e5Bot = null;
        e6Bot = null;

        e1 = null;
        e2 = null;
        e3 = null;
        e4 = null;
        e5 = null;
        e6 = null;

        e1Bounds = null;
        e2Bounds = null;
        e3Bounds = null;
        e4Bounds = null;
        e5Bounds = null;
        e6Bounds = null;

        // Messages
        callMessageOnE1Bot = null;
        callMessageOnE2Bot = null;
        callMessageOnE3Bot = null;
        callMessageOnE4Bot = null;
        callMessageOnE5Bot = null;
        callMessageOnE6Bot = null;

        returnMessageOfE1Bot = null;
        returnMessageOfE2Bot = null;
        returnMessageOfE3Bot = null;
        returnMessageOfE4Bot = null;
        returnMessageOfE5Bot = null;
        returnMessageOfE6Bot = null;

        callMessageOnE1Bounds = null;
        callMessageOnE2Bounds = null;
        callMessageOnE3Bounds = null;
        callMessageOnE4Bounds = null;
        callMessageOnE5Bounds = null;
        callMessageOnE6Bounds = null;

        returnMessageOfE1Bounds = null;
        returnMessageOfE2Bounds = null;
        returnMessageOfE3Bounds = null;
        returnMessageOfE4Bounds = null;
        returnMessageOfE5Bounds = null;
        returnMessageOfE6Bounds = null;

        super.tearDown();
    }

}
