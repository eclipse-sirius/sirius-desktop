/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;

/**
 * Specialized anchor with some customizations for sequence diagrams.
 * 
 * @author mporhel
 */
public class SequenceSlidableAnchor extends SlidableAnchor {

    boolean custom;

    /**
     * Constructor.
     * 
     * @param owner
     *            the figure that this anchor is associated with..
     */
    public SequenceSlidableAnchor(IFigure owner) {
        super(owner);
    }

    /**
     * Constructor.
     * 
     * @param owner
     *            the figure that this anchor is associated with.
     * @param pp
     *            the PrecisionPoint that the anchor will initially attach to.
     */
    public SequenceSlidableAnchor(IFigure owner, PrecisionPoint pp) {
        super(owner, pp);
    }

    /**
     * Constructor.
     * 
     * @param anchor
     *            .
     * @param pp
     *            the PrecisionPoint that the anchor will initially attach to.
     */
    public SequenceSlidableAnchor(ConnectionAnchor anchor, PrecisionPoint pp) {
        super(anchor.getOwner(), pp);
        custom = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Point getLocation(Point ownReference, Point foreignReference) {
        Point location = ownReference.getCopy();

        if (!custom) {
            location = super.getLocation(ownReference, foreignReference);
        }

        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTerminal() {
        String terminal = super.getTerminal();
        if (custom) {
            terminal = terminal + " custom"; //$NON-NLS-1$
        }
        return terminal;
    }

    /**
     * Parse ice information and set the corresponding location.
     * 
     * @param terminal
     *            an anchor terminal id.
     */
    public void updateCustomStatus(String terminal) {
        if (terminal.contains("custom")) { //$NON-NLS-1$
            custom = true;
        }
    }
}
