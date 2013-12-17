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
package org.eclipse.sirius.diagram.tools.internal.graphical.edit.part;

import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.ScalableFreeformLayeredPane;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.sirius.diagram.tools.internal.figure.DConnectionLayerEx;

/**
 * Installs the tree designer forest router and a scalable feedback layer.
 * 
 * @author ymortier
 */
public class DDiagramRootEditPart extends RenderedDiagramRootEditPart {

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
     * Installs the
     * {@link org.eclipse.sirius.diagram.ui.tools.internal.routers.DForestRouter}
     * . {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart#createPrintableLayers()
     */
    @Override
    protected LayeredPane createPrintableLayers() {
        final FreeformLayeredPane layeredPane = new FreeformLayeredPane();
        layeredPane.add(new BorderItemsAwareFreeFormLayer(), PRIMARY_LAYER);
        layeredPane.add(new DConnectionLayerEx(), CONNECTION_LAYER);
        layeredPane.add(new FreeformLayer(), DECORATION_PRINTABLE_LAYER);
        return layeredPane;
    }

    /**
     * Overridden to have a SCALED_FEEDBACK_LAYER.
     * 
     * {@inheritDoc}
     */
    @Override
    protected ScalableFreeformLayeredPane createScaledLayers() {
        ScalableFreeformLayeredPane layers = super.createScaledLayers();
        layers.add(new org.eclipse.sirius.diagram.tools.internal.figure.FeedbackLayer(), SCALED_FEEDBACK_LAYER);
        return layers;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editparts.FreeformGraphicalRootEditPart#getAdapter(java.lang.Class)
     */
    public Object getAdapter(@SuppressWarnings("rawtypes")
    Class key) {
        if (key == AutoexposeHelper.class)
            return new SiriusScroller(this, SiriusScroller.VIEWPOINT_SCROLLER_INSETS);
        return super.getAdapter(key);
    }

}
