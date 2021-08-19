/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.sirius.diagram.ui.business.api.image.ITreeImagesContentProvider;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageFiltersUtils;
import org.eclipse.ui.model.WorkbenchContentProvider;

/**
 * A tree content provider that manages a {@link java.io.File} structure for images in workspace.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public class ImageWorkspaceContentProvider implements ITreeImagesContentProvider {

    /**
     * The character that separates folder names.
     */
    private static final char SEPARATOR = File.separatorChar;

    private WorkbenchContentProvider wcp;

    /**
     * Absolute workspace path.
     */
    private final String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString() + SEPARATOR;

    /**
     * Create a new instance of {@link ImageWorkspaceContentProvider}.
     */
    public ImageWorkspaceContentProvider() {
        this.wcp = new WorkbenchContentProvider();
    }

    @Override
    public Object[] getElements(Object inputElement) {
        Object[] result = new Object[0];
        if (inputElement instanceof IContainer) {
            IContainer iContainer = (IContainer) inputElement;
            result = iContainer.getLocation().toFile().listFiles();
        } else if (inputElement instanceof File) {
            File file = (File) inputElement;
            result = file.listFiles();
        } else {
            result = wcp.getChildren(inputElement);
        }
        if (result == null) {
            result = new Object[0];
        } else {
            result = keepImagesAndDirectories(result);
        }
        return result;
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        Object[] result = new Object[0];
        if (parentElement instanceof IContainer) {
            IContainer iContainer = (IContainer) parentElement;
            result = iContainer.getLocation().toFile().listFiles();
        } else if (parentElement instanceof File) {
            File file = (File) parentElement;
            result = file.listFiles();
        } else {
            result = wcp.getChildren(parentElement);
        }
        if (result == null) {
            result = new Object[0];
        } else {
            result = keepImagesAndDirectories(result);
        }
        return result;
    }

    private Object[] keepImagesAndDirectories(Object[] result) {
        List<Object> visibleFiles = new ArrayList<>();
        for (Object element : result) {
            if (element instanceof File) {
                File file = (File) element;
                boolean isDirectoryToAdd = file.isDirectory() && !(file.listFiles().length < 1) && !file.getName().startsWith("."); //$NON-NLS-1$
                boolean isImageFile = !file.isDirectory() && getImageFile(element).isPresent();
                if (isDirectoryToAdd || isImageFile) {
                    visibleFiles.add(element);
                }
            }
        }
        return visibleFiles.toArray();
    }

    @Override
    public Object getParent(Object element) {
        Object result = wcp.getParent(element);
        if (result == null) {
            if (element instanceof File) {
                File file = (File) element;
                File parentFile = file.getParentFile();
                if (parentFile != null) {
                    if (!workspacePath.equals(file.getPath() + SEPARATOR)) {
                        result = parentFile;
                    } else {
                        result = null;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    @Override
    public Optional<File> getImageFile(Object element) {
        Optional<File> result = Optional.empty();
        if (element instanceof File && !((File) element).isDirectory()) {
            File file = (File) element;
            if (ImageFiltersUtils.isSupportedImageFile(file.getName())) {
                result = Optional.of(file);
            }
        }
        return result;
    }

    @Override
    public Optional<String> getPath(Object element) {
        Optional<String> result = Optional.empty();
        if (element instanceof File) {
            File file = (File) element;
            String filePath = file.getPath();
            if (filePath.startsWith(workspacePath)) {
                result = Optional.of(filePath.substring(workspacePath.length()));
            }
        }
        return result;
    }
}
