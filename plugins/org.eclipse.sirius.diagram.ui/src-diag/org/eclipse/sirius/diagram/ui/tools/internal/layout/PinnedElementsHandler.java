/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.EAST;
import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.NORTH;
import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.NORTH_EAST;
import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.NORTH_WEST;
import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.SOUTH;
import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.SOUTH_EAST;
import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.SOUTH_WEST;
import static org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction.WEST;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Fixes the layout of a particular level as done by a base layout provider to
 * take pinned elements into account.
 * <p>
 * The general algorithm is:
 * <ol>
 * <li>move all the pinned elements back to their original positions;</li>
 * <li>pack the elements by removing horizontal and vertical gaps the first step
 * may have created;</li>
 * <li>resolve potential overlaps between pinned elements and unpinned elements
 * by moving unpinned elements aside.</li>
 * </ol>
 * 
 * @author pcdavid
 */
public class PinnedElementsHandler {
    /**
     * A flag to enable additional but potentially costly asserts and/or debug
     * messages.
     */
    private static final boolean DEBUG = false;

    /**
     * Constant to indicate that padding information should be included in
     * bounds/bounding-box computation.
     */
    private static final boolean INCLUDE_PADDING = true;

    /**
     * Constant to indicate that padding information should be not included in
     * bounds/bounding-box computation.
     */
    private static final boolean EXCLUDE_PADDING = false;

    /**
     * The gap width to leave in between parts in addition to their padding
     * during the packing phase. If gaps are too small, there is not enough room
     * left to route the edges between parts, which can cause them to take a
     * longer route. Set to twice the default gap.
     */
    private static final int MINIMAL_GAP_WIDTH = 60;

    /**
     * A predicate to identify pinned/fixed edit-parts.
     */
    private final Predicate<IGraphicalEditPart> isPinned;

    /**
     * Compares points in left-to-right, top-to-bottom ("reading") order.
     */
    private final Comparator<Point> pointComparator = new Comparator<Point>() {
        public int compare(final Point p1, final Point p2) {
            if (p1.y != p2.y) {
                return p1.y - p2.y;
            } else {
                return p1.x - p2.x;
            }
        }
    };

    /**
     * A comparator used to order edit parts from top-left to bottom-right. Used
     * to produce a more regular and predictable result.
     */
    private final Comparator<IGraphicalEditPart> positionComparator = new Comparator<IGraphicalEditPart>() {
        public int compare(final IGraphicalEditPart igep1, final IGraphicalEditPart igep2) {
            return pointComparator.compare(getCurrentPosition(igep1), getCurrentPosition(igep2));
        }
    };

    /**
     * A comparator to sort edit-parts from left to right (taking their top-left
     * point as reference).
     */
    private final Comparator<IGraphicalEditPart> leftToRightComparator = new Comparator<IGraphicalEditPart>() {
        public int compare(final IGraphicalEditPart p1, final IGraphicalEditPart p2) {
            return getCurrentPosition(p1).x - getCurrentPosition(p2).x;
        }
    };

    /**
     * A comparator to sort edit-parts from top to bottom (taking their top-left
     * point as reference).
     */
    private final Comparator<IGraphicalEditPart> topToBottomComparator = new Comparator<IGraphicalEditPart>() {
        public int compare(final IGraphicalEditPart p1, final IGraphicalEditPart p2) {
            return getCurrentPosition(p1).y - getCurrentPosition(p2).y;
        }
    };

    /**
     * All the edit parts to consider for the layout, ordered by their position.
     */
    private final SortedSet<IGraphicalEditPart> allEditParts = Sets.newTreeSet(positionComparator);

    /**
     * All the pinned edit parts. A subset of <code>allEditParts</code>.
     */
    private final SortedSet<IGraphicalEditPart> fixedEditParts = Sets.newTreeSet(positionComparator);

    /**
     * The initial bounds of the edit parts, as computed by previous layout
     * providers but not yet applied.
     */
    private final Map<IGraphicalEditPart, Rectangle> initialBounds;

    /**
     * The currently computed bounds of the edit parts. Stored separately so
     * they can be easily reset.
     */
    private final Map<IGraphicalEditPart, Rectangle> currentBounds = Maps.newHashMap();

    /**
     * Provides access to additional layout constraints and preferences
     * specified in the VSM.
     */
    private final DiagramLayoutCustomization layoutCustomization;

    /**
     * IDiagramElementEditPart which are not actually pinned but have to stay
     * fixed.
     */
    private ArrayList<IDiagramElementEditPart> elementsToKeepFixed;

