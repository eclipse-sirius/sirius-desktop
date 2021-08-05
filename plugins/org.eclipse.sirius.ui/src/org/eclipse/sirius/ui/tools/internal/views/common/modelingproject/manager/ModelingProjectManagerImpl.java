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
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.manager;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategyRegistry;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.internal.modelingproject.marker.ModelingMarker;
import org.eclipse.sirius.business.internal.query.ModelingProjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.ui.tools.internal.views.modelexplorer.resourcelistener.ISessionFileLoadingListener;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A manager for modeling projects.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ModelingProjectManagerImpl implements ModelingProjectManager {

    /** The old Viewpoint nature id. */
    private static final String VIEWPOINT_MODELING_PROJECT_NATURE_ID = "fr.obeo.dsl.viewpoint.nature.modelingproject"; //$NON-NLS-1$

    private final SessionManagerListener sessionManagerListener = new SessionManagerListener.Stub() {
        @Override
        public void notify(Session updated, int notification) {
            if (notification == SessionListener.OPENING) {
                // No need to at it again to the sessionFileLoading list because
                // we add it during the starting of the load
                // ModelingProjectManager.loadAndOpenSession().
            } else if (notification == SessionListener.OPENED) {
                sessionFileLoading.remove(updated.getSessionResource().getURI());
            } else if (notification == SessionListener.CLOSED) {
                // make sure that the session is re-openable if CLOSED
                // That is necessary because the session may not have opened
                // correctly and the SessionListener.OPENED may not have been
                // sent.
                sessionFileLoading.remove(updated.getSessionResource().getURI());
            }
        }
    };

    private Predicate<URI> isAlreadyLoadedPredicate = new Predicate<URI>() {
        @Override
        public boolean apply(URI representationsFileURI) {
            return isAlreadyLoaded(representationsFileURI);
        }
    };

    /**
     * Set of representations files that are currently loading. There can be only one representations file in loading at
     * same time. However there may be many waiting to be loaded.
     */
    private Set<URI> sessionFileLoading = new HashSet<>();

    /**
     * Avoid instantiation.
     */
    protected ModelingProjectManagerImpl() {
    }

    /**
     * Default initialization of a {@link ModelingProjectManagerImpl}.
     *
     * @return a new instance of {@link ModelingProjectManagerImpl}.
     */
    public static ModelingProjectManagerImpl init() {
        return new ModelingProjectManagerImpl();
    }

    @Override
    public void loadAndOpenRepresentationsFile(final URI representationsFileURI) {
        loadAndOpenRepresentationsFiles(new ArrayList<URI>(Arrays.asList(representationsFileURI)), false);
    }

    @Override
    public void loadAndOpenRepresentationsFile(final URI representationsFileURI, boolean user) {
        loadAndOpenRepresentationsFiles(new ArrayList<URI>(Arrays.asList(representationsFileURI)), user);
    }

    @Override
    public void loadAndOpenRepresentationsFiles(final List<URI> representationsFilesURIs) {
        loadAndOpenRepresentationsFiles(representationsFilesURIs, false);
    }

    /**
     * Load and open representations files by scheduling a new job.
     * 
     * @param representationsFilesURIs
     *            The URIs of the representations files to open.
     * @param user
     *            <code>true</code> if this job is a user-initiated job, and <code>false</code> otherwise.
     * @throws CoreException
     *             Only useful in case of <code>alreadyInUserWorkspaceModifyOperation</code> is true.
     */
    private void loadAndOpenRepresentationsFiles(final List<URI> representationsFilesURIs, boolean user) {
        try {
            loadAndOpenRepresentationsFiles(representationsFilesURIs, false, false);
        } catch (CoreException e) {
            // Nothing do to, it can not happen as
            // <code>alreadyInUserWorkspaceModifyOperation</code> is false.
        }
    }

    /**
     * Load and open representations files by scheduling a new job or by launching directly the job (when
     * <code>alreadyInUserWorkspaceModifyOperation</code> is true).
     * 
     * @param representationsFilesURIs
     *            The URIs of the representations files to open.
     * @param user
     *            <code>true</code> if this job is a user-initiated job, and <code>false</code> otherwise.
     * @param alreadyInUserWorkspaceModifyOperation
     *            true if the loading and opening of representations files already occurs in a WorkspaceModifyOperation
     *            launches by user.
     * @throws CoreException
     *             Only useful in case of <code>alreadyInUserWorkspaceModifyOperation</code> is true.
     */
    private void loadAndOpenRepresentationsFiles(final List<URI> representationsFilesURIs, boolean user, boolean alreadyInUserWorkspaceModifyOperation) throws CoreException {
        // Add the specific sessions listener (if not already added).
        SessionManager.INSTANCE.addSessionsListener(sessionManagerListener);

        // List only the representations files that are not already loaded or
        // that are not currently in loading.
        Iterator<URI> representationsFilesURIsToLoadIterator = Iterators.filter(representationsFilesURIs.iterator(),
                Predicates.not(Predicates.or(Predicates.in(sessionFileLoading), isAlreadyLoadedPredicate)));
        if (!representationsFilesURIsToLoadIterator.hasNext()) {
            return;
        }

        // We add the representationsFilesURIs to the list of representations
        // files that are currently loading because the event
        // SessionListener.OPENING comes too late (after loading of the
        // representations file that can be very long).
        List<URI> tempRepresentationsFilesURIs = Lists.newArrayList(representationsFilesURIsToLoadIterator);
        sessionFileLoading.addAll(tempRepresentationsFilesURIs);
        // Launch the silently job to open the representations files
        for (URI representationsFilesURI : tempRepresentationsFilesURIs) {
            if (alreadyInUserWorkspaceModifyOperation) {
                WorkspaceJob job = new OpenRepresentationsFileJob(representationsFilesURI, true);
                job.setUser(user);
                job.setPriority(Job.SHORT);
                job.runInWorkspace(new NullProgressMonitor());
            } else {
                OpenRepresentationsFileJob.scheduleNewWhenPossible(representationsFilesURI, user, true);
            }
        }
    }

    /**
     * Check if the representations file is already loaded (known by SessionManager).
     *
     * @param representationsFileURI
     *            The URI of the representations file.
     * @return true if already loaded, false otherwise
     */
    private boolean isAlreadyLoaded(URI representationsFileURI) {
        for (Session session : Collections.unmodifiableCollection(SessionManager.INSTANCE.getSessions())) {
            if (representationsFileURI.equals(session.getSessionResource().getURI())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearCache(URI representationsFileURI) {
        sessionFileLoading.remove(representationsFileURI);
    }

    @Override
    public IProject createNewModelingProject(String projectName, boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor) throws CoreException {
        return createNewModelingProject(projectName, null, createAndOpenBlankRepresentationsFile, monitor);
    }

    @Override
    public IProject createNewModelingProject(final String projectName, final IPath projectLocationPath, final boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor)
            throws CoreException {
        final IWorkspaceRunnable create = new IWorkspaceRunnable() {
            @Override
            public void run(final IProgressMonitor monitor) throws CoreException {
                try {
                    monitor.beginTask(MessageFormat.format(Messages.ModelingProjectManagerImpl_createModelingProjectTask, projectName), 3);
                    final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                    if (!project.exists()) {
                        final IProjectDescription desc = project.getWorkspace().newProjectDescription(projectName);
                        IPath projectLocationPathTemp = projectLocationPath;
                        if (projectLocationPath != null && ResourcesPlugin.getWorkspace().getRoot().getLocation().equals(projectLocationPath)) {
                            projectLocationPathTemp = null;
                        }
                        desc.setLocation(projectLocationPathTemp);
                        String[] natures = { ModelingProject.NATURE_ID };
                        desc.setNatureIds(natures);

                        monitor.subTask(Messages.ModelingProjectManagerImpl_createProjectTask);
                        project.create(desc, new SubProgressMonitor(monitor, 1));
                        monitor.subTask(Messages.ModelingProjectManagerImpl_openProjectTask);
                        project.open(new SubProgressMonitor(monitor, 1));

                        if (createAndOpenBlankRepresentationsFile) {
                            monitor.subTask(Messages.ModelingProjectManagerImpl_createRepresentationFileTask);
                            Session newSession = createLocalRepresentationsFile(project, new SubProgressMonitor(monitor, 1));

                            Set<ISessionFileLoadingListener> sessionFileLoadingListeners = SiriusEditPlugin.getPlugin().getSessionFileLoadingListeners();
                            for (ISessionFileLoadingListener sessionFileLoadingListener : sessionFileLoadingListeners) {
                                sessionFileLoadingListener.notifySessionLoadedFromModelingProject(newSession);
                            }
                        }
                    }
                    if (!project.isOpen()) {
                        project.open(new SubProgressMonitor(monitor, 1));
                    }
                } finally {
                    monitor.done();
                }
            }
        };
        ResourcesPlugin.getWorkspace().run(create, monitor);
        return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    }

    @Override
    public void convertToModelingProject(final IProject project, IProgressMonitor monitor) throws CoreException {
        final IWorkspaceRunnable create = new IWorkspaceRunnable() {
            @Override
            public void run(final IProgressMonitor monitor) throws CoreException {
                try {
                    monitor.beginTask(Messages.ModelingProjectManagerImpl_convertToModelingProjectTask, 1);
                    doAddModelingNature(project, new SubProgressMonitor(monitor, 1));
                } finally {
                    monitor.done();
                }

            }
        };
        if (alreadyIsInWorkspaceModificationOperation()) {
            doAddModelingNature(project, monitor);
        } else {
            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            workspace.run(create, ResourcesPlugin.getWorkspace().getRoot(), IWorkspace.AVOID_UPDATE, monitor);
        }
    }

    private boolean alreadyIsInWorkspaceModificationOperation() {
        final Job currentJob = Job.getJobManager().currentJob();
        return currentJob != null && currentJob.getRule() != null;
    }

    @Override
    public void removeModelingNature(final IProject project, IProgressMonitor monitor) throws CoreException {
        final IWorkspaceRunnable create = new IWorkspaceRunnable() {
            @Override
            public void run(final IProgressMonitor monitor) throws CoreException {
                doRemoveModelingNature(project, monitor);
            }
        };
        ResourcesPlugin.getWorkspace().run(create, monitor);
    }

    @Override
    public Session createLocalRepresentationsFile(IProject project, IProgressMonitor monitor) throws CoreException {
        URI representationsURI = URI.createPlatformResourceURI(project.getFullPath().append(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME).toString(), true);

        /* Create a Session from the session model URI */
        org.eclipse.sirius.business.api.session.SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(representationsURI, monitor);
        sessionCreationOperation.execute();
        return sessionCreationOperation.getCreatedSession();
    }

    /**
     * Add the modeling nature. Open or create the main aird file. Look for semantic resources to add.<br>
     * This method must be called from a WorkspaceModifyOperation with WorkspaceRoot scheduling rule as it modifies the
     * nature of the project.
     *
     * @param project
     *            the project to convert.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Modeling Project nature addition
     * @throws CoreException
     *             if something fails.
     */
    protected void doAddModelingNature(IProject project, IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask(Messages.ModelingProjectManagerImpl_addingModelingNatureTask, 3);
            IProjectDescription description = project.getDescription();

            String[] natures = description.getNatureIds();
            if (description.hasNature(ModelingProjectManagerImpl.VIEWPOINT_MODELING_PROJECT_NATURE_ID)) {
                // Replace the old Viewpoint nature
                for (int i = 0; i < natures.length; i++) {
                    if (ModelingProjectManagerImpl.VIEWPOINT_MODELING_PROJECT_NATURE_ID.equals(natures[i])) {
                        natures[i] = ModelingProject.NATURE_ID;
                    }
                }
                description.setNatureIds(natures);
            } else {
                // Add the nature
                String[] newNatures = new String[natures.length + 1];
                System.arraycopy(natures, 0, newNatures, 1, natures.length);
                newNatures[0] = ModelingProject.NATURE_ID;
                description.setNatureIds(newNatures);
            }
            project.setDescription(description, new SubProgressMonitor(monitor, 1));

            // check project
            Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(project);
            if (optionalModelingProject.some()) {
                ModelingProject modelingProject = optionalModelingProject.get();
                try {
                    // See 525466. we set the validity to true in case of DefaultModelingProjectResourceListener that
                    // has set it to false or the representation file will not be created if it does not exist yet.
                    Option<URI> mainRepresentationsFileURI;
                    synchronized (modelingProject) {
                        modelingProject.setValid(true);
                        mainRepresentationsFileURI = modelingProject.getMainRepresentationsFileURI(new SubProgressMonitor(monitor, 1), false, true);
                    }
                    if (mainRepresentationsFileURI != null && mainRepresentationsFileURI.some()) {
                        // Open the session.
                        loadAndOpenRepresentationsFiles(new ArrayList<URI>(Arrays.asList(mainRepresentationsFileURI.get())), true, true);
                    }
                } catch (IllegalArgumentException e) {
                    // Clean existing marker if exists
                    try {
                        project.deleteMarkers(ModelingMarker.MARKER_TYPE, false, IResource.DEPTH_ZERO);
                    } catch (final CoreException ce) {
                        SiriusPlugin.getDefault().getLog().log(ce.getStatus());
                    }
                    if (e.getCause() != null && ModelingProjectQuery.ZERO_REPRESENTATIONS_FILE_FOUND_IN.equals(e.getCause().getMessage())) {
                        // 0 files has been found : create a representation
                        ModelingProjectManager.INSTANCE.createLocalRepresentationsFile(project, new SubProgressMonitor(monitor, 1));
                        // Project has been marked as invalid but now it has a
                        // main representation file, force the computation of
                        // its mainRepresentationFileURI.
                        modelingProject.getMainRepresentationsFileURI(new SubProgressMonitor(monitor, 1), true, true);
                    } else {
                        // Add a marker on this project
                        try {
                            final IMarker marker = project.createMarker(ModelingMarker.MARKER_TYPE);
                            marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
                            marker.setAttribute(IMarker.MESSAGE, e.getMessage());
                        } catch (final CoreException ce) {
                            SiriusPlugin.getDefault().getLog().log(ce.getStatus());
                        }
                        if (e.getCause() != null && ModelingProjectQuery.A_MODELING_PROJECT_MUST_CONTAIN_ONLY_ONE.equals(e.getCause().getMessage())) {
                            // several files have been found : rollback
                            removeModelingNature(project, new SubProgressMonitor(monitor, 1));
                            throw new CoreException(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getMessage()));
                        }
                    }
                }

                if (modelingProject.getSession() != null) {
                    // add semantic resources if already existing in the project
                    addSemanticResources(project, modelingProject.getSession(), new SubProgressMonitor(monitor, 1));
                }
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Remove the modeling nature.
     *
     * @param project
     *            the project to convert.
     * @param monitor
     *            a {@link IProgressMonitor} to show Modeling Project nature removal progression
     * @throws CoreException
     *             if something fails.
     */
    protected void doRemoveModelingNature(IProject project, IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask(Messages.ModelingProjectManagerImpl_removingModelingNatureTask, IProgressMonitor.UNKNOWN);
            IProjectDescription description = project.getDescription();
            String[] natures = description.getNatureIds();
            for (int i = 0; i < natures.length; ++i) {
                if (ModelingProject.NATURE_ID.equals(natures[i])) {
                    // Remove the nature
                    String[] newNatures = new String[natures.length - 1];
                    System.arraycopy(natures, 0, newNatures, 0, i);
                    System.arraycopy(natures, i + 1, newNatures, i, natures.length - i - 1);
                    description.setNatureIds(newNatures);
                    project.setDescription(description, new SubProgressMonitor(monitor, IProgressMonitor.UNKNOWN));

                    // remove markers corresponding to modeling projects
                    // Clean existing marker if exists
                    project.deleteMarkers(ModelingMarker.MARKER_TYPE, false, IResource.DEPTH_ZERO);
                    break;
                }
            }
        } finally {
            monitor.done();
        }
    }

    private void addSemanticResources(IContainer container, Session session, IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask(Messages.ModelingProjectManagerImpl_semanticResourcesAdditionTask, 1);
            Command semanticResourcesAdditionCommand = getSemanticResourcesAdditionCommand(container, session, monitor);
            session.getTransactionalEditingDomain().getCommandStack().execute(semanticResourcesAdditionCommand);
        } finally {
            monitor.done();
        }
    }

    private Command getSemanticResourcesAdditionCommand(IContainer container, Session session, IProgressMonitor monitor) throws CoreException {
        CompoundCommand cc = new CompoundCommand();
        if (container != null) {
            for (IResource resource : container.members()) {
                if (resource instanceof IFile) {
                    URI uri = URI.createPlatformResourceURI(resource.getFullPath().toOSString(), true);
                    if (ResourceStrategyRegistry.getInstance().isPotentialSemanticResource(uri) && ResourceStrategyRegistry.getInstance().isLoadableModel(uri, session)) {
                        AddSemanticResourceCommand cmd = new AddSemanticResourceCommand(session, uri, new SubProgressMonitor(monitor, 1));
                        cc.append(cmd);
                    }
                } else if (resource instanceof IContainer) {
                    Command subCc = getSemanticResourcesAdditionCommand((IContainer) resource, session, monitor);
                    if (subCc.canExecute()) {
                        cc.append(subCc);
                    }
                }
            }
        }
        return cc;
    }
}
