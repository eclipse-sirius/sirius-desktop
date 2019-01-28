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
 * The PageInfo object used for Relay connections.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLPageInfo {

    /**
     * Indicates if the connection has a previous page.
     */
    private boolean hasPreviousPage;

    /**
     * Indicates if the connection has a next page.
     */
    private boolean hasNextPage;

    /**
     * The constructor.
     * 
     * @param hasPreviousPage
     *            Indicates if the connection has a previous page
     * @param hasNextPage
     *            Indicates if the connection has a next page
     */
    public SiriusGraphQLPageInfo(boolean hasPreviousPage, boolean hasNextPage) {
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
    }

    /**
     * Return the hasPreviousPage.
     *
     * @return the hasPreviousPage
     */
    public boolean isHasPreviousPage() {
        return this.hasPreviousPage;
    }

    /**
     * Return the hasNextPage.
     *
     * @return the hasNextPage
     */
    public boolean isHasNextPage() {
        return this.hasNextPage;
    }
}
