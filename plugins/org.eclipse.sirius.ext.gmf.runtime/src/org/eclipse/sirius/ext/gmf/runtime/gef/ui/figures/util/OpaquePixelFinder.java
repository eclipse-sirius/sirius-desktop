/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo
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
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.sirius.ext.draw2d.figure.ImageFigureWithAlpha;

/**
 * Class that contains utility method to find an opaque pixel on a
 * {@link ImageFigureWithAlpha}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class OpaquePixelFinder {

    private int alphaLimit = 15;

    /**
     * Default constructor.
     */
    public OpaquePixelFinder() {
    }

    /**
     * Constructor that allows to define the alpha limit.
     * 
     * @param alphaLimit
     *            The alpha value until a pixel is consider has transparent
     */
    public OpaquePixelFinder(int alphaLimit) {
        setAlphaLimit(alphaLimit);
    }

    /**
     * Set the alpha value until a pixel is consider has transparent (between 0
     * and this value).
     * 
     * @param alphaLimit
     *            The limit to use
     */
    public void setAlphaLimit(int alphaLimit) {
        this.alphaLimit = alphaLimit;
    }

    /**
     * Search the first opaque point nearest from the initial cursor.
     * 
     * @param orientation
     *            an orientation ({@link PositionConstants#HORIZONTAL} or
     *            {@link PositionConstants#VERTICAL}) the method will consider
     *            to start its search of an anchor position.
     * @param initialCursorLocation
     *            The initial cursor location to consider
     * @param imageFigure
     *            The image in which to search an opaque point
     * @param box
     *            The bounding box of the figure in absolute coordinates.
     * @return The first opaque point nearest from the initial cursor or the
     *         <code>initialCursorLocation</code> itself if any.
     */
    public Point searchFirstOpaquePoint(int orientation, final Point initialCursorLocation, final ImageFigureWithAlpha imageFigure, Rectangle box) {
        if (box != null && box.width > 0 && box.height > 0) {
            /*
             * This algorithm adapt the anchor position returned by super to
             * shift it on the x or y axis up to the point where a
             * non-transparent pixel is found.
             */
            /*
             * We first decide from which side (TOP, LEFT, RIGHT or BOTTOM) we
             * will start the search, then maintain the offsetX and offsetY up
             * to the point where we find a non transparent pixel.
             */
            /*
             * if the orientation has been decided by the caller, then we should
             * stick to its choice to return a position which maps its
             * expectation. This is especially relevant in the case of
             * orthogonal routing.
             */
            int sideToStartSearchFrom = computeSideToStartFrom(orientation, initialCursorLocation, box);

            /*
             * the x offset in the box coordinate system where we are looking at
             * currently.
             */
            Point searchCursor = new Point(0, 0);

            /*
             * The primaryScanStep is used during the search to translate the
             * current cursor horizontally, or vertically, according to the
             * sideToStartSearchFrom
             */
            Point primaryScanStep = new Point(0, 0);
            /*
             * The secondaryScanStep is used during the search to translate the
             * current cursor horizontally, or vertically (the opposite axis of
             * primaryScanStep) according to the sideToStartSearchFrom
             */
            Point secondaryScanStep = new Point(0, 0);

            switch (sideToStartSearchFrom) {
            case PositionConstants.LEFT:
                /*
                 * the anchor starts at the left of the figure
                 */
                primaryScanStep.setX(1);
                secondaryScanStep.setY(1);
                searchCursor.setY(initialCursorLocation.y - box.getTopLeft().y);
                break;
            case PositionConstants.RIGHT:
                /*
                 * the anchor starts at the right of the figure
                 */
                primaryScanStep.setX(-1);
                secondaryScanStep.setY(1);
                searchCursor.setLocation(box.width - 1, initialCursorLocation.y - box.getTopLeft().y);
                break;
            case PositionConstants.TOP:
                primaryScanStep.setY(1);
                secondaryScanStep.setX(1);
                /*
                 * the anchor starts at the top of the figure
                 */
                searchCursor.setX(initialCursorLocation.x - box.getTopLeft().x);
                break;
            case PositionConstants.BOTTOM:
                /*
                 * the anchor starts at the bottom of the figure
                 */
                primaryScanStep.setY(-1);
                secondaryScanStep.setX(1);
                searchCursor.setLocation(initialCursorLocation.x - box.getTopLeft().x, box.height - 1);
                break;
            default:
                break;
            }

            if (primaryScanStep.x != 0 || primaryScanStep.y != 0) {
                searchCursor = searchFirstOpaquePoint(searchCursor, sideToStartSearchFrom, primaryScanStep, secondaryScanStep, imageFigure, box);
            }
            return box.getTopLeft().getCopy().translate(searchCursor.x, searchCursor.y);
        }
        return initialCursorLocation;
    }

    /**
     * Search the first opaque point nearest from the initial cursor.
     * 
     * @param initialCursor
     *            The initial cursor location
     * @param startSearchFromSide
     *            The side of the parent from which to start the search (one of
     *            {@link org.eclipse.draw2d.PositionConstants#LEFT},
     *            {@link org.eclipse.draw2d.PositionConstants#RIGHT},
     *            {@link org.eclipse.draw2d.PositionConstants#TOP} or
     *            {@link org.eclipse.draw2d.PositionConstants#BOTTOM}).
     * @param primaryScanStep
     *            It is used during the search to translate the current cursor
     *            horizontally, or vertically, according to the
     *            <code>sideToStartSearchFrom</code>
     * @param secondaryScanStep
     *            It is used to pass to the next line, or next column, according
     *            to the startSearchFromSide. This
     *            <code>secondaryScanStep</code> is used alternately as is, or
     *            as opposite to search the nearest opaque point in both
     *            direction.
     * @param imageFigure
     *            The image in which to search an opaque point
     * @param box
     *            The bounding box of the figure in absolute coordinates.
     * @return The first opaque point nearest from the initial cursor if found,
     *         or <code>initialCursor</code> otherwise.
     */
    public Point searchFirstOpaquePoint(final Point initialCursor, int startSearchFromSide, Point primaryScanStep, Point secondaryScanStep, final ImageFigureWithAlpha imageFigure, Rectangle box) {
        boolean foundAnOpaquePixel = false;
        int nbIteration = 0;
        Point searchCursor = new Point(initialCursor);
        while (!foundAnOpaquePixel && (nbIteration == 0 || (!isOut(box, secondaryScanStep.getScaled(nbIteration)) || !isOut(box, secondaryScanStep.getNegated().getScaled(nbIteration))))) {
            // Search in first direction
            searchCursor.translate(secondaryScanStep.getScaled(nbIteration));
            searchCursor = searchFirstOpaquePointOnPrimaryAxis(searchCursor, primaryScanStep, imageFigure, box);
            if (isOut(box, searchCursor)) {
                // Search in opposite direction
                searchCursor.setLocation(initialCursor);
                searchCursor.translate(secondaryScanStep.getNegated().getScaled(nbIteration));
                searchCursor = searchFirstOpaquePointOnPrimaryAxis(searchCursor, primaryScanStep, imageFigure, box);
            }
            if (isOut(box, searchCursor)) {
                nbIteration++;
                searchCursor.setLocation(initialCursor);
            } else {
                foundAnOpaquePixel = true;
            }
        }
        return searchCursor;
    }

    /**
     * Search the first opaque point nearest from the initial cursor (
     * <code>searchCursor</code> on the primary axis.
     * 
     * @param searchCursor
     *            The initial cursor location
     * @param primaryScanStep
     *            It is used during the search to translate the current cursor
     *            horizontally, or vertically, according to the primary axis
     * @param imageFigure
     *            The image in which to search an opaque point
     * @param box
     *            The bounding box of the figure in absolute coordinates.
     * @return the search cursor location at the end (for convenience)
     * 
     */
    private Point searchFirstOpaquePointOnPrimaryAxis(Point searchCursor, Point primaryScanStep, final ImageFigureWithAlpha imageFigure, Rectangle box) {
        while (!isOut(box, searchCursor) && isTransparentAt(imageFigure, searchCursor.x, searchCursor.y, box)) {
            searchCursor.translate(primaryScanStep);
        }
        return searchCursor;
    }

    /**
     * The alpha value until a pixel is consider has transparent (between 0 and
     * this value). The default value, used if not set, is 15.
     * 
     * @return The alpha limit.
     */
    protected int getAlphaLimit() {
        return alphaLimit;
    }

    /**
     * Check if the point coordinates considered relative to the box origin are
     * within the box bounds.
     * 
     * @param box
     *            a rectangle.
     * @param point
     *            any point, in relative coordinate with the box origin as
     *            reference.
     * @return true if the relative coordinates represented by the point are out
     *         of the box coordinates.
     */
    private boolean isOut(Rectangle box, Point point) {
        return box.width - Math.abs(point.x) < 0 || box.height - Math.abs(point.y) < 0;
    }

    private int computeSideToStartFrom(int orientation, final Point anchorForBoundingBox, Rectangle box) {
        int startSearchFromSide = PositionConstants.TOP;
        Point boxCenter = box.getCenter();
        /*
         * if the line orientation has been specified upstream, we match this
         * choice and pick the side which is closest while keeping the
         * horizontal or vertical orientation.
         */
        if (orientation == PositionConstants.VERTICAL) {
            if (anchorForBoundingBox.y >= boxCenter.y) {
                startSearchFromSide = PositionConstants.BOTTOM;
            } else {
                startSearchFromSide = PositionConstants.TOP;
            }
        } else if (orientation == PositionConstants.HORIZONTAL) {
            if (anchorForBoundingBox.x >= boxCenter.x) {
                startSearchFromSide = PositionConstants.RIGHT;
            } else {
                startSearchFromSide = PositionConstants.LEFT;
            }
        } else {
            /*
             * No orientation has been specified. We detect the side of the box
             * the anchor is at by intersecting a line going from anchor to the
             * box center and each line at the side of the box. From this
             * information we decide from which side we should start the search
             * of non-transparent pixel.
             */
            final LineSeg anchorToCenter = new LineSeg(anchorForBoundingBox, boxCenter);
            if (anchorToCenter.intersect(new LineSeg(box.getTopLeft(), box.getTopRight()), 3) != null) {
                startSearchFromSide = PositionConstants.TOP;
            } else if (anchorToCenter.intersect(new LineSeg(box.getTopLeft(), box.getBottomLeft()), 3) != null) {
                startSearchFromSide = PositionConstants.LEFT;
            } else if (anchorToCenter.intersect(new LineSeg(box.getBottomLeft(), box.getBottomRight()), 3) != null) {
                startSearchFromSide = PositionConstants.BOTTOM;
            } else {
                startSearchFromSide = PositionConstants.RIGHT;
            }
        }
        return startSearchFromSide;
    }

    /**
     * isTransparentAt Accessor to determine if the image is transparent at a
     * given point.
     * 
     * @param fig
     *            the image figure.
     * @param xInBox
     *            int location into the image
     * @param yInBox
     *            int location into the image
     * @param box
     *            a rectangle wrapping the figure which is actually displayed.
     * @return boolean true if transparent, false otherwise.
     */
    private boolean isTransparentAt(ImageFigureWithAlpha fig, final int xInBox, final int yInBox, Rectangle box) {

        int imageWidth = fig.getImageWidth();
        int imageHeight = fig.getImageHeight();

        /*
         * scaling x and y position to get the corresponding position in the
         * image buffer.
         */
        int x = (xInBox * imageWidth) / box.width;
        int y = (yInBox * imageHeight) / box.height;

        /*
         * boundary checking
         */
        if (x < 0 || x >= imageWidth || y < 0 || y >= imageHeight) {
            return true;
        }

        int transValue = fig.getImageAlphaValue(x, y) == 0 ? 0 : 255;

        // use a tolerance
        return transValue <= getAlphaLimit();
    }
}
