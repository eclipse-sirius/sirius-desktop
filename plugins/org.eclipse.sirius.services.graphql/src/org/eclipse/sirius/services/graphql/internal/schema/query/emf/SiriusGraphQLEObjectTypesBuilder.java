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
package org.eclipse.sirius.services.graphql.internal.schema.query.emf;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLType;
import graphql.schema.TypeResolver;

/**
 * Used to create the EObject type.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLEObjectTypesBuilder implements ISiriusGraphQLTypesBuilder {

    /**
     * The name of the EObject type.
     */
    public static final String EOBJECT_TYPE = "EObject"; //$NON-NLS-1$

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLType eObject = GraphQLInterfaceType.newInterface()
                .name(EOBJECT_TYPE)
                .field(SiriusGraphQLEObjectFragmentField.build())
                .typeResolver(this.getTypeResolver())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(eObject);
        return types;
    }

    /**
     * Returns the type resolver.
     * 
     * @return The type resolver
     */
    private TypeResolver getTypeResolver() {
        // @formatter:off
        return environment -> {
            Optional<EClass> optionalEClass = Optional.of(environment.getObject())
                    .filter(EObject.class::isInstance)
                    .map(EObject.class::cast)
                    .map(EObject::eClass);
            
            return optionalEClass.map(EClass::getName)
                    .map(environment.getSchema()::getObjectType)
                    .orElse(null);
        };
        // @formatter:on
    }

}
