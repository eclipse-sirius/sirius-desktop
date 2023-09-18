/*******************************************************************************
 * Copyright (c) 2015, 2023 THALES GLOBAL SERVICES and others.
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

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.draw2d.geometry.Vector;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg.KeyPoint;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import junit.framework.AssertionFailedError;

/**
 * Check the position of edge labels when modifying the edge.
 *
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class EdgeLabelsMoveFromEdgeMoveTest extends AbstractSiriusSwtBotGefTestCase {
    /**
     * This class customizes the test behavior of {@link CheckEditPartMoved}. It
     * allows to fail instantly if an exception has been logged and the part has
     * still not been moved. This should be used only if you know the test will
     * fail if any exception occurs during its execution. This check must be
     * combined with setErrorCatchActive(true) to fail with the exception
     * information. If not the test will always be successful in case of
     * exception.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    class SpecificCheckEditPartMoved extends CheckEditPartMoved {

        /**
         * Default Constructor.
         * 
         * @param editPartBot
         *            bot to check if moved
         */
        public SpecificCheckEditPartMoved(SWTBotGefEditPart editPartBot) {
            super(editPartBot);
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.sirius.tests.swtbot.support.api.condition.
         * CheckEditPartMoved#test()
         */
        @Override
        public boolean test() throws Exception {
            boolean result = super.test();
            if (!result) {
                result = doesAnErrorOccurs();
            }
            return result;
        }

    }

    /**
     * The name of the diagram representation using imbricated edges.
     */
    private static final String DIAGRAM_WITH_NESTED_EDGES_REPRESENTATION_NAME = "DiagramWithNestedEdges";

    /**
     * Constant use to indicate that the delta is not predictable and must be
     * computed from the initial location with a ratio respect.
     */
    private static final Dimension DELTA_TO_COMPUTE_FROM_RATIO = new Dimension(-1000, -1000);

    /**
     * Constant use to indicate that the delta is not predictable and must be
     * computed from the initial location with a ratio respect (the segment is
     * inverted so the ratio must be applied specifically).
     */
    private static final Dimension DELTA_TO_COMPUTE_FROM_INVERTED_RATIO = new Dimension(-2000, -2000);

    /**
     * Constant use to indicate that the delta is not predictable and must be
     * computed from the initial location with a ratio respect (the segment is
     * rotated so the ratio must be applied specifically). This is possible only
     * for bracket edge.<BR>
     * WARNING: Only the case of rotation to the right (from horizontal to
     * vertical is handle in this test). The other computation is to complicated
     * and implied to do the same as in
     * EdgeLabelsComputationUtil.applyOldRatioOnNewOrthogonalSegment() method.
     */
    private static final Dimension DELTA_TO_COMPUTE_FROM_ROTATED_RATIO = new Dimension(-3000, -3000);

    /**
     * Constant use to indicate that the delta is not predictable and must be
     * computed from standard location of label locator.
     */
    private static final Dimension DELTA_TO_COMPUTE_FROM_STANDARD = new Dimension(-4000, -4000);

    private static final String PROPERTIES_VIEW_ID = "org.eclipse.ui.views.PropertySheet";

    private Set<AssertionFailedError> failures = new LinkedHashSet<>();

    private SWTBotSiriusDiagramEditor diagramEditor;

    private static final String DATA_UNIT_DIR = "data/unit/edgeLabelMove/";

    private static final String MODEL = "edgeLabelsMoveTest.ecore";

    private static final String SESSION_FILE = "edgeLabelsMoveTest.aird";

    private static final String VSM = "VSMForEdgeLabelsMoveTest.odesign";

    private static final String DIAGRAM_DESCRIPTION_NAME = "Diagram";

    boolean isOutlineViewOpened;

    boolean isPropertiesViewOpened;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        // Close outline & property view (to improve test performances)
        final IWorkbenchPage currentPage = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0];
        final IViewReference[] viewReferences = currentPage.getViewReferences();
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < viewReferences.length; i++) {
                    if ("org.eclipse.ui.views.ContentOutline".equals(viewReferences[i].getId())) {
                        isOutlineViewOpened = true;
                        currentPage.hideView(viewReferences[i]);
                    } else if (PROPERTIES_VIEW_ID.equals(viewReferences[i].getId())) {
                        isPropertiesViewOpened = true;
                        currentPage.hideView(viewReferences[i]);
                    }
                }
            }
        });
    }

    @Override
    protected void tearDown() throws Exception {
        if (isOutlineViewOpened) {
            designerViews.openOutlineView();
        }
        if (isPropertiesViewOpened) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    try {
                        PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0].showView(PROPERTIES_VIEW_ID);
                    } catch (PartInitException e) {
                        fail("Could not reopen property view during teardown : " + e.getMessage());
                    }
                }
            });
        }
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Add specific test for bracket edges.
     */
    public void testLabelStabilityOnBracketEdges() {
        String diagramDescriptionName = "DiagramWithBracketEdge";
        String diagramName = "diagramWithBracketEdge";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();
        // Move down the middle segment
        edgeLabelExpectedPosition.put("refToC1BBegin", new Dimension(0, 30));
        edgeLabelExpectedPosition.put("refToC1BCenter", new Dimension(0, 30));
        edgeLabelExpectedPosition.put("refToC1BEnd", new Dimension(0, 0));
        doTestMoveSegment(diagramDescriptionName, diagramName, Arrays.asList(new Point(0, 30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1A", "C1B", 2);

        // Move down the middle
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToC1BBegin", new Dimension(0, -30));
        edgeLabelExpectedPosition.put("refToC1BCenter", new Dimension(0, -30));
        edgeLabelExpectedPosition.put("refToC1BEnd", new Dimension(0, 0));
        doTestMoveSegment(diagramDescriptionName, diagramName, Arrays.asList(new Point(0, -30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1A", "C1B", 2);

        // Move down, to the opposite side, the middle segment
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToC1BBegin", new Dimension(0, 500));
        edgeLabelExpectedPosition.put("refToC1BCenter", new Dimension(0, 500));
        edgeLabelExpectedPosition.put("refToC1BEnd", DELTA_TO_COMPUTE_FROM_INVERTED_RATIO);
        doTestMoveSegment(diagramDescriptionName, diagramName, Arrays.asList(new Point(0, 500)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1A", "C1B", 2, true);

        // Change orientation of the middle segment
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToC1BBegin", DELTA_TO_COMPUTE_FROM_ROTATED_RATIO);
        edgeLabelExpectedPosition.put("refToC1BCenter", DELTA_TO_COMPUTE_FROM_ROTATED_RATIO);
        doTestMoveSegment(diagramDescriptionName, diagramName, Arrays.asList(new Point(500, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1A", "C1B", 2);

        // Move source node moves also the "middle segment"
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToC1BBegin", new Dimension(0, 200));
        edgeLabelExpectedPosition.put("refToC1BCenter", new Dimension(0, 200));
        edgeLabelExpectedPosition.put("refToC1BEnd", DELTA_TO_COMPUTE_FROM_INVERTED_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramDescriptionName, diagramName, Arrays.asList(new Point(50, 200)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1A");

        // Move target node does not move the "middle segment"
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToC1BBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToC1BCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToC1BEnd", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramDescriptionName, diagramName, Arrays.asList(new Point(50, -200)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1B");

        logFailures();
    }

    /**
     * Ensures that when moving the source/target node of the edge, the labels
     * of this edges are correctly located in various configuration (zoom level,
     * scrollbars, initial position of the label, node move style, ...).
     */
    public void testLabelStabilityWhenMovingNodeOfRectilinearEdge() {

        // There are two nodes connected by an edge with 3 segments with
        // RECTILINEAR STYLE
        // Segments are horizontal,vertical and horizontal
        String diagramName = "EdgeWith3SegmentsHVH";
        // Part 1: Case Rectilinear C and D in spec -the ref point is on the 2nd
        // segment
        // test when label anchor remains on 2nd segment
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-10, 0), new Point(10, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_125,
                "B");

        // test when label anchor moves from 2nd segment to 3rd segment
        // the label should not move
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(100, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_50, "B");

        // test when label anchor moves from 2nd segment to 1st segment
        // the label should not move
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-30, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_50, "B");

        // test when Both begin and end nodes are moved
        // the label should not move as the edge position is relative to both
        // nodes
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(150 * 2, 150 * 2));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(150, 150)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_50, "A", "B");

        // Case Rectilinear E in spec - test when the reference point is no
        // longer on the segment. the new location is computed from a ratio.
        edgeLabelExpectedPosition.put("refToBCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, -49)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "B");

        // Part 2: the ref point is on the 3nd segment
        // test enlarging/reducing the 3nd segment
        // the label should not be moved
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-10, 0), new Point(10, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100,
                "D");

        // test moving node up
        // the label should be moved with the same node move
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, -10));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, -10)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // test when the reference point is no longer on the segment
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-54, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // Case Rectilinear F in spec - test when the reference segment is not
        // existing any more the label position should be reinitialized (reset
        // to std location)
        edgeLabelExpectedPosition.put("refToDBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_STANDARD);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-102, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // test when reference segment is merged (The reference point is on a
        // segment that is removed but merged with another one: The label is
        // moved with the same delta (to keep the same vector).
        // The delta is not the same as the segment is straightened (see
        // SetConnectionBendpointsAccordingToExtremityMoveCommmand#adaptPointListAndRefPoints).
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, -65));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, -66)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // Part 3: simple test on start and end edge label to check that
        // algorithm is also applied on these edge label
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 30));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(30, 30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A");

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(80, 30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A");

        // Part 4: the ref point is not on a segment
        // test enlarging/reducing the 3nd segment
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDEnd", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, -10)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, 200)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "A");

        // Move a node with result that the segment is "reversed", the label on
        // the corresponding segment should keep the same ratio and the others
        // label should not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", DELTA_TO_COMPUTE_FROM_INVERTED_RATIO);
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(350, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "A");

        logFailures();
    }

    /**
     * Ensures that moving the target node of the edge updates correctly the
     * middle label in various configuration (zoom level, scrollbars, initial
     * position of the label, node move).
     */
    public void testLabelStabilityWhenMovingSegmentOnRectilinearEdge() {

        // There are two nodes connected by an edge with 3 segments with
        // RECTILINEAR STYLE
        // Segments are horizontal,vertical and horizontal
        String diagramName = "EdgeWith3SegmentsHVH";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();

        // Case Rectilinear A, B and B' of the spec - The reference point is on
        // a segment
        // that is not impacted by the move of another segment --> The label
        // does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveSegment(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, 20), new Point(0, -10), new Point(0, -40)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B",
                2);

        // Case Rectilinear G of the spec - The reference point is on a segment
        // that is merged by the move of another segment --> The label does not
        // move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        doTestMoveSegment(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, -67)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 2);

        // Move vertical segment, the start and end label should not move
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveSegment(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-20, 0), new Point(20, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1);

        // Move vertical segment, the start label (outside of the edge bounds)
        // should not move
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToFBegin", new Dimension(0, 0));
        doTestMoveSegment(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(20, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "E", "F", 1);

        logFailures();
    }

    /**
     * Add specific test for edge linking nodes contained in container.
     */
    public void testLabelMoveForEdgeInContainer() {
        String diagramName = "EdgeWith3SegmentsHVH";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();
        edgeLabelExpectedPosition.put("refToC1BBegin", new Dimension(0, -31));
        edgeLabelExpectedPosition.put("refToC1BCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToC1BEnd", new Dimension(0, 0));
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(-5, -30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1A", "C1B", 0, true);

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToC1BBegin", new Dimension(0, 0));
        doTestMoveSegment(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, 20), new Point(0, -10), new Point(0, -40)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C1A",
                "C1B", 2);

        logFailures();
    }

    /**
     * Tests that when a node that is attached to an edge with a length of 0 is
     * moved, then no {@link ArithmeticException} occurs preventing it to move.
     * 
     * See 485010.
     */
    public void testEdgeWithZeroLengthMoveByTargetMove() {
        String diagramName = DIAGRAM_WITH_NESTED_EDGES_REPRESENTATION_NAME;

        diagramEditor = setUpEditorAccordingToDimensions(DIAGRAM_WITH_NESTED_EDGES_REPRESENTATION_NAME, diagramName, ZoomLevel.ZOOM_100);
        SWTBotGefEditPart targetEditPart = diagramEditor.getEditPart("F", IAbstractDiagramNodeEditPart.class);
        diagramEditor.scrollTo(0, 0);
        diagramEditor.reveal(targetEditPart.part());
        diagramEditor.select(targetEditPart);

        diagramEditor.getBounds(targetEditPart).getCenter();

        SpecificCheckEditPartMoved checkEditPartMoved = new SpecificCheckEditPartMoved(targetEditPart);
        diagramEditor.drag(targetEditPart, 0, 0);
        bot.waitUntil(checkEditPartMoved);
    }

    /**
     * Ensures that when moving a point of a rectilinear edge, the labels of
     * this edges are correctly located in various configuration (zoom level,
     * scrollbars, initial position of the label, node move style, ...).
     */
    public void testLabelStabilityWhenMovingPointOnRectilinearEdge() {
        // There are two nodes connected by an edge with 3 segments with
        // RECTILINEAR STYLE
        // Segments are horizontal,vertical and horizontal
        String diagramName = "EdgeWith3SegmentsHVH";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();

        // Case Rectilinear G of the spec - The reference point is on a segment
        // that is removed but merged with another one --> The label is moved
        // with the same delta (to keep the same vector).
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, -30));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(-5, -30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 0);

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 30));
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(5, 30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 3);

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, -15));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(15, 0));
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(15, -15)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1);

        // Case Rectilinear E of the spec - The reference point is on a segment
        // that is resized: reduced and the reference point is no longer on the
        // segment --> A new reference point is computed by keeping the same
        // ratio of the original reference point according to the length of the
        // segment.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(0, 33)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1);

        logFailures();
    }

    /**
     * Ensures that when moving the source/target node of the edge, the labels
     * of this edges are correctly located in various configuration (zoom level,
     * scrollbars, initial position of the label, node move style, ...).
     */
    public void testLabelStabilityWhenMovingNodeOfObliqueEdge() {
        // There are two nodes connected by an edge with 3 segments with
        // OBLIQUE STYLE
        String diagramName = "EdgeWithObliqueSegments";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();
        // edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(35, 70)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "A");

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(100, 100)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, false, "B");

        // Case Oblique C of the spec - The reference point is on a segment that
        // is resized: enlarged and the reference point is always on the segment
        // --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(100, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, false, "D");

        // Case Oblique D of the spec - The reference point is on a segment that
        // is resized: reduced and the reference point is always on the segment
        // --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-30, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, false, "D");

        // Case Oblique E of the spec - The reference point is on a segment that
        // is resized: reduced and the reference point is no longer on the
        // segment --> A new reference point is computed by keeping the same
        // ratio of the original reference point according to the length of the
        // segment.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-50, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, false, "D");

        logFailures();
    }

    /**
     * Ensures that when moving the source/target node of the edge, the labels
     * of this edges are correctly located in various configuration (zoom level,
     * scroll bars, initial position of the label, node move style, ...). This
     * method corresponds to a specific case where a bug has been detected
     * (short path for the only one segment and a label outside of the segment).
     */
    public void testLabelStabilityWhenMovingNodeOfObliqueEdgeWithShortSegmentPath() {
        // There are two nodes connected by an edge with 3 segments with
        // OBLIQUE STYLE
        String diagramName = "DiagramWithSmallObliqueEdge";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();

        // If the edge is moved "away", the label is "reseted" to its default
        // location. This case corresponds to an old ratio less than 0 or more
        // than 1 (label outside of the edge) and the new ratio is higher than
        // the old.
        edgeLabelExpectedPosition.put("toMCenter", DELTA_TO_COMPUTE_FROM_STANDARD);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(700, 250)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "L");

        // Same as above but more closer
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("toMCenter", DELTA_TO_COMPUTE_FROM_STANDARD);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-150, 150)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "L");

        // Same as above but with same old ratio on new segment
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("toMCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-150, 125)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "L");

        // If the edge is "reverted" on the same line, the ratio is applied.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("toMCenter", DELTA_TO_COMPUTE_FROM_INVERTED_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(-300, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "L");

        logFailures();
    }

    /**
     * Log the failures if <code>failures</code> is not empty.
     * 
     * @throws AssertionFailedError
     *             In case of problem
     */
    protected void logFailures() throws AssertionFailedError {
        if (!failures.isEmpty()) {
            String message = "";
            for (AssertionFailedError assertion : failures) {
                message = message + "\n" + assertion.getMessage();
            }

            throw new AssertionFailedError(failures.size() + " failures found : " + message);
        }
    }

    /**
     * Ensures that when moving a node several time of 10 by 10 pixels, the
     * labels of this edges stays stable.
     */
    public void testLabelStabilityWhenMovingNodeOfObliqueEdgeSeveralTimes() {
        String diagramName = "EdgeWithObliqueSegments";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 1));
        Point moveDelta = new Point(10, 10);

        // Step 1: Open the corresponding diagram
        diagramEditor = setUpEditorAccordingToDimensions(diagramName, ZoomLevel.ZOOM_100);
        Collection<SWTBotGefEditPart> editPartsToMove = new LinkedHashSet<>();
        SWTBotGefEditPart nodeEditPart = diagramEditor.getEditPart("B", IAbstractDiagramNodeEditPart.class);
        editPartsToMove.add(nodeEditPart);

        // Compute expected label locations
        SWTBotGefEditPart firstEditPartToMove = editPartsToMove.iterator().next();
        Connection figure;
        if (firstEditPartToMove.sourceConnections().size() > 0) {
            figure = (Connection) firstEditPartToMove.sourceConnections().get(0).part().getFigure();
        } else {
            figure = (Connection) firstEditPartToMove.targetConnections().get(0).part().getFigure();
        }
        Map<String, LabelPositionData> expectedlLabelPositions = computeExpectedLabelLocations(edgeLabelExpectedPosition, figure);

        try {
            // Step 4: Drag nodes and check edge label position
            SWTBotGefEditPart firstPartToMove = editPartsToMove.iterator().next();
            diagramEditor.select(editPartsToMove);
            diagramEditor.reveal(firstPartToMove.part());
            Point initialLocation = diagramEditor.getBounds(firstPartToMove).getCenter();
            // Drag the node 9x before calling the classical
            // "doPerformMoveAndCheckEdgeLabels" method
            for (int i = 0; i < 10; i++) {
                Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);
                // Perform drag
                final AtomicBoolean dragFinished = new AtomicBoolean(false);
                diagramEditor.dragWithKey(initialLocation.x, initialLocation.y, targetLocation.x, targetLocation.y, SWT.None, dragFinished);
                // Wait that the drag is done (the async Runnable simulating the
                // drag)
                bot.waitUntil(new ICondition() {

                    @Override
                    public boolean test() throws Exception {
                        return dragFinished.get();
                    }

                    @Override
                    public void init(SWTBot bot) {
                    }

                    @Override
                    public String getFailureMessage() {
                        return "The drag'n'drop operation has not finished.";
                    }
                });
                // Wait that the figures are redrawn. In a fast environment,
                // figures are not really redrawn and the rest of the test is
                // not reliable.
                SWTBotUtils.waitAllUiEvents();
                initialLocation = targetLocation;
            }

            doPerformMoveAndCheckEdgeLabels(diagramEditor, initialLocation, moveDelta, expectedlLabelPositions, "Move node:B-ZoomLevel: " + ZoomLevel.ZOOM_100 + ", MoveDelta: " + moveDelta + "-",
                    false);
        } catch (AssertionError e) {
            failures.add(new AssertionFailedError(e.getMessage()));
        }

        diagramEditor.close();
        SWTBotUtils.waitAllUiEvents();

        logFailures();
    }

    /**
     * Ensures that when moving a point of an oblique edge, the labels of this
     * edges are correctly located in various configuration (zoom level,
     * scrollbars, initial position of the label, node move style, ...).
     */
    public void testLabelStabilityWhenMovingPointOnObliqueEdge() {
        // There are two nodes connected by an edge with 3 segments with
        // OBLIQUE STYLE
        String diagramName = "EdgeWithObliqueSegments";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();

        // Case Oblique A of the spec - The reference point is on a segment that
        // is not impacted by the move --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(0, 50)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1, true);

        // Case Oblique B of the spec - The reference point is on a segment that
        // is not impacted by the move --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBEnd", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(0, 22)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 3, true);

        // Case Oblique F of the spec - The reference point is on a segment that
        // is removed --> The label is reset to standard location.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_STANDARD);
        edgeLabelExpectedPosition.put("refToDEnd", DELTA_TO_COMPUTE_FROM_STANDARD);
        doTestMovePointOnEdge(diagramName, Arrays.asList(new Point(0, -123)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C", "D", 2, true);

        // Case Oblique G of the spec - The reference point is on a segment that
        // is splitted into two segments --> The label is reset to standard
        // location.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBEnd", DELTA_TO_COMPUTE_FROM_STANDARD);
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(1, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveSegment(DIAGRAM_DESCRIPTION_NAME, diagramName, Arrays.asList(new Point(0, -100)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 2, false);

        logFailures();
    }

    /**
     * Ensures that by dragging or arranging edit parts with the given names,
     * bendpoints are moved as expected.
     *
     * @param diagramDescriptionName
     *            diagram description name
     * @param diagramName
     *            diagram name
     * @param moveDeltas
     *            all the move deltas to use for dragging elements.
     * @param edgeLabelDeltas
     *            The expected move delta for each label. The constants
     *            DELTA_TO* can be used instead of a precise mode delta. In this
     *            case, the delta is computed dynamically.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param nodesToMove
     *            name of the Edit parts to move
     */
    private void doTestMoveNodesOnlyMovesFirstOrLastBendpoint(String diagramDescriptionName, String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas,
            ZoomLevel zoomLevel, String... nodesToMove) {
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramDescriptionName, diagramName, moveDeltas, edgeLabelDeltas, zoomLevel, false, nodesToMove);
    }

    /**
     * Ensures that by dragging or arranging edit parts with the given names,
     * bendpoints are moved as expected.
     *
     * @param diagramDescriptionName
     *            diagram description name
     * @param diagramName
     *            diagram name
     * @param moveDeltas
     *            all the move deltas to use for dragging elements.
     * @param edgeLabelDeltas
     *            The expected move delta for each label. The constants
     *            DELTA_TO* can be used instead of a precise mode delta. In this
     *            case, the delta is computed dynamically.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param isToleranceAccepted
     *            true if the expected point can be imprecise (this is the case
     *            for oblique edges)
     * @param nodesToMove
     *            name of the Edit parts to move
     */
    private void doTestMoveNodesOnlyMovesFirstOrLastBendpoint(String diagramDescriptionName, String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas,
            ZoomLevel zoomLevel, boolean isToleranceAccepted, String... nodesToMove) {
        for (Point moveDelta : moveDeltas) {
            // Step 1: Open the corresponding diagram
            diagramEditor = setUpEditorAccordingToDimensions(diagramDescriptionName, diagramName, zoomLevel);
            Collection<SWTBotGefEditPart> editPartsToMove = new LinkedHashSet<>();
            for (String nodeToMove : nodesToMove) {
                SWTBotGefEditPart nodeEditPart = diagramEditor.getEditPart(nodeToMove, IAbstractDiagramNodeEditPart.class);
                editPartsToMove.add(nodeEditPart);
            }

            // Compute expected label locations
            SWTBotGefEditPart firstEditPartToMove = editPartsToMove.iterator().next();
            Connection figure;
            if (firstEditPartToMove.sourceConnections().size() > 0) {
                figure = (Connection) firstEditPartToMove.sourceConnections().get(0).part().getFigure();
            } else {
                figure = (Connection) firstEditPartToMove.targetConnections().get(0).part().getFigure();
            }
            Map<String, LabelPositionData> expectedlLabelPositions = computeExpectedLabelLocations(edgeLabelDeltas, figure);

            try {
                // Step 4: Drag nodes and check edge label position
                doPerformMoveAndCheckEdgeLabels(diagramEditor, editPartsToMove, moveDelta, expectedlLabelPositions,
                        "Move node:" + nodesToMove[0] + "-ZoomLevel: " + zoomLevel + ", MoveDelta: " + moveDelta + "-", isToleranceAccepted);
            } catch (AssertionError e) {
                failures.add(new AssertionFailedError(e.getMessage()));
            }

        }
        diagramEditor.close();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Ensures that by moving a segment of on edge, the labels are moved as
     * expected.
     *
     * @param diagramDescriptionName
     *            The diagram description name
     * @param diagramName
     *            diagram name
     * @param moveDeltas
     *            all the move deltas to use for moving segment.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param edgeLabelDeltas
     *            name of one of the label of the edge edit part to move
     * @param segmentIndex
     *            The index of the segment of the edge to move (0 for the first
     *            segment)
     */
    private void doTestMoveSegment(String diagramDescriptionName, String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel, String sourceName,
            String targetName, int segmentIndex) {
        doTestMoveSegment(diagramDescriptionName, diagramName, moveDeltas, edgeLabelDeltas, zoomLevel, sourceName, targetName, segmentIndex, false);
    }

    /**
     * Ensures that by moving a segment of on edge, the labels are moved as
     * expected.
     *
     * @param diagramDescriptionName
     *            The diagram description name
     * @param diagramName
     *            diagram name
     * @param moveDeltas
     *            all the move deltas to use for moving segment.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param edgeLabelDeltas
     *            name of one of the label of the edge edit part to move
     * @param segmentIndex
     *            The index of the segment of the edge to move (0 for the first
     *            segment)
     * @param isToleranceAccepted
     *            true if the expected point can be imprecise (this is the case
     *            for oblique edges)
     */
    private void doTestMoveSegment(String diagramDescriptionName, String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel, String sourceName,
            String targetName, int segmentIndex, boolean isToleranceAccepted) {
        for (Point moveDelta : moveDeltas) {
            // Step 1: Open the corresponding diagram
            diagramEditor = setUpEditorAccordingToDimensions(diagramDescriptionName, diagramName, zoomLevel);
            SWTBotGefEditPart sourceEditPart = diagramEditor.getEditPart(sourceName, IAbstractDiagramNodeEditPart.class);
            SWTBotGefEditPart targetEditPart = diagramEditor.getEditPart(targetName, IAbstractDiagramNodeEditPart.class);
            SWTBotGefConnectionEditPart edgeEditPart = diagramEditor.getConnectionEditPart(sourceEditPart, targetEditPart).get(0);
            diagramEditor.scrollTo(0, 0);
            diagramEditor.reveal(edgeEditPart.part());
            diagramEditor.select(edgeEditPart);
            Connection figure = (Connection) edgeEditPart.part().getFigure();
            PointList pointList = figure.getPoints();
            List<LineSeg> edgeSegments = PointListUtilities.getLineSegments(pointList);
            LineSeg lineSegToMove = edgeSegments.get(segmentIndex);
            Point initialLocation = new Point();
            lineSegToMove.pointOn(0, KeyPoint.MIDPOINT, initialLocation);
            // This initial location must be shift according to scrollbar
            figure.translateToAbsolute(initialLocation);

            // Compute expected label locations
            Map<String, LabelPositionData> expectedlLabelPositions = computeExpectedLabelLocations(edgeLabelDeltas, figure);

            try {
                // Step 4: Drag nodes and check edge label position
                doPerformMoveAndCheckEdgeLabels(diagramEditor, initialLocation, moveDelta, expectedlLabelPositions,
                        "Move segment n°:" + segmentIndex + "-ZoomLevel: " + zoomLevel + ", MoveDelta: " + moveDelta + "-", isToleranceAccepted);
            } catch (AssertionError e) {
                failures.add(new AssertionFailedError(e.getMessage()));
            }
        }
        diagramEditor.restore();
        diagramEditor.close();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Ensures that by moving a point of on edge, the labels are moved as
     * expected.
     *
     * @param diagramName
     *            diagram name
     * @param moveDeltas
     *            all the move deltas to use for moving segment.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param labelOfTheEdgeToMove
     *            name of one of the label of the edge edit part to move
     * @param index
     *            The index of the point of the edge to move (0 for the first
     *            point)
     */
    private void doTestMovePointOnEdge(String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel, String sourceName, String targetName, int index) {
        doTestMovePointOnEdge(diagramName, moveDeltas, edgeLabelDeltas, zoomLevel, sourceName, targetName, index, false);
    }

    /**
     * Ensures that by moving a point of on edge, the labels are moved as
     * expected.
     *
     * @param diagramName
     *            diagram name
     * @param moveDeltas
     *            all the move deltas to use for moving segment.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param labelOfTheEdgeToMove
     *            name of one of the label of the edge edit part to move
     * @param index
     *            The index of the point of the edge to move (0 for the first
     *            point)
     * @param isToleranceAccepted
     *            true if the expected point can be imprecise (this is the case
     *            for oblique edges)
     */
    private void doTestMovePointOnEdge(String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel, String sourceName, String targetName, int index,
            boolean isToleranceAccepted) {
        for (Point moveDelta : moveDeltas) {
            // Step 1: Open the corresponding diagram
            diagramEditor = setUpEditorAccordingToDimensions(diagramName, zoomLevel);
            SWTBotGefEditPart sourceEditPart = diagramEditor.getEditPart(sourceName, IAbstractDiagramNodeEditPart.class);
            SWTBotGefEditPart targetEditPart = diagramEditor.getEditPart(targetName, IAbstractDiagramNodeEditPart.class);
            SWTBotGefConnectionEditPart edgeEditPart = diagramEditor.getConnectionEditPart(sourceEditPart, targetEditPart).get(0);
            diagramEditor.scrollTo(0, 0);
            diagramEditor.reveal(edgeEditPart.part());
            diagramEditor.select(edgeEditPart);
            Connection figure = (Connection) edgeEditPart.part().getFigure();
            Point initialLocation = figure.getPoints().getPoint(index);
            // This initial location must be shift according to scrollbar
            figure.translateToAbsolute(initialLocation);

            // Compute expected label locations
            Map<String, LabelPositionData> expectedlLabelPositions = computeExpectedLabelLocations(edgeLabelDeltas, figure);
            try {
                // Step 4: Drag nodes and check edge label position
                doPerformMoveAndCheckEdgeLabels(diagramEditor, initialLocation, moveDelta, expectedlLabelPositions,
                        "Move point n°:" + index + "-ZoomLevel: " + zoomLevel + ", MoveDelta: " + moveDelta + "-", isToleranceAccepted);
            } catch (AssertionError e) {
                failures.add(new AssertionFailedError(e.getMessage()));
            }
        }
        diagramEditor.close();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * @param edgeLabelDeltas
     * @param figure
     * @return
     */
    private Map<String, LabelPositionData> computeExpectedLabelLocations(Map<String, Dimension> edgeLabelDeltas, Connection figure) {
        Map<String, LabelPositionData> expectedLabelPositions = new HashMap<>();
        for (Entry<String, Dimension> labelNameToDelta : edgeLabelDeltas.entrySet()) {
            SWTBotGefEditPart labelEditPart = diagramEditor.getEditPart(labelNameToDelta.getKey(), AbstractDEdgeNameEditPart.class);
            Rectangle labelBounds = ((AbstractGraphicalEditPart) labelEditPart.part()).getFigure().getBounds();
            Point initialLabelLocation = labelBounds.getLocation();
            Dimension expectedDelta = labelNameToDelta.getValue();
            if (DELTA_TO_COMPUTE_FROM_STANDARD.equals(expectedDelta)) {
                expectedLabelPositions.put(labelNameToDelta.getKey(), new LabelPositionData(true));
            } else if (!DELTA_TO_COMPUTE_FROM_RATIO.equals(expectedDelta) && !DELTA_TO_COMPUTE_FROM_INVERTED_RATIO.equals(expectedDelta)
                    && !DELTA_TO_COMPUTE_FROM_ROTATED_RATIO.equals(expectedDelta)) {
                // Normal case, translated by delta
                Point expectedLabelPosition = initialLabelLocation.getTranslated(expectedDelta);
                expectedLabelPositions.put(labelNameToDelta.getKey(), new LabelPositionData(expectedLabelPosition));
            } else {
                // Compute ratio of current reference point
                Point labelCenter = labelBounds.getCenter();
                @SuppressWarnings("rawtypes")
                List segments = PointListUtilities.getLineSegments(figure.getPoints());
                LineSeg nearestSegment = PointListUtilities.getNearestSegment(segments, labelCenter.x, labelCenter.y);
                Point nearestPointOnSegment = nearestSegment.perpIntersect(labelCenter.x, labelCenter.y);
                float ratio = nearestSegment.distanceAlong(nearestPointOnSegment);
                // Compute vector from current reference point to current
                // center of label
                Vector vector = new Vector(new PrecisionPoint(nearestPointOnSegment), new PrecisionPoint(labelCenter));
                if (DELTA_TO_COMPUTE_FROM_ROTATED_RATIO.equals(expectedDelta)) {
                    // Rotate the vector according to the expected rotation.
                    // WARNING: Only the case of rotation to the right (from
                    // horizontal to vertical is handle in this test). The other
                    // computation is to complicated and implied to do the same
                    // as in
                    // EdgeLabelsComputationUtil.applyOldRatioOnNewOrthogonalSegment()
                    // method.
                    Transform rotateTransform = new Transform();
                    rotateTransform.setRotation(Math.toRadians(90));
                    Point rotatedPoint = rotateTransform.getTransformed(vector.toPoint());
                    rotatedPoint.translate((labelBounds.width - labelBounds.height) / 2, 0);
                    vector = new Vector(rotatedPoint.x, rotatedPoint.y);
                }
                expectedLabelPositions.put(labelNameToDelta.getKey(), new LabelPositionData(segments.indexOf(nearestSegment), ratio, vector));
            }
        }
        return expectedLabelPositions;
    }

    /**
     * Class containing data to retrieve label location. Either:
     * <UL>
     * <LI>directly the expected label position</LI>
     * <LI>or the (ratio, vector and segmentIndex) that allows to retrieve the
     * new location from the new edge figure</LI>
     * <LI>or a boolean (standardLocationExpected) that is set to true to
     * compute the default location</LI>
     * </UL>
     *
     * @author lredor
     *
     */
    public class LabelPositionData {
        Point expectedLabelPosition;

        boolean standardLocationExpected;

        int segmentIndex;

        float ratio;

        Vector vector;

        // boolean invertedRatio;

        /**
         * Constructor that sets directly the expected label position.
         *
         * @param expectedLabelPosition
         *            the expected label position
         */
        public LabelPositionData(Point expectedLabelPosition) {
            this.expectedLabelPosition = expectedLabelPosition;
        }

        /**
         * Constructor used when the expected label position depends on the new
         * edge figure.
         *
         * @param segmentIndex
         *            The index of the nearest segment.
         * @param ratio
         *            The ratio of the reference point on the segment
         * @param vector
         *            The vector from this reference point to have the middle of
         *            the label
         */
        public LabelPositionData(int segmentIndex, float ratio, Vector vector) {
            this.segmentIndex = segmentIndex;
            this.ratio = ratio;
            this.vector = vector;
        }

        /**
         * Constructor used when the expected label position should be at its
         * standard location.
         *
         * @param standardLocationExpected
         *            true if the standard location must be used, false
         *            otherwise.
         */
        public LabelPositionData(boolean standardLocationExpected) {
            this.standardLocationExpected = standardLocationExpected;
        }
    }

    /**
     * Move specified <code>editParts</code> with the specified
     * <code>moveDelta</code> and check that the labels have been correctly move
     * according to <code>expectedLabelDeltas</code>.
     *
     * @param diagramEditor
     *            the currently active {@link SWTBotSiriusDiagramEditor}
     * @param editPartsToMove
     *            the editParts to drag
     * @param moveDelta
     *            the delta of the move.
     * @param expectedLabelLocations
     *            the expected locations of the edgeLabel
     * @param errorMessagePrefix
     *            The prefix of the message in case of error.
     * @param isToleranceAccepted
     *            true if the expected point can be imprecise (this is the case
     *            for oblique edges)
     */
    private void doPerformMoveAndCheckEdgeLabels(SWTBotSiriusDiagramEditor diagramEditor, Collection<SWTBotGefEditPart> editPartsToMove, Point moveDelta,
            Map<String, LabelPositionData> expectedLabelLocations, String errorMessagePrefix, boolean isToleranceAccepted) {
        SWTBotGefEditPart firstPartToMove = editPartsToMove.iterator().next();
        diagramEditor.select(editPartsToMove);
        diagramEditor.reveal(firstPartToMove.part());
        Point initialLocation = diagramEditor.getBounds(firstPartToMove).getCenter();
        doPerformMoveAndCheckEdgeLabels(diagramEditor, initialLocation, moveDelta, expectedLabelLocations, errorMessagePrefix, isToleranceAccepted);
    }

    /**
     * Move the point <code>initialLocation</code> with the specified
     * <code>moveDelta</code> and check that the labels have been correctly move
     * according to <code>expectedLabelDeltas</code>.
     *
     * @param diagramEditor
     *            the currently active {@link SWTBotSiriusDiagramEditor}
     * @param initialLocation
     *            the initial point to move
     * @param moveDelta
     *            the delta of the move.
     * @param expectedLabelLocations
     *            the expected locations of the edgeLabel
     * @param errorMessagePrefix
     *            The prefix of the message in case of error.
     * @param isToleranceAccepted
     *            true if the expected point can be imprecise (this is the case
     *            for oblique edges)
     */
    @SuppressWarnings("restriction")
    private void doPerformMoveAndCheckEdgeLabels(SWTBotSiriusDiagramEditor diagramEditor, Point initialLocation, Point moveDelta, Map<String, LabelPositionData> expectedLabelLocations,
            String errorMessagePrefix, boolean isToleranceAccepted) {
        List<SWTBotGefEditPart> selectedEditParts = diagramEditor.selectedEditParts();
        Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);

        // Perform drag
        final AtomicBoolean dragFinished = new AtomicBoolean(false);
        diagramEditor.dragWithKey(initialLocation.x, initialLocation.y, targetLocation.x, targetLocation.y, SWT.None, dragFinished);
        // Wait that the drag is done (the async Runnable simulating the
        // drag)
        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return dragFinished.get();
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "The drag'n'drop operation has not finished.";
            }
        });
        // Wait that the figures are redrawn. In a fast environment, figures
        // are not really redrawn and the rest of the test is not reliable.
        SWTBotUtils.waitAllUiEvents();
        try {
            assertEquals("Drag as failed: selection should be the same before and after drag.", selectedEditParts, diagramEditor.selectedEditParts());
            // Compare the location of each label with the expected.
            for (Entry<String, LabelPositionData> labelNameToPositionData : expectedLabelLocations.entrySet()) {
                SWTBotGefEditPart swtBotLabelEditPart = diagramEditor.getEditPart(labelNameToPositionData.getKey(), AbstractDEdgeNameEditPart.class);
                if (swtBotLabelEditPart.part() instanceof LabelEditPart) {
                    LabelEditPart labelEditPart = (LabelEditPart) swtBotLabelEditPart.part();
                    IFigure labelFigure = labelEditPart.getFigure();
                    Rectangle labelBounds = labelFigure.getBounds();
                    Point currentLabelLocation = labelBounds.getLocation();
                    Point expectedLabelPosition;
                    LabelPositionData labelPositionData = labelNameToPositionData.getValue();
                    if (labelPositionData.expectedLabelPosition != null) {
                        expectedLabelPosition = labelPositionData.expectedLabelPosition;
                    } else if (labelPositionData.standardLocationExpected) {
                        int location = LabelViewConstants.MIDDLE_LOCATION;
                        int editPartVisualId = DEdgeNameEditPart.VISUAL_ID;
                        if (labelEditPart instanceof DEdgeBeginNameEditPart) {
                            location = LabelViewConstants.SOURCE_LOCATION;
                            editPartVisualId = DEdgeBeginNameEditPart.VISUAL_ID;
                        } else if (labelEditPart instanceof DEdgeEndNameEditPart) {
                            location = LabelViewConstants.TARGET_LOCATION;
                            editPartVisualId = DEdgeEndNameEditPart.VISUAL_ID;
                        }
                        // Retrieve expected reference
                        Point anchorPoint = PointListUtilities.calculatePointRelativeToLine(((Connection) labelFigure.getParent()).getPoints(), 0, location, true);
                        // Retrieve default vector to apply
                        Point snapBack = LabelEditPart.getSnapBackPosition(SiriusVisualIDRegistry.getType(editPartVisualId));
                        // Compute expected center by applying vector to
                        // expected reference point
                        Point expectedCenter = anchorPoint.getTranslated(snapBack);
                        // Compute the expected label location
                        expectedLabelPosition = expectedCenter.getTranslated(-(labelBounds.width / 2), -(labelBounds.height / 2));
                    } else {
                        // Retrieve expected segment
                        @SuppressWarnings("rawtypes")
                        List segments = PointListUtilities.getLineSegments(((Connection) labelFigure.getParent()).getPoints());
                        LineSeg lineSeg = (LineSeg) segments.get(labelPositionData.segmentIndex);
                        // Retrieve expected reference point from ratio
                        Point refPoint = new Point();
                        lineSeg.pointOn((long) (lineSeg.length() * labelPositionData.ratio), KeyPoint.ORIGIN, refPoint);
                        // Compute expected center by applying vector to
                        // expected reference point
                        Point expectedCenter = refPoint.getTranslated(labelPositionData.vector.x, labelPositionData.vector.y);
                        // Compute the expected label location
                        expectedLabelPosition = expectedCenter.getTranslated(-(labelBounds.width / 2), -(labelBounds.height / 2));
                    }
                    if (isToleranceAccepted) {
                        GraphicTestsSupportHelp.assertEquals(errorMessagePrefix + "Bad label position for \"" + labelNameToPositionData.getKey() + "\"", expectedLabelPosition, currentLabelLocation,
                                1);
                    } else {
                        assertEquals(errorMessagePrefix + "Bad label position for \"" + labelNameToPositionData.getKey() + "\"", expectedLabelPosition, currentLabelLocation);
                    }
                }
            }
        } catch (AssertionError e) {
            takeScreenshot("_MoveDelta_" + moveDelta);
            throw e;
        } finally {
            // Undo move to restore clean state
            undo(localSession.getOpenedSession());
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Returns an editor set up according to the given test dimensions.
     *
     * @param diagramName
     *            the diagram name
     * @param zoomLevel
     *            the zoom level
     * @return
     */
    private SWTBotSiriusDiagramEditor setUpEditorAccordingToDimensions(String diagramName, ZoomLevel zoomLevel) {
        return setUpEditorAccordingToDimensions(DIAGRAM_DESCRIPTION_NAME, diagramName, zoomLevel);
    }

    /**
     * Returns an editor set up according to the given test dimensions.
     *
     * @param diagramDescriptionName
     *            the diagram description name
     * @param diagramName
     *            the diagram name
     * @param zoomLevel
     *            the zoom level
     * @return
     */
    private SWTBotSiriusDiagramEditor setUpEditorAccordingToDimensions(String diagramDescriptionName, String diagramName, ZoomLevel zoomLevel) {
        SWTBotSiriusDiagramEditor diagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), diagramDescriptionName, diagramName, DDiagram.class, true, true);
        diagramEditor.zoom(zoomLevel);
        diagramEditor.maximize();
        return diagramEditor;
    }

    /**
     * Tests that begin, end and center labels of an edge are snapped back when
     * the action "Snap Back Label(s) is used on it.
     */
    public void testSingleSnapBackOnEdgeWithThreeLabels() {
        String diagramName = "EdgeWith3SegmentsHVH";

        diagramEditor = setUpEditorAccordingToDimensions(diagramName, ZoomLevel.ZOOM_100);

        String beginLabel = "refToFBegin";
        String centerLabel = "refToFCenter";
        String endLabel = "refToFEnd";

        // Expected distance between edge center and label origin point after
        // using the action.
        Map<String, Long> expectedLabelToDistanceWithEdge = new HashMap<String, Long>();
        expectedLabelToDistanceWithEdge.put(beginLabel, Long.valueOf(65));
        expectedLabelToDistanceWithEdge.put(centerLabel, Long.valueOf(13));
        expectedLabelToDistanceWithEdge.put(endLabel, Long.valueOf(92));

        checkLabelsSnapBack("E", "F", beginLabel, centerLabel, endLabel, expectedLabelToDistanceWithEdge);
    }

    /**
     * Tests that begin and center labels of an edge are snapped back when the
     * action "Snap Back Label(s) is used on it and no end label is specified.
     */
    public void testSingleSnapBackOnEdgeWithTwoLabels() {
        String diagramName = "DiagramWithBeginAndCenterLabel";

        diagramEditor = setUpEditorAccordingToDimensions(diagramName, diagramName, ZoomLevel.ZOOM_100);

        String beginLabel = "refToFBegin";
        String centerLabel = "refToFCenter";

        // Expected distance between edge center and label origin point after
        // using the action.
        Map<String, Long> expectedLabelToDistanceWithEdge = new HashMap<String, Long>();
        expectedLabelToDistanceWithEdge.put(beginLabel, Long.valueOf(24));
        expectedLabelToDistanceWithEdge.put(centerLabel, Long.valueOf(10));

        checkLabelsSnapBack("E", "F", beginLabel, centerLabel, null, expectedLabelToDistanceWithEdge);
    }

    /**
     * Tests that edge labels don't move when using snap back label action on an
     * edge without labels defined.
     */
    public void testSingleSnapBackOnEdgeWithZeroLabels() {
        String diagramName = "DiagramWithZeroSpecifiedLabel";

        diagramEditor = setUpEditorAccordingToDimensions(diagramName, diagramName, ZoomLevel.ZOOM_100);

        checkAllEdgeLabelsDontMove("E", "F");
    }

    /**
     * Tests that edge labels don't move when using snap back label action on an
     * edge with empty labels.
     */
    public void testSingleSnapBackOnEdgeWithEmptyLabels() {
        String diagramName = "DiagramWithEmptyLabel";

        diagramEditor = setUpEditorAccordingToDimensions(diagramName, diagramName, ZoomLevel.ZOOM_100);

        checkAllEdgeLabelsDontMove("E", "F");
    }

    /**
     * Ensures that when moving the source and the target node of the edge, the
     * labels of this edges are correctly located in various configuration (zoom
     * level, scrollbars, initial position of the label, node move style, ...).
     */
    public void testLabelStabilityWhenMovingNodeOfTreeEdge() {
        // There are two nodes connected by an edge with 3 segments with
        // TREE STYLE
        String diagramName = "DiagWithNodeAndTreeLayoutAndIconOnEdgeLabel";
        Map<String, Dimension> edgeLabelExpectedPosition = new LinkedHashMap<>();
        edgeLabelExpectedPosition.put("superTypesOf K11", new Dimension(-2150, 225));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, diagramName, Arrays.asList(new Point(-2150, 225)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "K1", "K11");
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("superTypesOf K11", new Dimension(-2144, 224));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, diagramName, Arrays.asList(new Point(-536, 56)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_25, true, "K1", "K11");

        diagramName = "DiagWithNodeAndTreeLayout";
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("superTypesOf K11", new Dimension(-2150, 225));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, diagramName, Arrays.asList(new Point(-2150, 225)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "K1", "K11");
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("superTypesOf K11", new Dimension(-2144, 224));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, diagramName, Arrays.asList(new Point(-536, 56)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_25, true, "K1", "K11");

        logFailures();
    }

    /**
     * Verifies that all labels of the edge related to the given source and
     * target part does not move when Snap back Label(s) action is run.
     * 
     * @param targetEditPartName
     *            the name of the edit part used as target of the edge from
     *            which we test the snap back labels action.
     * @param sourceEditPartName
     *            the name of the edit part used as source of the edge from
     *            which we test the snap back labels action.
     * @param beginLabelName
     *            the name of the begin label of an edge.
     * @param centerLabelName
     *            the name of the center label of an edge.
     * @param endLabelName
     *            the name of the end label of an edge.
     * 
     */
    private void checkAllEdgeLabelsDontMove(String sourceEditPartName, String targetEditPartName) {
        SWTBotGefEditPart sourceEditPart = diagramEditor.getEditPart(sourceEditPartName, IAbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart targetEditPart = diagramEditor.getEditPart(targetEditPartName, IAbstractDiagramNodeEditPart.class);
        SWTBotGefConnectionEditPart swtEdgeEditPart = diagramEditor.getConnectionEditPart(sourceEditPart, targetEditPart).get(0);

        AbstractDiagramEdgeEditPart edgeEditPart = (AbstractDiagramEdgeEditPart) swtEdgeEditPart.part();

        List<?> children = edgeEditPart.getChildren();
        assertEquals(3, children.size());

        AbstractDiagramNameEditPart lablOne = (AbstractDiagramNameEditPart) children.get(0);
        AbstractDiagramNameEditPart lablTwo = (AbstractDiagramNameEditPart) children.get(1);
        AbstractDiagramNameEditPart lablThree = (AbstractDiagramNameEditPart) children.get(2);

        Rectangle originalBoundLabelOne = lablOne.getFigure().getBounds().getTranslated(0, 0);
        Rectangle originalBoundLabelTwo = lablTwo.getFigure().getBounds().getTranslated(0, 0);
        Rectangle originalBoundLabelThree = lablThree.getFigure().getBounds().getTranslated(0, 0);

        edgeEditPart.performRequest(new Request(ActionIds.EDGE_SNAP_BACK));
        SWTBotUtils.waitAllUiEvents();

        // We verify that labels have not moved.
        assertEquals("Label '" + lablOne.getEditText() + "' position has changed whereas it should have not.", originalBoundLabelOne, lablOne.getFigure().getBounds());
        assertEquals("Label '" + lablTwo.getEditText() + "' position has changed whereas it should have not.", originalBoundLabelTwo, lablTwo.getFigure().getBounds());
        assertEquals("Label '" + lablThree.getEditText() + "' position has changed whereas it should have not.", originalBoundLabelThree, lablThree.getFigure().getBounds());

    }

    /**
     * Verifies that given label(s) are snapped back correctly by using the
     * given original and expected distances between edge center and labels
     * origin points.
     * 
     * @param targetEditPartName
     *            the name of the edit part used as target of the edge from
     *            which we test the snap back labels action.
     * @param sourceEditPartName
     *            the name of the edit part used as source of the edge from
     *            which we test the snap back labels action.
     * @param beginLabelName
     *            the name of the begin label of an edge.
     * @param centerLabelName
     *            the name of the center label of an edge.
     * @param endLabelName
     *            the name of the end label of an edge.
     * @param expectedLabelToDistanceWithEdge
     *            a map containing the original distance between edge center and
     *            label origin point.
     */
    private void checkLabelsSnapBack(String sourceEditPartName, String targetEditPartName, String beginLabelName, String centerLabelName, String endLabelName,
            Map<String, Long> expectedLabelToDistanceWithEdge) {
        SWTBotGefEditPart sourceEditPart = diagramEditor.getEditPart(sourceEditPartName, IAbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart targetEditPart = diagramEditor.getEditPart(targetEditPartName, IAbstractDiagramNodeEditPart.class);
        SWTBotGefConnectionEditPart swtEdgeEditPart = diagramEditor.getConnectionEditPart(sourceEditPart, targetEditPart).get(0);

        AbstractDiagramEdgeEditPart edgeEditPart = (AbstractDiagramEdgeEditPart) swtEdgeEditPart.part();
        Point edgeCenter = ((ViewEdgeFigure) edgeEditPart.getFigure()).getPoints().getMidpoint();

        List<?> children = edgeEditPart.getChildren();
        assertEquals("Wrong number of children parts", 3, children.size());

        SWTBotGefEditPart botBeginRefPart = beginLabelName != null ? diagramEditor.getEditPart(beginLabelName) : null;
        SWTBotGefEditPart botCenterRefPart = centerLabelName != null ? diagramEditor.getEditPart(centerLabelName) : null;
        SWTBotGefEditPart botEndRefPart = endLabelName != null ? diagramEditor.getEditPart(endLabelName) : null;
        AbstractDEdgeNameEditPart beginRefPart = beginLabelName != null ? (AbstractDEdgeNameEditPart) botBeginRefPart.part() : null;
        AbstractDEdgeNameEditPart centerRefPart = centerLabelName != null ? (AbstractDEdgeNameEditPart) botCenterRefPart.part() : null;
        AbstractDEdgeNameEditPart endRefPart = endLabelName != null ? (AbstractDEdgeNameEditPart) botEndRefPart.part() : null;

        Point boundBeginRef = beginLabelName != null ? beginRefPart.getFigure().getBounds().getCenter() : null;
        Point boundCenterRef = centerLabelName != null ? centerRefPart.getFigure().getBounds().getCenter() : null;
        Point boundEndRef = endLabelName != null ? endRefPart.getFigure().getBounds().getCenter() : null;

        // We check the labels are not to their default location
        if (beginLabelName != null)
            assertNotEquals("Label '" + beginLabelName + "'must not be to the default position", expectedLabelToDistanceWithEdge.get(beginLabelName),
                    (Long) Math.round(edgeCenter.getDistance(boundBeginRef)));
        if (centerLabelName != null)
            assertNotEquals("Label '" + centerLabelName + "'must not be to the default position", expectedLabelToDistanceWithEdge.get(centerLabelName),
                    (Long) Math.round(edgeCenter.getDistance(boundCenterRef)));
        if (endLabelName != null)
            assertNotEquals("Label '" + endLabelName + "'must not be to the default position", expectedLabelToDistanceWithEdge.get(endLabelName),
                    (Long) Math.round(edgeCenter.getDistance(boundEndRef)));

        CheckEditPartMoved checkBeginRefPartMoved = beginLabelName != null ? new CheckEditPartMoved(botBeginRefPart) : null;
        CheckEditPartMoved checkCenterRefPartMoved = centerLabelName != null ? new CheckEditPartMoved(botCenterRefPart) : null;
        CheckEditPartMoved checkEndRefPartMoved = endLabelName != null ? new CheckEditPartMoved(botEndRefPart) : null;

        edgeEditPart.performRequest(new Request(ActionIds.EDGE_SNAP_BACK));
        if (beginLabelName != null)
            bot.waitUntil(checkBeginRefPartMoved);
        if (centerLabelName != null)
            bot.waitUntil(checkCenterRefPartMoved);
        if (endLabelName != null)
            bot.waitUntil(checkEndRefPartMoved);

        boundBeginRef = beginLabelName != null ? beginRefPart.getFigure().getBounds().getCenter() : null;
        boundCenterRef = centerLabelName != null ? centerRefPart.getFigure().getBounds().getCenter() : null;
        boundEndRef = endLabelName != null ? endRefPart.getFigure().getBounds().getCenter() : null;
        // We verify that labels have been moved near the edge at its default
        // location.
        if (beginLabelName != null)
            assertEquals("Label '" + beginLabelName + "'must be to its default position", expectedLabelToDistanceWithEdge.get(beginLabelName),
                    (Long) Math.round(edgeCenter.getDistance(boundBeginRef)));
        if (centerLabelName != null)
            assertEquals("Label '" + centerLabelName + "'must be to its default position", expectedLabelToDistanceWithEdge.get(centerLabelName),
                    (Long) Math.round(edgeCenter.getDistance(boundCenterRef)));
        if (endLabelName != null)
            assertEquals("Label '" + endLabelName + "'must be to its default position", expectedLabelToDistanceWithEdge.get(endLabelName), (Long) Math.round(edgeCenter.getDistance(boundEndRef)));

    }
}
