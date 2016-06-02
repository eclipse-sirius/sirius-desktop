/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DiagramRepresentationsFileMigrationParticipantV690;
import org.eclipse.sirius.diagram.ui.business.internal.migration.description.OptionalLayersVSMMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.osgi.framework.Version;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class OptionalLayerToAdditionalLayerMigrationTest extends SiriusTestCase {

    private static final String DATA_PATH = "/data/unit/migration/do_not_migrate/VP-4179/";

    private static final String VSM_NAME = "vp-4179_migration.odesign";

    private static final String VSM_PATH = SiriusTestsPlugin.PLUGIN_ID + DATA_PATH + VSM_NAME;

    private static final String VSM_WITH_REF_TO_MIGRATED_VSM_PATH = SiriusTestsPlugin.PLUGIN_ID + DATA_PATH + "vp-4179_withExternalRef_migration.odesign";

    private static final String AIRD_NAME = "vp-4179_migration.aird";

    private static final String AIRD_PATH = SiriusTestsPlugin.PLUGIN_ID + DATA_PATH + AIRD_NAME;

    private static final String AIRD_WITH_REF_TO_MIGRATED_VSM_PATH = SiriusTestsPlugin.PLUGIN_ID + DATA_PATH + "vp-4179_withRefToMigrated_migration.aird";

    /**
     * {@inheritDoc}
     */
    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Checks that the data were not already migrated. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version firstVsmLoadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(VSM_PATH, true), true);
        Version secondVsmLoadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(VSM_WITH_REF_TO_MIGRATED_VSM_PATH, true), true);
        Version firstRepresentationsFileloadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(AIRD_PATH, true), true);
        Version secondRepresentationsFileloadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(AIRD_WITH_REF_TO_MIGRATED_VSM_PATH, true), true);

        // Check that the migration is needed.
        Version firstVsmMigrationVersion = new OptionalLayersVSMMigrationParticipant().getMigrationVersion();
        assertTrue("Data corrupted: The first VSM should require a migration corresponding to the optional layers.",
                firstVsmLoadedVersion == null || firstVsmMigrationVersion.compareTo(firstVsmLoadedVersion) > 0);

        Version secondVsmMigrationVersion = new OptionalLayersVSMMigrationParticipant().getMigrationVersion();
        assertTrue("Data corrupted: The first VSM should require a migration corresponding to the optional layers.",
                secondVsmLoadedVersion == null || secondVsmMigrationVersion.compareTo(secondVsmLoadedVersion) > 0);

        Version firstRepresentationsFileMigrationVersion = DiagramRepresentationsFileMigrationParticipantV690.MIGRATION_VERSION;
        assertTrue("Data corrupted: The representations file should require a migration corresponding to the optional layers.",
                firstRepresentationsFileloadedVersion == null || firstRepresentationsFileMigrationVersion.compareTo(firstRepresentationsFileloadedVersion) > 0);

        Version secondRepresentationsFileMigrationVersion = DiagramRepresentationsFileMigrationParticipantV690.MIGRATION_VERSION;
        assertTrue("Data corrupted: The representations file should require a migration corresponding to the optional layers.",
                secondRepresentationsFileloadedVersion == null || secondRepresentationsFileMigrationVersion.compareTo(secondRepresentationsFileloadedVersion) > 0);
    }

    public void testVSMMigrationInPlugin() {
        ResourceSet set = new ResourceSetImpl();

        Group modeler = null;
        try {
            modeler = (Group) ModelUtils.load(URI.createPlatformPluginURI(VSM_PATH, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.

        // The version will only change on save, so here migration service will
        // still indicates that the migration is needed and no save is possible
        // because the loaded VSM is in plugins.
        assertNotNull("The loaded Group from VSM is null.", modeler);
        String version = modeler.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We have to check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(modeler);
    }

    private void checkMigrationEffect(Group modeler) {

        List<AdditionalLayer> additonalLayers = Lists.newArrayList(Iterators.filter(modeler.eAllContents(), AdditionalLayer.class));
        assertEquals("The VSM model should contains 2 additional layers.", 2, additonalLayers.size());

        // Get the tool of the optional layer
        ToolEntry originalTool = ((DiagramDescription) modeler.getOwnedViewpoints().get(0).getOwnedRepresentations().get(0)).getAdditionalLayers().get(0).getToolSections().get(0).getOwnedTools()
                .get(0);
        // Get the reused tool in tool sections (should be the same as orinigal
        // tool)
        ToolEntry reusedToolInToolSection = ((DiagramDescription) modeler.getOwnedViewpoints().get(0).getOwnedRepresentations().get(1)).getDefaultLayer().getToolSections().get(0).getReusedTools()
                .get(0);
        assertEquals("The reused tools in tool section links should be conserved after migration.", originalTool, reusedToolInToolSection);

        // Get the reused tool in default layer (should be the same as orinigal
        // tool)
        ToolEntry reusedToolInLayer = ((DiagramDescription) modeler.getOwnedViewpoints().get(0).getOwnedRepresentations().get(1)).getDefaultLayer().getReusedTools().get(0);
        assertEquals("The reused tools in layer links should be conserved after migration.", originalTool, reusedToolInLayer);

        // Get the reused tool in diagram (should be the same as orinigal tool)
        ToolEntry reusedToolInDiagram = ((DiagramDescription) modeler.getOwnedViewpoints().get(0).getOwnedRepresentations().get(1)).getReusedTools().get(0);
        assertEquals("The reused tools in diagram links should be conserved after migration.", originalTool, reusedToolInDiagram);
    }

    /**
     * Check the behavior of the optional layer migration on a VSM loaded from
     * resources in workspace.
     */
    public void testVSMMigrationInWorkspace() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_PATH, VSM_NAME);

        ResourceSet set = new ResourceSetImpl();
        Group modeler = null;
        try {
            modeler = (Group) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + VSM_NAME, true), set);
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

        // We can now check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(modeler);
    }

    /**
     * In this test, the VMS to migrate uses an already migrated VSM through a
     * reference that uses the old "optionalLayers". This test ensures that this
     * reference is not a proxy after migration.
     */
    public void testVSMReferencingAlreadyMigratedVSMMigration() {
        ResourceSet set = new ResourceSetImpl();

        // Load (and so migrate) the VSM
        Group modelerWithReference = null;
        try {
            modelerWithReference = (Group) ModelUtils.load(URI.createPlatformPluginURI(VSM_WITH_REF_TO_MIGRATED_VSM_PATH, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        // Get the reused tool and ensure that it is not a proxy
        ToolEntry reusedToolInDiagram = ((DiagramDescription) modelerWithReference.getOwnedViewpoints().get(0).getOwnedRepresentations().get(0)).getReusedTools().get(0);
        assertFalse("The reused tools in diagram links should be conserved after migration and should not be a proxy.", reusedToolInDiagram.eIsProxy());
    }

    public void testRepresentationsFileMigrationInPlugin() {
        ResourceSet set = new ResourceSetImpl();

        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformPluginURI(AIRD_PATH, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        // The version will only change on save, so here migration service will
        // still indicates that the migration is needed and no save is possible
        // because the loaded VSM is in plugins.
        assertNotNull("The loaded DAnalysis from representations file is null.", analysis);
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        checkMigrationEffect(analysis, 2);
    }

    private void checkMigrationEffect(DAnalysis modeler, int nbActiveLayers) {
        Collection<Layer> activatedLayers = ((DDiagram) new DViewQuery(modeler.getSelectedViews().get(0)).getLoadedRepresentations().get(0)).getActivatedLayers();
        assertEquals("The diagram should contains " + nbActiveLayers + " activated layers.", nbActiveLayers, activatedLayers.size());
        for (Layer layer : activatedLayers) {
            assertFalse("The layer should not be a proxy.", layer.eIsProxy());
        }
    }

    public void testRepresentationsFileMigrationInWorkspace() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, DATA_PATH, AIRD_NAME, VSM_NAME);
        ResourceSet set = new ResourceSetImpl();

        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + AIRD_NAME, true), set);
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

        // Check that the migration was done.
        assertNotNull("The loaded DAnalysis from representations file is null.", analysis);
        version = analysis.getVersion();
        assertFalse("The representations file migration should be done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        checkMigrationEffect(analysis, 2);
    }

    public void testRepresentationsFileReferencingAlreadyMigratedVSMMigration() {
        ResourceSet set = new ResourceSetImpl();

        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformPluginURI(AIRD_WITH_REF_TO_MIGRATED_VSM_PATH, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        checkMigrationEffect(analysis, 3);
    }
}
