/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo
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

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.migration.ComputedStyleDescriptionCachePackingFileMigrationParticipant;
import org.eclipse.sirius.diagram.business.internal.query.model.DDiagramInternalQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * Test if the migration of Bug 457481, i.e. ComputedStyleDescriptionCachePackingFileMigrationParticipant, is correctly
 * launched. This migration optimize the storage of StyleDescription computed through StyleCustomizations.
 * 
 * @author cbrun
 */
public class ComputedStyleDescriptionCachePackingFileMigrationParticipantTests extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/migration/do_not_migrate/custostorage/";

    private static final String MODELER_RESOURCE_NAME = "457481_custostorage.odesign";

    private static final String SEMANTIC_RESOURCE_NAME = "457481_custostorage.ecore";

    private static final String SESSION_RESOURCE_NAME = "457481_custostorage.aird";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + SESSION_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME, TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformPluginURI(SESSION_RESOURCE_NAME, true), true);

        // Check that the migration is needed.
        Version migration = ComputedStyleDescriptionCachePackingFileMigrationParticipant.MIGRATION_VERSION;
        assertTrue("The migration must be required on test data.", loadedVersion == null || migration.compareTo(loadedVersion) > 0);
    }

    /**
     * The test case contains Node, Container, Edges and List mappings and style customization reacting when a class is
     * abstract. This test make sure we don't have too many instances of computed style description kepts in the
     * diagram. inconsistency.
     */
    public void testMigration() {
        assertEquals(1, session.getOwnedViews().size());
        DView dView = session.getOwnedViews().iterator().next();
        List<DRepresentation> loadedRepresentations = new DViewQuery(dView).getLoadedRepresentations();
        assertEquals(1, loadedRepresentations.size());
        DRepresentation dRepresentation = loadedRepresentations.get(0);
        assertTrue(dRepresentation instanceof DDiagram);
        DDiagram dDiagram = (DDiagram) dRepresentation;
        ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = new DDiagramInternalQuery(dDiagram).getComputedStyleDescriptionRegistry(false);
        assertNotNull(computedStyleDescriptionRegistry);
        /* Without migration we should have had 24 computed StyleDescription. */
        assertEquals("Computed StyleDescriptiosshould have been shared among styles within the DDiagram", 3, computedStyleDescriptionRegistry.getComputedStyleDescriptions().size());
        /*
         * Without migration we should have had a map of 4 entries in the cache.
         */
        assertEquals("As the maps of ComputedStyleDescriptionRegistry are no more used, it should remains only the 3 computed StyleDescriptions", 3,
                computedStyleDescriptionRegistry.eContents().size());
        XMLResource airdResource = (XMLResource) session.getSessionResource();
        assertEquals("As the migration has done its job, the map of unknown data should be empty", 0, airdResource.getEObjectToExtensionMap().size());
    }
}
