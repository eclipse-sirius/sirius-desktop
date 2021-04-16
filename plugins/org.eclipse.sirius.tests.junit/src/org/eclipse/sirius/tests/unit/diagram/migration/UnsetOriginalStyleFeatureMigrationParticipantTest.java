/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
import java.io.InputStream;
import java.util.Collections;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.diagram.business.internal.migration.UnsetOriginalStyleFeatureMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Test the migration participant {@link UnsetOriginalStyleFeatureMigrationParticipant}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class UnsetOriginalStyleFeatureMigrationParticipantTest extends SiriusTestCase {

    /**
     * An existing model is used because because there is no real migration to check.
     */
    private static final String PATH = "data/unit/migration/do_not_migrate/originalStyle/";

    private static final String SESSION_RESOURCE_NAME = "originalStyleTest.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "originalStyleTest.ecore";

    private static final String ORIGINALSTYLE_FOUND = "All the features 'originalStyle' should be unset";

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = UnsetOriginalStyleFeatureMigrationParticipant.MIGRATION_VERSION;

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that the migration is done.
     */
    public void testMigrationVersionSerialisation() {
        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), set);
        } catch (IOException e) {
            failCheckData();
        }
        // check the data of the test
        checkAirdFile(analysis.eResource().getURI(), true);

        // Check that the migration was done.
        assertNotNull("Check the representation file test data.", analysis);

        // The version will change on save, so migration service will still
        // indicate that the migration is needed.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework will return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            failCheckData();
        }
        // save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        // check that the migration succeeded
        checkAirdFile(analysis.eResource().getURI(), false);
        assertTrue("The resource should not contain any error", analysis.eResource().getErrors().isEmpty());
    }

    private void checkAirdFile(URI uri, boolean shouldContainsOneOriginalStyleFeatureSet) {
        boolean containsOneOriginalStyleFeatureSet = false;
        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(uri);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if ((qName.equals("ownedDiagramElements") || qName.equals("ownedElements")))
                        if (attributes.getValue("originalStyle") != null) {
                            throw new SAXException(ORIGINALSTYLE_FOUND);
                        }
                }
            });
        } catch (

        SAXException e) {
            if (e.getMessage().equals(ORIGINALSTYLE_FOUND)) {
                containsOneOriginalStyleFeatureSet = true;
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

        if (shouldContainsOneOriginalStyleFeatureSet) {
            if (!containsOneOriginalStyleFeatureSet) {
                failCheckData();
            }
        } else {
            assertFalse(ORIGINALSTYLE_FOUND, containsOneOriginalStyleFeatureSet);
        }
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }
}
