/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.testers;

import java.util.Iterator;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;

/**
 * Tester to know if at least one element of the selected is kind of IDiagramElementEditPart and has label hidden.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class HiddenLabelTester extends PropertyTester {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object,
     *      java.lang.String, java.lang.Object[], java.lang.Object)
     */
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        boolean result = false;

        if ("isLabelHidden".equals(property)) { //$NON-NLS-1$
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
     * @return true if the selected element has label hidden.
     */
    private boolean testDiagramElementEditPart(IDiagramElementEditPart selectedElement) {
        boolean result = false;
        DDiagramElement diagramElement = selectedElement.resolveDiagramElement();
        if (diagramElement != null) {
            if (selectedElement instanceof AbstractDEdgeNameEditPart) {
                result = new DDiagramElementQuery(diagramElement).isLabelHidden(new EditPartQuery(selectedElement).getVisualID());
            } else {
                result = new DDiagramElementQuery(diagramElement).hasAnyHiddenLabel();
            }
        }
        return result;
    }

    /**
     * @param selectedElements
     *            The current selection
     * @return true if at least one element of the selected is kind of IDiagramElementEditPart and has label hidden.
     */
    private boolean testStructuredSelection(IStructuredSelection selectedElements) {
        boolean atLeastOneIsOK = false;
        final Iterator<?> iterator = selectedElements.iterator();

        while (!atLeastOneIsOK && iterator.hasNext()) {
            final Object next = iterator.next();
            if (next instanceof IDiagramElementEditPart) {
                atLeastOneIsOK = testDiagramElementEditPart((IDiagramElementEditPart) next);
            }
        }
        return atLeastOneIsOK;
    }
}
