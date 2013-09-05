/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure.anchor;

import java.util.Iterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

/**
 * The {@link org.eclipse.gmf.runtime.gef.ui.figures.SlidableImageAnchor} for
 * air. This anchor takes the client area into account.
 * 
 * @author ymortier
 */
public class AirSlidableImageAnchor extends SlidableAnchor {

    private ImageAnchorLocation imageAnchorLocation;

    private ImageFigure imageFig;

    /**
     * Create a new {@link AirSlidableImageAnchor}.
     */
    public AirSlidableImageAnchor() {
        super();
    }

    /**
     * Create a new {@link AirSlidableImageAnchor}.
     * 
     * @param f
     *            the container figure.
     * @param imageFig
     *            the image figure.
     * @param p
     *            the position.
     */
    public AirSlidableImageAnchor(final IFigure f, final ImageFigure imageFig, final PrecisionPoint p) {
        super(f, p);
        this.imageFig = imageFig;
    }

    /**
     * Create a new {@link AirSlidableImageAnchor}.
     * 
     * @param container
     *            the container figure.
     * @param imageFig
     *            the image figure.
     */
    public AirSlidableImageAnchor(final IFigure container, final ImageFigure imageFig) {
        super(container);
        this.imageFig = imageFig;
    }

    /**
     * Create a new {@link AirSlidableImageAnchor}.
     * 
     * @param f
     *            the container figure.
     */
    public AirSlidableImageAnchor(final IFigure f) {
        super(f);
    }

    /**
     * Return the image.
     * 
     * @return the image.
     * @see org.eclipse.gmf.runtime.gef.ui.figures.SlidableImageAnchor#getImage()
     */
    protected Image getImage() {
        final ImageFigure imageFigure = getImageFigure();
        if (imageFigure != null) {
            return imageFigure.getImage();
        }
        return null;
    }

    /**
     * Return the image figure.
     * 
     * @return the image figure.
     */
    protected ImageFigure getImageFigure() {
        ImageFigure ret = imageFig;
        if (ret == null) {
            final IFigure root = this.getOwner().getParent() == null ? this.getOwner() : this.getOwner().getParent();
            ret = AirSlidableImageAnchor.getImageFigure(root);
        }
        return ret;
    }

    /**
     * Returns the first {@link ImageFigure}.
     * 
     * @param root
     *            the root figure.
     * @return the first {@link ImageFigure}.
     */
    private static ImageFigure getImageFigure(final IFigure root) {
        ImageFigure ret = null;
        if (root instanceof ImageFigure) {
            ret = (ImageFigure) root;
        }
        final Iterator iterChilren = root.getChildren().iterator();
        while (iterChilren.hasNext() && ret == null) {
            final IFigure next = (IFigure) iterChilren.next();
            ret = AirSlidableImageAnchor.getImageFigure(next);
        }
        return ret;
    }

    private final class ImageAnchorLocation {

        private ImageData imgData;

        private ImageData transMaskData;

        private ImageAnchorLocation(final Image img) {
            imgData = img.getImageData();
            transMaskData = imgData.getTransparencyMask();
        }

        /**
         * @return Returns the imgData.
         */
        protected ImageData getImageData() {
            return imgData;
        }

        /**
         * @return Returns the transMaskData.
         */
        protected ImageData getTransparencyMaskData() {
            return transMaskData;
        }

        /**
         * isTransparentAt Accessor to determine if the image is transparent at
         * a given point.
         * 
         * @param x
         *            int location into the image
         * @param y
         *            int location into the image
         * @param checkAdjacent
         *            check adjacent pixels for transparency as well.
         * @return boolean true if transparent, false otherwise.
         */
        protected boolean isTransparentAt(final int x, final int y, final boolean checkAdjacent) {
            // boundary checking
            if (x < 0 || x >= getImageData().width || y < 0 || y >= getImageData().height) {
                return true;
            }

            // check for alpha channel
            int transValue = 255;
            // check for transparency mask
            if (getTransparencyMaskData() != null) {
                transValue = getTransparencyMaskData().getPixel(x, y) == 0 ? 0 : 255;
            }

            if (transValue != 0) {
                if (getImageData().alphaData != null) {
                    transValue = getImageData().getAlpha(x, y);
                }
            }

            // use a tolerance
            boolean trans = false;
            if (transValue < 10) {
                trans = true;

                if (checkAdjacent) {
                    trans = trans && isTransparentAt(x + 1, y, false);
                    trans = trans && isTransparentAt(x + 1, y + 1, false);
                    trans = trans && isTransparentAt(x + 1, y - 1, false);
                    trans = trans && isTransparentAt(x - 1, y + 1, false);
                    trans = trans && isTransparentAt(x - 1, y, false);
                    trans = trans && isTransparentAt(x - 1, y - 1, false);
                    trans = trans && isTransparentAt(x, y + 1, false);
                    trans = trans && isTransparentAt(x, y - 1, false);
                }
            }

            return trans;
        }

