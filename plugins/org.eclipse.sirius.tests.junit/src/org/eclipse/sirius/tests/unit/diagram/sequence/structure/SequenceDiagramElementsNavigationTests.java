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
package org.eclipse.sirius.tests.unit.diagram.sequence.structure;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.LostMessageEnd;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ObservationPoint;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.sequence.AbstractSequenceSiriusDiagramTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.junit.Assert;

/**
 * Test for the various basic identification and structural navigation methods between sequence diagram elements.
 * 
 * @author pcdavid
 */
public class SequenceDiagramElementsNavigationTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "navigation/";

    private static final String BASIC_MESSAGES_DIAGRAM = "Basic Messages Diagram";

    private static final String BASIC_EXECUTIONS_DIAGRAM = "Basic Executions Diagram";

    private static final String LOST_MESSAGE_END_DIAGRAM = "Basic Lost Message End Diagram";

    private static final String OBSERVATION_DIAGRAM = "Basic Observation Diagram";

    private static final String COMPLEX_DIAGRAM = "Complex";

    private static final String REPRESENTATION_TYPE = InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL;

    private static final String sessionModel = "fixture.aird";

    private static final String typesSemanticModel = "types.ecore";

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

    public void test_navigation_diagram_to_lifelines() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_MESSAGES_DIAGRAM, REPRESENTATION_TYPE);
        List<Lifeline> lifelines = sequenceDiagram.get().getAllLifelines();
        Assert.assertEquals(4, lifelines.size());
        for (String name : Arrays.asList("a", "b", "c", "d")) {
            Assert.assertTrue(getLifelineByName(lifelines, name).some());
        }
    }

    public void test_find_all_messages_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_MESSAGES_DIAGRAM, REPRESENTATION_TYPE);
        Set<Message> messages = sequenceDiagram.get().getAllMessages();
        Assert.assertNotNull(messages);
        Assert.assertEquals(7, messages.size());
        for (String name : Arrays.asList("m1", "m2", "m3", "m_create4", "m5", "m_destroy6", "m7")) {
            Assert.assertTrue(getMessageByName(messages, name).some());
        }
    }

    public void test_find_all_executions_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_EXECUTIONS_DIAGRAM, REPRESENTATION_TYPE);
        Set<Execution> executions = sequenceDiagram.get().getAllExecutions();
        Assert.assertNotNull(executions);
        Assert.assertEquals(11, executions.size());
        for (int i = 1; i <= 11; i++) {
            Assert.assertTrue(getExecutionByName(executions, "e" + i).some());
        }
    }

    public void test_find_all_states_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_EXECUTIONS_DIAGRAM, REPRESENTATION_TYPE);
        Set<State> states = sequenceDiagram.get().getAllStates();
        Assert.assertNotNull(states);
        Assert.assertEquals(2, states.size());
    }

    public void test_find_all_lost_messages_end_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(LOST_MESSAGE_END_DIAGRAM, REPRESENTATION_TYPE);
        Collection<LostMessageEnd> lme = sequenceDiagram.get().getAllLostMessageEnds();
        Assert.assertNotNull(lme);
        Assert.assertEquals(10, lme.size());
    }

    public void test_find_all_observation_point_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(OBSERVATION_DIAGRAM, REPRESENTATION_TYPE);
        Collection<ObservationPoint> obsPoints = sequenceDiagram.get().getAllObservationPoints();
        Assert.assertNotNull(obsPoints);
        Assert.assertEquals(22, obsPoints.size());
    }

    public void test_non_reflective_synchronous_message() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());
        Option<Execution> e1 = getExecutionByName(sequenceDiagram.get(), "e1");
        Assert.assertTrue(e1.some());
        // Test bounds and range
        // Assert.assertEquals(new Rectangle(270, 130, 20, 310),
        // e1.get().getProperLogicalBounds());
        Assert.assertEquals(new Range(130, 130 + 310), e1.get().getVerticalRange());
        Assert.assertEquals(e1.get().getVerticalRange(), e1.get().getExtendedVerticalRange());
        // Test structural properties
        Assert.assertFalse(e1.get().isReflective());
        // Test linked messages
        Assert.assertEquals(2, e1.get().getLinkedMessages().size());
        Assert.assertTrue(e1.get().getStartMessage().some());
        Assert.assertEquals(getMessageByName(sequenceDiagram.get(), "m1").get(), e1.get().getStartMessage().get());
        Assert.assertTrue(e1.get().getEndMessage().some());
        Assert.assertEquals(getMessageByName(sequenceDiagram.get(), "m_return2").get(), e1.get().getEndMessage().get());
        Assert.assertFalse(e1.get().startsWithReflectiveMessage());
        Assert.assertFalse(e1.get().endsWithReflectiveMessage());
        Assert.assertFalse(e1.get().isReflective());
        // Test parent
        Assert.assertEquals(getLifelineByName(sequenceDiagram.get(), "b").get(), e1.get().getLifeline().get());
        Assert.assertEquals(getLifelineByName(sequenceDiagram.get(), "b").get(), e1.get().getParentEvent());
        // Test children
        List<ISequenceEvent> subEvents = e1.get().getSubEvents();
        Assert.assertEquals(1, subEvents.size());
        Assert.assertEquals(getExecutionByName(sequenceDiagram.get(), "e2").get(), subEvents.get(0));
    }

    public void _test_reflective_synchronous_message() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());
        Option<Execution> e8 = getExecutionByName(sequenceDiagram.get(), "e8");
        Assert.assertTrue(e8.some());
        // Test bounds and range
        // Assert.assertEquals(new Rectangle(455, 580, 20, 90),
        // e8.get().getProperLogicalBounds());
        Assert.assertEquals(new Range(580, 580 + 90), e8.get().getVerticalRange());
        Assert.assertTrue(e8.get().getExtendedVerticalRange().includes(e8.get().getVerticalRange()));
        Assert.assertFalse(e8.get().getExtendedVerticalRange().equals(e8.get().getVerticalRange()));
        // Test structural properties
        Assert.assertTrue(e8.get().isReflective());
        // Test linked messages
        Assert.assertEquals(2, e8.get().getLinkedMessages().size());
        Assert.assertTrue(e8.get().getStartMessage().some());
        Assert.assertEquals(getMessageByName(sequenceDiagram.get(), "m16").get(), e8.get().getStartMessage().get());
        Assert.assertTrue(e8.get().getEndMessage().some());
        Assert.assertEquals(getMessageByName(sequenceDiagram.get(), "m_return17").get(), e8.get().getEndMessage().get());
        Assert.assertTrue(e8.get().startsWithReflectiveMessage());
        Assert.assertTrue(e8.get().endsWithReflectiveMessage());
        Assert.assertTrue(e8.get().isReflective());
        // Test parent
        Assert.assertEquals(getLifelineByName(sequenceDiagram.get(), "c").get(), e8.get().getLifeline().get());
        Assert.assertEquals(getExecutionByName(sequenceDiagram.get(), "e6").get(), e8.get().getParentEvent());
        // Test children
        List<ISequenceEvent> subEvents = e8.get().getSubEvents();
        Assert.assertEquals(2, subEvents.size());
        Assert.assertTrue(subEvents.contains(getMessageByName(sequenceDiagram.get(), "m18").get()));
        Assert.assertTrue(subEvents.contains(getMessageByName(sequenceDiagram.get(), "m_return19").get()));
    }
}
