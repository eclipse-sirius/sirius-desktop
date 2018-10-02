/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.table.business.internal.migration.description.LabelEditToolVariableMigrationParticipant;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;

/**
 * Ensures correct migration of
 * {@link LabelEditToolVariableMigrationParticipant}.
 *
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 */
public class LabelEditToolVariableMigrationTest extends SiriusTestCase {
    private static final String FOLDER_PATH = "data/table/unit/migration/";

    private static final String VSM_NAME = "tables.odesign";

    private static final String TEST_DIR = "/" + SiriusTestsPlugin.PLUGIN_ID + FOLDER_PATH;

    private static final String MODELER_PATH = TEST_DIR + VSM_NAME;

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated. It allows to check the effect of
     * the migration in the other test.
     */
    public void testVSMMigrationIsNeededOnData() {
        Version loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + MODELER_PATH, true), true);

        // Check that the migration is needed.
        Version migration = LabelEditToolVariableMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the line and table variables are present on table and cross
     * table's label edit tool after the migration of
     * {@link LabelEditToolVariableMigrationParticipant}.
     * 
     * @throws IOException
     *             if a save problem occurs.
     */
    public void testLabelEditToolVariableMigration() throws IOException {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, FOLDER_PATH, VSM_NAME);
        ResourceSet set = new ResourceSetImpl();
        Group group = null;
        Resource resourceVSM = set.getResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + VSM_NAME, true), true);
        group = (Group) resourceVSM.getContents().get(0);

        assertNotNull("Group is not retrieved.", group);

        // The version will change on save, so migration service will still
        // indicates that the migration is needed.
        String version = group.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        resourceVSM.save(Collections.emptyMap());

        // save should update the version.
        version = group.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // We can now check the migration effect to be sure that the migration
        // is effective.
        checkVSMMigrationEffect(group);

    }

    /**
     * Checks that all label edit tool from table and cross tables does contain
     * the line and table variables.
     * 
     * @param group
     *            the group from which the check is done.
     */
    private void checkVSMMigrationEffect(Group group) {
        TreeIterator<EObject> eAllContents = group.eAllContents();
        while (eAllContents.hasNext()) {
            EObject eObject = eAllContents.next();
            if (eObject instanceof LabelEditTool) {
                LabelEditTool labelEditTool = (LabelEditTool) eObject;
                assertTrue("The label edit tool has not the variable line", labelEditTool.getVariables().stream().anyMatch(variable -> "line".equals(variable.getName())));
                assertTrue("The label edit tool has not the variable table", labelEditTool.getVariables().stream().anyMatch(variable -> "table".equals(variable.getName())));
            }

        }
    }
}
