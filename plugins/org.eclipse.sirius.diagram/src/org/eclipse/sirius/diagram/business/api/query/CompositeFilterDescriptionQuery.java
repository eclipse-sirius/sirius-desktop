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

import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link CompositeFilterDescription} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class CompositeFilterDescriptionQuery {

    private CompositeFilterDescription composite;

    /**
     * Create a new query.
     * 
     * @param filterDescription
     *            the element to query.
     */
    public CompositeFilterDescriptionQuery(CompositeFilterDescription filterDescription) {
        this.composite = filterDescription;
    }

    /**
     * Get filters of the current composite which have the collapse filter kind.
     * 
     * @return collapse filters
     */
    public boolean isCollapseCompositeFilter() {
        return hasFilter(FilterKind.COLLAPSE_LITERAL);
    }

    /**
     * Get filters of the current composite which have the collapse filter kind.
     * 
     * @return collapse filters
     */
    public boolean isHideCompositeFilter() {
        return hasFilter(FilterKind.HIDE_LITERAL);
    }

    /**
     * Get filters of the current composite which have the collapse filter kind.
     * 
     * @return collapse filters
     */
    public Collection<Filter> getCollapseFilters() {
        return getFilters(FilterKind.COLLAPSE_LITERAL);
    }

    /**
     * Get filters of the current composite which have the collapse filter kind.
     * 
     * @return collapse filters
     */
    public Collection<Filter> getHideFilters() {
        return getFilters(FilterKind.HIDE_LITERAL);
    }

    private boolean hasFilter(FilterKind filterKind) {
        return Iterables.any(composite.getFilters(), new FilterKindPredicate(filterKind));
    }

    private Collection<Filter> getFilters(FilterKind filterKind) {
        return Lists.newArrayList(Iterables.filter(composite.getFilters(), new FilterKindPredicate(filterKind)));
    }

    private static class FilterKindPredicate implements Predicate<Filter> {

        private FilterKind filterKind;

        /**
         * Default constructor.
         * 
         * @param filterKind
         *            the expected filter kind
         */
        FilterKindPredicate(FilterKind filterKind) {
            super();
            this.filterKind = filterKind;
        }

        public boolean apply(Filter input) {
            return input.getFilterKind() == filterKind;
        }
    }

    /**
     * Get all the mappings concerned by a filter of king Hide in this composite
     * filter.
     * 
     * @return all the mappings concerned by a filter of king Hide in this
     *         composite filter.
     */
    public EList<DiagramElementMapping> getHiddenMappings() {
        EList<DiagramElementMapping> result = new BasicEList<DiagramElementMapping>();
        for (Filter filter : getHideFilters()) {
            if (filter instanceof MappingFilter) {
                result.addAll(((MappingFilter) filter).getMappings());
            }

        }
        return result;
    }
}
