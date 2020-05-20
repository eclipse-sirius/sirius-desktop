/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticEdgeFormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticNodeFormatDataKey;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.collect.Maps;

/**
 * Test class for {@link MappingBasedSiriusFormatDataManager} adapted from
 * {@link SiriusFormatDataManagerForSemanticElementsTest}
 * 
 * @author adieumegard
 */
public class MappingBasedSiriusFormatDataManagerTest extends AbstractSiriusFormatDataManagerForSemanticElementsTest {

    /**
     * This test checks that when providing a diagram to the {@link MappingBasedSiriusFormatDataManager} it actually
     * stores data.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBasicStoreFormat() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagram = diag.diagramEditPart;

            Map<EObject, EObject> mapping = buildMappingFromDiagrams(diag);
            final MappingBasedSiriusFormatDataManager manager = new MappingBasedSiriusFormatDataManager(mapping);
            manager.storeFormatData(diagram);

            // Enable this to save referenced data
            if (REGENERATE_TEST_DATA) {
                saveDiagram(diag, manager.getRootNodeFormatData().values());
            }

            assertTrue("Manager should contain data for diagram " + diag.name, manager.containsData());
        }
    }

    /**
     * This test checks that when providing a diagram to the {@link MappingBasedSiriusFormatDataManager} it actually
     * stores the right number of nodes and edges data.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckNumberOfStoredFormats() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagram = diag.diagramEditPart;

            Map<EObject, EObject> mapping = buildMappingFromDiagrams(diag);
            final MappingBasedSiriusFormatDataManager manager = new MappingBasedSiriusFormatDataManager(mapping);
            manager.storeFormatData(diagram);

            final List<NodeFormatData> nodeFormatData = getNodeFormatDataList(manager.getNodeFormatData());
            final List<EdgeFormatData> edgeFormatData = getEdgeFormatDataList(manager.getEdgeFormatData());

            assertEquals("Number of expected node format data is wrong for diagram " + diag.name, diag.numberOfNodeFormatData, nodeFormatData.size());
            assertEquals("Number of expected edge format data is wrong for diagram " + diag.name, diag.numberOfEdgeFormatData, edgeFormatData.size());
        }
    }

    /**
     * This test checks that two different {@link MappingBasedSiriusFormatDataManager} with the same parameters (mapping
     * function and diagram) will store the same format data. In other words checks that the behavior is deterministic.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckConsistentStoreFormat() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagramEditPart = diag.diagramEditPart;

            Map<EObject, EObject> mapping = buildMappingFromDiagrams(diag);
            final MappingBasedSiriusFormatDataManager manager = new MappingBasedSiriusFormatDataManager(mapping);
            manager.storeFormatData(diagramEditPart);
            List<NodeFormatData> firstNodeFormat = getNodeFormatDataList(manager.getRootNodeFormatData());
            List<EdgeFormatData> firstEdgeFormat = getEdgeFormatDataList(manager.getEdgeFormatData());
            for (int i = 0; i < ITERATIONS; i++) {
                final MappingBasedSiriusFormatDataManager otherManager = new MappingBasedSiriusFormatDataManager(mapping);
                otherManager.storeFormatData(diagramEditPart);

                final boolean haveSameFormat = FormatHelper.INSTANCE.haveSameLayout(firstNodeFormat, getNodeFormatDataList(otherManager.getRootNodeFormatData()),
                        ConfigurationFactory.buildConfiguration());
                assertTrue("All node formats should be the same for diagram " + diag.name, haveSameFormat);

                assertTrue("All edge formats should be the same for diagram " + diag.name,
                        FormatHelper.INSTANCE.haveSameLayout(firstEdgeFormat, getEdgeFormatDataList(otherManager.getEdgeFormatData()), ConfigurationFactory.buildConfiguration()));
            }
        }
    }

    /**
     * This test checks that when providing an Node object to the {@link MappingBasedSiriusFormatDataManager} it
     * actually stores correct information for the node.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStoreNodeFormatData() throws Exception {
        openAllDiagramsInRepresentation(DIAG_TYPE2_MYPACKAGE.parent);

        final IGraphicalEditPart editPart = (IGraphicalEditPart) DIAG_TYPE2_MYPACKAGE.diagramEditPart.getChildren().get(0);

        final DRepresentationElement element = (DRepresentationElement) ((View) editPart.getModel()).getElement();
        final String name = element.getName();
        assertEquals("Wrong edit part", "Container p1", name);

        Map<EObject, EObject> mapping = buildMappingFromDiagrams(DIAG_TYPE2_MYPACKAGE);
        final MappingBasedSiriusFormatDataManager manager = new MappingBasedSiriusFormatDataManager(mapping);
        manager.storeFormatData(editPart);
        assertTrue("Manager should contain data for diagram " + DIAG_TYPE2_MYPACKAGE.name, manager.containsData());

        final SemanticNodeFormatDataKey dataKey = new SemanticNodeFormatDataKey(element.getTarget());
        final Map<String, NodeFormatData> nodeFormatDataMap = manager.getNodeFormatData().get(dataKey);

        NodeFormatData nodeFormatData = nodeFormatDataMap.values().iterator().next();
        assertEquals("Wrong width", 653, nodeFormatData.getWidth());
        assertEquals("Wrong height", 173, nodeFormatData.getHeight());

        assertFalse("Node format data sould have children", nodeFormatData.getChildren().isEmpty());

    }

    /**
     * This test checks that when providing an Edge object to the {@link MappingBasedSiriusFormatDataManager} it
     * actually stores correct information for the edge.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStoreEdgeFormatData() throws Exception {
        openAllDiagramsInRepresentation(DIAG_TYPE2_MYPACKAGE.parent);

        final IGraphicalEditPart p1EditPart = (IGraphicalEditPart) DIAG_TYPE2_MYPACKAGE.diagramEditPart.getChildren().get(0);
        final IGraphicalEditPart edgeEditPart = (IGraphicalEditPart) ((IGraphicalEditPart) ((IGraphicalEditPart) p1EditPart.getChildren().get(1)).getChildren().get(0)).getSourceConnections().get(0);

        final DRepresentationElement element = (DRepresentationElement) ((View) edgeEditPart.getModel()).getElement();
        final String name = element.getName();
        assertEquals("Wrong edit part", "aC1-2", name);

        Map<EObject, EObject> mapping = buildMappingFromDiagrams(DIAG_TYPE2_MYPACKAGE);
        final MappingBasedSiriusFormatDataManager manager = new MappingBasedSiriusFormatDataManager(mapping);
        manager.storeFormatData(edgeEditPart);
        assertTrue("Manager should contain data for diagram " + DIAG_TYPE2_MYPACKAGE.name, manager.containsData());

        final SemanticEdgeFormatDataKey dataKey = new SemanticEdgeFormatDataKey(element.getTarget());

        getEdgeFormatDataList(manager.getEdgeFormatData());
        final Map<String, EdgeFormatData> edgeFormatData = manager.getEdgeFormatData().get(dataKey);

        assertEquals("Wrong point list", 4, edgeFormatData.values().iterator().next().getPointList().size());
    }

    private Map<EObject, EObject> buildMappingFromDiagrams(Diagram diagramToCopyFormat) {
        DiagramEditPart rootEditPart = diagramToCopyFormat.diagramEditPart;
        EObject semanticElement = rootEditPart.resolveSemanticElement();
        Set<EObject> eObjectSet = new HashSet<EObject>();
        eObjectSet.add(semanticElement);
        if (semanticElement instanceof DDiagram) {
            ((DDiagram) semanticElement).getOwnedDiagramElements().stream().forEach(child -> {
                eObjectSet.addAll(getChildrenNodes(child));
            });
        }
        return Maps.asMap(eObjectSet, elem -> elem);
    }

    private Set<EObject> getChildrenNodes(DSemanticDecorator node) {
        Set<EObject> eObjectSet = new HashSet<EObject>();
        // Add to set
        eObjectSet.add(node.getTarget());
        // Navigate
        if (node instanceof DNode) {
            ((DNode) node).getOwnedBorderedNodes().stream().forEach(child -> {
                eObjectSet.addAll(getChildrenNodes(child));
            });
        } else if (node instanceof DNodeContainer) {
            ((DNodeContainer) node).getOwnedBorderedNodes().stream().forEach(child -> {
                eObjectSet.addAll(getChildrenNodes(child));
            });
            ((DNodeContainer) node).getOwnedDiagramElements().stream().forEach(child -> {
                eObjectSet.addAll(getChildrenNodes(child));
            });
        } else if (node instanceof DNodeList) {
            ((DNodeList) node).getOwnedBorderedNodes().stream().forEach(element -> {
                eObjectSet.addAll(getChildrenNodes(element));
            });
        }
        if (node instanceof EdgeTarget) {
            ((EdgeTarget) node).getOutgoingEdges().stream().forEach(element -> {
                eObjectSet.addAll(getChildrenNodes(element));
            });
        }
        return eObjectSet;
    }
}
