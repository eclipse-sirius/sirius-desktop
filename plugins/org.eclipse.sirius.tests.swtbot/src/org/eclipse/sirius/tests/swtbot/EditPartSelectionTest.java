/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests to check the behavior of the editor when selecting a node or edge edit part.
 * 
 * @author lfasani
 */
public class EditPartSelectionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/selection/";

    private static final String MODEL = "TestSelection.ecore";

    private static final String SESSION_FILE = "TestSelection.aird";

    private static final String REPRESENTATION_DECRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_NAME = "diagram";

    private static final PrecisionPoint INITIAL_NODE_CENTER_POSITION = new PrecisionPoint(856.0, 412.0);

    private Session session;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();
        session = localSession.getOpenedSession();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(session, REPRESENTATION_DECRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class, true);
    }

    /**
     * Check that the selection of an edge that is not fully displayed in the editor will not move the content of the
     * editor that is not move the scroll bars.
     */
    public void testEditorContentPositionAfterEdgeSelection() {
        // check the initial position
        checkBottomRightCornerNodeAbsolutePosition("Bad diagram test data");

        // Select the edge
        SWTBotGefEditPart edgeEditPart = editor.getEditPart("[0..1] ref", ConnectionEditPart.class);
        edgeEditPart.click();
        SWTBotUtils.waitAllUiEvents();

        // Check that the node has not moved
        checkBottomRightCornerNodeAbsolutePosition("The selection of an edge moved the editor content. Bad node position");
    }

    /**
     * Check that the selection of an node that is not fully displayed in the editor will not move the content of the
     * editor that is not move the scroll bars.
     */
    public void testEditorContentPositionAfterNodeSelection() {
        // check the initial position
        SWTBotGefEditPart swtNodeEditPart = editor.getEditPart("C2", AbstractBorderedShapeEditPart.class);
        checkBottomRightCornerNodeAbsolutePosition("Bad diagram test data");

        swtNodeEditPart.click();
        SWTBotUtils.waitAllUiEvents();

        // Check that the node has not moved
        checkBottomRightCornerNodeAbsolutePosition("The selection of an Node moved the editor content. Bad node position");
    }

    void checkBottomRightCornerNodeAbsolutePosition(String message) {
        IGraphicalEditPart nodeEditPart = (IGraphicalEditPart) editor.getEditPart("C2", AbstractBorderedShapeEditPart.class).part();
        Rectangle bounds = nodeEditPart.getFigure().getBounds().getCopy();
        nodeEditPart.getFigure().translateToAbsolute(bounds);
        assertEquals("The selection of an edge moved the editor content. Bad node position", INITIAL_NODE_CENTER_POSITION, bounds.getCenter());
    }

}
