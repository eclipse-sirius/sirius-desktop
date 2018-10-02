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
package org.eclipse.sirius.services.graphql.internal;

import java.util.Map;

import org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult;

import graphql.ExecutionResult;

/**
 * The execution result.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLQueryResult implements ISiriusGraphQLQueryResult {

    /**
     * The execution result.
     */
    private ExecutionResult executionResult;

    /**
     * The constructor.
     *
     * @param executionResult
     *            The execution result
     */
    public SiriusGraphQLQueryResult(ExecutionResult executionResult) {
        this.executionResult = executionResult;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.graphql.api.ISiriusGraphQLQueryResult#getData()
     */
    @Override
    public Map<String, Object> getData() {
        return this.executionResult.toSpecification();
    }

}
