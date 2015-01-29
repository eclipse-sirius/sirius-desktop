/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Test that the viewpoint URI was migrated during the migration.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class MigrationSiriusURITest extends SiriusDiagramTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/migration/do_not_migrate/VP-2825/";

    private static final String A_VSM_NAME = "description/A.odesign";

    private static final String B_VSM_NAME = "description/B.odesign";

    private static final String REPRESENTATIONS_FILE = "model/My.aird";

    private static final String SEMANTIC_MODEL = "model/My.ecore";

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tests.support.api.SiriusTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Copying Session
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR + REPRESENTATIONS_FILE, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE);

        // Copying semantic model
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR + SEMANTIC_MODEL, TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL);

        // Copying A VSM into workspace
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR + A_VSM_NAME, TEMPORARY_PROJECT_NAME + "/" + A_VSM_NAME);

        // Copying B VSM into workspace
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, DATA_UNIT_DIR + B_VSM_NAME, TEMPORARY_PROJECT_NAME + "/" + B_VSM_NAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL, TEMPORARY_PROJECT_NAME + "/" + A_VSM_NAME, TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_FILE);
    }

    /**
     * Test migrationSiriusURI.
     * <ol>
     * <li>Step 1: Select viewpoint B and check that the selection is KO.</li>
     * <li>Step 2: Launch migration for modeler with viewpoint B</li>
     * <li>Step 3: Check that the selection is OK.</li>
     * </ol>
     */
    public void testMigrationSiriusURI() {
        Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints("ecore");
        Viewpoint viewpointB = null;
        for (Viewpoint viewpoint : viewpoints) {
            if ("A".equals(viewpoint.getName())) {
                // Check that the viewpoint A is selected
                assertEquals("The viewpoint 'A' should be selected", viewpoint.getName(), ((Viewpoint) session.getSelectedViewpoints(false).iterator().next()).getName());
            } else if ("B".equals(viewpoint.getName())) {
                viewpointB = viewpoint;
            }
        }
        assertNotNull(viewpointB);
        // Try to select Sirius B
        selectSirius(viewpointB, session);
        assertEquals("The viewpoints selection should have missing dependencies", false, ViewpointSelection.getMissingDependencies(new LinkedHashSet<Viewpoint>(session.getSelectedViewpoints(false)))
                .isEmpty());
        // Launch migration for modeler with viewpoint B
        // ModelerResourceFileMigration modelerResourceFileMigration = new
        // ModelerResourceFileMigrationImpl(bVSMURI);
        // modelerResourceFileMigration.run(new NullProgressMonitor());
        // modelerResourceFileMigration.dispose();
        // Check that Sirius B is selected
        boolean viewpointBSelected = false;
        for (Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
            if ("B".equals(viewpoint.getName())) {
                viewpointBSelected = true;
                break;
            }
        }
        assertEquals("The viewpoint 'B' should be selected", true, viewpointBSelected);
        assertEquals("The viewpoints selection should not have missing dependencies", true,
                ViewpointSelection.getMissingDependencies(new LinkedHashSet<Viewpoint>(session.getSelectedViewpoints(false))).isEmpty());
    }

    /**
     * This method select a viewpoint. No check is made in this method. Thus,
     * the view is selected even if it is not valid. This explains why in the
     * test, the second time we did not need to reselect the view.
     * 
     * @param viewpoint
     *            The viewpoint to select
     * @param session
     *            The session in which activate the viewpoint
     */
    private void selectSirius(final Viewpoint viewpoint, final Session session) {
        Command changeSiriussSelectionCmd = new ChangeViewpointSelectionCommand(session, selectionCallback, Collections.singleton(viewpoint), Collections.<Viewpoint> emptySet(),
                new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(changeSiriussSelectionCmd);
    }

}
