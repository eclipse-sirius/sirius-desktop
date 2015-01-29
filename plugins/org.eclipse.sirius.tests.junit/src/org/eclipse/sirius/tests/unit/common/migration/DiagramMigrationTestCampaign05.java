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
 * This class tests if hidden graphical elements are always hidden after a migration.
 * File (containing Diagram) : /data/unit/migration/TestCampaign_05/TestCampaign_05.aird
 * 
 * Aird details (the number is the name of the diagram) :
 * 1 : test if an hidden node is always hidden after a migration
 * 2 : test if an hidden container is always hidden after a migration
 * 3 : test if an hidden bordered node is always hidden after a migration
 * 4 : test if an hidden node (in a container) is always hidden after a migration
 * 5 : test if an hidden container (in a container) is always hidden after a migration
 * </pre>
 * 
 * @author MVenisse
 */
@RunWith(value = Parameterized.class)
public class DiagramMigrationTestCampaign05 extends AbstractMigrationTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/campaign/TestCampaign_05";

    private static final String SESSION_RESOURCE_FILENAME = "TestCampaign_05.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "TestCampaign_05.migrationmodeler";

    private static final Integer nbDiagrams = 5;

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private int diagramID;

    public DiagramMigrationTestCampaign05(int diagramID) throws Exception {
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

    @Test
    public void testHide() throws Exception {
        openEditorOnDiagram("" + diagramID);
        assertTrue("", semanticModel instanceof TestCase);
        Representation representation = ((TestCase) semanticModel).getRepresentations().get(diagramID - 1);
        checkHidden(representation);
    }

}
