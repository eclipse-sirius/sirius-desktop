/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.services.common.api;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.DefaultLocalSessionCreationOperation;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionCreationOperation;
import org.eclipse.sirius.services.common.internal.SiriusCommonServicesPlugin;

/**
 * A set of services to handle project I/O.
 * 
 * @author hmarchadour
 */
public class ProjectServices {

    /**
     * Test if an accessible project named with the given projectName exists.
     * 
     * @param projectName
     *            the project name.
     * @return true when a project is accessible.
     */
    public static boolean isAccessibleProject(String projectName) {
        IProject project = internalGetProject(projectName);
        return project.isAccessible();
    }

    /**
     * Returns an optional containing the project computed from the given object or an empty optional if it could not be
     * found.
     * 
     * @param object
     *            The name of the project as an Object
     * @return An optional with the project found or an empty optional
     */
    public static Optional<IProject> projectFromName(Object object) {
        // @formatter:off
        return Optional.ofNullable(object)
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(ResourcesPlugin.getWorkspace().getRoot()::getProject);
        // @formatter:on
    }

    /**
     * Create a new project according to the given {@link IProject}.
     * 
     * @param project
     *            the {@link IProject} to create
     * @throws CoreException
     */
    public static void createProject(IProject project) throws CoreException {
        internalCreateProject(project);
    }

    /**
     * Create a new modeling project according to the given {@link IProject}.
     * 
     * @param project
     *            the {@link IProject}
     * @return the expected modeling project or null
     * @throws CoreException
     */
    public static ModelingProject createModelingProject(IProject project) throws CoreException {
        internalCreateProject(project, ModelingProject.NATURE_ID);
        ModelingProject modelingProject = ModelingProject.asModelingProject(project).get();
        createDefaultAird(project);
        return modelingProject;
    }

    /**
     * Add a resource in the given {@link ModelingProject} at the given locaPath initialized with rootElements and return
     * it.
     * 
     * @param project
     *            the {@link ModelingProject}
     * @param localPath
     *            the resource local path
     * @param rootElements
     *            rootElements to store
     * @return the expected resource wrapped in an optional
     */
    public static Optional<Resource> addResource(ModelingProject project, String localPath, EObject... rootElements) {
        Optional<Resource> result = Optional.empty();
        URI resourceURI = URI.createPlatformResourceURI(project.getProject().getFullPath().append(localPath).toString(), true);
        Session session = project.getSession();
        Resource resource = session.getTransactionalEditingDomain().createResource(resourceURI.toString());

        result = Optional.ofNullable(resource);
        RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                for (EObject rootElement : rootElements) {
                    resource.getContents().add(rootElement);
                }
                try {
                    resource.save(Collections.EMPTY_MAP);
                    session.addSemanticResource(resourceURI, new NullProgressMonitor());
                } catch (IOException e) {
                    IStatus status = new Status(IStatus.ERROR, SiriusCommonServicesPlugin.PLUGIN_ID, e.getMessage(), e);
                    SiriusCommonServicesPlugin.getPlugin().getLog().log(status);
                }
            }
        };
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        session.save(new NullProgressMonitor());
        return result;
    }

    /**
     * Delete the given {@link IProject}.
     * 
     * @param project
     *            the {@link IProject} to delete.
     * @throws CoreException
     */
    public static void delete(IProject project) throws CoreException {
        project.getProject().delete(true, new NullProgressMonitor());
    }

    /**
     * Create a default AIRD in the given {@link IProject}.
     * 
     * @param project
     *            the {@link IProject}
     * @throws CoreException
     */
    public static void createDefaultAird(IProject project) throws CoreException {
        URI representationsURI = URI.createPlatformResourceURI(project.getFullPath().append(ModelingProject.DEFAULT_REPRESENTATIONS_FILE_NAME).toString(), true);
        SessionCreationOperation sessionCreationOperation = new DefaultLocalSessionCreationOperation(representationsURI, new NullProgressMonitor());
        sessionCreationOperation.execute();
    }

    private static void internalCreateProject(IProject project, String... natures) throws CoreException {
        if (!project.exists()) {
            IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
            projectDescription.setNatureIds(natures);
            project.create(projectDescription, new NullProgressMonitor());
            project.open(new NullProgressMonitor());

        } else {
            throw new IllegalStateException("Project with this name already exist"); //$NON-NLS-1$
        }
    }

    private static IProject internalGetProject(String projectName) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
    }
}
