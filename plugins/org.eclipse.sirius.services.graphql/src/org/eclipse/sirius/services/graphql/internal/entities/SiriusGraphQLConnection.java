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

import java.util.ArrayList;
import java.util.List;

/**
 * A Relay connection for the GraphQL API.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLConnection {
    /**
     * The total count.
     */
    private int totalCount;

    /**
     * The edges.
     */
    private List<SiriusGraphQLEdge> edges = new ArrayList<>();

    /**
     * The page info.
     */
    private SiriusGraphQLPageInfo pageInfo;

    /**
     * The constructor.
     *
     * @param totalCount
     *            The total count
     * @param edges
     *            The edges
     * @param pageInfo
     *            The page info
     */
    public SiriusGraphQLConnection(int totalCount, List<SiriusGraphQLEdge> edges, SiriusGraphQLPageInfo pageInfo) {
        this.totalCount = totalCount;
        this.edges = edges;
        this.pageInfo = pageInfo;
    }

    /**
     * Return the totalCount.
     *
     * @return the totalCount
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    /**
     * Return the edges.
     *
     * @return the edges
     */
    public List<SiriusGraphQLEdge> getEdges() {
        return this.edges;
    }

    /**
     * Return the pageInfo.
     *
     * @return the pageInfo
     */
    public SiriusGraphQLPageInfo getPageInfo() {
        return this.pageInfo;
    }
}
