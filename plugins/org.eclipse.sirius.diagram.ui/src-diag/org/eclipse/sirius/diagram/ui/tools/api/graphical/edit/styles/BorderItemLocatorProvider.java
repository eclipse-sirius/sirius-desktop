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
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.sirius.diagram.DDiagramElement;

/**
 * Provides Item Locator.
 * 
 * @author ymortier
 */
public interface BorderItemLocatorProvider {

    /**
     * Return the border item locator to use for the specified figure.
     * 
     * @param figure
     *            the figure.
     * @param diagramElementOwner
     *            the view point element that owns the border item.
     * @param diagramElementBorderItem
     *            the view point element on border.
     * @return the border item locator to use for the specified figure.
     */
    IBorderItemLocator getBorderItemLocator(IFigure figure, DDiagramElement diagramElementOwner, DDiagramElement diagramElementBorderItem);

}
