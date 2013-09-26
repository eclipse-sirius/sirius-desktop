/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.business.api.query.CompositeFilterDescriptionQuery;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.viewpoint.description.filter.Filter;
import org.eclipse.sirius.viewpoint.description.filter.FilterDescription;
import org.eclipse.sirius.viewpoint.description.filter.FilterKind;

/**
 * This class is used whenever we need to filter elements (hide/show considering
 * specific properties).
 * 
 * @author cbrun
 * 
 */
public final class FilterService {

    private FilterService() {

    }

    /**
     * Activate the global filter cache.
     * 
     * @deprecated use {@link DisplayServiceManager#getDisplayService()} and
     *             {@link org.eclipse.sirius.business.api.helper.display.DisplayService#activateCache()}
     *             instead
     */
    @Deprecated
    public static void activateCache() {
        DisplayServiceManager.INSTANCE.getDisplayService().activateCache();
    }

    /**
     * De-activate the global filter cache.
     * 
     * @deprecated use {@link DisplayServiceManager#getDisplayService()} and
     *             {@link org.eclipse.sirius.business.api.helper.display.DisplayService#deactivateCache()}
     *             instead
     */
    @Deprecated
    public static void deactivateCache() {
        DisplayServiceManager.INSTANCE.getDisplayService().deactivateCache();
    }

