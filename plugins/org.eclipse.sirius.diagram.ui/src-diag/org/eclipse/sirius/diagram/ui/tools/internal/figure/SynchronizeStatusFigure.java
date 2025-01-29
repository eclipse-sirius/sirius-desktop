/*******************************************************************************
 * Copyright (c) 2017, 2025 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.zoom.ZoomListener;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramColorRegistry;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.TransparentBorder;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ext.draw2d.figure.IFixedFigure;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

/**
 * A figure to display the synchronize status of the diagram in the bottom right corner.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class SynchronizeStatusFigure extends Ellipse implements IFixedFigure {

    /**
     * Border color of the notification figure when diagram is sync.
     */
    public static final RGB BORDER_COLOR_SYNC_DIAG = new RGB(247, 226, 107);

    /**
     * Border color of the notification figure when diagram is unsync.
     */
    public static final RGB BORDER_COLOR_UNSYNC_DIAG = new RGB(247, 89, 18);

    private static final int DEFAULT_WIDTH = 25;

    private static final int DEFAULT_HEIGHT = 25;

    private static final ImageDescriptor SYNC_DIAG_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/syncDiagram.png"); //$NON-NLS-1$

    private static final ImageDescriptor UNSYNC_DIAG_IMAGE_DESCRIPTOR = SiriusEditPlugin.Implementation.getBundledImageDescriptor("icons/full/decorator/unsyncDiagram.png"); //$NON-NLS-1$

    /**
     * The transparency of this shape in percent. Must be in [0, 100] range.
     */
    private int transparency = 20;

    private PropertyChangeListener propListener;

    private ZoomListener zoomListener;

    private Viewport viewport;

    private ZoomManager zoomManager;

    private DiagramRootEditPart rootEditPart;

    private Label label;

    private ScheduledThreadPoolExecutor executor;

    /**
     * Create a new instance.
     * 
     * @param rootEditPart
     *            the editor root edit part
     */
    public SynchronizeStatusFigure(DiagramRootEditPart rootEditPart) {
        this.rootEditPart = rootEditPart;
        this.viewport = (Viewport) rootEditPart.getFigure();
        this.zoomManager = (ZoomManager) rootEditPart.getViewer().getProperty(ZoomManager.class.toString());

        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setLayoutManager(new XYLayout());
        label = new Label((String) null);
        label.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.add(label);
        propListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                // location need to be updated when the scroll bar is moved and particularly when this figure become
                // outside the editor. In that case it is not repaint if not relocated.
                updateLocation();
            }
        };
        zoomListener = new ZoomListener() {
            @Override
            public void zoomChanged(double newZoom) {
                updateLocation();
            }
        };
        this.setConstraint(label, new Rectangle(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));
        updateLocation(true);
        refresh();
    }

    private void refresh() {
        Optional<DDiagram> diagram = Optional.of(rootEditPart).map(rootEP -> rootEP.getChildren().get(0)).map(diagEditPart -> ((IDDiagramEditPart) diagEditPart).resolveDDiagram().get());
        if (diagram.isPresent()) {
            boolean isSynchronized = diagram.get().isSynchronized();
            if (isSynchronized) {
                this.setForegroundColor(DiagramColorRegistry.getInstance().getColor(BORDER_COLOR_SYNC_DIAG));
                this.setLineStyle(SWT.LINE_SOLID);
                this.setLineWidth(3);
                label.setIcon(DiagramUIPlugin.getPlugin().getImage(SYNC_DIAG_IMAGE_DESCRIPTOR));
                label.setToolTip(new Label(Messages.SynchronizeStatusFigure_diagSynchronized));
            } else {
                this.setForegroundColor(DiagramColorRegistry.getInstance().getColor(BORDER_COLOR_UNSYNC_DIAG));
                this.setLineStyle(SWT.LINE_DASH);
                this.setLineWidth(2);
                label.setIcon(DiagramUIPlugin.getPlugin().getImage(UNSYNC_DIAG_IMAGE_DESCRIPTOR));
                label.setToolTip(new Label(Messages.SynchronizeStatusFigure_diagUnsynchronized));
            }
        }
    }

    @Override
    public void updateLocation() {
        updateLocation(false);
    }

    private void updateLocation(boolean force) {
        // We hide the figure during the update location. As, for example, during moving of the scroll, a lot of
        // PropertyChangeEvent can be sent and so a lot of updateLocation(). We only update location after a delay
        // (100ms) and set the figure as visible after. This avoids to have blink effect or temporary wrong location.
        setVisible(false);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Dimension viewDimension = viewport.getSize().getCopy();
                if (viewDimension.width() != 0 && viewDimension.height() != 0) {
                    // If the dimension of the diagram is {0, 0}, the diagram is not yet initialized so we don't compute
                    // location (it would be {-25, -25}).
                    Point viewLocation = viewport.getViewLocation().getCopy();
                    viewLocation.translate(viewDimension.preciseWidth(), viewDimension.preciseHeight());
                    viewLocation.performScale(1.0d / rootEditPart.getZoomManager().getZoom());
                    viewLocation.translate(getSize().negate());
                    setLocation(new Point(viewLocation.x, viewLocation.y));
                }
                setVisible(true);
            }
        };
        if (force) {
            runnable.run();
        } else if (executor != null) {
            executor.remove(executor.getQueue().peek());
            executor.schedule(runnable, 100, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        executor = new ScheduledThreadPoolExecutor(1);
        viewport.addPropertyChangeListener(Viewport.PROPERTY_VIEW_LOCATION, propListener);
        // Add a listener on zoom manager
        zoomManager.addZoomListener(zoomListener);
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        viewport.removePropertyChangeListener(Viewport.PROPERTY_VIEW_LOCATION, propListener);
        zoomManager.removeZoomListener(zoomListener);
        executor.shutdown();
        executor = null;
    }

    /**
     * Create a new notification figure and display it to the diagram.
     * 
     * @param rootEditPart
     *            the diagram root edit part
     */
    public static void updateNotification(DiagramRootEditPart rootEditPart) {
        boolean showSynchronizeStatusDecorator = DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiPreferencesKeys.PREF_SHOW_SYNCHRONIZE_STATUS_DECORATOR.name());
        final LayeredPane pane = (LayeredPane) rootEditPart.getLayer(LayerConstants.PRINTABLE_LAYERS);
        Optional<SynchronizeStatusFigure> synchronizeStatusFigure = getDiagramSynchronizeStatusFigure(rootEditPart);
        if (synchronizeStatusFigure.isPresent()) {
            if (showSynchronizeStatusDecorator) {
                // Refresh the existing figure
                synchronizeStatusFigure.get().refresh();
            } else {
                // Remove the existing figure
                pane.remove(synchronizeStatusFigure.get());
            }
        } else if (showSynchronizeStatusDecorator) {
            // Create a new status figure
            final SynchronizeStatusFigure notificationFigure = new SynchronizeStatusFigure(rootEditPart);
            pane.add(notificationFigure);
        }
    }

    /**
     * Return the diagram sync status figure (if any).
     * 
     * @param rootEditPart
     *            the diagram root edit part
     * @return an optional diagram sync figure.
     */
    public static Optional<SynchronizeStatusFigure> getDiagramSynchronizeStatusFigure(DiagramRootEditPart rootEditPart) {
        final LayeredPane pane = (LayeredPane) rootEditPart.getLayer(LayerConstants.PRINTABLE_LAYERS);
        return pane.getChildren().stream().filter(SynchronizeStatusFigure.class::isInstance).map(SynchronizeStatusFigure.class::cast).findFirst();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Shape#paintFigure(org.eclipse.draw2d.Graphics)
     */
    @Override
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
     * Converts transparency value from percent range [0, 100] to alpha range [0, 255] and applies converted value. 0%
     * corresponds to alpha 255 and 100% corresponds to alpha 0.
     * 
     * @param g
     *            The Graphics used to paint
     * @since 0.9.0
     */
    protected void applyTransparency(Graphics g) {
        g.setAlpha(255 - transparency * 255 / 100);
    }

}
