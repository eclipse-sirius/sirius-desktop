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

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileResourceHandler;
import org.eclipse.sirius.business.internal.migration.description.VSMExtendedMetaData;
import org.eclipse.sirius.business.internal.migration.description.VSMResourceHandler;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;

/**
 * Test that the migration mechanism is properly installed (or removed) in the
 * following cases:
 * <ul>
 * <li>The mechanism is removed when the file is reloaded with a migrated
 * version.</li>
 * <li>The mechanism is installed when the file is reloaded with an older
 * version.</li>
 * <li>The mechanism is not installed when reloading a migrated version.</li>
 * <li>The mechanism is updated when reloading an other version that also needs
 * migration.</li>
 * </ul>
 * 
 * @author Florian Barbin
 *
 */
public class GeneralMigrationMechanismTest extends SiriusTestCase {

    private static final String GENERAL_TEST_CASE_PATH = "/data/unit/migration/do_not_migrate/general/";

    private static final String AIRD_RESOURCE_FILENAME = "representations.aird";

    private static final String ODESIGN_RESOURCE_FILENAME = "description.odesign";

    /**
     * Tests that the Odesign resource has the migration mechanism installed
     * after having reloaded with an older version.
     */
    public void testReplaceOdesignWithOlderVersion() {
        testReplaceResourceWithOlderVersion(ODESIGN_RESOURCE_FILENAME);
    }

    /**
     * Tests that the Aird resource has the migration mechanism installed after
     * having reloaded with an older version.
     */
    public void testReplaceAirdWithOlderVersion() {
        testReplaceResourceWithOlderVersion(AIRD_RESOURCE_FILENAME);
    }

    /**
     * Tests that the Odesign resource doesn't have the migration mechanism
     * installed after having reloaded with a migrated version.
     */
    public void testReplaceOdesignOlderVersionWithMigratedVersion() {
        testReplaceOlderVersionWithMigratedVersion(ODESIGN_RESOURCE_FILENAME);
    }

    /**
     * Tests that the Aird resource doesn't have the migration mechanism
     * installed after having reloaded with a migrated version.
     */
    public void testReplaceAirdOlderVersionWithMigratedVersion() {
        testReplaceOlderVersionWithMigratedVersion(AIRD_RESOURCE_FILENAME);
    }

    /**
     * Tests that the migrated Odesign resource doesn't have the migration
     * mechanism installed after having reloaded with a migrated version.
     */
    public void testReloadAlreadyMigratedOdesign() {
        testReloadAlreadyMigrated(ODESIGN_RESOURCE_FILENAME);
    }

    /**
     * Tests that the migrated Aird resource doesn't have the migration
     * mechanism installed after having reloaded with a migrated version.
     */
    public void testReloadAlreadyMigratedAird() {
        testReloadAlreadyMigrated(AIRD_RESOURCE_FILENAME);
    }

    /**
     * Tests that the migrated Odesign resource have the migration mechanism
     * updated after having reloaded with an other old version.
     */
    public void testReplaceOdesignOldVersionWithAnOtherOldVersion() {
        testReplaceOldVersionWithAnOtherOldVersion(ODESIGN_RESOURCE_FILENAME);
    }

    /**
     * Tests that the migrated Aird resource have the migration mechanism
     * updated after having reloaded with an other old version.
     */
    public void testReplaceAirdOldVersionWithAnOtherOldVersion() {
        testReplaceOldVersionWithAnOtherOldVersion(AIRD_RESOURCE_FILENAME);
    }

