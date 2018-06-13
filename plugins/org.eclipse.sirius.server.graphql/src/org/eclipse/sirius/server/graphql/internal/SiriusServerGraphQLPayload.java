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
