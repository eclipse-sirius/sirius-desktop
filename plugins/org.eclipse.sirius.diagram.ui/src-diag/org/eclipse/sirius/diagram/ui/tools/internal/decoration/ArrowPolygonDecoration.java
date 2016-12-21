/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.decoration;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;

/**
 * 
 * A rotatable, polygon shaped decoration most commonly used for decorating the
 * ends of {@link org.eclipse.draw2d.Polyline polylines}.<BR>
 * This polygon decoration is like polyline decoration but it can be fill to
 * white color.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ArrowPolygonDecoration extends PolygonDecoration {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Polygon#outlineShape(org.eclipse.draw2d.Graphics)
     */
    @Override
    protected void outlineShape(final Graphics g) {
        g.drawPolyline(getPoints());
    }
}
