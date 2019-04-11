/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
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

    private static final String VSM_FILE = "My.odesign";

    private static final String REPRESENTATION_DECRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_NAME = "diagram";

    private static final PrecisionPoint INITIAL_NODE_CENTER_POSITION = new PrecisionPoint(856.0, 412.0);

    private Session session;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
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
     * Tests that no selection handles are visible after deleting two edges selected by their label parts. See #546298.
     */
    public void testMultiEdgesSelection() {
        // Select an element on the top left to always have the same initial
        // scroll
        editor.scrollTo(0, 0);
        editor.getEditPart("C1", AbstractBorderedShapeEditPart.class).click();

        // check the initial position of node C2
        checkBottomRightCornerNodeAbsolutePosition("Bad diagram test data");

        SWTBotGefEditPart editPart = editor.getEditPart("[0..1] ref");
        IFigure layer = LayerManager.Helper.find(editPart.part()).getLayer(LayerConstants.HANDLE_LAYER);

        editor.click("[0..1] ref");
        editor.clickWithKeys("[0..1] ref1", SWT.CTRL);

        editor.clickContextMenu("Edit").clickContextMenu("Delete from Model");
        assertEquals("Some selection handles are still visible whereas there should be none.", 0, layer.getChildren().size());
    }

    /**
     * Check that the selection of an edge that is not fully displayed in the editor will not move the content of the
     * editor that is not move the scroll bars.
     */
    public void testEditorContentPositionAfterEdgeSelection() {
        // Select an element on the top left to always have the same initial
        // scroll
        editor.scrollTo(0, 0);
        editor.getEditPart("C1", AbstractBorderedShapeEditPart.class).click();

        // check the initial position of node C2
        checkBottomRightCornerNodeAbsolutePosition("Bad diagram test data");

        // Select the edge
        SWTBotGefEditPart edgeEditPart = editor.getEditPart("[0..1] ref", ConnectionEditPart.class);
        edgeEditPart.click(getBounds(edgeEditPart).getTopLeft().getTranslated(2, 2));
        bot.waitUntil(new CheckSelectedCondition(editor, edgeEditPart.part()), 2000);

        // Check that the node C2 has not moved
        checkBottomRightCornerNodeAbsolutePosition("The selection of an edge moves the editor content. Bad node position");
    }

    /**
     * Check that the selection of an node that is not fully displayed in the editor will not move the content of the
     * editor that is not move the scroll bars.
     */
    public void testEditorContentPositionAfterNodeSelection() {
        // Select an element on the top left to always have the same initial
        // scroll
        editor.scrollTo(0, 0);
        editor.getEditPart("C1", AbstractBorderedShapeEditPart.class).click();

        // check the initial position of node C2
        checkBottomRightCornerNodeAbsolutePosition("Bad diagram test data");

        // Select the node C2
        SWTBotGefEditPart swtNodeEditPart = editor.getEditPart("C2", AbstractBorderedShapeEditPart.class);
        swtNodeEditPart.click();
        bot.waitUntil(new CheckSelectedCondition(editor, swtNodeEditPart.part()), 2000);

        // Check that the node C2 has not moved
        checkBottomRightCornerNodeAbsolutePosition("The selection of an Node moves the editor content. Bad node position");
    }

    /**
     * Check that the selection of a border node that is not fully displayed in
     * the editor will not move the content of the editor that is not move the
     * scroll bars.
     */
    public void testEditorContentPositionAfterBorderNodeSelection() {
        editor.close();
        SWTBotUtils.waitAllUiEvents();

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(session, "MyDiagram", "diagram2", DDiagram.class, true);
        // Select an element on the top left to always have the same initial
        // scroll
        editor.scrollTo(0, 0);
        editor.getEditPart("C1", AbstractBorderedShapeEditPart.class).click();

        // check the initial position of node C2
        checkBottomRightCornerNodeAbsolutePosition("Bad diagram test data");

        // Select the border node C2Border
        SWTBotGefEditPart swtNodeEditPart = editor.getEditPart("C2Border", AbstractDiagramBorderNodeEditPart.class);
        swtNodeEditPart.click(getBounds(swtNodeEditPart).getRight().getTranslated(-10, 0));
        bot.waitUntil(new CheckSelectedCondition(editor, swtNodeEditPart.part()), 2000);

        // Check that the node C2 has not moved
        checkBottomRightCornerNodeAbsolutePosition("The selection of a BorderNode moves the editor content. Bad node position");
    }
    
    void checkBottomRightCornerNodeAbsolutePosition(String message) {
        IGraphicalEditPart nodeEditPart = (IGraphicalEditPart) editor.getEditPart("C2", AbstractBorderedShapeEditPart.class).part();
        Rectangle bounds = nodeEditPart.getFigure().getBounds().getCopy();
        nodeEditPart.getFigure().translateToAbsolute(bounds);
        assertEquals(message, INITIAL_NODE_CENTER_POSITION, bounds.getCenter());
    }

    private Rectangle getBounds(SWTBotGefEditPart swtBotGefEditPart) {
        final IFigure figure = ((GraphicalEditPart) swtBotGefEditPart.part()).getFigure();
        Rectangle bounds = figure.getBounds().getCopy();
        if (figure instanceof PolylineConnectionEx) {
            bounds = ((PolylineConnectionEx) figure).getSimpleBounds();
        }
        figure.translateToAbsolute(bounds);
        return bounds;
    }

}
