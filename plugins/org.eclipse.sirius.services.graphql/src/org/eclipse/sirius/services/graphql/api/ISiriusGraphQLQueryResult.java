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
package org.eclipse.sirius.services.graphql.api;

import java.util.Map;

/**
 * Interface of the execution result.
 *
 * @author sbegaudeau
 */
public interface ISiriusGraphQLQueryResult {
    /**
     * Retrieve the data of the result.
     * 
     * @return The data of the result
     */
    Map<String, Object> getData();
}
