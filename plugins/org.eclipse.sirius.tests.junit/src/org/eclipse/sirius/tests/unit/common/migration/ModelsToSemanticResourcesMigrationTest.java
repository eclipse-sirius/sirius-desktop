/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.migration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.migration.ReplaceModelsBySemanticResources;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.internal.helper.AirdFileParser;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

/**
 * Ensures that models URIs migration to ResourceDescriptor data type is
 * correctly done.
 *
 * See {@link ReplaceModelsBySemanticResources} for more details.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ModelsToSemanticResourcesMigrationTest extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/control/multisession/";

    private static final String REPRESENTATIONS_FILE_NAME = "consumer.aird";

    private static final String REPRESENTATIONS_FILE_NAME2 = "consumerWithNonExistingModels.aird";

    private static final String MODEL_FILE_NAME = "consumer.ecore";

    private static final String MODEL_FILE_NAME2 = "NonExistingConsumer.ecore";

    private static final String MODEL_FILE_NAME_LIB = "lib.ecore";

    private static final String OTHER_PROJECT_IN_WS = "SiriusLibrary";

    private static final String RESOURCE_URI_IN_OTHER_PROJECT = "platform:/resource/" + OTHER_PROJECT_IN_WS + "/lib.ecore";

    private static final String RESOURCE_URI_IN_OTHER_PROJECT2 = "platform:/resource/" + OTHER_PROJECT_IN_WS + "/NonExistingLib.ecore";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = ReplaceModelsBySemanticResources.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the DAnalysis.semanticResources is correctly initialized from
     * DAnalysis.models
     */
    public void testModelsToSemanticResourcesMigration() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME, MODEL_FILE_NAME);
        copyFiles(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, OTHER_PROJECT_IN_WS + File.separator, MODEL_FILE_NAME_LIB);

        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.

        assertNotNull("Analysis is not retrieved.", analysis);

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
        checkMigrationEffect(analysis);

    }

    private void checkMigrationEffect(DAnalysis analysis) {
        // 1 - Check getSemanticResources
        EList<ResourceDescriptor> semanticResources = analysis.getSemanticResources();
        assertEquals("DAnalyis.getSemanticResources size error", 2, semanticResources.size());

        assertEquals("Bad semantic resource", new ResourceDescriptor("platform:/resource/DesignerTestProject/consumer.ecore"), semanticResources.get(0));
        assertEquals("Bad semantic resource", new ResourceDescriptor("platform:/resource/SiriusLibrary/lib.ecore"), semanticResources.get(1));

        // 2 - Check getModels
        EList<EObject> models = analysis.getModels();
        assertEquals("DAnalysis.getModels size error", 2, models.size());

        String string = ((ENamedElement) models.get(0)).getName();
        assertEquals("Bad model", "ConsumerRoot", string);
        string = ((ENamedElement) models.get(1)).getName();
        assertEquals("Bad model", "P0", string);

        // 3 - Check DAnalyis.semanticResources serialization
        List<String> semanticResourcesTagContent = AirdFileParser.parseSemanticResourcesTag(TEMPORARY_PROJECT_NAME);
        assertTrue("Bad semantic resource tag in aird file", semanticResourcesTagContent.contains(MODEL_FILE_NAME));
        assertTrue("Bad semantic resource tag in aird file", semanticResourcesTagContent.contains(RESOURCE_URI_IN_OTHER_PROJECT));
    }

    /**
     * Check that the DAnalysis.semanticResources is correctly initialized from
     * DAnalysis.models even if the model is unresolvable
     */
    public void testModelsToSemanticResourcesMigrationForNonResolvableModel() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, REPRESENTATIONS_FILE_PATH, REPRESENTATIONS_FILE_NAME2);

        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE_NAME2, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        checkMigrationEffectForNonResolvableModel(analysis);
    }

    private void checkMigrationEffectForNonResolvableModel(DAnalysis analysis) {
        // 1 - Check getSemanticResources
        EList<ResourceDescriptor> semanticResources = analysis.getSemanticResources();
        assertEquals("DAnalyis.getSemanticResources size error", 2, semanticResources.size());

        assertEquals("Bad semantic resource", new ResourceDescriptor("platform:/resource/DesignerTestProject/NonExistingConsumer.ecore"), semanticResources.get(0));
        assertEquals("Bad semantic resource", new ResourceDescriptor("platform:/resource/SiriusLibrary/NonExistingLib.ecore"), semanticResources.get(1));

        // 2 - Check getModels
        EList<EObject> models = analysis.getModels();
        assertEquals("DAnalysis.getModels size error", 0, models.size());

        // 3 - Check DAnalyis.semanticResources serialization
        List<String> semanticResourcesTagContent = AirdFileParser.parseSemanticResourcesTag(TEMPORARY_PROJECT_NAME);
        assertTrue("Bad semantic resource tag in aird file", semanticResourcesTagContent.contains(MODEL_FILE_NAME2));
        assertTrue("Bad semantic resource tag in aird file", semanticResourcesTagContent.contains(RESOURCE_URI_IN_OTHER_PROJECT2));
    }

}
