/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.action;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Predicate;

/**
 * A specific predicate to match edit part by the semantic element they should
 * represent.
 * 
 * @author cbrun
 * 
 */
public class MatchBySemanticElement implements Predicate<IGraphicalEditPart> {

    private final EObject target;

    /**
     * Create the predicate.
     * 
     * @param target
     *            the semantic element whose edit part we are looking for..
     */
    public MatchBySemanticElement(final EObject target) {
        this.target = target;
    }

    /**
     * {@inheritDoc}
     */
    public boolean apply(final IGraphicalEditPart editpart) {
        final EObject semanticElement = editpart.resolveSemanticElement();
        if (semanticElement == target || (semanticElement instanceof DSemanticDecorator && ((DSemanticDecorator) semanticElement).getTarget() == target)) {
            return true;
        }
        return false;
    }
}
