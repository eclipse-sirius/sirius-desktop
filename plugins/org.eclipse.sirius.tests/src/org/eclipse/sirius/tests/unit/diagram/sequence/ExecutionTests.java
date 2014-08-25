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
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.Message;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;

/**
 * Test Execution.
 * 
 * @author edugueperoux
 */
public class ExecutionTests extends AbstractSequenceSiriusDiagramTests {

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    private static final String REPRESENTATION_NAME = "Sequence Diagram Example";

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "executions/";

    private static final String sessionModel = "executions.aird";

    private static final String typesSemanticModel = "types.ecore";

    private IPreferenceStore workspaceViewerPreferenceStore;

    private boolean oldSnapMode;

    private Participant participantP1;

    private Participant participantP2;

    private Participant participantP3;

    private DDiagramElement diagramElementP1;

    private DDiagramElement diagramElementP2;

    private DDiagramElement diagramElementP3;

    private Bounds diagramElementP1Bounds;

    private Bounds diagramElementP2Bounds;

    private Bounds diagramElementP3Bounds;

    private Execution e1;

    private Execution e2;

    private DDiagramElement diagramElementE1;

    private DDiagramElement diagramElementE2;

    private Message callMessageOnE1;

    private Message reflexiveCallMessageOnE2;

    private Message reflexiveReturnMessageOnE1;

    private Message returnMessageOnParticipantA;

    private DEdge callMessageOnE1Edge;

    private DEdge reflexiveCallMessageOnE2Edge;

    private DEdge reflexiveReturnMessageOnE1Edge;

    private DEdge returnMessageOnParticipantAEdge;

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "executions.interactions";
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
        TestsUtil.synchronizationWithUIThread();

