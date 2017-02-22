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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceDiagramEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartResized;
import org.eclipse.sirius.tests.swtbot.support.api.matcher.WithSemantic;
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
public class ExecutionMove4Tests extends AbstractDefaultModelSequenceTests {

    private static final String PATH = DATA_UNIT_DIR + "vp-851/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample";

    private static final String MODEL = "creation-destruction.interactions";

    private static final String SESSION_FILE = "creation-destruction.aird";

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

    // Read Messages
    private SWTBotGefConnectionEditPart createMessageOfBBot;

    private SWTBotGefConnectionEditPart createMessageOfDBot;

    private SWTBotGefConnectionEditPart readMessageM5Bot;

    private SWTBotGefConnectionEditPart readMessageM6Bot;

    private SWTBotGefConnectionEditPart readMessageM7Bot;

    private SWTBotGefConnectionEditPart destroyMessageOfBBot;

    private SWTBotGefConnectionEditPart destroyMessageOfDBot;

    private Rectangle createMessageOfBBounds;

    private Rectangle createMessageOfDBounds;

    private Rectangle readMessageM5Bounds;

    private Rectangle readMessageM6Bounds;

    private Rectangle readMessageM7Bounds;

    private Rectangle destroyMessageOfBBounds;

    private Rectangle destroyMessageOfDBounds;

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

        e1 = interaction.getExecutions().get(2);
        e2 = interaction.getExecutions().get(1);
        e3 = interaction.getExecutions().get(0);

        // InstanceRoles
        SWTBotGefEditPart instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        SWTBotGefEditPart instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
        SWTBotGefEditPart instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        SWTBotGefEditPart instanceRoleEditPartDBot = editor.getEditPart(LIFELINE_D);

        // Execs
        e1Bot = instanceRoleEditPartABot.parent().descendants(WithSemantic.withSemantic(e1)).get(0);
        e2Bot = instanceRoleEditPartABot.parent().descendants(WithSemantic.withSemantic(e2)).get(0);
        e3Bot = instanceRoleEditPartABot.parent().descendants(WithSemantic.withSemantic(e3)).get(0);

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
        e3Bounds = editor.getBounds(e3Bot);

