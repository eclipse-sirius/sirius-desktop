/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.borderednode;

import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.DNodeQuery;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.ui.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A Locator of borderNode.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CanonicalDBorderItemLocator {

    /**
     * The number of sides. Used to avoid infinite loop exception in there is too many borderNode relative to the size
     * of the container.
     */
    private static final int NB_SIDES = 4;

    private int preferredSide = PositionConstants.WEST;

    private Dimension borderItemOffset = new Dimension(1, 1);

    private Rectangle constraint = new Rectangle(0, 0, 0, 0);

    private Node container;

    private boolean borderItemHasMoved;

    private int currentSide = PositionConstants.WEST;

    private BitSet authorizedSides = new BitSet(PositionConstants.NSEW);

    /**
     * The parent bounds can be set with {@link #setParentBorderBounds(Rectangle)} if it is known or if the
     * CanonicalDBorderItemLocator must consider a different bounds. If this bounds is not set, it will be computed at
     * the first {@link #getParentBorder()} call.
     */
    private Rectangle parentBorder;

    /**
     * True if the snap to grid is considered as activated. In this case, the returned location, by
     * {@link #getValidLocation(Rectangle, Node, Collection<Node>)}, is snapped to the grid (if possible).
     */
    private boolean snapToGrid;

    /**
     * The grid step (it is mandatory if the {@link #snapToGrid} is true.
     */
    private int gridSpacing = 0;

    /**
     * Default constructor.
     * 
     * @param containerNode
     *            the container
     * @param preferredSide
     *            the preferred side
     */
    public CanonicalDBorderItemLocator(Node containerNode, int preferredSide) {
        this.container = containerNode;
        this.preferredSide = preferredSide;
        initAuthorizedSides();
    }

    /**
     * Constructor if the snapToGrid is enabled.
     * 
     * @param containerNode
     *            the container
     * @param preferredSide
     *            the preferred side
     * @param snapToGrid
     *            true if the snapToGrid is enabled, false otherwise
     * @param gridSpacing
     *            The grid step in pixels
     */
    public CanonicalDBorderItemLocator(Node containerNode, int preferredSide, boolean snapToGrid, int gridSpacing) {
        this(containerNode, preferredSide);
        this.snapToGrid = snapToGrid;
        this.gridSpacing = gridSpacing;
    }

    /**
     * Set the border item offset.
     * 
     * @param borderItemOffset
     *            the border item offset
     */
    public void setBorderItemOffset(Dimension borderItemOffset) {
        this.borderItemOffset = borderItemOffset;
    }

    /**
     * Get the border item offset.
     * 
     * @return Returns the borderItemOffset.
     */
    public Dimension getBorderItemOffset() {
        return borderItemOffset;
    }

    /**
     * Set the constraint.
     * 
     * @param constraint
     *            the constraint
     */
    public void setConstraint(Rectangle constraint) {
        if (!constraint.equals(getConstraint())) {
            borderItemHasMoved = true;
        }
        this.constraint = constraint;

        if (constraint.getTopLeft().x == 0 || constraint.getTopLeft().y == 0) {
            setCurrentSideOfParent(getPreferredSideOfParent());
        }
    }

    /**
     * Accessor to return the constraint location of the border item.
     * 
     * @return the constraint
     */
    protected Rectangle getConstraint() {
        return constraint;
    }

    /**
     * Sets the side of the parent figure on which the border item should appear.
     * 
     * @param side
     *            the side on which this border item appears as defined in {@link PositionConstants}
     */
    public void setCurrentSideOfParent(int side) {
        this.currentSide = side;
    }

    /**
     * Returns the preferred side of the parent figure on which to place this border item.
     * 
     * @return the preferred side of the parent figure on which to place this border item as defined in
     *         {@link PositionConstants}
     */
    public int getPreferredSideOfParent() {
        return preferredSide;
    }

    /**
     * Find the closest side when x,y is inside parent.
     * 
     * @param proposedBounds
     *            the proposed bounds
     * @param parentBorder
     *            the parent border
     * @return draw constant
     */
    public static int findClosestSideOfParent(final Rectangle proposedBounds, final Rectangle parentBorder) {
        return findClosestSideOfParent(proposedBounds, parentBorder, null, false, 0);
    }

    /**
     * Find the closest side when x,y is inside parent.
     * 
     * @param proposedBounds
     *            the proposed bounds
     * @param parentBorder
     *            the parent border
     * @param authorizedSides
     *            the authorized sides. a BitSet using the {@link PositionConstants} values as index. All sides are
     *            considered as authorized if the value is null or the bitSet is empty.
     * @param snapToGrid
     *            true if the snapToGrid is enabled, false otherwise
     * @param gridSpacing
     *            The grid step in pixels
     * @return draw constant
     */
    private static int findClosestSideOfParent(final Rectangle proposedBounds, final Rectangle parentBorder, BitSet authorizedSides, boolean snapToGrid, int gridSpacing) {
        final Point parentCenter = parentBorder.getCenter();
        final Point childCenter = proposedBounds.getCenter();

        int position;
        boolean northAuthorized = true;
        boolean southAuthorized = true;
        boolean eastAuthorized = true;
        boolean westAuthorized = true;
        if (authorizedSides != null && !authorizedSides.isEmpty()) {
            northAuthorized = isAuthorized(authorizedSides, PositionConstants.NORTH);
            southAuthorized = isAuthorized(authorizedSides, PositionConstants.SOUTH);
            eastAuthorized = isAuthorized(authorizedSides, PositionConstants.EAST);
            westAuthorized = isAuthorized(authorizedSides, PositionConstants.WEST);
        }
        // if the east side is not authorized we try west event if this is not
        // natural.
        if (!eastAuthorized || canHandleWestSide(parentCenter, childCenter, northAuthorized, southAuthorized, westAuthorized)) {
            // West, North or South.
            position = handleWestSide(parentBorder, parentCenter, proposedBounds, northAuthorized, southAuthorized, westAuthorized, snapToGrid, gridSpacing);
        } else {
            position = handleEastSide(parentBorder, parentCenter, proposedBounds, northAuthorized, southAuthorized, eastAuthorized, snapToGrid, gridSpacing);
        }
        return position;
    }

    private static boolean canHandleWestSide(final Point parentCenter, final Point childCenter, boolean northAuthorized, boolean southAuthorized, boolean westAuthorized) {
        return (westAuthorized || southAuthorized || northAuthorized) && childCenter.x < parentCenter.x;
    }

    private static int handleWestSide(final Rectangle parentBorder, final Point parentCenter, final Rectangle borderNodeBounds, boolean northAuthorized, boolean southAuthorized,
            boolean westAuthorized, boolean snapToGrid, int gridSpacing) {
        int position;
        if (!southAuthorized || (westAuthorized || northAuthorized) && borderNodeBounds.getCenter().y < parentCenter.y) {
            // west or north
            position = handleNorthWest(parentBorder, borderNodeBounds, northAuthorized, westAuthorized, snapToGrid, gridSpacing);
        } else {
            // west or south
            position = handleSouthWest(parentBorder, borderNodeBounds, southAuthorized, westAuthorized, snapToGrid, gridSpacing);
        }
        return position;
    }

    private static int handleNorthWest(final Rectangle parentBounds, final Rectangle borderNodeBounds, boolean northAuthorized, boolean westAuthorized, boolean snapToGrid, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point borderNodeCenter = borderNodeBounds.getCenter();
        // if one of these sides is not authorized, we retain the only
        // one authorized.
        if (!northAuthorized || !westAuthorized) {
            if (northAuthorized) {
                position = PositionConstants.NORTH;
            } else {
                position = PositionConstants.WEST;
            }
        } else {
            // closer to west or north?
            final Point parentTopLeft = parentBounds.getTopLeft();
            Point childLocation = borderNodeBounds.getTopLeft();
            if (snapToGrid && childLocation.y < parentTopLeft.y && childLocation.x < parentTopLeft.x) {
                // The child location is outside the parent, if the snap to grid is enabled, we must find the next step
                // with at least x or y inside the parent bounds.
                position = findNorthOrWestPreviousLocationSnappedToGridAndInsideParentBounds(parentBounds, borderNodeBounds, gridSpacing);
            }
            if (position == PositionConstants.NONE) {
                if (borderNodeCenter.y < parentTopLeft.y) {
                    position = PositionConstants.NORTH;
                } else if ((borderNodeCenter.x - parentTopLeft.x) <= (borderNodeCenter.y - parentTopLeft.y)) {
                    position = PositionConstants.WEST;
                } else {
                    position = PositionConstants.NORTH;
                }
            }
        }
        return position;
    }

    private static int handleSouthWest(final Rectangle parentBounds, final Rectangle borderNodeBounds, boolean southAuthorized, boolean westAuthorized, boolean snapToGrid, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point borderNodeCenter = borderNodeBounds.getCenter();
        // if one of these sides is not authorized, we retain the only
        // one authorized.
        if (!southAuthorized || !westAuthorized) {
            if (southAuthorized) {
                position = PositionConstants.SOUTH;
            } else {
                position = PositionConstants.WEST;
            }
        } else {
            final Point parentBottomLeft = parentBounds.getBottomLeft();
            Point childLocation = borderNodeBounds.getTopLeft();
            if (snapToGrid && childLocation.y > parentBottomLeft.y && childLocation.x < parentBottomLeft.x) {
                // The child location is outside the parent, if the snap to grid is enabled, we must find the next step
                // with at least x or y inside the parent bounds.
                position = findWestOrSouthPreviousLocationSnappedToGridAndInsideParentBounds(parentBounds, borderNodeBounds, gridSpacing);
            }
            if (position == PositionConstants.NONE) {
                if (borderNodeCenter.y > parentBottomLeft.y) {
                    position = PositionConstants.SOUTH;
                } else if ((borderNodeCenter.x - parentBottomLeft.x) <= (parentBottomLeft.y - borderNodeCenter.y)) {
                    position = PositionConstants.WEST;
                } else {
                    position = PositionConstants.SOUTH;
                }
            }
        }
        return position;
    }

    private static int handleEastSide(final Rectangle parentBounds, final Point parentCenter, final Rectangle borderNodeBounds, boolean northAuthorized, boolean southAuthorized,
            boolean eastAuthorized, boolean snapToGrid, int gridSpacing) {
        int position;
        // EAST, NORTH or SOUTH
        if (!southAuthorized || (eastAuthorized || northAuthorized) && borderNodeBounds.getCenter().y < parentCenter.y) {
            // north or east
            position = handleNorthEast(parentBounds, borderNodeBounds, northAuthorized, eastAuthorized, snapToGrid, gridSpacing);
        } else { // south or east.
            position = handleSouthEast(parentBounds, borderNodeBounds, southAuthorized, eastAuthorized, snapToGrid, gridSpacing);
        }
        return position;
    }

    private static int handleNorthEast(final Rectangle parentBounds, final Rectangle borderNodeBounds, boolean northAuthorized, boolean eastAuthorized, boolean snapToGrid, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point borderNodeCenter = borderNodeBounds.getCenter();
        // if one of these sides is not authorized, we retain the only
        // one authorized.
        if (!eastAuthorized || !northAuthorized) {
            if (eastAuthorized) {
                position = PositionConstants.EAST;
            } else {
                position = PositionConstants.NORTH;
            }
        } else {
            final Point parentTopRight = parentBounds.getTopRight();
            Point childLocation = borderNodeBounds.getTopLeft();
            if (snapToGrid && childLocation.y < parentTopRight.y && childLocation.x > parentTopRight.x) {
                // The child location is outside the parent, if the snap to grid is enabled, we must find the next step
                // with at least x or y inside the parent bounds.
                position = findNorthOrEastPreviousLocationSnappedToGridAndInsideParentBounds(parentBounds, borderNodeBounds, gridSpacing);
            }
            if (position == PositionConstants.NONE) {
                if (borderNodeCenter.y < parentTopRight.y) {
                    position = PositionConstants.NORTH;
                } else if ((parentTopRight.x - borderNodeCenter.x) <= (borderNodeCenter.y - parentTopRight.y)) {
                    position = PositionConstants.EAST;
                } else {
                    position = PositionConstants.NORTH;
                }
            }
        }
        return position;
    }

    private static int handleSouthEast(final Rectangle parentBounds, final Rectangle borderNodeBounds, boolean southAuthorized, boolean eastAuthorized, boolean snapToGrid, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point borderNodeCenter = borderNodeBounds.getCenter();
        // if one of these sides is not authorized, we retain the only
        // one authorized.
        if (!eastAuthorized || !southAuthorized) {
            if (eastAuthorized) {
                position = PositionConstants.EAST;
            } else {
                position = PositionConstants.SOUTH;
            }
        } else {
            final Point parentBottomRight = parentBounds.getBottomRight();
            Point childLocation = borderNodeBounds.getTopLeft();
            if (snapToGrid && childLocation.y > parentBottomRight.y && childLocation.x > parentBottomRight.x) {
                // The child location is outside the parent, if the snap to grid is enabled, we must find the next step
                // with at least x or y inside the parent bounds.
                position = findEastOrSouthPreviousLocationSnappedToGridAndInsideParentBounds(parentBounds, borderNodeBounds, gridSpacing);
            }
            if (position == PositionConstants.NONE) {
                if (borderNodeCenter.y > parentBottomRight.y) {
                    position = PositionConstants.SOUTH;
                } else if ((parentBottomRight.x - borderNodeCenter.x) <= (parentBottomRight.y - borderNodeCenter.y)) {
                    position = PositionConstants.EAST;
                } else {
                    position = PositionConstants.SOUTH;
                }
            }
        }
        return position;
    }

    /**
     * Search the next location in the left and bottom direction where x or y is in the bounds of the parent by
     * respecting a step of <code>gridSpacing</code>. If both x and y is in the bounds, PositionConstants.NONE is
     * returned: there is no priority. If no location is found, PositionConstants.NONE is also returned: the default
     * rules are used. Otherwise, the best side is returned.
     * 
     * @param parentBounds
     *            The bounds of the parent of the border node
     * @param borderNodeBounds
     *            The bounds of the border node
     * @param gridSpacing
     *            The grid spacing
     * @return The best side if any, PositionConstants.NONE otherwise.
     */
    private static int findNorthOrWestPreviousLocationSnappedToGridAndInsideParentBounds(Rectangle parentBounds, Rectangle borderNodeBounds, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point location = borderNodeBounds.getLocation();
        double parentMinX = parentBounds.getLocation().preciseX();
        double parentMaxX = parentMinX + parentBounds.preciseWidth();
        double parentMinY = parentBounds.getLocation().preciseY();
        double parentMaxY = parentMinY + parentBounds.preciseHeight();
        while (!(parentMaxX < location.preciseX() && parentMaxY < location.preciseY())
                && !(isInBounds(location.preciseX(), parentMinX, parentMaxX) || isInBounds(location.preciseY(), parentMinY, parentMaxY))) {
            location.translate(getGapForNextGridStep(location.x, gridSpacing), getGapForNextGridStep(location.y, gridSpacing));
        }
        if (!location.equals(borderNodeBounds.getLocation())) {
            if (isInBounds(location.preciseX(), parentMinX, parentMaxX) && isInBounds(location.preciseY(), parentMinY, parentMaxY)) {
                // Both x and y is in bounds no specific location caused by snapToGrid
            } else if (isInBounds(location.preciseX(), parentMinX, parentMaxX)) {
                position = PositionConstants.NORTH;
            } else {
                position = PositionConstants.WEST;
            }
        }
        return position;
    }

    /**
     * Search the next location in the left and top direction where x or y is in the bounds of the parent by respecting
     * a step of <code>gridSpacing</code>. If both x and y is in the bounds, PositionConstants.NONE is returned: there
     * is no priority. If no location is found, PositionConstants.NONE is also returned: the default rules are used.
     * Otherwise, the best side is returned.
     * 
     * @param parentBounds
     *            The bounds of the parent of the border node
     * @param borderNodeBounds
     *            The bounds of the border node
     * @param gridSpacing
     *            The grid spacing
     * @return The best side if any, PositionConstants.NONE otherwise.
     */
    private static int findWestOrSouthPreviousLocationSnappedToGridAndInsideParentBounds(Rectangle parentBounds, Rectangle borderNodeBounds, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point location = borderNodeBounds.getLocation();
        double parentMinX = parentBounds.getLocation().preciseX();
        double parentMaxX = parentMinX + parentBounds.preciseWidth();
        double parentMinY = parentBounds.getLocation().preciseY();
        double parentMaxY = parentMinY + parentBounds.preciseHeight();
        while (!(parentMaxX < location.preciseX() && parentMinY > location.preciseY())
                && !(isInBounds(location.preciseX(), parentMinX, parentMaxX) || isInBounds(location.preciseY(), parentMinY, parentMaxY))) {
            location.translate(getGapForNextGridStep(location.x, gridSpacing), -getGapForPreviousGridStep(location.y, gridSpacing));
        }
        if (!location.equals(borderNodeBounds.getLocation())) {
            if (isInBounds(location.preciseX(), parentMinX, parentMaxX) && isInBounds(location.preciseY(), parentMinY, parentMaxY)) {
                // Both x and y is in bounds no specific location caused by snapToGrid
            } else if (isInBounds(location.preciseX(), parentMinX, parentMaxX)) {
                position = PositionConstants.SOUTH;
            } else {
                position = PositionConstants.WEST;
            }
        }
        return position;
    }

    /**
     * Search the next location in the left and top direction where x or y is in the bounds of the parent by respecting
     * a step of <code>gridSpacing</code>. If both x and y is in the bounds, PositionConstants.NONE is returned: there
     * is no priority. If no location is found, PositionConstants.NONE is also returned: the default rules are used.
     * Otherwise, the best side is returned.
     * 
     * @param parentBounds
     *            The bounds of the parent of the border node
     * @param borderNodeBounds
     *            The bounds of the border node
     * @param gridSpacing
     *            The grid spacing
     * @return The best side if any, PositionConstants.NONE otherwise.
     */
    private static int findEastOrSouthPreviousLocationSnappedToGridAndInsideParentBounds(Rectangle parentBounds, Rectangle borderNodeBounds, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point location = borderNodeBounds.getLocation();
        double parentMinX = parentBounds.getLocation().preciseX();
        double parentMaxX = parentMinX + parentBounds.preciseWidth();
        double parentMinY = parentBounds.getLocation().preciseY();
        double parentMaxY = parentMinY + parentBounds.preciseHeight();
        while (!(parentMinX > location.preciseX() && parentMinY > location.preciseY())
                && !(isInBounds(location.preciseX(), parentMinX, parentMaxX) || isInBounds(location.preciseY(), parentMinY, parentMaxY))) {
            location.translate(-getGapForPreviousGridStep(location.x, gridSpacing), -getGapForPreviousGridStep(location.y, gridSpacing));
        }
        if (!location.equals(borderNodeBounds.getLocation())) {
            if (isInBounds(location.preciseX(), parentMinX, parentMaxX) && isInBounds(location.preciseY(), parentMinY, parentMaxY)) {
                // Both x and y is in bounds no specific location caused by snapToGrid
            } else if (isInBounds(location.preciseX(), parentMinX, parentMaxX)) {
                position = PositionConstants.SOUTH;
            } else {
                position = PositionConstants.EAST;
            }
        }
        return position;
    }

    /**
     * Search the next location in the left and bottom direction where x or y is in the bounds of the parent by
     * respecting a step of <code>gridSpacing</code>. If both x and y is in the bounds, PositionConstants.NONE is
     * returned: there is no priority. If no location is found, PositionConstants.NONE is also returned: the default
     * rules are used. Otherwise, the best side is returned.
     * 
     * @param parentBounds
     *            The bounds of the parent of the border node
     * @param borderNodeBounds
     *            The bounds of the border node
     * @param gridSpacing
     *            The grid spacing
     * @return The best side if any, PositionConstants.NONE otherwise.
     */
    private static int findNorthOrEastPreviousLocationSnappedToGridAndInsideParentBounds(Rectangle parentBounds, Rectangle borderNodeBounds, int gridSpacing) {
        int position = PositionConstants.NONE;
        Point location = borderNodeBounds.getLocation();
        double parentMinX = parentBounds.getLocation().preciseX();
        double parentMaxX = parentMinX + parentBounds.preciseWidth();
        double parentMinY = parentBounds.getLocation().preciseY();
        double parentMaxY = parentMinY + parentBounds.preciseHeight();
        while (!(parentMinX > location.preciseX() && parentMaxY < location.preciseY())
                && !(isInBounds(location.preciseX(), parentMinX, parentMaxX) || isInBounds(location.preciseY(), parentMinY, parentMaxY))) {
            location.translate(-getGapForPreviousGridStep(location.x, gridSpacing), -getGapForPreviousGridStep(location.y, gridSpacing));
        }
        if (!location.equals(borderNodeBounds.getLocation())) {
            if (isInBounds(location.preciseX(), parentMinX, parentMaxX) && isInBounds(location.preciseY(), parentMinY, parentMaxY)) {
                // Both x and y is in bounds no specific location caused by snapToGrid
            } else if (isInBounds(location.preciseX(), parentMinX, parentMaxX)) {
                position = PositionConstants.NORTH;
            } else {
                position = PositionConstants.EAST;
            }
        }
        return position;
    }

    private static int getGapForNextGridStep(int currentCoordinate, int gridSpacing) {
        return getGapForNextGridStep(currentCoordinate, gridSpacing, Integer.MIN_VALUE);
    }

    private static int getGapForNextGridStep(int currentCoordinate, int gridSpacing, int levelToPass) {
        int remainder = Math.abs(currentCoordinate % gridSpacing);
        int gap = gridSpacing - remainder;
        if (levelToPass != Integer.MIN_VALUE && currentCoordinate + gap < levelToPass) {
            return gap + getGapForNextGridStep(currentCoordinate + gap, gridSpacing, levelToPass);
        }
        return gap;
    }

    private static int getGapForPreviousGridStep(int currentCoordinate, int gridSpacing) {
        return getGapForPreviousGridStep(currentCoordinate, gridSpacing, Integer.MIN_VALUE);
    }

    private static int getGapForPreviousGridStep(int currentCoordinate, int gridSpacing, int levelToPass) {
        int gap = gridSpacing;
        int remainder = Math.abs(currentCoordinate % gridSpacing);
        if (remainder > 0) {
            gap = remainder;
        }
        if (levelToPass != Integer.MIN_VALUE && currentCoordinate - gap > levelToPass) {
            return gap + getGapForPreviousGridStep(currentCoordinate - gap, gridSpacing, levelToPass);
        }
        return gap;
    }

    private static boolean isInBounds(double value, double min, double max) {
        return min < value && value < max;
    }

    private void updateAuthorizedSide(DNode borderNode) {
        initAuthorizedSides();
        DNodeQuery query = new DNodeQuery(borderNode);
        List<Side> forbiddenSides = query.getForbiddenSide();
        // If all the sides are forbidden, we consider all sides as authorized
        // since the border node has to be located somewhere anyway.
        if (!(forbiddenSides.size() == Side.VALUES.size())) {
            for (Side side : forbiddenSides) {
                if (Side.WEST.getName().equals(side.getName())) {
                    this.authorizedSides.clear(PositionConstants.WEST);
                } else if (Side.EAST.getName().equals(side.getName())) {
                    this.authorizedSides.clear(PositionConstants.EAST);
                } else if (Side.NORTH.getName().equals(side.getName())) {
                    this.authorizedSides.clear(PositionConstants.NORTH);
                } else if (Side.SOUTH.getName().equals(side.getName())) {
                    this.authorizedSides.clear(PositionConstants.SOUTH);
                }
            }
        }
    }

    private void initAuthorizedSides() {
        this.authorizedSides.clear();
        this.authorizedSides.set(PositionConstants.WEST);
        this.authorizedSides.set(PositionConstants.SOUTH);
        this.authorizedSides.set(PositionConstants.EAST);
        this.authorizedSides.set(PositionConstants.NORTH);
    }

    private static boolean isAuthorized(BitSet authorizedSides, int side) {
        return authorizedSides.get(side);
    }

    /**
     * Relocates the given view.
     * 
     * @param borderItem
     *            The view to relocate
     */
    public void relocate(Node borderItem) {
        EObject element = borderItem.getElement();
        if (element instanceof DNode) {
            updateAuthorizedSide((DNode) element);
        }
        final Dimension size = getSize(borderItem);
        final Rectangle rectSuggested = new Rectangle(getPreferredLocation(borderItem), size);
        // If the border item has moved, we change the preferred side, otherwise
        // we let the current side enabled
        if (borderItemHasMoved) {
            final int closestSide = findClosestSideOfParent(rectSuggested, getParentBorder(), this.authorizedSides, snapToGrid, gridSpacing);
            setPreferredSideOfParent(closestSide);
            setCurrentSideOfParent(closestSide);
            borderItemHasMoved = false;
        }
    }

    private Point locateOnBorder(final Rectangle suggestedLocation, final int suggestedSide, final int circuitCount, final Node borderItem, final Collection<Node> portsNodesToIgnore) {
        Point recommendedLocation = locateOnParent(suggestedLocation, suggestedSide, borderItem);

        Dimension nodeSize = suggestedLocation.getSize();

        if (circuitCount < 4 && conflicts(recommendedLocation, nodeSize, portsNodesToIgnore).some()) {
            Rectangle newRecommendedLocationBounds = new Rectangle(recommendedLocation, nodeSize);
            if (suggestedSide == PositionConstants.WEST) {
                recommendedLocation = locateOnWestBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsNodesToIgnore);
            } else if (suggestedSide == PositionConstants.SOUTH) {
                recommendedLocation = locateOnSouthBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsNodesToIgnore);
            } else if (suggestedSide == PositionConstants.EAST) {
                recommendedLocation = locateOnEastBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsNodesToIgnore);
            } else { // NORTH
                recommendedLocation = locateOnNorthBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsNodesToIgnore);
            }
        }
        return recommendedLocation;
    }

    /**
     * Determine if the the given point conflicts with the position of an existing borderItemFigure.
     * 
     * @param recommendedLocation
     *            the location to check
     * @param nodeSize
     *            the size of the bordered node to check
     * @param portsNodesToIgnore
     *            list of nodes to ignore during conflict detection.
     * @return the optional Rectangle of the border item that is in conflict with the given bordered node (a none
     *         option)
     */
    private Option<Rectangle> conflicts(final Point recommendedLocation, final Dimension nodeSize, final Collection<Node> portsNodesToIgnore) {
        final Rectangle recommendedRect = new Rectangle(recommendedLocation.x, recommendedLocation.y, nodeSize.width, nodeSize.height);
        final List<?> borderItems = container.getPersistedChildren();
        final ListIterator<?> iterator = borderItems.listIterator();
        while (iterator.hasNext()) {
            final Node borderItem = (Node) iterator.next();
            if (!portsNodesToIgnore.contains(borderItem)) {
                boolean takeIntoAccount = true;
                // Does not consider label that is not on border.
                ViewQuery viewQuery = new ViewQuery(borderItem);
                NodeQuery nodeQuery = new NodeQuery(borderItem);
                if (!nodeQuery.isBorderedNode() && !viewQuery.isForNameEditPartOnBorder()) {
                    takeIntoAccount = false;
                }
                if (isVisible(borderItem) && takeIntoAccount) {
                    LayoutConstraint borderItemLayoutConstraint = borderItem.getLayoutConstraint();
                    if (borderItemLayoutConstraint instanceof Bounds) {
                        Dimension extendedDimension = getExtendedDimension(borderItem);

                        Rectangle borderItemBounds = GMFHelper.getAbsoluteBounds(borderItem, true, false, false, false);

                        if (extendedDimension != null) {
                            borderItemBounds = PortLayoutHelper.getUncollapseCandidateLocation(extendedDimension, borderItemBounds, getParentBorder());
                        }

                        if (borderItemBounds.intersects(recommendedRect)) {
                            return Options.newSome(borderItemBounds);
                        }
                    }
                }
            }
        }
        return Options.newNone();
    }

    private boolean isVisible(Node node) {
        EObject element = node.getElement();
        if (element instanceof DDiagramElement) {
            return ((DDiagramElement) element).isVisible() && node.isVisible();
        }
        return node.isVisible();
    }

    private Dimension getExtendedDimension(Node node) {
        if (node.getElement() instanceof DDiagramElement) {
            DDiagramElementQuery diagramElementQuery = new DDiagramElementQuery((DDiagramElement) node.getElement());
            if (diagramElementQuery.isCollapsed()) {
                NodeQuery nodeQuery = new NodeQuery(node);
                return nodeQuery.getOriginalDimensionBeforeCollapse();
            }
        }
        return null;
    }

    /**
     * Ensure the suggested location actually lies on the parent boundary. The side takes precedence.
     * 
     * @param suggestedLocation
     *            suggested location
     * @param suggestedSide
     *            suggested side
     * @param borderItem
     *            the border item.
     * @return point the location point
     */
    protected Point locateOnParent(final Rectangle suggestedLocation, final int suggestedSide, final Node borderItem) {
        final Rectangle bounds = getParentBorder();
        final int parentFigureWidth = bounds.width;
        final int parentFigureHeight = bounds.height;
        final int parentFigureX = bounds.x;
        final int parentFigureY = bounds.y;
        final Dimension borderItemSize = suggestedLocation.getSize();
        int newX = suggestedLocation.x;
        int newY = suggestedLocation.y;
        final int westX = parentFigureX - borderItemSize.width + getBorderItemOffset().width;
        final int eastX = parentFigureX + parentFigureWidth - getBorderItemOffset().width;
        final int southY = parentFigureY + parentFigureHeight - getBorderItemOffset().height;
        final int northY = parentFigureY - borderItemSize.height + getBorderItemOffset().height;
        if (suggestedSide == PositionConstants.WEST || suggestedSide == PositionConstants.EAST) {
            if (suggestedSide == PositionConstants.WEST && suggestedLocation.x != westX) {
                newX = westX;
            } else if (suggestedSide == PositionConstants.EAST && suggestedLocation.x != eastX) {
                newX = eastX;
            }
            int nextGap = 0;
            int previousGap = 0;
            if (snapToGrid) {
                nextGap = getGapForNextGridStep(suggestedLocation.y, gridSpacing, parentFigureY);
                previousGap = getGapForPreviousGridStep(suggestedLocation.y, gridSpacing, bounds.getBottomRight().y - borderItemSize.height);
                if (suggestedLocation.y % gridSpacing != 0) {
                    // If the suggested location is not on the grid, we align it.
                    if (previousGap < nextGap) {
                        newY = suggestedLocation.y - previousGap;
                    } else {
                        newY = suggestedLocation.y + nextGap;
                    }
                }
            }
            if (borderItemSize.width > bounds.width) {
                // The border item width is higher than the parent item. In that
                // case, we will center the border item on this side.
                newY = parentFigureY - (borderItemSize.height - bounds.height) / 2;
            } else if (newY < parentFigureY) {
                if (snapToGrid) {
                    newY = suggestedLocation.y + nextGap;
                    if (newY > bounds.getBottomLeft().y - borderItemSize.height) {
                        newY = parentFigureY;
                    }
                } else {
                    newY = parentFigureY;
                }
            } else if (newY > bounds.getBottomLeft().y - borderItemSize.height) {
                if (snapToGrid) {
                    newY = suggestedLocation.y - previousGap;
                    if (newY < parentFigureY) {
                        newY = bounds.getBottomLeft().y - borderItemSize.height;
                    }
                } else {
                    newY = bounds.getBottomLeft().y - borderItemSize.height;
                }
            }
        } else if (suggestedSide == PositionConstants.SOUTH || suggestedSide == PositionConstants.NORTH) {
            if (suggestedSide == PositionConstants.SOUTH && suggestedLocation.y != southY) {
                newY = southY;
            } else if (suggestedSide == PositionConstants.NORTH && suggestedLocation.y != northY) {
                newY = northY;
            }
            int nextGap = 0;
            int previousGap = 0;
            if (snapToGrid) {
                nextGap = getGapForNextGridStep(suggestedLocation.x, gridSpacing, parentFigureX);
                previousGap = getGapForPreviousGridStep(suggestedLocation.x, gridSpacing, bounds.getBottomRight().x - borderItemSize.width);
                if (suggestedLocation.x % gridSpacing != 0) {
                    // If the suggested location is not on the grid, we align it.
                    if (previousGap < nextGap) {
                        newX = suggestedLocation.x - previousGap;
                    } else {
                        newX = suggestedLocation.x + nextGap;
                    }
                }
            }
            if (borderItemSize.width > bounds.width) {
                // The border item width is larger than the parent item. In that
                // case, we will center the border item on this side.
                newX = parentFigureX - (borderItemSize.width - bounds.width) / 2;
            } else if (newX < parentFigureX) {
                if (snapToGrid) {
                    newX = suggestedLocation.x + nextGap;
                    if (newX > bounds.getBottomRight().x - borderItemSize.width) {
                        newX = parentFigureX;
                    }
                } else {
                    newX = parentFigureX;
                }
            } else if (newX > bounds.getBottomRight().x - borderItemSize.width) {
                if (snapToGrid) {
                    newX = suggestedLocation.x - previousGap;
                    if (newX < parentFigureX) {
                        newX = bounds.getBottomRight().x - borderItemSize.width;
                    }
                } else {
                    newX = bounds.getBottomRight().x - borderItemSize.width;
                }
            }
        }
        return new Point(newX, newY);
    }

    /**
     * Locate the recommendedLocation on the south border :
     * <UL>
     * <LI>Search alternately to the left and to the right until find an available space</LI>
     * <LI>And finally if there is no space on this border search on the east border.</LI>
     * </UL>
     * 
     * @param recommendedLocation
     *            The desired location
     * @param circuitCount
     *            recursion count to avoid an infinite loop
     * @param borderItem
     *            the figure representing the border item.
     * @param portsNodesToIgnore
     *            the ports node to ignore
     * @return the location where the border item can be put
     */
    protected Point locateOnSouthBorder(final Rectangle recommendedLocation, final int circuitCount, final Node borderItem, final Collection<Node> portsNodesToIgnore) {
        final Dimension borderItemSize = recommendedLocation.getSize();
        Point resultLocation = null;
        final Point rightTestPoint = recommendedLocation.getLocation();
        final Point leftTestPoint = recommendedLocation.getLocation();
        boolean isStillFreeSpaceToTheRight = true;
        boolean isStillFreeSpaceToTheLeft = true;
        int rightHorizontalGap = 0;
        int leftHorizontalGap = 0;
        int next = getNextAuthorizedSide(PositionConstants.SOUTH);
        // The recommendedLocationForEast is set when we detected that there is
        // not free space on right of south side.
        Point recommendedLocationForNextSide = recommendedLocation.getLocation();
        Option<Rectangle> lastOptionalConflictingRectangleOnSameSide = Options.newNone();
        while (resultLocation == null && (isStillFreeSpaceToTheRight || isStillFreeSpaceToTheLeft)) {
            Option<Rectangle> optionalConflictingRectangle = Options.newNone();
            if (isStillFreeSpaceToTheRight) {
                // Move to the right on the south side
                rightTestPoint.setX(rightTestPoint.x + rightHorizontalGap);
                optionalConflictingRectangle = conflicts(rightTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().y == rightTestPoint.y) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    rightHorizontalGap = (optionalConflictingRectangle.get().x + optionalConflictingRectangle.get().width + 1) - rightTestPoint.x;
                    if (snapToGrid) {
                        rightHorizontalGap = computeGapWithSnapToGrid(rightTestPoint.x, rightHorizontalGap);
                    }
                    if (rightTestPoint.x + rightHorizontalGap + borderItemSize.width > getParentBorder().getBottomRight().x) {
                        isStillFreeSpaceToTheRight = false;
                    }
                } else {
                    resultLocation = rightTestPoint;
                }
            }
            if (isStillFreeSpaceToTheLeft && resultLocation == null) {
                // Move to the left on the south side
                leftTestPoint.setX(leftTestPoint.x - leftHorizontalGap);
                optionalConflictingRectangle = conflicts(leftTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().y == leftTestPoint.y) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    leftHorizontalGap = leftTestPoint.x - (optionalConflictingRectangle.get().x - borderItemSize.width - 1);
                    if (snapToGrid) {
                        leftHorizontalGap = computeGapWithSnapToGrid(leftTestPoint.x, leftHorizontalGap);
                    }
                    if (leftTestPoint.x - leftHorizontalGap < getParentBorder().getTopLeft().x) {
                        isStillFreeSpaceToTheLeft = false;
                    }
                } else {
                    resultLocation = leftTestPoint;
                }
            }
            // If this side is full
            if (!isStillFreeSpaceToTheLeft && !isStillFreeSpaceToTheRight) {
                if (circuitCount == NB_SIDES - 1) {
                    // There is no space on either side (so use the last
                    // conflicting position)
                    if (lastOptionalConflictingRectangleOnSameSide.some()) {
                        resultLocation = lastOptionalConflictingRectangleOnSameSide.get().getTopLeft();
                    } else {
                        resultLocation = optionalConflictingRectangle.get().getTopLeft();
                    }
                }
                // We only compute the new recommended location for the next
                // side (following the anticlockwise) if it is authorized.
                else if (next == PositionConstants.EAST) {
                    int nextYLocation = optionalConflictingRectangle.get().y - borderItemSize.height - 1;
                    if (snapToGrid) {
                        nextYLocation = Math.min(nextYLocation, rightTestPoint.y - getGapForPreviousGridStep(rightTestPoint.y));
                    }
                    recommendedLocationForNextSide = new Point(rightTestPoint.x + rightHorizontalGap, nextYLocation);
                }
            }
        }
        if (resultLocation == null) {
            // south is full, try the next (east).
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForNextSide, borderItemSize), next, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    /**
     * Locate the recommendedLocation on the north border :
     * <UL>
     * <LI>Search alternately to the left and to the right until find an available space</LI>
     * <LI>And finally if there is no space on this border search on the west border.</LI>
     * </UL>
     * 
     * @param recommendedLocation
     *            The desired location
     * @param circuitCount
     *            recursion count to avoid an infinite loop
     * @param borderItem
     *            the figure representing the border item.
     * @param portsNodesToIgnore
     *            the ports node to ignore
     * @return the location where the border item can be put
     */
    protected Point locateOnNorthBorder(final Rectangle recommendedLocation, final int circuitCount, final Node borderItem, final Collection<Node> portsNodesToIgnore) {
        final Dimension borderItemSize = recommendedLocation.getSize();
        Point resultLocation = null;
        final Point rightTestPoint = recommendedLocation.getLocation();
        final Point leftTestPoint = recommendedLocation.getLocation();
        boolean isStillFreeSpaceToTheRight = true;
        boolean isStillFreeSpaceToTheLeft = true;
        int rightHorizontalGap = 0;
        int leftHorizontalGap = 0;
        int next = getNextAuthorizedSide(PositionConstants.NORTH);
        // The recommendedLocationForWest is set when we detected that there is
        // not free space on left of north side.
        Point recommendedLocationForNextSide = recommendedLocation.getLocation();
        Option<Rectangle> lastOptionalConflictingRectangleOnSameSide = Options.newNone();
        while (resultLocation == null && (isStillFreeSpaceToTheRight || isStillFreeSpaceToTheLeft)) {
            Option<Rectangle> optionalConflictingRectangle = Options.newNone();
            if (isStillFreeSpaceToTheRight) {
                // Move to the right on the north side
                rightTestPoint.setX(rightTestPoint.x + rightHorizontalGap);
                optionalConflictingRectangle = conflicts(rightTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().y == rightTestPoint.y) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    rightHorizontalGap = (optionalConflictingRectangle.get().x + optionalConflictingRectangle.get().width + 1) - rightTestPoint.x;
                    if (snapToGrid) {
                        rightHorizontalGap = computeGapWithSnapToGrid(rightTestPoint.x, rightHorizontalGap);
                    }
                    if (rightTestPoint.x + rightHorizontalGap + borderItemSize.width > getParentBorder().getBottomRight().x) {
                        isStillFreeSpaceToTheRight = false;
                    }
                } else {
                    resultLocation = rightTestPoint;
                }
            }
            if (isStillFreeSpaceToTheLeft && resultLocation == null) {
                // Move to the left on the north side
                leftTestPoint.setX(leftTestPoint.x - leftHorizontalGap);
                optionalConflictingRectangle = conflicts(leftTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().y == leftTestPoint.y) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    leftHorizontalGap = leftTestPoint.x - (optionalConflictingRectangle.get().x - borderItemSize.width - 1);
                    if (snapToGrid) {
                        leftHorizontalGap = computeGapWithSnapToGrid(leftTestPoint.x, leftHorizontalGap);
                    }
                    if (leftTestPoint.x - leftHorizontalGap < getParentBorder().getTopLeft().x) {
                        isStillFreeSpaceToTheLeft = false;
                    }
                } else {
                    resultLocation = leftTestPoint;
                }
            }
            // If this side is full
            if (!isStillFreeSpaceToTheLeft && !isStillFreeSpaceToTheRight) {
                if (circuitCount == NB_SIDES - 1) {
                    // There is no space on either side (so use the last
                    // conflicting position)
                    if (lastOptionalConflictingRectangleOnSameSide.some()) {
                        resultLocation = lastOptionalConflictingRectangleOnSameSide.get().getTopLeft();
                    } else {
                        resultLocation = optionalConflictingRectangle.get().getTopLeft();
                    }
                }
                // We only compute the new recommended location for the next
                // side (following the anticlockwise) if it is authorized.
                else if (next == PositionConstants.WEST) {
                    int nextYLocation = optionalConflictingRectangle.get().y + optionalConflictingRectangle.get().height + 1;
                    if (snapToGrid) {
                        nextYLocation = Math.max(nextYLocation, leftTestPoint.y + getGapForNextGridStep(leftTestPoint.y));
                    }
                    recommendedLocationForNextSide = new Point(leftTestPoint.x - leftHorizontalGap, nextYLocation);
                }
            }
        }
        if (resultLocation == null) {
            // North is full, try the next (west).
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForNextSide, borderItemSize), next, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    /**
     * Locate the recommendedLocation on the west border :
     * <UL>
     * <LI>Search alternately upward and downward until find an available space</LI>
     * <LI>And finally if there is no space on this border search on the south border.</LI>
     * </UL>
     * 
     * @param recommendedLocation
     *            The desired location
     * @param circuitCount
     *            recursion count to avoid an infinite loop
     * @param borderItem
     *            the figure representing the border item.
     * @param portsNodesToIgnore
     *            the ports node to ignore
     * @return the location where the border item can be put
     */
    protected Point locateOnWestBorder(final Rectangle recommendedLocation, final int circuitCount, final Node borderItem, final Collection<Node> portsNodesToIgnore) {
        final Dimension borderItemSize = recommendedLocation.getSize();
        Point resultLocation = null;
        final Point belowTestPoint = recommendedLocation.getLocation();
        final Point aboveTestPoint = recommendedLocation.getLocation();
        boolean isStillFreeSpaceAbove = true;
        boolean isStillFreeSpaceBelow = true;
        int belowVerticalGap = 0;
        int aboveVerticalGap = 0;
        int next = getNextAuthorizedSide(PositionConstants.WEST);
        // The recommendedLocationForSouth is set when we detected that there is
        // not free space on bottom of west side.
        Point recommendedLocationForNextSide = recommendedLocation.getLocation();
        Option<Rectangle> lastOptionalConflictingRectangleOnSameSide = Options.newNone();
        while (resultLocation == null && (isStillFreeSpaceAbove || isStillFreeSpaceBelow)) {
            Option<Rectangle> optionalConflictingRectangle = Options.newNone();
            if (isStillFreeSpaceBelow) {
                // Move down on the west side
                belowTestPoint.setY(belowTestPoint.y + belowVerticalGap);
                optionalConflictingRectangle = conflicts(belowTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().x == belowTestPoint.x) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    belowVerticalGap = optionalConflictingRectangle.get().y + optionalConflictingRectangle.get().height - belowTestPoint.y + 1;
                    if (snapToGrid) {
                        belowVerticalGap = computeGapWithSnapToGrid(belowTestPoint.y, belowVerticalGap);
                    }
                    if (belowTestPoint.y + belowVerticalGap + borderItemSize.height > getParentBorder().getBottomLeft().y) {
                        isStillFreeSpaceBelow = false;
                    }
                } else {
                    resultLocation = belowTestPoint;
                }
            }
            if (isStillFreeSpaceAbove && resultLocation == null) {
                // Move up on the west side
                aboveTestPoint.setY(aboveTestPoint.y - aboveVerticalGap);
                optionalConflictingRectangle = conflicts(aboveTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().x == aboveTestPoint.x) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    aboveVerticalGap = aboveTestPoint.y - (optionalConflictingRectangle.get().y - borderItemSize.height - 1);
                    if (snapToGrid) {
                        aboveVerticalGap = computeGapWithSnapToGrid(aboveTestPoint.y, aboveVerticalGap);
                    }
                    if (aboveTestPoint.y - aboveVerticalGap < getParentBorder().getTopRight().y) {
                        isStillFreeSpaceAbove = false;
                    }
                } else {
                    resultLocation = aboveTestPoint;
                }
            }
            // If this side is full
            if (!isStillFreeSpaceBelow && !isStillFreeSpaceAbove) {
                if (circuitCount == NB_SIDES - 1) {
                    // There is no space on either side (so use the last
                    // conflicting position)
                    if (lastOptionalConflictingRectangleOnSameSide.some()) {
                        resultLocation = lastOptionalConflictingRectangleOnSameSide.get().getTopLeft();
                    } else {
                        resultLocation = optionalConflictingRectangle.get().getTopLeft();
                    }
                }
                // We only compute the new recommended location for the next
                // side (following the anticlockwise) if it is authorized.
                else if (next == PositionConstants.SOUTH) {
                    int nextXLocation = belowTestPoint.x + optionalConflictingRectangle.get().width + 1;
                    if (snapToGrid) {
                        nextXLocation = Math.max(nextXLocation, belowTestPoint.x + getGapForNextGridStep(belowTestPoint.x));
                    }
                    recommendedLocationForNextSide = new Point(nextXLocation, belowTestPoint.y + belowVerticalGap);

                }
            }
        }
        if (resultLocation == null) {
            // west is full, try the next (south).
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForNextSide, borderItemSize), next, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    /**
     * Locate the recommendedLocation on the east border :
     * <UL>
     * <LI>Search alternately upward and downward until find an available space</LI>
     * <LI>And finally if there is no space on this border search on the north border.</LI>
     * </UL>
     * 
     * @param recommendedLocation
     *            The desired location
     * @param circuitCount
     *            recursion count to avoid an infinite loop
     * @param borderItem
     *            the figure representing the border item.
     * @param portsNodesToIgnore
     *            the ports node to ignore
     * @return the location where the border item can be put
     */
    protected Point locateOnEastBorder(final Rectangle recommendedLocation, final int circuitCount, final Node borderItem, final Collection<Node> portsNodesToIgnore) {
        final Dimension borderItemSize = recommendedLocation.getSize();
        Point resultLocation = null;
        final Point belowTestPoint = recommendedLocation.getLocation();
        final Point aboveTestPoint = recommendedLocation.getLocation();
        boolean isStillFreeSpaceAbove = true;
        boolean isStillFreeSpaceBelow = true;
        int belowVerticalGap = 0;
        int aboveVerticalGap = 0;
        int next = getNextAuthorizedSide(PositionConstants.EAST);
        // The recommendedLocationForNorth is set when we detected that there is
        // not free space on top of east side.
        Point recommendedLocationForNextSide = recommendedLocation.getLocation();
        Option<Rectangle> lastOptionalConflictingRectangleOnSameSide = Options.newNone();
        while (resultLocation == null && (isStillFreeSpaceAbove || isStillFreeSpaceBelow)) {
            Option<Rectangle> optionalConflictingRectangle = Options.newNone();
            if (isStillFreeSpaceBelow) {
                // Move down on the east side
                belowTestPoint.setY(belowTestPoint.y + belowVerticalGap);
                optionalConflictingRectangle = conflicts(belowTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().x == belowTestPoint.x) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    belowVerticalGap = optionalConflictingRectangle.get().y + optionalConflictingRectangle.get().height - belowTestPoint.y + 1;
                    if (snapToGrid) {
                        belowVerticalGap = computeGapWithSnapToGrid(belowTestPoint.y, belowVerticalGap);
                    }
                    if (belowTestPoint.y + belowVerticalGap + borderItemSize.height > getParentBorder().getBottomLeft().y) {
                        isStillFreeSpaceBelow = false;
                    }
                } else {
                    resultLocation = belowTestPoint;
                }
            }
            if (isStillFreeSpaceAbove && resultLocation == null) {
                // Move up on the east side
                aboveTestPoint.setY(aboveTestPoint.y - aboveVerticalGap);
                optionalConflictingRectangle = conflicts(aboveTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    // We make sure the conflicting location is on the same side
                    // and not on one of the neighbor.
                    if (optionalConflictingRectangle.get().x == aboveTestPoint.x) {
                        lastOptionalConflictingRectangleOnSameSide = optionalConflictingRectangle;
                    }
                    aboveVerticalGap = aboveTestPoint.y - (optionalConflictingRectangle.get().y - borderItemSize.height - 1);
                    if (snapToGrid) {
                        aboveVerticalGap = computeGapWithSnapToGrid(aboveTestPoint.y, aboveVerticalGap);
                    }
                    if (aboveTestPoint.y - aboveVerticalGap < getParentBorder().getTopRight().y) {
                        isStillFreeSpaceAbove = false;
                    }
                } else {
                    resultLocation = aboveTestPoint;
                }
            }
            // If this side is full
            if (!isStillFreeSpaceBelow && !isStillFreeSpaceAbove) {
                if (circuitCount == NB_SIDES - 1) {
                    // There is no space on either side (so use the last
                    // conflicting position)
                    if (lastOptionalConflictingRectangleOnSameSide.some()) {
                        resultLocation = lastOptionalConflictingRectangleOnSameSide.get().getTopLeft();
                    } else {
                        resultLocation = optionalConflictingRectangle.get().getTopLeft();
                    }
                }
                // We only compute the new recommended location for the next
                // side (following the anticlockwise) if it is authorized.
                else if (next == PositionConstants.NORTH) {
                    int nextXLocation = optionalConflictingRectangle.get().x - borderItemSize.width - 1;
                    if (snapToGrid) {
                        nextXLocation = Math.min(nextXLocation, aboveTestPoint.x - getGapForPreviousGridStep(aboveTestPoint.x));
                    }
                    recommendedLocationForNextSide = new Point(nextXLocation, aboveTestPoint.y - aboveVerticalGap);
                }
            }
        }
        if (resultLocation == null) {
            // East is full, try the next (north).
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForNextSide, borderItemSize), next, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    private int computeGapWithSnapToGrid(int currentCoordinate, int proposedGap) {
        int gapForNextGridStep = getGapForNextGridStep(currentCoordinate);
        return gapForNextGridStep < proposedGap ? proposedGap + gridSpacing - (proposedGap % gridSpacing) : gapForNextGridStep;
    }

    /**
     * Sets the preferred side of the parent figure on which to place this border item.
     * 
     * @param preferredSide
     *            the preferred side of the parent figure on which to place this border item as defined in
     *            {@link PositionConstants}
     */
    // CHECKSTYLE:OFF
    public void setPreferredSideOfParent(int preferredSide) {
        this.preferredSide = preferredSide;
        setCurrentSideOfParent(preferredSide);
    }

    // CHECKSTYLE:ON

    private Point getPreferredLocation(final Node borderItem) {
        final Point constraintLocation = getConstraint().getLocation();
        final Point ptAbsoluteLocation = this.getAbsoluteToBorder(constraintLocation);
        return ptAbsoluteLocation;
    }

    /**
     * Returns the side of the parent figure on which the border item is currently on.
     * 
     * @return the side on which this border item appears as defined in {@link PositionConstants}
     */
    public int getCurrentSideOfParent() {
        return currentSide;
    }

    private Point getAbsoluteToBorder(Point ptRelativeOffset) {
        Point parentOrigin = getParentBorder().getTopLeft();
        return parentOrigin.translate(ptRelativeOffset);
    }

    private Rectangle getParentBorder() {
        if (parentBorder == null) {
            NodeQuery nodeQuery = new NodeQuery(container);
            parentBorder = nodeQuery.getHandleBounds();
        }
        return parentBorder;
    }

    /**
     * Set the parent border bounds if it is known. If this bounds is not set, it will be computed at the first
     * {@link #getParentBorder()} call.
     * 
     * @param parentBounds
     *            The bounds to consider for this {@link CanonicalDBorderItemLocator}.
     */
    public void setParentBorderBounds(Rectangle parentBounds) {
        parentBorder = parentBounds;
    }

    private Dimension getSize(Node borderItem) {
        return GMFHelper.getBounds(borderItem).getSize();
    }

    /**
     * Returns a suitable location for the border item given a proposed Bounds. By implementing this method, the
     * feedback shown when the user moves a border item can reflect where the locator will actually place the border
     * item.
     * <p>
     * For example, if the border item is restricted to being on the border of its parent shape, when the user attempts
     * to move the border item outside the border of the parent shape (the proposed bounds), the feedback will always
     * show the border item on the border. In this case, this method would return a location on the border close to the
     * proposed location.
     * </p>
     * 
     * @param proposedBounds
     *            a proposed bounds
     * @param borderItem
     *            the border item in question
     * @param portsNodesToIgnore
     *            list of nodes to ignore during conflict detection. This list must contain at least the
     *            <code>borderItem</code>.
     * @return a Point corresponding to the valid location
     */
    public Point getValidLocation(Rectangle proposedBounds, Node borderItem, final Collection<Node> portsNodesToIgnore) {
        EObject element = borderItem.getElement();
        if (element instanceof DNode) {
            updateAuthorizedSide((DNode) element);
        }
        final Rectangle realBounds = new Rectangle(proposedBounds);
        Dimension oldOffset = getBorderItemOffset();
        NodeQuery nodeQuery = new NodeQuery(borderItem);
        boolean isCollapsed = nodeQuery.isCollapsed();
        if (isCollapsed) {
            Dimension extendedDimension = nodeQuery.getOriginalDimensionBeforeCollapse();
            setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
            if (extendedDimension.height != proposedBounds.height || extendedDimension.width != proposedBounds.width) {
                Rectangle extendedBounds = PortLayoutHelper.getUncollapseCandidateLocation(extendedDimension, proposedBounds, getParentBorder());
                realBounds.setBounds(extendedBounds);
            }
        }

        final int side = findClosestSideOfParent(proposedBounds, getParentBorder(), this.authorizedSides, snapToGrid, gridSpacing);
        Point newTopLeft = locateOnBorder(realBounds, side, NB_SIDES - getNumberOfAuthorizedSides(), borderItem, portsNodesToIgnore);
        if (isCollapsed) {
            setBorderItemOffset(oldOffset);
            Rectangle collapsedBounds = PortLayoutHelper.getCollapseCandidateLocation(nodeQuery.getCollapsedSize(), new Rectangle(newTopLeft, nodeQuery.getOriginalDimensionBeforeCollapse()),
                    getParentBorder());
            newTopLeft = collapsedBounds.getLocation();
        }
        return newTopLeft;
    }

    /**
     * Returns the next authorized side starting from the current side.
     * 
     * @param currentSide
     *            the current {@link PositionConstants}.
     * @return the next authorized {@link PositionConstants}.
     */
    private int getNextAuthorizedSide(int currentSide) {
        return getNextAuthorizedSide(currentSide, currentSide);
    }

    private int getNextAuthorizedSide(int currentSide, int initialSide) {
        int nextAuthorized = PositionConstants.NONE;
        int nextSide = getNextSide(currentSide);
        if (initialSide == nextSide) {
            // if the iteration started from the next side, we have finished
            // without finding new authorized side.
        } else if (this.authorizedSides.get(nextSide)) {
            nextAuthorized = nextSide;
        } else {
            // if the next side is not authorized we step forward
            // recursively.
            nextAuthorized = getNextAuthorizedSide(nextSide, initialSide);
        }
        return nextAuthorized;
    }

    private int getNextSide(int current) {
        int next = PositionConstants.NONE;
        switch (current) {
        case PositionConstants.WEST:
            next = PositionConstants.SOUTH;
            break;
        case PositionConstants.SOUTH:
            next = PositionConstants.EAST;
            break;
        case PositionConstants.EAST:
            next = PositionConstants.NORTH;
            break;
        case PositionConstants.NORTH:
            next = PositionConstants.WEST;
            break;
        default:
            break;
        }
        return next;
    }

    private int getNumberOfAuthorizedSides() {
        return this.authorizedSides.cardinality();
    }

    private int getGapForNextGridStep(int currentCoordinate) {
        return CanonicalDBorderItemLocator.getGapForNextGridStep(currentCoordinate, gridSpacing);
    }

    private int getGapForPreviousGridStep(int currentCoordinate) {
        return CanonicalDBorderItemLocator.getGapForPreviousGridStep(currentCoordinate, gridSpacing);
    }
}
