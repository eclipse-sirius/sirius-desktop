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
import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.TransparentBorder;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A figure to display a lock notification for the semantic element represented
 * by a diagram.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class DiagramSemanticElementLockedNotificationFigure extends Ellipse {

    /**
     * Border color of the notification figure when locked by me.
     */
    public static final RGB BORDER_COLOR_LOCKED_BY_ME = new RGB(50, 205, 50);

    /**
     * Border color of the notification figure when locked by other.
     */
    public static final RGB BORDER_COLOR_LOCKED_BY_OTHER = new RGB(255, 0, 0);

    private static final int DEFAULT_WIDTH = 25;

    private static final int DEFAULT_HEIGHT = 25;

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_ME_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation
            .getBundledImageDescriptor("icons/full/decorator/permission_granted_to_current_user_exclusively.gif"); //$NON-NLS-1$

    /** The PERMISSION_GRANTED_TO_CURRENT_USER_EXCLUSIVELY icon descriptor. */
    private static final ImageDescriptor LOCK_BY_OTHER_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/permission_denied.gif"); //$NON-NLS-1$

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
     * @param lockStatus
     *            the {@link LockStatus} to display in the notification
     * @param height
     *            notification figure height
     * @param width
     *            notification figure width
     */
    public DiagramSemanticElementLockedNotificationFigure(DiagramRootEditPart rootEditPart, String message, LockStatus lockStatus, int height, int width) {
        Image lockStatusImage;
        Label label;

        this.rootEditPart = rootEditPart;
        this.viewport = (Viewport) rootEditPart.getFigure();
        this.setSize(width, height);
        this.setLineWidth(3);
        updateLocation();

        if (LockStatus.LOCKED_BY_ME.equals(lockStatus)) {
            this.setForegroundColor(DiagramColorRegistry.getInstance().getColor(BORDER_COLOR_LOCKED_BY_ME));
            lockStatusImage = DiagramUIPlugin.getPlugin().getImage(LOCK_BY_ME_IMAGE_DESCRIPTOR);
            label = new Label(message, lockStatusImage);
        } else if (LockStatus.LOCKED_BY_OTHER.equals(lockStatus)) {
            this.setForegroundColor(DiagramColorRegistry.getInstance().getColor(BORDER_COLOR_LOCKED_BY_OTHER));
            lockStatusImage = DiagramUIPlugin.getPlugin().getImage(LOCK_BY_OTHER_IMAGE_DESCRIPTOR);
            label = new Label(message, lockStatusImage);
        } else {
            label = new Label(message);
        }
        label.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.add(label);

        propListener = new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                updateLocation();
            }
        };

    }

    /**
     * Create a new instance.
     * 
     * @param rootEditPart
     *            the editor root edit part
     * @param message
     *            the message to display in the notification
     * @param lockStatus
     *            the {@link LockStatus} to display in the notification
     */
    public DiagramSemanticElementLockedNotificationFigure(DiagramRootEditPart rootEditPart, String message, LockStatus lockStatus) {
        this(rootEditPart, message, lockStatus, DEFAULT_HEIGHT, DEFAULT_WIDTH);
    }

    private void updateLocation() {
        Point viewLocation = viewport.getViewLocation().getCopy();

        viewLocation.performScale(1.0d / rootEditPart.getZoomManager().getZoom());

        this.setLocation(new Point(viewLocation.x, viewLocation.y));
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
     * @param lockStatus
     *            the {@link LockStatus} to display in the notification
     */
    public static void createNotification(DiagramRootEditPart rootEditPart, String message, LockStatus lockStatus) {
        final LayeredPane pane = (LayeredPane) rootEditPart.getLayer(LayerConstants.PRINTABLE_LAYERS);

        final IFigure notificationFigure = new DiagramSemanticElementLockedNotificationFigure(rootEditPart, message, lockStatus);
        removeNotification(rootEditPart);
        pane.add(notificationFigure);
    }

    /**
     * Create a new notification figure and display it to the diagram.
     * 
     * @param rootEditPart
     *            the diagram root edit part
     * @param message
     *            the message
     * @param lockStatus
     *            the {@link LockStatus} to display in the notification
     * @param height
     *            notification figure height
     * @param width
     *            notification figure width
     */
    public static void createNotification(DiagramRootEditPart rootEditPart, String message, LockStatus lockStatus, int height, int width) {
        final LayeredPane pane = (LayeredPane) rootEditPart.getLayer(LayerConstants.PRINTABLE_LAYERS);

        final IFigure notificationFigure = new DiagramSemanticElementLockedNotificationFigure(rootEditPart, message, lockStatus, height, width);
        removeNotification(rootEditPart);
        pane.add(notificationFigure);
    }

    /**
     * Create a new notification figure and display it to the diagram.
     * 
     * @param rootEditPart
     *            the diagram root edit part
     * @param lockStatus
     *            the {@link LockStatus} to display in the notification
     */
    public static void createNotification(DiagramRootEditPart rootEditPart, LockStatus lockStatus) {
        final LayeredPane pane = (LayeredPane) rootEditPart.getLayer(LayerConstants.PRINTABLE_LAYERS);

        final IFigure notificationFigure = new DiagramSemanticElementLockedNotificationFigure(rootEditPart, "", lockStatus); //$NON-NLS-1$
        removeNotification(rootEditPart);
        pane.add(notificationFigure);
    }

    /**
     * Removes the notification figure from the diagram.
     * 
     * @param rootEditPart
     *            the diagram root edit part
     */
    public static void removeNotification(DiagramRootEditPart rootEditPart) {
        final LayeredPane pane = (LayeredPane) rootEditPart.getLayer(LayerConstants.PRINTABLE_LAYERS);
        List<IFigure> figuresToRemove = Lists.newArrayList();
        // Collects notification figures that needs to be removed
        for (DiagramSemanticElementLockedNotificationFigure diagramSemanticElementLockedNotificationFigure : Iterables.filter(pane.getChildren(), DiagramSemanticElementLockedNotificationFigure.class)) {
            figuresToRemove.add(diagramSemanticElementLockedNotificationFigure);
        }
        // Removes these notation figures from Layer
        for (IFigure iFigure : figuresToRemove) {
            pane.remove(iFigure);
        }
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

}
