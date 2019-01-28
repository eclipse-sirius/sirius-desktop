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
package org.eclipse.sirius.services.graphql.common.api.pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.sirius.services.graphql.common.api.SiriusGraphQLContext;
import org.eclipse.sirius.services.graphql.common.api.directives.SiriusGraphQLCostDirective;
import org.eclipse.sirius.services.graphql.common.internal.SiriusGraphQLCommonMessages;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLArgument;

/**
 * Used to create a data fetcher supporting the pagination.
 *
 * @author sbegaudeau
 */
public final class SiriusGraphQLPaginationDataFetcher {

    /**
     * The constructor.
     */
    private SiriusGraphQLPaginationDataFetcher() {
        // Prevent instantiation
    }

    /**
     * Creates the data fetcher used to return the paginated result.
     *
     * @param callback
     *            The function used to compute the edges
     * @param cursorComputer
     *            The function used to compute the cursor for each edge
     * @param <T>
     *            The type of the edges to return
     * @return The data fetcher used to return the paginated result.
     */
    public static <T> DataFetcher<SiriusGraphQLConnection> build(Function<DataFetchingEnvironment, List<T>> callback, Function<T, String> cursorComputer) {
        return environment -> {
            Integer first = SiriusGraphQLPaginationDataFetcher.getFirst(environment);
            Integer last = SiriusGraphQLPaginationDataFetcher.getLast(environment);
            String after = SiriusGraphQLPaginationDataFetcher.getAfter(environment);
            String before = SiriusGraphQLPaginationDataFetcher.getBefore(environment);

            SiriusGraphQLPaginationDataFetcher.assertArguments(environment);

            int cost = SiriusGraphQLPaginationDataFetcher.getCost(environment);
            // @formatter:off
            Optional.ofNullable(environment.getContext())
                .filter(SiriusGraphQLContext.class::isInstance)
                .map(SiriusGraphQLContext.class::cast)
                .ifPresent(context -> context.add(cost));
            // @formatter:on

            List<T> allEdges = callback.apply(environment);
            List<T> edgesToReturn = SiriusGraphQLPaginationDataFetcher.getEdgesToReturn(allEdges, before, after, first, last, cursorComputer);

            // @formatter:off
            List<SiriusGraphQLEdge> edges = edgesToReturn.stream()
                    .map(edge -> new SiriusGraphQLEdge(edge, cursorComputer.apply(edge)))
                    .collect(Collectors.toList());
            // @formatter:on

            int totalCount = allEdges.size();
            boolean hasPreviousPage = SiriusGraphQLPaginationDataFetcher.hasPreviousPage(allEdges, edgesToReturn);
            boolean hasNextPage = SiriusGraphQLPaginationDataFetcher.hasNextPage(allEdges, edgesToReturn);
            SiriusGraphQLPageInfo pageInfo = new SiriusGraphQLPageInfo(hasPreviousPage, hasNextPage);
            return new SiriusGraphQLConnection(totalCount, edges, pageInfo);
        };
    }

    /**
     * Asserts the validity of the arguments retrieved.
     * 
     * @param environment
     *            The data fetching environment
     */
    private static void assertArguments(DataFetchingEnvironment environment) {
        Integer first = SiriusGraphQLPaginationDataFetcher.getFirst(environment);
        Integer last = SiriusGraphQLPaginationDataFetcher.getLast(environment);

        // Both after and before can be null but it has to be a specified value
        boolean hasFowardPaginationArguments = first != null && environment.containsArgument(SiriusGraphQLPaginationArguments.AFTER_ARG);
        boolean hasBackwardPaginationArguments = last != null && environment.containsArgument(SiriusGraphQLPaginationArguments.BEFORE_ARG);

        if (!hasFowardPaginationArguments && !hasBackwardPaginationArguments) {
            throw new IllegalArgumentException(SiriusGraphQLCommonMessages.SiriusGraphQLPaginationDataFetcher_invalidArguments);
        }
    }

