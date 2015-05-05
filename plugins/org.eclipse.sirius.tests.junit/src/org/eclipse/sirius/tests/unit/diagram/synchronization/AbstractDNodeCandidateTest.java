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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.AbstractDNodeCandidate;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tests.support.api.EqualsHashCodeTestCase;

/**
 * Test class for {@link AbstractDNodeCandidate}.
 * 
 * @author dlecan
 */
public class AbstractDNodeCandidateTest extends EqualsHashCodeTestCase {

    private static final DiagramFactory DF = DiagramFactory.eINSTANCE;

    private static final DescriptionFactory DDF = DescriptionFactory.eINSTANCE;

    private DNodeContainer dNodeContainer = DF.createDNodeContainer();

    private NodeMapping nodeMapping = DDF.createNodeMapping();

    private RefreshIdsHolder ids = new RefreshIdsHolder();

    /**
     * Constructor.
     */
    public AbstractDNodeCandidateTest() {
        super();
        nodeMapping.setName("dfqsd");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createInstance() throws Exception {
        DNode dNode = DF.createDNode();

        dNode.setActualMapping(nodeMapping);
        dNodeContainer.getOwnedDiagramElements().add(dNode);

        return new AbstractDNodeCandidate(dNode, ids);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createNotEqualInstance() throws Exception {
        return new AbstractDNodeCandidate(null, null, null, ids);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoNPEWithOneNullSemanticElement() throws Exception {
        DNode node = DF.createDNode();

        node.setActualMapping(nodeMapping);
        dNodeContainer.getOwnedDiagramElements().add(node);
        node.setTarget(null);

        AbstractDNodeCandidate candidate1 = new AbstractDNodeCandidate(node, ids);

        node.setTarget(dNodeContainer);

        AbstractDNodeCandidate candidate2 = new AbstractDNodeCandidate(node, ids);

        assertFalse(candidate1.equals(candidate2));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testNoNPEWithOneNullView() throws Exception {
        DNode node = DF.createDNode();

        node.setActualMapping(nodeMapping);
        node.setTarget(dNodeContainer);

        AbstractDNodeCandidate candidate1 = new AbstractDNodeCandidate(node, ids);

        dNodeContainer.getOwnedDiagramElements().add(node);

        AbstractDNodeCandidate candidate2 = new AbstractDNodeCandidate(node, ids);

        assertFalse(candidate1.equals(candidate2));
    }

}