    /**
     * Return if an element is visible in the diagram.
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return true if the element is visible, false otherwise
     * @deprecated use {@link DisplayServiceManager#getDisplayService()} and
     *             {@link import
     *             org.eclipse.sirius.business.api.helper.display
     *             .DisplayService#isDisplayed(DDiagram, DDiagramElement)}
     *             instead
     */
    @Deprecated
    public static boolean isVisible(final DDiagram diagram, final DDiagramElement element) {
        return DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, element);
    }

    /**
     * Check if an element or one of its parent is filtered in the diagram.
     * 
     * 
     * @deprecated Use
     *             {@link FilterService#getAppliedFilters(DDiagram, DDiagramElement)}
     *             to compute filter application,
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#isFiltered()}
     *             to know the result of this application,
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#getAppliedCompositeFilters()}
     *             to applied composite filters.
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return <code>true</code> if the element is filtered, <code>false</code>
     *         otherwise
     */
    @Deprecated
    public static boolean isFiltered(final DDiagram diagram, final DDiagramElement element) {
        return !FilterService.isVisibleForActivatedFiltersAndParentIsVisible(diagram, element);
    }

    /**
     * Check if an element is not filtered in the diagram.
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return <code>true</code> if the element is visible for filters (not
     *         filtered), <code>false</code> otherwise
     * @deprecated
     */
    @Deprecated
    public static boolean isNotFiltered(final DDiagram diagram, final DDiagramElement element) {
        return FilterService.isVisibleForActivatedFiltersAndParentIsVisible(diagram, element);
    }

    /**
     * @deprecated
     * @param diagram
     * @param element
     * @return
     */
    @Deprecated
    private static boolean isVisibleForActivatedFiltersAndParentIsVisible(final DDiagram diagram, final DDiagramElement element) {

        boolean isVisible = FilterService.isVisibleForActivatedFilters(diagram, element);

        if (isVisible) {
            final EObject eContainer = element.eContainer();
            if (eContainer instanceof DDiagramElement) {
                isVisible = FilterService.isVisibleForActivatedFiltersAndParentIsVisible(diagram, (DDiagramElement) eContainer);
            }
        }
        return isVisible;
    }

    /**
     * Check that element is visible for the activated filters. If no filter is
     * activated, all the elements is consider to be visible.
     * 
     * @deprecated Use
     *             {@link FilterService#getAppliedFilters(DDiagram, DDiagramElement)}
     *             to compute filter application,
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#isFiltered()}
     *             to know the result of this application,
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#getAppliedCompositeFilters()}
     *             to applied composite filters.
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return <code>true</code> if the element is visible for the activated
     *         filtered, <code>false</code> otherwise
     */
    @Deprecated
    public static boolean isVisibleForActivatedFilters(final DDiagram diagram, final DDiagramElement element) {
        final List<FilterDescription> filters = FilterService.sortFilters(diagram.getActivatedFilters());
        if (filters.isEmpty()) {
            return true;
        }
        return FilterService.isVisibleForFilters(filters, element);
    }

    /**
     * Check that element is filtered or not. In other words, check that the
     * element is visible for the activated filters). If no filter is activated,
     * all the elements is consider to be visible.
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return <code>true</code> if the element is filtered, <code>false</code>
     *         otherwise
     * @deprecated Use
     *             {@link FilterService#getAppliedFilters(DDiagram, DDiagramElement)}
     *             to compute filter application,
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#isFiltered()}
     *             to know the result of this application,
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#getAppliedCompositeFilters()}
     *             to applied composite filters.
     */
    @Deprecated
    public static boolean checkFilters(final DDiagram diagram, final DDiagramElement element) {
        return FilterService.isVisibleForActivatedFilters(diagram, element);
    }

    /**
     * Get the list of the activated filters applied to the element.
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return <code>true</code> if the element is visible for the activated
     *         filtered, <code>false</code> otherwise
     */
    public static List<FilterDescription> getAppliedFilters(final DDiagram diagram, final DDiagramElement element) {
        final List<FilterDescription> filters = FilterService.sortFilters(diagram.getActivatedFilters());
        if (filters.isEmpty()) {
            return Collections.emptyList();
        }
        return FilterService.getAppliedFiltersOnElement(filters, element);
    }

    /**
     * @param filters
     *            The filters to apply
     * @param element
     *            The element to check
     * @return the list of the filters applied to the element.
     */
    private static List<FilterDescription> getAppliedFiltersOnElement(final List<FilterDescription> filters, final DDiagramElement element) {
        List<FilterDescription> result = new ArrayList<FilterDescription>();
        for (FilterDescription filter : filters) {
            if (!filter.isVisible(element)) {
                result.add(filter);
            }
        }
        return result;
    }

    /**
     * @deprecated Use
     *             {@link FilterService#getAppliedFilters(DDiagram, DDiagramElement)}
     * @param filters
     *            The filters to apply
     * @param element
     *            The element to check
     * @return true is the element is visible for the filters.
     */
    @Deprecated
    private static boolean isVisibleForFilters(final List<FilterDescription> filters, final DDiagramElement element) {
        boolean result = true;
        final Iterator<FilterDescription> it = filters.iterator();
        while (it.hasNext() && result) {
            final FilterDescription filter = it.next();
            if (!filter.isVisible(element)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Tell whether an element is collapsed or not.
     * 
     * @param diagram
     *            the viewpoint.
     * @param element
     *            the element.
     * @return true if it collapsed.
     */
    public static boolean isCollapsed(final DDiagram diagram, final DDiagramElement element) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.IS_COLLAPSED_KEY);
        boolean isCollapsed = false;
        if (DisplayServiceManager.INSTANCE.getMode() != DisplayMode.ALL_IS_DISPLAYED) {
            isCollapsed = FilterService.isCollapsed(FilterService.sortFilters(diagram.getActivatedFilters()), element);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.IS_COLLAPSED_KEY);
        return isCollapsed;
    }

    /**
     * Tell whether an element is collapsed or not.
     * 
     * @param diagram
     *            the viewpoint.
     * @param element
     *            the element.
     * @return true if it collapsed.
     * @deprecated Replaced by {@link #isCollapsed(DDiagram, DDiagramElement)}
     */
    @Deprecated
    public static boolean isCollapse(final DDiagram diagram, final DDiagramElement element) {
        return FilterService.isCollapsed(diagram, element);
    }

    private static boolean isCollapsed(final Collection<FilterDescription> sortedFilters, final DDiagramElement element) {
        boolean isCollapsed = false;
        final Iterator<FilterDescription> it = sortedFilters.iterator();
        while (it.hasNext() && !isCollapsed) {
            final FilterDescription filter = it.next();
            if (filter instanceof CompositeFilterDescription) {
                CompositeFilterDescriptionQuery query = new CompositeFilterDescriptionQuery((CompositeFilterDescription) filter);
                for (Filter currentFilter : query.getCollapseFilters()) {
                    isCollapsed = FilterService.isFilterCollapsed(currentFilter, element);

                    if (isCollapsed) {
                        break;
                    }
                }
            }
        }
        return isCollapsed;
    }

    private static boolean isFilterCollapsed(final Filter filter, final DDiagramElement element) {
        return (filter.getFilterKind() == FilterKind.COLLAPSE_LITERAL) && !filter.isVisible(element);
    }

    /**
     * Sorts the filters.
     * 
     * @param filters
     *            the filters to sort.
     * @return the sorted filters.
     */
    private static List<FilterDescription> sortFilters(final Collection<FilterDescription> filters) {
        final List<FilterDescription> result = new ArrayList<FilterDescription>(filters);
        Collections.sort(result, new Comparator<FilterDescription>() {

            private boolean ownsACollapsedFilter(final FilterDescription filterDescription) {
                if (filterDescription instanceof CompositeFilterDescription) {
                    CompositeFilterDescriptionQuery query = new CompositeFilterDescriptionQuery((CompositeFilterDescription) filterDescription);
                    return !query.getCollapseFilters().isEmpty();
                }
                return false;
            }

            public int compare(final FilterDescription o1, final FilterDescription o2) {
                final FilterDescription filterDescription0 = o1;
                final FilterDescription filterDescription1 = o2;

                final boolean hasCollapse0 = ownsACollapsedFilter(filterDescription0);

                final boolean hasCollapse1 = ownsACollapsedFilter(filterDescription1);
                int result = 0;
                if (hasCollapse0 && !hasCollapse1) {
                    result = 1;
                } else if (hasCollapse1 && !hasCollapse0) {
                    result = -1;
                } else {
                    result = filterDescription0.hashCode() - filterDescription1.hashCode();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * Activate filters.
     * 
     * @deprecated
     */
    @Deprecated
    public static void activate() {
        DisplayServiceManager.INSTANCE.activateMode(DisplayMode.NORMAL);
    }

    /**
     * deactivate filters.
     * 
     * @deprecated
     */
    @Deprecated
    public static void deactivate() {
        DisplayServiceManager.INSTANCE.activateMode(DisplayMode.ALL_IS_DISPLAYED);
    }

    /**
     * Returns <code>true</code> if the service is active.
     * 
     * @return <code>true</code> if the service is active.
     * @deprecated
     */
    @Deprecated
    public static boolean isActivated() {
        return DisplayServiceManager.INSTANCE.getMode() == DisplayMode.NORMAL;
    }
}
