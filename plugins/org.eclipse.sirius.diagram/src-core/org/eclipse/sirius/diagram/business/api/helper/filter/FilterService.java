/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.helper.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.query.CompositeFilterDescriptionQuery;
import org.eclipse.sirius.diagram.business.internal.helper.filter.VariableFilterWrapper;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * This class is used whenever we need to filter elements (hide/show considering specific properties).
 * 
 * @author cbrun
 * 
 */
public final class FilterService {

    private FilterService() {

    }

    /**
     * Get the list of the activated filters applied to the element.
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return <code>true</code> if the element is visible for the activated filtered, <code>false</code> otherwise
     */
    public static List<FilterDescription> getAppliedFilters(final DDiagram diagram, final DDiagramElement element) {
        final List<FilterDescription> filters = diagram.getActivatedFilters();
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
            if (!isVisible(filter, element)) {
                result.add(filter);
            }
        }
        return result;
    }

    private static boolean isVisible(FilterDescription filterDescription, final DDiagramElement element) {
        if (filterDescription instanceof CompositeFilterDescription) {
            CompositeFilterDescription compositeFilterDescription = (CompositeFilterDescription) filterDescription;
            /* Here we delegate to all the contained filters */
            final Iterator<Filter> it = compositeFilterDescription.getFilters().iterator();
            while (it.hasNext()) {
                final Filter filter = it.next();
                /*
                 * element should be invisible and filter kind should be hide => when collapse element is visible
                 */
                if (!isVisible(filter, element) && filter.getFilterKind() == FilterKind.HIDE_LITERAL) {
                    return false;
                }
            }
            return true;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private static boolean checkExpression(final Filter filter, EObject element, final EStructuralFeature eFeature) {
        final IInterpreter acceleoInterpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element);
        boolean valid = true;
        final String expression = (String) filter.eGet(eFeature);
        if (expression != null) {
            try {
                valid = acceleoInterpreter.evaluateBoolean(element, expression);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(filter, eFeature, e);
            }
        }
        return valid;

    }

    private static boolean isVisible(VariableFilter filter, final DDiagramElement element) {
        Optional<VariableFilterWrapper> variableFilterWrapper = element.eAdapters().stream().filter(VariableFilterWrapper.class::isInstance).map(VariableFilterWrapper.class::cast).findFirst();
        if (variableFilterWrapper.isPresent()) {
            return variableFilterWrapper.get().isVisible(element);
        } else {
            // we add a wrapper adapter that avoid recomputing variables. It is an adapter to have easy access to it
            // from the filter.
            VariableFilterWrapper newVariableFilterWrapper = new VariableFilterWrapper(filter);
            filter.eAdapters().add(newVariableFilterWrapper);
            return newVariableFilterWrapper.isVisible(element);
        }
    }

    private static boolean isVisible(MappingFilter filter, final DDiagramElement element) {
        Boolean visible = null;
        /*
         * If there is no mapping then we don't use them
         */
        if (filter.getMappings().size() == 0) {
            if (filter.getSemanticConditionExpression() != null && !filter.getSemanticConditionExpression().trim().isEmpty()) {
                if (!checkExpression(filter, element, FilterPackage.eINSTANCE.getMappingFilter_SemanticConditionExpression())) {
                    visible = Boolean.FALSE;
                }
            }
            if (filter.getViewConditionExpression() != null && !filter.getViewConditionExpression().trim().isEmpty()) {
                if (!checkExpression(filter, element, FilterPackage.eINSTANCE.getMappingFilter_ViewConditionExpression())) {
                    visible = Boolean.FALSE;
                }
            }
        } else {
            final RepresentationElementMapping mapping = element.getMapping();
            if (mapping != null && doManage(filter, mapping)) {
                final boolean hasSemanticExpression = filter.getSemanticConditionExpression() != null && !filter.getSemanticConditionExpression().trim().isEmpty();
                final boolean hasViewExpression = filter.getViewConditionExpression() != null && !filter.getViewConditionExpression().trim().isEmpty();
                if (!hasSemanticExpression && !hasViewExpression) {
                    visible = Boolean.FALSE;
                }
                if (hasSemanticExpression) {
                    final EObject semantic = element.getTarget();
                    if (semantic != null && !checkExpression(filter, semantic, FilterPackage.eINSTANCE.getMappingFilter_SemanticConditionExpression())) {
                        visible = Boolean.FALSE;
                    }
                }
                if (hasViewExpression) {
                    if (!checkExpression(filter, element, FilterPackage.eINSTANCE.getMappingFilter_ViewConditionExpression())) {
                        visible = Boolean.FALSE;
                    }
                }

            }
        }
        if (visible == null) {
            visible = Boolean.TRUE;
        }
        return visible.booleanValue();
    }

    /**
     * Returns true if the given element is visible regarding the given filter. False otherwise.
     * 
     * @param filter
     *            the filter used to check visibility.
     * @param element
     *            the element from which visibility is tested.
     * @return true if the given element is visible regarding the given filter. False otherwise.
     */
    public static boolean isVisible(Filter filter, final DDiagramElement element) {
        if (filter instanceof VariableFilter) {
            return isVisible((VariableFilter) filter, element);
        } else if (filter instanceof MappingFilter) {
            return isVisible((MappingFilter) filter, element);
        }
        throw new UnsupportedOperationException();
    }

    private static boolean doManage(MappingFilter filter, EObject mapping) {
        boolean result = false;
        if (filter.getMappings().contains(mapping)) {
            result = true;
        } else {
            EObject importedMapping = getImportedMapping(mapping);
            if (importedMapping != null) {
                result = doManage(filter, importedMapping) && isInheritsAncestorFilters(mapping);
            }
        }
        return result;
    }

    private static EObject getImportedMapping(EObject mapping) {
        EObject result = null;
        if (mapping instanceof NodeMappingImport) {
            result = ((NodeMappingImport) mapping).getImportedMapping();
        } else if (mapping instanceof ContainerMappingImport) {
            result = ((ContainerMappingImport) mapping).getImportedMapping();
        } else if (mapping instanceof EdgeMappingImport) {
            result = ((EdgeMappingImport) mapping).getImportedMapping();
        }
        return result;
    }

    private static boolean isInheritsAncestorFilters(EObject mapping) {
        boolean result = false;
        if (mapping instanceof AbstractMappingImport) {
            result = ((AbstractMappingImport) mapping).isInheritsAncestorFilters();
        } else if (mapping instanceof EdgeMappingImport) {
            result = ((EdgeMappingImport) mapping).isInheritsAncestorFilters();
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
            isCollapsed = FilterService.isCollapsed(diagram.getActivatedFilters(), element);
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.IS_COLLAPSED_KEY);
        return isCollapsed;
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
        return (filter.getFilterKind() == FilterKind.COLLAPSE_LITERAL) && !isVisible(filter, element);
    }

    /**
     * Sorts the filters.
     * 
     * @param filters
     *            the filters to sort.
     * @return a new list with the sorted filters.
     */
    public static List<FilterDescription> sortFilters(final Collection<FilterDescription> filters) {
        final List<FilterDescription> result = new ArrayList<FilterDescription>(filters);
        Collections.sort(result, new Comparator<FilterDescription>() {

            private boolean ownsACollapsedFilter(final FilterDescription filterDescription) {
                if (filterDescription instanceof CompositeFilterDescription) {
                    CompositeFilterDescriptionQuery query = new CompositeFilterDescriptionQuery((CompositeFilterDescription) filterDescription);
                    return !query.getCollapseFilters().isEmpty();
                }
                return false;
            }

            @Override
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
}
