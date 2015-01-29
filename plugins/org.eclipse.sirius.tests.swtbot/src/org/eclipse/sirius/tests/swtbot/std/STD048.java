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
package org.eclipse.sirius.tests.swtbot.std;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.test.AbstractScenarioTestCase;

/**
 * 
 * @author amartin
 */
public class STD048 extends AbstractScenarioTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/std/";

    private static final String FILE_DIR = "/";

    private static final String VIEWPOINT_NAME_048 = "Design";

    private static final String REPRESENTATION_NAME_TABLE_048 = "Classes";

    private static final String SESSION_FILE_048 = "STD-TEST-048.aird";

    private static final String MODEL_048 = "STD-TEST-048.ecore";

    private static final String REPRESENTATION_INSTANCE_NAME_TABLE_048 = "new Classes";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_048, SESSION_FILE_048);
    }

    /**
     * Test-048.
     * 
     * @throws Exception
     *             if the test fails
     */
    public void testSTD048() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_048);

        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_048).selectRepresentation(REPRESENTATION_NAME_TABLE_048)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_TABLE_048, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-048]:Error the table couldn't be opened!", table);

        localSession.close(false);
        // ENHANCE THIS we should manipule the table...
    }

}
