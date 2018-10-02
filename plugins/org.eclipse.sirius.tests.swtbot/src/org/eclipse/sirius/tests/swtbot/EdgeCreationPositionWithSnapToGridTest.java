/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;

/**
 * Same tests as {@link EdgeCreationPositionTest} but with snapToGrid enabled.
 * 
 * @author lredor
 */
public class EdgeCreationPositionWithSnapToGridTest extends EdgeCreationPositionTest {

    private static final double gridStep = 100;

    @Override
    protected void openDiagram(String name) {
        super.openDiagram(name);
        editor.setSnapToGrid(true, gridStep, 2);
    }

    @Override
    protected void openDiagram(String name, ZoomLevel zoomLevel) {
        super.openDiagram(name);
        editor.setSnapToGrid(true, gridStep, 2);
        editor.zoom(zoomLevel);
    }

    @Override
    protected void assertAreValidAnchors(IGraphicalEditPart source, PrecisionPoint sourcePosition, IGraphicalEditPart target, PrecisionPoint targetPosition, DEdgeEditPart edge) {
        super.assertAreValidAnchors(source, sourcePosition, target, targetPosition, edge);
        // Check that at least
        // * the x coordinate is on the grid
        // * or the y coordinate is on the grid
        // * or both are the same as parent (case when the grid is outside the
        // node).
        Connection connectionFigure = edge.getConnectionFigure();
        PointList figurePoints = connectionFigure.getPoints();
        assertTrue("For starting point, the x coordinate should be on the grid or the y coordinate should be on the grid or both should be the same as parent.",
                checkLocation(figurePoints.getFirstPoint(), source));
        assertTrue("For ending point, the x coordinate should be on the grid or the y coordinate should be on the grid or both should be the same as parent.",
                checkLocation(figurePoints.getLastPoint(), target));
    }

    private boolean checkLocation(Point location, IGraphicalEditPart parentPart) {
        boolean result = (location.x % gridStep) == 0 || (location.y % gridStep) == 0;
        if (!result) {
            Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart, true);
            result = (location.x == parentBounds.x || location.x == (parentBounds.x + parentBounds.width)) && (location.y == parentBounds.y || location.y == (parentBounds.y + parentBounds.height));
        }
        return result;
    }
}
