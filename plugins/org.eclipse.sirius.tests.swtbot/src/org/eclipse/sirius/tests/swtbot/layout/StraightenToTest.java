/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.layout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Class test for the new feature that straighten edges. See bug #499991 for details.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StraightenToTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "straightenTo.migrationmodeler";

    private static final String SESSION_FILE = "straightenTo.aird";

    private static final String VSM_FILE = "useCase.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/straightenTo/";

    private static final String VSM_DATA_UNIT_DIR = "data/unit/shapeResizing/";

    private static final Point IGNORED_POINT = new Point(-1, -1);

    private String[] labels = { Messages.StraightenToAction_toTopLabel, Messages.StraightenToAction_toBottomLabel, Messages.StraightenToAction_LeftSidePinnedLabel,
            Messages.StraightenToAction_RightSidePinnedLabel, Messages.StraightenToAction_toLeftLabel, Messages.StraightenToAction_toRightLabel, Messages.StraightenToAction_TopSidePinnedLabel,
            Messages.StraightenToAction_BottomSidePinnedLabel };

    /**
     * This class allows to gather diagrams parts or swtbot parts with their label.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private class TestPart {
        public String partName;

        public SWTBotGefEditPart swtBotpart;

        public AbstractDiagramEdgeEditPart part;

        public TestPart(String edgeName, SWTBotGefEditPart editPart) {
            this.partName = edgeName;
            this.swtBotpart = editPart;
        }

        public TestPart(String edgeName, AbstractDiagramEdgeEditPart edgeEditPart) {
            this.partName = edgeName;
            this.part = edgeEditPart;
        }

    }

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
        closeOutline();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "useCase", "new useCase", DDiagram.class);
    }

    @Override
    protected void tearDown() throws Exception {
        // Reset editor scroll
        editor.scrollTo(0, 0);
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
     * <LI>Straighten oblique edge12 to Top: Expected: menu disabled (invalid axis)</LI>
     * <LI>Straighten oblique edge12 to Bottom: Expected: menu disabled (invalid axis)</LI>
     * </UL>
     */
    public void testObliqueEdgeLeftAndRight() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, true, true, true, true };
        checkEdgeActions(availableDirections, "edge12");
    }

    /**
     * <UL>
     * <LI>Straighten oblique edge24 to Left: Expected OK</LI>
     * <LI>Straighten oblique edge24 to Right: Expected OK</LI>
     * <LI>Straighten oblique edge24 to Top: Expected: menu disabled (invalid axis)</LI>
     * <LI>Straighten oblique edge24 to Bottom: Expected: menu disabled (invalid axis)</LI>
     * </UL>
     */
    public void testObliqueEdgeLeftAndRightWithWrongYGMFCoordinate() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, true, true, true, true };
        checkEdgeActions(availableDirections, "edge24");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge8 to Right: Expected: menu disabled (centered edge on source side)</LI>
     * <LI>Straighten oblique edge8 to Left: Expected: menu disabled (centered edge on source side)</LI>
     * </ul>
     */
    public void testObliqueEdgeLeftAndRightForbiddenBecauseofCentering() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, false, false, false, false };
        checkEdgeActions(availableDirections, "edge8");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge9 to Right: Expected: OK</LI>
     * <LI>Straighten oblique edge9 to Left: Expected: OK</LI>
     * </ul>
     */
    public void testObliqueEdgeLeftAndRightCenteringOnBothSourceAndTargetBorderNodes() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, true, true, true, true };
        checkEdgeActions(availableDirections, "edge9");
    }

    /**
     * <UL>
     * <LI>Straighten oblique edge15 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge15 to Bottom: Expected OK</LI>
     * </UL>
     */
    public void testObliqueEdgeTopAndBottom() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, true, true, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge15");
    }

    /**
     * <UL>
     * <LI>Straighten oblique edge25 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge25 to Bottom: Expected OK</LI>
     * </UL>
     */
    public void testObliqueEdgeTopAndBottomWithWrongXGMFCoordinate() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, true, true, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge25");
    }

    /**
     * <UL>
     * <LI>Straighten oblique edge21 to Top: Expected KO</LI>
     * <LI>Straighten oblique edge21 to Bottom: Expected OK</LI>
     * </UL>
     */
    public void testObliqueEdgeTopAndBottomBetweenBorderNodeNotAsSameLevel() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, true, true, false, false, false, false, false };
        checkEdgeActions(availableDirections, "edge21");
    }

    /**
     * <UL>
     * <LI>Straighten rectilinear edge14 to Top: Expected OK</LI>
     * <LI>Straighten rectilinear edge14 to Bottom: Expected: menu disabled (out of bounds)</LI>
     * </UL>
     */
    public void testRectilinearTopAndBottomOutOfBounds() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, false, false, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge14");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge4 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge4 to Bottom: Expected OK (even if it concerns a border node with several edge)</LI>
     * </ul>
     */
    public void testObliqueEdgeLinkedToBorderNodeWithSeveralEdges() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, true, true, true, false, false, false, false };
        checkEdgeActions(availableDirections, true, null, "edge4");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge17 to Left: Expected OK (even if it concerns a border node with several edge)</LI>
     * <LI>Straighten oblique edge17 to Right: Expected OK</LI>
     * </ul>
     */
    public void testRectilinearEdgeLinkedToBorderNodeWithSeveralEdges() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, true, true, true, true };
        checkEdgeActions(availableDirections, true, null, "edge17");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge17 to Left: Expected OK (even if it concerns a border node with several edge)</LI>
     * <LI>Straighten oblique edge17 to Right: Expected OK</LI>
     * </ul>
     */
    public void testRectilinearEdgeLinkedToBorderNodeWithSeveralEdgesWithZoom200() {
        editor.zoom(ZoomLevel.ZOOM_200);
        try {
            testRectilinearEdgeLinkedToBorderNodeWithSeveralEdges();
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge6 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge6 to Bottom: Expected: menu disabled (overlap)</LI>
     * </ul>
     */
    public void testObliqueEdgeTopAndBottomForbiddenForOverlap() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, false, false, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge6");
    }

    /**
     * Straighten rectilinear edge2 to *: Expected: menu disabled (not same axis)
     */
    public void testRectilinearAllForbiddenForDifferentAxes() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, false, false, false, false };
        checkEdgeActions(availableDirections, "edge2");
    }

    /**
     * <ul>
     * <LI>Straighten rectilinear edge5 to Right: Expected: OK</LI>
     * <LI>Straighten rectilinear edge5 to Left: Expected: menu disabled (centered edge on target side)</LI>
     * </ul>
     */
    public void testRectilinearToRightWithLeftForbiddenBecauseofCentering() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, false, true, true, false };
        checkEdgeActions(availableDirections, "edge5");
    }

    /**
     * <ul>
     * <LI>Straighten rectilinear edge5 to Right with zoom 200% : Expected: OK</LI>
     * <LI>Straighten rectilinear edge5 to Left with zoom 200% : Expected: menu disabled (centered edge on target
     * side)</LI>
     * </ul>
     */
    public void testRectilinearToRightWithLeftForbiddenBecauseofCenteringWithZoom200() {
        editor.zoom(ZoomLevel.ZOOM_200);
        try {
            // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
            boolean[] availableDirections = { false, false, false, false, false, true, true, false };
            checkEdgeActions(availableDirections, "edge5");
        } finally {
            editor.zoom(ZoomLevel.ZOOM_100);
        }
    }

    /**
     * Straighten edge3 AND edge5 to Right: Expected: OK (for both)
     */
    public void testTwoEdgesToRightWithOneWithLeftForbidden() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, false, false, false, false, true, true, false };
        checkEdgeActions(availableDirections, "edge5", "edge3");
    }

    /**
     * Edges subject to a straighten action.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    private enum EdgeImpact {
        FIRST_EDGE, SECOND_EDGE, BOTH_EDGE;
    }

    /**
     * Tests that launching "straighten to" action available for one edge among two selected applies the straightening
     * to this edge without error.
     */
    public void testTwoEdgesAllIncompatibleStraigthenAction() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, true, true, true, false, true, true, false };
        Map<Integer, EdgeImpact> directionsIndexToConcernedEdge = new HashMap<>();
        directionsIndexToConcernedEdge.put(0, EdgeImpact.FIRST_EDGE);
        directionsIndexToConcernedEdge.put(1, EdgeImpact.FIRST_EDGE);
        directionsIndexToConcernedEdge.put(2, EdgeImpact.FIRST_EDGE);
        directionsIndexToConcernedEdge.put(3, EdgeImpact.FIRST_EDGE);
        directionsIndexToConcernedEdge.put(5, EdgeImpact.SECOND_EDGE);
        directionsIndexToConcernedEdge.put(6, EdgeImpact.SECOND_EDGE);
        checkEdgeActions(availableDirections, directionsIndexToConcernedEdge, "edge15", "edge5");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge7 to Bottom: Expected: OK (with scrollbar)</LI>
     * <LI>Straighten oblique edge7 to Top: Expected: menu disabled (out of bounds)</LI>
     * </ul>
     */
    public void testObliqueToBottomWithScrollbar() {
        // Reveals the edit part to have scrollbar
        SWTBotGefEditPart editPart = editor.getEditPart("edge7", AbstractDiagramEdgeEditPart.class);
        editor.reveal(editPart.part());
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, true, false, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge7");
    }

    /**
     * <UL>
     * <LI>Straighten rectilinear edge14 to Top: Expected OK (with scrollbar)</LI>
     * <LI>Straighten rectilinear edge14 to Bottom: Expected: menu disabled (out of bounds with scrollbar)</LI>
     * </UL>
     */
    public void testRectilinearTopAndBottomOutOfBoundsWithScrollbar() {
        // Reveals the edit part to have scrollbar
        SWTBotGefEditPart editPart = editor.getEditPart("edge14", AbstractDiagramEdgeEditPart.class);
        editor.reveal(editPart.part());
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, false, false, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge14");
    }

    /**
     * Straighten edge12 AND container3 to *: Expected: menu visible (container in selection)
     */
    public void testMenuVisibilityWithEdgeAndContainer() {
        SWTBotGefEditPart editPart1 = editor.getEditPart("edge12", AbstractDiagramEdgeEditPart.class);
        SWTBotGefEditPart editPart2 = editor.getEditPart("container3", AbstractDiagramContainerEditPart.class);
        editor.select(editPart1, editPart2);
        editor.reveal(editPart1.part());
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, true, true, false, false, false, false, false };
        checkEdgeActions(availableDirections, "edge11");
    }

    /**
     * Straighten edge12 AND its name part are selected:
     * 
     * Expected: menu visible
     */
    public void testMenuVisibilityWithEdgeAndPartName() {
        SWTBotGefEditPart editPart1 = editor.getEditPart("edge12", AbstractDiagramEdgeEditPart.class);
        SWTBotGefEditPart editPart2 = editor.getEditPart("edge12", AbstractDiagramNameEditPart.class);
        editor.select(editPart1, editPart2);
        editor.reveal(editPart1.part());
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, true, true, false, false, false, false, false };
        checkEdgeActions(availableDirections, "edge11");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge11 to Top: Expected: menu disabled (out of bounds)</LI>
     * <LI>Straighten oblique edge11 to Bottom: Expected OK</LI>
     * </ul>
     */
    public void testObliqueEdgeTopAndBottomBetweenElementAtSameLevelButNotInSameContainer() {
        SWTBotGefEditPart editPart = editor.getEditPart("container9", AbstractDiagramContainerEditPart.class);
        editor.reveal(editPart.part());
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { false, true, true, false, false, false, false, false };
        checkEdgeActions(availableDirections, "edge11");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge10 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge10 to Bottom: Expected: menu disabled (out of bounds)</LI>
     * </ul>
     */
    public void testObliqueEdgeTopAndBottomBetweenElementNotAtSameLevel() {
        SWTBotGefEditPart editPart = editor.getEditPart("container9", AbstractDiagramContainerEditPart.class);
        editor.reveal(editPart.part());
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, false, true, false, false, false, false, false };
        checkEdgeActions(availableDirections, "edge10");
    }

    /**
     * <ul>
     * <LI>Straighten oblique edge13 to Top: Expected OK</LI>
     * <LI>Straighten oblique edge13 to Bottom: Expected OK</LI>
     * </ul>
     */
    public void testObliqueEdgeTopAndBottomBetweenBorderNodeAtSameLevelButNotInSameContainer() {
        SWTBotGefEditPart editPart = editor.getEditPart("container9", AbstractDiagramContainerEditPart.class);
        editor.reveal(editPart.part());
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, true, true, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge13");
    }

    /**
     * Straighten edge22 AND edge23 to top and bottom: Expected: OK (for both)
     */
    public void testTwoEdgesLinkedToBorderNodeWithOverlapBeforeStraighten() {
        // {top,bottom,leftSide,rightSide,left,right,topSide,BottomSide}
        boolean[] availableDirections = { true, true, true, true, false, false, false, false };
        checkEdgeActions(availableDirections, "edge22", "edge23");
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
        checkEdgeActions(availableDirections, false, null, edgeNames);
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
    private void checkEdgeActions(boolean[] availableDirections, Map<Integer, EdgeImpact> directionsIndexToConcernedEdge, String... edgeNames) {
        checkEdgeActions(availableDirections, false, directionsIndexToConcernedEdge, edgeNames);
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
     * @param checkOtherEdges
     *            true if the bendpoints stability of edges linked to moved border node must also be checked (only
     *            segment linked to border node must be moved), false otherwise
     * @param directionsIndexToConcernedEdge
     *            a map that associate the edge(s) that will be changed if the straighten command at the index given by
     *            the key is executed. The command to execute is the one represented by the direction at the given index
     *            of availableDirections parameter.
     * @param edgeNames
     *            list of names corresponding to the edges to select.
     */
    private void checkEdgeActions(boolean[] availableDirections, boolean checkOtherEdges, Map<Integer, EdgeImpact> directionsIndexToConcernedEdge, String... edgeNames) {
        for (int i = 0; i < 8; i++) {
            Map<TestPart, List<Point>> gefEditParts2ExpectedPointList = new HashMap<>();
            // Map only used if checkOtherEdges is true
            Map<TestPart, PointList> otherEdgeEditParts2ExpectedPointList = new HashMap<>();
            for (String edgeName : edgeNames) {
                gefEditParts2ExpectedPointList.put(new TestPart(edgeName, editor.getEditPart(edgeName, AbstractDiagramEdgeEditPart.class)), Lists.<Point> newArrayList());
            }
            editor.select(gefEditParts2ExpectedPointList.keySet().stream().map(part -> part.swtBotpart).collect(Collectors.toSet()));
            try {
                boolean enable = SWTBotUtils.isMenuEnabled(bot.getDisplay(), editor.getDiagramEditPart().getViewer().getControl(), labels[i]);
                // The test fail if the action should not be enable and if it is
                // and vice versa.
                if (availableDirections[i] ^ enable) {
                    String status = availableDirections[i] ? "enabled" : "disabled";
                    fail("the \"" + labels[i] + "\" menu should be " + status + " for the edge selection: " + Arrays.toString(edgeNames));
                }
                if (enable) {
                    int j = 0;
                    // if the action is enabled we check the result.
                    for (String edgeName : edgeNames) {
                        TestPart testPart = gefEditParts2ExpectedPointList.keySet().stream().filter(part -> edgeName.equals(part.partName)).findFirst().get();
                        SWTBotGefEditPart edgeEditPart = testPart.swtBotpart;
                        boolean computePoints = checkEdge(directionsIndexToConcernedEdge, i, j);
                        if (computePoints) {
                            List<Point> pointList = gefEditParts2ExpectedPointList.get(testPart);
                            computeExpectedPoints(edgeEditPart, pointList, i);
                            if (checkOtherEdges) {
                                computeOtherEdgesExpectedPoints(edgeEditPart, otherEdgeEditParts2ExpectedPointList, i);
                            }
                        }
                        j++;
                    }
                    editor.clickContextMenu(labels[i]);
                    j = 0;
                    for (String edgeName : edgeNames) {
                        TestPart testPart = gefEditParts2ExpectedPointList.keySet().stream().filter(part -> edgeName.equals(part.partName)).findFirst().get();
                        SWTBotGefEditPart edgeEditPart = testPart.swtBotpart;
                        boolean checkResult = checkEdge(directionsIndexToConcernedEdge, i, j);
                        if (checkResult) {
                            List<Point> pointList = gefEditParts2ExpectedPointList.get(testPart);
                            checkResult(edgeEditPart, pointList, i);
                            if (checkOtherEdges) {
                                checkResultOfOtherEdges(edgeEditPart, otherEdgeEditParts2ExpectedPointList);
                            }
                        }
                        j++;
                    }
                    undo();
                }
            } catch (WidgetNotFoundException e) {
                fail("the \"" + labels[i] + "\" menu should be displayed for the edge selection: " + Arrays.toString(edgeNames));
            }
        }
    }

    /**
     * Checks that the j th edge selected is changed by the straighten command at i index and thus it's change should be
     * verified.
     * 
     * @param directionsIndexToConcernedEdge
     *            the map that gives the edges impacted by the straighten command at the key position.
     * @param i
     *            the index of the straighten command to execute.
     * @param j
     *            the j th edge selected before executing the straighten command.
     * @return true the j th edge selected is changed by the straighten command at i index and thus it's change should
     *         be verified. False otherwise
     */
    private boolean checkEdge(Map<Integer, EdgeImpact> directionsIndexToConcernedEdge, int i, int j) {
        boolean checkEdge = false;
        if (directionsIndexToConcernedEdge != null) {
            // we only check result for edges concerned by the straighten command executed.
            switch (directionsIndexToConcernedEdge.get(i)) {
            case BOTH_EDGE:
                if (j == 0 || j == 1) {
                    checkEdge = true;
                }
                break;
            case FIRST_EDGE:
                if (j == 0) {
                    checkEdge = true;
                }
                break;
            case SECOND_EDGE:
                if (j == 1) {
                    checkEdge = true;
                }
                break;
            default:
                checkEdge = true;
                break;
            }
        } else {
            checkEdge = true;
        }
        return checkEdge;
    }

    /**
     * Checks that the current edge has the expected start and end points
     * 
     * @param edgeEditPart
     *            the current edge swtbot edit part.
     * @param pointList
     *            the expected point list. The Start at 0 index and End at 1 index.
     * @param i
     *            the current action tested in labels order {top,bottom,left,right}
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

    /**
     * Check that edges linked to the moved border node are at the expected location.
     * 
     * @param edgeEditPart
     *            The current straighten edge
     * @param otherEdgeEditParts2ExpectedPointList
     *            the list where register the expected points, a point set to {-1, -1} is to ignore in the comparison
     */
    private void checkResultOfOtherEdges(SWTBotGefEditPart edgeEditPart, Map<TestPart, PointList> otherEdgeEditParts2ExpectedPointList) {
        for (Entry<TestPart, PointList> entry : otherEdgeEditParts2ExpectedPointList.entrySet()) {
            PolylineConnectionEx polylineConnection = entry.getKey().part.getPolylineConnectionFigure();
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (!IGNORED_POINT.equals(entry.getValue().getPoint(i))) {
                    assertEquals("The point \"" + i + "\" of edge \"" + getLabelFromEdgeEditPart(entry.getKey().part) + "\" is not valid.", entry.getValue().getPoint(i),
                            polylineConnection.getPoints().getPoint(i));
                }
            }
        }
    }

    private String getLabelFromEdgeEditPart(SWTBotGefEditPart edgeEditPart) {
        return getLabelFromEdgeEditPart((DEdgeEditPart) edgeEditPart.part());
    }

    private String getLabelFromEdgeEditPart(AbstractDiagramEdgeEditPart edgeEditPart) {
        ViewEdgeFigure figure = (ViewEdgeFigure) edgeEditPart.getFigure();
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
     *            the list where register the expected points, (Start at 0 and End at 1)
     * @param i
     *            the current action index in labels order {top,bottom,left,right} order
     */
    private void computeExpectedPoints(SWTBotGefEditPart edgeEditPart, List<Point> pointList, int i) {
        AbstractDiagramEdgeEditPart part = (AbstractDiagramEdgeEditPart) edgeEditPart.part();
        PolylineConnectionEx polylineConnection = part.getPolylineConnectionFigure();
        Point startPointBefore = polylineConnection.getStart();
        Point endPointBefore = polylineConnection.getEnd();
        // Specific case is when both first and last points move (edge centering
        // on both source and target border nodes)
        boolean specificCase = ((ViewEdgeFigure) part.getFigure()).isSourceCentered() && ((ViewEdgeFigure) part.getFigure()).isTargetCentered()
                && part.getSource() instanceof AbstractDiagramBorderNodeEditPart && part.getTarget() instanceof AbstractDiagramBorderNodeEditPart;

        Point expectedStartPoint;
        Point expectedEndPoint;
        if (specificCase) {
            Rectangle sourceBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((AbstractDiagramBorderNodeEditPart) part.getSource());
            Rectangle targetBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((AbstractDiagramBorderNodeEditPart) part.getTarget());
            expectedStartPoint = sourceBounds.getCenter();
            expectedEndPoint = targetBounds.getCenter();
        } else {
            expectedStartPoint = polylineConnection.getStart().getCopy();
            expectedEndPoint = polylineConnection.getEnd().getCopy();
        }
        pointList.add(expectedStartPoint);
        pointList.add(expectedEndPoint);
        switch (i) {
        // top
        case 0:
            if (startPointBefore.y <= endPointBefore.y) {
                expectedEndPoint.setY(expectedStartPoint.y);
            } else {
                expectedStartPoint.setY(expectedEndPoint.y);
            }
            if (specificCase) {
                expectedStartPoint.setX(startPointBefore.x());
                expectedEndPoint.setX(endPointBefore.x());
            }
            break;
        // bottom
        case 1:
            if (startPointBefore.y <= endPointBefore.y) {
                expectedStartPoint.setY(expectedEndPoint.y);
            } else {
                expectedEndPoint.setY(expectedStartPoint.y);
            }
            if (specificCase) {
                expectedStartPoint.setX(startPointBefore.x());
                expectedEndPoint.setX(endPointBefore.x());
            }
            break;
        // left side
        case 2:
            if (startPointBefore.x <= endPointBefore.x) {
                expectedEndPoint.setY(expectedStartPoint.y);
            } else {
                expectedStartPoint.setY(expectedEndPoint.y);
            }
            if (specificCase) {
                expectedStartPoint.setX(startPointBefore.x());
                expectedEndPoint.setX(endPointBefore.x());
            }
            break;
        // right side
        case 3:
            if (startPointBefore.x <= endPointBefore.x) {
                expectedStartPoint.setY(expectedEndPoint.y);
            } else {
                expectedEndPoint.setY(expectedStartPoint.y);
            }
            if (specificCase) {
                expectedStartPoint.setX(startPointBefore.x());
                expectedEndPoint.setX(endPointBefore.x());
            }
            break;
        // left
        case 4:
            if (startPointBefore.x <= endPointBefore.x) {
                expectedEndPoint.setX(expectedStartPoint.x);
            } else {
                expectedStartPoint.setX(expectedEndPoint.x);
            }
            if (specificCase) {
                expectedStartPoint.setY(startPointBefore.y());
                expectedEndPoint.setY(endPointBefore.y());
            }
            break;
        // right
        case 5:
            if (startPointBefore.x <= endPointBefore.x) {
                expectedStartPoint.setX(expectedEndPoint.x);
            } else {
                expectedEndPoint.setX(expectedStartPoint.x);
            }
            if (specificCase) {
                expectedStartPoint.setY(startPointBefore.y());
                expectedEndPoint.setY(endPointBefore.y());
            }

            break;
        // top side
        case 6:
            if (startPointBefore.y <= endPointBefore.y) {
                expectedEndPoint.setX(expectedStartPoint.x);
            } else {
                expectedStartPoint.setX(expectedEndPoint.x);
            }
            if (specificCase) {
                expectedStartPoint.setY(startPointBefore.y());
                expectedEndPoint.setY(endPointBefore.y());
            }
            break;
        // bottom side
        case 7:
            if (startPointBefore.y <= endPointBefore.y) {
                expectedStartPoint.setX(expectedEndPoint.x);
            } else {
                expectedEndPoint.setX(expectedStartPoint.x);
            }
            if (specificCase) {
                expectedStartPoint.setY(startPointBefore.y());
                expectedEndPoint.setY(endPointBefore.y());
            }
            break;
        default:
            break;
        }
    }

    /**
     * Compute the expected points for edges linked to the moved border node. The current straighten edge is not in the
     * returned list
     * 
     * @param edgeEditPart
     *            The current straighten edge
     * @param otherEdgeEditParts2ExpectedPointList
     *            the list where register the expected points, a point set to {-1, -1} is to ignore in the comparison
     * @param i
     *            the current action index in labels order {top,bottom,left,right} order
     */
    private void computeOtherEdgesExpectedPoints(SWTBotGefEditPart edgeEditPart, Map<TestPart, PointList> otherEdgeEditParts2ExpectedPointList, int i) {
        AbstractDiagramEdgeEditPart part = (AbstractDiagramEdgeEditPart) edgeEditPart.part();
        PolylineConnectionEx polylineConnection = part.getPolylineConnectionFigure();
        Point startPointBefore = polylineConnection.getStart();
        Point endPointBefore = polylineConnection.getEnd();
        EditPart sourceEditPart = part.getSource();
        EditPart targetEditPart = part.getTarget();

        switch (i) {
        // top
        case 0:
            if (startPointBefore.y <= endPointBefore.y) {
                if (targetEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                    computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) targetEditPart, part, otherEdgeEditParts2ExpectedPointList);
                }
            } else if (sourceEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) sourceEditPart, part, otherEdgeEditParts2ExpectedPointList);
            }
            break;
        // bottom
        case 1:
            if (startPointBefore.y <= endPointBefore.y) {
                if (sourceEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                    computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) sourceEditPart, part, otherEdgeEditParts2ExpectedPointList);
                }
            } else if (targetEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) targetEditPart, part, otherEdgeEditParts2ExpectedPointList);
            }

            break;
        // left
        case 2:
            if (startPointBefore.x <= endPointBefore.x) {
                if (targetEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                    computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) targetEditPart, part, otherEdgeEditParts2ExpectedPointList);
                }
            } else if (sourceEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) sourceEditPart, part, otherEdgeEditParts2ExpectedPointList);
            }
            break;
        // right
        case 3:
            if (startPointBefore.x <= endPointBefore.x) {
                if (sourceEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                    computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) sourceEditPart, part, otherEdgeEditParts2ExpectedPointList);
                }
            } else if (targetEditPart instanceof AbstractDiagramBorderNodeEditPart) {
                computeOtherEdgesExpectedPoints((AbstractDiagramBorderNodeEditPart) targetEditPart, part, otherEdgeEditParts2ExpectedPointList);
            }

            break;

        default:
            break;
        }
    }

    /**
     * Compute the expected points for edges linked to the moved border node. The current straighten edge is not in the
     * returned list
     * 
     * @param borderNodeEditPart
     *            The moved border node edit part
     * @param edgeEditPart
     *            The current straighten edge
     * @param otherEdgeEditParts2ExpectedPointList
     *            the list where register the expected points, a point set to {-1, -1} is to ignore in the comparison
     */
    private void computeOtherEdgesExpectedPoints(AbstractDiagramBorderNodeEditPart borderNodeEditPart, AbstractDiagramEdgeEditPart selectedEdgeEditPart,
            Map<TestPart, PointList> otherEdgeEditParts2ExpectedPointList) {
        for (AbstractDiagramEdgeEditPart edgeEditPart : Iterables.filter(borderNodeEditPart.getSourceConnections(), AbstractDiagramEdgeEditPart.class)) {
            if (!edgeEditPart.equals(selectedEdgeEditPart)) {
                PolylineConnectionEx polylineConnection = edgeEditPart.getPolylineConnectionFigure();
                PointList pointList = new PointList();
                pointList.addAll(polylineConnection.getPoints());
                if (new ConnectionEditPartQuery(edgeEditPart).isEdgeWithObliqueRoutingStyle()) {
                    // Ignore the first point
                    pointList.setPoint(IGNORED_POINT, 0);
                } else {
                    // Ignore the first 2 points
                    pointList.setPoint(IGNORED_POINT, 0);
                    pointList.setPoint(IGNORED_POINT, 1);
                }
                otherEdgeEditParts2ExpectedPointList.put(new TestPart(((DEdgeNameEditPart) edgeEditPart.getChildren().get(0)).getLabelText(), edgeEditPart), pointList);
            }
        }
        for (AbstractDiagramEdgeEditPart edgeEditPart : Iterables.filter(borderNodeEditPart.getTargetConnections(), AbstractDiagramEdgeEditPart.class)) {
            if (!edgeEditPart.equals(selectedEdgeEditPart)) {
                PolylineConnectionEx polylineConnection = edgeEditPart.getPolylineConnectionFigure();
                PointList pointList = new PointList();
                pointList.addAll(polylineConnection.getPoints());
                if (new ConnectionEditPartQuery(edgeEditPart).isEdgeWithObliqueRoutingStyle()) {
                    // Ignore the last point
                    pointList.setPoint(IGNORED_POINT, pointList.size() - 1);
                } else {
                    // Ignore the last 2 points
                    pointList.setPoint(IGNORED_POINT, pointList.size() - 1);
                    pointList.setPoint(IGNORED_POINT, pointList.size() - 2);
                }
                otherEdgeEditParts2ExpectedPointList.put(
                        new TestPart(((DEdgeNameEditPart) edgeEditPart.getChildren().stream().filter(DEdgeNameEditPart.class::isInstance).findFirst().get()).getLabelText(), edgeEditPart), pointList);
            }
        }
    }
}
