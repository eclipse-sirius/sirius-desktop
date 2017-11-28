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
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class MultipleMappingImportTests2 extends SiriusDiagramTestCase implements MultipleMapppingImportsModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH2, MODELER_PATH);
        initViewpoint(MULTIPLE_MAPPING_IMPORT_VIEWPOINT_NAME);
    }

    public void testPreConditionNodeImport() throws Exception {

        DDiagram diagram = (DDiagram) getRepresentations(PRECONDITION_CHANGE_NODE_TREE).toArray()[0];
        refresh(diagram);

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 7 nodes here .", 7, elements.size());

        Layer treeLayer = getLayer(diagram, TREE_LAYER);
        NodeMapping f1Mapping = (NodeMapping) getNodeMapping(F1, treeLayer.getNodeMappings());
        NodeMapping f2Mapping = (NodeMapping) getNodeMapping(F2, treeLayer.getNodeMappings());
        NodeMapping f11Mapping = (NodeMapping) getNodeMapping(F11, treeLayer.getNodeMappings());
        NodeMapping f12Mapping = (NodeMapping) getNodeMapping(F12, treeLayer.getNodeMappings());
        NodeMapping f21Mapping = (NodeMapping) getNodeMapping(F21, treeLayer.getNodeMappings());
        NodeMapping f22Mapping = (NodeMapping) getNodeMapping(F22, treeLayer.getNodeMappings());

        Layer defaultLayer = getLayer(diagram, DEFAULT_LAYER);
        NodeMapping defaultNodeMapping = (NodeMapping) getNodeMapping(F_NODE, defaultLayer.getNodeMappings());

        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();

        List<DDiagramElement> diagramElements = diagram.getOwnedDiagramElements();
        Map<DDiagramElement, Bounds> beforeBounds = getBounds(diagramElements);
        checkMapping(diagramElements, defaultNodeMapping);

        activateLayer(diagram, TREE_LAYER);

        refresh(diagram);

        diagramElements = diagram.getOwnedDiagramElements();
        assertEquals("We should have 7 nodes here .", 7, elements.size());
        assertVisible(diagram.getOwnedDiagramElements());

        Map<DDiagramElement, Bounds> afterBounds = getBounds(diagramElements);
        compareBounds(beforeBounds, afterBounds);
        beforeBounds = afterBounds;

        List<DDiagramElement> elementsWithDefaultMapping = new ArrayList<>();
        elementsWithDefaultMapping.add(findByTargetName(diagramElements, F));
        checkMapping(elementsWithDefaultMapping, defaultNodeMapping);

        List<DDiagramElement> elementsMapping = new ArrayList<>();
        elementsMapping.add(findByTargetName(diagramElements, F1));
        checkMapping(elementsMapping, f1Mapping);

        elementsMapping = new ArrayList<>();
        elementsMapping.add(findByTargetName(diagramElements, F2));
        checkMapping(elementsMapping, f2Mapping);

        elementsMapping = new ArrayList<>();
        elementsMapping.add(findByTargetName(diagramElements, F11));
        checkMapping(elementsMapping, f11Mapping);

        elementsMapping = new ArrayList<>();
        elementsMapping.add(findByTargetName(diagramElements, F12));
        checkMapping(elementsMapping, f12Mapping);

        elementsMapping = new ArrayList<>();
        elementsMapping.add(findByTargetName(diagramElements, F21));
        checkMapping(elementsMapping, f21Mapping);

        elementsMapping = new ArrayList<>();
        elementsMapping.add(findByTargetName(diagramElements, F22));
        checkMapping(elementsMapping, f22Mapping);
    }

    private void assertVisible(final Collection<DDiagramElement> elements) {
        for (final DDiagramElement element : elements) {
            assertTrue(element.isVisible());
        }
    }

    private Map<DDiagramElement, Bounds> getBounds(List<DDiagramElement> diagramElements) {
        Map<DDiagramElement, Bounds> result = new HashMap<DDiagramElement, Bounds>();

        for (final DDiagramElement element : diagramElements) {
            Node gmfView = (Node) getGmfView(element);
            if (gmfView.getLayoutConstraint() instanceof Bounds) {
                final Bounds nodeBounds = (Bounds) gmfView.getLayoutConstraint();
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

    private NodeMapping getNodeMapping(String name, List<NodeMapping> mappings) {
        for (final NodeMapping mapping : mappings) {
            if (mapping.getName().equals(name)) {
                return mapping;
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

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
