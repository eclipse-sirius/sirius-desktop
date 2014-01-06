/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Function;

/**
 * Common interface for all the elements of a sequence diagram.
 * 
 * @author mporhel
 */
public interface ISequenceElement extends Adapter {

    /**
     * Function to get the notation view.
     */
    Function<ISequenceElement, View> NOTATION_VIEW = new Function<ISequenceElement, View>() {
        public View apply(ISequenceElement from) {
            return from.getNotationView();
        }
    };

    /**
     * Function to compute the semantic target element from a sequence element.
     */
    Function<ISequenceElement, EObject> SEMANTIC_TARGET = new Function<ISequenceElement, EObject>() {
        public EObject apply(ISequenceElement from) {
            EObject semantic = null;
            View notationView = from.getNotationView();
            if (notationView != null && notationView.getElement() instanceof DSemanticDecorator) {
                semantic = ((DSemanticDecorator) notationView.getElement()).getTarget();
            }
            return semantic;
        }
    };

    /**
     * A function to compute the proper logical bounds a sequence element.
     */
    Function<ISequenceElement, Rectangle> PROPER_LOGICAL_BOUNDS = new Function<ISequenceElement, Rectangle>() {
        public Rectangle apply(ISequenceElement from) {
            return from.getProperLogicalBounds();
        }
    };

    /**
     * Returns the view corresponding to the current sequence element.
     * 
     * @return the corresponding view.
     */
    View getNotationView();

    /**
     * Returns the semantic object that this element represents.
     * 
     * @return the semantic object that this element represents.
     */
    Option<EObject> getSemanticTargetElement();

    /**
     * Returns the sequence diagram this element is part of.
     * 
     * @return the sequence diagram this element is part of.
     */
    SequenceDiagram getDiagram();

    /**
     * Return an option referencing the current lifeline of the current sequence
     * event.
     * 
     * @return the lifeline of the current node.
     */
    Option<Lifeline> getLifeline();

    /**
     * Returns the logical bounds of this element itself, excluding any attached
     * element (sub-executions, message branches...).
     * 
     * @return the logical bounds of this element itself.
     */
    Rectangle getProperLogicalBounds();
}
