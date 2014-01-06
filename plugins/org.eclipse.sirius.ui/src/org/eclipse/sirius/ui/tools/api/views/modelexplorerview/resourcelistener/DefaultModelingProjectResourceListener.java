/*******************************************************************************
 * Copyright (c) 2011, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.resource.LoadEMFResource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.modelingproject.manager.AttachSemanticResourcesJob;
import org.eclipse.sirius.business.internal.modelingproject.manager.DetachSemanticResourcesJob;
import org.eclipse.sirius.business.internal.modelingproject.manager.InitializeModelingProjectJob;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.ModelingProjectFileQuery;
import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.OpenRepresentationsFileJob;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

import com.google.common.collect.Lists;

/**
 * A workspace resource listener to automatically add files loadable as emf
 * resource to modeling project session semantic resource.
 * <p>
 * <b>Note:</b> the org.eclipse.sirius.ui.modelprojectresourcelistener
 * extension point allows to define new {@link IModelingProjectResourceListener}
 * s . This extension point is not meant to be used by clients, but reserved to
 * internal use.</b>
 * </p>
 * 
 * @author mchauvin
 */
public class DefaultModelingProjectResourceListener implements IModelingProjectResourceListener {

    /**
     * Install the listener.
     */
    public void init() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.addResourceChangeListener(this);
    }

    /**
     * Dispose the listener.
     */
    public void dispose() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.removeResourceChangeListener(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    public void resourceChanged(IResourceChangeEvent event) {
        IResourceDelta delta = event.getDelta();

        if (delta != null) {
            try {
                ResourceDeltaVisitor visitor = getResourceDeltaVisitor();
                delta.accept(visitor);

                // For each project to initialize
                for (IProject projectToInitialize : visitor.projectsToInitialize) {
                    // Launch the job to initialize the modeling project (with
                    // option to force a reset).
                    InitializeModelingProjectJob job = new InitializeModelingProjectJob(Lists.newArrayList(projectToInitialize));
                    job.setForceInit(true);
                    job.setPriority(Job.SHORT);

                    // We want to be sure that each project to initialize is not
                    // modified elsewhere (e.g. by an Import wizard) before the
                    // job is launched (see VP-3746 for further details).
                    job.setRule(projectToInitialize);
                    job.schedule();
                }
                if (!visitor.projectsToInitializeAndLoad.isEmpty()) {
                    // Launch the silently job to initialize the modeling
                    // projects and then open the main representations files of
                    // each projects.
                    OpenRepresentationsFileJob.scheduleNewWhenPossible(new ArrayList<ModelingProject>(visitor.projectsToInitializeAndLoad), false);

                }
                if (!visitor.semanticResourcesURIsToAttachPerSession.isEmpty()) {
                    Job attachSemanticResourcesJob = new AttachSemanticResourcesJob(new LinkedHashMap<Session, Set<URI>>(visitor.semanticResourcesURIsToAttachPerSession));
                    attachSemanticResourcesJob.schedule();
                    visitor.semanticResourcesURIsToAttachPerSession.clear();
                }
                if (!visitor.semanticResourcesURIsToDetachPerSession.isEmpty()) {
                    Job detachSemanticResourcesJob = new DetachSemanticResourcesJob(new LinkedHashMap<Session, Set<URI>>(visitor.semanticResourcesURIsToDetachPerSession));
                    detachSemanticResourcesJob.schedule();
                    visitor.semanticResourcesURIsToDetachPerSession.clear();
                }
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                SiriusEditPlugin.getPlugin().log(e);
            }
        } else if (IResourceChangeEvent.PRE_CLOSE == event.getType()) {
            if (event.getResource() instanceof IProject) {
                final Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject((IProject) event.getResource());
                if (optionalModelingProject.some()) {
                    // The modeling project is being closed
                    // The cleaning of the ModelingProjectManager
                    // (sessionFileLoading) is already done by the
                    // sessionManagerListener of this last.
                    // The closing (and saving) of the session is already done
                    // by the
                    // DAnalysisSessionImpl.statusChanged (ResourceSyncClient).
                }
            }
        }
    }

    /**
     * Returns the {@link ResourceDeltaVisitor} to use for visiting changes.
     * 
     * @return the {@link ResourceDeltaVisitor} to use for visiting changes
     */
    protected ResourceDeltaVisitor getResourceDeltaVisitor() {
        return new ResourceDeltaVisitor(this);
    }

    /**
     * Indicates wether the given file contains a DAnalysis model.
     * 
     * @param file
     *            the file to test
     * @return true if the given file contains a DAnalysis model, false
     *         otherwise
     */
    protected boolean isRepresentationsModel(IFile file) {
        return new FileQuery(file).isSessionResourceFile();
    }

    /**
     * Indicates wether the given file contains a VSM model.
     * 
     * @param file
     *            the file to test
     * @return true if the given file contains a VSM model, false otherwise
     */
    protected boolean isVsmModel(IFile file) {
        return new FileQuery(file).isVSMFile();
    }

    /**
     * Check if file is a loadable model.
     * 
     * @see DefaultModelingProjectResourceListener#shouldIgnoreFile(IFile) which
     *      should be called before the isLoadableModel.
     * 
     * @param file
     *            the file to test
     * @param session
     *            the session to use for trying to load the model
     * 
     * @return <code>true</code> if it is, <code>false</code> otherwise
     */
    protected boolean isLoadableModel(IFile file, Session session) {
        if (file == null) {
            return false;
        }
        final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(session.getSessionResource().getURI());
        LoadEMFResource runnable = new LoadEMFResource(set, file);
        runnable.run();
        return runnable.getLoadedResource() != null;
    }

    /**
     * Check if the file could be during semantic resource detection. If it is,
     * this listener will check if is it loadable.
     * 
     * @param file
     *            an added file
     * @return <code>false</code> if the file should be ignored.
     */
    protected boolean isPotentialSemanticResource(IFile file) {
        return file != null && new ModelingProjectFileQuery(file).isPotentialSemanticResource();
    }

}
