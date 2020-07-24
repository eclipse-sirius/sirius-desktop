/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.bracket;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;

/**
 * A Query to compute new {@link PointList} from a {@link BendpointRequest} or
 * from the DimensionConnectionRouter.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketConnectionQuery {

    /** default side used for the first point. */
    public static final Direction DEFAULT_SOURCE_SIDE = Direction.RIGHT;

    /** default {@link Direction} used at creation. */
    public static final Direction DEFAULT_DIRECTION = Direction.RIGHT;

    /** default offset used at creation. */
    public static final int DEFAULT_OFFSET = 100;

    /** index for the origin point. */
    public static final int ORIGIN_POINT_INDEX = 2;

    /** index for the target point. */
    public static final int TARGET_POINT_INDEX = 3;

    /** offset. */
    public static final int DECO_OFFSET = 8;

    /** The {@link BendpointRequest}. */
    private BendpointRequest bendpointRequest;

    /** The {@link Connection}. */
    private Connection connection;

    /**
     * The current {@link PointList} of the {@link Connection} or a default one.
     */
    private PointList currentPointList;

    /**
     * Tells if we must add origin and target point in case of undefined general
     * Direction.
     */
    private boolean mustAddOriginAndTargetPoint;

    /** computed source {@link ConnectionAnchor#getLocation(Point)} . */
    private Point updatedFirstPoint;

    /** computed target {@link ConnectionAnchor#getLocation(Point)} . */
    private Point updatedLastPoint;

    /** The general {@link Direction} from the source. */
    private Direction generalDirection;

    /** the specific direction relative to the sourceFigure. */
    private Direction newDirectionToSourceFigure;

    /** the specific direction relative to the targetFigure. */
    private Direction newDirectionToTargetFigure;

    private Rectangle sourceBounds;

    private Rectangle targetBounds;

    /**
     * Default constructor.
     *
     * @param bendpointRequest
     *            the {@link BendpointRequest} to compute a new {@link PointList}
     * @param connection
     *            the {@link Connection}
     */
    public BracketConnectionQuery(BendpointRequest bendpointRequest, Connection connection) {
        this.bendpointRequest = bendpointRequest;
        this.connection = connection;
        this.currentPointList = connection.getPoints();
    }

    /**
     * Constructor used by Draw2d part BracketAnchor.
     *
     * @param connection
     *            the {@link Connection}
     */
    public BracketConnectionQuery(Connection connection) {
        this(null, connection);
    }

    /**
     * Get the new computed {@link PointList} from the specified
     * {@link BendpointRequest}.
     *
     * @return the new computed {@link PointList}
     */
    public PointList getNewPointList() {

        final PointList pointListFromConstraint = getPointListFromConstraint();

        currentPointList = pointListFromConstraint;
        Point firstPoint = currentPointList.getFirstPoint().getCopy();
        Point lastPoint = currentPointList.getLastPoint().getCopy();
        final Rectangle rectangle = new Rectangle(sourceBounds.getCenter(), targetBounds.getCenter());
        Point center = rectangle.getCenter().getCopy();

        Point location;

        if (bendpointRequest != null) {
            location = bendpointRequest.getLocation().getCopy();
        } else {
            location = currentPointList.getMidpoint().getCopy();
        }

        connection.translateToRelative(location);

        // For a move request keep the same direction
        final Direction newDirection = DirectionUtil.getDirection(location, center);
        generalDirection = newDirection;

        computeConnectionAnchorsLocation(location, newDirection);

        firstPoint = updatedFirstPoint;
        lastPoint = updatedLastPoint;

        final PointList newPointList = new PointList();
        newPointList.addPoint(firstPoint);
        if (mustAddOriginAndTargetPoint) {
            addOriginAndTargetPoint(newPointList, newDirection, location, firstPoint, lastPoint);
        }
        newPointList.addPoint(lastPoint);
        currentPointList = newPointList;

        return newPointList;
    }

    /**
     * Add intermediate {@link Point} to the new {@link PointList}.
     *
     * @param newPointList
     *            the {@link PointList} to update
     * @param newDirection
     *            the new {@link Direction}
     * @param location
     *            the location {@link Point}
     * @param firstPoint
     *            the first {@link Point} of the Dimension connection figure
     * @param lastPoint
     *            the last {@link Point} of the Dimension connection figure
     */
    private void addOriginAndTargetPoint(PointList newPointList, Direction newDirection, Point location, Point firstPoint, Point lastPoint) {
        final Point originPoint = new Point();
        final Point targetPoint = new Point();

        switch (newDirection) {
        case RIGHT:
        case LEFT:
            originPoint.x = location.x;
            originPoint.y = firstPoint.y;

            targetPoint.x = location.x;
            targetPoint.y = lastPoint.y;
            break;
        case TOP:
        case BOTTOM:
            originPoint.x = firstPoint.x;
            originPoint.y = location.y;

            targetPoint.x = lastPoint.x;
            targetPoint.y = location.y;

            break;
        default:
        }

        final Point originDecoPoint = getOriginDecoPoint(originPoint);
        newPointList.addPoint(originDecoPoint);
        newPointList.addPoint(originPoint);
        newPointList.addPoint(targetPoint);
        final Point targetDecoPoint = getTargetDecoPoint(targetPoint);
        newPointList.addPoint(targetDecoPoint);

    }

    /**
     * Tells if we have default {@link PointList} for a dimension connection not
     * yet routed.
     *
     * @return true if we have default {@link PointList} for a dimension
     *         connection not yet routed, false else
     */
    public boolean isDefaultPointListAtCreation() {
        if (currentPointList == null) {
            return false;
        }

        boolean isDefaultPointListAtCreation = currentPointList.size() == 0;
        if (!isDefaultPointListAtCreation && connection != null) {
            final Point firstPoint = currentPointList.getFirstPoint().getCopy();
            final Point lastPoint = currentPointList.getLastPoint().getCopy();
            final boolean defaultFirstPoint = firstPoint.x == 0 && firstPoint.y == 0;
            final boolean defaultLastPoint = lastPoint.x == 100 && lastPoint.y == 100;
            isDefaultPointListAtCreation = currentPointList.size() == 2 && defaultFirstPoint && defaultLastPoint;
        }
        return isDefaultPointListAtCreation;
    }

    /**
     * Tells if we have default constraint for a dimension connection not yet
     * routed.
     *
     * @return true if we have default constraint for a dimension connection not
     *         yet routed, false else
     */
    @SuppressWarnings("unchecked")
    public boolean hasDefaultContraint() {
        boolean hasDefaultContraint = false;
        final List<Bendpoint> bendpoints = (List<Bendpoint>) connection.getRoutingConstraint();
        if (bendpoints == null) {
            hasDefaultContraint = true;
        } else {
            hasDefaultContraint = bendpoints.size() == 2;
        }
        return hasDefaultContraint;
    }

    /**
     * Tells if we must add origin and target point.
     *
     * @param location
     *            the request cursor location
     * @param firstPoint
     *            the first {@link Point} of the {@link Connection}
     * @param lastPoint
     *            the last {@link Point} of the {@link Connection}
     * @param newDirection
     *            the new computed {@link Direction}
     * @return true if we must add origin and target point, false else
     */
    private boolean mustAddOriginAndTargetPoint(Point location, Point firstPoint, Point lastPoint, Direction newDirection) {
        switch (newDirection) {
        case LEFT:
        case RIGHT:
            mustAddOriginAndTargetPoint = firstPoint.y != lastPoint.y;
            break;
        case TOP:
        case BOTTOM:
            mustAddOriginAndTargetPoint = firstPoint.x != lastPoint.x;
            break;
        default:
            throw new UnsupportedOperationException();
        }
        return mustAddOriginAndTargetPoint;
    }

    /**
     * Get the anchor location {@link Point} from the specified
     * {@link ConnectionAnchor} according to the specified {@link Direction}.
     *
     * @param connectionAnchor
     *            specified {@link ConnectionAnchor}
     * @param direction
     *            the specified {@link Direction}
     * @return the anchor location {@link Point}
     */
    private Point getAnchorLocation(ConnectionAnchor connectionAnchor, Direction direction) {
        Point anchorLocation = null;

        // In case of connection as source/target, take the middle
        if (connectionAnchor.getOwner() instanceof Connection) {
            Connection ownerConnection = (Connection) connectionAnchor.getOwner();
            anchorLocation = ownerConnection.getPoints().getMidpoint().getCopy();
            ownerConnection.translateToAbsolute(anchorLocation);
        } else {
            final Rectangle connectionAnchorOwnerBounds = getConnectionAnchorOwnerBounds(connectionAnchor.getOwner());
            if (connectionAnchorOwnerBounds != null) {

                switch (direction) {
                case LEFT:
                    anchorLocation = connectionAnchorOwnerBounds.getLeft().getCopy();
                    break;
                case RIGHT:
                    anchorLocation = connectionAnchorOwnerBounds.getRight().getCopy();
                    break;
                case TOP:
                    anchorLocation = connectionAnchorOwnerBounds.getTop().getCopy();
                    break;
                case BOTTOM:
                    anchorLocation = connectionAnchorOwnerBounds.getBottom().getCopy();
                    break;
                default:
                    throw new UnsupportedOperationException();
                }
            } else {
                if (connectionAnchor == connection.getSourceAnchor()) {
                    anchorLocation = connection.getPoints().getFirstPoint().getCopy();
                } else {
                    anchorLocation = connection.getPoints().getLastPoint().getCopy();
                }
            }
        }
        connection.translateToRelative(anchorLocation);
        return anchorLocation;
    }

    /**
     * Gets the connection anchor owner.
     *
     * @param connectionAnchorOwner
     *            the connection anchor
     * @return the connection bounds
     */
    private Rectangle getConnectionAnchorOwnerBounds(IFigure connectionAnchorOwner) {
        if (connectionAnchorOwner == null) {
            return null;
        }
        Rectangle connectionAnchorOwnerBounds = connectionAnchorOwner.getBounds().getCopy();
        if (connectionAnchorOwner instanceof Connection) {
            Connection connectionOwner = (Connection) connectionAnchorOwner;
            connectionAnchorOwnerBounds = new Rectangle(connectionOwner.getPoints().getMidpoint(), Dimension.SINGLETON);
        }
        if (bendpointRequest != null) {
            Point location = connectionAnchorOwnerBounds.getLocation();
            connectionAnchorOwner.translateToAbsolute(location);
            connectionAnchorOwnerBounds.setLocation(location);
        } else {
            connectionAnchorOwner.translateToAbsolute(connectionAnchorOwnerBounds);
        }
        return connectionAnchorOwnerBounds;
    }

    /**
     * Get a specific Direction from the current cursor location, a
     * {@link ConnectionAnchor} and a general {@link Direction}.
     *
     * @param location
     *            the current cursor location
     * @param connectionAnchorOwner
     *            a {@link ConnectionAnchor#getOwner()}
     * @param direction
     *            a general {@link Direction}
     * @return a specific Direction
     */
    public Direction getDirection(Point location, IFigure connectionAnchorOwner, Direction direction) {
        Direction result;
        final Rectangle connectionAnchorOwnerBounds = getConnectionAnchorOwnerBounds(connectionAnchorOwner);
        final Point center = connectionAnchorOwnerBounds.getCenter();
        final int diffX = location.x - center.x;
        final int diffY = location.y - center.y;

        switch (direction) {
        case LEFT:
        case RIGHT:
            if (diffX < 0) {
                result = Direction.LEFT;
            } else {
                result = Direction.RIGHT;
            }
            break;
        case TOP:
        case BOTTOM:
            if (diffY > 0) {
                result = Direction.BOTTOM;
            } else {
                result = Direction.TOP;
            }
            break;
        default:
            throw new UnsupportedOperationException();
        }
        return result;
    }

    /**
     * Computes the source and target
     * {@link ConnectionAnchor#getLocation(Point)} for Dimension connection
     * figure.
     *
     * @param location
     *            a relative {@link Point}
     * @param newDirection
     *            the current general dimension figure direction
     */
    public void computeConnectionAnchorsLocation(Point location, Direction newDirection) {
        final Point firstPoint = currentPointList.getFirstPoint().getCopy();
        final Point lastPoint = currentPointList.getLastPoint().getCopy();
        newDirectionToSourceFigure = getDirection(location, connection.getSourceAnchor().getOwner(), newDirection);
        newDirectionToTargetFigure = getDirection(location, connection.getTargetAnchor().getOwner(), newDirection);
        mustAddOriginAndTargetPoint = mustAddOriginAndTargetPoint(location, firstPoint, lastPoint, newDirection);

        updatedFirstPoint = getAnchorLocation(connection.getSourceAnchor(), newDirectionToSourceFigure);
        updatedLastPoint = getAnchorLocation(connection.getTargetAnchor(), newDirectionToTargetFigure);

        if (!mustAddOriginAndTargetPoint) {
            switch (newDirection) {
            case LEFT:
            case RIGHT:
                updatedFirstPoint = getAnchorLocation(connection.getSourceAnchor(), Direction.LEFT);
                updatedLastPoint = getAnchorLocation(connection.getTargetAnchor(), Direction.RIGHT);
                break;
            case TOP:
            case BOTTOM:
                updatedFirstPoint = getAnchorLocation(connection.getSourceAnchor(), Direction.BOTTOM);
                updatedLastPoint = getAnchorLocation(connection.getTargetAnchor(), Direction.TOP);
                break;
            default:
                throw new UnsupportedOperationException();
            }
        }
    }

    /**
     * Get the origin deco point.
     *
     * @param originPoint
     *            the origin {@link Point}
     * @return the origin deco point
     */
    private Point getOriginDecoPoint(Point originPoint) {
        final Point originDecoPoint = getDecoPoint(newDirectionToSourceFigure, sourceBounds, originPoint);
        return originDecoPoint;
    }

    /**
     * Get the target deco point.
     *
     * @param targetPoint
     *            the target {@link Point}
     * @return the target deco point
     */
    private Point getTargetDecoPoint(Point targetPoint) {
        final Point targetDecoPoint = getDecoPoint(newDirectionToTargetFigure, targetBounds, targetPoint);
        return targetDecoPoint;
    }

    /**
     * Get a decoration point from a specific {@link Direction}.
     *
     * @param specificDirection
     *            the specific {@link Direction}
     * @param ownerBounds
     *            the bounds of the nearest owner
     * @param point
     *            the {@link Point}
     * @return a decoration point from a specific {@link Direction}
     */
    private Point getDecoPoint(Direction specificDirection, Rectangle ownerBounds, Point point) {
        final Point decoPoint = new Point();
        switch (specificDirection) {
        case RIGHT:
            decoPoint.y = point.y;
            if (point.x > ownerBounds.x && point.x < ownerBounds.x + ownerBounds.width) {
                decoPoint.x = point.x - DECO_OFFSET;
            } else {
                decoPoint.x = point.x + DECO_OFFSET;
            }
            break;
        case LEFT:
            decoPoint.y = point.y;
            if (point.x > ownerBounds.x && point.x < ownerBounds.x + ownerBounds.width) {
                decoPoint.x = point.x + DECO_OFFSET;
            } else {
                decoPoint.x = point.x - DECO_OFFSET;
            }
            break;
        case TOP:
            decoPoint.x = point.x;
            if (point.y > ownerBounds.y && point.y < ownerBounds.y + ownerBounds.height) {
                decoPoint.y = point.y + DECO_OFFSET;
            } else {
                decoPoint.y = point.y - DECO_OFFSET;
            }
            break;
        case BOTTOM:
            decoPoint.x = point.x;
            if (point.y > ownerBounds.y && point.y < ownerBounds.y + ownerBounds.height) {
                decoPoint.y = point.y - DECO_OFFSET;
            } else {
                decoPoint.y = point.y + DECO_OFFSET;
            }
            break;
        default:
            throw new UnsupportedOperationException();
        }
        return decoPoint;
    }

    /**
     * Get a {@link PointList} from the list of {@link Bendpoint}, the
     * {@link Connection#getRoutingConstraint()}.
     *
     * @return Get a {@link PointList} from the list of {@link Bendpoint}, i.e.
     *         the routing constraint
     */
    public PointList getPointListFromConstraint() {
        return getPointListFromConstraintAndMove(new Point(0, 0), true);
    }

    /**
     * Get a {@link PointList} from the list of {@link Bendpoint}, the
     * {@link Connection#getRoutingConstraint()}. The moveDelta allows to
     * compute the point list as if the source (of target) has been moved.
     *
     * @return Get a {@link PointList} from the list of {@link Bendpoint}, i.e.
     *         the routing constraint
     */
    // CHECKSTYLE:OFF
    public PointList getPointListFromConstraintAndMove(Point moveDelta, boolean sourceMove) {
        @SuppressWarnings("unchecked")
        final List<BracketRelativeBendpoint> bracketRelativeBendpoints = (List<BracketRelativeBendpoint>) connection.getRoutingConstraint();

        final PointList points = new PointList();
        Point firstPoint = null;
        Point originPoint = null;
        Point originDecoPoint = null;
        Point targetDecoPoint = null;
        Point targetPoint = null;
        Point lastPoint = null;

        if (bracketRelativeBendpoints != null && bracketRelativeBendpoints.size() == 1) {
            final BracketRelativeBendpoint firstDimensionRelativeBendpoint = bracketRelativeBendpoints.get(0);
            final Direction sourceDirection = Direction.values()[firstDimensionRelativeBendpoint.getSourceDirection()];
            final Direction firstDirection = Direction.values()[firstDimensionRelativeBendpoint.getDirection()];
            final int firstOffset = firstDimensionRelativeBendpoint.getOffset();
            generalDirection = firstDirection;

            firstPoint = getAnchorLocation(connection.getSourceAnchor(), sourceDirection);
            Point targetReferencePoint = connection.getTargetAnchor().getReferencePoint();
            // In case of connection as target, take the middle
            if (connection.getTargetAnchor().getOwner() instanceof Connection) {
                Connection targetConnection = (Connection) connection.getTargetAnchor().getOwner();
                targetReferencePoint = targetConnection.getPoints().getMidpoint().getCopy();
            }
            if (connection.getTargetAnchor() == null || connection.getSourceAnchor() == null || connection.getTargetAnchor().getOwner() == null || connection.getSourceAnchor().getOwner() == null) {
                return connection.getPoints();
            }
            sourceBounds = getConnectionAnchorOwnerBounds(connection.getSourceAnchor().getOwner());
            connection.translateToRelative(sourceBounds);
            targetBounds = getConnectionAnchorOwnerBounds(connection.getTargetAnchor().getOwner());
            connection.translateToRelative(targetBounds);
            if (sourceMove) {
                firstPoint.translate(moveDelta);
                sourceBounds.translate(moveDelta);
            } else {
                targetBounds.translate(moveDelta);
                targetReferencePoint.translate(moveDelta);
            }
            // connection.getTargetAnchor().getOwner().translateToRelative(targetReferencePoint);
            Direction lastDirection = null;
            int lastOffset = 0;

            switch (firstDirection) {
            case RIGHT:
                originDecoPoint = firstPoint.getTranslated(firstOffset + DECO_OFFSET, 0);
                originPoint = originDecoPoint.getTranslated(-DECO_OFFSET, 0);
                if (targetReferencePoint.x > originPoint.x) {
                    lastDirection = Direction.LEFT;
                } else {
                    lastDirection = Direction.RIGHT;
                }
                lastPoint = getAnchorLocation(connection.getTargetAnchor(), lastDirection);
                if (!sourceMove) {
                    lastPoint.translate(moveDelta);
                }
                lastOffset = originPoint.x - lastPoint.x;
                break;
            case LEFT:
                originDecoPoint = firstPoint.getTranslated(-firstOffset - DECO_OFFSET, 0);
                originPoint = originDecoPoint.getTranslated(DECO_OFFSET, 0);
                if (targetReferencePoint.x < originPoint.x) {
                    lastDirection = Direction.RIGHT;
                } else {
                    lastDirection = Direction.LEFT;
                }
                lastPoint = getAnchorLocation(connection.getTargetAnchor(), lastDirection);
                if (!sourceMove) {
                    lastPoint.translate(moveDelta);
                }
                lastOffset = originPoint.x - lastPoint.x;
                break;
            case TOP:
                originDecoPoint = firstPoint.getTranslated(0, -firstOffset - DECO_OFFSET);
                originPoint = originDecoPoint.getTranslated(0, DECO_OFFSET);
                if (targetReferencePoint.y > originPoint.y) {
                    lastDirection = Direction.TOP;
                } else {
                    lastDirection = Direction.BOTTOM;
                }
                lastPoint = getAnchorLocation(connection.getTargetAnchor(), lastDirection);
                if (!sourceMove) {
                    lastPoint.translate(moveDelta);
                }
                lastOffset = originPoint.y - lastPoint.y;
                break;
            case BOTTOM:
                originDecoPoint = firstPoint.getTranslated(0, firstOffset + DECO_OFFSET);
                originPoint = originDecoPoint.getTranslated(0, -DECO_OFFSET);
                if (targetReferencePoint.y > originPoint.y) {
                    lastDirection = Direction.TOP;
                } else {
                    lastDirection = Direction.BOTTOM;
                }
                lastPoint = getAnchorLocation(connection.getTargetAnchor(), lastDirection);
                if (!sourceMove) {
                    lastPoint.translate(moveDelta);
                }
                lastOffset = originPoint.y - lastPoint.y;
                break;
            default:
            }

            switch (lastDirection) {
            case RIGHT:
                if (originPoint.x > targetBounds.x && originPoint.x < targetBounds.x + targetBounds.width) {
                    targetDecoPoint = lastPoint.getTranslated(lastOffset - DECO_OFFSET, 0);
                    targetPoint = targetDecoPoint.getTranslated(DECO_OFFSET, 0);
                } else {
                    targetDecoPoint = lastPoint.getTranslated(lastOffset + DECO_OFFSET, 0);
                    targetPoint = targetDecoPoint.getTranslated(-DECO_OFFSET, 0);
                }
                break;
            case LEFT:
                if (originPoint.x > targetBounds.x && originPoint.x < targetBounds.x + targetBounds.width) {
                    targetDecoPoint = lastPoint.getTranslated(lastOffset + DECO_OFFSET, 0);
                    targetPoint = targetDecoPoint.getTranslated(-DECO_OFFSET, 0);
                } else {
                    targetDecoPoint = lastPoint.getTranslated(lastOffset - DECO_OFFSET, 0);
                    targetPoint = targetDecoPoint.getTranslated(DECO_OFFSET, 0);
                }
                break;
            case TOP:
                if (originPoint.y > targetBounds.y && originPoint.y < targetBounds.y + targetBounds.height) {
                    targetDecoPoint = lastPoint.getTranslated(0, lastOffset + DECO_OFFSET);
                    targetPoint = targetDecoPoint.getTranslated(0, -DECO_OFFSET);
                } else {
                    targetDecoPoint = lastPoint.getTranslated(0, lastOffset - DECO_OFFSET);
                    targetPoint = targetDecoPoint.getTranslated(0, DECO_OFFSET);
                }
                break;
            case BOTTOM:
                if (originPoint.y > targetBounds.y && originPoint.y < targetBounds.y + targetBounds.height) {
                    targetDecoPoint = lastPoint.getTranslated(0, lastOffset - DECO_OFFSET);
                    targetPoint = targetDecoPoint.getTranslated(0, DECO_OFFSET);
                } else {
                    targetDecoPoint = lastPoint.getTranslated(0, lastOffset + DECO_OFFSET);
                    targetPoint = targetDecoPoint.getTranslated(0, -DECO_OFFSET);
                }
                break;
            default:
            }

            points.addPoint(firstPoint);
            if (!(isVerticalBracket(firstDirection, lastDirection) || isHorizontalBracket(firstDirection, lastDirection))) {
                points.addPoint(originDecoPoint);
                points.addPoint(originPoint);
                points.addPoint(targetPoint);
                points.addPoint(targetDecoPoint);
            }
            points.addPoint(lastPoint);
        }
        return points;
    }

    // CHECKSTYLE:ON

    /**
     * Tells if the two specified direction indicate a vertical Direction.
     *
     * @param firstDirection
     *            the first {@link Direction}
     * @param lastDirection
     *            the last {@link Direction}
     * @return true if the two specified direction indicate a vertical
     *         Direction, false else
     */
    public boolean isVerticalBracket(Direction firstDirection, Direction lastDirection) {
        final boolean isVerticalDimensionOnDirection = firstDirection == Direction.TOP && lastDirection == Direction.BOTTOM || firstDirection == Direction.BOTTOM && lastDirection == Direction.TOP;
        return isVerticalDimensionOnDirection && connection.getSourceAnchor().getOwner().getBounds().getCenter().x == connection.getTargetAnchor().getOwner().getBounds().getCenter().x;
    }

    /**
     * Tells if the two specified direction indicate a horizontal Direction.
     *
     * @param firstDirection
     *            the first {@link Direction}
     * @param lastDirection
     *            the last {@link Direction}
     * @return true if the two specified direction indicate a horizontal
     *         Direction, false else
     */
    public boolean isHorizontalBracket(Direction firstDirection, Direction lastDirection) {
        final boolean isHorizontalDimension = firstDirection == Direction.LEFT && lastDirection == Direction.RIGHT || firstDirection == Direction.RIGHT && lastDirection == Direction.LEFT;
        return isHorizontalDimension && connection.getSourceAnchor().getOwner().getBounds().getCenter().y == connection.getTargetAnchor().getOwner().getBounds().getCenter().y;
    }

    /**
     * Get a {@link List} of gmf {@link RelativeBendpoint} from a two Direction
     * and two offset relative to dimension source/target.
     *
     * @param newPointList
     *            the {@link PointList} of the dimension {@link Connection}
     * @return a {@link List} of {@link RelativeBendpoint}
     */
    public List<RelativeBendpoint> getGMFRelativeBendpoints(PointList newPointList) {
        List<RelativeBendpoint> relativeBendpointsFromPointList = null;
        if (newPointList.size() >= 2) {
            final Point firstPoint = newPointList.getFirstPoint();
            final Point lastPoint = newPointList.getLastPoint();

            final Direction firstDirection = DirectionUtil.getDirection(newPointList.getPoint(1), firstPoint);
            final Direction sourceDirection = DirectionUtil.getDirection(firstPoint, sourceBounds.getCenter());

            int firstOffset = 0;

            switch (firstDirection) {
            case LEFT:
            case RIGHT:
                firstOffset = Math.abs(firstPoint.x - lastPoint.x);
                break;
            case TOP:
            case BOTTOM:
                firstOffset = Math.abs(firstPoint.y - lastPoint.y);
                break;
            default:
                break;
            }
            if (newPointList.size() == 6) {
                Point originPoint = newPointList.getPoint(BracketConnectionQuery.ORIGIN_POINT_INDEX);
                switch (firstDirection) {
                case LEFT:
                case RIGHT:
                    firstOffset = Math.abs(originPoint.x - firstPoint.x);
                    break;
                case TOP:
                case BOTTOM:
                    firstOffset = Math.abs(originPoint.y - firstPoint.y);
                    break;
                default:
                    break;
                }
            }
            relativeBendpointsFromPointList = getGMFRelativeBendpoints(sourceDirection, firstDirection, firstOffset);
        }
        return relativeBendpointsFromPointList;
    }

    /**
     * Get a {@link List} of gmf {@link RelativeBendpoint} from a two Direction
     * and two offset relative to dimension source/target.
     *
     * @param firstDirection
     *            the {@link Direction} relative to the dimension source
     * @param sourceDirection
     *            the side of the source figure from which start the bracket
     *            point list
     * @param firstOffset
     *            the offset relative to the dimension source
     * @return a {@link List} of {@link RelativeBendpoint}
     */
    public List<RelativeBendpoint> getGMFRelativeBendpoints(Direction sourceDirection, Direction firstDirection, int firstOffset) {
        final List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();
        newBendpoints.add(new RelativeBendpoint(sourceDirection.ordinal(), firstDirection.ordinal(), firstOffset, -1));
        return newBendpoints;
    }

    /**
     * Get Draw2d {@link BracketRelativeBendpoint}s from gmf
     * {@link RelativeBendpoint}s.
     *
     * @param gmfRelativeBendpoints
     *            gmf {@link RelativeBendpoint}s
     * @return Draw2d {@link BracketRelativeBendpoint}s
     */
    public List<BracketRelativeBendpoint> getBracketRelativeBendointFromGMFRelativeBendpoints(List<RelativeBendpoint> gmfRelativeBendpoints) {
        final List<BracketRelativeBendpoint> figureConstraint = new ArrayList<BracketRelativeBendpoint>();
        if (gmfRelativeBendpoints.size() == 1) {
            final RelativeBendpoint firstGMFRelativeBendpoint = gmfRelativeBendpoints.get(0);
            final int firstSourceDirection = firstGMFRelativeBendpoint.getSourceX();
            final int firstDirection = firstGMFRelativeBendpoint.getSourceY();
            final int firstOffset = firstGMFRelativeBendpoint.getTargetX();
            figureConstraint.add(new BracketRelativeBendpoint(connection, firstSourceDirection, firstDirection, firstOffset));
        }
        return figureConstraint;
    }

    /**
     * Get the general {@link Direction} of this bracket.
     *
     * @return the general {@link Direction}
     */
    public Direction getGeneralDirection() {
        return generalDirection;
    }

    /**
     * Get the {@link Rectangle} of the main bracket segment.
     *
     * @return the {@link Rectangle} of the main bracket segment
     */
    public Rectangle getBracketSegmentBounds() {
        Rectangle bracketSegmentRectangle = new Rectangle();
        PointList points = connection.getPoints();
        if (points.size() == 2) {
            Point firstPoint = points.getFirstPoint();
            Point lastPoint = points.getLastPoint();
            bracketSegmentRectangle = new Rectangle(firstPoint, lastPoint);
        } else if (points.size() == 6) {
            Point point1 = points.getPoint(ORIGIN_POINT_INDEX);
            Point point2 = points.getPoint(TARGET_POINT_INDEX);
            bracketSegmentRectangle = new Rectangle(point1, point2);
        }
        return bracketSegmentRectangle;
    }

}
