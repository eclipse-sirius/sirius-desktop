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
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Test InstanceRoleResizableEditPolicy for move and resize commands.
 * 
 * @author edugueperoux
 */
public class InstanceRoleResizableEditPolicyTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "lifelines_positions/";

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    private static final String typesSemanticModel = "types.ecore";

    private static final String sessionModel = "lifelines.aird";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "lifelines.interactions";
    }

    @Override
    protected String getTypesSemanticModel() {
        return typesSemanticModel;
    }

    @Override
    protected String getSessionModel() {
        return sessionModel;
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        createSequenceDiagramOfType(REPRESENTATION_TYPE);

        // Arrange All
        arrangeAll(diagramEditPart);
    }

    /**
     * Test InstanceRoleResizableEditPolicy#getMoveCommand :
     */
    public void testGetMoveCommand1() {

        Participant participantA = interaction.getParticipants().get(0);
        Participant participantB = interaction.getParticipants().get(1);
        Participant participantC;

        DDiagramElement diagramElementA = getFirstDiagramElement(sequenceDDiagram, participantA);
        DDiagramElement diagramElementB = getFirstDiagramElement(sequenceDDiagram, participantB);
        DDiagramElement diagramElementC;

        // Check if LIFELINE_A and LIFELINE_B are correctly arranged
        Bounds diagramElementABounds = getBounds(diagramElementA, participantA);
        assertEquals(LayoutConstants.LIFELINES_START_X, diagramElementABounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, diagramElementABounds.getY());

        Bounds diagramElementBBounds = getBounds(diagramElementB, participantB);
        assertEquals(LayoutConstants.LIFELINES_MIN_X_GAP + diagramElementBBounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementBBounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, diagramElementBBounds.getY());

        InstanceRoleEditPart instanceRoleEditPartA = (InstanceRoleEditPart) getEditPart(diagramElementA);
        InstanceRoleEditPart instanceRoleEditPartB = (InstanceRoleEditPart) getEditPart(diagramElementB);

        // Add LIFELINE_C
        final String LIFELINE_C = "c";
        createNewParticipant(asRectangle(diagramElementBBounds).getTopRight().getTranslated(50, 0));

        participantC = interaction.getParticipants().get(2);
        diagramElementC = getFirstDiagramElement(sequenceDDiagram, participantC);
        applyDirectEditTool("Edit Participant", sequenceDDiagram, diagramElementC, LIFELINE_C);

        // move LIFELINE_B to (20,0) delta
        Point delta = new Point(20, 0);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(instanceRoleEditPartB);
        changeBoundsRequest.setMoveDelta(delta);
        instanceRoleEditPartB.performRequest(changeBoundsRequest);

        Bounds newDiagramElementABounds = getBounds(diagramElementA, participantA);
        assertEquals(origin.x, newDiagramElementABounds.getX());
        assertEquals(origin.y, newDiagramElementABounds.getY());

        Bounds newDiagramElementBBounds = getBounds(diagramElementB, participantB);
        assertEquals(diagramElementBBounds.getX() + delta.x, newDiagramElementBBounds.getX());
        assertEquals(origin.y, newDiagramElementBBounds.getY());

        Bounds newDiagramElementCBounds = getBounds(diagramElementC, participantC);
        assertEquals(newDiagramElementBBounds.getX() + newDiagramElementBBounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementCBounds.getX(), 1);// delta
                                                                                                                                                                      // of
                                                                                                                                                                      // 1
                                                                                                                                                                      // unit
        assertEquals(origin.y, newDiagramElementCBounds.getY());

        // drag LIFELINE_A to (-2,-2) delta to test
        // LayoutConstants.LIFELINES_START margin constraints
        delta = new Point(-2, -2);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(instanceRoleEditPartA);
        changeBoundsRequest.setMoveDelta(delta);
        instanceRoleEditPartA.performRequest(changeBoundsRequest);

        newDiagramElementABounds = getBounds(diagramElementA, participantA);
        assertEquals(LayoutConstants.LIFELINES_START_X, newDiagramElementABounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, newDiagramElementABounds.getY());

        // drag LIFELINE_A to (30,0) delta to test if LIFELINE_B and LIFELINE_C
        // are correctly shifted when LIFELINE_A move to right
        delta = new Point(30, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(instanceRoleEditPartA);
        changeBoundsRequest.setMoveDelta(delta);
        instanceRoleEditPartA.performRequest(changeBoundsRequest);

        newDiagramElementABounds = getBounds(diagramElementA, participantA);
        assertEquals(LayoutConstants.LIFELINES_START_X + delta.x, newDiagramElementABounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, newDiagramElementABounds.getY());

        newDiagramElementBBounds = getBounds(diagramElementB, participantB);
        assertEquals(newDiagramElementABounds.getX() + newDiagramElementABounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementBBounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, diagramElementBBounds.getY());

        newDiagramElementCBounds = getBounds(diagramElementC, participantC);
        assertEquals(newDiagramElementBBounds.getX() + newDiagramElementBBounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementCBounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, newDiagramElementCBounds.getY());

        // Create a fourteenth InstanceRoleEditPart's figure named D
        final String LIFELINE_D = "d";
        createNewParticipant(asRectangle(newDiagramElementCBounds).getTopRight().getTranslated(50, 0));
        applyNodeCreationTool("Participant", sequenceDDiagram, sequenceDDiagram);
        Participant participantD = interaction.getParticipants().get(3);
        DDiagramElement diagramElementD = getFirstDiagramElement(sequenceDDiagram, participantC);
        InstanceRoleEditPart instanceRoleEditPartD = (InstanceRoleEditPart) getEditPart(diagramElementD);
        applyDirectEditTool("Edit Participant", sequenceDDiagram, diagramElementD, LIFELINE_D);

        // drag LIFELINE_D to (-200,0) to test if LIFELINE_A, LIFELINE_B and
        // LIFELINE_C are correctly shifted to leftmost
        delta = new Point(-200, 0);
        changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(instanceRoleEditPartD);
        changeBoundsRequest.setMoveDelta(delta);
        instanceRoleEditPartD.performRequest(changeBoundsRequest);

        newDiagramElementABounds = getBounds(diagramElementA, participantA);
        assertEquals(LayoutConstants.LIFELINES_START_X, newDiagramElementABounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, newDiagramElementABounds.getY());

        newDiagramElementBBounds = getBounds(diagramElementB, participantB);
        assertEquals(newDiagramElementABounds.getX() + newDiagramElementABounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementBBounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, diagramElementBBounds.getY());

        newDiagramElementCBounds = getBounds(diagramElementC, participantC);
        assertEquals(newDiagramElementBBounds.getX() + newDiagramElementBBounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementCBounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, newDiagramElementCBounds.getY());

        Bounds diagramElementDBounds = getBounds(diagramElementD, participantD);
        assertEquals(newDiagramElementCBounds.getX() + newDiagramElementCBounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementDBounds.getX());
        assertEquals(LayoutConstants.LIFELINES_START_Y, diagramElementDBounds.getY());
    }

}
