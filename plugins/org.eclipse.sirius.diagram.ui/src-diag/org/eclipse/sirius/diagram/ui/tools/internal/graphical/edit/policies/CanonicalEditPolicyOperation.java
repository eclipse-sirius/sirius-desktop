/*******************************************************************************
 * Copyright (c) 2009, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Provides basic canonical edit policy operations.
 * 
 * @author lredor
 */
public final class CanonicalEditPolicyOperation {

    /**
     * Avoid instantiation
     */
    private CanonicalEditPolicyOperation() {
        // empty.
    }

    /**
     * The semantic element is not valid if the semanticElement is a
     * {@link DSemanticDecorator} and its target is null.
     * 
     * @param semanticElement
     *            The semantic element to check
     * @return true if the semantic element is valid.
     */
    public static boolean isSemanticElementValid(final EObject semanticElement) {
        boolean result = true;
        if (semanticElement instanceof DSemanticDecorator && ((DSemanticDecorator) semanticElement).getTarget() == null) {
            result = false;
        }
        return result;
    }
}
