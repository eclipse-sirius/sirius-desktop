/*******************************************************************************
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ImageFigureWithAlpha;

import com.google.common.collect.Iterators;

/**
 * A slidable Anchor implementation which use the alpha value provided by some
 * image based figures to shift the edge anchors so that it gets close to the
 * image borders instead of the figure bounding box.
 * 
 * @author cbrun
 */
public class AlphaBasedSlidableImageAnchor extends SlidableAnchor {

    private int isTransparentMaxValue = 15;

    /**
     * Create a new {@link AlphaBasedSlidableImageAnchor}.
     */
    public AlphaBasedSlidableImageAnchor() {
        super();
    }

    /**
     * Create a new {@link AlphaBasedSlidableImageAnchor}.
     * 
     * @param f
     *            the container figure.
     */
    public AlphaBasedSlidableImageAnchor(final IFigure f) {
        super(f);
    }

    /**
     * Constructor. Takes point p to store the reference point
     * 
     * @param f
     *            <code>IFigure</code> that this anchor is associated with.
     * @param p
     *            the <code>PrecisionPoint</code> that the anchor will initially
     *            attach to.
     */
    public AlphaBasedSlidableImageAnchor(IFigure f, PrecisionPoint p) {
        super(f, p);
    }

    /**
     * Returns the first {@link ImageFigureWithAlpha} found which is a children
     * in the current box. null if not found.
     * 
     * @return the image figure.
     */
    private ImageFigureWithAlpha getImageFigure() {
        ImageFigureWithAlpha ret = null;
        if (ret == null) {
            final IFigure root = this.getOwner().getParent() == null ? this.getOwner() : this.getOwner().getParent();
            ret = AlphaBasedSlidableImageAnchor.getImageFigure(root);
        }
        return ret;
    }

    /**
     * Returns the first {@link ImageFigureWithAlpha} found in the children,
     * null if not found.
     * 
     * @param root
     *            the root figure.
     * @return the first {@link ImageFigureWithAlpha}.
     */
    private static ImageFigureWithAlpha getImageFigure(final IFigure root) {
        ImageFigureWithAlpha ret = null;
        if (root instanceof ImageFigureWithAlpha) {
            ret = (ImageFigureWithAlpha) root;
        }
        final Iterator<IFigure> iterChilren = Iterators.filter(root.getChildren().iterator(), IFigure.class);
        while (iterChilren.hasNext() && ret == null) {
            ret = AlphaBasedSlidableImageAnchor.getImageFigure(iterChilren.next());
        }
        return ret;
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
        return transValue < isTransparentMaxValue;
    }

    /**
     * Returns bounds of the figure.
     * 
     * @return the owner figure
     */
    protected IFigure getContainer() {
        return getOwner();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor#getLocation(org.eclipse.draw2d.geometry.Point,
     *      org.eclipse.draw2d.geometry.Point)
     */
    protected Point getLocation(final Point ownReference, final Point foreignReference) {
        return getLocation(ownReference, foreignReference, PositionConstants.NONE);
    }

