/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.io.File;
import java.net.MalformedURLException;

import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.internal.image.ImagesPath;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.WorkspaceImage;

/**
 * The {@link WorkspaceImageFigure} is useful to load images using a cache. The
 * image can be in the workspace, or if it's not found in the workspace it will
 * be looked up in the plug-ins.
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    @Override
    public void setSize(final int w, final int h) {
        if (keepAspectRatio) {
            final int newHeight = (int) (w / imageAspectRatio);
            super.setSize(w, newHeight);
        } else {
            super.setSize(w, h);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setMaximumSize(org.eclipse.draw2d.geometry.Dimension)
     */
    @Override
    public void setMaximumSize(final Dimension d) {
        super.setMaximumSize(this.getSize());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setMinimumSize(org.eclipse.draw2d.geometry.Dimension)
     */
    @Override
    public void setMinimumSize(final Dimension d) {
        super.setMinimumSize(this.getSize());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setPreferredSize(org.eclipse.draw2d.geometry.Dimension)
     */
    @Override
    public void setPreferredSize(final Dimension size) {
        super.setPreferredSize(this.getSize());
    }

    /**
     * Get an {@link Image} instance. The image will be stored in a cache.
     * 
     * @param path
     *            the path is a "/project/file" path, if it's not found in the
     *            workspace, the class will look for the file in the plug-ins.
     * @return an image instance given the path.
     * @deprecated
     */
    @Deprecated
    public static Image flyWeightImage(final String path) {
        if (path != null) {
            final File imageFile = FileProvider.getDefault().getFile(new Path(path));
            ImageDescriptor desc = null;
            if (imageFile != null && imageFile.exists() && imageFile.canRead()) {
                try {
                    desc = SiriusDiagramEditorPlugin.findImageDescriptor(imageFile.toURI().toURL());
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
     * @deprecated
     */
    @Deprecated
    public static Image flyWeightImage(final ImageDescriptor desc) {
        if (desc != null) {
            return SiriusDiagramEditorPlugin.getInstance().getImage(desc);
        } else {
            return WorkspaceImageFigure.getImageNotFound();
        }
    }

    private static Image getImageNotFound() {
        return SiriusDiagramEditorPlugin.getInstance().getImage(SiriusDiagramEditorPlugin.findImageWithDimensionDescriptor(ImagesPath.IMAGE_NOT_FOUND));
    }

    /**
     * Create an {@link WorkspaceImageFigure} instance from an image path.
     * 
     * @param path
     *            the path is a "/project/file" path, if it's not found in the
     *            workspace, the class will look for the file in the plug-ins.
     * @return an image instance given the path.
     */
    @Deprecated
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
    @Deprecated
    public static WorkspaceImageFigure createImageFigure(final WorkspaceImage wksImage) {
        final String path = wksImage.getWorkspacePath();
        return WorkspaceImageFigure.createImageFigure(path);

    }

    /**
     * Get the image aspect ratio.
     * 
     * @return the image aspect ratio
     */
    public double getImageAspectRatio() {
        return imageAspectRatio;
    }

    /**
     * Refresh the figure.
     * 
     * @param bundledImage
     *            the image associated to the figure
     */
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
    public void refreshFigure(final ContainerStyle containerStyle) {
        if (containerStyle instanceof WorkspaceImage) {
            WorkspaceImage workspaceImage = (WorkspaceImage) containerStyle;
            refreshFigure(workspaceImage);
        }
    }
}
