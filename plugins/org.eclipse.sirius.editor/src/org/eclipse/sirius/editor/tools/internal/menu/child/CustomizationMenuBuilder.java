/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * A {@link AbstractTypeRestrictingMenuBuilder} to contribute the
 * "Customization" menu.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class CustomizationMenuBuilder extends AbstractTypeRestrictingMenuBuilder {

    /**
     * Default constructor.
     */
    public CustomizationMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getCustomization());
        addValidType(DescriptionPackage.eINSTANCE.getVSMElementCustomization());
        addValidType(DescriptionPackage.eINSTANCE.getVSMElementCustomizationReuse());
        addValidType(DescriptionPackage.eINSTANCE.getEAttributeCustomization());
        addValidType(DescriptionPackage.eINSTANCE.getEReferenceCustomization());
    }

    @Override
    public String getLabel() {
        return "New Customization";
    }

    @Override
    public int getPriority() {
        return AbstractMenuBuilder.CUSTOMIZATION;
    }
}
