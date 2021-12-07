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
package org.eclipse.sirius.diagram.business.api.helper.graphicalfilters;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.HideFilterHelperImpl;

/**
 * Helper to handle HideFilter.
 * 
 * @author mporhel
 * @since 0.9.0
 */
public interface HideFilterHelper {

    /**
     * The singleton instance of the HideFilterHelper.
     */
    HideFilterHelper INSTANCE = HideFilterHelperImpl.init();

    /**
     * Hide the current element.
     * 
     * @param element
     *            the element to hide
     */
    void hide(DDiagramElement element);

    /**
     * Reveal the current element.
     * 
     * @param element
     *            the element to reveal
     */
    void reveal(DDiagramElement element);

    /**
     * Hide the label of the current element.
     * 
     * @param element
     *            the element to hide
     */
    void hideLabel(DDiagramElement element);

    /**
     * Hide the label of the current element.
     * 
     * @param element
     *            the element to hide
     * @param selectedLabelVisualIds
     *            the visual IDs of the edge labels to hide sorted by edge
     */
    void hideLabel(DDiagramElement element, Map<EObject, List<Integer>> selectedLabelVisualIds);

    /**
     * Reveal the label of the current element.
     * 
     * @param element
     *            the element to reveal
     */
    void revealLabel(DDiagramElement element);

    /**
     * Reveal the label of the current element.
     * 
     * @param element
     *            the element to reveal
     * @param selectedLabelVisualIds
     *            the visual IDs of the edge labels to reveal sorted by edge
     */
    void revealLabel(DDiagramElement element, Map<EObject, List<Integer>> selectedLabelVisualIds);
}
