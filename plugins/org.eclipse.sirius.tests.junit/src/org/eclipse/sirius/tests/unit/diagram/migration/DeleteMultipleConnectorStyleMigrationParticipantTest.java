/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.notation.ConnectorStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.internal.migration.DeleteMultipleConnectorStyleMigrationParticipant;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

import com.google.common.collect.Iterables;

/**
 * Test for {@link DeleteMultipleConnectorStyleMigrationParticipant}.
 * 
 * @author jmallet
 */
public class DeleteMultipleConnectorStyleMigrationParticipantTest extends SiriusTestCase {

    private static final String PATH = "data/unit/migration/do_not_migrate/connectorStyleDelete/";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private static final String SEMANTIC_MODEL_FILENAME = "My.ecore";

    private Resource sessionResource;

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setInfoCatchActive(true);
        genericSetUp();
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, PATH, SEMANTIC_MODEL_FILENAME, SESSION_RESOURCE_NAME);
        URI sessionResourceURI = URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        sessionResource = resourceSet.getResource(sessionResourceURI, true);

    }

    /**
     * Test that the data were not migrated on the repository. It allows to
     * check the effect of the migration in the other test.
     */
    public void testMigrationIsNeededOnData() {
        Version migrationVersion = new DeleteMultipleConnectorStyleMigrationParticipant().getMigrationVersion();

        // Check that the migration of the session resource is needed.
        Version loadedVersion = checkRepresentationFileMigrationStatus(URI.createPlatformResourceURI(SiriusTestCase.TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME, true), true);
        assertTrue("The migration must be required on test data.", migrationVersion.compareTo(loadedVersion) > 0);

        String infoLoggersMessage = getInfoLoggersMessage();
        assertTrue("The log does not contain information about this migration.", infoLoggersMessage.contains(Messages.DeleteMultipleConnectorMigrationParticipant_title));
        assertTrue("The log does not contain details about this migration.", infoLoggersMessage.contains("connectors style of some edges have been deleted because only one is required"));
    }

    /**
     * Test that multiple connector Style of edge on diagram are been removed.
     */
    public void testConnectorStylesHaveBeenRemoved() {
        for (DView view : ((DAnalysis) sessionResource.getContents().iterator().next()).getOwnedViews()) {
            for (DDiagram dDiagram : Iterables.filter(new DViewQuery(view).getLoadedRepresentations(), DDiagram.class)) {
                checkConnectorStyleNumber(dDiagram);
            }
        }
    }

    /**
     * Check that edge have correct number of Connector Style which implies that
     * multiple connector Style have been removed.
     * 
     * @param diagram
     *            the migrated diagram to check
     */
    private void checkConnectorStyleNumber(DDiagram diagram) {
        Iterator<EObject> iterator = diagram.eAllContents();
        while (iterator.hasNext()) {
            EObject element = iterator.next();
            if (element instanceof Edge) {
                Edge edge = (Edge) element;
                long nbConnectorStyles = edge.getStyles().stream().filter(ConnectorStyle.class::isInstance).count();
                assertTrue("Number of connector Style on edge should be equal to 1.", nbConnectorStyles == 1);
            }
        }
    }
}
