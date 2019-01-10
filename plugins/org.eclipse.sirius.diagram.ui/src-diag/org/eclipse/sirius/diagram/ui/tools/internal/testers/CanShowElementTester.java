/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealElementsAction;

/**
 * Tester to know if all selected elements can be revealed and are not visible.
 * 
 * @author fbarbin
 * 
 */
public class CanShowElementTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        boolean result = false;

        if ("canShowElement".equals(property)) { //$NON-NLS-1$
            if (receiver instanceof IStructuredSelection) {
                result = RevealElementsAction.isActive((IStructuredSelection) receiver);
            } else if (receiver instanceof IDiagramElementEditPart) {
                result = RevealElementsAction.isActive((IDiagramElementEditPart) receiver);
            }
        }
        return result;
    }
}
