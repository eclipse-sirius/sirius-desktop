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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * @author edugueperoux
 */
public class CombinedFragments2Tests extends AbstractCombinedFragmentSequence2Tests {

    // InstanceRoles
    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private SWTBotGefEditPart instanceRoleEditPartCBot;

    private SWTBotGefEditPart instanceRoleEditPartDBot;

    private SWTBotGefEditPart instanceRoleEditPartEBot;

    private InstanceRoleEditPart instanceRoleEditPartA;

    private InstanceRoleEditPart instanceRoleEditPartB;

    private InstanceRoleEditPart instanceRoleEditPartC;

    private InstanceRoleEditPart instanceRoleEditPartD;

    private InstanceRoleEditPart instanceRoleEditPartE;

    private Participant participantA;

    private Participant participantB;

    private Participant participantC;

    private Participant participantD;

    private Participant participantE;

    private Rectangle instanceRoleEditPartABounds;

    private Rectangle instanceRoleEditPartBBounds;

    private Rectangle instanceRoleEditPartCBounds;

    private Rectangle instanceRoleEditPartDBounds;

    private Rectangle instanceRoleEditPartEBounds;

    // IU
    private SWTBotGefEditPart firstInteractionUseBot;

    private InteractionUseEditPart firstInteractionUseEditPart;

    private InteractionUse firstInteractionUse;

    private Rectangle firstInteractionUseBounds;

    // CFCs
    private SWTBotGefEditPart firstCombinedFragmentBot;

    private SWTBotGefEditPart secondCombinedFragmentBot;

    private CombinedFragmentEditPart firstCombinedFragmentEditPart;

    private CombinedFragmentEditPart secondCombinedFragmentEditPart;

    private CombinedFragment firstCombinedFragment;

    private CombinedFragment secondCombinedFragment;

    private Rectangle firstCombinedFragmentBounds;

    private Rectangle secondCombinedFragmentBounds;

    // Operands
    private SWTBotGefEditPart secondOperandOfFirstCombinedFragmentBot;

    private Rectangle secondOperandOfFirstCombinedFragmentBounds;

    // Execs
    private SWTBotGefEditPart e2Bot;

    private Rectangle e2Bounds;

    // Messages
    private SWTBotGefConnectionEditPart createMessageOfE;

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

        SWTBotGefEditPart sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();

