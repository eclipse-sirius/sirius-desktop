/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.services.graphql.internal.schema.query.emf;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;

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
     * The constructor.
     *
     * @param ePackage
     *            The EPackage
     */
    public SiriusGraphQLEPackageTypesBuilder(EPackage ePackage) {
        this.ePackage = ePackage;
    }

    @Override
    public Set<GraphQLType> getTypes() {
        Set<GraphQLType> types = new LinkedHashSet<>();

        this.ePackage.getEClassifiers().forEach(eClassifier -> {
            if (eClassifier instanceof EClass) {
                EClass eClass = (EClass) eClassifier;
                types.addAll(new SiriusGraphQLEClassTypesBuilder(eClass).getTypes());
            }
        });

        return types;
    }

}
