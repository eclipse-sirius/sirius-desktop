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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Execution Move Tests.
 * 
 * @author pcdavid
 */
public class ExecutionMove6Tests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "vp-851/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "sync-message-to-self.interactions";

    private static final String SESSION_FILE = "sync-message-to-self.aird";

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

    private SWTBotGefEditPart sequenceDiagramBot;

    private Interaction interaction;

    private SWTBotGefEditPart e1Bot;

    private SWTBotGefEditPart e2Bot;

    private SWTBotGefEditPart e3Bot;

    private EObject e1;

    private EObject e2;

    private EObject e3;

    private Rectangle e1Bounds;

    private Rectangle e2Bounds;

    private Rectangle e3Bounds;

    // Messages
    private SWTBotGefConnectionEditPart callMessageOnE1Bot;

    private SWTBotGefConnectionEditPart callMessageOnE2Bot;

    private SWTBotGefConnectionEditPart callMessageOnE3Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE1Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE2Bot;

    private SWTBotGefConnectionEditPart returnMessageOfE3Bot;

    private Rectangle callMessageOnE1Bounds;

    private Rectangle callMessageOnE2Bounds;

    private Rectangle callMessageOnE3Bounds;

    private Rectangle returnMessageOfE1Bounds;

    private Rectangle returnMessageOfE2Bounds;

    private Rectangle returnMessageOfE3Bounds;

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
        SWTBotGefEditPart instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);

        // Execs
        e1 = interaction.getExecutions().get(0);
        e2 = interaction.getExecutions().get(2);
        e3 = interaction.getExecutions().get(1);

        e1Bot = instanceRoleEditPartABot.parent().descendants(WithSemantic.withSemantic(e1)).get(0);
        e2Bot = instanceRoleEditPartABot.parent().descendants(WithSemantic.withSemantic(e2)).get(0);
        e3Bot = instanceRoleEditPartCBot.parent().descendants(WithSemantic.withSemantic(e3)).get(0);

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
        e3Bounds = editor.getBounds(e3Bot);

        // Messages
        callMessageOnE1Bot = e1Bot.targetConnections().get(0);
        callMessageOnE2Bot = e2Bot.targetConnections().get(0);
        callMessageOnE3Bot = e3Bot.targetConnections().get(0);

        returnMessageOfE1Bot = e1Bot.sourceConnections().get(0);
        returnMessageOfE2Bot = e2Bot.sourceConnections().get(0);
        returnMessageOfE3Bot = e3Bot.sourceConnections().get(0);

        callMessageOnE1Bounds = editor.getBounds(callMessageOnE1Bot);
        callMessageOnE2Bounds = editor.getBounds(callMessageOnE2Bot);
        callMessageOnE3Bounds = editor.getBounds(callMessageOnE3Bot);

        returnMessageOfE1Bounds = editor.getBounds(returnMessageOfE1Bot);
        returnMessageOfE2Bounds = editor.getBounds(returnMessageOfE2Bot);
        returnMessageOfE3Bounds = editor.getBounds(returnMessageOfE3Bot);

    }

    private static final int NB_ENDS_SYNC_MESSAGE_TO_SELF = 4 * 3;

    /**
     * 
     */
    public void test_move_reflective_sync_above_sibling() {
        resizeLifeline(LIFELINE_A, 150);

        // Drag everything down a little to get enough room above e1
        int dy = 50;
        e1Bot.select();
        editor.drag(e1Bot, e1Bounds.x, e1Bounds.y + dy);
        editor.drag(e2Bot, e2Bounds.x, e2Bounds.y + dy); // drag doesn't works
        e3Bot.select();
        ICondition condition = new CheckEditPartMoved(e3Bot);
        editor.drag(e3Bot, e3Bounds.x, e3Bounds.y + dy);
        bot.waitUntil(condition);

        int dyShift = callMessageOnE2Bounds.height + e2Bounds.height + returnMessageOfE2Bounds.height;

        int newY = e1Bounds.getCenter().y - 10 + callMessageOnE2Bounds.height;
        dy = e2Bounds.y - newY;
        editor.drag(e2Bot, e2Bounds.x, newY);

        assertEquals(e1Bounds.getTranslated(0, dyShift), editor.getBounds(e1Bot));
        assertEquals(e2Bounds.getTranslated(0, dy), editor.getBounds(e2Bot));
        assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));

        assertEquals(callMessageOnE1Bounds.getTranslated(0, dyShift), editor.getBounds(callMessageOnE1Bot));
        assertEquals(callMessageOnE2Bounds.getTranslated(0, dy), editor.getBounds(callMessageOnE2Bot));
        assertEquals(callMessageOnE3Bounds.getTranslated(0, dyShift), editor.getBounds(callMessageOnE3Bot));

        assertEquals(returnMessageOfE1Bounds.getTranslated(0, dyShift), editor.getBounds(returnMessageOfE1Bot));
        assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        assertEquals(returnMessageOfE3Bounds.getTranslated(0, dyShift), editor.getBounds(returnMessageOfE3Bot));

        validateOrdering(NB_ENDS_SYNC_MESSAGE_TO_SELF);
    }

    /**
     * Disabled as the reconnections which are not yet handled properly mess up
     * the ranges.
     */
    public void test_move_reflective_sync_inside_new_parent() {
        int newY = e1Bounds.getCenter().y + callMessageOnE2Bounds.height;
        int dy = e2Bounds.y - newY;
        e2Bot.select();
        ICondition condition = new CheckEditPartMoved(e2Bot);
        editor.drag(e2Bot, e2Bounds.x, newY); // drag doesn't works
        bot.waitUntil(condition);

        int dyShift = callMessageOnE2Bounds.height + e2Bounds.height + returnMessageOfE2Bounds.height;
        assertEquals(e1Bounds.getTranslated(0, dyShift), editor.getBounds(e1Bot));
        assertEquals(e2Bounds.getResized(0, dy), editor.getBounds(e2Bot));
        assertEquals(e3Bounds.getTranslated(0, dyShift), editor.getBounds(e3Bot));

        assertEquals(callMessageOnE1Bounds.getTranslated(0, dyShift), editor.getBounds(callMessageOnE1Bot));
        assertEquals(callMessageOnE2Bounds, editor.getBounds(callMessageOnE2Bot));
        assertEquals(callMessageOnE3Bounds.getTranslated(0, dyShift), editor.getBounds(callMessageOnE3Bot));

        assertEquals(returnMessageOfE1Bounds.getTranslated(0, dyShift), editor.getBounds(returnMessageOfE1Bot));
        assertEquals(returnMessageOfE2Bounds.getTranslated(0, dy), editor.getBounds(returnMessageOfE2Bot));
        assertEquals(returnMessageOfE3Bounds.getTranslated(0, dyShift), editor.getBounds(returnMessageOfE3Bot));

        validateOrdering(NB_ENDS_SYNC_MESSAGE_TO_SELF);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        sequenceDiagramBot = null;

        interaction = null;

        e1Bot = null;
        e2Bot = null;
        e3Bot = null;

        e1 = null;
        e2 = null;
        e3 = null;

        e1Bounds = null;
        e2Bounds = null;
        e3Bounds = null;

        // Messages
        callMessageOnE1Bot = null;
        callMessageOnE2Bot = null;
        callMessageOnE3Bot = null;
        returnMessageOfE1Bot = null;
        returnMessageOfE2Bot = null;
        returnMessageOfE3Bot = null;

        callMessageOnE1Bounds = null;
        callMessageOnE2Bounds = null;
        callMessageOnE3Bounds = null;

        returnMessageOfE1Bounds = null;
        returnMessageOfE2Bounds = null;
        returnMessageOfE3Bounds = null;

        super.tearDown();
    }
}
