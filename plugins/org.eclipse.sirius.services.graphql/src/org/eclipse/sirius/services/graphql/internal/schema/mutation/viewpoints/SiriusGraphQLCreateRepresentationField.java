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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.viewpoints;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.services.common.api.ProjectServices;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.common.api.ViewpointServices;
import org.eclipse.sirius.services.graphql.internal.schema.mutation.resources.SiriusGraphQLProjectNameArgument;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLProjectTypesBuilder;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the creatRepresentation field.
 * 
 * @author sbegaudeau
 */
public final class SiriusGraphQLCreateRepresentationField {

    /**
     * The name of the createRepresentation field.
     */
    private static final String CREATE_REPRESENTATION_FIELD = "createRepresentation"; //$NON-NLS-1$

    /**
     * The name of the resourcePath argument.
     */
    private static final String RESOURCE_PATH_ARG = "resourcePath"; //$NON-NLS-1$

    /**
     * The name of the description argument.
     */
    private static final String DESCRIPTION_ARG = "description"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLCreateRepresentationField() {
        // Prevent instantiation
    }

    /**
     * Returns the createRepresentation field.
     * 
     * @return The createRepresentation field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CREATE_REPRESENTATION_FIELD)
                .argument(SiriusGraphQLProjectNameArgument.build())
                .argument(SiriusGraphQLCreateRepresentationField.getResourcePathArgument())
                .argument(SiriusGraphQLCreateRepresentationField.getDescriptionArgument())
                .type(new GraphQLTypeReference(SiriusGraphQLProjectTypesBuilder.PROJECT_TYPE))
                .dataFetcher(SiriusGraphQLCreateRepresentationField.getCreateRepresentationDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the resourcePath argument.
     * 
     * @return The resourcePath argument
     */
    private static GraphQLArgument getResourcePathArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(RESOURCE_PATH_ARG)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .build();
        // @formatter:on
    }

    /**
     * Returns the description argument.
     * 
     * @return The description argument
     */
    private static GraphQLArgument getDescriptionArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(DESCRIPTION_ARG)
                .type(new GraphQLNonNull(new GraphQLTypeReference(SiriusGraphQLRepresentationCreationDescriptionTypesBuilder.REPRESENTATION_CREATION_DESCRIPTION_TYPE)))
                .build();
        // @formatter:on
    }

    /**
     * Returns the createRepresentation data fetcher.
     * 
     * @return The createRepresentation data fetcher
     */
    private static DataFetcher<IProject> getCreateRepresentationDataFetcher() {
        // @formatter:off
        return environment -> {
            Optional<IProject> optionalProject = ProjectServices.projectFromName(environment.getArgument(SiriusGraphQLProjectNameArgument.PROJECT_NAME_ARG));
            Optional<Session> optionalSession = optionalProject.flatMap(SiriusServicesCommonOptionalUtils::toSession);
            
            Function<IProject, Optional<IFile>> getFile = iProject -> Optional.of(environment.getArgument(RESOURCE_PATH_ARG))
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .map(Path::new)
                    .map(iProject::getFile);
            
            Optional<IFile> optionalFile = optionalProject.flatMap(getFile);
            
            Optional<Resource> optionalResource = optionalFile.flatMap(iFile -> {
                return optionalSession.flatMap(session -> SiriusServicesCommonOptionalUtils.toResource(session, iFile));
            });
            
            Map<String, String> description = environment.<Map<String, String>>getArgument(DESCRIPTION_ARG);
            String viewpointIdentifier = description.get(SiriusGraphQLRepresentationCreationDescriptionTypesBuilder.VIEWPOINT_IDENTIFIER_FIELD);
            String representationIdentifier = description.get(SiriusGraphQLRepresentationCreationDescriptionTypesBuilder.REPRESENTATION_IDENTIFIER_FIELD);
            String eObjectFragment = description.get(SiriusGraphQLRepresentationCreationDescriptionTypesBuilder.EOBJECT_FRAGMENT_FIELD);
            String name = description.get(SiriusGraphQLRepresentationCreationDescriptionTypesBuilder.NAME_FIELD);
            
            Optional<Viewpoint> optionalViewpoint = ViewpointServices.viewpointFromIdentifier(viewpointIdentifier);
            Optional<RepresentationDescription> optionalRepresentationDescription = optionalViewpoint.flatMap(viewpoint -> {
                return viewpoint.getOwnedRepresentations().stream()
                        .filter(representationDescription -> representationIdentifier.equals(representationDescription.getName()))
                        .findFirst();
            });
            
            Optional<EObject> optionalEObject = optionalResource.map(resource -> resource.getEObject(eObjectFragment));
            
            optionalSession.ifPresent(session -> {
                optionalRepresentationDescription.ifPresent(representationDescription -> {
                    optionalEObject.ifPresent(eObject -> {
                        boolean canCreate = DialectManager.INSTANCE.canCreate(eObject, representationDescription, true);
                        if (canCreate) {
                            SiriusGraphQLCreateRepresentationField.createRepresentation(session, representationDescription, name, eObject);
                        }
                    });
                });
            });
            
            return optionalProject.orElse(null);
        };
        // @formatter:on
    }

    /**
     * Creates the representation using a recording command.
     * 
     * @param session
     *            The session
     * @param representationDescription
     *            The description of the representation
     * @param name
     *            The name of the representation
     * @param eObject
     *            The EObject used as the root of the representation
     */
    private static void createRepresentation(Session session, RepresentationDescription representationDescription, String name, EObject eObject) {
        RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                DialectManager.INSTANCE.createRepresentation(name, eObject, representationDescription, session, new NullProgressMonitor());
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
    }
}
