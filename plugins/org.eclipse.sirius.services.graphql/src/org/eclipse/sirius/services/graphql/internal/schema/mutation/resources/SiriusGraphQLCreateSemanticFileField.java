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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.sirius.services.graphql.internal.SiriusGraphQLPlugin;
import org.eclipse.sirius.services.graphql.internal.schema.query.resources.SiriusGraphQLFileTypesBuilder;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the createSemanticFile field.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLCreateSemanticFileField {

    /**
     * The name of the createSemanticFile field.
     */
    private static final String CREATE_SEMANTIC_FILE_FIELD = "createSemanticFile"; //$NON-NLS-1$

    /**
     * The name of the description argument.
     */
    private static final String DESCRIPTION_ARG = "description"; //$NON-NLS-1$

    /**
     * The constructor.
     */
    private SiriusGraphQLCreateSemanticFileField() {
        // Prevent instantiation
    }

    /**
     * Returns the createSemanticFile field.
     *
     * @return The createSemanticFile field
     */
    public static GraphQLFieldDefinition build() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(CREATE_SEMANTIC_FILE_FIELD)
                .argument(SiriusGraphQLProjectNameArgument.build())
                .argument(SiriusGraphQLContainerPathArgument.build())
                .argument(SiriusGraphQLCreateSemanticFileField.getDescriptionArgument())
                .type(new GraphQLTypeReference(SiriusGraphQLFileTypesBuilder.FILE_TYPE))
                .dataFetcher(SiriusGraphQLCreateSemanticFileField.getCreateSemanticFileDataFetcher())
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
                .type(new GraphQLNonNull(new GraphQLTypeReference(SiriusGraphQLSemanticFileCreationDescriptionTypesBuilder.SEMANTIC_FILE_CREATION_DESCRIPTION_TYPE)))
                .build();
        // @formatter:on
    }

    /**
     * Returns the createSemanticFile data fetcher.
     *
     * @return The createSemanticFile data fetcher
     */
    private static DataFetcher<IFile> getCreateSemanticFileDataFetcher() {
        return environment -> {
            Optional<IFile> optionalFile = SiriusGraphQLFileCreationHelper.getFile(environment);
            Optional<EClass> optionalEClass = SiriusGraphQLCreateSemanticFileField.getEClass(environment);
            if (optionalFile.isPresent() && optionalEClass.isPresent()) {
                IFile iFile = optionalFile.get();
                EClass eClass = optionalEClass.get();
                if (!iFile.exists()) {
                    try {
                        URI uri = URI.createPlatformResourceURI(iFile.getFullPath().toString(), true);
                        Factory factory = SiriusGraphQLCreateSemanticFileField.getResourceFactory(uri);
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
     * Returns the EClass of the root element.
     *
     * @param environment
     *            The environment
     * @return The EClass of the root element
     */
    private static Optional<EClass> getEClass(DataFetchingEnvironment environment) {
        Map<String, String> description = environment.<Map<String, String>> getArgument(DESCRIPTION_ARG);

        String ePackageNsUri = description.get(SiriusGraphQLSemanticFileCreationDescriptionTypesBuilder.EPACKAGE_NS_URI_FIELD);
        String eClassName = description.get(SiriusGraphQLSemanticFileCreationDescriptionTypesBuilder.ECLASS_NAME_FIELD);

        Optional<EPackage> optionalEPackage = Optional.ofNullable(EPackage.Registry.INSTANCE.getEPackage(ePackageNsUri));
        // @formatter:off
        return optionalEPackage.map(ePackage -> ePackage.getEClassifier(eClassName))
                .filter(EClass.class::isInstance)
                .map(EClass.class::cast);
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
