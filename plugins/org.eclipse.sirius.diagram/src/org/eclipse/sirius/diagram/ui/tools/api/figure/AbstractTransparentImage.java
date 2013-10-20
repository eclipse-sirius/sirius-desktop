/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

import org.eclipse.sirius.diagram.ui.tools.internal.figure.TransparentFigureGraphicsModifier;

/**
 * An abstract image figure to handle transparency.
 * 
 * @author mporhel
 */
public abstract class AbstractTransparentImage extends ImageFigure implements StyledFigure, ITransparentFigure {

    private int viewpointAlpha = DEFAULT_ALPHA;

    private boolean transparent;

    /**
     * Create a new {@link AbstractTransparentImage}.
     * 
     * @param flyWeightImage
     *            an image instance.
     */
    public AbstractTransparentImage(final Image flyWeightImage) {
        super(flyWeightImage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintFigure(Graphics graphics) {
        if (getImage() != null) {
            TransparentFigureGraphicsModifier modifier = new TransparentFigureGraphicsModifier(this, graphics);
            modifier.pushState();
            graphics.drawImage(getImage(), new Rectangle(getImage().getBounds()), getBounds());
            modifier.popState();
        }
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
