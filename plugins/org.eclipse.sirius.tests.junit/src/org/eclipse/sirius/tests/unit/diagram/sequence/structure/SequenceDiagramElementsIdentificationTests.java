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
package org.eclipse.sirius.tests.unit.diagram.sequence.structure;

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ObservationPoint;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.sequence.AbstractSequenceSiriusDiagramTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test for the various basic identification and structural navigation methods
 * between sequence diagram elements.
 * 
 * @author pcdavid
 */
public class SequenceDiagramElementsIdentificationTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "navigation/";

    private static final String BASIC_MESSAGES_DIAGRAM = "Basic Messages Diagram";

    private static final String BASIC_EXECUTIONS_DIAGRAM = "Basic Executions Diagram";

    private static final String sessionModel = "fixture.aird";

    private static final String typesSemanticModel = "types.ecore";

    private static final String BASIC_INTERACTION_USE_DIAGRAM = "Basic Interaction Use Diagram";

    private static final String BASIC_COMBINED_FRAGMENT_DIAGRAM = "Basic Combined Fragment Diagram";

    private static final String LOST_MESSAGE_END_DIAGRAM = "Basic Lost Message End Diagram";

    private static final String OBSERVATION_DIAGRAM = "Basic Observation Diagram";

    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return "fixture.interactions";
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

    private View getView(Predicate<DDiagramElement> viewpointElementPredicate) {
        List<DDiagramElement> elements = Lists.newArrayList(Iterables.filter(sequenceDDiagram.getDiagramElements(), viewpointElementPredicate));
        assertFalse(elements.isEmpty());
        IGraphicalEditPart editPart = getEditPart(elements.get(0));
        assertNotNull(editPart);
        return editPart.getNotationView();
    }

    /**
     * Check that we can correctly identify a sequence diagram from the GMF
     * View.
     */
    public void test_diagram_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_MESSAGES_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());
        assertTrue(ISequenceElementAccessor.getSequenceDiagram(diagramView).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(diagramView).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(diagramView).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(diagramView).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(diagramView).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(diagramView).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(diagramView).some());
        assertFalse(ISequenceElementAccessor.getISequenceEvent(diagramView).some());
        assertFalse(ISequenceElementAccessor.getLifeline(diagramView).some());
        assertFalse(ISequenceElementAccessor.getMessage(diagramView).some());
        assertFalse(ISequenceElementAccessor.getOperand(diagramView).some());
        assertFalse(ISequenceElementAccessor.getExecution(diagramView).some());
        assertFalse(ISequenceElementAccessor.getState(diagramView).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(diagramView).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(diagramView).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_instancerole_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_MESSAGES_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View irView = getView(InstanceRole.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getInstanceRole(irView).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(irView).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(irView).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(irView).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(irView).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(irView).some());
        assertFalse(ISequenceElementAccessor.getISequenceEvent(irView).some());
        assertFalse(ISequenceElementAccessor.getLifeline(irView).some());
        assertFalse(ISequenceElementAccessor.getMessage(irView).some());
        assertFalse(ISequenceElementAccessor.getOperand(irView).some());
        assertFalse(ISequenceElementAccessor.getExecution(irView).some());
        assertFalse(ISequenceElementAccessor.getState(irView).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(irView).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(irView).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_lifeline_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_MESSAGES_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View lifeline = getView(Lifeline.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getISequenceEvent(lifeline).some());
        assertTrue(ISequenceElementAccessor.getLifeline(lifeline).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(lifeline).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(lifeline).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(lifeline).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(lifeline).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(lifeline).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(lifeline).some());
        assertFalse(ISequenceElementAccessor.getMessage(lifeline).some());
        assertFalse(ISequenceElementAccessor.getOperand(lifeline).some());
        assertFalse(ISequenceElementAccessor.getExecution(lifeline).some());
        assertFalse(ISequenceElementAccessor.getState(lifeline).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(lifeline).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(lifeline).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_execution_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_EXECUTIONS_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View execution = getView(Execution.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getExecution(execution).some());
        assertTrue(ISequenceElementAccessor.getAbstractNodeEvent(execution).some());
        assertTrue(ISequenceElementAccessor.getISequenceEvent(execution).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(execution).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(execution).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(execution).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(execution).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(execution).some());
        assertFalse(ISequenceElementAccessor.getLifeline(execution).some());
        assertFalse(ISequenceElementAccessor.getMessage(execution).some());
        assertFalse(ISequenceElementAccessor.getOperand(execution).some());
        assertFalse(ISequenceElementAccessor.getState(execution).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(execution).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(execution).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_state_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_EXECUTIONS_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View state = getView(State.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getState(state).some());
        assertTrue(ISequenceElementAccessor.getAbstractNodeEvent(state).some());
        assertTrue(ISequenceElementAccessor.getISequenceEvent(state).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(state).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(state).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(state).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(state).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(state).some());
        assertFalse(ISequenceElementAccessor.getLifeline(state).some());
        assertFalse(ISequenceElementAccessor.getMessage(state).some());
        assertFalse(ISequenceElementAccessor.getOperand(state).some());
        assertFalse(ISequenceElementAccessor.getExecution(state).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(state).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(state).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_message_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_MESSAGES_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View message = getView(Message.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getMessage(message).some());
        assertTrue(ISequenceElementAccessor.getISequenceEvent(message).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(message).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(message).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(message).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(message).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(message).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(message).some());
        assertFalse(ISequenceElementAccessor.getLifeline(message).some());
        assertFalse(ISequenceElementAccessor.getOperand(message).some());
        assertFalse(ISequenceElementAccessor.getExecution(message).some());
        assertFalse(ISequenceElementAccessor.getState(message).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(message).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(message).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_end_of_life_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_MESSAGES_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View eol = getView(EndOfLife.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getEndOfLife(eol).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(eol).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(eol).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(eol).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(eol).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(eol).some());
        assertFalse(ISequenceElementAccessor.getISequenceEvent(eol).some());
        assertFalse(ISequenceElementAccessor.getLifeline(eol).some());
        assertFalse(ISequenceElementAccessor.getMessage(eol).some());
        assertFalse(ISequenceElementAccessor.getOperand(eol).some());
        assertFalse(ISequenceElementAccessor.getExecution(eol).some());
        assertFalse(ISequenceElementAccessor.getState(eol).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(eol).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(eol).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_interaction_use_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_INTERACTION_USE_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View iu = getView(InteractionUse.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getInteractionUse(iu).some());
        assertTrue(ISequenceElementAccessor.getISequenceEvent(iu).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(iu).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(iu).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(iu).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(iu).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(iu).some());
        assertFalse(ISequenceElementAccessor.getMessage(iu).some());
        assertFalse(ISequenceElementAccessor.getLifeline(iu).some());
        assertFalse(ISequenceElementAccessor.getOperand(iu).some());
        assertFalse(ISequenceElementAccessor.getExecution(iu).some());
        assertFalse(ISequenceElementAccessor.getState(iu).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(iu).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(iu).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_combined_fragment_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_COMBINED_FRAGMENT_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View cf = getView(CombinedFragment.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getCombinedFragment(cf).some());
        assertTrue(ISequenceElementAccessor.getISequenceEvent(cf).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(cf).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getMessage(cf).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(cf).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(cf).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(cf).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(cf).some());
        assertFalse(ISequenceElementAccessor.getLifeline(cf).some());
        assertFalse(ISequenceElementAccessor.getOperand(cf).some());
        assertFalse(ISequenceElementAccessor.getExecution(cf).some());
        assertFalse(ISequenceElementAccessor.getState(cf).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(cf).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(cf).some());

    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_operand_fragment_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_COMBINED_FRAGMENT_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View op = getView(Operand.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getOperand(op).some());
        assertTrue(ISequenceElementAccessor.getISequenceEvent(op).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(op).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getCombinedFragment(op).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(op).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(op).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(op).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(op).some());
        assertFalse(ISequenceElementAccessor.getLifeline(op).some());
        assertFalse(ISequenceElementAccessor.getMessage(op).some());
        assertFalse(ISequenceElementAccessor.getExecution(op).some());
        assertFalse(ISequenceElementAccessor.getState(op).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(op).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(op).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_lost_message_end_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(LOST_MESSAGE_END_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View le = getView(LostMessageEnd.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getLostMessageEnd(le).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(le).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getOperand(le).some());
        assertFalse(ISequenceElementAccessor.getISequenceEvent(le).some());
        assertFalse(ISequenceElementAccessor.getCombinedFragment(le).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(le).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(le).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(le).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(le).some());
        assertFalse(ISequenceElementAccessor.getLifeline(le).some());
        assertFalse(ISequenceElementAccessor.getMessage(le).some());
        assertFalse(ISequenceElementAccessor.getExecution(le).some());
        assertFalse(ISequenceElementAccessor.getState(le).some());
        assertFalse(ISequenceElementAccessor.getObservationPoint(le).some());
    }

    /**
     * Check that we can correctly identify a sequence element from the GMF
     * View.
     */
    public void test_observation_point_identification() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(OBSERVATION_DIAGRAM, InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
        assertNotNull(sequenceDiagram);
        assertTrue(sequenceDiagram.some());

        View le = getView(ObservationPoint.viewpointElementPredicate());

        assertTrue(ISequenceElementAccessor.getObservationPoint(le).some());
        assertTrue(ISequenceElementAccessor.getISequenceElement(le).some());
        // Also check we don't get any false positive for other element kinds.
        assertFalse(ISequenceElementAccessor.getOperand(le).some());
        assertFalse(ISequenceElementAccessor.getISequenceEvent(le).some());
        assertFalse(ISequenceElementAccessor.getCombinedFragment(le).some());
        assertFalse(ISequenceElementAccessor.getEndOfLife(le).some());
        assertFalse(ISequenceElementAccessor.getAbstractNodeEvent(le).some());
        assertFalse(ISequenceElementAccessor.getInstanceRole(le).some());
        assertFalse(ISequenceElementAccessor.getInteractionUse(le).some());
        assertFalse(ISequenceElementAccessor.getLifeline(le).some());
        assertFalse(ISequenceElementAccessor.getMessage(le).some());
        assertFalse(ISequenceElementAccessor.getExecution(le).some());
        assertFalse(ISequenceElementAccessor.getState(le).some());
        assertFalse(ISequenceElementAccessor.getLostMessageEnd(le).some());
    }
}
