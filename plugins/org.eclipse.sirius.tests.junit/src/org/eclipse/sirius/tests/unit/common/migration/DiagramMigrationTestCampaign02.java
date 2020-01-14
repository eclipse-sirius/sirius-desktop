/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
 * This class tests if the coordinates of graphical elements are the same after a migration.
 * File (containing Diagram) : /data/unit/migration/TestCampaign_02/TestCampaign_02.aird
 * 
 * Aird details (the number is the name of the diagram) :
 * 1 : test if a node has the same coordinates after a migration
 * 2 : test if a container has the same coordinates after a migration
 * 3 : test if a bordered node has the same coordinates after a migration
 * 4 : test if an edge between two nodes has the same coordinates after a migration
 * 5 : test if an edge between two containers has the same coordinates after a migration
 * 6 : test if an edge between two bordered nodes has the same coordinates after a migration
 * 7 : test if an edge between a node and a container has the same coordinates after a migration
 * 8 : test if an edge between a node and a bordered has the same coordinates after a migration
 * 9 : test if an edge between a container and a bordered node has the same coordinates after a migration
 * 10 : test if a node (inside a container) has the same coordinates after a migration
 * 11 : test if a container (inside a container) has the same coordinates after a migration
 * 12 : test layout using SVG without attribute viewBox 
 * 13 : test layout using SVG with attribute viewBox
 * </pre>
 * 
 * @author MVenisse
 */
@RunWith(value = Parameterized.class)
public class DiagramMigrationTestCampaign02 extends AbstractMigrationTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/campaign/TestCampaign_02";

    private static final String SESSION_RESOURCE_FILENAME = "TestCampaign_02.aird";

    protected static final String SEMANTIC_RESOURCE_FILENAME = "TestCampaign_02.migrationmodeler";

    private static final Integer nbDiagrams = 13;

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private int diagramID;

    public DiagramMigrationTestCampaign02(int diagramID) throws Exception {
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
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/dot.svg", "/" + TEMPORARY_PROJECT_NAME + "/dot.svg");
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/transparentRectangle.svg", "/" + TEMPORARY_PROJECT_NAME + "/transparentRectangle.svg");
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
    }

    @Test
    public void testNodeLayout() throws Exception {
        openEditorOnDiagram("" + diagramID);
        assertTrue("", semanticModel instanceof TestCase);
        Representation representation = ((TestCase) semanticModel).getRepresentations().get(diagramID - 1);
        checkNodeLayout(representation);
    }

    /**
     * Test post-migration edges layout.
     * 
     * @throws Exception
     */
    @Test
    public void testEdgeLayout() throws Exception {
        openEditorOnDiagram("" + diagramID);
        assertTrue("", semanticModel instanceof TestCase);
        Representation representation = ((TestCase) semanticModel).getRepresentations().get(diagramID - 1);
        if (diagramID == 13) {
            // This particular diagram can has a difference of 1 pixel in the bendpoints position depending on the
            // graphical context (Linux native vs Linux Xvfb vs Linux Xvnc).
            checkEdgeLayout(representation, 1);
        } else {
            checkEdgeLayout(representation);
        }
    }

}
