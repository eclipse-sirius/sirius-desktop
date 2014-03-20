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

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;

/**
 * Expression returning true if selection contains diagram elements.
 * 
 * @author fbarbin
 * 
 */
public class DDiagramElementTabbarExpression extends TabbarExpression {

    @Override
    protected boolean isVisible(IStructuredSelection selection) {
        Object firstSelectedElement = selection.getFirstElement();
        return !(firstSelectedElement instanceof DDiagramEditPart) && firstSelectedElement instanceof IGraphicalEditPart;
    }

}
