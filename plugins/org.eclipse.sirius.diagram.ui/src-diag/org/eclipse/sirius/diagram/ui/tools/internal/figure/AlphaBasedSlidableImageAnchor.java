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
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.BorderItemContainerFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ImageFigureWithAlpha;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.util.OpaquePixelFinder;

import com.google.common.collect.Iterators;

/**
 * A slidable Anchor implementation which use the alpha value provided by some
 * image based figures to shift the edge anchors so that it gets close to the
 * image borders instead of the figure bounding box.
 * 
 * @author cbrun
 */
public class AlphaBasedSlidableImageAnchor extends SlidableAnchor {

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
        // Do not go through BorderItemContainerFigure. Indeed this figure
        // contains BorderItems and the anchor is not expected to be in border
        // items.
        if (!(root instanceof BorderItemContainerFigure)) {
            if (root instanceof ImageFigureWithAlpha) {
                ret = (ImageFigureWithAlpha) root;
            }
            final Iterator<IFigure> iterChilren = Iterators.filter(root.getChildren().iterator(), IFigure.class);
            while (iterChilren.hasNext() && ret == null) {
                ret = AlphaBasedSlidableImageAnchor.getImageFigure(iterChilren.next());
            }
        }
        return ret;
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
                return new OpaquePixelFinder().searchFirstOpaquePoint(orientation, anchorFromSuper, imageFigure, box);
            }
        }
        return anchorFromSuper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
