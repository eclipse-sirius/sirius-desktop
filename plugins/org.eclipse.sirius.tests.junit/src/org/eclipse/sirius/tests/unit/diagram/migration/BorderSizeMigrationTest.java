/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.internal.migration.BorderSizeRepresentationFileMigrationParticipant;
import org.eclipse.sirius.diagram.ui.business.internal.migration.description.BorderSizeComputationExpressionMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.unit.diagram.layout.margin.BorderMarginTest;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * Ensures that borderSize and borderSizeComputationExpression are correctly
 * done.
 *
 * See {@link BorderSizeRepresentationFileMigrationParticipant} and
 * {@link BorderSizeComputationExpressionMigrationParticipant} for more details.
 * 
 * See {@link BorderMarginTest}, for concrete test on border and figure for 0
 * pixel borders.
 * 
 * @author mporhel
 */
public class BorderSizeMigrationTest extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/migration/do_not_migrate/borderSize/";

    private static final String PATH_3_1_0 = "3.1.0/";

    private static final String PATH_3_1_3 = "3.1.3/";

    private static final String REPRESENTATIONS_FILE_NAME = "borderSizeMigration.aird";

    private static final String VSM_FILE_NAME = "borderSizeMigration.odesign";

    private static final String MODEL_FILE_NAME = "borderSizeMigration.ecore";

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testVsmMigrationIsNeededOnData() {
        Version loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + VSM_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = BorderSizeComputationExpressionMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);

        // Check the migration is also needed on 3.1.0 VSM file
        loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + PATH_3_1_0 + VSM_FILE_NAME, true), true);
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
        assertTrue("The current test VSM data should by partially migrated.", BorderSizeComputationExpressionMigrationParticipant.INITIAL_MIGRATION_VERSION.compareTo(loadedVersion) < 0);

        // Check the migration is also needed on 3.1.3 VSM file
        loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + PATH_3_1_3 + VSM_FILE_NAME, true), true);
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
        assertTrue("The current test VSM data should by partially migrated.", BorderSizeComputationExpressionMigrationParticipant.ALREADY_MIGRATED_VERSION.compareTo(loadedVersion) <= 0);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testAirdMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = BorderSizeRepresentationFileMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check the behavior of the border size migration on an aird loaded from
     * plugins.
     * 
     * @throws IOException
     */
    public void testBorderSizeMigrationDoneInPlugin() throws IOException {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();

        DAnalysis analysis = null;
        analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), set);

        // Check that the migration was done.
        assertNotNull("Check the aird test data.", analysis);

        // The version will only change on save, so here migration service will
        // still indicates that the migration is needed and no save is possible
        // because the loaded VSM is in plugins.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        analysis.eResource().save(Collections.emptyMap());

        // save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We have to check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(analysis, true);
    }

    /**
     * Check the behavior of the border size computation expression migration a
     * vsm loaded from plugins.
     */
    public void testBorderSizeComputationExpressionMigrationDoneInPlugin() {
        ResourceSet set = new ResourceSetImpl();

        Group group = null;
        try {
            group = (Group) ModelUtils.load(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + VSM_FILE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        assertNotNull("Check the vsm test data.", group);

        // The version will only change on save, so here migration service will
        // still indicates that the migration is needed and no save is possible
        // because the loaded VSM is in plugins.
        String version = group.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We have to check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(group, true);
    }

    /**
     * Check the behavior of the border size migration on an aird loaded from
     * resources in workspace.
     */
    public void testBorderSizeMigrationDone() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, VSM_FILE_NAME, MODEL_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.

        assertNotNull("Check the VSM test data.", analysis);

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

        // We can now check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(analysis, true);
    }

    /**
     * Check the behavior of the border size computation expression migration a
     * vsm loaded from plugins.
     */
    public void testBorderSizeComputationExpressionMigrationDone() {
        doTestBorderSizeComputationExpressionMigration(REPRESENTATIONS_FILE_PATH, true);
    }

    /**
     * Check that the VSM files migrated in 3.1.0 will receive the new version
     * but that the migration will not produce any changes.
     */
    public void testBorderSizeComputationExpressionMigrationNotDoneOn3_1_0_files() {
        doTestBorderSizeComputationExpressionMigration(REPRESENTATIONS_FILE_PATH + PATH_3_1_0, false);
    }

    /**
     * Check that the VSM files migrated in 3.1.3 will receive the new version
     * but that the migration will not produce any changes.
     */
    public void testBorderSizeComputationExpressionMigrationNotDoneOn3_1_3_files() {
        doTestBorderSizeComputationExpressionMigration(REPRESENTATIONS_FILE_PATH + PATH_3_1_3, false);
    }

    private void doTestBorderSizeComputationExpressionMigration(String path, boolean expectedMigrationEffect) {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, path, REPRESENTATIONS_FILE_NAME, VSM_FILE_NAME, MODEL_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();
        Group group = null;
        try {
            group = (Group) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + VSM_FILE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        assertNotNull("Check the VSM test data.", group);

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = group.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            group.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // save should update the version.
        version = group.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We can now check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(group, expectedMigrationEffect);
    }

    private void checkMigrationEffect(Group group, boolean expectedMigration) {
        String expectedBorderSizeExpression = "0";
        String message = "The VSM migration is not expected to migrate the ";
        if (expectedMigration) {
            expectedBorderSizeExpression = "1";
            message = "The VSM migration is expected to migrate the ";

        }

        UnmodifiableIterator<ContainerMapping> containerMappings = Iterators.filter(group.eAllContents(), ContainerMapping.class);
        assertEquals(12, Iterators.size(containerMappings));

        while (containerMappings.hasNext()) {
            ContainerMapping cm = containerMappings.next();
            ContainerStyleDescription style = cm.getStyle();
            assertEquals("The VSM migration is expected to migrate the default style of " + cm.getName(), expectedBorderSizeExpression, style.getBorderSizeComputationExpression());

            style = cm.getConditionnalStyles().get(0).getStyle();
            assertEquals(message + "conditional style of " + cm.getName(), expectedBorderSizeExpression, style.getBorderSizeComputationExpression());
        }

    }

    private void checkMigrationEffect(DAnalysis analysis, boolean expectedMigration) {
        int expectedBorderSize = 0;
        String expectedBorderSizeExpression = "0";
        String message = "The representation file migration is not expected to migrate the style of ";
        if (expectedMigration) {
            expectedBorderSize = 1;
            expectedBorderSizeExpression = "1";
            message = "The representation file migration is expected to migrate the style of ";
        }

        DView view = analysis.getOwnedViews().iterator().next();
        Collection<DRepresentation> allRepresentations = new DViewQuery(view).getLoadedRepresentations();
        assertEquals(2, allRepresentations.size());

        UnmodifiableIterator<DNodeContainer> containers = Iterators.filter(view.eAllContents(), DNodeContainer.class);
        assertEquals(24, Iterators.size(containers));

        while (containers.hasNext()) {
            DNodeContainer container = containers.next();
            ContainerStyle style = (ContainerStyle) container.getStyle();
            assertEquals(message + container.getName(), expectedBorderSize, style.getBorderSize().intValue());

            // Complementary check as the style's
            // borderSizeComputationExpression is not used.
            assertEquals(message + container.getName(), expectedBorderSizeExpression, style.getBorderSizeComputationExpression());
        }
    }
}
