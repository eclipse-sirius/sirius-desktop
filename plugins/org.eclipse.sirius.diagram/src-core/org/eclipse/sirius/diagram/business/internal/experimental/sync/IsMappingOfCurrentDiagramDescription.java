/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.experimental.sync;

import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;

import com.google.common.base.Predicate;

/**
 * {@link Predicate} to checks if a {@link DiagramElementMapping} is owned by a
 * specified {@link DiagramDescription}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class IsMappingOfCurrentDiagramDescription implements Predicate<DiagramElementMapping> {

    private DiagramDescription diagramDescription;

    /**
     * Default constructor to specify the {@link DiagramDescription}.
     * 
     * @param diagramDescription
     *            the {@link DiagramDescription}
     */
    public IsMappingOfCurrentDiagramDescription(DiagramDescription diagramDescription) {
        this.diagramDescription = diagramDescription;
    }

    /**
     * Overridden to checks that a {@link DiagramElementMapping} is owned by a
     * {@link DiagramDescription}.
     * 
     * {@inheritDoc}
     */
    public boolean apply(DiagramElementMapping diagramElementMapping) {
        return diagramDescription.getAllEdgeMappings().contains(diagramElementMapping) || diagramDescription.getAllNodeMappings().contains(diagramElementMapping)
                || diagramDescription.getAllContainerMappings().contains(diagramElementMapping);
    }
}
