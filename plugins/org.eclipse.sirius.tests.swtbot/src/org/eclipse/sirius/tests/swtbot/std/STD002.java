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
public class STD002 extends AbstractScenarioTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/std/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_NAME_DIAGRAM_002 = "STD002-Diag";

    private static final String VIEWPOINT_NAME_002 = "STD-VP-002";

    private static final String MODEL_002 = "STD-TEST-002.ecore";

    private static final String SESSION_FILE_002 = "STD-TEST-002.aird";

    private static final String VSM_FILE_002 = "STD-TEST-002.odesign";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_002 = "new STD002-Diag";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR + "002/", MODEL_002, SESSION_FILE_002, VSM_FILE_002);
    }

    /**
     * Test-002
     */
    public void testSTD002() {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_002);

        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_002).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_002)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_002, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-002]:Error the diagram couldn't be opened!", diagram);

        diagram.getEditor().getEditPart("AClass");

        localSession.closeNoDirty();

    }

}
