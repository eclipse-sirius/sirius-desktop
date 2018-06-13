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
