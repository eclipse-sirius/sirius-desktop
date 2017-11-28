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
package org.eclipse.sirius.tests.unit.api.layers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class MultipleMappingImportTests extends SiriusDiagramTestCase implements MultipleMapppingImportsModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(MULTIPLE_MAPPING_IMPORT_VIEWPOINT_NAME);
    }

    public void testPreConditionNodeImport() throws Exception {
        nodeImportTesting(PRECONDITION_CHANGE_NODE_DIAGRAM);
    }

    public void testDomainClassNodeImport() throws Exception {
        nodeImportTesting(PRECONDITION_CHANGE_NODE_DIAGRAM);
    }

    public void testPreConditionContainerImport() throws Exception {
        containerImportTesting(PRECONDITION_CHANGE_CONTAINER_DIAGRAM);
    }

    public void testDomainClassContainerImport() throws Exception {
        containerImportTesting(DOMAINCLASS_CHANGE_CONTAINER_DIAGRAM);
    }

    public void testPreConditionNodeImportWithHideSubMapping() throws Exception {
        nodeImportTestingWithHideSubMapping(PRECONDITION_CHANGE_NODE_DIAGRAM_WITH_HIDESUBMAPPING);
    }

    public void testDomainClassNodeImportWithHideSubMapping() throws Exception {
        nodeImportTestingWithHideSubMapping(PRECONDITION_CHANGE_NODE_DIAGRAM_WITH_HIDESUBMAPPING);
    }

    public void testPreConditionContainerImportWithHideSubMapping() throws Exception {
        containerImportTestingWithHideSubMapping(PRECONDITION_CHANGE_CONTAINER_DIAGRAM_WITH_HIDESUBMAPPING);
    }

    public void testDomainClassContainerImportWithHideSubMapping() throws Exception {
        containerImportTestingWithHideSubMapping(DOMAINCLASS_CHANGE_CONTAINER_DIAGRAM_WITH_HIDESUBMAPPING);
    }

    public void nodeImportTesting(String diagramName) throws Exception {

        DDiagram diagram = (DDiagram) getRepresentations(diagramName).toArray()[0];
        refresh(diagram);

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 4 nodes here .", 4, elements.size());

        Layer alphaLayer = getLayer(diagram, ALPHA_LAYER);
        NodeMapping alphaNodeMapping = (NodeMapping) getNodeMapping(ALPHA_NODE, alphaLayer.getNodeMappings());

        Layer betaLayer = getLayer(diagram, BETA_LAYER);
        NodeMapping betaNodeMapping = (NodeMapping) getNodeMapping(BETA_NODE, betaLayer.getNodeMappings());

        Layer defaultLayer = getLayer(diagram, DEFAULT_LAYER);
        NodeMapping defaultNodeMapping = (NodeMapping) getNodeMapping(DEFAULT_NODE, defaultLayer.getNodeMappings());

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> diagramElements = diagram.getOwnedDiagramElements();
        Map<DDiagramElement, Bounds> beforeBounds = getBounds(diagramElements);
        checkMapping(diagramElements, defaultNodeMapping);

        activateLayer(diagram, ALPHA_LAYER);

        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        Map<DDiagramElement, Bounds> afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        List<DDiagramElement> elementsWithDefaultMapping = new ArrayList<>();
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC));
        checkMapping(elementsWithDefaultMapping, defaultNodeMapping);

        List<DDiagramElement> elementsWithAlhaMapping = new ArrayList<>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC));
        checkMapping(elementsWithAlhaMapping, alphaNodeMapping);

        activateLayer(diagram, BETA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        elementsWithDefaultMapping = new ArrayList<DDiagramElement>();
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        checkMapping(elementsWithDefaultMapping, defaultNodeMapping);

        elementsWithAlhaMapping = new ArrayList<DDiagramElement>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC));
        checkMapping(elementsWithAlhaMapping, alphaNodeMapping);

        List<DDiagramElement> elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC));
        checkMapping(elementsWithBetaMapping, betaNodeMapping);

        deactivateLayer(diagram, ALPHA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);

        elementsWithDefaultMapping = new ArrayList<DDiagramElement>();
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC));
        checkMapping(elementsWithDefaultMapping, defaultNodeMapping);

        elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC));
        checkMapping(elementsWithBetaMapping, betaNodeMapping);

    }

    public void nodeImportTestingWithHideSubMapping(String diagramName) throws Exception {

        DDiagram diagram = (DDiagram) getRepresentations(diagramName).toArray()[0];
        refresh(diagram);
        assertNotNull("The diagram " + diagramName + " should NOT be null!!", diagram);
        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 4 nodes here .", 4, elements.size());

        Layer alphaLayer = getLayer(diagram, ALPHA_LAYER);
        NodeMapping alphaNodeMapping = (NodeMapping) getNodeMapping(ALPHA_NODE, alphaLayer.getNodeMappings());

        Layer betaLayer = getLayer(diagram, BETA_LAYER);
        NodeMapping betaNodeMapping = (NodeMapping) getNodeMapping(BETA_NODE, betaLayer.getNodeMappings());

        Layer defaultLayer = getLayer(diagram, DEFAULT_LAYER);
        NodeMapping defaultNodeMapping = (NodeMapping) getNodeMapping(DEFAULT_NODE, defaultLayer.getNodeMappings());

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> diagramElements = diagram.getOwnedDiagramElements();
        Map<DDiagramElement, Bounds> beforeBounds = getBounds(diagramElements);

        checkMapping(diagramElements, defaultNodeMapping);

        activateLayer(diagram, ALPHA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        Map<DDiagramElement, Bounds> afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        List<DDiagramElement> notVisibleElements = new ArrayList<>();
        notVisibleElements.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        notVisibleElements.add(findByTargetName(diagramElements, BETA_SEMANTIC));
        assertNotVisible(notVisibleElements);

        List<DDiagramElement> elementsWithAlhaMapping = new ArrayList<>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC));
        checkMapping(elementsWithAlhaMapping, alphaNodeMapping);

        activateLayer(diagram, BETA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        notVisibleElements = new ArrayList<DDiagramElement>();
        notVisibleElements.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        assertNotVisible(notVisibleElements);

        elementsWithAlhaMapping = new ArrayList<DDiagramElement>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC));
        checkMapping(elementsWithAlhaMapping, alphaNodeMapping);

        List<DDiagramElement> elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC));
        checkMapping(elementsWithBetaMapping, betaNodeMapping);

        deactivateLayer(diagram, ALPHA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);

        notVisibleElements = new ArrayList<DDiagramElement>();
        notVisibleElements.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        notVisibleElements.add(findByTargetName(diagramElements, ALPHA_SEMANTIC));
        assertNotVisible(notVisibleElements);

        elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC));
        checkMapping(elementsWithBetaMapping, betaNodeMapping);

    }

    private void assertNotVisible(final Collection<DDiagramElement> elements) {
        for (final DDiagramElement element : elements) {
            assertFalse(element.isVisible());
        }
    }

    public void containerImportTesting(String diagramName) throws Exception {

        DDiagram diagram = (DDiagram) getRepresentations(diagramName).toArray()[0];
        refresh(diagram);

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 12 nodes here .", 12, elements.size());

        Layer alphaLayer = getLayer(diagram, ALPHA_LAYER);
        ContainerMapping alphaContainerMapping = getContainerMapping(ALPHA_CONTAINER, alphaLayer.getContainerMappings());

        Layer betaLayer = getLayer(diagram, BETA_LAYER);
        ContainerMapping betaContainerMapping = getContainerMapping(BETA_CONTAINER, betaLayer.getContainerMappings());

        Layer defaultLayer = getLayer(diagram, DEFAULT_LAYER);
        ContainerMapping defaultContainerMapping = getContainerMapping(DEFAULT_CONTAINER, defaultLayer.getContainerMappings());

        List<DDiagramElement> diagramElements = diagram.getOwnedDiagramElements();

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        checkMapping(diagramElements, defaultContainerMapping);
        Map<DDiagramElement, Bounds> beforeBounds = getBounds(diagramElements);

        activateLayer(diagram, ALPHA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        Map<DDiagramElement, Bounds> afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        List<DDiagramElement> elementsWithDefaultMapping = new ArrayList<>();
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithDefaultMapping, defaultContainerMapping);

        List<DDiagramElement> elementsWithAlhaMapping = new ArrayList<>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithAlhaMapping, alphaContainerMapping);

        activateLayer(diagram, BETA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        elementsWithDefaultMapping = new ArrayList<DDiagramElement>();
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        checkMapping(elementsWithDefaultMapping, defaultContainerMapping);

        elementsWithAlhaMapping = new ArrayList<DDiagramElement>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithAlhaMapping, alphaContainerMapping);

        List<DDiagramElement> elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithBetaMapping, betaContainerMapping);

        deactivateLayer(diagram, ALPHA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);

        elementsWithDefaultMapping = new ArrayList<DDiagramElement>();
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithDefaultMapping, defaultContainerMapping);

        elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithBetaMapping, betaContainerMapping);

    }

    public void containerImportTestingWithHideSubMapping(String diagramName) throws Exception {

        DDiagram diagram = (DDiagram) getRepresentations(diagramName).toArray()[0];
        refresh(diagram);

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 12 nodes here .", 12, elements.size());

        Layer alphaLayer = getLayer(diagram, ALPHA_LAYER);
        ContainerMapping alphaNodeMapping = getContainerMapping(ALPHA_CONTAINER, alphaLayer.getContainerMappings());

        Layer betaLayer = getLayer(diagram, BETA_LAYER);
        ContainerMapping betaNodeMapping = getContainerMapping(BETA_CONTAINER, betaLayer.getContainerMappings());

        Layer defaultLayer = getLayer(diagram, DEFAULT_LAYER);
        ContainerMapping defaultNodeMapping = getContainerMapping(DEFAULT_CONTAINER, defaultLayer.getContainerMappings());

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> diagramElements = diagram.getOwnedDiagramElements();

        Map<DDiagramElement, Bounds> beforeBounds = getBounds(diagramElements);

        checkMapping(diagramElements, defaultNodeMapping);

        activateLayer(diagram, ALPHA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        Map<DDiagramElement, Bounds> afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        List<DDiagramElement> elementsNotVisible = new ArrayList<>();
        elementsNotVisible.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        elementsNotVisible.add(findByTargetName(diagramElements, BETA_SEMANTIC_CONTAINER));
        assertNotVisible(elementsNotVisible);

        List<DDiagramElement> elementsWithAlhaMapping = new ArrayList<>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithAlhaMapping, alphaNodeMapping);

        activateLayer(diagram, BETA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        elementsNotVisible = new ArrayList<DDiagramElement>();
        elementsNotVisible.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        assertNotVisible(elementsNotVisible);

        elementsWithAlhaMapping = new ArrayList<DDiagramElement>();
        elementsWithAlhaMapping.add(findByTargetName(diagramElements, ALPHA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithAlhaMapping, alphaNodeMapping);

        List<DDiagramElement> elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithBetaMapping, betaNodeMapping);

        deactivateLayer(diagram, ALPHA_LAYER);
        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);

        elementsNotVisible = new ArrayList<DDiagramElement>();
        elementsNotVisible.add(findByTargetName(diagramElements, DEFAULT_SEMANTIC));
        elementsNotVisible.add(findByTargetName(diagramElements, ALPHA_SEMANTIC_CONTAINER));
        assertNotVisible(elementsNotVisible);

        elementsWithBetaMapping = new ArrayList<DDiagramElement>();
        elementsWithBetaMapping.add(findByTargetName(diagramElements, BETA_SEMANTIC_CONTAINER));
        checkMapping(elementsWithBetaMapping, betaNodeMapping);

    }

    private Map<DDiagramElement, Bounds> getBounds(List<DDiagramElement> diagramElements) {
        Map<DDiagramElement, Bounds> result = new HashMap<DDiagramElement, Bounds>();

        for (final DDiagramElement element : diagramElements) {
            Node gmfView = (Node) getGmfView(element);
            if (gmfView.getLayoutConstraint() instanceof Bounds) {
                final Bounds nodeBounds = ((Bounds) gmfView.getLayoutConstraint());
                final Bounds copyBounds = NotationFactory.eINSTANCE.createBounds();
                copyBounds.setHeight(nodeBounds.getHeight());
                copyBounds.setWidth(nodeBounds.getWidth());
                copyBounds.setX(nodeBounds.getX());
                copyBounds.setY(nodeBounds.getY());
                result.put(element, copyBounds);
            }
        }
        return result;
    }

    private void compareBounds(Map<DDiagramElement, Bounds> beforeBounds, Map<DDiagramElement, Bounds> afterBounds) throws Exception {

        for (final Map.Entry<DDiagramElement, Bounds> entry : beforeBounds.entrySet()) {
            Bounds before = entry.getValue();
            Bounds after = afterBounds.get(entry.getKey());
            assertEquals(before.getX(), after.getX());
            assertEquals(before.getY(), after.getY());
        }

    }

    private static void checkMapping(final List<DDiagramElement> elements, final AbstractNodeMapping firstMappingImport) {
        for (final DDiagramElement element : elements) {
            assertTrue("the element does not have the right node mapping", EcoreUtil.equals(element.getMapping(), firstMappingImport));
        }
    }

    private NodeMapping getNodeMapping(String name, List<NodeMapping> listMapping) {

        Iterator<NodeMapping> it = listMapping.iterator();

        while (it.hasNext()) {
            NodeMapping currentMapping = it.next();
            if (currentMapping.getName().equals(name)) {
                return currentMapping;
            }

        }
        return null;
    }

    private ContainerMapping getContainerMapping(String name, List<ContainerMapping> listMapping) {

        Iterator<ContainerMapping> it = listMapping.iterator();

        while (it.hasNext()) {
            ContainerMapping currentMapping = it.next();
            if (currentMapping.getName().equals(name)) {
                return currentMapping;
            }

        }
        return null;
    }

    private DDiagramElement findByTargetName(final List<DDiagramElement> list, final String targetName) {
        DDiagramElement found = Iterables.find(list, new Predicate<DDiagramElement>() {
            public boolean apply(DDiagramElement input) {
                return input.getTarget() instanceof ENamedElement && targetName.equals(((ENamedElement) input.getTarget()).getName());
            }
        });
        assertNotNull("'" + targetName + "' element not found", found);
        return found;
    }
}
