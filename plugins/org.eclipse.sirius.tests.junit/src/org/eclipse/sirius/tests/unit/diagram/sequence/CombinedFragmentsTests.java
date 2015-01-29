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
package org.eclipse.sirius.tests.unit.diagram.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Operand;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Test CombinedFragment and its Operand for move and resize commands.
 * 
 * @author edugueperoux
 */
public class CombinedFragmentsTests extends AbstractSequenceSiriusDiagramTests {

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID +UNIT_DATA_ROOT + "combinedFragments2/";

    private static final String semanticModelPath = "combinedFragments.interactions";

    private static final String semanticModelTypesPath = "types.ecore";

    private Participant participantA;

    private Participant participantB;

    private Participant participantC;

    private Participant participantD;

    private Participant participantE;

    private DDiagramElement diagramElementA;

    private DDiagramElement diagramElementB;

    private DDiagramElement diagramElementC;

    private DDiagramElement diagramElementD;

    private DDiagramElement diagramElementE;

    private InstanceRoleEditPart instanceRoleEditPartA;

    private InstanceRoleEditPart instanceRoleEditPartB;

    private InstanceRoleEditPart instanceRoleEditPartC;

    private InstanceRoleEditPart instanceRoleEditPartD;

    private InstanceRoleEditPart instanceRoleEditPartE;

    private Rectangle diagramElementABounds;

    private Rectangle diagramElementBBounds;

    private Rectangle diagramElementCBounds;

    private Rectangle diagramElementDBounds;

    private Rectangle diagramElementEBounds;

    private InteractionUse firstInteractionUse;

    private DDiagramElement diagramElementOfFirstIU;

    private InteractionUseEditPart firstInteractionUseEditPart;

    private Rectangle diagramElementOfFirstIUBounds;

    private CombinedFragment firstCombinedFragment;

    private CombinedFragment secondCombinedFragment;

    private DDiagramElement diagramElementOfFirstCFC;

    private DDiagramElement diagramElementOfSecondCFC;

    private Rectangle diagramElementOfFirstCFCBounds;

    private CombinedFragmentEditPart firstCombinedFragmentEditPart;

    private Operand firstOperandOfFirstCFC;

    private Operand secondOperandOfFirstCFC;

    private Operand thirdOperandOfFirstCFC;

    private Operand firstOperandOfSecondCFC;

    private DDiagramElement diagramElementOfFirstOperandOfFirstCFC;

    private DDiagramElement diagramElementOfSecondOperandOfFirstCFC;

    private DDiagramElement diagramElementOfThirdOperandOfFirstCFC;

    private DDiagramElement diagramElementOfFirstOperandOfSecondCFC;

    private Execution e1;

    private Execution e2;

    private Execution e3;

    private Execution e4;

    private DDiagramElement diagramElementOfE1;

    private DDiagramElement diagramElementOfE2;

    private DDiagramElement diagramElementOfE3;

