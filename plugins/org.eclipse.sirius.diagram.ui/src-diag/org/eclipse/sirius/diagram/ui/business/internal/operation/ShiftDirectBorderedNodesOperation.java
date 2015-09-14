/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.operation;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * This command moves the specified bordered nodes of a given EditPart
 * vertically or horizontally. The GMF location constraint is updated according
 * to the passed delta. It is used when an AbstractDNode is resized to ensure
 * the bordered nodes stay at the same side.
 * 
 * @author lredor
 */
public class ShiftDirectBorderedNodesOperation extends AbstractModelChangeOperation<Void> {
    /**
     * The delta
     */
    private Dimension delta;

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
     *            the vertical and horizontal amount to shift the bordered nodes
     *            (in logical space).
     */
    public ShiftDirectBorderedNodesOperation(List<Node> children, Dimension delta) {
        super(MessageFormat.format(Messages.ShiftDirectBorderedNodesOperation_name, delta));
        this.children = children;
        this.delta = delta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void execute() {
        for (Node child : children) {
            LayoutConstraint layoutConstraint = child.getLayoutConstraint();
            if (layoutConstraint instanceof Location && delta != null && (delta.width != 0 || delta.height != 0)) {
                Location location = (Location) layoutConstraint;
                location.setX(location.getX() + delta.width);
                location.setY(location.getY() + delta.height);
            }
        }
        return null;
    }

}
