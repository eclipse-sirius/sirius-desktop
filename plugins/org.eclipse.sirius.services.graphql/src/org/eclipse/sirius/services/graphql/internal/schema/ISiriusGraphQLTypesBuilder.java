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
