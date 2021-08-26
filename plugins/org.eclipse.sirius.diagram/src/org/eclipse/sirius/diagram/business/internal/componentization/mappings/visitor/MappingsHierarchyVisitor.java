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
package org.eclipse.sirius.diagram.business.internal.componentization.mappings.visitor;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.MappingHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;

/**
 * A visitor pattern to retrieve mappings children.
 * 
 * @author mchauvin
 */
public class MappingsHierarchyVisitor {

    /**
     * Get the children mappings.
     * 
     * @param mapping
     *            the mapping
     * @return .
     */
    public Collection<? extends DiagramElementMapping> getChildren(final DiagramElementMapping mapping) {
        final Collection<DiagramElementMapping> children = new ArrayList<DiagramElementMapping>();
        if (mapping instanceof ContainerMapping) {
            children.addAll(MappingHelper.getAllContainerMappings((ContainerMapping) mapping));
            children.addAll(MappingHelper.getAllNodeMappings((ContainerMapping) mapping));
            children.addAll(MappingHelper.getAllBorderedNodeMappings((ContainerMapping) mapping));
        } else if (mapping instanceof NodeMapping) {
            children.addAll(MappingHelper.getAllBorderedNodeMappings((NodeMapping) mapping));
        }
        /* do nothing for edge mapping as they do not have children */
        return children;
    }
}