    /**
     * Returns the value of the first argument.
     *
     * @param environment
     *            The data fetching environment.
     * @return The value of the first argument
     */
    private static Integer getFirst(DataFetchingEnvironment environment) {
        // @formatter:off
        return Optional.ofNullable(environment.getArgument(SiriusGraphQLPaginationArguments.FIRST_ARG))
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElse(null);
        // @formatter:on
    }

    /**
     * Returns the value of the last argument.
     *
     * @param environment
     *            The data fetching environment.
     * @return The value of the last argument
     */
    private static Integer getLast(DataFetchingEnvironment environment) {
        // @formatter:off
        return Optional.ofNullable(environment.getArgument(SiriusGraphQLPaginationArguments.LAST_ARG))
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElse(null);
        // @formatter:on
    }

    /**
     * Returns the value of the after argument.
     *
     * @param environment
     *            The data fetching environment.
     * @return The value of the after argument
     */
    private static String getAfter(DataFetchingEnvironment environment) {
        // @formatter:off
        return Optional.ofNullable(environment.getArgument(SiriusGraphQLPaginationArguments.AFTER_ARG))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .orElse(null);
        // @formatter:on
    }

    /**
     * Returns the value of the before argument.
     *
     * @param environment
     *            The data fetching environment.
     * @return The value of the before argument
     */
    private static String getBefore(DataFetchingEnvironment environment) {
        // @formatter:off
        return Optional.ofNullable(environment.getArgument(SiriusGraphQLPaginationArguments.BEFORE_ARG))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .orElse(null);
        // @formatter:on
    }

    /**
     * Returns the complexity of the field.
     *
     * @param environment
     *            The data fetching environment
     * @return The complexity of the field
     */
    private static int getComplexity(DataFetchingEnvironment environment) {
        // @formatter:off
        return Optional.ofNullable(environment.getFieldDefinition().getDirective(SiriusGraphQLCostDirective.COST))
                .map(directive -> directive.getArgument(SiriusGraphQLCostDirective.COMPLEXITY_ARG))
                .map(GraphQLArgument::getDefaultValue)
                .filter(Integer.class::isInstance)
                .map(Integer.class::cast)
                .orElse(Integer.valueOf(0))
                .intValue();
        // @formatter:on
    }

    /**
     * Returns the multipliers of the field.
     *
     * @param environment
     *            The data fetching environment
     * @return The multipliers of the field
     */
    private static List<String> getMultipliers(DataFetchingEnvironment environment) {
        // @formatter:off
        return Optional.ofNullable(environment.getFieldDefinition().getDirective(SiriusGraphQLCostDirective.COST))
                .map(directive -> directive.getArgument(SiriusGraphQLCostDirective.MULTIPLIERS_ARG))
                .map(GraphQLArgument::getDefaultValue)
                .map(value -> {
                    if (value instanceof List<?>) {
                        return ((List<?>) value).stream()
                                    .filter(String.class::isInstance)
                                    .map(String.class::cast)
                                    .collect(Collectors.toList());
                    }
                    return new ArrayList<String>();
                })
                .orElseGet(ArrayList::new);
        // @formatter:on
    }

    /**
     * Returns the cost of fetching the elements requested.
     *
     * @param environment
     *            The data fetching environment
     * @return The cost of fetching the elements requested
     */
    private static int getCost(DataFetchingEnvironment environment) {
        int complexity = SiriusGraphQLPaginationDataFetcher.getComplexity(environment);
        List<String> multipliers = SiriusGraphQLPaginationDataFetcher.getMultipliers(environment);

        int cost = 0;
        for (String multiplier : multipliers) {
            // @formatter:off
            int value = Optional.ofNullable(environment.getArgument(multiplier))
                    .filter(Integer.class::isInstance)
                    .map(Integer.class::cast)
                    .orElse(Integer.valueOf(0))
                    .intValue();
            // @formatter:on

            cost = cost + (value * complexity);
        }

        return cost;
    }

