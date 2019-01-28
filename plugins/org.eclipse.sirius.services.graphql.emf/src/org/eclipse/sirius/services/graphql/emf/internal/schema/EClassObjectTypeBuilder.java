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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;
import org.eclipse.sirius.services.graphql.emf.api.EMFSchemaConstants;
import org.eclipse.sirius.services.graphql.emf.internal.schema.INameProvider.NameKind;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to transform the definition of an EClass into a GraphQL type.
 *
 * @author sbegaudeau
 */
public class EClassObjectTypeBuilder {

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
    public EClassObjectTypeBuilder(EClass eClass, HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache) {
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
    public EClassObjectTypeBuilder nameProvider(INameProvider nameProvider) {
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
    public EClassObjectTypeBuilder eStructuralFeatureFilter(Function<EStructuralFeature, SiriusGraphQLFilterStatus> eStructuralFeatureFilter) {
        this.eStructuralFeatureFilter = eStructuralFeatureFilter;
        return this;
    }

    /**
     * Returns the GraphQL type.
     * 
     * @return The GraphQL type
     */
    public GraphQLObjectType getType() {
        GraphQLTypeReference[] interfaces = this.getInterfaces();

        // @formatter:off
        return GraphQLObjectType.newObject()
                .name(this.nameProvider.getName(this.eClass, NameKind.TYPE)) 
                .fields(new FieldsBuilder(this.eClass, this.eDataTypeToOutputTypeCache)
                        .eStructuralFeatureFilter(this.eStructuralFeatureFilter)
                        .getFields())
                .withInterfaces(interfaces)
                .withInterface(new GraphQLTypeReference(EMFSchemaConstants.EOBJECT_TYPE))
                .build();
        // @formatter:on
    }

    /**
     * Returns the interfaces implemented by the EClass.
     *
     * @return The interfaces implemented by the EClass
     */
    private GraphQLTypeReference[] getInterfaces() {
        List<EClass> eAllSuperTypes = new ArrayList<>(this.eClass.getEAllSuperTypes());
        Collections.reverse(eAllSuperTypes);

        // @formatter:off
        return eAllSuperTypes.stream()
                .map(EClass::getName)
                .map(GraphQLTypeReference::new)
                .toArray(GraphQLTypeReference[]::new);
        // @formatter:on
    }
}
