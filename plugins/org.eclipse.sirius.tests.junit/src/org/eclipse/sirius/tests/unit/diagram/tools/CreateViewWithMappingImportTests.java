/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.CreateView;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;

/**
 * Test that the {@link CreateView} tool's operation create a {@link DDiagramElement} with the correct mapping, i.e. the
 * more specific mapping, for example a mapping import.
 * 
 * See VP-3834.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CreateViewWithMappingImportTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/tools/VP-3834/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3834.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3834.aird";

    private static final String MODELER_RESOURCE_NAME = "VP-3834.odesign";

    private static final String LAYER_L1 = "L1";

    private static final String LAYER_L2 = "L2";

    private static final String LAYER_L3 = "L3";

    private static final String LAYER_L4 = "L4";

    private static final String LAYER_L5 = "L5";

    private static final String CREATE_EPACKAGE_GENERIC_TOOL = "Create EPackage With CreateView";

    private static final String CREATE_ECLASS_GENERIC_TOOL = "Create EClass With CreateView";

    private static final String CREATE_EREFERENCE_GENERIC_TOOL = "Create EReference With CreateView";

    private static final String CREATE_EPACKAGE_ECLASS_EATTRIBUTE_EOPERATION_CONTAINER_TOOL = "Create EPackageEClassEAttributeEOperation With ContainerCreationTool";

    private ContainerMapping containerMapping;

    private ContainerMapping containerMappingImportOfL1;

    private ContainerMapping containerMappingImportOfL2;

    private ContainerMapping containerMappingImportOfL3;

    private ContainerMapping containerMappingImportOfL4;

    private ContainerMapping containerMappingImportOfL5;

    private NodeMapping nodeMapping;

    private NodeMapping nodeMappingImportOfL1;

    private NodeMapping nodeMappingImportOfL2;

    private NodeMapping eClassNodeMappingImportOfL3;

    private NodeMapping eAttributeSubBorderedNodeMappingOfL3;

    private NodeMapping eOperationSubBorderedNodeMappingOfL3;

    private EdgeMapping edgeMapping;

    private EdgeMappingImport edgeMappingImportOfL1;

    private EdgeMappingImport edgeMappingImportOfL2;

    private DDiagram dDiagram;

    private IEditorPart diagramEditor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, MODELER_RESOURCE_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        dDiagram = (DDiagram) getRepresentations("VP-3834_Diagram", semanticModel).iterator().next();

        Layer defaultLayer = dDiagram.getDescription().getDefaultLayer();
        containerMapping = defaultLayer.getContainerMappings().get(0);
        nodeMapping = defaultLayer.getNodeMappings().get(0);
        edgeMapping = defaultLayer.getEdgeMappings().get(0);

        AdditionalLayer l1 = dDiagram.getDescription().getAdditionalLayers().get(0);
        containerMappingImportOfL1 = l1.getContainerMappings().get(0);
        nodeMappingImportOfL1 = l1.getNodeMappings().get(0);
        edgeMappingImportOfL1 = l1.getEdgeMappingImports().get(0);

        DiagramExtensionDescription diagramExtensionDescription = (DiagramExtensionDescription) ((Viewpoint) dDiagram.getDescription().eContainer()).getOwnedRepresentationExtensions().get(0);
        Layer l2 = diagramExtensionDescription.getLayers().get(0);
        containerMappingImportOfL2 = l2.getContainerMappings().get(0);
        nodeMappingImportOfL2 = l2.getNodeMappings().get(0);
        edgeMappingImportOfL2 = l2.getEdgeMappingImports().get(0);

        Layer l3 = diagramExtensionDescription.getLayers().get(1);
        containerMappingImportOfL3 = l3.getContainerMappings().get(0);
        eClassNodeMappingImportOfL3 = containerMappingImportOfL3.getBorderedNodeMappings().get(0);
        eAttributeSubBorderedNodeMappingOfL3 = eClassNodeMappingImportOfL3.getBorderedNodeMappings().get(0);
        eOperationSubBorderedNodeMappingOfL3 = eClassNodeMappingImportOfL3.getBorderedNodeMappings().get(1);

        Layer l4 = diagramExtensionDescription.getLayers().get(2);
        containerMappingImportOfL4 = l4.getContainerMappings().get(0);

        Layer l5 = diagramExtensionDescription.getLayers().get(3);
        containerMappingImportOfL5 = l5.getContainerMappings().get(0);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test that the {@link CreateView} tool's operation create a {@link DDiagramElement} with the correct mapping, i.e.
     * the more specific mapping, for example a mapping import.
     */
    public void testCreateViewWithoutMappingImportWithoutLayer() {
        // Test creation in default layer
        // Test DNodeContainer creation
        DDiagramElement createdEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMapping, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        DNodeContainer createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        assertEquals("The created view should have the FlatContainerStyle", DiagramPackage.Literals.FLAT_CONTAINER_STYLE, createdEPackageDNodeContainer.getStyle().eClass());
        // Test DNode creation
        DDiagramElement createdEClassView = testCreateView(CREATE_ECLASS_GENERIC_TOOL, nodeMapping, dDiagram);
        assertTrue("The created view should be a DNode", createdEClassView instanceof DNode);
        DNode createdEClassDNode = (DNode) createdEClassView;
        assertEquals("The created view should have the Square", DiagramPackage.Literals.SQUARE, createdEClassDNode.getStyle().eClass());
        // Test DEdge creation
        DEdge createdEReferenceDEdge = testCreateEdgeView(CREATE_EREFERENCE_GENERIC_TOOL, edgeMapping, createdEClassDNode);
        assertEquals("The created edge should have the EdgeStyle", DiagramPackage.Literals.EDGE_STYLE, createdEReferenceDEdge.getStyle().eClass());
        Iterable<BasicLabelStyle> labelStyles = Iterables.filter(createdEReferenceDEdge.getOwnedStyle().eContents(), BasicLabelStyle.class);
        assertTrue("The created edge style should have only a center label style", Iterables.size(labelStyles) == 1 && Iterables.getOnlyElement(labelStyles) instanceof CenterLabelStyle);
    }

    /**
     * Test that the {@link CreateView} tool's operation create a {@link DDiagramElement} with the correct mapping, i.e.
     * the more specific mapping, for example a mapping import.
     */
    public void testCreateViewWithMappingImportWithLayerL1() {
        // Test creation in layer L1
        activateLayer(dDiagram, LAYER_L1);
        // Test DNodeContainer creation
        DDiagramElement createdEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMappingImportOfL1, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        DNodeContainer createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        assertEquals("The created view should have the ShapeContainerStyle", DiagramPackage.Literals.SHAPE_CONTAINER_STYLE, createdEPackageDNodeContainer.getStyle().eClass());
        // Test DNode creation
        DDiagramElement createdEClassView = testCreateView(CREATE_ECLASS_GENERIC_TOOL, nodeMappingImportOfL1, dDiagram);
        assertTrue("The created view should be a DNode", createdEClassView instanceof DNode);
        DNode createdEClassDNode = (DNode) createdEClassView;
        assertEquals("The created view should have the Ellipse", DiagramPackage.Literals.ELLIPSE, createdEClassDNode.getStyle().eClass());
        // Test DEdge creation
        DEdge createdEReferenceDEdge = testCreateEdgeView(CREATE_EREFERENCE_GENERIC_TOOL, edgeMappingImportOfL1, createdEClassDNode);
        assertEquals("The created edge should have the BracketEdgeStyle", DiagramPackage.Literals.BRACKET_EDGE_STYLE, createdEReferenceDEdge.getStyle().eClass());
        Iterable<BasicLabelStyle> labelStyles = Iterables.filter(createdEReferenceDEdge.getOwnedStyle().eContents(), BasicLabelStyle.class);
        assertTrue("The created edge style should have only a center label style", Iterables.size(labelStyles) == 1 && Iterables.getOnlyElement(labelStyles) instanceof CenterLabelStyle);

    }

    /**
     * Test that the {@link CreateView} tool's operation create a {@link DDiagramElement} with the correct mapping, i.e.
     * the more specific mapping, for example a mapping import.
     */
    public void testCreateViewWithMappingImportWithLayerL1AndL2() {
        // Test creation in layer L2
        activateLayer(dDiagram, LAYER_L1);
        activateLayer(dDiagram, LAYER_L2);
        // Test DNodeContainer creation
        DDiagramElement createdEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMappingImportOfL2, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        DNodeContainer createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        assertEquals("The created view should have the WorkspaceImage style", DiagramPackage.Literals.WORKSPACE_IMAGE, createdEPackageDNodeContainer.getStyle().eClass());
        // Test DNode creation
        DDiagramElement createdEClassView = testCreateView(CREATE_ECLASS_GENERIC_TOOL, nodeMappingImportOfL2, dDiagram);
        assertTrue("The created view should be a DNode", createdEClassView instanceof DNode);
        DNode createdEClassDNode = (DNode) createdEClassView;
        assertEquals("The created view should have the Lozenge", DiagramPackage.Literals.LOZENGE, createdEClassDNode.getStyle().eClass());
        // Test DEdge creation
        DEdge createdEReferenceDEdge = testCreateEdgeView(CREATE_EREFERENCE_GENERIC_TOOL, edgeMappingImportOfL2, createdEClassDNode);
        assertEquals("The created edge should have the BracketEdgeStyle", DiagramPackage.Literals.BRACKET_EDGE_STYLE, createdEReferenceDEdge.getStyle().eClass());
        Iterable<BasicLabelStyle> labelStyles = Iterables.filter(createdEReferenceDEdge.getOwnedStyle().eContents(), BasicLabelStyle.class);
        assertTrue("The created edge style should have begin/center/end label style", Iterables.size(labelStyles) == 3);
    }

    /**
     * Test that the {@link CreateView} tool's operation create a {@link DDiagramElement} with the correct mapping, i.e.
     * the more specific mapping, for example a mapping import.
     */
    public void testCreateViewInContainerViewWithoutMappingImportWithoutLayer() {
        // Test creation in default layer
        // Test DNodeContainer creation
        DDiagramElement createdEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMapping, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        DNodeContainer createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        DDiagramElement createdSubEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMapping, createdEPackageDNodeContainer);
        assertTrue("The created view should be a DNodeContainer", createdSubEPackageView instanceof DNodeContainer);
        DNodeContainer createdSubEPackageDNodeContainer = (DNodeContainer) createdSubEPackageView;
        assertEquals("The created view should have the FlatContainerStyle", DiagramPackage.Literals.FLAT_CONTAINER_STYLE, createdSubEPackageDNodeContainer.getStyle().eClass());

        // Test DNode creation
        DDiagramElement createdEClassView = testCreateView(CREATE_ECLASS_GENERIC_TOOL, nodeMapping, createdEPackageDNodeContainer);
        assertTrue("The created view should be a DNode", createdEClassView instanceof DNode);
        DNode createdEClassDNode = (DNode) createdEClassView;
        assertEquals("The created view should have the Square", DiagramPackage.Literals.SQUARE, createdEClassDNode.getStyle().eClass());
        // Test DEdge creation
        DEdge createdEReferenceDEdge = testCreateEdgeView(CREATE_EREFERENCE_GENERIC_TOOL, edgeMapping, createdEClassDNode);
        assertEquals("The created edge should have the EdgeStyle", DiagramPackage.Literals.EDGE_STYLE, createdEReferenceDEdge.getStyle().eClass());
        Iterable<BasicLabelStyle> labelStyles = Iterables.filter(createdEReferenceDEdge.getOwnedStyle().eContents(), BasicLabelStyle.class);
        assertTrue("The created edge style should have only a center label style", Iterables.size(labelStyles) == 1 && Iterables.getOnlyElement(labelStyles) instanceof CenterLabelStyle);
    }

    /**
     * Test that the {@link CreateView} tool's operation create a {@link DDiagramElement} with the correct mapping, i.e.
     * the more specific mapping, for example a mapping import.
     */
    public void testCreateViewInContainerViewWithMappingImportWithLayerL1() {
        // Test creation in layer L1
        activateLayer(dDiagram, LAYER_L1);
        // Test DNodeContainer creation
        DDiagramElement createdEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMappingImportOfL1, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        DNodeContainer createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        DDiagramElement createdSubEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMappingImportOfL1, createdEPackageDNodeContainer);
        assertTrue("The created view should be a DNodeContainer", createdSubEPackageView instanceof DNodeContainer);
        DNodeContainer createdSubEPackageDNodeContainer = (DNodeContainer) createdSubEPackageView;
        assertEquals("The created view should have the ShapeContainerStyle", DiagramPackage.Literals.SHAPE_CONTAINER_STYLE, createdSubEPackageDNodeContainer.getStyle().eClass());
        checkSemanticEmptyCandidateExpression(createdSubEPackageDNodeContainer.getActualMapping());

        // Test DNode creation
        DDiagramElement createdEClassView = testCreateView(CREATE_ECLASS_GENERIC_TOOL, nodeMappingImportOfL1, createdEPackageDNodeContainer);
        assertTrue("The created view should be a DNode", createdEClassView instanceof DNode);
        DNode createdEClassDNode = (DNode) createdEClassView;
        assertEquals("The created view should have the Ellipse", DiagramPackage.Literals.ELLIPSE, createdEClassDNode.getStyle().eClass());
        // Test DEdge creation
        DEdge createdEReferenceDEdge = testCreateEdgeView(CREATE_EREFERENCE_GENERIC_TOOL, edgeMappingImportOfL1, createdEClassDNode);
        assertEquals("The created edge should have the BracketEdgeStyle", DiagramPackage.Literals.BRACKET_EDGE_STYLE, createdEReferenceDEdge.getStyle().eClass());
        Iterable<BasicLabelStyle> labelStyles = Iterables.filter(createdEReferenceDEdge.getOwnedStyle().eContents(), BasicLabelStyle.class);
        assertTrue("The created edge style should have only a center label style", Iterables.size(labelStyles) == 1 && Iterables.getOnlyElement(labelStyles) instanceof CenterLabelStyle);
        checkSemanticEmptyCandidateExpression(new IEdgeMappingQuery(createdEReferenceDEdge.getActualMapping()).getEdgeMapping().get());
    }

    private void checkSemanticEmptyCandidateExpression(DiagramElementMapping mapping) {
        assertTrue("A new test should be written to check behavior of create view with empty semantic candidates.", StringUtil.isEmpty(mapping.getSemanticCandidatesExpression()));
    }

    /**
     * Test that the {@link CreateView} tool's operation create a {@link DDiagramElement} with the correct mapping, i.e.
     * the more specific mapping, for example a mapping import.
     */
    public void testCreateViewInContainerViewWithMappingImportWithLayerL1AndL2() {
        // Test creation in layer L2
        activateLayer(dDiagram, LAYER_L1);
        activateLayer(dDiagram, LAYER_L2);
        // Test DNodeContainer creation
        DDiagramElement createdEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMappingImportOfL2, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        DNodeContainer createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        DDiagramElement createdSubEPackageView = testCreateView(CREATE_EPACKAGE_GENERIC_TOOL, containerMappingImportOfL2, createdEPackageDNodeContainer);
        assertTrue("The created view should be a DNodeContainer", createdSubEPackageView instanceof DNodeContainer);
        DNodeContainer createdSubEPackageDNodeContainer = (DNodeContainer) createdSubEPackageView;
        assertEquals("The created view should have the WorkspaceImage style", DiagramPackage.Literals.WORKSPACE_IMAGE, createdSubEPackageDNodeContainer.getStyle().eClass());

        // Test DNode creation
        DDiagramElement createdEClassView = testCreateView(CREATE_ECLASS_GENERIC_TOOL, nodeMappingImportOfL2, createdEPackageDNodeContainer);
        assertTrue("The created view should be a DNode", createdEClassView instanceof DNode);
        DNode createdEClassDNode = (DNode) createdEClassView;
        assertEquals("The created view should have the Lozenge", DiagramPackage.Literals.LOZENGE, createdEClassDNode.getStyle().eClass());
        // Test DEdge creation
        DEdge createdEReferenceDEdge = testCreateEdgeView(CREATE_EREFERENCE_GENERIC_TOOL, edgeMappingImportOfL2, createdEClassDNode);
        assertEquals("The created edge should have the BracketEdgeStyle", DiagramPackage.Literals.BRACKET_EDGE_STYLE, createdEReferenceDEdge.getStyle().eClass());
        Iterable<BasicLabelStyle> labelStyles = Iterables.filter(createdEReferenceDEdge.getOwnedStyle().eContents(), BasicLabelStyle.class);
        assertTrue("The created edge style should have begin/center/end label style", Iterables.size(labelStyles) == 3);
    }

    /**
     * Test the effect of {@link AbstractMappingImport#isHideSubMappings()} attribute on creation of view in manual
     * refresh.
     */
    public void testSubMappingImportThroughHideSubMappingsAttribute() {
        // Test creation of a EPackage with a EClass owning a EAttribute and a
        // EOperation
        activateLayer(dDiagram, LAYER_L1);
        activateLayer(dDiagram, LAYER_L2);
        activateLayer(dDiagram, LAYER_L3);

        // Test DNodeContainer creation with a DNode owning 2 DNode as
        // borderedNodes, as child
        DDiagramElement createdEPackageView = testCreateView(CREATE_EPACKAGE_ECLASS_EATTRIBUTE_EOPERATION_CONTAINER_TOOL, containerMappingImportOfL3, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        DNodeContainer createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        assertEquals("The created DNodeContainer should contains a DNode", 1, createdEPackageDNodeContainer.getOwnedBorderedNodes().size());
        assertTrue("The created DNodeContainer should contains a DNode", createdEPackageDNodeContainer.getOwnedBorderedNodes().get(0) instanceof DNode);
        DNode subDNode = createdEPackageDNodeContainer.getOwnedBorderedNodes().get(0);
        assertEquals("The mapping of the subDNode should be " + eClassNodeMappingImportOfL3.getName(), eClassNodeMappingImportOfL3, subDNode.getActualMapping());
        assertEquals("The created subDNode should have 2 borderedNodes", 2, subDNode.getOwnedBorderedNodes().size());
        DNode eAttributeBorderedDNode = subDNode.getOwnedBorderedNodes().get(0);
        DNode eOperationBorderedDNode = subDNode.getOwnedBorderedNodes().get(1);
        assertEquals("The mapping of the eAttribute borderedDNode should be " + eAttributeSubBorderedNodeMappingOfL3.getName(), eAttributeSubBorderedNodeMappingOfL3,
                eAttributeBorderedDNode.getActualMapping());
        assertEquals("The mapping of the eOperation borderedDNode should be " + eAttributeSubBorderedNodeMappingOfL3.getName(), eOperationSubBorderedNodeMappingOfL3,
                eOperationBorderedDNode.getActualMapping());

        // Test DNodeContainer creation in layer L4 : only the DNodeContainer
        // should be created as the hideSubMappings attribute of the mapping in
        // L4 is at true
        activateLayer(dDiagram, LAYER_L4);
        createdEPackageView = testCreateView(CREATE_EPACKAGE_ECLASS_EATTRIBUTE_EOPERATION_CONTAINER_TOOL, containerMappingImportOfL4, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        assertEquals("The created DNodeContainer should not contains a children", 0, createdEPackageDNodeContainer.getOwnedDiagramElements().size());
        assertEquals("The created DNodeContainer should not contains a children", 0, createdEPackageDNodeContainer.getOwnedBorderedNodes().size());

        activateLayer(dDiagram, LAYER_L5);
        createdEPackageView = testCreateView(CREATE_EPACKAGE_ECLASS_EATTRIBUTE_EOPERATION_CONTAINER_TOOL, containerMappingImportOfL5, dDiagram);
        assertTrue("The created view should be a DNodeContainer", createdEPackageView instanceof DNodeContainer);
        createdEPackageDNodeContainer = (DNodeContainer) createdEPackageView;
        assertEquals("The created DNodeContainer should not contains a childrenNode", 0, createdEPackageDNodeContainer.getOwnedDiagramElements().size());
        assertEquals("The created DNodeContainer should not contains a childrenNode", 0, createdEPackageDNodeContainer.getOwnedBorderedNodes().size());
    }

    private DDiagramElement testCreateView(String creationToolToUse, DiagramElementMapping diagramElementMapping, DragAndDropTarget containerView) {
        DDiagramElement createdView = null;
        boolean viewCreated = applyNodeCreationTool(creationToolToUse, dDiagram, containerView);
        TestsUtil.synchronizationWithUIThread();
        assertTrue("The view has not been created", viewCreated);
        List<DDiagramElement> ownedDiagramElements = getOwnedDiagramElements(containerView);
        createdView = ownedDiagramElements.get(ownedDiagramElements.size() - 1);
        assertEquals("The mapping of the created view should be the " + diagramElementMapping.eClass().getName() + " of the default layer", diagramElementMapping, createdView.getMapping());
        return createdView;
    }

    private List<DDiagramElement> getOwnedDiagramElements(DragAndDropTarget containerView) {
        List<DDiagramElement> ownedDiagramElements = new ArrayList<DDiagramElement>();
        if (containerView instanceof DDiagram) {
            DDiagram dDiagram = (DDiagram) containerView;
            ownedDiagramElements.addAll(dDiagram.getOwnedDiagramElements());
        } else if (containerView instanceof DNodeContainer) {
            DNodeContainer dNodeContainer = (DNodeContainer) containerView;
            ownedDiagramElements.addAll(dNodeContainer.getOwnedDiagramElements());
        }
        return ownedDiagramElements;
    }

    private DEdge testCreateEdgeView(String creationToolToUse, EObject edgeMapping, EdgeTarget edgeTarget) {
        DEdge createdEdge = null;
        boolean edgeCreated = applyNodeCreationTool(creationToolToUse, dDiagram, edgeTarget);
        assertTrue("The edge has not been created", edgeCreated);
        createdEdge = dDiagram.getEdges().get(dDiagram.getEdges().size() - 1);
        EObject realEdgeMapping = createdEdge.getMapping();
        while (realEdgeMapping instanceof EdgeMappingImportWrapper) {
            realEdgeMapping = ((EdgeMappingImportWrapper) realEdgeMapping).getWrappedEdgeMappingImport();
        }
        assertEquals("The mapping of the created edge should be the " + edgeMapping.eClass().getName() + " of the default layer", edgeMapping, realEdgeMapping);
        return createdEdge;
    }

    @Override
    public void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        diagramEditor = null;
        containerMapping = null;
        containerMappingImportOfL1 = null;
        containerMappingImportOfL2 = null;
        nodeMapping = null;
        nodeMappingImportOfL1 = null;
        nodeMappingImportOfL2 = null;
        edgeMapping = null;
        edgeMappingImportOfL1 = null;
        edgeMappingImportOfL2 = null;
        dDiagram = null;
        super.tearDown();
    }
}
