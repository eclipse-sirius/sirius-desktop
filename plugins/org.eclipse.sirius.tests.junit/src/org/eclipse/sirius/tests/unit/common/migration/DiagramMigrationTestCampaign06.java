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
package org.eclipse.sirius.tests.unit.common.migration;

import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;

/**
 * <pre>
 * This class tests if graphical element (unsynchro) are not lost after a migration.
 * File (containing Diagram) : /data/unit/migration/TestCampaign_06/TestCampaign_06.aird
 * 
 * Aird details (the number is the name of the diagram) :
 * 1 : test if a node (unsynchro) is not lost after a migration
 * 2 : test if a container (unsynchro) is not lost after a migration
 * 3 : test if a bordered node (unsynchro) is not lost after a migration
 * 4 : test if an edge (unsynchro) between two nodes is not lost after a migration
 * 5 : test if an edge (unsynchro) between two containers is not lost after a migration
 * 6 : test if an edge (unsynchro) between two bordered nodes is not lost after a migration
 * 7 : test if an edge (unsynchro) between a node and a container is not lost after a migration
 * 8 : test if an edge (unsynchro) between a node and a bordered node is not lost after a migration
 * 9 : test if an edge (unsynchro) between a container and a bordered node is not lost after a migration
 * 10 : test if a node (unsynchro) inside a container is not lost after a migration
 * 11 : test if a container (unsynchro) inside a container is not lost after a migration
 * </pre>
 * 
 * @author MVenisse
 */
public class DiagramMigrationTestCampaign06 extends AbstractMigrationTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/campaign/TestCampaign_06";

    private static final String SESSION_RESOURCE_FILENAME = "TestCampaign_06.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "TestCampaign_06.migrationmodeler";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SEMANTIC_RESOURCE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
    }

    /**
     * 
     * @throws Exception
     */
    public void testAllDiagramElementPresentUnsynchroDiagram() throws Exception {

        int nbDiagrams = ((TestCase) semanticModel).getRepresentations().size();

        for (int i = 1; i <= nbDiagrams; i++) {
            openEditorOnDiagram("" + i);
            assertTrue("", semanticModel instanceof TestCase);
            Representation representation = ((TestCase) semanticModel).getRepresentations().get(i - 1);
            checkAllDiagramElementPresent(representation);
        }

    }

}
