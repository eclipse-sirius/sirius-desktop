/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DRepresentationElement} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class DRepresentationElementQuery {

    private DRepresentationElement repElt;

    /**
     * Create a new query.
     * 
     * @param element
     *            the element to query.
     */
    public DRepresentationElementQuery(DRepresentationElement element) {
        this.repElt = element;
    }

    /**
     * Return the parent {@link DRepresentation} instance of the element.
     * 
     * @return the parent DRepresentation of the element.
     */
    public DRepresentation getParentRepresentation() {
        DRepresentation result = null;
        EObject cur = repElt;
        while (cur != null && result == null) {
            if (cur instanceof DRepresentation) {
                result = (DRepresentation) cur;
            }
            cur = cur.eContainer();
        }
        return result;
    }
}
