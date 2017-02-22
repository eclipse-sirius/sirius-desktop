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

import org.eclipse.draw2d.IFigure;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;

/**
 * Interface for worskpace image figures.
 * 
 * @author mporhel
 */
public interface IWorkspaceImageFigure extends IFigure {

    /**
     * Refreshes the figure.
     * 
     * @param containerStyle
     *            the style of the container
     */
    void refreshFigure(ContainerStyle containerStyle);

    /**
     * refresh the figure.
     * 
     * @param workspaceImage
     *            the image associated to the figure
     */
    void refreshFigure(WorkspaceImage workspaceImage);

    /**
     * Get the image aspect ratio.
     * 
     * @return the image aspect ratio
     */
    double getImageAspectRatio();
}
