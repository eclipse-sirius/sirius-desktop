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
package org.eclipse.sirius.tests.unit.api.mappings;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

/**
 * Tests for reuse mappings usage.
 * 
 * @author mchauvin
 */
public class MappingsReuseTests extends SiriusDiagramTestCase implements MappingsReuseTestModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DEFAULT_VIEWPOINT_NAME);
    }

    public void testReuseNodeMapping() throws Exception {
        diagram = (DDiagram) getRepresentations(REUSE_NODE_MAPPING_DESC_NAME).toArray()[0];
        refresh(diagram);
        assertEquals(1, getNumberOfElementsInDiagram());
        DNode node = (DNode) getFirstDiagramElement();
        assertEquals(COMMON_NODE_MAPPING_ON_ECLASS, node.getActualMapping().getName());
    }

    public void testReuseContainerNodeMapping() throws Exception {
        diagram = (DDiagram) getRepresentations(REUSE_CONTAINER_MAPPING_DESC_NAME).toArray()[0];
        refresh(diagram);
        assertEquals(1, getNumberOfElementsInDiagram());
        DNodeContainer container = (DNodeContainer) getFirstDiagramElement();
        assertEquals(COMMON_CONTAINER_MAPPING_ON_ECLASS, container.getMapping().getName());
    }

    public void testReuseNodeMappingInContainerMapping() throws Exception {
        diagram = (DDiagram) getRepresentations(REUSE_NODE_MAPPING_IN_CONTAINER_MAPPING_DESC_NAME).toArray()[0];
        refresh(diagram);
        assertEquals(2, getNumberOfElementsInDiagram());

        for (final DDiagramElement container : diagram.getOwnedDiagramElements()) {
            assertEquals(1, ((DNodeContainer) container).getNodes().size());
            assertEquals(COMMON_NODE_MAPPING_ON_ESTRUCTURAL_FEATURE, ((DNodeContainer) container).getNodes().get(0).getActualMapping().getName());
        }
    }

    public void testReuseBorderNodeMappingInContainerMapping() throws Exception {
        diagram = (DDiagram) createRepresentation(REUSE_BORDER_NODE_MAPPING_IN_CONTAINER_MAPPING_DESC_NAME);
        refresh(diagram);
        assertEquals(1, getNumberOfElementsInDiagram());

        DNodeContainer container = (DNodeContainer) getFirstDiagramElement();
        assertEquals(1, container.getOwnedBorderedNodes().size());
        assertEquals(COMMON_NODE_MAPPING_ON_ESTRUCTURAL_FEATURE, container.getOwnedBorderedNodes().get(0).getActualMapping().getName());
    }

    public void testReuseBorderNodeMappingInContainerMappingImport() throws Exception {
        diagram = (DDiagram) createRepresentation(REUSE_BORDER_NODE_MAPPING_IN_CONTAINER_MAPPING_IMPORT_DESC_NAME);
        refresh(diagram);

        assertEquals(1, getNumberOfElementsInDiagram());
        DNodeContainer container = (DNodeContainer) getFirstDiagramElement();
        assertEquals(1, container.getOwnedBorderedNodes().size());
        assertEquals(COMMON_NODE_MAPPING_ON_ESTRUCTURAL_FEATURE, container.getOwnedBorderedNodes().get(0).getActualMapping().getName());
        assertTrue(container.getOwnedBorderedNodes().get(0).isVisible());

        deactivateLayer(diagram, "import");
        refresh(diagram);

        assertEquals(1, getNumberOfElementsInDiagram());
        container = (DNodeContainer) getFirstDiagramElement();
        assertEquals(1, container.getOwnedBorderedNodes().size());
        assertFalse(container.getOwnedBorderedNodes().get(0).isVisible());

        activateLayer(diagram, "import");
        refresh(diagram);

        assertEquals(1, getNumberOfElementsInDiagram());
        container = (DNodeContainer) getFirstDiagramElement();
        assertEquals(1, container.getOwnedBorderedNodes().size());
        assertEquals(COMMON_NODE_MAPPING_ON_ESTRUCTURAL_FEATURE, container.getOwnedBorderedNodes().get(0).getActualMapping().getName());
        assertTrue(container.getOwnedBorderedNodes().get(0).isVisible());

    }

    private int getNumberOfElementsInDiagram() throws Exception {
        return diagram.getOwnedDiagramElements().size();
    }

    private DDiagramElement getFirstDiagramElement() throws Exception {
        return diagram.getOwnedDiagramElements().get(0);
    }
}
