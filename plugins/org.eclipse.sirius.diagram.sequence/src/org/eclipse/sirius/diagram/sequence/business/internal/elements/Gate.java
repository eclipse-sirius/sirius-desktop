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

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.business.internal.util.ParentOperandFinder;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Represents a Gate on a Combine fragment, Interaction Use or Interaction Container.
 * 
 * @author smonnier
 */
public class Gate extends AbstractSequenceNode {

    /**
     * The visual ID. Same as a normal bordered node on a container.
     */
    public static final int VISUAL_ID = 3012;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an gate.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getGateMapping())
                    && !InstanceRole.viewpointElementPredicate().apply((DDiagramElement) input.eContainer());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing this gate.
     */
    Gate(Node node) {
        super(node);
        Preconditions.checkArgument(Gate.notationPredicate().apply(node), Messages.Gate_nonGateNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an gate.
     * 
     * @return a predicate to check whether a GMF View represents an gate.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, Gate.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an gate.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an gate.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    /**
     * Returns the hierarchical parent event of this event (from a Notation point of view), if any.
     * 
     * @return the hierarchical parent event of this event, if any.
     */
    public ISequenceElement getHierarchicalParent() {
        if (CacheHelper.isStructuralCacheEnabled()) {
            ISequenceElement hierarchicalParent = CacheHelper.getGateToHierarchicalParentCache().get(this);
            if (hierarchicalParent != null) {
                return hierarchicalParent;
            }
        }

        EObject viewContainer = this.view.eContainer();
        if (viewContainer instanceof View) {
            View parentView = (View) viewContainer;
            Option<ISequenceNode> parentElement = ISequenceElementAccessor.getISequenceNode(parentView);
            if (parentElement.some()) {
                if (CacheHelper.isStructuralCacheEnabled()) {
                    CacheHelper.getGateToHierarchicalParentCache().put(this, parentElement.get());
                }

                return parentElement.get();
            }
        }
        throw new RuntimeException(MessageFormat.format(Messages.Gate_invalidGateContext, this));
    }

    /**
     * Returns the message which references this Gate.
     * 
     * @return the message which references this Gate.
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
        Rectangle parentBounds = getHierarchicalParent().getProperLogicalBounds();

        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            return new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()).getTranslated(parentBounds.getLocation());
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * If the parent is a CombinedFragment, find the operand whose vertical range includes the Gate center.
     * 
     * @return the deepest Operand container if existing.
     */
    public Option<Operand> getSiblingOperand() {
        Option<Operand> siblingOperand = Options.newNone();
        ISequenceElement parent = getHierarchicalParent();
        if (parent instanceof CombinedFragment cf) {
            int gateCenter = this.getProperLogicalBounds().getCenter().y;
            Optional<Operand> found = cf.getOperands().stream().filter(op -> op.getVerticalRange().includes(gateCenter)).findFirst();
            if (found.isPresent()) {
                siblingOperand = Options.fromNullable(found.get());
            }
        }
        return siblingOperand;
    }

    /**
     * Finds the deepest Operand container of the Gate's parent.
     * 
     * @return the deepest Operand container if existing.
     */
    public Option<Operand> getParentOperand() {
        Option<Operand> parentOperand = Options.newNone();
        ISequenceElement parent = getHierarchicalParent();
        if (parent instanceof AbstractFrame frame) {
            Collection<Lifeline> lifelines = frame.computeCoveredLifelines();
            if (lifelines.isEmpty()) {
                parentOperand = frame.getParentOperand();
            } else {
                int frameTop = frame.getProperLogicalBounds().y;
                parentOperand = new ParentOperandFinder(lifelines.iterator().next()).getParentOperand(new Range(frameTop, frameTop));
            }
        }
        return parentOperand;
    }

}
