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
package org.eclipse.sirius.services.graphql.emf.internal.schema;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeProvider;
import org.eclipse.sirius.services.graphql.emf.api.EMFSchemaConstants;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLInterfaceType.Builder;
import graphql.schema.GraphQLType;
import graphql.schema.TypeResolver;

/**
 * Used to create the EObject type.
 * 
 * @author sbegaudeau
 */
public class EObjectTypeProvider implements ISiriusGraphQLTypeProvider {

    @Override
    public GraphQLType getType(ISiriusGraphQLTypeCustomizer customizer) {
        // @formatter:off
        Builder builder = GraphQLInterfaceType.newInterface()
                .name(EMFSchemaConstants.EOBJECT_TYPE)
                .typeResolver(this.getTypeResolver());
        // @formatter:on

        Builder customizedBuilder = customizer.customize(EMFSchemaConstants.EOBJECT_TYPE, builder);
        return customizedBuilder.build();
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
                    .orElse(environment.getSchema().getObjectType(EMFSchemaConstants.DYNAMICEOBJECT_TYPE));
        };
        // @formatter:on
    }

}
