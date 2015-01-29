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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;

/**
 * Test class to test recreation of lost diagram elements during repair/migrate
 * action of aird file.
 * 
 * @author dlecan
 */
public class RepairMigrateLostDiagramElementsTC1Test extends AbstractRepairMigrateTest {

    private static final String PATH = "/data/unit/repair/lostelements/tc1";

    private static final String SESSION_RESOURCE_FILENAME = "tc1945.aird";

    private static final String MODELER_RESOURCE_FILENAME = "tc1945.odesign";

    private static final String SEMANTIC_RESOURCE_FILENAME = "tc1945.ecore";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String SEMANTIC_MODEL_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME;

    private static final String MODELER_PATH = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME;

    private static final String SESSION_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_FILENAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_FILENAME);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, SESSION_PATH);

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCreationOfElementsWithCreationOptionToFalseButContainedInElementWithCreationOptionToTrue1() throws Exception {

        DDiagram dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME).toArray()[0]);
        assertEquals("Wrong number of elements in diagram before migration", 9, dDiagram.getDiagramElements().size());

        session.close(new NullProgressMonitor());

        // repair of aird (migration is now done automatically)
        runRepairProcess(SESSION_RESOURCE_FILENAME);

        // Check again data
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI(SESSION_PATH, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        dDiagram = ((DDiagram) getRepresentations(REPRESENTATION_NAME).toArray()[0]);
        assertEquals("Wrong number of elements in diagram after migration", 9, dDiagram.getDiagramElements().size());
    }

    @Override
    protected void tearDown() throws Exception {
        if (session != null && session.isOpen()) {
            session.close(new NullProgressMonitor());
        }
        super.tearDown();
    }

}
