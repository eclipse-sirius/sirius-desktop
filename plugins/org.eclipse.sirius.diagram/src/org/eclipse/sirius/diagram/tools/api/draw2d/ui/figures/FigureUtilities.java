/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.api.draw2d.ui.figures;

import java.util.Iterator;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.sirius.diagram.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;

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

        relativeLocation.x = pPoint.x;
        relativeLocation.y = pPoint.y;
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

        absoluteLocation.x = pPoint.x;
        absoluteLocation.y = pPoint.y;
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

        point.x = pPoint.x;
        point.y = pPoint.y;
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
     * <p>
     * Returns a Point representing the margins associated to the given figure
     * and its children.
     * </p>
     * <p>
     * Used to correct issues occurring during Node Creation and DnD inside
     * Containers. The bordered node is not concerned by this shift.
     * </p>
     * 
     * @param fig
     *            the figure to consider
     * @param isConcernedBorderedNode
     *            true if the shift concerned bordered node, false otherwise.
     * @param editPart
     *            the current edit part
     * @return a Point representing the margins associated to the given figure
     *         and its children
     */
    public static Point getShiftFromMarginOffset(ResizableCompartmentFigure fig, boolean isConcernedBorderedNode, EditPart editPart) {
        // ignore shift for creation of bordered node.
        if (isConcernedBorderedNode) {
            return new Point(0, 0);
        }
        int leftShift = 0;
        int topShif = 0;
        // DnD and Node Creation in a container add extra x
        // and y values of 5 pixels
        // If the target EditPart is an
        // AbstractDNodeContainerCompartmentEditPart, we consider the shift
        // Margins associated to the figure linked with this editPart
        if (editPart instanceof AbstractDNodeContainerCompartmentEditPart) {

            Iterator<?> childrenFiguresIterator = fig.getChildren().iterator();
            while (childrenFiguresIterator.hasNext()) {
                Object next = childrenFiguresIterator.next();

                if (next instanceof IFigure) {
                    IFigure childrenFigure = (IFigure) next;
                    Border border = childrenFigure.getBorder();
                    if (border != null) {
                        Insets insets = border.getInsets(childrenFigure);
                        if (insets != null) {
                            leftShift += insets.left;
                            topShif += insets.top;
                        }
                    }
                }
            }
        }
        return new Point(leftShift, topShif);
    }
}
