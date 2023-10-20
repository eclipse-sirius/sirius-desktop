/*******************************************************************************
 * Copyright (c) 2018, 2023 Obeo
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

import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeListCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.MovePinnedElementsAction;
import org.eclipse.sirius.ext.base.Option;

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
        if (editPart instanceof IDiagramElementEditPart || editPart instanceof AbstractDNodeContainerCompartmentEditPart || editPart instanceof AbstractDNodeListCompartmentEditPart) {
            effectiveDiagramEditPart = getDiagramEditPart(editPart);
        } else if (editPart instanceof IDDiagramEditPart) {
            effectiveDiagramEditPart = (IDDiagramEditPart) editPart;
        }
        if (effectiveDiagramEditPart != null) {
            Option<DDiagram> ddiagram = effectiveDiagramEditPart.resolveDDiagram();
            if (ddiagram.some()) {
                return Optional.of(ddiagram.get());
            }
        }
        return Optional.empty();
    }

    /**
     * Determine if the current edit part is considered as pinned (or at least to be kept fixed) in case of automatic
     * layout.
     * 
     * @param elementsToNotMove
     *            IDiagramElementEditPart which are not actually pinned but have to stay fixed (for other reasons).
     * 
     * @return <code>true</code> if the current edit part can be moved by automatic layout, false otherwise.
     */
    public boolean isMovableByAutomaticLayout(List<IDiagramElementEditPart> elementsToNotMove) {
        boolean isMovableByAutomaticLayout = true;
        if (!(editPart instanceof SiriusNoteEditPart)) {
            if (editPart.resolveSemanticElement() instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = (DDiagramElement) editPart.resolveSemanticElement();
                isMovableByAutomaticLayout = MovePinnedElementsAction.getValue() || !(new PinHelper().isPinned(dDiagramElement) || (elementsToNotMove != null && elementsToNotMove.contains(editPart)));
            }
        } else {
            if (!Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false, null)) {
                isMovableByAutomaticLayout = false;
            } else {
                boolean connectedToPinnedElement = false;
                Iterable<ConnectionEditPart> filterSourceConnections = () -> editPart.getSourceConnections().stream().filter(ConnectionEditPart.class::isInstance).map(ConnectionEditPart.class::cast)
                        .iterator();
                for (ConnectionEditPart sourceConn : filterSourceConnections) {
                    if (sourceConn.getTarget() instanceof IGraphicalEditPart) {
                        connectedToPinnedElement = connectedToPinnedElement || !(new EditPartQuery((IGraphicalEditPart) sourceConn.getTarget()).isMovableByAutomaticLayout(elementsToNotMove));
                    }
                }
                Iterable<ConnectionEditPart> filterTargetConnections = () -> editPart.getTargetConnections().stream().filter(ConnectionEditPart.class::isInstance).map(ConnectionEditPart.class::cast)
                        .iterator();
                for (ConnectionEditPart targetConn : filterTargetConnections) {
                    if (targetConn.getSource() instanceof IGraphicalEditPart) {
                        connectedToPinnedElement = connectedToPinnedElement || !(new EditPartQuery((IGraphicalEditPart) targetConn.getSource()).isMovableByAutomaticLayout(elementsToNotMove));
                    }
                }
                if (connectedToPinnedElement) {
                    isMovableByAutomaticLayout = false;
                }
            }
        }
        return isMovableByAutomaticLayout;
    }

    /**
     * Return if the current edit part is a {@link NoteEditPart} and is linked to at least one other EditPart.
     * 
     * @return <code>true</code> if the current edit part is a {@link NoteEditPart} and is linked to at least one other
     *         EditPart, false otherwise.
     */
    public boolean isANoteLinkedToOtherEditPart() {
        boolean connectedToElement = false;
        if (editPart instanceof NoteEditPart) {
            connectedToElement = editPart.getSourceConnections().size() > 0 || editPart.getTargetConnections().size() > 0;
        }
        return connectedToElement;
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

    /**
     * Get the pinned status of the associated semantic element {@link DDiagramElement} of the given
     * {@link IGraphicalEditPart}. The pinned status is defined by having the following {@link ArrangeConstraint}
     * through {@link AbstractDNode#getArrangeConstraints()} or {@link DEdge#getArrangeConstraints()} :
     * 
     * <ol>
     * <li>{@link ArrangeConstraint#KEEP_LOCATION}</li>
     * <li>{@link ArrangeConstraint#KEEP_SIZE}</li>
     * <li>{@link ArrangeConstraint#KEEP_RATIO}</li>
     * </ol>
     * 
     * The pinned status can be masked by the Move Pinned Elements preference. If this configuration is enabled, this
     * method always returns unpinned.
     * 
     * @return <code>true</code> if the associated {@link DDiagramElement} is pinned, false else.
     */
    public boolean isPinned() {
        String prefKey = SiriusDiagramPreferencesKeys.PREF_MOVE_PINNED_ELEMENTS.name();
        boolean ignorePin = Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, prefKey, false, null);
        if (ignorePin) {
            return false;
        } else {
            boolean isPinned = false;
            if (editPart.resolveSemanticElement() instanceof DDiagramElement dDiagramElement) {
                isPinned = new PinHelper().isPinned(dDiagramElement);
            }
            return isPinned;
        }
    }
}
