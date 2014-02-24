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
package org.eclipse.sirius.diagram.business.internal.componentization.mappings.visitor;

import java.util.ArrayList;
import java.util.Collection;

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
            children.addAll(((ContainerMapping) mapping).getAllContainerMappings());
            children.addAll(((ContainerMapping) mapping).getAllNodeMappings());
            children.addAll(((ContainerMapping) mapping).getAllBorderedNodeMappings());
        } else if (mapping instanceof NodeMapping) {
            children.addAll(((NodeMapping) mapping).getAllBorderedNodeMappings());
        }
        /* do nothing for edge mapping as they do not have children */
        return children;
    }
}
