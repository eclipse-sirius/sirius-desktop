/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.style;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramEdgeEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * Test the edge size consistency from description to figure.
 * 
 * @author mporhel
 */
public class EdgeSizeTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/vp-1879/VP-1879.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/style/vp-1879/VP-1879.odesign";

    protected static final String REPRESENTATION_DESC_NAME = "vp1879";

    protected DDiagram diagram;

    protected DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint("VP-1879");
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME);
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }

    /**
     * Test method.
     */
    public void testConsistentEdgeSizes() {
        /* Check tests datas */
        assertEquals("The diagram should contain 8 edges", 8, diagram.getEdges().size());

        for (DEdge edge : diagram.getEdges()) {
            String name = edge.getName();
            IDiagramEdgeEditPart editPart = (IDiagramEdgeEditPart) getEditPart(edge);

            // The expected size : 1 or more pixel.
            int expectedSize = parseExpectedSize(name);

            // The viewpoint computed size : can be null, any integer.
            Integer currentDSize = edge.getOwnedStyle().getSize();
            if (expectedSize > 1) {
                assertEquals("The edge do not have the good style size", expectedSize, currentDSize.intValue());
            }

            PolylineConnectionEx polyline = editPart.getPolylineConnectionFigure();
            assertEquals("The edge with name '" + name + "' line do not have the expected size", expectedSize, polyline.getLineWidth());

        }
    }

    private int parseExpectedSize(String name) {
        int expectedSize;

        if (name == null || "".equals(name) || name.startsWith("empty size expr")) {
            // we expect a 1 pixel size
            expectedSize = 1;
        } else {
            expectedSize = Integer.parseInt(name);
        }

        if (expectedSize < 1) {
            // the tool now avoid to display only the shadow line, so we expect
            // to have a one pixel line for each edges with a size less or equal
            // to 1.
            expectedSize = 1;
        }

        return expectedSize;
    }
}