        /**
         * getLocation Delegation function used by the ConnectionAnchor
         * getLocation
         * 
         * @param start
         *            the <code>Point</code> that is the beginning of a line
         *            segment used to calculate the anchor location inside the
         *            image.
         * @param edge
         *            the <code>Point</code> that is the end of a line segment
         *            used to calculate the anchor location inside the image.
         * @param isDefaultAnchor
         *            - true if location for the default anchor should be
         *            calculated
         * @return Point representing the location inside the image to anchor
         *         to.
         */
        private Point getLocation(final Point start, final Point edge, final Rectangle containerRect, final boolean isDefaultAnchor) {

            final Point top = containerRect.getTopLeft();
            getOwner().getParent().translateToRelative(top);

            Point ptIntersect = null;
            final Rectangle imageBounds = getOwner().getBounds();
            final Dimension dim = edge.getDifference(top);
            final Point edgeImg = new Point(Math.max(0, Math.min(dim.width, imageBounds.width - 1)), Math.max(0, Math.min(dim.height, imageBounds.height - 1)));
            final Dimension startDim = start.getDifference(top);
            final Point startImg = new Point(Math.max(0, Math.min(startDim.width, imageBounds.width - 1)), Math.max(0, Math.min(startDim.height, imageBounds.height - 1)));
            ptIntersect = calculateIntersection(startImg, edgeImg);
            if (ptIntersect == null) {
                return null;
            }
            return ptIntersect.getTranslated(top.x, top.y);
        }

        /**
         * calculateIntersection Utility method to calculate the intersection
         * point of a given point at an angle into the image to find the first
         * opaque pixel.
         * 
         * @param start
         *            Point that is in the center of the Image.
         * @param edge
         *            Point that is on the edge of the Image.
         * @return Point that is the intersection with the first opaque pixel.
         */
        private Point calculateIntersection(final Point start, final Point edge) {
            final Point opaque = new Point(edge);

            final LineSeg line = new LineSeg(start, edge);
            long distance = Math.round(line.length());
            final Rectangle imageBounds = getOwner().getBounds();
            // otherwise calculate value
            while (opaque.x >= 0 && opaque.x < imageBounds.width && opaque.y >= 0 && opaque.y < imageBounds.height) {
                // convert x and y to client area.
                final int imageWidth = getImageData().width;
                final int imageHeight = getImageData().height;
                final int x = (int) (opaque.x * (double) imageWidth / imageBounds.width);
                final int y = (int) (opaque.y * (double) imageHeight / imageBounds.height);
                if (!isTransparentAt(x, y, true)) {
                    return opaque;
                }

                line.pointOn(distance, LineSeg.KeyPoint.ORIGIN, opaque);
                distance--;
            }

            // default is to fall through and return the chopbox point
            return null;
        }
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
    @Override
    protected Point getLocation(final Point ownReference, final Point foreignReference) {
        final Image image = getImage();
        if (image == null) {
            return super.getLocation(ownReference, foreignReference);
        }
        Rectangle bounds = getOwner().getBounds();
        final ImageFigure imageFigure = getImageFigure();
        if (imageFigure != null) {
            bounds = imageFigure.getBounds();
        }
        final Rectangle ownerRect = new Rectangle(bounds);
        getOwner().translateToAbsolute(ownerRect);
        final PointList intersections = getIntersectionPoints(ownReference, foreignReference);
        Point loc = null;
        if (intersections != null && intersections.size() != 0) {
            final Point ptRef = PointListUtilities.pickFarestPoint(intersections, foreignReference);
            final Point ptEdge = PointListUtilities.pickClosestPoint(intersections, foreignReference);
            final ImageAnchorLocation imgAnchorLocation = getImageAnchorLocation();
            loc = imgAnchorLocation.getLocation(ptRef, ptEdge, ownerRect, isDefaultAnchor());
            if (loc != null) {
                loc = normalizeToStraightlineTolerance(foreignReference, loc, 3);
            }
        }
        return loc;
    }

    private ImageAnchorLocation getImageAnchorLocation() {
        if (this.imageAnchorLocation == null) {
            final Image image = getImage();
            this.imageAnchorLocation = new ImageAnchorLocation(image);
        }
        return imageAnchorLocation;
    }
}
