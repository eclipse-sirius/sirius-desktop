/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.testers;

import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramHelper;

/**
 * Tester to know some elements are hidden in a diagram.
 * 
 * @author dlecan
 */
public class HiddenElementsTester extends PropertyTester {

    /**
     * {@inheritDoc}
     */
    public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
        boolean result = false;

        if ("diagramHasHiddenElements".equals(property) && receiver instanceof IStructuredSelection) { //$NON-NLS-1$
            final Iterator<?> iterator = ((IStructuredSelection) receiver).iterator();

            while (iterator.hasNext()) {

                final Object next = iterator.next();

                // Probably always IGraphicalEditPart type ...
                if (next instanceof IGraphicalEditPart) {
                    final IGraphicalEditPart part = (IGraphicalEditPart) next;

                    final DDiagram dDiagram = DDiagramHelper.findParentDDiagram(part);
                    result = new DDiagramQuery(dDiagram).containsHiddenElements();

                    // Always break loop, we already know with the first
                    // IGraphicalEditPart if
                    // the current diagram contains hidden elements.
                    break;
                }
            }
        }
        return result;
    }
}
