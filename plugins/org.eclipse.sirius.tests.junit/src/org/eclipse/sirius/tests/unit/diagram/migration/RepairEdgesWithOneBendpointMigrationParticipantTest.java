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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.internal.migration.RepairEdgesWithOneBendpointMigrationParticipant;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * Test for {@link RepairEdgesWithOneBendpointMigrationParticipant}.
 * 
 * @author gplouhinec
 *
 */
public class RepairEdgesWithOneBendpointMigrationParticipantTest extends SiriusTestCase {

    private static final String PATH = "data/unit/migration/do_not_migrate/edgeBendpointsRepair/";

    private static final String SESSION_RESOURCE_NAME = "NoteAttachmentWithOneBendpoint.aird";

    private static final String SEMANTIC_RESOURCE_NAME = "NoteAttachmentWithOneBendpoint.ecore";

    private static final String VSM_RESOURCE_NAME = "TestRectilinear.odesign";

    private Resource sessionResource;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SESSION_RESOURCE_NAME, SEMANTIC_RESOURCE_NAME, VSM_RESOURCE_NAME);
        URI sessionResourceURI = URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        sessionResource = resourceSet.getResource(sessionResourceURI, true);

    }

    /**
     * Test that the data were not migrated on the repository. It allows to check the effect of the migration in the
     * other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new RepairEdgesWithOneBendpointMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);
    }

    /**
     * Test that bend-points of edge on diagram are repaired.
     */
    public void testBendpointsHaveBeenRepaired() {
        for (DView view : ((DAnalysis) sessionResource.getContents().iterator().next()).getOwnedViews()) {
            for (DDiagram dDiagram : Iterables.filter(new DViewQuery(view).getLoadedRepresentations(), DDiagram.class)) {
                checkBendpointsNumber(dDiagram);
            }
        }
    }

    /**
     * Check that edge have correct number of bend-point which implies that they have been repaired.
     * 
     * @param diagram
     *            the migrated diagram to check
     */
    private void checkBendpointsNumber(DDiagram diagram) {
        Iterator<EObject> iterator = diagram.eAllContents();
        while (iterator.hasNext()) {
            EObject element = iterator.next();
            if (element instanceof Edge) {
                Edge edge = (Edge) element;
                RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();
                List<RelativeBendpoint> pointList = bendpoints.getPoints();
                assertTrue("Number of bendpoints on rectilinear edge should be greater than 1.", pointList.size() > 1);
            }
        }
    }

}
