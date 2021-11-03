/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.business.internal.query;

import java.util.Objects;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.ui.tools.api.figure.WorkspaceImageFigure;
import org.eclipse.swt.graphics.Image;

/**
 * Queries relative to WorkspaceImage.
 * 
 * @author jdupont
 */
public class WorkspaceImageQuery {

    private final String workspaceImagePath;

    /**
     * Constructor with a {@link WorkspaceImageDescription}.
     * 
     * @param workspaceImage
     *            the workspaceImageDescription to query.
     */
    public WorkspaceImageQuery(WorkspaceImageDescription workspaceImageDescription) {
        Objects.requireNonNull(workspaceImageDescription);
        workspaceImagePath = workspaceImageDescription.getWorkspacePath();
    }

    /**
     * Constructor with a {@link WorkspaceImage}.
     * 
     * @param workspaceImage
     *            the workspaceImage to query.
     */
    public WorkspaceImageQuery(WorkspaceImage workspaceImage) {
        Objects.requireNonNull(workspaceImage);
        workspaceImagePath = workspaceImage.getWorkspacePath();
    }


    /**
     * Return true if the path really corresponds to an existing image, false otherwise. If false, all other methods of
     * this query returns empty optional.
     * 
     * @return true if the path really corresponds to an existing image, false otherwise.
     */
    public boolean doesImageExist() {
        return getImage() != null;
    }

    /**
     * Return the dimension of the specified image if it exists, return a dimension of zero width and height otherwise
     * {@link #doesImageExist()} should be checked before calling this method.
     * 
     * @return the dimension of the specified image if it exists.
     */
    public Dimension getDefaultDimension() {
        Dimension result = new Dimension();
        if (doesImageExist()) {
            Image image = getImage();
            // Use default image size
            result.setWidth(image.getBounds().width);
            result.setHeight(image.getBounds().height);
        }

        return result;

    }

    /**
     * Return the ratio (width/height) of the specified image if it exists, 1 otherwise. {@link #doesImageExist()}
     * should be checked before calling this method.
     * 
     * @return the ratio (width/height) of the specified image if it exists, 1 otherwise.
     */
    public double getRatio() {
        double result = 1;
        if (doesImageExist()) {
            Image image = getImage();
            result = (double) image.getBounds().width / image.getBounds().height;
        }
        return result;
    }

    /**
     * Return the image corresponding to the path (it can be null if the image does not exist).
     * 
     * @return the image corresponding to the path
     */
    protected Image getImage() {
        return WorkspaceImageFigure.getImageInstanceFromPath(workspaceImagePath);
    }
}
