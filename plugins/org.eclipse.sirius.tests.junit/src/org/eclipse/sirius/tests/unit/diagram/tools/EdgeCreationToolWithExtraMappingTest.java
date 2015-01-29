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
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;

import com.google.common.collect.Iterables;

public class EdgeCreationToolWithExtraMappingTest extends SiriusDiagramTestCase {

    protected static final String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/applicability/test.ecore";

    protected static final String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/applicability/toolApplicability.odesign";

    protected static final String VIEWPOINT_NAME = "Test for tool applicability with extra mappings";

    protected DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();	
        genericSetUp(MODEL_PATH, VSM_PATH);
        initViewpoint(VIEWPOINT_NAME);
    }

    public void test_edge_creation_tool_with_extra_mapping_creates_only_one_edge() throws Exception {
        diagram = (DDiagram) createRepresentation("TestDiagram", semanticModel);
        assertNotNull(diagram);
        refresh(diagram);

        assertTrue(Iterables.isEmpty(findAllDEdges()));

        EClassifier a = ((EPackage) semanticModel).getEClassifier("A");
        assertNotNull(a);
        EPackage p2 = ((EPackage) semanticModel).getESubpackages().get(0);
        assertNotNull(p2);

        List<DDiagramElement> sources = getDiagramElementsFromLabel(diagram, "A");
        assertEquals(1, sources.size());
        DDiagramElement source = sources.get(0);
        assertSame(a, source.getTarget());

        List<DDiagramElement> targets = getDiagramElementsFromLabel(diagram, "p2");
        assertEquals(1, targets.size());
        DDiagramElement target = targets.get(0);
        assertSame(p2, target.getTarget());

        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) source, (EdgeTarget) target);

        assertEquals(1, Iterables.size(findAllDEdges()));
    }

    private Iterable<DEdge> findAllDEdges() {
        return Iterables.filter(new Iterable<EObject>() {
            public Iterator<EObject> iterator() {
                return diagram.eAllContents();
            }
        }, DEdge.class);
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }
}
