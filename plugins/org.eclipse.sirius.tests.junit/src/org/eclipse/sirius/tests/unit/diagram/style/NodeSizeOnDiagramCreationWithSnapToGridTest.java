/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.style;

import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.AbstractDNodeQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;

/**
 * Compared to {@link NodeSizeOnDiagramCreationTest}, the created elements size is snapped to the grid. The size, width
 * and height, must be a multiple of 17 ({@link AbstractNodeSizeTest#getGridSpacing()}.
 * 
 * @author lredor
 */
public class NodeSizeOnDiagramCreationWithSnapToGridTest extends NodeSizeOnDiagramCreationTest {
    @Override
    protected boolean isSnapToGrid() {
        return true;
    }

    @Override
    protected void doTest(String label, Optional<DiagramElementType> nodeType, Optional<DiagramElementType> parentType) throws Exception {
        if (!"Workspace_Image_new EClass 1".equals(label)) {
            // Workspace image is not tested. The ratio of the image is kept so it overrides the snap.
            super.doTest(label, nodeType, parentType);
        }
    }

    @Override
    protected void validateNotationSize(DNode node) {
        Node gmfNode = getGmfNode(node);
        assertNotNull(gmfNode);

        Size size = (Size) gmfNode.getLayoutConstraint();

        int axisToCkeck = axisToCkeck(node, gmfNode);
        if ((axisToCkeck & PositionConstants.HORIZONTAL) != 0) {
            assertTrue("The notation node " + node.getName() + " do not have the expect width: " + size.getWidth() + " is not a multiple of " + getGridSpacing(),
                    size.getWidth() % getGridSpacing() == 0);
        }
        if ((axisToCkeck & PositionConstants.VERTICAL) != 0) {
            assertTrue("The notation node " + node.getName() + " do not have the expect height: " + size.getHeight() + " is not a multiple of " + getGridSpacing(),
                    size.getHeight() % getGridSpacing() == 0);
        }
    }

    @Override
    protected void validateDraw2DSize(DNode node) {
        IGraphicalEditPart editPart = getEditPart(node);
        assertNotNull(editPart);

        IFigure figure = editPart.getFigure();
        assertNotNull(figure);
        Rectangle bounds = figure.getBounds();

        int axisToCkeck = axisToCkeck(node);
        if ((axisToCkeck & PositionConstants.HORIZONTAL) != 0) {
            assertTrue("The figure node " + node.getName() + " do not have the expect width: " + bounds.width + " is not a multiple of " + getGridSpacing(), bounds.width % getGridSpacing() == 0);
        }
        if ((axisToCkeck & PositionConstants.VERTICAL) != 0) {
            assertTrue("The figure node " + node.getName() + " do not have the expect height: " + bounds.height + " is not a multiple of " + getGridSpacing(), bounds.height % getGridSpacing() == 0);
        }
    }

    private int axisToCkeck(DNode node) {
        Node gmfNode = getGmfNode(node);
        assertNotNull(gmfNode);
        return axisToCkeck(node, gmfNode);
    }

    private int axisToCkeck(DNode node, Node gmfNode) {
        int axisToCkeck = PositionConstants.HORIZONTAL | PositionConstants.VERTICAL;
        if (new AbstractDNodeQuery(node).isBorderedNode() && gmfNode.eContainer() instanceof Node parent) {
            // Use draw2d figure to compute border side
            IGraphicalEditPart editPart = getEditPart(node);
            assertNotNull(editPart);
            IFigure figure = editPart.getFigure();
            assertNotNull(figure);
            Rectangle nodeBounds = figure.getBounds();

            IGraphicalEditPart parentEditPart = getEditPart((DDiagramElement) parent.getElement());
            assertNotNull(parentEditPart);
            IFigure parentFigure = editPart.getFigure();
            assertNotNull(parentFigure);
            Rectangle parentBounds = parentFigure.getBounds();

            int borderSide = CanonicalDBorderItemLocator.findClosestSideOfParent(nodeBounds, parentBounds);
            switch (borderSide) {
            case PositionConstants.NORTH:
            case PositionConstants.SOUTH:
                axisToCkeck = PositionConstants.HORIZONTAL;
                break;
            case PositionConstants.EAST:
            case PositionConstants.WEST:
                axisToCkeck = PositionConstants.VERTICAL;
                break;
            }
        }
        return axisToCkeck;
    }
}
