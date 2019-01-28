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
package org.eclipse.sirius.services.graphql.internal.schema.query;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.graphql.common.api.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.services.graphql.internal.schema.SchemaConstants;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the eObjects field of the file.
 * 
 * @author sbegaudeau
 */
public final class FileEObjectsField {
    /**
     * The name of the eObjects field.
     */
    private static final String EOBJECTS = "eObjects"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of an eObject.
     */
    private static final int EOBJECTS_COMPLEXITY = 1;

    /**
     * The constructor.
     */
    private FileEObjectsField() {
        // Prevent instantiation
    }

    /**
     * Returns the eObjects field.
     * 
     * @return The eObjects field
     */
    public static GraphQLFieldDefinition build() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(EOBJECTS)
                .type(new GraphQLTypeReference(SchemaConstants.FILE_EOBJECT_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(EOBJECTS_COMPLEXITY, multipliers).build())
                .dataFetcher(FileEObjectsField.getEObjectsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the eObjects data fetcher.
     * 
     * @return The eObjects data fetcher
     */
    private static DataFetcher<SiriusGraphQLConnection> getEObjectsDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            Optional<IFile> optionalFile = Optional.of(environment.getSource())
                    .filter(IFile.class::isInstance)
                    .map(IFile.class::cast);
            
            Optional<Session> optionalSession = optionalFile.map(IFile::getProject)
                    .flatMap(SiriusServicesCommonOptionalUtils::toSession);
            
            Optional<Resource> optionalResource = optionalFile.flatMap(iFile -> {
                return optionalSession.flatMap(session -> SiriusServicesCommonOptionalUtils.toResource(session, iFile));
            });
            
            return optionalResource.map(Resource::getContents).orElseGet(BasicEList::new);
        }, FileEObjectsField::computeEObjectCursor);
        // @formatter:on
    }

    /**
     * Returns the cursor of the given eObject.
     * 
     * @param eObject
     *            The eObject
     * @return The cursor of the given eObject
     */
    private static String computeEObjectCursor(EObject eObject) {
        String unEncodedCursor = EcoreUtil.getURI(eObject).toString();
        return Base64.getEncoder().encodeToString(unEncodedCursor.getBytes());
    }
}
