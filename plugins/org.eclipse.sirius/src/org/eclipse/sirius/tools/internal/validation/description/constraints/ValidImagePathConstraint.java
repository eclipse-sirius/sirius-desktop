/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.validation.description.constraints;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Validate image references.
 * 
 * @author bgrouhan
 *
 */
public class ValidImagePathConstraint extends AbstractValidImageConstraint{
    private static final EAttribute[] attrs = {DescriptionPackage.eINSTANCE.getViewpoint_Icon(),
        DescriptionPackage.eINSTANCE.getDecorationDescription_DecoratorPath(),
        StylePackage.eINSTANCE.getBasicLabelStyleDescription_IconPath(),
        ToolPackage.eINSTANCE.getMenuItemDescription_Icon(),
        ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_IconPath(),
        ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_WindowImagePath(),
        ToolPackage.eINSTANCE.getSelectionWizardDescription_IconPath(),
        ToolPackage.eINSTANCE.getSelectionWizardDescription_WindowImagePath(),
        ToolPackage.eINSTANCE.getToolDescription_IconPath()};
    
    @Override
    public EAttribute[] getImagePathAttributes() {
        return attrs;
    }

}
