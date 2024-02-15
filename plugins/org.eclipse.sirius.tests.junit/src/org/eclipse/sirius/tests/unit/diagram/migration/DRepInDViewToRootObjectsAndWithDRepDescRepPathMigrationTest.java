/*******************************************************************************
 * Copyright (c) 2016, 2024 THALES GLOBAL SERVICES.
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

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Test class to test the DView.ownedRepresentationDescriptors creation, the DView.ownedRepresentations removal and that
 * the DRepresentation is serialized as root objects in the aird file. This test also checks the change of
 * DRepresentationDescriptor.representation to derived and DRepresentationDescriptor.repPath is correctly set.
 * 
 * @author lfasani
 */
public class DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationTest extends SiriusTestCase {

    private static final String DREPRESENTATION_FOUND = "DRepresentation found.";

    private static final String PATH = "/data/unit/migration/do_not_migrate/dRepresentationMoveToRootObjects/";

    private static final String SESSION_RESOURCE_NAME = "dRepresentationMoveToRootObjectsMigration.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "dRepresentationMoveToRootObjectsMigration.ecore";

    private enum WhereToFindDRep {
        IN_DVIEW, AS_ROOT_OBJECTS
    };

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version dRepresentationMoveVersion = DRepInDViewToRootObjectsAndWithDRepDescRepPathMigrationParticipant.MIGRATION_VERSION_REP_IN_DVIEW_TO_ROOT_OBJECT;

        // Check that the representation file migration is needed.
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(uri, true);
        assertTrue("The DRepresentation move migration must be required on test data.", dRepresentationMoveVersion.compareTo(loadedVersion) > 0);

        // check the file contains DRepresentation
        checkFileContainsDRepresentation(uri, true, WhereToFindDRep.IN_DVIEW, "Test data should contain a DRepresentation in DView.ownedRepresentations.");
        checkFileContainsDRepresentation(uri, false, WhereToFindDRep.AS_ROOT_OBJECTS, "Test data should not contain a DRepresentation as root object.");
    }

    private void checkFileContainsDRepresentation(URI uri, boolean expectedDRepresentation, final WhereToFindDRep whereToFindDRep, String errorMessage) {
        boolean containsDRepresentation = false;
        ExtensibleURIConverterImpl uriConverterImpl = new ExtensibleURIConverterImpl();
        InputStream inputStream = null;
        try {
            inputStream = uriConverterImpl.createInputStream(uri);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputStream, new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (WhereToFindDRep.IN_DVIEW.equals(whereToFindDRep)) {
                        if (qName.equals("ownedRepresentations") && attributes.getValue("xmi:type").equals(TablePackage.eINSTANCE.getNsPrefix() + ":" + "DTable")) {
                            throw new SAXException(DREPRESENTATION_FOUND);
                        }
                    } else if (WhereToFindDRep.AS_ROOT_OBJECTS.equals(whereToFindDRep)) {
                        if (qName.equals(TablePackage.eINSTANCE.getNsPrefix() + ":" + "DTable")) {
                            throw new SAXException(DREPRESENTATION_FOUND);
                        }
                    }
                }
            });
        } catch (SAXException e) {
            if (e.getMessage().equals(DREPRESENTATION_FOUND)) {
                containsDRepresentation = true;
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
        assertEquals(errorMessage, expectedDRepresentation, containsDRepresentation);
    }

    private void failCheckData() {
        fail("Check the test data, we should not fail here.");
    }

    /**
     * Check the migration is done and does not produce errors during load.
     */
    public void testDRepresentationMoveToRootObjectsMigrationDone() {
        ResourceSet set = new ResourceSetImpl();
        DAnalysis analysis = null;
        try {
            analysis = (DAnalysis) ModelUtils.load(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), set);
        } catch (IOException e) {
            failCheckData();
        }

        // Check that the migration was done.
        assertNotNull("Check the representation file test data.", analysis);

        // Check there are no more any types
        // assertTrue("Check the migration logic.", ((XMLResource)
        // analysis.eResource()).getEObjectToExtensionMap().isEmpty());

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
        checkFileContainsDRepresentation(analysis.eResource().getURI(), false, WhereToFindDRep.IN_DVIEW,
                "After migration, the resource should not contain a DRepresentation in DView.ownedRepresentations.");
        checkFileContainsDRepresentation(analysis.eResource().getURI(), true, WhereToFindDRep.AS_ROOT_OBJECTS, "After migration, the resource should contain a DRepresentation as root object.");

        checkDRepresentationDescriptor(analysis);
        assertEquals("Bad DRepresentationDescriptor number", analysis.getOwnedViews().stream().flatMap(view -> view.getOwnedRepresentationDescriptors().stream()).count(), 3);

        assertFalse(platformProblemsListener.getErrorLoggersMessage(), platformProblemsListener.doesAnErrorOccurs());
    }

    private void checkDRepresentationDescriptor(DAnalysis dAnalysis) {
        List<DRepresentation> repList = dAnalysis.eResource().getContents().stream().filter(DRepresentation.class::isInstance).map(DRepresentation.class::cast).collect(Collectors.toList());
        // @formatter:off
        List<DRepresentationDescriptor> representationDescriptors = dAnalysis.getOwnedViews().stream().flatMap(v -> v.getOwnedRepresentationDescriptors().stream()).collect(toList());
        representationDescriptors.forEach(repDesc -> {
            // The order of the DRep may not be the same as the one of the
            // parsed DRepresentationDescriptor because the unknown feature "DView.ownedRepresentations" are
            // get from resource.getEObjectToExtensionMap()
            // So we only test that DRepDesc.representation
            DRepresentation representation = repDesc.getRepresentation();
            assertNotNull("DRepresentationDescriptor.representation is null", representation);
            assertTrue("The DRepresentation " + representation + " is not found as root object.", repList.contains(representation));
            assertEquals("The representation documentation has not been migrated.","todo",repDesc.getDocumentation());
            assertTrue("The representation name has not been migrated.",!StringUtil.isEmpty(repDesc.getName()));
            assertEquals(representation.getName(), repDesc.getName());
            assertEquals(representation.getDocumentation(), repDesc.getDocumentation());
        });
        // @formatter:on        
        DRepresentationDescriptor repDesc0 = representationDescriptors.get(0);
        assertEquals("Documentation for name  package", repDesc0.getName());
        assertEquals("todo", repDesc0.getDocumentation());

        DRepresentationDescriptor repDesc1 = representationDescriptors.get(1);
        assertEquals("P0 package entities", repDesc1.getName());
        assertEquals("todo", repDesc1.getDocumentation());

        DRepresentationDescriptor repDesc2 = representationDescriptors.get(2);
        assertEquals("Classes in P0 package", repDesc2.getName());
        assertEquals("todo", repDesc2.getDocumentation());
    }

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }
}
