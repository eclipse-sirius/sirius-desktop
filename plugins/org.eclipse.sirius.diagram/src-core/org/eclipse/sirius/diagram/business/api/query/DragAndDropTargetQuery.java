/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Iterables;

/**
 * A class aggregating all the queries (read-only!) having a {@link DragAndDropTarget} as a starting point.
 * 
 * @author mporhel
 * 
 */
public class DragAndDropTargetQuery {

    private DragAndDropTarget dndTarget;

    /**
     * Create a new query.
     * 
     * @param dndTarget
     *            the element to query.
     */
    public DragAndDropTargetQuery(DragAndDropTarget dndTarget) {
        this.dndTarget = dndTarget;
    }
    
    /**
     * Static factory to create a new query on a particular element.
     * 
     * @param dndTarget
     *            the element to query.
     * @return a query on the dndTarget.
     */
    public static DragAndDropTargetQuery on(DragAndDropTarget dndTarget) {
        return new DragAndDropTargetQuery(dndTarget);
    }


    /**
     * <strong>Warning</strong>: despite its name, this method find the <strong>FISRT</strong> diagram element which
     * matches mapping name and semantic object as target.
     * 
     * <p>
     * Look for a given DDiagram element having the mapping name and a semantic instance and the current
     * {@link DragAndDropTarget} container.
     * </p>
     * 
     * @param mappingName
     *            mapping name of the searched element.
     * @param object
     *            semantic target of the searched element.
     * @return the corresponding {@link DDiagramElement} if found.
     */
    public Option<DDiagramElement> findDesignerDiagramElement(final String mappingName, final EObject object) {
        final Iterator<EObject> it = dndTarget.eContents().iterator();
        while (it.hasNext()) {
            final EObject elem = it.next();
            if (elem instanceof DDiagramElement) {
                final Option<String> name = new DDiagramElementQuery((DDiagramElement) elem).getMappingName();
                if (name != null && name.some() && name.get().equals(mappingName)) {
                    if (((DDiagramElement) elem).getTarget() == object) {
                        return Options.newSome((DDiagramElement) elem);
                    }
                }
            }
        }
        return Options.newNone();
    }

    /**
     * Returns all the logical children elements of the target.
     * 
     * @return the logical children elements of the target.
     */
    public Iterable<? extends DDiagramElement> getLogicalChildren() {
        Iterable<? extends DDiagramElement> children;
        if (dndTarget instanceof DDiagram) {
            children = Iterables.concat(((DDiagram) dndTarget).getOwnedDiagramElements(), ((DDiagram) dndTarget).getEdges());
        } else if (dndTarget instanceof DNodeContainer) {
            children = Iterables.concat(((DNodeContainer) dndTarget).getOwnedDiagramElements(), ((DNodeContainer) dndTarget).getOwnedBorderedNodes());
        } else if (dndTarget instanceof DNodeList) {
            children = Iterables.concat(((DNodeList) dndTarget).getElements(), ((DNodeList) dndTarget).getOwnedBorderedNodes());
        } else if (dndTarget instanceof DNode) {
            children = ((DNode) dndTarget).getOwnedBorderedNodes();
        } else {
            children = Collections.emptySet();
        }
        return children;
    }
}
