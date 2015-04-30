/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.tools.internal.menu.child;

import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;

/**
 * The element creation menu.
 * 
 * @author cbrun
 * 
 */
public class ElementCreationMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public ElementCreationMenuBuilder() {
        super();
        addValidType(ToolPackage.eINSTANCE.getNodeCreationDescription());
        addValidType(ToolPackage.eINSTANCE.getContainerCreationDescription());
        addValidType(ToolPackage.eINSTANCE.getEdgeCreationDescription());
    }

    @Override
    public String getLabel() {
        return "New Element Creation";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.ELEMENT_CREATION;
    }
}
