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
package org.eclipse.sirius.tests.unit.migration;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tree.business.internal.migration.description.InitializeCreationToolElementsToSelectExpressionParticipant;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.osgi.framework.Version;

/**
 * Ensures that "Elements to select" expression is correctly managed
 *
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class InitializeElementsToSelectExpressionForTreeMigrationTest extends SiriusTestCase {

    private static final String PATH = "/data/unit/tools/selection/";

    private static final String MODELER_FILE_NAME = "VSMForSelection.odesign";

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        return null;
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkVsmFileMigrationStatus(URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + MODELER_FILE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = InitializeCreationToolElementsToSelectExpressionParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * Check that the ElementsToSelect expression is correctly initialized
     */
    public void testElementsToSelectExpressionMigration() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, MODELER_FILE_NAME);

        ResourceSet set = new ResourceSetImpl();
        Group vsmRoot = null;
        vsmRoot = (Group) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME, true), set);

        // Check that the migration was done.
        AbstractToolDescription tool = getTool(vsmRoot, "Tree_CreateItem");
        assertEquals("Bad ElementsToSelect expression", tool.getElementsToSelect(), InitializeCreationToolElementsToSelectExpressionParticipant.ELEMENTS_TO_SELECT_EXPRESSION);

        tool = getTool(vsmRoot, "Initialized_Tree_CreateItem");
        assertEquals("Bad ElementsToSelect expression", tool.getElementsToSelect(), "[instance/]");

        try {
            vsmRoot.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // save should update the version.
        String version = vsmRoot.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", VSMMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

    }

    private AbstractToolDescription getTool(EObject root, String name) {
        TreeIterator<EObject> allContents = root.eAllContents();
        while (allContents.hasNext()) {
            EObject eObject = allContents.next();
            if (eObject instanceof AbstractToolDescription && ((AbstractToolDescription) eObject).getName().equals(name)) {
                return (AbstractToolDescription) eObject;
            }
        }
        return null;
    }
}
