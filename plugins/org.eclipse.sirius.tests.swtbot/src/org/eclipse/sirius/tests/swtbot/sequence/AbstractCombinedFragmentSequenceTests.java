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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.Operand;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Abstract test class for combinedfragments.
 * 
 * @author edugueperoux
 */
public abstract class AbstractCombinedFragmentSequenceTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "combinedFragments/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample #1";

    private static final String MODEL = "combinedFragment.interactions";

    private static final String SESSION_FILE = "combinedFragment.aird";

    protected static final String TYPES_FILE = "types.ecore";

    protected static final int NB_INITIAL_EXECUTIONS = 2;

    protected static final int NB_INITIAL_INTERACTION_USE = 0;

    protected static final int NB_INITIAL_COMBINED_FRAGMENT = 2;

    // Main part
    protected SWTBotGefEditPart sequenceDiagramBot;

    protected Interaction interaction;

    // InstanceRoles
    protected SWTBotGefEditPart instanceRoleEditPartABot;

    protected SWTBotGefEditPart instanceRoleEditPartBBot;

    protected SWTBotGefEditPart instanceRoleEditPartCBot;

    protected SWTBotGefEditPart instanceRoleEditPartDBot;

    protected SWTBotGefEditPart instanceRoleEditPartEBot;

    protected InstanceRoleEditPart instanceRoleEditPartA;

    protected InstanceRoleEditPart instanceRoleEditPartB;

    protected InstanceRoleEditPart instanceRoleEditPartC;

    protected InstanceRoleEditPart instanceRoleEditPartD;

    protected InstanceRoleEditPart instanceRoleEditPartE;

    protected Participant participantA;

    protected Participant participantB;

    protected Participant participantC;

    protected Participant participantD;

    protected Participant participantE;

    protected Rectangle instanceRoleEditPartABounds;

    protected Rectangle instanceRoleEditPartBBounds;

    protected Rectangle instanceRoleEditPartCBounds;

    protected Rectangle instanceRoleEditPartDBounds;

    protected Rectangle instanceRoleEditPartEBounds;

    // CFCs
    protected SWTBotGefEditPart firstCombinedFragmentBot;

    protected SWTBotGefEditPart secondCombinedFragmentBot;

    protected CombinedFragmentEditPart firstCombinedFragmentEditPart;

    protected CombinedFragmentEditPart secondCombinedFragmentEditPart;

    protected CombinedFragment firstCombinedFragment;

    protected CombinedFragment secondCombinedFragment;

    protected Rectangle firstCombinedFragmentBounds;

    protected Rectangle secondCombinedFragmentBounds;

    // Operands
    protected SWTBotGefEditPart firstOperandOfFirstCombinedFragmentBot;

    protected SWTBotGefEditPart secondOperandOfFirstCombinedFragmentBot;

    protected SWTBotGefEditPart firstOperandOfSecondCombinedFragmentBot;

    protected SWTBotGefEditPart secondOperandOfSecondCombinedFragmentBot;

    protected OperandEditPart firstOperandOfFirstCombinedFragmentEditPart;

    protected OperandEditPart secondOperandOfFirstCombinedFragmentEditPart;

    protected OperandEditPart firstOperandOfSecondCombinedFragmentEditPart;

    protected OperandEditPart secondOperandOfSecondCombinedFragmentEditPart;

    protected Operand firstOperandOfFirstCombinedFragment;

    protected Operand secondOperandOfFirstCombinedFragment;

    protected Operand firstOperandOfSecondCombinedFragment;

    protected Operand secondOperandOfSecondCombinedFragment;

    protected Rectangle firstOperandOfFirstCombinedFragmentBounds;

    protected Rectangle secondOperandOfFirstCombinedFragmentBounds;

    protected Rectangle firstOperandOfSecondCombinedFragmentBounds;

    protected Rectangle secondOperandOfSecondCombinedFragmentBounds;

    // Execs
    protected SWTBotGefEditPart e1Bot;

    protected SWTBotGefEditPart e2Bot;

    protected ExecutionEditPart e1EditPart;

    protected ExecutionEditPart e2EditPart;

    protected Execution e1;

    protected Execution e2;

    protected Rectangle e1Bounds;

    protected Rectangle e2Bounds;

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
        firstOperandOfFirstCombinedFragmentBot = firstCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(0);
        secondOperandOfFirstCombinedFragmentBot = firstCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(1);
        firstOperandOfSecondCombinedFragmentBot = secondCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(0);
        secondOperandOfSecondCombinedFragmentBot = secondCombinedFragmentBot.descendants(IsInstanceOf.instanceOf(OperandEditPart.class)).get(1);

        firstOperandOfFirstCombinedFragmentEditPart = (OperandEditPart) firstOperandOfFirstCombinedFragmentBot.part();
        secondOperandOfFirstCombinedFragmentEditPart = (OperandEditPart) secondOperandOfFirstCombinedFragmentBot.part();
        firstOperandOfSecondCombinedFragmentEditPart = (OperandEditPart) firstOperandOfSecondCombinedFragmentBot.part();
        secondOperandOfSecondCombinedFragmentEditPart = (OperandEditPart) secondOperandOfSecondCombinedFragmentBot.part();

        firstOperandOfFirstCombinedFragment = (Operand) firstOperandOfFirstCombinedFragmentEditPart.resolveTargetSemanticElement();
        secondOperandOfFirstCombinedFragment = (Operand) secondOperandOfFirstCombinedFragmentEditPart.resolveTargetSemanticElement();
        firstOperandOfSecondCombinedFragment = (Operand) firstOperandOfSecondCombinedFragmentEditPart.resolveTargetSemanticElement();
        secondOperandOfSecondCombinedFragment = (Operand) secondOperandOfSecondCombinedFragmentEditPart.resolveTargetSemanticElement();

        firstOperandOfFirstCombinedFragmentBounds = editor.getBounds(firstOperandOfFirstCombinedFragmentBot);
        secondOperandOfFirstCombinedFragmentBounds = editor.getBounds(secondOperandOfFirstCombinedFragmentBot);
        firstOperandOfSecondCombinedFragmentBounds = editor.getBounds(firstOperandOfSecondCombinedFragmentBot);
        secondOperandOfSecondCombinedFragmentBounds = editor.getBounds(secondOperandOfSecondCombinedFragmentBot);

        // Execs
        e1Bot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(0);
        e2Bot = instanceRoleEditPartBBot.parent().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class)).get(1);

        e1EditPart = (ExecutionEditPart) e1Bot.part();
        e2EditPart = (ExecutionEditPart) e2Bot.part();

        e1 = (Execution) e1EditPart.resolveTargetSemanticElement();
        e2 = (Execution) e2EditPart.resolveTargetSemanticElement();

        e1Bounds = editor.getBounds(e1Bot);
        e2Bounds = editor.getBounds(e2Bot);
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

        Assert.assertEquals(2, interaction.getCombinedFragments().size());

        // Checks semantic covered participants
        Assert.assertEquals(2, firstCombinedFragment.getCoveredParticipants().size());
        assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantA));
        assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantB));

        Assert.assertEquals(2, secondCombinedFragment.getCoveredParticipants().size());
        assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantA));
        assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantB));

        // Check IU bounds
        assertTrue("CombinedFragment has bad left margin ", firstCombinedFragmentBounds.x >= instanceRoleEditPartABounds.x);
        assertTrue("CombinedFragment has bad left margin ", firstCombinedFragmentBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        assertTrue("CombinedFragment has bad top margin ", firstCombinedFragmentBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        assertTrue("CombinedFragment has bad right margin ", firstCombinedFragmentBounds.getTopRight().x >= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        assertTrue("CombinedFragment has bad right margin ", firstCombinedFragmentBounds.getTopRight().x <= instanceRoleEditPartBBounds.getTopRight().x);

        assertTrue("CombinedFragment has bad left margin ", secondCombinedFragmentBounds.x >= instanceRoleEditPartABounds.x);
        assertTrue("CombinedFragment has bad left margin ", secondCombinedFragmentBounds.x <= instanceRoleEditPartABounds.x + instanceRoleEditPartABounds.width / 2);
        assertTrue("CombinedFragment has bad top margin ", secondCombinedFragmentBounds.y >= instanceRoleEditPartABounds.getBottomLeft().y);
        assertTrue("CombinedFragment has bad right margin ", secondCombinedFragmentBounds.getTopRight().x >= instanceRoleEditPartBBounds.x + instanceRoleEditPartBBounds.width / 2);
        assertTrue("CombinedFragment has bad right margin ", secondCombinedFragmentBounds.getTopRight().x <= instanceRoleEditPartBBounds.getTopRight().x);

        // Check event ends orders
        assertTrue("Exec e1 must be above first CFC", e1Bounds.getBottom().y < firstCombinedFragmentBounds.y);
        assertTrue("Follow the start of the first CFC", firstCombinedFragmentBounds.y < firstOperandOfFirstCombinedFragmentBounds.y);
        assertTrue("Follow the start of the first Operand of the first CFC", firstOperandOfFirstCombinedFragmentBounds.y < e2Bounds.y);
        assertTrue("Follow the end of e2", e2Bounds.getBottom().y < firstOperandOfFirstCombinedFragmentBounds.getBottom().y);
        Assert.assertEquals("Follow the start of the second Operand of the first CFC", firstOperandOfFirstCombinedFragmentBounds.getBottom().y, secondOperandOfFirstCombinedFragmentBounds.y);
        Assert.assertEquals("Follow the end of the second Operand of the first CFC and the end of the CFC itself", firstCombinedFragmentBounds.getBottom().y,
                secondOperandOfFirstCombinedFragmentBounds.getBottom().y);

        assertTrue("Follow the start of the second CFC", secondCombinedFragmentBounds.y > firstCombinedFragmentBounds.getBottom().y);
        assertTrue("Follow the start of the first Operand of the second CFC", firstOperandOfSecondCombinedFragmentBounds.y > secondCombinedFragmentBounds.y);
        Assert.assertEquals("Follow the start of the second Operand of the second CFC", firstOperandOfSecondCombinedFragmentBounds.getBottom().y, secondOperandOfSecondCombinedFragmentBounds.y);
        Assert.assertEquals("Follow the end of the second Operand of the second CFC and the end of the CFC itself", secondCombinedFragmentBounds.getBottom().y,
                secondOperandOfSecondCombinedFragmentBounds.getBottom().y);

    }

    /**
     * Checks that figures haven't changed.
     */
    protected void assertNotChange() {

        Assert.assertEquals(NB_INITIAL_EXECUTIONS, interaction.getExecutions().size());
        Assert.assertEquals(NB_INITIAL_INTERACTION_USE, interaction.getInteractionUses().size());
        Assert.assertEquals(NB_INITIAL_COMBINED_FRAGMENT, interaction.getCombinedFragments().size());

        Assert.assertEquals(instanceRoleEditPartEBounds, editor.getBounds(instanceRoleEditPartEBot));

        assertDiagramElementsUnchanged();
        validateOrdering(12);
    }

    /**
     * Checks that diagram elements haven't changed.
     */
    protected void assertDiagramElementsUnchanged() {
        Assert.assertEquals(e1Bounds, editor.getBounds(e1Bot));
        Assert.assertEquals(e2Bounds, editor.getBounds(e2Bot));

        Assert.assertEquals(firstCombinedFragmentBounds, editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds, editor.getBounds(secondCombinedFragmentBot));

        Assert.assertEquals(firstOperandOfFirstCombinedFragmentBounds, editor.getBounds(firstOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfFirstCombinedFragmentBounds, editor.getBounds(secondOperandOfFirstCombinedFragmentBot));
        Assert.assertEquals(firstOperandOfSecondCombinedFragmentBounds, editor.getBounds(firstOperandOfSecondCombinedFragmentBot));
        Assert.assertEquals(secondOperandOfSecondCombinedFragmentBounds, editor.getBounds(secondOperandOfSecondCombinedFragmentBot));
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

        // CFCs
        firstCombinedFragmentBot = null;
        secondCombinedFragmentBot = null;

        firstCombinedFragmentEditPart = null;
        secondCombinedFragmentEditPart = null;

        firstCombinedFragment = null;
        secondCombinedFragment = null;

        firstCombinedFragmentBounds = null;
        secondCombinedFragmentBounds = null;

        // Operands
        firstOperandOfFirstCombinedFragmentBot = null;
        secondOperandOfFirstCombinedFragmentBot = null;
        firstOperandOfSecondCombinedFragmentBot = null;
        secondOperandOfSecondCombinedFragmentBot = null;

        firstOperandOfFirstCombinedFragmentEditPart = null;
        secondOperandOfFirstCombinedFragmentEditPart = null;
        firstOperandOfSecondCombinedFragmentEditPart = null;
        secondOperandOfSecondCombinedFragmentEditPart = null;

        firstOperandOfFirstCombinedFragment = null;
        secondOperandOfFirstCombinedFragment = null;
        firstOperandOfSecondCombinedFragment = null;
        secondOperandOfSecondCombinedFragment = null;

        firstOperandOfFirstCombinedFragmentBounds = null;
        secondOperandOfFirstCombinedFragmentBounds = null;
        firstOperandOfSecondCombinedFragmentBounds = null;
        secondOperandOfSecondCombinedFragmentBounds = null;

        // Execs
        e1Bot = null;
        e2Bot = null;

        e1EditPart = null;
        e2EditPart = null;

        e1 = null;
        e2 = null;

        e1Bounds = null;
        e2Bounds = null;

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
