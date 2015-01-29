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
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.internal.migration.description.ListContainerAttributeMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Ensures that listContainer attribute migration to childrenPresentation =
 * ContainerLayout.LIST is correctly done.
 * 
 * <p>
 * <li><b>Relevant tickets</b> : VP-3858</li>
 * </p>
 */
public class ListContainerAttributeMigrationTest extends SiriusTestCase {

    private static final String VSM_PATH = "/data/unit/migration/do_not_migrate/";

    private static final String VSM_NAME = "listMigration_DoNotMigrate.odesign";

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
        Version loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + VSM_PATH + VSM_NAME, true), true);

        // Check that the sequence migration is needed.
        Version listMigration = new ListContainerAttributeMigrationParticipant().getMigrationVersion();
        assertTrue("The list migration must be required on test data.", loadedVersion == null || listMigration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check the behavior of the listContainer attribute migration to
     * childrenPresentation on a VSM loaded from plugins.
     */
    public void testListContainerAttributeMigrationDoneInPlugin() {
        ResourceSet set = new ResourceSetImpl();

        Group modeler = null;
        try {
            modeler = (Group) ModelUtils.load(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + VSM_PATH + VSM_NAME, true), set);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check that the migration was done.
        assertNotNull("Check the VSM test data.", modeler);

        // The version will only change on save, so here migration service will
        // still indicates that the migration is needed and no save is possible
        // because the loaded VSM is in plugins.
        String version = modeler.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We have to check the migration effect to be sure that the migration
        // is effective.
        checkMigrationEffect(modeler);
    }

    /**
     * Check the behavior of the listContainer attribute migration to
     * childrenPresentation on a VSM loaded from resources in workspace.
     */
    public void testListContainerAttributeMigrationDone() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, VSM_PATH, VSM_NAME);

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

    private void checkMigrationEffect(Group modeler) {
        // The data contains 2 ContainerMapping : one standard with FreeForm
        // children presentation and a list container, ie with LIST children
        // presentation.
        // state.
        List<ContainerMapping> containerMappings = Lists.newArrayList(Iterators.filter(modeler.eAllContents(), ContainerMapping.class));
        assertEquals("The model should contains 2 containerMappings collapse.", 2, containerMappings.size());

        ContainerMapping c1 = containerMappings.get(0);
        ContainerMapping c2 = containerMappings.get(1);

        assertEquals("The first container should be a standard container.", ContainerLayout.FREE_FORM, c1.getChildrenPresentation());

        assertEquals("The second container should be a list container.", ContainerLayout.LIST, c2.getChildrenPresentation());
    }
}
