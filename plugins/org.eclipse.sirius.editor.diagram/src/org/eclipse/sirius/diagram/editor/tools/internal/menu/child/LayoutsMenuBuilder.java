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

import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;

/**
 * The layouts menu.
 * 
 * @author cbrun
 * 
 */
public class LayoutsMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public LayoutsMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getOrderedTreeLayout());
        addValidType(DescriptionPackage.eINSTANCE.getCompositeLayout());

    }

    @Override
    public String getLabel() {
        return "New Layout";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.LAYOUT;
    }
}
