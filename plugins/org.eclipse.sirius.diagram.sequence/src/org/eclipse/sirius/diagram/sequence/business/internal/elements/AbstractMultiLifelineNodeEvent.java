/*******************************************************************************
 * Copyright (c) 2025 CEA and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import org.eclipse.gmf.runtime.notation.Node;

import com.google.common.base.Predicate;

/**
 * Represents an Gate.
 * 
 * @author smonnier
 */
public abstract class AbstractMultiLifelineNodeEvent extends AbstractSequenceNodeEvent implements ISequenceElement {

    /**
     * Predicate to filter Frames and Operand from possible new parents of an execution reparent.
     */
    public static final Predicate<ISequenceEvent> NO_REPARENTABLE_EVENTS = new Predicate<ISequenceEvent>() {
        @Override
        public boolean apply(ISequenceEvent input) {
            return input instanceof Gate || input instanceof Message;
        }
    };

    /**
     * The visual ID. Same as a normal bordered node.
     * 
     * see org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart. VISUAL_ID
     */
    public static final int VISUAL_ID = 3001;

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing this execution.
     */
    AbstractMultiLifelineNodeEvent(Node node) {
        super(node);
    }
}
