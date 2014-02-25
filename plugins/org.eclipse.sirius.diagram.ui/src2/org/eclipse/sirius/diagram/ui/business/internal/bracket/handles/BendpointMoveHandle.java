/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.bracket.handles;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.swt.graphics.Cursor;

/**
 * A BendpointMoveHandle that is for the bendpoint in the middle.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BendpointMoveHandle extends org.eclipse.gef.handles.BendpointMoveHandle {

    /**
     * Creates a new BendpointMoveHandle.
     * 
     * @param owner
     *            the ConnectionEditPart owner
     * @param cursor
     *            the Cursor to use on the middle bendpoint
     */
    public BendpointMoveHandle(ConnectionEditPart owner, Cursor cursor) {
        super(owner, getCustomIndex(owner), getCustomIndex(owner));
        setCursor(cursor);
    }

    /**
     * Overridden to use a custom {@link Locator}. {@inheritDoc}
     */
    @Override
    protected void setLocator(Locator locator) {
        super.setLocator(new MidpointLocator(getConnection(), getCustomIndex(getOwner())));
    }

    /**
     * Get a custom index.
     * 
     * @param graphicalEditPart
     *            the host {@link GraphicalEditPart}.
     * @return a custom index
     */
    private static int getCustomIndex(GraphicalEditPart graphicalEditPart) {
        int customIndex = 0;
        final IFigure figure = graphicalEditPart.getFigure();
        if (figure instanceof Connection) {
            final Connection connection = (Connection) figure;
            final PointList points = connection.getPoints();
            if (points.size() == 2) {
                customIndex = 0;
            } else {
                customIndex = 2;
            }
        }
        return customIndex;
    }

}
