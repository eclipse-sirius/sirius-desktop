/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * Abstract base class for sequence elements which are represented by a GMF Node.
 * 
 * @author mporhel, pcdavid
 */
abstract class AbstractSequenceNode extends AbstractSequenceElement implements ISequenceNode {
    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node representing this element.
     */
    AbstractSequenceNode(Node node) {
        super(node);
    }

    /**
     * Convenience method to return the underlying GMF View as a Node.
     * 
     * @return the GMF Node representing this element.
     */
    @Override
    public Node getNotationNode() {
        return (Node) view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getBounds() {
        Node node = getNotationNode();
        if (!(node.getElement() instanceof DDiagramElement)) {
            return null;
        } else {
            Point absLoc = GMFNotationHelper.getAbsoluteLocation(node);
            int width = GMFNotationHelper.getWidth(node);
            int height = GMFNotationHelper.getHeight(node);
            return new Rectangle(absLoc.x, absLoc.y, width, height);
        }
    }

    /**
     * Returns the raw bounds of this element as stored in the GMF Node.
     */
    protected Rectangle getRawNotationBounds() {
        if (getNotationNode().getLayoutConstraint() instanceof Bounds) {
            Bounds bounds = (Bounds) getNotationNode().getLayoutConstraint();
            return new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public Option<Lifeline> getLifeline() {
        return getParentLifeline();
    }

    /**
     * Tries to find a lifeline among the ancestors of this element (including the element itself).
     * 
     * @return option on the parent lifeline of this sequenceElement
     */
    protected Option<Lifeline> getParentLifeline() {
        View current = view;
        do {
            Option<Lifeline> lifeline = ISequenceElementAccessor.getLifeline(current);
            if (lifeline.some()) {
                return lifeline;
            } else {
                current = (View) current.eContainer();
            }
        } while (current != null && !(current instanceof Diagram));
        return Options.newNone();
    }
}
