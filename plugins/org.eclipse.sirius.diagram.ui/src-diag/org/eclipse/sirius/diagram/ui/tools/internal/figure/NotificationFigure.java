/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.TransparentBorder;

/**
 * A figure to display a notification.
 * 
 * @author mchauvin
 * @provisional
 */
public class NotificationFigure extends RectangleFigure {

    private static final int WARNING_COLOR_VALUE = 13369343;

    private static final int WIDTH = 400;

    private static final int HEIGHT = 30;

    private static final int TEXT_OFFSET = 5;

    /**
     * The transparency of this shape in percent. Must be in [0, 100] range.
     */
    private int transparency = 20;

    private PropertyChangeListener propListener;

    private Viewport viewport;

    private DiagramRootEditPart rootEditPart;

    /**
     * Create a new instance.
     * 
     * @param rootEditPart
     *            the editor root edit part
     * @param message
     *            the message to display in the notification
     */
    public NotificationFigure(DiagramRootEditPart rootEditPart, String message) {

        this.rootEditPart = rootEditPart;
        this.viewport = (Viewport) rootEditPart.getFigure();

        this.setSize(WIDTH, HEIGHT);
        this.setBackgroundColor(DiagramColorRegistry.getInstance().getColor(Integer.valueOf(WARNING_COLOR_VALUE)));

        updateLocation();

        Cross cross = new Cross();
        int crossSize = cross.getSize().width;
        cross.setLocation(new Point(WIDTH - crossSize - 3, 3));
        this.add(cross);
        cross.initialize();

        Label label = new Label(message);
        label.setSize(WIDTH - TEXT_OFFSET - crossSize, HEIGHT - TEXT_OFFSET);
        this.add(label);

        propListener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                updateLocation();
            }
        };
    }

    private void updateLocation() {
        Point viewLocation = viewport.getViewLocation().getCopy();
        Dimension viewSize = viewport.getSize().getCopy();
        /* we divide by 2 to center the notification figure */

        // Workaround, as the size of the figure follows zoom, we apply twice
        // the zoom on figure offset to balance
        double notationFigureOffset = ((viewSize.width - WIDTH) / 2d) / (rootEditPart.getZoomManager().getZoom() * rootEditPart.getZoomManager().getZoom());

        viewLocation.performScale(1.0d / rootEditPart.getZoomManager().getZoom());

        this.setLocation(new PrecisionPoint(viewLocation.x + notationFigureOffset, viewLocation.y));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNotify() {
        super.addNotify();
        viewport.addPropertyChangeListener(Viewport.PROPERTY_VIEW_LOCATION, propListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeNotify() {
        super.removeNotify();
        viewport.removePropertyChangeListener(Viewport.PROPERTY_VIEW_LOCATION, propListener);
        viewport = null;
    }

    /**
     * Override to use local coordinates. .{@inheritDoc}
     */
    protected boolean useLocalCoordinates() {
        return true;
    }

    /**
     * Create a new notification figure and display it to the diagram.
     * 
     * @param rootEditPart
     *            the diagram root edit part
     * @param message
     *            the message
     */
    public static void createNotification(DiagramRootEditPart rootEditPart, String message) {
        final LayeredPane pane = (LayeredPane) rootEditPart.getLayer(LayerConstants.PRINTABLE_LAYERS);

        final IFigure nf = new NotificationFigure(rootEditPart, message);
        pane.add(nf);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
     */
    public void paintFigure(Graphics g) {
        applyTransparency(g);
        super.paintFigure(g);
        g.setAlpha(255);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.handles.HandleBounds#getHandleBounds()
     */
    public Rectangle getHandleBounds() {
        Insets insets = new Insets(0, 0, 0, 0);
        if (getBorder() instanceof TransparentBorder) {
            insets = ((TransparentBorder) getBorder()).getTransparentInsets(this);
        }

        // Ignore the insets when placing the handles
        return new Rectangle(getBounds().x + insets.left, getBounds().y + insets.top, getBounds().width - (insets.right + insets.left), getBounds().height - (insets.bottom + insets.top));
    }

    /**
     * Returns transparency value (belongs to [0, 100] interval).
     * 
     * @return transparency
     * @since 0.9.0
     */
    public int getTransparency() {
        return transparency;
    }

    /**
     * Sets the transparency if the given parameter is in [0, 100] range.
     * 
     * @param transparency
     *            The transparency to set
     * @since 0.9.0
     */
    public void setTransparency(int transparency) {
        if (transparency != this.transparency && transparency >= 0 && transparency <= 100) {
            this.transparency = transparency;
            repaint();
        }
    }

    /**
     * Converts transparency value from percent range [0, 100] to alpha range
     * [0, 255] and applies converted value. 0% corresponds to alpha 255 and
     * 100% corresponds to alpha 0.
     * 
     * @param g
     *            The Graphics used to paint
     * @since 0.9.0
     */
    protected void applyTransparency(Graphics g) {
        g.setAlpha(255 - transparency * 255 / 100);
    }

    /**
     * A cross figure to close the notation figure.
     * 
     * @author mchauvin
     */
    private static class Cross extends Shape {

        Cross() {
            this.setSize(10, 10);
        }

        /**
         * Override to use local coordinates. .{@inheritDoc}
         */
        protected boolean useLocalCoordinates() {
            return true;
        }

        @Override
        protected void fillShape(Graphics graphics) {
            graphics.pushState();
            int x = getBounds().x;
            int y = getBounds().y;
            int[] shape = new int[] { x, y, x + 2, y, x + 4, y + 2, x + 5, y + 2, x + 7, y, x + 9, y, x + 9, y + 2, x + 7, y + 4, x + 7, y + 5, x + 9, y + 7, x + 9, y + 9, x + 7, y + 9, x + 5, y + 7,
                    x + 4, y + 7, x + 2, y + 9, x, y + 9, x, y + 7, x + 2, y + 5, x + 2, y + 4, x, y + 2, };
            graphics.setBackgroundColor(ColorConstants.red);
            graphics.fillPolygon(shape);
            graphics.setForegroundColor(ColorConstants.black);
            graphics.drawPolygon(shape);
            graphics.popState();
        }

        @Override
        protected void outlineShape(Graphics arg0) {

        }

        /**
         * Initialize this figure. It should be done <b>after</b> adding this
         * figure to its notation parent figure.
         */
        public void initialize() {
            this.addMouseListener(new MouseListener.Stub() {
                @Override
                public void mousePressed(MouseEvent me) {
                    getParent().getParent().remove(getParent());
                }
            });
        }

    }

}
