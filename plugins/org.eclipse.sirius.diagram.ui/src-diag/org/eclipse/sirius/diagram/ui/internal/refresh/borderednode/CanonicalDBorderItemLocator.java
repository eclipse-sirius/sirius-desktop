/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh.borderednode;

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
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
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
     * The number of sides. Used to avoid infinite loop exception in there is
     * too many borderNode relative to the size of the container.
     */
    private static final int NB_SIDES = 4;

    private int preferredSide = PositionConstants.WEST;

    private Dimension borderItemOffset = new Dimension(1, 1);

    private Rectangle constraint = new Rectangle(0, 0, 0, 0);

    private Node container;

    private boolean borderItemHasMoved;

    private int currentSide = PositionConstants.WEST;

    /**
     * The parent bounds can be set with
     * {@link #setParentBorderBounds(Rectangle)} if it is known or if the
     * CanonicalDBorderItemLocator must consider a different bounds. If this
     * bounds is not set, it will be computed at the first
     * {@link #getParentBorder()} call.
     */
    private Rectangle parentBorder;

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
     * Sets the side of the parent figure on which the border item should
     * appear.
     * 
     * @param side
     *            the side on which this border item appears as defined in
     *            {@link PositionConstants}
     */
    public void setCurrentSideOfParent(int side) {
        this.currentSide = side;
    }

    /**
     * Returns the preferred side of the parent figure on which to place this
     * border item.
     * 
     * @return the preferred side of the parent figure on which to place this
     *         border item as defined in {@link PositionConstants}
     */
    public int getPreferredSideOfParent() {
        return preferredSide;
    }

    /**
     * Find the closest side when x,y is inside parent.
     * 
     * @param proposedLocation
     *            the proposed location
     * @param parentBorder
     *            the parent border
     * @return draw constant
     */
    public static int findClosestSideOfParent(final Rectangle proposedLocation, final Rectangle parentBorder) {
        // Rectangle parentBorder = getParentBorder();
        final Point parentCenter = parentBorder.getCenter();
        final Point childCenter = proposedLocation.getCenter();

        int position;

        if (childCenter.x < parentCenter.x) {
            // West, North or South.
            if (childCenter.y < parentCenter.y) {
                // west or north
                // closer to west or north?
                final Point parentTopLeft = parentBorder.getTopLeft();
                if (childCenter.y < parentTopLeft.y) {
                    position = PositionConstants.NORTH;
                } else if ((childCenter.x - parentTopLeft.x) <= (childCenter.y - parentTopLeft.y)) {
                    position = PositionConstants.WEST;
                } else {
                    position = PositionConstants.NORTH;
                }
            } else { // west or south
                final Point parentBottomLeft = parentBorder.getBottomLeft();
                if (childCenter.y > parentBottomLeft.y) {
                    position = PositionConstants.SOUTH;
                } else if ((childCenter.x - parentBottomLeft.x) <= (parentBottomLeft.y - childCenter.y)) {
                    position = PositionConstants.WEST;
                } else {
                    position = PositionConstants.SOUTH;
                }
            }
        } else {
            // EAST, NORTH or SOUTH
            if (childCenter.y < parentCenter.y) {
                // north or east
                final Point parentTopRight = parentBorder.getTopRight();
                if (childCenter.y < parentTopRight.y) {
                    position = PositionConstants.NORTH;
                } else if ((parentTopRight.x - childCenter.x) <= (childCenter.y - parentTopRight.y)) {
                    position = PositionConstants.EAST;
                } else {
                    position = PositionConstants.NORTH;
                }
            } else { // south or east.
                final Point parentBottomRight = parentBorder.getBottomRight();
                if (childCenter.y > parentBottomRight.y) {
                    position = PositionConstants.SOUTH;
                } else if ((parentBottomRight.x - childCenter.x) <= (parentBottomRight.y - childCenter.y)) {
                    position = PositionConstants.EAST;
                } else {
                    position = PositionConstants.SOUTH;
                }
            }
        }
        return position;
    }

    /**
     * Relocates the given view.
     * 
     * @param borderItem
     *            The view to relocate
     */
    public void relocate(Node borderItem) {
        final Dimension size = getSize(borderItem);
        final Rectangle rectSuggested = new Rectangle(getPreferredLocation(borderItem), size);
        // If the border item has moved, we change the preferred side, otherwise
        // we let the current side enabled
        if (borderItemHasMoved) {
            final int closestSide = findClosestSideOfParent(rectSuggested, getParentBorder());
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
     * Determine if the the given point conflicts with the position of an
     * existing borderItemFigure.
     * 
     * @param recommendedLocation
     *            the location to check
     * @param nodeSize
     *            the size of the bordered node to check
     * @param portsNodesToIgnore
     *            list of nodes to ignore during conflict detection.
     * @return the optional Rectangle of the border item that is in conflict
     *         with the given bordered node (a none option)
     */
    private Option<Rectangle> conflicts(final Point recommendedLocation, final Dimension nodeSize, final Collection<Node> portsNodesToIgnore) {
        final Rectangle recommendedRect = new Rectangle(recommendedLocation.x, recommendedLocation.y, nodeSize.width, nodeSize.height);
        final List<?> borderItems = container.getPersistedChildren();
        final ListIterator<?> iterator = borderItems.listIterator();
        while (iterator.hasNext()) {
            final Node borderItem = (Node) iterator.next();
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

                    Rectangle borderItemBounds = GMFHelper.getAbsoluteBounds(borderItem);

                    if (extendedDimension != null) {
                        borderItemBounds = PortLayoutHelper.getUncollapseCandidateLocation(extendedDimension, borderItemBounds, getParentBorder());
                    }

                    if (!(portsNodesToIgnore.contains(borderItem)) && borderItemBounds.intersects(recommendedRect)) {
                        return Options.newSome(borderItemBounds);
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
     * Ensure the suggested location actually lies on the parent boundary. The
     * side takes precedence.
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
        if (suggestedSide == PositionConstants.WEST) {
            if (suggestedLocation.x != westX) {
                newX = westX;
            }
            if (suggestedLocation.y < parentFigureY) {
                newY = parentFigureY;
            } else if (suggestedLocation.y > bounds.getBottomLeft().y - borderItemSize.height) {
                newY = bounds.getBottomLeft().y - borderItemSize.height;
            }
        } else if (suggestedSide == PositionConstants.EAST) {
            if (suggestedLocation.x != eastX) {
                newX = eastX;
            }
            if (suggestedLocation.y < parentFigureY) {
                newY = parentFigureY;
            } else if (suggestedLocation.y > bounds.getBottomLeft().y - borderItemSize.height) {
                newY = bounds.getBottomLeft().y - borderItemSize.height;
            }
        } else if (suggestedSide == PositionConstants.SOUTH) {
            if (suggestedLocation.y != southY) {
                newY = southY;
            }
            if (borderItemSize.width > bounds.width) {
                // The border item width is larger than the parent item. In that
                // case, we will center the border item on the south side of the
                // parent.
                newX = parentFigureX - (borderItemSize.width - bounds.width) / 2;
            } else if (suggestedLocation.x < parentFigureX) {
                newX = parentFigureX;
            } else if (suggestedLocation.x > bounds.getBottomRight().x - borderItemSize.width) {
                newX = bounds.getBottomRight().x - borderItemSize.width;
            }
        } else { // NORTH
            if (suggestedLocation.y != northY) {
                newY = northY;
            }
            if (borderItemSize.width > bounds.width) {
                // The border item width is larger than the parent item. In that
                // case, we will center the border item on the north side of the
                // parent.
                newX = parentFigureX - (borderItemSize.width - bounds.width) / 2;
            } else if (suggestedLocation.x < parentFigureX) {
                newX = parentFigureX;
            } else if (suggestedLocation.x > bounds.getBottomRight().x - borderItemSize.width) {
                newX = bounds.getBottomRight().x - borderItemSize.width;
            }
        }
        return new Point(newX, newY);
    }

    /**
     * Locate the recommendedLocation on the south border :
     * <UL>
     * <LI>Search alternately to the left and to the right until find an
     * available space</LI>
     * <LI>And finally if there is no space on this border search on the east
     * border.</LI>
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
        int rightVerticalGap = 0;
        int leftVerticalGap = 0;
        // The recommendedLocationForEast is set when we detected that there is
        // not free space on right of south side.
        Point recommendedLocationForEast = recommendedLocation.getLocation();
        while (resultLocation == null && (isStillFreeSpaceToTheRight || isStillFreeSpaceToTheLeft)) {
            if (isStillFreeSpaceToTheRight) {
                // Move to the right on the south side
                rightTestPoint.x += rightVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(rightTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    rightVerticalGap = (optionalConflictingRectangle.get().x + optionalConflictingRectangle.get().width + 1) - rightTestPoint.x;
                    if (rightTestPoint.x + rightVerticalGap + borderItemSize.width > getParentBorder().getBottomRight().x) {
                        isStillFreeSpaceToTheRight = false;
                        if (circuitCount == NB_SIDES - 1) {
                            // There is no space on either side (so use the last
                            // conflicting position)
                            resultLocation = optionalConflictingRectangle.get().getTopLeft();
                        } else {
                            recommendedLocationForEast = new Point(rightTestPoint.x + rightVerticalGap, optionalConflictingRectangle.get().y - borderItemSize.height - 1);
                        }
                    }
                } else {
                    resultLocation = rightTestPoint;
                }
            }
            if (isStillFreeSpaceToTheLeft && resultLocation == null) {
                // Move to the left on the south side
                leftTestPoint.x -= leftVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(leftTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    leftVerticalGap = leftTestPoint.x - (optionalConflictingRectangle.get().x - borderItemSize.width - 1);
                    if (leftTestPoint.x - leftVerticalGap < getParentBorder().getTopLeft().x) {
                        isStillFreeSpaceToTheLeft = false;
                    }
                } else {
                    resultLocation = leftTestPoint;
                }
            }
        }
        if (resultLocation == null) {
            // south is full, try east.
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForEast, borderItemSize), PositionConstants.EAST, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    /**
     * Locate the recommendedLocation on the north border :
     * <UL>
     * <LI>Search alternately to the left and to the right until find an
     * available space</LI>
     * <LI>And finally if there is no space on this border search on the west
     * border.</LI>
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
        int rightVerticalGap = 0;
        int leftVerticalGap = 0;
        // The recommendedLocationForWest is set when we detected that there is
        // not free space on left of north side.
        Point recommendedLocationForWest = recommendedLocation.getLocation();
        while (resultLocation == null && (isStillFreeSpaceToTheRight || isStillFreeSpaceToTheLeft)) {
            if (isStillFreeSpaceToTheRight) {
                // Move to the right on the north side
                rightTestPoint.x += rightVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(rightTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    rightVerticalGap = (optionalConflictingRectangle.get().x + optionalConflictingRectangle.get().width + 1) - rightTestPoint.x;
                    if (rightTestPoint.x + rightVerticalGap + borderItemSize.width > getParentBorder().getBottomRight().x) {
                        isStillFreeSpaceToTheRight = false;
                    }
                } else {
                    resultLocation = rightTestPoint;
                }
            }
            if (isStillFreeSpaceToTheLeft && resultLocation == null) {
                // Move to the left on the north side
                leftTestPoint.x -= leftVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(leftTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    leftVerticalGap = leftTestPoint.x - (optionalConflictingRectangle.get().x - borderItemSize.width - 1);
                    if (leftTestPoint.x - leftVerticalGap < getParentBorder().getTopLeft().x) {
                        isStillFreeSpaceToTheLeft = false;
                        if (circuitCount == NB_SIDES - 1) {
                            // There is no space on either side (so use the last
                            // conflicting position)
                            resultLocation = optionalConflictingRectangle.get().getTopLeft();
                        } else {
                            recommendedLocationForWest = new Point(leftTestPoint.x - leftVerticalGap, optionalConflictingRectangle.get().y + optionalConflictingRectangle.get().height + 1);
                        }
                    }
                } else {
                    resultLocation = leftTestPoint;
                }
            }
        }
        if (resultLocation == null) {
            // North is full, try west.
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForWest, borderItemSize), PositionConstants.WEST, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    /**
     * Locate the recommendedLocation on the west border :
     * <UL>
     * <LI>Search alternately upward and downward until find an available space</LI>
     * <LI>And finally if there is no space on this border search on the south
     * border.</LI>
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
        // The recommendedLocationForSouth is set when we detected that there is
        // not free space on bottom of west side.
        Point recommendedLocationForSouth = recommendedLocation.getLocation();
        while (resultLocation == null && (isStillFreeSpaceAbove || isStillFreeSpaceBelow)) {
            if (isStillFreeSpaceBelow) {
                // Move down on the west side
                belowTestPoint.y += belowVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(belowTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    belowVerticalGap = optionalConflictingRectangle.get().y + optionalConflictingRectangle.get().height - belowTestPoint.y + 1;
                    if (belowTestPoint.y + belowVerticalGap + borderItemSize.height > getParentBorder().getBottomLeft().y) {
                        isStillFreeSpaceBelow = false;
                        if (circuitCount == NB_SIDES - 1) {
                            // There is no space on either side (so use the last
                            // conflicting position)
                            resultLocation = optionalConflictingRectangle.get().getTopLeft();
                        } else {
                            recommendedLocationForSouth = new Point(belowTestPoint.x + optionalConflictingRectangle.get().width + 1, belowTestPoint.y + belowVerticalGap);
                        }
                    }
                } else {
                    resultLocation = belowTestPoint;
                }
            }
            if (isStillFreeSpaceAbove && resultLocation == null) {
                // Move up on the west side
                aboveTestPoint.y -= aboveVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(aboveTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    aboveVerticalGap = aboveTestPoint.y - (optionalConflictingRectangle.get().y - borderItemSize.height - 1);
                    if (aboveTestPoint.y - aboveVerticalGap < getParentBorder().getTopRight().y) {
                        isStillFreeSpaceAbove = false;
                    }
                } else {
                    resultLocation = aboveTestPoint;
                }
            }
        }
        if (resultLocation == null) {
            // west is full, try south.
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForSouth, borderItemSize), PositionConstants.SOUTH, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    /**
     * Locate the recommendedLocation on the east border :
     * <UL>
     * <LI>Search alternately upward and downward until find an available space</LI>
     * <LI>And finally if there is no space on this border search on the north
     * border.</LI>
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
        // The recommendedLocationForNorth is set when we detected that there is
        // not free space on top of east side.
        Point recommendedLocationForNorth = recommendedLocation.getLocation();
        while (resultLocation == null && (isStillFreeSpaceAbove || isStillFreeSpaceBelow)) {
            if (isStillFreeSpaceBelow) {
                // Move down on the east side
                belowTestPoint.y += belowVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(belowTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    belowVerticalGap = optionalConflictingRectangle.get().y + optionalConflictingRectangle.get().height - belowTestPoint.y + 1;
                    if (belowTestPoint.y + belowVerticalGap + borderItemSize.height > getParentBorder().getBottomLeft().y) {
                        isStillFreeSpaceBelow = false;
                    }
                } else {
                    resultLocation = belowTestPoint;
                }
            }
            if (isStillFreeSpaceAbove && resultLocation == null) {
                // Move up on the east side
                aboveTestPoint.y -= aboveVerticalGap;
                Option<Rectangle> optionalConflictingRectangle = conflicts(aboveTestPoint, borderItemSize, portsNodesToIgnore);
                if (optionalConflictingRectangle.some()) {
                    aboveVerticalGap = aboveTestPoint.y - (optionalConflictingRectangle.get().y - borderItemSize.height - 1);
                    if (aboveTestPoint.y - aboveVerticalGap < getParentBorder().getTopRight().y) {
                        isStillFreeSpaceAbove = false;
                        if (circuitCount == NB_SIDES - 1) {
                            // There is no space on either side (so use the last
                            // conflicting position)
                            resultLocation = optionalConflictingRectangle.get().getTopLeft();
                        } else {
                            recommendedLocationForNorth = new Point(optionalConflictingRectangle.get().x - borderItemSize.width - 1, aboveTestPoint.y - aboveVerticalGap);
                        }
                    }
                } else {
                    resultLocation = aboveTestPoint;
                }
            }
        }
        if (resultLocation == null) {
            // East is full, try north.
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForNorth, borderItemSize), PositionConstants.NORTH, circuitCount + 1, borderItem, portsNodesToIgnore);
        }
        return resultLocation;
    }

    /**
     * Sets the preferred side of the parent figure on which to place this
     * border item.
     * 
     * @param preferredSide
     *            the preferred side of the parent figure on which to place this
     *            border item as defined in {@link PositionConstants}
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
     * Returns the side of the parent figure on which the border item is
     * currently on.
     * 
     * @return the side on which this border item appears as defined in
     *         {@link PositionConstants}
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
     * Set the parent border bounds if it is known. If this bounds is not set,
     * it will be computed at the first {@link #getParentBorder()} call.
     * 
     * @param parentBounds
     *            The bounds to consider for this
     *            {@link CanonicalDBorderItemLocator}.
     */
    public void setParentBorderBounds(Rectangle parentBounds) {
        parentBorder = parentBounds;
    }

    private Dimension getSize(Node borderItem) {
        return GMFHelper.getBounds(borderItem).getSize();
    }

    /**
     * Returns a suitable location for the border item given a proposed Bounds.
     * By implementing this method, the feedback shown when the user moves a
     * border item can reflect where the locator will actually place the border
     * item.
     * <p>
     * For example, if the border item is restricted to being on the border of
     * its parent shape, when the user attempts to move the border item outside
     * the border of the parent shape (the proposed bounds), the feedback will
     * always show the border item on the border. In this case, this method
     * would return a location on the border close to the proposed location.
     * </p>
     * 
     * @param proposedBounds
     *            a proposed bounds
     * @param borderItem
     *            the border item in question
     * @param portsNodesToIgnore
     *            list of nodes to ignore during conflict detection. This list
     *            must contain at least the <code>borderItem</code>.
     * @return a Point corresponding to the valid location
     */
    public Point getValidLocation(Rectangle proposedBounds, Node borderItem, final Collection<Node> portsNodesToIgnore) {
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

        final int side = findClosestSideOfParent(proposedBounds, getParentBorder());
        Point newTopLeft = locateOnBorder(realBounds, side, 0, borderItem, portsNodesToIgnore);
        if (isCollapsed) {
            setBorderItemOffset(oldOffset);
            Rectangle collapsedBounds = PortLayoutHelper.getCollapseCandidateLocation(nodeQuery.getCollapsedSize(), new Rectangle(newTopLeft, nodeQuery.getOriginalDimensionBeforeCollapse()),
                    getParentBorder());
            newTopLeft = collapsedBounds.getLocation();
        }
        return newTopLeft;
    }
}
