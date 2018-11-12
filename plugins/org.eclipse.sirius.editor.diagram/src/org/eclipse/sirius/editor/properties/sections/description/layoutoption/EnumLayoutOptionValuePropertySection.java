/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.layoutoption;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EnumLayoutOption;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractComboPropertySection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section allowing to edit enum value of {@link EnumLayoutOption} object.
 * 
 * This section contains a label, an help button, a combo to select the value and a removal item button to remove
 * container of the attribute.
 * 
 */
public class EnumLayoutOptionValuePropertySection extends AbstractComboPropertySection {
    /** Help control of the section. */
    protected CLabel help;

    /**
     * The button allowing to remove this layout option override.
     */
    protected Button removeOverrideButton;

    /**
     * The layout option represented by this property section.
     */
    private LayoutOption layoutOption;

    /**
     * Initialize this property section with the semantic element behind.
     * 
     * @param layoutOption
     *            the semantic element behind the section.
     */
    public EnumLayoutOptionValuePropertySection(LayoutOption layoutOption) {
        this.layoutOption = layoutOption;
    }

    @Override
    protected String getDefaultLabelText() {
        return layoutOption.getLabel();
    }

    @Override
    protected String getLabelText() {
        String label = LayoutOptionPropertiesUtils.getLabel((CustomLayoutConfiguration) layoutOption.eContainer(), layoutOption);
        return label == null ? Messages.LayoutOptionValue_defaultLabel : label;
    }

    @Override
    protected EReference getFeature() {
        return DescriptionPackage.eINSTANCE.getEnumLayoutOption_Value();
    }

    @Override
    protected Object getFeatureValue(int index) {
        return getFeatureValueAt(index);
    }

    @Override
    protected boolean isEqual(int index) {
        boolean isEqual = false;
        if (getFeatureValueAt(index) == null)
            isEqual = eObject.eGet(getFeature()) == null;
        else
            isEqual = getFeatureValueAt(index).equals(eObject.eGet(getFeature()));
        return isEqual;
    }

    /**
     * Returns the value at the specified index in the choice of values for the feature.
     * 
     * @param index
     *            Index of the value.
     * @return the value at the specified index in the choice of values.
     */
    protected Object getFeatureValueAt(int index) {
        List<?> values = getChoiceOfValues();
        if (values.size() < index || values.size() == 0 || index == -1) {
            return null;
        }
        return values.get(index);
    }

    /**
     * Fetches the list of available values for the feature.
     * 
     * @return The list of available values for the feature.
     */
    @Override
    protected List<?> getChoiceOfValues() {
        final List<EnumLayoutValue> result = new ArrayList<>();
        Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
        CustomLayoutConfiguration layout = (CustomLayoutConfiguration) layoutOption.eContainer();
        CustomLayoutAlgorithm genericLayoutProviderSupplier = layoutProviderRegistry.get(layout.getId());
        if (genericLayoutProviderSupplier != null) {
            Map<String, LayoutOption> layoutOptions = genericLayoutProviderSupplier.getLayoutOptions();
            EnumLayoutOption layoutOptionTemplate = (EnumLayoutOption) layoutOptions.get(layoutOption.getId());
            EList<EnumLayoutValue> choices = layoutOptionTemplate.getChoices();
            EnumLayoutOption layoutOptionEnum = (EnumLayoutOption) layoutOption;
            for (EnumLayoutValue enumLayoutValue : choices) {
                boolean alreadyExistingOption = layoutOptionEnum.getValue() != null && enumLayoutValue.getName().equals(layoutOptionEnum.getValue().getName());
                if (!alreadyExistingOption) {
                    result.add(EcoreUtil.copy(enumLayoutValue));
                } else {
                    result.add(layoutOptionEnum.getValue());
                }
            }
        }
        return result;
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        eObject = layoutOption;

        ((FormData) combo.getLayoutData()).right = new FormAttachment(94, 0);

        removeOverrideButton = LayoutOptionPropertiesUtils.createRemoveOptionButton(combo, composite, (ViewpointPropertySheetPage) tabbedPropertySheetPage, getWidgetFactory(), layoutOption);

        help = LayoutOptionPropertiesUtils.createHelpLabel(getWidgetFactory(), composite, combo, nameLabel, getHelpIcon(), layoutOption);
    }

    @Override
    public void dispose() {
        if (help != null) {
            help.dispose();
        }
        if (removeOverrideButton != null) {
            removeOverrideButton.dispose();
        }
        super.dispose();
    }
}
