/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.Color;

/**
 * Common interface which add support of gradient for container and list figure descriptors.
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
     * Get the background style. Supported styles are:
     * <ul>
     * <li>0: left-to-right</li>
     * <li>1: liquid</li>
     * <li>2: top-to-bottom</li>
     * </ul>
     * 
     * @return the background style.
     */
    int getBackgroundStyle();

}
