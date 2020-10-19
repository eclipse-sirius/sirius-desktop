/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.Messages;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Represents the EndOfLife marker which can appear at the bottom of a lifeline. This element can be present even in the
 * case where the lifeline is not explicitly destroyed by a destruction message. In that case, it is used as a
 * convenience to allow the user to resize the lifeline vertically by dragging the EndOfLife marker.
 * 
 * @author mporhel, pcdavid
 */
public class EndOfLife extends AbstractSequenceNode {
    /**
     * The visual ID. Same as a normal bordered node.
     * 
     * @see org.eclipse.sirius.diagram.internal.edit.parts.DNode2EditPart.VISUAL_ID
     */
    public static final int VISUAL_ID = 3001;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an EndOfLife.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getEndOfLifeMapping());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node which represents this EOL.
     */
    EndOfLife(Node node) {
        super(node);
        Preconditions.checkArgument(EndOfLife.notationPredicate().apply(node), Messages.EndOfLife_nonEndOfLifeNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an EndOfLife.
     * 
     * @return a predicate to check whether a GMF View represents an EndOfLife.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, EndOfLife.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an EndOfLife.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an EndOfLife.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    /**
     * Returns the destruction message which targets this EOL, if any.
     * 
     * @return the destruction message which targets this EOL, if any.
     */
    public Option<Message> getDestructionMessage() {
        Node node = getNotationNode();
        for (Edge edge : Iterables.filter(node.getTargetEdges(), Edge.class)) {
            Option<Message> message = ISequenceElementAccessor.getMessage(edge);
            if (message.some() && message.get().getKind() == Message.Kind.DESTRUCTION) {
                return message;
            }
        }
        return Options.newNone();
    }

    @Override
    public Rectangle getProperLogicalBounds() {
        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            Rectangle llBounds = getLifeline().get().getProperLogicalBounds();
            Point bottom = llBounds.getBottom();
            int width = bounds.getWidth();
            return new Rectangle(bottom.x - width / 2, bottom.y, bounds.getWidth(), bounds.getHeight());
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * Tests whether this EOL marker (and thus the associated lifeline) is explicitly destroyed by a destruction
     * message.
     * 
     * @return <code>true</code> if this EOL marker is the target of a destruction message.
     */
    public boolean isExplicitelyDestroyed() {
        return getDestructionMessage().some();
    }

}
