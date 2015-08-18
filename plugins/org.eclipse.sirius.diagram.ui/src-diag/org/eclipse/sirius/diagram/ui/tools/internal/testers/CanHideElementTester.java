/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;

/**
 * Tester to know if all selected elements can be hidden and are not hidden.
 * 
 * @author mporhel
 * 
 */
public class CanHideElementTester extends PropertyTester {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
     *      java.lang.String, java.lang.Object[], java.lang.Object)
     */
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        boolean result = false;

        if ("canHideElement".equals(property)) { //$NON-NLS-1$
            if (receiver instanceof IStructuredSelection) {
                result = testStructuredSelection((IStructuredSelection) receiver);
            } else if (receiver instanceof IDiagramElementEditPart) {
                result = testDiagramElementEditPart((IDiagramElementEditPart) receiver);
            }
        }
        return result;
    }

    /**
     * @param selectedElement
     *            The current selection
     * @return true if all selected element has label hidden.
     */
    private boolean testDiagramElementEditPart(IDiagramElementEditPart selectedElement) {
        boolean result = true;
        DDiagramElement diagramElement = selectedElement.resolveDiagramElement();
        if (diagramElement == null) {
            result = false;
        } else {
            DDiagram parentDiagram = diagramElement.getParentDiagram();
            result = parentDiagram != null ? HideDDiagramElementAction.allowsHideReveal(parentDiagram).apply(diagramElement) : false;
        }
        return result;
    }

    /**
     * @param selectedElements
     *            The current selection
     * @return true if all selected elements is kind of IDiagramElementEditPart
     *         and has label hidden.
     */
    private boolean testStructuredSelection(IStructuredSelection selectedElements) {
        boolean result = true;
        final Iterator<?> iterator = selectedElements.iterator();

        if (!iterator.hasNext()) {
            result = false;
        }
        while (iterator.hasNext()) {
            final Object next = iterator.next();
            if (next instanceof IDiagramElementEditPart) {
                result = result && testDiagramElementEditPart((IDiagramElementEditPart) next);
            } else {
                result = false;
            }
        }
        return result;
    }
}
