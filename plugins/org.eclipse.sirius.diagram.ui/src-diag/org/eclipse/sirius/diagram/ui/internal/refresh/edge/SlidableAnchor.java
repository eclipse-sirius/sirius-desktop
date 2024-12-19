/*******************************************************************************
 * Copyright (c) 2011, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.refresh.edge;

import java.util.Optional;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;

/**
 * Provides the GMF implementation of Slidable anchor.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SlidableAnchor {

    /** the first character of the terminal. */
    private static final char TERMINAL_START_CHAR = '(';

    /** the separater character of the terminal. */
    private static final char TERMINAL_DELIMITER_CHAR = ',';

    /** the last character of the terminal. */
    private static final char TERMINAL_END_CHAR = ')';

    private static final int STRAIGHT_LINE_TOLERANCE = 3;

    // The connection anchor reference point (sometimes the same as anchor
    // location)
    private PrecisionPoint relativeReference;

    private View owner;

    /**
     * Creates a terminal string for any reference point passed in the format
     * understandable by slidable anchors.
     * 
     * @param p
     *            - a <Code>PrecisionPoint</Code> that must be represented as a
     *            unique <Code>String</Code>, namely as "(preciseX,preciseY)"
     * @return <code>String</code> terminal composed from specified
     *         <code>PrecisionPoint</code>
     */
    public static String composeTerminalString(PrecisionPoint p) {
        StringBuffer s = new StringBuffer(24);
        s.append(TERMINAL_START_CHAR); // 1 char
        s.append(p.preciseX()); // 10 chars
        s.append(TERMINAL_DELIMITER_CHAR); // 1 char
        s.append(p.preciseY()); // 10 chars
        s.append(TERMINAL_END_CHAR); // 1 char
        return s.toString(); // 24 chars max (+1 for safety, i.e. for string
        // termination)
    }

    /**
     * Default constructor. The anchor will have the center of the figure as the
     * reference point
     * 
     * @param owner
     *            <code>View</code> that this anchor is associated with.
     * @param relativeReference
     *            the relative reference
     */
    public SlidableAnchor(View owner, PrecisionPoint relativeReference) {
        this.owner = owner;
        this.relativeReference = relativeReference;
    }

    /**
     * Get the box.
     * 
     * @return the box
     */
    protected Rectangle getBox() {
        Rectangle box = null;
        if (owner instanceof Node) {
            Node node = (Node) owner;

            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Bounds) {
                Bounds bounds = (Bounds) layoutConstraint;
                box = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
                Node parentNode = node;
                while (parentNode.eContainer() instanceof Node) {
                    parentNode = (Node) parentNode.eContainer();
                    LayoutConstraint parentLayoutConstraint = parentNode.getLayoutConstraint();
                    if (parentLayoutConstraint instanceof Bounds) {
                        Bounds parentBounds = (Bounds) parentLayoutConstraint;
                        box.setX(box.x + parentBounds.getX());
                        box.setY(box.y + parentBounds.getY());
                    }

                }
            }
        } else if (owner instanceof Edge) {
            Optional<Rectangle> optionalRect = GMFHelper.getAbsoluteBounds((Edge) owner);
            if (optionalRect.isPresent()) {
                box = optionalRect.get();
            }
        }
        return box;
    }

    /**
     * Creates terminal string for slidable anchor.
     * 
     * @return <code>String</code> terminal for slidable anchor
     */
    public String getTerminal() {
        if (isDefaultAnchor())
            return StringStatics.BLANK;
        return composeTerminalString(relativeReference);
    }

    /**
     * Returns the reference point for this anchor in absolute coordinates. This
     * might be used by another anchor to determine its own location (i.e.
     * ChopboxAnchor).
     * 
     * @return The reference Point
     */
    public Point getReferencePoint() {
        return getAnchorPosition();
    }

    /**
     * From relative reference returns the relative coordinates of the anchor
     * Method's visibility can be changed as needed
     */
    private Point getAnchorPosition() {
        PrecisionRectangle rBox = new PrecisionRectangle(getBox());
        if (isDefaultAnchor())
            return rBox.getCenter();
        return new PrecisionPoint(relativeReference.preciseX() * rBox.preciseWidth() + rBox.preciseX(), relativeReference.preciseY() * rBox.preciseHeight() + rBox.preciseY());
    }

    /**
     * Calculates the location of the anchor depending on the anchors own
     * reference and foreign reference points.
     * 
     * @param ownReference
     *            - the own reference of the anchor
     * @param foreignReference
     *            - foreign reference that comes in
     * @return the location of the anchor depending on the anchors own reference
     *         and foreign reference points
     */
    protected Point getLocation(Point ownReference, Point foreignReference) {
        PointList intersections = getIntersectionPoints(ownReference, foreignReference);
        if (intersections != null && intersections.size() != 0) {
            Point location = PointListUtilities.pickClosestPoint(intersections, foreignReference);
            return location;
        }
        return null;
    }

    /**
     * Returns the location where the Connection should be anchored in absolute
     * coordinates. The anchor may use the given reference Point to calculate
     * this location.
     * 
     * @param reference
     *            The reference Point in absolute coordinates
     * @return The anchor's location
     */
    public Point getLocation(Point reference) {
        Point ownReference = normalizeToStraightlineTolerance(reference, getReferencePoint(), STRAIGHT_LINE_TOLERANCE);

        Point location = getLocation(ownReference, reference);
        if (location == null) {
            location = getLocation(new PrecisionPoint(getBox().getCenter()), reference);
            if (location == null) {
                location = getBox().getCenter();
            }
        }

        return location;
    }

    /**
     * Returns a new owned reference point that is normalized to be with-in a
     * straight-line tolerance value.
     * 
     * @param foreignReference
     *            <code>Point</code> that is the foreign reference point used to
     *            calculate the interfection anchor point on the shape in
     *            absolute coordinates.
     * @param ownReference
     *            <code>Point</code> that is the reference point with-in the
     *            shape in absolute coordinates
     * @param tolerance
     *            <code>int</code> value that is the difference in absolute
     *            coordinates where the two points would be considered straight
     *            and then adjusted.
     * @return <code>Point</code> that is the normalized owned reference to be
     *         with-in a given straight-line tolerance value of the foreign
     *         reference point.
     */
    protected Point normalizeToStraightlineTolerance(Point foreignReference, Point ownReference, int tolerance) {
        PrecisionPoint preciseOwnReference = new PrecisionPoint(ownReference);
        PrecisionPoint normalizedReference = (PrecisionPoint) preciseOwnReference.getCopy();
        PrecisionPoint preciseForeignReference = new PrecisionPoint(foreignReference);
        if (Math.abs(preciseForeignReference.preciseX() - preciseOwnReference.preciseX()) < tolerance) {
            normalizedReference.setPreciseX(preciseForeignReference.preciseX());
            return normalizedReference;
        }
        if (Math.abs(preciseForeignReference.preciseY() - preciseOwnReference.preciseY()) < tolerance) {
            normalizedReference.setPreciseY(preciseForeignReference.preciseY());
        }
        return normalizedReference;
    }

    /**
     * Calculates intersection points of the figure and the line that passes
     * through ownReference and foreignReference points.
     * 
     * @param ownReference
     *            the reference <code>Point</code> on or inside the shape that
     *            is being anchored to.
     * @param foreignReference
     *            the outside reference <code>Point</code> point that is the
     *            terminal end of the line formed by the two parameters.
     * @return intersection points of the figure and the line that passes
     *         through ownReference and foreignReference points
     */
    protected PointList getIntersectionPoints(Point ownReference, Point foreignReference) {
        final PointList polygon = getPolygonPoints();
        return (new LineSeg(ownReference, foreignReference)).getLineIntersectionsWithLineSegs(polygon);
    }

    /**
     * Returns the list of all the vertices of the figure. The created list must
     * form a polygon, i.e. closed polyline, for figures hence the starting and
     * ending points must be the same
     * 
     * @return the <code>PointList</code> list of all the vertices of the
     *         figure.
     */
    protected PointList getPolygonPoints() {
        PrecisionRectangle r = new PrecisionRectangle(getBox());
        PointList ptList = new PointList(5);
        ptList.addPoint(new PrecisionPoint(r.preciseX(), r.preciseY()));
        ptList.addPoint(new PrecisionPoint(r.preciseX() + r.preciseWidth(), r.preciseY()));
        ptList.addPoint(new PrecisionPoint(r.preciseX() + r.preciseWidth(), r.preciseY() + r.preciseHeight()));
        ptList.addPoint(new PrecisionPoint(r.preciseX(), r.preciseY() + r.preciseHeight()));
        ptList.addPoint(new PrecisionPoint(r.preciseX(), r.preciseY()));
        return ptList;
    }

    /**
     * Returns the owner Figure on which this anchor's location is dependent.
     * 
     * @since 0.9.0
     * @return Owner of this anchor
     */
    public View getOwner() {
        return owner;
    }

    /**
     * Returns true if the <Code>SlidableAnchor</Code> is default one with a
     * reference at the center.
     * 
     * @return <code>boolean</code> <code>true</code> is the
     *         <code>SlidableAnchor</code> is default one, <code>false</code>
     *         otherwise
     */
    public boolean isDefaultAnchor() {
        return relativeReference == null;
    }

    /**
     * Parses anchors terminal string and returns the relative reference
     * incorporated in the terminal string.
     * 
     * @param terminal
     *            - the terminal string containing relative reference
     * @return returns the relative reference incorporated in the terminal
     *         string
     */
    public static PrecisionPoint parseTerminalString(String terminal) {
        try {
            return new PrecisionPoint(Double.parseDouble(terminal.substring(terminal.indexOf(TERMINAL_START_CHAR) + 1, terminal.indexOf(TERMINAL_DELIMITER_CHAR))),
                    Double.parseDouble(terminal.substring(terminal.indexOf(TERMINAL_DELIMITER_CHAR) + 1, terminal.indexOf(TERMINAL_END_CHAR))));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Determines connection anchor point for Orthogonal connection. Generally,
     * the anchor point would the intersection of perpendicular line drawn from
     * the <code>orthoReference</code> point to the shape and shape's edge.
     * 
     * @param orthoReference
     *            the reference point
     * @return The location of the orthogonal connection anchor.
     */
    public Point getOrthogonalLocation(Point orthoReference) {
        PrecisionPoint ownReference = new PrecisionPoint(getReferencePoint());
        PrecisionRectangle bounds = new PrecisionRectangle(getBox());
        PrecisionPoint preciseOrthoReference = new PrecisionPoint(orthoReference);
        int orientation = PositionConstants.NONE;
        if (preciseOrthoReference.preciseX() >= bounds.preciseX() && preciseOrthoReference.preciseX() <= bounds.preciseX() + bounds.preciseWidth()) {
            ownReference.setPreciseX(preciseOrthoReference.preciseX());
            orientation = PositionConstants.VERTICAL;
        } else if (preciseOrthoReference.preciseY() >= bounds.preciseY() && preciseOrthoReference.preciseY() <= bounds.preciseY() + bounds.preciseHeight()) {
            ownReference.setPreciseY(preciseOrthoReference.preciseY());
            orientation = PositionConstants.HORIZONTAL;
        }

        Point location = getLocation(ownReference, preciseOrthoReference);
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

}
