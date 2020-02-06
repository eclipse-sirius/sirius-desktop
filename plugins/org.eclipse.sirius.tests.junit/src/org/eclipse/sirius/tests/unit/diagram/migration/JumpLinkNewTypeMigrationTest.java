/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.business.internal.migration.JumpLinkNewTypeMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

/**
 * Test that the version is correctly updated.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class JumpLinkNewTypeMigrationTest extends SiriusTestCase {

    /**
     * An existing model is used because because there is no real migration to check.
     */
    private static final String PATH = "data/unit/migration/do_not_migrate/edgeLabelMove/";

    private static final String SESSION_RESOURCE_NAME = "edgeLabelsMoveTest.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "edgeLabelsMoveTest.ecore";

    private static final String VSM_RESOURCE_NAME = "VSMForEdgeLabelsMoveTest.odesign";

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, VSM_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new JumpLinkNewTypeMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that the migration is done (ie migration version is changed).
     */
    public void testMigrationVersionSerialisation() {
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
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }
}
