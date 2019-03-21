/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.api.query;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

/**
 * A class aggregating all the queries (read-only!) having an {@link IGraphicalEditPart} as a starting point.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public final class EditPartQuery {

    private IGraphicalEditPart editPart;

    /**
     * Create a new query.
     * 
     * @param editPart
     *            the {@link IGraphicalEditPart} to query.
     */
    public EditPartQuery(IGraphicalEditPart editPart) {
        this.editPart = editPart;
    }

    /**
     * Return the {@link DiagramDescription} associated to the edit part or to one of its ancestor.
     * 
     * @return the {@link DiagramDescription} associated to the edit part or to one of its ancestor. Null if no such
     *         element exists.
     */
    public DiagramDescription getDiagramDescription() {
        Optional<DDiagram> optionalDDiagram = getDDiagram();
        if (optionalDDiagram.isPresent()) {
            return optionalDDiagram.get().getDescription();
        }

        return null;
    }

    /**
     * Return the {@link DDiagram} associated to the edit part or to one of its ancestor.
     * 
     * @return the {@link DDiagram} associated to the edit part or to one of its ancestor. Empty optional if no such
     *         element exists.
     */
    public Optional<DDiagram> getDDiagram() {
        IDDiagramEditPart effectiveDiagramEditPart = null;
        if (editPart instanceof IDiagramElementEditPart) {
            effectiveDiagramEditPart = getDiagramEditPart(editPart);
        } else if (editPart instanceof IDDiagramEditPart) {
            effectiveDiagramEditPart = (IDDiagramEditPart) editPart;
        }
        if (effectiveDiagramEditPart != null) {
            EObject resolvedSemanticElement = effectiveDiagramEditPart.resolveSemanticElement();
            if (resolvedSemanticElement instanceof DDiagram) {
                return Optional.of((DDiagram) resolvedSemanticElement);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns the {@link IDDiagramEditPart} that is an ancestor of the given {@link EditPart}.
     * 
     * @param editPart
     *            the edit part from which we look for an {@link IDDiagramEditPart} ancestor.
     * @return the {@link IDDiagramEditPart} that is an ancestor of the given {@link EditPart}. Null if no such element
     *         exists.
     */
    private IDDiagramEditPart getDiagramEditPart(EditPart theEditPart) {
        IDDiagramEditPart result = null;
        EditPart parent = theEditPart.getParent();
        if (parent instanceof IDDiagramEditPart) {
            result = (IDDiagramEditPart) parent;
        } else if (parent != null) {
            result = getDiagramEditPart(parent);
        }
        return result;
    }
}
