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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.migration.design.LayoutHelper;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.graphics.Color;

/**
 * Repair action test suite.
 * <ul>
 * <li>Test repair action on an aird containing elements that should not exist
 * anymore since a mapping as been deleted into VSM.</li>
 * <li>Test repair action after delete a filter into VSM. Elements masked by
 * filter should reappear after repair.</li>
 * <li>Test whether edge on edge doesn't disappear after repair action</li>
 * </ul>
 * 
 * @author fbarbin
 */
public class RepairActionTest extends AbstractRepairMigrateTest {

    private static final String PATH_DELETE_MAPPING = "/data/unit/repair/testDeleteMapping";

    private static final String PATH_DELETE_FILTER = "/data/unit/repair/testFilterDeleted";

    private static final String PATH_EDGE_TO_EDGE = "/data/unit/repair/testEdgeFromToEdge";

    private static final String PATH_DELETE_LAYER = "/data/unit/repair/testLayerDeleted";

    private static final String SESSION_RESOURCE_FILENAME = "test.aird";

    private static final String MODELER_RESOURCE_FILENAME_MAPPING = "repairDeleteMapping.odesign";

    private static final String MODELER_RESOURCE_FILENAME_FILTER = "testDeleteFilter.odesign";

    private static final String MODELER_RESOURCE_FILENAME_EDGE = "edgeToFromEdgeTest.odesign";

    private static final String MODELER_RESOURCE_FILENAME_DELETE_LAYER = "repairDeleteLayer.odesign";

    private static final String SEMANTIC_RESOURCE_FILENAME = "test.migrationmodeler";

    private static final String REPRESENTATION_NAME_MAPPING = "testMappingDeleted";

    private static final String REPRESENTATION_NAME_FILTER = "testFilterDeleted";

    private static final String REPRESENTATION_NAME_EDGE = "edgeToEdge";

    private static final String REPRESENTATION_NAME_LAYER = "testDeleteLayer";

    private static final String REPRESENTATION_NAME_DEFAULT = "test";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String MODELER_PATH_MAPPING = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME_MAPPING;

    private static final String MODELER_PATH_FILTER = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME_FILTER;

    private static final String MODELER_PATH_EDGE = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME_EDGE;

    private static final String MODELER_PATH_DELETE_LAYER = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME_DELETE_LAYER;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private static final String PATH_CHANGE_EDGE_STYLE = "/data/unit/repair/testChangeEdgeStyle";

    private static final String MODELER_RESOURCE_FILENAME_CHANGE_EDGE_STYLE = "testChangeEdgeStyle.odesign";

    private static final String MODELER_PATH_CHANGE_EDGE_STYLE = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME_CHANGE_EDGE_STYLE;

    private DiagramEditor editor;

