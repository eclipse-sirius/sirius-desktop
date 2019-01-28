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

import java.util.Collection;
import java.util.Optional;

/**
 * The GraphQL schema registry is used to retrieve all the GraphQL schema customizer registered.
 * 
 * @author sbegaudeau
 */
public interface ISiriusGraphQLSchemaRegistry {
    /**
     * Returns an optional containing the GraphQL schema customizer for the given identifier or an empty optional if
     * none was registered.
     * 
     * @param identifier
     *            The identifier
     * @return The optional containing the GraphQL schema customizer
     */
    Optional<ISiriusGraphQLSchemaCustomizer> get(String identifier);

    /**
     * Returns the collection of all the GraphQL schema customizer.
     * 
     * @return The collection of all the GraphQL schema customizer.
     */
    Collection<ISiriusGraphQLSchemaCustomizer> values();
}
