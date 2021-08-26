/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES and others.
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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DMappingBased} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class DMappingBasedQuery {

    private DMappingBased dMappingBased;

    /**
     * Create a new query.
     * 
     * @param dMappingBased
     *            the element to query.
     */
    public DMappingBasedQuery(DMappingBased dMappingBased) {
        this.dMappingBased = dMappingBased;
    }

    /**
     * Return true if the given element is from any mapping given in the list.
     * 
     * @param mappings
     *            mappings to consider
     * @return true if the given element is from any mapping given in the list.
     */
    public boolean isFromAnyMapping(final Collection<? extends DiagramElementMapping> mappings) {
        boolean anyIsfrom = false;
        final Iterator<? extends DiagramElementMapping> it = mappings.iterator();
        if (it.hasNext()) {
            RepresentationElementMapping mapping = dMappingBased.getMapping();
            while (it.hasNext() && !anyIsfrom) {
                if (mapping instanceof AbstractNodeMapping) {
                    anyIsfrom = new DiagramElementMappingQuery(it.next()).isSuperTypeOf((AbstractNodeMapping) mapping);
                } else {
                    anyIsfrom = new DiagramElementMappingQuery(it.next()).isTypeOf(dMappingBased);
                }
            }
        }
        return anyIsfrom;
    }
}
