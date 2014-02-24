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
import org.eclipse.sirius.diagram.business.internal.componentization.mappings.DiagramDescriptionMappingsRegistryImpl;
import org.eclipse.sirius.diagram.description.DiagramDescription;

/**
 * A registry to access mappings available depending on viewpoint selection.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface DiagramDescriptionMappingsRegistry {

    /** The shared instance. */
    DiagramDescriptionMappingsRegistry INSTANCE = DiagramDescriptionMappingsRegistryImpl.init();

    /**
     * Get the mappings manager for this session and this diagram description.
     * 
     * @param session
     *            the current session
     * @param description
     *            the diagram description
     * @return the associated mappings manager
     */
    DiagramDescriptionMappingsManager getDiagramDescriptionMappingsManager(final Session session, final DiagramDescription description);

    /**
     * Compute the available mappings for all
     * {@link DiagramDescriptionMappingsManager}.
     */
    void computeMappings();
}
