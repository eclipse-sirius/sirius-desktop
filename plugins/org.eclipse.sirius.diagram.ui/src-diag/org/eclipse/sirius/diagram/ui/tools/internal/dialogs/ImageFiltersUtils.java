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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * Static methods to identify and filter images.
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 *
 */
public final class ImageFiltersUtils {

    /**
     * The list of values for this enumerated type.
     */
    public static final ImageFileFormat[] VALUES = { ImageFileFormat.JPEG, ImageFileFormat.JPG, ImageFileFormat.SVG, ImageFileFormat.PNG };

    /** All supported image file extensions. */
    private static final List<String> IMAGE_FILE_EXTENSIONS = new ArrayList<String>();

    static {
        for (ImageFileFormat imageFileFormat : VALUES) {
            IMAGE_FILE_EXTENSIONS.add(imageFileFormat.getName().toLowerCase());
        }
    }

    private ImageFiltersUtils() {
    }

    /**
     * List of image file extensions.
     * 
     * @return the list of image file extensions as lowercase strings.
     */
    public static List<String> imageFileExtensionsList() {
        return IMAGE_FILE_EXTENSIONS;
    }

    /**
     * Creates the filter used to display only folders containing images.
     * 
     * @return the new created filter.
     */
    public static ViewerFilter createFileExtensionFilter() {
        return new FileExtensionFilter();
    }

    /**
     * Creates the filter used to display only folders containing images, and their images.
     * 
     * @return the new created filter.
     */
    public static ViewerFilter createImageFileFilter() {
        return new ImageFileFilter();
    }

    /**
     * Indicates if the file name corresponds to a supported image.
     * 
     * @param imageName
     *            name of the file
     * @return true if the file is a supported image; false otherwise.
     */
    public static boolean isSupportedImageFile(String imageName) {
        String extension = getExtension(imageName);
        if (!extension.isEmpty()) {
            extension = extension.toLowerCase();
            for (String imageExtension : IMAGE_FILE_EXTENSIONS) {
                if (extension.equals(imageExtension)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the extension of a file.
     * 
     * @param fileName
     *            name of the file
     * @return the value of the extension as a String if it exists; an empty String otherwise.
     */
    public static String getExtension(String fileName) {
        String extension = StringUtil.EMPTY_STRING;
        if (fileName != null) {
            int index = fileName.lastIndexOf('.');
            if (index == -1 || index == (fileName.length() - 1)) {
                return extension;
            }
            extension = fileName.substring(index + 1);
        }
        return extension;
    }

    /**
     * A filter used to select only directories containing images.
     *
     * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
     */
    static class FileExtensionFilter extends ViewerFilter {

        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            boolean isValid = false;
            if (viewer instanceof ContentViewer) {
                ContentViewer contentViewer = (ContentViewer) viewer;
                if (contentViewer.getContentProvider() instanceof ITreeContentProvider && contentViewer.getLabelProvider() instanceof ILabelProvider) {
                    ITreeContentProvider contentProvider = (ITreeContentProvider) contentViewer.getContentProvider();
                    ILabelProvider labelProvider = (ILabelProvider) contentViewer.getLabelProvider();
                    if (contentProvider.hasChildren(element)) {
                        isValid = true;
                        for (Object child : contentProvider.getChildren(element)) {
                            isValid = containsImages(child, contentProvider, labelProvider);
                            if (isValid) {
                                break;
                            }
                        }
                    }
                }
            }
            return isValid;
        }

        private boolean containsImages(Object element, ITreeContentProvider contentProvider, ILabelProvider labelProvider) {
            boolean isValid = false;
            if (contentProvider.hasChildren(element)) {
                isValid = true;
                for (Object child : contentProvider.getChildren(element)) {
                    isValid = containsImages(child, contentProvider, labelProvider);
                    if (isValid) {
                        break;
                    }
                }
            } else {
                String name = labelProvider.getText(element);
                isValid = isSupportedImageFile(name);
            }

            return isValid;
        }
    }

    /**
     * A filter used to select images and folders containing images. Images are selected if their name is a supported
     * image file.
     * 
     * @author Glenn Plouhinec
     *
     */
    static class ImageFileFilter extends ViewerFilter {

        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element) {
            boolean isValid = true;
            if (viewer instanceof ContentViewer) {
                ContentViewer contentViewer = (ContentViewer) viewer;
                if (contentViewer.getContentProvider() instanceof ITreeContentProvider && contentViewer.getLabelProvider() instanceof ILabelProvider) {
                    ITreeContentProvider contentProvider = (ITreeContentProvider) contentViewer.getContentProvider();
                    ILabelProvider labelProvider = (ILabelProvider) contentViewer.getLabelProvider();

                    if (contentProvider.hasChildren(element)) {
                        isValid = false;
                        for (Object child : contentProvider.getChildren(element)) {
                            isValid = select(viewer, parentElement, child);
                            if (isValid) {
                                break;
                            }
                        }
                    } else {
                        String name = labelProvider.getText(element);
                        isValid = isSupportedImageFile(name);
                    }
                }
            }
            return isValid;
        }
    }
}
