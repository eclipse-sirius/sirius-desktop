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
package org.eclipse.sirius.ui.tools.api.project;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;

import org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.manager.ModelingProjectManagerImpl;

/**
 * A manager for modeling projects.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public interface ModelingProjectManager {
    /**
     * Singleton instance.
     */
    ModelingProjectManager INSTANCE = ModelingProjectManagerImpl.init();

    /**
     * Initialize the modeling project manager. This method should be called
     * only once by the
     * {@link org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.manager.ui.tools.internal.views.common.modelingproject.manager.ModelingProjectManagerStartup}
     * .<BR>
     * Not intended to be used by client.
     */
    void initializeAfterLoad();

    /**
     * Load and open a representations file.
     * 
     * @param representationsFileURI
     *            The URI of the representations file to open.
     */
    void loadAndOpenRepresentationsFile(final URI representationsFileURI);

    /**
     * Load and open representations files.
     * 
     * @param representationsFilesURIs
     *            The URIs of the representations files to open.
     */
    void loadAndOpenRepresentationsFiles(final List<URI> representationsFilesURIs);

    /**
     * Clear the cache for this representations file.
     * 
     * @param representationsFileURI
     *            The URI of the representations file
     */
    void clearCache(URI representationsFileURI);

    /**
     * Create a new modeling project.
     * 
     * @param projectName
     *            The project name
     * @param createAndOpenBlankRepresentationsFile
     *            true if a blank representations file must be created and open,
     *            false otherwise
     * @return The new project
     * @throws CoreException
     *             in case of problem
     * @deprecated use
     *             {@link ModelingProjectManager#createNewModelingProject(String, boolean, IProgressMonitor)}
     *             instead
     */
    IProject createNewModelingProject(final String projectName, boolean createAndOpenBlankRepresentationsFile) throws CoreException;

    /**
     * Create a new modeling project.
     * 
     * @param projectName
     *            The project name
     * @param createAndOpenBlankRepresentationsFile
     *            true if a blank representations file must be created and open,
     *            false otherwise
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Modeling
     *            Project creation
     * @return The new project
     * @throws CoreException
     *             in case of problem
     */
    IProject createNewModelingProject(final String projectName, boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor) throws CoreException;

    /**
     * Create a new modeling project.
     * 
     * @param projectName
     *            The project name
     * @param projectLocationPath
     *            The project location path
     * @param createAndOpenBlankRepresentationsFile
     *            true if a blank representations file must be created and open,
     *            false otherwise
     * @return The new project
     * @throws CoreException
     *             in case of problem
     * @deprecated use
     *             {@link ModelingProjectManager#createNewModelingProject(String, IPath, boolean, IProgressMonitor)}
     *             instead
     */
    IProject createNewModelingProject(final String projectName, final IPath projectLocationPath, final boolean createAndOpenBlankRepresentationsFile) throws CoreException;

    /**
     * Create a new modeling project.
     * 
     * @param projectName
     *            The project name
     * @param projectLocationPath
     *            The project location path
     * @param createAndOpenBlankRepresentationsFile
     *            true if a blank representations file must be created and open,
     *            false otherwise
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Modeling
     *            Project creation
     * @return The new project
     * @throws CoreException
     *             in case of problem
     */
    IProject createNewModelingProject(final String projectName, final IPath projectLocationPath, final boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor) throws CoreException;

    /**
     * Create a local representation.
     * 
     * @param project
     *            the {@link IProject} to convert.
     * @throws CoreException
     *             in case of problem (2 aird and no main one for example).
     * @deprecated use
     *             {@link ModelingProjectManager#createLocalRepresentationsFile(IProject, IProgressMonitor)}
     *             instead
     */
    void createLocalRepresentationsFile(IProject project) throws CoreException;

    /**
     * Create a local representation.
     * 
     * @param project
     *            the {@link IProject} to convert.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of local
     *            representations resource file creation
     * @throws CoreException
     *             in case of problem (2 aird and no main one for example).
     */
    void createLocalRepresentationsFile(IProject project, IProgressMonitor monitor) throws CoreException;

    /**
     * Convert an existing project to a modeling project : add a the modeling
     * project nature, look for an existing main aird file or create it and then
     * populate the semantic resources with existing models contained in the
     * project.
     * 
     * @param project
     *            IProject
     * @throws CoreException
     *             in case of problem
     * @deprecated use
     *             {@link ModelingProjectManager#convertToModelingProject(IProject, IProgressMonitor)}
     *             instead
     */
    void convertToModelingProject(IProject project) throws CoreException;

    /**
     * Convert an existing project to a modeling project : add the modeling
     * project nature, look for an existing main aird file or create it and then
     * populate the semantic resources with existing models contained in the
     * project.
     * 
     * @param project
     *            IProject
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of conversion
     * @throws CoreException
     *             in case of problem
     */
    void convertToModelingProject(IProject project, IProgressMonitor monitor) throws CoreException;

    /**
     * Convert an existing project to a modeling project.
     * 
     * @param project
     *            IProject
     * @throws CoreException
     *             in case of problem
     * @deprecated use
     *             {@link ModelingProjectManager#removeModelingNature(IProject, IProgressMonitor)}
     *             instead
     */
    void removeModelingNature(IProject project) throws CoreException;

    /**
     * Convert an existing project to a modeling project.
     * 
     * @param project
     *            IProject
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Modeling
     *            Project nature removal
     * @throws CoreException
     *             in case of problem
     */
    void removeModelingNature(IProject project, IProgressMonitor monitor) throws CoreException;
}
