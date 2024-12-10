/*******************************************************************************
 * Copyright (c) 2024 CEA.
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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;

import com.google.common.base.Predicate;

/**
 * Represents an interaction container.
 * 
 * @author smonnier
 */
public class InteractionContainer extends AbstractSequenceNode {

    /**
     * The visual ID. Same as a normal container
     * 
     * @see DNodeContainerEditPart.VISUAL_ID.
     */
    public static final int VISUAL_ID = 2002;

    /**
     * Default margin of the Interaction Container.
     */
    public static final int MARGIN = 50;

    /**
     * Default width of the Interaction Container.
     */
    public static final int DEFAULT_WIDTH = 80 + MARGIN * 2;

    /**
     * Default height of the Interaction Container.
     */
    public static final int DEFAULT_HEIGHT = 500;

    InteractionContainer(Node node) {
        super(node);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an combined fragment.
     * 
     * @return a predicate to check whether a GMF View represents an combined fragment.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, InteractionContainer.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an execution.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an execution.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
    }

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an Interaction Container.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getInteractionContainerMapping());
        }
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
