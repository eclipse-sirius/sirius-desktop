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
package org.eclipse.sirius.services.graphql.internal.schema.query.pagination;

import java.util.Base64;

import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Switch used to compute the cursor.
 *
 * @author sbegaudeau
 */
public class SiriusGraphQLCursorSwitch {

    /**
     * Returns a cursor for the given object.
     *
     * @param object
     *            The object
     * @return A cursor for the given object
     */
    public String doSwitch(Object object) {
        String unEncodedCursor = null;

        if (object instanceof IResource) {
            unEncodedCursor = ((IResource) object).getName();
        } else if (object instanceof EObject) {
            unEncodedCursor = EcoreUtil.getURI((EObject) object).toString();
        }

        if (unEncodedCursor != null) {
            return Base64.getEncoder().encodeToString(unEncodedCursor.getBytes());
        }
        return null;
    }
}
