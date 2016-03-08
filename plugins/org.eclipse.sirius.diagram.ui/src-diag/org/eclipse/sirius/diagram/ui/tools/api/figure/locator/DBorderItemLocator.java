/******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - adaptation for designer
 *    Laurent Redor    (Obeo) <laurent.redor@obeo.fr>    - Trac #1735 : Port stability
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.api.figure.locator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A specific
 * {@link org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator} for
 * Designer.
 * 
 * @author ymortier
 */
public class DBorderItemLocator extends BorderItemLocator {

    /**
     * The number of sides. Used to avoid infinite loop exception in there is
     * too many borderNode relative to the size of the container.
     */
    private static final int NB_SIDES = 4;

    /** The left top offset. */
    private Dimension leftTopOffset;

    /** The right bottom offset. */
    private Dimension rightBottomOffset;

    /**
     * Indicates whether the position of the border item has already been
     * calculated. Uses to determine the conflicts with other border items
     * locations.
     */
    private boolean located;

    /**
     * Indicates if the border item has moved. This determines if the
     * computation of the position (relocate method) stems from a shift of the
     * port itself or something else (a resizing of its container, for example).
     * 
     */
    private boolean borderItemHasMoved;

    /**
     * This list of figures is set during the build of the command in
     * {@link org.eclipse.sirius.diagram.ui.graphical.edit.policies.SpecificBorderItemSelectionEditPolicy}
     * to be able to know the figures to ignore when draw2d will launch the
     * redraw.
     */
    private List<IFigure> figuresToIgnoreDuringNextRelocate = Lists.newArrayList();

    /**
     * Create an {@link DBorderItemLocator} with the specified parentFigure.
     * 
     * @param parentFigure
     *            the parent figure.
     */
    public DBorderItemLocator(final IFigure parentFigure) {
        super(parentFigure);
    }

    /**
     * Create a {@link DBorderItemLocator} with the specified item, parentFigure
     * and constraint.
     * 
     * @param borderItem
     *            the border item.
     * @param parentFigure
     *            the parent figure.
     * @param constraint
     *            the constraint.
     */
    public DBorderItemLocator(final IFigure borderItem, final IFigure parentFigure, final Rectangle constraint) {
        super(borderItem, parentFigure, constraint);
    }

    /**
     * Create a {@link DBorderItemLocator} with the specified item and
     * preferredSide.
     * 
     * @param parentFigure
     *            the parent figure.
     * @param preferredSide
     *            the preferred side.
     */
    public DBorderItemLocator(final IFigure parentFigure, final int preferredSide) {
        super(parentFigure, preferredSide);
    }

    /**
     * Define the right bottom offset.
     * 
     * @param rightBottomOffset
     *            the right bottom offset.
     */
    public void setRightBottomOffset(final Dimension rightBottomOffset) {
        this.rightBottomOffset = rightBottomOffset;
    }

    /**
     * Return the right bottom offset.
     * 
     * @return the right bottom offset.
     */
    public Dimension getRightBottomOffset() {
        if (this.rightBottomOffset != null) {
            return rightBottomOffset;
        }
        return getBorderItemOffset();
    }

    /**
     * Define the left top offset.
     * 
     * @param leftTopOffset
     *            the left top offset.
     */
    public void setLeftTopOffset(final Dimension leftTopOffset) {
        this.leftTopOffset = leftTopOffset;
    }

    /**
     * Return the left top offset.
     * 
     * @return the left top offset.
     */
    public Dimension getLeftTopOffset() {
        if (this.leftTopOffset != null) {
            return leftTopOffset;
        }
        return getBorderItemOffset();
    }

