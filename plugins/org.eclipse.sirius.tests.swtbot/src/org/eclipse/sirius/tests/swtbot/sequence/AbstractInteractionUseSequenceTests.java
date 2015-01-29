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
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Abstract test class for combinedfragments.
 * 
 * @author edugueperoux
 */
public abstract class AbstractInteractionUseSequenceTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "interactionUses/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Interaction Use tests";

    private static final String MODEL = "interactionUses.interactions";

    private static final String SESSION_FILE = "interactionUses.aird";

    private static final String TYPES_FILE = "types.ecore";

    /** Main part */
    protected SWTBotGefEditPart sequenceDiagramBot;

    /** Main interaction */
    protected Interaction interaction;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartABot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartBBot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartCBot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartDBot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartEBot;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartA;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartB;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartC;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartD;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartE;

    /** Participants */
    protected Participant participantA;

    /** Participants */
    protected Participant participantB;

    /** Participants */
    protected Participant participantC;

    /** Participants */
    protected Participant participantD;

    /** Participants */
    protected Participant participantE;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartABounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartBBounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartCBounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartDBounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartEBounds;

    /** IUs */
    protected SWTBotGefEditPart firstInteractionUseBot;

    /** IUs */
    protected SWTBotGefEditPart secondInteractionUseBot;

    /** IUs parts */
    protected InteractionUseEditPart firstInteractionEditPart;

    /** IUs parts */
    protected InteractionUseEditPart secondInteractionEditPart;

    /** IUs */
    protected InteractionUse firstInteractionUse;

    /** IUs */
    protected InteractionUse secondInteractionUse;

    /** IUs bounds */
    protected Rectangle firstInteractionUseBounds;

    /** IUs bounds */
    protected Rectangle secondInteractionUseBounds;

    /** Execs */
    protected SWTBotGefEditPart e1Bot;

    /** Execs */
    protected SWTBotGefEditPart e2Bot;

    /** Execs */
    protected SWTBotGefEditPart e3Bot;

    /** Execs */
    protected SWTBotGefEditPart e4Bot;

    /** Execs */
    protected SWTBotGefEditPart e5Bot;

    /** Execs */
    protected SWTBotGefEditPart e6Bot;

    /** Execs bounds */
    protected Rectangle e1Bounds;

    /** Execs bounds */
    protected Rectangle e2Bounds;

    /** Execs bounds */
    protected Rectangle e3Bounds;

    /** Execs bounds */
    protected Rectangle e4Bounds;

    /** Execs bounds */
    protected Rectangle e5Bounds;

    /** Execs bounds */
    protected Rectangle e6Bounds;

    /** Messages */
    protected SWTBotGefConnectionEditPart callMessageOnE1Bot;

    /** Messages */
    protected SWTBotGefConnectionEditPart callMessageOnE2Bot;

    /** Messages */
    protected SWTBotGefConnectionEditPart callMessageOnE4Bot;

    /** Messages */
    protected SWTBotGefConnectionEditPart callMessageOnE6Bot;

    /** Messages */
    protected SWTBotGefConnectionEditPart returnMessageOfE1Bot;

    /** Messages */
    protected SWTBotGefConnectionEditPart returnMessageOfE2Bot;

    /** Messages */
    protected SWTBotGefConnectionEditPart returnMessageOfE4Bot;

    /** Messages */
    protected SWTBotGefConnectionEditPart returnMessageOfE6Bot;

    /** Messages bounds */
    protected Rectangle callMessageOnE1Bounds;

    /** Messages bounds */
    protected Rectangle callMessageOnE2Bounds;

    /** Messages bounds */
    protected Rectangle callMessageOnE4Bounds;

    /** Messages bounds */
    protected Rectangle callMessageOnE6Bounds;

    /** Messages bounds */
    protected Rectangle returnMessageOfE1Bounds;

    /** Messages bounds */
    protected Rectangle returnMessageOfE2Bounds;

    /** Messages bounds */
    protected Rectangle returnMessageOfE4Bounds;

    /** Messages bounds */
    protected Rectangle returnMessageOfE6Bounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
        instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        instanceRoleEditPartDBot = editor.getEditPart(LIFELINE_D);
        instanceRoleEditPartEBot = editor.getEditPart(LIFELINE_E);

        instanceRoleEditPartA = (InstanceRoleEditPart) instanceRoleEditPartABot.parent().part();
        instanceRoleEditPartB = (InstanceRoleEditPart) instanceRoleEditPartBBot.parent().part();
        instanceRoleEditPartC = (InstanceRoleEditPart) instanceRoleEditPartCBot.parent().part();
        instanceRoleEditPartD = (InstanceRoleEditPart) instanceRoleEditPartDBot.parent().part();
        instanceRoleEditPartE = (InstanceRoleEditPart) instanceRoleEditPartEBot.parent().part();

        participantA = (Participant) instanceRoleEditPartA.resolveTargetSemanticElement();
        participantB = (Participant) instanceRoleEditPartB.resolveTargetSemanticElement();
        participantC = (Participant) instanceRoleEditPartC.resolveTargetSemanticElement();
        participantD = (Participant) instanceRoleEditPartD.resolveTargetSemanticElement();
        participantE = (Participant) instanceRoleEditPartE.resolveTargetSemanticElement();

        instanceRoleEditPartABounds = editor.getBounds(instanceRoleEditPartABot);
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);
        instanceRoleEditPartEBounds = editor.getBounds(instanceRoleEditPartEBot);

        sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();
        interaction = (Interaction) participantA.eContainer();

        // IUs
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);
        secondInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(1);

        firstInteractionEditPart = (InteractionUseEditPart) firstInteractionUseBot.part();
        secondInteractionEditPart = (InteractionUseEditPart) secondInteractionUseBot.part();

        firstInteractionUse = (InteractionUse) firstInteractionEditPart.resolveTargetSemanticElement();
        secondInteractionUse = (InteractionUse) secondInteractionEditPart.resolveTargetSemanticElement();

        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);
        secondInteractionUseBounds = editor.getBounds(secondInteractionUseBot);

        // Execs
        e1Bot = instanceRoleEditPartCBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e2Bot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e3Bot = instanceRoleEditPartDBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e4Bot = instanceRoleEditPartCBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);
        e5Bot = instanceRoleEditPartCBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(2);

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
        e3Bounds = editor.getBounds(e3Bot);
        e4Bounds = editor.getBounds(e4Bot);
        e5Bounds = editor.getBounds(e5Bot);

        // Messages
        callMessageOnE1Bot = e1Bot.targetConnections().get(0);
        callMessageOnE2Bot = e2Bot.targetConnections().get(0);
        callMessageOnE4Bot = e4Bot.targetConnections().get(0);
        returnMessageOfE1Bot = e1Bot.sourceConnections().get(0);
        returnMessageOfE2Bot = e2Bot.sourceConnections().get(0);
        returnMessageOfE4Bot = e4Bot.sourceConnections().get(0);

        callMessageOnE1Bounds = editor.getBounds(callMessageOnE1Bot);
        callMessageOnE2Bounds = editor.getBounds(callMessageOnE2Bot);
        callMessageOnE4Bounds = editor.getBounds(callMessageOnE4Bot);
        returnMessageOfE1Bounds = editor.getBounds(returnMessageOfE1Bot);
        returnMessageOfE2Bounds = editor.getBounds(returnMessageOfE2Bot);
        returnMessageOfE4Bounds = editor.getBounds(returnMessageOfE4Bot);

    }

    /**
     * Checks the diagram consistency following semantic eventEnds ordering.
     */
    public void testDiagramConsistency() {

        // Check InstanceRoles positions
        Assert.assertEquals(origin.x, instanceRoleEditPartABounds.x);
        Assert.assertEquals(true, instanceRoleEditPartABounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP <= instanceRoleEditPartBBounds.x);
        Assert.assertEquals(true, instanceRoleEditPartBBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP <= instanceRoleEditPartCBounds.x);
        Assert.assertEquals(true, instanceRoleEditPartCBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP <= instanceRoleEditPartDBounds.x);
        Assert.assertEquals(true, instanceRoleEditPartDBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP <= instanceRoleEditPartEBounds.x);

        Assert.assertEquals(origin.y, instanceRoleEditPartABounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartBBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartCBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartDBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartEBounds.y);

        // Checks semantic covered lifelines
        Assert.assertEquals(4, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        Assert.assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        // Check IU bounds
        Assert.assertTrue("InteractionUse has bad left margin ", firstInteractionUseBounds.x >= instanceRoleEditPartABounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", firstInteractionUseBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("InteractionUse has bad top margin ", firstInteractionUseBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        Assert.assertTrue("InteractionUse has bad right margin ", firstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", firstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartDBounds.getTopRight().x);
        Assert.assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, firstInteractionUseBounds.height);

        Assert.assertTrue("InteractionUse has bad left margin ", secondInteractionUseBounds.x >= instanceRoleEditPartBBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", secondInteractionUseBounds.x <= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad top margin ", secondInteractionUseBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        Assert.assertTrue("InteractionUse has bad right margin ", secondInteractionUseBounds.getTopRight().x >= instanceRoleEditPartCBounds.x + instanceRoleEditPartCBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", secondInteractionUseBounds.getTopRight().x <= instanceRoleEditPartCBounds.getTopRight().x);
        Assert.assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, secondInteractionUseBounds.height);

        // Check event ends orders
        Assert.assertTrue("Exec e1 must be below first IU", e1Bounds.y > firstInteractionUseBounds.getBottomLeft().y);
        Assert.assertTrue("Follow the start of Exec e3", e3Bounds.y > e1Bounds.getBottomLeft().y);
        Assert.assertTrue("Follow the start of Exec e4", e4Bounds.y > e3Bounds.y);
        Assert.assertTrue("Follow the start of Exec e2", e2Bounds.y > e4Bounds.y);
        Assert.assertTrue("Follow the start of IU ref.2", secondInteractionUseBounds.y > e2Bounds.y);
        Assert.assertTrue("Follow the end of Exec e2", e2Bounds.getBottomLeft().y > secondInteractionUseBounds.getBottomLeft().y);
        Assert.assertTrue("Follow the start of Exec e5", e5Bounds.y > e2Bounds.getBottomLeft().y);
        Assert.assertTrue("Follow the end of Exec e4", e4Bounds.getBottomLeft().y > e5Bounds.getBottomLeft().y);
        Assert.assertTrue("Follow the end of Exec e4", e3Bounds.getBottomLeft().y > e4Bounds.getBottomLeft().y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        // Main part
        sequenceDiagramBot = null;

        interaction = null;

        // InstanceRoles
        instanceRoleEditPartABot = null;
        instanceRoleEditPartBBot = null;
        instanceRoleEditPartCBot = null;
        instanceRoleEditPartDBot = null;
        instanceRoleEditPartEBot = null;

        instanceRoleEditPartA = null;
        instanceRoleEditPartB = null;
        instanceRoleEditPartC = null;
        instanceRoleEditPartD = null;
        instanceRoleEditPartE = null;

        participantA = null;
        participantB = null;
        participantC = null;
        participantD = null;
        participantE = null;

        instanceRoleEditPartABounds = null;
        instanceRoleEditPartBBounds = null;
        instanceRoleEditPartCBounds = null;
        instanceRoleEditPartDBounds = null;
        instanceRoleEditPartEBounds = null;

        // IUs
        firstInteractionUseBot = null;
        secondInteractionUseBot = null;

        firstInteractionEditPart = null;
        secondInteractionEditPart = null;

        firstInteractionUse = null;
        secondInteractionUse = null;

        firstInteractionUseBounds = null;
        secondInteractionUseBounds = null;

        // Execs
        e1Bot = null;
        e2Bot = null;
        e3Bot = null;
        e4Bot = null;
        e5Bot = null;
        e6Bot = null;

        e1Bounds = null;
        e2Bounds = null;
        e3Bounds = null;
        e4Bounds = null;
        e5Bounds = null;
        e6Bounds = null;

        // Messages
        callMessageOnE1Bot = null;
        callMessageOnE2Bot = null;
        callMessageOnE4Bot = null;
        callMessageOnE6Bot = null;
        returnMessageOfE1Bot = null;
        returnMessageOfE2Bot = null;
        returnMessageOfE4Bot = null;
        returnMessageOfE6Bot = null;

        callMessageOnE1Bounds = null;
        callMessageOnE2Bounds = null;
        callMessageOnE4Bounds = null;
        callMessageOnE6Bounds = null;

        returnMessageOfE1Bounds = null;
        returnMessageOfE2Bounds = null;
        returnMessageOfE4Bounds = null;
        returnMessageOfE6Bounds = null;

        super.tearDown();
    }

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

    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    protected String getSessionModel() {
        return SESSION_FILE;
    }

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

}
