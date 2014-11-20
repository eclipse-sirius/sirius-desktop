/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.figure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.AnchorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.figure.anchor.ZoomDependantAnchor;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.AlphaBasedSlidableImageAnchor;

/**
 * The air default size node figure to add custom anchor.
 * 
 * @author ymortier
 */
public class AirDefaultSizeNodeFigure extends DefaultSizeNodeFigure {

    /** The zoom manager. */
    protected ZoomManager zoomManager;

    /** The anchor provider. */
    private AnchorProvider anchorProvider;

    /** The slidable anchor area. */
    private double slidableAnchorArea = -1.0; // disabled by default, so that we
                                              // use the value from super.

    /**
     * Create a new {@link AirDefaultSizeNodeFigure}.
     * 
     * @param defSize
     *            the size.
     * @param anchorProvider
     *            the anchor provider.
     */
    public AirDefaultSizeNodeFigure(final Dimension defSize, final AnchorProvider anchorProvider) {
        super(defSize);
        this.anchorProvider = anchorProvider;
    }

    /**
     * Create a new {@link AirDefaultSizeNodeFigure}.
     * 
     * @param width
     *            the width.
     * @param height
     *            the height.
     * @param anchorProvider
     *            the anchor provider.
     */
    public AirDefaultSizeNodeFigure(final int width, final int height, final AnchorProvider anchorProvider) {
        super(width, height);
        this.anchorProvider = anchorProvider;
    }

    /**
     * Create an anchor with the provider given as parameter.
     * 
     * @param provider
     *            the provider
     * @param p
     *            the precision point
     * @return the created anchor
     */
    public ConnectionAnchor createAnchor(final AnchorProvider provider, final PrecisionPoint p) {
        final ConnectionAnchor anchor = provider.createAnchor(this, p);
        setZoomManagerToAnchor(anchor);
        return anchor;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#createAnchor(org.eclipse.draw2d.geometry.PrecisionPoint)
     */
    @Override
    protected ConnectionAnchor createAnchor(final PrecisionPoint p) {
        ConnectionAnchor result;
        if (this.anchorProvider != null) {
            final ConnectionAnchor anchor = this.anchorProvider.createAnchor(this, p);
            setZoomManagerToAnchor(anchor);
            result = anchor;
        } else if (p == null) {
            // If the old terminal for the connection anchor cannot be resolved
            // (by SlidableAnchor) a null PrecisionPoint will passed in - this
            // is handled here
            result = createDefaultAnchor();
        } else {
            result = new AlphaBasedSlidableImageAnchor(this, p);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#createConnectionAnchor(org.eclipse.draw2d.geometry.Point)
     */
    @Override
    protected ConnectionAnchor createConnectionAnchor(final Point p) {
        return super.createConnectionAnchor(p);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#createDefaultAnchor()
     */
    @Override
    protected ConnectionAnchor createDefaultAnchor() {
        if (this.anchorProvider != null) {
            final ConnectionAnchor anchor = this.anchorProvider.createDefaultAnchor(this);
            setZoomManagerToAnchor(anchor);
            return anchor;
        }
        return new AlphaBasedSlidableImageAnchor(this);
    }

    private void setZoomManagerToAnchor(final ConnectionAnchor anchor) {
        if (anchor instanceof ZoomDependantAnchor) {
            ((ZoomDependantAnchor) anchor).setZoomManager(zoomManager);
        }
    }

    /**
     * No insets. {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#getInsets()
     */
    @Override
    public Insets getInsets() {
        return new Insets(0);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#getMinimumSize(int, int)
     */
    @Override
    public Dimension getMinimumSize(final int hint, final int hint2) {
        return new Dimension(10, 10); // the minimun size.
    }

    /**
     * Overridden to allow user control using
     * {@link #setSlidableAnchorArea(double)}.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public double getSlidableAnchorArea() {
        if (this.slidableAnchorArea >= 0) {
            return this.slidableAnchorArea;
        } else {
            return super.getSlidableAnchorArea();
        }
    }

    /**
     * Specifies how large the area of the figure's bounds where SlidableAnchor
     * will be created. The value must be below 1.0. A negative value means
     * "use the default from the superclass".
     * 
     * @param value
     *            the percentage of area of the figure's bounds where
     *            SlidableAnchor will be created.
     */
    public void setSlidableAnchorArea(double value) {
        if (value > 1.0) {
            this.slidableAnchorArea = 1.0;
        } else {
            this.slidableAnchorArea = value;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Figure#getMaximumSize()
     */
    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Return the {@link AnchorProvider} of the figure.
     * 
     * @return the {@link AnchorProvider} of the figure.
     */
    public AnchorProvider getAnchorProvider() {
        return anchorProvider;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure#getConnectionAnchor(java.lang.String)
     */
    @Override
    public ConnectionAnchor getConnectionAnchor(final String terminal) {
        ConnectionAnchor connectAnchor = (ConnectionAnchor) getConnectionAnchors().get(terminal == null ? szAnchor : terminal);
        if (connectAnchor == null) {
            if (szAnchor.equals(terminal)) {
                // get a new one - this figure doesn't support static anchors
                connectAnchor = createDefaultAnchor();
            } else {
                connectAnchor = createAnchor(BaseSlidableAnchor.parseTerminalString(terminal));
            }
            getConnectionAnchors().put(terminal == null ? szAnchor : terminal, connectAnchor);
        }

        return connectAnchor;
    }

    /**
     * Set the zoom manager.
     * 
     * @param manager
     *            the zoom manager
     */
    public void setZoomManager(final ZoomManager manager) {
        if (zoomManager != manager) {
            zoomManager = manager;
            for (ConnectionAnchor anchor : (Iterable<ConnectionAnchor>) this.getConnectionAnchors().values()) {
                if (anchor instanceof ZoomDependantAnchor) {
                    ((ZoomDependantAnchor) anchor).setZoomManager(manager);
                }
            }
        }
    }

}
