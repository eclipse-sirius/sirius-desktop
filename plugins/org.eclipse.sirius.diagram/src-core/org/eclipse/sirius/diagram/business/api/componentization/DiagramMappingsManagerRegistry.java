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
package org.eclipse.sirius.diagram.business.api.componentization;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.DiagramMappingsManagerRegistryImpl;

/**
 * This class will move in diagram plug-in when refresh refactoring will occurs.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface DiagramMappingsManagerRegistry {

    /**
     * The shared instance.
     */
    DiagramMappingsManagerRegistry INSTANCE = DiagramMappingsManagerRegistryImpl.init();

    /**
     * Get the diagram mappings manager.
     * 
     * @param session
     *            the current session
     * @param diagram
     *            the diagram for which to retrieve the mappings manager
     * @return the manager to retrieve available mappings for a given diagram
     */
    DiagramMappingsManager getDiagramMappingsManager(Session session, DDiagram diagram);

    /**
     * Remove the given diagram mappings manager, forcing them to be recomputed
     * when needed later.
     * 
     * 
     * @param manager
     *            the diagram mappings manager to remove
     */
    void removeDiagramMappingsManagers(DiagramMappingsManager manager);

}
