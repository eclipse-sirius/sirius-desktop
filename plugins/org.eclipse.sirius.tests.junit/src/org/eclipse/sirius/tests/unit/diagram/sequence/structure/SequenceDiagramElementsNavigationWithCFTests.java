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
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.unit.diagram.sequence.AbstractSequenceSiriusDiagramTests;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.junit.Assert;

import com.google.common.collect.Lists;

/**
 * Test for the various basic identification and structural navigation methods
 * between sequence diagram elements.
 * 
 * @author pcdavid
 */
public class SequenceDiagramElementsNavigationWithCFTests extends AbstractSequenceSiriusDiagramTests {

    private static final String PATH = SiriusTestsPlugin.PLUGIN_ID + UNIT_DATA_ROOT + "navigation/";

    private static final String BASIC_INTERACTION_USE_DIAGRAM = "Basic Interaction Use Diagram";

    private static final String BASIC_COMBINED_FRAGMENT_DIAGRAM = "Basic Combined Fragment Diagram";

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

    public void test_find_all_interaction_uses_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_INTERACTION_USE_DIAGRAM, REPRESENTATION_TYPE);
        List<InteractionUse> ius = Lists.newArrayList(sequenceDiagram.get().getAllInteractionUses());
        Assert.assertNotNull(ius);
        Assert.assertEquals(2, ius.size());
        for (int i = 1; i <= 2; i++) {
            Assert.assertTrue(getInteractionUseByName(ius, "ref" + i).some());
            List<Lifeline> coveredLifelines = Lists.newArrayList(ius.get(i - 1).computeCoveredLifelines());
            Assert.assertTrue(coveredLifelines.size() == 1);
            Assert.assertEquals("p" + i, getLifelineSemanticName(coveredLifelines.get(0)));
        }
    }

    public void test_find_all_combined_fragments_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_COMBINED_FRAGMENT_DIAGRAM, REPRESENTATION_TYPE);
        List<CombinedFragment> cfs = Lists.newArrayList(sequenceDiagram.get().getAllCombinedFragments());
        Assert.assertNotNull(cfs);
        Assert.assertEquals(2, cfs.size());
        for (int i = 1; i <= 2; i++) {
            Assert.assertTrue(getCombinedFragmentByName(cfs, "opt" + i).some());
            CombinedFragment combinedFragment = cfs.get(i - 1);
            Assert.assertEquals("op" + i, getOperandSemanticName(combinedFragment.getOperands().get(0)));
            List<Lifeline> coveredLifelines = Lists.newArrayList(combinedFragment.computeCoveredLifelines());
            Assert.assertTrue(coveredLifelines.size() == 1);
            Assert.assertEquals("p" + i, getLifelineSemanticName(coveredLifelines.get(0)));
        }
    }

    public void test_find_all_operands_on_diagram() {
        Option<SequenceDiagram> sequenceDiagram = openSequenceDiagramOfType(BASIC_COMBINED_FRAGMENT_DIAGRAM, REPRESENTATION_TYPE);
        List<Operand> ops = Lists.newArrayList(sequenceDiagram.get().getAllOperands());
        Assert.assertNotNull(ops);
        Assert.assertEquals(2, ops.size());
        for (int i = 1; i <= 2; i++) {
            Assert.assertTrue(getOperandByName(ops, "op" + i).some());
            Assert.assertEquals("opt" + i, getCombinedFragmentSemanticName(ops.get(i - 1).getCombinedFragment()));
        }
    }
}
