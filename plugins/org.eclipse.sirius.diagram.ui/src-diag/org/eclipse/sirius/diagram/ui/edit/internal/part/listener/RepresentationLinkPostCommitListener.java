/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.part.listener;

import static org.eclipse.emf.transaction.NotificationFilter.createFeatureFilter;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Triggers refresh of this diagram's representation links when appropriate.
 */
public class RepresentationLinkPostCommitListener extends ResourceSetListenerImpl {

    private DDiagramEditorImpl diagramEditor;

    /**
     * Default constructor.
     * 
     * @param dDiagramEditorImpl
     *            listened part.
     */
    public RepresentationLinkPostCommitListener(DDiagramEditorImpl dDiagramEditorImpl) {
        // Representation links must be refreshed not only when the target representation
        // is deleted, but also when the target representation is added after
        // undoing a deletion
        super(createFeatureFilter(ViewpointPackage.Literals.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS).and(NotificationFilter.NOT_TOUCH));
        diagramEditor = dDiagramEditorImpl;
        diagramEditor.getEditingDomain().addResourceSetListener(this);
    }

    @Override
    public boolean isPrecommitOnly() {
        return false;
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        Collection<DRepresentationDescriptor> affected = new HashSet<DRepresentationDescriptor>();
        for (Notification notif : event.getNotifications()) {
            switch (notif.getEventType()) {
            case Notification.REMOVE:
                affected.add((DRepresentationDescriptor) notif.getOldValue());
                break;
            case Notification.REMOVE_MANY:
                affected.addAll((Collection<DRepresentationDescriptor>) notif.getOldValue());
                break;
            case Notification.ADD:
                affected.add((DRepresentationDescriptor) notif.getNewValue());
                break;
            case Notification.ADD_MANY:
                affected.addAll((Collection<DRepresentationDescriptor>) notif.getNewValue());
                break;
            default:
                break;
            }
        }

        ECrossReferenceAdapter semanticCrossReferencer = diagramEditor.getSession().getSemanticCrossReferencer();
        for (DRepresentationDescriptor descriptor : affected) {
            for (EObject referencer : new EObjectQuery(descriptor, semanticCrossReferencer).getInverseReferences(NotationPackage.Literals.VIEW__ELEMENT)) {
                if (referencer instanceof Shape && ((Shape) referencer).getDiagram() == diagramEditor.getDiagram()) {
                    EditPart part = (EditPart) diagramEditor.getDiagramGraphicalViewer().getEditPartRegistry().get(referencer);
                    if (part instanceof SiriusNoteEditPart) {
                        ((SiriusNoteEditPart) part).refreshDiagramNameCompartmentEditPart();
                    }
                }
            }
        }
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        diagramEditor = null;
    }
}
