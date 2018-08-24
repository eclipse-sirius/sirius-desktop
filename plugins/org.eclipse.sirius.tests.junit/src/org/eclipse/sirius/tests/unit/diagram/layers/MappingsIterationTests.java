/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.layers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.DiagramDescriptionMappingsManagerImpl;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.DiagramMappingsManagerImpl;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DNodeCandidate;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingsListVisitor;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;

public class MappingsIterationTests extends AbstractMappingsTableTest {

    private static final Set<DNodeCandidate> EMPTY_SET = Collections.emptySet();

    private DSemanticDiagram diagram;

    private Layer layer;

    private DiagramMappingsManager manager;

    private EPackage semanticTarget;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // Create a DSemanticDiagram with a valid semantic target so that the
        // DiagramMappingsManagerImpl can iterate on children
        diagram = DiagramFactory.eINSTANCE.createDSemanticDiagram();
        semanticTarget = EcoreFactory.eINSTANCE.createEPackage();
        diagram.setTarget(semanticTarget);
        new ResourceSetImpl().createResource(URI.createURI("temp:/resource.xml")).getContents().add(semanticTarget);

        layer = DescriptionFactory.eINSTANCE.createLayer();
        description.setDefaultLayer(layer);
        manager = new DiagramMappingsManagerImpl(diagram, new DiagramDescriptionMappingsManagerImpl(description));
    }

    public void testMappingNotInActiveLayer() {

        final AbstractNodeMapping nodeMappingNotInActiveLayer = createNodeMapping(null);
        final AbstractNodeMapping containerMappingNotInActiveLayer = createContainerMapping(null);

        MappingsListVisitorTest visitor = new MappingsListVisitorTest() {
            public Set<DNodeCandidate> visit(final DiagramElementMapping mapping, final Set<DNodeCandidate> semanticFilter) {
                assertFalse("mapping not in an active layer should not appear in the smart iteration", mapping.equals(nodeMappingNotInActiveLayer));
                assertFalse("mapping not in an active layer should not appear in the smart iteration", mapping.equals(containerMappingNotInActiveLayer));
                return EMPTY_SET;
            }
        };

        manager.computeMappings(null, true);
        iterate(visitor);
    }

    public void testNodeMappingImportHierachy() {

        diagram.getActivatedLayers().add(layer);

        final AbstractNodeMapping mappingA = createNodeMapping(layer);
        final AbstractNodeMapping mappingAAimportA = createNodeMappingImport(layer, (NodeMapping) mappingA, false);
        final AbstractNodeMapping mappingAAAimportAA = createNodeMappingImport(layer, (NodeMapping) mappingAAimportA, false);

        MappingsListVisitorTest test = new MappingsListVisitorTest() {

            public Set<DNodeCandidate> visit(DiagramElementMapping mapping, Set<DNodeCandidate> semanticFilter) {

                if (mapping.equals(mappingA))
                    assertTrue("a imported mapping should be visited after the mapping which imports it", visitedMappings.contains(mappingAAimportA));

                if (mapping.equals(mappingAAimportA)) {
                    assertFalse("a mapping which imports another mapping shoud be visited before its imported mapping", visitedMappings.contains(mappingA));
                    assertTrue("a imported mapping should be visited after the mapping which imports it", visitedMappings.contains(mappingAAAimportAA));
                }

                if (mapping.equals(mappingAAAimportAA))
                    assertFalse("a mapping which imports another mapping shoud be visited before its imported mapping", visitedMappings.contains(mappingAAimportA));

                visitedMappings.add(mapping);

                return EMPTY_SET;
            }
        };

        manager.computeMappings(null, true);
        iterate(test);
        assertFalse("no mappings where visted", test.visitedMappings.isEmpty());
    }

    public void testNodeMappingImportHierachyBug1794() {

        diagram.getActivatedLayers().add(layer);

        final AbstractNodeMapping mappingNM1 = createNodeMapping(layer);
        final AbstractNodeMapping mappingNM3 = createNodeMappingImport(layer, (NodeMapping) mappingNM1, false);
        final AbstractNodeMapping mappingNM2 = createNodeMappingImport(layer, (NodeMapping) mappingNM3, false);

        /* inverse NM2 and NM3 mapping order */
        layer.getNodeMappings().move(2, (NodeMapping) mappingNM3);

        MappingsListVisitorTest test = new MappingsListVisitorTest() {

            public Set<DNodeCandidate> visit(DiagramElementMapping mapping, Set<DNodeCandidate> semanticFilter) {

                if (mapping.equals(mappingNM1))
                    assertTrue("a imported mapping should be visited after the mapping which imports it", visitedMappings.contains(mappingNM3));

                if (mapping.equals(mappingNM3)) {
                    assertFalse("a mapping which imports another mapping shoud be visited before its imported mapping", visitedMappings.contains(mappingNM1));
                    assertTrue("a imported mapping should be visited after the mapping which imports it", visitedMappings.contains(mappingNM2));
                }

                if (mapping.equals(mappingNM2))
                    assertFalse("a mapping which imports another mapping shoud be visited before its imported mapping", visitedMappings.contains(mappingNM3));

                visitedMappings.add(mapping);

                return EMPTY_SET;
            }
        };

        manager.computeMappings(null, true);
        iterate(test);
        assertFalse("no mappings where visted", test.visitedMappings.isEmpty());
    }

    public void testContainerMappingImportHierachy() {

        diagram.getActivatedLayers().add(layer);

        final AbstractNodeMapping mappingA = createContainerMapping(layer);
        final AbstractNodeMapping mappingAAimportA = createContainerMappingImport(layer, (ContainerMapping) mappingA, false);
        final AbstractNodeMapping mappingAAAimportAA = createContainerMappingImport(layer, (ContainerMapping) mappingAAimportA, false);

        MappingsListVisitorTest test = new MappingsListVisitorTest() {

            public Set<DNodeCandidate> visit(DiagramElementMapping mapping, Set<DNodeCandidate> semanticFilter) {

                if (mapping.equals(mappingA))
                    assertTrue("a imported mapping should be visited after the mapping which imports it", visitedMappings.contains(mappingAAimportA));

                if (mapping.equals(mappingAAimportA)) {
                    assertFalse("a mapping which imports another mapping shoud be visited before its imported mapping", visitedMappings.contains(mappingA));
                    assertTrue("a imported mapping should be visited after the mapping which imports it", visitedMappings.contains(mappingAAAimportAA));
                }

                if (mapping.equals(mappingAAAimportAA))
                    assertFalse("a mapping which imports another mapping shoud be visited before its imported mapping", visitedMappings.contains(mappingAAimportA));

                visitedMappings.add(mapping);

                return EMPTY_SET;
            }
        };

        manager.computeMappings(null, true);
        iterate(test);
        assertFalse("no mappings where visted", test.visitedMappings.isEmpty());
    }

    /**
     * This test may not be necessary and is wrong. See the FIXME in
     * DiagramMappingsManagerImpl. hide is related to visibility not to mapping
     * traversal
     */
    public void testNodeMappingImportAndHideSubElements() {

        diagram.getActivatedLayers().add(layer);

        final AbstractNodeMapping mappingB = createNodeMapping(layer);
        createNodeMappingImport(layer, (NodeMapping) mappingB, true);

        MappingsListVisitorTest test = new MappingsListVisitorTest() {

            public Set<DNodeCandidate> visit(DiagramElementMapping mapping, Set<DNodeCandidate> semanticFilter) {
                assertFalse("if a mapping import another mapping and hide submappings, the submappings should not be visited", visitedMappings.contains(mappingB));
                visitedMappings.add(mapping);
                return EMPTY_SET;
            }
        };

        manager.computeMappings(null, true);
        iterate(test);
        assertFalse("no mappings where visted", test.visitedMappings.isEmpty());
    }

    /**
     * This test may not be necessary and is wrong. See the FIXME in
     * DiagramMappingsManagerImpl. hide is related to visibility not to mapping
     * traversal
     */
    public void testContainerMappingImportAndHideSubElements() {

        diagram.getActivatedLayers().add(layer);

        final AbstractNodeMapping mappingB = createContainerMapping(layer);
        createContainerMappingImport(layer, (ContainerMapping) mappingB, true);

        MappingsListVisitorTest test = new MappingsListVisitorTest() {

            public Set<DNodeCandidate> visit(DiagramElementMapping mapping, Set<DNodeCandidate> semanticFilter) {
                assertFalse("if a mapping import another mapping and hide submappings, the submappings should not be visited", visitedMappings.contains(mappingB));
                visitedMappings.add(mapping);
                return EMPTY_SET;
            }
        };

        manager.computeMappings(null, true);
        iterate(test);
        assertFalse("no mappings where visted", test.visitedMappings.isEmpty());
    }

    /**
     * Ensures that if the {@link DDiagram} contains {@link DDiagramElement}
     * which are associated to a null or deleted (eResource() is null) target,
     * the {@link DiagramMappingsManager} does not evaluate the children of this
     * element (e.g. Semantic Candidate Expression for bordered Nodes). This
     * could lead to unnecessary interpreter errors that can be avoided.
     */
    public void testDiagramElementsWithDeletedTarget() {
        diagram.getActivatedLayers().add(layer);

        // Step 1: Create a Container mapping in a node mapping
        final ContainerMapping containerMapping = createContainerMapping(layer);
        final NodeMapping subNodeMapping = createNodeMapping(layer);
        containerMapping.getSubNodeMappings().add(subNodeMapping);

        // Step 2: Create the associated DDiagramElements in the diagram (with
        // valid semantic targets)
        DNodeContainer containerNode = DiagramFactory.eINSTANCE.createDNodeContainer();
        containerNode.setActualMapping(containerMapping);
        EClassifier childSemanticTarget = EcoreFactory.eINSTANCE.createEClass();
        semanticTarget.getEClassifiers().add(childSemanticTarget);
        containerNode.setTarget(childSemanticTarget);
        DNode childNode = DiagramFactory.eINSTANCE.createDNode();
        childNode.setActualMapping(subNodeMapping);
        containerNode.getOwnedDiagramElements().add(childNode);
        diagram.getOwnedDiagramElements().add(containerNode);

        // Step 3: Visit mappings : the child mapping should be visited
        MappingsListVisitorTest mappingVisitor = new MappingsListVisitorTest() {

            public Set<DNodeCandidate> visit(DiagramElementMapping mapping, Set<DNodeCandidate> semanticFilter) {
                visitedMappings.add(mapping);
                return EMPTY_SET;
            }
        };
        manager.computeMappings(null, true);
        iterate(mappingVisitor);
        assertTrue("All mappings should have been visited at their semantic target is valid", mappingVisitor.visitedMappings.contains(containerMapping));
        manager.iterate(mappingVisitor, containerNode);
        assertTrue("All mappings should have been visited at their semantic target is valid", mappingVisitor.visitedMappings.contains(subNodeMapping));

        // Step 4: Delete the container's target
        mappingVisitor.visitedMappings.clear();
        EcoreUtil.delete(childSemanticTarget);

        // Visit mappings again : the child mapping should never gets visited as
        // the target of the DNodeContainer is now null
        manager.computeMappings(null, true);
        iterate(mappingVisitor);
        assertTrue("The diagram and the container mapping should have been visited, but not the child as its container is associated to a null target",
                mappingVisitor.visitedMappings.contains(containerMapping));
        manager.iterate(mappingVisitor, containerNode);
        assertFalse("The diagram and the container mapping should have been visited, but not the child as its container is associated to a null target",
                mappingVisitor.visitedMappings.contains(subNodeMapping));

    }

    private <T extends AbstractNodeMapping> void iterate(final MappingsListVisitor visitor) {
        manager.iterate(visitor, diagram);
    }

    private abstract static class MappingsListVisitorTest implements MappingsListVisitor {

        protected Set<DiagramElementMapping> visitedMappings = new HashSet<DiagramElementMapping>();
    }
}
