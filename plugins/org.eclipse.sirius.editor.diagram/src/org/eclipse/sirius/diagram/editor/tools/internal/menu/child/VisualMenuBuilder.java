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
 * The visual menu.
 * 
 * @author cbrun
 * 
 */
public class VisualMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * Build the menu.
     */
    public VisualMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getEdgeMapping());
        addValidType(DescriptionPackage.eINSTANCE.getLayer());
        addValidType(DescriptionPackage.eINSTANCE.getNodeMapping());
        addValidType(DescriptionPackage.eINSTANCE.getContainerMapping());
        addValidType(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eINSTANCE.getDecorationDescriptionsSet());

        addRestrictedType(DescriptionPackage.eINSTANCE.getContainerMappingImport());
        addRestrictedType(DescriptionPackage.eINSTANCE.getNodeMappingImport());
    }

    @Override
    public String getLabel() {
        return "New Diagram Element";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.DIAGRAM_ELEMENT;
    }
}
