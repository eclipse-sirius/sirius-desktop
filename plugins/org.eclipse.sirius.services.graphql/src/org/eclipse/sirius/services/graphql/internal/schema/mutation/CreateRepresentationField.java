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
package org.eclipse.sirius.services.graphql.internal.schema.mutation;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.services.common.api.SiriusServicesCommonOptionalUtils;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;
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
public final class CreateRepresentationField {

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
    private CreateRepresentationField() {
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
                .argument(ProjectNameArgument.build())
                .argument(CreateRepresentationField.getResourcePathArgument())
                .argument(CreateRepresentationField.getDescriptionArgument())
                .type(new GraphQLTypeReference(WorkspaceSchemaConstants.PROJECT_TYPE))
                .dataFetcher(CreateRepresentationField.getCreateRepresentationDataFetcher())
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
                .type(new GraphQLNonNull(new GraphQLTypeReference(RepresentationCreationDescriptionTypesProvider.REPRESENTATION_CREATION_DESCRIPTION_TYPE)))
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
            Optional<String> optionalProjectName = Optional.of(environment.getArgument(ProjectNameArgument.PROJECT_NAME_ARG))
                    .filter(String.class::isInstance)
                    .map(String.class::cast);
            
            Optional<IProject> optionalProject = optionalProjectName.map(ResourcesPlugin.getWorkspace().getRoot()::getProject);
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
            String viewpointIdentifier = description.get(RepresentationCreationDescriptionTypesProvider.VIEWPOINT_IDENTIFIER_FIELD);
            String representationIdentifier = description.get(RepresentationCreationDescriptionTypesProvider.REPRESENTATION_IDENTIFIER_FIELD);
            String eObjectFragment = description.get(RepresentationCreationDescriptionTypesProvider.EOBJECT_FRAGMENT_FIELD);
            String name = description.get(RepresentationCreationDescriptionTypesProvider.NAME_FIELD);
            
            Optional<Viewpoint> optionalViewpoint = optionalSession.flatMap(session -> {
                return session.getSelectedViewpoints(false).stream()
                        .filter(viewpoint -> viewpointIdentifier.equals(viewpoint.getName()))
                        .findFirst();
            });

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
                            CreateRepresentationField.createRepresentation(session, representationDescription, name, eObject);
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