    /**
     * Creates a new resolver.
     * 
     * @param parts
     *            all the edit parts to consider.
     * @param initialBounds
     *            the initial locations of the edit parts, as computed by
     *            previous layout providers but not yet applied.
     * @param elementsToKeepFixed
     *            IDiagramElementEditPart which are not actually pinned but have
     *            to stay fixed
     */
    public PinnedElementsHandler(final Collection<IGraphicalEditPart> parts, final Map<IGraphicalEditPart, Rectangle> initialBounds, ArrayList<IDiagramElementEditPart> elementsToKeepFixed) {
        this.initialBounds = Collections.unmodifiableMap(getAllInitialPositions(parts, initialBounds));
        this.elementsToKeepFixed = elementsToKeepFixed;
        this.allEditParts.addAll(parts);
        this.isPinned = new IsPinnedPredicate(this.elementsToKeepFixed);
        this.fixedEditParts.addAll(Collections2.filter(parts, isPinned));
        this.layoutCustomization = new DiagramLayoutCustomization();
        this.layoutCustomization.initializePaddingWithEditParts(Lists.newArrayList(parts));
    }

    private Map<IGraphicalEditPart, Rectangle> getAllInitialPositions(final Collection<IGraphicalEditPart> parts, final Map<IGraphicalEditPart, Rectangle> explicitBounds) {
        final Map<IGraphicalEditPart, Rectangle> result = Maps.newHashMap(explicitBounds);
        for (IGraphicalEditPart part : parts) {
            if (!result.containsKey(part)) {
                final Rectangle bounds = part.getFigure().getBounds().getCopy();
                result.put(part, bounds);
            }
        }
        return result;
    }

    /**
     * Computes the new locations of all the parts to move to handle pinned
     * elements properly.
     * 
     * @return for each part which needs to move, the new position it should be
     *         moved to.
     */
    public Map<IGraphicalEditPart, Point> computeSolution() {
        resetPinnedElements();
        removeGaps();
        resolveOverlaps();
        return getSolution();
    }

