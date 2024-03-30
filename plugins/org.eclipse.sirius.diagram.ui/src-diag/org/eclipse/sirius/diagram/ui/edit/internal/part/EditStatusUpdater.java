/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES adn others.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.part.IDiagramDialectGraphicalViewer;

/**
 * Class responsible for updating the edit part status when the semantic
 * elements have been deleted outside of the diagram context.
 * 
 * @author cbrun
 * 
 */
public class EditStatusUpdater implements NotificationListener {

    private IDiagramElementEditPart host;

    /**
     * Create the updater for an editpart.
     * 
     * @param self
     *            the host editpart.
     */
    public EditStatusUpdater(IDiagramElementEditPart self) {
        this.host = self;
    }

    /**
     * {@inheritDoc}
     */
    public void notifyChanged(Notification message) {
        switch (message.getEventType()) {
        /*
         * PLEASE do not tell to refresh the diagrams on a remove event or the
         * element deletion will fail when another diagram is opened.
         */
        case Notification.SET:
        case Notification.UNSET:
        case Notification.ADD:
        case Notification.ADD_MANY:
        case Notification.MOVE:
            final Object newValue = message.getNewValue();
            if (newValue instanceof EObject) {
                final IDiagramDialectGraphicalViewer viewer = EditStatusUpdater.getViewer(host);
                if (viewer != null) {
                    EditStatusUpdater.enableRelatedAndChildren(viewer, (EObject) newValue);
                }
            }

            break;
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
            if (EditStatusUpdater.isADelete(message)) {
                final Object oldValue = message.getOldValue();
                final IDiagramDialectGraphicalViewer viewer = EditStatusUpdater.getViewer(host);
                if (viewer != null) {
                    EditStatusUpdater.disableRelatedAndChildren(viewer, (EObject) oldValue);
                }
            }
            break;
        default:
            break;
        }
    }

    private static IDiagramDialectGraphicalViewer getViewer(final IDiagramElementEditPart editPart) {
        if (editPart.isActive()) {
            final RootEditPart rootPart = editPart.getRoot();
            if (rootPart != null) {
                final EditPartViewer viewer = rootPart.getViewer();
                if (viewer instanceof IDiagramDialectGraphicalViewer) {
                    return (IDiagramDialectGraphicalViewer) viewer;
                }
            }
        }
        return null;
    }

    private static void disableRelatedAndChildren(final IDiagramDialectGraphicalViewer viewer, final EObject removedSemanticElement) {
        EditStatusUpdater.changeEnabilityForRelatedAndChildren(viewer, removedSemanticElement, false);
    }

    private static void enableRelatedAndChildren(final IDiagramDialectGraphicalViewer viewer, final EObject addedSemanticElement) {
        EditStatusUpdater.changeEnabilityForRelatedAndChildren(viewer, addedSemanticElement, true);
    }

    private static void changeEnabilityForRelatedAndChildren(final IDiagramDialectGraphicalViewer viewer, final EObject addedSemanticElement, final boolean enability) {
        EditStatusUpdater.changeEnability(viewer, addedSemanticElement, enability);
        // FIXME we should use the model accesor
        final Iterator<EObject> children = addedSemanticElement.eAllContents();
        while (children.hasNext()) {
            EditStatusUpdater.changeEnability(viewer, children.next(), enability);
        }
    }

    private static void changeEnability(final IDiagramDialectGraphicalViewer viewer, final EObject semanticElement, final boolean enability) {
        final List<IGraphicalEditPart> concernedEditParts = viewer.findEditPartsForElement(semanticElement, IGraphicalEditPart.class);
        for (final IGraphicalEditPart editPart : concernedEditParts) {
            if (editPart.isEditModeEnabled() != enability) {
                EditStatusUpdater.setEnability(editPart, enability);
            }
        }
    }

    private static void setEnability(final IGraphicalEditPart self, final boolean enability) {
        if (!enability) {
            self.disableEditMode();
        } else {
            self.enableEditMode();
        }
        // Refresh the decoration edit policy to display the
        // decorator
        final DecorationEditPolicy decorationEditPolicy = (DecorationEditPolicy) self.getEditPolicy(EditPolicyRoles.DECORATION_ROLE);
        if (decorationEditPolicy != null && self.isActive() && self.getRoot() != null) {
            /*
             * This is quite bad as it's breaking the editpart isolations, each
             * editpart and its editpolicies should be responsible for updating
             * themselves depending on their state (active or not) and on model
             * changes, others should not explicitely get them and call them !
             */
            decorationEditPolicy.refresh();
        }
        // Disable his connections
        for (var gefConnection : self.getSourceConnections()) {
            if (gefConnection instanceof ConnectionEditPart gmfConnection) {
                EditStatusUpdater.setEnability(gmfConnection, enability);
            }
        }
        for (var gefConnection : self.getTargetConnections()) {
            if (gefConnection instanceof ConnectionEditPart gmfConnection) {
                EditStatusUpdater.setEnability(gmfConnection, enability);
            }
        }
    }

    private static boolean isADelete(final Notification message) {
        boolean isADelete = false;
        final Object f = message.getFeature();

        if (f instanceof EReference && !((EReference) f).isContainment()) {
            return isADelete;
        } else {
            final Object oldValue = message.getOldValue();
            if (oldValue instanceof EObject) {
                isADelete = ((EObject) oldValue).eContainer() == null;
            }
        }
        return isADelete;
    }

}
