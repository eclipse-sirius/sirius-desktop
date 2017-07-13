/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * Useful methods related to eclipse workspace.
 * 
 * @author mchauvin
 */
public final class WorkspaceUtil {

    /**
     * Avoid instantiation.
     */
    private WorkspaceUtil() {

    }

    /**
     * Get the files with a specific extension in the given containers (Project, Folder etc.).
     * 
     * @param containers
     *            the containers in which to look for
     * 
     * @param extension
     *            the file extension
     * 
     * @return the files in the workspace which the given extension
     * 
     *         examples :
     * 
     *         getFilesFromWorkspace (null) : return all files from the workspace getFilesFromWorkspaces ("java" :
     *         return all java sources files from the workspace
     */
    public static List<IFile> getFilesFromWorkspace(final Collection<IContainer> containers, final String extension) {
        List<IFile> allFiles = new ArrayList<IFile>();
        try {
            allFiles = getWorkspaceFiles(containers.toArray(new IContainer[containers.size()]));
        } catch (final CoreException e1) {
            // do nothing -- fail silently
        }
        if (extension == null)
            return allFiles;

        final List<IFile> toRemove = new ArrayList<IFile>();
        for (final IFile file : allFiles) {
            if (!extension.equals(file.getFileExtension()))
                toRemove.add(file);
        }
        for (final IFile file : toRemove) {
            allFiles.remove(file);
        }
        return allFiles;
    }

    /**
     * Get all files contained in a set of resources.
     * 
     * @param resources
     *            the resources to scan
     * @throws CoreException
     *             if {@link org.eclipse.core.resources.IContainer#members()} on a resource fails. Reasons include:
     *             <ul>
     *             <li>This resource does not exist.</li>
     *             <li>This resource is a project that is not open.</li>
     *             </ul>
     */
    private static List<IFile> getWorkspaceFiles(final IResource[] resources) throws CoreException {
        final List<IFile> files = new ArrayList<IFile>();
        for (int i = 0; i < resources.length; i++) {
            final IResource resource = resources[i];
            if (resource.isAccessible()) {
                if (resource instanceof IFile)
                    files.add((IFile) resource);
                else if (resource instanceof IContainer)
                    files.addAll(getWorkspaceFiles(((IContainer) resource).members()));
            }
        }
        return files;
    }

}