    /**
     * Calculates the location of the anchor depending on the anchors own
     * reference and foreign reference points.
     * 
     * @param ownReference
     *            - the own reference of the anchor
     * @param foreignReference
     *            - foreign reference that comes in
     * @param orientation
     *            - an orientation (a {@link PositionConstants} the method will
     *            consider to start its search of an anchor position.
     * @return the location of the anchor depending on the anchors own reference
     *         and foreign reference points
     */
    protected Point getLocation(final Point ownReference, final Point foreignReference, int orientation) {
        final Point anchorFromSuper = super.getLocation(ownReference, foreignReference);
        final ImageFigureWithAlpha imageFigure = getImageFigure();
        // For image with border, we consider the original location.
        if (!(imageFigure instanceof IFigure && ((IFigure) imageFigure).getBorder() != null)) {
            if (imageFigure != null && anchorFromSuper != null) {
                Rectangle box = getBox();
                if (box != null && box.width > 0 && box.height > 0) {
                    /*
                     * This algorithm adapt the anchor position returned by
                     * super to shift it on the x or y axis up to the point
                     * where a non-transparent pixel is found.
                     */
                    /*
                     * We first decide from which side (TOP, LEFT, RIGHT or
                     * BOTTOM) we will start the search, then maintain the
                     * offsetX and offsetY up to the point where we find a non
                     * transparent pixel.
                     */
                    /*
                     * if the orientation has been decided by the caller, then
                     * we should stick to its choice to return a position which
                     * maps its expectation. This is especially relevant in the
                     * case of orthogonal routing.
                     */
                    int sideToStartSearchFrom = computeSideToStartFrom(orientation, anchorFromSuper, box);

                    /*
                     * the x offset in the box coordinate system where we are
                     * looking at currently.
                     */
                    Point searchCursor = new Point(0, 0);

                    /*
                     * The primaryScanStep is used during the search to
                     * translate the current cursor horizontally, or vertically,
                     * according to the sideToStartSearchFrom
                     */
                    Point primaryScanStep = new Point(0, 0);
                    /*
                     * The secondaryScanStep is used during the search to
                     * translate the current cursor horizontally, or vertically
                     * (the opposite axis of primaryScanStep) according to the
                     * sideToStartSearchFrom
                     */
                    Point secondaryScanStep = new Point(0, 0);

                    switch (sideToStartSearchFrom) {
                    case PositionConstants.LEFT:
                        /*
                         * the anchor starts at the left of the figure
                         */
                        primaryScanStep.x = 1;
                        secondaryScanStep.y = 1;
                        searchCursor.y = anchorFromSuper.y - box.getTopLeft().y;
                        break;
                    case PositionConstants.RIGHT:
                        /*
                         * the anchor starts at the right of the figure
                         */
                        primaryScanStep.x = -1;
                        secondaryScanStep.y = 1;
                        searchCursor.setLocation(box.width, anchorFromSuper.y - box.getTopLeft().y);
                        break;
                    case PositionConstants.TOP:
                        primaryScanStep.y = 1;
                        secondaryScanStep.x = 1;
                        /*
                         * the anchor starts at the top of the figure
                         */
                        searchCursor.x = anchorFromSuper.x - box.getTopLeft().x;
                        break;
                    case PositionConstants.BOTTOM:
                        /*
                         * the anchor starts at the bottom of the figure
                         */
                        primaryScanStep.y = -1;
                        secondaryScanStep.x = 1;
                        searchCursor.setLocation(anchorFromSuper.x - box.getTopLeft().x, box.height);
                        break;
                    default:
                        break;
                    }

                    if (primaryScanStep.x != 0 || primaryScanStep.y != 0) {
                        searchCursor = searchFirstOpaquePoint(searchCursor, sideToStartSearchFrom, primaryScanStep, secondaryScanStep, imageFigure, box);
                    }
                    return box.getTopLeft().getCopy().translate(searchCursor.x, searchCursor.y);
                }
            }
        }
        return anchorFromSuper;
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
     * Search the first opaque point nearest from the initial cursor.
     * 
     * @param initialCursor
     *            The initial cursor location
     * @param sideToStartSearchFrom
     *            The side of the parent from which to start the search
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
    private Point searchFirstOpaquePoint(final Point initialCursor, int startSearchFromSide, Point primaryScanStep, Point secondaryScanStep, final ImageFigureWithAlpha imageFigure, Rectangle box) {
        boolean foundAnOpaquePixel = false;
        int nbIteration = 0;
        Point searchCursor = new Point(initialCursor);
        while (!foundAnOpaquePixel && (nbIteration == 0 || (!isOut(box, secondaryScanStep.getScaled(nbIteration)) || !isOut(box, secondaryScanStep.getNegated().getScaled(nbIteration))))) {
            // Search in first direction
            searchCursor.translate(secondaryScanStep.getScaled(nbIteration));
            while (!isOut(box, searchCursor) && isTransparentAt(imageFigure, searchCursor.x, searchCursor.y, box)) {
                searchCursor.translate(primaryScanStep);
            }
            if (isOut(box, searchCursor)) {
                // Search in opposite direction
                searchCursor.setLocation(initialCursor);
                searchCursor.translate(secondaryScanStep.getNegated().getScaled(nbIteration));
                while (!isOut(box, searchCursor) && isTransparentAt(imageFigure, searchCursor.x, searchCursor.y, box)) {
                    searchCursor.translate(primaryScanStep);
                }
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

    /**
     * {@inheritDoc}
     */
    public Point getOrthogonalLocation(Point orthoReference) {
        /*
         * copy pasted from super, only to customize the call to getLocation to
         * pass the orientation which has been chosen in the case of an
         * orthogonal location.
         */
        PrecisionPoint ownReference = new PrecisionPoint(getReferencePoint());
        PrecisionRectangle bounds = new PrecisionRectangle(FigureUtilities.getAnchorableFigureBounds(getOwner()));
        getOwner().translateToAbsolute(bounds);
        bounds.expand(0.000001, 0.000001);
        PrecisionPoint preciseOrthoReference = new PrecisionPoint(orthoReference);
        int orientation = PositionConstants.NONE;
        if (bounds.contains(preciseOrthoReference)) {
            int side = getClosestSide(ownReference, bounds);
            switch (side) {
            case PositionConstants.LEFT:
            case PositionConstants.RIGHT:
                ownReference.setPreciseY(preciseOrthoReference.preciseY());
                orientation = PositionConstants.HORIZONTAL;
                break;
            case PositionConstants.TOP:
            case PositionConstants.BOTTOM:
                ownReference.setPreciseX(preciseOrthoReference.preciseX());
                orientation = PositionConstants.VERTICAL;
                break;
            default:
                break;
            }
        } else if (preciseOrthoReference.preciseX() >= bounds.preciseX() && preciseOrthoReference.preciseX() <= bounds.preciseX() + bounds.preciseWidth()) {
            ownReference.setPreciseX(preciseOrthoReference.preciseX());

            orientation = PositionConstants.VERTICAL;
        } else if (preciseOrthoReference.preciseY() >= bounds.preciseY() && preciseOrthoReference.preciseY() <= bounds.preciseY() + bounds.preciseHeight()) {
            ownReference.setPreciseY(preciseOrthoReference.preciseY());
            orientation = PositionConstants.HORIZONTAL;
        }
        /*
         * actual change regarding the "super" code, we pass the orientation to
         * the dedicated getLocation method as this information might be used to
         * "force" the algorithm to pick an horizontal or vertical search in the
         * image buffer.
         */
        Point location = getLocation(ownReference, preciseOrthoReference, orientation);
        if (location == null) {
            location = getLocation(orthoReference);
            orientation = PositionConstants.NONE;
        }

        if (orientation != PositionConstants.NONE) {
            PrecisionPoint loc = new PrecisionPoint(location);
            if (orientation == PositionConstants.VERTICAL) {
                loc.setPreciseX(preciseOrthoReference.preciseX());
            } else {
                loc.setPreciseY(preciseOrthoReference.preciseY());
            }
            location = loc;
        }

        return location;
    }

    /**
     * Returns the position of the closest edge of the rectangle closest to the
     * point
     * 
     * @param p
     *            the point
     * @param r
     *            the rectangle
     * @return position of the closest edge
     */
    private static int getClosestSide(Point p, Rectangle r) {
        /*
         * copy pasted "as is" from super because getOrthogonalLocation needs
         * it. It should be identical.
         */
        double diff = Math.abs(r.preciseX() + r.preciseWidth() - p.preciseX());
        int side = PositionConstants.RIGHT;
        double currentDiff = Math.abs(r.preciseX() - p.preciseX());
        if (currentDiff < diff) {
            diff = currentDiff;
            side = PositionConstants.LEFT;
        }
        currentDiff = Math.abs(r.preciseY() + r.preciseHeight() - p.preciseY());
        if (currentDiff < diff) {
            diff = currentDiff;
            side = PositionConstants.BOTTOM;
        }
        currentDiff = Math.abs(r.preciseY() - p.preciseY());
        if (currentDiff < diff) {
            diff = currentDiff;
            side = PositionConstants.TOP;
        }
        return side;
    }
}
