/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.ViewportAutoexposeHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A {@link SiriusScroller} that make the scroll bigger if the cursor is
 * outside editor (the farthest the cursor is, the highest the multiplicator
 * will be).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class SiriusScroller extends ViewportAutoexposeHelper {

    /**
     * The insets on which the scroll will be installed (represents the zone
     * within the editor in which scroll is active).
     */
    public static final Insets VIEWPOINT_SCROLLER_INSETS = new Insets(50);

    /** Defines the range where autoscroll is active inside a viewer. */
    private static final Insets DEFAULT_EXPOSE_THRESHOLD = new Insets(200);

    /**
     * The maximum value for the scroll multiplicator.
     */
    private static final int MAXIMUM_MULTIPLICATOR = 8;

    /**
     * The distance (in px) from the cursor to the editor bounds that causes
     * increase of the multiplicator.
     */
    private static final int DISTANCE_THAT_INCREASE_MULTIPLICATOR = 20;

    /** The last time an auto expose was performed. */
    private long lastStepTime;

    /** The insets for this helper. */
    private Insets threshold;

    /**
     * Constructs a new helper on the given GraphicalEditPart. The editpart must
     * have a <code>Viewport</code> somewhere between its <i>contentsPane</i>
     * and its <i>figure</i> inclusively.
     * 
     * @param owner
     *            the GraphicalEditPart that owns the Viewport
     */
    public SiriusScroller(GraphicalEditPart owner) {
        super(owner);
        threshold = DEFAULT_EXPOSE_THRESHOLD;
    }

    /**
     * Constructs a new helper on the given GraphicalEditPart. The editpart must
     * have a <code>Viewport</code> somewhere between its <i>contentsPane</i>
     * and its <i>figure</i> inclusively.
     * 
     * @param owner
     *            the GraphicalEditPart that owns the Viewport
     * @param threshold
     *            the Expose Threshold to use when determing whether or not a
     *            scroll should occur.
     */
    public SiriusScroller(GraphicalEditPart owner, Insets threshold) {
        super(owner);
        this.threshold = threshold;
    }

    /**
     * Returns <code>true</code> if the given point is inside the viewport, but
     * near its edge.
     * 
     * @param where
     *            the point to test
     * @return <code>true</code> if the given point is inside the viewport, but
     *         near its edge
     * @see org.eclipse.gef.AutoexposeHelper#detect(org.eclipse.draw2d.geometry.Point)
     */
    public boolean detect(Point where) {
        lastStepTime = 0;
        Viewport port = findViewport(owner);
        Rectangle rect = Rectangle.SINGLETON;
        port.getClientArea(rect);
        port.translateToParent(rect);
        port.translateToAbsolute(rect);
        return rect.contains(where) && !rect.shrink(threshold).contains(where);
    }

    /**
     * Returns <code>true</code> if the given point is outside the viewport or
     * near its edge. Scrolls the viewport by a calculated (time based) amount
     * in the current direction.
     * 
     * @param where
     *            the cursor location
     * @return <code>true</code> if the given point is outside the viewport or
     *         near its edge, false otherwise
     * 
     * @see org.eclipse.gef.AutoexposeHelper#step(org.eclipse.draw2d.geometry.Point)
     */
    public boolean step(Point where) {
        Viewport port = findViewport(owner);

        Option<Point> loc = calculateScroll(port, where);
        if (loc.some()) {
            port.setViewLocation(loc.get());
        }
        return true;
    }

    /**
     * Calculates the scroll according to the cursor location and the editor
     * bounds. If the cursor is outside the editor, then a multiplicator
     * coefficient will be applied on the scroll, according to the distance
     * between editor and cursor.
     * 
     * @param port
     *            the editor bounds
     * @param where
     *            the cursor location
     * @return the scroll according to the cursor location and the editor bounds
     */
    public Option<Point> calculateScroll(Viewport port, Point where) {

        Option<Point> result = Options.newNone();
        int multiplicator = 1;

        // Step 1 : checking that the point is valid for scroll
        Rectangle rect = Rectangle.SINGLETON;
        port.getClientArea(rect);
        port.translateToParent(rect);
        port.translateToAbsolute(rect);
        if (!rect.contains(where) || rect.shrink(threshold).contains(where)) {
            // If the zone is out of editor bounds
            // we calculate a new multiplicator according to the distance
            // between the cursor location on the bounds of the editors
            multiplicator = calculateMultiplicator(port, where);
        }

        // Step 2 : calculate scroll offset (timed base)
        int scrollOffset = 0;
        if (lastStepTime == 0)
            lastStepTime = System.currentTimeMillis();

        long difference = System.currentTimeMillis() - lastStepTime;

        if (difference > 0) {
            scrollOffset = (int) difference / 3;
            lastStepTime = System.currentTimeMillis();
        }

        // Step 3 : calculate scroll location
        if (scrollOffset != 0) {
            scrollOffset = multiplicator * scrollOffset;
            rect.shrink(threshold);

            int region = rect.getPosition(where);
            Point loc = port.getViewLocation();

            loc = updateLocationAccordingToPosition(scrollOffset, region, loc);
            result = Options.newSome(loc);
        }
        return result;
    }

    /**
     * Calculates the mutliplicator to apply on the offset, according to the
     * cursor distance from editor : the farthest the cursor is, the highest the
     * multiplicator will be.
     * 
     * @param port
     *            the editor bounds
     * @param where
     *            the cursor location
     * @return the mutliplicator to apply on the offset
     */
    private int calculateMultiplicator(Viewport port, Point where) {
        int mutliplicator = 1;
        double maximalDistance = 0;

        // Getting the distance between the cursor and the editor bounds
        // left distance
        if (where.x < port.getBounds().x) {
            maximalDistance = Math.abs(port.getBounds().x - where.x);
        }

        // right distance
        if (where.x > (port.getBounds().x + port.getBounds().width)) {
            maximalDistance = where.x - (port.getBounds().x + port.getBounds().width);
        }
        // top distance
        if (where.y < port.getBounds().y) {
            double topDistance = Math.abs(port.getBounds().y - where.y);
            if (topDistance > maximalDistance) {
                maximalDistance = topDistance;
            }
        }
        // bottom distance
        if (where.y > (port.getBounds().y + port.getBounds().height)) {
            double bottomDistance = where.y - (port.getBounds().y + port.getBounds().height);
            if (bottomDistance > maximalDistance) {
                maximalDistance = bottomDistance;
            }
        }

        // Calculating the multiplicator accord to maximal distance
        int candidateMultiplicator = (int) maximalDistance / DISTANCE_THAT_INCREASE_MULTIPLICATOR + 1;
        if (candidateMultiplicator > mutliplicator) {
            mutliplicator = Math.min(MAXIMUM_MULTIPLICATOR, candidateMultiplicator);
        }
        return mutliplicator;
    }

    private Point updateLocationAccordingToPosition(int scrollOffset, int region, Point loc) {
        Point result = loc;
        if ((region & PositionConstants.SOUTH) != 0) {
            result.setY(result.y + scrollOffset);
        } else {
            if ((region & PositionConstants.NORTH) != 0) {
                result.setY(result.y - scrollOffset);
            }
        }

        if ((region & PositionConstants.EAST) != 0) {
            result.setX(result.x + scrollOffset);
        } else {
            if ((region & PositionConstants.WEST) != 0) {
                result.setX(result.x - scrollOffset);
            }
        }
        return result;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editparts.ViewportAutoexposeHelper#toString()
     */
    public String toString() {
        return "ViewportAutoexposeHelper for: " + owner; //$NON-NLS-1$
    }

}
