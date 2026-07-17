/*******************************************************************************
 * Copyright (c) 2025, 2026 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.refresh;

import java.util.Collections;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;

/**
 * Helper class to compute node location during diagram synchronization.
 * 
 * @author <a href="mailto:nicolas.perans@obeo.fr">Nicolas Peransin</a>
 */
public class NodePositionHelper {

    /**
     * True if the snap to grid is considered as activated. In this case, the returned location, by
     * {@link #getValidLocation(Rectangle, Node, Collection<Node>)}, is snapped to the grid (if possible).
     */
    private final boolean snapToGrid;

    /**
     * The grid step, expected to be strictly positive. Only used when {@code snapToGrid} is true.
     */
    private final int gridSpacing;

    /**
     * Default constructor.
     * <p>
     * If {@code snapToGrid } value is invalid (not strictly positive), the Snap-To-Grid behavior is
     * disabled whatever the value of {@code snapToGrid }.
     * </p>
     * 
     * @param snapToGrid
     *            flag of current diagram regarding element alignment.
     * @param gridSpacing
     *            grid spacing of the current diagram.
     */
    public NodePositionHelper(boolean snapToGrid, int gridSpacing) {
        this.snapToGrid = snapToGrid 
                // Protection against 0 division and invalid computations.
                && gridSpacing > 0;
        
        this.gridSpacing = gridSpacing;
    }

    /**
     * Computes the position of border node using location and size from parent interaction.
     * 
     * @param node
     *            GMF border node
     * @param contextLocation
     *            user-selected location
     * @param size
     *            user-provided size (maybe null)
     * @return location of border
     */
    public Point getOnBorderPositionFromParent(Node node, Point contextLocation, Dimension size) {
        AbstractDNode port = (AbstractDNode) node.getElement();
        Node parent = (Node) node.eContainer();

        CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parent, PositionConstants.NSEW, snapToGrid, gridSpacing);
        Rectangle parentBorderBounds = getParentAbsoluteBorderBounds(parent);
        locator.setParentBorderBounds(parentBorderBounds);
        Dimension borderOffsets = IBorderItemOffsets.DEFAULT_OFFSET;
        if (new ViewQuery(node).isForNameEditPart()) {
            borderOffsets = IBorderItemOffsets.NO_OFFSET;
        } else if (new DDiagramElementQuery(port).isIndirectlyCollapsed()) {
            borderOffsets = IBorderItemOffsets.COLLAPSE_FILTER_OFFSET;
        }
        locator.setBorderItemOffset(borderOffsets);

        Rectangle constraint = new Rectangle(
                // Safe location
                contextLocation != null ? contextLocation : new Point(0, 0),
                // Dimension is required for Border Item Locator
                size != null ? size : getBorderNodeDefaultSize(port));
        locator.setConstraint(constraint);
        final Rectangle dummyBounds = new Rectangle(constraint);
        Point parentAbsoluteLocation = parentBorderBounds.getLocation();
        dummyBounds.translate(parentAbsoluteLocation);

        final Point realLocation = locator.getValidLocation(dummyBounds, node, Collections.singleton(node));
        final Dimension d = realLocation.getDifference(parentAbsoluteLocation);
        final Point location = new Point(d.width, d.height);
        realLocation.setLocation(location);

