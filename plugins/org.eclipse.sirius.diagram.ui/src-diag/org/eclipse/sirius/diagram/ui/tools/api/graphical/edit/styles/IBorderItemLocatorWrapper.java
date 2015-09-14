/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * This class wraps an
 * {@link org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator} that
 * must be used instead {@link IBorderItemLocator}. As GMF generated code uses
 * {@link IBorderItemLocator} instead
 * {@link org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator} this
 * wrapper lets the client to use the good interface.
 * 
 * @author ymortier
 */
public class IBorderItemLocatorWrapper implements IBorderItemLocator {

    /** The wrapped locator. */
    private org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator wrappedLocator;

    /**
     * Create a new <code>IBorderItemLocatorWrapper</code>.
     * 
     * @param locator
     *            the locator to wrap.
     * @throws IllegalArgumentException
     *             if <code>locator</code> is <code>null</code>.
     */
    public IBorderItemLocatorWrapper(final org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator locator) throws IllegalArgumentException {
        if (locator == null) {
            throw new IllegalArgumentException(Messages.IBorderItemLocatorWrapper_nullLocator);
        }
        this.wrappedLocator = locator;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator#getCurrentSideOfParent()
     */
    @Override
    public int getCurrentSideOfParent() {
        return this.wrappedLocator.getCurrentSideOfParent();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator#getValidLocation(org.eclipse.draw2d.geometry.Rectangle,
     *      org.eclipse.draw2d.IFigure)
     */
    @Override
    public Rectangle getValidLocation(final Rectangle proposedLocation, final IFigure borderItem) {
        return this.wrappedLocator.getValidLocation(proposedLocation, borderItem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator#setConstraint(org.eclipse.draw2d.geometry.Rectangle)
     */
    @Override
    public void setConstraint(final Rectangle constraint) {
        this.wrappedLocator.setConstraint(constraint);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.draw2d.Locator#relocate(org.eclipse.draw2d.IFigure)
     */
    @Override
    public void relocate(final IFigure target) {
        this.wrappedLocator.relocate(target);
    }

}
