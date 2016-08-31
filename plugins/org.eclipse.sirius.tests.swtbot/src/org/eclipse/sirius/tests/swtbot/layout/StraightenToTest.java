/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.layout;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Class test for the new feature that straighten edges. See bug #499991 for
 * details.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StraightenToTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "straightenTo.migrationmodeler";

    private static final String SESSION_FILE = "straightenTo.aird";

    private static final String VSM_FILE = "useCase.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/straightenTo/";

    private static final String VSM_DATA_UNIT_DIR = "data/unit/shapeResizing/";

    private String[] labels = { Messages.StraightenToAction_toTopLabel, Messages.StraightenToAction_toBottomLabel, Messages.StraightenToAction_toLeftLabel, Messages.StraightenToAction_toRightLabel };

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        copyFileToTestProject(Activator.PLUGIN_ID, VSM_DATA_UNIT_DIR, VSM_FILE);
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "useCase", "new useCase", DDiagram.class);
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        editor = null;
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * <UL>
     * <LI>Straighten oblique edge12 to Left: Expected OK</LI>
     * <LI>Straighten oblique edge12 to Right: Expected OK</LI>
     * <LI>Straighten oblique edge12 to Top: Expected: menu disabled (invalid
     * axis)</LI>
     * <LI>Straighten oblique edge12 to Bottom: Expected: menu disabled (invalid
     * axis)</LI>
     * </UL>
     */
    public void testObliqueEdgeLeftAndRight() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { false, false, true, true };
        checkEdgeActions(availableDirections, "edge12");
    }

    /**
     * <UL>
     * <LI>Straighten oblique edge15 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge15 to Bottom: Expected OK</LI>
     * </UL>
     */
    public void testObliqueEdgeTopAndBottom() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { true, true, false, false };
        checkEdgeActions(availableDirections, "edge15");
    }

    /**
     * <UL>
     * <LI>Straighten rectilinear edge14 to Top: Expected OK</LI>
     * <LI>Straighten rectilinear edge14 to Bottom: Expected: menu disabled (out
     * of bounds)</LI>
     * </UL>
     */
    public void testRectilinearTopAndBottomOutOfBounds() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { true, false, false, false };
        checkEdgeActions(availableDirections, "edge14");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge4 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge4 to Bottom: Expected: menu disabled (border
     * node with several edge)</LI>
     * </ul>
     */
    public void testObliqueEdgeTopAndBottomForbidden() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { true, false, false, false };
        checkEdgeActions(availableDirections, "edge4");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge6 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge6 to Bottom: Expected: menu disabled (overlap)
     * </LI>
     * </ul>
     */
    public void testObliqueEdgeTopAndBottomForbiddenForOverlap() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { true, false, false, false };
        checkEdgeActions(availableDirections, "edge6");
    }

    /**
     * Straighten rectilinear edge2 to *: Expected: menu disabled (not same
     * axis)
     */
    public void testRectilineaAllForbiddenForDifferentAxes() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { false, false, false, false };
        checkEdgeActions(availableDirections, "edge2");
    }

    /**
     * <ul>
     * <LI>Straighten rectilinear edge5 to Right: Expected: OK</LI>
     * <LI>Straighten rectilinear edge5 to Left: Expected: menu disabled
     * (centered edge on target side)</LI>
     * </ul>
     */
    public void testRectilineaToRightWithLeftForbiddenBecauseofCentering() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { false, false, false, true };
        checkEdgeActions(availableDirections, "edge5");
    }

    /**
     * <ul>
     * <LI>Straighten rectilinear edge5 to Right with zoom 200% : Expected: OK
     * </LI>
     * <LI>Straighten rectilinear edge5 to Left with zoom 200% : Expected: menu
     * disabled (centered edge on target side)</LI>
     * </ul>
     */
    public void testRectilineaToRightWithLeftForbiddenBecauseofCenteringWithZoom200() {
        editor.zoom(ZoomLevel.ZOOM_200);
        try {
            // {top,bottom,left,right}
            boolean[] availableDirections = { false, false, false, true };
            checkEdgeActions(availableDirections, "edge5");
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Straighten edge3 AND edge5 to Right: Expected: OK (for both)
     */
    public void testTwoEdgesToRightWithOneWithLeftForbidden() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { false, false, false, true };
        checkEdgeActions(availableDirections, "edge5", "edge3");
    }

    /**
     * Straighten edge5 AND edge15 to *: Expected: menu disabled (not same axis
     * for both edges)
     */
    public void testTwoEdgesAllForbidden() {
        // {top,bottom,left,right}
        boolean[] availableDirections = { false, false, false, false };
        checkEdgeActions(availableDirections, "edge5", "edge15");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge7 to Bottom: Expected: OK (with scrollbar)
     * </LI>
     * <LI>Straighten oblique edge7 to Top: Expected: menu disabled (out of
     * bounds)</LI>
     * </ul>
     */
    public void testObliqueToBottomWithScrollbar() {
        // Reveals the edit part to have scrollbar
        SWTBotGefEditPart editPart = editor.getEditPart("edge7", AbstractDiagramEdgeEditPart.class);
        editor.reveal(editPart.part());
        // {top,bottom,left,right}
        boolean[] availableDirections = { false, true, false, false };
        checkEdgeActions(availableDirections, "edge7");
    }

    /**
     * <UL>
     * <LI>Straighten rectilinear edge14 to Top: Expected OK (with scrollbar)
     * </LI>
     * <LI>Straighten rectilinear edge14 to Bottom: Expected: menu disabled (out
     * of bounds with scrollbar)</LI>
     * </UL>
     */
    public void testRectilinearTopAndBottomOutOfBoundsWithScrollbar() {
        // Reveals the edit part to have scrollbar
        SWTBotGefEditPart editPart = editor.getEditPart("edge14", AbstractDiagramEdgeEditPart.class);
        editor.reveal(editPart.part());
        // {top,bottom,left,right}
        boolean[] availableDirections = { true, false, false, false };
        checkEdgeActions(availableDirections, "edge14");
    }

    /**
     * Straighten edge12 AND container3 to *: Expected: menu not visible
     * (container in selection)
     */
    public void testMenuVisibilityWithEdgeAndContainer() {
        SWTBotGefEditPart editPart1 = editor.getEditPart("edge12", AbstractDiagramEdgeEditPart.class);
        SWTBotGefEditPart editPart2 = editor.getEditPart("container3", AbstractDiagramContainerEditPart.class);
        editor.select(editPart1, editPart2);
        try {
            editor.clickContextMenu(Messages.StraightenToMenuAction_text);
            fail("The " + Messages.StraightenToMenuAction_text + " menu should not be displayed");
        } catch (WidgetNotFoundException e) {
            // do nothing, normal case.
        }
    }

    /**
     * Checks the edge "to straight" actions. Makes sure that:
     * <ul>
     * <li>All actions (To Top, To Bottom etc.) exist in the menu</li>
     * <li>Actions are enable according to the availableDirections argument</li>
     * <li>All enabled actions result is correct</li>
     * </ul>
     * 
     * @param availableDirections
     *            the actions that should be available: {top,bottom,left,right}
     * @param edgeNames
     *            list of names corresponding to the edges to select.
     */
    private void checkEdgeActions(boolean[] availableDirections, String... edgeNames) {
        for (int i = 0; i < 4; i++) {
            Map<SWTBotGefEditPart, List<Point>> gefEditParts2ExpectedPointList = Maps.newHashMap();
            for (String edgeName : edgeNames) {
                gefEditParts2ExpectedPointList.put(editor.getEditPart(edgeName, AbstractDiagramEdgeEditPart.class), Lists.<Point> newArrayList());
            }
            editor.select(gefEditParts2ExpectedPointList.keySet());
            try {
                boolean enable = SWTBotUtils.isMenuEnabled(bot.getDisplay(), editor.getDiagramEditPart().getViewer().getControl(), labels[i]);
                // The test fail if the action should not be enable and if it is
                // and vice versa.
                if (availableDirections[i] ^ enable) {
                    String status = availableDirections[i] ? "enabled" : "disabled";
                    fail("the \"" + labels[i] + "\" menu should be " + status + " for the edge selection: " + Arrays.toString(edgeNames));
                }
                if (enable) {
                    // if the action is enabled we check the result.
                    for (SWTBotGefEditPart edgeEditPart : gefEditParts2ExpectedPointList.keySet()) {
                        List<Point> pointList = gefEditParts2ExpectedPointList.get(edgeEditPart);
                        computeExpectedPoints(edgeEditPart, pointList, i);
                    }
                    editor.clickContextMenu(labels[i]);
                    for (SWTBotGefEditPart edgeEditPart : gefEditParts2ExpectedPointList.keySet()) {
                        List<Point> pointList = gefEditParts2ExpectedPointList.get(edgeEditPart);
                        checkResult(edgeEditPart, pointList, i);
                    }
                    undo();
                }
            } catch (WidgetNotFoundException e) {
                fail("the \"" + labels[i] + "\" menu should be displayed for the edge selection: " + Arrays.toString(edgeNames));
            }
        }
    }

    /**
     * Checks that the current edge has the expected start and end points
     * 
     * @param edgeEditPart
     *            the current edge swtbot edit part.
     * @param pointList
     *            the expected point list. The Start at 0 index and End at 1
     *            index.
     * @param i
     *            the current action tested in labels order
     *            {top,bottom,left,right}
     */
    private void checkResult(SWTBotGefEditPart edgeEditPart, List<Point> pointList, int i) {
        AbstractDiagramEdgeEditPart part = (AbstractDiagramEdgeEditPart) edgeEditPart.part();
        PolylineConnectionEx polylineConnection = part.getPolylineConnectionFigure();
        assertEquals("The edge should have only two points: the start and the end.", 2, polylineConnection.getPoints().size());
        Point newStartPoint = polylineConnection.getStart();
        Point newEndPoint = polylineConnection.getEnd();
        Point expectedStartPoint = pointList.get(0);
        Point expectedEndPoint = pointList.get(1);
        String edgeLabel = getLabelFromEdgeEditPart(edgeEditPart);
        assertEquals("Wrong edge expected start point when applying \"" + labels[i] + "\" action on edge " + edgeLabel, expectedStartPoint, newStartPoint);
        assertEquals("Wrong edge expected end point when applying \"" + labels[i] + "\" action on edge " + edgeLabel, expectedEndPoint, newEndPoint);
    }

    private String getLabelFromEdgeEditPart(SWTBotGefEditPart edgeEditPart) {
        DEdgeEditPart dEdgeEditPart = (DEdgeEditPart) edgeEditPart.part();
        ViewEdgeFigure figure = (ViewEdgeFigure) dEdgeEditPart.getFigure();
        SiriusWrapLabel label = figure.getFigureViewEdgeNameFigure();
        String edgeLabel = label.getText();
        return edgeLabel;
    }

    /**
     * Compute the expected start and end points for the current edge.
     * 
     * @param edgeEditPart
     *            the swtbot gef editpart to check points.
     * @param pointList
     *            the list where register the expected points, (Start at 0 and
     *            End at 1)
     * @param i
     *            the current action index in labels order
     *            {top,bottom,left,right} order
     */
    private void computeExpectedPoints(SWTBotGefEditPart edgeEditPart, List<Point> pointList, int i) {

        AbstractDiagramEdgeEditPart part = (AbstractDiagramEdgeEditPart) edgeEditPart.part();
        PolylineConnectionEx polylineConnection = part.getPolylineConnectionFigure();
        Point startPointBefore = polylineConnection.getStart();
        Point endPointBefore = polylineConnection.getEnd();
        Point expectedStartPoint = startPointBefore.getCopy();
        Point expectedEndPoint = endPointBefore.getCopy();
        pointList.add(expectedStartPoint);
        pointList.add(expectedEndPoint);
        switch (i) {
        // top
        case 0:
            if (startPointBefore.y <= endPointBefore.y) {
                expectedEndPoint.setY(startPointBefore.y);
            } else {
                expectedStartPoint.setY(endPointBefore.y);
            }
            break;
        // bottom
        case 1:
            if (startPointBefore.y <= endPointBefore.y) {
                expectedStartPoint.setY(endPointBefore.y);
            } else {
                expectedEndPoint.setY(startPointBefore.y);
            }
            break;
        // left
        case 2:
            if (startPointBefore.x <= endPointBefore.x) {
                expectedEndPoint.setX(startPointBefore.x);
            } else {
                expectedStartPoint.setX(endPointBefore.x);
            }
            break;
        // right
        case 3:
            if (startPointBefore.x <= endPointBefore.x) {
                expectedStartPoint.setX(endPointBefore.x);
            } else {
                expectedEndPoint.setX(startPointBefore.x);
            }
            break;

        default:
            break;
        }
    }
}
