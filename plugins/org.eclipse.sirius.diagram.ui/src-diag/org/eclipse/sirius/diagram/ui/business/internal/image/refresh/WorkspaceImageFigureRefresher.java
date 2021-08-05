/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.internal.image.refresh;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
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
     * Overridden to refresh the IWorkspaceImageFigure if their corresponding image in workspace have changed.
     * 
     * {@inheritDoc}
     */
    @Override
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
                DiagramPlugin.getDefault().logError(Messages.WorkspaceImageFigureRefresher_imageDescriptorUpdateError, e);
            }

        }
    }

    /**
     * Refresh all the edit parts of the opened editors.
     */
    private void refreshOpenedEditors() {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            @Override
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
