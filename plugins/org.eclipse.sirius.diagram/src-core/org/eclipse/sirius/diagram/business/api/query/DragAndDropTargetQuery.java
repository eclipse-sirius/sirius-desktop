/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.query;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DragAndDropTarget} as a starting point.
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
     * <strong>Warning</strong>: despite its name, this method find the
     * <strong>FISRT</strong> diagram element which matches mapping name and
     * semantic object as target.
     * 
     * <p>
     * Look for a given DDiagram element having the mapping name and a semantic
     * instance and the current {@link DragAndDropTarget} container.
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
}
