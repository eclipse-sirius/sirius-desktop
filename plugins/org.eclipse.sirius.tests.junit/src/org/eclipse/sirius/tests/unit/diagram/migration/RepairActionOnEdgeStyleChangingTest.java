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

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.migration.design.LayoutHelper;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.graphics.Color;

/**
 * Check edges color after the launching of the repair action after that the
 * color has been changed in VSM. (See VP-3612)
 * 
 * @author fbarbin
 */
public class RepairActionOnEdgeStyleChangingTest extends AbstractRepairMigrateTest {

    private static final String SESSION_RESOURCE_FILENAME = "test.aird";

    private static final String REPRESENTATION_NAME_DEFAULT = "test";

    private static final String PATH_CHANGE_EDGE_STYLE = "/data/unit/repair/testChangeEdgeStyle";

    private static final String MODELER_RESOURCE_FILENAME_CHANGE_EDGE_STYLE = "testChangeEdgeStyle.odesign";

    private static final String MODELER_PATH_CHANGE_EDGE_STYLE = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME_CHANGE_EDGE_STYLE;

    private DiagramEditor editor;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private static final String SEMANTIC_RESOURCE_FILENAME = "test.migrationmodeler";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
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
    }

    /**
     * Check edges color after the launching of the repair action after that the
     * color has been changed in VSM:
     * <ul>
     * <li>Check that edges color is right before repair (for the customized one
     * and not customized others)</li>
     * <li>Check that edges color is right after repair (not customized edges
     * are up-to-date with VSM new edge style color but the customized one keeps
     * the same color)</li>
     * </ul>
     * 
     * @throws Exception
     *             test error.
     */
    public void testRepairChangeEdgeStyle() throws Exception {

        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME_DEFAULT).toArray()[0]);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

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
        // Check color for customized edge
        List<DDiagramElement> edgesList = getDiagramElementsFromLabel(dDiagram, "edge 2");
        Color edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge2 red color before repair", edgeColor.getRed(), 225);
        assertEquals("Wrong edge2 green color before repair", edgeColor.getGreen(), 225);
        assertEquals("Wrong edge2 blue color before repair", edgeColor.getBlue(), 135);

        // Check color for not customized edges
        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 1");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge1 red color before repair", edgeColor.getRed(), 136);
        assertEquals("Wrong edge1 green color before repair", edgeColor.getGreen(), 136);
        assertEquals("Wrong edge1 blue color before repair", edgeColor.getBlue(), 136);

        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 3");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge3 red color before repair", edgeColor.getRed(), 136);
        assertEquals("Wrong edge3 green color before repair", edgeColor.getGreen(), 136);
        assertEquals("Wrong edge3 blue color before repair", edgeColor.getBlue(), 136);
    }

    private void checkEdgesColorAfterRepair(DDiagram dDiagram) {
        // Check color for customized edge
        List<DDiagramElement> edgesList = getDiagramElementsFromLabel(dDiagram, "edge 2");
        Color edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge2 red color after repair", 225, edgeColor.getRed());
        assertEquals("Wrong edge2 green color after repair", 225, edgeColor.getGreen());
        assertEquals("Wrong edge2 blue color after repair", 135, edgeColor.getBlue());

        // Check color for not customized edges
        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 1");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge1 red color after repair", 194, edgeColor.getRed());
        assertEquals("Wrong edge1 green color after repair", 239, edgeColor.getGreen());
        assertEquals("Wrong edge1 blue color after repair", 255, edgeColor.getBlue());

        edgesList = getDiagramElementsFromLabel(dDiagram, "edge 3");
        edgeColor = LayoutHelper.getForegroundColor((DEdge) edgesList.get(0));

        assertEquals("Wrong edge3 red color after repair", 194, edgeColor.getRed());
        assertEquals("Wrong edge3 green color after repair", 239, edgeColor.getGreen());
        assertEquals("Wrong edge3 blue color after repair", 255, edgeColor.getBlue());
    }
}
