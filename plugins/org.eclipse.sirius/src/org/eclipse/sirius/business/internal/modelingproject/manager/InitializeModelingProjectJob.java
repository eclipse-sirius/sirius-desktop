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
package org.eclipse.sirius.business.internal.modelingproject.manager;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.internal.modelingproject.marker.ModelingMarker;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A job to compute the main representations file of each modeling project of
 * the workspace.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class InitializeModelingProjectJob extends WorkspaceJob {
    /**
     * The label of the job.
     */
    public static final String JOB_LABEL = "Initializing Modeling Projects";

    /**
     * The label of the job (if all projects are empty, ie containing only
     * .project file). There is a specific job name if all projects are empty
     * because this job can be launch just before a job that initialize the non
     * empty project (in case of importing simultaneously several projects). See
     * ModelingProjectResourceListener for more details.
     */
    public static final String JOB_LABEL_FOR_EMPTY_PROJECTS = "Initializing Empty Modeling Projects";

    /**
     * The family id for this kind of job.
     */
    private static final String JOB_FAMILY_ID = SiriusPlugin.ID + ".initializemodelingprojects"; //$NON-NLS-1$

    /**
     * The projects concerned by this job. If null, all projects of the
     * workspace is concerned by this job.
     */
    private List<IProject> projects;

    /**
     * True if the main representations file must be recomputed (even if it is
     * already known), false otherwise.
     */
    private boolean forceInit;

    /**
     * Constructor.
     */
    public InitializeModelingProjectJob() {
        this(JOB_LABEL);
    }

    /**
     * Constructor.
     * 
     * @param projects
     *            The projects concerned by this job
     */
    public InitializeModelingProjectJob(List<IProject> projects) {
        this(Iterators.all(projects.iterator(), new Predicate<IProject>() {
            public boolean apply(IProject input) {
                try {
                    return input.members().length == 1;
                } catch (CoreException e) {
                    return false;
                }
            }
        }) ? JOB_LABEL_FOR_EMPTY_PROJECTS : JOB_LABEL);
        this.projects = projects;
    }

    /**
     * Constructor.
     * 
     * @param name
     *            The name of the job.
     */
    public InitializeModelingProjectJob(String name) {
        super(name);
    }

    /**
     * Compute the main representations file of each modeling projects of the
     * list of <code>projects</code>.
     * 
     * @param projects
     *            The projects concerned by this job. If null, all projects of
     *            the workspace is concerned by this job.
     * @param forceInit
     *            True if the main representations file must be recomputed (even
     *            if it is already known), false otherwise.
     * @param monitor
     *            the monitor to be used for reporting progress and responding
     *            to cancelation. The monitor is never <code>null</code>
     * @return resulting status of the initialization
     */
    public static IStatus initializeModelingProjects(List<IProject> projects, boolean forceInit, IProgressMonitor monitor) {
        List<IStatus> errorStatus = Lists.newArrayList();
        try {
            IProject[] projectsTable;
            if (projects != null) {
                projectsTable = projects.toArray(new IProject[0]);
            } else {
                projectsTable = ResourcesPlugin.getWorkspace().getRoot().getProjects();
            }
            monitor.beginTask("", projectsTable.length); //$NON-NLS-1$
            for (int i = 0; i < projectsTable.length; i++) {
                Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(projectsTable[i]);
                if (optionalModelingProject.some()) {
                    // Clean existing marker if exists
                    try {
                        optionalModelingProject.get().getProject().deleteMarkers(ModelingMarker.MARKER_TYPE, false, IResource.DEPTH_ZERO);
                    } catch (final CoreException e) {
                        SiriusPlugin.getDefault().getLog().log(e.getStatus());
                    }
                    try {
                        optionalModelingProject.get().getMainRepresentationsFileURI(new SubProgressMonitor(monitor, 1), forceInit, true);
                    } catch (IllegalArgumentException e) {
                        // Add the problem to the result status of this job
                        errorStatus.add(new Status(IStatus.ERROR, SiriusPlugin.ID, IStatus.OK, e.getMessage(), null));
                    }
                } else {
                    monitor.worked(1);
                }
            }
        } finally {
            monitor.done();
        }

        IStatus result = Status.OK_STATUS;
        if (errorStatus.size() == 1) {
            result = new MultiStatus(SiriusPlugin.ID, IStatus.ERROR, errorStatus.toArray(new IStatus[0]), "One modeling project is invalid.", null);
        } else if (errorStatus.size() > 1) {
            result = new MultiStatus(SiriusPlugin.ID, IStatus.ERROR, errorStatus.toArray(new IStatus[0]), "Several modeling projects are invalid.", null);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) {
        return initializeModelingProjects(projects, forceInit, monitor);
    }

    /**
     * The main representations file will be computed even if it is already
     * known.
     * 
     * @param forceInit
     *            the forceInit to set
     */
    public void setForceInit(boolean forceInit) {
        this.forceInit = forceInit;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.jobs.Job#belongsTo(java.lang.Object)
     */
    @Override
    public boolean belongsTo(Object family) {
        return JOB_FAMILY_ID.equals(family);
    }

}
