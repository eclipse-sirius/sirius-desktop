/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;

/**
 * 
 * @author scosta
 */
public class EdgeLabelLocationZoomTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeLabelLocationZoom/";

    private static final String REPRESENTATION_INSTANCE_NAME = "new DTestBugLabelEdge";

    private static final String REPRESENTATION_NAME = "DTestBugLabelEdge";

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
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    public void testEdgeLabelLocationWithZoom() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        DEdgeNameEditPart labelEditPart = (DEdgeNameEditPart) editor.getEditPart("newEReference1", DEdgeNameEditPart.class).part();
        for (ZoomLevel zoom : ZoomLevel.values()) {
            editor.zoom(zoom);
            Rectangle labelBound = labelEditPart.getFigure().getBounds();
            String failureMessagePos = "The label is in the wrong position (zoom " + zoom.getLevel() + ")";
            String failureMessageSize = "The label is the wrong size (zoom " + zoom.getLevel() + ")";
            assertEquals(failureMessagePos, labelBound.preciseX(), 1372., 2.);
            assertEquals(failureMessagePos, labelBound.preciseY(), 1055., 2.);
            assertEquals(failureMessageSize, labelBound.preciseWidth(), 135., 2.);
            assertEquals(failureMessageSize, labelBound.preciseHeight(), 23., 2.);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        diagram = null;
        super.tearDown();
    }
}
