/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES and others.
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Represents the ObservationPoint marker which can appear to represent a EventEnd location.
 * 
 * @author mporhel
 */
public class ObservationPoint extends AbstractSequenceNode {
    /**
     * The visual ID. Same as a standard node.
     * 
     * see org.eclipse.sirius.diagram.internal.edit.parts.DNodeEditPart. VISUAL_ID
     */
    public static final int VISUAL_ID = 2001;

    /**
     * Predicate to check whether a Sirius DDiagramElement represents an ObservationPoint.
     */
    private enum SiriusElementPredicate implements Predicate<DDiagramElement> {
        INSTANCE;

        @Override
        public boolean apply(DDiagramElement input) {
            return AbstractSequenceElement.isSequenceDiagramElement(input, DescriptionPackage.eINSTANCE.getObservationPointMapping());
        }
    }

    /**
     * Constructor.
     * 
     * @param node
     *            the GMF Node which represents this ObservationPoint.
     */
    ObservationPoint(Node node) {
        super(node);
        Preconditions.checkArgument(ObservationPoint.notationPredicate().apply(node), Messages.ObservationPoint_nonObservationPointNode);
    }

    /**
     * Returns a predicate to check whether a GMF View represents an ObservationPoint.
     * 
     * @return a predicate to check whether a GMF View represents an ObservationPoint.
     */
    public static Predicate<View> notationPredicate() {
        return new NotationPredicate(NotationPackage.eINSTANCE.getNode(), VISUAL_ID, ObservationPoint.viewpointElementPredicate());
    }

    /**
     * Returns a predicate to check whether a Sirius DDiagramElement represents an ObservationPoint.
     * 
     * @return a predicate to check whether a Sirius DDiagramElement represents an ObservationPoint.
     */
    public static Predicate<DDiagramElement> viewpointElementPredicate() {
        return SiriusElementPredicate.INSTANCE;
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

    /**
     * Look in its diagram'semantic ordering for the observed eventEnd: and event end whose semantic end is the
     * observation point semantic target element.
     * 
     * @return the observed EventEnd if valid.
     */
    public Option<EventEnd> getObservedEventEnd() {
        Option<EObject> semanticTargetElement = getSemanticTargetElement();
        if (semanticTargetElement.some()) {
            for (EventEnd eventEnd : getDiagram().getSequenceDDiagram().getSemanticOrdering().getEventEnds()) {
                if (eventEnd.getSemanticEnd() == semanticTargetElement.get()) {
                    return Options.newSome(eventEnd);
                }
            }
        }
        return Options.newNone();
    }

    /**
     * Get the observed logical location (ie: it corresponds to the center of the logical bounds).
     * 
     * @return the observed logical location.
     */
    public Point getObservedLogicalLocation() {
        return getProperLogicalBounds().getCenter().getCopy();
    }

}
