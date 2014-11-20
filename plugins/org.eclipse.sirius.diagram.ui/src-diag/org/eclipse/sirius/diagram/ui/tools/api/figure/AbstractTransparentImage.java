/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.ui.tools.internal.figure.TransparentFigureGraphicsModifier;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

/**
 * An abstract image figure to handle transparency.
 * 
 * @author mporhel
 */
public abstract class AbstractTransparentImage extends ImageFigure implements StyledFigure, ITransparentFigure, ImageFigureWithAlpha {

    private int viewpointAlpha = DEFAULT_ALPHA;

    private boolean transparent;

    /**
     * We keep a reference to the imageData and keep it in sync with the image
     * with is currently displayed as retrieving it through image.getImageData()
     * will actually build a new buffer based on the image and hence is very
     * costly.
     */
    private ImageData imageData;

    /**
     * Create a new {@link AbstractTransparentImage}.
     * 
     * @param flyWeightImage
     *            an image instance.
     */
    public AbstractTransparentImage(final Image flyWeightImage) {
        super(flyWeightImage);
        this.imageData = flyWeightImage.getImageData();
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

    @Override
    public int getImageHeight() {
        return this.imageData.height;
    }

    @Override
    public int getImageWidth() {
        return this.imageData.width;
    }

    @Override
    public int getImageAlphaValue(int x, int y) {
        if (x < this.imageData.width && y < this.imageData.height) {
            return this.imageData.getAlpha(x, y) == 0 ? 0 : 255;
        }
        return 255;

    }

    @Override
    public void setImage(Image image) {
        if (getImage() == image) {
            return;
        }
        super.setImage(image);
        this.imageData = image.getImageData();
    }
}
