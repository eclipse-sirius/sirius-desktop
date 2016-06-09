/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.query;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;

/**
 * A class aggregating all the queries (read-only!) having a {@link DView} as a
 * starting point.
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
     * Get the {@link DRepresentation}s which are already loaded in the dView
     * resourceSet.
     * 
     * @return an unmodifiable list with the loaded {@link DRepresentation}s
     */
    public List<DRepresentation> getLoadedRepresentations() {
        if (activateTrace) {
            Thread.dumpStack();
        }
        List<DRepresentation> representations = Lists.newArrayList();
        for (DRepresentationDescriptor repDescriptor : dView.getOwnedRepresentationDescriptors()) {
            DRepresentation representation = repDescriptor.getRepresentation();
            if (representation != null) {
                representations.add(representation);
            }
        }
        return Collections.unmodifiableList(representations);
    }

    /**
     * Get all the EObject following a predicate from all the
     * {@link DRepresentation} inside the {@link DView} ({@link DRepresentation}
     * s are not owned by the {@link DView}).
     * 
     * @param predicate
     *            predicate used to filter the result
     * @return the result iterator
     */
    public Iterator<EObject> getAllContentInRepresentations(final Predicate<? super EObject> predicate) {
        Iterator<EObject> iterator = Collections.emptyIterator();
        List<DRepresentation> allRepresentations = this.getLoadedRepresentations();
        for (DRepresentation dRepresentation : allRepresentations) {
            UnmodifiableIterator<EObject> currentIterator = Iterators.filter(dRepresentation.eAllContents(), predicate);
            iterator = Iterators.concat(iterator, currentIterator);
        }
        return iterator;
    }

}
