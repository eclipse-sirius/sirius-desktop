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
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;

/**
 * A compartment figure not drawing the borders..
 * 
 * @author cbrun
 * 
 */
public class InvisibleResizableCompartmentFigure extends ShapeCompartmentFigure {

    /**
     * Create a new compartment figure without borders.
     * 
     * @param title
     *            compartment title.
     * @param mode
     *            mapping mode.
     */
    public InvisibleResizableCompartmentFigure(final String title, final IMapMode mode) {
        super(title, mode);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#paintBorder(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintBorder(final Graphics graphics) {
        /*
         * we don't want to draw the borders.
         */
    }

}
