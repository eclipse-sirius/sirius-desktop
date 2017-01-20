/*******************************************************************************
 * Copyright (c) 2015, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;



import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckBoundsCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Tests for the move edge group feature. See #471104 for more details.
 * 
 * @author Florian Barbin
 *
 */
public class MoveEdgeGroupTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String SEMANTIC_RESOURCE_NAME = "moveEdgeGroup.migrationmodeler";

    private static final String SESSION_RESOURCE_NAME = "moveEdgeGroup.aird";

    private static final String MODELER_RESOURCE_NAME = "moveEdgeGroup.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/moveEdgeGroup/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_NAME, SESSION_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        // Close the outline view
        bot.viewById("org.eclipse.ui.views.ContentOutline").close();
        SWTBotUtils.waitAllUiEvents();

        // Open the session
        UIResource sessionAirdResource = new UIResource(designerProject, SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        // Open the editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "useCase", "diagram1", DDiagram.class, true, false);
        editor.maximize();
    }

    @Override
    protected void tearDown() throws Exception {
        if (editor != null) {
            editor.zoom(ZoomLevel.ZOOM_100);
            editor.restore();
            editor.scrollTo(0, 0);
        }
        // Close the editor before opening the outline
        if (editor != null) {
            editor.close();
            SWTBotUtils.waitAllUiEvents();
        }

        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Tests that moving a rectilinear edge group works properly for the
     * following cases:
     * <ul>
     * <li>By selecting a bendpoint</li>
     * <li>By selecting a segment</li>
     * <li>In case of conflict with others border nodes, the command is not
     * applied.</li>
     * </ul>
     */
    public void testMoveRectilinearEdgeGroup() {
        moveBendpoint(ZoomLevel.ZOOM_100, "edge1", new Point(-20, -20), PositionConstants.HORIZONTAL, true);
        moveSegment(ZoomLevel.ZOOM_100, new Point(-20, -20), PositionConstants.HORIZONTAL, true, "edge1");
        moveSegment(ZoomLevel.ZOOM_100, new Point(15, -20), PositionConstants.HORIZONTAL, false, "edge2");
    }

    /**
     * Tests that moving a rectilinear edge group works properly for the same
     * cases than above but with a 125% zoom.
     */
    public void testMoveRectilinearEdgeGroupZoom125() {
        moveBendpoint(ZoomLevel.ZOOM_125, "edge1", new Point(-20, -20), PositionConstants.HORIZONTAL, true);
        moveSegment(ZoomLevel.ZOOM_125, new Point(-20, -20), PositionConstants.HORIZONTAL, true, "edge1");
        moveSegment(ZoomLevel.ZOOM_125, new Point(15, -20), PositionConstants.HORIZONTAL, false, "edge2");
    }
    
    /**
     * Tests that moving an oblique edge group works properly for the following
     * cases:
     * <ul>
     * <li>By selecting a bendpoint</li>
     * <li>By selecting a segment:
     * <ul>
     * <li>In case of conflict with others border nodes, the command is not
     * applied.</li>
     * <li>In case of authorized move.</li>
     * </ul>
     * </li>
     * </ul>
     */
    public void testMoveObliqueEdgeGroup() {
        moveBendpoint(ZoomLevel.ZOOM_100, "edge4", new Point(-10, -10), PositionConstants.VERTICAL, true);
        moveSegment(ZoomLevel.ZOOM_100, new Point(-25, -25), PositionConstants.VERTICAL, false, "edge3");
        moveSegment(ZoomLevel.ZOOM_100, new Point(-10, -10), PositionConstants.VERTICAL, true, "edge3");
    }


    /**
     * Tests that moving an oblique edge group works properly with a 125% zoom.
     */
    public void testMoveObliqueEdgeGroupZoom125() {
        moveBendpoint(ZoomLevel.ZOOM_125, "edge4", new Point(-10, -10), PositionConstants.VERTICAL, true);
        moveSegment(ZoomLevel.ZOOM_125, new Point(-25, -25), PositionConstants.VERTICAL, false, "edge3");
        moveSegment(ZoomLevel.ZOOM_125, new Point(-10, -10), PositionConstants.VERTICAL, true, "edge3");
    }

    private void moveSegment(ZoomLevel zoomLevel, Point delta, int expectedAxis, boolean authorized, String name) {
        editor.zoom(zoomLevel);
        editor.scrollTo(0, 0);


        SWTBotGefEditPart elementToMove = editor.getEditPart(name, AbstractDiagramEdgeEditPart.class);

        // Select the element to move
        editor.select(elementToMove);

        // Get point to move
        assertTrue(elementToMove.part() instanceof ConnectionEditPart);
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) elementToMove.part();
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        Point firstPoint = pointList.getPoint(0);
        Point secondPoint = pointList.getPoint(1);
        Point pointToMove = new Point();
        if (firstPoint.x == secondPoint.x) {
            pointToMove.x = firstPoint.x;
            pointToMove.y = Math.round(firstPoint.y + ((secondPoint.y - firstPoint.y) / 2));
        } else {
            pointToMove.y = firstPoint.y;
            pointToMove.x = Math.round(firstPoint.x + ((secondPoint.x - firstPoint.x) / 2));
        }

        dragPoint(zoomLevel, name, pointToMove, delta, expectedAxis, authorized);
        // Move to initial location
        undo(localSession.getOpenedSession());
        editor.scrollTo(0, 0);
    }

    private void moveBendpoint(ZoomLevel zoomLevel, String name, Point delta, int expectedAxis, boolean authorized) {
        editor.zoom(zoomLevel);
        editor.scrollTo(0, 0);

        SWTBotGefEditPart elementToMove = editor.getEditPart(name, AbstractDiagramEdgeEditPart.class);

        // Select the element to move
        editor.select(elementToMove);

        // Get the bendpoint to move
        assertTrue(elementToMove.part() instanceof ConnectionEditPart);
        ConnectionEditPart connectionEditPart = (ConnectionEditPart) elementToMove.part();
        assertTrue(connectionEditPart.getFigure() instanceof ViewEdgeFigure);
        PointList pointList = ((ViewEdgeFigure) connectionEditPart.getFigure()).getPoints().getCopy();
        Point pointToMove = pointList.getPoint(1);

        dragPoint(zoomLevel, name, pointToMove, delta, expectedAxis, authorized);
        // Move to initial location
        undo(localSession.getOpenedSession());
        editor.scrollTo(0, 0);

    }

    /**
     * Drags a point from the given start by applying the given delta.
     * 
     * @param zoomLevel
     *            the current zoom level.
     * @param name
     *            the edit part name.
     * @param start
     *            the start point.
     * @param delta
     *            the move delta.
     * @param expectedAxis
     *            the expected move axis (HORIZONTAL or VERTICAL)
     * @param noConflicts
     *            true if no conflicts are expected. If false, the move edge
     *            group should not be authorized and should retrieve its initial
     *            location.
     */
    private void dragPoint(ZoomLevel zoomLevel, String name, Point start, Point delta, int expectedAxis, boolean noConflicts) {
        boolean snapToGrid = editor.isSnapToGrid();
        boolean snapToShape = editor.isSnapToShape();
        try {
            editor.setSnapToGrid(false);
            editor.setSnapToShape(false);
            SWTBotGefEditPart elementToMove = editor.getEditPart(name, AbstractDiagramEdgeEditPart.class);

            // Select the element to move
            editor.select(elementToMove);

            assertTrue(elementToMove.part() instanceof ConnectionEditPart);
            ConnectionEditPart connectionEditPart = (ConnectionEditPart) elementToMove.part();

            // Get connection ends bounds
            IGraphicalEditPart source = (IGraphicalEditPart) connectionEditPart.getSource();
            Rectangle sourceInitalBounds = source.getFigure().getBounds().getCopy();
            source.getFigure().translateToAbsolute(sourceInitalBounds);
            IGraphicalEditPart target = (IGraphicalEditPart) connectionEditPart.getTarget();
            Rectangle targetInitalBounds = target.getFigure().getBounds().getCopy();
            target.getFigure().translateToAbsolute(targetInitalBounds);

            final Point endpoint = new Point(start.x + delta.x, start.y + delta.y);

            start.scale(zoomLevel.getAmount());
            endpoint.scale(zoomLevel.getAmount());

            // Move with F3 key pressed
            final AtomicBoolean dragFinished = new AtomicBoolean(false);
            editor.dragWithKey(start.x, start.y, endpoint.x, endpoint.y, SWT.F3, dragFinished);
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

            Point pointToTranslate = delta.getCopy();
            if (PositionConstants.HORIZONTAL == expectedAxis) {
                pointToTranslate.y = 0;
            } else {
                pointToTranslate.x = 0;
            }
            Rectangle expectedSourceBounds = sourceInitalBounds.getCopy();
            Rectangle expectedTargetBounds = targetInitalBounds.getCopy();
            if (noConflicts) {
                pointToTranslate.scale(zoomLevel.getAmount());
                expectedSourceBounds.translate(pointToTranslate);
                expectedTargetBounds.translate(pointToTranslate);
            }
            bot.waitUntil(new CheckBoundsCondition(source, expectedSourceBounds, false, false));
            bot.waitUntil(new CheckBoundsCondition(target, expectedTargetBounds, false, false));
        } finally {
            editor.setSnapToGrid(snapToGrid);
            editor.setSnapToShape(snapToShape);
        }
    }

}
