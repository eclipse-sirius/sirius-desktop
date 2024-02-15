/*******************************************************************************
 * Copyright (c) 2017, 2024 Obeo.
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
import org.eclipse.sirius.business.internal.migration.DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Test class to test the AddRepresentationUidMigrationParticipant participant.
 * 
 * @author lfasani
 */
public class AddRepresentationUidMigrationTest extends SiriusTestCase {

    private static final String PATH = "/data/unit/migration/do_not_migrate/addRepresentationUid/";

    private static final String SESSION_RESOURCE_NAME = "addRepresentationUidMigration.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "addRepresentationUidMigration.ecore";

    @Override
    protected void setUp() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version participantVersion = DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant.MIGRATION_VERSION_REP_PATH_UID;

        // Check that the representation file migration is needed.
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(uri, true);
        assertTrue("The AddRepresentationUidMigrationParticipant migration should be required on test data.", participantVersion.compareTo(loadedVersion) > 0);

        checkFileDoNotContainDRepresentationUid(uri);
    }

    private void checkFileDoNotContainDRepresentationUid(URI uri) {
        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(uri);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("diagram:DSemanticDiagram") && !StringUtil.isEmpty(attributes.getValue("uid"))) {
                        throw new SAXException("There should not be any uid attribute for diagram:DSemanticDiagram tag");
                    }
                }
            });
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
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }

    /**
     * Check the migration is done and does not produce errors during load.
     */
    public void testAddRepresentationUidMigrationParticipantMigrationDone() {
        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), set);
        } catch (IOException e) {
            failCheckData();
        }

        // Check that the migration was done.
        assertNotNull("Check the representation file test data.", analysis);

        // The version will change on save, so migration service will still
        // indicate that the migration is needed.
        String version = analysis.getVersion();
        assertTrue("Before save, the migration framework should return true even if the migration has been done during load.",
                RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));

        try {
            analysis.eResource().save(Collections.emptyMap());
        } catch (IOException e) {
            failCheckData();
        }
        // save should update the version.
        version = analysis.getVersion();
        assertFalse("The version tag should now be set telling that the migration was done.", RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version)));
        checkMigration(analysis);

        assertFalse(platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }

    private void checkMigration(DAnalysis dAnalysis) {
        dAnalysis.getOwnedViews().stream().flatMap(v -> v.getOwnedRepresentationDescriptors().stream()).forEachOrdered(repDesc -> {
            DRepresentation representation = repDesc.getRepresentation();
            assertNotNull("DRepresentationDescriptor.representation is null. DRepresentationDescriptor.repPath uri may not contain the DRep.uid fragment ", representation);
            assertTrue("The DRepresentation.uid is empty.", !StringUtil.isEmpty(representation.getUid()));
        });
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