    private DDiagramElement diagramElementOfE4;

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return semanticModelPath;
    }

    @Override
    protected String getTypesSemanticModel() {
        return semanticModelTypesPath;
    }

    @Override
    protected String getSessionModel() {
        // return sessionModelPath;
        return null;
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // openSequenceDiagramOfType(REPRESENTATION_NAME, REPRESENTATION_TYPE);
        createSequenceDiagramOfType(REPRESENTATION_TYPE);

        // Arrange All
        arrangeAll(diagramEditPart);

        participantA = interaction.getParticipants().get(0);
        participantB = interaction.getParticipants().get(1);
        participantC = interaction.getParticipants().get(2);
        participantD = interaction.getParticipants().get(3);
        participantE = interaction.getParticipants().get(4);

        diagramElementA = getFirstDiagramElement(sequenceDDiagram, participantA);
        diagramElementB = getFirstDiagramElement(sequenceDDiagram, participantB);
        diagramElementC = getFirstDiagramElement(sequenceDDiagram, participantC);
        diagramElementD = getFirstDiagramElement(sequenceDDiagram, participantD);
        diagramElementE = getFirstDiagramElement(sequenceDDiagram, participantE);

        instanceRoleEditPartA = (InstanceRoleEditPart) getEditPart(diagramElementA);
        instanceRoleEditPartB = (InstanceRoleEditPart) getEditPart(diagramElementB);
        instanceRoleEditPartC = (InstanceRoleEditPart) getEditPart(diagramElementC);
        instanceRoleEditPartD = (InstanceRoleEditPart) getEditPart(diagramElementD);
        instanceRoleEditPartE = (InstanceRoleEditPart) getEditPart(diagramElementE);

        diagramElementABounds = instanceRoleEditPartA.getFigure().getBounds();
        diagramElementBBounds = instanceRoleEditPartB.getFigure().getBounds();
        diagramElementCBounds = instanceRoleEditPartC.getFigure().getBounds();
        diagramElementDBounds = instanceRoleEditPartD.getFigure().getBounds();
        diagramElementEBounds = instanceRoleEditPartE.getFigure().getBounds();

        firstInteractionUse = interaction.getInteractionUses().get(0);

        diagramElementOfFirstIU = getFirstDiagramElement(sequenceDDiagram, firstInteractionUse);

        firstInteractionUseEditPart = (InteractionUseEditPart) getEditPart(diagramElementOfFirstIU);

        diagramElementOfFirstIUBounds = firstInteractionUseEditPart.getFigure().getBounds();

        firstCombinedFragment = interaction.getCombinedFragments().get(0);
        secondCombinedFragment = interaction.getCombinedFragments().get(1);

        diagramElementOfFirstCFC = getFirstDiagramElement(sequenceDDiagram, firstCombinedFragment);
        diagramElementOfSecondCFC = getFirstDiagramElement(sequenceDDiagram, secondCombinedFragment);

        firstCombinedFragmentEditPart = (CombinedFragmentEditPart) getEditPart(diagramElementOfFirstCFC);

        diagramElementOfFirstCFCBounds = firstCombinedFragmentEditPart.getFigure().getBounds();

        firstOperandOfFirstCFC = firstCombinedFragment.getOwnedOperands().get(0);
        secondOperandOfFirstCFC = firstCombinedFragment.getOwnedOperands().get(1);
        thirdOperandOfFirstCFC = firstCombinedFragment.getOwnedOperands().get(2);
        firstOperandOfSecondCFC = secondCombinedFragment.getOwnedOperands().get(0);

        diagramElementOfFirstOperandOfFirstCFC = getFirstDiagramElement(sequenceDDiagram, firstOperandOfFirstCFC);
        diagramElementOfSecondOperandOfFirstCFC = getFirstDiagramElement(sequenceDDiagram, secondOperandOfFirstCFC);
        diagramElementOfThirdOperandOfFirstCFC = getFirstDiagramElement(sequenceDDiagram, thirdOperandOfFirstCFC);
        diagramElementOfFirstOperandOfSecondCFC = getFirstDiagramElement(sequenceDDiagram, firstOperandOfSecondCFC);

        e1 = interaction.getExecutions().get(0);
        e2 = interaction.getExecutions().get(1);
        e3 = interaction.getExecutions().get(2);
        e4 = interaction.getExecutions().get(3);

        diagramElementOfE1 = getFirstDiagramElement(sequenceDDiagram, e1);
        diagramElementOfE2 = getFirstDiagramElement(sequenceDDiagram, e2);
        diagramElementOfE3 = getFirstDiagramElement(sequenceDDiagram, e3);
        diagramElementOfE4 = getFirstDiagramElement(sequenceDDiagram, e4);
    }

    public void testDiagramConsistency() {

        assertNotNull(diagramElementA);
        assertNotNull(diagramElementB);
        assertNotNull(diagramElementC);
        assertNotNull(diagramElementD);
        assertNotNull(diagramElementE);

        assertEquals(origin.x, diagramElementABounds.x);
        assertEquals(diagramElementABounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementBBounds.x);
        assertEquals(diagramElementBBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementCBounds.x);
        assertEquals(diagramElementCBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementDBounds.x);
        assertEquals(diagramElementDBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementEBounds.x);

        assertEquals(origin.y, diagramElementABounds.y);
        assertEquals(origin.y, diagramElementBBounds.y);
        assertEquals(origin.y, diagramElementCBounds.y);
        assertEquals(origin.y, diagramElementDBounds.y);

        assertEquals(2, firstInteractionUse.getCoveredParticipants().size());
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantE));

        assertEquals(2, firstCombinedFragment.getCoveredParticipants().size());
        assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantA));
        assertTrue(firstCombinedFragment.getCoveredParticipants().contains(participantB));

        assertEquals(5, secondCombinedFragment.getCoveredParticipants().size());
        assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantA));
        assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantB));
        assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantC));
        assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantD));
        assertTrue(secondCombinedFragment.getCoveredParticipants().contains(participantE));

        assertNotNull(diagramElementOfFirstIU);
        assertNotNull(diagramElementOfFirstCFC);
        assertNotNull(diagramElementOfSecondCFC);
        assertNotNull(diagramElementOfFirstOperandOfFirstCFC);
        assertNotNull(diagramElementOfSecondOperandOfFirstCFC);
        assertNotNull(diagramElementOfThirdOperandOfFirstCFC);
        assertNotNull(diagramElementOfFirstOperandOfSecondCFC);

        // Check IU bounds
        assertTrue("InteractionUse has bad left margin ", diagramElementOfFirstIUBounds.x >= diagramElementABounds.x);
        assertTrue("InteractionUse has bad left margin ", diagramElementOfFirstIUBounds.x <= diagramElementABounds.x + diagramElementABounds.width / 2);
        assertTrue("InteractionUse has bad top margin ", diagramElementOfFirstIUBounds.y >= diagramElementABounds.getBottomLeft().y);
        assertTrue("InteractionUse has bad right margin ", diagramElementOfFirstIUBounds.getTopRight().x >= diagramElementDBounds.x + diagramElementDBounds.width / 2);
        assertTrue("InteractionUse has bad right margin ", diagramElementOfFirstIUBounds.getTopRight().x <= diagramElementDBounds.getTopRight().x);

        // TODO : check CFC/CFO bounds
        // assertTrue(diagramElementOfFirstOperandOfFirstCFCBounds);

        assertNotNull(diagramElementOfE1);
        assertNotNull(diagramElementOfE2);
        assertNotNull(diagramElementOfE3);
        assertNotNull(diagramElementOfE4);

        // TODO: Check Executions bounds

        // TODO : Check messages bounds
    }

    /**
     * Test that after a CombinedFragment is created, it become not horizontal
     * moveable (see section 3.5.6 of spec on
     * "Sequence Diagrams : Combined Fragments").
     */
    public void testHorizontalMove() {
        // Test that horizontal move of the first Combined Fragment has not
        // effect
        int xDelta = 20;
        Point delta = new Point(xDelta, 0);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(firstCombinedFragmentEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        firstCombinedFragmentEditPart.performRequest(changeBoundsRequest);

        Bounds newDiagramElementOfFirstCFCBounds = getBounds(diagramElementOfFirstCFC, firstCombinedFragment);

        assertEquals(diagramElementOfFirstCFCBounds.x, newDiagramElementOfFirstCFCBounds.getX());
        assertEquals(diagramElementOfFirstIUBounds.y, newDiagramElementOfFirstCFCBounds.getY());
        assertEquals(diagramElementOfFirstIUBounds.width, newDiagramElementOfFirstCFCBounds.getWidth());
        assertEquals(diagramElementOfFirstIUBounds.height, newDiagramElementOfFirstCFCBounds.getHeight());

        delta = new Point(-xDelta, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);
    }
}
