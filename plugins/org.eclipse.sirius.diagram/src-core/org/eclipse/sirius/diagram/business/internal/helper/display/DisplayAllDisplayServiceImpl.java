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
package org.eclipse.sirius.diagram.business.internal.helper.display;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayService;

/**
 * An implementation of {@link DisplayService} which return always true.
 * 
 * @author mchauvin
 */
public final class DisplayAllDisplayServiceImpl implements DisplayService {

    /**
     * Avoid instantiation.
     */
    private DisplayAllDisplayServiceImpl() {

    }

    /**
     * Initialize a new {@link DisplayService}.
     * 
     * @return a new created instance
     */
    public static DisplayService init() {
        return new DisplayAllDisplayServiceImpl();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#activateCache()
     */
    public void activateCache() {
        /* do nothing */
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#deactivateCache()
     */
    public void deactivateCache() {
        /* do nothing */
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#isDisplayed(DDiagram,
     *      DDiagramElement)
     */
    public boolean isDisplayed(final DDiagram diagram, final DDiagramElement element) {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#computeVisibility(DiagramMappingsManager,
     *      DDiagram, DDiagramElement)
     */
    public boolean computeVisibility(DiagramMappingsManager session, final DDiagram diagram, final DDiagramElement element) {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.helper.display.DisplayService#computeLabelVisibility(DDiagram,
     *      DDiagramElement)
     */
    public boolean computeLabelVisibility(DDiagram diagram, DDiagramElement element) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void refreshAllElementsVisibility(final DDiagram diagram) {
        // do nothing
    }

}
