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
package org.eclipse.sirius.services.graphql.internal.schema;

import org.eclipse.sirius.services.graphql.common.api.ISiriusGraphQLTypeCustomizer;
import org.eclipse.sirius.services.graphql.core.api.CoreSchemaConstants;
import org.eclipse.sirius.services.graphql.internal.schema.query.ActivatedViewpointsField;
import org.eclipse.sirius.services.graphql.internal.schema.query.FileEObjectsField;
import org.eclipse.sirius.services.graphql.internal.schema.query.FileRepresentationsField;
import org.eclipse.sirius.services.graphql.internal.schema.query.RepresentationDescriptionEPackagesField;
import org.eclipse.sirius.services.graphql.workspace.api.WorkspaceSchemaConstants;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLObjectType;

/**
 * Implementation of the {@link ISiriusGraphQLTypeCustomizer}.
 * 
 * @author sbegaudeau
 */
public class SiriusGraphQLTypeCustomizer implements ISiriusGraphQLTypeCustomizer {

    @Override
    public GraphQLInterfaceType.Builder customize(String name, GraphQLInterfaceType.Builder interfaceTypeBuilder) {
        GraphQLInterfaceType.Builder customizedInterfaceTypeBuilder = interfaceTypeBuilder;
        if (CoreSchemaConstants.REPRESENTATION_DESCRIPTION_TYPE.equals(name)) {
            customizedInterfaceTypeBuilder = this.customizeRepresentationDescription(interfaceTypeBuilder);
        }
        return customizedInterfaceTypeBuilder;
    }

    /**
     * Customize the representation description type.
     * 
     * @param objectTypeBuilder
     *            The builder
     * @return The given builder customized
     */
    private GraphQLInterfaceType.Builder customizeRepresentationDescription(GraphQLInterfaceType.Builder interfaceTypeBuilder) {
        return interfaceTypeBuilder.field(RepresentationDescriptionEPackagesField.build());
    }

    @Override
    public GraphQLObjectType.Builder customize(String name, GraphQLObjectType.Builder objectTypeBuilder) {
        GraphQLObjectType.Builder customizedObjectTypeBuilder = objectTypeBuilder;
        if (WorkspaceSchemaConstants.FILE_TYPE.equals(name)) {
            customizedObjectTypeBuilder = this.customizeFile(objectTypeBuilder);
        } else if (WorkspaceSchemaConstants.PROJECT_TYPE.equals(name)) {
            customizedObjectTypeBuilder = this.customizeProject(objectTypeBuilder);
        } else if (CoreSchemaConstants.DIAGRAM_DESCRIPTION_TYPE.equals(name)) {
            customizedObjectTypeBuilder = this.customizeDiagramDescription(objectTypeBuilder);
        }
        return customizedObjectTypeBuilder;
    }

    /**
     * Customize the diagram description type.
     * 
     * @param objectTypeBuilder
     *            The builder
     * @return The given builder customized
     */
    private GraphQLObjectType.Builder customizeDiagramDescription(GraphQLObjectType.Builder objectTypeBuilder) {
        return objectTypeBuilder.field(RepresentationDescriptionEPackagesField.build());
    }

    /**
     * Customize the file type.
     * 
     * @param objectTypeBuilder
     *            The builder
     * @return The given builder customized
     */
    private GraphQLObjectType.Builder customizeFile(GraphQLObjectType.Builder objectTypeBuilder) {
        return objectTypeBuilder.field(FileRepresentationsField.build()).field(FileEObjectsField.build());
    }

    /**
     * Customize the project type.
     * 
     * @param objectTypeBuilder
     *            The builder
     * @return The given builder customized
     */
    private GraphQLObjectType.Builder customizeProject(GraphQLObjectType.Builder objectTypeBuilder) {
        return objectTypeBuilder.field(ActivatedViewpointsField.build());
    }
}
