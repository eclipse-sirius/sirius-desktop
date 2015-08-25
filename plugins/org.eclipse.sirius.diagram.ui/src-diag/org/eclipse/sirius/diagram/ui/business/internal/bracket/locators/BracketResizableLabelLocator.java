/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.bracket.locators;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.locator.EdgeLabelLocator;

/**
 * Bracket specific
 * {@link org.eclipse.gmf.runtime.diagram.ui.figures.LabelLocator} to have the
 * center label the middle of the bracket segment as reference point.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BracketResizableLabelLocator extends EdgeLabelLocator {

    /**
     * Constructor for figure who are located and sized.
     *
     * @param parent
     *            the parent figure
     * @param bounds
     *            the bounds
     * @param alignment
     *            the alignment
     */
    public BracketResizableLabelLocator(IFigure parent, Rectangle bounds, int alignment) {
        super(parent, bounds, alignment);
    }

    /**
     * Overridden to return the middle of the bracket segment as reference
     * point.
     *
     * {@inheritDoc}
     */
    @Override
    protected Point getReferencePoint() {
        return BracketLabelLocator.getReferencePoint(parent);
    }
}
