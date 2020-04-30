/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScalableFigure;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.DConnectionLayerEx;
import org.eclipse.sirius.diagram.ui.tools.internal.handler.SiriusAnimatableZoomManager;

/**
 * Installs the tree designer forest router and a scalable feedback layer.
 *
 * @author ymortier
 */
public class DDiagramRootEditPart extends RenderedDiagramRootEditPart {
    
    /**
     * Layer id for figures that should be drawn on top of everything else.
     */
    public static final String OVERLAY_LAYER = "Overlay Layer"; //$NON-NLS-1$

    private SiriusAnimatableZoomManager siriusZoomManager;

    /**
     * Copy of org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart. zoomLevels
     */
    private double[] siriusZoomLevels = { .05, .1, .25, .5, .75, 1, 1.25, 1.5, 1.75, 2, 4 };

    /**
     * Constructor.
     *
     * @param mu
     *            the measurement unit
     */
    public DDiagramRootEditPart(final MeasurementUnit mu) {
        super(mu);
    }

    /**
     * Installs the {@link org.eclipse.sirius.diagram.ui.tools.internal.routers.DForestRouter} .
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected LayeredPane createPrintableLayers() {
        final FreeformLayeredPane layeredPane = new FreeformLayeredPane();
        layeredPane.add(new BorderItemsAwareFreeFormLayer(), LayerConstants.PRIMARY_LAYER);
        layeredPane.add(new DConnectionLayerEx(), LayerConstants.CONNECTION_LAYER);
        layeredPane.add(new FreeformLayer(), DiagramRootEditPart.DECORATION_PRINTABLE_LAYER);
        layeredPane.add(new FreeformLayer(), OVERLAY_LAYER);
        return layeredPane;
    }

    /**
     * Overridden to have a SCALED_FEEDBACK_LAYER.
     * <p>
     * {@inheritDoc}
     */
    @Override
    protected ScalableFreeformLayeredPane createScaledLayers() {
        ScalableFreeformLayeredPane layers = super.createScaledLayers();
        Layer feedbackLayer = new FreeformLayer();
        feedbackLayer.setEnabled(false);
        layers.add(feedbackLayer, LayerConstants.SCALED_FEEDBACK_LAYER);
        return layers;
    }

    @Override
    public Object getAdapter(Class key) {
        if (key == AutoexposeHelper.class) {
            return new SiriusScroller(this, SiriusScroller.VIEWPOINT_SCROLLER_INSETS);
        }
        return super.getAdapter(key);
    }

    @Override
    public ZoomManager getZoomManager() {
        if (siriusZoomManager == null) {
            siriusZoomManager = new SiriusAnimatableZoomManager((ScalableFigure) getScaledLayers(), (Viewport) getFigure());
            siriusZoomManager.setZoomLevels(siriusZoomLevels);
            refreshEnableZoomAnimation(siriusZoomManager);
        }

        return siriusZoomManager;
    }

    @Override
    public void zoomIn() {
        getZoomManager().zoomIn();
    }

    @Override
    public void zoomIn(Point center) {
        if (getZoomManager() instanceof SiriusAnimatableZoomManager) {
            ((SiriusAnimatableZoomManager) getZoomManager()).zoomTo(getZoomManager().getNextZoomLevel(), center, true);
        }
    }

    @Override
    public void zoomOut() {
        getZoomManager().zoomOut();
    }

    @Override
    public void zoomOut(Point center) {
        if (getZoomManager() instanceof SiriusAnimatableZoomManager) {
            ((SiriusAnimatableZoomManager) getZoomManager()).zoomTo(getZoomManager().getPreviousZoomLevel(), center, true);
        }
    }

    @Override
    public void zoomTo(double zoom, Point center) {
        if (getZoomManager() instanceof SiriusAnimatableZoomManager) {
            ((SiriusAnimatableZoomManager) getZoomManager()).zoomTo(zoom, center, true);
        }
    }

    @Override
    public void zoomTo(Rectangle rect) {
        getZoomManager().zoomTo(rect);
    }

    /**
     * Duplicated method from super DiagramRootEditPart.
     */
    private void refreshEnableZoomAnimation(ZoomManager zoomMangr) {
        IPreferenceStore preferenceStore = (IPreferenceStore) getPreferencesHint().getPreferenceStore();
        boolean animatedZoom = preferenceStore.getBoolean(IPreferenceConstants.PREF_ENABLE_ANIMATED_ZOOM);
        zoomMangr.setZoomAnimationStyle(animatedZoom ? ZoomManager.ANIMATE_ZOOM_IN_OUT : ZoomManager.ANIMATE_NEVER);
    }
}