        // IUs
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);

        firstInteractionUseEditPart = (InteractionUseEditPart) firstInteractionUseBot.part();

        firstInteractionUse = (InteractionUse) firstInteractionUseEditPart.resolveTargetSemanticElement();

        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);

        // CFCs
        firstCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        secondCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(1);

        firstCombinedFragmentEditPart = (CombinedFragmentEditPart) firstCombinedFragmentBot.part();
        secondCombinedFragmentEditPart = (CombinedFragmentEditPart) secondCombinedFragmentBot.part();

        firstCombinedFragment = (CombinedFragment) firstCombinedFragmentEditPart.resolveTargetSemanticElement();
        secondCombinedFragment = (CombinedFragment) secondCombinedFragmentEditPart.resolveTargetSemanticElement();

        firstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);
        secondCombinedFragmentBounds = editor.getBounds(secondCombinedFragmentBot);

        // Operands
        secondOperandOfFirstCombinedFragmentBot = firstCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(1);

        secondOperandOfFirstCombinedFragmentBounds = editor.getBounds(secondOperandOfFirstCombinedFragmentBot);

        // Execs
        e2Bot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e2Bounds = editor.getBounds(e2Bot);

        createMessageOfE = instanceRoleEditPartEBot.parent().targetConnections().get(0);
    }

    /**
     * Test creation of sub reflexive syncCall in operand of a combined
     * fragment.
     */
    public void _testSubReflexiveSyncCallCreationInCombinedFragmentCreationDeeplyNested() {
        Point start = e2Bounds.getCenter();
        Point finish = start.getTranslated(0, 5);
        SWTBotGefEditPart newSubReflexiveSyncCallBot;
        Rectangle newReflexiveSyncCallBounds;

        final int NB_SUB_REFLEXIVE_SYNC_CALL_CREATION = 20;
        for (int i = 0; i < NB_SUB_REFLEXIVE_SYNC_CALL_CREATION; i++) {
            createSyncCall(start, finish);
            newSubReflexiveSyncCallBot = editor.getEditPart(new Rectangle(start, finish).getCenter(), ExecutionEditPart.class);
            newReflexiveSyncCallBounds = editor.getBounds(newSubReflexiveSyncCallBot);
            start = newReflexiveSyncCallBounds.getTop().translate(5, 5);
            finish = start.getTranslated(5, 5);

            // TODO : Checks constraints
        }
        for (int i = 0; i < NB_SUB_REFLEXIVE_SYNC_CALL_CREATION; i++) {
            undo();
        }
        testDiagramConsistency();
    }

    /**
     * Checks the diagram consistency following semantic eventEnds ordering.
     */
    public void testDiagramConsistency() {

        // Check InstanceRoles positions
        int dx = 10;
        Assert.assertEquals(origin.x + dx, instanceRoleEditPartABounds.x);
        Assert.assertEquals(instanceRoleEditPartABounds.getTopRight().x + 2 * dx + LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartBBounds.x);
        Assert.assertEquals(instanceRoleEditPartBBounds.getTopRight().x + 2 * dx + LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartCBounds.x);
        Assert.assertEquals(instanceRoleEditPartCBounds.getTopRight().x + 2 * dx + LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartDBounds.x);
        Assert.assertEquals(instanceRoleEditPartDBounds.getTopRight().x + 2 * dx + LayoutConstants.LIFELINES_MIN_X_GAP, instanceRoleEditPartEBounds.x);

        Assert.assertEquals(origin.y, instanceRoleEditPartABounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartBBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartCBounds.y);
        Assert.assertEquals(origin.y, instanceRoleEditPartDBounds.y);
        Assert.assertEquals(editor.getBounds(createMessageOfE).y, instanceRoleEditPartEBounds.getCenter().y);

        // Checks semantic covered participants
        Assert.assertEquals(2, firstInteractionUse.getCoveredParticipants().size());
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));
        Assert.assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantE));

        Assert.assertEquals(2, firstCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantB));

        Assert.assertEquals(5, secondCombinedFragment.getCoveredParticipants().size());
        Assert.assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantA));
        Assert.assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantB));
        Assert.assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantC));
        Assert.assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantD));
        Assert.assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantE));

        // Check IU bounds
        Assert.assertTrue("InteractionUse has bad left margin ", firstInteractionUseBounds.x >= instanceRoleEditPartDBounds.x);
        Assert.assertTrue("InteractionUse has bad left margin ", firstInteractionUseBounds.x <= instanceRoleEditPartDBounds.x + instanceRoleEditPartDBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad top margin ", firstInteractionUseBounds.y >= instanceRoleEditPartDBounds.getBottomLeft().y);
        Assert.assertTrue("InteractionUse has bad right margin ", firstInteractionUseBounds.getTopRight().x >= instanceRoleEditPartEBounds.x + instanceRoleEditPartEBounds.width / 2);
        Assert.assertTrue("InteractionUse has bad right margin ", firstInteractionUseBounds.getTopRight().x <= instanceRoleEditPartEBounds.getTopRight().x);
        Assert.assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, firstInteractionUseBounds.height);

        Assert.assertTrue("CombinedFragment has bad left margin ", firstCombinedFragmentBounds.x <= instanceRoleEditPartABounds.x);
        Assert.assertTrue("CombinedFragment has bad left margin ", firstCombinedFragmentBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("CombinedFragment has bad top margin ", firstCombinedFragmentBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        Assert.assertTrue("CombinedFragment has bad right margin ", firstCombinedFragmentBounds.getTopRight().x >= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        Assert.assertTrue("CombinedFragment has bad right margin ", firstCombinedFragmentBounds.getTopRight().x >= instanceRoleEditPartBBounds.getTopRight().x);

        Assert.assertTrue("CombinedFragment has bad left margin ", secondCombinedFragmentBounds.x <= instanceRoleEditPartABounds.x);
        Assert.assertTrue("CombinedFragment has bad left margin ", secondCombinedFragmentBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        Assert.assertTrue("CombinedFragment has bad top margin ", secondCombinedFragmentBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        Assert.assertTrue("CombinedFragment has bad right margin ", secondCombinedFragmentBounds.getTopRight().x >= instanceRoleEditPartEBounds.x + instanceRoleEditPartEBounds.width / 2);
        Assert.assertTrue("CombinedFragment has bad right margin ", secondCombinedFragmentBounds.getTopRight().x >= instanceRoleEditPartEBounds.getTopRight().x);

    }

    /**
     * Test Operand creation on CombinedFragment cartridge.
     */
    public void testCombinedFragmentOperandCreationOnCartridge() {
        Point creationLocation = firstCombinedFragmentBounds.getLocation().getTranslated(4, 4);
        createCombinedFragmentOperand(creationLocation);

    }

    /**
     * Test that nested CombinedFragment creation with a single click on
     * existing CombinedFragment (on Operand) is possible and expand parent
     * bounds to respect left diagram margin.
     */
    // TODO
    public void testCombinedFragmentCreationDeeplyNested() {
        Point creationLocation = new Point(instanceRoleEditPartABounds.getCenter().x, secondOperandOfFirstCombinedFragmentBounds.getCenter().y);
        SWTBotGefEditPart newCombinedFragmentBot;
        Rectangle newCombinedFragmentBounds;

        final int NB_CFC_CREATION = 20;
        for (int i = 0; i < NB_CFC_CREATION; i++) {
            createCombinedFragment(creationLocation);
            newCombinedFragmentBot = editor.getEditPart(creationLocation.getTranslated(0, 5), CombinedFragmentEditPart.class);
            newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);
            creationLocation = new Point(editor.getBounds(instanceRoleEditPartABot).getCenter().x, newCombinedFragmentBounds.getCenter().y);

            // TODO : Checks constraints
        }
        for (int i = 0; i < NB_CFC_CREATION; i++) {
            undo();
        }
        testDiagramConsistency();
    }

    /**
     * Test creation of sub executions in operand of a combined fragment.
     */
    public void _testSubExecutionCreationInCombinedFragmentCreationDeeplyNested() {
        Point creationLocation = e2Bounds.getCenter();
        SWTBotGefEditPart newSubExecutionBot;
        Rectangle newSubExecutionBounds;

        final int NB_SUB_EXECUTION_CREATION = 20;
        for (int i = 0; i < NB_SUB_EXECUTION_CREATION; i++) {
            createExecution(creationLocation.x, creationLocation.y);
            newSubExecutionBot = editor.getEditPart(creationLocation.getTranslated(5, 5), ExecutionEditPart.class);
            newSubExecutionBounds = editor.getBounds(newSubExecutionBot);
            creationLocation = newSubExecutionBounds.getCenter();

            // TODO : Checks constraints
        }
        for (int i = 0; i < NB_SUB_EXECUTION_CREATION; i++) {
            undo();
        }
        testDiagramConsistency();
    }
}
