/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Represents the LostMEssageEnd marker which can appear at at the end of a message. This element can be present if a
 * message do not have a starting end or a finishing end.
 * 
 * @author mporhel
 */
public class LostMessageEnd extends AbstractSequenceNode {
    /**
     * The visual ID. Same as a standard node.
     * 
     * see org.eclipse.sirius.diagram.internal.edit.parts.DNodeEditPart. VISUAL_ID
     */
    public static final int VISUAL_ID = 2001;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an Lost Message End.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            boolean result = AbstractSequenceElement.isSequenceDiagramElement(input, org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE.getNodeMapping());
            if (input instanceof EdgeTarget) {
                EdgeTarget et = (EdgeTarget) input;
                result = result && Iterables.any(Iterables.concat(et.getIncomingEdges(), et.getOutgoingEdges()), Message.viewpointElementPredicate());
            }

            List<Predicate<DDiagramElement>> potentialMessageTarget = new ArrayList<>();
            potentialMessageTarget.add(EndOfLife.viewpointElementPredicate());
            potentialMessageTarget.add(AbstractNodeEvent.viewpointElementPredicate());
            potentialMessageTarget.add(Lifeline.viewpointElementPredicate());
            potentialMessageTarget.add(InstanceRole.viewpointElementPredicate());

            result = result && !Predicates.or(potentialMessageTarget).apply(input);

            return result;
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node which represents this Lost Message End.
     */
    LostMessageEnd(Node node) {
        super(node);
        Preconditions.checkArgument(LostMessageEnd.notationPredicate().apply(node), Messages.LostMessage_nonLostMessageEndNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an Lost Message End.
     * 
     * @return a predicate to check whether a GMF View represents an Lost Message End.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, LostMessageEnd.viewpointElementPredicate());
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
     * Returns the message which references this lost end.
     * 
     * @return the message which references this lost end.
     */
    public Option<Message> getMessage() {
        Message msg = null;
        Node node = getNotationNode();
        Iterable<Edge> srcEdges = Iterables.filter(node.getSourceEdges(), Edge.class);
        Iterable<Edge> tgtEdges = Iterables.filter(node.getTargetEdges(), Edge.class);
        for (Edge edge : Iterables.concat(srcEdges, tgtEdges)) {
            Option<Message> message = ISequenceElementAccessor.getMessage(edge);
            if (message.some()) {
                msg = message.get();
                break;
            }
        }

        return Options.newSome(msg);
    }

    @Override
    public Option<Lifeline> getLifeline() {
        return Options.newNone();
    }

    @Override
    public Rectangle getProperLogicalBounds() {
        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            return new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        } else {
            throw new RuntimeException();
        }
    }
}
