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
package org.eclipse.sirius.services.graphql.common.api;

/**
 * Filter status indicating if an element should be kept or rejected.
 * 
 * @author sbegaudeau
 */
public enum SiriusGraphQLFilterStatus {
    /**
     * Indicate that the element will be used.
     */
    KEEP,
    /**
     * Indicate that the element will not be used.
     */
    REJECT
}
