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
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.sirius.diagram.ui.business.internal.migration.SetChangeIdMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Tests the migration participant {@link SetChangeIdMigrationParticipant}.
 * 
 * @author Glenn Plouhinec
 *
 */
public class SetChangeIdMigrationParticipantTest extends SiriusDiagramTestCase {

    private static final String ROOT_PACKAGE_ENTITIES_CHANGE_ID = "687a6087-f885-4f41-9b24-f2b403939cec";

    private static final String SESSION_RESOURCE_NAME = "setChangeId.aird";

    private static final String PATH = "/data/unit/migration/do_not_migrate/setChangeId/";

    private static final String SESSION_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME;

    private static final String SEMANTIC_RESOURCE_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + PATH + "setChangeId.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(Collections.singletonList(SEMANTIC_RESOURCE_PATH), Collections.<String> emptyList(), SESSION_RESOURCE_PATH);
    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new SetChangeIdMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SESSION_RESOURCE_PATH, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);

        checkFileMissingChangeId(uri, true, "Test data should contain changeId");
        checkFileMissingChangeId(uri, false, "Test data should contain null changeId");
    }

    /**
     * Checks that there are no more null changeId after the migration.
     */
    public void testChangeIds() {
        for (DView view : session.getOwnedViews()) {
            for (DRepresentationDescriptor descriptor : view.getOwnedRepresentationDescriptors()) {
                if (descriptor.getName().equals("root package entities")) {
                    assertEquals("The changeId of the representation \"" + descriptor.getName() + "\" should not be overridden.", ROOT_PACKAGE_ENTITIES_CHANGE_ID, descriptor.getChangeId());
                }
                assertNotNull("The changeId of the representation \"" + descriptor.getName() + "\" should not be null.", descriptor.getChangeId());
            }
        }
    }

    private void checkFileMissingChangeId(URI uri, boolean expectedChangeId, String errorMessage) {
        boolean hasChangeId = !expectedChangeId;
        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(uri);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("ownedRepresentationDescriptors")) {
                        String changeIdAttribute = attributes.getValue("changeId");
                        if (expectedChangeId) {
                            if (changeIdAttribute != null && changeIdAttribute.equals(ROOT_PACKAGE_ENTITIES_CHANGE_ID)) {
                                throw new SAXException("changeId exists");
                            }
                        } else {
                            if (changeIdAttribute == null) {
                                throw new SAXException("changeId null");
                            }
                        }
                    }
                }
            });
        } catch (SAXException e) {
            if (e.getMessage().contains("exists")) {
                hasChangeId = true;
            } else {
                hasChangeId = false;
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
        assertEquals(errorMessage, expectedChangeId, hasChangeId);
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }
}
