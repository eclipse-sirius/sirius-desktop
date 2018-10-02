/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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

import java.util.NoSuchElementException;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.business.internal.query.DEdgeQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import com.google.common.collect.Iterables;

/**
 * Same tests as {@link EdgeCreationPositionTest} but with border nodes at the
 * ends of the edge.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class EdgeWithBorderNodeCreationPositionTest extends EdgeCreationPositionTest {

    /**
     * Possibility to override computed value in
     * {@link #checkSide(PrecisionPoint, IGraphicalEditPart, int)} by a specific
     * one. This value must be reset to PositionConstants.NONE at the end of the
     * test it it is changed.
     */
    protected int sourceSide = PositionConstants.NONE;

    /**
     * Possibility to override computed value in
     * {@link #checkSide(PrecisionPoint, IGraphicalEditPart, int)} by a specific
     * one. This value must be reset to PositionConstants.NONE at the end of the
     * test it it is changed.
     */
    protected int targetSide = PositionConstants.NONE;

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

    /**
     * Get the only one border node of this parent.
     * 
     * @param parent
     *            The edit part containing the border node
     * @return the only one border node of this parent (fail otherwise)
     */
    protected AbstractDiagramBorderNodeEditPart getBorderNode(EditPart parent) {
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
    protected void assertAreValidAnchors(IGraphicalEditPart source, PrecisionPoint sourcePosition, IGraphicalEditPart target, PrecisionPoint targetPosition, DEdgeEditPart edge) {
        // Get the new source border node
        IGraphicalEditPart sourceBorderNode = getBorderNode(source);
        // Get the new target border node
        IGraphicalEditPart targetBorderNode = getBorderNode(target);

        super.assertAreValidAnchors(sourceBorderNode, sourcePosition, targetBorderNode, targetPosition, edge);

        // Check that the side is as expected ( for edge creation with border
        // nodes, the side must be the at least
        checkSide(sourcePosition, sourceBorderNode, sourceSide);
        checkSide(targetPosition, targetBorderNode, targetSide);
    }

    @Override
    protected void assertAreValidAnchorsAndBendpoints(IGraphicalEditPart source, IGraphicalEditPart target, DEdgeEditPart edge) {
        // The super method is only called for oblique edges as for rectilinear
        // and tree the intersection points are not the same.
        if (new DEdgeQuery((DEdge) edge.resolveSemanticElement()).getRouting().equals(Routing.MANUAL_LITERAL)) {
            super.assertAreValidAnchorsAndBendpoints(source, target, edge);
        }
    }

    private void checkSide(PrecisionPoint location, IGraphicalEditPart borderNodeEditPart, int expectedSide) {
        if (expectedSide == PositionConstants.NONE) {
            expectedSide = getExpectedSide(location, borderNodeEditPart);
        }
        assertTrue("The edit part should be a IBorderItemEditPart.", borderNodeEditPart instanceof IBorderItemEditPart);
        final IBorderItemEditPart borderPart = (IBorderItemEditPart) borderNodeEditPart;
        final IBorderItemLocator borderItemLocator = borderPart.getBorderItemLocator();
        bot.waitUntil(new ICondition() {
            
            @Override
            public boolean test() throws Exception {
                return borderItemLocator.getCurrentSideOfParent() != PositionConstants.NSEW;
            }
            
            @Override
            public void init(SWTBot bot) {
            }
            
            @Override
            public String getFailureMessage() {
                return "The current side of parent is not set in BorderItemLocator.";
            }
        });
        assertEquals("The border node side is not correct.", expectedSide, borderItemLocator.getCurrentSideOfParent());
    }

    /**
     * Return the expected side according to a location.
     * 
     * @param location
     *            A location expressed in a ratio compared to the container
     *            (from 0 to 1).
     */
    private int getExpectedSide(PrecisionPoint location, IGraphicalEditPart borderNodeEditPart) {
        Dimension parentFigureSize = ((IGraphicalEditPart) borderNodeEditPart.getParent()).getFigure().getSize();
        double horizontalRatio = parentFigureSize.preciseWidth() * location.preciseX();
        double verticalRatio = parentFigureSize.preciseHeight() * location.preciseY();
        if (location.preciseX() > 0.5) {
            horizontalRatio = parentFigureSize.preciseWidth() - (parentFigureSize.preciseWidth() * location.preciseX());
        }
        if (location.preciseY() > 0.5) {
            verticalRatio = parentFigureSize.preciseHeight() - (parentFigureSize.preciseHeight() * location.preciseY());
        }

        int expectedSide = PositionConstants.NONE;
        if (location.preciseX() < 0.5) {
            if (horizontalRatio < verticalRatio) {
                expectedSide = PositionConstants.WEST;
            } else if (location.preciseY() < 0.5) {
                expectedSide = PositionConstants.NORTH;
            } else {
                expectedSide = PositionConstants.SOUTH;
            }
        } else if (horizontalRatio < verticalRatio) {
            expectedSide = PositionConstants.EAST;
        } else if (location.preciseY() < 0.5) {
            expectedSide = PositionConstants.NORTH;
        } else {
            expectedSide = PositionConstants.SOUTH;
        }
        return expectedSide;
    }
}
