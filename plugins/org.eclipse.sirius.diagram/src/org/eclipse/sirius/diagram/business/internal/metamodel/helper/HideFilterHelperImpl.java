/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Default implementation of an helper to handle HideFilter.
 * 
 * @author mporhel
 */
public final class HideFilterHelperImpl implements HideFilterHelper {

    private HideFilterHelperImpl() {
        // Do nothing...
    }

    /**
     * Create a new instance of {@link HideFilterHelperImpl}.
     * 
     * @return an instance of {@link HideFilterHelperImpl}
     */
    public static HideFilterHelperImpl init() {
        return new HideFilterHelperImpl();
    }

    /**
     * {@inheritDoc}
     * 
     * @see HideFilterHelper#hide(DDiagramElement)
     */
    public void hide(final DDiagramElement element) {
        if (new DDiagramElementQuery(element).isHidden()) {
            return;
        }

        HideFilter filter = DiagramFactory.eINSTANCE.createHideFilter();
        element.getGraphicalFilters().add(filter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see HideFilterHelper#reveal(DDiagramElement)
     */
    public void reveal(final DDiagramElement element) {
        if (!isDirectlyHidden(element)) {
            return;
        }

        final Object hidefilter = EcoreUtil.getObjectByType(element.getGraphicalFilters(), DiagramPackage.eINSTANCE.getHideFilter());
        if (hidefilter instanceof HideFilter) {
            element.getGraphicalFilters().remove(hidefilter);
        }
    }

    private boolean isDirectlyHidden(final DDiagramElement element) {
        DDiagramElementQuery query = new DDiagramElementQuery(element);
        return element != null && query.isHidden();
    }

    /**
     * {@inheritDoc}
     * 
     * @see HideFilterHelper#hideLabel(DDiagramElement)
     */
    public void hideLabel(final DDiagramElement element) {
        if (new DDiagramElementQuery(element).isLabelHidden()) {
            return;
        }

        if (element.getGraphicalFilters().stream().anyMatch(gf -> gf instanceof HideLabelFilter)) {
            // if there was already an HideLabelFilter, it was partial (not all labels were hidden for this
            // DDiagramElement) and can be removed
            HideLabelFilter filter = element.getGraphicalFilters().stream().filter(HideLabelFilter.class::isInstance).map(HideLabelFilter.class::cast).findFirst().get();
            element.getGraphicalFilters().remove(filter);
        }
        HideLabelFilter filter = DiagramFactory.eINSTANCE.createHideLabelFilter();
        element.getGraphicalFilters().add(filter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see HideFilterHelper#hide(DDiagramElement, boolean)
     */
    public void hideLabel(final DDiagramElement element, Map<EObject, List<Integer>> selectedLabelVisualIds) {
        if (new DDiagramElementQuery(element).areAllLabelsHidden(selectedLabelVisualIds.get(element))) {
            return;
        }

        HideLabelFilter filter;
        if (Iterables.any(element.getGraphicalFilters(), Predicates.instanceOf(HideLabelFilter.class))) {
            filter = element.getGraphicalFilters().stream().filter(HideLabelFilter.class::isInstance).map(HideLabelFilter.class::cast).findFirst().get();
        } else {
            filter = DiagramFactory.eINSTANCE.createHideLabelFilter();
            element.getGraphicalFilters().add(filter);
        }
        if (element instanceof DEdge && !selectedLabelVisualIds.isEmpty()) {
            filter.getHiddenLabels().addAll(selectedLabelVisualIds.get(element));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see HideFilterHelper#reveal(DDiagramElement, boolean)
     */
    public void revealLabel(final DDiagramElement element) {
        if (!new DDiagramElementQuery(element).hasAnyHiddenLabel()) {
            return;
        }

        final Object filter = EcoreUtil.getObjectByType(element.getGraphicalFilters(), DiagramPackage.eINSTANCE.getHideLabelFilter());
        if (filter instanceof HideLabelFilter) {
            element.getGraphicalFilters().remove(filter);
        }
    }

    @Override
    public void revealLabel(DDiagramElement element, Map<EObject, List<Integer>> selectedLabelVisualIds) {
        if (!new DDiagramElementQuery(element).hasAnyHiddenLabel()) {
            return;
        }

        final Object filter = EcoreUtil.getObjectByType(element.getGraphicalFilters(), DiagramPackage.eINSTANCE.getHideLabelFilter());
        if (filter instanceof HideLabelFilter) {
            HideLabelFilter hideLabelFilter = (HideLabelFilter) filter;
            hideLabelFilter.getHiddenLabels().removeAll(selectedLabelVisualIds.get(element));
            if (hideLabelFilter.getHiddenLabels().isEmpty()) {
                element.getGraphicalFilters().remove(hideLabelFilter);
            }
        }
    }

}
