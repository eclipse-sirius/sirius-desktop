/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.migration.description.VSMMigrationService;
import org.eclipse.sirius.diagram.ui.business.internal.migration.description.DecorationImageDescriptionVSMMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.Group;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Test class to test the feature name change from
 * DecorationDescription.decoratorPath to DecorationDescription.imageExpression
 * 
 * @author lfasani
 */
public class DecorationDescriptionImageExpressionMigrationTest extends SiriusTestCase {

    private static final String PATH = "/data/unit/migration/do_not_migrate/decorationDescription/";

    private static final String VSM_NAME = "DecorationDescription.odesign";

    private static final String DATA_TO_MIGRATE_FOUND = "DecorationDescription.decoratorPath feature to migrate found";

    /**
     * Test that the data were not migrated on the repo. It allows to check the
     * effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = DecorationImageDescriptionVSMMigrationParticipant.MIGRATION_VERSION;

        // Check that the representation file migration is needed.
        URI vsmURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + VSM_NAME, true);
        Version vsmLoadedVersion = checkVsmFileMigrationStatus(vsmURI, true);
        assertTrue("The decoration description migration must be required on test data.", migrationVersion.compareTo(vsmLoadedVersion) > 0);

        // check the file contains data to migrate
        checkFileContainsDecorationDescriptionToMigrate(vsmURI);
    }

    /**
     * Check the behavior of the optional layer migration on a VSM loaded from
     * resources in workspace.
     */
    public void testVSMMigrationInWorkspace() {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, VSM_NAME);

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
        List<DecorationDescription> decorationDescriptions = Lists.newArrayList(Iterators.filter(modeler.eAllContents(), DecorationDescription.class));
        assertEquals("The VSM model should contains 2 additional layers.", 2, decorationDescriptions.size());

        for (DecorationDescription decorationDescription : decorationDescriptions) {
            String imageExpression = decorationDescription.getImageExpression();
            assertEquals("", "/path/to/image.png", imageExpression);
        }
    }

    private void checkFileContainsDecorationDescriptionToMigrate(URI uri) {
        boolean dataToMigrateFound = false;
        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(uri);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("decorationDescriptions") && attributes.getValue("decoratorPath") != null) {
                        throw new SAXException(DATA_TO_MIGRATE_FOUND);
                    }
                }
            });
        } catch (SAXException e) {
            if (e.getMessage().equals(DATA_TO_MIGRATE_FOUND)) {
                dataToMigrateFound = true;
            }
        } catch (Exception e) {
            failCheckData();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException e) {
                    failCheckData();
                }
            }
        }
        assertTrue("Data to migrate not found", dataToMigrateFound);
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