    /**
     * Indicates if there is a previous page.
     *
     * @param edges
     *            The whole list of edges to consider
     * @param edgesToReturn
     *            The list of edges to return
     * @param <T>
     *            The type of the edge
     * @return <code>true</code> if there is a previous page, <code>false</code> otherwise
     */
    private static <T> boolean hasPreviousPage(List<T> edges, List<T> edgesToReturn) {
        return edges.size() > 0 && (edgesToReturn.isEmpty() || edges.indexOf(edgesToReturn.get(0)) > 0);
    }

    /**
     * Indicates if there is a next page.
     *
     * @param edges
     *            The whole list of edges to consider
     * @param edgesToReturn
     *            The list of edges to return
     * @param <T>
     *            The type of the edge
     * @return <code>true</code> if there is a next page, <code>false</code> otherwise
     */
    private static <T> boolean hasNextPage(List<T> edges, List<T> edgesToReturn) {
        return edges.size() > 0 && (!edgesToReturn.isEmpty() && edges.indexOf(edgesToReturn.get(edgesToReturn.size() - 1)) < edges.size() - 1);
    }

    /**
     * Computes the edges to return for the given pagination arguments. See
     * https://facebook.github.io/relay/graphql/connections.htm#sec-Pagination-algorithm
     *
     * @param allEdges
     *            All the edges which can be returned
     * @param before
     *            The before cursor
     * @param after
     *            The after cursor
     * @param first
     *            The number of edges to return for the forward pagination
     * @param last
     *            The number of edges to return for the backward pagination
     * @param cursorComputer
     *            The function used to compute the cursor
     * @param <T>
     *            The type of the edge
     * @return The sliced edges
     */
    private static <T> List<T> getEdgesToReturn(List<T> allEdges, String before, String after, Integer first, Integer last, Function<T, String> cursorComputer) {
        List<T> edges = SiriusGraphQLPaginationDataFetcher.applyCursorsToEdges(allEdges, before, after, cursorComputer);
        if (Objects.nonNull(first) && first.intValue() > 0 && edges.size() > first.intValue()) {
            edges = edges.subList(0, first.intValue());
        }
        if (Objects.nonNull(last) && last.intValue() > 0 && edges.size() > last.intValue()) {
            edges = edges.subList(edges.size() - last.intValue(), edges.size());
        }
        return edges;
    }

    /**
     * Apply the cursor to the given edges. See
     * https://facebook.github.io/relay/graphql/connections.htm#ApplyCursorsToEdges()
     *
     * @param allEdges
     *            The list of the edges
     * @param before
     *            The before cursor
     * @param after
     *            The after cursor
     * @param cursorComputer
     *            The function used to compute the cursor
     * @param <T>
     *            The type of the edge
     * @return The sliced list of edges
     */
    private static <T> List<T> applyCursorsToEdges(List<T> allEdges, String before, String after, Function<T, String> cursorComputer) {
        List<T> edges = allEdges;
        if (Objects.nonNull(after) && !after.isEmpty()) {
            // @formatter:off
            Optional<T> optionalAfterEdge = edges.stream()
                    .filter(anEdge -> after.equals(cursorComputer.apply(anEdge)))
                    .findFirst();
            if (optionalAfterEdge.isPresent()) {
                T afterEdge = optionalAfterEdge.get();
                int afterEdgeIndex = edges.indexOf(afterEdge);
                edges = edges.subList(afterEdgeIndex + 1, edges.size());
            }
            // @formatter:on
        } else if (Objects.nonNull(before) && !before.isEmpty()) {
            // @formatter:off
            Optional<T> optionalBeforeEdge = edges.stream()
                    .filter(anEdge -> before.equals(cursorComputer.apply(anEdge)))
                    .findFirst();
            if (optionalBeforeEdge.isPresent()) {
                T beforeEdge = optionalBeforeEdge.get();
                int beforeEdgeIndex = edges.indexOf(beforeEdge);
                edges = edges.subList(0, beforeEdgeIndex);
            }
            // @formatter:on
        }
        return edges;
    }
}