    private void testReloadAlreadyMigrated(String resourceName) {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + resourceName, "/" + TEMPORARY_PROJECT_NAME + "/" + resourceName);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(URI.createURI("/" + TEMPORARY_PROJECT_NAME + "/" + resourceName), true);
        try {
            // We make sure the file is migrated.
            resource.save(Collections.emptyMap());

            // we load the resource again in an other resource set and check
            // that the
            // mechanism is not installed.
            resourceSet = new ResourceSetImpl();
            resource = resourceSet.getResource(URI.createURI("/" + TEMPORARY_PROJECT_NAME + "/" + resourceName), true);
            migrationMechanismShouldBeInstalled(resource, false);

            // We reload the resource. The mechanism shouldn't be installed.
            resource.unload();
            resource.load(Collections.emptyMap());
            migrationMechanismShouldBeInstalled(resource, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testReplaceResourceWithOlderVersion(String resourceName) {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + resourceName, "/" + TEMPORARY_PROJECT_NAME + "/" + resourceName);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(URI.createURI("/" + TEMPORARY_PROJECT_NAME + "/" + resourceName), true);
        try {
            // We make sure the file is migrated.
            resource.save(Collections.emptyMap());

            // we load the resource again in an other resource set and check
            // that the
            // mechanism is not installed.
            resourceSet = new ResourceSetImpl();
            resource = resourceSet.getResource(URI.createURI("/" + TEMPORARY_PROJECT_NAME + "/" + resourceName), true);
            migrationMechanismShouldBeInstalled(resource, false);

            // We replace the file by the older version and reload the resource.
            // The mechanism should be installed.
            EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + resourceName, "/" + TEMPORARY_PROJECT_NAME + "/" + resourceName);
            resource.unload();
            resource.load(Collections.emptyMap());
            migrationMechanismShouldBeInstalled(resource, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testReplaceOlderVersionWithMigratedVersion(String resourceName) {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + resourceName, "/" + TEMPORARY_PROJECT_NAME + "/" + resourceName);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(URI.createURI("/" + TEMPORARY_PROJECT_NAME + "/" + resourceName), true);
        resourceSet = new ResourceSetImpl();
        Resource resourceOld = resourceSet.getResource(URI.createURI("/" + TEMPORARY_PROJECT_NAME + "/" + resourceName), true);
        try {
            // The file need to be migrated so the mechanism should be
            // installed.
            migrationMechanismShouldBeInstalled(resource, true);
            // We save after migration to replace the file by the migrated one.
            resource.save(Collections.emptyMap());

            // This resource is still based on the older version, we reload it
            // and we check that the migration mechanism has been removed.
            migrationMechanismShouldBeInstalled(resourceOld, true);
            resourceOld.unload();
            resourceOld.load(Collections.emptyMap());
            migrationMechanismShouldBeInstalled(resourceOld, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testReplaceOldVersionWithAnOtherOldVersion(String resourceName) {
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + resourceName, "/" + TEMPORARY_PROJECT_NAME + "/" + resourceName);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource resource = resourceSet.getResource(URI.createURI("/" + TEMPORARY_PROJECT_NAME + "/" + resourceName), true);
        try {

            migrationMechanismShouldBeInstalled(resource, true, "8.0.0");

            // We replace the file by the older version and reload the resource.
            // The mechanism should be installed with the new version
            EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, GENERAL_TEST_CASE_PATH + "older/" + resourceName, "/" + TEMPORARY_PROJECT_NAME + "/" + resourceName);
            resource.unload();
            resource.load(Collections.emptyMap());
            migrationMechanismShouldBeInstalled(resource, true, "7.0.0");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void migrationMechanismShouldBeInstalled(Resource resource, boolean shouldBeInstalled) {
        migrationMechanismShouldBeInstalled(resource, shouldBeInstalled, null);
    }

    private void migrationMechanismShouldBeInstalled(Resource resource, boolean shouldBeInstalled, String expectedVersion) {
        assertTrue("The resource should be an XML Resource", resource instanceof XMLResource);
        Object extendedMD = ((XMLResource) resource).getDefaultLoadOptions().get(XMLResource.OPTION_EXTENDED_META_DATA);
        Object resourceHandler = ((XMLResource) resource).getDefaultLoadOptions().get(XMLResource.OPTION_RESOURCE_HANDLER);
        if (expectedVersion != null) {
            checkVersion(extendedMD, expectedVersion);
            checkVersion(resourceHandler, expectedVersion);
        }
        assertTrue("the ExtendedMetaData should not be null", !shouldBeInstalled || (extendedMD != null));
        assertTrue("the ResourceHandler should not be null", !shouldBeInstalled || (resourceHandler != null));
        if (extendedMD != null) {
            assertTrue("The ExtendedMetaData should " + (shouldBeInstalled ? "" : "not") + " be installed",
                    !(shouldBeInstalled ^ (extendedMD instanceof VSMExtendedMetaData || extendedMD instanceof RepresentationsFileExtendedMetaData)));
        }
        if (resourceHandler != null) {
            assertTrue("The ResourceHandler should " + (shouldBeInstalled ? "" : "not") + " be installed",
                    !(shouldBeInstalled ^ (resourceHandler instanceof VSMResourceHandler || resourceHandler instanceof RepresentationsFileResourceHandler)));
        }
    }

    private void checkVersion(Object migrationMechanism, String expectedVersion) {
        Field field = migrationMechanism.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        try {
            Object version = field.get(migrationMechanism);
            assertEquals("The migration mechanism doesn't have the expected version", expectedVersion, version);
        } catch (IllegalArgumentException e) {
            fail("Cannot verify the migration version: " + e.getMessage());
        } catch (SecurityException e) {
            fail("Cannot verify the migration version: " + e.getMessage());
        } catch (IllegalAccessException e) {
            fail("Cannot verify the migration version: " + e.getMessage());
        }

    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

}
