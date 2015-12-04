/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Helper to identify the semantic element associated to a Sirius representation
 * element.
 * 
 * @author pcdavid
 */
public class SemanticElementFinder {
    public static EObject getAssociatedSemanticElement(Object selection) {
        EObject semanticElement = null;
        if (selection instanceof GraphicalEditPart) {
            semanticElement = ((GraphicalEditPart) selection).resolveSemanticElement();
        } else if (selection instanceof ConnectionEditPart) {
            semanticElement = ((Edge) ((ConnectionEditPart) selection).getModel()).getElement();
        } else if (selection instanceof DSemanticDecorator) {
            semanticElement = ((DSemanticDecorator) selection).getTarget();
        }
        if (semanticElement instanceof DSemanticDecorator) {
            return ((DSemanticDecorator) semanticElement).getTarget();
        } else {
            return semanticElement;
        }
    }
}
