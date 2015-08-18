/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Obeo - adaptation
 *    Maxime Porhel (Obeo) <maxime.porhel@obeo.fr> - Trac bug #1524 : Layout issue after modification made in breakdown diagram.
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import java.util.Map;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.ConnectionRouter;
import org.eclipse.draw2d.FreeformViewport;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ITreeConnection;
import org.eclipse.gmf.runtime.draw2d.ui.internal.routers.OrthogonalRouter;

import com.google.common.collect.Maps;

/**
 * A router for Sirius .
 * 
 * @author ymortier
 */
public class DForestRouter extends BendpointConnectionRouter implements OrthogonalRouter {

    private final Map<AnchorKey, DTreeRouter> connections = Maps.newHashMap();

    private final Map<AnchorKey, Boolean> trunkVertexes = Maps.newHashMap();

    @Override
    public void remove(final Connection conn) {
        if (conn.getSourceAnchor() == null || conn.getTargetAnchor() == null) {
            return;
        }

        final ConnectionRouter connectionRouter = getSubRouter(conn);
        if (connectionRouter != null) {
            connectionRouter.remove(conn);
        }

        super.remove(conn);
    }

    /**
     * Utility method to retrieve the sub router that manages the individual
     * trees.
     * 
     * @param conn
     *            <code>Connection</code> to be routered
     * @return <code>TreeRouter</code> that will end up routing the given
     *         <code>Connection</code>.
     */
    public DTreeRouter getSubRouter(final Connection conn) {
        if (conn.getTargetAnchor() == null) {
            return null;
        }

        String hint = "base"; //$NON-NLS-1$
        if (conn instanceof ITreeConnection) {
            hint = ((ITreeConnection) conn).getHint();
        }

        final AnchorKey connectionKey = new AnchorKey(conn.getTargetAnchor(), hint);
        DTreeRouter connectionRouter = connections.get(connectionKey);
        if (connectionRouter == null) {
            DTreeRouter oldConnectionRouter = null;
            if (!"base".equals(hint)) { //$NON-NLS-1$
                // Search only with hint (if the targetAnchor has been moved but
                // the target is the same)
                oldConnectionRouter = getSubRouterWithHint(hint);
            }
            if (connectionRouter == null) {
                connectionRouter = new DTreeRouter();
                if (oldConnectionRouter != null) {
                    // Reuse the trunk location of the previous router to avoid
                    // to reset with the default trunk location
                    try {
                        connectionRouter.setTrunkLocation(conn, oldConnectionRouter.getTrunkLocation(conn));
                    } catch (NullPointerException e) {
                        // We are probably in reconnection, so the
                        // oldConnectionRouter has not already the connection in
                        // its list.
                        connectionRouter.setTrunkOrientation(oldConnectionRouter.getTrunkOrientation());
                        connectionRouter.setTrunkVertex(oldConnectionRouter.getTrunkVertex());
                    }
                }
                // remove connection of other routers after reconnectiontool
                for (Object obj : connections.values()) {
                    if (obj instanceof DTreeRouter) {
                        ((DTreeRouter) obj).remove(conn);
                    }
                }
            }
            connections.put(connectionKey, connectionRouter);
        }

        return connectionRouter;
    }

    /**
     * Search the router only on the hint of the ITreeConnection (usefull for
     * example if the targetAnchor has been moved on the same target node and we
     * want to use the same router).
     * 
     * @param hint
     *            The hint about the connection which determines which tree this
     *            connection will be contributed to
     * @return <code>TreeRouter</code> that will end up routing the given
     *         <code>Connection</code>.
     */
    private DTreeRouter getSubRouterWithHint(String hint) {
        for (AnchorKey key : connections.keySet()) {
            if (hint.equals(key.getQualifier())) {
                return connections.get(key);
            }
        }
        return null;
    }

    @Override
    public void route(final Connection conn) {
        internalRoute(conn);
    }

    private void internalRoute(final Connection conn) {
        if (conn != null) {
            if (conn.getTargetAnchor().getOwner() == null || conn.getSourceAnchor().getOwner() == null) {
                final PointList points = conn.getPoints();
                points.removeAllPoints();

                final Point delta = getFreeformViewport(conn).getViewLocation();

                final Point ref1 = conn.getTargetAnchor().getReferencePoint().getCopy();
                // conn.getTargetAnchor().getOwner().translateToAbsolute(ref1);
                final Point ref2 = conn.getSourceAnchor().getReferencePoint().getCopy();
                // conn.getSourceAnchor().getOwner().translateToAbsolute(ref2);
                final PrecisionPoint precisePt = new PrecisionPoint();

                precisePt.setLocation(conn.getSourceAnchor().getLocation(ref1).getTranslated(delta));
                points.addPoint(precisePt);

                precisePt.setLocation(conn.getTargetAnchor().getLocation(ref2).getTranslated(delta));
                points.addPoint(precisePt);
                conn.setPoints(points);
                return;
            }

            final DTreeRouter treeRouter = getSubRouter(conn);

            if (treeRouter != null) {

                // remove existing trunk vertex before routing occurs.
                Dimension trunk = treeRouter.getTrunkVertex();
                if (trunk != null) {
                    final AnchorKey trunkKey = new AnchorKey(conn.getTargetAnchor(), trunk);
                    trunkVertexes.remove(trunkKey);
                }

                treeRouter.route(conn);

                trunk = treeRouter.getTrunkVertex();
                final Dimension adjustedTrunk = accountForTrunkOverlap(trunk, conn);
                if (!adjustedTrunk.equals(trunk)) {
                    treeRouter.setTrunkVertex(adjustedTrunk);
                    treeRouter.invalidate(conn);
                }

            }
        }
    }

