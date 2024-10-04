/*******************************************************************************
 * Copyright (c) 2017, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part.listener;

import java.util.Iterator;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.SynchronizeStatusFigure;
import org.eclipse.ui.PlatformUI;

/**
 * When the DDiagram synchronize state changes, this ResourceSet listener will update </br>
 * - the status bar </br>
 * - the decorator in the bottom right corner of the editor.
 * 
 * @author lfasani
 */
public class SynchronizedStatusPostCommitListener extends ResourceSetListenerImpl {

    private DiagramEditor diagramEditor;

    /**
     * Default constructor.
     * 
     * @param dDiagramEditorImpl
     *            listened part.
     */
    public SynchronizedStatusPostCommitListener(DDiagramEditorImpl dDiagramEditorImpl) {
        super(NotificationFilter.NOT_TOUCH.and(NotificationFilter.createFeatureFilter(DiagramPackage.eINSTANCE.getDDiagram_Synchronized())));
        this.diagramEditor = dDiagramEditorImpl;
        dDiagramEditorImpl.getEditingDomain().addResourceSetListener(this);
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
        if (diagramEditor instanceof DDiagramEditorImpl) {
            PlatformUI.getWorkbench().getDisplay().syncExec(() -> {
                // refresh the status bar
                ((DDiagramEditorImpl) diagramEditor).rebuildStatusLine();

                // refresh the decorator in the diagram editor
                EObject resolveSemanticElement = diagramEditor.getDiagramEditPart().resolveSemanticElement();
                Iterator<Notification> notifs = event.getNotifications().iterator();
                while (notifs.hasNext()) {
                    Notification notif = notifs.next();

                    if (Objects.equals(resolveSemanticElement, notif.getNotifier())) {
                        SynchronizeStatusFigure.updateNotification((DiagramRootEditPart) diagramEditor.getDiagramEditPart().getRoot());
                        break;
                    }
                }
            });
        }
    }

    /**
     * Dispose this listener.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
    }
}
