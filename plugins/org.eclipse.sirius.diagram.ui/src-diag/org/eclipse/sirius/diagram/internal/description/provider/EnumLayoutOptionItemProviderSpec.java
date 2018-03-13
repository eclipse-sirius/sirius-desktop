/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.description.provider;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.IntegerLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.provider.EnumLayoutOptionItemProvider;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

/**
 * Customize the label of {@link EnumLayoutOption} items in VSM editor. Also override child descriptor to propose
 * available {@link EnumLayoutValue} with their attributes already filled.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class EnumLayoutOptionItemProviderSpec extends EnumLayoutOptionItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            factory to use.
     */
    public EnumLayoutOptionItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        EnumLayoutOption layout = (EnumLayoutOption) object;
        return layout.getLabel() + ": " + (layout.getValue() != null ? layout.getValue().getName() : ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
        EnumLayoutOption layoutOption = (EnumLayoutOption) object;
        CustomLayoutConfiguration layout = (CustomLayoutConfiguration) layoutOption.eContainer();
        CustomLayoutAlgorithm genericLayoutProviderSupplier = layoutProviderRegistry.get(layout.getId());
        Map<String, LayoutOption> layoutOptions = genericLayoutProviderSupplier.getLayoutOptions();
        EnumLayoutOption layoutOptionTemplate = (EnumLayoutOption) layoutOptions.get(layoutOption.getId());
        EList<EnumLayoutValue> choices = layoutOptionTemplate.getChoices();
        for (EnumLayoutValue enumLayoutValue : choices) {
            newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.ENUM_LAYOUT_OPTION__VALUE, EcoreUtil.copy(enumLayoutValue)));
        }
    }

    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        if (child instanceof EnumLayoutValue) {
            return ((EnumLayoutValue) child).getName();
        }

        return super.getCreateChildText(owner, feature, child, selection);
    }

}
