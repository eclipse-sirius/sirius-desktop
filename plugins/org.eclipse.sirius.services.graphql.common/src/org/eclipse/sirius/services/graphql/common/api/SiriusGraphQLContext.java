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
package org.eclipse.sirius.services.graphql.common.api;

import java.text.MessageFormat;

import org.eclipse.sirius.services.graphql.common.internal.SiriusGraphQLCommonMessages;

/**
 * The context of the GraphQL requests.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLContext {

    /**
     * The current cost.
     */
    private int cost;

    /**
     * The maximal cost allowed.
     */
    private int maxCost;

    /**
     * The constructor.
     *
     * @param maxCost
     *            The maximal cost allowed
     */
    public SiriusGraphQLContext(int maxCost) {
        this.maxCost = maxCost;
    }

    /**
     * Adds the new cost to the current cost.
     *
     * @param newCost
     *            The new cost
     */
    public void add(int newCost) {
        this.cost = this.cost + newCost;
        if (this.cost > maxCost) {
            String message = SiriusGraphQLCommonMessages.SiriusGraphQLContext_requestTooExpensive;
            String formattedMessage = MessageFormat.format(message, this.cost, maxCost);
            throw new IllegalArgumentException(formattedMessage);
        }
    }
}
