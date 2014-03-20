/*******************************************************************************
 * Copyright (c) 2010, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.figures;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.ext.draw2d.figure.FigureUtilities;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * Override GMF LayoutHelper to use the center of the visible part of the
 * container (and not just the center of the part of the container) for
 * reference position.
 * 
 * @author lredor
 */
public class SiriusLayoutHelper extends org.eclipse.gmf.runtime.diagram.ui.figures.LayoutHelper {

    /**
     * Default constructor.
     * 
     */
    public SiriusLayoutHelper() {
        super();
    }

    /**
     * Override to use the center of the visible part of the container and not
     * just the center of the part of the container.
     * 
     * @param parent
     *            the containing figure (typically <tt>layout()</tt>'s input
     *            parameter)
     * @param viewport
     *            The {@link Viewport} of the current diagram
     * @param part
     *            a part from the diagram.
     * @return the nearest free point of the center of the visible part of the
     *         container (in logical coordinates, not in screen coordinates).
     */
    public Point getReferencePosition(IFigure parent, Viewport viewport, IGraphicalEditPart part) {
        Point result;
        // Get the visible area relative to the logical origin (always in 100%)
        Rectangle visibleAreainLogicalRef = viewport.getBounds().getCopy();
        GraphicalHelper.screen2logical(visibleAreainLogicalRef, part);

        // Get the parent bounds relative to the logical origin (always in 100%)
        Rectangle parentBoundsInLogicalRef = parent.getBounds().getCopy();
        Point topLeft = parentBoundsInLogicalRef.getTopLeft();
        FigureUtilities.translateToAbsoluteByIgnoringScrollbar(parent, topLeft);
        parentBoundsInLogicalRef.setLocation(topLeft);

        // Check if the parent is visible
        boolean parentIsVisible = visibleAreainLogicalRef.intersects(parentBoundsInLogicalRef);
        Rectangle parentVisibleArea = visibleAreainLogicalRef.getIntersection(parentBoundsInLogicalRef);
        if (!parentIsVisible) {
            // If the parent is not currently visible use the center of it.
            result = parent.getBounds().getCenter().getCopy();
        } else if (parentVisibleArea.equals(visibleAreainLogicalRef)) {
            // The parent take all the place of the visible viewPort (or is the
            // diagram), so we take
            // the center of this.
            result = parentVisibleArea.getCenter();
        } else {
            // Take the center of the visible edit part and translate again in
            // relative to its container coordinates
            result = parentVisibleArea.getCenter();
            FigureUtilities.translateToRelativeByIgnoringScrollbar(parent, result);
        }
        return result;
    }
}
