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
package org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.query.ResourceDeltaQuery;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.Sets;

/**
 * The visitor used to detect emf resources changes.
 * 
 * @author mchauvin.
 */
public class ResourceDeltaVisitor implements IResourceDeltaVisitor {

    /**
     * List of projects to initialize (force a reset to compute again the main
     * representations file).
     */
    protected Set<IProject> projectsToInitialize = Sets.newHashSet();

    /**
     * List of modeling projects to initialize and load.
     */
    protected Set<ModelingProject> projectsToInitializeAndLoad = Sets.newHashSet();

    /**
     * The {@link Set} of semantic resource {@link URI} to attach to each
     * {@link Session}.
     */
    protected Map<Session, Set<URI>> semanticResourcesURIsToAttachPerSession = new LinkedHashMap<Session, Set<URI>>();

    /**
     * The {@link Set} of semantic resource {@link URI} to deattach to each
     * {@link Session}.
     */
    protected Map<Session, Set<URI>> semanticResourcesURIsToDetachPerSession = new LinkedHashMap<Session, Set<URI>>();

    /**
     * The list of modeling projects that are currently being imported.
     */
    private final Set<ModelingProject> modelingProjectsBeingImported = new HashSet<ModelingProject>();

    private DefaultModelingProjectResourceListener defaultModelingProjectResourceListener;

    /**
     * Default constructor.
     * 
     * @param defaultModelingProjectResourceListener
     *            the {@link DefaultModelingProjectResourceListener} to use
     */
    public ResourceDeltaVisitor(DefaultModelingProjectResourceListener defaultModelingProjectResourceListener) {
        this.defaultModelingProjectResourceListener = defaultModelingProjectResourceListener;
    }

    /**
     * {@inheritDoc}
     */
    public boolean visit(IResourceDelta delta) throws CoreException {
        boolean visitChildren = true;
        IResource res = delta.getResource();
        if (res.getType() == IResource.FILE) {
            visitChildren = visitFile(delta, (IFile) res);
        } else if (res.getType() == IResource.PROJECT) {
            visitChildren = visitProject(delta, res.getProject());
        }
        return visitChildren;
    }

    /**
     * Visits the given resource delta that corresponds to a project.
     * 
     * @param delta
     *            The given resource delta
     * @param project
     *            The project concerning by this delta
     * @return <code>true</code> if the resource delta's children should be
     *         visited; <code>false</code> if they should be skipped.
     * @exception CoreException
     *                if the visit fails for some reason.
     */
    protected boolean visitProject(IResourceDelta delta, final IProject project) throws CoreException {
        boolean visitChildren = true;
        // The kind IResourceDelta.CHANGED and the flag
        // IResourceDelta.OPEN can not be used for detecting closing of
        // modeling project because we have not access to nature after
        // project closing.
        final Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(project);
        if (project != null) {
            if (optionalModelingProject.some()) {
                if (IResourceDelta.ADDED == delta.getKind() && !project.isSynchronized(IResource.DEPTH_INFINITE)) {
                    // After importing a project, we have this first
                    // notification, but the project is not synchronized so
                    // we can not determine the main representations files
                    // because it currently contains only the .project file.
                    // So this project is added to the list of modeling
                    // project being imported to launch an initialization
                    // for the next changed notification on this project
                    // (refresh of it).
                    modelingProjectsBeingImported.add(optionalModelingProject.get());
                } else if (IResourceDelta.ADDED == delta.getKind() && project.isSynchronized(IResource.DEPTH_INFINITE)) {
                    // For empty project (only with .project file), the rule
                    // explain above is not true and the project is
                    // considered as sync and no notification will be thrown
                    // after. So we must initialize this project now.
                    projectsToInitialize.add(project);
                } else if (IResourceDelta.CHANGED == delta.getKind() && modelingProjectsBeingImported.contains(optionalModelingProject.get())) {
                    // This case follows a IResourceDelta.ADDED event, so we
                    // must now initialize this project.
                    modelingProjectsBeingImported.remove(optionalModelingProject.get());
                    projectsToInitialize.add(project);
                } else if (IResourceDelta.CHANGED == delta.getKind() && (delta.getFlags() & IResourceDelta.OPEN) != 0) {
                    // An existing project is opened, so automatically
                    // initialize and load the representations file
                    projectsToInitializeAndLoad.add(optionalModelingProject.get());
                } else if (IResourceDelta.CHANGED == delta.getKind() && (delta.getFlags() & IResourceDelta.DESCRIPTION) != 0) {
                    // The project's description has changed (possibly the
                    // modeling nature has been added).
                    if (new ResourceDeltaQuery(delta).hasModelingNatureAdded()) {
                        // So automatically initialize and load the
                        // representations file
                        projectsToInitializeAndLoad.add(optionalModelingProject.get());
                    }
                }
            } else if (IResourceDelta.CHANGED == delta.getKind() && (delta.getFlags() & IResourceDelta.DESCRIPTION) != 0) {
                // The project's description has changed (possibly the
                // modeling nature has been removed).
                if (new ResourceDeltaQuery(delta).hasModelingNatureRemoved()) {
                    // Clean existing marker if exists
                    WorkspaceJob job = new MarkerDeletionJob(project);
                    job.setRule(project);
                    job.schedule();
                }
            }
        }
        return visitChildren;
    }

