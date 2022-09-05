/*******************************************************************************
 * Copyright (c) 2019, 2022 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * Tests that the API to update change id and available in {@link AbstractRepresentationsFileMigrationParticipant} do
 * work.
 * 
 * There is no migration provided with Sirius 6.3.0 to init the change id. The
 * {@link ChangeIdUpdaterMigrationParticipant} has been added in tests plugin to simulate the future use of the
 * {@link AbstractRepresentationsFileMigrationParticipant#updateChangeId(DAnalysis, DRepresentation)} method.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ChangeIdAPIForMigrationTest extends SiriusTestCase {

    private static final String PATH = "data/unit/migration/do_not_migrate/changeId/";

    private static final String SESSION_RESOURCE_NAME = "ListElement.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "ListElement.ecore";

    private Resource sessionResource;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME);
        URI sessionResourceURI = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + "/" + PATH + SESSION_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        sessionResource = resourceSet.getResource(sessionResourceURI, true);

    }

    /**
     * Test that the data were not migrated on the repo. It allows to check the effect of the migration in the other
     * test.
     */
    public void testMigrationIsNeededOnData() {
        Version participantVersion = ChangeIdUpdaterMigrationParticipant.MIGRATION_VERSION;

        // Check that the representation file migration is needed.
        URI uri = URI.createPlatformPluginURI(SiriusTestsPlugin.PLUGIN_ID + PATH + SESSION_RESOURCE_NAME, true);
        Version loadedVersion = checkRepresentationFileMigrationStatus(uri, true);
        assertTrue("The AddRepresentationUidMigrationParticipant migration should be required on test data.", participantVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * This test verifies that {@link AbstractRepresentationsFileMigrationParticipant#updateChangeId()} allows to update
     * the change id correctly during a migration.
     */
    public void testUpdateChangeIdWhenDoingAMigration() {
        DAnalysis analysis = (DAnalysis) sessionResource.getContents().get(0);
        EList<DView> ownedViews = analysis.getOwnedViews();
        for (DView dView : ownedViews) {
            List<DRepresentationDescriptor> ownedRepresentationDescriptors = dView.getOwnedRepresentationDescriptors();
            for (DRepresentationDescriptor representationDescriptor : ownedRepresentationDescriptors) {
                assertTrue("Change id has not been updated.", representationDescriptor.getChangeId() != null && !representationDescriptor.getChangeId().isEmpty());
                try {
                    Long.parseLong(representationDescriptor.getChangeId());
                } catch (NumberFormatException e) {
                    fail(String.format("The changeId {0} of the DRepresentationDescriptor {1} is not a time stamp", representationDescriptor.getChangeId(), representationDescriptor.getName()));
                }
            }
        }
    }

}