        workspaceViewerPreferenceStore = ((DiagramGraphicalViewer) diagramEditPart.getViewer()).getWorkspaceViewerPreferenceStore();
        oldSnapMode = workspaceViewerPreferenceStore.getBoolean(WorkspaceViewerProperties.SNAPTOGRID);
        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, false);

        // Arrange All
        arrangeAll(diagramEditPart);

        // instanceRoles
        participantP1 = interaction.getParticipants().get(0);
        participantP2 = interaction.getParticipants().get(1);
        participantP3 = interaction.getParticipants().get(2);

        diagramElementP1 = getFirstDiagramElement(sequenceDDiagram, participantP1);
        diagramElementP2 = getFirstDiagramElement(sequenceDDiagram, participantP2);
        diagramElementP3 = getFirstDiagramElement(sequenceDDiagram, participantP3);

        updateInstanceRolesBounds();

        e1 = interaction.getExecutions().get(0);
        e2 = interaction.getExecutions().get(1);

        diagramElementE1 = getFirstDiagramElement(sequenceDDiagram, e1);
        diagramElementE2 = getFirstDiagramElement(sequenceDDiagram, e2);

        callMessageOnE1 = interaction.getMessages().get(0);
        reflexiveCallMessageOnE2 = interaction.getMessages().get(1);
        reflexiveReturnMessageOnE1 = interaction.getMessages().get(2);
        returnMessageOnParticipantA = interaction.getMessages().get(3);

        callMessageOnE1Edge = getFirstEdgeElement(sequenceDDiagram, callMessageOnE1);
        reflexiveCallMessageOnE2Edge = getFirstEdgeElement(sequenceDDiagram, reflexiveCallMessageOnE2);
        reflexiveReturnMessageOnE1Edge = getFirstEdgeElement(sequenceDDiagram, reflexiveReturnMessageOnE1);
        returnMessageOnParticipantAEdge = getFirstEdgeElement(sequenceDDiagram, returnMessageOnParticipantA);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
    }

    private void updateInstanceRolesBounds() {
        diagramElementP1Bounds = getBounds(diagramElementP1, participantP1);
        diagramElementP2Bounds = getBounds(diagramElementP2, participantP2);
        diagramElementP3Bounds = getBounds(diagramElementP3, participantP3);
    }

    /**
     * Test that after an arrange all create messages are correctly arranged.
     */
    public void testDiagramConsistency() {

        assertNotNull(diagramElementP1);
        assertNotNull(diagramElementP2);
        assertNotNull(diagramElementP3);

        assertNotNull(diagramElementE1);
        assertNotNull(diagramElementE2);

        assertNotNull(callMessageOnE1Edge);
        assertNotNull(reflexiveCallMessageOnE2Edge);
        assertNotNull(reflexiveReturnMessageOnE1Edge);
        assertNotNull(returnMessageOnParticipantAEdge);

        checkInitialBounds();

    }

    private void checkInitialBounds() {
        assertEquals(origin.x, diagramElementP1Bounds.getX());
        assertEquals(origin.y, diagramElementP1Bounds.getY());

        assertEquals(diagramElementP1Bounds.getX() + diagramElementP1Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_PACKED_X_GAP, diagramElementP2Bounds.getX());
        assertEquals(origin.y, diagramElementP2Bounds.getY());

        assertEquals(diagramElementP2Bounds.getX() + diagramElementP2Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_PACKED_X_GAP, diagramElementP3Bounds.getX());
        assertEquals(origin.y, diagramElementP3Bounds.getY());

    }

    /**
     * Test that Execution e1 deletion is effective and :
     * <ol>
     * <li>call/return message are assigned to lifeline b as target/source with
     * same vertical range</li>
     * <li>execution e2 is reparanted to lifeline b with same range, same for
     * call/return message of e2</li>
     * <li>test undo/redo</li>
     * </ol>
     * 
     * @throws Exception
     * 
     */
    public void testExecutionDeletion1() throws Exception {
        // Delete e1
        ExecutionEditPart e1EditPart = (ExecutionEditPart) getEditPart(diagramElementE1);
        DestroyElementRequest destroyElementRequest = new DestroyElementRequest(e1, false);
        EditCommandRequestWrapper editCommandRequestWrapper = new EditCommandRequestWrapper(destroyElementRequest);
        e1EditPart.performRequest(editCommandRequestWrapper);
        TestsUtil.synchronizationWithUIThread();

        // BUG : exec e1 isn't deleted in the model
        // assertFalse(interaction.getExecutions().contains(e1));
        assertNull(getFirstEdgeElement(sequenceDDiagram, e1));
        checkBoundsAfterExecutionE1DeletionOfTestExecutionDeletion1();

        // test undo
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canUndo());
        undo();
        TestsUtil.synchronizationWithUIThread();

        updateInstanceRolesBounds();

        Thread.sleep(200);
        checkInitialBounds();

        // test redo
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canRedo());
        redo();

        Thread.sleep(200);
        checkBoundsAfterExecutionE1DeletionOfTestExecutionDeletion1();
    }

    private void checkBoundsAfterExecutionE1DeletionOfTestExecutionDeletion1() {
        Bounds newDiagramElementP1Bounds = getBounds(diagramElementP1, participantP1);
        Bounds newDiagramElementP2Bounds = getBounds(diagramElementP2, participantP2);
        Bounds newDiagramElementP3Bounds = getBounds(diagramElementP3, participantP3);

        assertEquals(origin.x, newDiagramElementP1Bounds.getX());
        assertEquals(origin.y, newDiagramElementP1Bounds.getY());

        assertEquals(diagramElementP1Bounds.getX() + diagramElementP1Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP + 20, newDiagramElementP2Bounds.getX());
        assertEquals(origin.y, newDiagramElementP2Bounds.getY());

        assertEquals(diagramElementP2Bounds.getX() + diagramElementP2Bounds.getWidth() + LayoutConstants.LIFELINES_MIN_X_GAP + 20, newDiagramElementP3Bounds.getX());
        assertEquals(origin.y, newDiagramElementP3Bounds.getY());
    }

    /**
     * Same as previous test testExecutionDeletion1 but after the undo test a
     * move on the reflexive syncCall's execution
     * 
     * @throws Exception
     */
    // TEMP : after a undo, a move of the reflexive syncCall's execution throw a
    // NPE.
    public void testExecutionDeletion1Bis() throws Exception {
        // Delete createP2Message
        ExecutionEditPart e1EditPart = (ExecutionEditPart) getEditPart(diagramElementE1);
        DestroyElementRequest destroyElementRequest = new DestroyElementRequest(e1, false);
        EditCommandRequestWrapper editCommandRequestWrapper = new EditCommandRequestWrapper(destroyElementRequest);
        e1EditPart.performRequest(editCommandRequestWrapper);

        // BUG : exec e1 isn't deleted in the model
        // assertFalse(interaction.getExecutions().contains(e1));
        assertNull(getFirstEdgeElement(sequenceDDiagram, e1));
        checkBoundsAfterExecutionE1DeletionOfTestExecutionDeletion1();

        // test undo
        assertTrue(session.getTransactionalEditingDomain().getCommandStack().canUndo());
        undo();

        // test move of the reflexive syncCall's execution
        // BUG : after a undo resize, move of related sequence event throw a NPE
        ExecutionEditPart e2EditPart = (ExecutionEditPart) getEditPart(diagramElementE2);
        ChangeBoundsRequest changeBoundsRequest = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
        changeBoundsRequest.setEditParts(e2EditPart);
        changeBoundsRequest.setMoveDelta(new Point(0, 10));
        e2EditPart.performRequest(changeBoundsRequest);

        Thread.sleep(300);
        checkBoundsAfterExecutionE1DeletionOfTestExecutionDeletion1();

    }

    @Override
    protected void tearDown() throws Exception {

        participantP1 = null;
        participantP2 = null;
        participantP3 = null;
        diagramElementP1 = null;
        diagramElementP2 = null;
        diagramElementP3 = null;
        diagramElementP1Bounds = null;
        diagramElementP2Bounds = null;
        diagramElementP3Bounds = null;
        e1 = null;
        e2 = null;
        diagramElementE1 = null;
        diagramElementE2 = null;
        callMessageOnE1 = null;
        reflexiveCallMessageOnE2 = null;
        reflexiveReturnMessageOnE1 = null;
        returnMessageOnParticipantA = null;
        callMessageOnE1Edge = null;
        reflexiveCallMessageOnE2Edge = null;
        reflexiveReturnMessageOnE1Edge = null;
        returnMessageOnParticipantAEdge = null;

        workspaceViewerPreferenceStore.setValue(WorkspaceViewerProperties.SNAPTOGRID, oldSnapMode);
        workspaceViewerPreferenceStore = null;

        super.tearDown();
    }

}
