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

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;
import org.eclipse.sirius.services.graphql.emf.internal.schema.INameProvider.NameKind;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLType;
import graphql.schema.TypeResolver;

/**
 * Used to transform the definition of an abstract or interface EClass.
 * 
 * @author sbegaudeau
 */
public class EClassInterfaceTypeBuilder {

    /**
     * The EClass.
     */
    private EClass eClass;

    /**
     * The cache of the EDataType to GraphQL output type.
     */
    private HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache;

    /**
     * The name provider.
     */
    private INameProvider nameProvider = (anEClass, nameKind) -> anEClass.getName();

    /**
     * The EStructuralFeature filter.
     */
    private Function<EStructuralFeature, SiriusGraphQLFilterStatus> eStructuralFeatureFilter;

    /**
     * The constructor.
     * 
     * @param eClass
     *            The EClass
     * @param eDataTypeToOutputTypeCache
     *            The cache of the EDataType to GraphQL output type
     */
    public EClassInterfaceTypeBuilder(EClass eClass, HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache) {
        this.eClass = eClass;
        this.eDataTypeToOutputTypeCache = eDataTypeToOutputTypeCache;
    }

    /**
     * Sets the name provider.
     * 
     * @param nameProvider
     *            The name provider
     * @return The current builder
     */
    @SuppressWarnings({ "checkstyle:HiddenField" })
    public EClassInterfaceTypeBuilder nameProvider(INameProvider nameProvider) {
        this.nameProvider = nameProvider;
        return this;
    }

    /**
     * Sets the EStructuralFeature filter.
     * 
     * @param eStructuralFeatureFilter
     *            The EStructuralFeature
     * @return The current builder
     */
    @SuppressWarnings({ "checkstyle:HiddenField" })
    public EClassInterfaceTypeBuilder eStructuralFeatureFilter(Function<EStructuralFeature, SiriusGraphQLFilterStatus> eStructuralFeatureFilter) {
        this.eStructuralFeatureFilter = eStructuralFeatureFilter;
        return this;
    }

    /**
     * Returns the GraphQL type.
     * 
     * @return The GraphQL type
     */
    public GraphQLType getType() {
     // @formatter:off
        return GraphQLInterfaceType.newInterface()
                .name(this.nameProvider.getName(this.eClass, NameKind.INTERFACE))
                .fields(new FieldsBuilder(this.eClass, this.eDataTypeToOutputTypeCache)
                        .eStructuralFeatureFilter(this.eStructuralFeatureFilter)
                        .getFields())
                .typeResolver(this.getTypeResolver())
                .build();
        // @formatter:on
    }

    /**
     * Returns the type resolver.
     *
     * @return The type resolver
     */
    private TypeResolver getTypeResolver() {
        // @formatter:off
        return environment -> Optional.of(environment.getObject())
                .filter(EObject.class::isInstance)
                .map(EObject.class::cast)
                .map(EObject::eClass)
                .map(anEClass -> this.nameProvider.getName(anEClass, NameKind.TYPE))
                .map(name -> environment.getSchema().getObjectType(name))
                .orElse(null);
        // @formatter:on
    }

}
