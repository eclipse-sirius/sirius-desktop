/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.business.api.helper.graphicalfilters.HideFilterHelper;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;

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
     * @see HideFilterHelper#hide(DDiagramElement, boolean)
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
     * @see HideFilterHelper#reveal(DDiagramElement, boolean)
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
     * @see HideFilterHelper#hide(DDiagramElement, boolean)
     */
    public void hideLabel(final DDiagramElement element) {
        if (new DDiagramElementQuery(element).isLabelHidden()) {
            return;
        }

        HideLabelFilter filter = DiagramFactory.eINSTANCE.createHideLabelFilter();
        element.getGraphicalFilters().add(filter);
    }

    /**
     * {@inheritDoc}
     * 
     * @see HideFilterHelper#reveal(DDiagramElement, boolean)
     */
    public void revealLabel(final DDiagramElement element) {
        if (!new DDiagramElementQuery(element).isLabelHidden()) {
            return;
        }

        final Object filter = EcoreUtil.getObjectByType(element.getGraphicalFilters(), DiagramPackage.eINSTANCE.getHideLabelFilter());
        if (filter instanceof HideLabelFilter) {
            element.getGraphicalFilters().remove(filter);
        }
    }
}