    /**
     * Returns the {@link FreeformViewport} that owned this figure.
     * 
     * @return the {@link FreeformViewport} that owned this figure.
     */
    private FreeformViewport getFreeformViewport(final IFigure figure) {
        IFigure current = figure;
        while (!(current instanceof FreeformViewport) && current != null) {
            current = current.getParent();
        }
        return (FreeformViewport) current;
    }

    /**
     * This method is copy/paste from
     * org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ForestRouter
     * 
     * Makes sure the routed tree doesn't intersect with an existing tree in the
     * "forest". This is called recursively for each trunk.
     * 
     * @param trunk
     *            <code>Dimension</code> trunkVertex value to compare
     * @param conn
     *            <code>Connection</code> that is connection currently being
     *            routed
     * @return <code>Dimension</code> new trunk vertex value
     */
    private Dimension accountForTrunkOverlap(final Dimension trunk, final Connection conn) {
        Dimension result = trunk;
        if (conn.getTargetAnchor() != null && conn.getTargetAnchor().getOwner() == null) {

            final AnchorKey trunkKey = new AnchorKey(conn.getTargetAnchor(), trunk);

            // check if trunk vertex doesn't exist or if it exceeds a maximum
            // then
            // return.
            int ownerExt = conn.getTargetAnchor().getOwner().getBounds().width / 2;
            int trunkExt = trunk.width;

            if (conn instanceof ITreeConnection) {
                if (((ITreeConnection) conn).getOrientation() == ITreeConnection.Orientation.VERTICAL) {
                    ownerExt = conn.getTargetAnchor().getOwner().getBounds().height / 2;
                    trunkExt = trunk.height;
                }
            }

            if (trunkVertexes.get(trunkKey) == null || Math.abs(trunkExt) > ownerExt) {
                trunkVertexes.put(trunkKey, Boolean.TRUE);
                result = trunk;
            } else {
                final Dimension newTrunk = new Dimension(trunk);
                if (((ITreeConnection) conn).getOrientation() == ITreeConnection.Orientation.HORIZONTAL) {
                    newTrunk.expand(10, 0);
                } else {
                    newTrunk.expand(0, 10);
                }
                result = accountForTrunkOverlap(newTrunk, conn);
            }
        }
        return result;
    }

    /**
     * This class is copy/paste from
     * org.eclipse.gmf.runtime.draw2d.ui.internal.routers.ForestRouter
     * 
     * @author sshaw
     */
    private static class AnchorKey {

        private final ConnectionAnchor anchor;

        private final Object qualifier;

        AnchorKey(final ConnectionAnchor anchor, final Object qualifier) {
            this.anchor = anchor;
            this.qualifier = qualifier;
        }

        @Override
        public boolean equals(final Object object) {
            boolean isEqual = false;
            AnchorKey hashKey;

            if (object instanceof AnchorKey) {
                hashKey = (AnchorKey) object;
                final ConnectionAnchor hkA1 = hashKey.getAnchor();
                final Object hkA2 = hashKey.getQualifier();

                isEqual = hkA1.equals(anchor) && hkA2.equals(qualifier);
            }
            return isEqual;
        }

        /**
         * Accessor to retrieve the <code>ConnectionAnchor</code> that is stored
         * as part of the key.
         * 
         * @return the <code>ConnectionAnchor</code> that is used for the key.
         */
        public ConnectionAnchor getAnchor() {
            return anchor;
        }

        /**
         * Accessor to retrieve the qualifier object that is stored as part of
         * the key.
         * 
         * @return the <code>Object</code> that is designated the qualifier.
         */
        public Object getQualifier() {
            return qualifier;
        }

        @Override
        public int hashCode() {
            return anchor.hashCode() ^ qualifier.hashCode();
        }
    }

    @Override
    public void invalidate(final Connection conn) {
        if (conn != null && conn.getSourceAnchor() != null && conn.getTargetAnchor() != null) {
            super.invalidate(conn);
        }
    }

}
