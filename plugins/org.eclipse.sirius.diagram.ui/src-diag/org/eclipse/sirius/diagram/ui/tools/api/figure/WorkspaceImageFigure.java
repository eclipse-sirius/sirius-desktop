/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.internal.refresh.listeners.WorkspaceFileResourceChangeListener;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AbstractTransparentImage;
import org.eclipse.swt.graphics.Image;

/**
 * The {@link WorkspaceImageFigure} is useful to load images using a cache. The image can be in the workspace, or if
 * it's not found in the workspace it will be looked up in the plug-ins.
 *
 * @author cbrun
 *
 */
public class WorkspaceImageFigure extends AbstractTransparentImage implements IWorkspaceImageFigure {

    private double imageAspectRatio = 1.0;

    private boolean keepAspectRatio = true;

    /**
     * Create a new {@link WorkspaceImageFigure}.
     *
     * @param flyWeightImage
     *            an image instance.
     */
    public WorkspaceImageFigure(final Image flyWeightImage) {
        super(flyWeightImage);
        imageAspectRatio = (double) flyWeightImage.getBounds().width / flyWeightImage.getBounds().height;
        minSize = new Dimension(0, 0);
    }

    @Override
    public void setSize(final int w, final int h) {
        if (keepAspectRatio) {
            final int newHeight = (int) (w / imageAspectRatio);
            super.setSize(w, newHeight);
        } else {
            super.setSize(w, h);
        }
    }

    @Override
    public void setMaximumSize(final Dimension d) {
        super.setMaximumSize(this.getSize());
    }

    @Override
    public void setMinimumSize(final Dimension d) {
        super.setMinimumSize(this.getSize());
    }

    @Override
    public void setPreferredSize(final Dimension size) {
        super.setPreferredSize(this.getSize());
    }

    /**
     * Get an {@link Image} instance. The image will be stored in a cache.
     *
     * @param path
     *            the path is a "/project/file" path, if it's not found in the workspace, the class will look for the
     *            file in the plug-ins.
     * @return an image instance given the path.
     */
    public static Image flyWeightImage(final String path) {
        if (path != null) {
            final File imageFile = WorkspaceFileResourceChangeListener.getInstance().getFileFromURI(path);
            ImageDescriptor desc = null;
            if (imageFile != null && WorkspaceFileResourceChangeListener.getInstance().getReadStatusOfFile(imageFile)) {
                try {
                    desc = WorkspaceFileResourceChangeListener.getInstance().findImageDescriptor(imageFile);
                } catch (MalformedURLException e) {
                    // do nothing
                }
            }
            return WorkspaceImageFigure.flyWeightImage(desc);
        }
        return WorkspaceImageFigure.getImageNotFound();
    }

    /**
     * Get an {@link Image} instance. The image will be stored in a cache.
     *
     * @param desc
     *            the image descriptor
     * @return an image instance given the image descriptor.
     */
    public static Image flyWeightImage(final ImageDescriptor desc) {
        if (desc != null) {
            return DiagramUIPlugin.getPlugin().getImage(desc);
        } else {
            return WorkspaceImageFigure.getImageNotFound();
        }
    }

    private static Image getImageNotFound() {
        return DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.Implementation.findImageWithDimensionDescriptor(DiagramImagesPath.IMAGE_NOT_FOUND));
    }

    /**
     * Create an {@link WorkspaceImageFigure} instance from an image path.
     *
     * @param path
     *            the path is a "/project/file" path, if it's not found in the workspace, the class will look for the
     *            file in the plug-ins.
     * @return an image instance given the path.
     */
    public static WorkspaceImageFigure createImageFigure(final String path) {
        final WorkspaceImageFigure fig = new WorkspaceImageFigure(WorkspaceImageFigure.flyWeightImage(path));
        return fig;
    }

    /**
     * Create an {@link WorkspaceImageFigure} instance from a workspace image.
     *
     * @param wksImage
     *            : an instance of {@link WorkspaceImage}.
     * @return the image figure built using the {@link WorkspaceImage} object.
     */
    public static WorkspaceImageFigure createImageFigure(final WorkspaceImage wksImage) {
        final String path = wksImage.getWorkspacePath();
        // ici on pourrait transformer le path image:/./folder/image.png en image workspace mais quid des path
        // remoteimage:/./folder
        // non en fait les paths CDO seront persistes en cdo:/project/folder et a l'import on met les images dnas le
        // project cible si le nom du projet cdo correspond au premier segment du path image cdo
        return WorkspaceImageFigure.createImageFigure(path);

    }

    /**
     * Get the image aspect ratio.
     *
     * @return the image aspect ratio
     */
    @Override
    public double getImageAspectRatio() {
        return imageAspectRatio;
    }

    /**
     * Refresh the figure.
     *
     * @param bundledImage
     *            the image associated to the figure
     */
    @Override
    public void refreshFigure(final WorkspaceImage bundledImage) {
        final String path = bundledImage.getWorkspacePath();
        final Image image = WorkspaceImageFigure.flyWeightImage(path);
        if (!image.equals(this.getImage())) {
            this.setImage(WorkspaceImageFigure.flyWeightImage(path));
            this.repaint();
        }
    }

    /**
     * Refresh the figure.
     *
     * @param containerStyle
     *            the style of the container
     */
    @Override
    public void refreshFigure(final ContainerStyle containerStyle) {
        if (containerStyle instanceof WorkspaceImage) {
            WorkspaceImage workspaceImage = (WorkspaceImage) containerStyle;
            refreshFigure(workspaceImage);
        }
    }

    /**
     * Extract image from path.
     * 
     * @param path
     *            the path of the image
     * @return the image
     */
    public static Image getImageInstanceFromPath(final String path) {
        final Image image;
        if (path != null && isSvgImage(path)) {
            image = SVGWorkspaceImageFigure.flyWeightImage(path);
        } else {
            image = WorkspaceImageFigure.flyWeightImage(path);
        }
        return image;
    }

    /**
     * Check svg format from the path of an image.
     * 
     * @param path
     *            the path of the image to check
     * @return true for svg or svgz image format.
     */
    public static boolean isSvgImage(String path) {
        if (path == null) {
            return false;
        } else {
            String pathToUpperCase = path.toUpperCase();
            return pathToUpperCase.endsWith(ImageFileFormat.SVG.getName()) || pathToUpperCase.endsWith(ImageFileFormat.SVGZ.getName());
        }
    }
}
