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
package org.eclipse.sirius.diagram.business.api.helper.display;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;

/**
 * .
 * 
 * @author mchauvin
 */
public interface DisplayService {

    /**
     * Activate the global cache.
     */
    void activateCache();

    /**
     * Deactivate the global cache.
     */
    void deactivateCache();

    /**
     * Check if an element is displayed in the diagram.
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return true if the element is visible, false otherwise
     */
    boolean isDisplayed(DDiagram diagram, DDiagramElement element);

    /**
     * Check if an element has to be displayed in the diagram.
     * 
     * @param session
     *            the current session.
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return true if the element is visible, false otherwise
     */
    boolean computeVisibility(DiagramMappingsManager session, DDiagram diagram, DDiagramElement element);

    /**
     * Check if the label of this element has to be displayed in the diagram
     * (only available for label on border).
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return true if the label of element must be visible, false otherwise
     * @since 0.9.0
     */
    boolean computeLabelVisibility(DDiagram diagram, DDiagramElement element);

    /**
     * Refresh visibility of given diagram elements.
     * 
     * @param diagram
     *            the given diagram.
     * @since 0.9.0
     */
    void refreshAllElementsVisibility(DDiagram diagram);

}
