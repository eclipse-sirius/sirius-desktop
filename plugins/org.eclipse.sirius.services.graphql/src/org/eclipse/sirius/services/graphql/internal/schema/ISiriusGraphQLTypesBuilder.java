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
package org.eclipse.sirius.services.graphql.internal.schema;

import java.util.Set;

import graphql.schema.GraphQLType;

/**
 * Interface used by the contributors of the GraphQL schema which create types.
 *
 * @author sbegaudeau
 */
public interface ISiriusGraphQLTypesBuilder {
    /**
     * Returns the types created.
     *
     * @return The types created
     */
    Set<GraphQLType> getTypes();
}
