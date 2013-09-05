/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.query;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Preconditions;

import org.eclipse.sirius.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SVGWorkspaceImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.sirius.ui.tools.api.dialogs.ImageFileFormat;

/**
 * Queries relative to WorkspaceImage.
 * 
 * @author jdupont
 */
public class WorkspaceImageQuery {

    private static final Dimension DEFAULT_WORKSPACE_DIMENSION = new Dimension(2, 2);

    private final WorkspaceImageDescription workspaceImage;

    /**
     * Constructor.
     * 
     * @param workspaceImage
     *            the node to query.
     */
    public WorkspaceImageQuery(WorkspaceImageDescription workspaceImage) {
        this.workspaceImage = Preconditions.checkNotNull(workspaceImage);
    }

    /**
     * Return the default draw2D dimension according to the specified image.
     * 
     * @return the default draw2D dimension according to the specified image.
     */
    public Dimension getDefaultDimension() {
        final Dimension result = DEFAULT_WORKSPACE_DIMENSION.getCopy();
        final String path = workspaceImage.getWorkspacePath();
        final Image image;
        if (path != null && path.toUpperCase().endsWith(ImageFileFormat.SVG.getName())) {
            image = SVGWorkspaceImageFigure.flyWeightImage(path);
        } else {
            image = WorkspaceImageFigure.flyWeightImage(path);
        }

        if (image != null) {
            // Use default image size
            result.width = image.getBounds().width;
            result.height = image.getBounds().height;
        }

        return result;
    }

}
