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
package org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints;

import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder.CONNECTION_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder.EDGE_SUFFIX;
import static org.eclipse.sirius.services.graphql.internal.schema.query.viewpoints.SiriusGraphQLRepresentationDescriptionTypesBuilder.REPRESENTATION_DESCRIPTION_TYPE;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.services.graphql.internal.entities.SiriusGraphQLConnection;
import org.eclipse.sirius.services.graphql.internal.schema.ISiriusGraphQLTypesBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLConnectionTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLEdgeTypeBuilder;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationArguments;
import org.eclipse.sirius.services.graphql.internal.schema.query.pagination.SiriusGraphQLPaginationDataFetcher;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to create the Viewpoint type of the GraphQL schema.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLViewpointTypesBuilder implements ISiriusGraphQLTypesBuilder {
    /**
     * The name of the Viewpoint type.
     */
    public static final String VIEWPOINT_TYPE = "Viewpoint"; //$NON-NLS-1$

    /**
     * The name of the Viewpoint to RepresentationDescription connection type.
     */
    public static final String VIEWPOINT_REPRESENTATION_DESCRIPTION_CONNECTION_TYPE = VIEWPOINT_TYPE + REPRESENTATION_DESCRIPTION_TYPE + CONNECTION_SUFFIX;

    /**
     * The name of the Viewpoint to RepresentationDescription edge type.
     */
    public static final String VIEWPOINT_REPRESENTATION_DESCRIPTION_EDGE_TYPE = VIEWPOINT_TYPE + REPRESENTATION_DESCRIPTION_TYPE + EDGE_SUFFIX;

    /**
     * The name of the identifier field.
     */
    private static final String IDENTIFIER_FIELD = "identifier"; //$NON-NLS-1$

    /**
     * The name of the name field.
     */
    private static final String NAME_FIELD = "name"; //$NON-NLS-1$

    /**
     * The name of the representationDescriptions field.
     */
    private static final String REPRESENTATION_DESCRIPTIONS_FIELD = "representationDescriptions"; //$NON-NLS-1$

    /**
     * The complexity of the retrieval of a representation description.
     */
    private static final int COMPLEXITY = 1;

    @Override
    public Set<GraphQLType> getTypes() {
        GraphQLObjectType representationDescriptionEdge = new SiriusGraphQLEdgeTypeBuilder(VIEWPOINT_REPRESENTATION_DESCRIPTION_EDGE_TYPE, REPRESENTATION_DESCRIPTION_TYPE).build();
        GraphQLObjectType representationDescriptionConnection = new SiriusGraphQLConnectionTypeBuilder(VIEWPOINT_REPRESENTATION_DESCRIPTION_CONNECTION_TYPE,
                VIEWPOINT_REPRESENTATION_DESCRIPTION_EDGE_TYPE).build();

        // @formatter:off
        GraphQLObjectType viewpoint = GraphQLObjectType.newObject()
                .name(VIEWPOINT_TYPE)
                .field(this.getIdentifierField())
                .field(this.getNameField())
                .field(this.getRepresentationDescriptionsField())
                .build();
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(viewpoint);
        types.add(representationDescriptionEdge);
        types.add(representationDescriptionConnection);
        return types;
    }

    private GraphQLFieldDefinition getIdentifierField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(IDENTIFIER_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .dataFetcher(this.getIdentifierDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the identifier of the viewpoint.
     *
     * @return The identifier of the viewpoint
     */
    private DataFetcher<String> getIdentifierDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getSource())
                .filter(Viewpoint.class::isInstance)
                .map(Viewpoint.class::cast)
                .map(Viewpoint::getName)
                .orElse(null);
        // @formatter:on
    }

    /**
     * Returns the name field.
     *
     * @return The name field
     */
    private GraphQLFieldDefinition getNameField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(NAME_FIELD)
                .type(new GraphQLNonNull(Scalars.GraphQLString))
                .dataFetcher(this.getNameDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the name data fetcher.
     *
     * @return The name data fetcher
     */
    private DataFetcher<String> getNameDataFetcher() {
        // @formatter:off
        return environment -> Optional.of(environment.getSource())
                .filter(Viewpoint.class::isInstance)
                .map(Viewpoint.class::cast)
                .map(IdentifiedElementQuery::new)
                .map(IdentifiedElementQuery::getLabel)
                .orElse(null);
        // @formatter:on
    }

    /**
     * Returns the representation descriptions field.
     *
     * @return The representation descriptions field.
     */
    private GraphQLFieldDefinition getRepresentationDescriptionsField() {
        List<String> multipliers = new ArrayList<>();
        multipliers.add(SiriusGraphQLPaginationArguments.FIRST_ARG);
        multipliers.add(SiriusGraphQLPaginationArguments.LAST_ARG);

        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(REPRESENTATION_DESCRIPTIONS_FIELD)
                .argument(SiriusGraphQLPaginationArguments.build())
                .withDirective(new SiriusGraphQLCostDirective(COMPLEXITY, multipliers).build())
                .type(new GraphQLTypeReference(VIEWPOINT_REPRESENTATION_DESCRIPTION_CONNECTION_TYPE))
                .dataFetcher(this.getRepresentationDescriptionsDataFetcher())
                .build();
        // @formatter:on
    }

    /**
     * Returns the representation descriptions data fetcher.
     *
     * @return The representation descriptions data fetcher.
     */
    private DataFetcher<SiriusGraphQLConnection> getRepresentationDescriptionsDataFetcher() {
        // @formatter:off
        return SiriusGraphQLPaginationDataFetcher.build(environment -> {
            return Optional.of(environment.getSource())
                    .filter(Viewpoint.class::isInstance)
                    .map(Viewpoint.class::cast)
                    .map(viewpoint -> {
                        // TODO Return only diagram descriptions for now
                        return viewpoint.getOwnedRepresentations().stream()
                                .filter(DiagramDescription.class::isInstance)
                                .collect(Collectors.toList());
                    })
                    .orElseGet(ArrayList::new);
        });
        // @formatter:on
    }
}
