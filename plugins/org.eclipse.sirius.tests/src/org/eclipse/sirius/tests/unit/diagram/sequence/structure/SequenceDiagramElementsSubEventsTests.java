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

import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.sequence.AbstractSequenceSiriusDiagramTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.junit.Assert;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test for the various basic identification and structural navigation methods
 * between sequence diagram elements.
 * 
 * @author pcdavid
 */
public class SequenceDiagramElementsSubEventsTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "navigation/";

    private static final String COMPLEX_DIAGRAM = "Complex with CF";

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

    public void test_sub_events_lifeline() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());

        Option<Lifeline> p1 = getLifelineByName(sequenceDiagram.get(), "p1");
        Assert.assertTrue(p1.some());
        checkSubEvents(p1.get(), 5, 2, 1, 1, 0, 1, 0);

        Option<Lifeline> p2 = getLifelineByName(sequenceDiagram.get(), "p2");
        Assert.assertTrue(p2.some());
        checkSubEvents(p2.get(), 6, 2, 1, 1, 0, 1, 1);
    }

    public void test_sub_events_execution() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());

        Option<Execution> e1 = getExecutionByName(sequenceDiagram.get(), "e1");
        Assert.assertTrue(e1.some());
        checkSubEvents(e1.get(), 1, 0, 1, 0, 0, 0, 0);

        Option<Execution> e2 = getExecutionByName(sequenceDiagram.get(), "e2");
        Assert.assertTrue(e2.some());
        checkSubEvents(e2.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<Execution> e3 = getExecutionByName(sequenceDiagram.get(), "e3");
        Assert.assertTrue(e3.some());
        checkSubEvents(e3.get(), 1, 0, 0, 1, 0, 0, 0);

        Option<Execution> e4 = getExecutionByName(sequenceDiagram.get(), "e4");
        Assert.assertTrue(e4.some());
        checkSubEvents(e4.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<Execution> e5 = getExecutionByName(sequenceDiagram.get(), "e5");
        Assert.assertTrue(e5.some());
        checkSubEvents(e5.get(), 1, 0, 0, 1, 0, 0, 0);

        Option<Execution> e6 = getExecutionByName(sequenceDiagram.get(), "e6");
        Assert.assertTrue(e6.some());
        checkSubEvents(e6.get(), 1, 0, 0, 0, 0, 0, 1);

        Option<Execution> e7 = getExecutionByName(sequenceDiagram.get(), "e7");
        Assert.assertTrue(e7.some());
        checkSubEvents(e7.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<Execution> e8 = getExecutionByName(sequenceDiagram.get(), "e8");
        Assert.assertTrue(e8.some());
        checkSubEvents(e8.get(), 1, 0, 0, 0, 0, 1, 0);

        Option<Execution> e9 = getExecutionByName(sequenceDiagram.get(), "e9");
        Assert.assertTrue(e9.some());
        checkSubEvents(e9.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<Execution> e10 = getExecutionByName(sequenceDiagram.get(), "e10");
        Assert.assertTrue(e10.some());
        checkSubEvents(e10.get(), 1, 0, 0, 0, 0, 0, 1);

    }

    public void test_sub_events_state() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());

        Option<State> s1 = getStateByName(sequenceDiagram.get(), "s1");
        Assert.assertTrue(s1.some());
        checkSubEvents(s1.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<State> s2 = getStateByName(sequenceDiagram.get(), "s2");
        Assert.assertTrue(s2.some());
        checkSubEvents(s2.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<State> s3 = getStateByName(sequenceDiagram.get(), "s3");
        Assert.assertTrue(s3.some());
        checkSubEvents(s3.get(), 0, 0, 0, 0, 0, 0, 0);
    }

    public void test_sub_events_message() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());

        Option<Message> m1 = getMessageByName(sequenceDiagram.get(), "m1");
        Assert.assertTrue(m1.some());
        checkSubEvents(m1.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<Message> m2 = getMessageByName(sequenceDiagram.get(), "m2");
        Assert.assertTrue(m2.some());
        checkSubEvents(m2.get(), 0, 0, 0, 0, 0, 0, 0);
    }

    public void test_sub_events_interaction_use() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());

        Option<InteractionUse> iu1 = getInteractionUseByName(sequenceDiagram.get(), "ref1");
        Assert.assertTrue(iu1.some());
        checkSubEvents(iu1.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<InteractionUse> iu2 = getInteractionUseByName(sequenceDiagram.get(), "ref2");
        Assert.assertTrue(iu2.some());
        checkSubEvents(iu2.get(), 0, 0, 0, 0, 0, 0, 0);

        Option<InteractionUse> iu3 = getInteractionUseByName(sequenceDiagram.get(), "ref.3");
        Assert.assertTrue(iu3.some());
        checkSubEvents(iu3.get(), 0, 0, 0, 0, 0, 0, 0);
    }

    public void test_sub_events_combined_fragment() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());

        Option<CombinedFragment> cf1 = getCombinedFragmentByName(sequenceDiagram.get(), "opt1");
        Assert.assertTrue(cf1.some());
        checkSubEvents(cf1.get(), 2, 0, 0, 0, 2, 0, 0);

        Option<CombinedFragment> cf2 = getCombinedFragmentByName(sequenceDiagram.get(), "opt2");
        Assert.assertTrue(cf2.some());
        checkSubEvents(cf2.get(), 1, 0, 0, 0, 1, 0, 0);
    }

    public void test_sub_events_operand() throws Exception {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(COMPLEX_DIAGRAM, REPRESENTATION_TYPE);
        Assert.assertTrue(sequenceDiagram.some());

        Option<Operand> op1 = getOperandByName(sequenceDiagram.get(), "op1");
        Assert.assertTrue(op1.some());
        checkSubEvents(op1.get(), 1, 0, 0, 0, 0, 1, 0);

        Option<Operand> op3 = getOperandByName(sequenceDiagram.get(), "op3");
        Assert.assertTrue(op3.some());
        checkSubEvents(op3.get(), 1, 0, 0, 1, 0, 0, 0);

        Option<Operand> op4 = getOperandByName(sequenceDiagram.get(), "op4");
        Assert.assertTrue(op4.some());
        checkSubEvents(op4.get(), 1, 1, 0, 0, 0, 0, 0);

        Option<Operand> op2 = getOperandByName(sequenceDiagram.get(), "op2");
        Assert.assertTrue(op2.some());
        checkSubEvents(op2.get(), 5, 2, 1, 2, 0, 0, 0);

        Option<Operand> op5 = getOperandByName(sequenceDiagram.get(), "op5");
        Assert.assertTrue(op5.some());
        checkSubEvents(op5.get(), 1, 1, 0, 0, 0, 0, 0);

        Option<Operand> op6 = getOperandByName(sequenceDiagram.get(), "op6");
        Assert.assertTrue(op6.some());
        checkSubEvents(op6.get(), 4, 2, 0, 0, 0, 2, 0);
    }

    private void checkSubEvents(ISequenceEvent ise, int subs, int execs, int ius, int cfs, int ops, int msgs, int states) {
        // Validate parameters
        Assert.assertEquals("Wrong parameters : subs should be the sum of the other numbers.", subs, execs + cfs + ius + msgs + ops + states);

        List<ISequenceEvent> subEvents = ise.getSubEvents();
        List<InteractionUse> subIUs = Lists.newArrayList(Iterables.filter(subEvents, InteractionUse.class));
        List<CombinedFragment> subCFs = Lists.newArrayList(Iterables.filter(subEvents, CombinedFragment.class));
        List<Execution> subExecs = Lists.newArrayList(Iterables.filter(subEvents, Execution.class));
        List<Message> subMsgs = Lists.newArrayList(Iterables.filter(subEvents, Message.class));
        List<Operand> subOps = Lists.newArrayList(Iterables.filter(subEvents, Operand.class));
        List<State> subStates = Lists.newArrayList(Iterables.filter(subEvents, State.class));

        Assert.assertEquals(subs, subEvents.size());
        Assert.assertEquals(execs, subExecs.size());
        Assert.assertEquals(ius, subIUs.size());
        Assert.assertEquals(cfs, subCFs.size());
        Assert.assertEquals(ops, subOps.size());
        Assert.assertEquals(msgs, subMsgs.size());
        Assert.assertEquals(states, subStates.size());

    }
}
