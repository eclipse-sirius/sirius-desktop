/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.api.tools;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueAction;

/**
 * Test the creation of edges
 * 
 * @author nlepine
 * 
 */
public class CreateEdgeTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/tool/tc931/";

    private static final String SEMANTIC_MODEL_NAME = "TestActivity.uml";

    private static final String REPRESENTATION_MODEL_NAME = "TestActivity.aird";

    private static final String MODELER_NAME = "uml2.odesign";

    private static final String REPRESENTATION_DESC_NAME = "Activity Diagram";

    private static final String VIEWPOINT_NAME = "UML Behavioral Modeling";

    private DDiagram diagram;

    private IEditorPart editorPart;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_NAME, MODELER_NAME, REPRESENTATION_MODEL_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATION_MODEL_NAME);
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * test the edge creation on manual refresh
     * 
     * @throws Exception
     */
    public void testEdgeCreationManualRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        Model model = (Model) semanticModel;
        Activity activity = (Activity) model.getPackagedElements().get(0);
        List<ActivityNode> ownedNodes = getActivityOwnedNodes(activity);
        OpaqueAction source = (OpaqueAction) ownedNodes.get(0);
        OpaqueAction target = (OpaqueAction) ownedNodes.get(1);

        applyEdgeCreationTool("Control Flow", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        ActivityEdge edge = activity.getEdges().get(0);
        assertNotNull(edge);
        DEdge edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);

        assertEquals("Wrong EdgeSourceArrow for Edge.", EdgeArrows.INPUT_ARROW_LITERAL, ((EdgeStyle) edgeElement.getStyle()).getSourceArrow());
        assertEquals("Wrong EdgeTargetArrow for Edge.", EdgeArrows.NO_DECORATION_LITERAL, ((EdgeStyle) edgeElement.getStyle()).getTargetArrow());

        IDiagramEdgeEditPart gmfEP = (IDiagramEdgeEditPart) getEditPart(edgeElement, editorPart);
        assertNotNull(gmfEP);
        assertNotNull(gmfEP.getPolylineConnectionFigure());

        assertEquals("Wrong Edge target", getFirstDiagramElement(diagram, target), ((Edge) gmfEP.getModel()).getSource().getElement());
        assertEquals("Wrong Edge source", getFirstDiagramElement(diagram, source), ((Edge) gmfEP.getModel()).getTarget().getElement());

        doTestUndoRedo(activity, edge);

        refresh(diagram);

        edge = activity.getEdges().get(0);
        assertNotNull(edge);
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);

        assertEquals("Wrong EdgeSourceArrow for Edge.", EdgeArrows.INPUT_ARROW_LITERAL, ((EdgeStyle) edgeElement.getStyle()).getSourceArrow());
        assertEquals("Wrong EdgeTargetArrow for Edge.", EdgeArrows.NO_DECORATION_LITERAL, ((EdgeStyle) edgeElement.getStyle()).getTargetArrow());

        gmfEP = (IDiagramEdgeEditPart) getEditPart(edgeElement, editorPart);
        assertNotNull(gmfEP);
        assertNotNull(gmfEP.getPolylineConnectionFigure());

        assertEquals("Wrong Edge target", getFirstDiagramElement(diagram, target), ((Edge) gmfEP.getModel()).getSource().getElement());
        assertEquals("Wrong Edge source", getFirstDiagramElement(diagram, source), ((Edge) gmfEP.getModel()).getTarget().getElement());

        doTestCloseOpen();
    }

    /**
     * test the edge creation on manual refresh
     * 
     * @throws Exception
     */
    public void testEdgeCreationManualRefreshWithUnsynchronizedDiagram() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        unsynchronizeDiagram(diagram);

        Model model = (Model) semanticModel;
        Activity activity = (Activity) model.getPackagedElements().get(0);
        List<ActivityNode> ownedNodes = getActivityOwnedNodes(activity);
        OpaqueAction source = (OpaqueAction) ownedNodes.get(0);
        OpaqueAction target = (OpaqueAction) ownedNodes.get(1);
        applyEdgeCreationTool("Control Flow", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        ActivityEdge edge = activity.getEdges().get(0);
        assertNotNull(edge);
        DEdge edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);

        doTestUndoRedoUnsynchronized(activity, edge);

        refresh(diagram);

        edge = activity.getEdges().get(0);
        assertNotNull(edge);
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);

        doTestCloseOpenUnsynchronized();

    }

    private void doTestCloseOpen() {
        Model model;
        Activity activity;
        ActivityEdge edge;
        DEdge edgeElement;
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        model = (Model) semanticModel;
        activity = (Activity) model.getPackagedElements().get(0);
        edge = activity.getEdges().get(0);
        assertNotNull(edge);
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);
    }

    private void doTestCloseOpenUnsynchronized() {
        Model model;
        Activity activity;
        ActivityEdge edge;
        DEdge edgeElement;
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        model = (Model) semanticModel;
        activity = (Activity) model.getPackagedElements().get(0);
        edge = activity.getEdges().get(0);
        assertNotNull(edge);
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);
    }

    private void doTestUndoRedo(Activity activity, ActivityEdge edge) throws Exception {
        DEdge edgeElement;
        // undo the creation of the edge
        undo();
        undo();
        assertTrue(activity.getEdges().isEmpty());
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNull(edgeElement);

        // redo the creation of the edge
        redo();
        redo();
        ActivityEdge edge2 = activity.getEdges().get(0);
        edgeElement = getFirstEdgeElement(diagram, edge2);
        assertNotNull(edgeElement);
    }

    private void doTestUndoRedoUnsynchronized(Activity activity, ActivityEdge edge) throws Exception {
        DEdge edgeElement;
        // undo the creation of the edge
        undo();
        assertTrue(activity.getEdges().isEmpty());
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNull(edgeElement);

        // redo the creation of the edge
        redo();
        ActivityEdge edge2 = activity.getEdges().get(0);
        edgeElement = getFirstEdgeElement(diagram, edge2);
        assertNotNull(edgeElement);
    }

    /**
     * test the edge creation on auto refresh and unsynchro
     * 
     * @throws Exception
     */
    public void testEdgeCreationAutoRefreshWithUnsynchronizedDiagram() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        unsynchronizeDiagram(diagram);

        Model model = (Model) semanticModel;
        Activity activity = (Activity) model.getPackagedElements().get(0);
        List<ActivityNode> ownedNodes = getActivityOwnedNodes(activity);
        OpaqueAction source = (OpaqueAction) ownedNodes.get(0);
        OpaqueAction target = (OpaqueAction) ownedNodes.get(1);

        applyEdgeCreationTool("Control Flow", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        ActivityEdge edge = activity.getEdges().get(0);
        assertNotNull(edge);
        DEdge edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);

        doTestUndoRedoAutoRefreshUnsynchronized(activity, edge);

        doTestCloseOpenUnsynchronized();
    }

    private void doTestUndoRedoAutoRefreshUnsynchronized(final Activity activity, ActivityEdge edge) throws Exception {
        DEdge edgeElement;
        // undo the creation of the edge
        // domain.getCommandStack().undo(); // undo the refresh
        undo(); // undo the creation edge
        assertTrue(activity.getEdges().isEmpty());
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNull(edgeElement);

        // redo the creation of the edge
        redo();
        // domain.getCommandStack().redo();
        ActivityEdge edge2 = activity.getEdges().get(0);
        edgeElement = getFirstEdgeElement(diagram, edge2);
        assertNotNull(edgeElement);
    }

    /**
     * test the edge creation on auto refresh
     * 
     * @throws Exception
     */
    public void testEdgeCreationAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        Model model = (Model) semanticModel;
        final Activity activity = (Activity) model.getPackagedElements().get(0);
        List<ActivityNode> ownedNodes = getActivityOwnedNodes(activity);
        OpaqueAction source = (OpaqueAction) ownedNodes.get(0);
        OpaqueAction target = (OpaqueAction) ownedNodes.get(1);

        applyEdgeCreationTool("Control Flow", diagram, (EdgeTarget) getFirstDiagramElement(diagram, source), (EdgeTarget) getFirstDiagramElement(diagram, target));

        ActivityEdge edge = activity.getEdges().get(0);
        assertNotNull(edge);
        DEdge edgeElement = getFirstEdgeElement(diagram, edge);
        assertNotNull(edgeElement);

        assertEquals("Wrong EdgeSourceArrow for Edge.", EdgeArrows.INPUT_ARROW_LITERAL, ((EdgeStyle) edgeElement.getStyle()).getSourceArrow());
        assertEquals("Wrong EdgeTargetArrow for Edge.", EdgeArrows.NO_DECORATION_LITERAL, ((EdgeStyle) edgeElement.getStyle()).getTargetArrow());

        IDiagramEdgeEditPart gmfEP = (IDiagramEdgeEditPart) getEditPart(edgeElement, editorPart);
        assertNotNull(gmfEP);
        assertNotNull(gmfEP.getPolylineConnectionFigure());

        assertEquals("Wrong Edge target", getFirstDiagramElement(diagram, target), ((Edge) gmfEP.getModel()).getSource().getElement());
        assertEquals("Wrong Edge source", getFirstDiagramElement(diagram, source), ((Edge) gmfEP.getModel()).getTarget().getElement());

        doTestUndoRedoAutoRefresh(activity, edge);

        doTestCloseOpen();
    }

    /**
     * @param activity
     * @param edge
     * @throws Exception
     */
    private void doTestUndoRedoAutoRefresh(final Activity activity, ActivityEdge edge) throws Exception {
        DEdge edgeElement;
        // undo the creation of the edge
        // undo the refresh
        undo();
        // undo the creation edge
        undo();

        assertTrue(activity.getEdges().isEmpty());
        edgeElement = getFirstEdgeElement(diagram, edge);
        assertNull(edgeElement);

        // redo the creation of the edge
        redo();
        redo();
        ActivityEdge edge2 = activity.getEdges().get(0);
        edgeElement = getFirstEdgeElement(diagram, edge2);
        assertNotNull(edgeElement);
    }

    @Override
    protected void tearDown() throws Exception {

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();

        diagram = null;
        editorPart = null;

        super.tearDown();
    }

    private List<ActivityNode> getActivityOwnedNodes(Activity activity) {
        List<ActivityNode> ownedNodes = activity.getNodes();
        if (!isOlderThanUML2_4_0()) {
            ownedNodes = (List<ActivityNode>) ReflectionHelper.invokeMethodWithoutExceptionWithReturn(activity, "getOwnedNodes", new Class[] {}, new Object[] {});
        }
        return ownedNodes;
    }

    protected boolean isOlderThanUML2_4_0() {
        String umlVersion = Platform.getBundle("org.eclipse.uml2.uml").getHeaders().get("Bundle-Version");
        int umlMajor = Integer.parseInt(umlVersion.substring(0, 1));
        return umlMajor < 4;
    }
}
