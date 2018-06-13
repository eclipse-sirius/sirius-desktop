/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.graphql.internal.entities;

/**
 * The Edge object used for Relay connections.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLEdge {
    /**
     * The node.
     */
    private Object node;

    /**
     * The cursor.
     */
    private String cursor;

    /**
     * The constructor.
     * 
     * @param node
     *            The node
     * @param cursor
     *            The cursor
     */
    public SiriusGraphQLEdge(Object node, String cursor) {
        this.node = node;
        this.cursor = cursor;
    }

    /**
     * Returns the node.
     *
     * @return the node
     */
    public Object getNode() {
        return this.node;
    }

    /**
     * Returns the cursor.
     *
     * @return the cursor
     */
    public String getCursor() {
        return this.cursor;
    }
}
