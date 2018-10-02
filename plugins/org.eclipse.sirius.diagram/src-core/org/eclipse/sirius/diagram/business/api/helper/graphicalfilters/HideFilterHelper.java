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
package org.eclipse.sirius.diagram.business.api.helper.graphicalfilters;

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
     * Reveal the label of the current element.
     * 
     * @param element
     *            the element to reveal
     */
    void revealLabel(DDiagramElement element);
}
