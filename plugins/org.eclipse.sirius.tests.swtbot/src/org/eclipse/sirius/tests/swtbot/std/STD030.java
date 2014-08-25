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

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;

/**
 * 
 * @author amartin
 */
public class STD030 extends AbstractSTDTestCase {

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE_030 = "STD-TEST-030.aird";

    private static final String MODEL_030 = "STD-TEST-030.ecore";

    private static final String VIEWPOINT_NAME_030 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_030 = "Classes";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_030 = "STD-TEST-030-TABLE";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { SESSION_FILE_030, MODEL_030 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "030/";
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD017() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_030);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_030).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_030)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_030, UIDiagramRepresentation.class).open();

        // TODOAMA finish the tests
        assertNotNull("[Test-030]:Error the table couldn't be opened!", diagram);

        // diagram.close();
        localSession.close(false);

    }

}
