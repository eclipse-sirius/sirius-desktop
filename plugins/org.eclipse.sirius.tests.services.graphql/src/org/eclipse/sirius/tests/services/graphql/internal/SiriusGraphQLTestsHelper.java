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
package org.eclipse.sirius.tests.services.graphql.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult;
import org.eclipse.sirius.services.graphql.api.SiriusGraphQLInterpreter;

import graphql.schema.GraphQLSchema;

/**
 * Utility class used to help writing GraphQL tests.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLTestsHelper {
    /**
     * The data field of the result.
     */
    private static final String DATA = "data"; //$NON-NLS-1$

    /**
     * The schema field of the result.
     */
    private static final String SCHEMA = "__schema"; //$NON-NLS-1$

    /**
     * The types field of the result.
     */
    private static final String TYPES = "types"; //$NON-NLS-1$

    /**
     * The name field of the result.
     */
    private static final String NAME = "name"; //$NON-NLS-1$

    /**
     * Returns the introspected version of the given GraphQL schema.
     * 
     * @param schema
     *            The GraphQL schema
     * @return The introspected version of the given GraphQL schema
     */
    public ISiriusGraphQLQueryResult getSchema(GraphQLSchema schema) {
        String query = SiriusGraphQLTestsMessages.introspectionQuery;
        Map<String, Object> variables = new HashMap<>();
        String operationName = ""; //$NON-NLS-1$
        Object context = null;
        return new SiriusGraphQLInterpreter(schema).execute(query, variables, operationName, context);
    }

    /**
     * Returns the types of the GraphQL schema from the introspection result.
     * 
     * @param result
     *            The introspection result
     * @return The types of the GraphQL schema from the introspection result
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findTypes(ISiriusGraphQLQueryResult result) {
        Map<String, Object> rawData = result.getData();
        Map<String, Object> data = (Map<String, Object>) rawData.get(DATA);
        Map<String, Object> schema = (Map<String, Object>) data.get(SCHEMA);
        return (List<Map<String, Object>>) schema.get(TYPES);
    }

    /**
     * Test that the definition of the type retrieved matches the expected definition.
     * 
     * @param result
     *            The introspection result
     * @param expected
     *            The expected definition
     * @param typeName
     *            The name of the type
     */
    public void testType(ISiriusGraphQLQueryResult result, String expected, String typeName) {
        List<Map<String, Object>> types = new SiriusGraphQLTestsHelper().findTypes(result);

        Optional<Map<String, Object>> optionalType = types.stream().filter(type -> typeName.equals(type.get(NAME))).findFirst();
        if (optionalType.isPresent()) {
            Map<String, Object> type = optionalType.get();
            String typeString = new SiriusGraphQLTypeSerializer().typeToString(type);
            assertEquals(expected, typeString);
        } else {
            fail("The " + typeName + " type has not been found in the schema"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }
}
