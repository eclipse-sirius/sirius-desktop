/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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
package org.eclipse.sirius.services.graphql.api;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLContext;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLMessages;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.internal.schema.SiriusGraphQLSchemaProvider;

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
     * The constant used to set a custom value for the max cost allowed in a GraphQL query.
     */
    private static final String MAX_COST = "org.eclipse.sirius.services.graphql.cost"; //$NON-NLS-1$

    /**
     * The maximal cost allowed for a GraphQL query.
     */
    private static final int DEFAULT_MAX_COST = 100;

    /**
     * The schema.
     */
    private GraphQLSchema schema;

    /**
     * The constructor.
     */
    public SiriusGraphQLInterpreter() {
        this(new SiriusGraphQLSchemaProvider().getSchema());
    }

    /**
     * The constructor.
     * 
     * @param schema
     *            The GraphQL schema to use
     */
    public SiriusGraphQLInterpreter(GraphQLSchema schema) {
        this.schema = schema;
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
                .context(new SiriusGraphQLContext(this.getMaxCost()))
                .build();

        GraphQL graphQL = GraphQL.newGraphQL(this.schema)
                .build();
        // @formatter:on

        ExecutionResult executionResult = graphQL.execute(executionInput);

        return new SiriusGraphQLQueryResult(executionResult);
    }

    /**
     * Returns the maximum cost of a GraphQL query.
     * 
     * @return The maximum cost of a GraphQL query
     */
    private int getMaxCost() {
        int value = DEFAULT_MAX_COST;
        String propertyValue = System.getProperty(MAX_COST);
        if (propertyValue != null && propertyValue.length() > 0) {
            try {
                value = Integer.parseInt(propertyValue);
            } catch (NumberFormatException exception) {
                String message = String.format(SiriusGraphQLMessages.SiriusGraphQLInterpreter_wrongPropertyTypeWarning, propertyValue, DEFAULT_MAX_COST);
                IStatus status = new Status(IStatus.ERROR, SiriusGraphQLPlugin.PLUGIN_ID, message, exception);
                SiriusGraphQLPlugin.getPlugin().getLog().log(status);
            }
        }
        return value;
    }
}
