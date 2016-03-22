/*******************************************************************************
 * Copyright (c) 2011, 2016 THALES GLOBAL SERVICES.
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
     * Load and open a representations file.
     * 
     * @param representationsFileURI
     *            The URI of the representations file to open.
     */
    void loadAndOpenRepresentationsFile(URI representationsFileURI);

    /**
     * Load and open a representations file.
     * 
     * @param representationsFileURI
     *            The URI of the representations file to open.
     * @param user
     *            <code>true</code> if this job is a user-initiated job, and
     *            <code>false</code> otherwise.
     */
    void loadAndOpenRepresentationsFile(URI representationsFileURI, boolean user);

    /**
     * Load and open representations files.
     * 
     * @param representationsFilesURIs
     *            The URIs of the representations files to open.
     */
    void loadAndOpenRepresentationsFiles(List<URI> representationsFilesURIs);

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
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Modeling
     *            Project creation
     * @return The new project
     * @throws CoreException
     *             in case of problem
     */
    IProject createNewModelingProject(String projectName, boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor) throws CoreException;

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
    IProject createNewModelingProject(String projectName, IPath projectLocationPath, boolean createAndOpenBlankRepresentationsFile, IProgressMonitor monitor) throws CoreException;

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
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of Modeling
     *            Project nature removal
     * @throws CoreException
     *             in case of problem
     */
    void removeModelingNature(IProject project, IProgressMonitor monitor) throws CoreException;
}
