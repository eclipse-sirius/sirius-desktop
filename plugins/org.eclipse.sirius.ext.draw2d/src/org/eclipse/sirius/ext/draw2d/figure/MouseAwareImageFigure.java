/*******************************************************************************
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * A figure that shows an image that change on mouseExited and mouseEntered
 * events. Note that it is the client's responsibility to dispose the given
 * images. The image is resized to fit the client area size.
 * 
 * 
 * @author ymortier
 */
public class MouseAwareImageFigure extends ImageFigure {

    /** Left Top corner. */
    private static final Point LEFT_TOP_CORNER = new Point(0, 0);

    /** the image that is shown when the mouse is not on the figure. */
    protected Image imageWOFocus;

    /** the image that is shown when the mouse is on the figure. */
    protected Image imageWFocus;

    /** the size. */
    private Dimension size;

    /**
     * Create a new {@link MouseAwareImageFigure}.
     */
    public MouseAwareImageFigure() {
        super();
        init();
    }

    /**
     * Create a {@link MouseAwareImageFigure} with the specified image that is
     * shwon when the mouse is not on the figure.
     * 
     * @param imageWOFocus
     *            the image that is shown when the mouse is not on the image.
     */
    public MouseAwareImageFigure(final Image imageWOFocus) {
        super(imageWOFocus);
        this.imageWOFocus = imageWOFocus;
        init();
    }

    /**
     * Create a {@link MouseAwareImageFigure} with the specified images.
     * 
     * @param imageWOFocus
     *            the image that is shown when the mouse is not on the image.
     * @param imageWFocus
     *            the image that is shown when the mouse is on the image.
     */
    public MouseAwareImageFigure(final Image imageWOFocus, final Image imageWFocus) {
        super(imageWOFocus);
        this.imageWOFocus = imageWOFocus;
        this.imageWFocus = imageWFocus;
        init();
    }

    /**
     * Initialize the figure.
     */
    private void init() {
        this.size = new Dimension();
        this.addMouseMotionListener(new ToggleImage());
    }

    /**
     * Define the image that is shown when the mouse is on the figure.
     * 
     * @param imageWFocus
     *            the image that is shown when the mouse is on the figure.
     */
    public void setImageWFocus(final Image imageWFocus) {
        this.imageWFocus = imageWFocus;
    }

    /**
     * Return the image that is shown when the mouse is on the figure.
     * 
     * @return the image that is shown when the mouse is on the figure.
     */
    public Image getImageWFocus() {
        return imageWFocus;
    }

    /**
     * Define the image that is shown when the mouse is not on the figure.
     * 
     * @param imageWOFocus
     *            the image that is shown when the mouse is not on the figure.
     */
    public void setImageWOFocus(final Image imageWOFocus) {
        this.setImage(imageWOFocus);
        this.imageWOFocus = imageWOFocus;
    }

    /**
     * Return the image that is shown when the mouse is not on the figure.
     * 
     * @return the image that is shown when the mouse is not on the figure.
     */
    public Image getImageWOFocus() {
        return imageWOFocus;
    }

    /**
     * This class toggle the image that is shown when the mouse entered or
     * exited the figure.
     * 
     * @author ymortier
     */
    private final class ToggleImage implements MouseMotionListener {

        /**
         * @see MouseMotionListener#mouseDragged(MouseEvent)
         */
        public void mouseDragged(final MouseEvent me) {
            // do nothing.
        }

        /**
         * @see MouseMotionListener#mouseEntered(MouseEvent)
         */
        public void mouseEntered(final MouseEvent me) {
            if (imageWFocus != null) {
                MouseAwareImageFigure.this.setImage(imageWFocus);
            }
        }

        /**
         * @see MouseMotionListener#mouseExited(MouseEvent)
         */
        public void mouseExited(final MouseEvent me) {
            MouseAwareImageFigure.this.setImage(imageWOFocus);
        }

        /**
         * @see MouseMotionListener#mouseHover(MouseEvent)
         */
        public void mouseHover(final MouseEvent me) {
            // do nothing.
        }

        /**
         * @see MouseMotionListener#mouseMoved(MouseEvent)
         */
        public void mouseMoved(final MouseEvent me) {
            // do nothing.
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.ImageFigure#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void paintFigure(final Graphics graphics) {
        // super.paintFigure(graphics);
        graphics.drawImage(this.getImage(), new Rectangle(LEFT_TOP_CORNER, super.getPreferredSize(0, 0)), new Rectangle(this.getLocation(), this.getSize()));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#setSize(int, int)
     */
    @Override
    public void setSize(final int w, final int h) {
        this.size.setWidth(w);
        this.size.setHeight(h);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#getBounds()
     */
    @Override
    public Rectangle getBounds() {
        final Rectangle realBounds = new Rectangle(super.getBounds());
        realBounds.setSize(this.size);
        return realBounds;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.ImageFigure#getPreferredSize(int, int)
     */
    @Override
    public Dimension getPreferredSize(final int hint, final int hint2) {
        return this.size;
    }

}
