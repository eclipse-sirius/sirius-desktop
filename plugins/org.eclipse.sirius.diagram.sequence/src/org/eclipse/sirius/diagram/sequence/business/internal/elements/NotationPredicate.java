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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Predicate to check whether a GMF View represents an EndOfLife.
 * 
 * @author pcdavid
 */
public class NotationPredicate implements Predicate<View> {
    private final EClass viewType;

    private final int visualId;

    private final Predicate<DDiagramElement> viewpointElementPredicate;

    /**
     * Constructor.
     * 
     * @param viewType
     *            the expected type of GMF View.
     * @param visualId
     *            the visual id of the view.
     * @param viewpointElementPredicate
     *            the test to apply to the underlying DDiagramElement.
     */
    public NotationPredicate(EClass viewType, int visualId, Predicate<DDiagramElement> viewpointElementPredicate) {
        this.viewType = Preconditions.checkNotNull(viewType);
        this.visualId = visualId;
        this.viewpointElementPredicate = Preconditions.checkNotNull(viewpointElementPredicate);
    }

    /**
     * {@inheritDoc}
     */
    public boolean apply(View input) {
        if (viewType.isInstance(input) && viewHasType(input, visualId)) {
            EObject element = input.getElement();
            return (element instanceof DDiagramElement) && viewpointElementPredicate.apply((DDiagramElement) element);
        } else {
            return false;
        }
    }

    private boolean viewHasType(View view, int type) {
        return view.getType().equals(Integer.toString(type));
    }

}
