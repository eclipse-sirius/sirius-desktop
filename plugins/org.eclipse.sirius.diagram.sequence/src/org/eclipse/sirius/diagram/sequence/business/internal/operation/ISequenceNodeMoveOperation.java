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

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceNode;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

/**
 * Move an execution, interaction use of combined fragment vertically.
 * 
 * @author pcdavid
 */
public class ISequenceNodeMoveOperation extends AbstractModelChangeOperation<Void> {

    private final Collection<ISequenceNode> seqNodes = new HashSet<>();

    private final int logicalShift;

    /**
     * Constructor.
     * 
     * @param node
     *            the sequence node to move.
     * @param logicalShift
     *            the logical shift.
     */
    public ISequenceNodeMoveOperation(ISequenceNode node, int logicalShift) {
        super(Messages.ISequenceNodeMoveOperation_operationName);
        this.seqNodes.add(Objects.requireNonNull(node));
        this.logicalShift = logicalShift;
    }

    /**
     * Constructor.
     * 
     * @param nodes
     *            the sequence nodes to move.
     * @param logicalShift
     *            the logical shift.
     */
    public ISequenceNodeMoveOperation(Collection<ISequenceNode> nodes, int logicalShift) {
        super(Messages.ISequenceNodeMoveOperation_operationName);
        Objects.requireNonNull(nodes);
        this.seqNodes.addAll(nodes);
        this.logicalShift = logicalShift;
    }

    @Override
    public Void execute() {
        for (ISequenceNode seqNode : seqNodes) {
            Node node = seqNode.getNotationNode();
            LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Location && logicalShift != 0) {
                Location location = (Location) layoutConstraint;
                location.setY(location.getY() + logicalShift);
            }
        }
        return null;
    }
}
