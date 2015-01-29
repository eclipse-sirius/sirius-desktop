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
package org.eclipse.sirius.tests.unit.diagram.dragndrop;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Tests that the label visibility is keeped when a edge is reconnected or a
 * node is dropped.
 * 
 * See VP-3894.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class LabelVisibilityOnDragAndDropTests extends SiriusDiagramTestCase {

    private static final String KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP = "kept hidden after a drag and drop.";

    private static final String KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP = "kept visible after a drag and drop.";

    private static final String VISIBLE_AT_THE_DIAGRAM_OPENING = "visible at the diagram opening";

    private static final String VISIBLE_AFTER_HIDE_LABEL = "visible after hide label.";

    private static final String HIDDEN_AFTER_HIDE_LABEL = "hidden after hide label.";

    private static final String PATH = "/data/unit/dragndrop/VP-3894/";

    private static final String MODELER_RESOURCE_NAME = "VP-3894.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3894.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-3894.aird";

    private static final String RECONNECT_TOOL_NAME = "ReconnectESupertype";

    private static final String DROP_PACKAGE_TOOL_NAME = "Drop EPackage into EPackage";

    private static final String DROP_CLASS_TOOL_NAME = "DropClass";

    private static final String DROP_ATTRIBUTE_TOOL_NAME = "DropAttribute";

    private DDiagram dDiagram;

    private DDiagram dDiagramBis;

    private DEdge c1Edge;

    private DEdge a2Edge;

    private DNode c2DNode;

    private DNode c3DNode;

    private DNode a2DNode;

    private DNodeContainer p1DNodeContainer;

    private DNodeContainer p2DNodeContainer;

    private DDiagramEditor dDiagramEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, MODELER_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME);
        genericSetUp(Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME), Collections.singletonList(TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME),
                TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        Iterator<DRepresentation> iterator = allRepresentations.iterator();
        dDiagram = (DDiagram) iterator.next();
        dDiagramBis = (DDiagram) iterator.next();
    }

    /* For initialization and after drag and drop. */
    private void affectVariables(DDiagram dDiagram) {
        c1Edge = getDiagramElementsFromLabel(dDiagram, "c1", DEdge.class).get(0);
        a2Edge = getDiagramElementsFromLabel(dDiagram, "a2", DEdge.class).get(0);
        a2DNode = getDiagramElementsFromLabel(dDiagram, "a2", DNode.class).get(0);
        c2DNode = getDiagramElementsFromLabel(dDiagram, "c2", DNode.class).get(0);
        c3DNode = getDiagramElementsFromLabel(dDiagram, "c3", DNode.class).get(0);
        p1DNodeContainer = getDiagramElementsFromLabel(dDiagram, "p1", DNodeContainer.class).get(0);
        p2DNodeContainer = getDiagramElementsFromLabel(dDiagram, "p2", DNodeContainer.class).get(0);
    }

    /**
     * Test that after a reconnect of a {@link DEdge} whose labels are hidden
     * these last are always hidden.
     */
    public void testEdgeReconnection() {
        affectVariables(dDiagram);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check initial state
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        // Action
        hideLabel(c1Edge);

        // Check hide label effect
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        applyEdgeReconnectionTool(RECONNECT_TOOL_NAME, dDiagram, c1Edge, c2DNode, c3DNode);
        TestsUtil.synchronizationWithUIThread();

        // Check reconnect effect on labels
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, "kept visible after a reconnect.", "kept hidden after a reconnect.");
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
    }

    /**
     * Test that after a drag'n drop of a {@link DNode} whose label is hidden
     * this last is always hidden.
     */
    public void testDNodeDragAndDrop() {
        affectVariables(dDiagram);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check initial state
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        // Action
        hideLabel(c1Edge);
        hideLabel(a2Edge);
        hideLabel(a2DNode);
        hideLabel(c2DNode);

        // Check hide label effect
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(c2DNode, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);

        applyContainerDropDescriptionTool(dDiagram, DROP_CLASS_TOOL_NAME, p2DNodeContainer, c2DNode);
        TestsUtil.synchronizationWithUIThread();

        // Check drag and drop effect on labels
        affectVariables(dDiagram);
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(c2DNode, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
    }

    /**
     * Test that after a drag'n drop of a bordered {@link DNode} whose label is
     * hidden this last is always hidden.
     */
    public void testBorderedNodeDragAndDrop() {
        affectVariables(dDiagram);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check initial state
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        // Action
        hideLabel(a2Edge);
        hideLabel(a2DNode);

        // Check hide label effect
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);

        applyContainerDropDescriptionTool(dDiagram, DROP_ATTRIBUTE_TOOL_NAME, c3DNode, a2DNode);
        TestsUtil.synchronizationWithUIThread();

        // Check drag and drop effect on labels
        affectVariables(dDiagram);
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
    }

    /**
     * Test that after a drag'n drop of a {@link DNodeContainer} whose label is
     * hidden this last is always hidden.
     */
    public void testDNodeContainerDragAndDrop() {
        affectVariables(dDiagram);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check initial state
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        // Action
        hideLabel(c1Edge);
        hideLabel(a2Edge);
        hideLabel(c2DNode);
        hideLabel(a2DNode);

        // Check hide label effect
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(c2DNode, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);

        // Drag and Drop
        applyContainerDropDescriptionTool(dDiagram, DROP_PACKAGE_TOOL_NAME, p2DNodeContainer, p1DNodeContainer);
        TestsUtil.synchronizationWithUIThread();

        // Check drag and drop effect on labels
        affectVariables(dDiagram);
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(c2DNode, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
    }

    /**
     * Test that after a drag'n drop of a {@link DNode} whose label is visible
     * and the style has hideLabelByDefault, this last is always visible.
     */
    public void testDNodeDragAndDropWithHideLabelByDefault() {
        affectVariables(dDiagramBis);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagramBis, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check initial state
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        // Action
        hideLabel(c1Edge);
        hideLabel(a2Edge);
        revealLabel(a2DNode);
        revealLabel(c2DNode);

        // Check hide label effect
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);

        applyContainerDropDescriptionTool(dDiagramBis, DROP_CLASS_TOOL_NAME, p2DNodeContainer, c2DNode);
        TestsUtil.synchronizationWithUIThread();

        // Check drag and drop effect on labels
        affectVariables(dDiagramBis);
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);

    }

    /**
     * Test that after a drag'n drop of a bordered {@link DNode} whose label is
     * visible and the style has hideLabelByDefault, this last is always
     * visible.
     */
    public void testBorderedNodeDragAndDropWithHideLabelByDefault() {
        affectVariables(dDiagramBis);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagramBis, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check initial state
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        // Action
        hideLabel(a2Edge);
        revealLabel(a2DNode);

        // Check hide label effect
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);

        applyContainerDropDescriptionTool(dDiagramBis, DROP_ATTRIBUTE_TOOL_NAME, c3DNode, a2DNode);
        TestsUtil.synchronizationWithUIThread();

        // Check drag and drop effect on labels
        affectVariables(dDiagramBis);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
    }

    /**
     * Test that after a drag'n drop of a {@link DNodeContainer} whose label is
     * visible and the style has hideLabelByDefault, this last is always
     * visible.
     */
    public void testDNodeContainerDragAndDropWithHideLabelByDefault() {
        affectVariables(dDiagramBis);
        dDiagramEditor = (DDiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagramBis, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Check initial state
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, true, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(c2DNode, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);
        checkGmgNodeAndLabelVisibility(a2DNode, true, false, VISIBLE_AT_THE_DIAGRAM_OPENING, VISIBLE_AT_THE_DIAGRAM_OPENING);

        // Action
        hideLabel(c1Edge);
        hideLabel(a2Edge);
        revealLabel(c2DNode);
        revealLabel(a2DNode);

        // Check hide label effect
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, VISIBLE_AFTER_HIDE_LABEL, HIDDEN_AFTER_HIDE_LABEL);

        // Drag and Drop
        applyContainerDropDescriptionTool(dDiagramBis, DROP_PACKAGE_TOOL_NAME, p2DNodeContainer, p1DNodeContainer);
        TestsUtil.synchronizationWithUIThread();

        // Check drag and drop effect on labels
        affectVariables(dDiagramBis);
        checkGmgEdgeAndLabelsVisibility(c1Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgEdgeAndLabelsVisibility(a2Edge, true, false, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(c2DNode, true, true, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
        checkGmgNodeAndLabelVisibility(a2DNode, true, true, KEPT_VISIBLE_AFTER_A_DRAG_AND_DROP, KEPT_HIDDEN_AFTER_A_DRAG_AND_DROP);
    }

    private void checkGmgNodeAndLabelVisibility(AbstractDNode dde, boolean visibleView, boolean visibleLabels, String viewMessage, String labelMessage) {
        Node gmfNode = getGmfNode(dde);
        checkGmgViewAndLabelsVisibility(gmfNode, visibleView, visibleLabels, viewMessage, labelMessage);
    }

    private void checkGmgEdgeAndLabelsVisibility(DEdge dEdge, boolean visibleView, boolean visibleLabels, String viewMessage, String labelMessage) {
        Edge gmfEdge = getGmfEdge(dEdge);
        checkGmgViewAndLabelsVisibility(gmfEdge, visibleView, visibleLabels, viewMessage, labelMessage);
    }

    private void checkGmgViewAndLabelsVisibility(View view, boolean visibleView, boolean visibleLabels, String viewMessage, String labelMessage) {
        assertEquals("The gmf view should be " + viewMessage, visibleView, view.isVisible());
        for (Object child : view.getChildren()) {
            if (child instanceof View && new ViewQuery((View) child).isForNameEditPart()) {
                View nodeNameView = (View) child;
                String failureMessage = "The gmf name view should be " + labelMessage;
                assertEquals(failureMessage, visibleLabels, nodeNameView.isVisible());
            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        dDiagram = null;
        dDiagramBis = null;
        c1Edge = null;
        a2Edge = null;
        c2DNode = null;
        c3DNode = null;
        a2DNode = null;
        p1DNodeContainer = null;
        p2DNodeContainer = null;
        DialectUIManager.INSTANCE.closeEditor(dDiagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        dDiagramEditor = null;
        super.tearDown();
    }
}
