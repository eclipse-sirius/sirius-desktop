/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomWidgetConditionalStyle;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class CustomDescriptionItemProviderSpec extends CustomDescriptionItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public CustomDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        String label = ((CustomDescription) object).getLabelExpression();
        return label == null || label.length() == 0 ? getString("_UI_CustomDescription_type") : //$NON-NLS-1$
                label;
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS, PropertiesFactory.eINSTANCE.createCustomExpression()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS, PropertiesFactory.eINSTANCE.createCustomOperation()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CUSTOM_DESCRIPTION__STYLE, PropertiesFactory.eINSTANCE.createCustomWidgetStyle()));

        CustomWidgetConditionalStyle conditionalStyle = PropertiesFactory.eINSTANCE.createCustomWidgetConditionalStyle();
        conditionalStyle.setStyle(PropertiesFactory.eINSTANCE.createCustomWidgetStyle());
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CUSTOM_DESCRIPTION__CONDITIONAL_STYLES, conditionalStyle));
    }
}
