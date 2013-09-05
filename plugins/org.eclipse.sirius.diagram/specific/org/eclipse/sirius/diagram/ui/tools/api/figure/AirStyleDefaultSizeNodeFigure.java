/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;

/**
 * The sefault size node for styles.
 * 
 * @author ymortier
 */
public class AirStyleDefaultSizeNodeFigure extends DefaultSizeNodeFigure {

    /**
     * Constructor.
     * 
     * @param defSize
     *            the default size.
     */
    public AirStyleDefaultSizeNodeFigure(final Dimension defSize) {
        super(defSize);
    }

    /**
     * Constructor.
     * 
     * @param width
     *            the initial width to initialize the default size with
     * @param height
     *            the initial height to initialize the default size with
     */
    public AirStyleDefaultSizeNodeFigure(final int width, final int height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setBounds(org.eclipse.draw2d.geometry.Rectangle)
     */
    @Override
    public void setBounds(final Rectangle rect) {
        if (getParent() != null) {
            super.setBounds(getParent().getBounds());
        } else {
            super.setBounds(rect);
        }
    }
}
