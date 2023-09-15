/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
 * This class tests if coordinates (and bendpoints) are the same before and
 * after a migration for the "edge to edge" graphical element.
 * 
 * File (containing Diagram) :
 * /data/unit/migration/TestCampaign_09/TestCampaign_09.aird
 * 
 * Aird details (the number is the name of the diagram) :
 * 
 * 
 * 1 : test for a edge between a node and another edge
 * 2 : test for a edge between a container and another edge
 * 3 : test for a edge between a bordered node and another edge
 * 4 : test for a edge between a node (in a container) and another edge
 * 5 : test for a edge between a container (in a container) and another edge
 * 6 : test for a edge between another edge and another edge
 * 7 : test for a edge between a bordered node (in a container) and another edge
 * </pre>
 * 
 * @author MVenisse
 */
@RunWith(value = Parameterized.class)
public class DiagramMigrationTestCampaign09 extends AbstractMigrationTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/campaign/TestCampaign_09";

    private static final String SESSION_RESOURCE_FILENAME = "TestCampaign_09.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "TestCampaign_09.migrationmodeler";

    private static final Integer nbDiagrams = 7;

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private int diagramID;

    public DiagramMigrationTestCampaign09(int diagramID) throws Exception {
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

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/" + SESSION_RESOURCE_FILENAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/" + SEMANTIC_RESOURCE_FILENAME,
                "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);
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

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testEdgeLayout() throws Exception {
        openEditorOnDiagram("" + diagramID);
        assertTrue("", semanticModel instanceof TestCase);
        Representation representation = ((TestCase) semanticModel).getRepresentations().get(diagramID - 1);
        if (diagramID == 1 || diagramID == 2 || diagramID == 3 || diagramID == 4 || diagramID == 7) {
            // This particular diagram can has a difference of 2 pixel in the bendpoints position depending on the
            // graphical context (Linux native vs Linux Xvfb vs Linux Xvnc).
            checkEdgeLayout(representation, 2);
        } else {
            checkEdgeLayout(representation, 0);
        }
    }

}
