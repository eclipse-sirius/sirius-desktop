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

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

import com.google.common.collect.Iterables;

public class SiriusSequenceDiagramTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT;

    private static final String typesSemanticModel = "simple.ecore";

    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getSemanticModel() {
        return "simple.interactions";
    }

    protected String getTypesSemanticModel() {
        return typesSemanticModel;
    }

    protected String getSessionModel() {
        return null;
    }

    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createSequenceDiagramOfType(InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_LABEL);
    }

    public void testParticipantCreationToolCreatesOneSemanticElement() {
        assertEquals("The interaction should be initially empty.", 0, interaction.getParticipants().size());
        assertTrue(applyNodeCreationTool("Participant", sequenceDDiagram, sequenceDDiagram));
        assertEquals("The 'Participant' tool should have created a Participant", 1, interaction.getParticipants().size());
    }

    public void testParticipantCreationToolCreatesInstanceRoleAndLifeline() {
        assertEquals("The diagram should be initially empty.", 0, sequenceDDiagram.getDiagramElements().size());
        assertTrue(applyNodeCreationTool("Participant", sequenceDDiagram, sequenceDDiagram));
        assertEquals("The 'Participant' tool should have created two diagram elements", 3, sequenceDDiagram.getDiagramElements().size());
        // Check the instance role.
        Iterable<DDiagramElement> elements = Iterables.filter(sequenceDDiagram.getDiagramElements(), InstanceRole.viewpointElementPredicate());
        assertEquals("There should be exactly one instance role.", 1, Iterables.size(elements));
        assertTrue("The instance role should be a DNode.", Iterables.getOnlyElement(elements) instanceof DNode);
        DNode instanceRole = (DNode) Iterables.getOnlyElement(elements);
        // Check the lifeline
        elements = Iterables.filter(sequenceDDiagram.getDiagramElements(), Lifeline.viewpointElementPredicate());
        assertEquals("There should be exactly one lifeline.", 1, Iterables.size(elements));
        assertTrue("The lifeline should be a DNode.", Iterables.getOnlyElement(elements) instanceof DNode);
        DNode lifeline = (DNode) Iterables.getOnlyElement(elements);
        // Check their link to the semantic model
        assertSame("The instance role and lifeline should have the same semantic element.", instanceRole.getTarget(), lifeline.getTarget());
        assertEquals("There should be exactly one Participant", 1, interaction.getParticipants().size());
        Participant participant = interaction.getParticipants().get(0);
        assertSame("The semantic target of the instance role should be the single participant.", participant, instanceRole.getTarget());
    }

}
