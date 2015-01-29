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

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * <pre>
 * This class tests if graphical element are not lost after a migration.
 * File (containing Diagram) : /data/unit/migration/TestCampaign_01/TestCampaign_01.aird
 * 
 * Aird details (the number is the name of the diagram) :
 * 1 : test if a node is not lost after a migration
 * 2 : test if a container is not lost after a migration
 * 3 : test if a bordered node is not lost after a migration
 * 4 : test if an edge between two nodes is not lost after a migration
 * 5 : test if an edge between two containers is not lost after a migration
 * 6 : test if an edge between two bordered nodes is not lost after a migration
 * 7 : test if an edge between a node and a container is not lost after a migration
 * 8 : test if an edge between a node and a bordered node is not lost after a migration
 * 9 : test if an edge between a container and a bordered node is not lost after a migration
 * 10 : test if a node (inside a container) is not lost after a migration
 * 11 : test if a container (inside a container) is not lost after a migration
 * </pre>
 */
@RunWith(value = Parameterized.class)
public class DiagramMigrationTestCampaign01 extends AbstractMigrationTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/campaign/TestCampaign_01";

    private static final String SESSION_RESOURCE_FILENAME = "TestCampaign_01.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "TestCampaign_01.migrationmodeler";

    private static final Integer nbDiagrams = 11;

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private int diagramID;

    public DiagramMigrationTestCampaign01(int diagramID) throws Exception {
        this.diagramID = diagramID;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[nbDiagrams][1];
        for (int i = 1; i <= nbDiagrams; i++) {
            data[i - 1][0] = i;
        }
        return Arrays.asList(data);
    }

    @Override
    @Before
    public void setUp() throws Exception {
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
    @Test
    public void testAllDiagramElementPresent() throws Exception {
        openEditorOnDiagram("" + diagramID);
        assertTrue("", semanticModel instanceof TestCase);
        Representation representation = ((TestCase) semanticModel).getRepresentations().get(diagramID - 1);
        checkAllDiagramElementPresent(representation);
    }

}
