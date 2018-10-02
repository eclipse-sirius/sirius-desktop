/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.server.graphql.internal;

import java.util.HashMap;
import java.util.Map;

/**
 * The GraphQL payload to execute.
 *
 * @author sbegaudeau
 */
public class SiriusServerGraphQLPayload {
    /**
     * The query.
     */
    private String query;

    /**
     * The variables
     */
    private Map<String, Object> variables = new HashMap<>();

    /**
     * The name of the operation.
     */
    private String operationName;

    /**
     * Return the query.
     *
     * @return the query
     */
    public String getQuery() {
        return this.query;
    }

    /**
     * Return the variables.
     *
     * @return the variables
     */
    public Map<String, Object> getVariables() {
        return this.variables;
    }

    /**
     * Return the operationName.
     *
     * @return the operationName
     */
    public String getOperationName() {
        return this.operationName;
    }
}
