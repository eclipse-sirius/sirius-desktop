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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;

/**
 * A class aggregating all the queries (read-only!) having a {@link DView} as a starting point.
 * 
 * @author lfasani
 */
public final class DViewQuery {
    private static boolean activateTrace;
    static {
        activateTrace = Boolean.parseBoolean(System.getProperty("activate_trace_getLoadedRepresentations", Boolean.FALSE.toString())); //$NON-NLS-1$
    }

    private DView dView;

    /**
     * Create a new query.
     * 
     * @param dView
     *            the {@link DView} to query.
     */
    public DViewQuery(DView dView) {
        this.dView = dView;
    }

    /**
     * Get the {@link DRepresentation}s which are already loaded in the dView resourceSet.
     * 
     * @return an unmodifiable list with the loaded {@link DRepresentation}s
     */
    public List<DRepresentation> getLoadedRepresentations() {
        if (activateTrace) {
            Thread.dumpStack();
        }
        List<DRepresentation> representations = dView.getOwnedRepresentationDescriptors().stream().filter(DRepresentationDescriptor::isLoadedRepresentation)
                .map(DRepresentationDescriptor::getRepresentation).collect(Collectors.toList());
        return Collections.unmodifiableList(representations);
    }

    /**
     * Get the {@link DRepresentationDescriptor}s which have their associated representation already loaded in the dView
     * resourceSet.
     * 
     * @return an unmodifiable list with the {@link DRepresentationDescriptor}s which have their representation loaded.
     */
    public List<DRepresentationDescriptor> getLoadedRepresentationsDescriptors() {
        if (activateTrace) {
            Thread.dumpStack();
        }
        List<DRepresentationDescriptor> representationDescriptors = dView.getOwnedRepresentationDescriptors().stream().filter(DRepresentationDescriptor::isLoadedRepresentation)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(representationDescriptors);
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
        Iterator<EObject> iterator = Collections.emptyIterator();
        List<DRepresentation> allRepresentations = this.getLoadedRepresentations();
        for (DRepresentation dRepresentation : allRepresentations) {
            UnmodifiableIterator<EObject> currentIterator = Iterators.filter(dRepresentation.eAllContents(), (t) -> predicate.test(t));
            iterator = Iterators.concat(iterator, currentIterator);
        }
        return iterator;
    }

}
