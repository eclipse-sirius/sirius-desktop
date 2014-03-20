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
