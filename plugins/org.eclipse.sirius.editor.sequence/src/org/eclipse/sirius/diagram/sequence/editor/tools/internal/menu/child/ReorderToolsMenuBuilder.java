/*******************************************************************************
 * Copyright (c) 2014, 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.editor.tools.internal.menu.child;

import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;

/**
 * The reorder tools menu.
 * 
 * @author mporhel
 * 
 */
public class ReorderToolsMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public ReorderToolsMenuBuilder() {
        super();
        addValidType(ToolPackage.eINSTANCE.getReorderTool());
        addValidType(ToolPackage.eINSTANCE.getInstanceRoleReorderTool());
    }

    @Override
    public String getLabel() {
        return "New Reorder";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.REORDER;
    }
}
