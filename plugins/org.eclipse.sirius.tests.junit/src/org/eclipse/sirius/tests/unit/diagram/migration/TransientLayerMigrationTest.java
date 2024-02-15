/*******************************************************************************
 * Copyright (c) 2017, 2024 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.business.internal.migration.TransientLayerMigrationParticipant;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Test class to test that, in the case the layer is now considered as transient, the removal of the layer activation
 * and the removal of decorations on DDiagramElements.
 * 
 * @author lfasani
 */
public class TransientLayerMigrationTest extends SiriusTestCase {

    private static final String PATH = "/data/unit/migration/do_not_migrate/transientLayer/";

    private static final String SESSION_RESOURCE_NAME = "transientLayer.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "transientLayer.ecore";

    private static final String VSM_RESOURCE_NAME = "transientLayer.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_RESOURCE_NAME, VSM_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version dRepresentationContainerRemovalVersion = TransientLayerMigrationParticipant.MIGRATION_VERSION;

        // Check that the representation file migration is needed.
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(uri, true);
        assertTrue("The transient layer migration must be required on test data.", dRepresentationContainerRemovalVersion.compareTo(loadedVersion) > 0);

        // check the file contains decorations
        checkTransientLayerMigration(uri, 2, 3);
    }

    private void checkTransientLayerMigration(URI uri, int expectedNbDecoration, int expectedNbActivatedLayer) {
        int nbDeco[] = new int[1];
        int nbActivatedLayer[] = new int[1];
        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(uri);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("decorations") && attributes.getValue("xmi:type").equals(ViewpointPackage.eINSTANCE.getNsPrefix() + ":" + "Decoration")) {
                        nbDeco[0] = nbDeco[0] + 1;
                    } else if (qName.equals("activatedLayers")) {
                        nbActivatedLayer[0] = nbActivatedLayer[0] + 1;
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
        assertEquals("Bad decoration number", expectedNbDecoration, nbDeco[0]);
        assertEquals("Bad activated layer number", expectedNbActivatedLayer, nbActivatedLayer[0]);
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }

    /**
     * Check the migration is done and does not produce errors during VSM load.
     */
    public void testDRepresentationContainerRemovalMigrationDone() {
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
        // indicates that the migration is needed.
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
        checkTransientLayerMigration(analysis.eResource().getURI(), 1, 2);
        assertFalse(platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        // TODO Auto-generated method stub
        return null;
    }
}