    /**
     * Tests repair action after delete mapping into VSM.
     * 
     * @throws Exception
     *             test error.
     */
    public void testRepairActionAfterDeleteMapping() throws Exception {
        // Setup

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_MAPPING + "/" + MODELER_RESOURCE_FILENAME_MAPPING, "/" + TEMPORARY_PROJECT_NAME + "/"
                + MODELER_RESOURCE_FILENAME_MAPPING);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_MAPPING + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_MAPPING + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH_MAPPING, SESSION_PATH);

        // repair of aird (migration is now done automatically)
        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_MAPPING).toArray()[0]);
        assertEquals("Wrong number of elements in diagram before repair", 12, dDiagram.getDiagramElements().size());
        session.close(new NullProgressMonitor());
        runRepairProcess(SESSION_RESOURCE_FILENAME);
        // Check again data
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(SESSION_PATH, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_MAPPING).toArray()[0]);
        assertEquals("Wrong number of elements in diagram after repair", 9, dDiagram.getDiagramElements().size());
        session.close(new NullProgressMonitor());
    }

    /**
     * Tests repair action after delete filter into VSM.
     * 
     * @throws Exception
     *             test error.
     */
    public void testRepairActionAfterDeleteFilter() throws Exception {
        // Setup

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_FILTER + "/" + MODELER_RESOURCE_FILENAME_FILTER, "/" + TEMPORARY_PROJECT_NAME + "/"
                + MODELER_RESOURCE_FILENAME_FILTER);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_FILTER + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_FILTER + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH_FILTER, SESSION_PATH);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        // changeSiriusUIPreference(DesignerPreferencesKeys.PREF_AUTO_REFRESH.name(),
        // false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_FILTER).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        IGraphicalEditPart editPart = getEditPart(dDiagram);

        assertEquals("Wrong number of elements in diagram before repair", 7, editPart.getChildren().size());
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        session.close(new NullProgressMonitor());
        runRepairProcess(SESSION_RESOURCE_FILENAME);
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(SESSION_PATH, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_FILTER).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        editPart = getEditPart(dDiagram);

        assertEquals("Wrong number of elements in diagram after repair", 9, editPart.getChildren().size());

        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Tests repair action on representation containing edge on edge.
     * 
     * @throws Exception
     *             test error.
     */
    public void testRepairActionWhithEdgeToFromEdge() throws Exception {
        // Setup

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_EDGE_TO_EDGE + "/" + MODELER_RESOURCE_FILENAME_EDGE, "/" + TEMPORARY_PROJECT_NAME + "/"
                + MODELER_RESOURCE_FILENAME_EDGE);
        EclipseTestsSupportHelper.INSTANCE
                .copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_EDGE_TO_EDGE + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_EDGE_TO_EDGE + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH_EDGE, SESSION_PATH);

        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_EDGE).toArray()[0]);
        List<DEdge> edgesToCheck = new ArrayList<DEdge>();

        setEdgesToCheck(dDiagram, edgesToCheck);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertEquals("Wrong number of elements in diagram before repair", 2, edgesToCheck.size());
        assertNotNull("Edge edit part is missing", getEditPart(edgesToCheck.get(0)));
        assertNotNull("Edge edit part is missing", getEditPart(edgesToCheck.get(1)));
        DialectUIManager.INSTANCE.closeEditor(editor, false);

        TestsUtil.synchronizationWithUIThread();
        session.close(new NullProgressMonitor());
        runRepairProcess(SESSION_RESOURCE_FILENAME);
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(SESSION_PATH, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_EDGE).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        edgesToCheck.clear();
        setEdgesToCheck(dDiagram, edgesToCheck);
        assertEquals("Wrong number of elements in diagram after repair", 2, edgesToCheck.size());
        assertNotNull("Edge edit part is missing", getEditPart(edgesToCheck.get(0)));
        assertNotNull("Edge edit part is missing", getEditPart(edgesToCheck.get(1)));

        DialectUIManager.INSTANCE.closeEditor(editor, false);

        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * 
     * 
     * @throws Exception
     *             test error.
     */
    public void testRepairDeletedLayer() throws Exception {
        // Setup

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_LAYER + "/" + MODELER_RESOURCE_FILENAME_DELETE_LAYER, "/" + TEMPORARY_PROJECT_NAME + "/"
                + MODELER_RESOURCE_FILENAME_DELETE_LAYER);
        EclipseTestsSupportHelper.INSTANCE
                .copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_LAYER + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_DELETE_LAYER + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH_DELETE_LAYER, SESSION_PATH);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_LAYER).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        IGraphicalEditPart editPart = getEditPart(dDiagram);

        assertEquals("Wrong number of elements in diagram before repair", 9, editPart.getChildren().size());
        DialectUIManager.INSTANCE.closeEditor(editor, false);

        TestsUtil.synchronizationWithUIThread();
        session.close(new NullProgressMonitor());
        runRepairProcess(SESSION_RESOURCE_FILENAME);
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(SESSION_PATH, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_LAYER).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        editPart = getEditPart(dDiagram);

        assertEquals("Wrong number of elements in diagram after repair", 6, editPart.getChildren().size());

        DialectUIManager.INSTANCE.closeEditor(editor, false);

        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Test repair action after have changed edge color in VSM. See VP-3612:
     * "Repair Action: Edge style is kept after a repair even if style in VSM has been changed."
     * .
     * <ul>
     * <li>Check that edges color are right before repair (for the customized
     * one and none customized others)</li>
     * <li>Check that edges color are right after repair (none customized edges
     * are up-to-date with VSM new edge color but the customized one keep the
     * same color)</li>
     * </ul>
     * 
     * @throws Exception
     *             test error.
     */
    public void testRepairChangeEdgeStyle() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_CHANGE_EDGE_STYLE + "/" + MODELER_RESOURCE_FILENAME_CHANGE_EDGE_STYLE, "/" + TEMPORARY_PROJECT_NAME + "/"
                + MODELER_RESOURCE_FILENAME_CHANGE_EDGE_STYLE);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_CHANGE_EDGE_STYLE + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH_CHANGE_EDGE_STYLE + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH_CHANGE_EDGE_STYLE, SESSION_PATH);
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_DEFAULT).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // check edges color before repair action
        checkEdgesColorBeforeRepair(dDiagram);

        DialectUIManager.INSTANCE.closeEditor(editor, false);

        TestsUtil.synchronizationWithUIThread();
        session.close(new NullProgressMonitor());
        runRepairProcess(SESSION_RESOURCE_FILENAME);
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(SESSION_PATH, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_DEFAULT).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        checkEdgesColorAfterRepair(dDiagram);

        DialectUIManager.INSTANCE.closeEditor(editor, false);

        TestsUtil.synchronizationWithUIThread();

    }

    private void checkEdgesColorBeforeRepair(DDiagram dDiagram) {
        List<DDiagramElement> edgesList = getDiagramElementsFromLabel(dDiagram, "edge 2");
        Color edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge2 color before repair", edgeColor.getRed(), 225);
        assertEquals("Wrong edge2 color before repair", edgeColor.getGreen(), 225);
        assertEquals("Wrong edge2 color before repair", edgeColor.getBlue(), 135);

        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 1");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge1 color before repair", edgeColor.getRed(), 136);
        assertEquals("Wrong edge1 color before repair", edgeColor.getGreen(), 136);
        assertEquals("Wrong edge1 color before repair", edgeColor.getBlue(), 136);

        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 3");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge3 color before repair", edgeColor.getRed(), 136);
        assertEquals("Wrong edge3 color before repair", edgeColor.getGreen(), 136);
        assertEquals("Wrong edge3 color before repair", edgeColor.getBlue(), 136);
    }

    private void checkEdgesColorAfterRepair(DDiagram dDiagram) {
        List<DDiagramElement> edgesList = getDiagramElementsFromLabel(dDiagram, "edge 2");
        Color edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge2 color after repair", edgeColor.getRed(), 225);
        assertEquals("Wrong edge2 color after repair", edgeColor.getGreen(), 225);
        assertEquals("Wrong edge2 color after repair", edgeColor.getBlue(), 135);

        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 1");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge1 color after repair", edgeColor.getRed(), 194);
        assertEquals("Wrong edge1 color after repair", edgeColor.getGreen(), 239);
        assertEquals("Wrong edge1 color after repair", edgeColor.getBlue(), 255);

        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 3");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge3 color after repair", edgeColor.getRed(), 194);
        assertEquals("Wrong edge3 color after repair", edgeColor.getGreen(), 239);
        assertEquals("Wrong edge3 color after repair", edgeColor.getBlue(), 255);
    }

    private void setEdgesToCheck(DDiagram dDiagram, List<DEdge> edgesToCheck) {
        for (DDiagramElement diagramElement : dDiagram.getDiagramElements()) {
            EObject target = diagramElement.getTarget();
            if (target instanceof Container) {
                String id = ((Container) target).getId();
                if (id.equals("c")) {
                    edgesToCheck.addAll(((DNodeContainer) diagramElement).getOutgoingEdges());
                }

            }
            if (target instanceof Node) {
                String id = ((Node) target).getId();
                if (id.equals("a")) {
                    edgesToCheck.addAll(((DNode) diagramElement).getOutgoingEdges());
                }
            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (session != null && session.isOpen()) {
            session.close(new NullProgressMonitor());
        }
    }

}
