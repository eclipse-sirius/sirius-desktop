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
package org.eclipse.sirius.business.api.helper.display;

import org.eclipse.sirius.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;

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
    boolean isDisplayed(final DDiagram diagram, final DDiagramElement element);

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
    boolean computeVisibility(DiagramMappingsManager session, final DDiagram diagram, final DDiagramElement element);
    
    /**
     * Check if an element has to be displayed in the diagram.
     * 
     * This method is deprecated, instead of passing the session you should use the other method taking the DiagramMappingsManager as a parameter.
     * @param session
     *            the current session.
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return true if the element is visible, false otherwise
     * @since 2.4
     */
    @Deprecated
    boolean computeVisibility(Session session, final DDiagram diagram, final DDiagramElement element);

    /**

    /**
     * Check if the label of this element has to be displayed in the diagram
     * (only available for label on border).
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return true if the label of element must be visible, false otherwise
     * @since 2.13
     */
    boolean computeLabelVisibility(final DDiagram diagram, final DDiagramElement element);

    /**
     * Refresh visibility of given diagram elements.
     * 
     * @param diagram
     *            the given diagram.
     * @since 2.4
     */
    void refreshAllElementsVisibility(final DDiagram diagram);

}
