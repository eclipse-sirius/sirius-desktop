/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

/**
 * A set of methods that are useful when manipulating figures on the real
 * coordinates system of the diagram (and not for the visible area like
 * translateToAbsolute or translateToRelative).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public final class FigureUtilities {
    /**
     * Constructor to prevent instantiation
     */
    private FigureUtilities() {
    }

    /**
     * Translate the relative coordinates to absolute coordinate by ignoring the
     * scroll bar. Indeed, the IFigure.translateToAbsolute() give the absolute
     * position from the visible area but not form the real origin of the
     * diagram. This method also ignores the zoom factor (the coordinates are
     * always in 100%).
     * 
     * @param figure
     *            The figure
     * @param relativeLocation
     *            location relative to this figure (in 100%, real values)
     */
    public static void translateToAbsoluteByIgnoringScrollbar(final IFigure figure, final Point relativeLocation) {
        PrecisionPoint pPoint = new PrecisionPoint(relativeLocation);

        figure.translateToAbsolute(pPoint);
        FigureUtilities.removeParentScrollbarAndZoomFactor(figure, pPoint);

        relativeLocation.setX(pPoint.x);
        relativeLocation.setY(pPoint.y);
    }

    /**
     * Translate the absoluteLocation coordinates to relative coordinate by
     * ignoring the scroll bar. Indeed, the IFigure.translateToRelative() give
     * the relative position from an absolute position of the visible area but
     * not from an absolute position of the real origin of the diagram. This
     * method also ignores the zoom factor (the coordinates are always in 100%).
     * 
     * @param figure
     *            The figure
     * @param absoluteLocation
     *            location in absolute coordinates
     */
    public static void translateToRelativeByIgnoringScrollbar(final IFigure figure, final Point absoluteLocation) {
        PrecisionPoint pPoint = new PrecisionPoint(absoluteLocation);

        FigureUtilities.addParentScrollbarAndZoomFactor(figure, pPoint);
        figure.translateToRelative(pPoint);

        absoluteLocation.setX(pPoint.x);
        absoluteLocation.setY(pPoint.y);
    }

    /**
     * @param figure
     *            the actual figure level
     * @param location
     *            a location in absolute coordinates
     */
    private static void addParentScrollbarAndZoomFactor(final IFigure figure, final PrecisionPoint location) {
        if (figure instanceof Viewport) {
            location.performTranslate(-((Viewport) figure).getHorizontalRangeModel().getValue(), -((Viewport) figure).getVerticalRangeModel().getValue());
        }
        if (figure instanceof ScalableFigure) {
            location.performScale(((ScalableFigure) figure).getScale());
        }
        if (figure.getParent() != null) {
            FigureUtilities.addParentScrollbarAndZoomFactor(figure.getParent(), location);
        }
    }

    /**
     * @param figure
     *            the actual figure level
     * @param location
     *            a location in absolute coordinates
     */
    private static void removeParentScrollbarAndZoomFactor(final IFigure figure, final PrecisionPoint location) {
        if (figure.getParent() != null) {
            FigureUtilities.removeParentScrollbarAndZoomFactor(figure.getParent(), location);
        }
        if (figure instanceof Viewport) {
            location.performTranslate(((Viewport) figure).getHorizontalRangeModel().getValue(), ((Viewport) figure).getVerticalRangeModel().getValue());
        }
        if (figure instanceof ScalableFigure) {
            location.performScale(1 / ((ScalableFigure) figure).getScale());
        }
    }

    /**
     * Same thing as
     * {@link Figure#translateToRelative(org.eclipse.draw2d.geometry.Translatable)}
     * , but it uses {@link PrecisionPoint} internally to perform more precise
     * translation.
     * 
     * @param figure
     *            Figure uses to perform translation.
     * @param point
     *            Point to translate.
     */
    public static void precisionTranslateToRelative(Figure figure, Point point) {
        PrecisionPoint pPoint = new PrecisionPoint(point);

        figure.translateToRelative(pPoint);

        point.setX(pPoint.x);
        point.setY(pPoint.y);
    }

    /**
     * Returns the {@link FreeformViewport} that owns this figure, if any.
     * 
     * @param figure
     *            the figure.
     * @return the {@link FreeformViewport} that owns this figure, or
     *         <code>null</code> if there is none.
     */
    public static FreeformViewport getFreeformViewport(IFigure figure) {
        IFigure current = figure;
        while (!(current instanceof FreeformViewport) && current != null) {
            current = current.getParent();
        }
        return (FreeformViewport) current;
    }

    /**
     * Returns the root {@link FreeformViewport} that owns this figure, if any.
     * We call root {@link FreeformViewport} the last one we found when going to
     * the top of the figure hierarchy.
     * 
     * @param figure
     *            the figure.
     * @return the {@link FreeformViewport} that owns this figure, or
     *         <code>null</code> if there is none.
     */
    public static FreeformViewport getRootFreeformViewport(IFigure figure) {
        IFigure current = figure;
        FreeformViewport rootFreeformViewport = null;
        while (current != null) {
            if (current instanceof FreeformViewport) {
                rootFreeformViewport = (FreeformViewport) current;
            }
            current = current.getParent();
        }
        return rootFreeformViewport;
    }
}
