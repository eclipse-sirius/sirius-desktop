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
package org.eclipse.sirius.services.graphql.internal.schema.mutation.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.internal.schema.SiriusGraphQLArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLFileTypesBuilder;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the createFile field.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLCreateFileField {

    /**
     * The name of the createFile mutation.
     */
    private static final String CREATE_FILE_FIELD = "createFile"; //$NON-NLS-1$

    /**
     * The name of the rootEObjectEPackageNsURI argument.
     */
    private static final String ROOT_EOBJECT_EPACKAGE_NSURI_ARG = "rootEObjectEPackageNsURI"; //$NON-NLS-1$

    /**
     * The name of the rootEObjectEClassName argument.
     */
    private static final String ROOT_EOBJECT_ECLASS_NAME_ARG = "rootEObjectEClassName"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLCreateFileField() {
        // Prevent instantiation
    }

    /**
     * Returns the createFolder field.
     *
     * @return The createFolder field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CREATE_FILE_FIELD)
                .argument(SiriusGraphQLProjectNameArgument.build())
                .argument(SiriusGraphQLContainerPathArgument.build())
                .argument(SiriusGraphQLNameArgument.build())
                .argument(SiriusGraphQLArguments.newString(ROOT_EOBJECT_ECLASS_NAME_ARG))
                .argument(SiriusGraphQLArguments.newString(ROOT_EOBJECT_EPACKAGE_NSURI_ARG))
                .type(new GraphQLTypeReference(SiriusGraphQLFileTypesBuilder.FILE_TYPE))
                .dataFetcher(SiriusGraphQLCreateFileField.getCreateFileDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the createFile data fetcher.
     *
     * @return The createFile data fetcher
     */
    private static DataFetcher<IFile> getCreateFileDataFetcher() {
        return environment -> {
            Optional<IFile> optionalFile = SiriusGraphQLCreateFileField.getFile(environment);
            Optional<EClass> optionalEClass = SiriusGraphQLCreateFileField.getEClass(environment);
            if (optionalFile.isPresent() && optionalEClass.isPresent()) {
                IFile iFile = optionalFile.get();
                EClass eClass = optionalEClass.get();
                if (!iFile.exists()) {
                    try {
                        URI uri = URI.createPlatformResourceURI(iFile.getFullPath().toString(), true);
                        Factory factory = SiriusGraphQLCreateFileField.getResourceFactory(uri);
                        Resource resource = factory.createResource(uri);
                        EObject eObject = eClass.getEPackage().getEFactoryInstance().create(eClass);

                        resource.getContents().add(eObject);
                        resource.save(new HashMap<>());
                    } catch (IOException exception) {
                        IStatus status = new Status(IStatus.ERROR, SiriusGraphQLPlugin.PLUGIN_ID, exception.getMessage(), exception);
                        SiriusGraphQLPlugin.getPlugin().log(status);
                    }
                    return iFile;
                }
            }

            return null;
        };
    }

    /**
     * Returns the file to create from the given environment.
     *
     * @param environment
     *            The environment
     * @return The file to create from the given environment
     */
    private static Optional<IFile> getFile(DataFetchingEnvironment environment) {
        Optional<String> optionalName = Optional.of(environment.getArgument(SiriusGraphQLNameArgument.NAME_ARG));
        Optional<IContainer> optionalContainer = SiriusGraphQLCreateFileField.getContainer(environment);

        // @formatter:off
        return optionalName.flatMap(name -> {
            return optionalContainer.map(iContainer -> iContainer.getFile(new Path(name)));
        });
        // @formatter:on
    }

    /**
     * Returns the container in which the file should be created.
     *
     * @param environment
     *            The environment
     * @return The container in which the file should be created
     */
    private static Optional<IContainer> getContainer(DataFetchingEnvironment environment) {
        Optional<String> optionalProjectName = Optional.of(environment.getArgument(SiriusGraphQLProjectNameArgument.PROJECT_NAME_ARG));
        Optional<IProject> optionalProject = optionalProjectName.map(ResourcesPlugin.getWorkspace().getRoot()::getProject);
        Optional<String> optionalContainerPath = Optional.of(environment.getArgument(SiriusGraphQLContainerPathArgument.CONTAINER_PATH_ARG));

        // @formatter:off
        return optionalContainerPath.flatMap(containerPath -> {
            return optionalProject.map(iProject -> iProject.findMember(containerPath))
                    .filter(IContainer.class::isInstance)
                    .map(IContainer.class::cast);
        });
        // @formatter:on
    }

    /**
     * Returns the EClass of the root element.
     *
     * @param environment
     *            The environment
     * @return The EClass of the root element
     */
    private static Optional<EClass> getEClass(DataFetchingEnvironment environment) {
        Optional<String> optionalEPackageNsURI = Optional.of(environment.getArgument(ROOT_EOBJECT_EPACKAGE_NSURI_ARG));
        Optional<EPackage> optionalEPackage = optionalEPackageNsURI.map(EPackage.Registry.INSTANCE::getEPackage);
        Optional<String> optionalEClassName = Optional.of(environment.getArgument(ROOT_EOBJECT_ECLASS_NAME_ARG));

        // @formatter:off
        return optionalEClassName.flatMap(eClassName -> {
            return optionalEPackage.map(ePackage -> ePackage.getEClassifier(eClassName))
                    .filter(EClass.class::isInstance)
                    .map(EClass.class::cast);
        });
        // @formatter:on
    }

    /**
     * Returns the resource factory for the given URI.
     *
     * @param uri
     *            The URI
     * @return The resource factory for the given URI
     */
    private static Factory getResourceFactory(URI uri) {
        Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        Object object = extensionToFactoryMap.get(uri.fileExtension());
        if (object instanceof Factory) {
            return (Factory) object;
        }
        return new XMIResourceFactoryImpl();
    }
}
