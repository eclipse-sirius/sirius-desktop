/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.sync.visitor;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.api.query.DragAndDropTargetQuery;

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

    @Override
    public Iterable<? extends DDiagramElement> getChildren(final DragAndDropTarget element) {
        return new DragAndDropTargetQuery(element).getLogicalChildren();
    }
}
