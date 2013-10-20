/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.graph.DirectedGraph;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;

/**
 * Specialization of a left to right diagram layout leveraging specific layout
 * settings associated with a given diagram instance.
 * 
 * @author cbrun
 */
@SuppressWarnings("restriction")
public class CompositeLeftRightLayoutProvider extends AbstractCompositeLayoutProvider {
    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.AbstractCompositeLayoutProvider#translateToGraph(org.eclipse.draw2d.geometry.Rectangle)
     */
    @Override
    public Rectangle translateToGraph(final Rectangle r) {
        final Rectangle rDP = new Rectangle(r);
        getMapMode().LPtoDP(rDP);
        return rDP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle translateFromGraph(final Rectangle rect) {
        final Rectangle rLP = new Rectangle(rect);
        getMapMode().DPtoLP(rLP);
        return rLP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean layoutTopDown(final ConnectionEditPart poly) {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.providers.internal.CompositeLayoutProvider#createGraph()
     */
    @Override
    protected DirectedGraph createGraph() {
        DirectedGraph g = super.createGraph();
        g.setDirection(PositionConstants.EAST);
        return g;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.providers.internal.CompositeLayoutProvider#getLayoutDirection(org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart)
     */
    protected int getLayoutDirection(GraphicalEditPart ep) {
        return PositionConstants.EAST;
    }
}
