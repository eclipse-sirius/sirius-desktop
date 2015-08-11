/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This class is complementary to {@link BendpointsStabilityOnMovesTest} but
 * with specific cases detected after.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class BendpointsStabilityOnMovesSpecificCasesTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PROPERTIES_VIEW_ID = "org.eclipse.ui.views.PropertySheet";

    private static final String DATA_UNIT_DIR = "data/unit/bendpointsStability/otherSpecificCases/";

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DIAGRAM_DESCRIPTION_NAME = "Entities";

    boolean isOutlineViewOpened;

    boolean isPropertiesViewOpened;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
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

        // Open the testing diagram editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESCRIPTION_NAME, "rectilinearCase1", DSemanticDiagram.class, true, true);
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
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
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency.
     */
    public void testFirstPointConsistency() {
        testFirstPointConsistency(new Point(20, 20), false, false);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency. In this case, the first segment is merged with
     * the second one.
     */
    public void testFirstPointConsistencyWithMergeSegment() {
        testFirstPointConsistency(new Point(0, 99), true, false);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency (when the first segment is removed).
     */
    public void testFirstPointConsistencyWithFirstSegmentRemoval() {
        testFirstPointConsistency(new Point(60, 20), false, true);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency.
     */
    public void testLastPointConsistency() {
        testLastPointConsistency(new Point(-20, 50), false, false);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency. In this case, the last segment is merged with the
     * previous one.
     */
    public void testLastPointConsistencyWithMergeSegment() {
        testLastPointConsistency(new Point(0, -139), true, false);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency (when the last segment is removed).
     */
    public void testLastPointConsistencyWithLastSegmentRemoval() {
        testLastPointConsistency(new Point(-120, 50), false, true);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency.
     * 
     * @param moveDelta
     *            The delta from which the source node will be moved
     * @param segmentMerged
     *            true if the corresponding segment is merged with the next one
     *            (2 points less and segments normalize and straight), false
     *            otherwise. Exclusive with segmentRemoved.
     * @param segmentRemoved
     *            true if the corresponding segment is removed (absorbed by the
     *            moved node), false otherwise. Exclusive with segmentMerged.
     */
    protected void testLastPointConsistency(Point moveDelta, boolean segmentMerged, boolean segmentRemoved) {
        String nodeToMoveName = "C2";
        editor.reveal(nodeToMoveName);
        // Step 2: store the previous bendpoints
        SWTBotGefEditPart editPartToMove = editor.getEditPart(nodeToMoveName, IAbstractDiagramNodeEditPart.class);
        SWTBotGefConnectionEditPart connectionEditPart = editPartToMove.targetConnections().get(0);
        PointList previousPoints = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();

        // Step 3: Drag node
        Rectangle nodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) editPartToMove.part());
        Point initialLocation = nodeBounds.getCenter();
        Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);
        ICondition editPartMovedCondition = new CheckEditPartMoved(editPartToMove);
        editor.drag(initialLocation, targetLocation);
        bot.waitUntil(editPartMovedCondition);
        assertEquals("Drag as failed: selection should be the same before and after drag.", editPartToMove, editor.selectedEditParts().get(0));
        // Step 4: Check bendpoints
        compareActualBendpointsWithExpected(editor, connectionEditPart, previousPoints, moveDelta, nodeBounds, false, segmentMerged, segmentRemoved);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency.
     * 
     * @param moveDelta
     *            The delta from which the source node will be moved
     * @param segmentMerged
     *            true if the corresponding segment is merged with the next one
     *            (2 points less and segments normalize and straight), false
     *            otherwise. Exclusive with segmentRemoved.
     * @param segmentRemoved
     *            true if the corresponding segment is removed (absorbed by the
     *            moved node), false otherwise. Exclusive with segmentMerged.
     */
    protected void testFirstPointConsistency(Point moveDelta, boolean segmentMerged, boolean segmentRemoved) {
        String nodeToMoveName = "C1";
        editor.reveal(nodeToMoveName);
        // Step 2: store the previous bendpoints
        SWTBotGefEditPart editPartToMove = editor.getEditPart(nodeToMoveName, IAbstractDiagramNodeEditPart.class);
        SWTBotGefConnectionEditPart connectionEditPart = editPartToMove.sourceConnections().get(0);
        PointList previousPoints = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();

        // Step 3: Drag node
        Rectangle nodeBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent((GraphicalEditPart) editPartToMove.part());
        Point initialLocation = nodeBounds.getCenter();
        Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);
        ICondition editPartMovedCondition = new CheckEditPartMoved(editPartToMove);
        editor.drag(initialLocation, targetLocation);
        bot.waitUntil(editPartMovedCondition);
        assertEquals("Drag as failed: selection should be the same before and after drag.", editPartToMove, editor.selectedEditParts().get(0));
        // Step 4: Check bendpoints
        compareActualBendpointsWithExpected(editor, connectionEditPart, previousPoints, moveDelta, nodeBounds, true, segmentMerged, segmentRemoved);
    }

    // Check the first or last bendpoint of the connection.
    private void compareActualBendpointsWithExpected(SWTBotSiriusDiagramEditor diagramEditor, SWTBotGefConnectionEditPart connectionEditPart, PointList previousPoints, Point moveDelta,
            Rectangle movedNodeBounds, boolean firstPoint, boolean segmentMerged, boolean segmentRemoved) {
        List<Point> newGMFBendpointsFromSource = GMFHelper.getPointsFromSource(connectionEditPart.part());
        String messagePrefix = "First";
        if (!firstPoint) {
            messagePrefix = "Last";
        }
        if (segmentMerged) {
            assertEquals(messagePrefix + " segment must me merged. We should have 2 GMF points less.", previousPoints.size() - 2, newGMFBendpointsFromSource.size());
        } else if (segmentRemoved) {
            assertEquals(messagePrefix + " segment must me removed. We should have 1 GMF point less.", previousPoints.size() - 1, newGMFBendpointsFromSource.size());
        }
        Point previousDraw2dPoint;
        Point previousMergedPoint;
        Point currentDraw2dPoint;
        Point currentGmfPoint;
        int yNodeBorder;
        PointList actualBendPoints = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();
        if (firstPoint) {
            previousDraw2dPoint = previousPoints.getFirstPoint();
            previousMergedPoint = previousPoints.getPoint(2);
            currentDraw2dPoint = actualBendPoints.getFirstPoint();
            currentGmfPoint = newGMFBendpointsFromSource.get(0);
            yNodeBorder = movedNodeBounds.getTranslated(moveDelta).getBottom().y;
        } else {
            previousDraw2dPoint = previousPoints.getLastPoint();
            previousMergedPoint = previousPoints.getPoint(previousPoints.size() - 3);
            currentDraw2dPoint = actualBendPoints.getLastPoint();
            currentGmfPoint = newGMFBendpointsFromSource.get(newGMFBendpointsFromSource.size() - 1);
            yNodeBorder = movedNodeBounds.getTranslated(moveDelta).y;
        }
        // The first (or last) bendpoint should have moved.
        assertNotEquals(messagePrefix + " point should have moved", previousDraw2dPoint, currentDraw2dPoint);
        if (segmentMerged) {
            Point expectedPoint = previousDraw2dPoint.getTranslated(moveDelta);
            Point otherExpectedPoint = new Point(expectedPoint.x, previousMergedPoint.y);
            assertTrue(messagePrefix + " point should have moved at the expected location. expected:<" + expectedPoint + "or " + otherExpectedPoint + "> but was:<" + currentDraw2dPoint + ">",
                    expectedPoint.equals(currentDraw2dPoint) || otherExpectedPoint.equals(currentDraw2dPoint));
        } else if (segmentRemoved) {
            Point otherExpectedPoint = new Point(previousMergedPoint.x, yNodeBorder);
            assertEquals(messagePrefix + " point should have moved at the expected location", otherExpectedPoint, currentDraw2dPoint);
        } else {
            assertEquals(messagePrefix + " point should have moved at the expected location", previousDraw2dPoint.getTranslated(moveDelta), currentDraw2dPoint);
        }
        // The draw2d and GMF point should be the same.
        assertEquals(messagePrefix + " draw2d and GMF points should be the same", currentDraw2dPoint, currentGmfPoint);
    }
}
