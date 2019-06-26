/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo.
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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A test ensuring that the DDiagramElement synchronizer correctly forces refresh. In particular, :
 * <ul>
 * <li>ensures that only "force refresh" current diagram is refreshed when automatic refresh preference is disabled.</li>
 * </ul>
 * <p>
 * Relevant tickets :
 * <ul>
 * <li>[517956] Do not refresh all diagrams with Force refresh tool</li>
 * </ul>
 * </p>
 * 
 * @author jmallet</a>
 */
public class ForceRefreshToolTest extends SiriusDiagramTestCase {

    private static final String TEST_DATA_FOLDER = SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/forceRefreshTool/";

    private static final String SEMANTIC_MODEL_PATH = TEST_DATA_FOLDER + "Vp3616.ecore";

    private static final String MODELER_DESCRIPTION_PATH = TEST_DATA_FOLDER + "Vp3616.odesign";

    private static final String SESSION_PATH = TEST_DATA_FOLDER + "representations.aird";

    private DDiagram firstDiag;

    private DDiagram secondDiag;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_DESCRIPTION_PATH, SESSION_PATH);

        Iterator<DRepresentation> iterator = getRepresentations("PackageDiag").iterator();
        firstDiag = (DDiagram) iterator.next();
        DiagramEditor diagramEditor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, firstDiag,
                new NullProgressMonitor());

        secondDiag = (DDiagram) iterator.next();
        diagramEditor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, secondDiag,
                new NullProgressMonitor());
    }

    /**
     * Ensures that force refresh works only on current diagram when autoRefresh is disable.
     * 
     * @throws Exception
     *             any exception
     */
    public void testForceRefreshWithoutAutoRefresh() throws Exception {
        // disable autoRefresh Sirius preference
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        // Step 1 : checking that both diagrams are empty
        assertTrue("Diagram should be empty", firstDiag.getOwnedDiagramElements().isEmpty());
        assertTrue("Diagram should be empty", secondDiag.getOwnedDiagramElements().isEmpty());

        // Step 2 : applying a Creation tool whit Force Refresh
        applyNodeCreationTool("createPackageWithRefresh", secondDiag, secondDiag);

        // Step 3 : ensuring that the Creation works and refresh has been activated only on the second diagram
        assertEquals("Force refresh diagram has not been refreshed. Bad diagram elements number in diagram "
                + new DRepresentationQuery(secondDiag).getRepresentationDescriptor().getName(), 1, secondDiag.getOwnedDiagramElements().size());
        assertTrue("The non 'force refresh' diagram has been refreshed whereas 'automatic refresh' is false.",
                firstDiag.getOwnedDiagramElements().isEmpty());
    }

    /**
     * Ensures that autoRefresh,when it is enable, works when force refresh tool is applied.
     * 
     * @throws Exception
     *             any exception
     */
    public void testForceRefreshWithAutoRefresh() throws Exception {
        // enable autoRefresh Sirius preference
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        // Step 1 : checking that both diagrams are empty
        assertTrue("Diagram should be empty", firstDiag.getOwnedDiagramElements().isEmpty());
        assertTrue("Diagram should be empty", secondDiag.getOwnedDiagramElements().isEmpty());

        // Step 2 : applying a Creation tool whit Force Refresh
        applyNodeCreationTool("createPackageWithRefresh", secondDiag, secondDiag);

        // Step 3 : ensuring that the Creation works and refresh has been activated on both diagram
        assertEquals("Force refresh diagram has not been refreshed. Bad diagram elements number in diagram "
                + new DRepresentationQuery(secondDiag).getRepresentationDescriptor().getName(), 1, secondDiag.getOwnedDiagramElements().size());
        assertEquals("Non 'Force refresh' diagram has not been refreshed. Bad diagram elements number in diagram "
                + new DRepresentationQuery(firstDiag).getRepresentationDescriptor().getName(), 1, firstDiag.getOwnedDiagramElements().size());
    }

    /**
     * Ensures that autoRefresh,when it is enable, works when tool without force refresh is applied.
     * 
     * @throws Exception
     *             any exception
     */
    public void testAutoRefreshWithoutForceRefresh() throws Exception {
        // enable autoRefresh Sirius preference
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        // Step 1 : checking that both diagrams are empty
        assertTrue("Diagram should be empty", firstDiag.getOwnedDiagramElements().isEmpty());
        assertTrue("Diagram should be empty", secondDiag.getOwnedDiagramElements().isEmpty());

        // Step 2 : applying a Creation tool without Force Refresh
        applyNodeCreationTool("createPackageNotRefresh", secondDiag, secondDiag);

        // Step 3 : ensuring that the Creation works and refresh has been activated on both diagram
        assertEquals("Force refresh diagram has not been refreshed. Bad diagram elements number in diagram "
                + new DRepresentationQuery(secondDiag).getRepresentationDescriptor().getName(), 1, secondDiag.getOwnedDiagramElements().size());
        assertEquals("Non 'Force refresh' diagram has not been refreshed. Bad diagram elements number in diagram "
                + new DRepresentationQuery(firstDiag).getRepresentationDescriptor().getName(), 1, firstDiag.getOwnedDiagramElements().size());
    }
}
