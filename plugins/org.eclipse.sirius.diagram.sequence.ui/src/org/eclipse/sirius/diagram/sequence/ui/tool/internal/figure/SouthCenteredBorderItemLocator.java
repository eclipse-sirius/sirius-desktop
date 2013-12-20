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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.CenteredBorderItemLocator;

/**
 * Specific locator for south centered figures. There is no possibility to
 * change the side. The border item offset can be forced.
 * 
 * @author mporhel
 * 
 */
public class SouthCenteredBorderItemLocator extends CenteredBorderItemLocator {

    private final Dimension forcedBorderItemOffset;

    /**
     * Creates a new SouthCenteredBorderItemLocator with the specified
     * parentFigure.
     * 
     * @param parentFigure
     *            the parent figure
     */
    public SouthCenteredBorderItemLocator(IFigure parentFigure) {
        this(parentFigure, null);
    }

    /**
     * Creates a new SouthCenteredBorderItemLocator with the specified
     * parentFigure.
     * 
     * @param parentFigure
     *            the parent figure
     * @param forcedBorderItemOffset
     *            unchangeable border item offset.
     */
    public SouthCenteredBorderItemLocator(IFigure parentFigure, Dimension forcedBorderItemOffset) {
        super(parentFigure);
        this.forcedBorderItemOffset = forcedBorderItemOffset;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator#setPreferredSideOfParent(int)
     */
    @Override
    public void setPreferredSideOfParent(int preferredSide) {
        super.setPreferredSideOfParent(PositionConstants.SOUTH);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator#setCurrentSideOfParent(int)
     */
    @Override
    public void setCurrentSideOfParent(int side) {
        super.setCurrentSideOfParent(PositionConstants.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void relocate(IFigure borderItem) {
        super.relocate(borderItem);
        unfix();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator#setBorderItemOffset(org.eclipse.draw2d.geometry.Dimension)
     */
    @Override
    public void setBorderItemOffset(Dimension borderItemOffset) {
        if (forcedBorderItemOffset != null) {
            super.setBorderItemOffset(forcedBorderItemOffset);
        } else {
            super.setBorderItemOffset(borderItemOffset);

        }
    }

}
