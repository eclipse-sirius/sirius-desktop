/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;

/**
 * This figure overrides {@link BorderedNodeFigure} in order to force layout
 * because of an issue where resizing a node will not relocate its bordered
 * nodes.
 * 
 * @author smonnier
 * 
 */
public class DBorderedNodeFigure extends BorderedNodeFigure {

    /**
     * Creates a new DBorderedNodeFigure figure.
     * 
     * @param mainFigure
     *            the figure to use with this figure
     */
    public DBorderedNodeFigure(IFigure mainFigure) {
        super(mainFigure);
    }

    /**
     * This method has been overridden to avoid comparing bounds of this and
     * getMainFigure(). {@inheritDoc}
     */
    @Override
    protected void layout() {
        getMainFigure().setBounds(this.getBounds().getCopy());
        getBorderItemContainer().invalidateTree();
        erase();
    }
}
