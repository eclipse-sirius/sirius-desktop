/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Vector;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg.KeyPoint;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import junit.framework.AssertionFailedError;

/**
 * Check the position of edge labels when modifying the edge.
 *
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class EdgeLabelsMoveTest extends AbstractSiriusSwtBotGefTestCase {

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
     * computed from standard location of label locator.
     */
    private static final Dimension DELTA_TO_COMPUTE_FROM_STANDARD = new Dimension(-3000, -3000);

    private static final String PROPERTIES_VIEW_ID = "org.eclipse.ui.views.PropertySheet";

    private Set<AssertionFailedError> failures = Sets.newLinkedHashSet();

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
     * TODO: Add specific test for bracket edges. Currently KO with this
     * feature.
     */
    public void _testWithBracketEdges() {
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
        Map<String, Dimension> edgeLabelExpectedPosition = Maps.newLinkedHashMap();
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(-10, 0), new Point(10, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_125, "B");

        // test when label anchor moves from 2nd segment to 3rd segment
        // the label should not move
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(100, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_50, "B");

        // test when label anchor moves from 2nd segment to 1st segment
        // the label should not move
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(-30, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_50, "B");

        // test when Both begin and end nodes are moved
        // the label should not move as the edge position is relative to both
        // nodes
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(150 * 2, 150 * 2));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(150, 150)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_50, "A", "B");

        // Case Rectilinear E in spec - test when the reference point is no
        // longer on the segment. the new location is computed from a ratio.
        edgeLabelExpectedPosition.put("refToBCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(0, -49)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "B");

        // Part 2: the ref point is on the 3nd segment
        // test enlarging/reducing the 3nd segment
        // the label should not be moved
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(-10, 0), new Point(10, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // test moving node up
        // the label should be moved with the same node move
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, -10));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(0, -10)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // test when the reference point is no longer on the segment
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(-54, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // Case Rectilinear F in spec - test when the reference segment is not
        // existing any more the label position should be reinitialized (reset
        // to std location)
        edgeLabelExpectedPosition.put("refToDBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_STANDARD);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(-102, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // test when reference segment is merged (The reference point is on a
        // segment that is removed but merged with another one: The label is
        // moved with the same delta (to keep the same vector).
        // The delta is not the same as the segment is straightened (see
        // SetConnectionBendpointsAccordingToExtremityMoveCommmand#adaptPointListAndRefPoints).
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, -65));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(0, -66)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        // Part 3: simple test on start and end edge label to check that
        // algorithm is also applied on these edge label
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 30));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(30, 30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A");

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(80, 30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A");

        // Part 4: the ref point is not on a segment
        // test enlarging/reducing the 3nd segment
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDEnd", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(0, -10)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "D");

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(0, 200)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "A");

        // Move a node with result that the segment is "reversed", the label on
        // the corresponding segment should keep the same ratio and the others
        // label should not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", DELTA_TO_COMPUTE_FROM_INVERTED_RATIO);
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(350, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "A");

        if (!failures.isEmpty()) {
            String message = "";
            for (AssertionFailedError assertion : failures) {
                message = message + "\n" + assertion.getMessage();
            }
            System.out.println(message);
            throw new AssertionFailedError(failures.size() + " failures found : " + message);
        }
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
        Map<String, Dimension> edgeLabelExpectedPosition = Maps.newLinkedHashMap();

        // Case Rectilinear A, B and B' of the spec - The reference point is on
        // a segment
        // that is not impacted by the move of another segment --> The label
        // does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveSegment(diagramName, Lists.newArrayList(new Point(0, 20), new Point(0, -10), new Point(0, -40)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 2);

        // Case Rectilinear G of the spec - The reference point is on a segment
        // that is merged by the move of another segment --> The label does not
        // move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        doTestMoveSegment(diagramName, Lists.newArrayList(new Point(0, -67)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 2);

        // Move vertical segment, the start and end label should not move
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveSegment(diagramName, Lists.newArrayList(new Point(-20, 0), new Point(20, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1);

        // Move vertical segment, the start label (outside of the edge bounds)
        // should not move
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToFBegin", new Dimension(0, 0));
        doTestMoveSegment(diagramName, Lists.newArrayList(new Point(20, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "E", "F", 1);

        if (!failures.isEmpty()) {
            String message = "";
            for (AssertionFailedError assertion : failures) {
                message = message + "\n" + assertion.getMessage();
            }
            System.out.println(message);
            throw new AssertionFailedError(failures.size() + " failures found : " + message);
        }
    }

    /**
     * TODO: Add specific test for edge linking nodes contained in container.
     */
    public void _testLabelMoveForEdgeInContainer() {
    }

    /**
     * Ensure that only the first or last bendpoint is moved when moving one of
     * edge extremity in various configuration (zoom level, edge style, ...).
     */
    public void testLabelStabilityWhenMovingPointOnRectilinearEdge() {
        // There are two nodes connected by an edge with 3 segments with
        // RECTILINEAR STYLE
        // Segments are horizontal,vertical and horizontal
        String diagramName = "EdgeWith3SegmentsHVH";
        Map<String, Dimension> edgeLabelExpectedPosition = Maps.newLinkedHashMap();

        // Case Rectilinear G of the spec - The reference point is on a segment
        // that is removed but merged with another one --> The label is moved
        // with the same delta (to keep the same vector).
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, -31));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMovePointOnEdge(diagramName, Lists.newArrayList(new Point(-5, -30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 0);

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 29));
        doTestMovePointOnEdge(diagramName, Lists.newArrayList(new Point(5, 30)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 3);

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, -15));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(15, 0));
        doTestMovePointOnEdge(diagramName, Lists.newArrayList(new Point(15, -15)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1);

        // Case Rectilinear E of the spec - The reference point is on a segment
        // that is resized: reduced and the reference point is no longer on the
        // segment --> A new reference point is computed by keeping the same
        // ratio of the original reference point according to the length of the
        // segment.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMovePointOnEdge(diagramName, Lists.newArrayList(new Point(0, 33)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1);

        if (!failures.isEmpty()) {
            String message = "";
            for (AssertionFailedError assertion : failures) {
                message = message + "\n" + assertion.getMessage();
            }
            System.out.println(message);
            throw new AssertionFailedError(failures.size() + " failures found : " + message);
        }
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
        Map<String, Dimension> edgeLabelExpectedPosition = Maps.newLinkedHashMap();
        // edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(35, 70)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "A");

        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(100, 100)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "B");

        // Case Oblique C of the spec - The reference point is on a segment that
        // is resized: enlarged and the reference point is always on the segment
        // --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(100, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "D");

        // Case Oblique D of the spec - The reference point is on a segment that
        // is resized: reduced and the reference point is always on the segment
        // --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", new Dimension(0, 0));
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(-30, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "D");

        // Case Oblique E of the spec - The reference point is on a segment that
        // is resized: reduced and the reference point is no longer on the
        // segment --> A new reference point is computed by keeping the same
        // ratio of the original reference point according to the length of the
        // segment.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, Lists.newArrayList(new Point(-50, 0)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, true, "D");

        if (!failures.isEmpty()) {
            String message = "";
            for (AssertionFailedError assertion : failures) {
                message = message + "\n" + assertion.getMessage();
            }
            System.out.println(message);
            throw new AssertionFailedError(failures.size() + " failures found : " + message);
        }
    }

    public void testLabelStabilityWhenMovingPointOnObliqueEdge() {
        // There are two nodes connected by an edge with 3 segments with
        // OBLIQUE STYLE
        String diagramName = "EdgeWithObliqueSegments";
        Map<String, Dimension> edgeLabelExpectedPosition = Maps.newLinkedHashMap();

        // Case Oblique A of the spec - The reference point is on a segment that
        // is not impacted by the move --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBEnd", new Dimension(0, 0));
        doTestMovePointOnEdge(diagramName, Lists.newArrayList(new Point(0, 50)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 1, true);

        // Case Oblique B of the spec - The reference point is on a segment that
        // is not impacted by the move --> The label does not move.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBEnd", DELTA_TO_COMPUTE_FROM_RATIO);
        doTestMovePointOnEdge(diagramName, Lists.newArrayList(new Point(0, 22)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 3);

        // Case Oblique F of the spec - The reference point is on a segment that
        // is removed --> The label is reset to standard location.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToDCenter", DELTA_TO_COMPUTE_FROM_STANDARD);
        edgeLabelExpectedPosition.put("refToDEnd", DELTA_TO_COMPUTE_FROM_STANDARD);
        doTestMovePointOnEdge(diagramName, Lists.newArrayList(new Point(0, -123)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "C", "D", 2, true);

        // Case Oblique G of the spec - The reference point is on a segment that
        // is splitted into two segments --> The label is reset to standard
        // location.
        edgeLabelExpectedPosition.clear();
        edgeLabelExpectedPosition.put("refToBEnd", DELTA_TO_COMPUTE_FROM_STANDARD);
        edgeLabelExpectedPosition.put("refToBBegin", new Dimension(0, 0));
        edgeLabelExpectedPosition.put("refToBCenter", new Dimension(0, 0));
        doTestMoveSegment(diagramName, Lists.newArrayList(new Point(0, -100)), edgeLabelExpectedPosition, ZoomLevel.ZOOM_100, "A", "B", 2, true);

        if (!failures.isEmpty()) {
            String message = "";
            for (AssertionFailedError assertion : failures) {
                message = message + "\n" + assertion.getMessage();
            }
            System.out.println(message);
            throw new AssertionFailedError(failures.size() + " failures found : " + message);
        }
    }

    /**
     * Ensures that by dragging or arranging edit parts with the given names,
     * bendpoints are moved as expected.
     *
     * @param diagramName
     *            diagram name
     * @param routingStylesToTest
     *            used to determine on which diagram(s) we should perform this
     *            test
     * @param moveDeltas
     *            all the move deltas to use for dragging elements.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param nodesToMove
     *            name of the Edit parts to move
     */
    private void doTestMoveNodesOnlyMovesFirstOrLastBendpoint(String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel, String... nodesToMove) {
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(diagramName, moveDeltas, edgeLabelDeltas, zoomLevel, false, nodesToMove);
    }

    /**
     * Ensures that by dragging or arranging edit parts with the given names,
     * bendpoints are moved as expected.
     *
     * @param diagramName
     *            diagram name
     * @param routingStylesToTest
     *            used to determine on which diagram(s) we should perform this
     *            test
     * @param moveDeltas
     *            all the move deltas to use for dragging elements.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param nodesToMove
     *            name of the Edit parts to move
     * @param isToleranceAccepted
     *            true if the expected point can be imprecise (this is the case
     *            for oblique edges)
     */
    private void doTestMoveNodesOnlyMovesFirstOrLastBendpoint(String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel,
            boolean isToleranceAccepted, String... nodesToMove) {
        for (Point moveDelta : moveDeltas) {
            // Step 1: Open the corresponding diagram
            diagramEditor = setUpEditorAccordingToDimensions(diagramName, zoomLevel);
            Collection<SWTBotGefEditPart> editPartsToMove = Sets.newLinkedHashSet();
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
    private void doTestMoveSegment(String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel, String sourceName, String targetName,
            int segmentIndex) {
        doTestMoveSegment(diagramName, moveDeltas, edgeLabelDeltas, zoomLevel, sourceName, targetName, segmentIndex, false);
    }

    /**
     * Ensures that by moving a segment of on edge, the labels are moved as
     * expected.
     *
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
    private void doTestMoveSegment(String diagramName, Collection<Point> moveDeltas, Map<String, Dimension> edgeLabelDeltas, ZoomLevel zoomLevel, String sourceName, String targetName,
            int segmentIndex, boolean isToleranceAccepted) {
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
        Map<String, LabelPositionData> expectedlLabelPositions = Maps.newHashMap();
        for (Entry<String, Dimension> labelNameToDelta : edgeLabelDeltas.entrySet()) {
            SWTBotGefEditPart labelEditPart = diagramEditor.getEditPart(labelNameToDelta.getKey(), AbstractDEdgeNameEditPart.class);
            Rectangle labelBounds = ((AbstractGraphicalEditPart) labelEditPart.part()).getFigure().getBounds();
            Point initialLabelLocation = labelBounds.getLocation();
            Dimension expectedDelta = labelNameToDelta.getValue();
            if (DELTA_TO_COMPUTE_FROM_STANDARD.equals(expectedDelta)) {
                expectedlLabelPositions.put(labelNameToDelta.getKey(), new LabelPositionData(true));
            } else if (!DELTA_TO_COMPUTE_FROM_RATIO.equals(expectedDelta) && !DELTA_TO_COMPUTE_FROM_INVERTED_RATIO.equals(expectedDelta)) {
                Point expectedLabelPosition = initialLabelLocation.getTranslated(labelNameToDelta.getValue());
                expectedlLabelPositions.put(labelNameToDelta.getKey(), new LabelPositionData(expectedLabelPosition));
            } else {
                // Compute ratio of current reference point
                Point labelCenter = labelBounds.getCenter();
                List segments = PointListUtilities.getLineSegments(figure.getPoints());
                LineSeg nearestSegment = PointListUtilities.getNearestSegment(segments, labelCenter.x, labelCenter.y);
                Point nearestPointOnSegment = nearestSegment.perpIntersect(labelCenter.x, labelCenter.y);
                float ratio = nearestSegment.distanceAlong(nearestPointOnSegment);
                // Compute vector from current reference point to current
                // center of label
                Vector vector = new Vector(new PrecisionPoint(nearestPointOnSegment), new PrecisionPoint(labelCenter));
                expectedlLabelPositions.put(labelNameToDelta.getKey(), new LabelPositionData(segments.indexOf(nearestSegment), ratio, vector));
            }
        }
        return expectedlLabelPositions;
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
    private void doPerformMoveAndCheckEdgeLabels(SWTBotSiriusDiagramEditor diagramEditor, Point initialLocation, Point moveDelta, Map<String, LabelPositionData> expectedLabelLocations,
            String errorMessagePrefix, boolean isToleranceAccepted) {
        List<SWTBotGefEditPart> selectedEditParts = diagramEditor.selectedEditParts();
        Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);

        // Perform drag
        diagramEditor.dragWithKey(initialLocation.x, initialLocation.y, targetLocation.x, targetLocation.y, SWT.None);
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
                                2);
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
     *            the routing style
     * @param zoomLevel
     *            the zoom level
     * @return
     */
    private SWTBotSiriusDiagramEditor setUpEditorAccordingToDimensions(String diagramName, ZoomLevel zoomLevel) {
        String diagramDescriptionName = DIAGRAM_DESCRIPTION_NAME;
        SWTBotSiriusDiagramEditor diagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), diagramDescriptionName, diagramName, DDiagram.class, true, true);
        diagramEditor.zoom(zoomLevel);
        return diagramEditor;
    }
}
