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

import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.sample.migration.design.Draw2dToSiriusModelTransformer;
import org.eclipse.sirius.tests.sample.migration.design.MigrationModeler;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.SWT;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * <pre>
 * This class tests if style customizations are the same before and
 * after a migration for the feature "Style.custom" to "Customizable.customFeatures".
 * 
 * File (containing Diagram) :
 * /data/unit/migration/TestCampaign_10/TestCampaign_10.aird
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
@RunWith(value = Parameterized.class)
public class DiagramMigrationTestCampaign10 extends AbstractMigrationTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/campaign/TestCampaign_10";

    private static final String SESSION_RESOURCE_FILENAME = "TestCampaign_10.aird";

    private static final String SEMANTIC_RESOURCE_FILENAME = "TestCampaign_10.migrationmodeler";

    private static final String IMAGE_FILENAME = "image.bmp";

    private static final String NEW_IMAGE_FILENAME = "image.jpg";

    private static final Integer nbDiagrams = 1;

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    private int diagramID;

    public DiagramMigrationTestCampaign10(int diagramID) throws Exception {
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

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + SESSION_RESOURCE_FILENAME);

        String projectRelativePath = GENERAL_TEST_CASE_PATH + "/" + SEMANTIC_RESOURCE_FILENAME;
        if (TestsUtil.isJuno3Platform() && "win32".equals(SWT.getPlatform())) {
            projectRelativePath = GENERAL_TEST_CASE_PATH + "/3.8/" + SEMANTIC_RESOURCE_FILENAME;
        }
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, projectRelativePath, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/" + IMAGE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "/" + NEW_IMAGE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + NEW_IMAGE_FILENAME);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testAllCustomisationsKeeped() throws Exception {
        if (!TestsUtil.isJuno3Platform()) {
            openEditorOnDiagram("" + diagramID, MigrationModeler.DIAGRAM_BIS_VIEWPOINT_NAME);
            assertTrue("", semanticModel instanceof TestCase);
            Diagram expectedDiagram = (Diagram) ((TestCase) semanticModel).getRepresentations().get(diagramID - 1);

            DDiagramEditPart dDiagramEditPart = getDDiagramEditPart();
            Draw2dToSiriusModelTransformer draw2dToSiriusModelTransformer = new Draw2dToSiriusModelTransformer(dDiagramEditPart);
            Diagram migrationModel = draw2dToSiriusModelTransformer.getMigrationModel();

            assertEqualsMigrationDiagrams(expectedDiagram, migrationModel);

            refresh(currentdRepresentation);

            migrationModel = draw2dToSiriusModelTransformer.getMigrationModel();

            assertEqualsMigrationDiagrams(expectedDiagram, migrationModel);
        }
    }

}
