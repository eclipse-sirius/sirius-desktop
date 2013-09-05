/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper.graphicalfilters;

import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.business.internal.metamodel.helper.HideFilterHelperImpl;

/**
 * Helper to handle HideFilter.
 * 
 * @author mporhel
 * @since 2.4
 */
public interface HideFilterHelper {

    /**
     * The singleton instance of the HideFilterHelper.
     */
    HideFilterHelper INSTANCE = HideFilterHelperImpl.init();

    /**
     * Check if an element has been hidden by an hide action.
     * 
     * @deprecated Use
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#isHidden()}
     * @param element
     *            the element
     * @return <code>true</code> if the element is hidden by a HideFilter,
     *         <code>false</code> otherwise
     */
    @Deprecated
    boolean isDirectlyHidden(final DDiagramElement element);

    /**
     * Check if an element has been hidden or is hidden thanks to another
     * element (container in case of {@link org.eclipse.sirius.AbstractDNode}
     * , source or target in case of {@link org.eclipse.sirius.DEdge}.
     * 
     * @deprecated Use
     *             {@link org.eclipse.sirius.business.api.query.DDiagramElementQuery#isIndirectlyHidden()}
     * @param element
     *            the element
     * @return <code>true</code> if the element is hidden by a HideFilter,
     *         <code>false</code> otherwise
     */
    @Deprecated
    boolean isIndirectlyHidden(final DDiagramElement element);

    /**
     * Hide the current element.
     * 
     * @param element
     *            the element to hide
     */
    void hide(final DDiagramElement element);

    /**
     * Reveal the current element.
     * 
     * @param element
     *            the element to reveal
     */
    void reveal(final DDiagramElement element);

    /**
     * Hide the label of the current element.
     * 
     * @param element
     *            the element to hide
     */
    void hideLabel(final DDiagramElement element);

    /**
     * Reveal the label of the current element.
     * 
     * @param element
     *            the element to reveal
     */
    void revealLabel(final DDiagramElement element);
}
