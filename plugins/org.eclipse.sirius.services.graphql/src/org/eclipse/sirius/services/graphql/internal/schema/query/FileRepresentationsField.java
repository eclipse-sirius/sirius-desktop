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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.graphql.common.api.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.common.api.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.services.graphql.internal.schema.SchemaConstants;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the representations field of the file.
 * 
 * @author sbegaudeau
 */
public final class FileRepresentationsField {

    /**
     * The name of the representations field.
     */
    private static final String REPRESENTATIONS = "representations"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of a representation.
     */
    private static final int REPRESENTATIONS_COMPLEXITY = 1;

    /**
     * The constructor.
     */
    private FileRepresentationsField() {
        // Prevent instantiation
    }

    /**
     * Returns the representations field.
     *
     * @return The representations field.
     */
    public static GraphQLFieldDefinition build() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(REPRESENTATIONS)
                .type(new GraphQLTypeReference(SchemaConstants.FILE_REPRESENTATION_CONNECTION_TYPE))
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(REPRESENTATIONS_COMPLEXITY, multipliers).build())
                .dataFetcher(FileRepresentationsField.getRepresentationsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the representations data fetcher.
     *
     * @return The representations data fetcher.
     */
    private static DataFetcher<SiriusGraphQLConnection> getRepresentationsDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            Optional<IFile> optionalFile = Optional.of(environment.getSource())
                    .filter(IFile.class::isInstance)
                    .map(IFile.class::cast);
            
            Optional<Session> optionalSession = optionalFile.map(IFile::getProject)
                    .flatMap(SiriusServicesCommonOptionalUtils::toSession);

            List<DRepresentation> representations = new ArrayList<>();
            if (optionalFile.isPresent() && optionalSession.isPresent()) {
                IFile iFile = optionalFile.get();
                Session session = optionalSession.get();

                Collection<DRepresentationDescriptor> representationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
                for (DRepresentationDescriptor representationDescriptor : representationDescriptors) {
                    EObject eObject = representationDescriptor.getTarget();
                    URI uri = eObject.eResource().getURI();
                    URI fileUri = URI.createPlatformResourceURI(iFile.getFullPath().toString(), true);
                    if (uri.equals(fileUri)) {
                        representations.add(representationDescriptor.getRepresentation());
                    }
                }
            }

            return representations;
        }, FileRepresentationsField::computeRepresentationCursor);
        // @formatter:on
    }

    /**
     * Returns the cursor of the given representation.
     * 
     * @param representation
     *            The representation
     * @return The cursor of the given representation
     */
    private static String computeRepresentationCursor(DRepresentation dRepresentation) {
        String unEncodedCursor = EcoreUtil.getURI(dRepresentation).toString();
        return Base64.getEncoder().encodeToString(unEncodedCursor.getBytes());
    }
}
