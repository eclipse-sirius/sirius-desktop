/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;

/**
 * The anchor provider.
 * 
 * @author ymortier
 */
public interface AnchorProvider {

    /**
     * Create and return an anchor.
     * 
     * @param figure
     *            the figure that owns the anchor.
     * 
     * @return the anchor.
     */
    ConnectionAnchor createDefaultAnchor(AirDefaultSizeNodeFigure figure);

    /**
     * Creates an anchor at the specified point (from the ratio of the
     * reference's coordinates and bounds of the figure.
     * 
     * @param figure
     *            the figure that owns the anchor.
     * 
     * @param p
     *            relative reference for the <Code>SlidableAnchor</Code>
     * @return a <code>ConnetionAnchor</code> for this figure with relative
     *         reference at p
     */
    ConnectionAnchor createAnchor(AirDefaultSizeNodeFigure figure, PrecisionPoint p);

}
