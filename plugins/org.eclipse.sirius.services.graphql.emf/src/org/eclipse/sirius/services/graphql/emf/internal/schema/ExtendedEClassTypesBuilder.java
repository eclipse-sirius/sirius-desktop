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
package org.eclipse.sirius.services.graphql.emf.internal.schema;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLType;

/**
 * Used to transformed the definition of an EClass which is extended by other EClass(es).
 * 
 * @author sbegaudeau
 */
public class ExtendedEClassTypesBuilder {

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
    public ExtendedEClassTypesBuilder(EClass eClass, HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache) {
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
    public ExtendedEClassTypesBuilder nameProvider(INameProvider nameProvider) {
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
    public ExtendedEClassTypesBuilder eStructuralFeatureFilter(Function<EStructuralFeature, SiriusGraphQLFilterStatus> eStructuralFeatureFilter) {
        this.eStructuralFeatureFilter = eStructuralFeatureFilter;
        return this;
    }

    /**
     * Returns the GraphQL types.
     * 
     * @return The GraphQL type
     */
    public Set<GraphQLType> getTypes() {
        Set<GraphQLType> types = new LinkedHashSet<>();

        // @formatter:off
        GraphQLType interfaceType = new EClassInterfaceTypeBuilder(this.eClass, this.eDataTypeToOutputTypeCache)
                .nameProvider(this.nameProvider)
                .eStructuralFeatureFilter(this.eStructuralFeatureFilter)
                .getType();
        
        GraphQLObjectType objectType = new EClassObjectTypeBuilder(this.eClass, this.eDataTypeToOutputTypeCache)
                .nameProvider(this.nameProvider)
                .eStructuralFeatureFilter(this.eStructuralFeatureFilter)
                .getType();
        // @formatter:on

        types.add(interfaceType);
        types.add(objectType);

        return types;
    }

}
