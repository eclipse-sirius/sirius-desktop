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
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class RadioDescriptionItemProviderSpec extends RadioDescriptionItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public RadioDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        String label = ((RadioDescription) object).getLabelExpression();
        return label == null || label.length() == 0 ? getString("_UI_RadioDescription_type") : //$NON-NLS-1$
                label;
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.RADIO_DESCRIPTION__INITIAL_OPERATION, ToolFactory.eINSTANCE.createInitialOperation()));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.RADIO_DESCRIPTION__STYLE, PropertiesFactory.eINSTANCE.createRadioWidgetStyle()));

        RadioWidgetConditionalStyle conditionalStyle = PropertiesFactory.eINSTANCE.createRadioWidgetConditionalStyle();
        conditionalStyle.setStyle(PropertiesFactory.eINSTANCE.createRadioWidgetStyle());
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.RADIO_DESCRIPTION__CONDITIONAL_STYLES, conditionalStyle));
    }
}