        // Messages
        SWTBotGefEditPart lifelineEditPartABot = instanceRoleEditPartABot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefEditPart lifelineEditPartBBot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefEditPart lifelineEditPartCBot = instanceRoleEditPartCBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        SWTBotGefEditPart lifelineEditPartDBot = instanceRoleEditPartDBot.parent().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class)).get(0);
        createMessageOfBBot = lifelineEditPartABot.sourceConnections().get(0);
        createMessageOfDBot = lifelineEditPartCBot.sourceConnections().get(0);
        readMessageM5Bot = lifelineEditPartCBot.sourceConnections().get(1);
        readMessageM6Bot = lifelineEditPartDBot.targetConnections().get(0);
        readMessageM7Bot = lifelineEditPartBBot.sourceConnections().get(0);
        destroyMessageOfBBot = lifelineEditPartABot.sourceConnections().get(1);
        destroyMessageOfDBot = lifelineEditPartCBot.sourceConnections().get(2);

        createMessageOfBBounds = editor.getBounds(createMessageOfBBot);
        createMessageOfDBounds = editor.getBounds(createMessageOfDBot);
        readMessageM5Bounds = editor.getBounds(readMessageM5Bot);
        readMessageM6Bounds = editor.getBounds(readMessageM6Bot);
        readMessageM7Bounds = editor.getBounds(readMessageM7Bot);
        destroyMessageOfBBounds = editor.getBounds(destroyMessageOfBBot);
        destroyMessageOfDBounds = editor.getBounds(destroyMessageOfDBot);

    }

    private static final int NB_ENDS_CREATION_DESTRUCTION = 20;

    /**
     * Move e1 on e3.
     */
    public void test_move_execution_above_creation_and_desctruction_messages() {
        int newY = e3Bounds.getCenter().y;
        int dy = newY - e1Bounds.y;
        
        e1Bot.select();
        
        ICondition conditionE2 = new CheckEditPartMoved(e2Bot);
        ICondition conditionE3 = new CheckEditPartResized(e3Bot);

        editor.drag(e1Bot, e1Bounds.x, newY);
        
        bot.waitUntil(conditionE2);
        bot.waitUntil(conditionE3);

        e1Bot = e3Bot.descendants(WithSemantic.withSemantic(e1)).get(0);

        int dxShift = 3 * LayoutConstants.DEFAULT_EXECUTION_WIDTH / 4;
        int dyShift = e1Bounds.height;
        assertEquals(e1Bounds.getTranslated(dxShift, dy), editor.getBounds(e1Bot));
        assertEquals(e2Bounds.getTranslated(0, dyShift), editor.getBounds(e2Bot));
        assertEquals(e3Bounds.getResized(0, dyShift), editor.getBounds(e3Bot));

        assertEquals(createMessageOfBBounds.getTranslated(0, dyShift), editor.getBounds(createMessageOfBBot));
        assertEquals(createMessageOfDBounds.getTranslated(0, dyShift), editor.getBounds(createMessageOfDBot));
        assertEquals(readMessageM5Bounds.getTranslated(0, dyShift), editor.getBounds(readMessageM5Bot));
        assertEquals(readMessageM6Bounds.getTranslated(0, dyShift), editor.getBounds(readMessageM6Bot));
        assertEquals(readMessageM7Bounds.getTranslated(0, dyShift), editor.getBounds(readMessageM7Bot));
        assertEquals(destroyMessageOfBBounds.getTranslated(0, dyShift), editor.getBounds(destroyMessageOfBBot));
        assertEquals(destroyMessageOfDBounds.getTranslated(0, dyShift), editor.getBounds(destroyMessageOfDBot));

        validateOrdering(NB_ENDS_CREATION_DESTRUCTION);
    }

    /**
     * Move e1 between the create message and e2.
     */
    public void test_move_execution_between_creation_and_destruction_messages() {
        int newY = e2Bounds.y - 10;
        int dy = newY - e1Bounds.y;
        e1Bot.select();
        ICondition condition = new CheckEditPartMoved(e1Bot);
        editor.drag(e1Bot, e1Bounds.x, newY);
        bot.waitUntil(condition);

        int dyShift = e1Bounds.height;
        assertEquals(e1Bounds.getTranslated(0, dy), editor.getBounds(e1Bot));
        assertEquals(e2Bounds.getTranslated(0, dyShift), editor.getBounds(e2Bot));
        assertEquals(e3Bounds, editor.getBounds(e3Bot));

        assertEquals(createMessageOfBBounds, editor.getBounds(createMessageOfBBot));
        assertEquals(createMessageOfDBounds.getTranslated(0, dyShift), editor.getBounds(createMessageOfDBot));
        assertEquals(readMessageM5Bounds.getTranslated(0, dyShift), editor.getBounds(readMessageM5Bot));
        assertEquals(readMessageM6Bounds.getTranslated(0, dyShift), editor.getBounds(readMessageM6Bot));
        assertEquals(readMessageM7Bounds.getTranslated(0, dyShift + 1), editor.getBounds(readMessageM7Bot));
        assertEquals(destroyMessageOfBBounds.getTranslated(0, dyShift), editor.getBounds(destroyMessageOfBBot));
        assertEquals(destroyMessageOfDBounds.getTranslated(0, dyShift), editor.getBounds(destroyMessageOfDBot));

        validateOrdering(NB_ENDS_CREATION_DESTRUCTION);
    }

    /**
     * Test that move the top of e1 in the middle of e2 in same range's
     * lowerbound as the readMessage7 is not possible.
     */
    public void test_move_execution_between_creation_and_destruction_messages2() {
        int newY = e2Bounds.getBottom().y - e1Bounds.height / 2;
        e1Bot.select();
        editor.drag(e1Bot, e1Bounds.x, newY);
        bot.sleep(500);

        assertNotChanged();

        validateOrdering(NB_ENDS_CREATION_DESTRUCTION);
    }

    private void assertNotChanged() {
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));
        Assert.assertEquals(e3Bounds, editor.getBounds(e3Bot));

        Assert.assertEquals(createMessageOfBBounds, editor.getBounds(createMessageOfBBot));
        Assert.assertEquals(createMessageOfDBounds, editor.getBounds(createMessageOfDBot));

        Assert.assertEquals(readMessageM5Bounds, editor.getBounds(readMessageM5Bot));
        Assert.assertEquals(readMessageM6Bounds, editor.getBounds(readMessageM6Bot));
        Assert.assertEquals(readMessageM7Bounds, editor.getBounds(readMessageM7Bot));

        Assert.assertEquals(destroyMessageOfBBounds, editor.getBounds(destroyMessageOfBBot));
        Assert.assertEquals(destroyMessageOfDBounds, editor.getBounds(destroyMessageOfDBot));

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

        // Read Messages
        createMessageOfBBot = null;
        createMessageOfDBot = null;

        readMessageM5Bot = null;
        readMessageM6Bot = null;
        readMessageM7Bot = null;

        destroyMessageOfBBot = null;
        destroyMessageOfDBot = null;

        createMessageOfBBounds = null;
        createMessageOfDBounds = null;

        readMessageM5Bounds = null;
        readMessageM6Bounds = null;
        readMessageM7Bounds = null;

        destroyMessageOfBBounds = null;
        destroyMessageOfDBounds = null;

        super.tearDown();
    }
}
