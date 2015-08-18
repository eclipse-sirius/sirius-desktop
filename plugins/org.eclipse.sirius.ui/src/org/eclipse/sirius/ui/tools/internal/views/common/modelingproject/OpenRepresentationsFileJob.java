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
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.modelingproject.manager.InitializeModelingProjectJob;
import org.eclipse.sirius.business.internal.modelingproject.marker.ModelingMarker;
import org.eclipse.sirius.common.tools.api.util.MarkerUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionHelper;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A job to load one or more representations files (load the aird file and all
 * the referenced resource). Warning before calling this job you must call
 * waitOtherJobs methods to ensure that there is no job of this kind currently
 * running.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class OpenRepresentationsFileJob extends AbstractRepresentationsFileJob {
    /**
     * The default label for the job that open a representations file.
     */
    public static final String JOB_LABEL = "Loading models";

    private static final String QUOTE = "\""; //$NON-NLS-1$

    /**
     * The list of representations files to load. This list is exclusive with
     * the list of modeling projects.
     */
    List<URI> representationsFilesURIs = Lists.newArrayList();

    /**
     * The list of modeling projects to initialize and for which to load the
     * main representations file. This list is exclusive with the list of
     * representations files.
     */
    List<ModelingProject> modelingProjects = Lists.newArrayList();

    /**
     * Constructor to open only one representations file.
     * 
     * @param representationsFileURI
     *            The URI of the representations file to open.
     */
    public OpenRepresentationsFileJob(final URI representationsFileURI) {
        super(JOB_LABEL);
        this.representationsFilesURIs.add(representationsFileURI);
    }

    /**
     * Constructor to open several representations files.
     * 
     * @param elements
     *            A list of URIs of the representations files to open or a list
     *            of the modeling projects to initialize and open.
     */
    public OpenRepresentationsFileJob(List<? extends Object> elements) {
        super(JOB_LABEL);
        if (!(Iterators.all(elements.iterator(), Predicates.instanceOf(URI.class)) || Iterators.all(elements.iterator(), Predicates.instanceOf(ModelingProject.class)))) {
            throw new IllegalArgumentException("This list must be a list of URI or a list of ModelingProject.");
        }
        Iterators.addAll(this.representationsFilesURIs, Iterators.filter(elements.iterator(), URI.class));
        Iterators.addAll(this.modelingProjects, Iterators.filter(elements.iterator(), ModelingProject.class));
    }

    /**
     * Launch this job when all other openRepresentationFile's job are finished.
     * 
     * @param representationsFileURI
     *            The URI of the representations file to open.
     * @param user
     *            <code>true</code> if this job is a user-initiated job, and
     *            <code>false</code> otherwise.
     */
    public static void scheduleNewWhenPossible(URI representationsFileURI, boolean user) {
        // Just wait other job if some are already in progress
        OpenRepresentationsFileJob.waitOtherJobs();

        // Schedule a new job for this representations file.
        Job job = new OpenRepresentationsFileJob(representationsFileURI);
        job.setUser(user);
        job.setPriority(Job.SHORT);
        job.schedule();
    }

    /**
     * Launch this job when all other openRepresentationFile's job are finished.
     * 
     * @param elements
     *            A list of URIs of the representations files to open or a list
     *            of the modeling projects to initialize and open.
     * @param user
     *            <code>true</code> if this job is a user-initiated job, and
     *            <code>false</code> otherwise.
     */
    public static void scheduleNewWhenPossible(List<? extends Object> elements, boolean user) {
        if (!(Iterators.all(elements.iterator(), Predicates.instanceOf(URI.class)) || Iterators.all(elements.iterator(), Predicates.instanceOf(ModelingProject.class)))) {
            throw new IllegalArgumentException("This list must be a list of URI or a list of ModelingProject.");
        }

        // Just wait other job if some are already in progress
        OpenRepresentationsFileJob.waitOtherJobs();

        // Schedule a new job for this representations file.
        Job job = new OpenRepresentationsFileJob(elements);
        job.setUser(user);
        job.setPriority(Job.SHORT);
        job.schedule();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) {
        IStatus initializationStatus = Status.OK_STATUS;
        List<IStatus> openingStatuses = Lists.newArrayList();
        try {
            monitor.beginTask("Loading models", 11);
            monitor.subTask("Initialize modeling projects");
            if (!modelingProjects.isEmpty()) {
                // Initialize the modeling projects before open the main
                // representations files.
                List<IProject> projects = Lists.newArrayList();
                for (ModelingProject modelingProject : modelingProjects) {
                    projects.add(modelingProject.getProject());
                }
                initializationStatus = InitializeModelingProjectJob.initializeModelingProjects(projects, true, new SubProgressMonitor(monitor, 2));

                SubProgressMonitor getRepresentationsFilesMonitor = new SubProgressMonitor(monitor, 1);
                try {
                    getRepresentationsFilesMonitor.beginTask("", modelingProjects.size()); //$NON-NLS-1$
                    for (ModelingProject modelingProject : modelingProjects) {
                        Option<URI> optionalMainSessionFileURI = modelingProject.getMainRepresentationsFileURI(new SubProgressMonitor(getRepresentationsFilesMonitor, 1), false, false);
                        if (optionalMainSessionFileURI.some()) {
                            representationsFilesURIs.add(optionalMainSessionFileURI.get());
                        }
                    }
                } finally {
                    getRepresentationsFilesMonitor.done();
                }
            } else {
                monitor.worked(3);
            }
            SubProgressMonitor subMonitor = new SubProgressMonitor(monitor, 8);
            try {
                subMonitor.beginTask("", 100 * representationsFilesURIs.size()); //$NON-NLS-1$
                for (URI representationsFileURI : representationsFilesURIs) {
                    // Clean existing marker if exists
                    IProject project = null;
                    if (representationsFileURI.isPlatform()) {
                        String projectName = URI.decode(representationsFileURI.segment(1));
                        project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                    }
                    try {
                        if (project != null) {
                            project.deleteMarkers(ModelingMarker.MARKER_TYPE, false, IResource.DEPTH_ZERO);
                        }
                    } catch (final CoreException e) {
                        SiriusPlugin.getDefault().getLog().log(e.getStatus());
                    }

                    subMonitor.subTask("Loading representations file " + QUOTE + representationsFileURI.lastSegment() + QUOTE);
                    final Set<Session> sessions = performOpenSession(representationsFileURI, new SubProgressMonitor(subMonitor, 90));
                    if (sessions.isEmpty()) {
                        subMonitor.worked(10);
                        String errorMessage = logLoadingProblem(project, representationsFileURI, null);
                        openingStatuses.add(new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, errorMessage, null)); //$NON-NLS-1$)
                    } else {
                        subMonitor.subTask("Opening the startup representations of " + QUOTE + representationsFileURI.lastSegment() + QUOTE);
                        // Open the startup representations of each session
                        for (final Session session : sessions) {
                            SessionHelper.openStartupRepresentations(session, new SubProgressMonitor(subMonitor, 10 / sessions.size()));
                        }
                    }
                }
            } finally {
                subMonitor.done();
            }
        } finally {
            monitor.done();
        }
        List<IStatus> allStatuses = Lists.newArrayList(openingStatuses);
        if (!initializationStatus.isOK()) {
            if (initializationStatus instanceof MultiStatus) {
                allStatuses.addAll(0, Lists.newArrayList(((MultiStatus) initializationStatus).getChildren()));
            } else {
                allStatuses.add(0, initializationStatus);
            }
        }

        IStatus result = Status.OK_STATUS;
        if (allStatuses.size() == 1) {
            result = allStatuses.get(0);
        } else if (allStatuses.size() > 1) {
            result = new MultiStatus(SiriusEditPlugin.ID, IStatus.ERROR, allStatuses.toArray(new IStatus[0]), "Several modeling projects are invalid or can not be opened.", null);
        }

        return result;
    }

    /**
     * Log loading problem (Add a marker on this project, change valid status of
     * this modeling project, clean the cache of the ModelingProjectManager).
     * 
     * @param project
     *            The project concerned by this problem.
     * @param representationsFileURI
     *            The URI of the representations file concerned by this problem.
     * @param exception
     *            The thrown exception
     * @return The error message.
     */
    protected String logLoadingProblem(IProject project, URI representationsFileURI, Exception exception) {
        String message = ""; //$NON-NLS-1$
        if (project != null) {
            boolean isModelingProject = ModelingProject.hasModelingProjectNature(project);
            if (isModelingProject) {
                message += "The modeling project \"" + project.getName() + "\" is invalid: Problem during loading models";
            } else {
                message += "The representations file \"" + representationsFileURI.toPlatformString(true) + "\" is invalid: Problem during loading models";
            }
            // Add a marker on this project
            if (exception != null) {
                message += ": " + (exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage());
            } else {
                message += " (see Error Log for more details)";
            }

            Option<IMarker> optionalMarker = MarkerUtil.addMarkerFor(project, message, IMarker.SEVERITY_ERROR, ModelingMarker.MARKER_TYPE);
            if (optionalMarker.some() && exception instanceof Diagnostic) {
                MarkerUtil.setAttribute(optionalMarker.get(), IMarker.LOCATION, ((Diagnostic) exception).getLocation());
            }
            // Set this project in invalid state
            Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(project);
            if (optionalModelingProject.some()) {
                optionalModelingProject.get().setValid(false);
            }
        }
        // Clear modeling project manager cache for this representations file
        ModelingProjectManager.INSTANCE.clearCache(representationsFileURI);
        return message;
    }

    /**
     * Open session.
     * 
     * @param representationsFileURI
     *            The URI of the representations file corresponding to the
     *            session to opened.
     * @param monitor
     *            the progress monitor.
     * @return Resource associated to session.
     */
    public Set<Session> performOpenSession(URI representationsFileURI, IProgressMonitor monitor) {
        monitor.beginTask("Load representations and models", 16);
        Set<Session> openedSessions = new HashSet<Session>();
        if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(representationsFileURI.fileExtension())) {
            monitor.worked(1);
            Session session = SessionManager.INSTANCE.getSession(representationsFileURI, new SubProgressMonitor(monitor, 10));
            // Open the session if needed (load the referenced models by
            // a ResolveAll call)
            monitor.subTask("Loading referenced models of " + QUOTE + representationsFileURI.lastSegment() + QUOTE);
            if (session != null) {
                if (!session.isOpen()) {
                    session.open(new SubProgressMonitor(monitor, 4));
                }
                IEditingSession editingSession;
                // JGO : Do not create an editing session in case the session is
                // null
                // the session could be null if the session is not a local
                // session (CDO for example) and
                // if the remote CDO server is unreachable
                editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
                if (!editingSession.isOpen()) {
                    editingSession.open();
                }
                if (openedSessions != null) {
                    openedSessions.add(session);
                }
            }
            monitor.worked(1);
        }
        monitor.done();
        return openedSessions;
    }

    /**
     * Waits until all jobs of this kind are finished. This method must be
     * called from UI Thread.
     */
    public static void waitOtherJobs() {
        if (shouldWaitOtherJobs()) {
            try {
                if (Display.getCurrent() != null) {
                    PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {
                        public void run(IProgressMonitor monitor) throws InterruptedException {
                            Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, monitor);
                        }
                    });
                } else {
                    Job.getJobManager().join(AbstractRepresentationsFileJob.FAMILY, new NullProgressMonitor());
                }
            } catch (InvocationTargetException e) {
                StatusManager.getManager().handle(new Status(IStatus.ERROR, SiriusEditPlugin.ID, IStatus.OK, getLocalizedMessage(e), getCause(e)));
            } catch (InterruptedException e) {
                // Do nothing;
            }
        }
    }

    /**
     * Check if other jobs of this kind are running. This method must be called
     * from UI Thread.
     * 
     * @return true if other jobs of this kind are running.
     */
    public static boolean shouldWaitOtherJobs() {
        Job[] jobs = Job.getJobManager().find(AbstractRepresentationsFileJob.FAMILY);
        return jobs != null && jobs.length > 0;
    }

    /**
     * Returns a localized message describing the given exception. If the given
     * exception does not have a localized message, this returns the string
     * "An error occurred".
     * 
     * @param exception
     *            The exception to deal with
     * @return The message
     */
    protected static String getLocalizedMessage(Throwable exception) {
        String message = exception.getLocalizedMessage();
        if (message == null) {
            // Workaround for the fact that CoreException does not implement a
            // getLocalizedMessage() method.
            // Remove this branch when and if CoreException implements
            // getLocalizedMessage()
            if (exception instanceof CoreException) {
                CoreException ce = (CoreException) exception;
                return ce.getStatus().getMessage();
            } else {
                message = "An unexpected exception was thrown.";
            }
        }

        return message;
    }

    /**
     * Returns the cause of the given exception.
     * 
     * @param exception
     *            The exception to deal with
     * @return the cause of the given exception.
     */
    protected static Throwable getCause(Throwable exception) {
        // Figure out which exception should actually be logged -- if the given
        // exception is
        // a wrapper, unwrap it
        Throwable cause = null;
        if (exception != null) {
            if (exception instanceof CoreException) {
                // Workaround: CoreException contains a cause, but does not
                // actually implement getCause().
                // If we get a CoreException, we need to manually unpack the
                // cause. Otherwise, use
                // the general-purpose mechanism. Remove this branch if
                // CoreException ever implements
                // a correct getCause() method.
                CoreException ce = (CoreException) exception;
                cause = ce.getStatus().getException();
            } else {
                // use reflect instead of a direct call to getCause(), to allow
                // compilation against JCL Foundation
                try {
                    Method causeMethod = exception.getClass().getMethod("getCause", new Class[0]); //$NON-NLS-1$
                    Object o = causeMethod.invoke(exception, new Object[0]);
                    if (o instanceof Throwable) {
                        cause = (Throwable) o;
                    }
                } catch (NoSuchMethodException e) {
                    // ignore
                } catch (IllegalArgumentException e) {
                    // ignore
                } catch (IllegalAccessException e) {
                    // ignore
                } catch (InvocationTargetException e) {
                    // ignore
                }
            }

            if (cause == null) {
                cause = exception;
            }
        }

        return cause;
    }
}
