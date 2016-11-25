/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.tools;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test that performing an undo after an edge creation tool doesn't lead to
 * dangling references on {@link DNode#getOutgoingEdges()} and
 * {@link DNode#getIncomingEdges()}. See #508138 more details.
 * 
 * @author Florian Barbin
 *
 */
public class UndoAfterInconsistentEdgeCreationViewTest extends SiriusDiagramTestCase {

    private static final String REFERENCE_CREATE_TOOL_NAME = "Create Reference";

    private static final String EDGE_PB_REPRESENTATION_DESC_NAME = "Package";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/undoEdgeCreationToolDangling/dEdgeUndoDangling.ecore";

    private static final String SESSION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/undoEdgeCreationToolDangling/dEdgeUndoDangling.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/tools/undoEdgeCreationToolDangling/dEdgeUndoDangling.odesign";

    private DDiagram diagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_MODEL_PATH);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = (DDiagram) getRepresentations(EDGE_PB_REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull(editor);
    }

    /**
     * This test performs the following actions:
     * <ul>
     * <li>Create an edge using the edge creation tool.</li>
     * <li>Check that this edge has been removed by the refresh. Indeed, this
     * edge creation tool creates a new DEdge inconsistent with the edge mapping
     * precondition (always false)</li>
     * <li>Perform an undo</li>
     * <li>Make sure there is still no DEdge in the diagram and the DNodes do
     * not have references toward a detached DEdge.</li>
     * </ul>
     * 
     * @throws Exception
     *             if an error occurs during the undo.
     */
    public void testEdgeEditPartCreationAfterFilterDeactivation() throws Exception {
        List<DNode> nodeClasses = Lists.newArrayList(Iterables.filter(diagram.getDiagramElements(), DNode.class));
        assertEquals("Bad input data : wrong class number", 2, nodeClasses.size());
        DNode class1 = nodeClasses.get(0);
        DNode class2 = nodeClasses.get(1);
        applyEdgeCreationTool(REFERENCE_CREATE_TOOL_NAME, diagram, class1, class2);
        assertEquals("The refresh should have removed the created DEdge", 0, diagram.getEdges().size());
        assertEquals("The refresh should have removed the created DEdge", 0, class1.getOutgoingEdges().size());
        assertEquals("The refresh should have removed the created DEdge", 0, class2.getIncomingEdges().size());
        undo();
        assertEquals("After undo, there should be no DEdge in the diagram", 0, diagram.getEdges().size());
        assertEquals("After undo, the DNode should not reference any edges", 0, class1.getOutgoingEdges().size());
        assertEquals("After undo, the DNode should not reference any edges", 0, class2.getIncomingEdges().size());
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        diagram = null;
        editor = null;
        super.tearDown();
    }

}
