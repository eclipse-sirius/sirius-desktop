/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A class aggregating all the queries (read-only!) having a {@link EObject} as
 * a starting point.
 * 
 * @author mporhel
 * 
 */
public class EObjectQuery extends org.eclipse.sirius.business.api.query.EObjectQuery {

    /**
     * Create a new query.
     * 
     * @param eObject
     *            the element to query.
     */
    public EObjectQuery(EObject eObject) {
        super(eObject);
    }

    /**
     * Create a new query. Prefer this constructor if in the context of the
     * call, you already have access to the cross referencer of the session
     * containing the queried EObject.
     * 
     * @param eObject
     *            the element to query.
     * @param xref
     *            ECrossReferenceAdapter to use for all queries about inverse
     *            references.
     */
    public EObjectQuery(EObject eObject, ECrossReferenceAdapter xref) {
        super(eObject, xref);
    }

    /**
     * Browse the model upward (from the leaf to the root) and return the first
     * diagram found.
     * 
     * @return the diagram if found, null otherwise.
     */
    public Option<DDiagram> getParentDiagram() {
        Option<DRepresentation> parentRepresentation = getRepresentation();
        if (parentRepresentation.some() && parentRepresentation.get() instanceof DDiagram) {
            return Options.newSome((DDiagram) parentRepresentation.get());
        }
        return Options.newNone();
    }
}
