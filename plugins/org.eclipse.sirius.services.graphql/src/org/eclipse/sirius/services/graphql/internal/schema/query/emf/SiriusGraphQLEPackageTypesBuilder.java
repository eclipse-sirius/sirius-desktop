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

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLType;

/**
 * Used to create all the GraphQL type definitions for a metamodel.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLEPackageTypesBuilder implements ISiriusGraphQLTypesBuilder {

    /**
     * The name of the EPackage type.
     */
    public static final String EPACKAGE_TYPE = "EPackage"; //$NON-NLS-1$

    /**
     * The EPackage.
     */
    private EPackage ePackage;

    /**
     * The cache of the EDataType to GraphQL Scalars.
     */
    private HashMap<EDataType, GraphQLScalarType> eDataTypeToScalarCache;

    /**
     * The constructor.
     *
     * @param ePackage
     *            The EPackage
     * @param eDataTypeToScalarCache
     *            The cache of the EDataType to GraphQL Scalars
     */
    public SiriusGraphQLEPackageTypesBuilder(EPackage ePackage, HashMap<EDataType, GraphQLScalarType> eDataTypeToScalarCache) {
        this.ePackage = ePackage;
        this.eDataTypeToScalarCache = eDataTypeToScalarCache;
    }

    @Override
    public Set<GraphQLType> getTypes() {
        Set<GraphQLType> types = new LinkedHashSet<>();

        this.ePackage.getEClassifiers().forEach(eClassifier -> {
            if (eClassifier instanceof EClass) {
                EClass eClass = (EClass) eClassifier;
                types.addAll(new SiriusGraphQLEClassTypesBuilder(eClass, this.eDataTypeToScalarCache).getTypes());
            }
        });

        return types;
    }

}
