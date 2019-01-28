/*******************************************************************************
 * Copyright (c) 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.common.api.pagination;

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
