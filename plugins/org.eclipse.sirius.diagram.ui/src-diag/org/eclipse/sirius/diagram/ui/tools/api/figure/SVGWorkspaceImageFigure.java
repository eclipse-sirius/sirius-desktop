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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.svg.SimpleImageTranscoder;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.swt.graphics.Image;

/**
 * The {@link SVGWorkspaceImageFigure} is useful to load svg images using a
 * cache. The image can be in the workspace, or if it's not found in the
 * workspace it will be looked up in the plug-ins.
 *
 * @author mporhel
 *
 */
public class SVGWorkspaceImageFigure extends SVGFigure implements IWorkspaceImageFigure {

    private double imageAspectRatio = 1.0;

    private boolean keepAspectRatio = true;

    /**
     * Create a new {@link SVGWorkspaceImageFigure}.
     */
    public SVGWorkspaceImageFigure() {
        minSize = new Dimension(0, 0);
    }

    /**
     * Create the {@link SVGWorkspaceImageFigure} from a {@link WorkspaceImage}
     * instance.
     *
     * @param image
     *            {@link SVGWorkspaceImageFigure} specification.
     * @return new Figure.
     */
    public static SVGWorkspaceImageFigure createImageFigure(final WorkspaceImage image) {
        SVGWorkspaceImageFigure fig = new SVGWorkspaceImageFigure();
        fig.refreshFigure(image);
        return fig;
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
     * Get the image aspect ratio.
     *
     * @return the image aspect ratio
     */
    @Override
    public double getImageAspectRatio() {
        return imageAspectRatio;
    }

    /**
     * Refreshes the figure.
     *
     * @param containerStyle
     *            the style of the container
     */
    @Override
    public void refreshFigure(final ContainerStyle containerStyle) {
        if (containerStyle instanceof FlatContainerStyle) {
            final FlatContainerStyle style = (FlatContainerStyle) containerStyle;
            boolean updated = this.updateImageURI(style.getBackgroundStyle().getName());
            if (updated) {
                this.contentChanged();
            }
        } else if (containerStyle instanceof WorkspaceImage) {
            refreshFigure((WorkspaceImage) containerStyle);
        } else {
            this.setURI(null);
        }
    }

    /**
     * refresh the figure.
     *
     * @param workspaceImage
     *            the image associated to the figure
     */
    @Override
    public void refreshFigure(final WorkspaceImage workspaceImage) {
        if (workspaceImage != null) {
            boolean updated = this.updateImageURI(workspaceImage.getWorkspacePath());
            if (updated) {
                this.contentChanged();
                SimpleImageTranscoder transcoder = getTranscoder();
                if (transcoder != null) {
                    imageAspectRatio = transcoder.getAspectRatio();
                }
            }
        } else {
            this.setURI(null);
        }
    }

    private boolean updateImageURI(String workspacePath) {
        if (workspacePath != null) {
            Option<String> existingImageUri = SVGWorkspaceImageFigure.getImageUri(workspacePath, false);
            if (existingImageUri.some()) {
                setURI(existingImageUri.get());
            } else {
                setURI(SVGFigure.IMAGE_NOT_FOUND_URI);
            }
            return true;
        }
        return false;
    }

    /**
     * Return an optional uri as used in the document key to read svg files.
     *
     * @param workspacePath
     *            the workspace path of the file.
     * @param force
     *            true to avoid to check that the file exists and is readble.
     * @return an optional system uri.
     */
    private static Option<String> getImageUri(String workspacePath, boolean force) {
        final File imageFile = FileProvider.getDefault().getFile(new Path(workspacePath));
        if (imageFile != null && (force || imageFile.exists() && imageFile.canRead())) {
            return Options.newSome(imageFile.toURI().toString());
        }
        Option<String> nonExistingFile = Options.newNone();
        if (force) {
            // Deleted file : retrieve the key.
            nonExistingFile = Options.newSome(ResourcesPlugin.getWorkspace().getRoot().getLocationURI().toString() + workspacePath);
        }

        return nonExistingFile;
    }

    /**
     * Get an {@link Image} instance. The image will be stored in a cache.
     *
     * @param path
     *            the path is a "/project/file" path, if it's not found in the
     *            workspace, the class will look for the file in the plug-ins.
     * @return an image instance given the path.
     */
    public static Image flyWeightImage(String path) {
        SVGWorkspaceImageFigure fig = new SVGWorkspaceImageFigure();
        fig.updateImageURI(path);
        fig.contentChanged();
        return fig.getImage(new PrecisionRectangle(0, 0, -1, -1), null);
    }

    /**
     * Remove all entries whose key begins with the given key. Remove from the
     * document map, the entries with the given keys to force to re-read the
     * file.
     *
     * @param workspacePath
     *            the modified or deleted image file path.
     * @return an option with the document uri used as key for the svg file if a
     *         corresponding element was removed.
     */
    public static Option<String> removeFromCache(String workspacePath) {
        Option<String> imageUri = SVGWorkspaceImageFigure.getImageUri(workspacePath, true);
        if (imageUri.some()) {
            if (SVGFigure.doRemoveFromCache(imageUri.get())) {
                return imageUri;
            }
        }
        return Options.newNone();
    }
}
