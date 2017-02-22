/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.sync.visitor;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;

/**
 * A visitor to retrieve diagram element children.
 * 
 * @author mchauvin
 */
public interface DiagramElementsHierarchyVisitor {

    /**
     * The shared instance.
     */
    DiagramElementsHierarchyVisitor INSTANCE = DefaultDiagramElementsHierarchyVisitor.init();

    /**
     * Get the children.
     * 
     * @param element
     *            the diagram element
     * @return the diagram element children
     */
    Iterable<? extends DDiagramElement> getChildren(DragAndDropTarget element);

}
