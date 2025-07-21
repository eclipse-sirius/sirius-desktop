/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.style;

import java.awt.Dimension;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * 
 * @author mporhel
 */
public abstract class AbstractNodeSizeTest extends SiriusDiagramTestCase {

    /**
     * The id of the outline view.
     */
    private static final String OUTLINE_VIEW_ID = "org.eclipse.ui.views.ContentOutline";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/vp973/vp973.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/vp973/vp973.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/vp973/vp973.odesign";

    protected static final String REPRESENTATION_DESC_NAME = "vp973";

    private static final Dimension EXPECTED_DIMENSION = new Dimension(20, 20);

    protected DDiagram diagram;

    protected DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Close outline to improve performance (this test is very long if the
        // outline is open)
        EclipseUIUtil.hideView(OUTLINE_VIEW_ID);

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
    }

    @Override
    protected Integer getGridSpacing() {
        // Use a value that is not multiple of 20, the default dimension, to easily detect differences between with and
        // without snapToGrid enabled.
        return 17;
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);

        // Reopen the Outline (for the rest of suite)
        EclipseUIUtil.showView(OUTLINE_VIEW_ID);

        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }

    /**
     * 
     * Validates the border size of a Node mapping with Square description.
     * 
     * @throws Exception
     */
    public void testNode1Size() throws Exception {
        DiagramElementType nodeType = new DiagramElementType(false, false);
        DiagramElementType containerType = null;
        doTest(Optional.ofNullable(nodeType), Optional.ofNullable(containerType));
    }

    /**
     * 
     * Validates the border size of a Node mapping with Square description.
     * 
     * @throws Exception
     */
    public void testNode2Size() throws Exception {
        DiagramElementType nodeType = new DiagramElementType(false, true);
        DiagramElementType containerType = new DiagramElementType(false, false);
        doTest(Optional.ofNullable(nodeType), Optional.ofNullable(containerType));
    }

    /**
     * 
     * Validates the border size of a Node mapping with Square description.
     * 
     * @throws Exception
     */
    public void testNode2ContainedSize() throws Exception {
        DiagramElementType nodeType = new DiagramElementType(false, true);
        DiagramElementType containerType = new DiagramElementType(true, false);
        doTest(Optional.ofNullable(nodeType), Optional.ofNullable(containerType));
    }

    /**
     * 
     * Validates the border size of a Node mapping with Square description.
     * 
     * @throws Exception
     */
    public void testNode3Size() throws Exception {
        DiagramElementType nodeType = new DiagramElementType(true, false);
        DiagramElementType containerType = new DiagramElementType(false, false, true);
        doTest(Optional.ofNullable(nodeType), Optional.ofNullable(containerType));
    }

    /**
     * 
     * Validates the border size of a Node mapping with Square description.
     * 
     * @throws Exception
     */
    public void testNode4Size() throws Exception {
        DiagramElementType nodeType = new DiagramElementType(false, true);
        DiagramElementType containerType = new DiagramElementType(false, false, true);
        doTest(Optional.ofNullable(nodeType), Optional.ofNullable(containerType));
    }

    /**
     * 
     * Validates the border size of a Node mapping with Square description.
     * 
     * @throws Exception
     */
    public void testNode4RecSize() throws Exception {
        DiagramElementType nodeType = new DiagramElementType(false, true);
        DiagramElementType parentType = new DiagramElementType(false, true);
        doTest(Optional.ofNullable(nodeType), Optional.ofNullable(parentType));
    }

    private void doTest(Optional<DiagramElementType> nodeType, Optional<DiagramElementType> parentType) throws Exception {
        String labelEnd = "_new EClass 1";
        doTest("Square" + labelEnd, nodeType, parentType);
        doTest("Lozenge" + labelEnd, nodeType, parentType);
        doTest("Ellipse" + labelEnd, nodeType, parentType);
        doTest("Bundle_Image_Square" + labelEnd, nodeType, parentType);
        doTest("Bundle_Image_Stroke" + labelEnd, nodeType, parentType);
        doTest("Bundle_Image_Triangle" + labelEnd, nodeType, parentType);
        doTest("Bundle_Image_Dot" + labelEnd, nodeType, parentType);
        doTest("Bundle_Image_Ring" + labelEnd, nodeType, parentType);
        doTest("Note" + labelEnd, nodeType, parentType);
        doTest("Dot" + labelEnd, nodeType, parentType);
        doTest("Gauge" + labelEnd, nodeType, parentType);
        doTest("Workspace_Image" + labelEnd, nodeType, parentType);
    }

    /**
     * 
     * Validates the border size of a Container mapping with Workspace Image
     * container description.
     * 
     * @throws Exception
     */
    protected void doTest(String label, Optional<DiagramElementType> nodeType, Optional<DiagramElementType> parentType) throws Exception {

        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, label);

        Predicate<DNode> nodeFilter = new NodeFilter(nodeType);
        Predicate<DNode> parentFilter = new ParentFilter(parentType);

        Iterable<DNode> dNodes = Iterables.filter(diagramElementsFromLabel, DNode.class);
        Iterable<DNode> nodeOK = Iterables.filter(dNodes, nodeFilter);
        Iterable<DNode> nodeWithParentOK = Iterables.filter(nodeOK, parentFilter);
        DNode node = Iterables.getOnlyElement(nodeWithParentOK);

        validateSiriusSize(node);
        validateNotationSize(node);
        validateDraw2DSize(node);
    }

    private void validateSiriusSize(DNode node) {
        assertNotNull(node);
        assertEquals("The DNode " + node.getName() + " do not have the expect width", EXPECTED_DIMENSION.width, node.getWidth().intValue());
        assertEquals("The DNode " + node.getName() + " do not have the expect height", EXPECTED_DIMENSION.height, node.getHeight().intValue());
    }

    protected void validateNotationSize(DNode node) {
        Node gmfNode = getGmfNode(node);
        assertNotNull(gmfNode);

        Size size = (Size) gmfNode.getLayoutConstraint();

        assertEquals("The notation node " + node.getName() + " do not have the expect width", LayoutUtils.SCALE * EXPECTED_DIMENSION.width, size.getWidth());
        assertEquals("The notation node " + node.getName() + " do not have the expect height", LayoutUtils.SCALE * EXPECTED_DIMENSION.height, size.getWidth());
    }

    protected void validateDraw2DSize(DNode node) {
        IGraphicalEditPart editPart = getEditPart(node);
        assertNotNull(editPart);

        IFigure figure = editPart.getFigure();
        assertNotNull(figure);

        Rectangle bounds = figure.getBounds();
        assertEquals("The figure node " + node.getName() + " do not have the expect width", LayoutUtils.SCALE * EXPECTED_DIMENSION.width, bounds.width);
        assertEquals("The figure node " + node.getName() + " do not have the expect height", LayoutUtils.SCALE * EXPECTED_DIMENSION.height, bounds.height);

    }

    protected class DiagramElementType {
        boolean bordered;

        boolean contained;

        private boolean container;

        public DiagramElementType(boolean contained, boolean bordered) {
            this(contained, bordered, false);
        }

        public DiagramElementType(boolean contained, boolean bordered, boolean container) {
            assertFalse("Cannot be bordered and contained", contained && bordered);
            this.bordered = bordered;
            this.contained = contained;
            this.container = container;
        }

        public boolean isContained() {
            return contained;
        }

        public boolean isBordered() {
            return bordered;
        }

        public boolean isContainer() {
            return container;
        }
    }

    private class NodeFilter implements Predicate<DNode> {

        private Optional<DiagramElementType> nodeType;

        public NodeFilter(Optional<DiagramElementType> nodeType) {
            this.nodeType = Objects.requireNonNull(nodeType);
        }

        @Override
        public boolean apply(DNode input) {
            boolean expectedType = false;
            if (nodeType.isPresent()) {
                if (nodeType.get().isBordered()) {
                    expectedType = DiagramPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes().equals(input.eContainingFeature());
                } else if (nodeType.get().isContained()) {
                    expectedType = DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements().equals(input.eContainingFeature());
                } else {
                    expectedType = input.getParentDiagram().equals(input.eContainer());
                }
            }
            return expectedType;
        }
    };

    private class ParentFilter implements Predicate<DNode> {

        private Optional<DiagramElementType> parentType;

        public ParentFilter(Optional<DiagramElementType> parentType) {
            this.parentType = Objects.requireNonNull(parentType);
        }

        @Override
        public boolean apply(DNode input) {
            boolean expectedParentType = false;
            EObject parent = input.eContainer();
            if (!parentType.isPresent()) {
                expectedParentType = input.getParentDiagram().equals(parent);
            } else {
                expectedParentType = parentType.get().isContainer() ? parent instanceof DNodeContainer : parent instanceof DNode;

                if (parentType.get().isBordered()) {
                    expectedParentType = expectedParentType && DiagramPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes().equals(parent.eContainingFeature());
                } else if (parentType.get().isContained()) {
                    expectedParentType = expectedParentType && DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements().equals(parent.eContainingFeature());
                } else {
                    expectedParentType = expectedParentType && input.getParentDiagram().equals(parent.eContainer());
                }
            }
            return expectedParentType;
        }
    };
}
