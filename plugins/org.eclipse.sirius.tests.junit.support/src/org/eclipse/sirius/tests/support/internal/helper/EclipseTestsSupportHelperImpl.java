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
package org.eclipse.sirius.tests.support.internal.helper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.filebuffers.manipulation.ContainerCreator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import junit.framework.TestCase;

/**
 * Eclipse specific operations helper.
 * 
 * @author mchauvin
 */
public final class EclipseTestsSupportHelperImpl implements EclipseTestsSupportHelper {
    /**
     * Singleton instance of the eclipse tests support.
     */
    public static final EclipseTestsSupportHelper INSTANCE = new EclipseTestsSupportHelperImpl();

    /**
     * Avoid instantiation.
     */
    private EclipseTestsSupportHelperImpl() {
        // Nothing
    }

    @Override
    public Resource createResourceInProject(final ResourceSet set, final String projectName, final String fileName) {
        return set.createResource(URI.createPlatformResourceURI("/" + projectName + "/" + fileName, true));
    }

    @Override
    public IProject createModelingProject(String projectName, boolean createAndOpenBlankRepresentationsFile) {
        try {
            return ModelingProjectManager.INSTANCE.createNewModelingProject(projectName, null, createAndOpenBlankRepresentationsFile, new NullProgressMonitor());
        } catch (CoreException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public IProject createProject(final String projectName) {
        final IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        try {
            if (!project.exists()) {
                project.create(projectDescription, new NullProgressMonitor());
            }
            project.open(new NullProgressMonitor());
        } catch (final CoreException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public void deleteProject(final String projectName) {
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            @Override
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                if (project.exists()) {
                    if (!project.isOpen()) {
                        project.open(new NullProgressMonitor());
                    }
                    // On Mac OS X, read only files might be marked as immutable
                    // too, setting the read only state to false will allow to
                    // delete the project.
                    for (IResource res : project.members(IResource.DEPTH_INFINITE)) {
                        changeFileReadOnlyAttribute(res, false);
                    }
                    project.delete(true, new NullProgressMonitor());
                }
            }
        };
        try {
            operation.run(new NullProgressMonitor());
        } catch (InvocationTargetException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String workspaceRelativePath) {
        final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspaceRelativePath));
        WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
            @Override
            protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
                if (file.exists()) {
                    // On Mac OS X, read only files might be marked as immutable
                    // too, setting the read only state to false will allow to
                    // delete the project.
                    changeFileReadOnlyAttribute(file, false);

                    file.delete(true, new NullProgressMonitor());
                }
            }
        };
        try {
            operation.run(new NullProgressMonitor());
        } catch (InvocationTargetException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            // Propagate as runtime exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public void copyFile(String relativePath, String destinationWorkspaceRelativePath) {

        if (relativePath == null) {
            throw new IllegalArgumentException("relativePath cannot be null");
        }
        if (destinationWorkspaceRelativePath == null) {
            throw new IllegalArgumentException("destination WorkspaceRelativePath cannot be null");
        }

        final File sourceFile = FileProvider.getDefault().getFile(new Path(relativePath));
        final IFile destinationFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(destinationWorkspaceRelativePath));

        if (sourceFile == null) {
            throw new IllegalArgumentException("sourceFile cannot be null: check the source path: " + relativePath);
        }
        if (destinationFile == null) {
            throw new IllegalArgumentException("destinationFile cannot be null: check the workspace relative destination path: " + destinationWorkspaceRelativePath);
        }

        copyFile(sourceFile, destinationFile, true);
    }

    @Override
    public void copyFile(final String bundleID, final String projectRelativePath, final String destinationWorkspaceRelativePath, final boolean refreshAfterCopy) {
        if (bundleID == null) {
            throw new IllegalArgumentException("bundleID cannot be null");
        }
        if (projectRelativePath == null) {
            throw new IllegalArgumentException("projectRelativePath cannot be null");
        }
        if (destinationWorkspaceRelativePath == null) {
            throw new IllegalArgumentException("destinationWorkspaceRelativePath cannot be null");
        }

        final File sourceFile = FileProvider.getDefault().getFile(bundleID, new Path(projectRelativePath));
        final IFile destinationFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(destinationWorkspaceRelativePath));

        if (sourceFile == null) {
            throw new IllegalArgumentException("sourceFile cannot be null: check the project relative source path: " + projectRelativePath + " in " + bundleID);
        }
        if (destinationFile == null) {
            throw new IllegalArgumentException("destinationFile cannot be null: check the workspace relative destination path: " + destinationWorkspaceRelativePath);
        }

        copyFile(sourceFile, destinationFile, refreshAfterCopy);
    }

    @Override
    public void copyFile(final String bundleID, final String projectRelativePath, final String destinationWorkspaceRelativePath) {
        copyFile(bundleID, projectRelativePath, destinationWorkspaceRelativePath, true);
    }

    private void copyFile(final File sourceFile, final IFile destinationFile, final boolean refreshAfterCopy) {

        final StringBuffer errorMessage = new StringBuffer();
        try {
            new ContainerCreator(ResourcesPlugin.getWorkspace(), destinationFile.getParent().getFullPath()).createContainer(new NullProgressMonitor());

            copyFile(sourceFile, new File(destinationFile.getLocation().toOSString()));

            if (refreshAfterCopy) {
                // Refresh the new created file
                destinationFile.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
            }
        } catch (final CoreException e) {
            errorMessage.append(e.getMessage());
        } catch (final IOException e) {
            errorMessage.append(e.getMessage());
        }
        if (!destinationFile.exists() && refreshAfterCopy) {
            throw new RuntimeException("Problem during the copy of the file : " + errorMessage);
        }
    }

    @Override
    public void copyFile(final File sourceFile, final File destFile) throws IOException {
        if (sourceFile == null) {
            throw new IllegalArgumentException("sourceFile cannot be null");
        }
        if (destFile == null) {
            throw new IllegalArgumentException("destFile cannot be null");
        }
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException("File '" + sourceFile + "' to copy does not exists");
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        Files.copy(Paths.get(sourceFile.getAbsolutePath()), Paths.get(destFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws IOException {
        Files.walk(Paths.get(sourceDirectoryLocation)).forEach(source -> {
            java.nio.file.Path destination = Paths.get(destinationDirectoryLocation, source.toString().substring(sourceDirectoryLocation.length()));
            try {
                Files.copy(source, destination);
            } catch (IOException e) {
            }
        });
    }

    @Override
    public void changeFileReadOnlyAttribute(final String destinationWorkspaceRelativePath, final boolean readOnly) {
        if (destinationWorkspaceRelativePath == null) {
            throw new IllegalArgumentException("destinationWorkspaceRelativePath cannot be null");
        }

        final IFile destinationFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(destinationWorkspaceRelativePath));
        if (destinationFile.exists()) {
            try {
                changeFileReadOnlyAttribute(destinationFile, readOnly);
            } catch (CoreException e) {
                // Propagate as runtime exception
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void setReadOnlyStatus(boolean readOnly, IResource... resources) {
        try {
            for (IResource res : resources) {
                changeFileReadOnlyAttribute(res, readOnly);
            }
        } catch (CoreException e) {
            TestCase.fail("Some resource can not be passed to " + (readOnly ? "read only. " : "writtable. " + e.getMessage()));
        }
    }

    private void changeFileReadOnlyAttribute(IResource res, boolean readOnly) throws CoreException {
        ResourceAttributes attr = res.getResourceAttributes();
        if (attr != null && attr.isReadOnly() != readOnly) {
            attr.setReadOnly(readOnly);
            res.setResourceAttributes(attr);
        }
    }

}