        locator.relocate(node);
        return location;
    }

    /**
     * Computes the position of a border node using layoutData created during interaction.
     * 
     * @param node
     *            GMF border node
     * @param layoutData
     *            data of user interaction
     * @return location of border
     */
    public Point getOnBorderPositionFromLayoutData(Node node, LayoutData layoutData) {
        AbstractDNode port = (AbstractDNode) node.getElement();
        Node parent = (Node) node.eContainer();

        // We get the layoutData from the manager directly with the node
        // (drag'n'drop) but this location should be adapt to be correct
        // according to CanonicalDBorderItemLocator.
        final Point location = layoutData.getLocation() != null ? layoutData.getLocation() : new Point(0, 0);

        Dimension size = layoutData.getSize();

        // Compute the best location according to other existing bordered nodes.
        CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parent, PositionConstants.NSEW, snapToGrid, gridSpacing);
        Rectangle parentBorderBounds = getParentAbsoluteBorderBounds(parent);
        locator.setParentBorderBounds(parentBorderBounds);
        Dimension borderOffsets = IBorderItemOffsets.DEFAULT_OFFSET;
        if (port != null && new DDiagramElementQuery(port).isIndirectlyCollapsed()) {
            borderOffsets = IBorderItemOffsets.COLLAPSE_FILTER_OFFSET;
        }
        locator.setBorderItemOffset(borderOffsets);

        // CanonicalDBorderItemLocator works with absolute GMF parent
        // location so we need to translate BorderedNode absolute location.
        final Point parentAbsoluteLocation = parentBorderBounds.getTopLeft();
        final Point realLocation = locator.getValidLocation(new Rectangle(location.getTranslated(parentAbsoluteLocation), size), node, Collections.singleton(node));

        // Compute the new relative position to the parent
        realLocation.translate(parentAbsoluteLocation.negate());

        return realLocation;
    }

    private Dimension getBorderNodeDefaultSize(AbstractDNode abstractDNode) {
        Dimension defaultSize = new Dimension(-1, -1);
        if (abstractDNode instanceof DNode viewNode && !new org.eclipse.sirius.diagram.business.api.query.DNodeQuery(viewNode).isAutoSize()) {
            defaultSize = new DNodeQuery(viewNode).getDefaultDimension();
        }
        return defaultSize;
    }

    /**
     * Returns the dimension of a node corrected by the grid.
     * <p>
     * When the "snap to grid" is enable, user expects the node corner to be matching a grid point.
     * </p>
     * <p>
     * If no size or no grid is provided, the result matches the input {@code size}.
     * </p>
     * <p>
     * The fact that a node cannot be resized is not treated in the computation of the dimension. 
     * The "authorized resize" check must be done by caller.
     * </p>
     * 
     * @param node
     *            GMF element
     * @param size
     *            actual size to adjust
     * @return adjusted dimension
     */
    public Dimension getAdjustedDimension(Node node, Size size) {
        Dimension result = new Dimension(size.getWidth(), size.getHeight());

        if (snapToGrid && gridSpacing > 0) {
            // When snap to grid, node origin is located on grid.
            int enlargedAxis = PositionConstants.HORIZONTAL | PositionConstants.VERTICAL;

            if (isBorderedNode(node.getElement())) {
                enlargedAxis = PositionConstants.NONE;
                if (node.getLayoutConstraint() instanceof Bounds nodeBounds // this node
                        && node.eContainer() instanceof Node parent) {
                    // For Border node, only enlarge the attached dimension.
                    // As there is a small shift inside the component,
                    // adjusting the other dimension is useless:
                    // when moving the element, the size would not match the grid.
                    int borderSide = getPortSide(new Rectangle(nodeBounds.getX(), nodeBounds.getY(), nodeBounds.getWidth(), nodeBounds.getHeight()), getParentRelativeBounds(parent));
                    switch (borderSide) {
                    case PositionConstants.NORTH:
                    case PositionConstants.SOUTH:
                        enlargedAxis = PositionConstants.HORIZONTAL;
                        break;
                    case PositionConstants.EAST:
                    case PositionConstants.WEST:
                        enlargedAxis = PositionConstants.VERTICAL;
                        break;
                    }
                }
            }
            if ((enlargedAxis & PositionConstants.HORIZONTAL) != 0) {
                result.width = extendSizeToGrid(result.width);
            }
            if ((enlargedAxis & PositionConstants.VERTICAL) != 0) {
                result.height = extendSizeToGrid(result.height);
            }
        }
        return result;
    }

    private int extendSizeToGrid(int value) {
        if (value == -1) {
            // no value
            return -1;
        }

        int shift = value % gridSpacing;
        int enlarge = 0;
        if (shift != 0) {
            enlarge = gridSpacing - shift;
        }
        return value + enlarge;
    }

    /**
     * Evaluates if element is a borderedNode.
     * 
     * @param element
     *            the element to check
     * @return true if the element is a bordered node, false otherwise
     */
    public static boolean isBorderedNode(EObject element) {
        return element instanceof DNode // only node
                && element.eContainer() instanceof AbstractDNode parentDNode // in border reference
                && parentDNode.getOwnedBorderedNodes().contains(element);
    }

    /**
     * Evaluates if element is a direct child of {@link DDiagram}.
     * 
     * @param element
     *            the element to check
     * @return true if the element is a direct child of {@link DDiagram}, false otherwise
     */
    public static boolean isTopLevelNode(EObject element) {
        return element.eContainer() instanceof DDiagram dDiagram // only for diagram
                && dDiagram.getOwnedDiagramElements().contains(element);
    }

    /**
     * Evaluates if element is a child of {@link DNodeContainer} but not a bordered node.
     * 
     * @param element
     *            the element to check
     * 
     * @return true if the element is a child of {@link DNodeContainer} but not a bordered node, false otherwise
     */
    public static boolean isInsideNodeContainer(EObject element) {
        return element.eContainer() instanceof DNodeContainer dNodeContainer // only in container
                && dNodeContainer.getOwnedDiagramElements().contains(element);
    }

    private static ResizeKind getNodeResizeKind(Node createdNode) {
        if (createdNode.getElement() instanceof DNode dNode) {
            return dNode.getResizeKind();
        }
        return ResizeKind.NSEW_LITERAL;
    }

    /**
     * Evaluates if a node's width can be changed.
     * 
     * @param node
     *            GMF element
     * @return true if the width can modified
     */
    public static boolean canResizeWidth(Node node) {
        ResizeKind kind = getNodeResizeKind(node);
        return kind == ResizeKind.EAST_WEST_LITERAL || kind == ResizeKind.NSEW_LITERAL;
    }

    /**
     * Evaluates if a node's height can be changed.
     * 
     * @param node
     *            GMF element
     * @return true if the height can modified
     */
    public static boolean canResizeHeight(Node node) {
        ResizeKind kind = getNodeResizeKind(node);
        return kind == ResizeKind.NORTH_SOUTH_LITERAL || kind == ResizeKind.NSEW_LITERAL;
    }

    /**
     * Adjust the location of node when grid is on.
     * <p>
     * If the width, or the height, is not resizable, adjusting the location — i.e., shifting it so that the center of
     * the node aligns with a grid point — helps improve edge alignment.
     * <p>
     * 
     * 
     * @param node
     *            element to move
     * @param location
     *            point where element should be place
     * @return adjusted location
     */
    public Point getAdjustedLocation(Node node, Point location) {
        if (location != null && snapToGrid && node.getLayoutConstraint() instanceof Size size) {
            Point adjustedLocation = location.getCopy().translate(getShiftToCenter(node, location, size, gridSpacing));
            return constrainBorderNodeFreeAxis(node, location, adjustedLocation, size);
        }
        return location;
    }

    private static Point constrainBorderNodeFreeAxis(Node node, Point location, Point adjustedLocation, Size size) {
        if (location != null && isBorderedNode(node.getElement()) && node.eContainer() instanceof Node parent) {
            Rectangle parentBounds = getParentRelativeBounds(parent);
            int borderSide = getPortSide(new Rectangle(location.x(), location.y(), size.getWidth(), size.getHeight()), parentBounds);
            switch (borderSide) {
            case PositionConstants.NORTH:
            case PositionConstants.SOUTH:
                adjustedLocation.x = Math.clamp(adjustedLocation.x, 0, parentBounds.width - size.getWidth());
                break;
            case PositionConstants.EAST:
            case PositionConstants.WEST:
                adjustedLocation.y = Math.clamp(adjustedLocation.y, 0, parentBounds.height - size.getHeight());
                break;
            default:
                break;
            }
        }
        return adjustedLocation;
    }

    private static int shiftLocationToCenter(boolean resizable, int size, int gridSpacing) {
        // Negative shift is less perturbing for user.
        // This way the figure includes the click point.
        // If it was positive, for some regions, the element would appear outside the click point.
        return !resizable && size > 0
                // Halfing before modulo ensures the center is on a point.
                ? -(size / 2 % gridSpacing)
                // no shift
                : 0;
    }

    /**
     * Gets the shift for a node to be centered on a point of the grid, if the node is not resizable.
     * 
     * @param node
     *            to shift
     * @param size
     *            dimension of the node
     * @param gridSpacing
     *            spacing of the grid
     * @return Point with x, y indicating the shift
     */
    public static Point getShiftToCenter(Node node, Size size, int gridSpacing) {
        return getShiftToCenter(node, null, size, gridSpacing);
    }

    /**
     * Gets the shift for a node to be centered on a point of the grid, if the node is not resizable.
     * <p>
     * For border nodes, the shift is applied only on the free axis. The other axis is constrained by the parent side.
     * </p>
     *
     * @param node
     *            to shift
     * @param location
     *            candidate location
     * @param size
     *            dimension of the node
     * @param gridSpacing
     *            spacing of the grid
     * @return Point with x, y indicating the shift
     */
    public static Point getShiftToCenter(Node node, Point location, Size size, int gridSpacing) {
        Point shift = new Point(
                // Horizontal shift
                shiftLocationToCenter(canResizeWidth(node), size.getWidth(), gridSpacing),
                // Vertical shift
                shiftLocationToCenter(canResizeHeight(node), size.getHeight(), gridSpacing));
        if (location != null && isBorderedNode(node.getElement()) && node.eContainer() instanceof Node parent) {
            Rectangle parentBounds = getParentRelativeBounds(parent);
            int borderSide = getPortSide(new Rectangle(location.x(), location.y(), size.getWidth(), size.getHeight()), parentBounds);
            switch (borderSide) {
            case PositionConstants.NORTH:
            case PositionConstants.SOUTH:
                shift.y = 0;
                break;
            case PositionConstants.EAST:
            case PositionConstants.WEST:
                shift.x = 0;
                break;
            default:
                break;
            }
        }
        return shift;
    }

    private static int getPortSide(Rectangle borderNode, Rectangle container) {
        return CanonicalDBorderItemLocator.findClosestSideOfParent(borderNode, container);
    }

    private static Rectangle getParentRelativeBounds(Node parent) {
        Rectangle absoluteBounds = getParentAbsoluteBorderBounds(parent);
        return new Rectangle(0, 0, absoluteBounds.width(), absoluteBounds.height());
    }

    private static Rectangle getParentAbsoluteBorderBounds(Node parent) {
        return GMFHelper.getAbsoluteBounds(parent, true, true, false, false);
    }
}
