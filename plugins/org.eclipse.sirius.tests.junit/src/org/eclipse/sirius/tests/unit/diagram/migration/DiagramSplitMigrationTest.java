/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramSplitMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * Ensures that version attributes are set during save. Migration occurs during load, not during resource simple
 * creation.
 */
public class DiagramSplitMigrationTest extends SiriusTestCase {

    private static final String PATH = "data/unit/migration/do_not_migrate/splitDiagram/";

    private static final String VSM_RESOURCE_NAME = "split.odesign";

    private static final String SESSION_RESOURCE_NAME = "split.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "split.ecore";

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, VSM_RESOURCE_NAME, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version diagramSplitVersion = new DiagramSplitMigrationParticipant().getMigrationVersion();

        // Check that the vsm migration is needed.
        Version loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + VSM_RESOURCE_NAME, true), true);
        assertTrue("The list migration must be required on test data.", diagramSplitVersion.compareTo(loadedVersion) > 0);

        // Check that the representation file migration is needed.
        loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The list migration must be required on test data.", diagramSplitVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Check the migration is done and does not produce errors during VSM load.
     */
    public void testDiagramSplitVSMMigrationDone() {
        ResourceSet set = new ResourceSetImpl();
        Group modeler = null;
        try {
            modeler = (Group) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.

        assertNotNull("Check the VSM test data.", modeler);

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = modeler.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            modeler.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // save should update the version.
        version = modeler.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        assertFalse(platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }

    /**
     * Check the migration is done and does not produce errors during VSM load.
     */
    public void testDiagramSplitRepresentationFileMigrationDone() {
        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.

        assertNotNull("Check the representation file test data.", analysis);

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        assertFalse(platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }

}
