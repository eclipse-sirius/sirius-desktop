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
package org.eclipse.sirius.diagram.ui.edit.api.part;

import org.eclipse.draw2d.IFigure;

/**
 * Top level edit part for nodes that on the border of another node.
 * 
 * @author ymortier
 */
public interface IDiagramBorderNodeEditPart extends IAbstractDiagramNodeEditPart {

    /**
     * Returns the primary figure.
     * 
     * @return the primary figure.
     */
    IFigure getPrimaryFigure();

    /**
     * Refreshes the figure.
     */
    void refreshFigure();

}
