/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo.
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;
import graphql.schema.TypeResolver;

/**
 * Used to transform the definition of an EClass into a GraphQL type.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLEClassTypesBuilder implements ISiriusGraphQLTypesBuilder {

    /**
     * The suffix of the implementation type.
     */
    private static final String IMPL_SUFFIX = "Impl"; //$NON-NLS-1$

    /**
     * The EClass.
     */
    private EClass eClass;

    /**
     * The cache of the EDataType to GraphQL Scalars.
     */
    private HashMap<EDataType, GraphQLScalarType> eDataTypeToScalarCache;

    /**
     * The constructor.
     *
     * @param eClass
     *            The EClass
     * @param eDataTypeToScalarCache
     *            The cache of the EDataType to GraphQL Scalars
     */
    public SiriusGraphQLEClassTypesBuilder(EClass eClass, HashMap<EDataType, GraphQLScalarType> eDataTypeToScalarCache) {
        this.eClass = eClass;
        this.eDataTypeToScalarCache = eDataTypeToScalarCache;
    }

    @Override
    public Set<GraphQLType> getTypes() {
        Set<GraphQLType> types = new LinkedHashSet<>();

        GraphQLInterfaceType interfaceType = this.buildInterfaceType();
        types.add(interfaceType);

        if (!this.eClass.isAbstract() && !this.eClass.isInterface()) {
            types.add(this.buildObjectType(interfaceType));
        }

        return types;
    }

    /**
     * Creates the GraphQL interface representing the EClass.
     *
     * @return The GraphQL interface representing the EClass.
     */
    private GraphQLInterfaceType buildInterfaceType() {
        // @formatter:off
        return GraphQLInterfaceType.newInterface()
                .name(this.eClass.getName())
                .fields(this.getFields())
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
                .map(EClass::getName)
                .map(name -> environment.getSchema().getObjectType(name + IMPL_SUFFIX)) 
                .orElse(null);
        // @formatter:on
    }

    /**
     * Creates the GraphQL type representing the EClass.
     *
     * @param interfaceType
     *            The type of the matching GraphQL interface
     * @return The GraphQL type representing the EClass
     */
    private GraphQLObjectType buildObjectType(GraphQLInterfaceType interfaceType) {
        // @formatter:off
        return GraphQLObjectType.newObject()
                .name(this.eClass.getName() + IMPL_SUFFIX) 
                .fields(this.getFields())
                .withInterface(interfaceType)
                .withInterfaces(this.getInterfaces())
                .withInterface(new GraphQLTypeReference(SiriusGraphQLEObjectTypesBuilder.EOBJECT_TYPE))
                .build();
        // @formatter:on
    }

    /**
     * Returns the interfaces implemented by the EClass.
     *
     * @return The interfaces implemented by the EClass
     */
    private GraphQLTypeReference[] getInterfaces() {
        // @formatter:off
        return this.eClass.getEAllSuperTypes().stream()
                .map(EClass::getName)
                .map(GraphQLTypeReference::new)
                .toArray(GraphQLTypeReference[]::new);
        // @formatter:on
    }

    /**
     * Returns the fields of the EClass.
     *
     * @return The fields of the EClass
     */
    private List<GraphQLFieldDefinition> getFields() {
        List<GraphQLFieldDefinition> fields = new ArrayList<>();

        fields.add(SiriusGraphQLEObjectFragmentField.build());

        // @formatter:off
        this.eClass.getEAllStructuralFeatures().stream()
                .filter(this::isSupported)
                .map(this::getField)
                .forEach(fields::add);
        // @formatter:on

        return fields;
    }

    /**
     * Indicates if the given EStructuralFeature is supported.
     *
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return <code>true</code> if the EStructuralFeature is supported, <code>false</code> otherwise
     */
    private boolean isSupported(EStructuralFeature eStructuralFeature) {
        boolean isSupported = eStructuralFeature instanceof EAttribute && this.getScalar(((EAttribute) eStructuralFeature).getEAttributeType()) != null;
        isSupported = isSupported || eStructuralFeature instanceof EReference;
        return isSupported;
    }

    /**
     * Returns the field for the given EStructuralFeature.
     *
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return The field for the given EStructuralFeature
     */
    private GraphQLFieldDefinition getField(EStructuralFeature eStructuralFeature) {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(eStructuralFeature.getName())
                .type(this.getType(eStructuralFeature))
                .build();
        // @formatter:on
    }

    /**
     * Returns the type of the given EStructuralFeature.
     *
     * @param eStructuralFeature
     *            The EStructuralFeature
     * @return The type of the given EStructuralFeature
     */
    private GraphQLOutputType getType(EStructuralFeature eStructuralFeature) {
        GraphQLOutputType type = null;
        if (eStructuralFeature instanceof EAttribute) {
            EAttribute eAttribute = (EAttribute) eStructuralFeature;
            type = this.getScalar(eAttribute.getEAttributeType());

            if (Scalars.GraphQLBoolean.equals(type) || Scalars.GraphQLInt.equals(type)) {
                type = new GraphQLNonNull(type);
            }
        } else if (eStructuralFeature instanceof EReference) {
            EReference eReference = (EReference) eStructuralFeature;
            if (eReference.isMany()) {
                type = new GraphQLNonNull(new GraphQLList(new GraphQLNonNull(new GraphQLTypeReference(eReference.getEReferenceType().getName()))));
            } else {
                type = new GraphQLTypeReference(eReference.getEReferenceType().getName());
            }
        }
        return type;
    }

    /**
     * Returns the scalar matching the given EDataType.
     *
     * @param eDataType
     *            The EDataType
     * @return The scalar matching the given EDataType
     */
    private GraphQLOutputType getScalar(EDataType eDataType) {
        GraphQLOutputType type = null;

        if (EcorePackage.eINSTANCE.getEBigDecimal().equals(eDataType)) {
            type = Scalars.GraphQLBigDecimal;
        } else if (EcorePackage.eINSTANCE.getEBigInteger().equals(eDataType)) {
            type = Scalars.GraphQLBigInteger;
        } else if (EcorePackage.eINSTANCE.getEBoolean().equals(eDataType)) {
            type = Scalars.GraphQLBoolean;
        } else if (EcorePackage.eINSTANCE.getEByte().equals(eDataType)) {
            type = Scalars.GraphQLByte;
        } else if (EcorePackage.eINSTANCE.getEChar().equals(eDataType)) {
            type = Scalars.GraphQLChar;
        } else if (EcorePackage.eINSTANCE.getEFloat().equals(eDataType)) {
            type = Scalars.GraphQLFloat;
        } else if (EcorePackage.eINSTANCE.getEInt().equals(eDataType)) {
            type = Scalars.GraphQLInt;
        } else if (EcorePackage.eINSTANCE.getELong().equals(eDataType)) {
            type = Scalars.GraphQLLong;
        } else if (EcorePackage.eINSTANCE.getEShort().equals(eDataType)) {
            type = Scalars.GraphQLShort;
        } else if (EcorePackage.eINSTANCE.getEString().equals(eDataType)) {
            type = Scalars.GraphQLString;
        } else {
            GraphQLScalarType graphQLScalarType = this.eDataTypeToScalarCache.get(eDataType);
            if (graphQLScalarType == null) {
                graphQLScalarType = new SiriusGraphQLEDataTypeScalarBuilder(eDataType).build();
                this.eDataTypeToScalarCache.put(eDataType, graphQLScalarType);
            }
            type = graphQLScalarType;
        }

        return type;
    }
}