    /**
     * Get the preferred location. If none has been previously set, use the
     * preferred side to take an initial guess.
     * 
     * @param borderItem
     *            the border item
     * @return point
     */
    @Override
    protected Point getPreferredLocation(final IFigure borderItem) {
        final Point constraintLocation = getConstraint().getLocation();
        final Point ptAbsoluteLocation = this.getAbsoluteToBorder(constraintLocation);
        return ptAbsoluteLocation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator#relocate(org.eclipse.draw2d.IFigure)
     */
    @Override
    public void relocate(final IFigure borderItem) {
        Rectangle parentBounds = getParentFigure().getBounds().getCopy();
        // If the relocate is called before that the figure have been
        // "initialized", it is by-passed.
        if (!(parentBounds.x == 0 && parentBounds.y == 0 && parentBounds.width <= 0 && parentBounds.height <= 0)) {
            final Dimension size = getSize(borderItem);
            final Rectangle rectSuggested = new Rectangle(getPreferredLocation(borderItem), size);
            // If the border item has moved, we change the preferred side,
            // otherwise we let the current side enabled
            if (borderItemHasMoved) {
                final int closestSide = DBorderItemLocator.findClosestSideOfParent(rectSuggested, getParentBorder());
                setPreferredSideOfParent(closestSide);
                setCurrentSideOfParent(closestSide);
                borderItemHasMoved = false;
            } else {
                // We use the notion if figuresToIgnoreDuringNextRelocate only
                // if bordered node is moved.
                figuresToIgnoreDuringNextRelocate.clear();
            }
            final Point ptNewLocation = locateOnBorder(rectSuggested, getCurrentSideOfParent(), 0, borderItem, figuresToIgnoreDuringNextRelocate, new ArrayList<IFigure>());
            borderItem.setLocation(ptNewLocation);
            figuresToIgnoreDuringNextRelocate.clear();
            borderItem.setSize(size);
            this.located = true;
        }
    }

    @Override
    protected Point locateOnBorder(Point suggestedLocation, int suggestedSide, int circuitCount, IFigure borderItem) {
        List<IFigure> figuresToIgnore = Lists.newArrayList();
        figuresToIgnore.add(borderItem);
        return locateOnBorder(new Rectangle(suggestedLocation, getSize(borderItem)), suggestedSide, circuitCount, borderItem, figuresToIgnore, new ArrayList<IFigure>());
    }

    /**
     * The preferred side takes precedence. Search prior on the suggestedSide
     * then on the following side in the anticlockwise. This is behavior similar
     * to that of GMF. We could envisage a more intelligent behavior that avoids
     * passing over other borderedNodes even if it meant a move to the following
     * side.
     * 
     * @param suggestedLocation
     *            the suggested location
     * @param suggestedSide
     *            the suggested side
     * @param circuitCount
     *            recursion count to avoid an infinite loop
     * @param borderItem
     *            the border item.
     * @param portsFiguresToIgnore
     *            the ports figures to ignore
     * @param additionalFiguresForConflictDetection
     *            Figures that are not brothers of the current figure but that
     *            must be used for conflict detection
     * @return point
     */
    protected Point locateOnBorder(final Rectangle suggestedLocation, final int suggestedSide, final int circuitCount, final IFigure borderItem, final Collection<IFigure> portsFiguresToIgnore,
            List<IFigure> additionalFiguresForConflictDetection) {
        Point recommendedLocation = locateOnParent(suggestedLocation, suggestedSide, borderItem);

        Rectangle newRecommendedLocationBounds = new Rectangle(recommendedLocation, suggestedLocation.getSize());
        if (circuitCount < NB_SIDES && conflicts(newRecommendedLocationBounds, borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection).some()) {
            if (suggestedSide == PositionConstants.WEST) {
                recommendedLocation = locateOnWestBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
            } else if (suggestedSide == PositionConstants.SOUTH) {
                recommendedLocation = locateOnSouthBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
            } else if (suggestedSide == PositionConstants.EAST) {
                recommendedLocation = locateOnEastBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
            } else { // NORTH
                recommendedLocation = locateOnNorthBorder(newRecommendedLocationBounds, circuitCount, borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
            }
        }
        return recommendedLocation;
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
     * @param portsFiguresToIgnore
     *            the ports figures to ignore
     * @param additionalFiguresForConflictDetection
     *            Figures that are not brothers of the current figure but that
     *            must be used for conflict detection
     * @return the location where the border item can be put
     */
    protected Point locateOnSouthBorder(final Rectangle recommendedLocation, final int circuitCount, final IFigure borderItem, final Collection<IFigure> portsFiguresToIgnore,
            List<IFigure> additionalFiguresForConflictDetection) {
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(rightTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(leftTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForEast, borderItemSize), PositionConstants.EAST, circuitCount + 1, borderItem, portsFiguresToIgnore,
                    additionalFiguresForConflictDetection);
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
     * @param portsFiguresToIgnore
     *            the ports figures to ignore
     * @param additionalFiguresForConflictDetection
     *            Figures that are not brothers of the current figure but that
     *            must be used for conflict detection
     * @return the location where the border item can be put
     */
    protected Point locateOnNorthBorder(final Rectangle recommendedLocation, final int circuitCount, final IFigure borderItem, final Collection<IFigure> portsFiguresToIgnore,
            List<IFigure> additionalFiguresForConflictDetection) {
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(rightTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(leftTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForWest, borderItemSize), PositionConstants.WEST, circuitCount + 1, borderItem, portsFiguresToIgnore,
                    additionalFiguresForConflictDetection);
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
     * @param portsFiguresToIgnore
     *            the ports figures to ignore
     * @param additionalFiguresForConflictDetection
     *            Figures that are not brothers of the current figure but that
     *            must be used for conflict detection
     * @return the location where the border item can be put
     */
    protected Point locateOnWestBorder(final Rectangle recommendedLocation, final int circuitCount, final IFigure borderItem, final Collection<IFigure> portsFiguresToIgnore,
            List<IFigure> additionalFiguresForConflictDetection) {
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(belowTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(aboveTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForSouth, borderItemSize), PositionConstants.SOUTH, circuitCount + 1, borderItem, portsFiguresToIgnore,
                    additionalFiguresForConflictDetection);
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
     * @param portsFiguresToIgnore
     *            the ports figures to ignore
     * @param additionalFiguresForConflictDetection
     *            Figures that are not brothers of the current figure but that
     *            must be used for conflict detection
     * @return the location where the border item can be put
     */
    protected Point locateOnEastBorder(final Rectangle recommendedLocation, final int circuitCount, final IFigure borderItem, final Collection<IFigure> portsFiguresToIgnore,
            List<IFigure> additionalFiguresForConflictDetection) {
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(belowTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
                Option<Rectangle> optionalConflictingRectangle = conflicts(new Rectangle(aboveTestPoint, borderItemSize), borderItem, portsFiguresToIgnore, additionalFiguresForConflictDetection);
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
            resultLocation = locateOnBorder(new Rectangle(recommendedLocationForNorth, borderItemSize), PositionConstants.NORTH, circuitCount + 1, borderItem, portsFiguresToIgnore,
                    additionalFiguresForConflictDetection);
        }
        return resultLocation;
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
    protected Point locateOnParent(final Rectangle suggestedLocation, final int suggestedSide, final IFigure borderItem) {
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
     * Determine if the the given point conflicts with the position of an
     * existing borderItemFigure.
     * 
     * @param recommendedRect
     *            The desired location
     * @param targetBorderItem
     *            the border node for which we detect conflicts.
     * @param portsFiguresToIgnore
     *            the ports figures to ignore
     * @param additionalFiguresForConflictDetection
     *            Figures that are not brothers of the current figure but that
     *            must be used for conflict detection
     * @return the optional Rectangle of the border item that is in conflict
     *         with the given bordered node (a none option)
     */
    protected Option<Rectangle> conflicts(final Rectangle recommendedRect, final IFigure targetBorderItem, final Collection<IFigure> portsFiguresToIgnore,
            List<IFigure> additionalFiguresForConflictDetection) {

        // 1- Detect conflicts with brother figures
        Option<Rectangle> conflictedRectangle = conflicts(recommendedRect, getBrotherFigures(targetBorderItem), portsFiguresToIgnore);
        // 2- Detect conflicts with feedback figures (if there is no brother
        // conflicts).
        if (!conflictedRectangle.some() && additionalFiguresForConflictDetection != null && additionalFiguresForConflictDetection.size() > 0) {
            // Translate to same coordinates system as current border node
            // (targetBorderItem)
            Iterable<IFigure> feedbackFigures = Iterables.transform(additionalFiguresForConflictDetection, new Function<IFigure, IFigure>() {
                @Override
                public IFigure apply(IFigure input) {
                    Rectangle newBounds = new Rectangle(input.getBounds());
                    input.translateToAbsolute(newBounds);
                    targetBorderItem.translateToRelative(newBounds);
                    input.setBounds(newBounds);
                    return input;
                }
            });
            conflictedRectangle = conflicts(recommendedRect, Lists.newArrayList(feedbackFigures), portsFiguresToIgnore);
            // Reset to the feedback layer coordinates system
            for (IFigure figure : additionalFiguresForConflictDetection) {
                Rectangle newBounds = new Rectangle(figure.getBounds());
                targetBorderItem.translateToAbsolute(newBounds);
                figure.translateToRelative(newBounds);
                figure.setBounds(newBounds);
            }
        }
        return conflictedRectangle;
    }

    /**
     * Get the figures of the brother's border nodes of
     * <code>targetBorderItem</code>.
     * 
     * @param targetBorderItem
     *            Contextual border item.
     * @return The list of figure of the brother border nodes.
     */
    protected List<IFigure> getBrotherFigures(final IFigure targetBorderItem) {
        @SuppressWarnings("unchecked")
        Iterable<IFigure> brotherFigures = Iterables.filter(targetBorderItem.getParent().getChildren(),
                Predicates.and(Predicates.instanceOf(IFigure.class), Predicates.not(Predicates.equalTo(targetBorderItem))));
        return Lists.newArrayList(brotherFigures);
    }

    /**
     * Determine if the the given rectangle conflicts with the position of
     * <code>figuresToCheck</code>.
     * 
     * @param recommendedRect
     *            The desired bounds
     * @param figuresToCheck
     *            Other figures to check if they conflict the
     *            <code>recommendedRect</code>
     * @param portsFiguresToIgnore
     *            the ports figures to ignore, even if they are in
     *            <code>figuresToCheck</code>
     * @return the optional Rectangle of the border item that is in conflict
     *         with the given bordered node (none option if no conflict)
     */
    protected Option<Rectangle> conflicts(final Rectangle recommendedRect, List<IFigure> figuresToCheck, final Collection<IFigure> portsFiguresToIgnore) {
        final ListIterator<IFigure> iterator = figuresToCheck.listIterator();
        while (iterator.hasNext()) {
            final IFigure borderItem = iterator.next();
            if (!portsFiguresToIgnore.contains(borderItem)) {
                boolean takeIntoAccount = true;
                // We consider the brothers that :
                // * have a parent without layoutManager and are directly in a
                // Layer (this case corresponds to feedback of collapsed
                // bordered nodes that are expanded during drop)
                // * have a parent with a layoutManager and that contains a
                // constraint of type DBorderItemLocator that is located.
                if (borderItem.getParent().getLayoutManager() == null) {
                    if (!(borderItem.getParent() instanceof Layer)) {
                        takeIntoAccount = false;
                    }
                } else if (borderItem.getParent().getLayoutManager().getConstraint(borderItem) instanceof DBorderItemLocator) {
                    final DBorderItemLocator airBorderItemLocator = (DBorderItemLocator) borderItem.getParent().getLayoutManager().getConstraint(borderItem);
                    takeIntoAccount = airBorderItemLocator.located && !airBorderItemLocator.borderItemHasMoved;
                }
                if (borderItem.isVisible() && takeIntoAccount) {
                    final Rectangle rect = new Rectangle(borderItem.getBounds());
                    if (!(portsFiguresToIgnore.contains(borderItem)) && rect.intersects(recommendedRect)) {
                        return Options.newSome(rect);
                    }
                }
            }
        }
        return Options.newNone();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator#setConstraint(org.eclipse.draw2d.geometry.Rectangle)
     */
    @Override
    public void setConstraint(final Rectangle theConstraint) {
        if (!theConstraint.equals(getConstraint())) {
            borderItemHasMoved = true;
        }
        super.setConstraint(theConstraint);
    }

    /**
     * Provides a copy of the current {@link BorderItemLocator} constraint.
     * 
     * @return a copy of the constraint rectangle.
     */
    public Rectangle getCurrentConstraint() {
        return super.getConstraint().getCopy();
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
        if (canHandleWestSide(parentCenter, childCenter)) {
            // West, North or South.
            position = handleWestSide(parentBorder, parentCenter, childCenter);
        } else {
            position = handleEastSide(parentBorder, parentCenter, childCenter);
        }
        return position;
    }

    private static boolean canHandleWestSide(final Point parentCenter, final Point childCenter) {
        return childCenter.x < parentCenter.x;
    }

    // south or east.
    private static int handleWestSide(final Rectangle parentBorder, final Point parentCenter, final Point childCenter) {
        int position;
        if (childCenter.y < parentCenter.y) {
            // west or north
            position = handleNorthWest(parentBorder, childCenter);
        } else {
            // west or south
            position = handleSouthWest(parentBorder, childCenter);
        }
        return position;
    }

    private static int handleNorthWest(final Rectangle parentBorder, final Point childCenter) {
        int position;
        // closer to west or north?
        final Point parentTopLeft = parentBorder.getTopLeft();
        if (childCenter.y < parentTopLeft.y) {
            position = PositionConstants.NORTH;
        } else if ((childCenter.x - parentTopLeft.x) <= (childCenter.y - parentTopLeft.y)) {
            position = PositionConstants.WEST;
        } else {
            position = PositionConstants.NORTH;
        }
        return position;
    }

    private static int handleSouthWest(final Rectangle parentBorder, final Point childCenter) {
        int position;
        final Point parentBottomLeft = parentBorder.getBottomLeft();
        if (childCenter.y > parentBottomLeft.y) {
            position = PositionConstants.SOUTH;
        } else if ((childCenter.x - parentBottomLeft.x) <= (parentBottomLeft.y - childCenter.y)) {
            position = PositionConstants.WEST;
        } else {
            position = PositionConstants.SOUTH;
        }
        return position;
    }

    private static int handleEastSide(final Rectangle parentBorder, final Point parentCenter, final Point childCenter) {
        int position;
        // EAST, NORTH or SOUTH
        if (childCenter.y < parentCenter.y) {
            // north or east
            position = handleNorthEast(parentBorder, childCenter);
        } else { // south or east.
            position = handleSouthEast(parentBorder, childCenter);
        }
        return position;
    }

    private static int handleNorthEast(final Rectangle parentBorder, final Point childCenter) {
        int position;
        final Point parentTopRight = parentBorder.getTopRight();
        if (childCenter.y < parentTopRight.y) {
            position = PositionConstants.NORTH;
        } else if ((parentTopRight.x - childCenter.x) <= (childCenter.y - parentTopRight.y)) {
            position = PositionConstants.EAST;
        } else {
            position = PositionConstants.NORTH;
        }
        return position;
    }

    private static int handleSouthEast(final Rectangle parentBorder, final Point childCenter) {
        int position;
        final Point parentBottomRight = parentBorder.getBottomRight();
        if (childCenter.y > parentBottomRight.y) {
            position = PositionConstants.SOUTH;
        } else if ((parentBottomRight.x - childCenter.x) <= (parentBottomRight.y - childCenter.y)) {
            position = PositionConstants.EAST;
        } else {
            position = PositionConstants.SOUTH;
        }
        return position;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator#getValidLocation(org.eclipse.draw2d.geometry.Rectangle,
     *      org.eclipse.draw2d.IFigure)
     */
    @Override
    public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem) {
        final int side = DBorderItemLocator.findClosestSideOfParent(proposedLocation, getParentBorder());
        List<IFigure> figuresToIgnore = Lists.newArrayList();
        figuresToIgnore.add(borderItem);
        final Point newTopLeft = locateOnBorder(proposedLocation, side, 0, borderItem, figuresToIgnore, new ArrayList<IFigure>());
        Rectangle realLocation = proposedLocation.getCopy();
        realLocation.setLocation(newTopLeft);
        return realLocation;
    }

    /**
     * Lets consider this border item as not fixed.
     */
    public void unfix() {
        located = false;
    }

    /**
     * Get valid location.
     * 
     * @param proposedLocation
     *            a proposed location and optionally size
     * @param borderItem
     *            the border item in question
     * @param figuresToIgnore
     *            list of figures to ignore during conflict detection. This list
     *            must contain at least the <code>borderItem</code>.
     * @param additionalFiguresForConflictDetection
     *            Figures that are not brothers of the current figure but that
     *            must be used for conflict detection
     * @return a rectangle containing the valid location
     */
    public Rectangle getValidLocation(Rectangle proposedLocation, IFigure borderItem, Collection<IFigure> figuresToIgnore, List<IFigure> additionalFiguresForConflictDetection) {
        final Rectangle realLocation = new Rectangle(proposedLocation);
        final int side = DBorderItemLocator.findClosestSideOfParent(proposedLocation, getParentBorder());
        final Point newTopLeft = locateOnBorder(proposedLocation, side, 0, borderItem, figuresToIgnore, additionalFiguresForConflictDetection);
        realLocation.setLocation(newTopLeft);
        return realLocation;
    }

    /**
     * This method must be used only when commands are build to move a bordered
     * node (for example in
     * {@link org.eclipse.sirius.diagram.ui.graphical.edit.policies.SpecificBorderItemSelectionEditPolicy}
     * ) after calling
     * {@link #getValidLocation(Rectangle, IFigure, Collection, List))} .
     * 
     * @param figuresToIgnore
     *            The list of figures to ignore.
     */
    public void setFiguresToIgnoresDuringNextRelocate(List<IFigure> figuresToIgnore) {
        this.figuresToIgnoreDuringNextRelocate = figuresToIgnore;
    }

    /**
     * This method set to false the borderItemHasMoved field. It must be used
     * carefully only in case where {@link #setConstraint(Rectangle)} is called
     * to compute temporary a
     * {@link #getValidLocation(Rectangle, IFigure, Collection, List)}, one time
     * with a new value and just after with the old value.
     */
    public void resetBorderItemMovedState() {
        this.borderItemHasMoved = false;
    }
}
