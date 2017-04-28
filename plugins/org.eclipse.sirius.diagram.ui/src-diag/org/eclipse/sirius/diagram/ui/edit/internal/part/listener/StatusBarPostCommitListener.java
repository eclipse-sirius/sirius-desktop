/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.internal.part.listener;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.ui.PlatformUI;

/**
 * A ResourceSet listener to update the status bar when the synchronize state changed on DDiagram.
 * 
 * @author lfasani
 */
public class StatusBarPostCommitListener extends ResourceSetListenerImpl {

    private DiagramEditor diagramEditor;

    /**
     * Default constructor.
     * 
     * @param dDiagramEditorImpl
     *            listened part.
     */
    public StatusBarPostCommitListener(DDiagramEditorImpl dDiagramEditorImpl) {
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
