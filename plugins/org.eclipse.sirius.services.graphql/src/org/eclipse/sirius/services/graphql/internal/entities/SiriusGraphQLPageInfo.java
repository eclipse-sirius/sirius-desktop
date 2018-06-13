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
