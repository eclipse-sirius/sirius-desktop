/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;

/**
 * DModel property tester.
 * 
 * @author mchauvin
 */
public class DModelElementPropertyTester extends PropertyTester {

    /**
     * Constructor.
     */
    public DModelElementPropertyTester() {
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

            if ("isDModelElement".equals(property)) { //$NON-NLS-1$
                final EObject model = part.resolveSemanticElement();
                result = model instanceof DDiagram || model instanceof DDiagramElement;
                if (!result && part.getParent() instanceof IGraphicalEditPart) {
                    // Check the parent editPart, indeed when the selection is
                    // made
                    // with the "rectangle selection" the child editPart is
                    // tested.
                    final EObject parentModel = ((IGraphicalEditPart) part.getParent()).resolveSemanticElement();
                    result = parentModel instanceof DDiagram || parentModel instanceof DDiagramElement;
                }
            } else if ("isDDiagram".equals(property)) { //$NON-NLS-1$
                final EObject model = part.resolveSemanticElement();
                result = model instanceof DDiagram;
            } else if ("isDDiagramElement".equals(property)) { //$NON-NLS-1$
                final EObject model = part.resolveSemanticElement();
                result = model instanceof DDiagramElement;
            }
        }

        return result;

    }

}
