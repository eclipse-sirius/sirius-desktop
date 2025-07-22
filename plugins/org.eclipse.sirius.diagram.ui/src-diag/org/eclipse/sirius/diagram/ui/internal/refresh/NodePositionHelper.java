/*******************************************************************************
 * Copyright (c) 2025 THALES GLOBAL SERVICES and others.
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
     * 
     * @param snapToGrid
     *            flag of current diagram regarding element alignment.
     * @param gridSpacing
     *            space of grid point of current diagram .
     */
    public NodePositionHelper(boolean snapToGrid, int gridSpacing) {
        this.snapToGrid = snapToGrid;
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
                size != null ? size : getBorderDefaultSize(port));
        locator.setConstraint(constraint);
        final Rectangle dummyBounds = new Rectangle(constraint);
        Point parentAbsoluteLocation = GMFHelper.getAbsoluteLocation(parent, true, false);
        dummyBounds.translate(parentAbsoluteLocation);

        final Point realLocation = locator.getValidLocation(dummyBounds, node, Collections.singleton(node));
        final Dimension d = realLocation.getDifference(parentAbsoluteLocation);
        final Point location = new Point(d.width, d.height);
        realLocation.setLocation(location);

        locator.relocate(node);
        return location;
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
    public Point getOnBorderPositionFromLayoutData(Node node, LayoutData layoutData) {
        AbstractDNode port = (AbstractDNode) node.getElement();
        Node parent = (Node) node.eContainer();

        // We get the layoutData from the manager directly with the node
        // (drag'n'drop) but this location should be adapt to be correct
        // according to CanonicalDBorderItemLocator.
        final Point location = layoutData.getLocation() != null ? layoutData.getLocation() : new Point(0, 0);

        Dimension size = layoutData.getSize();

        // Compute the best location according to other existing bordered nodes.
        CanonicalDBorderItemLocator locator = new CanonicalDBorderItemLocator(parent, PositionConstants.NSEW);
        Dimension borderOffsets = IBorderItemOffsets.DEFAULT_OFFSET;
        if (port != null && new DDiagramElementQuery(port).isIndirectlyCollapsed()) {
            borderOffsets = IBorderItemOffsets.COLLAPSE_FILTER_OFFSET;
        }
        locator.setBorderItemOffset(borderOffsets);

        // CanonicalDBorderItemLocator works with absolute GMF parent
        // location so we need to translate BorderedNode absolute location.
        final Point parentAbsoluteLocation = GMFHelper.getAbsoluteBounds(parent, false, false, false, false).getTopLeft();
        final Point realLocation = locator.getValidLocation(new Rectangle(location.getTranslated(parentAbsoluteLocation), size), node, Collections.singleton(node));

        // Compute the new relative position to the parent
        realLocation.translate(parentAbsoluteLocation.negate());

        return realLocation;
    }

    private Dimension getBorderDefaultSize(AbstractDNode abstractDNode) {
        Dimension defaultSize = new Dimension(-1, -1);
        if (abstractDNode instanceof DNode viewNode && !new org.eclipse.sirius.diagram.business.api.query.DNodeQuery(viewNode).isAutoSize()) {
            defaultSize = new DNodeQuery(viewNode).getDefaultDimension();
        }
        return defaultSize;
    }

    /**
     * Returns the dimension of a node corrected by the grid.
     * <p>
     * When grid is enable, user expects the node corner to be matching a grid point.
     * </p>
     * <p>
     * If no size or no grid is provided, the result matches the input {@code size}.
     * </p>
     * <p>
     * The fact a node cannot be resized is no treated in the computation of dimension.
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
                        && node.eContainer() instanceof Node parent // and parent
                        && parent.getLayoutConstraint() instanceof Bounds parentBounds) {
                    // For Border node, only enlarge the attached dimension.
                    // As there is a small shift inside the component,
                    // adjusting the other dimension is useless:
                    // when moving the element, the size would not match the grid.
                    int borderSide = getPortSide(nodeBounds, parentBounds);
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
                result.width = extendToGrid(result.width);
            }
            if ((enlargedAxis & PositionConstants.VERTICAL) != 0) {
                result.height = extendToGrid(result.height);
            }
        }
        return result;
    }

    private int extendToGrid(int value) {
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
     * Evaluates if width of a node can changed.
     * 
     * @param node
     *            GMF element
     * @return true if width can modified
     */
    public static boolean canResizeWidth(Node node) {
        ResizeKind kind = getNodeResizeKind(node);
        return kind == ResizeKind.EAST_WEST_LITERAL || kind == ResizeKind.NSEW_LITERAL;
    }

    /**
     * Evaluates if height of a node can changed.
     * 
     * @param node
     *            GMF element
     * @return true if height can modified
     */
    public static boolean canResizeHeight(Node node) {
        ResizeKind kind = getNodeResizeKind(node);
        return kind == ResizeKind.NORTH_SOUTH_LITERAL || kind == ResizeKind.NSEW_LITERAL;
    }

    /**
     * Returns the side of a border node from its container using {@link PositionConstants}.
     * 
     * @param borderNode
     *            bounds of border node
     * @param container
     *            bounds of container node
     * @return side
     */
    private static int getPortSide(Bounds borderNode, Bounds container) {
        // Warning: legacy implementation has suspicious results.
        // CanonicalDBorderItemLocator.findClosestSideOfParent(borderNode, container);
        if (container.getWidth() == 0) { // no dimension, 0-division risk
            return PositionConstants.WEST;
        }

        Point center = new Rectangle(borderNode.getX(), borderNode.getY(), borderNode.getWidth(), borderNode.getHeight()).getCenter();
        // Diagonal SW to NE
        boolean aboveNwse = isAboveLine(center, container.getHeight(), container.getWidth(), 0);
        // Diagonal NW to SE
        boolean aboveSwne = isAboveLine(center, -container.getHeight(), container.getWidth(), container.getHeight());
        if (aboveSwne) {
            return aboveNwse ? PositionConstants.NORTH : PositionConstants.WEST;
        } else {
            return aboveNwse ? PositionConstants.EAST : PositionConstants.SOUTH;
        }
    }

    /**
     * Returns if a point is above a line.
     * <p>
     * Referential is screen-based (Y-axis increases toward the South, X-Axis increases toward East).
     * </p>
     * <p>
     * Line is defined with formula:
     * 
     * <pre>
     * y = (a / r) * x + b
     * </pre>
     * </p>
     * 
     * @param point
     *            location to test
     * @param a
     *            ratio of slope
     * @param r
     *            division of slope
     * @param b
     *            y-intercept
     * @return true if above
     */
    private static boolean isAboveLine(Point point, int a, int r, int b) {
        // Y head to South !
        return point.y * r <= a * point.x + b * r;
    }
}
