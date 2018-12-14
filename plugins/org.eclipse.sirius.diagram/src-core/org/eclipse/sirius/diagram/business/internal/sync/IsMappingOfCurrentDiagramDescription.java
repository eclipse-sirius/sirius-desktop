/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.sync;

import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContentHelper;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;

import com.google.common.base.Predicate;

/**
 * {@link Predicate} to checks if a {@link DiagramElementMapping} is owned by a specified {@link DiagramDescription}.
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
     * Overridden to checks that a {@link DiagramElementMapping} is owned by a {@link DiagramDescription}.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean apply(DiagramElementMapping diagramElementMapping) {
        return diagramDescription.getAllEdgeMappings().contains(diagramElementMapping) || ContentHelper.getAllNodeMappings(diagramDescription, false).contains(diagramElementMapping)
                || ContentHelper.getAllContainerMappings(diagramDescription, false).contains(diagramElementMapping);
    }
}
