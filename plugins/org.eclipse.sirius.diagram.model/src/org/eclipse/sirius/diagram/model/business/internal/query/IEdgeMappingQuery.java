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
package org.eclipse.sirius.diagram.model.business.internal.query;

import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.model.business.internal.description.spec.EdgeMappingImportWrapper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A class aggregating all the queries (read-only!) having a {@link IEdgeMapping} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class IEdgeMappingQuery {

    private IEdgeMapping iEdgeMapping;

    /**
     * Create a new query.
     * 
     * @param iEdgeMapping
     *            the element to query.
     */
    public IEdgeMappingQuery(IEdgeMapping iEdgeMapping) {
        this.iEdgeMapping = iEdgeMapping;
    }

    /**
     * Return the wrapper in case of EdgeMappingImport or EdgeMappingImportWrapper, or the EdgeMapping itself otherwise.
     * 
     * @return The correct EdgeMapping
     */
    public Option<EdgeMapping> getEdgeMapping() {
        EdgeMapping result = null;
        if (iEdgeMapping instanceof EdgeMappingImport && !(iEdgeMapping instanceof EdgeMappingImportWrapper)) {
            result = EdgeMappingImportWrapper.getWrapper((EdgeMappingImport) iEdgeMapping);
        } else {
            result = (EdgeMapping) iEdgeMapping;
        }
        return Options.newSome(result);
    }

    /**
     * Returns the real edge mapping. For example, in case of edge mapping import of an edge mapping import, the method
     * will recursively call getImportedMapping until to get the real one.
     * 
     * @return the real edge mapping.
     */
    public Option<EdgeMapping> getOriginalEdgeMapping() {
        EdgeMapping result = null;
        if (iEdgeMapping instanceof EdgeMappingImport) {
            result = getOriginalEdgeMapping((EdgeMappingImport) iEdgeMapping);
        } else {
            result = (EdgeMapping) iEdgeMapping;
        }
        return Options.newSome(result);
    }

    private EdgeMapping getOriginalEdgeMapping(EdgeMappingImport edgeMappingImport) {
        EdgeMapping result = null;
        if (edgeMappingImport.getImportedMapping() instanceof EdgeMappingImport) {
            result = getOriginalEdgeMapping((EdgeMappingImport) edgeMappingImport.getImportedMapping());
        } else if (edgeMappingImport.getImportedMapping() instanceof EdgeMapping) {
            result = (EdgeMapping) edgeMappingImport.getImportedMapping();
        }
        return result;
    }
}
