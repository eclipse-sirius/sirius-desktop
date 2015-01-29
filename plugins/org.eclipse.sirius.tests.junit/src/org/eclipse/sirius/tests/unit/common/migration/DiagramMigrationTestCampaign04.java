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
 * This class tests if pin (or unpin) graphical elements are always pin (or unpin) after a migration.
 * File (containing Diagram) : /data/unit/migration/TestCampaign_04/TestCampaign_04.aird
 * 
 * Aird details (the number is the name of the diagram) :
 * 1 : test if an unpin node is always unpin after a migration
 * 2 : test if an unpin container is always unpin after a migration
 * 3 : test if an unpin bordered node is always unpin after a migration
 * 4 : test if an unpin node (in a container) is always unpin after a migration
 * 5 : test if an unpin container (in a container) is always unpin after a migration
 * 6 : test if a pin node is always pin after a migration
 * 7 : test if a pin container is always pin after a migration
 * 8 : test if a pin bordered node is always pin after a migration
 * 9 : test if a pin node (in a container) is always pin after a migration
 * 10 : test if a pin container (in a container) is always pin after a migration
 * </pre>
 * 
 * @author MVenisse
 */
@RunWith(value = Parameterized.class)
public class DiagramMigrationTestCampaign04 extends AbstractMigrationTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/campaign/TestCampaign_04";

    private static final String SESSION_RESOURCE_FILENAME = "TestCampaign_04.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "TestCampaign_04.migrationmodeler";

    private static final Integer nbDiagrams = 10;

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private int diagramID;

    public DiagramMigrationTestCampaign04(int diagramID) throws Exception {
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
    public void testPinUnpin() throws Exception {
        openEditorOnDiagram("" + diagramID);
        assertTrue("", semanticModel instanceof TestCase);
        Representation representation = ((TestCase) semanticModel).getRepresentations().get(diagramID - 1);
        checkPinUnpin(representation);
    }

}
