/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.UMLFactory;

/**
 * Test the experimental diagram synchronizer.
 * 
 * @author cbrun
 * 
 */
public class DiagramSynchronizerTest extends AbstractSynchronizerTest {

    private static final String CLASS1_NAME = "Class1";

    private static final String TEST_CLASS_DIAGRAM = "Test class diagram";

    private String MWSDC_AIRD = "/org.eclipse.sirius.tests.junit/data/unit/refresh/mwsdc1243/mwscdaird.aird";


    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testDummyToLoadPlugins() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Node Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);
        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        getRefreshedDiagram();
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testNodesInit() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Node Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should have 4 nodes here .", 4, diagram.getOwnedDiagramElements().size());
        final PackageableElement class1 = ((Model) semanticModel).getPackagedElement(CLASS1_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, class1);

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                EcoreUtil.delete(class1);
            }
        });

        /*
         * refresh
         */
        diagram = getRefreshedDiagram();
        assertEquals("We should have 3 nodes here as we deleted a class", 3, diagram.getOwnedDiagramElements().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testNodesInitWithImport() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Node Class Diagram with Import");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should have 4 nodes here", 4, diagram.getOwnedDiagramElements().size());

        final PackageableElement class1 = ((Model) semanticModel).getPackagedElement(CLASS1_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, class1);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                EcoreUtil.delete(class1);
            }
        });

        /*
         * refresh
         */
        diagram = getRefreshedDiagram();
        assertEquals("We should have 3 nodes here as we deleted a class", 3, diagram.getOwnedDiagramElements().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testNodesWithBorder() throws Exception {
        DiagramDescription classDiagPorts = findDiagramDescription("Node Class Diagram with Ports");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiagPorts);

        prepareSynchronizer(classDiagPorts, "Test class diagram with ports");
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 4 nodes here", 4, diagram.getOwnedDiagramElements().size());
        DNode firstNode = (DNode) findElementNamed(diagram, CLASS1_NAME);

        assertNotNull("We should have retrieved a node named \"Class1\"", firstNode);

        assertEquals("We should have 1 port around this node ", 1, firstNode.getOwnedBorderedNodes().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testNodesInitWithSemanticCandidates() throws Exception {
        DiagramDescription classDiagCandidates = findDiagramDescription("Node Class Diagram with Semantic Candidates");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiagCandidates);

        prepareSynchronizer(classDiagCandidates, "Test  class diagram with candidates");
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should have 3 nodes here as we only keep the current package.", 3, diagram.getOwnedDiagramElements().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testContainersInit() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Container Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 containers here", 4, diagram.getOwnedDiagramElements().size());

        final PackageableElement class1 = ((Model) semanticModel).getPackagedElement(CLASS1_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, class1);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                EcoreUtil.delete(class1);
            }
        });
        /*
         * refresh
         */
        diagram = getRefreshedDiagram();
        assertEquals("We should have 3 containers here as we deleted a class", 3, diagram.getOwnedDiagramElements().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testContainersInitWithImport() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Container Class Diagram with Import");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should have 4 containers here", 4, diagram.getOwnedDiagramElements().size());

        final PackageableElement class1 = ((Model) semanticModel).getPackagedElement(CLASS1_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, class1);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                EcoreUtil.delete(class1);
            }
        });
        /*
         * refresh
         */
        diagram = getRefreshedDiagram();
        assertEquals("We should have 3 containers here as we deleted a class", 3, diagram.getOwnedDiagramElements().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testContainersWithBorder() throws Exception {
        DiagramDescription classDiagPorts = findDiagramDescription("Container Class Diagram with Ports");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiagPorts);

        prepareSynchronizer(classDiagPorts, "Test class diagram with ports");
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 4 containers here", 4, diagram.getOwnedDiagramElements().size());
        DNodeContainer firstContainer = (DNodeContainer) findElementNamed(diagram, CLASS1_NAME);

        assertNotNull("We should have retrieved a node named \"Class1\"", firstContainer);

        assertEquals("We should have 1 port around this node ", 1, firstContainer.getOwnedBorderedNodes().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testContainerInitWithSemanticCandidates() throws Exception {
        DiagramDescription classDiagCandidates = findDiagramDescription("Container Class Diagram with Semantic Candidates");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiagCandidates);

        prepareSynchronizer(classDiagCandidates, "Test   class diagram with candidates");
        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should have 3 containers here as we only keep the current package.", 3, diagram.getOwnedDiagramElements().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testContainersToListChange() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Container Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);
        final ContainerMapping containerMapping = findContainerMapping(classDiag, "CD_Class");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, containerMapping);

        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 containers here", 4, diagram.getOwnedDiagramElements().size());
        /* set mapping to be a list container mapping */

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                containerMapping.setChildrenPresentation(ContainerLayout.LIST);
            }
        });

        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 containers here", 4, diagram.getOwnedDiagramElements().size());
        for (final DDiagramElement element : diagram.getOwnedDiagramElements()) {
            assertTrue("diagram element should be a DNodeList instance", element instanceof DNodeList);
        }

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgeBetweenNodes() throws Exception {
        DiagramDescription classEdgesDiag = findDiagramDescription("Edges Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classEdgesDiag);

        prepareSynchronizer(classEdgesDiag, "Test  class diagram with candidates");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 4 edges. ", 4, diagram.getEdges().size());
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgeBetweenNodesWithDomain() throws Exception {
        DiagramDescription classEdgesDiag = findDiagramDescription("Edges Class Diagram with Domain");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classEdgesDiag);

        prepareSynchronizer(classEdgesDiag, "Test class diagram  with candidates");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 6 edges.", 6, diagram.getEdges().size());
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgeContainerToContainer() throws Exception {
        DiagramDescription classEdgesDiag = findDiagramDescription("Edges Class Diagram with Container");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classEdgesDiag);
        prepareSynchronizer(classEdgesDiag, "Test class  diagram with candidates");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 4 edges.", 4, diagram.getEdges().size());
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgeContainerToContainerWithEdgeMappingPrecondition() throws Exception {
        DiagramDescription classEdgesDiag = findDiagramDescription("Edges Class Diagram with Container with Edge Mapping precondition");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classEdgesDiag);
        prepareSynchronizer(classEdgesDiag, "Test class  diagram with candidates");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 4 edges.", 4, diagram.getEdges().size());
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgeContainerToNode() throws Exception {
        DiagramDescription classEdgesDiag = findDiagramDescription("Edges Class Diagram with Container and Node");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classEdgesDiag);

        prepareSynchronizer(classEdgesDiag, "Test class diagram with candidates");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 4 edges.", 4, diagram.getEdges().size());
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgeBorderToBorder() throws Exception {
        DiagramDescription classEdgesDiag = findDiagramDescription("Edges Class Diagram with Border to Node");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classEdgesDiag);

        prepareSynchronizer(classEdgesDiag, "Test class diagram with candidates");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 4 edges.", 4, diagram.getEdges().size());
    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgesNodeToNodewithPath() throws Exception {
        DiagramDescription packageDiagWithPath = findDiagramDescription("Package Diagram With paths");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, packageDiagWithPath);

        prepareSynchronizer(packageDiagWithPath, "Test package diagram with edges paths");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 6 edges.", 6, diagram.getEdges().size());

        DEdge pathEdge = null;
        for (DEdge edge : diagram.getEdges()) {
            Option<String> mappingName = new DDiagramElementQuery(edge).getMappingName();
            if (mappingName.some() && "Browsing Path".equals(mappingName.get())) {
                pathEdge = edge;
            }
        }

        assertNotNull("The edge with a path is not found", pathEdge);

        Collection<EObject> allpackages = interpreter.evaluateCollection(((Model) semanticModel), "aql:self.eAllContents(uml::Package)");
        assertEquals("the path'ed elements should be all the model packages", allpackages.size(), pathEdge.getPath().size());

    }

    /**
     * refresh test.
     * 
     * @throws Exception
     *             on error.
     */
    public void testEdgesNodeToNodewithPathAndDuplicateElements() throws Exception {
        DiagramDescription packageDiagWithPath = findDiagramDescription("Package Diagram With paths and duplicate elements inside them");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, packageDiagWithPath);

        prepareSynchronizer(packageDiagWithPath, "Test package diagram with edges paths");

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }
        assertEquals("We should have 6 edges.", 6, diagram.getEdges().size());

        DEdge pathEdge = null;
        for (DEdge edge : (Iterable<DEdge>) diagram.getEdges()) {
            Option<String> mappingName = new DDiagramElementQuery(edge).getMappingName();
            if (mappingName.some() && "Browsing Path".equals(mappingName.get())) {
                pathEdge = edge;
            }
        }

        assertNotNull("The edge with a path is not found", pathEdge);

        Collection<EObject> allpackages = interpreter.evaluateCollection(((Model) semanticModel), "aql:self.eAllContents(uml::Package) + Sequence{self.eAllContents(uml::Package)->last()}");
        assertEquals("the path'ed elements should be all the model packages", allpackages.size(), pathEdge.getPath().size());

    }

    /**
     * Unit test for trac #1243.
     * <p>
     * Two mappings with the same Domain Class conflict together.
     * </p>
     * 
     * @throws Exception
     *             is the test fails.
     */
    public void testMappingsWithSameDomainClass() throws Exception {
        URI sessionResourceURI = URI.createPlatformPluginURI(MWSDC_AIRD, true);

        final Session session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        session.open(new NullProgressMonitor());

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, session.getSemanticResources().size());
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, session.getSemanticResources().iterator().next().getContents().size());
        assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, session.getSemanticResources().iterator().next().getContents().get(0) instanceof Package);

        final Package rootPackage = (Package) session.getSemanticResources().iterator().next().getContents().get(0);

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, DialectManager.INSTANCE.getRepresentations(rootPackage, session).size());
        final DRepresentation representation = DialectManager.INSTANCE.getRepresentations(rootPackage, session).iterator().next();
        assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, representation instanceof DSemanticDiagram);

        final DSemanticDiagram diagram = (DSemanticDiagram) representation;
        //
        // refresh the diagram and check there are 4 nodes.
        session.getTransactionalEditingDomain().getCommandStack().execute(new RefreshRepresentationsCommand(session.getTransactionalEditingDomain(), defaultProgress, diagram));

        assertEquals("Invalid nodes count", 4, diagram.getOwnedDiagramElements().size());
        session.close(new NullProgressMonitor());
        IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
        if (uiSession != null) {
            uiSession.close();
            TestsUtil.emptyEventsFromUIThread();
        }
    }

    /**
     * Test the good deletion of duplicate diagram elements
     * 
     * @throws Exception
     *             on error.
     */
    public void testDuplicateElementsDeletion() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Container Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 containers here", 4, diagram.getOwnedDiagramElements().size());
        final DNodeContainer originalContainer = (DNodeContainer) diagram.getOwnedDiagramElements().get(0);
        /* create a duplicated container and attach it to the diagram */
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                final DNodeContainer container = DiagramFactory.eINSTANCE.createDNodeContainer();
                container.setActualMapping(originalContainer.getActualMapping());
                container.setTarget(originalContainer.getTarget());
                final DDiagram parentDiagram = (DDiagram) originalContainer.eContainer();
                parentDiagram.getOwnedDiagramElements().add(container);
            }
        });

        assertEquals("We should  have 5 containers here", 5, diagram.getOwnedDiagramElements().size());

        /* refresh should clear the duplicate element */
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 containers here", 4, diagram.getOwnedDiagramElements().size());
    }

    public void testIgnoredElementsDeletion() throws Exception {
        DiagramDescription classDiag = findDiagramDescription("Container Class Diagram");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        DiagramDescription otherClassDiag = findDiagramDescription("Container Class Diagram with Semantic Candidates");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, otherClassDiag);

        final ContainerMapping otherContainerMapping = findContainerMapping(otherClassDiag, "CDSemantic_Class");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, otherContainerMapping);

        prepareSynchronizer(classDiag, TEST_CLASS_DIAGRAM);

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 containers here", 4, diagram.getOwnedDiagramElements().size());
        final DNodeContainer originalContainer = (DNodeContainer) diagram.getOwnedDiagramElements().get(0);
        final RepresentationElementMapping originalContainerMapping = originalContainer.getMapping();
        /* create 2 fake containers and attach them to the diagram */
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                /* create a container without semantic element */
                final DNodeContainer containerWithoutTarget = DiagramFactory.eINSTANCE.createDNodeContainer();
                containerWithoutTarget.setActualMapping(originalContainer.getActualMapping());

                /*
                 * create an uml2 class and a container with a mapping defined
                 * in another diagram description
                 */
                final Class umlClass = ((Model) semanticModel).createOwnedClass("fake class", false);
                final DNodeContainer containerWithBadMapping = DiagramFactory.eINSTANCE.createDNodeContainer();
                containerWithBadMapping.setTarget(umlClass);
                containerWithBadMapping.setActualMapping(otherContainerMapping);

                final DNodeContainer containerWithBadMapping2 = DiagramFactory.eINSTANCE.createDNodeContainer();
                containerWithBadMapping2.setActualMapping(otherContainerMapping);

                /* add them to the diagram */
                final DDiagram parentDiagram = (DDiagram) originalContainer.eContainer();
                parentDiagram.getOwnedDiagramElements().add(containerWithoutTarget);
                parentDiagram.getOwnedDiagramElements().add(containerWithBadMapping);
                parentDiagram.getOwnedDiagramElements().add(containerWithBadMapping2);
            }
        });

        assertEquals("We should  have 7 containers here", 7, diagram.getOwnedDiagramElements().size());

        /* refresh should clear the duplicate element */
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        /*
         * the tree containers we created should be deleted but one new should
         * have been created for the class we created
         */
        assertEquals("We should  have 5 containers here", 5, diagram.getOwnedDiagramElements().size());
        for (final DDiagramElement element : diagram.getOwnedDiagramElements()) {
            assertTrue("diagram element should be a DNodeList instance", originalContainerMapping == element.getMapping());
        }

    }

    public void testIfLabelsAreVisibleAtCreation() {
        DiagramDescription diagDesc = findDiagramDescription("LabelVisibleByDefault");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagDesc);

        prepareSynchronizer(diagDesc, TEST_CLASS_DIAGRAM);

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 diagram elements here.", 4, diagram.getOwnedDiagramElements().size());
        assertEquals("We should  have 0 hidden element here.", 0, computeHiddenElements(diagram).size());

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain, "Modify models") {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                Package newPackage = UMLFactory.eINSTANCE.createPackage();
                newPackage.setName("newPackage");
                Class newClass = UMLFactory.eINSTANCE.createClass();
                newClass.setName("newClass");
                Package newSubPackage = UMLFactory.eINSTANCE.createPackage();
                newSubPackage.setName("newSubPackage");
                newPackage.getPackagedElements().add(newClass);
                newPackage.getPackagedElements().add(newSubPackage);
                ((Model) semanticModel).getPackagedElements().add(newPackage);
            }
        });

        diagram = getRefreshedDiagram();
        assertEquals("We should  have 6 diagram elements here.", 6, diagram.getOwnedDiagramElements().size());
        assertEquals("We should  have 0 hidden elements here.", 0, computeHiddenElements(diagram).size());

    }

    /**
     * List of hidden diagramElement (or diagramElement from which label is
     * hidden). Use the same code used in the
     * AbstractDDiagramEditPart.activate().
     * 
     * @param diagram
     */
    private List<DDiagramElement> computeHiddenElements(DDiagram diagram) {
        List<DDiagramElement> hiddenElements = new ArrayList<>();
        DDiagramQuery dDiagramQuery = new DDiagramQuery(diagram);
        for (final DDiagramElement diagramElement : diagram.getDiagramElements()) {
            if (dDiagramQuery.isHidden(session, diagramElement)) {
                hiddenElements.add(diagramElement);
            } else if (dDiagramQuery.isLabelHidden(session, diagramElement)) {
                hiddenElements.add(diagramElement);
            }
        }
        return hiddenElements;
    }

    public void testIfLabelsAreHiddenAtCreation() {
        DiagramDescription diagDesc = findDiagramDescription("LabelHiddenByDefault");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagDesc);

        prepareSynchronizer(diagDesc, TEST_CLASS_DIAGRAM);

        DDiagram diagram = null;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            diagram = getRefreshedDiagram();
        }

        assertEquals("We should  have 4 diagram elements here.", 4, diagram.getOwnedDiagramElements().size());
        assertEquals("We should  have 5 hidden elements here.", 5, computeHiddenElements(diagram).size());

        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain, "Modify models") {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                Package newPackage = UMLFactory.eINSTANCE.createPackage();
                newPackage.setName("newPackage");
                Class newClass = UMLFactory.eINSTANCE.createClass();
                newClass.setName("newClass");
                Package newSubPackage = UMLFactory.eINSTANCE.createPackage();
                newSubPackage.setName("newSubPackage");
                newPackage.getPackagedElements().add(newClass);
                newPackage.getPackagedElements().add(newSubPackage);
                ((Model) semanticModel).getPackagedElements().add(newPackage);
            }
        });

        diagram = getRefreshedDiagram();
        assertEquals("We should  have 6 diagram elements here.", 6, diagram.getOwnedDiagramElements().size());
        assertEquals("We should  have 9 hidden elements here.", 9, computeHiddenElements(diagram).size());
    }
}
