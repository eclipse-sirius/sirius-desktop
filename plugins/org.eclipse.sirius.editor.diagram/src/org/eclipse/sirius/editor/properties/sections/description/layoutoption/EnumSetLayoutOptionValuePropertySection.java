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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.EnumLayoutValue;
import org.eclipse.sirius.diagram.description.EnumSetLayoutOption;
import org.eclipse.sirius.diagram.description.LayoutOption;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.editor.Messages;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogPropertySection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section allowing to edit enum set values of {@link EnumSetLayoutOption} object.
 * 
 * This section contains a label, an help button, a text widget to show the used values, a button to open a dialog to
 * edit values and a removal item button to remove container of the attribute.
 * 
 */
public class EnumSetLayoutOptionValuePropertySection extends AbstractEditorDialogPropertySection {

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
    public EnumSetLayoutOptionValuePropertySection(LayoutOption layoutOption) {
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
        return DescriptionPackage.eINSTANCE.getEnumSetLayoutOption_Values();
    }

    @Override
    protected String getFeatureAsText() {
        String string = new String();

        if (eObject instanceof EnumSetLayoutOption && eObject.eGet(getFeature()) != null) {
            List<?> values = (List<?>) eObject.eGet(getFeature());
            Iterator<?> iterator = values.iterator();
            while (iterator.hasNext()) {
                EObject eObj = (EObject) iterator.next();
                string += getAdapterFactoryLabelProvider(eObj).getText(eObj);
                if (iterator.hasNext())
                    string += ", ";
            }
        }
        return string;
    }

    @Override
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        eObject = layoutOption;

        ((FormData) text.getLayoutData()).right = new FormAttachment(91, 0);
        ((FormData) button.getLayoutData()).left = new FormAttachment(91, 0);
        ((FormData) button.getLayoutData()).right = new FormAttachment(94, 0);

        removeOverrideButton = LayoutOptionPropertiesUtils.createRemoveOptionButton(button, composite, (ViewpointPropertySheetPage) tabbedPropertySheetPage, getWidgetFactory(), layoutOption);

        help = LayoutOptionPropertiesUtils.createHelpLabel(getWidgetFactory(), composite, text, nameLabel, getHelpIcon(), layoutOption);
    }

    @Override
    protected SelectionListener createButtonListener() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                List<?> currentValues = (List<?>) eObject.eGet(getFeature());
                List<?> choiceOfValues = getChoiceOfValues(currentValues);
                FeatureEditorDialog dialog = new FeatureEditorDialog(composite.getShell(), getAdapterFactoryLabelProvider(eObject), eObject, getFeature().getEType(), currentValues,
                        layoutOption.getLabel(), choiceOfValues, false, false, choiceOfValues != null);

                dialog.open();
                List<?> result = dialog.getResult();

                // Dialog returns null reference if closed/cancelled.
                if (result != null)
                    handleFeatureModified(result);
            }
        };
    }

    /**
     * Fetches the list of available values and values already set without duplicates..
     * 
     * @param currentValues
     *            the list of current values to remove from available choice of values.
     * 
     * @return The list of available values for the feature.
     */
    @Override
    protected List<?> getChoiceOfValues(List<?> currentValues) {
        final List<EnumLayoutValue> result = new ArrayList<>();
        Map<String, CustomLayoutAlgorithm> layoutProviderRegistry = DiagramUIPlugin.getPlugin().getLayoutAlgorithms();
        CustomLayoutConfiguration layout = (CustomLayoutConfiguration) layoutOption.eContainer();
        CustomLayoutAlgorithm genericLayoutProviderSupplier = layoutProviderRegistry.get(layout.getId());
        Map<String, LayoutOption> layoutOptions = genericLayoutProviderSupplier.getLayoutOptions();
        EnumSetLayoutOption layoutOptionTemplate = (EnumSetLayoutOption) layoutOptions.get(layoutOption.getId());
        EList<EnumLayoutValue> choices = layoutOptionTemplate.getChoices();
        EnumSetLayoutOption layoutOptionEnumSet = (EnumSetLayoutOption) layoutOption;
        for (EnumLayoutValue enumLayoutValue : choices) {
            Optional<EnumLayoutValue> alreadyExistingOption = layoutOptionEnumSet.getValues().stream().filter((value) -> value.getName().equals(enumLayoutValue.getName())).findFirst();
            if (!alreadyExistingOption.isPresent()) {
                result.add(EcoreUtil.copy(enumLayoutValue));
            } else {
                result.add(alreadyExistingOption.get());
            }
        }
        return result;
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
