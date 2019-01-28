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
package org.eclipse.sirius.services.graphql.emf.internal.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to compute the fields of an EClass.
 * 
 * @author sbegaudeau
 */
public class FieldsBuilder {

    /**
     * The EClass.
     */
    private EClass eClass;

    /**
     * The cache of the EDataType to GraphQL output type.
     */
    private HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache;

    /**
     * The EStructuralFeature filter.
     */
    private Function<EStructuralFeature, SiriusGraphQLFilterStatus> eStructuralFeatureFilter = eStructuralFeature -> SiriusGraphQLFilterStatus.KEEP;

    /**
     * The constructor.
     * 
     * @param eClass
     *            The EClass
     * @param eDataTypeToOutputTypeCache
     *            The cache of the EDataType to GraphQL output type
     */
    public FieldsBuilder(EClass eClass, HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache) {
        this.eClass = eClass;
        this.eDataTypeToOutputTypeCache = eDataTypeToOutputTypeCache;
    }

    /**
     * Sets the EStructuralFeature filter.
     * 
     * @param eStructuralFeatureFilter
     *            The EStructuralFeature
     * @return The current builder
     */
    @SuppressWarnings({ "checkstyle:HiddenField" })
    public FieldsBuilder eStructuralFeatureFilter(Function<EStructuralFeature, SiriusGraphQLFilterStatus> eStructuralFeatureFilter) {
        this.eStructuralFeatureFilter = eStructuralFeatureFilter;
        return this;
    }

    /**
     * Returns the list of fields.
     * 
     * @return The lsit of fields
     */
    public List<GraphQLFieldDefinition> getFields() {
        List<GraphQLFieldDefinition> fields = new ArrayList<>();

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
        boolean isSupported = eStructuralFeature instanceof EAttribute;
        isSupported = isSupported && SiriusGraphQLFilterStatus.KEEP == this.eStructuralFeatureFilter.apply(eStructuralFeature);
        isSupported = isSupported && this.getScalar(((EAttribute) eStructuralFeature).getEAttributeType()) != null;
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
            type = this.eDataTypeToOutputTypeCache.get(eDataType);
            this.eDataTypeToOutputTypeCache.computeIfAbsent(eDataType, (dataType) -> {
                GraphQLOutputType graphQLOutputType = null;
                if (dataType instanceof EEnum) {
                    EEnum eEnum = (EEnum) dataType;
                    graphQLOutputType = new EEnumTypeBuilder(eEnum).getType();
                } else {
                    graphQLOutputType = new EStructuralFeatureScalarTypeBuilder(eDataType).getType();
                }
                return graphQLOutputType;
            });
        }

        return type;
    }

}
