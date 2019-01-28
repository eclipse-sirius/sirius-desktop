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

import java.util.Set;

import graphql.schema.GraphQLType;

/**
 * Common interfaces of the types builders.
 * 
 * @author sbegaudeau
 */
public interface ISiriusGraphQLTypesProvider {
    /**
     * Creates the GraphQL types.
     * 
     * @param customizer
     *            Used to customize the creation of the types
     * @return The created types
     */
    Set<GraphQLType> getTypes(ISiriusGraphQLTypeCustomizer customizer);
}
