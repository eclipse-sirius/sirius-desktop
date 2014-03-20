/*******************************************************************************
 * Copyright (c) 2011, 2012 Obeo. All Rights Reserved.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo  - initial API and implementation
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.routers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketConnectionQuery;

/**
 * Routes Connections directly from the source anchor to the target anchor with
 * some bendpoints inside. Adapts bendpoints location from orientation and
 * offset.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketConnectionRouter extends AbstractRouter {

    /** The figure constraints for each {@link Connection}. */
    private Map<Connection, Object> constraints = new HashMap<Connection, Object>();

    /** A common {@link BracketConnectionQuery} to the source and target anchor. */
    private BracketConnectionQuery bracketConnectionQuery;

    /**
     * Constructs a new DimensionConnectionRouter.
     * 
     * @param bracketConnectionQuery
     *            {@link BracketConnectionQuery}
     */
    public BracketConnectionRouter(BracketConnectionQuery bracketConnectionQuery) {
        this.bracketConnectionQuery = bracketConnectionQuery;
    }

    /**
     * Sets the constraint for the given {@link Connection}.
     * 
     * @param connection
     *            The connection whose constraint we are setting
     * @param constraint
     *            The constraint
     */
    public void setConstraint(Connection connection, Object constraint) {
        constraints.put(connection, constraint);
    }

    /**
     * Gets the constraint for the given {@link Connection}.
     * 
     * @param connection
     *            The connection whose constraint we are retrieving
     * @return The constraint
     */
    public Object getConstraint(Connection connection) {
        return constraints.get(connection);
    }

    /**
     * Removes the given connection from the map of constraints.
     * 
     * @param connection
     *            The connection to remove
     */
    public void remove(Connection connection) {
        constraints.remove(connection);
    }

    /**
     * Routes the given Connection directly between the source and target
     * anchors.
     * 
     * @param connection
     *            the {@link Connection} to be routed
     */
    public void route(Connection connection) {
        final PointList newPointList = bracketConnectionQuery.getPointListFromConstraint();
        connection.setPoints(newPointList);
    }
}
