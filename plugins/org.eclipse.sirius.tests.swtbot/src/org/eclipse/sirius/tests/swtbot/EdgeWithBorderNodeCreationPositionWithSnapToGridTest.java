/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.NoSuchElementException;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.collect.Iterables;

/**
 * Same tests as {@link EdgeCreationPositionTest} but with snapToGrid enabled.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EdgeWithBorderNodeCreationPositionWithSnapToGridTest extends EdgeCreationPositionTest {

    private static final double gridStep = 100;

    @Override
    protected void openDiagram(String name) {
        super.openDiagram(name);
        editor.setSnapToGrid(true, gridStep, 2);
    }

    @Override
    protected String getCreateEdgeToolName() {
        return "SuperWithBorderNode";
    }

    @Override
    protected DEdgeEditPart getSingleDEdgeFrom(NodeEditPart sourcePart) {
        // Get the new source border node
        AbstractDiagramBorderNodeEditPart sourceBorderNode = getBorderNode(sourcePart);
        assertEquals(1, sourceBorderNode.getSourceConnections().size());
        ConnectionEditPart edge = (ConnectionEditPart) sourceBorderNode.getSourceConnections().get(0);
        assertTrue(edge instanceof DEdgeEditPart);
        return (DEdgeEditPart) edge;
    }

    private AbstractDiagramBorderNodeEditPart getBorderNode(EditPart parent) {
        AbstractDiagramBorderNodeEditPart result = null;
        try {
            result = Iterables.getOnlyElement(Iterables.filter(parent.getChildren(), AbstractDiagramBorderNodeEditPart.class));
        } catch (NoSuchElementException e) {
            fail("There is no border node created on source.");
        } catch (IllegalArgumentException e) {
            fail("There should be only one border node created on source.");
        }
        return result;
    }

    @Override
    protected void assertAreValidAnchors(IGraphicalEditPart source, IGraphicalEditPart target, DEdgeEditPart edge) {
        // Get the new source border node
        IGraphicalEditPart sourceBorderNode = getBorderNode(source);
        // Get the new target border node
        IGraphicalEditPart targetBorderNode = getBorderNode(target);

        super.assertAreValidAnchors(sourceBorderNode, targetBorderNode, edge);
        // Check that at least
        // * the x coordinate of the border node is on the grid
        // * or the y coordinate of the border node is on the grid
        // * or one of them is the same as parent (case when the grid is outside
        // the
        // node).
        assertTrue("For starting point, the x coordinate should be on the grid or the y coordinate should be on the grid or at least one should be the same as parent.",
                checkLocation(GraphicalHelper.getAbsoluteBoundsIn100Percent(sourceBorderNode).getTopLeft(), source));
        assertTrue("For ending point, the x coordinate should be on the grid or the y coordinate should be on the grid or at least one should be the same as parent.",
                checkLocation(GraphicalHelper.getAbsoluteBoundsIn100Percent(targetBorderNode).getTopLeft(), target));
    }

    private boolean checkLocation(Point location, IGraphicalEditPart parentPart) {
        boolean result = (location.x % gridStep) == 0 || (location.y % gridStep) == 0;
        if (!result) {
            Rectangle parentBounds = GraphicalHelper.getAbsoluteBoundsIn100Percent(parentPart);
            result = (location.x == parentBounds.x || location.x == (parentBounds.x + parentBounds.width)) || (location.y == parentBounds.y || location.y == (parentBounds.y + parentBounds.height));
        }
        return result;
    }
}
