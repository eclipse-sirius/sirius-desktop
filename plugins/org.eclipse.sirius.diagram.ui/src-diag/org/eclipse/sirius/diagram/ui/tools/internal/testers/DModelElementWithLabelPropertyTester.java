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
package org.eclipse.sirius.diagram.ui.tools.internal.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;

/**
 * A tester to check if the selection has a label that can be hidden.
 * 
 * @author lredor
 */
public class DModelElementWithLabelPropertyTester extends PropertyTester {

    /**
     * Constructor.
     */
    public DModelElementWithLabelPropertyTester() {
        // Nothing
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
     *      java.lang.String, java.lang.Object[], java.lang.Object)
     */
    public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
        boolean result = false;

        if (receiver instanceof IGraphicalEditPart) {
            final IGraphicalEditPart part = (IGraphicalEditPart) receiver;

            if ("isDModelElementWithLabel".equals(property)) { //$NON-NLS-1$
                final EObject model = part.resolveSemanticElement();
                if (model instanceof DDiagramElement) {
                    result = new DDiagramElementQuery((DDiagramElement) model).canHideLabel();
                }
            }
        }
        return result;

    }
}
