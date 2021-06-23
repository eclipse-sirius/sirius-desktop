/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.sequence;

import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.query.SequenceMessageViewQuery;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.sample.interactions.Message;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Test CreateMessage.
 * 
 * @author edugueperoux
 */
public class CreateMessageTests extends AbstractSequenceSiriusDiagramTests {

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    private static final String REPRESENTATION_NAME = "Sequence Diagram Example";

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "createMessage/";

    private static final String sessionModel = "semanticModel.aird";

    private static final String typesSemanticModel = "semanticModelTypes.ecore";

    private Participant participantP1;

    private Participant participantP2;

    private Participant participantP3;

    private Participant participantP4;

    private DDiagramElement diagramElementP1;

    private DDiagramElement diagramElementP2;

    private DDiagramElement diagramElementP3;

    private DDiagramElement diagramElementP4;

    private Bounds diagramElementP1Bounds;

    private Bounds diagramElementP2Bounds;

    private Bounds diagramElementP3Bounds;

    private Bounds diagramElementP4Bounds;

    private Message createP2Message;

    private Message createP4Message;

    private Message createP3Message;

    private DEdge createP2Edge;

    private DEdge createP4Edge;

    private DEdge createP3Edge;

    private Range createP2EdgeRange;

    private Range createP4EdgeRange;

    private Range createP3EdgeRange;

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "semanticModel.interactions";
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

        openSequenceDiagramOfType(REPRESENTATION_NAME, REPRESENTATION_TYPE);

        // Arrange All
        arrangeAll(diagramEditPart);

        // instanceRoles
        participantP1 = interaction.getParticipants().get(0);
        participantP2 = interaction.getParticipants().get(1);
        participantP3 = interaction.getParticipants().get(2);
        participantP4 = interaction.getParticipants().get(3);

        diagramElementP1 = getFirstDiagramElement(sequenceDDiagram, participantP1);
        diagramElementP2 = getFirstDiagramElement(sequenceDDiagram, participantP2);
        diagramElementP3 = getFirstDiagramElement(sequenceDDiagram, participantP3);
        diagramElementP4 = getFirstDiagramElement(sequenceDDiagram, participantP4);

        updateInstanceRolesBounds();

        // create messages
        createP2Message = interaction.getMessages().get(0);
        createP4Message = interaction.getMessages().get(1);
        createP3Message = interaction.getMessages().get(2);

        createP2Edge = getFirstEdgeElement(sequenceDDiagram, createP2Message);
        createP4Edge = getFirstEdgeElement(sequenceDDiagram, createP4Message);
        createP3Edge = getFirstEdgeElement(sequenceDDiagram, createP3Message);

