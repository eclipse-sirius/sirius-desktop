/*******************************************************************************
 * Copyright (c) 2011, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.modelingproject.manager.AttachSemanticResourcesJob;
import org.eclipse.sirius.business.internal.modelingproject.manager.InitializeModelingProjectJob;
import org.eclipse.sirius.business.internal.modelingproject.marker.InvalidModelingProjectMarkerUpdaterJob;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;

/**
 * A workspace resource listener to automatically add files loadable as emf
 * resource to modeling project session semantic resource.
 * <p>
 * <b>Note:</b> the org.eclipse.sirius.ui.modelprojectresourcelistener extension
 * point allows to define new {@link IModelingProjectResourceListener} s . This
 * extension point is not meant to be used by clients, but reserved to internal
 * use.</b>
 * </p>
 * 
 * @author mchauvin
 */
public class DefaultModelingProjectResourceListener implements IModelingProjectResourceListener {

    /**
     * Install the listener.
     */
    @Override
    public void init() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.addResourceChangeListener(this);
    }

    /**
     * Dispose the listener.
     */
    @Override
    public void dispose() {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        workspace.removeResourceChangeListener(this);
    }

    @Override
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
                    InitializeModelingProjectJob job = new InitializeModelingProjectJob(new ArrayList<IProject>(Arrays.asList(projectToInitialize)));
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
                    for (ModelingProject modelingProject : visitor.projectsToInitializeAndLoad) {
                        try {
                            Option<URI> mainRepresentationsFileURIOption = modelingProject.getMainRepresentationsFileURI(new NullProgressMonitor(), true, true);
                            if (mainRepresentationsFileURIOption.some()) {
                                URI mainRepresentationsFileURI = mainRepresentationsFileURIOption.get();
                                ModelingProjectManager.INSTANCE.loadAndOpenRepresentationsFile(mainRepresentationsFileURI, true);
                            }
                        } catch (IllegalArgumentException e) {
                            IProject project = modelingProject.getProject();
                            Job invalidModelingProjectMarkerUpdaterJob = new InvalidModelingProjectMarkerUpdaterJob(project, e.getMessage());
                            invalidModelingProjectMarkerUpdaterJob.schedule();
                        }
                    }
                }
                if (!visitor.semanticResourcesURIsToAttachPerSession.isEmpty()) {
                    Job attachSemanticResourcesJob = new AttachSemanticResourcesJob(new LinkedHashMap<Session, Set<URI>>(visitor.semanticResourcesURIsToAttachPerSession));
                    attachSemanticResourcesJob.schedule();
                    visitor.semanticResourcesURIsToAttachPerSession.clear();
                }
                // No need to do anything special for removed semantic
                // resources, the Session already reacts correctly to them.
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                SiriusEditPlugin.getPlugin().log(e);
            }
        }
    }

    /**
     * Returns the {@link ResourceDeltaVisitor} to use for visiting changes.
     * 
     * @return the {@link ResourceDeltaVisitor} to use for visiting changes
     */
    protected ResourceDeltaVisitor getResourceDeltaVisitor() {
        return new ResourceDeltaVisitor();
    }
}
