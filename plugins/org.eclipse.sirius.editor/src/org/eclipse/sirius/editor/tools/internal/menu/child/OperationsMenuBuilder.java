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
package org.eclipse.sirius.editor.tools.internal.menu.child;

import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * The operations menu.
 * 
 * @author cbrun
 * 
 */
public class OperationsMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public OperationsMenuBuilder() {
        super();
        addValidType(ToolPackage.eINSTANCE.getModelOperation());
        addValidType(ToolPackage.eINSTANCE.getInitialOperation());
        addValidType(ToolPackage.eINSTANCE.getInitialNodeCreationOperation());
        addValidType(ToolPackage.eINSTANCE.getInitEdgeCreationOperation());
        addValidType(ToolPackage.eINSTANCE.getInitialContainerDropOperation());

        addRestrictedType(ToolPackage.eINSTANCE.getExternalJavaAction());
    }

    @Override
    public String getLabel() {
        return "New Operation";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.OPERATION;
    }
}