        updateCreateMessagesRanges();
    }

    private void updateInstanceRolesBounds() {
        diagramElementP1Bounds = getBounds(diagramElementP1, participantP1);
        diagramElementP2Bounds = getBounds(diagramElementP2, participantP2);
        diagramElementP3Bounds = getBounds(diagramElementP3, participantP3);
        diagramElementP4Bounds = getBounds(diagramElementP4, participantP4);
    }

    private void updateCreateMessagesRanges() {
        createP2EdgeRange = new SequenceMessageViewQuery(getGmfEdge(createP2Edge)).getVerticalRange();
        createP4EdgeRange = new SequenceMessageViewQuery(getGmfEdge(createP4Edge)).getVerticalRange();
        createP3EdgeRange = new SequenceMessageViewQuery(getGmfEdge(createP3Edge)).getVerticalRange();
    }

    /**
     * Test that after an arrange all create messages are correctly arranged.
     */
    public void testDiagramConsistency() {

        assertNotNull(diagramElementP1);
        assertNotNull(diagramElementP2);
        assertNotNull(diagramElementP3);
        assertNotNull(diagramElementP4);

        assertNotNull(createP2Edge);
        assertNotNull(createP4Edge);
        assertNotNull(createP3Edge);

        checkInitialBounds();
    }

    private void checkInitialBounds() {
        assertEquals(origin.x, diagramElementP1Bounds.getX());
        assertEquals(origin.y, diagramElementP1Bounds.getY());

        assertEquals(diagramElementP1Bounds.getX() + diagramElementP1Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementP2Bounds.getX());
        int yP2 = createP2EdgeRange.getLowerBound() - diagramElementP2Bounds.getHeight() / 2;
        assertEquals(yP2, diagramElementP2Bounds.getY());

        assertEquals(diagramElementP2Bounds.getX() + diagramElementP2Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementP3Bounds.getX());
        int yP3 = createP3EdgeRange.getLowerBound() - diagramElementP3Bounds.getHeight() / 2;
        assertEquals(yP3, diagramElementP3Bounds.getY());

        assertEquals(diagramElementP3Bounds.getX() + diagramElementP3Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, diagramElementP4Bounds.getX());
        int yP4 = createP4EdgeRange.getLowerBound() - diagramElementP4Bounds.getHeight() / 2;
        assertEquals(yP4, diagramElementP4Bounds.getY());

        // TODO : checks messages bounds.
    }

    /**
     * Test that CreateMessage deletion is effective and reposition the target InstanceRole at
     * LayoutConstants.LIFELINES_START_Y.
     */
    public void testCreateMessageDeletion1() {
        // Delete createP2Message
        SequenceMessageEditPart createP2EditPart = (SequenceMessageEditPart) getEditPart(createP2Edge);
        DestroyElementRequest destroyElementRequest = new DestroyElementRequest(createP2Message, false);
        EditCommandRequestWrapper editCommandRequestWrapper = new EditCommandRequestWrapper(destroyElementRequest);
        createP2EditPart.performRequest(editCommandRequestWrapper);

        assertFalse(interaction.getMessages().contains(createP2Message));
        assertNull(getFirstEdgeElement(sequenceDDiagram, createP2Message));
        checkBoundsAfterCreateP2MessageDeletionOfTestCreateMessageDeletion1();

        // test undo
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canUndo());
        session.getTransactionalEditingDomain().getCommandStack().undo();

        updateInstanceRolesBounds();
        updateCreateMessagesRanges();
        checkInitialBounds();

        // test redo
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canRedo());
        session.getTransactionalEditingDomain().getCommandStack().redo();

        checkBoundsAfterCreateP2MessageDeletionOfTestCreateMessageDeletion1();

    }

    private void checkBoundsAfterCreateP2MessageDeletionOfTestCreateMessageDeletion1() {
        Bounds newDiagramElementP1Bounds = getBounds(diagramElementP1, participantP1);
        Bounds newDiagramElementP2Bounds = getBounds(diagramElementP2, participantP2);
        Bounds newDiagramElementP3Bounds = getBounds(diagramElementP3, participantP3);
        Bounds newDiagramElementP4Bounds = getBounds(diagramElementP4, participantP4);

        Range newCreateP4EdgeRange = new SequenceMessageViewQuery(getGmfEdge(createP4Edge)).getVerticalRange();
        Range newCreateP3EdgeRange = new SequenceMessageViewQuery(getGmfEdge(createP3Edge)).getVerticalRange();

        assertEquals(origin.x, newDiagramElementP1Bounds.getX());
        assertEquals(origin.y, newDiagramElementP1Bounds.getY());

        assertEquals(diagramElementP1Bounds.getX() + diagramElementP1Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementP2Bounds.getX());
        assertEquals(origin.y, newDiagramElementP2Bounds.getY());

        assertEquals(diagramElementP2Bounds.getX() + diagramElementP2Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementP3Bounds.getX());
        int yP3 = newCreateP3EdgeRange.getLowerBound() - newDiagramElementP3Bounds.getHeight() / 2;
        assertEquals(yP3, newDiagramElementP3Bounds.getY());

        assertEquals(diagramElementP3Bounds.getX() + diagramElementP3Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementP4Bounds.getX());
        int yP4 = newCreateP4EdgeRange.getLowerBound() - newDiagramElementP4Bounds.getHeight() / 2;
        assertEquals(yP4, newDiagramElementP4Bounds.getY());
    }

    /**
     * Test that InstanceRole deletion is effective and delete also all related CreateMessage.
     */
    public void testCreateMessageDeletion2() {
        // Delete createP2Message
        InstanceRoleEditPart participantP1EditPart = (InstanceRoleEditPart) getEditPart(diagramElementP1);
        DestroyElementRequest destroyElementRequest = new DestroyElementRequest(participantP1, false);
        EditCommandRequestWrapper editCommandRequestWrapper = new EditCommandRequestWrapper(destroyElementRequest);
        participantP1EditPart.performRequest(editCommandRequestWrapper);

        assertFalse(interaction.getMessages().contains(createP2Message));
        assertNull(getFirstEdgeElement(sequenceDDiagram, createP2Message));
        assertFalse(interaction.getParticipants().contains(participantP1));
        assertNull(getFirstEdgeElement(sequenceDDiagram, participantP1));

        checkBoundsAfterParticipantP1DeletionOfTestCreateMessageDeletion2();

        // test undo
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canUndo());
        session.getTransactionalEditingDomain().getCommandStack().undo();

        updateInstanceRolesBounds();
        updateCreateMessagesRanges();
        checkInitialBounds();

        // test redo
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canRedo());
        session.getTransactionalEditingDomain().getCommandStack().redo();

        checkBoundsAfterParticipantP1DeletionOfTestCreateMessageDeletion2();
    }

    private void checkBoundsAfterParticipantP1DeletionOfTestCreateMessageDeletion2() {
        Bounds newDiagramElementP2Bounds = getBounds(diagramElementP2, participantP2);
        Bounds newDiagramElementP3Bounds = getBounds(diagramElementP3, participantP3);
        Bounds newDiagramElementP4Bounds = getBounds(diagramElementP4, participantP4);

        Range newCreateP4Range = new SequenceMessageViewQuery(getGmfEdge(createP4Edge)).getVerticalRange();
        Range newCreateP3Range = new SequenceMessageViewQuery(getGmfEdge(createP3Edge)).getVerticalRange();

        assertEquals(diagramElementP1Bounds.getX() + diagramElementP1Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementP2Bounds.getX());
        assertEquals(origin.y, newDiagramElementP2Bounds.getY());

        assertEquals(diagramElementP2Bounds.getX() + diagramElementP2Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementP3Bounds.getX());
        int yP3 = newCreateP3Range.getLowerBound() - newDiagramElementP3Bounds.getHeight() / 2;
        assertEquals(yP3, newDiagramElementP3Bounds.getY());

        assertEquals(diagramElementP3Bounds.getX() + diagramElementP3Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP, newDiagramElementP4Bounds.getX());
        int yP4 = newCreateP4Range.getLowerBound() - newDiagramElementP4Bounds.getHeight() / 2;
        assertEquals(yP4, newDiagramElementP4Bounds.getY());
    }

}
