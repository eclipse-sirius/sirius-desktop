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
public class STD001 extends AbstractSTDTestCase {

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_NAME_TABLE_001 = "Classes";

    private static final String REPRESENTATION_NAME_DIAGRAM_001 = "Entities";

    private static final String VIEWPOINT_NAME_001 = "Design";

    private static final String MODEL_001 = "STD-TEST-001.ecore";

    private static final String SESSION_FILE_001 = "STD-TEST-001.aird";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "RootSTDTestCase package entities";

    private static final String REPRESENTATION_INSTANCE_NAME_TABLE = "new Classes";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { MODEL_001, SESSION_FILE_001 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "001/";
    }

    /**
     * @throws Exception
     *             if test fails.
     */
    public void testSTD001() throws Exception {

        // This is the name of the 2 diagram to open
        // new Classes
        // RootSTDTestCase package entities

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_001);

        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_001).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_001)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM, UIDiagramRepresentation.class).open();

        bot.sleep(3000);

        final UIDiagramRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_001).selectRepresentation(REPRESENTATION_NAME_TABLE_001)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_TABLE, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-001]:Error the diagram couldn't be opened!", diagram);
        assertNotNull("[Test-001]:Error the table couldn't be opened!", table);

        localSession.close(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
