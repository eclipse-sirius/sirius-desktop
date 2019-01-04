/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.ext.draw2d.figure.ICollapseMode;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

/**
 * 
 * @author smonnier
 */
public class EdgeStabilityOnPortCollapsingTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layout/ticket1481/tc1481.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layout/ticket1481/tc1481.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layout/ticket1481/tc1481_domainbased.odesign";

    private static final String REPRESENTATION_DESC_NAME = "TC1481 domain based";

    private static final HashMap<String, List<Point>> CONNECTION_EDITPART_POINTS = new HashMap<String, List<Point>>();

    private static final int DELTA_INIT = 1;

    /**
     * Port collapsing leads to edge shifting. Very small, but something which can be measured If this shifting is 0, it
     * means that port collapsing is broken !
     */
    private static final int MINIMUM_DELTA_AFTER_COLLAPSE = 1;

    private static final int MINIMUM_DELTA_AFTER_UNCOLLAPSE = 0;

    // VP-1142 : as the ICollapseMode.MINIMIZED_DIMENSION can be changed, we
    // compute the delta after collapse from this minimized dimension
    // 14 is the size of the port if not collapsed
    private static final int DELTA_AFTER_COLLAPSE = Math.abs(14 - ICollapseMode.MINIMIZED_DIMENSION.width) + 1;

    private static final int DELTA_AFTER_UNCOLLAPSE = 2;

    private DDiagram diagram;

    private DiagramEditor editor;

    static {
        ArrayList<Point> pointsToB = new ArrayList<Point>();
        pointsToB.add(new Point(244, 283));
        pointsToB.add(new Point(333, 283));
        pointsToB.add(new Point(333, 370));
        pointsToB.add(new Point(528, 370));
        CONNECTION_EDITPART_POINTS.put("toB", pointsToB);
        ArrayList<Point> pointsToE = new ArrayList<Point>();
        pointsToE.add(new Point(244, 250));
        pointsToE.add(new Point(612, 250));
        CONNECTION_EDITPART_POINTS.put("toE", pointsToE);
        ArrayList<Point> pointsToF = new ArrayList<Point>();
        pointsToF.add(new Point(244, 195));
        pointsToF.add(new Point(377, 195));
        pointsToF.add(new Point(377, 85));
        pointsToF.add(new Point(618, 85));
        CONNECTION_EDITPART_POINTS.put("toF", pointsToF);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);
        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);
        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Checks that the points contained in all edges of the diagram are in the expected positions.
     * 
     * @throws Exception
     */
    public void testEdgeStabilityOnPortCollapsing() throws Exception {
        // Check connection shapes before collapsing
        checkConnectionPoints("toB", 0, DELTA_INIT);
        checkConnectionPoints("toE", 0, DELTA_INIT);
        checkConnectionPoints("toF", 0, DELTA_INIT);

        collapsePorts();

        checkConnectionPoints("toB", MINIMUM_DELTA_AFTER_COLLAPSE, DELTA_AFTER_COLLAPSE);
        checkConnectionPoints("toE", MINIMUM_DELTA_AFTER_COLLAPSE, DELTA_AFTER_COLLAPSE);
        checkConnectionPoints("toF", MINIMUM_DELTA_AFTER_COLLAPSE, DELTA_AFTER_COLLAPSE);

        uncollapsePorts();

        // Check connection shapes at original locations
        checkConnectionPoints("toB", MINIMUM_DELTA_AFTER_UNCOLLAPSE, DELTA_AFTER_UNCOLLAPSE);
        checkConnectionPoints("toE", MINIMUM_DELTA_AFTER_UNCOLLAPSE, DELTA_AFTER_UNCOLLAPSE);
        checkConnectionPoints("toF", MINIMUM_DELTA_AFTER_UNCOLLAPSE, DELTA_AFTER_UNCOLLAPSE);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeStabilityWhenPortIsNearByAParentCorner() throws Exception {

        ConnectionEditPart connectionEditPart = getConnectionEditPart("toJ");

        EditPart sourcePort = connectionEditPart.getSource();
        EditPart parent = sourcePort.getParent();

        collapsePorts();

        Rectangle parentBounds = ((IGraphicalEditPart) parent).getFigure().getBounds();
        Rectangle sourcePortBounds = ((IGraphicalEditPart) sourcePort).getFigure().getBounds();

        // If source of the edge stays on the same side of parent container, y
        // should remain unchanged
        // Margin of 4 pixels is acceptable.
        assertEquals(parentBounds.getBottom().y, sourcePortBounds.getBottom().y, 4);
    }

    private void checkConnectionPoints(String editPartName, int minimumDelta, int maximumDelta) {
        ConnectionEditPart edgeEP = getConnectionEditPart(editPartName);
        edgeEP.refresh();
        TestsUtil.synchronizationWithUIThread();

        PolylineConnectionEx polylineConnectionEx = (PolylineConnectionEx) edgeEP.getFigure();

        PointList pointList = polylineConnectionEx.getPoints().getCopy();
        List<Point> referencePoints = CONNECTION_EDITPART_POINTS.get(editPartName);

        assertEquals("Invalid points number in connection: " + editPartName, referencePoints.size(), pointList.size());
        for (int i = 0; i < referencePoints.size(); i++) {
            Point point = pointList.getPoint(i);
            Point referencePoint = referencePoints.get(i);
            assertEquals("X position of point number " + i + " of connection named " + editPartName + " is invalid.", referencePoint.x, point.x, maximumDelta);
            assertEquals("Y position of point number " + i + " of connection named " + editPartName + " is invalid.", referencePoint.y, point.y, maximumDelta);

            // Check this only for first and last points of pointList
            if (i == 0 || i == referencePoints.size() - 1) {
                assertPositionIsBetween("Position of point number " + i + " of connection named " + editPartName + " is invalid.", point, referencePoint, minimumDelta, maximumDelta);
            }
        }
    }

    private ConnectionEditPart getConnectionEditPart(String editPartName) {
        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, editPartName);
        assertEquals("There are not as many edit part as expected", 1, diagramElementsFromLabel.size());

        DDiagramElement dDiagramElement = diagramElementsFromLabel.get(0);

        IGraphicalEditPart part = getEditPart(dDiagramElement);
        assertNotNull("Edit part not found", part);
        assertTrue(part instanceof ConnectionEditPart);

        ConnectionEditPart edgeEP = (ConnectionEditPart) part;
        return edgeEP;
    }

    private void collapsePorts() {
        TestsUtil.synchronizationWithUIThread();

        activateFilter(diagram, "port collapse");

        refresh(diagram);

        TestsUtil.synchronizationWithUIThread();
    }

    private void uncollapsePorts() {
        TestsUtil.synchronizationWithUIThread();

        deactivateFilter(diagram, "port collapse");

        refresh(diagram);

        TestsUtil.synchronizationWithUIThread();
    }

    private void assertPositionIsBetween(String message, Point actual, Point expected, int lower, int higher) {
        double distance = actual.getDistance(expected);
        assertTrue(message, distance <= higher);

        // If collapsing feature works, first and last points in pointList must
        // have changed their position. Otherwise, it means collapsing feature
        // is broken
        boolean lowerCondition = distance >= lower;
        assertTrue(message, lowerCondition);
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
