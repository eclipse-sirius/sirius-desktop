/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.navigator;

import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.InvalidModelingProjectMarkerUpdaterJob;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.progress.UIJob;

/**
 * {@link ITreeViewerListener} to open {@link ModelingProject}'s session on
 * project expansion.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class OpenSessionOnExpandListener implements ITreeViewerListener {

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
        // Do nothing
    }

    @Override
    public void treeExpanded(final TreeExpansionEvent event) {
        if (event.getElement() instanceof IProject) {
            IProject projectExpanded = (IProject) event.getElement();

            Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(projectExpanded);
            if (optionalModelingProject.some()) {
                Option<URI> optionalMainSessionFileURI = Options.newNone();
                try {
                    optionalMainSessionFileURI = optionalModelingProject.get().getMainRepresentationsFileURI(new NullProgressMonitor(), false, true);
                } catch (IllegalArgumentException e) {
                    Job invalidModelingProjectMarkerUpdaterJob = new InvalidModelingProjectMarkerUpdaterJob(optionalModelingProject.get().getProject(), e.getMessage());
                    invalidModelingProjectMarkerUpdaterJob.schedule();
                }
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
     */
    private class ExpandAgainJob extends UIJob {

        private final AbstractTreeViewer viewer;

        private final Object itemToExpand;

        /**
         * Default constructor.
         * 
         * @param viewer
         *            the {@link AbstractTreeViewer} on which do expansion
         * @param itemToExpand
         *            the item to expand
         */
        ExpandAgainJob(AbstractTreeViewer viewer, IFile itemToExpand) {
            super(MessageFormat.format(Messages.OpenSessionOnExpandListener_expandJob, itemToExpand.getName()));
            this.viewer = viewer;
            this.itemToExpand = itemToExpand;

        }

        @Override
        public IStatus runInUIThread(IProgressMonitor monitor) {
            try {
                Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
            } catch (OperationCanceledException | InterruptedException e) {
                // Do nothing
            }
            if (viewer != null) {
                viewer.expandToLevel(itemToExpand, 1);
            }
            return Status.OK_STATUS;
        }

    }

}
