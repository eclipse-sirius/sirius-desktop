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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.SequenceLayout;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Test InteractionUse for move and resize commands.
 * 
 * @author edugueperoux
 */
public class InteractionUseTests extends AbstractSequenceSiriusDiagramTests {

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID +UNIT_DATA_ROOT + "interactionUses/";

    private static final String typesSemanticModel = "types.ecore";

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

    private InteractionUse secondInteractionUse;

    private DDiagramElement diagramElementOfFirstIU;

    private DDiagramElement diagramElementOfSecondIU;

    private InteractionUseEditPart firstInteractionUseEditPart;

    private InteractionUseEditPart secondInteractionUseEditPart;

    private Rectangle diagramElementOfFirstIUBounds;

    private Rectangle diagramElementOfSecondIUBounds;

    private Execution e1;

    private Execution e2;

    private Execution e3;

    private Execution e4;

    private Execution e5;

    private DDiagramElement diagramElementOfE1;

    private DDiagramElement diagramElementOfE2;

    private DDiagramElement diagramElementOfE3;

    private DDiagramElement diagramElementOfE4;

    private DDiagramElement diagramElementOfE5;

    private ExecutionEditPart e1EditPart;

    private ExecutionEditPart e2EditPart;

    private ExecutionEditPart e3EditPart;

    private ExecutionEditPart e4EditPart;

    private ExecutionEditPart e5EditPart;

    private Rectangle diagramElementOfE1Bounds;

    private Rectangle diagramElementOfE2Bounds;

    private Rectangle diagramElementOfE3Bounds;

    private Rectangle diagramElementOfE4Bounds;

