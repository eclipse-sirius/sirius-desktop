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
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypesProvider;
import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLFilterStatus;
import org.eclipse.sirius.services.graphql.emf.internal.schema.INameProvider.NameKind;

import graphql.schema.GraphQLOutputType;
import graphql.schema.GraphQLType;

/**
 * Used to create all the GraphQL type definitions for a metamodel.
 *
 * @author sbegaudeau
 */
public class EPackageTypesProvider implements ISiriusGraphQLTypesProvider {

    /**
     * The suffix of the implementation type.
     */
    private static final String IMPL_SUFFIX = "Impl"; //$NON-NLS-1$

    /**
     * The EPackage.
     */
    private EPackage ePackage;

    /**
     * The cache of the EDataType to GraphQL output type.
     */
    private HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache = new HashMap<>();

    /**
     * The filter used to select the EClassifier to consider.
     */
    private Function<EClassifier, SiriusGraphQLFilterStatus> eClassifierFilter = eClassifier -> {
        if (EcorePackage.eINSTANCE.getEObject().equals(eClassifier)) {
            return SiriusGraphQLFilterStatus.REJECT;
        }
        return SiriusGraphQLFilterStatus.KEEP;
    };

    /**
     * The name provider.
     */
    private INameProvider nameProvider = (anEClass, nameKind) -> {
        if (this.isExtendedClass(anEClass) && NameKind.TYPE.equals(nameKind)) {
            return anEClass.getName() + IMPL_SUFFIX;
        }
        return anEClass.getName();
    };

    /**
     * The EStructuralFeature filter.
     */
    private Function<EStructuralFeature, SiriusGraphQLFilterStatus> eStructuralFeatureFilter = eStructuralFeature -> {
        boolean shouldBeRejected = EcorePackage.eINSTANCE.getEJavaClass().equals(eStructuralFeature.getEType());
        shouldBeRejected = shouldBeRejected || EcorePackage.eINSTANCE.getEJavaObject().equals(eStructuralFeature.getEType());
        shouldBeRejected = shouldBeRejected || EcorePackage.eINSTANCE.getEFeatureMap().equals(eStructuralFeature.getEType());
        shouldBeRejected = shouldBeRejected || EcorePackage.eINSTANCE.getEFeatureMapEntry().equals(eStructuralFeature.getEType());
        if (shouldBeRejected) {
            return SiriusGraphQLFilterStatus.REJECT;
        }
        return SiriusGraphQLFilterStatus.KEEP;
    };

    /**
     * The constructor.
     *
     * @param ePackage
     *            The EPackage
     */
    public EPackageTypesProvider(EPackage ePackage) {
        this.ePackage = ePackage;
    }

    /**
     * Sets the cache of the EDataType to GraphQL output type.
     * 
     * @param eDataTypeToOutputTypeCache
     *            The cache
     * @return The current builder
     */
    @SuppressWarnings({ "checkstyle:HiddenField" })
    public EPackageTypesProvider cache(HashMap<EDataType, GraphQLOutputType> eDataTypeToOutputTypeCache) {
        this.eDataTypeToOutputTypeCache = eDataTypeToOutputTypeCache;
        return this;
    }

    /**
     * Sets the filter used to select the EClassifier to consider.
     * 
     * @param eClassifierFilter
     *            the filter
     * @return The current builder
     */
    @SuppressWarnings({ "checkstyle:HiddenField" })
    public EPackageTypesProvider eClassifierFilter(Function<EClassifier, SiriusGraphQLFilterStatus> eClassifierFilter) {
        this.eClassifierFilter = eClassifierFilter;
        return this;
    }

    /**
     * Sets the name provider.
     * 
     * @param nameProvider
     *            The name provider
     * @return The current builder
     */
    @SuppressWarnings({ "checkstyle:HiddenField" })
    public EPackageTypesProvider nameProvider(INameProvider nameProvider) {
        this.nameProvider = nameProvider;
        return this;
    }

    @Override
    public Set<GraphQLType> getTypes(ISiriusGraphQLTypeCustomizer customizer) {
        Set<GraphQLType> types = new LinkedHashSet<>();

        // @formatter:off
        Stream<EClass> eClassStream = this.ePackage.getEClassifiers().stream()
                .filter(eClassifier -> SiriusGraphQLFilterStatus.KEEP.equals(this.eClassifierFilter.apply(eClassifier)))
                .filter(EClass.class::isInstance)
                .map(EClass.class::cast);
        
        eClassStream.forEach(eClass -> {
            if (eClass.isAbstract() || eClass.isInterface()) {
                types.add(this.getInterfaceType(eClass));
            } else if (this.isExtendedClass(eClass)) {
                types.addAll(this.getExtendedClassTypes(eClass));
            } else {
                types.add(this.getEClassType(eClass));
            }
        });
        // @formatter:on

        return types;
    }

    /**
     * Returns the interface type.
     * 
     * @param eClass
     *            The EClass
     * @return The interface types
     */
    private GraphQLType getInterfaceType(EClass eClass) {
        // @formatter:off
        return new EClassInterfaceTypeBuilder(eClass, this.eDataTypeToOutputTypeCache)
                .nameProvider(this.nameProvider)
                .eStructuralFeatureFilter(this.eStructuralFeatureFilter)
                .getType();
        // @formatter:on
    }

    /**
     * Returns the extended class types.
     * 
     * @param eClass
     *            The EClass
     * @return The extended class types
     */
    private Set<GraphQLType> getExtendedClassTypes(EClass eClass) {
        // @formatter:off
        return new ExtendedEClassTypesBuilder(eClass, this.eDataTypeToOutputTypeCache)
                .nameProvider(this.nameProvider)
                .eStructuralFeatureFilter(this.eStructuralFeatureFilter)
                .getTypes();
        // @formatter:on
    }

    /**
     * Returns the EClass type.
     * 
     * @param eClass
     *            The EClass
     * @return The EClass type
     */
    private GraphQLType getEClassType(EClass eClass) {
        // @formatter:off
        return new EClassObjectTypeBuilder(eClass, this.eDataTypeToOutputTypeCache)
                .nameProvider(this.nameProvider)
                .eStructuralFeatureFilter(this.eStructuralFeatureFilter)
                .getType();
        // @formatter:on
    }

    /**
     * Indicates if the EClass is extended by another EClass.
     * 
     * @return <code>true</code> if the EClass is extended by another EClass, <code>false</code> otherwise
     */
    private boolean isExtendedClass(EClass eClass) {
        // @formatter:off
        return eClass.getEPackage().getEClassifiers().stream()
                .filter(EClass.class::isInstance)
                .map(EClass.class::cast)
                .filter(anEClass -> anEClass.getESuperTypes().contains(eClass))
                .findFirst()
                .isPresent();
        // @formatter:on
    }

}
