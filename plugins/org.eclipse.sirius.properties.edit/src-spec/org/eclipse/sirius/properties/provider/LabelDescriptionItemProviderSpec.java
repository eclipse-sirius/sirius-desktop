/**
 * Copyright (c) 2016, 2017 Obeo.
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
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class LabelDescriptionItemProviderSpec extends LabelDescriptionItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public LabelDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        Object styledText = this.getStyledText(object);
        if (styledText instanceof StyledString) {
            return ((StyledString) styledText).getString();
        }
        return super.getText(object);
    }

    @Override
    public Object getStyledText(Object object) {
        return Utils.computeLabel(this, object, "_UI_LabelDescription_type"); //$NON-NLS-1$
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_LABEL_DESCRIPTION__STYLE, PropertiesFactory.eINSTANCE.createLabelWidgetStyle()));

        LabelWidgetConditionalStyle conditionalStyle = PropertiesFactory.eINSTANCE.createLabelWidgetConditionalStyle();
        conditionalStyle.setStyle(PropertiesFactory.eINSTANCE.createLabelWidgetStyle());
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES, conditionalStyle));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.ABSTRACT_LABEL_DESCRIPTION__ACTIONS, PropertiesFactory.eINSTANCE.createWidgetAction()));
    }
}
