/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.internal.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.tests.support.api.EqualsHashCodeTestCase;

/**
 * Test class for {@link DEdgeCandidate}
 * 
 * @author dlecan
 */
public class DEdgeCandidateTest extends EqualsHashCodeTestCase {

    private static final DiagramFactory DF = DiagramFactory.eINSTANCE;

    private static final DescriptionFactory DDF = DescriptionFactory.eINSTANCE;

    private EdgeMapping edgeMapping = DDF.createEdgeMapping();

    private DNode sourceEdgeTarget = DF.createDNode();

    private DNodeContainer targetEdgeTarget = DF.createDNodeContainer();

    private DDiagram dDiagram = DF.createDDiagram();

    private RefreshIdsHolder ids = new RefreshIdsHolder();

    public DEdgeCandidateTest() {
        super();
        edgeMapping.setName("dfgsdf");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createInstance() throws Exception {
        return createDEgedCandidateInstance();
    }

    private Object createDEgedCandidateInstance() {
        DEdge dEdge = DF.createDEdge();

        dEdge.setActualMapping(edgeMapping);
        dEdge.setSourceNode(sourceEdgeTarget);
        dEdge.setTargetNode(targetEdgeTarget);

        return new DEdgeCandidate(dEdge, ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new DEdgeCandidate(null, null, null, null, ids);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testIsInvalid1() throws Exception {
        DEdge dEdge = DF.createDEdge();

        dEdge.setActualMapping(edgeMapping);
        dEdge.setSourceNode(sourceEdgeTarget);
        dEdge.setTargetNode(targetEdgeTarget);

        DEdgeCandidate instance = new DEdgeCandidate(dEdge, ids);
        assertTrue(instance.isInvalid());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testIsInvalid2() throws Exception {
        DEdge dEdge = DF.createDEdge();

        dEdge.setActualMapping(edgeMapping);
        dEdge.setSourceNode(sourceEdgeTarget);
        dEdge.setTargetNode(targetEdgeTarget);

        DEdgeCandidate instance = new DEdgeCandidate(dEdge, ids);

        dDiagram.getOwnedDiagramElements().add(sourceEdgeTarget);
        assertTrue(instance.isInvalid());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testIsInvalid3() throws Exception {
        DEdge dEdge = DF.createDEdge();

        dEdge.setActualMapping(edgeMapping);
        dEdge.setSourceNode(sourceEdgeTarget);
        dEdge.setTargetNode(targetEdgeTarget);

        DEdgeCandidate instance = new DEdgeCandidate(dEdge, ids);

        dDiagram.getOwnedDiagramElements().add(targetEdgeTarget);
        assertTrue(instance.isInvalid());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testIsNotInvalid() throws Exception {
        DEdge dEdge = DF.createDEdge();

        dEdge.setActualMapping(edgeMapping);
        dEdge.setSourceNode(sourceEdgeTarget);
        dEdge.setTargetNode(targetEdgeTarget);

        DEdgeCandidate instance = new DEdgeCandidate(dEdge, ids);

        dDiagram.getOwnedDiagramElements().add(sourceEdgeTarget);
        dDiagram.getOwnedDiagramElements().add(targetEdgeTarget);
        assertFalse(instance.isInvalid());
    }

}