    /**
     * Visits the given resource delta that corresponds to a file.
     * 
     * @param delta
     *            The given resource delta
     * @param file
     *            The file concerning by this delta
     * @return <code>true</code> if the resource delta's children should be
     *         visited; <code>false</code> if they should be skipped.
     */
    protected boolean visitFile(IResourceDelta delta, IFile file) {
        boolean visitChildren = true;
        IProject project = file.getProject();
        Option<ModelingProject> modelingProject = ModelingProject.asModelingProject(project);
        if (project != null && modelingProject.some() && !projectsToInitializeAndLoad.contains(modelingProject.get()) && !projectsToInitialize.contains(project)) {
            // If the modelingProject is in the list to initialize do
            // nothing on its files
            final Session session = modelingProject.get().getSession();
            if (session != null) {
                visitFileWithOpenedRepresentationsFile(delta, file, session);
            } else {
                // There is no session, probably because this modeling
                // project is invalid. So we must initialize this
                // project again
                switch (delta.getKind()) {
                case IResourceDelta.ADDED:
                case IResourceDelta.REMOVED:
                    if (defaultModelingProjectResourceListener.isRepresentationsModel(file)) {
                        // A representations file is added so this project
                        // is potentially became valid, so we must load it.
                        projectsToInitializeAndLoad.add(modelingProject.get());
                    }
                    break;
                case IResourceDelta.CHANGED:
                    /* Ignore markers-only changes */
                    if (delta.getMarkerDeltas().length == 0) {
                        /* do nothing for the moment */
                    }
                    break;
                default:
                    /* Ignore other delta kinds. */
                    break;
                }
            }
            visitChildren = false;
        }
        return visitChildren;
    }

    /**
     * Visits the given resource delta that corresponds to a file that contains
     * in a modeling project with an opened session.
     * 
     * @param delta
     *            The given resource delta
     * @param file
     *            The file concerning by this delta
     * @param session
     *            The opened session associated with the modeling project of
     *            this file
     */
    protected void visitFileWithOpenedRepresentationsFile(IResourceDelta delta, IFile file, final Session session) {
        final URI uri = URI.createPlatformResourceURI(delta.getFullPath().toString(), true);
        switch (delta.getKind()) {
        case IResourceDelta.ADDED:
            if (defaultModelingProjectResourceListener.isPotentialSemanticResource(file) && defaultModelingProjectResourceListener.isLoadableModel(file, session)) {
                if (session != null) {
                    Set<URI> semanticResourcesURIsToAttach = semanticResourcesURIsToAttachPerSession.get(session);
                    if (semanticResourcesURIsToAttach == null) {
                        semanticResourcesURIsToAttach = new LinkedHashSet<URI>();
                        semanticResourcesURIsToAttachPerSession.put(session, semanticResourcesURIsToAttach);
                    }
                    semanticResourcesURIsToAttach.add(uri);
                }
            } else if (this.defaultModelingProjectResourceListener.isRepresentationsModel(file)) {
                projectsToInitialize.add(file.getProject());
            }
            /*
             * We should probably be clever here to handle controlled resource
             */
            break;
        case IResourceDelta.REMOVED:
            if (session != null) {
                Set<URI> semanticResourcesURIsToDetach = semanticResourcesURIsToDetachPerSession.get(session);
                if (semanticResourcesURIsToDetach == null) {
                    semanticResourcesURIsToDetach = new LinkedHashSet<URI>();
                    semanticResourcesURIsToDetachPerSession.put(session, semanticResourcesURIsToDetach);
                }
                semanticResourcesURIsToDetach.add(uri);
            }
            if (this.defaultModelingProjectResourceListener.isRepresentationsModel(file)) {
                projectsToInitialize.add(file.getProject());
            }
            break;
        case IResourceDelta.CHANGED:
            /* Ignore markers-only changes */
            if (delta.getMarkerDeltas().length == 0) {
                /* do nothing for the moment */
            }
            break;
        default:
            /* Ignore other delta kinds. */
        }
    }

}
