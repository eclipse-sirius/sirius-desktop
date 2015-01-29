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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test refresh after unsynchronized diagram.
 * 
 * @author fbarbin
 */
public class DiagramUnsynchronizedRefreshTest extends SiriusDiagramTestCase {

    private static final String IMPORT_LAYER = "importLayer";

    private static final int EXPECTED_NUMBER_OF_ELEMENTS = 4;

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/unsynchronized/unsynchronized.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/unsynchronized/unsynchronized.odesign";

    private static final String VIEWPOINT_NAME = "unsynchronized";

    private static final String REPRESENTATION_NAME = "diag";

    private DDiagram diagramSync;

    private DDiagram diagramUnsync;

    public void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        diagramSync = (DDiagram) createRepresentation(REPRESENTATION_NAME, semanticModel);
        diagramUnsync = (DDiagram) createRepresentation(REPRESENTATION_NAME, semanticModel);
    }

    /**
     * Test refresh result after unsynchronized diagram and activated
     * "importLayer".
     */
    public void testRefreshAfterUnsynchronisedWithoutAutoRefresh() {

        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagramUnsync, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedRepresentationElements().size());
        // Activate layer
        assertTrue("Problem during layer activation", activateLayer(diagramUnsync, IMPORT_LAYER));
        // Unsynchronized the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                diagramUnsync.setSynchronized(false);
            }
        });
        // Manual refresh
        refresh(diagramUnsync);
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Deactivate layer
        assertTrue("Problem during layer deactivation", deactivateLayer(diagramUnsync, IMPORT_LAYER));
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Manual refresh
        refresh(diagramUnsync);
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
    }

    /**
     * Test activation of layer after unsynchronized diagram and refresh.
     */
    public void testLayerWithImportEdgeAfterUnsynchronisedAndRefreshWithoutAutoRefresh() {
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagramUnsync, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedRepresentationElements().size());
        // Unsynchronized the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                diagramUnsync.setSynchronized(false);
            }
        });
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Manual refresh
        refresh(diagramUnsync);
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Activate layer
        assertTrue("Problem during layer activation", activateLayer(diagramUnsync, IMPORT_LAYER));
        // Manual refresh
        // refresh(diagram);
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
    }

    /**
     * Ticket D2328. Test refresh result after unsynchronized diagram and
     * activated "importLayer".
     */
    public void testRefreshAfterUnsynchronisedWithAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagramUnsync, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedRepresentationElements().size());
        // Activate layer
        assertTrue("Problem during layer activation", activateLayer(diagramUnsync, IMPORT_LAYER));
        // Unsynchronized the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                diagramUnsync.setSynchronized(false);
            }
        });
        // Manual refresh
        refresh(diagramUnsync);
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Deactivate layer
        assertTrue("Problem during layer deactivation", deactivateLayer(diagramUnsync, IMPORT_LAYER));
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Manual refresh
        refresh(diagramUnsync);
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
    }

    /**
     * Test activation of layer after unsynchronized diagram and refresh.
     */
    public void testLayerWithImportEdgeAfterUnsynchronisedAndRefreshWithAutoRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        final IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, diagramUnsync, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedRepresentationElements().size());
        // Unsynchronized the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                diagramUnsync.setSynchronized(false);
            }
        });
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Manual refresh
        refresh(diagramUnsync);
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());
        // Activate layer
        assertTrue("Problem during layer activation", activateLayer(diagramUnsync, IMPORT_LAYER));
        // all elements should still be here
        assertEquals(EXPECTED_NUMBER_OF_ELEMENTS, diagramUnsync.getOwnedDiagramElements().size());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
    }

    /**
     * If an edge exists in a unsynchronized diagram and is reconnect in another
     * diagram, it should be removed in the unsynchronized diagram.
     */
    public void testReconnectEdgeAndUnsyncDiagRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // Open the first diagram and unsynchronize it.
        final IEditorPart editorUnsync = DialectUIManager.INSTANCE.openEditor(session, diagramUnsync, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                diagramUnsync.setSynchronized(false);
            }
        });
        // Reconnect the edge in another diagram
        final IEditorPart editorSync = DialectUIManager.INSTANCE.openEditor(session, diagramSync, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals("Bad initial state on edge", 1, diagramUnsync.getEdges().size());
        applyEdgeReconnectionTool("reconnectEType", diagramSync, diagramSync.getEdges().get(0), (EdgeTarget) diagramSync.getOwnedDiagramElements().get(0), (EdgeTarget) diagramSync
                .getOwnedDiagramElements().get(2));
        TestsUtil.synchronizationWithUIThread();
        assertEquals("The edge should be deleted during the refresh in the unsynchronized diagram", 0, diagramUnsync.getEdges().size());
        // Close opened editors.
        DialectUIManager.INSTANCE.closeEditor(editorUnsync, false);
        DialectUIManager.INSTANCE.closeEditor(editorSync, false);
    }
}
