/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters;

import java.util.Collection;
import java.util.List;

import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.business.api.diagramtype.ICollapseUpdater;
import org.eclipse.sirius.diagram.business.api.helper.filter.FilterService;
import org.eclipse.sirius.diagram.business.api.query.CompositeFilterDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Helper to handle CollapseFilter and AppliedCompositeFilters.
 * 
 * @author mporhel
 * @since 0.9.0
 */
public class CompositeFilterApplicationBuilder {

    private DDiagram diagram;

    /**
     * Create a new instance of {@link CompositeFilterApplicationBuilder}.
     * 
     * @param diagram
     *            the current {@link DDiagram}
     */
    public CompositeFilterApplicationBuilder(DDiagram diagram) {
        this.diagram = diagram;
    }

    /**
     * Compute composite filters application (hide and collapse) for diagram
     * elements of the current diagram.
     */
    public void computeCompositeFilterApplications() {
        ICollapseUpdater collapseUpdater = CollapseUpdater.getICollapseUpdater(diagram);

        for (DDiagramElement element : diagram.getDiagramElements()) {
            List<CompositeFilterDescription> appliedHideFilters = Lists.newArrayList(Iterables.filter(FilterService.getAppliedFilters(diagram, element), CompositeFilterDescription.class));
            handleHideCompositeFilters(element, appliedHideFilters);

            boolean isDirectlycollapsed = FilterService.isCollapsed(diagram, element);
            handleCollapseCompositeFilters(element, isDirectlycollapsed, collapseUpdater);
        }
    }

    private void handleCollapseCompositeFilters(DDiagramElement element, boolean isDirectlycollapsed, ICollapseUpdater collapseUpdater) {
        DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);

        if (elementQuery.isCollapsed()) {
            updateCollapseApplication(element, isDirectlycollapsed, collapseUpdater);
        } else if (isDirectlycollapsed) {
            createCollapseApplication(element, collapseUpdater);
        }

    }

    private void handleHideCompositeFilters(final DDiagramElement element, final List<CompositeFilterDescription> appliedFilters) {
        DDiagramElementQuery elementQuery = new DDiagramElementQuery(element);

        Iterable<CompositeFilterDescription> appliedHideFilters = Iterables.filter(appliedFilters, new IsHideFilter());

        Option<AppliedCompositeFilters> appliedCompositeFilters = elementQuery.getAppliedCompositeFilters();
        if (appliedCompositeFilters.some()) {
            updateFilterApplication(element, appliedCompositeFilters.get(), Lists.newArrayList(appliedHideFilters));
        } else if (!Iterables.isEmpty(appliedHideFilters)) {
            createFilterApplication(element, Lists.newArrayList(appliedHideFilters));
        }
    }

    private void createCollapseApplication(DDiagramElement element, ICollapseUpdater collapseUpdater) {
        if (element != null) {
            // if the element was not collapsed but a new filter is activated,
            // we add a CollapseFilter on it.
            collapseUpdater.synchronizeCollapseFiltersAndGMFBounds(element, true, CollapseFilter.class);
        }
    }

    private void updateCollapseApplication(DDiagramElement element, boolean isDirectlycollapsed, ICollapseUpdater collapseUpdater) {
        if (element != null && !isDirectlycollapsed) {
            // if the element was directly collapsed but there is no more direct
            // collapse filter active on it, we remove its collapseFilter.
            collapseUpdater.synchronizeCollapseFiltersAndGMFBounds(element, false, CollapseFilter.class);
        }
    }

    private void createFilterApplication(DDiagramElement element, List<CompositeFilterDescription> appliedFilters) {
        if (element != null && appliedFilters != null && !appliedFilters.isEmpty()) {
            AppliedCompositeFilters filterApplication = DiagramFactory.eINSTANCE.createAppliedCompositeFilters();
            filterApplication.getCompositeFilterDescriptions().addAll(appliedFilters);
            element.getGraphicalFilters().add(filterApplication);
        }
    }

    private void updateFilterApplication(DDiagramElement element, AppliedCompositeFilters filterApplication, List<CompositeFilterDescription> appliedFilters) {
        if (element != null && filterApplication != null && appliedFilters != null) {
            if (appliedFilters.isEmpty()) {
                element.getGraphicalFilters().remove(filterApplication);
            } else {
                Collection<CompositeFilterDescription> filterToAdd = Lists.newArrayList(appliedFilters);
                Iterables.removeAll(filterToAdd, filterApplication.getCompositeFilterDescriptions());

                Collection<CompositeFilterDescription> filterToRemove = Lists.newArrayList(filterApplication.getCompositeFilterDescriptions());
                Iterables.removeAll(filterToRemove, appliedFilters);

                filterApplication.getCompositeFilterDescriptions().removeAll(filterToRemove);
                filterApplication.getCompositeFilterDescriptions().addAll(filterToAdd);
            }
        }
    }

    /**
     * 
     * Predicate that checks if the filter is a hide filter.
     * 
     */
    private static final class IsHideFilter implements Predicate<CompositeFilterDescription> {
        public boolean apply(CompositeFilterDescription input) {
            return new CompositeFilterDescriptionQuery(input).isHideCompositeFilter();
        }
    }
}
