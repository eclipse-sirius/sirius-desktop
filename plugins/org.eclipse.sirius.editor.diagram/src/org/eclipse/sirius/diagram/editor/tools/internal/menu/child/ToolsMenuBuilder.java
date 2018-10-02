/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.editor.tools.internal.menu.child;

import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;

/**
 * The tools menu.
 * 
 * @author cbrun
 * 
 */
public class ToolsMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public ToolsMenuBuilder() {
        super();
        addValidType(ToolPackage.eINSTANCE.getToolSection());
        addValidType(ToolPackage.eINSTANCE.getToolGroup());
        addValidType(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eINSTANCE.getToolDescription());

        addRestrictedType(ToolPackage.eINSTANCE.getNodeCreationDescription());
        addRestrictedType(ToolPackage.eINSTANCE.getContainerCreationDescription());
        addRestrictedType(ToolPackage.eINSTANCE.getEdgeCreationDescription());
    }

    @Override
    public String getLabel() {
        return "New Tool";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.TOOL;
    }
}
