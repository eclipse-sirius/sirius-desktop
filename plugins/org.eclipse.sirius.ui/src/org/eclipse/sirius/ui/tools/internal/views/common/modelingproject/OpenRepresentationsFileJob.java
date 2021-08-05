/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.AbstractRepresentationsFileJob;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.modelingproject.marker.ModelingMarker;
import org.eclipse.sirius.common.tools.api.util.MarkerUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionHelper;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.resourcelistener.ISessionFileLoadingListener;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A job to load one representations files (load the aird file and all the referenced resource). Warning before calling
 * this job you must call waitOtherJobs methods to ensure that there is no job of this kind currently running.<BR>
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class OpenRepresentationsFileJob extends AbstractRepresentationsFileJob {

    /** The default label for the job that open a representations file. */
    public static final String JOB_LABEL = Messages.OpenRepresentationsFileJob_label;

    /** The {@link URI} of the representations file to open. */
    private URI representationsFileURI;

    /**
     * <code>True</code> if this session opening comes from a direct user action, </code>false<code> otherwise.
     */
    private final boolean isDirectUserActionLoading;

    /**
     * Constructor to open only one representations file.
     *
     * @param representationsFileURI
     *            The URI of the representations file to open.
     */
    public OpenRepresentationsFileJob(final URI representationsFileURI) {
        this(representationsFileURI, false);
    }

    /**
     * Constructor to open only one representations file.
     *
     * @param representationsFileURI
     *            The URI of the representations file to open.
     * @param isDirectUserActionLoading
     *            <code>true</code> if this session opening comes from a direct user action, </code>false<code>
     *            otherwise
     */
    public OpenRepresentationsFileJob(final URI representationsFileURI, final boolean isDirectUserActionLoading) {
        super(OpenRepresentationsFileJob.JOB_LABEL);
        this.representationsFileURI = representationsFileURI;
        this.isDirectUserActionLoading = isDirectUserActionLoading;
        // During the execution of this job, some refresh can occurs on files of
        // this project but also on file of other projects according to
        // dependencies of this session, so the scheduling rule must include the
        // scheduling rule
        // of these files. We use the workspace scheduling rule to have all
        // files
        // scheduling rules.
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        // Add the workspace rule to the default rule
        setRule(MultiRule.combine(getRule(), workspace.getRuleFactory().createRule(workspace.getRoot())));
    }

    /**
     * Launch this job when all other openRepresentationFile's job are finished.
     *
     * @param representationsFileURI
     *            The URI of the representations file to open.
     * @param user
     *            <code>true</code> if this job is a user-initiated job, and <code>false</code> otherwise.
     */
    public static void scheduleNewWhenPossible(URI representationsFileURI, boolean user) {
        scheduleNewWhenPossible(representationsFileURI, user, false);
    }

    /**
     * Launch this job when all other openRepresentationFile's job are finished.
     *
     * @param representationsFileURI
     *            The URI of the representations file to open.
     * @param user
     *            <code>true</code> if this job is a user-initiated job, and <code>false</code> otherwise.
     * @param isDirectUserActionLoading
     *            <code>true</code> if this session opening comes from a direct user action, </code>false<code>
     *            otherwise
     */
    public static void scheduleNewWhenPossible(URI representationsFileURI, boolean user, boolean isDirectUserActionLoading) {
        // Schedule a new job for this representations file.
        Job job = new OpenRepresentationsFileJob(representationsFileURI, isDirectUserActionLoading);
        job.setUser(user);
        job.setPriority(Job.SHORT);
        job.schedule();
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {
            PlatformUI.getWorkbench().getProgressService().showInDialog(activeWorkbenchWindow.getShell(), job);
        }
    }

    @Override
    public IStatus runInWorkspace(IProgressMonitor monitor) {
        SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.OpenRepresentationsFileJob_loadingModelsTask, 100);
        // Clean existing marker if exists
        IProject project = null;
        try {
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

            subMonitor.subTask(MessageFormat.format(Messages.OpenRepresentationsFileJob_loadingRepresentationFileTask, representationsFileURI.lastSegment()));
            Session session = performOpenSession(subMonitor.newChild(90));
            if (session != null) {
                monitor.subTask(MessageFormat.format(Messages.OpenRepresentationsFileJob_openingStartRepresentationTask, representationsFileURI.lastSegment()));
                // Open the startup representations of each session
                SessionHelper.openStartupRepresentations(session, subMonitor.newChild(10));
            }
            // CHECKSTYLE:OFF to add a marker on this project
        } catch (RuntimeException e) {
            // CHECKSTYLE:ON
            String message = markeModelingProjectAsInvalid(project, e);
            throw new RuntimeException(message, e);
        } finally {
            monitor.done();
        }
        return Status.OK_STATUS;
    }

    /**
     * Open session.
     *
     * @param monitor
     *            the progress monitor.
     * @return the opened {@link Session}.
     */
    private Session performOpenSession(IProgressMonitor monitor) {
        Session session = null;
        try {
            SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.OpenRepresentationsFileJob_loadRepresentationsTask, 16);
            if (SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(representationsFileURI.fileExtension())) {
                subMonitor.worked(1);
                subMonitor.subTask(MessageFormat.format(Messages.OpenRepresentationsFileJob_loadReferencedModelsTask, representationsFileURI.lastSegment()));
                session = SessionManager.INSTANCE.openSession(representationsFileURI, subMonitor.newChild(14), SiriusEditPlugin.getPlugin().getUiCallback(), isDirectUserActionLoading);
                if (session != null) {
                    Set<ISessionFileLoadingListener> sessionFileLoadingListeners = SiriusEditPlugin.getPlugin().getSessionFileLoadingListeners();
                    for (ISessionFileLoadingListener sessionFileLoadingListener : sessionFileLoadingListeners) {
                        sessionFileLoadingListener.notifySessionLoadedFromModelingProject(session);
                    }

                    IEditingSession editingSession = SessionUIManager.INSTANCE.getOrCreateUISession(session);
                    if (!editingSession.isOpen()) {
                        editingSession.open();
                    }
                }
                subMonitor.worked(1);
            }
        } finally {
            monitor.done();
        }
        return session;
    }

    /**
     * Marks the {@link ModelingProject} associated to the specified <code>project</code> as invalid :
     * 
     * <ul>
     * <li>add a error marker to the project</li>
     * <li>mark the associated {@link IProject} as invalid</li>
     * <li>clear the cache of {@link ModelingProjectManager} about this {@link ModelingProject}</li>
     * </ul>
     *
     * @param project
     *            the project associated to the {@link ModelingProject}.
     * @param exception
     *            the {@link RuntimeException} origin of the failing session opening
     * @return the error message to dispatch.
     */
    private String markeModelingProjectAsInvalid(IProject project, RuntimeException exception) {
        String message = null;
        if (project != null) {
            boolean isModelingProject = ModelingProject.hasModelingProjectNature(project);
            final String errorDetail;
            if (exception != null) {
                errorDetail = exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage();
            } else {
                errorDetail = Messages.OpenRepresentationsFileJob_loadingProblem_defaultErrorDetail;
            }
            if (isModelingProject) {
                message = MessageFormat.format(Messages.OpenRepresentationsFileJob_loadingProblem_modelingProject, project.getName(), errorDetail);
            } else {
                message = MessageFormat.format(Messages.OpenRepresentationsFileJob_loadingProblem_representationFile, representationsFileURI.toPlatformString(true), errorDetail);
            }

            Optional<IMarker> optionalMarker = MarkerUtil.addMarkerFor(project, message, IMarker.SEVERITY_ERROR, ModelingMarker.MARKER_TYPE);
            if (optionalMarker.isPresent() && exception instanceof Diagnostic) {
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
     * Check if other jobs of this kind are running. This method must be called from UI Thread.
     *
     * @return true if other jobs of this kind are running.
     */
    public static boolean shouldWaitOtherJobs() {
        Job[] jobs = Job.getJobManager().find(AbstractRepresentationsFileJob.FAMILY);
        return jobs != null && jobs.length > 0;
    }

    /**
     * Returns a localized message describing the given exception. If the given exception does not have a localized
     * message, this returns the string "An error occurred".
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
                message = Messages.OpenRepresentationsFileJob_unexpectedException;
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