    private boolean hasRemainingSolvableOverlaps() {
        for (IGraphicalEditPart part : fixedEditParts) {
            if (!Collections2.filter(findOverlappingParts(part), Predicates.not(isPinned)).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Move all the pinned parts back to their original positions. We can not
     * tell the original layout algorithm to simply ignore them (too many
     * side-effects) or consider them as obstacles, so we must cleanup after it.
     */
    private void resetPinnedElements() {
        for (IGraphicalEditPart part : fixedEditParts) {
            // Access currentBounds directly: #setCurrentPosition() refuses to
            // move pinned parts.
            currentBounds.put(part, part.getFigure().getBounds().getCopy());
        }
    }

    /**
     * Pack the layout to avoid vertical and horizontal gaps left by the pinned
     * parts after they are moved back to their original positions. Only the
     * gaps which take the whole height/width of the diagram and are wide enough
     * are taken into account ("wide enough" meaning beyond the configurable
     * padding associated to the diagram elements).
     */
    private void removeGaps() {
        if (fixedEditParts.isEmpty()) {
            /*
             * The packing algorithms are potentially costly if there are many
             * elements. If there are no pinned elements, the basic layout
             * algorithms should not have created any substantial gaps, so don't
             * try to find gaps which very probably do not exist.
             */
            return;
        }
        packHorizontally();
        packVertically();
    }

    /**
     * Finds all the packable vertical gaps with no edit-parts and moves the
     * parts beyond them to the left to reduce the gap to the minimum while
     * still respecting the parts' padding.
     */
    private void packHorizontally() {
        final int[] hRange = getHorizontalRange(allEditParts, EXCLUDE_PADDING);
        final List<IGraphicalEditPart> movableParts = Lists.newArrayList(Collections2.filter(allEditParts, Predicates.not(isPinned)));
        Collections.sort(movableParts, leftToRightComparator);
        for (int i = 0; i < movableParts.size(); i++) {
            /*
             * Split the movable parts into two groups (left and right) on index
             * i. If there is a sufficiently large gap between the two groups,
             * move the elements of the right group to the left.
             */
            final Set<IGraphicalEditPart> leftSide = Sets.newHashSet(movableParts.subList(0, i));
            final Rectangle leftBox;
            final Insets leftPadding;
            if (i == 0) {
                // Artificial box corresponding to the left margin of the figure
                // along which to pack.
                leftBox = new Rectangle(hRange[0] - 1, 0, 1, 1);
                leftPadding = new Insets(0, 0, 0, 0);
            } else {
                leftBox = getBoundingBox(leftSide, EXCLUDE_PADDING);
                leftPadding = getPadding(leftSide);
            }

            final Set<IGraphicalEditPart> rightSide = Sets.newHashSet(movableParts.subList(i, movableParts.size()));
            final Rectangle rightBox = getBoundingBox(rightSide, EXCLUDE_PADDING);
            final Insets rightPadding = getPadding(rightSide);

            final int currentGapWidth = rightBox.getLeft().x - leftBox.getRight().x;
            final int minGapWidth = Math.max(MINIMAL_GAP_WIDTH, Math.max(leftPadding.right, rightPadding.left));
            if (i == 0 && isHorizontalOriginFree(allEditParts, hRange[0])) {
                // We can move the rightSide elements to the free space
                translate(rightSide, new Dimension(-currentGapWidth, 0));
            } else if (currentGapWidth > minGapWidth) {
                translate(rightSide, new Dimension(-(currentGapWidth - minGapWidth), 0));
            }
        }
    }

    /**
     * Check if the some parts of the diagram are already to the x origin
     * position.
     * 
     * @param parts
     *            List of parts to check
     * @param xOrigin
     *            The x coordinate to consider as the origin
     * @return true if the x origin is free
     */
    private boolean isHorizontalOriginFree(SortedSet<IGraphicalEditPart> parts, int xOrigin) {
        boolean result = true;
        for (IGraphicalEditPart part : parts) {
            final Rectangle bounds = getCurrentBounds(part, false);
            result = result && bounds.x != xOrigin;
        }
        return result;
    }

    /**
     * Check if the some parts of the diagram are already to the y origin
     * position.
     * 
     * @param parts
     *            List of parts to check
     * @param yOrigin
     *            The y coordinate to consider as the origin
     * @return true if the x origin is free
     */
    private boolean isVerticalOriginFree(SortedSet<IGraphicalEditPart> parts, int yOrigin) {
        boolean result = true;
        for (IGraphicalEditPart part : parts) {
            final Rectangle bounds = getCurrentBounds(part, false);
            result = result && bounds.y != yOrigin;
        }
        return result;
    }

    /**
     * Compute the horizontal position range for the given <code>parts</code>.
     * 
     * @param parts
     *            The list of parts to consider
     * @param includePadding
     *            true if we must include padding, false otherwise
     * @return the horizontal range
     */
    private int[] getHorizontalRange(final Collection<IGraphicalEditPart> parts, final boolean includePadding) {
        int minPadding = getSmallestHorizontalMargin(parts);
        int min = minPadding;
        int max = Integer.MIN_VALUE;
        for (IGraphicalEditPart part : parts) {
            final Rectangle bounds = getCurrentBounds(part, includePadding);
            min = Math.min(min, bounds.getLeft().x);
            max = Math.max(max, bounds.getRight().x);
        }
        // The minimum can not be less than the minPadding
        min = Math.max(min, minPadding);
        return new int[] { min, max };
    }

    /**
     * Get the smallest margin to the left of the container for the given
     * <code>parts</code>.
     * 
     * @param parts
     *            The list of parts to consider
     * @return the smallest margin to the left of the container
     */
    private int getSmallestHorizontalMargin(final Collection<IGraphicalEditPart> parts) {
        int min = Integer.MAX_VALUE;
        for (IGraphicalEditPart part : parts) {
            if (part.getParent() instanceof DiagramEditPart) {
                // We don't consider padding in diagram
                min = 0;
            } else {
                min = Math.min(min, layoutCustomization.getNodePadding(part).left);
            }
        }
        return min;
    }

    /**
     * Finds all the packable horizontal gaps with no edit-parts and moves the
     * parts beyond them to the top to reduce the gap to the minimum while still
     * respecting the parts' padding.
     */
    private void packVertically() {
        final int[] vRange = getVerticalRange(allEditParts, EXCLUDE_PADDING);
        final List<IGraphicalEditPart> movableParts = Lists.newArrayList(Collections2.filter(allEditParts, Predicates.not(isPinned)));
        Collections.sort(movableParts, topToBottomComparator);
        for (int i = 0; i < movableParts.size(); i++) {
            /*
             * Split the movable parts into two groups (top and bottom) on index
             * i. If there is a sufficiently large gap between the two groups,
             * move the elements of the bottom group to the top.
             */
            final Set<IGraphicalEditPart> topSide = Sets.newHashSet(movableParts.subList(0, i));
            final Rectangle topBox;
            final Insets topPadding;
            if (i == 0) {
                // Artificial box corresponding to the top margin of the figure
                // along which to pack.
                topBox = new Rectangle(0, vRange[0] - 1, 1, 1);
                topPadding = new Insets(0, 0, 0, 0);
            } else {
                topBox = getBoundingBox(topSide, EXCLUDE_PADDING);
                topPadding = getPadding(topSide);
            }

            final Set<IGraphicalEditPart> bottomSide = Sets.newHashSet(movableParts.subList(i, movableParts.size()));
            final Rectangle bottomBox = getBoundingBox(bottomSide, EXCLUDE_PADDING);
            final Insets bottomPadding = getPadding(bottomSide);

            final int currentGapWidth = bottomBox.getTop().y - topBox.getBottom().y;
            final int minGapWidth = Math.max(MINIMAL_GAP_WIDTH, Math.max(topPadding.bottom, bottomPadding.top));
            if (i == 0 && isVerticalOriginFree(allEditParts, vRange[0])) {
                // We can move the bottomSide elements to the free space
                translate(bottomSide, new Dimension(0, -currentGapWidth));
            } else if (currentGapWidth > minGapWidth) {
                translate(bottomSide, new Dimension(0, -(currentGapWidth - minGapWidth)));
            }
        }
    }

    /**
     * Compute the vertical position range for the given <code>parts</code>.
     * 
     * @param parts
     *            The list of parts to consider
     * @param includePadding
     *            true if we must include padding, false otherwise
     * @return the vertical range
     */
    private int[] getVerticalRange(final Collection<IGraphicalEditPart> parts, final boolean includePadding) {
        int minPadding = getSmallestVerticalMargin(parts);
        int min = minPadding;
        int max = Integer.MIN_VALUE;
        for (IGraphicalEditPart part : parts) {
            final Rectangle bounds = getCurrentBounds(part, includePadding);
            min = Math.min(min, bounds.getTop().y);
            max = Math.max(max, bounds.getBottom().y);
        }
        // The minimum can not be less than the minPadding
        min = Math.max(min, minPadding);
        return new int[] { min, max };
    }

    /**
     * Get the smallest margin to the top of the container for the given
     * <code>parts</code>.
     * 
     * @param parts
     *            The list of parts to consider
     * @return the smallest margin to the top of the container
     */
    private int getSmallestVerticalMargin(final Collection<IGraphicalEditPart> parts) {
        int min = Integer.MAX_VALUE;
        for (IGraphicalEditPart part : parts) {
            if (part.getParent() instanceof DiagramEditPart) {
                // We don't consider padding in diagram
                min = 0;
            } else {
                min = Math.min(min, layoutCustomization.getNodePadding(part).top);
            }
        }
        return min;
    }

    /**
     * Resolve all the overlaps between pinned parts and movable parts.
     */
    private void resolveOverlaps() {
        for (IGraphicalEditPart part : fixedEditParts) {
            resolveOverlaps(part);
        }
        assert !hasRemainingSolvableOverlaps() : "solvable but unsolved overlaps remain";
    }

    /**
     * Resolve all the overlaps concerning the given fixed edit part. This
     * method may also resolve overlaps concerning other fixed parts in the
     * process, but will at least resolve all the (solvable) ones concerning the
     * specified part. After successful execution of this method, the only parts
     * overlapping <code>fixedPart</code>, if any, are also fixed parts, and
     * thus are unsolvable overlaps.
     * 
     * @param fixedPart
     *            the fixed edit part to consider.
     */
    private void resolveOverlaps(final IGraphicalEditPart fixedPart) {
        final Set<IGraphicalEditPart> solvableOverlaps = Sets.filter(findOverlappingParts(fixedPart), Predicates.not(isPinned));
        final Map<Direction, SortedSet<IGraphicalEditPart>> groupedOverlaps = groupByDirection(fixedPart, solvableOverlaps);
        for (Entry<Direction, SortedSet<IGraphicalEditPart>> group : groupedOverlaps.entrySet()) {
            // For a same group, we kept the movedPositions to allow a complete
            // rollback to move again several parts in same time
            Map<IGraphicalEditPart, Point> previousMovedPositionsBefore = Maps.newHashMap();
            for (IGraphicalEditPart part : group.getValue()) {
                assert overlaps(fixedPart, part);
                previousMovedPositionsBefore = moveAside(Collections.singleton(part), Collections.singleton(fixedPart), group.getKey(), previousMovedPositionsBefore);
                assert !overlaps(fixedPart, part);
            }
        }
        assert Collections2.filter(findOverlappingParts(fixedPart), Predicates.not(isPinned)).isEmpty() : "solvable but unsolved overlaps remain";
    }

    /**
     * Move the specified movable parts in the specified direction enough to
     * avoid overlaps with all the specified fixed parts while not creating any
     * new overlap with other fixed parts. All the movable parts are translated
     * of the same amount, as a group. More movable parts than the ones
     * specified explicitly may be move along as they are "pushed" aside to make
     * enough room.
     * 
     * @param parts
     *            the parts to move.
     * @param fixedParts
     *            the fixed parts to avoid.
     * @param dir
     *            the general direction in which to move the movable parts.
     * @param previousMovedPositionsOfSameDir
     *            the list of original position of each edit parts that have
     *            previously moved in this direction
     * @return The positions done during this step (and previous steps) to
     *         eventually used it to restore the previous position.
     */
    private Map<IGraphicalEditPart, Point> moveAside(final Set<IGraphicalEditPart> parts, final Set<IGraphicalEditPart> fixedParts, final Direction dir,
            Map<IGraphicalEditPart, Point> previousMovedPositionsOfSameDir) {
        /*
         * First try to move just enough to avoid the explicitly specified
         * obstacles.
         */
        addSavePositions(parts, previousMovedPositionsOfSameDir);
        tryMove(parts, fixedParts, dir);
        final Set<IGraphicalEditPart> overlaps = findOverlappingParts(parts);
        if (!overlaps.isEmpty()) {
            /*
             * We created new overlaps. Try a more aggressive change, taking
             * more parts into consideration and/or moving further.
             */
            Set<IGraphicalEditPart> newMovables = parts;
            Set<IGraphicalEditPart> newFixed = fixedParts;

            final Set<IGraphicalEditPart> movableOverlaps = Sets.newHashSet(Collections2.filter(overlaps, Predicates.not(isPinned)));
            if (!movableOverlaps.isEmpty()) {
                /*
                 * If we created new overlaps with movable parts, simply re-try
                 * with an extended set of movable parts including the ones we
                 * need to push along.
                 */
                newMovables = Sets.union(parts, movableOverlaps);
            }

            final Set<IGraphicalEditPart> fixedOverlaps = Sets.newHashSet(Collections2.filter(overlaps, isPinned));
            if (!fixedOverlaps.isEmpty()) {
                /*
                 * If we created new overlaps with other fixed parts, re-try
                 * with an extended set of fixed obstacles to avoid.
                 */
                newFixed = Sets.union(fixedParts, fixedOverlaps);
            }

            /*
             * Retry with the new, extended sets of parts to consider.
             */
            assert newMovables.size() > parts.size() || newFixed.size() > fixedParts.size();
            moveParts(newMovables, previousMovedPositionsOfSameDir);
            moveAside(newMovables, newFixed, dir, previousMovedPositionsOfSameDir);
        }
        /*
         * Check that the specified movable parts no longer overlap with the
         * specified fixed parts.
         */
        assert Sets.intersection(Sets.filter(findOverlappingParts(fixedParts), Predicates.not(isPinned)), parts).isEmpty();
        return previousMovedPositionsOfSameDir;
    }

    private Map<IGraphicalEditPart, Point> addSavePositions(final Set<IGraphicalEditPart> parts, Map<IGraphicalEditPart, Point> positionsBefore) {
        for (IGraphicalEditPart part : parts) {
            positionsBefore.put(part, getCurrentPosition(part));
        }
        return positionsBefore;
    }

    /**
     * Translate all the given <code>parts</code> of the same amount in the
     * specified <code>direction</code> as far as required to avoid overlaps
     * with the specified <code>fixedParts</code>. The move may create new
     * overlaps with parts other than those in <code>fixedParts</code>.
     */
    private void tryMove(final Set<IGraphicalEditPart> parts, final Set<IGraphicalEditPart> fixedParts, final Direction direction) {
        assert !Sets.intersection(Sets.filter(findOverlappingParts(fixedParts), Predicates.not(isPinned)), parts).isEmpty();
        final Rectangle movablesBox = getBoundingBox(parts, EXCLUDE_PADDING);
        final Insets movablesPadding = getPadding(parts);
        final Rectangle fixedBox = getBoundingBox(fixedParts, EXCLUDE_PADDING);
        final Insets fixedPadding = getPadding(fixedParts);
        final Dimension move = computeMoveVector(movablesBox, movablesPadding, fixedBox, fixedPadding, direction);
        for (IGraphicalEditPart part : parts) {
            translate(part, move);
        }
        assert Sets.intersection(Sets.filter(findOverlappingParts(fixedParts), Predicates.not(isPinned)), parts).isEmpty();
    }

    /**
     * Computes the global padding of a set of edit parts.
     */
    private Insets getPadding(final Set<IGraphicalEditPart> parts) {
        final Rectangle smallBox = getBoundingBox(parts, EXCLUDE_PADDING);
        final Rectangle bigBox = getBoundingBox(parts, INCLUDE_PADDING);
        final int top = verticalDistance(bigBox.getTop(), smallBox.getTop());
        final int left = horizontalDistance(bigBox.getLeft(), smallBox.getLeft());
        final int bottom = verticalDistance(bigBox.getBottom(), smallBox.getBottom());
        final int right = horizontalDistance(bigBox.getRight(), smallBox.getRight());
        return new Insets(top, left, bottom, right);
    }

    private void translate(final Set<IGraphicalEditPart> parts, final Dimension move) {
        for (IGraphicalEditPart part : parts) {
            translate(part, move);
        }
    }

    private void translate(final IGraphicalEditPart part, final Dimension move) {
        setCurrentPosition(part, getCurrentPosition(part).getTranslated(move));
    }

    /**
     * Computes a move vector suitable to translate <code>movable</code> in the
     * specified <code>direction</code> until it no longer overlaps with
     * <code>fixed</code>. Assumes the two rectangles specified overlap when
     * taking their padding into account.
     */
    private Dimension computeMoveVector(final Rectangle movable, final Insets movablePadding, final Rectangle fixed, final Insets fixedPadding, final Direction direction) {
        final Dimension move;
        if (direction == NORTH) {
            move = computeNorthMoveVector(movable, movablePadding, fixed, fixedPadding);
        } else if (direction == SOUTH) {
            move = computeSouthMoveVector(movable, movablePadding, fixed, fixedPadding);
        } else if (direction == EAST) {
            move = computeEastMoveVector(movable, movablePadding, fixed, fixedPadding);
        } else if (direction == WEST) {
            move = computeWestMoveVector(movable, movablePadding, fixed, fixedPadding);
        } else if (direction == NORTH_EAST) {
            move = computeMoveVector(movable, movablePadding, fixed, fixedPadding, NORTH).expand(computeMoveVector(movable, movablePadding, fixed, fixedPadding, EAST));
        } else if (direction == NORTH_WEST) {
            move = computeMoveVector(movable, movablePadding, fixed, fixedPadding, NORTH).expand(computeMoveVector(movable, movablePadding, fixed, fixedPadding, WEST));
        } else if (direction == SOUTH_EAST) {
            move = computeMoveVector(movable, movablePadding, fixed, fixedPadding, SOUTH).expand(computeMoveVector(movable, movablePadding, fixed, fixedPadding, EAST));
        } else if (direction == SOUTH_WEST) {
            move = computeMoveVector(movable, movablePadding, fixed, fixedPadding, SOUTH).expand(computeMoveVector(movable, movablePadding, fixed, fixedPadding, WEST));
        } else {
            move = null;
            assert false : "Unknown direction";
        }
        return move;
    }

    private Dimension computeWestMoveVector(final Rectangle movable, final Insets movablePadding, final Rectangle fixed, final Insets fixedPadding) {
        final Dimension move;
        if (movable.intersects(fixed)) {
            final int padding = Math.max(movablePadding.right, fixedPadding.left);
            move = new Dimension(-(padding + horizontalDistance(fixed.getLeft(), movable.getRight())), 0);
        } else {
            final int dx1 = horizontalDistance(fixed.getExpanded(fixedPadding).getLeft(), movable.getRight());
            final int dx2 = horizontalDistance(fixed.getLeft(), movable.getExpanded(movablePadding).getRight());
            move = new Dimension(-Math.max(dx1, dx2), 0);
        }
        return move;
    }

    private Dimension computeEastMoveVector(final Rectangle movable, final Insets movablePadding, final Rectangle fixed, final Insets fixedPadding) {
        final Dimension move;
        if (movable.intersects(fixed)) {
            final int padding = Math.max(movablePadding.left, fixedPadding.right);
            move = new Dimension(padding + horizontalDistance(fixed.getRight(), movable.getLeft()), 0);
        } else {
            final int dx1 = horizontalDistance(fixed.getExpanded(fixedPadding).getRight(), movable.getLeft());
            final int dx2 = horizontalDistance(fixed.getRight(), movable.getExpanded(movablePadding).getLeft());
            move = new Dimension(Math.max(dx1, dx2), 0);
        }
        return move;
    }

    private Dimension computeSouthMoveVector(final Rectangle movable, final Insets movablePadding, final Rectangle fixed, final Insets fixedPadding) {
        final Dimension move;
        if (movable.intersects(fixed)) {
            final int padding = Math.max(movablePadding.top, fixedPadding.bottom);
            move = new Dimension(0, padding + verticalDistance(fixed.getBottom(), movable.getTop()));
        } else {
            final int dy1 = verticalDistance(fixed.getExpanded(fixedPadding).getBottom(), movable.getTop());
            final int dy2 = verticalDistance(fixed.getBottom(), movable.getExpanded(movablePadding).getTop());
            move = new Dimension(0, Math.max(dy1, dy2));
        }
        return move;
    }

    private Dimension computeNorthMoveVector(final Rectangle movable, final Insets movablePadding, final Rectangle fixed, final Insets fixedPadding) {
        final Dimension move;
        if (movable.intersects(fixed)) {
            final int padding = Math.max(movablePadding.bottom, fixedPadding.top);
            move = new Dimension(0, -(padding + verticalDistance(fixed.getTop(), movable.getBottom())));
        } else {
            final int dy1 = verticalDistance(fixed.getExpanded(fixedPadding).getTop(), movable.getBottom());
            final int dy2 = verticalDistance(fixed.getTop(), movable.getExpanded(movablePadding).getBottom());
            move = new Dimension(0, -Math.max(dy1, dy2));
        }
        return move;
    }

    private int verticalDistance(final Point p1, final Point p2) {
        return Math.abs(p1.y - p2.y);
    }

    private int horizontalDistance(final Point p1, final Point p2) {
        return Math.abs(p1.x - p2.x);
    }

    private Map<Direction, SortedSet<IGraphicalEditPart>> groupByDirection(final IGraphicalEditPart origin, final Set<IGraphicalEditPart> parts) {
        final Map<Direction, SortedSet<IGraphicalEditPart>> result = Maps.newHashMap();
        for (IGraphicalEditPart part : parts) {
            final Direction dir = getDirection(origin, part);
            if (!result.containsKey(dir)) {
                result.put(dir, Sets.newTreeSet(positionComparator));
            }
            result.get(dir).add(part);
        }
        return result;
    }

    private Direction getDirection(final IGraphicalEditPart sourcePart, final IGraphicalEditPart destPart) {
        final Point source = getCurrentBounds(sourcePart, EXCLUDE_PADDING).getCenter();
        final Point dest = getCurrentBounds(destPart, EXCLUDE_PADDING).getCenter();
        final int dx = dest.x - source.x;
        final int dy = dest.y - source.y;
        final Direction result;
        if (dx < 0) {
            if (dy < 0) {
                result = NORTH_WEST;
            } else if (dy == 0) {
                result = WEST;
            } else {
                result = SOUTH_WEST;
            }
        } else if (dx > 0) {
            if (dy < 0) {
                result = NORTH_EAST;
            } else if (dy == 0) {
                result = EAST;
            } else {
                result = SOUTH_EAST;
            }
        } else {
            if (dy < 0) {
                result = NORTH;
            } else if (dy == 0) {
                // Default to SOUTH if the parts have the same center
                result = EAST;
            } else {
                result = SOUTH;
            }
        }
        return result;
    }

    /**
     * Returns all the parts which overlap with any of the specified parts. Does
     * not consider internal overlap between the specified elements themselves.
     */
    private Set<IGraphicalEditPart> findOverlappingParts(final Set<IGraphicalEditPart> parts) {
        final Set<IGraphicalEditPart> result = Sets.newHashSet();
        for (IGraphicalEditPart part : parts) {
            result.addAll(findOverlappingParts(part));
        }
        result.removeAll(parts);
        return result;
    }

    private Set<IGraphicalEditPart> findOverlappingParts(final IGraphicalEditPart part) {
        final Set<IGraphicalEditPart> result = Sets.newHashSet();
        for (IGraphicalEditPart candidate : allEditParts) {
            if (overlaps(candidate, part)) {
                result.add(candidate);
            }
        }
        return result;
    }

    private boolean overlaps(final IGraphicalEditPart part1, final IGraphicalEditPart part2) {
        if (part1 == part2) {
            return false;
        } else {
            return getCurrentBounds(part1, EXCLUDE_PADDING).intersects(getCurrentBounds(part2, INCLUDE_PADDING))
                    || getCurrentBounds(part1, INCLUDE_PADDING).intersects(getCurrentBounds(part2, EXCLUDE_PADDING));
        }
    }

    private Rectangle getBoundingBox(final Set<IGraphicalEditPart> parts, final boolean includePadding) {
        Rectangle box = null;
        for (IGraphicalEditPart part : parts) {
            if (box == null) {
                box = getCurrentBounds(part, includePadding);
            } else {
                box = box.getUnion(getCurrentBounds(part, includePadding));
            }
        }
        return box;
    }

    private Point getInitialPosition(final IGraphicalEditPart part) {
        return getInitialBounds(part).getTopLeft();
    }

    private Rectangle getInitialBounds(final IGraphicalEditPart part) {
        return initialBounds.get(part);
    }

    private Map<IGraphicalEditPart, Point> getSolution() {
        final Map<IGraphicalEditPart, Point> result = Maps.newHashMap();
        for (IGraphicalEditPart part : currentBounds.keySet()) {
            result.put(part, currentBounds.get(part).getTopLeft());
        }
        return result;
    }

    private Point getCurrentPosition(final IGraphicalEditPart part) {
        return getCurrentBounds(part, EXCLUDE_PADDING).getTopLeft();
    }

    private Rectangle getCurrentBounds(final IGraphicalEditPart part, final boolean includePadding) {
        final Rectangle bounds;
        if (currentBounds.containsKey(part)) {
            bounds = currentBounds.get(part);
        } else {
            bounds = getInitialBounds(part);
        }
        if (includePadding == INCLUDE_PADDING) {
            final Insets padding = layoutCustomization.getNodePadding(part);
            return bounds.getExpanded(padding);
        } else {
            return bounds;
        }
    }

    private void setCurrentPosition(final IGraphicalEditPart part, final Point position) {
        Preconditions.checkArgument(!isPinned.apply(part), "Pinned elements can not move");
        if (position.equals(getInitialPosition(part))) {
            currentBounds.remove(part);
        } else {
            final Rectangle oldBounds = getCurrentBounds(part, EXCLUDE_PADDING);
            final Rectangle newBounds = new Rectangle(position.x, position.y, oldBounds.width, oldBounds.height);
            currentBounds.put(part, newBounds);
        }
    }

    /**
     * Move all the specified parts to the new positions specified in the map.
     */
    private void moveParts(Set<IGraphicalEditPart> partToReset, final Map<IGraphicalEditPart, Point> positions) {
        for (IGraphicalEditPart editPart : partToReset) {
            // If the editPart has not been moved in the same direction, its
            // initial position can not be found.
            if (positions.get(editPart) != null) {
                setCurrentPosition(editPart, positions.get(editPart));
                positions.remove(editPart);
            }
        }
    }

    @SuppressWarnings("unused")
    private void printInitialState() {
        debugMessage("==============================================================================="); //$NON-NLS-1$
        debugMessage("Initial state (before #resolveOverlaps()"); //$NON-NLS-1$
        debugMessage("----------------------------------------"); //$NON-NLS-1$
        for (IGraphicalEditPart part : allEditParts) {
            debugMessage("- " + part.getClass().getSimpleName() + " (semantic: " + part.resolveSemanticElement() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            debugMessage("  Pinned: " + isPinned.apply(part)); //$NON-NLS-1$
            debugMessage("  Intrinsic bounds (main figure):              " + part.getFigure().getBounds()); //$NON-NLS-1$
            debugMessage("  Initial bounds (after previous layout pass): " + getInitialBounds(part)); //$NON-NLS-1$
        }
        debugMessage(""); //$NON-NLS-1$
    }

    @SuppressWarnings("unused")
    private void printResolvedState() {
        debugMessage("Solution (only moved elements)"); //$NON-NLS-1$
        debugMessage("------------------------------"); //$NON-NLS-1$
        for (IGraphicalEditPart part : currentBounds.keySet()) {
            debugMessage("- " + part.getClass().getSimpleName() + " (semantic: " + part.resolveSemanticElement() + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            debugMessage("  Pinned: " + isPinned.apply(part)); //$NON-NLS-1$
            debugMessage("  Intrinsic bounds (main figure):              " + part.getFigure().getBounds()); //$NON-NLS-1$
            debugMessage("  Initial bounds (after previous layout pass): " + getInitialBounds(part)); //$NON-NLS-1$
            debugMessage("  Computed bounds (after resolution):          " + getCurrentPosition(part)); //$NON-NLS-1$
        }
        debugMessage(""); //$NON-NLS-1$
    }

    private void debugMessage(final String msg) {
        if (DEBUG) {
            // CHECKSTYLE:OFF
            System.out.println("DEBUG: " + msg); //$NON-NLS-1$
            // CHECKSTYLE:ON
        }
    }
}
