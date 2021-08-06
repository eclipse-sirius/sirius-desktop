/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;

/**
 * A class aggregating all the queries (read-only!) having a {@link DView} as a starting point.
 * 
 * @author lfasani
 */
public final class DViewQuery {

    private org.eclipse.sirius.model.business.internal.query.DViewQuery internalQuery;

    /**
     * Create a new query.
     * 
     * @param dView
     *            the {@link DView} to query.
     */
    public DViewQuery(DView dView) {
        this.internalQuery = new org.eclipse.sirius.model.business.internal.query.DViewQuery(dView);
    }

    /**
     * Get the {@link DRepresentation}s which are already loaded in the dView resourceSet.
     * 
     * @return an unmodifiable list with the loaded {@link DRepresentation}s
     */
    public List<DRepresentation> getLoadedRepresentations() {
        return internalQuery.getLoadedRepresentations();
    }

    /**
     * Get the {@link DRepresentationDescriptor}s which have their associated representation already loaded in the dView
     * resourceSet.
     * 
     * @return an unmodifiable list with the {@link DRepresentationDescriptor}s which have their representation loaded.
     */
    public List<DRepresentationDescriptor> getLoadedRepresentationsDescriptors() {
        return internalQuery.getLoadedRepresentationsDescriptors();
    }

    /**
     * Get all the EObject following a predicate from all the {@link DRepresentation} inside the {@link DView}
     * ({@link DRepresentation} s are not owned by the {@link DView}).
     * 
     * @param predicate
     *            predicate used to filter the result
     * @return the result iterator
     */
    public Iterator<EObject> getAllContentInRepresentations(final Predicate<? super EObject> predicate) {
        return internalQuery.getAllContentInRepresentations(predicate);
    }

}
