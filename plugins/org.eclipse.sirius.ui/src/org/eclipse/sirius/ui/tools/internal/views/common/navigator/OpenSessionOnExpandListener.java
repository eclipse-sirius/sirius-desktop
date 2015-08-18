/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.ui.progress.UIJob;

/**
 * This listener opens a representations file.<BR>
 * When user expands a modeling project, the representations file is opened
 * "silently". And when user expands the representations file, if the "silently"
 * opening is not finished, it become blocker.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class OpenSessionOnExpandListener implements ITreeViewerListener {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeViewerListener#treeCollapsed(org.eclipse.jface.viewers.TreeExpansionEvent)
     */
    public void treeCollapsed(TreeExpansionEvent event) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ITreeViewerListener#treeExpanded(org.eclipse.jface.viewers.TreeExpansionEvent)
     */
    public void treeExpanded(final TreeExpansionEvent event) {
        if (event.getElement() instanceof IProject) {
            IProject projectExpanded = (IProject) event.getElement();

            Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(projectExpanded);
            if (optionalModelingProject.some()) {
                Option<URI> optionalMainSessionFileURI = optionalModelingProject.get().getMainRepresentationsFileURI(new NullProgressMonitor(), false, false);
                if (optionalMainSessionFileURI.some()) {
                    // Load the main representations file of this modeling
                    // project if it's not already loaded or during loading.
                    ModelingProjectManager.INSTANCE.loadAndOpenRepresentationsFile(optionalMainSessionFileURI.get());
                }
            }
        } else if (event.getElement() instanceof IFile) {
            reactToFileExpanded((IFile) event.getElement(), event);
        }
    }

    private void reactToFileExpanded(IFile expandedFile, final TreeExpansionEvent event) {
        Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(expandedFile.getProject());
        if (optionalModelingProject.some() || SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(expandedFile.getFileExtension())) {
            if (OpenRepresentationsFileJob.shouldWaitOtherJobs()) {
                // We are loading session(s), wait loading is finished and
                // re-expand the tree
                OpenRepresentationsFileJob.waitOtherJobs();

                // Session is now loaded, the expanded file could have
                // children now, demand new expand.
                UIJob expandAgain = new ExpandAgainJob(event.getTreeViewer(), expandedFile);
                expandAgain.schedule();
            }
        }
    }

    /**
     * {@link UIJob} to expand the loaded file
     * 
     * @author mporhel
     * 
     */
    private class ExpandAgainJob extends UIJob {

        private final AbstractTreeViewer viewer;

        private final Object itemToExpand;

        /**
         * 
         * @param viewer
         * @param itemToExpand
         */
        public ExpandAgainJob(AbstractTreeViewer viewer, IFile itemToExpand) {
            super("Expand " + itemToExpand.getName());
            this.viewer = viewer;
            this.itemToExpand = itemToExpand;

        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            if (viewer != null) {
                if (isViewerBusy()) {
                    schedule();
                } else {
                    viewer.expandToLevel(itemToExpand, 1);
                }
            }
            return Status.OK_STATUS;
        }

        private boolean isViewerBusy() {
            boolean viewerIsBusy = false;

            // ColumnViewer.isBusy was not public before 3.4
            if (viewer != null) {
                Method method = null;
                try {
                    method = viewer.getClass().getMethod("isBusy"); //$NON-NLS-1$
                } catch (SecurityException e) {
                    // No method, no data
                } catch (NoSuchMethodException e) {
                    // No method, no data
                }

                if (method != null) {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }

                    try {
                        Object data = method.invoke(viewer);
                        if (data instanceof Boolean) {
                            viewerIsBusy = ((Boolean) data).booleanValue();
                        }
                    } catch (IllegalArgumentException e) {
                        // No access, no data
                    } catch (IllegalAccessException e) {
                        // No access, no data
                    } catch (InvocationTargetException e) {
                        // No access, no data
                    }
                }

            }
            return viewerIsBusy;
        }
    };
}
