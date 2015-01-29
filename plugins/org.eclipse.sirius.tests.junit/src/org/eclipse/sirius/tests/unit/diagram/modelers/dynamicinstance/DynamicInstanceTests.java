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
package org.eclipse.sirius.tests.unit.diagram.modelers.dynamicinstance;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * Test dynamic instance creation scenario in a diagram.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DynamicInstanceTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/dynamicInstance/";

    private static final String METAMODEL_RESOURCE_NAME = "component.ecore";

    private static final String SEMANTIC_RESOURCE_NAME = "component.component";

    private static final String MODELER_RESOURCE_NAME = "component.odesign";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "component.aird";

    private static final String COMPONENT_DIAGRAM_WITH_PREFIX_DESC_NAME = "ComponentDiagramWithPrefix";

    private static final String COMPONENT_DIAGRAM_WITH_PREFIX_INSTANCE_NAME = "new " + COMPONENT_DIAGRAM_WITH_PREFIX_DESC_NAME;

    private static final String COMPONENT_DIAGRAM_WITHOUT_PREFIX_DESC_NAME = "ComponentDiagramWithPrefix";

    private static final String COMPONENT_DIAGRAM_WITHOUT_PREFIX_INSTANCE_NAME = "new " + COMPONENT_DIAGRAM_WITHOUT_PREFIX_DESC_NAME;

    private DDiagramEditor dDiagramEditor;

    private EObject applicationEObject;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, METAMODEL_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, MODELER_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        applicationEObject = session.getSemanticResources().iterator().next().getContents().get(0);

    }

    public void testDiagramWithPrefixCreation() {
        DDiagram dDiagramWithPrefix = (DDiagram) createRepresentation(COMPONENT_DIAGRAM_WITH_PREFIX_DESC_NAME, COMPONENT_DIAGRAM_WITH_PREFIX_INSTANCE_NAME, applicationEObject);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagramWithPrefix, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        create2ComponentsWithPortAndConnectionInDDiagramEditor(dDiagramWithPrefix);

    }

    public void testDiagramWithoutPrefixCreation() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
            junit.framework.AssertionFailedError: expected:<1> but was:<0>
            at junit.framework.Assert.fail(Assert.java:57)
            at junit.framework.Assert.failNotEquals(Assert.java:329)
            at junit.framework.Assert.assertEquals(Assert.java:78)
            at junit.framework.Assert.assertEquals(Assert.java:234)
            at junit.framework.Assert.assertEquals(Assert.java:241)
            at junit.framework.TestCase.assertEquals(TestCase.java:409)
            at org.eclipse.sirius.tests.unit.diagram.modelers.dynamicinstance.DynamicInstanceTests.create2ComponentsWithPortAndConnectionInDDiagramEditor(DynamicInstanceTests.java:125)
            at org.eclipse.sirius.tests.unit.diagram.modelers.dynamicinstance.DynamicInstanceTests.testDiagramWithoutPrefixCreation(DynamicInstanceTests.java:75)
             */
            return;
        }
        DDiagram dDiagramWithPrefix = (DDiagram) createRepresentation(COMPONENT_DIAGRAM_WITHOUT_PREFIX_DESC_NAME, COMPONENT_DIAGRAM_WITHOUT_PREFIX_INSTANCE_NAME, applicationEObject);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagramWithPrefix, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        create2ComponentsWithPortAndConnectionInDDiagramEditor(dDiagramWithPrefix);
    }

    private void create2ComponentsWithPortAndConnectionInDDiagramEditor(DDiagram dDiagram) {
        Object components = applicationEObject.eGet(applicationEObject.eClass().getEStructuralFeature("components"));
        assertTrue(components instanceof List<?>);
        List<?> applicationComponents = (List<?>) components;
        assertEquals(0, applicationComponents.size());

        Object connections = applicationEObject.eGet(applicationEObject.eClass().getEStructuralFeature("connections"));
        assertTrue(connections instanceof List<?>);
        List<?> applicationConnections = (List<?>) connections;
        assertEquals(0, applicationConnections.size());

        boolean component1Created = applyNodeCreationTool("ComponentCreationTool", dDiagram, dDiagram);
        TestsUtil.synchronizationWithUIThread();
        assertTrue("Can't create a node for a dynamic instance on diagram with prefix", component1Created);
        assertEquals(1, applicationComponents.size());
        Object applicationComponent1 = applicationComponents.get(0);
        assertTrue(applicationComponent1 instanceof DynamicEObjectImpl);
        DynamicEObjectImpl component1 = (DynamicEObjectImpl) applicationComponent1;
        Object ports1 = component1.eGet(component1.eClass().getEStructuralFeature("ports"));
        assertTrue(ports1 instanceof List<?>);
        List<?> component1Ports = (List<?>) ports1;
        assertEquals(1, dDiagram.getOwnedDiagramElements().size());
        DDiagramElement component1DDiagramElement = dDiagram.getOwnedDiagramElements().get(0);
        assertTrue(component1DDiagramElement instanceof DNodeContainer);
        DNodeContainer component1DNodeContainer = (DNodeContainer) component1DDiagramElement;

        boolean component2Created = applyNodeCreationTool("ComponentCreationTool", dDiagram, dDiagram);
        TestsUtil.synchronizationWithUIThread();
        assertTrue("Can't create a node for a dynamic instance on diagram with prefix", component2Created);
        assertEquals(2, applicationComponents.size());
        Object applicationComponent2 = applicationComponents.get(1);
        assertTrue(applicationComponent2 instanceof DynamicEObjectImpl);
        DynamicEObjectImpl component2 = (DynamicEObjectImpl) applicationComponent2;
        Object ports2 = component2.eGet(component2.eClass().getEStructuralFeature("ports"));
        assertTrue(ports2 instanceof List<?>);
        List<?> component2Ports = (List<?>) ports2;
        assertEquals(2, dDiagram.getOwnedDiagramElements().size());
        DDiagramElement component2DDiagramElement = dDiagram.getOwnedDiagramElements().get(1);
        assertTrue(component2DDiagramElement instanceof DNodeContainer);
        DNodeContainer component2DNodeContainer = (DNodeContainer) component2DDiagramElement;

        boolean port1Created = applyNodeCreationTool("PortCreationTool", dDiagram, component1DNodeContainer);
        TestsUtil.synchronizationWithUIThread();
        assertTrue("Can't create a node for a dynamic instance on diagram with prefix", port1Created);
        assertEquals(1, component1Ports.size());
        Object component1Port1 = component1Ports.get(0);
        assertTrue(component1Port1 instanceof DynamicEObjectImpl);
        assertEquals(1, component1DNodeContainer.getOwnedBorderedNodes().size());
        DNode component1Port1DNode = component1DNodeContainer.getOwnedBorderedNodes().get(0);

        boolean port2Created = applyNodeCreationTool("PortCreationTool", dDiagram, component2DNodeContainer);
        TestsUtil.synchronizationWithUIThread();
        assertTrue("Can't create a node for a dynamic instance on diagram with prefix", port2Created);
        assertEquals(1, component2Ports.size());
        Object component2Port1 = component2Ports.get(0);
        assertTrue(component2Port1 instanceof DynamicEObjectImpl);
        assertEquals(1, component2DNodeContainer.getOwnedBorderedNodes().size());
        DNode component2Port1DNode = component2DNodeContainer.getOwnedBorderedNodes().get(0);

        boolean connectionCreated = applyEdgeCreationTool("ConnectionCreationTool", dDiagram, component1Port1DNode, component2Port1DNode);
        TestsUtil.synchronizationWithUIThread();
        assertTrue("Can't create a edge for a dynamic instance on diagram with prefix", connectionCreated);
        assertEquals(1, applicationConnections.size());
        Object connection = applicationConnections.get(0);
        assertTrue(connection instanceof DynamicEObjectImpl);
        assertEquals(1, dDiagram.getEdges().size());
    }

    @Override
    public void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(dDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        dDiagramEditor = null;
        applicationEObject = null;
        super.tearDown();
    }
}
