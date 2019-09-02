/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.migration.ActivatedFilterSortingMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

/**
 * Test class to test the migration done on the activated filters list of diagrams.
 * 
 * @author pguilet
 */
public class ActivatedFilterSortingMigrationParticipantTest extends SiriusTestCase {

    private static final String PATH = "/data/unit/migration/do_not_migrate/sortingFilter/";

    private static final String SESSION_RESOURCE_NAME = "filterMigration.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "filterMigration.ecore";

    private static final String DESIGN_FILENAME = "filterMigration.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_RESOURCE_NAME, DESIGN_FILENAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testAirdMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = ActivatedFilterSortingMigrationParticipant.MIGRATION_VERSION_FILTER_SORTING;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }

    /**
     * Check the migration is done and does not produce errors during load.
     */
    public void testFilterSortingMigration() {
        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), set);
        } catch (IOException e) {
            failCheckData();
        }

        // Check that the migration was done.
        assertNotNull("Check the representation file test data.", analysis);

        // The version will change on save, so migration service will still
        // indicate that the migration is needed.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            failCheckData();
        }
        // save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));
       
        DDiagram diagram = (DDiagram) analysis.getOwnedViews().get(0).getOwnedRepresentationDescriptors().get(0).getRepresentation();
        assertEquals("Activated filters have not been sorted.", "ClassFilter", diagram.getActivatedFilters().get(0).getName());
        assertEquals("Activated filters have not been sorted.", "ClassFilter2", diagram.getActivatedFilters().get(1).getName());

        assertFalse(getErrorLoggersMessage(), doesAnErrorOccurs());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
