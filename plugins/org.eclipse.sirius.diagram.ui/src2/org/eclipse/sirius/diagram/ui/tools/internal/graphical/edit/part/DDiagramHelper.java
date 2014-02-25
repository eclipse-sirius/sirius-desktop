/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;

import com.google.common.collect.Iterables;

/**
 * {@link DDiagram} helpers.
 * 
 * @author dlecan
 */
public final class DDiagramHelper {

    /**
     * Constructor.
     */
    private DDiagramHelper() {
        // Nothing
    }

    /**
     * Find the parent {@link DDiagram}.
     * 
     * @param editPart
     *            Edit part.
     * @return The parent diagram or <code>null</code> if not found.
     */
    public static DDiagram findParentDDiagram(final IGraphicalEditPart editPart) {
        DDiagram result = null;
        final IDDiagramEditPart parentDiagram = DDiagramHelper.findParentDiagram(editPart);
        if (parentDiagram != null && editPart.isActive()) {
            result = parentDiagram.resolveDDiagram().get();
        }
        return result;
    }

    /**
     * Find the parent diagram.
     * 
     * @param element
     *            Edit part.
     * @return The parent diagram or <code>null</code> if not found.
     */
    public static IDDiagramEditPart findParentDiagram(final EditPart element) {
        IDDiagramEditPart result = null;
        if (element instanceof IDDiagramEditPart) {
            result = (IDDiagramEditPart) element;
        } else if (element instanceof DDiagramRootEditPart) {
            Iterable<IDDiagramEditPart> ddiagramChildren = Iterables.filter(element.getChildren(), IDDiagramEditPart.class);
            if (!Iterables.isEmpty(ddiagramChildren)) {
                result = ddiagramChildren.iterator().next();
            }
        } else if (element != null) {
            result = DDiagramHelper.findParentDiagram(element.getParent());
        }
        return result;
    }

}
