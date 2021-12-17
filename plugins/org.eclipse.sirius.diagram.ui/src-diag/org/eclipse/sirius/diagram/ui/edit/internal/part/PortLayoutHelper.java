/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewLocationHint;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewSizeHint;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;

/**
 * An helper for getting the location and the size of a port.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class PortLayoutHelper {
    /**
     * Defautl constructor.
     */
    private PortLayoutHelper() {

    }

    /**
     * Return the default location and the size of this port (under Rectangle
     * object).
     * 
     * @param parentEditPart
     *            The parent edit part of the port
     * @param portNode
     *            The node corresponding to the port
     * @param viewDescriptor
     *            The view descriptor corresponding to the port
     * @return location and the size of this port
     */
    protected static Rectangle getDefaultBounds(final IBorderedShapeEditPart parentEditPart, final DNode portNode, final ViewDescriptor viewDescriptor) {
        Dimension size = ViewSizeHint.getInstance().consumeSize();
        Point location = null;
        if (DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(portNode.getParentDiagram(), portNode)) {
            location = ViewLocationHint.getInstance().consumeLocation(parentEditPart);
        }
        if (size == null) {
            size = LayoutUtils.getDefaultDimension(viewDescriptor);
        }
        if (location == null) {
            location = new Point(0, 0);
        }
        return new Rectangle(location, size);
    }

    /**
     * Get the bounds of the port.
     * 
     * @param parentEditPart
     *            The parent edit part of the port
     * @param portNode
     *            The node representing this port
     * @param viewDescriptor
     *            The view descriptor corresponding to the port
     * @param container
     *            The DDiagramElement container
     * @return the bounds of the port
     */
    public static Rectangle getBounds(final IBorderedShapeEditPart parentEditPart, final DNode portNode, final ViewDescriptor viewDescriptor, final DDiagramElement container) {

        Rectangle finalBounds = null;
        LayoutData layoutData = SiriusLayoutDataManager.INSTANCE.getData(portNode);
        if (layoutData == null) {
            // Check if we are in creation mode and not in drag'n'drop
            // In creation mode we must calculate the best position for the new
            // port
            layoutData = SiriusLayoutDataManager.INSTANCE.getData(portNode, true);
            Rectangle tempBounds;
            if (layoutData != null) {
                // We get the layoutData from the manager with the parent of the
                // node
                tempBounds = PortLayoutHelper.completeLayoutData(layoutData, viewDescriptor);
            } else {
                tempBounds = PortLayoutHelper.getDefaultBounds(parentEditPart, portNode, viewDescriptor);
            }
            // Validate the bounds according to constraint
            finalBounds = new Rectangle(LayoutUtils.getValidLocation(portNode, tempBounds.getLocation(), tempBounds.getSize(), container, parentEditPart.getBorderedFigure().getBorderItemContainer(),
                    parentEditPart.getMainFigure()));
        } else {
            // We get the layoutData from the manager directly with the node
            // (drag'n'drop)
            finalBounds = PortLayoutHelper.completeLayoutData(layoutData, viewDescriptor);
        }
        return finalBounds;

    }

    /**
     * Returns the new rectangle coordinates according to the new expanded
     * dimension. Current bounds and parent border should have the same
     * coordinate origin point.
     * 
     * @param expandedDim
     *            the new dimension of expanded node (this dimension should be
     *            logical, as if the zoom is set to 100%).
     * @param collapsedBounds
     *            the current bounds (this bounds should be logical, as if the
     *            zoom is set to 100%).
     * @param parentBorder
     *            the port parent border.
     * @return the new expanded bounds.
     */
    public static Rectangle getUncollapseCandidateLocation(Dimension expandedDim, Rectangle collapsedBounds, Rectangle parentBorder) {
        Rectangle suggestedBounds = new Rectangle(collapsedBounds.x - (expandedDim.width - collapsedBounds.width) / 2, collapsedBounds.y - (expandedDim.height - collapsedBounds.height) / 2,
                expandedDim.width, expandedDim.height);
        if (parentBorder != null) {
            int side = CanonicalDBorderItemLocator.findClosestSideOfParent(suggestedBounds, parentBorder);
            suggestedBounds = getCorrectLocationOnParentForExpand(suggestedBounds, collapsedBounds, side, IBorderItemOffsets.COLLAPSE_FILTER_OFFSET, IBorderItemOffsets.DEFAULT_OFFSET);
        }
        return suggestedBounds;
    }

    /**
     * Returns the new rectangle coordinates according to the new collapsed
     * dimension. Current bounds and parent border should have the same
     * coordinate origin point.
     * 
     * @param collapsedDim
     *            the new dimension of collapsed node (this dimension should be
     *            logical, as if the zoom is set to 100%).
     * @param expandedBounds
     *            the current bounds before collapsing (this bounds should be
     *            logical, as if the zoom is set to 100%).
     * @param parentBorder
     *            the port parent border.
     * @return the new collapsed bounds.
     */
    public static Rectangle getCollapseCandidateLocation(Dimension collapsedDim, Rectangle expandedBounds, Rectangle parentBorder) {
        Rectangle suggestedBounds = new Rectangle(expandedBounds.x + (expandedBounds.width - collapsedDim.width) / 2, expandedBounds.y + (expandedBounds.height - collapsedDim.height) / 2,
                collapsedDim.width, collapsedDim.height);
        if (parentBorder != null) {
            int side = CanonicalDBorderItemLocator.findClosestSideOfParent(suggestedBounds, parentBorder);
            suggestedBounds = getCorrectLocationOnParentForCollapse(suggestedBounds, expandedBounds, side, IBorderItemOffsets.COLLAPSE_FILTER_OFFSET, IBorderItemOffsets.DEFAULT_OFFSET);
        }
        return suggestedBounds;
    }

    private static Rectangle getCorrectLocationOnParentForExpand(Rectangle suggestedExpandedBounds, Rectangle currentBounds, int side, Dimension collapsedOffset, Dimension expandedOffset) {
        Rectangle correctLocation = new Rectangle(suggestedExpandedBounds);
        switch (side) {
        case PositionConstants.WEST:
            correctLocation.setX(currentBounds.x - (collapsedOffset.width - currentBounds.width) - (suggestedExpandedBounds.width - expandedOffset.width));
            break;
        case PositionConstants.EAST:
            correctLocation.setX(currentBounds.x - (expandedOffset.width - collapsedOffset.width));
            break;
        case PositionConstants.SOUTH:
            correctLocation.setY(currentBounds.y - (expandedOffset.height - collapsedOffset.height));
            break;
        case PositionConstants.NORTH:
            correctLocation.setY(currentBounds.y - (collapsedOffset.height - currentBounds.height) - (suggestedExpandedBounds.height - expandedOffset.height));
            break;

        default:
            break;
        }

        return correctLocation;

    }

    private static Rectangle getCorrectLocationOnParentForCollapse(Rectangle suggestedCollapsedBounds, Rectangle currentBounds, int side, Dimension collapsedOffset, Dimension expandedOffset) {
        Rectangle correctLocation = new Rectangle(suggestedCollapsedBounds);
        switch (side) {
        case PositionConstants.WEST:
            correctLocation.setX(currentBounds.x + currentBounds.width - suggestedCollapsedBounds.width - (expandedOffset.width - collapsedOffset.width));
            break;
        case PositionConstants.EAST:
            correctLocation.setX(currentBounds.x + (expandedOffset.width - collapsedOffset.width));
            break;
        case PositionConstants.SOUTH:
            correctLocation.setY(currentBounds.y + (expandedOffset.height - collapsedOffset.height));
            break;
        case PositionConstants.NORTH:
            correctLocation.setY(currentBounds.y + currentBounds.height - suggestedCollapsedBounds.height - (expandedOffset.height - collapsedOffset.height));
            break;

        default:
            break;
        }
        return correctLocation;

    }

    /**
     * Complete the layoutData with default location or default size if one is
     * missing.
     * 
     * @param layoutData
     *            The layout data associates with this port
     * @param viewDescriptor
     *            The view descriptor corresponding to the port
     * @return a rectangle representing the location and the position of the
     *         port
     */
    private static Rectangle completeLayoutData(final LayoutData layoutData, final ViewDescriptor viewDescriptor) {
        final Point location = layoutData.getLocation() != null ? layoutData.getLocation() : new Point(0, 0);
        final Dimension size = layoutData.getSize() != null ? layoutData.getSize() : LayoutUtils.getDefaultDimension(viewDescriptor);
        return new Rectangle(location, size);
    }
}
