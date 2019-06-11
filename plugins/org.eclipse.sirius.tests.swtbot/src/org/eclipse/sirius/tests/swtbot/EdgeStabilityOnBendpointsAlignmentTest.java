/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * 
 * @author smonnier
 */
public class EdgeStabilityOnBendpointsAlignmentTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "tc1981.ecore";

    private static final String SESSION_FILE = "tc1981.aird";

    private static final String DATA_UNIT_DIR = "data/unit/edgeStabilityOnBendpointsAlignment/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method. For the moment mouseDrag does not work on bendpoints. The
     * edge is selected but not moved.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeStabilityOnBendpointsAlignment() throws Exception {

        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            editor.drag(360, 98, 268, 225);

            editor.drag(95, 195, 224, 132);
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }
    }

}
