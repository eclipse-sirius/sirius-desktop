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
package org.eclipse.sirius.diagram.business.internal.operation;

import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * This command moves the specified bordered nodes of a given EditPart
 * vertically or horizontally. It is used when an AbstractDNode is resized to
 * ensure the bordered nodes stay at the same side.
 * 
 * @author lredor
 */
public class ShiftDirectBorderedNodesOperation extends AbstractModelChangeOperation<Void> {
    /**
     * The direction is one of {@link PositionConstants#HORIZONTAL} or
     * {@link PositionConstants#VERTICAL}
     */
    private final int direction;

    /**
     * The delta
     */
    private final int delta;

    /**
     * The Node to move
     */
    private final List<Node> children;

    /**
     * Constructor.
     * 
     * @param children
     *            the bordered nodes that must be shifted.
     * @param delta
     *            the vertical or horizontal amount to shift the bordered nodes
     *            (in logical space).
     * @param direction
     *            the direction, vertical or horizontal to shift the bordered
     *            nodes (one of {@link PositionConstants#HORIZONTAL} or
     *            {@link PositionConstants#VERTICAL})
     */
    public ShiftDirectBorderedNodesOperation(List<Node> children, int delta, int direction) {
        super("Shift bordered nodes' positions by " + delta);
        this.children = children;
        this.delta = delta;
        this.direction = direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        for (Node child : children) {
            LayoutConstraint layoutConstraint = child.getLayoutConstraint();
            if (layoutConstraint instanceof Location && delta != 0) {
                Location location = (Location) layoutConstraint;
                if (PositionConstants.HORIZONTAL == direction) {
                    location.setX(location.getX() + delta);
                } else {
                    location.setY(location.getY() + delta);
                }
            }
        }
        return null;
    }

}
