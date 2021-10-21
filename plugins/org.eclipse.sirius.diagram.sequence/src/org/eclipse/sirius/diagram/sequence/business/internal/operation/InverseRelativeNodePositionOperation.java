/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import java.util.Objects;

import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * An operation to inverse the relative position of a node on both axes. This is used for labels on messages to make
 * sure they are always above the message, regardless of its direction (left-to-right or right-to-left).
 * 
 * @author pcdavid, smonnier
 */
public class InverseRelativeNodePositionOperation extends AbstractModelChangeOperation<Void> {
    private final Node node;

    /**
     * Constructor.
     * 
     * @param node
     *            the node whose position to inverse.
     */
    public InverseRelativeNodePositionOperation(Node node) {
        super(Messages.InverseRelativeNodePositionOperation_operationName);
        this.node = Objects.requireNonNull(node);
    }

    @Override
    public Void execute() {
        if (node.getLayoutConstraint() instanceof Location) {
            Location l = (Location) node.getLayoutConstraint();
            l.setY(-l.getY());
            l.setX(-l.getX());
        }
        return null;
    }

}
