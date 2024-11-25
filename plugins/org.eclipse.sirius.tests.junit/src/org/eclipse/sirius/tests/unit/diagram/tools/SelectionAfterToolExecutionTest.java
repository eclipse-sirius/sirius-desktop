/*******************************************************************************
 * Copyright (c) 2015, 2024 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Check selection in diagram after tool execution.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SelectionAfterToolExecutionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/tools/selection/";

    private static final String SEMANTIC_RESOURCE_NAME = "testVSMForSelection.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "testVSMForSelection.aird";

    private static final String MODELER_RESOURCE_NAME = "VSMForSelection.odesign";

    private static final String CONTAINER_CREATION_TOOL = "Create_sub_class_with_Views";

    private static final String CONTAINER_CREATION_TOOL2 = "Create_two_sub_class";

    private static final String EDGE_CREATION_TOOL = "InOutEdge";

    private static final String GENERIC_CREATION_TOOL = "Composite_With_Views_Creation";

    private DDiagram diagramForSelection;

    private DDiagramEditor editorForSelection;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, MODELER_RESOURCE_NAME);
        genericSetUp(Collections.singleton(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Arrays.asList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME),
                TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);

        diagramForSelection = (DDiagram) getRepresentations("Diagram", semanticModel).iterator().next();
    }

    protected void endSetUp(boolean enableCreatedElementsConstraintInSelectElementsListener) {
        if (!enableCreatedElementsConstraintInSelectElementsListener) {
            System.setProperty("org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener", "false"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        try {
            editorForSelection = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagramForSelection, new NullProgressMonitor());
            TestsUtil.emptyEventsFromUIThread();
        } finally {
            if (!enableCreatedElementsConstraintInSelectElementsListener) {
                System.setProperty("org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener", "true"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
    }

    /**
     * Test selection after container creation
     */
    public void testSelectionAfterContainerCreation() {
        endSetUp(true);
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);
        DDiagramElement nodeB = diagramForSelection.getDiagramElements().get(3);
        final AbstractToolDescription tool = getTool(diagramForSelection, CONTAINER_CREATION_TOOL);

        changeSelectionExpression(tool, "[containerView.eContainer()/]", false);
        applyContainerCreationTool(CONTAINER_CREATION_TOOL, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check the selection is empty if a DRepresentation is found
        checkExpectedElementsInSelection(editorForSelection, null, 0);

        changeSelectionExpression(tool, "", true);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check inverted default selection
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("Edge source:NodeList SubA", "NodeList SubA"), 2);

        changeSelectionExpression(tool, "[/]", true);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check empty selection
        checkExpectedElementsInSelection(editorForSelection, null, 0);

        changeSelectionExpression(tool, "[subClassView/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL, diagramForSelection, nodeB);
        TestsUtil.synchronizationWithUIThread();
        // check the selection containing a DRepresentationElement
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("NodeList SubB"), 1);

        changeSelectionExpression(tool, "var:containerView", false);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL, diagramForSelection, nodeB);
        TestsUtil.synchronizationWithUIThread();
        // check the selection is is empty, because the system property
        // "org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener" has its default value, and
        // the existing DDiagramElement corresponding to variable containerView (ie class B) is not in the list of
        // created elements during the tool execution
        checkExpectedElementsInSelection(editorForSelection, null, 0);
    }

    /**
     * Test selection after container creation with the system property
     * "org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener" set to false and check that the
     * behavior is not the same when the "elements to select" expression does not contain the created elements. The
     * scenario is the same than the last of {@link #testSelectionAfterContainerCreation()}, but with a different
     * expected result.
     */
    public void testSelectionAfterContainerCreation_withConstraintPropertySystemSetToFalse() {
        endSetUp(false);
        DDiagramElement nodeB = diagramForSelection.getDiagramElements().get(3);
        final AbstractToolDescription tool = getTool(diagramForSelection, CONTAINER_CREATION_TOOL);

        changeSelectionExpression(tool, "var:containerView", false);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL, diagramForSelection, nodeB);
        TestsUtil.synchronizationWithUIThread();
        // check the selection is the existing DNode corresponding to variable containerView (ie class B). New
        // behavior according to system property set to false and used in SelectDRepresentationElementsListener.
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList(nodeB.getName()), 1);
    }

    /**
     * Test selection after container creation for expression containing semantic elements
     */
    public void testSelectionFromSemanticElementAfterContainerCreation() {
        endSetUp(true);
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);

        final AbstractToolDescription tool = getTool(diagramForSelection, CONTAINER_CREATION_TOOL2);

        changeSelectionExpression(tool, "", false);
        applyContainerCreationTool(CONTAINER_CREATION_TOOL2, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check default selection
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("NodeList SubA_1", "NodeList SubA_2", "Edge source:NodeList SubA_1", "Edge source:NodeList SubA_2"), 4);

        changeSelectionExpression(tool, "", true);
        applyContainerCreationTool(CONTAINER_CREATION_TOOL2, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check inverted default selection
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("Edge source:NodeList SubA_2", "Edge source:NodeList SubA_1", "NodeList SubA_2", "NodeList SubA_1"), 4);

        changeSelectionExpression(tool, "[subClass->asSequence()->including(subClass2)/]", false);
        applyContainerCreationTool(CONTAINER_CREATION_TOOL2, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check that selection is the same as default when all semantic element
        // are found
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("NodeList SubA_1", "Edge source:NodeList SubA_1", "NodeList SubA_2", "Edge source:NodeList SubA_2"), 4);

        changeSelectionExpression(tool, "[subClass2->asSequence()->including(subClass)/]", true);
        applyContainerCreationTool(CONTAINER_CREATION_TOOL2, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check that the order is reverted. Both the lists of several
        // graphical elements corresponding to the same semantic element AND the
        // element in each list must be reverted
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("Edge source:NodeList SubA_1", "NodeList SubA_1", "Edge source:NodeList SubA_2", "NodeList SubA_2"), 4);

        changeSelectionExpression(tool, "[subClass2/]", true);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL2, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check the selection with subSet of created semantic elements
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("Edge source:NodeList SubA_2", "NodeList SubA_2"), 2);

        changeSelectionExpression(tool, "var:container", false);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL2, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check the selection is is empty, because the system property
        // "org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener" has its default value, and
        // the existing DDiagramElement corresponding to variable container (ie class A) is not in the list of
        // created elements during the tool execution
        checkExpectedElementsInSelection(editorForSelection, null, 0);

    }

    /**
     * Test selection after container creation for expression containing semantic elements, with the system property
     * "org.eclipse.sirius.ui.enableCreatedElementsConstraintInSelectElementsListener" set to false and check that the
     * behavior is not the same when the "elements to select" expression does not contain the created elements. The
     * scenario is the same than the last of {@link #testSelectionFromSemanticElementAfterContainerCreation()}, but with
     * a different expected result.
     */
    public void testSelectionFromSemanticElementAfterContainerCreation_withConstraintPropertySystemSetToFalse() {
        endSetUp(false);
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);

        final AbstractToolDescription tool = getTool(diagramForSelection, CONTAINER_CREATION_TOOL2);

        changeSelectionExpression(tool, "var:container", false);
        TestsUtil.synchronizationWithUIThread();
        applyContainerCreationTool(CONTAINER_CREATION_TOOL2, diagramForSelection, nodeA);
        TestsUtil.synchronizationWithUIThread();
        // check the selection is the existing DNode corresponding to variable container (ie class A). New
        // behavior according to system property set to false and used in SelectDRepresentationElementsListener.
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList(nodeA.getName()), 1);
    }

    /**
     * Test selection after edge creation
     */
    public void testSelectionAfterEdgeCreation() {
        endSetUp(true);
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);
        DDiagramElement nodeB = diagramForSelection.getDiagramElements().get(3);

        final AbstractToolDescription tool = getTool(diagramForSelection, EDGE_CREATION_TOOL);

        changeSelectionExpression(tool, "", false);
        applyEdgeCreationTool(EDGE_CREATION_TOOL, diagramForSelection, ((EdgeTarget) nodeA), ((EdgeTarget) nodeB));
        TestsUtil.synchronizationWithUIThread();
        // check the default selection
        checkExpectedElementsInSelection(editorForSelection, null, 3);

        changeSelectionExpression(tool, "[inOutRefView/]", false);
        applyEdgeCreationTool(EDGE_CREATION_TOOL, diagramForSelection, ((EdgeTarget) nodeA), ((EdgeTarget) nodeB));
        TestsUtil.synchronizationWithUIThread();
        // check the selection with a DRepresentationElement
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("Edge source:Node"), 1);
    }

    /**
     * Test selection after generic tool
     */
    public void testSelectionAfterGenericTool() {
        endSetUp(true);
        final AbstractToolDescription tool = getTool(diagramForSelection, GENERIC_CREATION_TOOL);

        changeSelectionExpression(tool, "[viewForComponent/]", false);
        applyGenericTool(GENERIC_CREATION_TOOL, diagramForSelection, diagramForSelection);
        TestsUtil.synchronizationWithUIThread();
        // check the selection
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("NodeList Component"), 1);
    }

    /**
     * Test selection when no objects have been notified according to SelectDRepresentationElementsListener filter. In
     * that case the selected elements are
     * <ul>
     * <li>the result of interpreted query if provided.</li>
     * <li>not changed if query expression is empty</li>
     * </ul>
     */
    public void testSelectionAfterDirectEditTool() {
        endSetUp(true);
        final String tool_Name = "Edit Name";
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);

        final AbstractToolDescription tool = getTool(diagramForSelection, tool_Name);

        changeSelectionExpression(tool, "[self.eContainer().eContents(EClass)/]", false);
        TestsUtil.synchronizationWithUIThread();
        applyDirectEditTool(tool_Name, diagramForSelection, nodeA, "A1");
        TestsUtil.synchronizationWithUIThread();

        // check the selection
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("NodeList A1", "NodeList B", "NodeList C"), 3);

        changeSelectionExpression(tool, "", false);
        TestsUtil.synchronizationWithUIThread();
        applyDirectEditTool(tool_Name, diagramForSelection, nodeA, "A2");
        TestsUtil.synchronizationWithUIThread();

        // check the selection is the same than previous
        checkExpectedElementsInSelection(editorForSelection, Arrays.asList("NodeList A2", "NodeList B", "NodeList C"), 3);
    }

    /**
     * Check that variables are recognized during expression run time computation for NodeCreation
     */
    public void testRunTimeVariableAfterNodeCreation() {
        endSetUp(true);
        final String tool_Name = "Package Creation";
        DDiagramElement nodeP1 = diagramForSelection.getDiagramElements().get(1);
        final AbstractToolDescription tool = getTool(diagramForSelection, tool_Name);

        changeSelectionExpression(tool, "[container->including(containerView)/]", false);
        applyNodeCreationTool(tool_Name, diagramForSelection, nodeP1);

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that variables are recognized during expression run time computation for ContainerCreation
     */
    public void testRunTimeVariableAfterContainerCreation() {
        endSetUp(true);
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);
        final AbstractToolDescription tool = getTool(diagramForSelection, CONTAINER_CREATION_TOOL);

        changeSelectionExpression(tool, "[container->including(containerView)/]", false);
        applyContainerCreationTool(CONTAINER_CREATION_TOOL, diagramForSelection, nodeA);

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that variables are recognized during expression run time computation for EdgeCreation
     */
    public void testRunTimeVariableAfterEdgeCreation() {
        endSetUp(true);
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);
        DDiagramElement nodeB = diagramForSelection.getDiagramElements().get(3);

        final AbstractToolDescription tool = getTool(diagramForSelection, EDGE_CREATION_TOOL);

        changeSelectionExpression(tool, "[source->including(target)->including(sourceView)->including(targetView)/]", false);
        applyEdgeCreationTool(EDGE_CREATION_TOOL, diagramForSelection, ((EdgeTarget) nodeA), ((EdgeTarget) nodeB));

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that variables are recognized during expression run time computation for Reconnect
     */
    public void testRunTimeVariableAfterReconnect() {
        endSetUp(true);
        final String tool_Name = "ReconnectEReference";
        DDiagramElement nodeB = diagramForSelection.getDiagramElements().get(3);
        DDiagramElement nodeC = diagramForSelection.getDiagramElements().get(4);
        DDiagramElement edgeAB = diagramForSelection.getDiagramElements().get(0);

        final AbstractToolDescription tool = getTool(diagramForSelection, tool_Name);

        changeSelectionExpression(tool, "[edgeView->including(element)->including(otherEnd)->including(source)->including(target)->including(sourceView)->including(targetView)/]", false);
        applyEdgeReconnectionTool(tool_Name, diagramForSelection, (DEdge) edgeAB, (EdgeTarget) nodeB, (EdgeTarget) nodeC);

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that variables are recognized during expression run time computation for ContainerDrop
     */
    public void testRunTimeVariableAfterContainerDrop() {
        endSetUp(true);
        final String tool_Name = "Drop EClass";
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);
        DDiagramElement p1 = diagramForSelection.getDiagramElements().get(1);

        final AbstractToolDescription tool = getTool(diagramForSelection, tool_Name);

        changeSelectionExpression(tool, "[oldSemanticContainer->including(newSemanticContainer)->including(element)->including(newContainerView)/]", false);
        applyContainerDropDescriptionTool(diagramForSelection, tool_Name, (DragAndDropTarget) p1, nodeA);

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that variables are recognized during expression run time computation for Deletion
     */
    public void testRunTimeVariableAfterDeletion() {
        endSetUp(true);
        final String tool_Name = "Delete Class";
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);

        final AbstractToolDescription tool = getTool(diagramForSelection, tool_Name);

        changeSelectionExpression(tool, "[element->including(elementView)->including(containerView)/]", false);
        applyDeletionTool(nodeA);

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that variables are recognized during expression run time computation for DirectEdit
     */
    public void testRunTimeVariableAfterDirectEdit() {
        endSetUp(true);
        final String tool_Name = "Edit Name";
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);

        final AbstractToolDescription tool = getTool(diagramForSelection, tool_Name);

        changeSelectionExpression(tool, "[arg0/]", false);
        applyDirectEditTool(tool_Name, diagramForSelection, nodeA, "");

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check that variables are recognized during expression run time computation for DoubleClick
     */
    public void testRunTimeVariableAfterDoubleClick() {
        endSetUp(true);
        final String tool_Name = "Double Click";
        DDiagramElement nodeA = diagramForSelection.getDiagramElements().get(2);

        final AbstractToolDescription tool = getTool(diagramForSelection, tool_Name);

        changeSelectionExpression(tool, "[[element->including(elementView)/]", false);
        applyDirectEditTool(tool_Name, diagramForSelection, nodeA, "");

        assertFalse("An error occurred during runtime execution of ElementsToSelect expression", platformProblemsListener.doesAnErrorOccurs());
    }

}
