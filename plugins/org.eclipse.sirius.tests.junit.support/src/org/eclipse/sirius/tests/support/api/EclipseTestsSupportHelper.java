/**
 * Copyright (c) 2009, 2022 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.tests.support.internal.helper.EclipseTestsSupportHelperImpl;

/**
 * Instance managing the EclipseTestsSupportHelper.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public interface EclipseTestsSupportHelper {
    /**
     * Singleton instance of the eclipse tests support.
     */
    EclipseTestsSupportHelper INSTANCE = EclipseTestsSupportHelperImpl.INSTANCE;

    /**
     * Create a project.
     * 
     * @param projectName
     *            name of the created project
     * 
     * @return a new created Project
     */
    IProject createProject(String projectName);

    /**
     * Create a project.
     * 
     * @param projectName
     *            name of the created project
     * @param createAndOpenBlankRepresentationsFile
     *            true if a blank representations file must be created and open, false otherwise
     * 
     * @return a new created Project
     */
    IProject createModelingProject(String projectName, boolean createAndOpenBlankRepresentationsFile);

    /**
     * Create a resource in a project.
     * 
     * @param set
     *            the resource set
     * @param projectName
     *            name of the project
     * @param fileName
     *            name of the file to create
     * @return the created resource
     */
    Resource createResourceInProject(ResourceSet set, String projectName, String fileName);

    /**
     * Delete the project.
     * 
     * @param projectName
     *            name of the project to delete.
     */
    void deleteProject(String projectName);

    /**
     * Copy a file from a plug-in in the workspace.
     * 
     * @param bundleID
     *            The ID of the bundle or project containing the source file
     * @param projectRelativePath
     *            The project relative path of the source file
     * @param destinationWorkspaceRelativePath
     *            the destination path
     */
    void copyFile(String bundleID, String projectRelativePath, String destinationWorkspaceRelativePath);

    /**
     * Copy a file from a plug-in in the workspace.
     * 
     * @param bundleID
     *            The ID of the bundle or project containing the source file
     * @param projectRelativePath
     *            The project relative path of the source file
     * @param destinationWorkspaceRelativePath
     *            the destination path
     * @param refreshAfterCopy
     *            should refresh destination file after copy
     */
    void copyFile(String bundleID, String projectRelativePath, String destinationWorkspaceRelativePath, boolean refreshAfterCopy);

    /**
     * Copy a file from a plug-in in the workspace.
     * 
     * @param relativePath
     *            The relative path of the source file
     * @param destinationWorkspaceRelativePath
     *            the destination path
     */
    void copyFile(String relativePath, String destinationWorkspaceRelativePath);

    /**
     * Copy a file to another one.
     * 
     * @param sourceFile
     *            The source file
     * @param destFile
     *            The destination file
     * @throws IOException
     *             In case of problem
     */
    void copyFile(File sourceFile, File destFile) throws IOException;

    /**
     * Change the read only status of the file with the given path.
     * 
     * @param fileWorkspaceRelativePath
     *            the path of the file to modify, relative to workspace
     * @param readOnly
     *            <code>true</code> if specified file must be read-only, <code>false</code> otherwise.
     */
    void changeFileReadOnlyAttribute(String fileWorkspaceRelativePath, boolean readOnly);

    /**
     * Change the read only status of the given resources.
     * 
     * Please note that some file systems might not support setting the file as read only or might lock the files.
     * 
     * @param readOnly
     *            the read only status to set
     * @param resources
     *            the files to update
     */
    void setReadOnlyStatus(boolean readOnly, IResource... resources);

    /**
     * Delete this file (launch in a WorkspaceModifyOperation).
     * 
     * @param workspaceRelativePath
     *            The path of the file to delete, relative to the workspace.
     */
    void deleteFile(String workspaceRelativePath);

    /**
     * Copy all files recursively from the source location to the target location.
     * 
     * @param sourceDirectoryLocation
     *            the absolute path source location
     * @param destinationDirectoryLocation
     *            the absolute path target location
     * @throws IOException
     *             in case of problem
     */
    void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws IOException;
}
