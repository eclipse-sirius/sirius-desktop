/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.expressions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;

/**
 * Expression to display item if the current diagram is selected or any other
 * element that no correspondign to a diagram element.
 * 
 * @author fbarbin
 * 
 */
public class DDiagramTabbarExpression extends TabbarExpression {

    @Override
    protected boolean isVisible(IStructuredSelection selection) {
        boolean isVisible = false;
        if (selection.getFirstElement() instanceof DDiagramEditPart) {
            isVisible = true;
        }
        return isVisible;
    }

}
