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
package org.eclipse.sirius.tests.unit.diagram.migration;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV801;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * Test if the migration of VP-4650 is correctly launched. This migration add a
 * HideLabelFilter when missing if the corresponding GMF label view is hidden.
 * 
 * @author fbarbin
 * 
 */
public class MigrationOfHideLabelFilterInconsistencyTest extends SiriusDiagramTestCase {

    private String VSM_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/VP-4650/VP-4650.odesign";

    private String MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/VP-4650/VP-4650.ecore";

    private String REPRESENTATIONS_FILE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/migration/VP-4650/VP-4650.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(MODEL_PATH, VSM_PATH, REPRESENTATIONS_FILE_PATH);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(REPRESENTATIONS_FILE_PATH, true), true);

        // Check that the migration is needed.
        Version migration = DiagramRepresentationsFileMigrationParticipantV801.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * The test case contains two border nodes with an hidden label but one
     * hasn't HideLabelFilter. It contains a DEdge without HideLabelFilter too.
     * This test checks that the HideLabelFilter are restored by the migration
     * for elements in this inconsistency.
     */
    public void testMigration() {

        for (DView view : session.getOwnedViews()) {
            DDiagram representation = (DDiagram) new DViewQuery(view).getLoadedRepresentations().get(0);

            DEdge dEdge = representation.getEdges().get(0);
            DNode bNode1 = ((AbstractDNode) representation.getOwnedDiagramElements().get(0)).getOwnedBorderedNodes().get(0);
            DNode bNode2 = ((AbstractDNode) representation.getOwnedDiagramElements().get(1)).getOwnedBorderedNodes().get(0);

            assertTrue("The DNode should have a HideLabelFilter", !bNode1.getGraphicalFilters().isEmpty() && bNode1.getGraphicalFilters().get(0) instanceof HideLabelFilter);

            assertTrue("The DNode should have a HideLabelFilter", !bNode2.getGraphicalFilters().isEmpty() && bNode2.getGraphicalFilters().get(0) instanceof HideLabelFilter);

            assertTrue("The DEdge should have a HideLabelFilter", !dEdge.getGraphicalFilters().isEmpty() && dEdge.getGraphicalFilters().get(0) instanceof HideLabelFilter);
        }

    }
}
