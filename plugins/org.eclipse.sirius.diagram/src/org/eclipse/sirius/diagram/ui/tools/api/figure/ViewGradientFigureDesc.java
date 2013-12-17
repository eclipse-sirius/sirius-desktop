/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.swt.graphics.Color;

/**
 * Common interface which add support of gradient for container and list figure
 * descriptors.
 * 
 * @author mporhel
 */
public interface ViewGradientFigureDesc extends IFigure {
    /**
     * Get the gradient color.
     * 
     * @return the gradient color.
     */
    Color getGradientColor();

    /**
     * Get the background style.
     * 
     * @return the background style.
     */
    BackgroundStyle getBackgroundStyle();

}
