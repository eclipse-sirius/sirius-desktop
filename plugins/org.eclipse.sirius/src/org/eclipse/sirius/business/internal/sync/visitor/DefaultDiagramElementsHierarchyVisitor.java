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
package org.eclipse.sirius.business.internal.sync.visitor;

import java.util.Collections;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeContainer;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.DragAndDropTarget;

/**
 * The default visitor to retrieve diagram elements children.
 * 
 * @author mchauvin
 */
public final class DefaultDiagramElementsHierarchyVisitor implements DiagramElementsHierarchyVisitor {

    private DefaultDiagramElementsHierarchyVisitor() {
    }

    /**
     * Create a new instance.
     * 
     * @return a newly created instance
     */
    public static DefaultDiagramElementsHierarchyVisitor init() {
        return new DefaultDiagramElementsHierarchyVisitor();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.sync.visitor.DiagramElementsHierarchyVisitor#getChildren(org.eclipse.emf.ecore.EObject)
     */
    public Iterable<? extends DDiagramElement> getChildren(final DragAndDropTarget element) {

        Iterable<? extends DDiagramElement> children;

        if (element instanceof DDiagram) {
            children = Iterables.concat(((DDiagram) element).getOwnedDiagramElements(), ((DDiagram) element).getEdges());

        } else if (element instanceof DNodeContainer) {
            children = Iterables.concat(((DNodeContainer) element).getOwnedDiagramElements(), ((DNodeContainer) element).getOwnedBorderedNodes());

        } else if (element instanceof DNodeList) {
            children = Iterables.concat(((DNodeList) element).getElements(), ((DNodeList) element).getOwnedBorderedNodes());

        } else if (element instanceof DNode) {
            children = ((DNode) element).getOwnedBorderedNodes();
        } else {
            children = Collections.emptySet();
        }
        return children;
    }
}
