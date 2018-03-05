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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.EnumOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.description.provider.CustomLayoutConfigurationItemProvider;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;

/**
 * Customize the label of {@link GenericLayout} items in VSM editor. Also override child descriptor to propose available
 * {@link LayoutOption} with their attributes already filled.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class CustomLayoutConfigurationItemProviderSpec extends CustomLayoutConfigurationItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory
     *            factory to use.
     */
    public CustomLayoutConfigurationItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        CustomLayoutConfiguration layout = (CustomLayoutConfiguration) object;
        return layout.getLabel();
    }

    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
        CustomLayoutConfiguration layout = (CustomLayoutConfiguration) object;

        CustomLayoutAlgorithm genericLayoutProviderSupplier = layoutProviderRegistry.get(layout.getId());
        Map<String, LayoutOption> layoutOptions = genericLayoutProviderSupplier.getLayoutOptions();
        for (LayoutOption layoutOption : layoutOptions.values()) {
            LayoutOption copy = EcoreUtil.copy(layoutOption);
            if (copy instanceof EnumOption) {
                ((EnumOption) copy).getChoices().clear();
            }
            newChildDescriptors.add(createChildParameter(org.eclipse.sirius.diagram.description.DescriptionPackage.Literals.CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS, copy));
        }
    }

    @Override
    public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
        if (child instanceof LayoutOption) {
            return ((LayoutOption) child).getLabel();
        }

        return super.getCreateChildText(owner, feature, child, selection);
    }

}
