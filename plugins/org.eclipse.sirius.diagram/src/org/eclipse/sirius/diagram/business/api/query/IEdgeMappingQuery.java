/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.ext.base.Option;

/**
 * A class aggregating all the queries (read-only!) having a {@link IEdgeMapping} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class IEdgeMappingQuery {

    private org.eclipse.sirius.diagram.business.internal.metamodel.query.IEdgeMappingQuery internalQuery;

    /**
     * Create a new query.
     * 
     * @param iEdgeMapping
     *            the element to query.
     */
    public IEdgeMappingQuery(IEdgeMapping iEdgeMapping) {
        this.internalQuery = new org.eclipse.sirius.diagram.business.internal.metamodel.query.IEdgeMappingQuery(iEdgeMapping);
    }

    /**
     * Return the wrapper in case of EdgeMappingImport or EdgeMappingImportWrapper, or the EdgeMapping itself otherwise.
     * 
     * @return The correct EdgeMapping
     */
    public Option<EdgeMapping> getEdgeMapping() {
        return this.internalQuery.getEdgeMapping();
    }

    /**
     * Returns the real edge mapping. For example, in case of edge mapping import of an edge mapping import, the method
     * will recursively call getImportedMapping until to get the real one.
     * 
     * @return the real edge mapping.
     */
    public Option<EdgeMapping> getOriginalEdgeMapping() {
        return this.internalQuery.getOriginalEdgeMapping();
    }
}
