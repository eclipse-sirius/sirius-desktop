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
package org.eclipse.sirius.diagram.ui.tools.api.figure.locator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.LayoutHelper;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;

/**
 * This locator locate the item in the north of the bordered figure.
 * 
 * @author ymortier
 */
public class NorthNameLocator implements IBorderItemLocator {

    /** Constraints. */
    private Rectangle constraint;

    /** the figure around which this border item appears */
    private final IFigure parentFigure;

    /** The offset between the bordered figure and the border item. */
    private int offset = 10;

    /**
     * Create a new <code>LilelineNameLocator</code>.
     * 
     * @param parentFigure
     *            the parent figure.
     */
    public NorthNameLocator(final IFigure parentFigure) {
        this.parentFigure = parentFigure;
    }

    /**
     * Create a new <code>LilelineNameLocator</code>.
     * 
     * @param parentFigure
     *            the parent figure.
     * @param offset
     *            The offset between the bordered figure and the border item.
     */
    public NorthNameLocator(final IFigure parentFigure, final int offset) {
        this.parentFigure = parentFigure;
        this.offset = offset;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator#getCurrentSideOfParent()
     */
    public int getCurrentSideOfParent() {
        return PositionConstants.NORTH;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator#getValidLocation(org.eclipse.draw2d.geometry.Rectangle,
     *      org.eclipse.draw2d.IFigure)
     */
    public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem) {
        final Rectangle realLocation = new Rectangle(proposedLocation);
        final int x = (getParentBorder().x + (getParentBorder().width / 2)) - (getSize(borderItem).width / 2);
        final int y = getParentBorder().y - getSize(borderItem).height - this.offset;
        realLocation.setLocation(new Point(x, y));
        realLocation.setSize(getSize(borderItem));
        return realLocation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator#setConstraint(org.eclipse.draw2d.geometry.Rectangle)
     */
    public void setConstraint(final Rectangle constraint) {
        this.constraint = constraint;
        getParentFigure().revalidate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Locator#relocate(org.eclipse.draw2d.IFigure)
     */
    public void relocate(final IFigure target) {
        final Rectangle proposed = new Rectangle(this.constraint);
        this.getParentFigure().translateToAbsolute(proposed);
        this.constraint = this.getValidLocation(proposed, target);
        target.setSize(this.getSize(target));
        target.setLocation(this.constraint.getLocation());
    }

    /**
     * Return the parentFigure.
     * 
     * @return the parentFigure.
     */
    public IFigure getParentFigure() {
        return this.parentFigure;
    }

    /**
     * Utility to calculate the parent bounds with consideration for the handle
     * bounds inset.
     * 
     * @return <code>Rectangle</code> that is the bounds of the parent.
     */
    protected Rectangle getParentBorder() {
        Rectangle bounds = new Rectangle(getParentFigure().getBounds());
        if (getParentFigure() instanceof NodeFigure) {
            bounds = new Rectangle(((NodeFigure) getParentFigure()).getHandleBounds());
        }
        return bounds;
    }

    /**
     * Gets the size of the border item figure.
     * 
     * @param borderItem
     *            the border item figure to get the size
     * @return the size of the border item figure.
     */
    protected final Dimension getSize(final IFigure borderItem) {
        Dimension size = LayoutHelper.UNDEFINED.getSize();
        if (this.constraint != null) {
            size = this.constraint.getSize();
        }
        if (LayoutHelper.UNDEFINED.getSize().equals(size)) {
            size = borderItem.getPreferredSize();
        }
        return size;
    }
}
