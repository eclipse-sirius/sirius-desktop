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
package org.eclipse.sirius.tests.unit.diagram.style;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

/**
 * Tests that sourceView, targetView, view and diagram variables are vailables
 * for edge size computation.
 * 
 * @author mporhel
 */
public class EdgeSizeComputationVariableTest extends SiriusDiagramTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/style/edgeSizeVariable/";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + "EdgeSizeVariable.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + DATA_UNIT_DIR + "EdgeSizeVariable.odesign";

    private static final String REPRESENTATION_DESC_NAME = "EdgeSizeVariable";

    protected DDiagram diagram;

    protected DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME);
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, defaultProgress);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        editor = null;
        session.close(new NullProgressMonitor());
        session = null;
        super.tearDown();
    }

    /**
     * Tests that sourceView, targetView, view and diagram variables are
     * vailables for edge size computation.
     * 
     */
    public void testEdgeSizeComputationWithVariables() {
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), true);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        boolean errorCatchActive = isErrorCatchActive();
        Multimap<String, IStatus> prevError = LinkedHashMultimap.create(errors);
        clearErrors();
        setErrorCatchActive(true);

        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME);
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, defaultProgress);
        TestsUtil.synchronizationWithUIThread();

        assertFalse("No error should occurs during refresh: check the variable access.", doesAnErrorOccurs());

        // resetErrorCatchActive
        setErrorCatchActive(errorCatchActive);
        errors.putAll(prevError);

        // Check the expected size.
        assertEquals("The diagram should contain 2 nodes and 4 edges.", 6, diagram.getRepresentationElements().size());
        assertEquals("The diagram should contain 2 nodes.", 2, diagram.getNodes().size());
        EList<DEdge> edges = diagram.getEdges();
        assertEquals("The diagram should contain 4 edges.", 4, edges.size());

        checkEdgeSize(edges, "diagram", 2);
        checkEdgeSize(edges, "sourceView", 2);
        checkEdgeSize(edges, "targetView", 2);
        checkEdgeSize(edges, "view", 2);
    }

    private void checkEdgeSize(EList<DEdge> edges, String name, int expectedSize) {
        DEdge edge = getEdge(edges, name);
        assertNotNull("Check the test data: the edge with name " + name + " has not been found.", edge);
        assertEquals("The edge named " + name + " does not have the expected size.", expectedSize, edge.getOwnedStyle().getSize().intValue());
    }

    private DEdge getEdge(EList<DEdge> edges, String name) {
        for (DEdge edge : edges) {
            if (name.equals(edge.getName())) {
                return edge;
            }
        }
        return null;
    }
}
