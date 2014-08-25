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
public class STD019 extends AbstractSTDTestCase {

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE_019 = "STD-TEST-019.aird";

    private static final String MODEL_019 = "STD-TEST-019.ecore";

    private static final String VIEWPOINT_NAME_019 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_019 = "Classes";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_019 = "STD-TEST-019-TABLE";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { SESSION_FILE_019, MODEL_019 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "019/";
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD017() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_019);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_019).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_019)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_019, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-019]:Error the table couldn't be opened!", diagram);

        // diagram.close();
        localSession.close(false);

    }

}
