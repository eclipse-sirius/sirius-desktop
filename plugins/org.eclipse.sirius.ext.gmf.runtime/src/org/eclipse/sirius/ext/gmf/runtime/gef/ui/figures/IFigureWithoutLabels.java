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
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Represents the shape of a {@link Figure} but without the labels that are outside the main shape of the figure.<br/>
 * Example: labels of an Edge.
 * 
 * @author lfasani
 *
 */
public interface IFigureWithoutLabels {

    /**
     * Get the bounding box ignoring the labels that are outside the main shape of the figure.
     * 
     * @return the bounds
     */
    Rectangle getBoundsWithoutLabels();
}
