/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.image.refresh;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

/**
 * IWorkspaceImageFigure refresher according to workspace changes.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WorkspaceImageFigureRefresher implements IResourceChangeListener {

    /**
     * Initialize this refresher.
     */
    public void init() {
        ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
    }

    /**
     * Overridden to refresh the IWorkspaceImageFigure if their corresponding
     * image in workspace have changed.
     * 
     * {@inheritDoc}
     */
    public void resourceChanged(IResourceChangeEvent event) {
        IResourceDelta delta = event.getDelta();
        if (delta != null) {
            try {
                WorkspaceImageChangeDetector deltaVisitor = new WorkspaceImageChangeDetector();
                delta.accept(deltaVisitor);
                // If one at least editor is opened and requires a refresh
                if (deltaVisitor.isAtLeastOneEditorToRefresh()) {
                    refreshOpenedEditors();
                }
            } catch (CoreException e) {
                SiriusDiagramEditorPlugin.getInstance().logError("Update image descriptor failed.", e);
            }

        }
    }

    /**
     * Refresh all the edit parts of the opened editors.
     */
    private void refreshOpenedEditors() {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                if (activeWorkbenchWindow != null) {
                    IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
                    if (activePage != null) {
                        IEditorPart editor = activePage.getActiveEditor();
                        if (editor instanceof DiagramDocumentEditor) {
                            refreshAllEditPart(((DiagramDocumentEditor) editor).getDiagramEditPart());
                        }
                    }
                }
            }
        });
    }

    /**
     * Refresh all the edit pars of the diagram.
     * 
     * @param diagramEditPart
     *            DiagramEditPart
     */
    public static void refreshAllEditPart(DiagramEditPart diagramEditPart) {
        Iterable<GraphicalEditPart> editParts = Iterables.filter(diagramEditPart.getChildren(), GraphicalEditPart.class);
        for (GraphicalEditPart editPart : editParts) {
            if (editPart.isActive()) {
                editPart.refresh();
                refreshChildrenEditPart(editPart);
            }
        }
    }

    private static void refreshChildrenEditPart(GraphicalEditPart parentEditPart) {
        Iterable<GraphicalEditPart> editParts = Iterables.filter(parentEditPart.getChildren(), GraphicalEditPart.class);
        for (GraphicalEditPart editPart : editParts) {
            editPart.refresh();
            refreshChildrenEditPart(editPart);
        }
    }

    /**
     * Dispose this refresher.
     */
    public void dispose() {
        ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
    }

}