    private Rectangle diagramElementOfE5Bounds;

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "interactionUses.interactions";
    }

    @Override
    protected String getTypesSemanticModel() {
        return typesSemanticModel;
    }

    @Override
    protected String getSessionModel() {
        // return sessionModel;
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
        diagramEditPart.getFigure().validate();

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
        secondInteractionUse = interaction.getInteractionUses().get(1);

        diagramElementOfFirstIU = getFirstDiagramElement(sequenceDDiagram, firstInteractionUse);
        diagramElementOfSecondIU = getFirstDiagramElement(sequenceDDiagram, secondInteractionUse);

        firstInteractionUseEditPart = (InteractionUseEditPart) getEditPart(diagramElementOfFirstIU);
        secondInteractionUseEditPart = (InteractionUseEditPart) getEditPart(diagramElementOfSecondIU);

        diagramElementOfFirstIUBounds = firstInteractionUseEditPart.getFigure().getBounds();
        diagramElementOfSecondIUBounds = secondInteractionUseEditPart.getFigure().getBounds();

        e1 = interaction.getExecutions().get(0);
        e2 = interaction.getExecutions().get(3);
        e3 = interaction.getExecutions().get(1);
        e4 = interaction.getExecutions().get(2);
        e5 = interaction.getExecutions().get(4);

        diagramElementOfE1 = getFirstDiagramElement(sequenceDDiagram, e1);
        diagramElementOfE2 = getFirstDiagramElement(sequenceDDiagram, e2);
        diagramElementOfE3 = getFirstDiagramElement(sequenceDDiagram, e3);
        diagramElementOfE4 = getFirstDiagramElement(sequenceDDiagram, e4);
        diagramElementOfE5 = getFirstDiagramElement(sequenceDDiagram, e5);

        e1EditPart = (ExecutionEditPart) getEditPart(diagramElementOfE1);
        e2EditPart = (ExecutionEditPart) getEditPart(diagramElementOfE2);
        e3EditPart = (ExecutionEditPart) getEditPart(diagramElementOfE3);
        e4EditPart = (ExecutionEditPart) getEditPart(diagramElementOfE4);
        e5EditPart = (ExecutionEditPart) getEditPart(diagramElementOfE5);

        diagramElementOfE1Bounds = e1EditPart.getFigure().getBounds();
        diagramElementOfE2Bounds = e2EditPart.getFigure().getBounds();
        diagramElementOfE3Bounds = e3EditPart.getFigure().getBounds();
        diagramElementOfE4Bounds = e4EditPart.getFigure().getBounds();
        diagramElementOfE5Bounds = e5EditPart.getFigure().getBounds();
    }

    /**
     * Checks the diagram consistency following semantic eventEnds ordering.
     */
    public void testDiagramConsistency() {

        assertNotNull(diagramElementA);
        assertNotNull(diagramElementB);
        assertNotNull(diagramElementC);
        assertNotNull(diagramElementD);
        assertNotNull(diagramElementE);

        // Check InstanceRoles positions
        assertEquals(origin.x, diagramElementABounds.x);
        assertEquals(diagramElementABounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementBBounds.x);
        assertEquals(diagramElementBBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementCBounds.x);
        assertEquals(diagramElementCBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementDBounds.x);
        assertEquals(diagramElementDBounds.getTopRight().x + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementEBounds.x);

        assertEquals(origin.y, diagramElementABounds.y);
        assertEquals(origin.y, diagramElementBBounds.y);
        assertEquals(origin.y, diagramElementCBounds.y);
        assertEquals(origin.y, diagramElementDBounds.y);
        assertEquals(origin.y, diagramElementEBounds.y);

        // Checks semantic covered lifelines
        assertEquals(4, firstInteractionUse.getCoveredParticipants().size());
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantA));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        assertNotNull(diagramElementOfFirstIU);
        assertNotNull(diagramElementOfSecondIU);

        // Check IU bounds
        assertTrue("InteractionUse has bad left margin ", diagramElementOfFirstIUBounds.x >= diagramElementABounds.x);
        assertTrue("InteractionUse has bad left margin ", diagramElementOfFirstIUBounds.x <= diagramElementABounds.x + diagramElementABounds.width / 2);
        assertTrue("InteractionUse has bad top margin ", diagramElementOfFirstIUBounds.y >= diagramElementABounds.getBottomLeft().y);
        assertTrue("InteractionUse has bad right margin ", diagramElementOfFirstIUBounds.getTopRight().x >= diagramElementDBounds.x + diagramElementDBounds.width / 2);
        assertTrue("InteractionUse has bad right margin ", diagramElementOfFirstIUBounds.getTopRight().x <= diagramElementDBounds.getTopRight().x);
        assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, diagramElementOfFirstIUBounds.height);

        assertTrue("InteractionUse has bad left margin ", diagramElementOfSecondIUBounds.x >= diagramElementBBounds.x);
        assertTrue("InteractionUse has bad left margin ", diagramElementOfSecondIUBounds.x <= diagramElementBBounds.x + diagramElementBBounds.width / 2);
        assertTrue("InteractionUse has bad top margin ", diagramElementOfSecondIUBounds.y >= diagramElementABounds.getBottomLeft().y);
        assertTrue("InteractionUse has bad right margin ", diagramElementOfSecondIUBounds.getTopRight().x >= diagramElementCBounds.x + diagramElementCBounds.width / 2);
        assertTrue("InteractionUse has bad right margin ", diagramElementOfSecondIUBounds.getTopRight().x <= diagramElementCBounds.getTopRight().x);
        assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, diagramElementOfSecondIUBounds.height);

        assertNotNull(diagramElementOfE1);
        assertNotNull(diagramElementOfE2);
        assertNotNull(diagramElementOfE3);
        assertNotNull(diagramElementOfE4);
        assertNotNull(diagramElementOfE5);

        // Check event ends orders
        assertTrue("Exec e1 must be below first IU", diagramElementOfE1Bounds.y > diagramElementOfFirstIUBounds.getBottomLeft().y);
        assertTrue("Follow the start of Exec e3", diagramElementOfE3Bounds.y > diagramElementOfE1Bounds.getBottomLeft().y);
        assertTrue("Follow the start of Exec e4", diagramElementOfE4Bounds.y > diagramElementOfE3Bounds.y);
        assertTrue("Follow the start of Exec e2", diagramElementOfE2Bounds.y > diagramElementOfE4Bounds.y);
        assertTrue("Follow the start of IU ref.2", diagramElementOfSecondIUBounds.y > diagramElementOfE2Bounds.y);
        assertTrue("Follow the end of Exec e2", diagramElementOfE2Bounds.getBottomLeft().y > diagramElementOfFirstIUBounds.getBottomLeft().y);
        assertTrue("Follow the start of Exec e5", diagramElementOfE5Bounds.y > diagramElementOfE2Bounds.getBottomLeft().y);
        assertTrue("Follow the end of Exec e4", diagramElementOfE4Bounds.getBottomLeft().y > diagramElementOfE5Bounds.getBottomLeft().y);
        assertTrue("Follow the end of Exec e4", diagramElementOfE3Bounds.getBottomLeft().y > diagramElementOfE4Bounds.getBottomLeft().y);

        // TODO : Check messages bounds
    }

    /**
     * Test InteractionUse creation
     * 
     * NOTE : IU creation with 1 or 2 clicks can only be checked with swtbot
     */
    public void testIUCreation1() {
        applyNodeCreationTool(InteractionsConstants.INTERACTION_USE_TOOL_ID, sequenceDDiagram, sequenceDDiagram);
        // TODO
    }

    /**
     * Test that after a InteractionUse is created, it become not horizontal
     * moveable (see section 2.5.6 of spec on
     * "Sequence Diagrams : Combined Fragments"). VP-1106
     */
    public void testHorizontalMove() {

        // Test that horizontal move of the first InteractionUse has not effect
        // to right
        int xDelta = 20;
        Point delta = new Point(xDelta, 0);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        Bounds newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        // to left
        delta = new Point(-xDelta, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        // Test that horizontal move of the second InteractionUse has not effect
        // to right
        delta = new Point(xDelta, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);

        Bounds newDiagramElementOfSecondIUBounds = getBounds(diagramElementOfSecondIU, secondInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        // to left
        // failed : but works in SWTBot tests
        delta = new Point(-xDelta, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);

        newDiagramElementOfSecondIUBounds = getBounds(diagramElementOfSecondIU, secondInteractionUse);

        assertEquals(diagramElementOfSecondIUBounds, asRectangle(newDiagramElementOfSecondIUBounds));
    }

    /**
     * Test that vertical move is allowed.
     */
    public void testVerticalMove() {

        // Test that vertical move of the first InteractionUse is allowed
        int yDelta = 20;
        Point delta = new Point(0, yDelta);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move down
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        Bounds newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds.getTranslated(0, yDelta), asRectangle(newDiagramElementOfFirstIUBounds));

        delta = new Point(0, -yDelta);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move up
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds.getTranslated(0, -yDelta), asRectangle(newDiagramElementOfFirstIUBounds));

        // Test that vertical move of the second InteractionUse is allowed
        delta = new Point(0, yDelta);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move up second IU insert it before e2 and e4, and shift them
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);

        delta = new Point(0, -yDelta);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move down the second IU to have e2, e4 expand and e6 shifted
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);
    }

    /**
     * Test the vertical move of Execution e2 : move of e2 must shift the whole
     * block (2nd syncCall, 2nd IU, e4 with its subExecution e5 and the messages
     * on e4 but not e3) of the same delta.
     */
    public void testVerticalMoveOfE2() {
        int xDelta = 20;
        Point delta = new Point(0, xDelta);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(e2EditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move up
        e2EditPart.performRequest(changeBoundsRequest);

        // Check event ends orders
        assertTrue("Exec e1 must be below first IU", diagramElementOfE1Bounds.y > diagramElementOfFirstIUBounds.getBottomLeft().y);
        assertTrue("Follow the start of Exec e3", diagramElementOfE3Bounds.y > diagramElementOfE1Bounds.getBottomLeft().y);
        assertTrue("Follow the start of Exec e4", diagramElementOfE4Bounds.y > diagramElementOfE3Bounds.y);
        assertTrue("Follow the start of Exec e2", diagramElementOfE2Bounds.y > diagramElementOfE4Bounds.y);
        assertTrue("Follow the start of IU ref.2", diagramElementOfSecondIUBounds.y > diagramElementOfE2Bounds.y);
        assertTrue("Follow the end of Exec e2", diagramElementOfE2Bounds.getBottomLeft().y > diagramElementOfFirstIUBounds.getBottomLeft().y);
        assertTrue("Follow the start of Exec e5", diagramElementOfE5Bounds.y > diagramElementOfE2Bounds.getBottomLeft().y);
        assertTrue("Follow the end of Exec e4", diagramElementOfE4Bounds.getBottomLeft().y > diagramElementOfE5Bounds.getBottomLeft().y);
        assertTrue("Follow the end of Exec e4", diagramElementOfE3Bounds.getBottomLeft().y > diagramElementOfE4Bounds.getBottomLeft().y);

    }

    /**
     * Test that horizontal resize has not effect because not allowed.
     */
    public void testHorizontalResize() {

        // Test that horizontal resize of the first InteractionUse has not
        // effect
        int xDelta = 20;
        Point delta = new Point(xDelta, 0);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        changeBoundsRequest.setSizeDelta(new Dimension(diagramElementOfFirstIUBounds.getSize().shrink(xDelta, 0)));
        changeBoundsRequest.setResizeDirection(PositionConstants.WEST);
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        Bounds newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        delta = new Point(-xDelta, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        // Test that horizontal resize of the second InteractionUse has not
        // effect
        delta = new Point(xDelta, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);

        Bounds newDiagramElementOfSecondIUBounds = getBounds(diagramElementOfSecondIU, secondInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        delta = new Point(-xDelta, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);

        newDiagramElementOfSecondIUBounds = getBounds(diagramElementOfSecondIU, secondInteractionUse);

        assertEquals(diagramElementOfSecondIUBounds, asRectangle(newDiagramElementOfSecondIUBounds));
    }

    /**
     * 
     */
    public void testVerticalResize() {

        // Test that vertical move of the first InteractionUse is allowed
        int xDelta = 20;
        Point delta = new Point(0, xDelta);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move up
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        Bounds newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        delta = new Point(0, -xDelta);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(firstInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move down
        firstInteractionUseEditPart.performRequest(changeBoundsRequest);

        newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);

        assertEquals(diagramElementOfFirstIUBounds, asRectangle(newDiagramElementOfFirstIUBounds));

        // Test that vertical move of the second InteractionUse is allowed
        delta = new Point(0, xDelta);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move up second UI insert it before e2 and e4, and shift them
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);

        delta = new Point(0, -xDelta);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(secondInteractionUseEditPart);
        changeBoundsRequest.setMoveDelta(delta);
        // vertical move down the second UI to have e2, e4 expand and e6 shifted
        secondInteractionUseEditPart.performRequest(changeBoundsRequest);

    }

    /**
     * Test that change graphical order of InstanceRole in
     * InteractionUse.coveredParticipants feature of a IU doesn't change
     * semantic order.
     */
    public void testInstanceRoleMoveOnInteractionUse1() {

        // Drag InstanceRoleA between InstanceRoleB and InstanceRoleC
        int xDelta = (diagramElementCBounds.getTopRight().x) - diagramElementABounds.x;
        Point delta = new Point(xDelta, 0);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(instanceRoleEditPartA);
        changeBoundsRequest.setMoveDelta(delta);
        instanceRoleEditPartA.performRequest(changeBoundsRequest);

        // Check that InteractionUse.coveredParticipants feature hasn't changed
        assertEquals(4, firstInteractionUse.getCoveredParticipants().size());
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantA));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        // Check graphical consistency
        Bounds newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);
        Bounds newDiagramElementOfSecondIUBounds = getBounds(diagramElementOfSecondIU, secondInteractionUse);

        Bounds newDiagramElementABounds = getBounds(diagramElementA, participantA);
        Bounds newDiagramElementBBounds = getBounds(diagramElementB, participantB);
        Bounds newDiagramElementCBounds = getBounds(diagramElementC, participantC);
        Bounds newDiagramElementDBounds = getBounds(diagramElementD, participantD);

        assertTrue(newDiagramElementOfFirstIUBounds.getX() >= newDiagramElementBBounds.getX());
        assertTrue(newDiagramElementOfFirstIUBounds.getX() < newDiagramElementBBounds.getX() + newDiagramElementBBounds.getWidth() / 2);
        assertTrue(newDiagramElementOfFirstIUBounds.getY() > newDiagramElementABounds.getY() + newDiagramElementABounds.getHeight());
        assertTrue(newDiagramElementOfFirstIUBounds.getX() + newDiagramElementOfFirstIUBounds.getWidth() > newDiagramElementDBounds.getX() + newDiagramElementDBounds.getWidth() / 2);
        assertTrue(newDiagramElementOfFirstIUBounds.getX() + newDiagramElementOfFirstIUBounds.getWidth() <= newDiagramElementDBounds.getX() + newDiagramElementDBounds.getWidth());

        assertTrue(newDiagramElementOfSecondIUBounds.getX() >= newDiagramElementBBounds.getX());
        assertTrue(newDiagramElementOfSecondIUBounds.getX() < newDiagramElementBBounds.getX() + newDiagramElementBBounds.getWidth() / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfSecondIUBounds.getX() + newDiagramElementOfSecondIUBounds.getWidth() > newDiagramElementCBounds.getX()
                + newDiagramElementCBounds.getWidth() / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfSecondIUBounds.getX() + newDiagramElementOfSecondIUBounds.getWidth() <= newDiagramElementCBounds.getX()
                + newDiagramElementCBounds.getWidth());
    }

    /**
     * Test that graphical insertion of an InstanceRole between graphical
     * representations of InteractionUse.coveredParticipants doesn't change
     * InteractionUse.coveredParticipants.
     */
    public void testInstanceRoleMoveOnInteractionUse2() {

        // Drag InstanceRoleE between InstanceRoleB and InstanceRoleC
        int xDelta = diagramElementCBounds.x - diagramElementEBounds.x - LayoutConstants.LIFELINES_MIN_X_GAP / 2;
        Point delta = new Point(xDelta, 0);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(instanceRoleEditPartE);
        changeBoundsRequest.setMoveDelta(delta);
        instanceRoleEditPartE.performRequest(changeBoundsRequest);

        // Check that InteractionUse.coveredParticipants feature hasn't changed
        assertEquals(4, firstInteractionUse.getCoveredParticipants().size());
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantA));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        // Check graphical consistency
        Bounds newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);
        Bounds newDiagramElementOfSecondIUBounds = getBounds(diagramElementOfSecondIU, secondInteractionUse);

        Bounds newDiagramElementABounds = getBounds(diagramElementA, participantA);
        Bounds newDiagramElementBBounds = getBounds(diagramElementB, participantB);
        Bounds newDiagramElementCBounds = getBounds(diagramElementC, participantC);
        Bounds newDiagramElementDBounds = getBounds(diagramElementD, participantD);

        assertTrue(newDiagramElementOfFirstIUBounds.getX() >= newDiagramElementABounds.getX());
        assertTrue(newDiagramElementOfFirstIUBounds.getX() < newDiagramElementABounds.getX() + newDiagramElementABounds.getWidth() / 2);
        assertTrue(newDiagramElementOfFirstIUBounds.getY() > newDiagramElementABounds.getY() + newDiagramElementABounds.getHeight());
        assertTrue(newDiagramElementOfFirstIUBounds.getX() + newDiagramElementOfFirstIUBounds.getWidth() > newDiagramElementDBounds.getX() + newDiagramElementDBounds.getWidth() / 2);
        assertTrue(newDiagramElementOfFirstIUBounds.getX() + newDiagramElementOfFirstIUBounds.getWidth() <= newDiagramElementDBounds.getX() + newDiagramElementDBounds.getWidth());

        assertTrue(newDiagramElementOfSecondIUBounds.getX() >= newDiagramElementBBounds.getX());
        assertTrue(newDiagramElementOfSecondIUBounds.getX() < newDiagramElementBBounds.getX() + newDiagramElementBBounds.getWidth() / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfSecondIUBounds.getX() + newDiagramElementOfSecondIUBounds.getWidth() > newDiagramElementCBounds.getX()
                + newDiagramElementCBounds.getWidth() / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfSecondIUBounds.getX() + newDiagramElementOfSecondIUBounds.getWidth() <= newDiagramElementCBounds.getX()
                + newDiagramElementCBounds.getWidth());
    }

    /**
     * Test that resize request on InstanceRole doesn't change
     * InteractionUse.coveredParticipants feature.
     */
    public void testInstanceRoleResizeOnInteractionUse1() {

        // Resize InstanceRoleC to right to see if its lifeline is out of the
        // IUs.
        int delta = 200;
        Dimension sizeDelta = new Dimension(delta, delta);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
        changeBoundsRequest.setEditParts(instanceRoleEditPartC);
        changeBoundsRequest.setSizeDelta(sizeDelta);
        instanceRoleEditPartC.performRequest(changeBoundsRequest);

        // Check that InteractionUse.coveredParticipants feature hasn't changed
        assertEquals(4, firstInteractionUse.getCoveredParticipants().size());
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantA));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantC));
        assertTrue(firstInteractionUse.getCoveredParticipants().contains(participantD));

        assertEquals(2, secondInteractionUse.getCoveredParticipants().size());
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantB));
        assertTrue(secondInteractionUse.getCoveredParticipants().contains(participantC));

        // Check graphical consistency
        Bounds newDiagramElementOfFirstIUBounds = getBounds(diagramElementOfFirstIU, firstInteractionUse);
        Bounds newDiagramElementOfSecondIUBounds = getBounds(diagramElementOfSecondIU, secondInteractionUse);

        Bounds newDiagramElementABounds = getBounds(diagramElementA, participantA);
        Bounds newDiagramElementBBounds = getBounds(diagramElementB, participantB);
        Bounds newDiagramElementCBounds = getBounds(diagramElementC, participantC);
        Bounds newDiagramElementDBounds = getBounds(diagramElementD, participantD);

        assertTrue(newDiagramElementOfFirstIUBounds.getX() >= newDiagramElementABounds.getX());
        assertTrue(newDiagramElementOfFirstIUBounds.getX() < newDiagramElementABounds.getX() + newDiagramElementABounds.getWidth() / 2);
        assertTrue(newDiagramElementOfFirstIUBounds.getY() > newDiagramElementABounds.getY() + newDiagramElementABounds.getHeight());
        assertTrue(newDiagramElementOfFirstIUBounds.getX() + newDiagramElementOfFirstIUBounds.getWidth() > newDiagramElementDBounds.getX() + newDiagramElementDBounds.getWidth() / 2);
        assertTrue(newDiagramElementOfFirstIUBounds.getX() + newDiagramElementOfFirstIUBounds.getWidth() <= newDiagramElementDBounds.getX() + newDiagramElementDBounds.getWidth());

        assertTrue(newDiagramElementOfSecondIUBounds.getX() >= newDiagramElementBBounds.getX());
        assertTrue(newDiagramElementOfSecondIUBounds.getX() < newDiagramElementBBounds.getX() + newDiagramElementBBounds.getWidth() / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfSecondIUBounds.getX() + newDiagramElementOfSecondIUBounds.getWidth() > newDiagramElementCBounds.getX()
                + newDiagramElementCBounds.getWidth() / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfSecondIUBounds.getX() + newDiagramElementOfSecondIUBounds.getWidth() <= newDiagramElementCBounds.getX()
                + newDiagramElementCBounds.getWidth());
    }

    /**
     * Test that changing InteractionUse#getCoveredParticipants directly by
     * changing the semantic model change the coveredParticipants graphically:
     * add participantE to the coveredParticipants list of firstInteractionUse
     */
    public void testCoveredParticipantsGraphicalChangeByChangingSemantic1() {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(firstInteractionUse);
        transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
            @Override
            protected void doExecute() {
                firstInteractionUse.getCoveredParticipants().add(participantE);
                new SequenceLayout(diagramEditPart.getDiagramView()).horizontalLayout(false);
            }
        });

        Rectangle newDiagramElementOfFirstIUBounds = asRectangle(getBounds(diagramElementOfFirstIU, firstInteractionUse));
        Rectangle newDiagramElementABounds = asRectangle(getBounds(diagramElementA, participantA));
        Rectangle newDiagramElementEBounds = asRectangle(getBounds(diagramElementE, participantE));

        assertTrue("InteractionUse has bad left margin ", newDiagramElementOfFirstIUBounds.x >= newDiagramElementABounds.x);
        assertTrue("InteractionUse has bad left margin ", newDiagramElementOfFirstIUBounds.x <= newDiagramElementABounds.x + newDiagramElementABounds.width / 2);
        assertTrue("InteractionUse has bad top margin ", newDiagramElementOfFirstIUBounds.y >= newDiagramElementABounds.getBottomLeft().y);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfFirstIUBounds.getTopRight().x >= newDiagramElementEBounds.x + newDiagramElementEBounds.width / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfFirstIUBounds.getTopRight().x <= newDiagramElementEBounds.getTopRight().x);
        assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, newDiagramElementOfFirstIUBounds.height);

        transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
            @Override
            protected void doExecute() {
                firstInteractionUse.getCoveredParticipants().remove(participantE);
            }
        });
    }

    /**
     * Test that changing InteractionUse#getCoveredParticipants directly by
     * changing the semantic model change the coveredParticipants graphically:
     * remove participantA to the coveredParticipants list of
     * firstInteractionUse
     */
    public void testCoveredParticipantsGraphicalChangeByChangingSemantic2() {
        TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(interaction);
        transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
            @Override
            protected void doExecute() {
                firstInteractionUse.getCoveredParticipants().remove(participantA);
                new SequenceLayout(diagramEditPart.getDiagramView()).horizontalLayout(false);
            }
        });

        Rectangle newDiagramElementOfFirstIUBounds = asRectangle(getBounds(diagramElementOfFirstIU, firstInteractionUse));
        Rectangle newDiagramElementBBounds = asRectangle(getBounds(diagramElementB, participantB));
        Rectangle newDiagramElementDBounds = asRectangle(getBounds(diagramElementD, participantD));

        assertTrue("InteractionUse has bad left margin ", newDiagramElementOfFirstIUBounds.x >= newDiagramElementBBounds.x);
        assertTrue("InteractionUse has bad left margin ", newDiagramElementOfFirstIUBounds.x <= newDiagramElementBBounds.x + newDiagramElementBBounds.width / 2);
        assertTrue("InteractionUse has bad top margin ", newDiagramElementOfFirstIUBounds.y >= newDiagramElementBBounds.getBottomLeft().y);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfFirstIUBounds.getTopRight().x >= newDiagramElementDBounds.x + newDiagramElementDBounds.width / 2);
        assertTrue("InteractionUse has bad right margin ", newDiagramElementOfFirstIUBounds.getTopRight().x <= newDiagramElementDBounds.getTopRight().x);
        assertEquals(LayoutConstants.DEFAULT_INTERACTION_USE_HEIGHT, newDiagramElementOfFirstIUBounds.height);

        transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
            @Override
            protected void doExecute() {
                firstInteractionUse.getCoveredParticipants().remove(participantA);
            }
        });
    }

}
