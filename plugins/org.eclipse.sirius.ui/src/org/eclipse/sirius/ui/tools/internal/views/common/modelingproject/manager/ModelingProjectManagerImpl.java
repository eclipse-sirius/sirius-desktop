/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.manager;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.resource.LoadEMFResource;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.internal.modelingproject.marker.ModelingMarker;
import org.eclipse.sirius.business.internal.query.ModelingProjectQuery;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.ModelingProjectFileQuery;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A manager for modeling projects.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ModelingProjectManagerImpl implements ModelingProjectManager {

    /** The old Viewpoint nature id. */
    private static final String VIEWPOINT_MODELING_PROJECT_NATURE_ID = "fr.obeo.dsl.viewpoint.nature.modelingproject"; //$NON-NLS-1$

    private final SessionManagerListener sessionManagerListener = new SessionManagerListener.Stub() {
        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.business.api.session.SessionManagerListener#notify(org.eclipse.sirius.business.api.session.Session,
         *      int)
         */
        public void notify(Session updated, int notification) {
            if (notification == SessionListener.OPENING) {
                // No need to at it again to the sessionFileLoading list because
                // we add it during the starting of the load
                // ModelingProjectManager.loadAndOpenSession().
            } else if (notification == SessionListener.OPENED) {
                sessionFileLoading.remove(updated.getSessionResource().getURI());
            }
        }
    };

    private Predicate<URI> isAlreadyLoadedPredicate = new Predicate<URI>() {
        public boolean apply(URI representationsFileURI) {
            return isAlreadyLoaded(representationsFileURI);
        }
    };

    /**
     * Set of representations files that are currently loading. There can be
     * only one representations file in loading at same time. However there may
     * be many waiting to be loaded.
     */
    private Set<URI> sessionFileLoading = Sets.newHashSet();

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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager#loadAndOpenRepresentationsFile(org.eclipse.emf.common.util.URI)
     */
    public void loadAndOpenRepresentationsFile(final URI representationsFileURI) {
        loadAndOpenRepresentationsFiles(Lists.newArrayList(representationsFileURI));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager#loadAndOpenRepresentationsFiles(java.util.List)
     */
    public void loadAndOpenRepresentationsFiles(final List<URI> representationsFilesURIs) {
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
        OpenRepresentationsFileJob.scheduleNewWhenPossible(tempRepresentationsFilesURIs, false);
    }

    /**
     * Check if the representations file is already loaded (known by
     * SessionManager).
     * 
     * @param representationsFileURI
     *            The URI of the representations file.
     * @return true if already loaded, false otherwise
     */
    private boolean isAlreadyLoaded(URI representationsFileURI) {
        for (Iterator<Session> iterator = Collections.unmodifiableCollection(SessionManager.INSTANCE.getSessions()).iterator(); iterator.hasNext(); /* */) {
            Session session = iterator.next();
            if (representationsFileURI.equals(session.getSessionResource().getURI())) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager#clearCache(org.eclipse.emf.common.util.URI)
     */
    public void clearCache(URI representationsFileURI) {
        sessionFileLoading.remove(representationsFileURI);
    }

    /**
     * {@inheritDoc}
     */
    public IProject createNewModelingProject(String projectName, boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor) throws CoreException {
        return createNewModelingProject(projectName, null, createAndOpenBlankRepresentationsFile, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public IProject createNewModelingProject(final String projectName, final IPath projectLocationPath, final boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor)
            throws CoreException {
        final IWorkspaceRunnable create = new IWorkspaceRunnable() {
            public void run(final IProgressMonitor monitor) throws CoreException {
                try {
                    monitor.beginTask("Modeling Project creation : " + projectName, 3);
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

                        monitor.subTask("Create project");
                        project.create(desc, new SubProgressMonitor(monitor, 1));
                        monitor.subTask("Open project");
                        project.open(new SubProgressMonitor(monitor, 1));

                        if (createAndOpenBlankRepresentationsFile) {
                            monitor.subTask("Create local representations file");
                            createLocalRepresentationsFile(project, new SubProgressMonitor(monitor, 1));
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

    /**
     * {@inheritDoc}
     */
    public void convertToModelingProject(final IProject project, IProgressMonitor monitor) throws CoreException {
        final IWorkspaceRunnable create = new IWorkspaceRunnable() {
            public void run(final IProgressMonitor monitor) throws CoreException {
                try {
                    monitor.beginTask("Conversion to Modeling Project", 1);
                    doAddModelingNature(project, new SubProgressMonitor(monitor, 1));
                } finally {
                    monitor.done();
                }

            }
        };
        if (alreadyIsInWorkspaceModificationOperation()) {
            doAddModelingNature(project, monitor);
        } else {
            ResourcesPlugin.getWorkspace().run(create, monitor);
        }
    }

    private boolean alreadyIsInWorkspaceModificationOperation() {
        final Job currentJob = Job.getJobManager().currentJob();
        return currentJob != null && currentJob.getRule() != null;
    }

    /**
     * {@inheritDoc}
     */
    public void removeModelingNature(final IProject project, IProgressMonitor monitor) throws CoreException {
        final IWorkspaceRunnable create = new IWorkspaceRunnable() {
            public void run(final IProgressMonitor monitor) throws CoreException {
                doRemoveModelingNature(project, monitor);
            }
        };
        ResourcesPlugin.getWorkspace().run(create, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public void createLocalRepresentationsFile(IProject project, IProgressMonitor monitor) throws CoreException {
        URI representationsURI = URI.createPlatformResourceURI(project.getFullPath().append(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME).toString(), true);

        /* Create a Session from the session model URI */
        org.eclipse.sirius.business.api.session.SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(representationsURI, monitor);
        sessionCreationOperation.execute();
    }

    /**
     * Add the modeling nature. Open or create the main aird file. Look for
     * semantic resources to add.
     * 
     * @param project
     *            the project to convert.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Modeling
     *            Project nature addition
     * @throws CoreException
     *             if something fails.
     */
    protected void doAddModelingNature(IProject project, IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask("Add Modeling Project nature", 3);
            IProjectDescription description = project.getDescription();

            String[] natures = description.getNatureIds();
            if (description.hasNature(VIEWPOINT_MODELING_PROJECT_NATURE_ID)) {
                // Replace the old Viewpoint nature
                for (int i = 0; i < natures.length; i++) {
                    if (VIEWPOINT_MODELING_PROJECT_NATURE_ID.equals(natures[i])) {
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
                try {
                    Option<URI> mainRepresentationsFileURI = optionalModelingProject.get().getMainRepresentationsFileURI(new SubProgressMonitor(monitor, 1), false, true);
                    if (mainRepresentationsFileURI.some()) {
                        // Open the session.
                        OpenRepresentationsFileJob.scheduleNewWhenPossible(mainRepresentationsFileURI.get(), true);
                    }
                } catch (IllegalArgumentException e) {
                    if (e.getMessage().contains(ModelingProjectQuery.ZERO_REPRESENTATIONS_FILE_FOUND_IN)) {
                        // 0 files has been found : create a representation
                        ModelingProjectManager.INSTANCE.createLocalRepresentationsFile(project, new SubProgressMonitor(monitor, 1));
                        // Project has been marked as invalid but now it has a
                        // main representation file, force the computation of
                        // its mainRepresentationFileURI.
                        optionalModelingProject.get().getMainRepresentationsFileURI(new SubProgressMonitor(monitor, 1), true, true);
                    } else if (e.getMessage().contains(ModelingProjectQuery.A_MODELING_PROJECT_MUST_CONTAIN_ONLY_ONE)) {
                        // several files have been found : rollback
                        removeModelingNature(project, new SubProgressMonitor(monitor, 1));
                        throw new CoreException(new Status(IStatus.ERROR, SiriusEditPlugin.ID, e.getMessage()));
                    }
                }

                // open session
                if (OpenRepresentationsFileJob.shouldWaitOtherJobs()) {
                    // We are loading session(s)
                    OpenRepresentationsFileJob.waitOtherJobs();
                }
                if (optionalModelingProject.get().getSession() != null) {
                    // add semantic resources if already existing in the project
                    addSemanticResources(project, optionalModelingProject.get().getSession(), new SubProgressMonitor(monitor, 1));
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
     *            a {@link IProgressMonitor} to show Modeling Project nature
     *            removal progression
     * @throws CoreException
     *             if something fails.
     */
    protected void doRemoveModelingNature(IProject project, IProgressMonitor monitor) throws CoreException {
        try {
            monitor.beginTask("Remove Modeling Project nature", IProgressMonitor.UNKNOWN);
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
            monitor.beginTask("Semantic resources addition", 1);
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
                if (resource instanceof IFile && new ModelingProjectFileQuery((IFile) resource).isPotentialSemanticResource() && isLoadableModel((IFile) resource, session)) {
                    final URI uri = URI.createPlatformResourceURI(resource.getFullPath().toOSString(), true);
                    AddSemanticResourceCommand cmd = new AddSemanticResourceCommand(session, uri, new SubProgressMonitor(monitor, 1));
                    cc.append(cmd);
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

    /**
     * Check if file is a loadable model.
     * 
     * @param file
     *            IFile
     * @param session
     *            Session
     * 
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    private boolean isLoadableModel(IFile file, Session session) {
        if (file == null) {
            return false;
        }
        final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(session.getSessionResource().getURI());
        LoadEMFResource runnable = new LoadEMFResource(set, file);
        runnable.run();
        Resource resource = runnable.getLoadedResource();
        return resource != null && !(new ResourceQuery(resource).isRepresentationsResource());
    }

}
