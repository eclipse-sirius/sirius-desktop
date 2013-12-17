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

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gmf.runtime.diagram.ui.figures.NoteFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.TransparentFigureGraphicsModifier;

/**
 * Specific Figure to handle documentation notes.
 * 
 * @author cbrun
 * 
 */
public class AirNoteFigure extends NoteFigure implements StyledFigure, ITransparentFigure {

    private int viewpointAlpha = DEFAULT_ALPHA;

    private boolean transparent;

    /**
     * Create a new note with specicic values.
     * 
     * @param width
     *            <code>int</code> value that is the default width in logical
     *            units
     * @param height
     *            <code>int</code> value that is the default height in logical
     *            units
     * @param insets
     *            <code>Insets</code> that is the empty margin inside the note
     *            figure in logical units
     */
    public AirNoteFigure(final int width, final int height, final Insets insets) {
        super(width, height, insets);
    }

    /**
     * Create a new note figure with default values.
     */
    public AirNoteFigure() {
        super(100, 50, new Insets(5, 5, 5, 5));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintFigure(Graphics g) {
        TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, g);
        modifier.pushState();
        super.paintFigure(g);
        modifier.popState();

    }

    /**
     * {@inheritDoc}
     */
    public int getSiriusAlpha() {
        return viewpointAlpha;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * {@inheritDoc}
     */
    public void setSiriusAlpha(int alpha) {
        this.viewpointAlpha = alpha;

    }

    /**
     * {@inheritDoc}
     */
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

}
