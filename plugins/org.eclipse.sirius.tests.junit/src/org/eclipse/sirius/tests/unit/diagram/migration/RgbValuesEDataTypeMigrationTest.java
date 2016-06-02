/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
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
import org.eclipse.sirius.business.internal.migration.RGBValuesToDataTypeMigrationParticipant;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.osgi.framework.Version;

/**
 * Ensures that RGBValues migration from EObject to data type is correctly done.
 *
 * See {@link RGBValuesToDataTypeMigrationParticipant} for more details.
 * 
 * @author mporhel
 */
public class RgbValuesEDataTypeMigrationTest extends SiriusTestCase {

    private static final String REPRESENTATIONS_FILE_PATH = "/data/unit/migration/do_not_migrate/rgbvalues/";

    private static final String REPRESENTATIONS_FILE_NAME = "rgbValues.aird";

    private static final String VSM_FILE_NAME = "rgbValuesMigration.odesign";

    private static final String MODEL_FILE_NAME = "rgbValues.ecore";

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
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = RGBValuesToDataTypeMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check the behavior of the RGBValues migration from EClass to EDatatype on
     * an aird loaded from plugins.
     */
    public void testRGBValuesToEDataTypeMigrationDoneInPlugin() {
        ResourceSet set = new ResourceSetImpl();

        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + REPRESENTATIONS_FILE_PATH + REPRESENTATIONS_FILE_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        assertNotNull("Check the aird test data.", analysis);

        // The version will only change on save, so here migration service will
        // still indicates that the migration is needed and no save is possible
        // because the loaded VSM is in plugins.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We have to check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(analysis);
    }

    /**
     * Check the behavior of the RGBValues migration from EClass to EDatatype on
     * an aird loaded from resources in workspace.
     */
    public void testRGBValuesToEDataTypeMigrationDone() {
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
        checkMigrationEffect(analysis);
    }

    private void checkMigrationEffect(DAnalysis analysis) {
        DView view = analysis.getOwnedViews().iterator().next();
        Collection<DRepresentation> allRepresentations = new DViewQuery(view).getLoadedRepresentations();
        assertEquals(3, allRepresentations.size());
        for (DRepresentation rep : allRepresentations) {
            if (rep instanceof DDiagram) {
                DDiagram diag = (DDiagram) rep;
                DDiagramElementContainer contClass = diag.getContainers().get(0);
                assertEquals("NewEClass1", contClass.getName());
                checkColors(contClass, false, RGBValues.create(154, 103, 23), RGBValues.create(255, 245, 181), RGBValues.create(246, 139, 139), RGBValues.create(114, 73, 110));

                DDiagramElementContainer contPackage = diag.getContainers().get(1);
                assertEquals("p", contPackage.getName());
                checkColors(contPackage, true, RGBValues.create(155, 199, 204), RGBValues.create(152, 168, 191), RGBValues.create(255, 255, 255), RGBValues.create(228, 179, 229));

                DDiagramElementContainer rootPackage = diag.getContainers().get(2);
                assertEquals("root", rootPackage.getName());
                checkColors(rootPackage, false, RGBValues.create(0, 0, 0), RGBValues.create(0, 0, 0), RGBValues.create(255, 255, 255), RGBValues.create(209, 209, 209));
            } else if (rep instanceof DTree) {
                DTreeItem item = (DTreeItem) rep.getRepresentationElements().iterator().next();
                assertEquals(RGBValues.create(204, 242, 166), item.getOwnedStyle().getLabelColor());
                assertEquals(RGBValues.create(253, 206, 137), item.getOwnedStyle().getBackgroundColor());
            } else if (rep instanceof DTable) {
                DTable table = (DTable) rep;

                DLine line = table.getLines().iterator().next();
                assertEquals(RGBValues.create(255, 245, 181), line.getCurrentStyle().getBackgroundColor());

                DColumn col = table.getColumns().iterator().next();
                assertEquals(RGBValues.create(239, 41, 41), col.getCurrentStyle().getForegroundColor());
            }
        }

    }

    private void checkColors(DDiagramElementContainer contClass, boolean customColors, RGBValues borderColor, RGBValues labelColor, RGBValues backgroundColor, RGBValues foregroundColor) {
        FlatContainerStyle ownedStyle = (FlatContainerStyle) contClass.getOwnedStyle();
        assertEquals(borderColor, ownedStyle.getBorderColor());
        assertEquals(labelColor, ownedStyle.getLabelColor());
        assertEquals(foregroundColor, ownedStyle.getForegroundColor());
        assertEquals(backgroundColor, ownedStyle.getBackgroundColor());

        if (customColors) {
            assertTrue(ownedStyle.getCustomFeatures().contains("labelColor"));
            assertTrue(ownedStyle.getCustomFeatures().contains("borderColor"));
            assertTrue(ownedStyle.getCustomFeatures().contains("foregroundColor"));
            assertTrue(ownedStyle.getCustomFeatures().contains("backgroundColor"));
        } else {
            assertTrue(ownedStyle.getCustomFeatures().isEmpty());
        }
    }
}
