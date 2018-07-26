/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.IFigure;

/**
 * 
 * A fixed figure is a figure that has always the same position in a Sirius Diagram like {@link SynchronizeStatusFigure}
 * and {@link DiagramSemanticElementLockedNotificationFigure}. This interface provides methods to update those figures.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public interface IFixedFigure extends IFigure {
    /**
     * Update this figure location regarding its graphic context.
     */
    void updateLocation();
}
