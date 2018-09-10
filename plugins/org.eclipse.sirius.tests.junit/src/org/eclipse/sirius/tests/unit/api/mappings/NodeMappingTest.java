/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.mappings;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * Tests for NodeMapping usage.
 * 
 * @author lredor
 */
public class NodeMappingTest extends SiriusDiagramTestCase implements MappingsReuseTestModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
    }

    /**
     * Test getNodesCandidates() (avoid Stack overflow of #2121).
     * 
     * @throws Exception
     */
    public void testGetNodesCandidates() throws Exception {
        assertEquals("Bad number of Sirius", 1, viewpoints.size());
        assertEquals("Bad number of Representations", 6, viewpoints.iterator().next().getOwnedRepresentations().size());
        assertEquals("Bad name for the first representation", "Common", viewpoints.iterator().next().getOwnedRepresentations().get(0).getName());
        assertEquals("Bad name for the first representation", "Common", viewpoints.iterator().next().getOwnedRepresentations().get(0).getName());
        RepresentationDescription representationDesc = viewpoints.iterator().next().getOwnedRepresentations().get(0);
        assertTrue("Bad type for representation", representationDesc instanceof DiagramDescription);
        DiagramDescription diagDesc = (DiagramDescription) representationDesc;
        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        for (NodeMapping nodeMapping : ContentHelper.getAllNodeMappings(diagDesc, false)) {
            if (COMMON_NODE_MAPPING_ON_ECLASS.equals(nodeMapping.getName())) {
                EList<EObject> nodesCandidates = NodeMappingHelper.getNodesCandidates((INodeMappingExt) nodeMapping, semanticModel, semanticModel, diag);
                assertEquals("Bad number of nodes candidates for mapping \"" + COMMON_NODE_MAPPING_ON_ECLASS + "\"", 1, nodesCandidates.size());
            }
        }
    }
}
