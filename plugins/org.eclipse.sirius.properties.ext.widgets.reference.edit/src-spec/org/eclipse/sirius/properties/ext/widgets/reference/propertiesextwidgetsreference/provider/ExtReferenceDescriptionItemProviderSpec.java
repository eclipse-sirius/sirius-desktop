/**
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferenceFactory;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;
import org.eclipse.sirius.properties.provider.Utils;

/**
 * The user modified version of the {@link ExtReferenceDescriptionItemProvider}.
 * 
 * @author sbegaudeau
 */
public class ExtReferenceDescriptionItemProviderSpec extends ExtReferenceDescriptionItemProvider {

    /**
     * The constructor.
     * 
     * @param adapterFactory
     *            The adapter factory
     */
    public ExtReferenceDescriptionItemProviderSpec(AdapterFactory adapterFactory) {
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
        return Utils.computeLabel(this, object, "_UI_ExtReferenceDescription_type"); //$NON-NLS-1$
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        newChildDescriptors.add(createChildParameter(PropertiesExtWidgetsReferencePackage.Literals.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE,
                PropertiesExtWidgetsReferenceFactory.eINSTANCE.createExtReferenceWidgetStyle()));

        ExtReferenceWidgetConditionalStyle conditionalStyle = PropertiesExtWidgetsReferenceFactory.eINSTANCE.createExtReferenceWidgetConditionalStyle();
        conditionalStyle.setStyle(PropertiesExtWidgetsReferenceFactory.eINSTANCE.createExtReferenceWidgetStyle());
        newChildDescriptors.add(createChildParameter(PropertiesExtWidgetsReferencePackage.Literals.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES, conditionalStyle));
    }

}
