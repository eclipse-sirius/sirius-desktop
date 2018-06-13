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
package org.eclipse.sirius.services.graphql.api;

import java.util.Map;

import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.internal.schema.SiriusGraphQLSchemaBuilder;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

/**
 * The GraphQL interpreter.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLInterpreter {

    /**
     * The schema.
     */
    private GraphQLSchema schema;

    /**
     * The constructor.
     */
    public SiriusGraphQLInterpreter() {
        this.schema = new SiriusGraphQLSchemaBuilder().build();
    }

    /**
     * Executes the given query.
     *
     * @param query
     *            The query
     * @param variables
     *            The variables
     * @param operationName
     *            The name of the operation
     * @param context
     *            The context of the execution
     * @return The result of the execution
     */
    public ISiriusGraphQLQueryResult execute(String query, Map<String, Object> variables, String operationName, Object context) {
        // @formatter:off
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .variables(variables)
                .operationName(operationName)
                .context(context)
                .build();

        GraphQL graphQL = GraphQL.newGraphQL(this.schema)
                .build();
        // @formatter:on

        ExecutionResult executionResult = graphQL.execute(executionInput);

        return new SiriusGraphQLQueryResult(executionResult);
    }
}
