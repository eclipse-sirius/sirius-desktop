/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.folding;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ui.business.internal.query.EdgeTargetQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.EdgeTargetQuery.FoldingState;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ToggleFoldingStateCommand;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorPart;

/**
 * Abstract base class for folding-related tests with some utility methods.
 * 
 * @author pcdavid
 */
public abstract class AbstractFoldingTest extends SiriusDiagramTestCase {

    protected static final String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/folding/model/test.ecore";

    protected static final String SESSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/folding/model/test.aird";

    protected static final String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/folding/description/ecore.odesign";

    protected static final String MODELER_MODEL_FILENAME = "ecore.odesign";

    protected static final String VIEWPOINT_NAME = "DesignTestFoldingStyle";

    protected DDiagram diagram;

    protected DiagramDocumentEditor diagramEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, SESSION_PATH);
        initViewpoint(VIEWPOINT_NAME);
        TestsUtil.synchronizationWithUIThread();
    }

    protected void toggleContainerFoldingState(String name) {
        DNodeContainer foldingPoint = getNodeContainer(name);
        assertTrue(new EdgeTargetQuery(foldingPoint).isFoldingPoint());
        IGraphicalEditPart part = getEditPart(foldingPoint);
        assertTrue(part instanceof IDiagramElementEditPart);
        session.getTransactionalEditingDomain().getCommandStack().execute(new ToggleFoldingStateCommand(session.getTransactionalEditingDomain(), (IDiagramElementEditPart) part));
    }

    protected void toggleNodeFoldingState(String name) {
        DNode foldingPoint = getNode(name);
        assertTrue(new EdgeTargetQuery(foldingPoint).isFoldingPoint());
        IGraphicalEditPart part = getEditPart(foldingPoint);
        assertTrue(part instanceof IDiagramElementEditPart);
        session.getTransactionalEditingDomain().getCommandStack().execute(new ToggleFoldingStateCommand(session.getTransactionalEditingDomain(), (IDiagramElementEditPart) part));
    }

    protected void foldContainer(String name) {
        assertContainerFoldingState(FoldingState.UNFOLDED, name);
        toggleContainerFoldingState(name);
        assertContainerFoldingState(FoldingState.FOLDED, name);
    }

    protected void unfoldContainer(String name) {
        assertContainerFoldingState(FoldingState.FOLDED, name);
        toggleContainerFoldingState(name);
        assertContainerFoldingState(FoldingState.UNFOLDED, name);
    }

    protected void foldNode(String name) {
        assertNodeFoldingState(FoldingState.UNFOLDED, name);
        toggleNodeFoldingState(name);
        assertNodeFoldingState(FoldingState.FOLDED, name);
    }

    protected void unfoldNode(String name) {
        assertNodeFoldingState(FoldingState.FOLDED, name);
        toggleNodeFoldingState(name);
        assertNodeFoldingState(FoldingState.UNFOLDED, name);
    }

    protected void assertContainerFoldingState(FoldingState expected, String name) {
        assertTrue("Container " + name + " should be " + expected + ".", new EdgeTargetQuery(getNodeContainer(name)).getFoldingState() == expected);
    }

    protected void assertNodeFoldingState(FoldingState expected, String name) {
        assertTrue("Node " + name + " should be " + expected + ".", new EdgeTargetQuery(getNode(name)).getFoldingState() == expected);
    }

    protected void assertContainerIsVisible(String name) {
        assertTrue("Container " + name + " should be visible.", getNodeContainer(name).isVisible());
    }

    protected void assertContainerIsNotVisible(String name) {
        assertFalse("Container " + name + " should not be visible.", getNodeContainer(name).isVisible());
    }

    protected void assertNodeIsVisible(String name) {
        assertTrue("Node " + name + " should be visible.", getNode(name).isVisible());
    }

    protected void assertNodeIsNotVisible(String name) {
        assertFalse("Node " + name + " should not be visible.", getNode(name).isVisible());
    }

    protected void assertContainerIsFoldingPoint(String name) {
        EdgeTargetQuery query = new EdgeTargetQuery(getNodeContainer(name));
        assertTrue("The container " + name + " should be a folding point.", query.isFoldingPoint());
    }

    protected void assertContainerIsNotFoldingPoint(String name) {
        EdgeTargetQuery query = new EdgeTargetQuery(getNodeContainer(name));
        assertFalse("The container " + name + " should not be a folding point.", query.isFoldingPoint());
    }

    protected void assertNodeIsFoldingPoint(String name) {
        EdgeTargetQuery query = new EdgeTargetQuery(getNode(name));
        assertTrue("The node " + name + " should be a folding point.", query.isFoldingPoint());
    }

    protected void assertNodeIsNotFoldingPoint(String name) {
        EdgeTargetQuery query = new EdgeTargetQuery(getNode(name));
        assertFalse("The node " + name + " should not be a folding point.", query.isFoldingPoint());
    }

    protected DNodeContainer getNodeContainer(String name) {
        List<DDiagramElement> elements = getDiagramElementsFromLabel(diagram, name);
        assertEquals(1, elements.size());
        assertTrue(elements.get(0) instanceof DNodeContainer);
        return (DNodeContainer) elements.get(0);
    }

    protected DNode getNode(String name) {
        List<DDiagramElement> elements = getDiagramElementsFromLabel(diagram, name);
        assertEquals(1, elements.size());
        assertTrue(elements.get(0) instanceof DNode);
        return (DNode) elements.get(0);
    }

    protected void openDiagram(String name) {
        diagram = getDiagramByName(name);
        IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertTrue("We should have a DiagramDocumentEditor", editorPart instanceof DiagramDocumentEditor);
        diagramEditor = (DiagramDocumentEditor) editorPart;
    }

    protected DDiagram getDiagramByName(final String name) {
        Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        DRepresentationDescriptor representationDescriptor = representationDescriptors.stream().filter(repDesc -> repDesc.getRepresentation() instanceof DDiagram)
                .filter(input -> name.equals(input.getName())).findFirst().orElse(null);
        if (representationDescriptor != null) {
            return (DDiagram) representationDescriptor.getRepresentation();
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
