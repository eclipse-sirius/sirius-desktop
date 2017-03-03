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
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class CategoryItemProviderSpec extends CategoryItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public CategoryItemProviderSpec(AdapterFactory adapterFactory) {
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
        return Utils.computeLabel(this, object, "_UI_Category_type"); //$NON-NLS-1$
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        PageDescription page = PropertiesFactory.eINSTANCE.createPageDescription();
        page.setLabelExpression("Page"); //$NON-NLS-1$
        page.setSemanticCandidateExpression(ViewExtensionDescriptionItemProviderSpec.DEFAULT_SEMANTIC_CANDIDATES_EXPRESSION);
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__PAGES, page));

        GroupDescription group = PropertiesFactory.eINSTANCE.createGroupDescription();
        group.setLabelExpression("Group"); //$NON-NLS-1$
        group.setSemanticCandidateExpression(ViewExtensionDescriptionItemProviderSpec.DEFAULT_SEMANTIC_CANDIDATES_EXPRESSION);
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__GROUPS, group));

        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createPageOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createGroupOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createContainerOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createTextOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createButtonOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createLabelOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createCheckboxOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createSelectOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createDynamicMappingForOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createDynamicMappingIfOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createTextAreaOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createRadioOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createListOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createCustomOverrideDescription()));
        newChildDescriptors.add(createChildParameter(PropertiesPackage.Literals.CATEGORY__OVERRIDES, PropertiesFactory.eINSTANCE.createHyperlinkOverrideDescription()));
    }
}
