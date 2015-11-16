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

import com.google.common.base.Function;

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

    private static final String VSM_FILE = "My.odesign";

    private static final String DIAGRAM_DESCRIPTION_NAME = "Entities";

    boolean isOutlineViewOpened;

    boolean isPropertiesViewOpened;

    /**
     * Function to check if a point is at the expected location. The
     * {@link #setData(Point, Point, Rectangle)} method must be called before
     * calling {@link #apply(Point)} method.<BR>
     * If the {@link #apply(Point)} method return false, the method
     * {@link #getErrorMessage()} can be called to display the corresponding
     * error message.<BR>
     * The methods {@link #apply(Point)}, {@link #getErrorMessage()}, and/or
     * {@link #getExpectedPoint()} can be override to handle specific cases.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class AssertPointLocationFunction implements Function<Point, Boolean> {
        /** Original point from which to find the expected one. */
        protected Point originalPoint;

        /** The delta of the move. */
        protected Point moveDelta;

        /**
         * Another point (3rd for first point and n-3 for the last point) to
         * retrieve other axis in case of segment merge.
         */
        protected Point previousMergedPoint;

        /** The bounds of the moved node (before move). */
        protected Rectangle movedNodeBounds;

        /** Store the last input to use it in the {@link #getErrorMessage()}. */
        protected Point lastInputUsedForThisFunction;

        /**
         * Default constructor.
         * 
         * @param moveDelta
         *            The delta of the move.
         */
        public AssertPointLocationFunction(Point moveDelta) {
            this.moveDelta = moveDelta;
        }

        /**
         * 
         * @param originalPoint
         *            Original point from which to find the expected one.
         * @param previousMergedPoint
         *            Another point (3rd for first point and n-3 for the last
         *            point) to retrieve other axis in case of segment merge.
         * @param movedNodeBounds
         *            The bounds of the moved node (before move)
         */
        public void setData(Point originalPoint, Point previousMergedPoint, Rectangle movedNodeBounds) {
            this.originalPoint = originalPoint;
            this.previousMergedPoint = previousMergedPoint;
            this.movedNodeBounds = movedNodeBounds;
        }

        @Override
        public Boolean apply(Point input) {
            if (originalPoint != null && previousMergedPoint != null) {
                lastInputUsedForThisFunction = input;
                return lastInputUsedForThisFunction.equals(getExpectedPoint());
            } else {
                return false;
            }
        }

        /**
         * @return The expected point according to data.
         */
        public Point getExpectedPoint() {
            return originalPoint.getTranslated(moveDelta);
        }

        /**
         * Must be called only in case of failing of {@link #apply(Point)}
         * method. This method returns the message to display.
         * 
         * @return the message to display.
         */
        public String getErrorMessage() {
            return "Point should have moved at the expected location. Expected:<" + getExpectedPoint() + "> but was:<" + lastInputUsedForThisFunction + ">";
        }
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
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
    public void _testFirstPointConsistency() {
        final Point moveDelta = new Point(20, 20);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta);
        testFirstPointConsistency(moveDelta, 0, assertPointLocationFunction);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency. In this case, the first segment is merged with
     * the second one.
     */
    public void _testFirstPointConsistencyWithMergeSegment() {
        final Point moveDelta = new Point(0, 99);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta) {
            Point otherExpectedPoint;

            @Override
            public Boolean apply(Point input) {
                if (originalPoint != null && previousMergedPoint != null) {
                    Point lastInputUsedForThisFunction = input;
                    Point expectedPoint = originalPoint.getTranslated(moveDelta);
                    otherExpectedPoint = new Point(expectedPoint.x, previousMergedPoint.y);
                    return lastInputUsedForThisFunction.equals(expectedPoint) || lastInputUsedForThisFunction.equals(otherExpectedPoint);
                } else {
                    return false;
                }
            }

            @Override
            public String getErrorMessage() {
                return "First point should have moved at the expected location. Expected:<" + getExpectedPoint() + "or " + otherExpectedPoint + "> but was:<" + lastInputUsedForThisFunction + ">";
            }
        };
        testFirstPointConsistency(moveDelta, -2, assertPointLocationFunction);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency (when the first segment is removed).
     */
    public void testFirstPointConsistencyWithFirstSegmentRemoval() {
        final Point moveDelta = new Point(60, 20);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta) {
            @Override
            public Point getExpectedPoint() {
                return new Point(previousMergedPoint.x, movedNodeBounds.getTranslated(moveDelta).getBottom().y);
            }
        };
        testFirstPointConsistency(moveDelta, -1, assertPointLocationFunction);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency (when the first segment is inverted).
     */
    public void _testFirstPointConsistencyWithFirstSegmentInverted() {
        final Point moveDelta = new Point(340, 0);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta) {
            @Override
            public Point getExpectedPoint() {
                return originalPoint.getTranslated(moveDelta).getTranslated(new Point(-movedNodeBounds.width(), 0));
            }
        };
        testFirstPointConsistency(moveDelta, 0, assertPointLocationFunction);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency.
     */
    public void testMoveOKWithEdgeFromNodeToItsContainer() {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        // Open the other testing diagram editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Diag", "newDiag", DSemanticDiagram.class, true, true);
        final Point moveDelta = new Point(20, 20);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta);
        testFirstPointConsistency(moveDelta, 0, assertPointLocationFunction, true);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency.
     */
    public void testMoveOKWithEdgeFromContainerToItsNodes() {
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        // Open the other testing diagram editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Diag2", "newDiag2", DSemanticDiagram.class, true, true);
        final Point moveDelta = new Point(20, 20);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta);
        testLastPointConsistency(moveDelta, 0, assertPointLocationFunction, true);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency.
     */
    public void _testLastPointConsistency() {
        Point moveDelta = new Point(-20, 50);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta);
        testLastPointConsistency(moveDelta, 0, assertPointLocationFunction);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency. In this case, the last segment is merged with the
     * previous one.
     */
    public void _testLastPointConsistencyWithMergeSegment() {
        Point moveDelta = new Point(0, -139);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta) {
            Point otherExpectedPoint;

            @Override
            public Boolean apply(Point input) {
                if (originalPoint != null && previousMergedPoint != null) {
                    Point lastInputUsedForThisFunction = input;
                    Point expectedPoint = originalPoint.getTranslated(moveDelta);
                    otherExpectedPoint = new Point(expectedPoint.x, previousMergedPoint.y);
                    return lastInputUsedForThisFunction.equals(expectedPoint) || lastInputUsedForThisFunction.equals(otherExpectedPoint);
                } else {
                    return false;
                }
            }

            @Override
            public String getErrorMessage() {
                return "Last point should have moved at the expected location. Expected:<" + getExpectedPoint() + "or " + otherExpectedPoint + "> but was:<" + lastInputUsedForThisFunction + ">";
            }
        };
        testLastPointConsistency(moveDelta, -2, assertPointLocationFunction);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency (when the last segment is removed).
     */
    public void _testLastPointConsistencyWithLastSegmentRemoval() {
        final Point moveDelta = new Point(-120, 50);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta) {
            @Override
            public Point getExpectedPoint() {
                return new Point(previousMergedPoint.x, movedNodeBounds.getTranslated(moveDelta).y);
            }
        };
        testLastPointConsistency(moveDelta, -1, assertPointLocationFunction);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency (when the last segment is inverted).
     */
    public void _testLastPointConsistencyWithLastSegmentInverted() {
        final Point moveDelta = new Point(-340, 0);
        AssertPointLocationFunction assertPointLocationFunction = new AssertPointLocationFunction(moveDelta) {
            @Override
            public Point getExpectedPoint() {
                return originalPoint.getTranslated(moveDelta).getTranslated(new Point(movedNodeBounds.width(), 0));
            }
        };
        testLastPointConsistency(moveDelta, 0, assertPointLocationFunction);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency.
     * 
     * @param moveDelta
     *            The delta from which the source node will be moved
     * @param nbGMFPointsDelta
     *            Number of GMF points that are added (or removed) after the
     *            move.
     * @param assertPointLocationFunction
     *            the function to use to check the expected last point location
     *            after move
     */
    private void testLastPointConsistency(Point moveDelta, int nbGMFPointsDelta, AssertPointLocationFunction assertPointLocationFunction) {
        testLastPointConsistency(moveDelta, nbGMFPointsDelta, assertPointLocationFunction, false);
    }

    /**
     * Test that last point is moved has expected and that draw2d and GMF last
     * points are consistency.
     * 
     * @param moveDelta
     *            The delta from which the source node will be moved
     * @param nbGMFPointsDelta
     *            Number of GMF points that are added (or removed) after the
     *            move.
     * @param assertPointLocationFunction
     *            the function to use to check the expected last point location
     *            after move
     * @param edgeWithOnly2Points
     *            true if the edge has only two points, false otherwise
     */
    private void testLastPointConsistency(Point moveDelta, int nbGMFPointsDelta, AssertPointLocationFunction assertPointLocationFunction, boolean edgeWithOnly2Points) {
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
        if (edgeWithOnly2Points) {
            assertPointLocationFunction.setData(previousPoints.getLastPoint(), previousPoints.getFirstPoint(), nodeBounds);
        } else {
            assertPointLocationFunction.setData(previousPoints.getLastPoint(), previousPoints.getPoint(previousPoints.size() - 3), nodeBounds);
        }
        compareActualBendpointsWithExpected(editor, connectionEditPart, previousPoints, moveDelta, nodeBounds, false, nbGMFPointsDelta, assertPointLocationFunction);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency.
     * 
     * @param moveDelta
     *            The delta from which the source node will be moved
     * @param nbGMFPointsDelta
     *            Number of GMF points that are added (or removed) after the
     *            move.
     * @param assertPointLocationFunction
     *            the function to use to check the expected last point location
     *            after move
     */
    private void testFirstPointConsistency(Point moveDelta, int nbGMFPointsDelta, AssertPointLocationFunction assertPointLocationFunction) {
        testFirstPointConsistency(moveDelta, nbGMFPointsDelta, assertPointLocationFunction, false);
    }

    /**
     * Test that first point is moved has expected and that draw2d and GMF last
     * points are consistency.
     * 
     * @param moveDelta
     *            The delta from which the source node will be moved
     * @param nbGMFPointsDelta
     *            Number of GMF points that are added (or removed) after the
     *            move.
     * @param assertPointLocationFunction
     *            the function to use to check the expected last point location
     *            after move
     * @param edgeWithOnly2Points
     *            true if the edge has only two points, false otherwise
     */
    private void testFirstPointConsistency(Point moveDelta, int nbGMFPointsDelta, AssertPointLocationFunction assertPointLocationFunction, boolean edgeWithOnly2Points) {
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
        if (edgeWithOnly2Points) {
            assertPointLocationFunction.setData(previousPoints.getFirstPoint(), previousPoints.getLastPoint(), nodeBounds);
        } else {
            assertPointLocationFunction.setData(previousPoints.getFirstPoint(), previousPoints.getPoint(2), nodeBounds);
        }
        compareActualBendpointsWithExpected(editor, connectionEditPart, previousPoints, moveDelta, nodeBounds, true, nbGMFPointsDelta, assertPointLocationFunction);
    }

    // Check the first or last bendpoint of the connection.
    private void compareActualBendpointsWithExpected(SWTBotSiriusDiagramEditor diagramEditor, SWTBotGefConnectionEditPart connectionEditPart, PointList previousPoints, Point moveDelta,
            Rectangle movedNodeBounds, boolean firstPoint, int nbGMFPointsDelta, AssertPointLocationFunction assertPointLocationFunction) {
        List<Point> newGMFBendpointsFromSource = GMFHelper.getPointsFromSource(connectionEditPart.part());
        String messagePrefix = "First";
        if (!firstPoint) {
            messagePrefix = "Last";
        }
        assertEquals("We should have a delta of " + nbGMFPointsDelta + " GMF points.", previousPoints.size() + nbGMFPointsDelta, newGMFBendpointsFromSource.size());

        Point previousDraw2dPoint;
        Point currentDraw2dPoint;
        Point currentGmfPoint;
        PointList actualBendPoints = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();
        if (firstPoint) {
            previousDraw2dPoint = previousPoints.getFirstPoint();
            currentDraw2dPoint = actualBendPoints.getFirstPoint();
            currentGmfPoint = newGMFBendpointsFromSource.get(0);
        } else {
            previousDraw2dPoint = previousPoints.getLastPoint();
            currentDraw2dPoint = actualBendPoints.getLastPoint();
            currentGmfPoint = newGMFBendpointsFromSource.get(newGMFBendpointsFromSource.size() - 1);
        }
        // The first (or last) bendpoint should have moved.
        assertNotEquals(messagePrefix + " point should have moved", previousDraw2dPoint, currentDraw2dPoint);
        if (!assertPointLocationFunction.apply(currentDraw2dPoint)) {
            fail(assertPointLocationFunction.getErrorMessage());
        }
        // The draw2d and GMF point should be the same.
        assertEquals(messagePrefix + " draw2d and GMF points should be the same", currentDraw2dPoint, currentGmfPoint);
    }
}
