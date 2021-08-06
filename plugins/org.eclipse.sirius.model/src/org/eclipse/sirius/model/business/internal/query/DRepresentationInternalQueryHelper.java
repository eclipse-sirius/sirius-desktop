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
package org.eclipse.sirius.model.business.internal.query;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A class aggregating all the queries (read-only!) having a {@link DRepresentation} as a starting point and a potential
 * session.
 * 
 * @author mporhel
 * 
 */
public class DRepresentationInternalQueryHelper {

    private static DRepresentationInternalQueryHelper instance = new DRepresentationInternalQueryHelper();

    public static void setInstance(DRepresentationInternalQueryHelper helper) {
        DRepresentationInternalQueryHelper.instance = helper;
    }

    public static DRepresentationInternalQueryHelper getInstance() {
        return DRepresentationInternalQueryHelper.instance;
    }

    /**
     * Get a cross referencer to retrieve the representation descriptor.
     * 
     * @param query
     *            the current query.
     * @return a cross referencer to retrieve the representation descriptor.
     */
    public ECrossReferenceAdapter getCrossReferencer(DRepresentationInternalQuery query) {
        return null;
    }

    /**
     * Get all analyses of the current session to retrieve the representation descriptor.
     * 
     * @param query
     *            the current query.
     * @return all analyses of the current session.
     */
    public Collection<DAnalysis> getAllAnalyses(DRepresentationInternalQuery query) {
        return Collections.EMPTY_LIST;
    }

    /**
     * Returns true if a session is known from this helper.
     * 
     * @param query
     *            the current query.
     * @return true if a session is known from this helper.
     */
    public boolean hasSession(DRepresentationInternalQuery query) {
        return false;
    }

}
