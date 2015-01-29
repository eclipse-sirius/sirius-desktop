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
package org.eclipse.sirius.tests.unit.diagram.modeler.uml;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Complete the tests of the layers of the class
 * org.eclipse.sirius.tests.unit.layers.LayersTest.java.
 * 
 * @author lredor
 */
public class LayerTests extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/uml/layers/trac1694.uml";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/uml/layers/trac1694.odesign";

    private static final String VIEWPOINT_NAME = "Trac1694";

    private static final String REPRESENTATION_DESC_NAME = "trac1694";

    private static final String ERROR_MSG_WRONG_NUMBER = "Wrong number of elements";

    private static final String LAYER = "secondLayer";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Tests that when a layer containing only an edgeMapping that the edges are
     * not created at each activation/desactivation to avoid the lost of layout.
     * Trac #1694
     * 
     * @throws Exception
     *             if the test fails.
     */
    public void testEdgeMappingConservationOfLayout() throws Exception {
        assertNotNull("The input data is incorrect", diagram);
        // Open the editor (and refresh it)
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // The layer is not activated so the DEdge is not yet created
        assertEquals(ERROR_MSG_WRONG_NUMBER, 2, diagram.getOwnedDiagramElements().size());

        // Activate the layer.
        activateLayer(diagram, LAYER);
        // Refresh the diagram
        refresh(diagram);

        // The layer is activated so the DEdge is created
        assertEquals(ERROR_MSG_WRONG_NUMBER, 3, diagram.getOwnedDiagramElements().size());
        DEdge edge = null;
        for (DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            if (diagramElement instanceof DEdge) {
                edge = (DEdge) diagramElement;
            }
        }
        assertNotNull("No edge on this diagram", edge);
        IGraphicalEditPart editPart = getEditPart(edge, editor);
        // The edge is visible so it should have an editPart
        assertNotNull("No editPart for the edge on this diagram", editPart);

        // Deactivate the layer.
        deactivateLayer(diagram, LAYER);
        // Refresh the diagram
        refreshEditor(editor);

        // The layer is not activated but the DEdge is conserved to conserve the
        // layout
        assertEquals(ERROR_MSG_WRONG_NUMBER, 3, diagram.getOwnedDiagramElements().size());
        assertFalse("This edge should not be visible", edge.isVisible());
        editPart = getEditPart(edge, editor);
        // The edge is not visible so it shouldn't have an editPart
        assertNotNull("The edit part for the edge should still exist", editPart);
        assertFalse("The edit part's View should be invisible", editPart.getNotationView().isVisible());

        // Activate again the layer.
        activateLayer(diagram, LAYER);
        // Refresh the diagram
        refreshEditor(editor);
        assertEquals(ERROR_MSG_WRONG_NUMBER, 3, diagram.getOwnedDiagramElements().size());

        DEdge secondEdge = null;
        for (DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            if (diagramElement instanceof DEdge) {
                secondEdge = (DEdge) diagramElement;
            }
        }
        assertSame("The second activation recreate the edge.", edge, secondEdge);
        editPart = getEditPart(edge, editor);
        // The edge is visible so it should have an editPart
        assertTrue("The edge should be visible", edge.isVisible());
        assertNotNull("No editPart for the edge on this diagram", editPart);
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    protected void refreshEditor(IEditorPart editor) {
        final Request refreshRequest = new GroupRequest(RequestConstants.REQ_REFRESH_VIEWPOINT);
        ((DiagramEditor) editor).getDiagramEditPart().performRequest(refreshRequest);
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }
}
